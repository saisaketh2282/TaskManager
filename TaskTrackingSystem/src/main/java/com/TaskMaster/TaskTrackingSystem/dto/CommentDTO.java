package com.TaskMaster.TaskTrackingSystem.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private String content;
    private Long authorId;
    private Long taskId;
    private LocalDateTime timestamp;
} 