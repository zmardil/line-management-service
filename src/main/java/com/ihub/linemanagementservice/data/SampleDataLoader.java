package com.ihub.linemanagementservice.data;

import com.ihub.linemanagementservice.entities.Line;
import com.ihub.linemanagementservice.entities.TeamLeader;
import com.ihub.linemanagementservice.enums.Shift;
import com.ihub.linemanagementservice.enums.Status;
import com.ihub.linemanagementservice.respositories.LineRepository;
import com.ihub.linemanagementservice.respositories.TeamLeaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SampleDataLoader implements CommandLineRunner {
    private final LineRepository lineRepository;
    private final TeamLeaderRepository teamLeaderRepository;

    @Override
    public void run(String... args) throws Exception {
        List<TeamLeader> teamLeaders = List.of(
                TeamLeader.builder().id("TL00001").username("imilaMahesan").email("imila.mahesan@ihub.com").build(),
                TeamLeader.builder().id("TL00002").username("ardilMohamed").email("ardil.mohamed@ihub.com").build(),
                TeamLeader.builder().id("TL00003").username("ayyashMohamed").email("ayyash.mohamed@ihub.com").build()
        );

        teamLeaderRepository.saveAll(teamLeaders);

        List<Line> lines = List.of(
            Line.builder().status(Status.ONLINE).build(),
            Line.builder().build(),
            Line.builder().status(Status.MAINTENANCE).build()
        );

        lineRepository.saveAll(lines);

    }
}
