package com.api.picpaysimplificado.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferDTO {

    @NotBlank
    private Long payerId;

    @NotBlank
    private String payeeKeyValue;

    @NotBlank
    private Double value;
}
