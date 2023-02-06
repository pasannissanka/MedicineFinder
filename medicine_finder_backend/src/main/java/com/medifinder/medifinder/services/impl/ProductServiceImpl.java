package com.medifinder.medifinder.services.impl;

import com.medifinder.medifinder.dto.PharmaDto;
import com.medifinder.medifinder.dto.ProductDto;
import com.medifinder.medifinder.dto.requests.CreateProductReq;
import com.medifinder.medifinder.dto.requests.UpdateProductReq;
import com.medifinder.medifinder.entities.Pharma;
import com.medifinder.medifinder.entities.Product;
import com.medifinder.medifinder.repositories.ProductRepository;
import com.medifinder.medifinder.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductDto createProduct(PharmaDto loggedInUser, CreateProductReq productReq) throws Exception {
        Product newProduct = productRepository.save(
                Product.builder()
                        .price(productReq.getPrice())
                        .available(productReq.isAvailable())
                        .genericName(productReq.getGenericName())
                        .description(productReq.getDescription())
                        .brandName(productReq.getBrandName())
                        .pharma(Pharma.builder().id(loggedInUser.getId()).build())
                        .build()
        );
        return new ProductDto().toProductDto(newProduct);
    }

    public ProductDto findProductById(String id) throws Exception {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty())
            throw new Exception("Not found");
        return new ProductDto().toProductDto(product.get());
    }

    public List<ProductDto> searchProducts(String brandName, String genericName) {
        List<Product> products = productRepository.searchProducts(brandName, genericName);
        return products.stream().map(product -> new ProductDto().toProductDto(product)).toList();
    }

    public List<ProductDto> findAllPharmaProducts(PharmaDto loggedInUser) {
        List<Product> products = productRepository.findAllByPharma_Id(loggedInUser.getId());
        return products.stream().map(product -> new ProductDto().toProductDto(product)).toList();
    }

    public ProductDto updateProductById(String id, UpdateProductReq body) throws Exception {
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isEmpty())
            throw new Exception("Product not found");

        Product product = foundProduct.get();

        product.setAvailable(body.isAvailable());
        product.setPrice(body.getPrice());
        product.setDescription(body.getDescription());
        product.setBrandName(body.getBrandName());
        product.setGenericName(body.getGenericName());

        return new ProductDto().toProductDto(productRepository.save(product));
    }

    public boolean deleteProduct(String id) {
        productRepository.deleteById(id);
        return true;
    }
}
