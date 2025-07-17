package com.TaskMaster.TaskTrackingSystem.dto;

import lombok.Data;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password; // Only for registration/login
    private Set<String> roles;
} 