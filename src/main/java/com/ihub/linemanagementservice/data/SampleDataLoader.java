package com.ihub.linemanagementservice.data;

import com.ihub.linemanagementservice.entities.Line;
import com.ihub.linemanagementservice.entities.Shift;
import com.ihub.linemanagementservice.entities.TeamLeader;
import com.ihub.linemanagementservice.enums.ShiftType;
import com.ihub.linemanagementservice.enums.Status;
import com.ihub.linemanagementservice.respositories.LineRepository;
import com.ihub.linemanagementservice.respositories.TeamLeaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class SampleDataLoader implements CommandLineRunner {
    private final LineRepository lineRepository;
    private final TeamLeaderRepository teamLeaderRepository;

    @Override
    public void run(String... args) throws Exception {

        TeamLeader teamLeader = TeamLeader.builder().id("TL00001").username("johndoe").email("johndoe@ihub.com").build();

        List<TeamLeader> teamLeaders = List.of(
                teamLeader,
                TeamLeader.builder().id("TL00002").username("imilaMahesan").email("imila.mahesan@ihub.com").build(),
                TeamLeader.builder().id("TL00003").username("ardilMohamed").email("ardil.mohamed@ihub.com").build(),
                TeamLeader.builder().id("TL00004").username("ayyashMohamed").email("ayyash.mohamed@ihub.com").build()
        );

        teamLeaderRepository.saveAll(teamLeaders);

        Line line = Line.builder().status(Status.MAINTENANCE).build();
        line.setShifts(Map.of(
                ShiftType.A, Shift.builder().type(ShiftType.A).isActive(true).line(line).teamLeader(teamLeader).build(),
                ShiftType.B, Shift.builder().type(ShiftType.B).isActive(true).line(line).teamLeader(teamLeader).build()
        ));

        List<Line> lines = List.of(
                line,
                Line.builder().status(Status.ONLINE).build(),
                Line.builder().build(),
                Line.builder().status(Status.MAINTENANCE).build()
        );

        lineRepository.saveAll(lines);
//        lineRepository.deleteById("LN0001");

    }
}
