package com.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.model.StoreModel;
import com.myapp.service.StoreService;

@RestController
public class Endpoint {
	private static final Logger LOGGER = Logger.getLogger(Endpoint.class.getName());
	@Autowired
	StoreService storeService;
	// INSERT.....................................................................
	@CrossOrigin
	@PostMapping("/store")
	public  ResponseEntity<Object> insertStore(@RequestBody StoreModel store) {
		
		if(store.getNome()== null) {
			LOGGER.info(HttpStatus.BAD_REQUEST + "insert error, invalid param." );
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}		
		if(store!=null && !store.getNome().isEmpty()) {
				StoreModel savedStore = storeService.save(store);
				return new ResponseEntity<>(savedStore,HttpStatus.ACCEPTED);
		}
		else {
			LOGGER.info(HttpStatus.BAD_REQUEST + "insert error, invalid param." );
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}		
	}
	// UPLOAD...................................................................
	@CrossOrigin	
	@PutMapping("/store/{nome}")
	public ResponseEntity<Object> updateStore(@RequestBody StoreModel store,@PathVariable String nome) {
		try {
			if(store!=null) {
				StoreModel storeDb = storeService.getFromId(nome);
				if(storeDb== null) {
					LOGGER.info(HttpStatus.BAD_REQUEST + "insert error, invalid param." );
					return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
				}
				StoreModel savedStore = storeService.save(store);
		   		if(savedStore!=null)
		   			return new ResponseEntity<>(savedStore,HttpStatus.OK);
		   		else {
		   			LOGGER.info(HttpStatus.BAD_REQUEST + "insert error, invalid param." );
		   			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		   		}		   			
		   	}else {
		   		LOGGER.info(HttpStatus.BAD_REQUEST + "insert error, invalid param." );
		   		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		   	}
		}catch (Exception e) {
			LOGGER.severe(e.getMessage());
			return new ResponseEntity<>(new HashMap<String,String>().put("message", e.getMessage()),HttpStatus.SERVICE_UNAVAILABLE);
		}	   				
	}
	// DELETE...................................................................
	@CrossOrigin
	@DeleteMapping("/store/{nome}")	 
	public ResponseEntity<Object> deleteStore(@PathVariable String nome) {
		System.out.println("DELETE SERVICE");
		try {
			if(nome.isEmpty()) {
				LOGGER.info(HttpStatus.BAD_REQUEST + "insert error, invalid param." );
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}
				
			StoreModel store =  storeService.getFromId(nome);			
			if(store!=null) {
				storeService.delete(store);
				if(storeService.getFromId(nome)==null)
					return new ResponseEntity<>(null,HttpStatus.OK);
				else {
					LOGGER.info(HttpStatus.NOT_MODIFIED + "insert error, invalid param." );
					return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
				}					
			}else {
				LOGGER.info(HttpStatus.BAD_REQUEST + "insert error, invalid param." );
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}			
		}catch (Exception e) {
			LOGGER.severe(e.getMessage());
			return new ResponseEntity<>(new HashMap<String,String>().put("message", e.getMessage()),HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	// GET.................................................................
	@CrossOrigin
	@GetMapping("/store")
	public ResponseEntity<Object> getAllStore(){		
		try {
			List<StoreModel> listStore = storeService.findAll();
			if(listStore!=null && !listStore.isEmpty()) {
				return new ResponseEntity<>(listStore,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}	
		}catch (Exception e) {
			LOGGER.severe(e.getMessage());
			return new ResponseEntity<>(new HashMap<String,String>().put("message", e.getMessage()),HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	// GET DETAIL ....................................................................
	@CrossOrigin
	@GetMapping("/store/detail")
	public ResponseEntity<Object> getStoreFromName(@RequestParam("nome") String nome){
		try {
			StoreModel store = storeService.getFromId(nome);
			if(store!=null ) {
				return new ResponseEntity<>(store,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
			}	
		}catch (Exception e) {
			LOGGER.severe(e.getMessage());
			return new ResponseEntity<>(new HashMap<String,String>().put("message", e.getMessage()),HttpStatus.SERVICE_UNAVAILABLE);
		}		
	}
}
