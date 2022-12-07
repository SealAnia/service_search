package com.example.service_search.controller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.service_search.dto.RegionDto;
import com.example.service_search.model.entity.Region;
import com.example.service_search.service.impl.RegionServiceImpl;

@RestController
@RequestMapping(value = "/regions")
public class RegionRestController {
	
	private final RegionServiceImpl regionService;
	
	@Autowired
	public RegionRestController(RegionServiceImpl regionService) {
		this.regionService = regionService;
	}
	
	@GetMapping(value = "")
	public List<Region> findAllRegions() {
		return regionService.getAll();
	}
	
	@GetMapping(value = "/{regionId}")
	public Region findRegionById(@PathVariable Integer regionId) {
		return regionService.readById(regionId);
	}
	
	@PostMapping(value = "/")
	public void createRegion(@RequestBody Region region) {
		regionService.createOrUpdate(region);
	}
	
	@PutMapping(value = "/{regionId}")
	public ResponseEntity<Region> updateRegion(@PathVariable Integer regionId, @RequestBody Region region) {
		Region updatedRegion = regionService.readById(regionId);
		updatedRegion.setName(region.getName());
		regionService.createOrUpdate(updatedRegion);
		return ResponseEntity.ok(updatedRegion);
	}
	
	@DeleteMapping(value = "/{regionId}")
	public void deleteRegion(@PathVariable Integer regionId) {
		regionService.delete(regionId);
	}
	
	@GetMapping(value = "/all") 
	public ModelAndView getAllRegions() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("region_views/Region");
		modelAndView.getModel().put("regions", regionService.getAll());
		return modelAndView;
	}
	
	@GetMapping(value = "/by_id/{regionId}")
	public ModelAndView getRegionById(@PathVariable Integer regionId) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			modelAndView.setViewName("region_views/RegionDetails");
			modelAndView.getModel().put("region", regionService.readById(regionId));
			modelAndView.getModel().put("towns", regionService.readById(regionId).getTowns());
			return modelAndView;
		} catch(NoSuchElementException e) {
			modelAndView.setViewName("region_views/NoSuchRegion");
			modelAndView.addObject("regionId", regionId);
			e.printStackTrace();
			return modelAndView;
		}
	}
	
	@GetMapping(value = "/show_region_form")
	public ModelAndView showRegionForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("region_views/AddRegion");
		modelAndView.getModel().put("region", new RegionDto());
		return modelAndView;
	}
	
	@PostMapping(value = "")
	public ModelAndView addNewRegion(@ModelAttribute(name = "region") RegionDto regionDto) {
		Region region = new Region();
		region.setName(regionDto.getName());
		regionService.createOrUpdate(region);
		return new ModelAndView("redirect:/regions/all");
	}
	
	@GetMapping(value = "/edit/{regionId}")
	public ModelAndView showEditRegionForm(@PathVariable Integer regionId, @ModelAttribute(name = "newRegion") Region newRegion) {
		Region region = regionService.readById(regionId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("region_views/UpdateRegion");
		modelAndView.addObject("region", region);
		modelAndView.getModel().put("oldName", region.getName());
		return modelAndView;
	}
	
	@PostMapping(value = "/by_id/{regionId}")
	public ModelAndView updateRegion(@PathVariable Integer regionId, @ModelAttribute(name = "region") RegionDto regionDto) {
		ModelAndView modelAndView = new ModelAndView();
		Region region = regionService.readById(regionDto.getRegionId());
		
		if(regionDto.getName().length() == 0) {
			region.setName(region.getName());
		} else if(regionDto.getName().length() > 0) {
			region.setName(regionDto.getName());
		}
		regionService.createOrUpdate(region);
		modelAndView.setViewName("region_views/RegionDetails");
		modelAndView.addObject("region", region);
		modelAndView.addObject("towns", region.getTowns());
		return modelAndView;
	}
	
	@GetMapping(value = "/delete/{regionId}")
	public ResponseEntity<Integer> deleteARegion(@PathVariable Integer regionId, HttpServletResponse response) throws IOException {
		regionService.delete(regionId);
		response.sendRedirect("/regions/all");
		return new ResponseEntity<Integer>(regionId, HttpStatus.OK);
	}

}
