package com.api.picpaysimplificado.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_transfers")
@Getter
@Setter
public class Transfer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Long transferId;

    @ManyToOne
    @JoinColumn(name = "payer_id", nullable = false)
    private User payer;

    @ManyToOne
    @JoinColumn(name = "payee_id", nullable = false)
    private User payee;

    @Column(nullable = false)
    private Double value;
}
