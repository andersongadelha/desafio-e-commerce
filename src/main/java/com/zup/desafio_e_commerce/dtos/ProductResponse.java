package com.zup.desafio_e_commerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductResponse {
    private Long id;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("preco")
    private Double price;
    @JsonProperty("quantidade")
    private int quantity;

    public ProductResponse(Long id, String name, Double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
