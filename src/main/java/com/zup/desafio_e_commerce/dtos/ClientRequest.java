package com.zup.desafio_e_commerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientRequest {
    @JsonProperty("nome")
    private String name;
    private String cpf;
    private String email;

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
}
