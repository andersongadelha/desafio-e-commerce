package com.zup.desafio_e_commerce.controllers;

import com.zup.desafio_e_commerce.dtos.ClientRequest;
import com.zup.desafio_e_commerce.dtos.ClientResponse;
import com.zup.desafio_e_commerce.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> saveClient(@RequestBody ClientRequest clientRequest) {
        URI uri = URI.create("/clientes");

        return ResponseEntity.created(uri).body(clientService.save(clientRequest));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClientResponse> getClientByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(clientService.findByCpf(cpf));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable String cpf, @RequestBody ClientRequest clientRequest) {

        return ResponseEntity.ok(clientService.update(cpf, clientRequest));
    }
}
