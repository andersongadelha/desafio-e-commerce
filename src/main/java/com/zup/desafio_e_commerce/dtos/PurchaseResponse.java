package com.zup.desafio_e_commerce.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PurchaseResponse {
    private String cpf;
    @JsonProperty("produtosComprados")
    private List<Products> purchasedProducts;

    public static class Products {
        @JsonProperty("nome")
        private String name;
        @JsonProperty("quantidade")
        private String quantity;

        public Products(String name, String quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public String getQuantity() {
            return quantity;
        }
    }

    public PurchaseResponse(String cpf, List<Products> purchasedProducts) {
        this.cpf = cpf;
        this.purchasedProducts = purchasedProducts;
    }

    public String getCpf() {
        return cpf;
    }

    public List<Products> getPurchasedProducts() {
        return purchasedProducts;
    }
}
