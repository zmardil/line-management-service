package com.ihub.linemanagementservice.services;

import com.ihub.linemanagementservice.dtos.LineDTO;
import com.ihub.linemanagementservice.dtos.ShiftDTO;
import com.ihub.linemanagementservice.entities.Line;
import com.ihub.linemanagementservice.entities.Shift;
import com.ihub.linemanagementservice.enums.ShiftType;
import com.ihub.linemanagementservice.exceptions.ResourceNotFoundException;
import com.ihub.linemanagementservice.respositories.LineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LineService {

    private final LineRepository lineRepository;

    private final TeamLeaderService teamLeaderService;

    public List<Line> getAllLines() {
        return lineRepository.findAll();
    }

    public Line getLineById(String id) {
        Optional<Line> line = lineRepository.findById(id);
        if (line.isEmpty()) throw new ResourceNotFoundException("Line of " + id + " not found.");
        return line.get();
    }

    public void deleteLineById(String id) {
        if (!lineRepository.existsById(id)) throw new ResourceNotFoundException("Line of " + id + " not found.");
        lineRepository.deleteById(id);
    }

    public Line createLine(LineDTO lineDTO) {
        Line line = Line.builder()
                .status(lineDTO.getStatus())
                .noOfMachines(lineDTO.getNoOfMachines())
                .build();
        Map<ShiftType, Shift> shifts = lineDTO.getShifts()
                .entrySet().stream().map(entry -> {
                    ShiftType shiftType = entry.getKey();
                    ShiftDTO shiftDTO = entry.getValue();
                    return Shift.builder()
                            .line(line)
                            .type(shiftType)
                            .isActive(shiftDTO.getIsActive())
                            .teamLeader(
                                    teamLeaderService.getTeamLeaderById(shiftDTO.getTeamLeaderId())
                            )
                            .build();
                }).collect(Collectors.toMap(Shift::getType, Function.identity()));
        line.setShifts(shifts);

        return lineRepository.save(line);
    }

    public Line patchLineById(String id, LineDTO lineDTO) {
        Line line = lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line of " + id + " not found."));
        Optional.ofNullable(lineDTO.getStatus()).ifPresent(line::setStatus);
        Optional.ofNullable(lineDTO.getNoOfMachines()).ifPresentOrElse(line::setNoOfMachines, () -> line.setNoOfMachines(null));
        Optional.ofNullable(lineDTO.getShifts()).ifPresent(shiftsMap -> {
            Map<ShiftType, Shift> shifts = shiftsMap.entrySet().stream().map(entry -> {
                ShiftType shiftType = entry.getKey();
                ShiftDTO shiftDTO = entry.getValue();
                return Shift.builder()
                        .line(line)
                        .type(shiftType)
                        .isActive(shiftDTO.getIsActive())
                        .teamLeader(
                                Optional.ofNullable(shiftDTO.getTeamLeaderId()).map(teamLeaderService::getTeamLeaderById).orElse(null)
                        )
                        .build();
            }).collect(Collectors.toMap(Shift::getType, Function.identity()));
            line.setShifts(shifts);
        });
        return lineRepository.save(line);
    }

    public Line updateLineById(String id, LineDTO lineDTO) {
        Line line = lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line of" + id + " not found."));
        Optional.ofNullable(lineDTO.getStatus()).ifPresent(line::setStatus);
        Optional.ofNullable(lineDTO.getNoOfMachines()).ifPresent(line::setNoOfMachines);
        Optional.ofNullable(lineDTO.getShifts()).ifPresent(shiftsMap -> {
            Map<ShiftType, Shift> shifts = shiftsMap.entrySet().stream().map(entry -> {
                ShiftType shiftType = entry.getKey();
                ShiftDTO shiftDTO = entry.getValue();
                return Shift.builder()
                        .line(line)
                        .type(shiftType)
                        .isActive(shiftDTO.getIsActive())
                        .teamLeader(
                                teamLeaderService.getTeamLeaderById(shiftDTO.getTeamLeaderId())
                        )
                        .build();
            }).collect(Collectors.toMap(Shift::getType, Function.identity()));
            line.setShifts(shifts);
        });
        return lineRepository.save(line);
    }
}
