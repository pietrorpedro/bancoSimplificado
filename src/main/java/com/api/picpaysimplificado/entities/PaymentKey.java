package com.api.picpaysimplificado.entities;

import com.api.picpaysimplificado.types.KeyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_payment_keys")
@Getter
@Setter
public class PaymentKey {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_id")
    private Long keyId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "key_value", unique = true, nullable = false)
    private String keyValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "key_type", nullable = false)
    private KeyType keyType;
}
