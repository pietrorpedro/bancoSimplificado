package com.api.picpaysimplificado.dtos;

import com.api.picpaysimplificado.types.UserType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicUserDTO {
    
    @NotBlank
    private String fullName;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
