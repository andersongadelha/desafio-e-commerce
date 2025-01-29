package com.zup.desafio_e_commerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequest {
    @JsonProperty("nome")
    private String name;
    @JsonProperty("preco")
    private Double price;
    @JsonProperty("quantidade")
    private int quantity;

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
