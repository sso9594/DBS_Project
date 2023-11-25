package com.dbs.dbsproject.repository;

import com.dbs.dbsproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
