package com.dbs.dbsproject.repository;

import com.dbs.dbsproject.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
