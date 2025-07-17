package com.TaskMaster.TaskTrackingSystem.service.impl;

import com.TaskMaster.TaskTrackingSystem.dto.TaskDTO;
import com.TaskMaster.TaskTrackingSystem.model.Task;
import com.TaskMaster.TaskTrackingSystem.model.User;
import com.TaskMaster.TaskTrackingSystem.model.Team;
import com.TaskMaster.TaskTrackingSystem.repository.TaskRepository;
import com.TaskMaster.TaskTrackingSystem.repository.UserRepository;
import com.TaskMaster.TaskTrackingSystem.repository.TeamRepository;
import com.TaskMaster.TaskTrackingSystem.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Task createTask(TaskDTO taskDTO) {
        Task task = Task.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .dueDate(taskDTO.getDueDate())
                .status(Task.Status.valueOf(taskDTO.getStatus()))
                .build();
        if (taskDTO.getAssigneeId() != null) {
            userRepository.findById(taskDTO.getAssigneeId()).ifPresent(task::setAssignee);
        }
        if (taskDTO.getProjectId() != null) {
            teamRepository.findById(taskDTO.getProjectId()).ifPresent(task::setProject);
        }
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long taskId, TaskDTO taskDTO) {
        Task task = getTaskById(taskId);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setStatus(Task.Status.valueOf(taskDTO.getStatus()));
        if (taskDTO.getAssigneeId() != null) {
            userRepository.findById(taskDTO.getAssigneeId()).ifPresent(task::setAssignee);
        }
        if (taskDTO.getProjectId() != null) {
            teamRepository.findById(taskDTO.getProjectId()).ifPresent(task::setProject);
        }
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public List<Task> getTasksByAssignee(Long assigneeId) {
        Optional<User> user = userRepository.findById(assigneeId);
        return user.map(taskRepository::findByAssignee).orElse(List.of());
    }

    @Override
    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus(Task.Status.valueOf(status));
    }

    @Override
    public List<Task> searchTasks(String query) {
        return taskRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }

    @Override
    public Task assignTask(Long taskId, Long userId) {
        Task task = getTaskById(taskId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        task.setAssignee(user);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
} 