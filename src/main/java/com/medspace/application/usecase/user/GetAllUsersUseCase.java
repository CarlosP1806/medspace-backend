package com.medspace.application.usecase.user;

import java.util.List;
import com.medspace.application.service.UserService;
import com.medspace.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetAllUsersUseCase {

    @Inject
    UserService userService;

    public List<User> execute() {
        return userService.getAllUsers();
    }
}
