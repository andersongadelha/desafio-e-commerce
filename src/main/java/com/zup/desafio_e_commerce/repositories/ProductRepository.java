package com.zup.desafio_e_commerce.repositories;

import com.zup.desafio_e_commerce.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
