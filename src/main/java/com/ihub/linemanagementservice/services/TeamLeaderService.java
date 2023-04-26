package com.ihub.linemanagementservice.services;

import com.ihub.linemanagementservice.entities.TeamLeader;
import com.ihub.linemanagementservice.exceptions.ResourceNotFoundException;
import com.ihub.linemanagementservice.respositories.TeamLeaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamLeaderService {
    private final TeamLeaderRepository teamLeaderRepository;

    public TeamLeader getTeamLeaderById(String id) {
        Optional<TeamLeader> teamLeader = teamLeaderRepository.findById(id);
        if (teamLeader.isEmpty()) throw new ResourceNotFoundException("Team leader of " + id + " not found.");
        return teamLeader.get();
    }
}
