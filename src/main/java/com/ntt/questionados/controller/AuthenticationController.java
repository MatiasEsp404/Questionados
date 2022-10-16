package com.ntt.questionados.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntt.questionados.dto.request.AuthenticationRequest;
import com.ntt.questionados.dto.request.RegisterRequest;
import com.ntt.questionados.dto.response.AuthenticationResponse;
import com.ntt.questionados.dto.response.RegisterResponse;
import com.ntt.questionados.service.abstraction.IAuthenticationService;
import com.ntt.questionados.service.abstraction.IRegisterService;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

	@Autowired
	private IRegisterService registerService;

	@Autowired
	private IAuthenticationService authService;

	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(registerService.register(registerRequest));
	}

	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationResponse> login(
			@Valid @RequestBody AuthenticationRequest authenticationRequest) {
		return ResponseEntity.ok().body(authService.login(authenticationRequest));
	}

}
