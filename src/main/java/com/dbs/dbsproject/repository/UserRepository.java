package com.dbs.dbsproject.repository;

import com.dbs.dbsproject.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//    @PersistenceContext
//    private EntityManager em;
//
//    public void save(User user){
//        Long userid = user.getUserid();
//        String name = user.getName();
//        String loginid = user.getLoginid();
//        String password = user.getPassword();
//        String email = user.getEmail();
//        String ph_num = user.getPh_num();
//        String address = user.getAddress();
//
//        String sql = "INSERT INTO user (user_id, address, email, loginid, name, password, ph_num)" +
//                " VALUE (" + userid + "," + "\"" + address + "\"," + "\"" + email + "\"," + "\"" + loginid + "\","
//                + "\"" + name + "\"," + "\"" + password + "\"," + "\"" + ph_num + "\")";
//
//        TypedQuery<User> query = em.createQuery(sql, User.class);
//    }
     Optional<User> findByLoginid(String loginId);
}
