package com.zup.desafio_e_commerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PurchaseRequest {
    private String cpf;
    @JsonProperty("produtos")
    private List<Product> products;

    public static class Product {
        @JsonProperty("nome")
        private String name;

        public String getName() {
            return name;
        }
    }

    public String getCpf() {
        return cpf;
    }

    public List<Product> getProducts() {
        return products;
    }
}
