package com.sm.storagemanager.SignUp.service;

import org.springframework.http.ResponseEntity;

import com.sm.storagemanager.ResponseHttp.Response;
import com.sm.storagemanager.SignUp.dto.SignUpDto;

public interface SignUpService {

    public ResponseEntity<Response<SignUpDto>> singUp(SignUpDto signUpDto);
}
