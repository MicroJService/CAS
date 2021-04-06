package org.microjservice.cas.dao;

import org.microjservice.cas.entity.UserEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User Dao
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Repository
public interface UserDao extends JpaRepository<UserEntity, Long>
{
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUserName(String userName);

    Optional<UserEntity> findByCountryCodeAndPhone(Integer countryCode, Long email);
}
