
# Firebase Service Account Secret
resource "google_secret_manager_secret" "firebase_service_account_secret" {

  secret_id = "firebase-service-account"

  replication {
    auto {}
  }

}

resource "google_secret_manager_secret_version" "firebase_service_account_secret_version" {
  secret      = google_secret_manager_secret.firebase_service_account_secret.id
  secret_data = file(var.firebase_service_account_json_path)
}



# Database Password Secret
resource "google_secret_manager_secret" "db_password_secret" {

  secret_id = "db-password-secret"

  replication {
    auto {}
  }

}

resource "google_secret_manager_secret_version" "db_password_secret_version" {
  secret      = google_secret_manager_secret.db_password_secret.id
  secret_data = var.database_password

}
