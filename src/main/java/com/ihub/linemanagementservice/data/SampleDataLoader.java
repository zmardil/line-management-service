package com.ihub.linemanagementservice.data;

import com.ihub.linemanagementservice.entities.Line;
import com.ihub.linemanagementservice.enums.Shift;
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
            Line.builder().teamLeaderId("TL001").shift(Shift.A).build(),
            Line.builder().teamLeaderId("TL002").shift(Shift.A).build(),
            Line.builder().teamLeaderId("TL003").shift(Shift.B).build()
        );

        lineRepository.saveAll(lines);

    }
}
