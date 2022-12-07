package com.example.service_search.dto;

import java.util.List;

import com.example.service_search.model.entity.Town;

public class RegionDto {
	
	private Integer regionId;
	private String name;
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
	
	public RegionDto() {
	}

}
