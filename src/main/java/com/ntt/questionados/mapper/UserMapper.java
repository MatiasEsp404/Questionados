package com.ntt.questionados.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ntt.questionados.dto.request.RegisterRequest;
import com.ntt.questionados.dto.response.RegisterResponse;
import com.ntt.questionados.dto.response.UserResponse;
import com.ntt.questionados.entity.UserEntity;

@Component
public class UserMapper {

	public UserEntity toUserEntity(RegisterRequest registerRequest) {
		return UserEntity.builder().username(registerRequest.getUsername()).email(registerRequest.getEmail())
				.password(registerRequest.getPassword()).build();
	}

	public RegisterResponse toRegisterResponse(UserEntity userEntity) {
		return RegisterResponse.builder().id(userEntity.getId()).username(userEntity.getEmail())
				.email(userEntity.getEmail()).build();
	}

	public List<UserResponse> toListUserResponse(List<UserEntity> listUserEntities) {
		List<UserResponse> userResponses = new ArrayList<>();
		for (UserEntity userEntity : listUserEntities) {
			userResponses.add(toUserResponse(userEntity));
		}
		return userResponses;
	}

	public UserResponse toUserResponse(UserEntity userEntity) {
		return UserResponse.builder()
				.id(userEntity.getId())
				.username(userEntity.getUsername())
				.email(userEntity.getEmail())
				.role(userEntity.getRole().getName())
				.build();
	}

}
