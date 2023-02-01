package com.medifinder.medifinder.Product;


import com.medifinder.medifinder.Pharma.Dto.PharmaDto;
import com.medifinder.medifinder.Pharma.PharmaService;
import com.medifinder.medifinder.Product.Dto.CreateProductReq;
import com.medifinder.medifinder.Product.Dto.ProductDto;
import com.medifinder.medifinder.Product.Dto.UpdateProductReq;
import com.medifinder.medifinder.Utils.Dto.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
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
    public ResponseEntity<Response<List<ProductDto>>> search() {
        List<ProductDto> products = productService.searchProducts();
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

    @GetMapping("/pharma/{id}")
    public ResponseEntity<Response<List<ProductDto>>> getPharamaProducts(@PathVariable String id) throws Exception {
        PharmaDto pharmaDto = pharmaService.findPharmaUserById(id);
        List<ProductDto> products = productService.findAllPharmaProducts(pharmaDto);
        return ResponseEntity.ok().body(Response.ok(products));
    }
}
