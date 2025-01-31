package com.zup.desafio_e_commerce.services;

import com.zup.desafio_e_commerce.dtos.PurchaseRequest;
import com.zup.desafio_e_commerce.dtos.PurchaseResponse;
import com.zup.desafio_e_commerce.exceptions.ClientNotFoundException;
import com.zup.desafio_e_commerce.exceptions.InsufficientStockException;
import com.zup.desafio_e_commerce.exceptions.UnregisterProductException;
import com.zup.desafio_e_commerce.models.ProductEntity;
import com.zup.desafio_e_commerce.repositories.ClientRepository;
import com.zup.desafio_e_commerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;

    public PurchaseResponse purchase(PurchaseRequest purchaseRequest) {
        if (!clientRepository.existsByCpf(purchaseRequest.getCpf())) {
            throw new ClientNotFoundException("Não foi encontrado nenhum cliente com o cpf: " + purchaseRequest.getCpf());
        }
        Map<ProductEntity, Integer> productsInStock = validateAndReturnStock(purchaseRequest.getProducts());

        return purchaseAndMapReturn(purchaseRequest.getCpf(), productsInStock);
    }

    private PurchaseResponse purchaseAndMapReturn(String cpf, Map<ProductEntity, Integer> productsInStock) {
        List<PurchaseResponse.Products> purchasedProductsList = new ArrayList<>();
        productsInStock.forEach((product, qnt) -> {
            int atualStock = product.getQuantity();
            product.setQuantity(atualStock - qnt);

            productRepository.save(product);
            purchasedProductsList.add(new PurchaseResponse.Products(product.getName(), String.valueOf(qnt)));
        });

        return new PurchaseResponse(cpf, purchasedProductsList);
    }

    private Map<ProductEntity, Integer> validateAndReturnStock(List<PurchaseRequest.Product> products) {
        List<String> productNames = products.stream().map(PurchaseRequest.Product::getName).toList();
        List<ProductEntity> productEntities = productRepository.findByNameIn(productNames);
        List<String> registeredProducts = productEntities.stream().map(ProductEntity::getName).toList();
        List<String> unregisteredProducts = productNames.stream()
                .filter(product -> !registeredProducts.contains(product))
                .distinct()
                .toList();
        if (!unregisteredProducts.isEmpty()) {
            throw new UnregisterProductException("Não foi possível encontrar na base: " + unregisteredProducts);
        }
        Map<String, Integer> productsQuantityMap = new HashMap<>();
        productNames.forEach(name -> {
            productsQuantityMap.put(name, productsQuantityMap.getOrDefault(name, 0) + 1);
        });

        List<String> insufficientStockProducts = new ArrayList<>();
        Map<ProductEntity, Integer> productEntityQuantityMap = new HashMap<>();

        for (ProductEntity productEntity : productEntities) {
            String productName = productEntity.getName();
            int requestedQuantity = productsQuantityMap.getOrDefault(productName, 0);

            if (requestedQuantity > productEntity.getQuantity()) {
                insufficientStockProducts.add(
                        String.format("Produto em falta: %s, Estoque atual: %d, Quantidade solicitada: %d",
                                productName, productEntity.getQuantity(), requestedQuantity)
                );
            }
            productEntityQuantityMap.put(productEntity, requestedQuantity);
        }
        if (!insufficientStockProducts.isEmpty()) {
            throw new InsufficientStockException("Estoque insuficiente para os seguintes produtos: " + insufficientStockProducts);
        }

        return productEntityQuantityMap;
    }
}
