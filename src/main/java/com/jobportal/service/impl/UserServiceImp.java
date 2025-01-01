package com.jobportal.service.impl;

import com.jobportal.dto.LoginDto;
import com.jobportal.dto.UserDto;
import com.jobportal.entity.User;
import com.jobportal.exception.JobPortalException;
import com.jobportal.reposistory.UserRepository;
import com.jobportal.service.UserService;
import com.jobportal.utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "userService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) throws JobPortalException {
        Optional<User> isUserEmailPresent = userRepository.findByEmail(userDto.getEmail());
        if (isUserEmailPresent.isPresent()) {
            throw new JobPortalException("USER_FOUND");
        }
        userDto.setId(Utilities.getNextSequence("users"));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userDto.toUserEntity();
        userRepository.save(user);
        return user.toUserDto();
    }

    @Override
    public UserDto loginUser(LoginDto loginDto) throws JobPortalException {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND"));
        if(!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) throw new JobPortalException("INVALID_CREDENTIALS");

        return user.toUserDto();
    }
}
