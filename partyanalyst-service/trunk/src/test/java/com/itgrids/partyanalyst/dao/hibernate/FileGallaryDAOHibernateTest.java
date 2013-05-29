package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;

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
		List<Object[]> results = fileGallaryDAO.getFirstFourNewsToDisplay(3596L,0,20,"Public");
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
	
	/*public void testGetRecentlyUploadedFiles(){
		List<FileGallary> list = fileGallaryDAO.getRecentlyUploadedFiles(1, 15,IConstants.PHOTO_GALLARY);
		for(int i=0;i<list.size();i++){
		System.out.println(list.get(i).getGallary().getContentType().getContentType());
		System.out.println(list.get(i).getGallary().getCandidate().getLastname());
		System.out.println(list.get(i).getFile().getFilePath());
		}
		System.out.println(list.size());
	}*/
	/*public void testgetTotalIndividualSources()
	{
		//List<Long> list = fileGallaryDAO.getTotalIndividualSources(900L,"public","Sakshi",null,null,null); 
		//List<Long> list = fileGallaryDAO.getTotalIndividualSources(900L,"public",null,"Telugu",null,null); 
		//List<Long> list = fileGallaryDAO.getTotalIndividualSources(900L,"public",null,null,"Problems",null); 
		List<Long> list = fileGallaryDAO.getTotalIndividualSources(900L,"public",null,null,null,"High"); 
		
		System.out.println(list.get(0).toString());
		
	}*/
	
	/*public void testGetFileIdByFileGallaryId()
	{
		System.out.println((Long)fileGallaryDAO.getFileIdByFileGallaryId(1042l));
	}
    public void testgetGalleryIdsOfAFile()
    {
       List<Object> list = fileGallaryDAO.getGalleryIdsOfAFile(1052l);
       for(Object params : list)
       {
    	  System.out.println((Long)params); 
       }
    }*/
    
   /* public void testGetFilesOfInGallaries()
    {
    	List<Long> list = new ArrayList<Long>(0);
    	list.add(1092l);
    	list.add(1190l);
    	List<FileGallary> rlist = fileGallaryDAO.getFilesOfInGallaries(list);
    	{
    		for(FileGallary params : rlist)
    		{
    			System.out.println(params.getFile().getFileTitle());
    			System.out.println(params.getFile().getFileDescription());
    			System.out.println(params.getFile().getFilePath());
    			System.out.println(params.getGallary().getContentType().getContentType());
    		}
    	}
    }
	*/
	/*public void testGetFirstFileAndGallaryInfo()
	{
		 List<Object[]> list = fileGallaryDAO.getFirstFileAndGallaryInfo(9l);
		 System.out.println(list.size());
		 
		 for(Object object : list.get(0))
		 {
			 System.out.println(object.toString());
		 }
	}*/
	
	
	/*public void testGetRecentlyUploadedFiles()
	{
		List<FileGallary> list = fileGallaryDAO.getRecentlyUploadedFiles(0,15,"where model.gallary.contentType.contentType = 'Video Gallary'");
		
		System.out.println(list.size());
		
		for(FileGallary fileGallary : list)
		{
			System.out.println("FileId - "+fileGallary.getFile().getFileId()+" -- Gallary Id - "+fileGallary.getGallary().getGallaryId()
					+" -- "+fileGallary.getFile().getFileTitle());
		}
	}*/
	
	/*public void testGetRecentlyUploadedFilIds()
	{
		List<Long> list = fileGallaryDAO.getRecentlyUploadedPhotoIds(0,15);
		
		System.out.println(list.size());
		
		for(Long id : list)
		{
			System.out.println(id);
		}
		
		List<FileGallary> list2 = fileGallaryDAO.getFileGallaryByFileIdsList(list);
		
		System.out.println(list2.size());
		
		for(FileGallary fileGallary : list2)
		{
			System.out.println("FileId - "+fileGallary.getFile().getFileId()+" -- Gallary Id - "+fileGallary.getGallary().getGallaryId()
					+" -- "+fileGallary.getFile().getFileTitle());
		}
	}*/
	/*public void testGetCandidateGallaryDetailsForSubscribers()
	{
		Date updatedDate = new Date();
		Calendar geCal = new GregorianCalendar(TimeZone.getTimeZone(IConstants.TIME_ZONE_INDIA));
		geCal.setTimeInMillis(updatedDate.getTime());

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, geCal.get(Calendar.YEAR));
		calendar.set(Calendar.MONTH, geCal.get(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, geCal.get(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		List<Long> candidateIds = new ArrayList<Long>();
		candidateIds.add(900l);
		List<Object[]> list = fileGallaryDAO.getCandidateGallaryDetailsForSubscribers(calendar.getTime(), new DateUtilService().getCurrentDateAndTime(),candidateIds,"videos");
		
		System.out.println(list.size());
		 for(Object[] data:list)
		 {
			 System.out.println(data[0].toString());
		 }
		
	}*/
	
	/*public void testGetAllNewsDetails()
	{
		List<Object[]> list = fileGallaryDAO.getAllNewsDetails(900l,0,1000,"All");
		
		System.out.println(list.size());
		for(Object[] file: list){
			System.out.println(((File)file[0]).getFileId());
		}
		
	}*/
	/*public void testGetAllRecordCountInGallary()
	{
		 List<Long> list = fileGallaryDAO.getAllRecordCountInGallary(1318L);
		 System.out.println(list.get(0));
		 
	}*/
	/*public void testGetHomePageNewsDetails()
	{
		 List<FileGallary> list = fileGallaryDAO.getHomePageNewsDetails(0,15);
		 System.out.println(list.size());
		 
	}*/
	
	/*public void testgetRecentlyUploadedGallaries()
	{
		List<Long> list =fileGallaryDAO.getRecentlyUploadedGallaries(0, 15);
		System.out.println(list.size());
	
	}*/
	
	/*public void testgetStartingRecordInGallaries()
	{
		
		
		List<FileGallary> file = fileGallaryDAO.getStartingRecordInGallaries(140l);
		System.out.println(file.size());
	}*/
	/*public void testgetRecentlyUploadedVedioGallaryIds()
	{
		 String queryStr2 = "where model.gallary.contentType.contentType = 'Video Gallary'";
		List<Long> file = fileGallaryDAO.getRecentlyUploadedVedioGallaryIds(0, 15,queryStr2);
		System.out.println(file.size());
	}*/
	
	/*public void testgetRecentlyUploadedNewsGallaryIds()
	{
		 String queryStr3 = "where model.gallary.contentType.contentType = 'News Gallary'";
		List<Long> file = fileGallaryDAO.getRecentlyUploadedNewsGallaryIds(0, 100,queryStr3);
		System.out.println(file.size());
		for(Long fileid:file){
			System.out.println(fileid);
		}
	}
	*/
	
	/*public void testgetStartingRecordInNewsGallaries()
	{
		List<FileGallary> list = fileGallaryDAO. getStartingRecordInNewsGallaries(1996l);
		System.out.println(list.size());
	}*/
	/*public void testGetRecentlyUploadedNewsFileIds()
	{
		 String queryStr3 = "where model.gallary.contentType.contentType = 'News Gallary'  and  model.file.regionScopes.regionScopesId < 4 ";
		 List<FileGallary> file = fileGallaryDAO.getRecentlyUploadedNewsFileIds(0, 10,queryStr3);
		 System.out.println(file.size());
		for(FileGallary fileGallary:file){
			System.out.println("fileId: "+fileGallary.getFile().getFileId()+" gallaryId: "+fileGallary.getGallary().getGallaryId());
		}
	}*/
	
	
	/*public void testGetCandidateGallariesByCategory(){
		
		List<Long> candidateds  = new ArrayList<Long>();
		
		candidateds.add(2090L);
		
		Long Id = 4L;
		
		fileGallaryDAO.getCandidateGallariesByCategory(candidateds, Id);
		
		
	}*/
	
	public void testGetCandidateGallariesByCategory(){
	
		//String n="In a bid to neutralise th^e Congress from ";
		String n="Former councillors wife squeaks past Congress rival";
	
				String[] j={"","^"};
				for(int i=0;i<j.length;i++){
					n=n.replace(j[i], "");
				}
			System.out.println(n);
			
	}
	
}
