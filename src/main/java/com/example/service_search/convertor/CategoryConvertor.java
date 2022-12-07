package com.example.service_search.convertor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.service_search.dto.CategoryDto;
import com.example.service_search.model.entity.Category;

@Component
public class CategoryConvertor {
	
	private final ModelMapper modelMapper;
	
	public CategoryConvertor() {
		this.modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(Category.class, CategoryDto.class)
		.addMapping(Category::getComment, CategoryDto::setDescription)//Маппит поля при создании объекта
		.addMappings(mapper -> mapper.map(Category::getComment, CategoryDto::setDescription));//Маппит поля при вычитке
	}
	
	public CategoryDto convertToDto(Category category){
		return modelMapper.map(category, CategoryDto.class);
	}
	
	public Category convertToEntity(CategoryDto dto) {
		return modelMapper.map(dto, Category.class);
	}

}
