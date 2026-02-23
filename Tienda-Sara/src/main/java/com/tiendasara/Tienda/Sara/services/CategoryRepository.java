package com.tiendasara.Tienda.Sara.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tiendasara.Tienda.Sara.models.Category;
public interface CategoryRepository extends JpaRepository<Category, Integer>{
}
