package com.ihub.linemanagementservice.services;

import com.ihub.linemanagementservice.respositories.TeamLeaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeamLeaderService {
    private final TeamLeaderRepository teamLeaderRepository;
}
