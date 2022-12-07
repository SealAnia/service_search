package com.example.service_search.service;

import java.util.List;

public interface DefaultService<T> {
	
	List<T> getAll();
	T readById(int id);
	T createOrUpdate(T t);
	void delete(int id);

}
