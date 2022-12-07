package com.example.service_search.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	@Column(name = "name", columnDefinition = "TEXT", nullable = false, unique = true)
	private String name;
	@Column(name = "comment", columnDefinition = "TEXT")
	private String comment;
	@Column(name = "available_online", columnDefinition = "BIT", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isAvailableOnline;
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<User> user;
	
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

	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public boolean isAvailableOnline() {
		return isAvailableOnline;
	}
	public void setAvailableOnline(boolean isAvailableOnline) {
		this.isAvailableOnline = isAvailableOnline;
	}
	
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	
	public Category() {
	}
	
	@Override
	public String toString() {
		return categoryId.toString() + ". " + name + ". " + comment;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((isAvailableOnline) ? 0 : 1);
		return result;
	}
	
	public boolean equals(Category category) {
		if(category == this) {
			return true;
		}
		if(category == null || category.getClass() != this.getClass()) {
			return false;
		}
		Category categoryTwo = (Category) category;
		return categoryId == categoryTwo.categoryId
				&& (name == categoryTwo.name || (name != null && name.equals(categoryTwo.name)))
				&& (comment == categoryTwo.comment || (comment != null && comment.equals(categoryTwo.comment)))
				&& isAvailableOnline == categoryTwo.isAvailableOnline;
	}
	
}
