package com.medspace.domain.repository;

import java.util.List;
import com.medspace.domain.model.User;

public interface UserRepository {
    public User insertUser(User user);

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public void deleteUserById(Long id);
}
