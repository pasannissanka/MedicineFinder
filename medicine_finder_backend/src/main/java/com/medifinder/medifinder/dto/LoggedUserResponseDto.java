package com.medifinder.medifinder.dto;

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
