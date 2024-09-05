package com.api.picpaysimplificado.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.picpaysimplificado.dtos.PaymentKeyDTO;
import com.api.picpaysimplificado.dtos.PublicUserDTO;
import com.api.picpaysimplificado.entities.PaymentKey;
import com.api.picpaysimplificado.entities.User;
import com.api.picpaysimplificado.exceptions.PaymentKeyNotFoundException;
import com.api.picpaysimplificado.exceptions.UserNotFoundException;
import com.api.picpaysimplificado.repositories.PaymentKeyRepository;
import com.api.picpaysimplificado.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentKeyService {
    
    @Autowired
    private PaymentKeyRepository paymentKeyRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void addPaymentKey(PaymentKeyDTO paymentKeyDTO) {
        if (paymentKeyRepository.existsByKeyValue(paymentKeyDTO.getKeyValue())) {
            throw new IllegalArgumentException("This Payment Key already exists");
        }

        User user = userRepository.findById(paymentKeyDTO.getUserId())
            .orElseThrow(() -> new UserNotFoundException("User not foud"));

        PaymentKey paymentKey = new PaymentKey();
        paymentKey.setUser(user);
        paymentKey.setKeyValue(paymentKeyDTO.getKeyValue());
        paymentKey.setKeyType(paymentKeyDTO.getKeyType());

        paymentKeyRepository.save(paymentKey);
    }

    public PublicUserDTO getUserByPaymentKeyValue(String paymentKeyValue) {
        PaymentKey paymentKey = paymentKeyRepository.findByKeyValue(paymentKeyValue)
            .orElseThrow(() -> new PaymentKeyNotFoundException("Payment Key not found"));

        User user = paymentKey.getUser();
        PublicUserDTO publicUserDTO = new PublicUserDTO();
        BeanUtils.copyProperties(user, publicUserDTO);

        return publicUserDTO;
    }

}
