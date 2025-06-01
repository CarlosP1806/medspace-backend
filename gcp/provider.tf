
# Terraform Configuration for GCP
provider "google" {
  credentials = file("terraform-service-account.json")

  project = var.project_id
  region  = var.region
}

#Store Terraform state in GCS
terraform {
  backend "gcs" {
    bucket = "terraform-state-bucket-3213213"
    prefix = "terraform/state"
  }
}
