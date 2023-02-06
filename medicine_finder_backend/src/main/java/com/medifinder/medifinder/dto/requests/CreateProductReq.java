package com.medifinder.medifinder.dto.requests;

import lombok.Data;

@Data
public class CreateProductReq {
    private String brandName;
    private String genericName;
    private double price;
    private boolean available;
    private String description;
}
