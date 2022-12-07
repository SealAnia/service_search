package com.example.service_search.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.service_search.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
}
