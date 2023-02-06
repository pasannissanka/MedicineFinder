package com.medifinder.medifinder.repositories;

import com.medifinder.medifinder.entities.Product;
import com.medifinder.medifinder.repositories.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>, ProductRepositoryCustom {

    @Query("select p from Product p where p.pharma.id = ?1")
    List<Product> findAllByPharma_Id(String id);

    @Override
    List<Product> findAll();
}