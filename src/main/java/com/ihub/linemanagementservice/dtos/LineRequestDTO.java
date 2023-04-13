package com.ihub.linemanagementservice.dtos;

import com.ihub.linemanagementservice.enums.Shift;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class LineRequestDTO {

    @NotBlank(message = "id required!")
    private String teamLeaderId;

    @NotNull(message = "shift required!")
    @Enumerated(EnumType.STRING)
    private Shift shift;

    private Boolean isActive;
}
