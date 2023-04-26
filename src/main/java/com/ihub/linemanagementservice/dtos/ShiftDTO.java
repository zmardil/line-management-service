package com.ihub.linemanagementservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class ShiftDTO {

    @NotNull
    private Boolean isActive;

    @NotBlank(message = "Team leader ID required.")
    private String teamLeaderId;

    public interface UpdateGroup {
    }

}
