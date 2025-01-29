package com.zup.desafio_e_commerce.services;

import com.zup.desafio_e_commerce.dtos.ClientRequest;
import com.zup.desafio_e_commerce.dtos.ClientResponse;
import com.zup.desafio_e_commerce.models.ClientEntity;
import com.zup.desafio_e_commerce.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public ClientResponse save(ClientRequest requisicao) {
        ClientEntity newClient = new ClientEntity(requisicao.getNome(), requisicao.getCpf(), requisicao.getEmail());
        ClientEntity savedClient = clientRepository.save(newClient);

        return new ClientResponse(savedClient.getId(), savedClient.getName(), savedClient.getCpf(), savedClient.getEmail());
    }

    public ClientResponse findById(Long id) {
        ClientEntity clientById = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return new ClientResponse(clientById.getId(), clientById.getName(), clientById.getCpf(), clientById.getEmail());
    }
}
