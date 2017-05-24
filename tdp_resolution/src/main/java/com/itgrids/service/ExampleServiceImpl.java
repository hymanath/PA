package com.itgrids.service;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.ExampleDao;
import com.itgrids.dao.IUserAdressDAO;
import com.itgrids.model.ExampleModel;
import com.itgrids.model.UserAddress;

@Service
 public class ExampleServiceImpl extends GenericManagerImpl<ExampleModel, Integer> implements ExampleService{
	
	
	
   private ExampleDao exampleDao;
   
   @Autowired
   private IUserAdressDAO userAdressDAO;
	
	@Autowired
	public ExampleServiceImpl(ExampleDao exampleDao) {
	        super(exampleDao);
	        this.exampleDao = exampleDao;
	    }
	
	@Transactional
	@Override
	public ExampleModel get(Integer id) {
		
		return super.get(id);
	}
	
	
	@Transactional
	public ExampleModel getByUserName(String userName) {
		
		UserAddress userAddress = new UserAddress();
		userAddress.setStateId(2L);
		userAddress.setDistrictId(2l);
		userAdressDAO.save(userAddress);
		
		UserAddress address2 = userAdressDAO.get(1L);
		address2.setStateId(3l);
		
		userAdressDAO.save(address2);
		
		userAdressDAO.updateUserAddress(3L,2L);
		
		return exampleDao.getExampleByUsername(userName);
		
	}
	
	@Transactional
	@Override
		public ExampleModel save(ExampleModel object) {
			// TODO Auto-generated method stub
			return super.save(object);
		}
	

	

}
