package com.dbs.dbsproject.service;

import com.dbs.dbsproject.dto.JoinRequest;
import com.dbs.dbsproject.repository.UserRepository;
import com.dbs.dbsproject.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public void join(JoinRequest request){
        userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
    }


}
