package com.zup.desafio_e_commerce.controllers;

import com.zup.desafio_e_commerce.dtos.PurchaseRequest;
import com.zup.desafio_e_commerce.dtos.PurchaseResponse;
import com.zup.desafio_e_commerce.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseResponse> purchase(@RequestBody PurchaseRequest purchaseRequest) {
        return ResponseEntity.ok(purchaseService.purchase(purchaseRequest));
    }
}
