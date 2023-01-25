package com.medifinder.medifinder.Auth.Dto;

import com.medifinder.medifinder.Customer.Dto.CustomerDto;
import com.medifinder.medifinder.Pharma.Dto.PharmaDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class LoggedUserResponseDto {
    @Nullable
    private PharmaDto pharmacyUser;
    @Nullable
    private CustomerDto customerUser;

}
