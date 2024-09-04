package com.api.picpaysimplificado.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.picpaysimplificado.dtos.TransferDTO;
import com.api.picpaysimplificado.exceptions.InsufficientFundsException;
import com.api.picpaysimplificado.exceptions.PaymentKeyNotFoundException;
import com.api.picpaysimplificado.exceptions.UnauthorizedException;
import com.api.picpaysimplificado.exceptions.UserNotFoundException;
import com.api.picpaysimplificado.services.TransferService;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    
    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<String> transfer(@RequestBody TransferDTO transferDTO) {
        try {
            transferService.processTransfer(transferDTO);
            return ResponseEntity.ok("Transfer successful");
        }catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (PaymentKeyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InsufficientFundsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

}
