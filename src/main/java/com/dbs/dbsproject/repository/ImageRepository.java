package com.dbs.dbsproject.repository;

import com.dbs.dbsproject.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByProductid(Long id);
}
