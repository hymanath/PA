package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.itgrids.partyanalyst.dao.ISpecialPageDescriptionDAO;
import com.itgrids.partyanalyst.service.ISpecialPageService;

public class SpecialPageService implements ISpecialPageService{
	
	//log object creation
	private static final Logger log = Logger.getLogger(SpecialPageService.class);
	//declare DAO class reference variable as property variable
	ISpecialPageDescriptionDAO specialPageDescriptionDAO;
	//getter&setter methods for reference variables
	public ISpecialPageDescriptionDAO getSpecialPageDescriptionDAO() {
		return specialPageDescriptionDAO;
	}

	public void setSpecialPageDescriptionDAO(
			ISpecialPageDescriptionDAO specialPageDescriptionDAO) {
		this.specialPageDescriptionDAO = specialPageDescriptionDAO;
	} 
	
	//implementations of declaration reference variable
	public List<String> getSpecialPageDescription(Long specialPageId)
	{
	   try{
			log.debug("Entered into getSpecialPageDescription() Method");
		     List<Object> results = specialPageDescriptionDAO.getSpecialPageDescription(specialPageId);
		 
		   if(results != null && results.size() >0)
		   {
			   List<String> descList = new ArrayList<String>(0); 
			   for(Object desc :results)
				 descList.add(desc.toString());
			   return descList;
		   }
		  else 
			return null;
		 
	   }catch(Exception e){
		 log.error("Exception Occured in getCandidateProfileDescriptionByCandidateID() method - "+e);
		 return null;
		 }
	}
}
