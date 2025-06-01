# Create Artifact Registry repository
resource "google_artifact_registry_repository" "docker_repo" {
  location      = var.region
  repository_id = "medspace-backend"
  description   = "Docker repository for container images"
  format        = "DOCKER"

}
