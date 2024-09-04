package com.api.picpaysimplificado.entities;

import java.util.List;

import com.api.picpaysimplificado.types.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "full_name")
    @NotBlank
    private String fullName;

    @Column(unique = true, name = "identification_number")
    @NotBlank
    private String identificationNumber;

    @Column(unique = true)
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private Double value = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    @OneToMany(mappedBy = "payer")
    private List<Transfer> outgoingTransfers;

    @OneToMany(mappedBy = "payee")
    private List<Transfer> incomingTransfers;

    @OneToMany(mappedBy = "user")
    private List<PaymentKey> paymentKeys;
}
