package com.medspace.application.usecase.user;

import com.medspace.application.service.UserService;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateUserUseCase {

    @Inject
    UserService userService;

    public void execute(User user) {
        userService.createUser(user);
    }

}
