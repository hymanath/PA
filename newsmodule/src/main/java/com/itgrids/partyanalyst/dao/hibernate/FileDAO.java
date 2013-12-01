package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dto.AnalysisVO;
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
		
		str.append("select distinct model.candidatePartyFile.file from CandidatePartyCategory model where model.gallary.category.categoryId=:categoryId and (model.candidatePartyFile.file.isDeleted !='Y') ");
		 if(newsType != null && !newsType.equalsIgnoreCase(""))
			 str.append(" and (model.candidatePartyFile.file.isPrivate != 'Y') ");
		 if(fromDate != null)
			 str.append(" and date(model.candidatePartyFile.file.fileDate) >= :fromDate ");
			 
		 if(toDate != null)
			 str.append(" and date(model.candidatePartyFile.file.fileDate) <= :toDate ");
		 
		 str.append(" order by date(model.candidatePartyFile.file.fileDate) desc,model.candidatePartyFile.file.updatedDate desc");
				 
		 Query query = getSession().createQuery(str.toString());
		query.setParameter("categoryId", categoryId);
		
		if(fromDate != null)
	   		query.setParameter("fromDate",fromDate);
			 
		if(toDate != null)
		 query.setParameter("toDate",toDate);
	   	 
		if(startIndex != null)
			query.setFirstResult(startIndex);
			if(endIndex != null)
			query.setMaxResults(endIndex);
			
		return query.list();	
	}
	
	public Long getFilesByCategoryIdCount(Long categoryId,String newsType,Date fromDate,Date toDate)
	{
		StringBuilder str= new StringBuilder();
		
		str.append("select  count(distinct model.candidatePartyFile.file.fileId) from CandidatePartyCategory model where model.gallary.category.categoryId=:categoryId and (model.candidatePartyFile.file.isDeleted !='Y') ");
		 if(newsType != null && !newsType.equalsIgnoreCase(""))
			 str.append(" and (model.candidatePartyFile.file.isPrivate != 'Y') ");
		 if(fromDate != null)
			 str.append(" and date(model.candidatePartyFile.file.fileDate) >= :fromDate ");
			 
		 if(toDate != null)
			 str.append(" and date(model.candidatePartyFile.file.fileDate) <= :toDate ");
		  
		 Query query = getSession().createQuery(str.toString());
		query.setParameter("categoryId", categoryId);
		
		if(fromDate != null)
	   		query.setParameter("fromDate",fromDate);
			 
		if(toDate != null)
		 query.setParameter("toDate",toDate);
	   	 
		
			
		return (Long)query.uniqueResult();	
	}
	
	@SuppressWarnings("unchecked")
	public List<File> getTotalFilesList(Long userId,Date fromDate,Date toDate,Integer startIndex,Integer maxIndex)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select model from File model where model.isDeleted != 'Y' ");
	   if(fromDate != null)
		  str.append(" and date(model.fileDate) >= :fromDate ");
	   if(toDate != null)
		  str.append(" and date(model.fileDate) <= :toDate ");
	   		 
	   str.append(" order by date(model.fileDate) desc,model.updatedDate desc ");
	  
	  Query query = getSession().createQuery(str.toString());
	  
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
	
	public Long getTotalFilesListCount(Date fromDate,Date toDate)
	{
	  StringBuilder str = new StringBuilder();
	  str.append(" select count(*) from File model where model.isDeleted != 'Y' ");
	   if(fromDate != null)
		  str.append(" and date(model.fileDate) >= :fromDate ");
	   if(toDate != null)
		  str.append(" and date(model.fileDate) <= :toDate ");
	   		 
	   str.append(" order by date(model.fileDate) desc,model.updatedDate desc ");
	  
	  Query query = getSession().createQuery(str.toString());
	  
	  if(fromDate != null)
	   query.setParameter("fromDate", fromDate);
	  if(toDate != null)
	   query.setParameter("toDate", toDate);
	  return (Long)query.uniqueResult();
	 
	}
		
	 @SuppressWarnings("unchecked")
	public List<File> getAllTheNewsForAUserBasedByUserId(String userType,Long userId,Date fromDate,Date toDate,Long importanceId,Long regionValue,Integer startIndex,Integer maxIndex)
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
		 if(startIndex != null)
		   query.setFirstResult(startIndex);
		 if(maxIndex != null)
		 query.setMaxResults(maxIndex);
		return query.list();
		 
	}
	 
	 @SuppressWarnings("unchecked")
	public List<File> getAllTheNewsForAUserBasedByUserIdForALocation(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,Integer startIndex,Integer maxIndex)
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
		 if(startIndex != null)
		   query.setFirstResult(startIndex);
		 if(maxIndex != null)
		   query.setMaxResults(maxIndex);
		return query.list();
		 
	}
	 public List<File> getAllLatestFilesByFileIds(List<Long> fileIds){
			
			Query query = getSession().createQuery("select model from File model where model.fileId in(:fileIds) order by model.fileDate");
			
			query.setParameterList("fileIds", fileIds);
			
			return query.list();
			
			
		}
	 
	 
	 @SuppressWarnings("unchecked")
		public List<File> getTotalFilesListByLocation(Long userId,Date fromDate,Date toDate,Integer startIndex,Integer maxIndex,Long locationVal,Long scopeId)
		{
		  StringBuilder str = new StringBuilder();
		  str.append(" select model from File model where model.isDeleted != 'Y' ");
		  if((locationVal != null && locationVal > 0) && (scopeId == 3))
			 str.append(" and model.userAddress.district.districtId =:locationVal ");	
		  if((locationVal != null && locationVal > 0) && (scopeId == 4))
			   str.append(" and model.userAddress.constituency.constituencyId =:locationVal ");	
		  if((locationVal != null && locationVal > 0) && (scopeId == 5))
			   str.append(" and model.userAddress.parliamentConstituency.constituencyId =:locationVal ");	
		  //if(scopeId != null && scopeId > 0)
			  // str.append(" and model.regionScopes.regionScopesId=:scopeId ");	  
		  if(userId > 0)
			  str.append(" and model.user.userId=:userId ");	    
		   if(fromDate != null)
			  str.append(" and date(model.fileDate) >= :fromDate ");
		   if(toDate != null)
			  str.append(" and date(model.fileDate) <= :toDate ");
		   str.append(" order by date(model.fileDate) desc,model.updatedDate desc ");
		   Query query = getSession().createQuery(str.toString());
		   if(fromDate != null)
		   query.setParameter("fromDate", fromDate);
		  if(toDate != null)
		   query.setParameter("toDate", toDate);  
		  if((locationVal != null && locationVal > 0) && (scopeId !=null && scopeId > 0))
			  query.setParameter("locationVal", locationVal);  
		  if(userId > 0)
			query.setParameter("userId", userId);   
		  if(startIndex != null)
		   query.setFirstResult(startIndex);
		  if(maxIndex != null)
			query.setMaxResults(maxIndex);
		  return query.list();
		 
		}
	 
	 public List<Object[]> getNewsBySearchCriteria(String query,AnalysisVO vo){
		 Query queryObj = getSession().createQuery(query);
		 if(vo.getFromDate() != null){
			 queryObj.setDate("fromDate", vo.getFromDate());
		 }
         if(vo.getToDate() != null){
        	 queryObj.setDate("toDate", vo.getToDate());
		 }
			return queryObj.list();	
	 }
	 
	 public List<Object[]> getSelectedNewsBySearchCriteria(String query,Date fromDate,Date toDate,Integer startIndex,Integer maxIndex){
		 Query queryObj = getSession().createQuery(query);
		 if(fromDate != null)
		  queryObj.setDate("fromDate", fromDate);
		 if(toDate != null)
		  queryObj.setDate("toDate", toDate);
		 if(startIndex != null)
			 queryObj.setFirstResult(startIndex);
		 if(maxIndex != null)
			queryObj.setMaxResults(maxIndex);
			return queryObj.list();	
	 }
	 
	 public Long getSelectedNewsCountBySearchCriteria(String query,Date fromDate,Date toDate){
		 Query queryObj = getSession().createQuery(query);
		 if(fromDate != null)
		  queryObj.setDate("fromDate", fromDate);
		 if(toDate != null)
		  queryObj.setDate("toDate", toDate);
			return (Long)queryObj.uniqueResult();	
	 }
	 public Long getTotalFilesListCountByLocation(Long userId,Date fromDate,Date toDate,Long locationVal,Long scopeId)
		{
		  StringBuilder str = new StringBuilder();
		  str.append(" select count(distinct model.fileId) from File model where model.isDeleted != 'Y' ");
		  if((locationVal != null && locationVal > 0) && (scopeId == 3))
			 str.append(" and model.userAddress.district.districtId =:locationVal ");	
		  if((locationVal != null && locationVal > 0) && (scopeId == 4))
			   str.append(" and model.userAddress.constituency.constituencyId =:locationVal ");	
		  if((locationVal != null && locationVal > 0) && (scopeId == 5))
			   str.append(" and model.userAddress.parliamentConstituency.constituencyId =:locationVal ");	
		  //if(scopeId != null && scopeId > 0)
			  // str.append(" and model.regionScopes.regionScopesId=:scopeId ");	  
		  if(userId > 0)
			  str.append(" and model.user.userId=:userId ");	    
		   if(fromDate != null)
			  str.append(" and date(model.fileDate) >= :fromDate ");
		   if(toDate != null)
			  str.append(" and date(model.fileDate) <= :toDate ");
		   str.append(" order by date(model.fileDate) desc,model.updatedDate desc ");
		   Query query = getSession().createQuery(str.toString());
		   if(fromDate != null)
		   query.setParameter("fromDate", fromDate);
		  if(toDate != null)
		   query.setParameter("toDate", toDate);  
		  if((locationVal != null && locationVal > 0) && (scopeId !=null && scopeId > 0))
			  query.setParameter("locationVal", locationVal);  
		  if(userId > 0)
			query.setParameter("userId", userId);   
		  return (Long)query.uniqueResult();
		 
		}
}
