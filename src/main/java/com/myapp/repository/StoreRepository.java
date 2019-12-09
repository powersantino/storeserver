package com.myapp.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.model.StoreModel;

@Repository
public interface StoreRepository extends JpaRepository<StoreModel, String>{	
	Optional <StoreModel> findById(String nome);
}
