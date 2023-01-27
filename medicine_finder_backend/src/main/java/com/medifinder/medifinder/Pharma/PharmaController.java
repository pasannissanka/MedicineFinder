package com.medifinder.medifinder.Pharma;

import com.medifinder.medifinder.Pharma.Dto.CreatePharmaReqDto;
import com.medifinder.medifinder.Pharma.Dto.PharmaDto;
import com.medifinder.medifinder.Utils.Dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pharma")
public class PharmaController {
    @Autowired
    private PharmaService pharmaService;

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


}
