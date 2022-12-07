package com.example.service_search.dto;

public class CategoryDto {
	
	private Integer categoryId;
	private String name;
	private String description;
	
	private String isAvailableOnline;
	//private List<String> subcategories;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	//public List<String> getSubcategories() {
		//return subcategories;
	//}
	//public void setSubcategories(List<String> subcategories) {
		//this.subcategories = subcategories;
	//}
	
	public String getIsAvailableOnline() {
		return isAvailableOnline;
	}
	public void setIsAvailableOnline(String isAvailableOnline) {
		this.isAvailableOnline = isAvailableOnline;
	}
	
	public CategoryDto() {
	}
	
}
