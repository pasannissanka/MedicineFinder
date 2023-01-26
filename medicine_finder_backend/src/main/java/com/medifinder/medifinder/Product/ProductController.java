package com.medifinder.medifinder.Product;


import com.medifinder.medifinder.Pharma.Dto.PharmaDto;
import com.medifinder.medifinder.Pharma.PharmaService;
import com.medifinder.medifinder.Product.Dto.CreateProductReq;
import com.medifinder.medifinder.Product.Dto.ProductDto;
import com.medifinder.medifinder.Utils.Models.ResponseBody;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseBody<ProductDto> create(@RequestBody CreateProductReq productReq, Authentication authentication) {
        try {
            PharmaDto pharmaDto = pharmaService.findPharamUserByEmail(authentication.getName());
            ProductDto product = productService.createProduct(pharmaDto, productReq);
            return new ResponseBody<ProductDto>()
                    .setMessage("SUCCESS")
                    .setData(product);
        } catch (Exception ex) {
            return new ResponseBody<ProductDto>()
                    .setMessage("ERROR")
                    .setError(ex.getMessage());
        }
    }

    @GetMapping()
    public ResponseBody<List<ProductDto>> search() {
        try {
            List<ProductDto> products = productService.searchProducts();
            return new ResponseBody<List<ProductDto>>()
                    .setMessage("SUCCESS")
                    .setData(products);
        } catch (Exception ex) {
            return new ResponseBody<List<ProductDto>>()
                    .setMessage("ERROR")
                    .setError(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseBody<ProductDto> getProductById(@PathVariable String id) {
        try {
            ProductDto productDto = productService.findProductById(id);
            return new ResponseBody<ProductDto>()
                    .setMessage("SUCCESS")
                    .setData(productDto);
        } catch (Exception ex) {
            return new ResponseBody<ProductDto>()
                    .setMessage("ERROR")
                    .setError(ex.getMessage());
        }
    }

    @GetMapping("/pharma/{id}")
    public ResponseBody<List<ProductDto>> getPharamaProducts(@PathVariable String id) {
        try {
            PharmaDto pharmaDto = pharmaService.findPharmaUserById(id);
            List<ProductDto> products = productService.findAllPharmaProducts(pharmaDto);
            return new ResponseBody<List<ProductDto>>()
                    .setMessage("SUCCESS")
                    .setData(products);
        } catch (Exception ex) {
            return new ResponseBody<List<ProductDto>>()
                    .setMessage("ERROR")
                    .setError(ex.getMessage());
        }
    }


}
