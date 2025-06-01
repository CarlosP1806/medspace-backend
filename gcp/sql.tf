resource "google_sql_database_instance" "medspace_db" {
  name             = "medspace-db"
  database_version = "MYSQL_8_0"
  region           = "us-central1"

  depends_on = [google_service_networking_connection.private_vpc_connection]

  lifecycle {
    ignore_changes = [
      settings[0].ip_configuration[0].authorized_networks,
    ]
  }

  settings {
    tier                        = "db-f1-micro"
    edition                     = "ENTERPRISE"
    availability_type           = "ZONAL"
    deletion_protection_enabled = true
    disk_autoresize             = false
    disk_size                   = 10
    disk_type                   = "PD_HDD"

    ip_configuration {
      ipv4_enabled    = true
      private_network = google_compute_network.main_vpc.id
    }

  }
}

#create root user for the database
resource "google_sql_user" "users" {
  name     = "root"
  instance = google_sql_database_instance.medspace_db.name
  password = var.database_password
}

# Create the main database
resource "google_sql_database" "main_database" {
  name     = "main"
  instance = google_sql_database_instance.medspace_db.name
}
