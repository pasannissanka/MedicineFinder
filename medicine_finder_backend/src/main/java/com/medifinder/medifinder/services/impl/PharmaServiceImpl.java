package com.medifinder.medifinder.services.impl;

import com.medifinder.medifinder.dto.CreateNewUserReqBody;
import com.medifinder.medifinder.dto.PharmaDto;
import com.medifinder.medifinder.dto.requests.CreatePharmaReqDto;
import com.medifinder.medifinder.entities.Pharma;
import com.medifinder.medifinder.entities.Role;
import com.medifinder.medifinder.entities.User;
import com.medifinder.medifinder.repositories.PharmaRepository;
import com.medifinder.medifinder.repositories.UserRepository;
import com.medifinder.medifinder.services.PharmaService;
import com.medifinder.medifinder.services.UserService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PharmaServiceImpl implements PharmaService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PharmaRepository pharmaRepository;

    public PharmaDto createPharmaUser(CreatePharmaReqDto reqDto) throws Exception {
        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);

        Optional<User> existingUser = userRepository.findByEmail((reqDto.getEmail()));
        if (existingUser.isPresent()) {
            throw new Exception("Email already taken");
        }
        User newUser = userService.createNewUser(CreateNewUserReqBody.builder().email(reqDto.getEmail()).password(reqDto.getPassword()).role(Role.PHARMA).build());
        Pharma newPharma = pharmaRepository.save(Pharma.builder().details(reqDto.getDetails()).user(newUser).name(reqDto.getName()).address(reqDto.getAddress()).location(gf.createPoint(new Coordinate(reqDto.getLng(), reqDto.getLat()))).build());
        return new PharmaDto().toPharmaDto(newPharma);
    }


    public List<PharmaDto> findAllPharmaUsers() {
        List<Pharma> pharmas = pharmaRepository.findAll();
        return pharmas.stream().map(el -> new PharmaDto().toPharmaDto(el)).toList();
    }

    public PharmaDto findPharmaUser(String user_id) throws Exception {
        Optional<Pharma> pharmaUser = pharmaRepository.findByUser_Id(user_id);
        if (pharmaUser.isEmpty()) throw new Exception("User not found");
        return new PharmaDto().toPharmaDto(pharmaUser.get());
    }

    public PharmaDto findPharmaUserById(String id) throws Exception {
        Optional<Pharma> pharmaUser = pharmaRepository.findById(id);
        if (pharmaUser.isEmpty()) throw new Exception("User not found");
        return new PharmaDto().toPharmaDto(pharmaUser.get());
    }

    public PharmaDto findPharamUserByEmail(String email) throws Exception {
        Optional<Pharma> pharma = pharmaRepository.findByUser_Email(email);
        if (pharma.isEmpty()) throw new Exception("Not found");
        return new PharmaDto().toPharmaDto(pharma.get());
    }

    public List<PharmaDto> findNearbyPharmas(double lat, double lng, double radius) {
        List<Pharma> pharmas = pharmaRepository.findAllByDistance(lng, lat, radius);
        return pharmas.stream().map(p -> new PharmaDto().toPharmaDto(p)).toList();
    }
}
