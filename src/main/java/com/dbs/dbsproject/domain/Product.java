package com.dbs.dbsproject.domain;

import com.dbs.dbsproject.dto.ProductDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productid;
    @Column(name = "user_id")
    private String userid;

    private String title;

    private String content;

    private String thumbnail;
    private Integer price;

    private boolean state;

    @UpdateTimestamp
    private LocalDateTime date;

    public Product update(ProductDto productDto){
        this.userid = productDto.getUserid();
        this.title = productDto.getTitle();
        this.content = productDto.getContent();
        this.price = productDto.getPrice();
        this.date = productDto.getDate();
        return this;
    }
}
