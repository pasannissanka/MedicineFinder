package com.medifinder.medifinder.services;

import com.medifinder.medifinder.dto.PharmaDto;
import com.medifinder.medifinder.dto.requests.CreateProductReq;
import com.medifinder.medifinder.dto.ProductDto;
import com.medifinder.medifinder.dto.requests.UpdateProductReq;


import java.util.List;


public interface ProductService {

    ProductDto createProduct(PharmaDto loggedInUser, CreateProductReq productReq) throws Exception;

    ProductDto findProductById(String id) throws Exception;

    List<ProductDto> searchProducts(
            String brandName,
            String genericName,
            Boolean available,
            Double priceLow,
            Double priceHigh
    );

    List<ProductDto> findAllPharmaProducts(PharmaDto loggedInUser);

    ProductDto updateProductById(String id, UpdateProductReq body) throws Exception;

    boolean deleteProduct(String id);
}


