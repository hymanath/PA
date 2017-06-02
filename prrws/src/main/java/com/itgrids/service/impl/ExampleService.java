package com.itgrids.service.impl;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IExampleDao;
import com.itgrids.model.ExampleModel;
import com.itgrids.service.IExampleService;

@Service
 public class ExampleService extends GenericManagerImpl<ExampleModel, Integer> implements IExampleService{
	
	
	
   private IExampleDao exampleDao;
   
   @Autowired
   public ExampleService(IExampleDao exampleDao) {
	        super(exampleDao);
	        this.exampleDao = exampleDao;
	    }
	
	@Transactional
	@Override
	public ExampleModel get(Integer id) {
		
		return super.get(id);
	}
	
	@Transactional
	@Override
		public ExampleModel save(ExampleModel object) {
			// TODO Auto-generated method stub
			return super.save(object);
		}
	

	

}
