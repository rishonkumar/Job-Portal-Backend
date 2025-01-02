package com.jobportal.service.impl;

import com.jobportal.dto.LoginDto;
import com.jobportal.dto.UserDto;
import com.jobportal.entity.OTP;
import com.jobportal.entity.User;
import com.jobportal.exception.JobPortalException;
import com.jobportal.reposistory.OTPRepository;
import com.jobportal.reposistory.UserRepository;
import com.jobportal.service.UserService;
import com.jobportal.utility.Utilities;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

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

    @Override
    public Boolean sendOtp(String email) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
        message.setTo(email);
        message.setSubject("Your OTP Code");
        String generateOtp = Utilities.generateOtp();
        OTP otp = new OTP(email,generateOtp, LocalDateTime.now());
        otpRepository.save(otp);
        message.setText(" " + generateOtp, false);
        mailSender.send(mimeMessage);
        return true;
    }
}
