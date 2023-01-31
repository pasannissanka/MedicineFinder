package com.medifinder.medifinder.Product.Dto;

import com.medifinder.medifinder.Pharma.Dto.PharmaDto;
import lombok.Data;

@Data
public class CreateProductReq {
    private String brandName;
    private String genericName;
    private double price;
    private boolean available;
    private String description;
}
