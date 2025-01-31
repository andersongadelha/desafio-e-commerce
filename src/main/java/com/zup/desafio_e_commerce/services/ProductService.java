package com.zup.desafio_e_commerce.services;

import com.zup.desafio_e_commerce.dtos.ProductRequest;
import com.zup.desafio_e_commerce.dtos.ProductResponse;
import com.zup.desafio_e_commerce.exceptions.InvalidValueException;
import com.zup.desafio_e_commerce.exceptions.ProductNotFoundException;
import com.zup.desafio_e_commerce.models.ProductEntity;
import com.zup.desafio_e_commerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductResponse save(ProductRequest requisicao) {
        validatePrice(requisicao.getPrice());
        validateQuantity(requisicao.getQuantity());
        ProductEntity newProduct = new ProductEntity(requisicao.getName(), requisicao.getPrice(), requisicao.getQuantity());
        ProductEntity savedProduct = productRepository.save(newProduct);

        return new ProductResponse(savedProduct.getId(), savedProduct.getName(), savedProduct.getPrice(), savedProduct.getQuantity());
    }

    public List<ProductResponse> getProducts() {
        List<ProductEntity> products = productRepository.findAll();

        return productRepository.findAll().stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getQuantity()))
                .toList();
    }

    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Não foi encontrado produto com id: " + id);
        }
        productRepository.deleteById(id);
    }

    private void validatePrice(Double price) {
        if ((price <= 0)) {
            throw new InvalidValueException("O valor deve ser maior que zero.");
        }
    }

    private void validateQuantity(int quantity) {
        if ((quantity < 0)) {
            throw new InvalidValueException("A quantidade deve ser maior ou igual a zero.");
        }
    }
}
