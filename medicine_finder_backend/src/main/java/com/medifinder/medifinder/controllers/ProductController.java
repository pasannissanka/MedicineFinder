package com.medifinder.medifinder.controllers;


import com.medifinder.medifinder.dto.PharmaDto;
import com.medifinder.medifinder.services.PharmaService;
import com.medifinder.medifinder.dto.requests.CreateProductReq;
import com.medifinder.medifinder.dto.ProductDto;
import com.medifinder.medifinder.dto.requests.UpdateProductReq;
import com.medifinder.medifinder.services.ProductService;
import com.medifinder.medifinder.dto.responses.Response;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @Autowired
    private final PharmaService pharmaService;

    @PostMapping()
    @PreAuthorize("hasAuthority('PHARMA')")
    public ResponseEntity<Response<ProductDto>> create(@RequestBody CreateProductReq productReq, Authentication authentication) throws Exception {
        PharmaDto pharmaDto = pharmaService.findPharamUserByEmail(authentication.getName());
        ProductDto product = productService.createProduct(pharmaDto, productReq);
        return ResponseEntity.ok().body(Response.ok(product));
    }

    @GetMapping()
    public ResponseEntity<Response<List<ProductDto>>> search(
            @RequestParam(name = "brand_name") @Nullable String brand_name,
            @RequestParam(name = "generic_name") @Nullable String generic_name,
            @RequestParam(name = "available") @Nullable Boolean available,
            @RequestParam(name = "price_low") @Nullable Double priceLow,
            @RequestParam(name = "price_high") @Nullable Double priceHigh
    ) {
        List<ProductDto> products = productService.searchProducts(brand_name, generic_name, available, priceLow, priceHigh);
        return ResponseEntity.ok().body(Response.ok(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<ProductDto>> getProductById(@PathVariable String id) throws Exception {
        ProductDto productDto = productService.findProductById(id);
        return ResponseEntity.ok().body(Response.ok(productDto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Response<ProductDto>> updateProduct(@PathVariable String id, @RequestBody UpdateProductReq req) throws Exception {
        ProductDto productDto = productService.updateProductById(id, req);
        return ResponseEntity.ok().body(Response.ok(productDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Boolean>> deleteProduct(@PathVariable String id) {
        boolean deleted = productService.deleteProduct(id);
        return ResponseEntity.ok().body(Response.ok(deleted));
    }
}
