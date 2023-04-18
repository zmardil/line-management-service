package com.ihub.linemanagementservice.data;

import com.ihub.linemanagementservice.entities.Line;
import com.ihub.linemanagementservice.enums.Shift;
import com.ihub.linemanagementservice.enums.Status;
import com.ihub.linemanagementservice.respositories.LineRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SampleDataLoader implements CommandLineRunner {
    private final LineRepository lineRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Line> lines = List.of(
            Line.builder().status(Status.ONLINE).build(),
            Line.builder().build(),
            Line.builder().status(Status.MAINTENANCE).build()
        );

        lineRepository.saveAll(lines);

    }
}
