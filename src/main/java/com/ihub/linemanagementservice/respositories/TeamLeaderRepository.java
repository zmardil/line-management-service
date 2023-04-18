package com.ihub.linemanagementservice.respositories;

import com.ihub.linemanagementservice.entities.TeamLeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamLeaderRepository extends JpaRepository<TeamLeader, String> {
}
