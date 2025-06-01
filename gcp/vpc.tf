# Create VPC
resource "google_compute_network" "main_vpc" {
  name                    = "main-vpc"
  auto_create_subnetworks = false
}

# Create a subnetwork in the VPC
resource "google_compute_subnetwork" "main_subnet" {
  name          = "main-subnet"
  ip_cidr_range = "10.0.0.0/16"
  network       = google_compute_network.main_vpc.id
}

# Create VPC Connector so Cloud Run etc can access VPC resources
resource "google_vpc_access_connector" "main_vpc_connector" {
  name          = "main-vpc-connector"
  region        = var.region
  network       = google_compute_network.main_vpc.name
  ip_cidr_range = "10.8.0.0/28" # Must be /28, separate from subnet CIDR
  max_instances = 3
  min_instances = 2
}


# Reserve IP range for private services
resource "google_compute_global_address" "private_ip_address" {
  name          = "private-ip-address"
  purpose       = "VPC_PEERING"
  address_type  = "INTERNAL"
  prefix_length = 16
  network       = google_compute_network.main_vpc.id
}

# Create private connection
resource "google_service_networking_connection" "private_vpc_connection" {
  network                 = google_compute_network.main_vpc.id
  service                 = "servicenetworking.googleapis.com"
  reserved_peering_ranges = [google_compute_global_address.private_ip_address.name]
}
