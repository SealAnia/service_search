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

import com.example.service_search.dto.CompanyDto;
import com.example.service_search.exception.NumberOfSymbolsDifferentFromRequiredException;
import com.example.service_search.model.entity.Company;
import com.example.service_search.service.impl.CompanyServiceImpl;

@RestController
@RequestMapping(value = "/companies")
public class CompanyRestController {
	
	private final CompanyServiceImpl companyService;
	
	@Autowired
	public CompanyRestController(CompanyServiceImpl companyService) {
		this.companyService = companyService;
	}
	
	@GetMapping(value = "")
	public List<Company> findAllCompanies() {
		return companyService.getAll();
	}
	
	@GetMapping(value = "/{companyId}")
	public Company findCompanyById(@PathVariable Integer companyId) {
		return companyService.readById(companyId);
	}
	
	@PostMapping(value = "/")
	public void createCompany(@RequestBody Company company) {
		companyService.createOrUpdate(company);
	}
	
	@PutMapping(value = "/{companyId}")
	public ResponseEntity<Company> updateCompany(@PathVariable Integer companyId, @RequestBody Company company) {
		Company updatedCompany = companyService.readById(companyId);
		updatedCompany.setName(company.getName());
		updatedCompany.setRegistrationNumber(company.getRegistrationNumber());
		companyService.createOrUpdate(updatedCompany);
		return ResponseEntity.ok(updatedCompany);
	}
	
	@DeleteMapping(value = "/{companyId}")
	public void deleteCompany(@PathVariable Integer companyId) {
		companyService.delete(companyId);
	}
	
	@GetMapping(value = "/all")
	public ModelAndView getAllCompanies() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("company_views/Company");
		modelAndView.getModel().put("companies", companyService.getAll());
		return modelAndView;
	}
	
	@GetMapping(value = "/by_id/{companyId}")
	public ModelAndView getCompanyById(@PathVariable Integer companyId) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.setViewName("company_views/CompanyDetails");
			modelAndView.getModel().put("company", companyService.readById(companyId));
			return modelAndView;
		} catch(NoSuchElementException e) {
			modelAndView.setViewName("company_views/NoSuchCompany");
			modelAndView.getModel().put("companyId", companyId);
			e.printStackTrace();
			return modelAndView;
		}
	}
	
	//NEEDS UPDATING!!! ADD USERID!!!
	@GetMapping(value = "/show_company_form")
	public ModelAndView showCompanyForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("company_views/AddCompany");
		modelAndView.getModel().put("company", new CompanyDto());
		return modelAndView;
	}
	
	//NEEDS UPDATING!!! ADD USERID!!!
	@PostMapping(value = "")
	public ModelAndView addNewCompany(@ModelAttribute(name = "company") CompanyDto companyDto) {
		ModelAndView modelAndView = new ModelAndView();
		Company company = new Company();
		company.setName(companyDto.getName());
		company.setRegistrationNumber(companyDto.getRegistrationNumber());
		
		try {
			companyService.createOrUpdateCompany(company);
			//companyService.createOrUpdate(company);
			modelAndView.setViewName("company_views/CompanyDetails");
			modelAndView.addObject(company);
			return modelAndView;
		}
		catch (NullPointerException e) {
			modelAndView.setViewName("company_views/AddCompany");
			modelAndView.getModel().put("message", "Registration number cannot be empty");
			return modelAndView;
		}
		catch (NumberOfSymbolsDifferentFromRequiredException e) {
			modelAndView.setViewName("company_views/AddCompany");
			modelAndView.getModel().put("message", e.getMessage());
			return modelAndView;
		}
	}
	
	@GetMapping(value = "/edit/{companyId}")
	public ModelAndView showEditCompanyForm(@PathVariable Integer companyId, @ModelAttribute(name = "newCompany") Company newCompany) {
		Company company = companyService.readById(companyId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("company_views/UpdateCompany");
		modelAndView.addObject("company", company);
		modelAndView.getModel().put("oldName", company.getName());
		modelAndView.getModel().put("oldRegistrationNumber", company.getRegistrationNumber());
		return modelAndView;
	}
	
	@PostMapping(value = "/by_id/{companyId}")
	public ModelAndView updateCompany(@PathVariable Integer companyId, @ModelAttribute(name = "company") CompanyDto companyDto) {
		ModelAndView modelAndView = new ModelAndView();
		Company company = companyService.readById(companyDto.getCompanyId());
		
		if(companyDto.getName().length() == 0) {
			company.setName(company.getName());
		} else if(companyDto.getName().length() > 0) {
			company.setName(companyDto.getName());
		}
		if(companyDto.getRegistrationNumber() == null) {
			company.setRegistrationNumber(company.getRegistrationNumber());
		} else if(companyDto.getRegistrationNumber() != null) {
			company.setRegistrationNumber(companyDto.getRegistrationNumber());
		}
		companyService.createOrUpdate(company);
		modelAndView.setViewName("company_views/CompanyDetails");
		modelAndView.addObject("company", company);
		return modelAndView;
	}
	
	@GetMapping(value = "/delete/{companyId}")
	public ResponseEntity<Integer> deleteCompany(@PathVariable Integer companyId, HttpServletResponse response) throws IOException {
		companyService.delete(companyId);
		response.sendRedirect("/companies/all");
		return new ResponseEntity<Integer>(companyId, HttpStatus.OK);
	}
	
}
