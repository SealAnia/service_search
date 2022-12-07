package com.example.service_search.dto;

import com.example.service_search.model.entity.Region;

public class TownDto {
	
	private Integer townId;
	private String name;
	private Region region;
	
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
	
	public TownDto() {
	}
	

}
