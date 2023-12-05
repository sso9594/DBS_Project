package com.dbs.dbsproject.dto;

import com.dbs.dbsproject.domain.Product;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long productid;
    private String userid;

    private String title;

    private String content;

    private String thumbnail;

    private Integer price;

    private boolean state;

    private LocalDateTime date;

    public Product toEntity(){
        return Product.builder()
                .productid(this.productid)
                .userid(this.userid)
                .title(this.title)
                .content(this.content)
                .thumbnail(this.thumbnail)
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
                .price(product.getPrice())
                .date(product.getDate())
                .build();
    }
}
