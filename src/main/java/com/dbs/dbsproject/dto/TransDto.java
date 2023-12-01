package com.dbs.dbsproject.dto;

import com.dbs.dbsproject.domain.Transaction;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransDto {

    private Long taid;
    private Long productId;
    private String buyerId;
    private String sellerId;
    private String productTitle;
    private boolean state;
    private boolean deliveryState;

    public Transaction toEntity(){
        return Transaction.builder()
                .taid(this.taid)
                .productId(this.productId)
                .buyerId(this.buyerId)
                .sellerId(this.sellerId)
                .productTitle(this.productTitle)
                .state(this.state)
                .deliveryState(this.deliveryState)
                .build();
    }
}
