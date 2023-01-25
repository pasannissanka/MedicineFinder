package com.medifinder.medifinder.Auth.Service;

import com.medifinder.medifinder.Auth.Dto.CreateNewUserReqBody;
import com.medifinder.medifinder.Auth.Dto.UserDto;
import com.medifinder.medifinder.Auth.Model.User;
import com.medifinder.medifinder.Auth.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserDto createNewUser(CreateNewUserReqBody data) throws Exception {
        Optional<User> user = userRepository.findByEmail(data.getEmail());

        if (user.isPresent())
            throw new Exception("Email already taken");

        User newUser = new User(data.getEmail(), passwordEncoder.encode(data.getPassword()), data.getRole());
        userRepository.save(newUser);

        return new UserDto().toUserDto(newUser);
    }

    public UserDto findUserByEmail(String email) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty())
            throw new Exception("User not found");
        return new UserDto().toUserDto(user.get());
    }
}
