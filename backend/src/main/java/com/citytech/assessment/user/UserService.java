package com.citytech.assessment.user;

import com.citytech.assessment.entity.User;
import com.citytech.assessment.entity.UserCredentials;
import com.citytech.assessment.user.auth.dto.NewUserRequest;
import com.citytech.assessment.user.dao.UserCredentialsRepository;
import com.citytech.assessment.user.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    public User findByUserId(String id) {
        return userRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String addUser(NewUserRequest newUserRequest) {
        User user = new User();
        UserCredentials userCredentials = new UserCredentials();

        user.setUserId(newUserRequest.getUserId());
        user.setName(newUserRequest.getName());
        userCredentials.setUserId(newUserRequest.getUserId());
        userCredentials.setPassword(passwordEncoder.encode(generateRandomPassword(10)));

        userRepository.save(user);
        userCredentialsRepository.save(userCredentials);

        return "New User added Successfully";
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }

        return sb.toString();
    }
}
