package com.TaskMaster.TaskTrackingSystem.service;

import com.TaskMaster.TaskTrackingSystem.dto.CommentDTO;
import com.TaskMaster.TaskTrackingSystem.model.Comment;
import java.util.List;

public interface CommentService {
    Comment addComment(CommentDTO commentDTO);
    List<Comment> getCommentsByTask(Long taskId);
} 