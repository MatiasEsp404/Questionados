package com.ntt.questionados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ntt.questionados.config.security.common.Role;
import com.ntt.questionados.config.security.util.JwtUtils;
import com.ntt.questionados.dto.request.AuthenticationRequest;
import com.ntt.questionados.dto.request.RegisterRequest;
import com.ntt.questionados.dto.response.AuthenticationResponse;
import com.ntt.questionados.dto.response.RegisterResponse;
import com.ntt.questionados.entity.RoleEntity;
import com.ntt.questionados.entity.UserEntity;
import com.ntt.questionados.config.exception.runtime.EntityNotFoundException;
import com.ntt.questionados.config.exception.runtime.InvalidCredentialsException;
import com.ntt.questionados.config.exception.runtime.UserAlreadyExistException;
import com.ntt.questionados.mapper.UserMapper;
import com.ntt.questionados.repository.IRoleRepository;
import com.ntt.questionados.repository.IUserRepository;
import com.ntt.questionados.service.abstraction.IAuthenticationService;
import com.ntt.questionados.service.abstraction.IRegisterService;

@Service
public class AuthenticationService implements IRegisterService, IAuthenticationService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authManager;

	@Override
	public RegisterResponse register(RegisterRequest registerRequest) {
		if (userRepository.findByEmail(registerRequest.getEmail()) != null) {
			throw new UserAlreadyExistException("Email is already in use.");
		}

		RoleEntity userRoleEntity = roleRepository.findByName(Role.USER.getFullRoleName());
		if (userRoleEntity == null) {
			throw new EntityNotFoundException("Missing record in role table.");
		}

		UserEntity userEntity = userMapper.toUserEntity(registerRequest);
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userEntity.setSoftDeleted(false);
		userEntity.setRole(userRoleEntity);
		userEntity = userRepository.save(userEntity);

		RegisterResponse registerResponse = userMapper.toRegisterResponse(userEntity);
		registerResponse.setToken(jwtUtils.generateToken(userEntity));
		return registerResponse;
	}

	@Override
	public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
		Authentication authentication;
		try {
			authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail(), authenticationRequest.getPassword()));
		} catch (Exception e) {
			throw new InvalidCredentialsException("Invalid email or password.");
		}

		String jwt = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());
		return new AuthenticationResponse(jwt);
	}

}
