package com.citytech.assessment.user.dao;

import com.citytech.assessment.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, String> {
    boolean existsByUserId(String userId);
}
