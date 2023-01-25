package com.medifinder.medifinder.Pharma.Service;

import com.medifinder.medifinder.Auth.Model.Role;
import com.medifinder.medifinder.Auth.Model.User;
import com.medifinder.medifinder.Auth.Repository.UserRepository;
import com.medifinder.medifinder.Pharma.Dto.CreatePharmaReqDto;
import com.medifinder.medifinder.Pharma.Dto.PharmaDto;
import com.medifinder.medifinder.Pharma.Models.Pharma;
import com.medifinder.medifinder.Pharma.Repository.PharmaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PharmaService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PharmaRepository pharmaRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public PharmaDto createPharmaUser(CreatePharmaReqDto reqDto) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail((reqDto.getEmail()));
        if (existingUser.isPresent()) {
            throw new Exception("Email already taken");
        }
        User newUser = userRepository.save(
                new User(
                        reqDto.getEmail(),
                        passwordEncoder.encode(reqDto.getPassword()),
                        Role.PHARMA
                )
        );
        Pharma newPharma = pharmaRepository.save(
                new Pharma()
                        .setDetails(reqDto.getDetails())
                        .setUser(newUser)
        );
        return new PharmaDto().toPharmaDto(newPharma);
    }


    public List<PharmaDto> findAllPharmaUsers() {
        List<Pharma> pharmas = pharmaRepository.findAll();
        return pharmas.stream().map(el -> new PharmaDto().toPharmaDto(el)).toList();
    }

    public PharmaDto findPharmaUser(String user_id) throws Exception {
        Optional<Pharma> pharmaUser = pharmaRepository.findByUser_Id(user_id);
        if (pharmaUser.isEmpty())
            throw new Exception("User not found");
        return new PharmaDto().toPharmaDto(pharmaUser.get());
    }
}
