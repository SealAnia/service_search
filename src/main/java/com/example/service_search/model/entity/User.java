package com.example.service_search.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	@Column(name = "nickname", nullable = false, unique = true)
	private String nickname;
	@Column(name = "max_number_of_orders", columnDefinition = "integer default 0")
	private Integer maxNumberOfOrders;
	@Column(name = "is_busy", columnDefinition = "BIT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isBusy;
	@ManyToOne
	@JoinColumn(name = "town_id")
	private Town town;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id")
	private Company company;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	//@OneToOne
	//private Portfolio portfolio;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "personal _data_id")
	private PersonalData personalData;
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Integer getMaxNumberOfOrders() {
		return maxNumberOfOrders;
	}
	public void setMaxNumberOfOrders(Integer maxNumberOfOrders) {
		this.maxNumberOfOrders = maxNumberOfOrders;
	}
	
	public boolean isBusy() {
		return isBusy;
	}
	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}
	
	public Town getTown() {
		return town;
	}
	public void setTown(Town town) {
		this.town = town;
	}
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	//public Portfolio getPortfolio() {
		//return portfolio;
	//}
	//public void setPortfolio(Portfolio portfolio) {
		//this.portfolio = portfolio;
	//}
	
	public PersonalData getPersonalData() {
		return personalData;
	}
	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public User() {
	}
	
	public User(Integer userId, String nickname, Integer maxNumberOfOrders, boolean isBusy, 
			Town town, Company company, Category category
			//, Portfolio portfolio, PersonalData personalData
			, Role role) {
		this.userId = userId;
		this.nickname = nickname;
		this.maxNumberOfOrders = maxNumberOfOrders;
		this.isBusy = isBusy;
		this.town = town;
		this.company = company;
		this.category = category;
		//this.portfolio = portfolio;
		//this.personalData = personalData;
		this.role = role;
	}
	
	public User(Integer userId, String nickname, Town town, PersonalData personalData, Role role) {
		this.userId = userId;
		this.nickname = nickname;
		this.town = town;
		this.personalData = personalData;
		this.role = role;
	}
	
}
