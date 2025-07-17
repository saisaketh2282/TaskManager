package com.TaskMaster.TaskTrackingSystem.repository;

import com.TaskMaster.TaskTrackingSystem.model.Attachment;
import com.TaskMaster.TaskTrackingSystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findByTask(Task task);
} 