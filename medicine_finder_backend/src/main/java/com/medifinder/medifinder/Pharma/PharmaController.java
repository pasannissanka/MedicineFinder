package com.medifinder.medifinder.Pharma;

import com.medifinder.medifinder.Pharma.Dto.CreatePharmaReqDto;
import com.medifinder.medifinder.Pharma.Dto.PharmaDto;
import com.medifinder.medifinder.Pharma.Service.PharmaService;
import com.medifinder.medifinder.Utils.Models.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pharma")
public class PharmaController {
    @Autowired
    private PharmaService pharmaService;

    @PostMapping()
    public ResponseBody<PharmaDto> createPharmaUser(@RequestBody CreatePharmaReqDto reqDto) {
        try {
            PharmaDto data = pharmaService.createPharmaUser(reqDto);
            return new ResponseBody<PharmaDto>().setData(data).setMessage("SUCCESS");
        } catch (Exception ex) {
            return new ResponseBody<PharmaDto>().setError(ex.getMessage()).setMessage("ERROR");
        }
    }

    @GetMapping()
    public ResponseBody<List<PharmaDto>> findAllPharmaUsers() {
        List<PharmaDto> pharmaDtoList = pharmaService.findAllPharmaUsers();
        return new ResponseBody<List<PharmaDto>>()
                .setMessage("SUCCESS")
                .setData(pharmaDtoList);
    }


}
