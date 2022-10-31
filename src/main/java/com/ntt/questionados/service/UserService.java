package com.ntt.questionados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ntt.questionados.config.security.util.SecurityUtils;
import com.ntt.questionados.dto.response.ListUsersResponse;
import com.ntt.questionados.dto.response.UserResponse;
import com.ntt.questionados.entity.UserEntity;
import com.ntt.questionados.mapper.UserMapper;
import com.ntt.questionados.repository.IUserRepository;
import com.ntt.questionados.service.abstraction.IGetUserService;

@Service
public class UserService implements UserDetailsService, IGetUserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SecurityUtils securityUtils;

	@Override
	public ListUsersResponse listActiveUsers() {
		List<UserEntity> listUserEntities = userRepository.findAllActiveUsers();
		ListUsersResponse listUsersResponse = new ListUsersResponse();
		listUsersResponse.setUsers(userMapper.toListUserResponse(listUserEntities));
		return listUsersResponse;
	}

	@Override
	public UserResponse getUserAuthenticated() {
		return userMapper.toUserResponse((UserEntity) securityUtils.getUserAuthenticated());
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return getUser(email);
	}

	private UserEntity getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		return userEntity;
	}

}
