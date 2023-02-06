package com.medifinder.medifinder.services;


import com.medifinder.medifinder.dto.requests.CreatePharmaReqDto;
import com.medifinder.medifinder.dto.PharmaDto;

import java.util.List;

public interface PharmaService {

    PharmaDto createPharmaUser(CreatePharmaReqDto reqDto) throws Exception;

    List<PharmaDto> findAllPharmaUsers();

    PharmaDto findPharmaUser(String user_id) throws Exception;

    PharmaDto findPharmaUserById(String id) throws Exception;

    PharmaDto findPharamUserByEmail(String email) throws Exception;

    List<PharmaDto> findNearbyPharmas(double lat, double lng, double radius);
}
