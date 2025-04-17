package com.medspace.infrastructure.mapper;

import java.sql.Timestamp;
import com.medspace.domain.model.User;
import com.medspace.infrastructure.entity.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        User user = new User();
        user.setId(userEntity.getId());
        user.setFullName(userEntity.getFullName());
        user.setEmail(userEntity.getEmail());
        user.setPasswordHash(userEntity.getPasswordHash());
        user.setProfilePictureUrl(userEntity.getProfilePictureUrl());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        user.setCreatedAt(userEntity.getCreatedAt().toInstant());
        user.setUserType(userEntity.getUserType());
        user.setTenantSpecialty(TenantSpecialityMapper.toDomain(userEntity.getTenantSpecialty()));
        user.setTenantProfessionalLicenseUrl(userEntity.getTenantProfessionalLicenseUrl());
        user.setAverageRating(userEntity.getAverageRating());
        user.setStripeCustomerId(userEntity.getStripeCustomerId());
        user.setDefaultPaymentMethod(userEntity.getDefaultPaymentMethod());

        return user;

    }

    public static UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setFullName(user.getFullName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPasswordHash(user.getPasswordHash());
        userEntity.setProfilePictureUrl(user.getProfilePictureUrl());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setCreatedAt(Timestamp.from(user.getCreatedAt()));
        userEntity.setUserType(user.getUserType());
        userEntity.setTenantSpecialty(TenantSpecialityMapper.toEntity(user.getTenantSpecialty()));
        userEntity.setTenantProfessionalLicenseUrl(user.getTenantProfessionalLicenseUrl());
        userEntity.setAverageRating(user.getAverageRating());
        userEntity.setStripeCustomerId(user.getStripeCustomerId());
        userEntity.setDefaultPaymentMethod(user.getDefaultPaymentMethod());

        return userEntity;

    }
}
