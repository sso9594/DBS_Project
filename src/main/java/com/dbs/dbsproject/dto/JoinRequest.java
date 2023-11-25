package com.dbs.dbsproject.dto;

import com.dbs.dbsproject.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinRequest {
    private String name;
    private String id;
    private String password;
    private String email;
    private String ph_num;
    private String address;

    public User toEntity(String encodedPassword){
        return User.builder()
                .id(this.id)
                .password(encodedPassword)
                .email(this.email)
                .ph_num(this.ph_num)
                .address(this.address)
                .build();
    }
}
