package com.ihub.linemanagementservice.dtos;

import com.ihub.linemanagementservice.enums.ShiftType;
import com.ihub.linemanagementservice.enums.Status;
import com.ihub.linemanagementservice.validations.EnumConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class LineDTO {

    @PositiveOrZero(
            groups = {UpdateGroup.class, PatchGroup.class},
            message = "noOfMachines should be zero or positive integer."
    )
    private Integer noOfMachines;

    //    @NotBlank(groups = UpdateGroup.class, message = "status required.")
    @EnumConstraint(
            value = Status.class,
            groups = UpdateGroup.class,
            message = "Invalid status."
    )
    private Status status;

    @Valid
    private Map<@EnumConstraint(value = ShiftType.class, message = "Invalid shift type.") ShiftType, ShiftDTO> shifts;

    public interface UpdateGroup {
    }

    public interface PatchGroup {
    }

}
