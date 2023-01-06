package com.example.service_search.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.example.service_search.dto.PortfolioDto;
import com.example.service_search.model.entity.Portfolio;
import com.example.service_search.service.impl.PortfolioServiceImpl;

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
	
	@GetMapping(value = "/show_portfolio_form")
	public ModelAndView showPortfolioForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("portfolio_views/AddPortfolio");
		modelAndView.getModel().put("portfolio", new PortfolioDto());
		return modelAndView;
	}
	
	@PostMapping(value = "")
	public ModelAndView addNewPortfolio(@ModelAttribute(name = "portfolio") PortfolioDto portfolioDto) {
		Portfolio portfolio = new Portfolio();
		portfolio.setDescription(portfolioDto.getDescription());
		portfolio.setImage(portfolioDto.getImage());
		portfolioService.createOrUpdate(portfolio);
		return new ModelAndView("redirect:/goods");
	}
	
}
