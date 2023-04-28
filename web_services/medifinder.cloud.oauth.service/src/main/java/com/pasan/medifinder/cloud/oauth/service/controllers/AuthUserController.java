package com.pasan.medifinder.cloud.oauth.service.controllers;

import com.pasan.medifinder.cloud.oauth.service.dtos.AuthUserDto;
import com.pasan.medifinder.cloud.oauth.service.dtos.request.CreateAuthUserRequest;
import com.pasan.medifinder.cloud.oauth.service.services.UserService;
import com.pasan.medifinder.cloud.oauth.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController()
@RequestMapping("/auth/users")
public class AuthUserController {

    private final UserService userService;

    @Autowired
    public AuthUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<HashMap<String, ?>> createNewUser(@RequestBody CreateAuthUserRequest request) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            AuthUserDto userDto = userService.create(request);

            response.put(Utils.MESSAGE, Utils.USER_CREATED);
            response.put(Utils.STATUS, Utils.SUCCESS);
            response.put(Utils.DATA, userDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put(Utils.MESSAGE, e.getMessage());
            response.put(Utils.STATUS, Utils.FAILED);
            return ResponseEntity.unprocessableEntity().body(response);
        }
    }
}
