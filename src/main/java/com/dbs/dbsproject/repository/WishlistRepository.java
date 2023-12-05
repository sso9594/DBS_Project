package com.dbs.dbsproject.repository;

import com.dbs.dbsproject.domain.Wishlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Page<Wishlist> findAllByUserId(Pageable pageable, String id);
}
