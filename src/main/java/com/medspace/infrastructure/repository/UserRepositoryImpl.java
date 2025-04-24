package com.medspace.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import com.medspace.domain.model.User;
import com.medspace.domain.repository.UserRepository;
import com.medspace.infrastructure.entity.UserEntity;
import com.medspace.infrastructure.mapper.UserMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<UserEntity, Long> {

    @Override
    @Transactional
    public User insertUser(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        persist(userEntity);
        user = UserMapper.toDomain(userEntity);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = listAll();
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            users.add(UserMapper.toDomain(userEntity));
        }
        return users;
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = findById(id);
        if (userEntity == null) {
            return null;
        }
        return UserMapper.toDomain(userEntity);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        UserEntity userEntity = findById(id);
        if (userEntity != null) {
            delete(userEntity);
        }
    }

    @Override
    public User getUserByFirebaseId(String id) {
        UserEntity userEntity = find("firebaseUid", id).firstResult();

        if (userEntity == null) {
            return null;
        }

        return UserMapper.toDomain(userEntity);
    }

}


