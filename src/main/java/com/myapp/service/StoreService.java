package com.myapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.model.StoreModel;
import com.myapp.repository.StoreRepository;

@Service("storeService")
public class StoreService {
	@Autowired 
	StoreRepository storeRepository; 
	// insert/update
	public StoreModel save(StoreModel store) {
		return  storeRepository.save(store);				
	}
	// get from id
	public StoreModel getFromId(String nome) {
		Optional <StoreModel> result = storeRepository.findById(nome);
		if(result.isPresent())
			return result.get();
		else
			return null;
	}
	// delete
	public boolean delete(StoreModel store) {
		try {
			storeRepository.delete(store);
			return true;
		}catch (Exception e) {
			return false;
		}		
	}
	public List<StoreModel> findAll() {
		return storeRepository.findAll();		
	}	
}
