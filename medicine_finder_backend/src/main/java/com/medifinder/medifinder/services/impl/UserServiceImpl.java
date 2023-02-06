package com.medifinder.medifinder.services.impl;

import com.medifinder.medifinder.dto.CreateNewUserReqBody;
import com.medifinder.medifinder.dto.UserDto;
import com.medifinder.medifinder.entities.User;
import com.medifinder.medifinder.repositories.UserRepository;
import com.medifinder.medifinder.services.EmailService;
import com.medifinder.medifinder.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public User createNewUser(CreateNewUserReqBody data) throws Exception {
        Optional<User> user = userRepository.findByEmail(data.getEmail());

        if (user.isPresent())
            throw new Exception("Email already taken");

        User newUser = new User(data.getEmail(), passwordEncoder.encode(data.getPassword()), data.getRole());
        userRepository.save(newUser);

        emailService.sendVerifyEmail(newUser.getEmail(), newUser.getUsername(), newUser.getEmailVerificationToken());
        return newUser;
    }

    public UserDto findUserByEmail(String email) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty())
            throw new Exception("User not found");
        return new UserDto().toUserDto(user.get());
    }

    public boolean verifyEmail(String token) throws Exception {
        Optional<User> user = userRepository.findByEmailVerificationToken(token);
        if (user.isEmpty())
            throw new Exception("token not found");
        int rowsAffected = userRepository.updateVerifiedAndEmailVerificationTokenById(true, user.get().getId());
        return rowsAffected == 1;
    }
}
