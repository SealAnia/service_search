package com.example.service_search.service;

import java.util.List;

public interface DefaultDtoService<D> {
	
	List<D> getAllDtos();
	D readDtoById(int id);

}
