package com.fitness.userservice.Service;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository Repository;

    public UserResponse register(RegisterRequest request) {
        if(Repository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());

        User savedResponse = Repository.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedResponse.getId());
        userResponse.setEmail(savedResponse.getEmail());
        userResponse.setPassword(savedResponse.getPassword());
        userResponse.setFirstname(savedResponse.getFirstname());
        userResponse.setLastname(savedResponse.getLastname());
        userResponse.setCreatedAt(savedResponse.getCreatedAt());
        userResponse.setUpdatedAt(savedResponse.getUpdatedAt());

        return userResponse;
    }


    public UserResponse getUserProfile(String userId) {
        User user  = Repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not Found"));
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());

        return userResponse;
    }

    public Boolean existsByuserId(String userId) {
        log.info("Calling User API for Validation userId : {}",userId);
        return Repository.existsById(userId);
    }


}
