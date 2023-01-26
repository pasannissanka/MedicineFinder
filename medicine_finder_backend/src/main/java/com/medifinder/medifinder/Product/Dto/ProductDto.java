package com.medifinder.medifinder.Product.Dto;

import com.medifinder.medifinder.Pharma.Dto.PharmaDto;
import com.medifinder.medifinder.Product.Model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String id;
    private String brandName;
    private String genericName;
    private double price;
    private boolean isAvailable;
    private String description;
    private PharmaDto pharma;

    public ProductDto toProductDto(Product product) {
        return ProductDto.builder()
                .description(product.getDescription())
                .id(product.getId())
                .brandName(product.getBrandName())
                .genericName(product.getGenericName())
                .price(product.getPrice())
                .isAvailable(product.isAvailable())
                .pharma(new PharmaDto().toPharmaDto(product.getPharma()))
                .build();
    }
}
