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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.service_search.dto.RegionDto;
import com.example.service_search.dto.TownDto;
import com.example.service_search.model.entity.Region;
import com.example.service_search.model.entity.Town;
import com.example.service_search.service.impl.RegionServiceImpl;
import com.example.service_search.service.impl.TownServiceImpl;

@RestController
@RequestMapping(value = "/towns")
public class TownRestController {
	
	private final TownServiceImpl townService;
	private final RegionServiceImpl regionService;
	
	@Autowired
	public TownRestController(TownServiceImpl townService, RegionServiceImpl regionService) {
		this.townService = townService;
		this.regionService = regionService;
	}
	
	@GetMapping(value = "")
	public List<Town> findAllTowns() {
		return townService.getAll();
	}
	
	@GetMapping(value = "/{townId}")
	public Town findTownById(@PathVariable Integer townId) {
		return townService.readById(townId);
	}
	
	@PostMapping(value = "/")
	public void createTown(@RequestBody Town town) {
		townService.createOrUpdate(town);
	}
	
	@PutMapping(value = "/{townId}")
	public ResponseEntity<Town> updateTown(@PathVariable Integer townId, @RequestBody Town town) {
		Town updatedTown = townService.readById(townId);
		updatedTown.setName(town.getName());
		townService.createOrUpdate(updatedTown);
		return ResponseEntity.ok(updatedTown);
	}
	
	@DeleteMapping(value = "/{townId}")
	public void deleteTown(@PathVariable Integer townId) {
		townService.delete(townId);
	}
	
	//RETURNS EVERYTHING
	@GetMapping(value = "/all")
	public ModelAndView getAllTowns() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("town_views/Town");
		modelAndView.addObject("towns", townService.getAll());
		return modelAndView;
	}
	
	//RETURNS BY ID
	@GetMapping(value = "by_id/{townId}")
	public ModelAndView getTownById(@PathVariable Integer townId) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			modelAndView.setViewName("town_views/TownDetails");
			modelAndView.addObject("town", townService.readById(townId));
			return modelAndView;
		} catch(NoSuchElementException e) {
			modelAndView.setViewName("town_views/NoSuchTown");
			modelAndView.getModel().put("townId", townId);
			e.printStackTrace();
			return modelAndView;
		}
	}
	
	//CREATES A NEW TOWN
	@GetMapping(value = "/show_town_form")
	public ModelAndView showTownForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("town_views/AddTown");
		modelAndView.addObject("town", new TownDto());
		return modelAndView;
	}
	
	@PostMapping(value = "")
	public ModelAndView addNewTown(@ModelAttribute(name = "town") TownDto townDto, 
			//NEW 
			@ModelAttribute(name = "region") RegionDto regionDto, 
			@RequestParam(name = "regionId") Integer regionId) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		Town town = new Town();
		town.setName(townDto.getName());
		
		Region region = new Region();
		region = regionService.readById(regionId);
		town.setRegion(region);
		
		townService.createOrUpdate(town);
		
		modelAndView.setViewName("town_views/TownDetails");
		modelAndView.addObject("town", town);
		
		modelAndView.addObject("regions", regionService.getAll());
		return modelAndView;
	}
	
	//UPDATES A TOWN
	@GetMapping(value = "/edit/{townId}")
	public ModelAndView showTownEditForm(@PathVariable Integer townId, @ModelAttribute(name = "newTown") Town newTown) {
		Town town = townService.readById(townId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("town_views/UpdateTown");
		modelAndView.addObject("town", town);
		modelAndView.getModel().put("oldName", town.getName());
		modelAndView.getModel().put("oldRegion", town.getRegion().getName());
		return modelAndView;
	}
	
	@PostMapping(value = "/by_id/{townId}")
	public ModelAndView updateTown(@ModelAttribute(name = "town") TownDto townDto, @PathVariable Integer townId, 
			@RequestParam(name = "regionId") Integer regionId) {
		ModelAndView modelAndView = new ModelAndView();
		Town town = townService.readById(townDto.getTownId());
		
		if(townDto.getName().length() == 0) {
			town.setName(town.getName());
		} else if(townDto.getName().length() > 0) {
			town.setName(townDto.getName());
		}
		
		Region region = regionService.readById(regionId);
		town.setRegion(region);
		
		townService.createOrUpdate(town);
		modelAndView.setViewName("town_views/TownDetails");
		modelAndView.addObject("town", town);
		modelAndView.addObject("regions", regionService.getAll());
		return modelAndView;
	}
	
	@GetMapping(value = "/delete/{townId}")
	public ResponseEntity<Integer> deleteATown(@PathVariable Integer townId, HttpServletResponse response) throws IOException {
		townService.delete(townId);
		response.sendRedirect("/towns/all");
		return new ResponseEntity<Integer>(townId, HttpStatus.OK);
	}

}
