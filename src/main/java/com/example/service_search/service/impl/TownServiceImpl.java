package com.example.service_search.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.service_search.model.entity.Town;
import com.example.service_search.model.repository.TownRepository;
import com.example.service_search.service.TownService;

@Service
public class TownServiceImpl implements TownService {
	
	private final TownRepository townRepository;
	
	@Autowired
	public TownServiceImpl(TownRepository townRepository) {
		this.townRepository = townRepository;
	}

	@Override
	public List<Town> getAll() {
		return townRepository.findAll(Sort.by(Direction.ASC, "name"));
	}

	@Override
	public Town readById(int townId) {
		return townRepository.findById(townId).get();
	}

	@Override
	public Town createOrUpdate(Town town) {
		return townRepository.saveAndFlush(town);
	}

	@Override
	public void delete(int townId) {
		townRepository.deleteById(townId);
	}

}
