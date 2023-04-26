package com.ihub.linemanagementservice.entities;

import com.ihub.linemanagementservice.enums.ShiftType;
import com.ihub.linemanagementservice.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Map;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Line {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "line-id-generator"
    )
    @GenericGenerator(
            name = "line-id-generator",
            strategy = "com.ihub.linemanagementservice.utils.LineIdGenerator"
    )
    private String id;

    private Integer noOfMachines;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Status status = Status.OFFLINE;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL)
    @MapKey(name = "type")
    private Map<ShiftType, Shift> shifts;

}

