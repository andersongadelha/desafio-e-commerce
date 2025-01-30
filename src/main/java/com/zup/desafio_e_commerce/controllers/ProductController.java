package com.zup.desafio_e_commerce.controllers;

import com.zup.desafio_e_commerce.dtos.ProductRequest;
import com.zup.desafio_e_commerce.dtos.ProductResponse;
import com.zup.desafio_e_commerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    ResponseEntity<ProductResponse> saveProduct(@RequestBody ProductRequest productRequest) {
        URI uri = URI.create("/produtos");

        return ResponseEntity.created(uri).body(productService.save(productRequest));
    }

    @GetMapping
    ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }
}
