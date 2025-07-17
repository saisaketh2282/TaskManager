package com.TaskMaster.TaskTrackingSystem.service;

import com.TaskMaster.TaskTrackingSystem.dto.UserDTO;
import com.TaskMaster.TaskTrackingSystem.model.User;
import java.util.Optional;

public interface UserService {
    User register(UserDTO userDTO);
    String login(String username, String password);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    User getProfile(Long userId);
    User updateProfile(Long userId, UserDTO userDTO);
} 