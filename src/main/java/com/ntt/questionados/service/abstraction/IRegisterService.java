package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.request.RegisterRequest;
import com.ntt.questionados.dto.response.RegisterResponse;

public interface IRegisterService {

	RegisterResponse register(RegisterRequest registerRequest);
	
}
