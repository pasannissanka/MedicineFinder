package com.medifinder.medifinder.repositories.custom;

import com.medifinder.medifinder.entities.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> searchProducts(String brandName, String genericName);
}
