
package com.medspace.application.usecase.user;



import com.medspace.application.service.UserService;

import com.medspace.domain.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import com.medspace.domain.model.User;
import jakarta.inject.Inject;



@ApplicationScoped
public class GetTenantCountUseCase {

    private final UserRepository userRepository;

    @Inject
    public GetTenantCountUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long execute() {
        return userRepository.countByUserType(User.UserType.TENANT);
    }
}




