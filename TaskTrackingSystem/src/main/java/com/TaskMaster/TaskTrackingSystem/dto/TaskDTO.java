package com.TaskMaster.TaskTrackingSystem.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String status;
    private Long assigneeId;
    private Long projectId;
} 