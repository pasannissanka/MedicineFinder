package com.medifinder.medifinder.services;

import com.medifinder.medifinder.dto.AuthenticatedRequest;
import com.medifinder.medifinder.dto.AuthenticatedResponse;


public interface AuthenticationService {
    AuthenticatedResponse authenticate(AuthenticatedRequest request);

}
