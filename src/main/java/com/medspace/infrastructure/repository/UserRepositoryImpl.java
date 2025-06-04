package com.medspace.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import com.medspace.domain.model.TenantSpecialty;
import com.medspace.domain.model.User;
import com.medspace.domain.repository.UserRepository;
import com.medspace.infrastructure.entity.TenantSpecialtyEntity;
import jakarta.persistence.criteria.Join;

import com.medspace.infrastructure.entity.UserEntity;
import com.medspace.infrastructure.mapper.TenantSpecialtyMapper;
import com.medspace.infrastructure.mapper.UserMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import jakarta.persistence.criteria.Predicate;


@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<UserEntity, Long> {
    @Inject
    EntityManager em;

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

    @Override
    public TenantSpecialty findSpecialtyByNameIgnoreCase(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<TenantSpecialtyEntity> query = cb.createQuery(TenantSpecialtyEntity.class);
        Root<TenantSpecialtyEntity> root = query.from(TenantSpecialtyEntity.class);

        Predicate predicate = cb.equal(cb.lower(root.get("name")), name.toLowerCase());
        query.select(root).where(predicate);

        TenantSpecialtyEntity entity =
                em.createQuery(query).getResultStream().findFirst().orElse(null);
        if (entity == null) {
            return null;
        }
        // Conversión a dominio
        return new TenantSpecialty(entity.getId(), entity.getName());
    }

    @Override
    public long countByUserTypeAndTenantSpecialty(User.UserType userType,
            TenantSpecialty specialty) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<UserEntity> user = query.from(UserEntity.class);

        Predicate userTypePredicate = cb.equal(user.get("userType"), userType);

        Join<UserEntity, TenantSpecialtyEntity> specialtyJoin = user.join("tenantSpecialty");
        Predicate specialtyPredicate = cb.equal(specialtyJoin.get("id"), specialty.getId());

        query.select(cb.count(user)).where(cb.and(userTypePredicate, specialtyPredicate));

        return em.createQuery(query).getSingleResult();
    }


}


