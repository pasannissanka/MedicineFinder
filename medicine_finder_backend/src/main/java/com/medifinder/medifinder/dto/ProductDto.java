package com.medifinder.medifinder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.medifinder.medifinder.entities.Product;
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
    private boolean available;
    private String description;
    private String pharma_id;

    public ProductDto toProductDto(Product product) {
        return ProductDto.builder()
                .description(product.getDescription())
                .id(product.getId())
                .brandName(product.getBrandName())
                .genericName(product.getGenericName())
                .price(product.getPrice())
                .available(product.isAvailable())
                .pharma_id(product.getPharma().getId())
                .build();
    }

    @JsonProperty("available")
    public boolean isAvailable() {
        return available;
    }
}
