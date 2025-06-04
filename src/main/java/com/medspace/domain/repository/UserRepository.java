package com.medspace.domain.repository;

import java.util.List;
import com.medspace.domain.model.TenantSpecialty;
import com.medspace.domain.model.User;

public interface UserRepository {
    public User insertUser(User user);

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User getUserByFirebaseId(String id);

    public void deleteUserById(Long id);

    public User updateUserById(Long id, User user);

    long countByUserType(User.UserType userType);

    long countByUserTypeAndTenantSpecialty(User.UserType userType, TenantSpecialty specialty);

    TenantSpecialty findSpecialtyByNameIgnoreCase(String name);


}
