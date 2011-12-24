package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
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
		List<Object[]> results = fileGallaryDAO.getFirstFourNewsToDisplay(900L,0,20,"Public");
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
		  System.out.println((newsDetails[6].toString()));
	   	 if(newsDetails[7]!= null)
	   	System.out.println((sdf.format((Date)newsDetails[7])) );
	   	 if(newsDetails[8]!= null)
	   	System.out.println(((Long)newsDetails[8]));
	 }
	 
	}*/
	//public List<File> getPartyLatestVideos(Long partyId,Integer startIndex, Integer maxResults)
	/*public void testGetPartyLatestVideos()
	{
		List<File> list = fileGallaryDAO.getPartyLatestVideos(163l,0,20);
		System.out.println(list.size());
		
		for(File file : list)
		{
			System.out.println(file.getFileId() +"--"+file.getFilePath());
		}
	}*/
	/*public void testGetNewsCountByScope()
	{
		List<Long> list = fileGallaryDAO.getNewsCountByScope(900L,null,"Private");
		System.out.println(list.size());
		System.out.println(list.get(0));
	}*/
//	public void testGetNewsByScope()
//	{
//		List<Object[]> list = fileGallaryDAO.getNewsByScope(900L,null,0,20,"Public",null,"Hindi");
//		System.out.println(list.size());
//	}
//	public Integer deleteFilesAndPhotos(Long fileId,Long gallaryId)
/*public void testDeleteFilesAndPhotos()
{
int i= fileGallaryDAO.deleteFilesAndPhotos(804l,1l);
System.out.println("i " +i);
}*/

	/* public void testGetPhotoAndFileDescForUpdate()
	   {
		   
		   List<Object[]> result = fileGallaryDAO.getPhotoAndFileDescForUpdate(1l,10l);
		   System.out.println("size "+ result.size());
		   for (Object[] object : result) {
			System.out.println((Long)object[0]);
			
			System.out.println(object[1]!=null?object[1].toString():null);
			System.out.println(object[2]!=null?object[2].toString():null);
			System.out.println(object[3]!=null?object[3].toString():null);
		} 
	   }*/
	
	//public List<File> getFilesofaparty(Long partyId)
	
	/*public void testGetFilesofaparty()
	{
		List<File> result =fileGallaryDAO.getFilesofaparty(163l);
		System.out.println(result.size());
		for (File file : result) {
			System.out.println(file.getFileName());
			System.out.println(file.getFileDescription());
						
		}
	}*/
	
	public void testGetRecentlyUploadedFiles(){
		List<FileGallary> list = fileGallaryDAO.getRecentlyUploadedFiles(1, 15,IConstants.PHOTO_GALLARY);
		for(int i=0;i<list.size();i++){
		System.out.println(list.get(i).getGallary().getContentType().getContentType());
		System.out.println(list.get(i).getGallary().getCandidate().getLastname());
		System.out.println(list.get(i).getFile().getFilePath());
		}
		System.out.println(list.size());
	}
}
