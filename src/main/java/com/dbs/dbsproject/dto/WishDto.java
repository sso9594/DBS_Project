package com.dbs.dbsproject.dto;

import com.dbs.dbsproject.domain.Wishlist;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishDto {

    private Long wlId;
    private String userId;
    private Long productId;

    public Wishlist toEntity(){
        return Wishlist.builder()
                .wlId(this.wlId)
                .productId(this.productId)
                .userId(this.userId)
                .build();
    }
}
