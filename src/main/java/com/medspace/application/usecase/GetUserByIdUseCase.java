package com.medspace.application.usecase;

import com.medspace.application.service.UserService;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetUserByIdUseCase {

    @Inject
    UserService userService;

    public User execute(Long userId) {
        return userService.getUserById(userId);
    }

}
