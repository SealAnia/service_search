package com.example.service_search.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

//import util.AllWordsToUpperCase;
import static util.AllWordsToUpperCase.*;

import java.util.List;

@Entity
@Table(name = "region")
public class Region {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer regionId;
	@Column(name = "name", columnDefinition = "TEXT", nullable = false)
	private String name;
	@OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<Town> towns;
	
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Town> getTowns() {
		return towns;
	}
	public void setTowns(List<Town> towns) {
		this.towns = towns;
	}
	
	public Region() {
	}
	
	@Override
	public String toString() {
		return regionId.toString() + ". " + allWordsToUpperCase(name);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + regionId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	public boolean equals(Region region) {
		if(region == this) {
			return true;
		} 
		if(region == null || region.getClass() != this.getClass()) {
			return false;
		}
		Region regionTwo = new Region();
		return region.regionId == regionTwo.regionId
				&& (name == regionTwo.name || (name != null && name.equals(regionTwo.name)));
	}
	
}
