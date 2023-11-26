package com.dbs.dbsproject.dto;

import com.dbs.dbsproject.domain.Product;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private String userid;

    private String title;

    private String content;

    private String img;

    private Integer price;

    private boolean state;

    private LocalDateTime date;

    public Product toEntity(){
        return Product.builder()
                .userid(this.userid)
                .title(this.title)
                .content(this.content)
                .img(this.img)
                .price(this.price)
                .state(this.state)
                .date(this.date)
                .build();
    }

    public ProductDto fromEntity(Product product){
        return ProductDto.builder()
                .userid(product.getUserid())
                .title(product.getTitle())
                .content(product.getContent())
                .img(product.getImg())
                .price(product.getPrice())
                .date(product.getDate())
                .build();
    }
}
