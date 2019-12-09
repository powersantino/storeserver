package com.myapp;


import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.myapp.model.StoreModel;
import com.myapp.repository.StoreRepository;

/*
 * Classe di debug per il popolamento del DB 
 */
@Component
public class DataLoader implements ApplicationRunner {	
	private StoreRepository storeRepository;
	@Autowired
    public DataLoader(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
	
	@Override
	public void run(ApplicationArguments args) throws Exception {		
		StoreModel store = new StoreModel();
		store.setNome("NOME_1");
		store.setIndirizzo("INDIRIZZO_1");
		store.setLat(new BigDecimal(43.4628032));
		store.setLon(new BigDecimal(11.8786277));
		File file = new ClassPathResource("/static/casa1.jpg").getFile();
		store.setFoto( Files.readAllBytes(file.toPath()));
		storeRepository.save(store);
		
		store.setNome("NOME_2");
		store.setIndirizzo("INDIRIZZO_2");
		store.setLat(new BigDecimal(45.38406));
		store.setLon(new BigDecimal(12.04681));
		file = new ClassPathResource("/static/casa2.jpg").getFile();
		store.setFoto( Files.readAllBytes(file.toPath()));
		storeRepository.save(store);	
		
		store.setNome("NOME_3");
		store.setIndirizzo("INDIRIZZO_3");
		store.setLat(new BigDecimal(43.4628032));
		store.setLon(new BigDecimal(11.8786277));
		file = new ClassPathResource("/static/casa3.jpg").getFile();
		store.setFoto( Files.readAllBytes(file.toPath()));
		storeRepository.save(store);
	}
}

	
	
