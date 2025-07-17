package com.TaskMaster.TaskTrackingSystem.service.impl;

import com.TaskMaster.TaskTrackingSystem.dto.CommentDTO;
import com.TaskMaster.TaskTrackingSystem.model.Comment;
import com.TaskMaster.TaskTrackingSystem.model.Task;
import com.TaskMaster.TaskTrackingSystem.model.User;
import com.TaskMaster.TaskTrackingSystem.repository.CommentRepository;
import com.TaskMaster.TaskTrackingSystem.repository.TaskRepository;
import com.TaskMaster.TaskTrackingSystem.repository.UserRepository;
import com.TaskMaster.TaskTrackingSystem.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Comment addComment(CommentDTO commentDTO) {
        User author = userRepository.findById(commentDTO.getAuthorId()).orElseThrow(() -> new RuntimeException("User not found"));
        Task task = taskRepository.findById(commentDTO.getTaskId()).orElseThrow(() -> new RuntimeException("Task not found"));
        Comment comment = Comment.builder()
                .content(commentDTO.getContent())
                .author(author)
                .task(task)
                .timestamp(LocalDateTime.now())
                .build();
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        return commentRepository.findByTask(task);
    }
} 