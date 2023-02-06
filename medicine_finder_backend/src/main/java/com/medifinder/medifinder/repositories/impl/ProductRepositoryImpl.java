package com.medifinder.medifinder.repositories.impl;

import com.medifinder.medifinder.entities.Product;
import com.medifinder.medifinder.repositories.custom.ProductRepositoryCustom;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
    @Override
    public List<Product> searchProducts(String brandName, String genericName) {
        return null;
    }
}
