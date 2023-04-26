package com.ihub.linemanagementservice.services;

import com.ihub.linemanagementservice.respositories.ShiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShiftService {
    private final ShiftRepository shiftRepository;

}
