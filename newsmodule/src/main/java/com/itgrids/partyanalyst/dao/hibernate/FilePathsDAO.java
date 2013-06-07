package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFilePathsDAO;
import com.itgrids.partyanalyst.model.FilePaths;

public class FilePathsDAO extends GenericDaoHibernate<FilePaths,Long> implements IFilePathsDAO {

	public FilePathsDAO(){
		super(FilePaths.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getMaxOrderNo()
	{
		return getHibernateTemplate().find("select max(model.orderNo) from FilePaths model");
	}
	public List<Object[]> getProblemRelatedFiles(final Long problemId,final Long userId){
		Object[] params = {problemId,userId};
		return getHibernateTemplate().find("select model.fileSourceLanguage.file.fileTitle,model.fileSourceLanguage.file.fileDescription," +
				"model.filePath from FilePaths model where model.fileSourceLanguage.file.fileId in (select model1.file.fileId from ProblemFiles " +
				" model1 where model1.isApproved='true' and model1.isDelete ='false' and model1.problem.problemId =? and model1.user.userId = ?)" +
				" order by model.fileSourceLanguage.file.fileId desc",params);
	}
	public List<Object[]> getProblemFiles(final Long problemId,final Long userId,final String visibility){

		StringBuilder query = new StringBuilder();
		query.append("select model.fileSourceLanguage.file.fileTitle,model.fileSourceLanguage.file.fileDescription,model.filePath,model1.user.userId,model1.user.firstName,model1.user.lastName, model1.problemFilesId " +
				" from FilePaths model,ProblemFiles model1 where model.fileSourceLanguage.file.fileId = model1.file.fileId  and model1.isApproved='true' and model1.isDelete ='false' and model1.problem.problemId = :problemId ");
		if(visibility != null)
			query.append(" and model1.visibility.type = :visibility ");
		if(userId != null)
			query.append(" and model1.user.userId :userId ");
		query.append(" order by model1.updatedTime desc ");
		
		Query queryObj = getSession().createQuery(query.toString());
		
		queryObj.setParameter("problemId", problemId);
		if(visibility != null)
			queryObj.setParameter("visibility", visibility);
		if(userId != null)
		    queryObj.setParameter("userId", userId);
		return queryObj.list();
		
	}
	
	
	public List<Object> getFilePaths(long fileId)
	{
		return getHibernateTemplate().find("select model.filePath from FilePaths model where model.fileSourceLanguage.file.fileId=?",fileId);
		
	}
	
/*	public List<Object> getFilePathsBasedOnFileTypeIds(List<Integer> fileTypeIds )
	{
		
	
		//return getHibernateTemplate().findByNamedParam("select model.filePath from FilePaths model where model.fileType.fileTypeId in (:fileTypeIds)", "fileTypeIds", fileTypeIds); 
		
       	
	Query query = getSession().createQuery("select model.filePath from FilePaths model where model.fileType.fileTypeId in (:fileTypeIds) and model.haveThumnails=:haveThumnails ").setParameterList("fileTypeIds", fileTypeIds);

//query.setParameter("specialPageId", specialPageId); and model4.updateddate <=:toDate and lastVerifiedDate < :today  
	      query.setParameter("haveThumnails", "false");
          query.setFirstResult(0);
          query.setMaxResults(1000);

   return query.list();
	}*/
	
	/*public void insertThumbnailPath ( final Map<String,List<String>> dataToSave)
	{  
		Query query=null;
		     if(dataToSave !=null){
		Iterator <String> itr=dataToSave.keySet().iterator();
		while(itr.hasNext()){
			
		String obj=(itr.next());
		query= getSession().createQuery("update  FilePaths model set  model.thumb=:thumb,model.small=:small,model.medium=:medium where  model.filePath = :filePaths)");
		
		query.setParameter("thumb", dataToSave.get(obj).get(0) );
		query.setParameter("small",  dataToSave.get(obj).get(1) );
		query.setParameter("medium", dataToSave.get(obj).get(2));
	}
	}
	}*/
	
	public int  updateFilePathsThumbnails(List<String> dataToSave)
	  {  
		
		
		Query query=null;
			
		query= getSession().createQuery("update  FilePaths model set  model.haveThumnails=:haveThumnails where  model.filePath in(:filePath))");	   
		query.setParameterList("filePath",dataToSave);
		query.setParameter("haveThumnails", "true");
	  return      query.executeUpdate();
	      
	
	  }
	public void  updateThumbFilePaths(Map<String,String> dataToSave)
	  {  
		
		
		Iterator <String> itr=dataToSave.keySet().iterator();
		Query query=null;
				while(itr.hasNext()){
			
			String obj=(itr.next());
		query= getSession().createQuery("update  FilePaths model set  model.thumb=:thumb where  model.filePath = :filePath)");	   
		query.setParameter("thumb", dataToSave.get(obj) );
		query.setParameter("haveThumnails", "true");
	        query.executeUpdate();
	      
	  }
	
	  }
	public void  updateMediumFilePaths(Map<String,String> dataToSave)
	  {  
		
		
		Iterator <String> itr=dataToSave.keySet().iterator();
		Query query=null;
				while(itr.hasNext()){
			
			String obj=(itr.next());
		query= getSession().createQuery("update  FilePaths model set  model.medium=:medium where  model.filePath = :filePath)");	   
		query.setParameter("medium", dataToSave.get(obj) );
		query.setParameter("haveThumnails", "true");
	        query.executeUpdate();
	      
	  }
	
	  }

	//@Override
	public List<Object> getFilePathsBasedOnFileTypeIds(List<?> fileTypeIds  )
	{       /* String[] ids = {"1","2"};
		
		 List<?> contentType = Arrays.asList(ids);*/
		List<Long> contentType = new ArrayList<Long>();
		contentType.add(1l);
		contentType.add(2l);
		
		Query query = getSession().createQuery("select  model.filePath from FilePaths model where " +
				                                "  model.fileSourceLanguage.fileSourceLanguageId " +
				                                " in (select model3.fileSourceLanguageId from FileSourceLanguage  model3 " +
				                                " where model3.file.fileId " +
				                                " in( select model5.file.fileId from  FileGallary model5 where  model5.gallary.gallaryId " +
				                                " in( select model6.gallaryId from Gallary model6 where model6.contentType.contentTypeId in (:contentType) " +
				                                " and model6.isDelete =:isDelete ))) " +
				                               " and model.haveThumnails = :haveThumnails " );
		
		//query.setParameter("specialPageId", specialPageId); and model4.updateddate <=:toDate and lastVerifiedDate < :today  
		// query.setParameterList("fileType", fileTypeIds);
	     query.setParameterList("contentType", contentType);
	     query.setParameter("isDelete", "false");
	     query.setParameter("haveThumnails", "false");
		 query.setFirstResult(0);
		 query.setMaxResults(5000);
	
		
		  return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getFilePathsBasedOnFileSource(List<Long> sourceIds)
	{
		Query query = getSession().createQuery("select model.filePathsId ,model.filePath ," +
				"model.fileSourceLanguage.file from FilePaths model" +
				" where model.fileSourceLanguage.fileSourceLanguageId = :sourceIds");
		query.setParameterList("sourceIds", sourceIds);
		
		return query.list();
	}

	
	
}
