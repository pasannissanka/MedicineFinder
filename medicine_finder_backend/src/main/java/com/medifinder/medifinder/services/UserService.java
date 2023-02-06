package com.medifinder.medifinder.services;

import com.medifinder.medifinder.dto.CreateNewUserReqBody;
import com.medifinder.medifinder.dto.UserDto;
import com.medifinder.medifinder.entities.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User createNewUser(CreateNewUserReqBody data) throws Exception;

    UserDto findUserByEmail(String email) throws Exception;

    boolean verifyEmail(String token) throws Exception;
}
