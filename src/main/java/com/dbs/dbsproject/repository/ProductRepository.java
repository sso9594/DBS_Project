package com.dbs.dbsproject.repository;

import com.dbs.dbsproject.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOrderByProductidDesc(Pageable pageable);
}
