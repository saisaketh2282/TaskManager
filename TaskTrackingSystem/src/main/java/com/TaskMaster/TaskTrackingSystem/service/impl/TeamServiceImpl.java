package com.TaskMaster.TaskTrackingSystem.service.impl;

import com.TaskMaster.TaskTrackingSystem.dto.TeamDTO;
import com.TaskMaster.TaskTrackingSystem.model.Team;
import com.TaskMaster.TaskTrackingSystem.model.User;
import com.TaskMaster.TaskTrackingSystem.repository.TeamRepository;
import com.TaskMaster.TaskTrackingSystem.repository.UserRepository;
import com.TaskMaster.TaskTrackingSystem.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Team createTeam(TeamDTO teamDTO) {
        Team team = Team.builder()
                .name(teamDTO.getName())
                .build();
        if (teamDTO.getMemberIds() != null) {
            Set<User> members = teamDTO.getMemberIds().stream()
                    .map(id -> userRepository.findById(id).orElse(null))
                    .filter(u -> u != null)
                    .collect(Collectors.toSet());
            team.setMembers(members);
        }
        return teamRepository.save(team);
    }

    @Override
    public Team joinTeam(Long teamId, Long userId) {
        Team team = getTeamById(teamId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        team.getMembers().add(user);
        return teamRepository.save(team);
    }

    @Override
    public Team inviteMember(Long teamId, Long userId) {
        return joinTeam(teamId, userId);
    }

    @Override
    public List<Team> getTeamsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return teamRepository.findAll().stream()
                .filter(team -> team.getMembers().contains(user))
                .collect(Collectors.toList());
    }

    @Override
    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
} 