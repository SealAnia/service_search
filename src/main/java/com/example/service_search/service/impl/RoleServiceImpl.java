package com.example.service_search.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service_search.model.entity.Role;
import com.example.service_search.model.repository.RoleRepository;
import com.example.service_search.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	private final RoleRepository roleRepository;
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public List<Role> getAll() {
		return roleRepository.findAll();
	}
	
	@Override
	public Role readById(int roleId) {
		return roleRepository.findById(roleId).get();
	}

	@Override
	public Role createOrUpdate(Role role) {
		return roleRepository.saveAndFlush(role);
	}
	
	@Override
	public void delete(int roleId) {
		roleRepository.deleteById(roleId);
	}
	
}
