package com.medifinder.medifinder.Product.Repository;

import com.medifinder.medifinder.Product.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}