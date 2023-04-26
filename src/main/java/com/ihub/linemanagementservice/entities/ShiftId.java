package com.ihub.linemanagementservice.entities;

import com.ihub.linemanagementservice.enums.ShiftType;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ShiftId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private ShiftType type;
    private Line line;

}
