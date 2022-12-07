package com.example.service_search.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service_search.exception.NumberOfSymbolsDifferentFromRequiredException;
import com.example.service_search.model.entity.Company;
import com.example.service_search.model.repository.CompanyRepository;
import com.example.service_search.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private final CompanyRepository companyRepository;
	
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public List<Company> getAll() {
		return companyRepository.findAll();
	}

	@Override
	public Company readById(int companyId) {
		return companyRepository.findById(companyId).get();
	}
	
	@Override
	public Company createOrUpdate(Company company) {
		return companyRepository.saveAndFlush(company);
	}
	
	public Company createOrUpdateCompany(Company company) throws NumberOfSymbolsDifferentFromRequiredException {
		if(company.getRegistrationNumber().toString().length() != 10) {
			throw new NumberOfSymbolsDifferentFromRequiredException("Registration Number must consist of 10 chars");
		}
		return createOrUpdate(company);
		
	}

	@Override
	public void delete(int companyId) {
		companyRepository.deleteById(companyId);
	}

}
