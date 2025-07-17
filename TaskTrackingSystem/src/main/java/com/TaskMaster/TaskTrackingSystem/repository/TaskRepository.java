package com.TaskMaster.TaskTrackingSystem.repository;

import com.TaskMaster.TaskTrackingSystem.model.Task;
import com.TaskMaster.TaskTrackingSystem.model.User;
import com.TaskMaster.TaskTrackingSystem.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignee(User assignee);
    List<Task> findByProject(Team project);
    List<Task> findByStatus(Task.Status status);
    List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
} 