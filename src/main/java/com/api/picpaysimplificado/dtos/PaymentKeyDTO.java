package com.api.picpaysimplificado.dtos;

import com.api.picpaysimplificado.types.KeyType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentKeyDTO {
    
    private Long userId;
    private String keyValue;
    private KeyType keyType;
}
