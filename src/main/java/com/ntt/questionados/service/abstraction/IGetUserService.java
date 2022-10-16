package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.response.ListUsersResponse;
import com.ntt.questionados.dto.response.UserResponse;

public interface IGetUserService {

	  ListUsersResponse listActiveUsers();
	  UserResponse getUserAuthenticated();
	
}
