package com.jobportal.controller;

import com.jobportal.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin //can be used from any resources
@Validated
@RequestMapping("/profile")
public class ProfileController
{
    @Autowired
    private ProfileService profileService;


}
