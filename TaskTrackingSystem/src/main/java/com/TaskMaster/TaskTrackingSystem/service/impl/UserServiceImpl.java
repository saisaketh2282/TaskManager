package com.TaskMaster.TaskTrackingSystem.service.impl;

import com.TaskMaster.TaskTrackingSystem.dto.UserDTO;
import com.TaskMaster.TaskTrackingSystem.model.User;
import com.TaskMaster.TaskTrackingSystem.repository.UserRepository;
import com.TaskMaster.TaskTrackingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    // @Autowired private JwtUtil jwtUtil; // To be implemented

    @Override
    public User register(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .roles(userDTO.getRoles())
                .build();
        return userRepository.save(user);
    }

    @Override
    public String login(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            // return jwtUtil.generateToken(userOpt.get());
            return "JWT_TOKEN_PLACEHOLDER";
        }
        throw new RuntimeException("Invalid credentials");
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getProfile(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateProfile(Long userId, UserDTO userDTO) {
        User user = getProfile(userId);
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        return userRepository.save(user);
    }
} 