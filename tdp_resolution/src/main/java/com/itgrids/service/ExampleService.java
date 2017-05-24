package com.itgrids.service;

import org.appfuse.service.GenericManager;

import com.itgrids.model.ExampleModel;

 public interface ExampleService extends GenericManager<ExampleModel, Integer> {

	ExampleModel getByUserName(String userName);
	
	

}
