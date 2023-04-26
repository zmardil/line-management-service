package com.ihub.linemanagementservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ihub.linemanagementservice.enums.ShiftType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ShiftId.class)
public class Shift {

    @Id
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @MapsId("type")
    @EqualsAndHashCode.Exclude
    private ShiftType type;

    @Id
    @JsonIgnore
    @ManyToOne
    @MapsId("lineId")
    @EqualsAndHashCode.Exclude
    private Line line;

    @OneToOne
    private TeamLeader teamLeader;

    @Builder.Default
    private Boolean isActive = false;

}