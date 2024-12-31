package com.jobportal.service.impl;

import com.jobportal.dto.UserDto;
import com.jobportal.entity.User;
import com.jobportal.reposistory.UserRepository;
import com.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = userDto.toUserEntity();
        userRepository.save(user);
        return user.toUserDto();
    }
}
