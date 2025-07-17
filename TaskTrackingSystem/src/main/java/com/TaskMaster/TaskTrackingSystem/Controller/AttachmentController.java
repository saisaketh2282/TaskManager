package com.TaskMaster.TaskTrackingSystem.Controller;

import com.TaskMaster.TaskTrackingSystem.model.Attachment;
import com.TaskMaster.TaskTrackingSystem.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/upload/{taskId}")
    public ResponseEntity<Attachment> uploadAttachment(@PathVariable Long taskId, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(attachmentService.uploadAttachment(taskId, file));
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Attachment>> getAttachmentsByTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(attachmentService.getAttachmentsByTask(taskId));
    }
} 