package com.medifinder.medifinder.Pharma.Dto;

import com.medifinder.medifinder.Auth.Dto.UserDto;
import com.medifinder.medifinder.Pharma.Models.Pharma;
import com.medifinder.medifinder.Pharma.PharmaRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PharmaDto {
    private String id;
    private UserDto user;
    private String details;
    private String address;

    @Autowired
    private PharmaRepository pharmaRepository;

    public PharmaDto toPharmaDto(Pharma data) {
        return new PharmaDto()
                .setId(data.getId())
                .setUser(new UserDto().toUserDto(data.getUser()))
                .setDetails(data.getDetails())
                .setAddress(data.getAddress());
    }

    public Pharma toPharma(PharmaDto pharmaDto) throws Exception {
        Optional<Pharma> pharma = pharmaRepository.findById(pharmaDto.getId());
        if (pharma.isEmpty())
            throw new Exception("Not found");
        return pharma.get();
    }
}
