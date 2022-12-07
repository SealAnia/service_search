package com.example.service_search.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.service_search.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
}
