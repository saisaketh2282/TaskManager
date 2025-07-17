package com.TaskMaster.TaskTrackingSystem.dto;

import lombok.Data;

@Data
public class AttachmentDTO {
    private Long id;
    private String filename;
    private String url;
    private Long taskId;
} 