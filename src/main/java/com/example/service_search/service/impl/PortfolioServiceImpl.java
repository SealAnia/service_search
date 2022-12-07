package com.example.service_search.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service_search.model.entity.Portfolio;
import com.example.service_search.model.repository.PortfolioRepository;
import com.example.service_search.service.PortfolioService;

@Service
public class PortfolioServiceImpl implements PortfolioService {
	
	private final PortfolioRepository portfolioRepository;
	
	@Autowired
	public PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
		this.portfolioRepository = portfolioRepository;
	}

	@Override
	public List<Portfolio> getAll() {
		return portfolioRepository.findAll();
	}

	@Override
	public Portfolio readById(int portfolio_id) {
		return portfolioRepository.findById(portfolio_id).get();
	}

	@Override
	public Portfolio createOrUpdate(Portfolio portfolio) {
		return portfolioRepository.saveAndFlush(portfolio);
	}

	@Override
	public void delete(int portfolio_id) {
		portfolioRepository.deleteById(portfolio_id);
	}

}
