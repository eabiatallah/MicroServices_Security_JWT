package com.eaa.spring_security_jwt.repository;


import com.eaa.spring_security_jwt.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);

    UserInfo findByEmail(String email);
}
