package com.api.picpaysimplificado.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.picpaysimplificado.entities.PaymentKey;

@Repository
public interface PaymentKeyRepository extends JpaRepository<PaymentKey, Long>{
    
    // Verifica se a chave existe
    boolean existsByKeyValue(String keyValue);

    Optional<PaymentKey> findByKeyValue(String keyValue);

}
