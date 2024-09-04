package com.api.picpaysimplificado.dtos;


import com.api.picpaysimplificado.types.UserType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    
    @NotBlank
    private String fullName;

    @NotBlank
    private String identificationNumber;

    @NotBlank
    @Email
    private String email;

    private Double value = 0.0;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
