package com.example.service_search.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.service_search.model.entity.Region;
import com.example.service_search.model.repository.RegionRepository;
import com.example.service_search.service.RegionService;

@Service
public class RegionServiceImpl implements RegionService {
	
	@Autowired
	private final RegionRepository regionRepository;
	
	public RegionServiceImpl(RegionRepository regionRepository) {
		this.regionRepository = regionRepository;
	}
	
	@Override
	public List<Region> getAll() {
		return regionRepository.findAll(Sort.by(Direction.ASC, "name"));
	}

	@Override
	public Region readById(int regionId) {
		return regionRepository.findById(regionId).get();
	}

	@Override
	public Region createOrUpdate(Region region) {
		return regionRepository.saveAndFlush(region);
	}

	@Override
	public void delete(int regionId) {
		regionRepository.deleteById(regionId);
	}

}
