package com.ihub.linemanagementservice.controllers;

import com.ihub.linemanagementservice.dtos.LineDTO;
import com.ihub.linemanagementservice.dtos.ShiftDTO;
import com.ihub.linemanagementservice.entities.Line;
import com.ihub.linemanagementservice.services.LineService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lines")
@AllArgsConstructor
public class LineController {

    private final LineService lineService;

    @PostMapping
    public ResponseEntity<Line> create(@RequestBody @Valid LineDTO lineDTO) {
        return new ResponseEntity<>(lineService.createLine(lineDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> read() {
        List<Line> lines = lineService.getAllLines();
        Map<String, Object> response = new HashMap<>();
        response.put("lines", lines);
        response.put("count", lines.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Line> read(@PathVariable String id) {
        return new ResponseEntity<>(lineService.getLineById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Line> update(
            @PathVariable String id,
            @RequestBody @Validated({LineDTO.UpdateGroup.class, ShiftDTO.UpdateGroup.class}) LineDTO lineDTO
    ) {
        return new ResponseEntity<>(lineService.updateLineById(id, lineDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Line> patch(
            @PathVariable String id,
            @RequestBody @Validated(LineDTO.PatchGroup.class) LineDTO lineDTO
    ) {
        return new ResponseEntity<>(lineService.patchLineById(id, lineDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        lineService.deleteLineById(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
