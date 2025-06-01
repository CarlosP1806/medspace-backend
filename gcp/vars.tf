variable "project_id" {
  default = "med-space-c2eba"
}
variable "region" {
  default = "us-central1"
}

variable "github_repo_backend" {
  description = "GitHub repository in format 'owner/repo'"
  type        = string
  default     = "CarlosP1806/medspace-backend"
}

variable "github_repo_python_backend" {
  description = "GitHub repository for Python backend in format 'owner/repo'"
  type        = string
  default     = "Jose-AE/medspace-python"
}

variable "github_user_1" {
  description = "The GitHub organization name"
  type        = string
  default     = "CarlosP1806"
}

variable "github_user_2" {
  description = "The GitHub organization name"
  type        = string
  default     = "Jose-AE"
}



variable "backend_service_name" {
  description = "Quarkus medspace backend "
  type        = string
  default     = "medspace-backend"
}

variable "python_backend_service_name" {
  description = "Python backend service name"
  type        = string
  default     = "python-backend"
}

variable "database_password" {
  description = "Database password"
  type        = string
  default     = "root"
  sensitive   = true
}

variable "firebase_service_account_json_path" {
  default = "../src/main/resources/firebase-service-account.json"
  type    = string
}
