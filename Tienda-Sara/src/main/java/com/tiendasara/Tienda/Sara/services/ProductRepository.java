package com.tiendasara.Tienda.Sara.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tiendasara.Tienda.Sara.models.Product;
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
