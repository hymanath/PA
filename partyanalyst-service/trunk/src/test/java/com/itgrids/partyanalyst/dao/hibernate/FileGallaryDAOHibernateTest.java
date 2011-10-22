package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.utils.IConstants;

public class FileGallaryDAOHibernateTest extends BaseDaoTestCase{

	private IFileGallaryDAO fileGallaryDAO;

	public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
		this.fileGallaryDAO = fileGallaryDAO;
	}
	
	/*public void test()
	{
		fileGallaryDAO.getAll();
	}*/
	/*public void testGetAllRecordInGallary()
	{
		List<Object[]> results = fileGallaryDAO.getAllRecordInGallary(300L);
		System.out.println(results.size());
	}*/
	/*public void testGetNewsRecordsBySearchCriteria()
	{
		FileVO  fileVO = new FileVO();
		fileVO.setCandidateId(13722L);
		fileVO.setKeywords("news");
		List<Object[]> results = fileGallaryDAO.getNewsRecordsBySearchCriteria(fileVO,IConstants.NEWS_GALLARY);
		System.out.println(results.size());
	}*/
	/*public void testGetNewsToDisplay()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Object[]> results = fileGallaryDAO.getFirstFourNewsToDisplay(13722L,1,4);
		System.out.println(results.size());
		for(Object[] newsDetails: results){
		    
		    System.out.println((Long)newsDetails[0]);
		    System.out.println( newsDetails[1].toString());		    			    	
		    System.out.println((IConstants.UPLOADED_FILES+"/"+newsDetails[1].toString()));
		    System.out.println((newsDetails[3].toString()));
	   	    String desc=""; 
	   	 if(newsDetails[4] != null && newsDetails[4].toString().length()>=55)
	   	    {
	   	    desc = newsDetails[4].toString().substring(0, 50);
	   	    desc = desc+"...";
	   	    }
	   	    else
	   	    {
	   	     desc = newsDetails[4].toString();
	   	     desc = desc+"...";
	   	    }
	   	 System.out.println(desc);
	   	 if(newsDetails[5]!= null)
	   	System.out.println((newsDetails[5].toString()));
	   	 if(newsDetails[6]!= null)
	   	System.out.println((sdf.format((Date)newsDetails[6])) );
	   	 if(newsDetails[7]!= null)
	   	System.out.println(((Long)newsDetails[7]));
	 }
	 
	}*/
	
	public void testGetCandidateLatestVideos()
	{
		List<File> list = fileGallaryDAO.getCandidateLatestVideos(3424l,0,20);
		System.out.println(list.size());
		
		for(File file : list)
		{
			System.out.println(file.getFileId() +"--"+file.getFilePath());
		}
	}
	
}
