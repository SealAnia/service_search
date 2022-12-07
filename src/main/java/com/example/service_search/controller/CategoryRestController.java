package com.example.service_search.controller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.service_search.dto.CategoryDto;
import com.example.service_search.model.entity.Category;
import com.example.service_search.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping(value = "/categories")
public class CategoryRestController {
	
	private final CategoryServiceImpl categoryService;
	
	@Autowired
	public CategoryRestController(CategoryServiceImpl categoryService) {
		this.categoryService = categoryService;
	}
	
	//RETURNS ENTITIES
	@GetMapping(value = "") 
	public List<Category> findAllCategories() {
		return categoryService.getAll();
	}
	
	//RETURNS DTOS
	@GetMapping(value = "/all_dtos")
	public List<CategoryDto> findAllCategoryDtos(){
		return categoryService.getAllDtos();
	}
	
	//RETURNS AN ENTITY BY ID
	@GetMapping(value = "/{categoryId}") 
	public Category findCategoryById (@PathVariable Integer categoryId) {
		return categoryService.readById(categoryId);
	}
	
	//RETURNS A DTO BY ID
	@GetMapping(value = "/dto_by_id/{categoryId}") 
	public CategoryDto findCategoryDtoById (@PathVariable Integer categoryId) {
		return categoryService.readDtoById(categoryId);
	}
	
	@PostMapping(value = "/")
	public void createCategory(@RequestBody Category category) {
		categoryService.createOrUpdate(category);
	}

	@PutMapping(value = "/{categoryId}") 
	public ResponseEntity<Category> updateCategory(@PathVariable Integer categoryId, @RequestBody Category category) {
		Category updatedCategory = categoryService.readById(categoryId);
		updatedCategory.setName(category.getName());
		updatedCategory.setComment(category.getComment());
		updatedCategory.setAvailableOnline(category.isAvailableOnline());
		categoryService.createOrUpdate(updatedCategory);
		return ResponseEntity.ok(updatedCategory);
	}
	
	@DeleteMapping(value = "/{categoryId}") 
	public void deleteCategory(@PathVariable Integer categoryId) {
		categoryService.delete(categoryId);
	}
	
	//RETURNS EVERYTHING
	@GetMapping(value = "/all")
	public ModelAndView getAllCategories() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("category_views/Category");
		modelAndView.getModel().put("categories", categoryService.getAll());
		return modelAndView;
	}
	
	//RETURNS BY ID
	@GetMapping(value = "/by_id/{categoryId}")
	public ModelAndView getCategoryById(@PathVariable Integer categoryId) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			modelAndView.setViewName("category_views/CategoryDetails");
			modelAndView.getModel().put("category", categoryService.readById(categoryId));
			modelAndView.getModel().put("isAvailableOnline", Boolean.valueOf(categoryService.readById(categoryId).isAvailableOnline()));
			return modelAndView;
		} catch(NoSuchElementException e) {
			modelAndView.setViewName("category_views/NoSuchCategory");
			modelAndView.getModel().put("categoryId", categoryId);
			e.printStackTrace();
			return modelAndView;
		}
	}
	
	//CREATES A NEW CATEGORY
	@GetMapping(value = "/show_category_form")
	public ModelAndView showCategoryForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("category_views/AddCategory");
		modelAndView.getModel().put("category", new CategoryDto());
		return modelAndView;
	}
	
	@PostMapping(value = "")
	public ModelAndView addNewCategory(@ModelAttribute(name = "category") CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setComment(categoryDto.getDescription());
		
		if(categoryDto.getIsAvailableOnline().equals("YES")) {
			category.setAvailableOnline(true);
		} else if(categoryDto.getIsAvailableOnline().equals("NO")) {
			category.setAvailableOnline(false);
		}
		
		categoryService.createOrUpdate(category);
		return new ModelAndView("redirect:/categories/all");
	}
	
	//UPDATES A CATEGORY
	@GetMapping(value = "/edit/{categoryId}")
	public ModelAndView showCategoryEditForm(@PathVariable Integer categoryId, @ModelAttribute(name = "newCategory") CategoryDto newCategory) {
		CategoryDto dto = categoryService.readDtoById(categoryId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("category_views/UpdateCategoryInfo");
		modelAndView.addObject("category", dto);
		modelAndView.getModel().put("oldName", dto.getName());
		modelAndView.getModel().put("oldDescription", dto.getDescription());
		return modelAndView;
	}
	
	@PostMapping(value = "/by_id/{categoryId}")
	public ModelAndView updateCategory(@ModelAttribute("category") CategoryDto categoryDto, @PathVariable Integer categoryId) {
		
		ModelAndView modelAndView = new ModelAndView(); 
		Category category = categoryService.readById(categoryDto.getCategoryId());
		
		if(categoryDto.getName().length() == 0) {
			category.setName(category.getName());
		} else if(categoryDto.getName().length() > 0) {
			category.setName(categoryDto.getName());
		}
		
		if(categoryDto.getDescription().length() == 0) {
			category.setComment(category.getComment());
		} else if(categoryDto.getDescription().length() > 0) {
			category.setComment(categoryDto.getDescription());
		}
		
		if(categoryDto.getIsAvailableOnline().equals("YES")) {
			category.setAvailableOnline(true);
		} else if(categoryDto.getIsAvailableOnline().equals("NO")) {
			category.setAvailableOnline(false);
		} else if(categoryDto.getIsAvailableOnline().length() == 0) {
			category.setAvailableOnline(category.isAvailableOnline());
		}
		modelAndView.getModel().put("isAvailableOnline", Boolean.valueOf(categoryService.readById(categoryId).isAvailableOnline()));
		categoryService.createOrUpdate(category);
		
		modelAndView.setViewName("category_views/CategoryDetails"); 
		modelAndView.addObject("category", category);
		return modelAndView;
	}
	
	//DELETES A CATEGORY
	@GetMapping(value = "/delete/{categoryId}")
	public ResponseEntity<Integer> deleteACategory(@PathVariable Integer categoryId, HttpServletResponse response) throws IOException {
		categoryService.delete(categoryId);
		response.sendRedirect("/categories/all");
		return new ResponseEntity<Integer>(categoryId, HttpStatus.OK);
	}
	
}