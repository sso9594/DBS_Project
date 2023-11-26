package com.dbs.dbsproject.dto;

import com.dbs.dbsproject.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequest {
    private String name;
    private String loginid;
    private String password;
    private String email;
    private String ph_num;
    private String address;

    public User toEntity(String encodedPassword){
        return User.builder()
                .name(this.name)
                .loginid(this.loginid)
                .password(encodedPassword)
                .email(this.email)
                .ph_num(this.ph_num)
                .address(this.address)
                .build();
    }
}
