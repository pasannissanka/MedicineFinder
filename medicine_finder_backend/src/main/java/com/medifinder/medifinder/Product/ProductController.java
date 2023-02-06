package com.medifinder.medifinder.Product;


import com.medifinder.medifinder.Pharma.Dto.PharmaDto;
import com.medifinder.medifinder.Pharma.PharmaService;
import com.medifinder.medifinder.Product.Dto.CreateProductReq;
import com.medifinder.medifinder.Product.Dto.ProductDto;
import com.medifinder.medifinder.Product.Dto.UpdateProductReq;
import com.medifinder.medifinder.Utils.Dto.Response;
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
            @RequestParam(name = "availability") @Nullable Boolean available
    ) {
        List<ProductDto> products = productService.searchProducts(brand_name, generic_name);
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
    public ResponseEntity<Response<Boolean>> deleteProduct(@PathVariable String id) throws Exception {
        boolean deleted = productService.deleteProduct(id);
        return ResponseEntity.ok().body(Response.ok(deleted));
    }

//    @GetMapping("/search")
//    public ResponseEntity<Response<List<ProductDto>>> searchProductByNames(@RequestParam(name = "name") String name) {
//        List<ProductDto> productDtos = productService.findProductsByName(name);
//        return ResponseEntity.ok().body(Response.ok(productDtos));
//    }
//
//    @GetMapping("/text_search")
//    public ResponseEntity<Response<List<String >>> searchByBrandName(@RequestParam(name = "brand_name")) {
//
//    }
}
