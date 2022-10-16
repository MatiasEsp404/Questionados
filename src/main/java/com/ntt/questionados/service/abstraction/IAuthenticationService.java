package com.ntt.questionados.service.abstraction;

import com.ntt.questionados.dto.request.AuthenticationRequest;
import com.ntt.questionados.dto.response.AuthenticationResponse;

public interface IAuthenticationService {

	AuthenticationResponse login(AuthenticationRequest authenticationRequest);

}
