package com.hitesh.ecommerce.auth.service;

import com.hitesh.ecommerce.auth.dto.AuthenticationRequest;
import com.hitesh.ecommerce.auth.dto.AuthenticationResponse;
import com.hitesh.ecommerce.auth.dto.RegisterRequest;
import com.hitesh.ecommerce.auth.model.User;
import com.hitesh.ecommerce.auth.repository.UserRepository;
import com.hitesh.ecommerce.auth.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);
        String token = jwtUtils.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        String token = jwtUtils.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public boolean validate(String token) {
        return jwtUtils.validateToken(token);
    }
}
