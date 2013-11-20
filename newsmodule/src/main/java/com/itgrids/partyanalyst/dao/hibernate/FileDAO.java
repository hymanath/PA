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
		
		str.append("select model from File model where model.category.categoryId=:categoryId and (model.isDeleted !='Y') or (model.isDeleted is null)");
		 if(newsType != null && !newsType.equalsIgnoreCase(""))
			 str.append(" and (model.isPrivate != 'Y') or (model.isPrivate is null)");
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
	public List<File> getTotalFilesList(Long userId,Date fromDate,Date toDate,Integer startIndex,Integer maxIndex)
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
	  if(startIndex != null)
	   query.setFirstResult(startIndex);
	  
	  if(maxIndex != null)
		query.setMaxResults(maxIndex);
	  return query.list();
	 
	}
		
	 public List<File> getAllTheNewsForAUserBasedByUserId(String userType,Long userId,Date fromDate,Date toDate,Long importanceId,Long regionValue)
	 {
		 StringBuilder str = new StringBuilder();
		 str.append("select distinct model from File model where model.isDeleted !='Y' ");
		 if(!"Admin".equalsIgnoreCase(userType))
		 str.append("and model.user.userId = :userId ");
		 if(importanceId != 0)
		 str.append("and model.newsImportance.newsImportanceId = :importanceId ");
		 if(regionValue != 1)
		 str.append("and model.regionScopes.regionScopesId = :regionValue ") ; 
		 if(fromDate != null)
			 str.append("and date(model.fileDate) >= :fromDate ");
		 if(toDate != null)
			 str.append("and date(model.fileDate) <= :toDate "); 
		 str.append("order by model.fileDate desc ");
		 Query query = getSession().createQuery(str.toString());
		 if(!"Admin".equalsIgnoreCase(userType))
		 query.setParameter("userId", userId);
		 if(fromDate != null)
		 query.setDate("fromDate", fromDate);
		 if(toDate != null)
		 query.setDate("toDate", toDate);
		 if(importanceId != 0)
		 query.setParameter("importanceId", importanceId);
		 if(regionValue != 1)
			 query.setParameter("regionValue", regionValue);
		 query.setFirstResult(0);
		 query.setMaxResults(300);
		return query.list();
		 
	}
	 
	 public List<File> getAllTheNewsForAUserBasedByUserIdForALocation(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds)
	 {
		 StringBuilder str = new StringBuilder();
		 str.append("select distinct model from File model where model.isDeleted !='Y' ");
		 if(!"Admin".equalsIgnoreCase(userType))
		 str.append("and model.user.userId = :userId ");
		 if(regionValue.longValue() == 1l){
		   str.append("and model.userAddress.state.stateId = :location ");
		 }else if(regionValue.longValue() == 2l){
		   str.append("and model.userAddress.district.districtId = :location ");
		 }else if(regionValue.longValue() == 3l){
		   str.append("and model.userAddress.constituency.constituencyId in (:location) ");
		 }else if(regionValue.longValue() == 4l){
		   str.append("and model.userAddress.constituency.constituencyId = :location ");
		 }
		 if(fromDate != null)
			 str.append("and date(model.fileDate) >= :fromDate ");
		 if(toDate != null)
			 str.append("and date(model.fileDate) <= :toDate "); 
		 str.append("order by model.fileDate desc ");
		 Query query = getSession().createQuery(str.toString());
		 if(!"Admin".equalsIgnoreCase(userType))
		 query.setParameter("userId", userId);
		 if(fromDate != null)
		 query.setParameter("fromDate", fromDate);
		 if(toDate != null)
		 query.setParameter("toDate", toDate);
		 if(regionValue.longValue() == 1l || regionValue.longValue() == 2l || regionValue.longValue() == 4l)
			 query.setParameter("location", location);
		 if(regionValue.longValue() == 3l)
			 query.setParameterList("location", locationIds);
		 query.setFirstResult(0);
		 query.setMaxResults(300);
		return query.list();
		 
	}
	 public List<File> getAllLatestFilesByFileIds(List<Long> fileIds){
			
			Query query = getSession().createQuery("select model from File model where model.fileId in(:fileIds) order by model.fileDate desc");
			
			query.setParameterList("fileIds", fileIds);
			
			return query.list();
			
			
		}
}
