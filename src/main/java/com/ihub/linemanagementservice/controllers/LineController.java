package com.ihub.linemanagementservice.controllers;

import com.ihub.linemanagementservice.dtos.LineRequestDTO;
import com.ihub.linemanagementservice.entities.Line;
import com.ihub.linemanagementservice.services.LineService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lines")
@AllArgsConstructor
public class LineController {

    @Autowired
    private final LineService lineService;

    @PostMapping
    public ResponseEntity<Line> create(@RequestBody @Valid LineRequestDTO lineRequestDTO) {
        return new ResponseEntity<>(lineService.addline(lineRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Line>> read() {
        return new ResponseEntity<>(lineService.getAllLines(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Line> read(@PathVariable Long id) {
        return new ResponseEntity<>(lineService.getLineById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Line> update(@PathVariable Long id, @RequestBody @Valid LineRequestDTO lineRequestDTO) {
        return new ResponseEntity<>(lineService.updateLineById(id, lineRequestDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Line> patch(@PathVariable Long id, @RequestBody @Valid LineRequestDTO lineRequestDTO) {
        return new ResponseEntity<>(lineService.patchLineByid(id, lineRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        lineService.deleteLineById(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
