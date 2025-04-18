package com.medspace.application.usecase.user;

import java.io.InputStream;
import com.medspace.application.service.UserService;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateUserUseCase {

    @Inject
    UserService userService;

    public void execute(User user, InputStream profilePhoto, InputStream professionalLicense) {
        // Upload profile photo if exists
        if (profilePhoto != null) {
            // Implement logic
            String profilePhotoUrl = "";
            user.setProfilePictureUrl(profilePhotoUrl);
        }

        // Upload professional license if exists and user is a tenant
        if (professionalLicense != null && user.getUserType() == User.UserType.TENANT) {
            String licenseUrl = "";
            user.setTenantProfessionalLicenseUrl(licenseUrl);
        }



        userService.createUser(user);
    }

}
