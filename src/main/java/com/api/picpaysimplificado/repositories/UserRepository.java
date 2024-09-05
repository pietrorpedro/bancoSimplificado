package com.api.picpaysimplificado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.picpaysimplificado.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
