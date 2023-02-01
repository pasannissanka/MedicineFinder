package com.medifinder.medifinder.Product;

import com.medifinder.medifinder.Product.Model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("select p from Product p where p.pharma.id = ?1")
    List<Product> findAllByPharma_Id(String id);

    @Override
    List<Product> findAll();

    
}