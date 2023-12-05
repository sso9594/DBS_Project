package com.dbs.dbsproject.service;

import com.dbs.dbsproject.domain.User;
import com.dbs.dbsproject.dto.JoinRequest;
import com.dbs.dbsproject.dto.LoginRequest;
import com.dbs.dbsproject.repository.UserRepository;
import com.dbs.dbsproject.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public ResponseEntity join(JoinRequest request) throws NoSuchAlgorithmException {
        userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    public ResponseEntity login(LoginRequest request) throws NoSuchAlgorithmException {
        Optional<User> optionalUser = userRepository.findByLoginid(request.getId());
        if(optionalUser.isEmpty()){
            throw new RuntimeException("Not found Account");
        }

        User user = optionalUser.get();

        if(!Objects.equals(user.getPassword(), encoder.encode(request.getPassword()))){
            System.out.println(encoder.encode(request.getPassword()));
            throw new RuntimeException("Not matches Password");
        }

        return new ResponseEntity("Success", HttpStatus.OK);
    }

}
