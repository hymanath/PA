package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.model.File;

public class FileDAO extends GenericDaoHibernate<File, Long> implements
		IFileDAO {
	public FileDAO() {
		super(File.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getFileByFileId(Long fileId)
	{
		return getHibernateTemplate().find("select model.fileId,model.fileName,model.filePath,model.fileTitle,model.fileDescription , " +
				" model.sourceObj.source ,model.fileDate from File model where  model.fileId=?",fileId); 
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCategoryDetailsOfAFile(Long fileId)
	{
		return getHibernateTemplate().find("select model.category.categoryId,model.category.categoryType from File model where model.fileId = ?",fileId);
	}
	
	
	public List<File> getAllFilesByFileIds(List<Long> fileIds){
		
		Query query = getSession().createQuery("select model from File model where model.fileId in(:fileIds)");
		
		query.setParameterList("fileIds", fileIds);
		
		return query.list();
		
		
	}
	
	public Integer updateProblemFileDetailsByFileId(Long fileId, String fileTitle, String fileDescription)
	{
		Query query = getSession().createQuery(" update File model set model.fileTitle = ?, model.fileDescription =? " +
				" where model.fileId = ? ");
		
		query.setParameter(0, fileTitle);
		query.setParameter(1, fileDescription);
		query.setParameter(2, fileId);
		
		return query.executeUpdate();
	}
	
	public List<File> getFilesByCategoryId(Long categoryId,Integer startIndex ,Integer endIndex,String newsType,Date fromDate,Date toDate)
	{
		StringBuilder str= new StringBuilder();
		
		str.append("select model from File model where model.category.categoryId=:categoryId and model.isDeleted ='N'");
		 if(newsType != null && !newsType.equalsIgnoreCase(""))
			 str.append(" and model.isPrivate = 'N' ");
		 if(fromDate != null)
			 str.append(" and date(model.fileDate) >= :fromDate ");
			 
		 if(toDate != null)
			 str.append(" and date(model.fileDate) <= :toDate ");
				 
		 Query query = getSession().createQuery(str.toString());
		query.setParameter("categoryId", categoryId);
		if(startIndex != null)
		query.setFirstResult(startIndex);
		if(endIndex != null)
		query.setMaxResults(endIndex);
		if(newsType != null && !newsType.equalsIgnoreCase(""))
		query.setParameter("newsType", newsType);
		if(fromDate != null)
	   		query.setParameter("fromDate",fromDate);
			 
		if(toDate != null)
		 query.setParameter("toDate",toDate);
	   	 
		return query.list();	
	}
	
	@SuppressWarnings("unchecked")
	public List<File> getTotalFilesList(Long userId,Date fromDate,Date toDate)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select model from File model where model.user.userId =:userId ");
	   if(fromDate != null)
		  str.append(" and date(model.fileDate) >= :fromDate ");
	   if(toDate != null)
		  str.append(" and date(model.fileDate) <= :toDate ");
	   		 
	   str.append(" order by model.fileDate desc ");
	  
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("userId", userId);
	  if(fromDate != null)
	   query.setParameter("fromDate", fromDate);
	  if(toDate != null)
	   query.setParameter("toDate", toDate);
	  return query.list();
	 
	}
	
}
