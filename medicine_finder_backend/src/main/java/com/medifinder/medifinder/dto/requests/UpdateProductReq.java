package com.medifinder.medifinder.dto.requests;

import jakarta.annotation.Nullable;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

@Data

public class UpdateProductReq {

    @Nullable
    private String brandName;
    @Nullable
    private String genericName;
    @Nullable
    private double price;
    @Nullable
    private boolean available;
    @Nullable
    private String description;
}

