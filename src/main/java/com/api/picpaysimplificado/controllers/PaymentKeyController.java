package com.api.picpaysimplificado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.picpaysimplificado.dtos.PaymentKeyDTO;
import com.api.picpaysimplificado.dtos.PublicUserDTO;
import com.api.picpaysimplificado.exceptions.PaymentKeyNotFoundException;
import com.api.picpaysimplificado.exceptions.UserNotFoundException;
import com.api.picpaysimplificado.services.PaymentKeyService;

@RestController
@RequestMapping("/paymentKey")
public class PaymentKeyController {
    
    @Autowired
    private PaymentKeyService paymentKeyService;

    @PostMapping
    public ResponseEntity<String> addPaymentKey(@RequestBody PaymentKeyDTO paymentKeyDTO) {
        try {
            paymentKeyService.addPaymentKey(paymentKeyDTO);
            return ResponseEntity.ok("Payment Key added");
        }catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch(UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{paymentKeyValue}")
    public ResponseEntity<PublicUserDTO> getUserByPaymentKeyValue(@PathVariable String paymentKeyValue) {
        try {
            PublicUserDTO publicUserDTO = paymentKeyService.getUserByPaymentKeyValue(paymentKeyValue);
            return ResponseEntity.ok().body(publicUserDTO);
        }catch(PaymentKeyNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
