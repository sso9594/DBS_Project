package com.dbs.dbsproject.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wl_id")
    private Long wlId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "product_id")
    private Long productId;

}
