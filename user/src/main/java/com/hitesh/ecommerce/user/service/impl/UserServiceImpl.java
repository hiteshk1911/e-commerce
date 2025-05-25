package com.hitesh.ecommerce.user.service.impl;

import com.hitesh.ecommerce.user.dto.UserRequest;
import com.hitesh.ecommerce.user.dto.UserResponse;
import com.hitesh.ecommerce.user.entity.User;
import com.hitesh.ecommerce.user.entity.UserRole;
import com.hitesh.ecommerce.user.repository.UserRepository;
import com.hitesh.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest request) {
        try {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "User with this email already exists");
            }
            User user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .role(UserRole.valueOf(request.getRole().toUpperCase()))
                    .build();

            User saved = userRepository.save(user);
            return mapToResponse(saved);
        } catch (Exception ex) {
            log.error("Error while creating user", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while creating user");
        }
    }

    @Override
    public UserResponse getUserById(Long id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
            return mapToResponse(user);
        } catch (Exception ex) {
            log.error("Error while fetching user", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while fetching user");
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        try {
            return userRepository.findAll().stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error while fetching all users", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while fetching all users");
        }
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

            user.setName(request.getName());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setRole(UserRole.valueOf(request.getRole().toUpperCase()));

            return mapToResponse(userRepository.save(user));
        } catch (Exception ex) {
            log.error("Error while updating user", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while updating user");
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
            userRepository.deleteById(id);
        } catch (Exception ex) {
            log.error("Error while deleting user", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while deleting user");
        }
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().name())
                .build();
    }
}