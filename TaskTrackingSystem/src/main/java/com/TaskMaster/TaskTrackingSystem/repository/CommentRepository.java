package com.TaskMaster.TaskTrackingSystem.repository;

import com.TaskMaster.TaskTrackingSystem.model.Comment;
import com.TaskMaster.TaskTrackingSystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTask(Task task);
} 