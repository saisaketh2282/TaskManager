package com.TaskMaster.TaskTrackingSystem.dto;

import lombok.Data;
import java.util.Set;

@Data
public class TeamDTO {
    private Long id;
    private String name;
    private Set<Long> memberIds;
} 