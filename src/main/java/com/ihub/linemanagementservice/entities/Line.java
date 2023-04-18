package com.ihub.linemanagementservice.entities;

import com.ihub.linemanagementservice.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;


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

}

