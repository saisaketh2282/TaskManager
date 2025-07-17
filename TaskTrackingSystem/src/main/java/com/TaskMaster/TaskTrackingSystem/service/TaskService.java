package com.TaskMaster.TaskTrackingSystem.service;

import com.TaskMaster.TaskTrackingSystem.dto.TaskDTO;
import com.TaskMaster.TaskTrackingSystem.model.Task;
import java.util.List;

public interface TaskService {
    Task createTask(TaskDTO taskDTO);
    Task updateTask(Long taskId, TaskDTO taskDTO);
    void deleteTask(Long taskId);
    Task getTaskById(Long taskId);
    List<Task> getTasksByAssignee(Long assigneeId);
    List<Task> getTasksByStatus(String status);
    List<Task> searchTasks(String query);
    Task assignTask(Long taskId, Long userId);
    List<Task> getAllTasks();
} 