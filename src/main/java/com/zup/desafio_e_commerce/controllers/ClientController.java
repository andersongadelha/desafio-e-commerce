package com.zup.desafio_e_commerce.controllers;

import com.zup.desafio_e_commerce.dtos.ClientRequest;
import com.zup.desafio_e_commerce.dtos.ClientResponse;
import com.zup.desafio_e_commerce.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/cliente")
    ResponseEntity<ClientResponse> saveClient(@RequestBody ClientRequest clientRequest) {
        URI uri = URI.create("/clientes/cliente");

        return ResponseEntity.created(uri).body(clientService.save(clientRequest));
    }

    @GetMapping("/cliente/{id}")
    ResponseEntity<ClientResponse> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }
}
