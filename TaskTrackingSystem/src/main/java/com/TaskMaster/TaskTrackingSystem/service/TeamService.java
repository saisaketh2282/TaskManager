package com.TaskMaster.TaskTrackingSystem.service;

import com.TaskMaster.TaskTrackingSystem.dto.TeamDTO;
import com.TaskMaster.TaskTrackingSystem.model.Team;
import java.util.List;

public interface TeamService {
    Team createTeam(TeamDTO teamDTO);
    Team joinTeam(Long teamId, Long userId);
    Team inviteMember(Long teamId, Long userId);
    List<Team> getTeamsByUser(Long userId);
    Team getTeamById(Long teamId);
    List<Team> getAllTeams();
} 