package com.medifinder.medifinder.Pharma;

import com.medifinder.medifinder.Pharma.Dto.CreatePharmaReqDto;
import com.medifinder.medifinder.Pharma.Dto.PharmaDto;
import com.medifinder.medifinder.Product.Dto.ProductDto;
import com.medifinder.medifinder.Product.ProductService;
import com.medifinder.medifinder.Utils.Dto.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/pharmas")
public class PharmaController {
    private final PharmaService pharmaService;
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<Response<PharmaDto>> createPharmaUser(@RequestBody CreatePharmaReqDto reqDto) throws Exception {
        PharmaDto data = pharmaService.createPharmaUser(reqDto);
        return ResponseEntity.ok().body(Response.ok(data));
    }

    @GetMapping()
    public ResponseEntity<Response<List<PharmaDto>>> search() {
        List<PharmaDto> pharmaDtoList = pharmaService.findAllPharmaUsers();
        return ResponseEntity.ok().body(Response.ok(pharmaDtoList));
    }

    @GetMapping("/nearby")
    public ResponseEntity<Response<List<PharmaDto>>> findNearbyPharmas(@RequestParam String lat, @RequestParam String lng, @RequestParam String radius) {
        double dlat = Double.parseDouble(lat);
        double dlng = Double.parseDouble(lng);
        double dradius = Double.parseDouble((radius));

        List<PharmaDto> pharmaDtos = pharmaService.findNearbyPharmas(dlat, dlng, dradius);
        return ResponseEntity.ok().body(Response.ok(pharmaDtos));
    }

    @GetMapping("{pharma_id}/products")
    public ResponseEntity<Response<List<ProductDto>>> getPharamaProducts(@PathVariable String pharma_id) throws Exception {
        PharmaDto pharmaDto = pharmaService.findPharmaUserById(pharma_id);
        List<ProductDto> products = productService.findAllPharmaProducts(pharmaDto);
        return ResponseEntity.ok().body(Response.ok(products));
    }


}
