package com.dbs.dbsproject.repository;

import com.dbs.dbsproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginid(String loginId);
}
