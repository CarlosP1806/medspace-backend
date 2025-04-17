package com.medspace.application.usecase;

import com.medspace.application.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DeleteUserByIdUseCase {

    @Inject
    UserService userService;

    public void execute(Long userId) {
        userService.deleteUserById(userId);
    }
}
