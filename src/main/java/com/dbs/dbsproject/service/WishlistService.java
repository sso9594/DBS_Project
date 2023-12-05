package com.dbs.dbsproject.service;

import com.dbs.dbsproject.domain.Wishlist;
import com.dbs.dbsproject.dto.WishDto;
import com.dbs.dbsproject.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public Wishlist save(WishDto wishDto){
        return wishlistRepository.save(wishDto.toEntity());
    }

    public Page<Wishlist> findAllbyUserId(PageRequest pageRequest, String id){
        return wishlistRepository.findAllByUserId(pageRequest, id);
    }
}
