package com.ntt.questionados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ntt.questionados.entity.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	  @Query(value = "SELECT u FROM UserEntity u WHERE u.softDeleted=false")
	  List<UserEntity> findAllActiveUsers();
	
}
