package com.api.picpaysimplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.picpaysimplificado.entities.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long>{
    
}
