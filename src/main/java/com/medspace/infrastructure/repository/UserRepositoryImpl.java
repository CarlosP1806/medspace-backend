package com.medspace.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import com.medspace.domain.model.User;
import com.medspace.domain.repository.UserRepository;
import com.medspace.infrastructure.entity.UserEntity;
import com.medspace.infrastructure.mapper.TenantSpecialtyMapper;
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

    @Override
    @Transactional
    public User updateUserById(Long id, User user) {
        UserEntity originalUserEntity = findById(id);

        if (originalUserEntity == null) {
            return null;
        }

        if (user.getFullName() != null) {
            originalUserEntity.setFullName(user.getFullName());
        }

        if (user.getEmail() != null) {
            originalUserEntity.setEmail(user.getEmail());
        }

        if (user.getPhoneNumber() != null) {
            originalUserEntity.setPhoneNumber(user.getPhoneNumber());
        }

        if (user.getPfpPath() != null) {
            originalUserEntity.setPfpPath(user.getPfpPath());
        }

        if (user.getBio() != null) {
            originalUserEntity.setBio(user.getBio());
        }

        if (user.getTenantSpecialty() != null) {
            originalUserEntity
                    .setTenantSpecialty(TenantSpecialtyMapper.toEntity(user.getTenantSpecialty()));
        }

        if (user.getTenantLicensePath() != null) {
            originalUserEntity.setTenantLicensePath(user.getTenantLicensePath());
        }

        if (user.getFirebaseUid() != null) {
            originalUserEntity.setFirebaseUid(user.getFirebaseUid());
        }

        if (user.getUserType() != null) {
            originalUserEntity.setUserType(user.getUserType());
        }

        if (user.getAverageRating() != null) {
            originalUserEntity.setAverageRating(user.getAverageRating());
        }

        if (user.getStripeCustomerId() != null) {
            originalUserEntity.setStripeCustomerId(user.getStripeCustomerId());
        }

        if (user.getDefaultPaymentMethod() != null) {
            originalUserEntity.setDefaultPaymentMethod(user.getDefaultPaymentMethod());
        }


        return UserMapper.toDomain(originalUserEntity);
    }

    @Override
    public long countByUserType(User.UserType userType) {
        return count("userType", userType);
    }


}


