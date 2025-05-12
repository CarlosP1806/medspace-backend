package com.medspace.application.usecase.user;

import com.medspace.application.service.UserService;
import com.medspace.domain.model.User;
import com.medspace.domain.model.User.UserType;
import com.medspace.infrastructure.dto.user.EditUserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class EditUserProfileByIdUseCase {

    @Inject
    UserService userService;

    public void execute(Long userId, EditUserDTO editUserDTO) {
        User user = userService.getUserById(userId);


        if (editUserDTO.getFullName() != null) {
            user.setFullName(editUserDTO.getFullName());
        }

        if (editUserDTO.getEmail() != null) {
            user.setEmail(editUserDTO.getEmail());
        }

        if (editUserDTO.getPhoneNumber() != null) {
            user.setPhoneNumber(editUserDTO.getPhoneNumber());
        }

        if (editUserDTO.getPfpPath() != null) {
            user.setPfpPath(editUserDTO.getPfpPath());
        }

        if (editUserDTO.getBio() != null) {
            user.setBio(editUserDTO.getBio());
        }


        // Tenant-specific fields
        if (user.getUserType() == UserType.TENANT) {
            if (editUserDTO.getTenantSpecialtyId() != null) {
                user.setTenantSpecialty(
                        userService.getTenantSpecialtyById(editUserDTO.getTenantSpecialtyId()));
            }

            if (editUserDTO.getTenantLicensePath() != null) {
                user.setBio(editUserDTO.getTenantLicensePath());
            }
        }


        userService.updateUserById(userId, user);
    }

}
