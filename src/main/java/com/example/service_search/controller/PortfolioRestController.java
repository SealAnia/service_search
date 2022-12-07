package com.example.service_search.controller;

import java.awt.Image;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.example.service_search.model.entity.Portfolio;
import com.example.service_search.service.impl.PortfolioServiceImpl;

import util.ImageUtility;

@RestController
@RequestMapping(value = "/goods")
public class PortfolioRestController {
	
	private final PortfolioServiceImpl portfolioService;
	
	@Autowired
	public PortfolioRestController(PortfolioServiceImpl portfolioService) {
		this.portfolioService = portfolioService;
	}
	
	@GetMapping(value = "")
	public List<Portfolio> findAllGoodsAndServices() {
		return portfolioService.getAll();
	}
	
	@PostMapping("/upload")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file, @RequestBody Portfolio portfolio) throws IOException {
		
		portfolioService.createOrUpdate(portfolio);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }
	
}
