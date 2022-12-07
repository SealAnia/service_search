package com.example.service_search.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.service_search.convertor.CategoryConvertor;
import com.example.service_search.dto.CategoryDto;
import com.example.service_search.model.entity.Category;
import com.example.service_search.model.repository.CategoryRepository;
import com.example.service_search.service.CategoryDtoService;
import com.example.service_search.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService, CategoryDtoService {
	
	private final CategoryRepository categoryRepository;
	private final CategoryConvertor convertor;
	
	@Autowired
	public CategoryServiceImpl (CategoryRepository categoryRepository, CategoryConvertor convertor) {
		this.categoryRepository = categoryRepository;
		this.convertor = convertor;
	}
	
	//A METHOD TO RETURN ALL ENTITIES
	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll(Sort.by(Direction.ASC, "name"));
	}
	
	//A METHOD TO RETURN ALL DTOS
	@Override
	public List<CategoryDto> getAllDtos() {
		return categoryRepository.findAll().stream()
				.map(convertor::convertToDto)
				.collect(Collectors.toList());
	}
	
	//A METHOD TO RETURN AN ENTITY BY ID
	@Override
	public Category readById(int categoryId) {
		return categoryRepository.findById(categoryId).get();
	}
	
	//A METHOD TO RETURN A DTO BY ID
	@Override
	public CategoryDto readDtoById(int categoryId) {
		return categoryRepository.findById(categoryId).map(convertor::convertToDto).get();
	}
	
	@Override
	public Category createOrUpdate(Category category) {
		return categoryRepository.saveAndFlush(category);
	}

	@Override
	public void delete(int categoryId) {
		categoryRepository.deleteById(categoryId);
	}
	
}
