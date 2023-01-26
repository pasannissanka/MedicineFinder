package com.medifinder.medifinder.Product;

import com.medifinder.medifinder.Pharma.Dto.PharmaDto;
import com.medifinder.medifinder.Product.Dto.CreateProductReq;
import com.medifinder.medifinder.Product.Dto.ProductDto;
import com.medifinder.medifinder.Product.Model.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductDto createProduct(PharmaDto loggedInUser, CreateProductReq productReq) throws Exception {
        Product newProduct = productRepository.save(
                Product.builder()
                        .price(productReq.getPrice())
                        .isAvailable(productReq.isAvailable())
                        .genericName(productReq.getGenericName())
                        .description(productReq.getDescription())
                        .brandName(productReq.getBrandName())
                        .pharma(new PharmaDto().toPharma(loggedInUser))
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

    public List<ProductDto> searchProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> new ProductDto().toProductDto(product)).toList();
    }

    public List<ProductDto> findAllPharmaProducts(PharmaDto loggedInUser) {
        List<Product> products = productRepository.findAllByPharma_Id(loggedInUser.getId());
        return products.stream().map(product -> new ProductDto().toProductDto(product)).toList();
    }
}