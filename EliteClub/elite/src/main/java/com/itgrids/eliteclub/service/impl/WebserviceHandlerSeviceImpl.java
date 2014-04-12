package com.itgrids.eliteclub.service.impl;


import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgrids.eliteclub.dao.UserDAO;
import com.itgrids.eliteclub.dto.UserContactsInputVO;
import com.itgrids.eliteclub.model.ContactDetails;

import com.itgrids.eliteclub.model.User;
import com.itgrids.eliteclub.service.WebserviceHandlerSevice;

@Service("webserviceHandlerSevice")
public class WebserviceHandlerSeviceImpl implements   WebserviceHandlerSevice
{

@Autowired
private UserDAO userDAO;

	@Override
	public Object loadCategories(UserContactsInputVO inputVo) {
		
		User user= getUserObject(inputVo);
		
		
		userDAO.save(user);
		
		return null;
	}

	public  User getUserObject(UserContactsInputVO inputVo) {
		User user = new User();
		
		user.setEmailId(inputVo.getEmailId());
		user.setImeiNo(inputVo.getImeiNo());
		user.setMobileno(inputVo.getMobileno());
		user.setUserName(inputVo.getUserName());
		Map<String,String> contacts=inputVo.getContacts();
	
		
		for (String key : contacts.keySet()) {
			ContactDetails contact= new ContactDetails();
			
			contact.setName(contacts.get(key));
			contact.setPhoneNumber(key);
			contact.setUser(user);
			
			user.getContactDetailses().add(contact);
		}
		
		
		return user;
	}

}
