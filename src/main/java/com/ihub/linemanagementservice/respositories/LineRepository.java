package com.ihub.linemanagementservice.respositories;

import com.ihub.linemanagementservice.entities.Line;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Line, Long> {
}
