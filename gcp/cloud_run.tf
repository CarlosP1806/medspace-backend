# Cloud Run backend service configuration
resource "google_cloud_run_v2_service" "backend_service" {
  name                = var.backend_service_name
  location            = var.region
  deletion_protection = false
  ingress             = "INGRESS_TRAFFIC_ALL"

  lifecycle {
    ignore_changes = [
      template[0].containers[0].image

    ]
  }

  template {
    scaling {
      min_instance_count = 0
      max_instance_count = 1
    }

    volumes {
      name = "firebase-service-account"
      secret {
        secret = google_secret_manager_secret.firebase_service_account_secret.id
      }
    }

    containers {
      image = "us-docker.pkg.dev/cloudrun/container/hello"

      ports {
        container_port = 8080
      }

      volume_mounts {
        name       = "firebase-service-account"
        mount_path = "/secrets"
      }

      env {
        name = "DB_PASSWORD"
        value_source {
          secret_key_ref {
            secret  = google_secret_manager_secret.db_password_secret.id
            version = "latest"
          }
        }
      }

      env {
        name  = "FIREBASE_CREDENTIALS_PATH"
        value = "/secrets/firebase-service-account"
      }

      env {
        name  = "CORS_ORIGINS"
        value = "*"
      }

      env {
        name  = "DB_URL"
        value = "jdbc:mysql://${google_sql_database_instance.medspace_db.private_ip_address}/main"
      }

      env {
        name  = "DB_USERNAME"
        value = "root"
      }
    }

    vpc_access {
      connector = google_vpc_access_connector.main_vpc_connector.id
      egress    = "PRIVATE_RANGES_ONLY" #Only route traffic to VPC for private IPs
    }
  }
}



## IAM Binding to allow all users to invoke the Cloud Run service
resource "google_cloud_run_service_iam_binding" "default" {
  location = google_cloud_run_v2_service.backend_service.location
  service  = google_cloud_run_v2_service.backend_service.name
  role     = "roles/run.invoker"
  members = [
    "allUsers"
  ]
}



# Cloud Run python backend service configuration
resource "google_cloud_run_v2_service" "python_backend_service" {
  name                = var.python_backend_service_name
  location            = var.region
  deletion_protection = false
  ingress             = "INGRESS_TRAFFIC_INTERNAL_ONLY"
  #ingress = "INGRESS_TRAFFIC_ALL"

  lifecycle {
    ignore_changes = [
      template[0].containers[0].image

    ]
  }

  template {
    scaling {
      min_instance_count = 0
      max_instance_count = 1
    }


    containers {
      image = "us-docker.pkg.dev/cloudrun/container/hello"

      ports {
        container_port = 8080
      }


      env {
        name = "DB_PASSWORD"
        value_source {
          secret_key_ref {
            secret  = google_secret_manager_secret.db_password_secret.id
            version = "latest"
          }
        }
      }

      env {
        name  = "DB_URL"
        value = "jdbc:mysql://${google_sql_database_instance.medspace_db.private_ip_address}/main"
      }

      env {
        name  = "DB_USERNAME"
        value = "root"
      }
    }

    vpc_access {
      connector = google_vpc_access_connector.main_vpc_connector.id
      egress    = "PRIVATE_RANGES_ONLY" #Only route traffic to VPC for private IPs
    }
  }
}



## IAM Binding to allow all users to invoke the Cloud Run main backend service
resource "google_cloud_run_service_iam_binding" "python_backend_default" {
  location = google_cloud_run_v2_service.python_backend_service.location
  service  = google_cloud_run_v2_service.python_backend_service.name
  role     = "roles/run.invoker"
  members = [
    "allUsers"
  ]
}
