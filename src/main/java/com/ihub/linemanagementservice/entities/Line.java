package com.ihub.linemanagementservice.entities;

import com.ihub.linemanagementservice.enums.Shift;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "lines")
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

    private String teamLeaderId;

    @Enumerated(EnumType.STRING)
    private Shift shift;

    @Builder.Default
    private Boolean isActive = true;

    @CreatedDate
    @Builder.Default
    private Date createdAt = new Date();

}

