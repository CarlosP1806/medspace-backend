# Create Workload Identity Pool
resource "google_iam_workload_identity_pool" "github_pool" {
  workload_identity_pool_id = "github"
  display_name              = "GitHub Actions Pool"
  description               = "Identity pool for GitHub Actions"

}

# Create Workload Identity Provider
resource "google_iam_workload_identity_pool_provider" "github_provider" {
  workload_identity_pool_id          = google_iam_workload_identity_pool.github_pool.workload_identity_pool_id
  workload_identity_pool_provider_id = "my-repo"
  display_name                       = "GitHub Provider"
  description                        = "OIDC identity pool provider for GitHub Actions"

  attribute_mapping = {
    "google.subject"             = "assertion.sub"
    "attribute.actor"            = "assertion.actor"
    "attribute.repository"       = "assertion.repository"
    "attribute.repository_owner" = "assertion.repository_owner"
  }

  attribute_condition = "assertion.repository_owner == '${var.github_user_1}' || assertion.repository_owner == '${var.github_user_2}'"


  oidc {
    issuer_uri = "https://token.actions.githubusercontent.com"
  }
}


# Grant roles to the Workload Identity Pool on github main backend repository
resource "google_project_iam_member" "github_actions_permissions_backend" {
  for_each = toset([
    "roles/run.admin",
    "roles/artifactregistry.writer",
    "roles/cloudbuild.builds.builder",
    "roles/iam.serviceAccountUser",
    "roles/secretmanager.secretAccessor"
  ])


  project = var.project_id
  role    = each.value
  member  = "principalSet://iam.googleapis.com/${google_iam_workload_identity_pool.github_pool.name}/attribute.repository/${var.github_repo_backend}"
}



# Grant roles to the Workload Identity Pool on python github repository
resource "google_project_iam_member" "github_actions_permissions_python" {
  for_each = toset([
    "roles/run.admin",
    "roles/artifactregistry.writer",
    "roles/cloudbuild.builds.builder",
    "roles/iam.serviceAccountUser",
    "roles/secretmanager.secretAccessor"
  ])


  project = var.project_id
  role    = each.value
  member  = "principalSet://iam.googleapis.com/${google_iam_workload_identity_pool.github_pool.name}/attribute.repository/${var.github_repo_python_backend}"
}

