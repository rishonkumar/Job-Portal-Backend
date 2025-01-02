package com.jobportal.service;

import com.jobportal.dto.LoginDto;
import com.jobportal.dto.UserDto;
import com.jobportal.exception.JobPortalException;

public interface UserService {
    public UserDto registerUser(UserDto userDto) throws JobPortalException;

    UserDto loginUser(LoginDto loginDto) throws JobPortalException;

    public Boolean sendOtp(String email) throws Exception;
}
