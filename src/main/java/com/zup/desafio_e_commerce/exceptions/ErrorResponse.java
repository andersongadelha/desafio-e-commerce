package com.zup.desafio_e_commerce.exceptions;

public class ErrorResponse {
    private String erro;

    public ErrorResponse(String erro) {
        this.erro = erro;
    }

    public String getErro() {
        return erro;
    }
}