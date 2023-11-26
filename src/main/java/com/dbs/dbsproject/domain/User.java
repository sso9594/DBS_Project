package com.dbs.dbsproject.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userid;

    private String name;

    private String loginid;

    private String password;

    private String email;

    private String ph_num;

    private String address;

}
