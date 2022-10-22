package com.ntt.questionados.config.seeder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ntt.questionados.config.security.common.Role;
import com.ntt.questionados.entity.RoleEntity;
import com.ntt.questionados.repository.IRoleRepository;

@Component
public class RoleSeeder {

	@Autowired
	private IRoleRepository roleRepository;

	@EventListener
	public void seed(ContextRefreshedEvent event) {
		createRoleTable();
	}

	private void createRoleTable() {
		if (roleRepository.count() < 2) {
			roleRepository.saveAll(List.of(buildRole(Role.USER), buildRole(Role.ADMIN)));
		}
	}

	private RoleEntity buildRole(Role role) {
		return RoleEntity.builder().name(role.getFullRoleName()).build();
	}

}
