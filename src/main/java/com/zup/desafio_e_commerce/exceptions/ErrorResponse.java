package com.zup.desafio_e_commerce.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    @JsonProperty("erro")
    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getErro() {
        return error;
    }
}