package com.ihub.linemanagementservice.respositories;

import com.ihub.linemanagementservice.entities.Shift;
import com.ihub.linemanagementservice.entities.ShiftId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, ShiftId> {
}
