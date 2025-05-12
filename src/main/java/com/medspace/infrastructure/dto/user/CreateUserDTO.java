package com.medspace.infrastructure.dto.user;

import com.medspace.domain.model.TenantSpecialty;
import com.medspace.domain.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    @NotBlank
    @Size(max = 30, message = "Name must not exceed 30 characters")
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String pfpPath;

    @NotBlank
    @Size(max = 500, message = "Bio must not exceed 500 characters")
    private String bio;

    @NotNull
    @Pattern(regexp = "LANDLORD|TENANT|ANALYST",
            message = "User type must be one of: LANDLORD, TENANT, ANALYST")
    private String userType;

    private Long tenantSpecialtyId;

    private String tenantLicensePath;



    public User toUser() {
        User user = new User();
        user.setFullName(this.fullName);
        user.setEmail(this.email);
        user.setPhoneNumber(this.phoneNumber);
        user.setPfpPath(this.pfpPath);
        user.setTenantLicensePath(this.tenantLicensePath);
        user.setBio(bio);
        user.setUserType(User.UserType.valueOf(this.userType.trim())); // Added trim() for safety

        // Only set tenant specialty for TENANT users and when the ID is provided
        if (this.tenantSpecialtyId != null && user.getUserType() == User.UserType.TENANT) {
            TenantSpecialty specialty = new TenantSpecialty();
            specialty.setId(this.tenantSpecialtyId);
            user.setTenantSpecialty(specialty);
        }

        return user;
    }
}
