-- Insert default tenant specialty
INSERT INTO tenant_specialties (name) VALUES ('General Medicine');

INSERT INTO users (
    full_name, email, firebase_uid, pfp_path, phone_number,
    created_at, user_type, tenant_specialty_id, tenant_license_path,
    average_rating, stripe_customer_id, default_payment_method
) VALUES 
(
    'Tenant User', 'tenant@gmail.com', 'lhei5Tghcvh827DqeQOuH4S7rL82', 'https://example.com/tenant.jpg', '1111111111',
    CURRENT_TIMESTAMP, 'TENANT', 1, 'https://example.com/license1.pdf',
    4.5, 'cus_123tenant', 'pm_123tenant'
),
(
    'Landlord User', 'landlord@gmail.com', 'p1QZXIMnQgfLv0P1cIQSBUktmiE3', 'https://example.com/landlord.jpg', '2222222222',
    CURRENT_TIMESTAMP, 'LANDLORD', null, null,
    null, 'cus_123landlord', 'pm_123landlord'
),
(
    'Analyst User', 'analyst@gmail.com', 'EHl5gLFE5KVqhGY9EqIKXl2sO1w2', 'https://example.com/analyst.jpg', '3333333333',
    CURRENT_TIMESTAMP, 'ANALYST', null, null,
    null, null, null
);
