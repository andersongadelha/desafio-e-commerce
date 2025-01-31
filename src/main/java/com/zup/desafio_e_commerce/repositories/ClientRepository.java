package com.zup.desafio_e_commerce.repositories;

import com.zup.desafio_e_commerce.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Boolean existsByCpf(String cpf);
    Optional<ClientEntity> findByCpf(String cpf);
}
