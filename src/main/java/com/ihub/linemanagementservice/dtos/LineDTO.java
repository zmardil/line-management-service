package com.ihub.linemanagementservice.dtos;

import com.ihub.linemanagementservice.enums.Status;
import com.ihub.linemanagementservice.validations.EnumConstraint;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class LineDTO {

    @PositiveOrZero(groups = {UpdateGroup.class, PatchGroup.class}, message = "noOfMachines should be zero or positive integer.")
    private Integer noOfMachines;

//    @NotBlank(groups = UpdateGroup.class, message = "status required.")
    @EnumConstraint(value = Status.class, groups = UpdateGroup.class, message = "Invalid status.")
    private Status status;

    public interface UpdateGroup {}
    public interface PatchGroup {}

}
