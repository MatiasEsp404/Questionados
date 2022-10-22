package com.ntt.questionados.controller;

import com.ntt.questionados.config.security.common.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntt.questionados.dto.response.ListUsersResponse;
import com.ntt.questionados.service.abstraction.IGetUserService;

@RestController
@RequestMapping(path = Paths.USERS)
public class UserController {

	@Autowired
	private IGetUserService getUserService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListUsersResponse> listActiveUsers() {
		return ResponseEntity.ok().body(getUserService.listActiveUsers());
	}

}
