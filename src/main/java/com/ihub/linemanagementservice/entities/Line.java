package com.ihub.linemanagementservice.entities;

import com.ihub.linemanagementservice.enums.Shift;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
        strategy = GenerationType.AUTO
    )
    private Long id;

    private String teamLeaderId;

    @Enumerated(EnumType.STRING)
    private Shift shift;

    @Builder.Default
    private Boolean isActive = true;

    @CreatedDate
    @Builder.Default
    private Date createdAt = new Date();

}

