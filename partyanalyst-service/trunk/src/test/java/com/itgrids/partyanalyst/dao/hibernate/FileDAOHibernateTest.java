package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.model.File;

public class FileDAOHibernateTest extends BaseDaoTestCase{
	
	private IFileDAO fileDAO;

	

	public void setFileDAO(IFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}
	
	/*public void test()
	{
		fileDAO.getAll();
		
	}*/
	/*public void testGetFileByFileId()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Object[]> results =fileDAO.getFileByFileId(1L);
		System.out.println(results.size());
		for(Object[] newsDetails: results){
		    
		    System.out.println((Long)newsDetails[0]);
		    System.out.println( newsDetails[1].toString());		    			    	
		    System.out.println((IConstants.UPLOADED_FILES+"/"+newsDetails[1].toString()));
		    System.out.println((newsDetails[3].toString()));
	   	 System.out.println(newsDetails[4].toString());
	   	 if(newsDetails[5]!= null)
	   	System.out.println((newsDetails[5].toString()));
	   	 if(newsDetails[6]!= null)
	   	System.out.println((sdf.format((Date)newsDetails[6])) );
	 }
	 
		
	}*/
	
	/*public void testUpdateProblemFileDetailsByFileId()
	{
		System.out.println(fileDAO.updateProblemFileDetailsByFileId(10l, "qweqw", "qweqwe"));
	}*/
	public void testFile()
	{
		File file = fileDAO.get(23423l);
		String desc = file.getFileDescription();
		
	}
}
