package com.itgrids.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.ExampleModel;

public interface ExampleDao extends GenericDao<ExampleModel,Integer>{
	
	public ExampleModel getExampleByUsername(String userName);

}
