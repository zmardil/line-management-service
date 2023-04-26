package com.ihub.linemanagementservice.dtos;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class TeamLeaderDTO {
    private String id;
    private String username;
    @Email
    private String email;
}
