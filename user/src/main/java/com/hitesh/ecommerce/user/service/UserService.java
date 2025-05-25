package com.hitesh.ecommerce.user.service;

import com.hitesh.ecommerce.user.dto.UserRequest;
import com.hitesh.ecommerce.user.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(Long id, UserRequest request);
    void deleteUser(Long id);
}
