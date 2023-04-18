package com.ihub.linemanagementservice.services;

import com.ihub.linemanagementservice.dtos.LineDTO;
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
    public Line getLineById(String id) {
        Optional<Line> line = lineRepository.findById(id);
        if(line.isEmpty()) throw new ResourceNotFoundException("Line not found.");
        return line.get();
    }

    public void deleteLineById(String id) {
        if(!lineRepository.existsById(id)) throw new ResourceNotFoundException("Line not found.");
        lineRepository.deleteById(id);
    }

    public Line createLine(LineDTO lineDTO) {
        Line line = Line.builder()
                .status(lineDTO.getStatus())
                .noOfMachines(lineDTO.getNoOfMachines())
                .build();
        return lineRepository.save(line);
    }

    public Line patchLineById(String id, LineDTO lineDTO) {
        Line line = lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found."));
        Optional.ofNullable(lineDTO.getStatus()).ifPresent(line::setStatus);
        Optional.ofNullable(lineDTO.getNoOfMachines()).ifPresentOrElse(line::setNoOfMachines, () -> line.setNoOfMachines(null));
        return lineRepository.save(line);
    }

    public Line updateLineById(String id, LineDTO lineDTO) {
        Line line = lineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Line not found."));
        Optional.ofNullable(lineDTO.getStatus()).ifPresent(line::setStatus);
        Optional.ofNullable(lineDTO.getNoOfMachines()).ifPresent(line::setNoOfMachines);
        return lineRepository.save(line);
    }
}
