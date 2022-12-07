package com.example.service_search.dto;

public class PortfolioDto {
	
	private Integer portfolioId;
	private byte[] image;
	private String description;
	
	public Integer getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(Integer portfolioId) {
		this.portfolioId = portfolioId;
	}
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public PortfolioDto() {
	}

}
