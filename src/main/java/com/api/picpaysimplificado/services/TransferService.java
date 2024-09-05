package com.api.picpaysimplificado.services;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.picpaysimplificado.dtos.TransferDTO;
import com.api.picpaysimplificado.entities.PaymentKey;
import com.api.picpaysimplificado.entities.Transfer;
import com.api.picpaysimplificado.entities.User;
import com.api.picpaysimplificado.exceptions.PaymentKeyNotFoundException;
import com.api.picpaysimplificado.exceptions.UnauthorizedException;
import com.api.picpaysimplificado.exceptions.UserNotFoundException;
import com.api.picpaysimplificado.repositories.PaymentKeyRepository;
import com.api.picpaysimplificado.repositories.TransferRepository;
import com.api.picpaysimplificado.repositories.UserRepository;
import com.api.picpaysimplificado.types.UserType;

import jakarta.transaction.Transactional;

@Service
public class TransferService {
    

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentKeyRepository paymentKeyRepository;

    @Autowired
    private TransferRepository transferRepository;


    @Transactional
    public void processTransfer(TransferDTO transferDTO) {
        User payer = userRepository.findById(transferDTO.getPayerId())
            .orElseThrow(() -> new UserNotFoundException("User not found"));

        PaymentKey payeeKey = paymentKeyRepository.findByKeyValue(transferDTO.getPayeeKeyValue())
            .orElseThrow(() -> new PaymentKeyNotFoundException("Payment Key not found"));

        User payee = payeeKey.getUser();

        if (payer.getUserType() == UserType.SHOPKEEPER) {
            throw new UnauthorizedException("Unauthorized Transfer");
        }
        
        if (payer.getValue() < transferDTO.getValue()) {
            throw new IllegalArgumentException("Insufficient funds for the transfer");
        }

        if (transferDTO.getValue() <= 0) {
            throw new IllegalArgumentException("Invalid Value");
        }

        if (!checkAuthorization()) {
            throw new UnauthorizedException("Unauthorized Transfer");
        }
        
        Transfer transfer = new Transfer();
        transfer.setPayer(payer);
        transfer.setPayee(payee);
        transfer.setValue(transferDTO.getValue());

        transferRepository.save(transfer);

        payer.setValue(payer.getValue() - transferDTO.getValue());
        payee.setValue(payee.getValue() + transferDTO.getValue());
        
        userRepository.save(payer);
        userRepository.save(payee);
    }

    private boolean checkAuthorization() {
        String urlString = "https://util.devi.tools/api/v2/authorize";
        
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                return true;
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
