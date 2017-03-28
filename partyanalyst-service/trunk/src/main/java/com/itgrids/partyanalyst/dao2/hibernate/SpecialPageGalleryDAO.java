package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itgrids.partyanalyst.dao.ISpecialPageGalleryDAO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FilePaths;
import com.itgrids.partyanalyst.model.SpecialPage;
import com.itgrids.partyanalyst.model.SpecialPageGallery;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class SpecialPageGalleryDAO extends GenericDaoHibernate<SpecialPageGallery,Long> implements ISpecialPageGalleryDAO{

	public SpecialPageGalleryDAO(){
		super(SpecialPageGallery.class);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getSpecialPageGallaryDetails(Long specialPageId,String contentType) {
		
		Query query = getSession().createQuery("select model.gallary.gallaryId , model.gallary.name , model.gallary.description " +
				" from SpecialPageGallery model where model.gallary.contentType.contentType =:contentType and model.specialPage.specialPageId =:specialPageId order by model.createdDate desc");
		query.setParameter("specialPageId", specialPageId);
		query.setParameter("contentType", contentType);
		
		return query.list();
	}

	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpecialPageGallaryDetail(Long specialPageId,
			int firstRecord, int maxRecord, String type) {
		
		Query query = getSession().createQuery("select model.gallary.gallaryId , model.gallary.name,model.gallary.description,model.gallary.createdDate," +
				"model.gallary.updateddate from SpecialPageGallery model where model.gallary.contentType.contentType =:type and model.gallary.isDelete =:isDelete " +
				"and model.gallary.isPrivate =:isPrivate and model.specialPage.specialPageId =:specialPageId and (select count(*)  from FileGallary model1 where model1.gallary.gallaryId = model.gallary.gallaryId and model1.isDelete = :isDelete " +
				"and model1.isPrivate = :isPrivate ) > 0 order by model.createdDate desc");
		
		query.setParameter("specialPageId", specialPageId);
		query.setParameter("type", type);
		query.setParameter("isDelete", "false");
		query.setParameter("isPrivate", "false");
		query.setFirstResult(firstRecord);
		query.setMaxResults(maxRecord);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<File> getGalleryBasedOnSpecialPageId(Long specialPageId,int firstRecord, int maxRecord, String contentType){
		
		
		
		Query query = getSession().createQuery("select model.file from FileGallary model where model.gallary.gallaryId in (select model1.gallary.gallaryId from SpecialPageGallery model1" +
				" where model1.gallary.contentType.contentType =:contentType and model1.gallary.isDelete =:isDelete " +
				" and model1.gallary.isPrivate =:isPrivate and model1.specialPage.specialPageId =:specialPageId ) and model.isDelete =:isDelete order by model.file.fileDate desc");
		
		query.setParameter("specialPageId", specialPageId);
		query.setParameter("contentType", contentType);
		query.setParameter("isDelete", "false");
		query.setParameter("isPrivate", "false");
		query.setFirstResult(firstRecord);
		query.setMaxResults(maxRecord);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGalleriesBasedOnSpecialPageId(Long specialPageId,int firstRecord, int maxRecord, String contentType){
		
		
		
		Query query = getSession().createQuery("select model.file,model.fileGallaryId from FileGallary model where model.gallary.gallaryId in (select model1.gallary.gallaryId from SpecialPageGallery model1" +
				" where model1.gallary.contentType.contentType =:contentType and model1.gallary.isDelete =:isDelete " +
				" and model1.gallary.isPrivate =:isPrivate and model1.specialPage.specialPageId =:specialPageId ) and model.isDelete =:isDelete group by model.file.fileId order by model.file.fileDate desc,model.updateddate desc ");
		
		query.setParameter("specialPageId", specialPageId);
		query.setParameter("contentType", contentType);
		query.setParameter("isDelete", "false");
		query.setParameter("isPrivate", "false");
		query.setFirstResult(firstRecord);
		query.setMaxResults(maxRecord);
		return query.list();
		
	}
	public List<Long> getGalleryCountBasedOnSpecialPageId(Long specialPageId, String contentType){
		
		
		
		Query query = getSession().createQuery("select count(model.file.fileId) from FileGallary model where model.gallary.gallaryId in (select model1.gallary.gallaryId from SpecialPageGallery model1" +
				" where model1.gallary.contentType.contentType =:contentType and model1.gallary.isDelete =:isDelete " +
				" and model1.gallary.isPrivate =:isPrivate and model1.specialPage.specialPageId =:specialPageId ) and model.isDelete =:isDelete group by model.file.fileId order by model.file.fileDate desc");
		
		query.setParameter("specialPageId", specialPageId);
		query.setParameter("contentType", contentType);
		query.setParameter("isDelete", "false");
		query.setParameter("isPrivate", "false");
		return query.list();
		
	}
	
	public List<Object[]> getGallariesBySpecialPageId(Long specialPageId, String contentType){
		Object[] params = {specialPageId , contentType};
		return getHibernateTemplate().find("select model.gallary.gallaryId , model.gallary.name from SpecialPageGallery model where model.specialPage.specialPageId=? and model.gallary.contentType.contentType=? and model.gallary.isDelete='false' order by model.gallary.name asc",params);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getSpecialPageGallaryId(Long specialPageId,int firstRecord, int maxRecord, String contentType){
		
		
		
		Query query = getSession().createQuery("select distinct model.gallary.gallaryId,model.gallary.name,model.gallary.description,model.gallary.createdDate,model.gallary.updateddate from SpecialPageGallery model where model.specialPage.specialPageId =:specialPageId and model.gallary.contentType.contentType =:contentType and model.gallary.isDelete =:isDelete  and model.gallary.isPrivate=:isPrivate ");
		
		query.setParameter("specialPageId", specialPageId);
		query.setParameter("contentType", contentType);
		query.setParameter("isDelete", "false");
		query.setParameter("isPrivate", "false");
		query.setFirstResult(firstRecord);
		query.setMaxResults(maxRecord);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<SpecialPage> getSpecialPageByGalleryId(Long gallaryId)
	{
		return getHibernateTemplate().find("select model.specialPage from SpecialPageGallery model where model.gallary.gallaryId = ?",gallaryId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getOtherGalleries(Long specialPageId,List<Long> gallaryIds,String contentType)
	{
		Query query = getSession().createQuery("select model.gallary.gallaryId from SpecialPageGallery model where model.gallary.contentType.contentType = ? and " +
				" model.specialPage.specialPageId = ? and model.gallary.gallaryId not in(:gallaryIds) and model.gallary.isPrivate = 'false' and model.gallary.isDelete = 'false'");
		
		query.setParameter(0,contentType);
		query.setParameter(1,specialPageId);
		query.setParameterList("gallaryIds",gallaryIds);
		
		return query.list();
	}
	
	      // MyMethods
public List<File> getExpiredVideosList1( String contentType){
		
		
		
		Query query = getSession().createQuery("  select  model from File model where model.fileId in (select model1.file.fileId from FileGallary model1" +
				" where model1.gallary.gallaryId in( select model2.gallaryId from Gallary model2 where model2.contentType.contentType =:contentType and  model2.isDelete =:isDelete " +
				" and model2.isPrivate =:isPrivate) and model1.isDelete =:isDelete and model1.isPrivate =:isPrivate ) order by model.fileDate ASC");
		
		//query.setParameter("specialPageId", specialPageId);
		query.setParameter("contentType", contentType);
		query.setParameter("isDelete", "false");
		query.setParameter("isPrivate", "false");
		//query.setFirstResult(firstRecord);
		//query.setMaxResults(maxRecord);
		return query.list();
		
	}
public List<Object[]> getExpiredVideosList( Date from, Date to ,String contentType  ){
	
	String from1="2011-10-30";
	String to1="2011-12-30";
	
	Query query = getSession().createQuery("select  model.filePath,model.fileSourceLanguage.fileSourceLanguageId from FilePaths model where model.fileType.fileTypeId " +
			                                "in (select model2.fileTypeId from FileType model2 where model2.type=:contentType) " +
			                                "and model.fileSourceLanguage.fileSourceLanguageId " +
			                                "in (select model3.fileSourceLanguageId from FileSourceLanguage  model3 where model3.file.fileId " +
			                                "in( select model5.file.fileId from  FileGallary model5 where DATE(model5.updateddate)  BETWEEN :startDate AND :toDate " +
			                                "and model5.isDelete =:isDelete and model5.isPrivate =:isPrivate  )) " +
			                               " and  model.lastUpdatedDate < :today " );
	
	//query.setParameter("specialPageId", specialPageId); and model4.updateddate <=:toDate and lastVerifiedDate < :today  
	 query.setParameter("startDate",from );
	 query.setParameter("toDate",to );
     query.setParameter("today", new DateUtilService().getDayBeforeYesterDayDate());
	 query.setParameter("contentType", contentType);
     query.setParameter("isDelete", "false");
	 query.setParameter("isPrivate", "false");
     query.setFirstResult(0);
	 query.setMaxResults(50);
	
	  return query.list();
	
}

  
 /* public int  markCheckedVideos( List<String> paths){
	  Session session=getSession();
	  Transaction tx=session.beginTransaction();
	
	  Query query = session.createQuery(" update FilePaths model set model.lastVerifiedDate=:newDate where model.file_paths in(:paths)").setParameterList("paths", paths);

	  return  query.executeUpdate();

		
	}*/
  public List<Object> getFileSourceLanguageIds(List<String> filePaths)
	{
		Query query = getSession().createQuery("select model.fileSourceLanguage.fileSourceLanguageId from  FilePaths model " +
				                                "where model.filePath in(:filePaths)").setParameterList("filePaths", filePaths);
		
		
		return query.list();
	}
  public List<Object> getFileIds(List<Object> languageIds)
 	{
 		Query query = getSession().createQuery("select model.file.fileId  from  FileSourceLanguage  model  " +
 				                                 "where model.fileSourceLanguageId in(:languageIds)").setParameterList("languageIds", languageIds);
 		
 		
 		return query.list();
 	}
  
  
  
  public int[] deleteYoutubeVideoRecords(List<String> filePaths ,List<Object> fileIds, List<Object> fileSourceLanguageIds, List<Long> languageIds){
		int[] count=null;
	    int[] results=null;
	 // Session session=getSession();
		// Transaction tx=session.beginTransaction();
	             
	   return null;
	}
  public int  updateLastUpdateDateInFilePaths(List<Long> languageIds)
  {
	  Query query = getSession().createQuery("update  FilePaths model set  model.lastUpdatedDate=:today where  model.fileSourceLanguage.fileSourceLanguageId in(:languageIds)"); 
      query.setParameter("today", new DateUtilService().getCurrentDateAndTime()); 
      query.setParameterList("languageIds", languageIds); 
      return  query.executeUpdate();
  }
  public int  deleteRecordsFromFilePath(List<String> filePaths)
  {
	   Query query = getSession().createQuery("delete  from FilePaths model where model.filePath in(:filePaths)").setParameterList("filePaths", filePaths); 
	   return query.executeUpdate();
  }
  public int  deleteRecordsFromFileSourceLanguage(List<Object> fileSourceLanguageIds)
  {
		 Query query = getSession().createQuery("delete  from  FileSourceLanguage model where model.fileSourceLanguageId in (:fileSourceLanguageIds)").setParameterList("fileSourceLanguageIds", fileSourceLanguageIds);
		 return query.executeUpdate();
  }
  public int  deleteRecordsFromFileGallary(List<Object> fileIds)
  {
	   Query query = getSession().createQuery("delete from  FileGallary model  where  model.file.fileId in (:fileID)");  //.setParameterList("fileID", fileIds); 
       //query.setParameter("isDelete", "true"); 
       query.setParameterList("fileID", fileIds);
       return query.executeUpdate();
  }
  public int   deleteRecordsFromFile(List<Object> fileIds)
  {      
	    List<Object> fileIds1=  verifyBatchVideos(fileIds);
		 Query query = getSession().createQuery("delete  from   File model where model.fileId in(:fileIds)").setParameterList("fileIds", fileIds1);
		return query.executeUpdate();
  }
  
  public List<Object> verifyBatchVideos(List<Object> fileIds)
  {
	  List<Object> list=new ArrayList<Object>();
	  Query query=null;
	   for(int i=0;i<fileIds.size();i++)
	   {
		  query = getSession().createQuery("select count(*)  from  FileSourceLanguage model where model.file.fileId = :fileID ").setParameter("fileID", fileIds.get(i));
          List<Long> cnt=query.list();
          
          if(cnt.get(0).intValue()<1)
        	  list.add(fileIds.get(i));
		 
		 
	 }
	  return list;
  }
  
  
  @SuppressWarnings("unchecked")
public List<Object[]> checkGalleryExistForASpecialPage(Long specialPageId, String gallaryName, String contentType)
  {
	  StringBuilder stringBuilder = new StringBuilder();
	  stringBuilder.append(" select model.gallary.gallaryId, model.gallary.name from SpecialPageGallery model where model.specialPage.specialPageId = :specialPageId ");
	  stringBuilder.append(" and model.gallary.name = :gallaryName and model.gallary.contentType.contentType = :contentType and model.isDelect = :isDelete");
	  
	  Query queryObj = getSession().createQuery(stringBuilder.toString());
	  queryObj.setParameter("specialPageId", specialPageId);
	  queryObj.setParameter("gallaryName", gallaryName);
	  queryObj.setParameter("contentType", contentType);
	  queryObj.setParameter("isDelete", IConstants.FALSE);
	  
	  return queryObj.list();
  }
  
  

}
