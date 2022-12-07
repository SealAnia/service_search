package com.example.service_search.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer companyId;
	@Column(name = "name", columnDefinition = "TEXT", nullable = false)
	private String name;
	@Column(name = "registration_number", columnDefinition = "BIGINT", nullable = false, unique = true)
	private Long registrationNumber;
	@OneToOne(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
	private User user;
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(Long registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Company() {
	}
	
	@Override
	public String toString() {
		return companyId.toString() + ". " + name.substring(0, 1).toUpperCase() + name.substring(1) 
		+ " registration number: " + registrationNumber.toString();
	}
	
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + companyId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((registrationNumber == null) ? 0 : registrationNumber.hashCode());
		return result;
	}
	
	public boolean equals(Company company) {
		if(company == this) {
			return true;
		}
		if(company == null || company.getClass() != this.getClass()) {
			return false;
		}
		Company companyTwo = (Company) company;
		return companyId == companyTwo.companyId
				&& (name == companyTwo.name || (name != null && name.equals(companyTwo.name)))
				&& registrationNumber == companyTwo.registrationNumber;
	}
	
}
