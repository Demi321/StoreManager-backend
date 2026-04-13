package com.sm.storagemanager.SignUp.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sm.storagemanager.ResponseHttp.Response;
import com.sm.storagemanager.SignUp.dto.SignUpDto;
import com.sm.storagemanager.SignUp.service.SignUpService;

import lombok.extern.log4j.Log4j2; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/signup")
@Log4j2
public class SignUpController {
    
    @Autowired

    private SignUpService signUpService;

    @PostMapping("")
    public ResponseEntity<Response<SignUpDto>>signUp(@RequestBody SignUpDto signUpDto){
     
             return signUpService.singUp(signUpDto);
         
    }


}
