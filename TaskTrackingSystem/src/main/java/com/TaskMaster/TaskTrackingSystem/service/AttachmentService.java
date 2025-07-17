package com.TaskMaster.TaskTrackingSystem.service;

import com.TaskMaster.TaskTrackingSystem.dto.AttachmentDTO;
import com.TaskMaster.TaskTrackingSystem.model.Attachment;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface AttachmentService {
    Attachment uploadAttachment(Long taskId, MultipartFile file);
    List<Attachment> getAttachmentsByTask(Long taskId);
} 