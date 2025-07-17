package com.TaskMaster.TaskTrackingSystem.controller;

import com.TaskMaster.TaskTrackingSystem.dto.TeamDTO;
import com.TaskMaster.TaskTrackingSystem.model.Team;
import com.TaskMaster.TaskTrackingSystem.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.createTeam(teamDTO));
    }

    @PostMapping("/{teamId}/join/{userId}")
    public ResponseEntity<Team> joinTeam(@PathVariable Long teamId, @PathVariable Long userId) {
        return ResponseEntity.ok(teamService.joinTeam(teamId, userId));
    }

    @PostMapping("/{teamId}/invite/{userId}")
    public ResponseEntity<Team> inviteMember(@PathVariable Long teamId, @PathVariable Long userId) {
        return ResponseEntity.ok(teamService.inviteMember(teamId, userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Team>> getTeamsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(teamService.getTeamsByUser(userId));
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long teamId) {
        return ResponseEntity.ok(teamService.getTeamById(teamId));
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }
} 