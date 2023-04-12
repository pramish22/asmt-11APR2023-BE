package com.citytech.assessment.user.auth;

import com.citytech.assessment.entity.UserCredentials;
import com.citytech.assessment.security.TokenProvider;
import com.citytech.assessment.user.auth.dto.AuthResponse;
import com.citytech.assessment.user.auth.dto.LoginRequest;
import com.citytech.assessment.user.dao.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest loginRequest) {
        String token = authenticate(loginRequest.getUserId(), loginRequest.getPassword());

        return buildAuthResponse(token);
    }

    private String authenticate(String userId, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userId,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.generateJWTToken(userId, 300000);
    }

    private AuthResponse buildAuthResponse(String token) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);

        return authResponse;
    }

    public String createAdmin() {
        if (!userCredentialsRepository.existsByUserId("default_admin")) {
            UserCredentials userCredentials = new UserCredentials();

            userCredentials.setUserId("default_admin");
            userCredentials.setPassword(passwordEncoder.encode("default_password"));

            userCredentialsRepository.save(userCredentials);

            return "Admin created Successfully";
        }

        return "Admin already exists!";
    }
}
