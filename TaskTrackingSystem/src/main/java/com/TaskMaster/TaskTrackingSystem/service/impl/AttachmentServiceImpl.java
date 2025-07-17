package com.TaskMaster.TaskTrackingSystem.service.impl;

import com.TaskMaster.TaskTrackingSystem.model.Attachment;
import com.TaskMaster.TaskTrackingSystem.model.Task;
import com.TaskMaster.TaskTrackingSystem.repository.AttachmentRepository;
import com.TaskMaster.TaskTrackingSystem.repository.TaskRepository;
import com.TaskMaster.TaskTrackingSystem.service.AttachmentService;
import com.TaskMaster.TaskTrackingSystem.util.FileStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private FileStorageUtil fileStorageUtil;

    @Override
    public Attachment uploadAttachment(Long taskId, MultipartFile file) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        String url;
        try {
            url = fileStorageUtil.saveFile(file);
        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        }
        Attachment attachment = Attachment.builder()
                .filename(file.getOriginalFilename())
                .url(url)
                .task(task)
                .build();
        return attachmentRepository.save(attachment);
    }

    @Override
    public List<Attachment> getAttachmentsByTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        return attachmentRepository.findByTask(task);
    }
} 