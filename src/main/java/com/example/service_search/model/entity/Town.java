package com.example.service_search.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import static util.AllWordsToUpperCase.*;

import java.util.List;

@Entity
@Table(name = "town")
public class Town {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer townId;
	@Column(name = "name", columnDefinition = "TEXT", nullable = false)
	private String name;
	@ManyToOne
	@JoinColumn(name = "region_id")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	private Region region;
	@OneToMany(mappedBy = "town", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<User> users;
	
	public Integer getTownId() {
		return townId;
	}
	public void setTownId(Integer townId) {
		this.townId = townId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public Town() {
	}
	
	@Override
	public String toString() {
		return townId + ". " + allWordsToUpperCase(name) + ". Region: " + allWordsToUpperCase(region.getName());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + townId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	public boolean equals(Town town) {
		if(town == this) {
			return true;
		}
		if(town == null || town.getClass() != this.getClass()) {
			return false;
		}
		Town townTwo = (Town) town;
		return townId == townTwo.townId 
				&& (name == townTwo.name || (name != null && name.equals(townTwo.name)));
	}
	
}
