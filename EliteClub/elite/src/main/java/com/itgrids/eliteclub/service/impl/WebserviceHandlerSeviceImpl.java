package com.itgrids.eliteclub.service.impl;


import java.util.List;
import java.util.Map;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.eliteclub.dao.FileDAO;
import com.itgrids.eliteclub.dao.UserDAO;
import com.itgrids.eliteclub.dto.AudioFileVo;
import com.itgrids.eliteclub.dto.CategoriesVo;
import com.itgrids.eliteclub.dto.CategoryResponseVo;
import com.itgrids.eliteclub.dto.UserContactsInputVO;
import com.itgrids.eliteclub.model.Category;
import com.itgrids.eliteclub.model.ContactDetails;
import com.itgrids.eliteclub.model.File;

import com.itgrids.eliteclub.model.User;
import com.itgrids.eliteclub.service.WebserviceHandlerSevice;
import com.itgrids.eliteclub.webservice.components.WebServiceHandler;

@Service("webserviceHandlerSevice")
@Transactional
public class WebserviceHandlerSeviceImpl implements   WebserviceHandlerSevice
{
	Logger log=LogManager.getLogger(WebserviceHandlerSeviceImpl.class);
@Autowired
private UserDAO userDAO;

@Autowired
private FileDAO  fileDAO;

//	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public Object loadCategories(UserContactsInputVO inputVo) {
		log.debug("saving user contacts ");
		User user=null;
		
		User userExisist=userDAO.getUserByIMEINumber(inputVo.getImeiNo());
		if(userExisist !=null && userExisist.getImeiNo().equalsIgnoreCase(inputVo.getImeiNo()))
			user=userExisist;
		else{
		user= getUserObject(inputVo);
				
		userDAO.saveOrUpdateForTrancasction(user);
		
		}
		
		System.out.println(user.getUserId());
		
		//getAudioFilesBasedOnCategories();
		try{
			CategoryResponseVo vo= getAudioFilesBasedOnCategoriesHelper();
			vo.setUserId(user.getUserId().toString());
			return vo;
		}catch ( Exception e) {
			log.error("exception Occured  while retriving audio files"+ e.getMessage());
           
			e.printStackTrace();
			throw new RuntimeException();
		}
		//return new CategoryResponseVo();
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
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public CategoryResponseVo  getAudioFilesBasedOnCategoriesHelper()

	{ /*  if(true)
		throw new RuntimeException("bitch");*/
		log.debug("inside helper method to get Audio files ");
		return  getAudioFilesBasedOnCategories(null);
	}

	public CategoryResponseVo  getAudioFilesBasedOnCategories(String imeiNo)
	{
		log.debug("find out  different categories for files");
		List<Category> objects=(List<Category>)fileDAO.loadFilesByCategory();
		CategoryResponseVo vo =new CategoryResponseVo();
		convertCategoryToCategoryVo( objects,vo);
		if(imeiNo !=null)
		{
		User userExisist=userDAO.getUserByIMEINumber(imeiNo);
		vo.setUserId(userExisist.getUserId().toString());
		userExisist=null;
		}
	/*	try{
			if(true)
		throw new RuntimeException();
		
		}finally{
			
		}*/
		
		return vo;
		
	}
	

	private void convertCategoryToCategoryVo(List<Category> objects,
			CategoryResponseVo vo) {
		
		
		for (Category category : objects) {
			CategoriesVo cvo =new CategoriesVo();
			getCategoryVoFromCategory(category, cvo);
			vo.getCategories().add(cvo);
			
		}
		
	}
    //category entity to categories vo
	private void getCategoryVoFromCategory(Category category, CategoriesVo cvo) {
		
		cvo.setCategoryId(category.getCategoryId());
		cvo.setCategoryName(category.getCategoryName());
		
	
		//set Audio files for Category
		for (File file : category.getFiles()) {
			
			cvo.getFiles().add(getFileVo(file));
		}
		
		
	}

	
	 // file entity to vo conversion
	private AudioFileVo getFileVo(File file) {
		
		
		AudioFileVo af = new AudioFileVo();
		
		af.setFileId(file.getFileId());
		af.setDescription(file.getDescription());
		af.setUrl(file.getUrl());
		af.setContentTypeId(file.getContentType().getContenttypeId());
		return af;
	}
	
	
	

}
