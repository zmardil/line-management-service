package com.ihub.linemanagementservice.services;

import com.ihub.linemanagementservice.dtos.LineRequestDTO;
import com.ihub.linemanagementservice.entities.Line;
import com.ihub.linemanagementservice.exceptions.ResourceNotFoundException;
import com.ihub.linemanagementservice.respositories.LineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LineService {

    private final LineRepository lineRepository;

    public List<Line> getAllLines() {
        return lineRepository.findAll();
    }
    public Line getLineById(Long id) {
        Optional<Line> line = lineRepository.findById(id);
        if(line.isEmpty()) throw new ResourceNotFoundException("Line not found.");
        return line.get();
    }

    public void deleteLineById(Long id) {
        if(!lineRepository.existsById(id)) throw new ResourceNotFoundException("Line not found.");
        lineRepository.deleteById(id);
    }

    public Line addline(LineRequestDTO lineRequestDTO) {
        Line line = Line.builder()
                .teamLeaderId(lineRequestDTO.getTeamLeaderId())
                .shift(lineRequestDTO.getShift())
                .isActive(lineRequestDTO.getIsActive())
                .build();
        return lineRepository.save(line);
    }

    public Line patchLineByid(Long id, LineRequestDTO lineRequestDTO) {
        Line line = lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found."));
        if(lineRequestDTO.getTeamLeaderId() != null) line.setTeamLeaderId(lineRequestDTO.getTeamLeaderId());
        if(lineRequestDTO.getShift() != null) line.setShift(lineRequestDTO.getShift());
        if(lineRequestDTO.getIsActive() != null) line.setIsActive(lineRequestDTO.getIsActive());
        return lineRepository.save(line);
    }

    public Line updateLineById(Long id, LineRequestDTO lineRequestDTO) {
        Line line = lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found."));
        line.setTeamLeaderId(lineRequestDTO.getTeamLeaderId());
        line.setShift(lineRequestDTO.getShift());
        line.setIsActive(lineRequestDTO.getIsActive());
        return lineRepository.save(line);
    }
}
