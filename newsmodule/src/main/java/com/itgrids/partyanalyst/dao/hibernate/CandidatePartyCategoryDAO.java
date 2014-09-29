package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidatePartyCategoryDAO;
import com.itgrids.partyanalyst.model.CandidatePartyCategory;
import com.itgrids.partyanalyst.model.File;

public class CandidatePartyCategoryDAO extends GenericDaoHibernate<CandidatePartyCategory, Long> implements ICandidatePartyCategoryDAO{

	public CandidatePartyCategoryDAO() {
		super(CandidatePartyCategory.class);
		
	}
	  @SuppressWarnings("unchecked")
		public List<Object[]> getCandidateRelatedCategories(Long candidateId,Date fromDate, Date toDate,String newsType)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append(" select distinct model.gallary.gallaryId,model.gallary.name from CandidatePartyCategory model where (model.candidatePartyFile.sourceCandidate.candidateId =:candidateId) or (model.candidatePartyFile.destinationCandidate.candidateId =:candidateId)");
			
			 str.append(" and model.gallary.isDelete = 'false' and model.candidatePartyFile.file.isDeleted != 'Y' ");
			 if(!newsType.equals(""))
				str.append(" and model.gallary.isPrivate = 'false' "); 
			 
			 if(fromDate != null)
				 str.append(" and model.candidatePartyFile.file.fileDate >= :fromDate ");
			 if(toDate != null)
				 str.append(" and model.candidatePartyFile.file.fileDate <= :toDate ");
			 
			 str.append(" order by model.gallary.name ");
			 Query query = getSession().createQuery(str.toString());
			 
			 query.setParameter("candidateId", candidateId);
			
			 if(fromDate != null)
			  query.setParameter("fromDate", fromDate);
			 if(toDate != null)
			  query.setParameter("toDate", toDate);
			
			 
			 return query.list();
		 }
		
		 public List<File> getFileListByCandidateId(Long candidateId,Integer firstResult,Integer maxResult,String queryType, Date fromDate, Date toDate,List<Long> categoryIdsList)
		  {
			  StringBuilder str = new StringBuilder();
				str.append(" select distinct (model.candidatePartyFile.file) from CandidatePartyCategory model where (model.candidatePartyFile.sourceCandidate.candidateId = :candidateId or model.candidatePartyFile.destinationCandidate.candidateId = :candidateId)");
				str.append(" and (model.candidatePartyFile.file.isDeleted !='Y' or model.candidatePartyFile.file.isDeleted is null)");
				if(queryType.equals("Public") || queryType.equals(""))
				  str.append(" and (model.candidatePartyFile.file.isPrivate !='Y' or model.candidatePartyFile.file.isPrivate is null)");
						
				else if(queryType.equals("Private"))
				  str.append("  and model.candidatePartyFile.file.isPrivate='Y'");
				
				if(fromDate != null)
				 str.append(" and date(model.candidatePartyFile.file.fileDate) >= :fromDate");
				if(toDate != null)
				 str.append(" and date(model.candidatePartyFile.file.fileDate) <= :toDate");
			
				if(categoryIdsList != null && categoryIdsList.size() > 0)
				str.append(" and model.gallary.gallaryId in (:categoryIdsList)");
					
				 str.append(" order by model.candidatePartyFile.file.fileDate desc ");
				Query query = getSession().createQuery(str.toString());
					 
				 query.setLong("candidateId", candidateId);
				
				 if(fromDate != null)
				  query.setParameter("fromDate", fromDate);
				 if(toDate != null)
				  query.setParameter("toDate", toDate);
				 
				 if(categoryIdsList != null && categoryIdsList.size() > 0)
				  query.setParameterList("categoryIdsList", categoryIdsList);
				 
				 if(firstResult != null)
				 query.setFirstResult(firstResult);
				 if(maxResult != null)
				 query.setMaxResults(maxResult);
				 return query.list();
		  }
	@SuppressWarnings("unchecked")
	public List<Object[]> getSelectdGalleryNews(int startIndex,int maxIndex,Long gallaryId)
	{
		Query query = getSession().createQuery("select distinct  model.candidatePartyFile.file.fileTitle ," +
				" model.candidatePartyFile.file.fileDescription , " +
				" model.candidatePartyFile.file.fileDate ,  " +
				" model.candidatePartyFile.file.filePath ," +
				" model.candidatePartyFile.file.fileId ," +
				" model.candidatePartyFile.file.font.fontId,model.candidatePartyFile.file.descFont.fontId from CandidatePartyCategory model " +
				" where model.gallary.gallaryId = :gallaryId and model.candidatePartyFile.file.isDeleted != 'Y' order by model.candidatePartyFile.file.fileDate desc");
		query.setParameter("gallaryId", gallaryId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list(); 
	}
	
	
	public Long getCountForNewsInASelectedGallery(Long gallaryId)
	{
		Query query = getSession().createQuery("select count( distinct model.candidatePartyFile.file.fileId) from CandidatePartyCategory model " +
				" where model.gallary.gallaryId = :gallaryId and model.candidatePartyFile.file.isDeleted != 'Y' ");
		query.setParameter("gallaryId", gallaryId);
		return (Long)query.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getLatestGallerices()
	{
		Query query = getSession().createQuery("select distinct model.gallary.gallaryId, " +
				" model.gallary.name from CandidatePartyCategory model order by model.candidatePartyCategoryId desc ");
		query.setFirstResult(0);
		query.setMaxResults(5);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllCategoryes()
	{
		return getHibernateTemplate().find("select  model.gallary.gallaryId , model.gallary.name ,model.gallary.name , count(distinct model.candidatePartyFile.file.fileId) from CandidatePartyCategory model where model.candidatePartyFile.file.isDeleted != 'Y' group by model.gallary.gallaryId");
	}
	
	
	 @SuppressWarnings("unchecked")
		public List<File> getAllTheNewsForAUserBasedByUserId(String userType,Long userId,Date fromDate,Date toDate,Long importanceId,Long regionValue,List<Long> gallaryIds,Integer startIndex,Integer maxIndex)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append("select distinct model.candidatePartyFile.file from CandidatePartyCategory model " );
			 if(regionValue != 1)
				 str.append(" ,UserAddress ua ");
			 str.append(" where model.candidatePartyFile.file.isDeleted !='Y' ");
			 if(!"Admin".equalsIgnoreCase(userType))
			 str.append("and model.candidatePartyFile.file.user.userId = :userId ");
			 if(importanceId != 0)
			 str.append("and model.candidatePartyFile.file.newsImportance.newsImportanceId = :importanceId ");
			 if(regionValue != 1)
			 str.append("and model.candidatePartyFile.file.fileId = ua.file.fileId and ua.regionScopes.regionScopesId = :regionValue ") ; 
			 if(gallaryIds !=null && gallaryIds.size() > 0)
			 str.append("and model.gallary.gallaryId in (:gallaryIds) ") ; 
			 if(fromDate != null)
				 str.append("and date(model.candidatePartyFile.file.fileDate) >= :fromDate ");
			 if(toDate != null)
				 str.append("and date(model.candidatePartyFile.file.fileDate) <= :toDate "); 
			 str.append("order by model.candidatePartyFile.file.fileDate desc ");
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
			 if(gallaryIds !=null && gallaryIds.size() > 0)
				 query.setParameterList("gallaryIds", gallaryIds);
			 if(startIndex != null)
			   query.setFirstResult(startIndex);
			 if(maxIndex != null)
			 query.setMaxResults(maxIndex);
			return query.list();
			 
		}
	 
	 @SuppressWarnings("unchecked")
		public List<File> getAllTheNewsForAUserBasedByUserIdForALocation(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,List<Long> gallaryIds,Long importanceId,Integer startIndex,Integer maxIndex)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append("select distinct model.candidatePartyFile.file from CandidatePartyCategory model ");
			 if(regionValue.longValue() == 1l || regionValue.longValue() == 2l || regionValue.longValue() == 3l || regionValue.longValue() == 4l){
				 str.append(" ,UserAddress ua ");
			 }
			 str.append("where model.candidatePartyFile.file.isDeleted !='Y' ");
			 if(!"Admin".equalsIgnoreCase(userType))
			 str.append("and model.candidatePartyFile.file.user.userId = :userId ");
			 if(importanceId != 0)
				 str.append("and model.candidatePartyFile.file.newsImportance.newsImportanceId = :importanceId ");
			 if(regionValue.longValue() == 1l || regionValue.longValue() == 2l || regionValue.longValue() == 3l || regionValue.longValue() == 4l){
				 str.append(" and  model.candidatePartyFile.file.fileId = ua.file.fileId ");
			 }
			 if(regionValue.longValue() == 1l){
				   str.append("and ua.state.stateId = :location ");
			 }else if(regionValue.longValue() == 2l){
			   str.append("and ua.district.districtId = :location ");
			 }else if(regionValue.longValue() == 3l){
			   str.append("and ua.constituency.constituencyId in (:location) ");
			 }else if(regionValue.longValue() == 4l){
			   str.append("and ua.constituency.constituencyId = :location ");
			 }
			 if(gallaryIds !=null && gallaryIds.size() > 0)
				 str.append("and model.gallary.gallaryId in (:gallaryIds) ") ; 
			 if(fromDate != null)
				 str.append("and date(model.candidatePartyFile.file.fileDate) >= :fromDate ");
			 if(toDate != null)
				 str.append("and date(model.candidatePartyFile.file.fileDate) <= :toDate "); 
			 str.append("order by model.candidatePartyFile.file.fileDate desc ");
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
			 if(gallaryIds !=null && gallaryIds.size() > 0)
				 query.setParameterList("gallaryIds", gallaryIds);
			 if(importanceId != 0)
				 query.setParameter("importanceId", importanceId);
			 if(startIndex != null)
			   query.setFirstResult(startIndex);
			 if(maxIndex != null)
			   query.setMaxResults(maxIndex);
			return query.list();
			 
		}
	 
	 @SuppressWarnings("unchecked")
		public Long getAllTheNewsCountForAUserBasedByUserIdCount(String userType,Long userId,Date fromDate,Date toDate,Long importanceId,Long regionValue,List<Long> gallaryIds)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append("select count(distinct model.candidatePartyFile.file.fileId) from CandidatePartyCategory model ");
			 if(regionValue != 1)
				str.append(" ,UserAddress ua ");
			 str.append("where model.candidatePartyFile.file.isDeleted !='Y' ");
			 if(!"Admin".equalsIgnoreCase(userType))
			 str.append("and model.candidatePartyFile.file.user.userId = :userId ");
			 if(importanceId != 0)
			 str.append("and model.candidatePartyFile.file.newsImportance.newsImportanceId = :importanceId ");
			 if(regionValue != 1)
				 str.append("and model.candidatePartyFile.file.fileId = ua.file.fileId and ua.regionScopes.regionScopesId = :regionValue ") ;
			 if(gallaryIds !=null && gallaryIds.size() > 0)
			 str.append("and model.gallary.gallaryId in (:gallaryIds) ") ; 
			 if(fromDate != null)
				 str.append("and date(model.candidatePartyFile.file.fileDate) >= :fromDate ");
			 if(toDate != null)
				 str.append("and date(model.candidatePartyFile.file.fileDate) <= :toDate "); 
			 str.append("order by model.candidatePartyFile.file.fileDate desc ");
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
			 if(gallaryIds !=null && gallaryIds.size() > 0)
				 query.setParameterList("gallaryIds", gallaryIds);
			return (Long)query.uniqueResult();
		}
	 
	 @SuppressWarnings("unchecked")
		public Long getAllTheNewsCountForAUserBasedByUserIdForALocation(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,List<Long> gallaryIds,Long importanceId)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append("select count(distinct model.candidatePartyFile.file.fileId) from CandidatePartyCategory model " );
			 if(regionValue.longValue() == 1l || regionValue.longValue() == 2l || regionValue.longValue() == 3l || regionValue.longValue() == 4l){
				 str.append(" ,UserAddress ua ");
			 }
			 str.append("where model.candidatePartyFile.file.isDeleted !='Y' ");
			 if(!"Admin".equalsIgnoreCase(userType))
			 str.append("and model.candidatePartyFile.file.user.userId = :userId ");
			 if(importanceId != 0)
				 str.append("and model.candidatePartyFile.file.newsImportance.newsImportanceId = :importanceId ");
			 if(regionValue.longValue() == 1l || regionValue.longValue() == 2l || regionValue.longValue() == 3l || regionValue.longValue() == 4l){
				 str.append(" and  model.candidatePartyFile.file.fileId = ua.file.fileId ");
			 }
			 if(regionValue.longValue() == 1l){
			     str.append("and ua.state.stateId = :location ");
			 }else if(regionValue.longValue() == 2l){
			   str.append("and ua.district.districtId = :location ");
			 }else if(regionValue.longValue() == 3l){
			   str.append("and ua.constituency.constituencyId in (:location) ");
			 }else if(regionValue.longValue() == 4l){
			   str.append("and ua.constituency.constituencyId = :location ");
			 }
			 if(gallaryIds !=null && gallaryIds.size() > 0)
				 str.append("and model.gallary.gallaryId in (:gallaryIds) ") ; 
			 if(fromDate != null)
				 str.append("and date(model.candidatePartyFile.file.fileDate) >= :fromDate ");
			 if(toDate != null)
				 str.append("and date(model.candidatePartyFile.file.fileDate) <= :toDate "); 
			 str.append("order by model.candidatePartyFile.file.fileDate desc ");
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
			 if(gallaryIds !=null && gallaryIds.size() > 0)
				 query.setParameterList("gallaryIds", gallaryIds);
			if(importanceId != 0)
				query.setParameter("importanceId", importanceId);
			return (Long) query.uniqueResult();
			 
		}
	 public List<Object[]> getCandidatePartyCategoryDetialsByFileId(Long candidatePartyFileId){
			StringBuffer query = new StringBuffer();
			query.append("select model.candidatePartyCategoryId,model.gallary.gallaryId from CandidatePartyCategory model where model.candidatePartyFile.candidatePartyFileId = :candidatePartyFileId");
			Query queryObj = getSession().createQuery(query.toString());
			queryObj.setParameter("candidatePartyFileId", candidatePartyFileId);
			
			 return queryObj.list();
	}
	 
	 public void deleteCandidatePartyCategories(List<Long> candidatePartyFileIds){
		 Query query = getSession().createQuery("delete from CandidatePartyCategory model where model.candidatePartyFile.candidatePartyFileId in(:candidatePartyFileIds)");
		 query.setParameterList("candidatePartyFileIds", candidatePartyFileIds);
		 query.executeUpdate();
	 }
	  @SuppressWarnings("unchecked")
		public List<Object[]> getCategorysCountByFileId(Set<Long> fileIds)
		{
			 Query query = getSession().createQuery("select distinct model.candidatePartyFile.file.fileId, model.gallary.name" +
			 		" from CandidatePartyCategory model where model.candidatePartyFile.file.fileId in (:fileIds) ");
			 query.setParameterList("fileIds", fileIds);
			 return query.list();
		 }
	 public List<Object[]> getCategoriesList(List<Long> fileIds)
	 {
		 Query query = getSession().createQuery("select distinct model.candidatePartyFile.file.fileId , model.gallary.name from CandidatePartyCategory model where model.candidatePartyFile.file.fileId in (:fileIds)");
		 query.setParameterList("fileIds", fileIds);
		 return query.list();
				 
	 }
	 
	 @SuppressWarnings("unchecked")
	 public List<Object[]> getCategoeryAndConsttituencyWiseNews(List<Long> categIds,List<Long> constituencyIds , Date fromDate , Date toDate , int startIndex,int maxIndex,List<Long> partyIds)
	 {
		 StringBuffer queryString = new StringBuffer();
		 
		 queryString.append("select distinct model.candidatePartyFile.file.fileDate , " +
		 		" model.candidatePartyFile.file.fileDescription , " +
		 		" model.gallary.name , ua.constituency.name ," +
		 		" model.candidatePartyFile.file.descFont.fontId,model.candidatePartyFile.file.fileId from " +
		 		" CandidatePartyCategory model,UserAddress ua where ua.constituency.constituencyId in (:constituencyIds)  and  model.candidatePartyFile.file.fileId = ua.file.fileId  and " +
		 		" model.gallary.gallaryId in (:categIds)  " +
		 		" and (model.candidatePartyFile.sourceParty.partyId in(:partyIds) or ( model.candidatePartyFile.sourceParty.partyId is null and model.candidatePartyFile.destinationParty.partyId in(:partyIds) )) " +
		 		" and model.candidatePartyFile.file.isDeleted = 'N' ");
		 if(fromDate != null)
		 {
			queryString.append(" and  model.candidatePartyFile.file.fileDate >= :fromDate ");
		 }
		 if(toDate != null)
		 {
			queryString.append(" and model.candidatePartyFile.file.fileDate <= :toDate ");
		 }
		queryString.append(" order by model.gallary.name , ua.constituency.name , " +
		 		" model.candidatePartyFile.file.fileDate");
		
		 Query query = getSession().createQuery(queryString.toString());
		 query.setParameterList("categIds", categIds);
		 query.setParameterList("constituencyIds", constituencyIds);
		 query.setParameterList("partyIds", partyIds);
		 if(fromDate != null)
		 {
			 query.setParameter("fromDate", fromDate);
		 }
		 if(toDate != null)
		 {
			 query.setParameter("toDate", toDate);
		 }
		 query.setFirstResult(startIndex);
		 query.setMaxResults(maxIndex);
		 return query.list();
	 }
	 
	 @SuppressWarnings("unchecked")
	 public List<Long> getCategoeryAndConsttituencyWiseNewsIds(List<Long> categIds,List<Long> constituencyIds , Date fromDate , Date toDate ,List<Long> partyIds)
	 {
		 StringBuffer queryString = new StringBuffer();
		 
		 queryString.append("select distinct model.candidatePartyFile.file.fileId  from " +
		 		"  CandidatePartyCategory model,UserAddress ua where ua.constituency.constituencyId in (:constituencyIds) and  model.candidatePartyFile.file.fileId = ua.file.fileId  and " +
		 		"  model.gallary.gallaryId in (:categIds)  " +
		 		"  and (model.candidatePartyFile.sourceParty.partyId in( :partyIds) or(model.candidatePartyFile.sourceParty.partyId is null and  model.candidatePartyFile.destinationParty.partyId in( :partyIds))) " +
				"  and model.candidatePartyFile.file.isDeleted = 'N' ");
		 if(fromDate != null)
		 {
			queryString.append(" and  model.candidatePartyFile.file.fileDate >= :fromDate ");
		 }
		 if(toDate != null)
		 {
			queryString.append(" and model.candidatePartyFile.file.fileDate <= :toDate ");
		 }
		 Query query = getSession().createQuery(queryString.toString());
		 query.setParameterList("categIds", categIds);
		 query.setParameterList("constituencyIds", constituencyIds);
		 query.setParameterList("partyIds", partyIds);
		 if(fromDate != null)
		 {
			 query.setParameter("fromDate", fromDate);
		 }
		 if(toDate != null)
		 {
			 query.setParameter("toDate", toDate);
		 }
		 return query.list();
	 }
	 
	 @SuppressWarnings("unchecked")
	 public List<Object[]> getCategoeryAndConsttituencyWiseCount(List<Long> categIds,List<Long> constituencyIds , Date fromDate , Date toDate,List<Long> partyIds )
	 {
		StringBuffer queryString = new StringBuffer();
		
		queryString.append("select model.gallary.name,count(distinct model.candidatePartyFile.file.fileId)," +
		 		"  model.gallary.gallaryId , " +
		 		"  ua.constituency.constituencyId , " +
		 		"  ua.constituency.name from " +
		 		"  CandidatePartyCategory model,UserAddress ua where ua.constituency.constituencyId in (:constituencyIds) and ua.file.fileId = model.candidatePartyFile.file.fileId and " +
		 		"  model.gallary.gallaryId in (:categIds)   " +
		 		"  and (model.candidatePartyFile.sourceParty.partyId in (:partyIds) or (model.candidatePartyFile.sourceParty.partyId is null and model.candidatePartyFile.destinationParty.partyId in (:partyIds))) " +
				"  and model.candidatePartyFile.file.isDeleted = 'N' ");
		 if(fromDate != null)
		 {
			queryString.append(" and  model.candidatePartyFile.file.fileDate >= :fromDate ");
		 }
		 if(toDate != null)
		 {
			queryString.append(" and model.candidatePartyFile.file.fileDate <= :toDate ");
		 }
		queryString.append("  group by ua.constituency.constituencyId ,model.gallary.gallaryId ");
		 
	    Query query = getSession().createQuery(queryString.toString());
		 
		query.setParameterList("categIds", categIds);
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameterList("partyIds", partyIds);
		 if(fromDate != null)
		 {
			 query.setParameter("fromDate", fromDate);
		 }
		 if(toDate != null)
		 {
			 query.setParameter("toDate", toDate);
		 }
		return query.list();
	 }
	
	 @SuppressWarnings("unchecked")
	 public List<Object[]> getCategoeryAndConsttituencyWiseTotalCount(List<Long> categIds,List<Long> constituencyIds , Date fromDate , Date toDate,List<Long> partyIds)
	 {
		 StringBuffer queryString = new StringBuffer();
		 
		 queryString.append("select distinct  model.candidatePartyFile.file.fileId,model.gallary.gallaryId from " +
		 		" CandidatePartyCategory model,UserAddress ua  where ua.constituency.constituencyId in (:constituencyIds) and  model.candidatePartyFile.file.fileId = ua.file.fileId " +
		 		" and model.gallary.gallaryId in (:categIds)  " +
		 		" and (model.candidatePartyFile.sourceParty.partyId in( :partyIds) or (model.candidatePartyFile.sourceParty.partyId is null and model.candidatePartyFile.destinationParty.partyId in( :partyIds)))  " +
				" and model.candidatePartyFile.file.isDeleted = 'N' ");
		 if(fromDate != null)
		 {
			queryString.append(" and  model.candidatePartyFile.file.fileDate >= :fromDate ");
		 }
		 if(toDate != null)
		 {
			queryString.append(" and model.candidatePartyFile.file.fileDate <= :toDate ");
		 }
		 Query query = getSession().createQuery(queryString.toString());
		 
		 query.setParameterList("categIds", categIds);
		 query.setParameterList("constituencyIds", constituencyIds);
		 query.setParameterList("partyIds", partyIds);
		 if(fromDate != null)
		 {
			 query.setParameter("fromDate", fromDate);
		 }
		 if(toDate != null)
		 {
			 query.setParameter("toDate", toDate);
		 }
		 

		 return query.list();
	 }
	
	 @SuppressWarnings("unchecked")
	 public List<Object[]> getCategoeryAndDisrictWiseCount(List<Long> categIds,List<Long> districtIds , Date fromDate , Date toDate,List<Long>  partyIds)
	 {
		 StringBuffer queryString = new StringBuffer();
		 queryString.append("select model.gallary.name,count(distinct model.candidatePartyFile.file.fileId)," +
		 		" model.gallary.gallaryId , " +
		 		" ua.locationValue , " +
		 		" ua.district.districtName from " +
		 		" CandidatePartyCategory model,UserAddress ua where ua.file.fileId = model.candidatePartyFile.file.fileId and ua.regionScopes.regionScopesId = 3 and" +
		 		" ua.locationValue in (:districtIds) and " +
		 		" model.gallary.gallaryId in (:categIds)  " +
		 		" and (model.candidatePartyFile.sourceParty.partyId in( :partyIds) or( model.candidatePartyFile.sourceParty.partyId is null and model.candidatePartyFile.destinationParty.partyId in( :partyIds)))" +
				" and model.candidatePartyFile.file.isDeleted = 'N' ");
		 if(fromDate != null)
		 {
			queryString.append(" and  model.candidatePartyFile.file.fileDate >= :fromDate ");
		 }
		 if(toDate != null)
		 {
			queryString.append(" and model.candidatePartyFile.file.fileDate <= :toDate ");
		 }
		 queryString.append("  group by ua.locationValue ,model.gallary.gallaryId");
		 Query query = getSession().createQuery(queryString.toString());
		 
		 query.setParameterList("categIds", categIds);
		 query.setParameterList("districtIds", districtIds);
		 query.setParameterList("partyIds", partyIds);
		 if(fromDate != null)
		 {
			 query.setParameter("fromDate", fromDate);
		 }
		 if(toDate != null)
		 {
			 query.setParameter("toDate", toDate);
		 }
		 
		 return query.list();
	 }
	 
	 public List<Object[]> getProblemsCount(Date fromDate,Date toDate,Long locationType,List<Long> locationIds,Long categoryId){
		 StringBuilder str = new StringBuilder();
		 
		 str.append("select count(distinct cpc.candidatePartyFile.file.fileId)  ");
		 if(locationType.longValue() == 1l){
			 str.append(" , ua.district.districtId ");
		 }else{
			 str.append(" , ua.constituency.constituencyId ");
		 }
		 str.append("from CandidatePartyCategory cpc,UserAddress ua where  cpc.gallary.gallaryId =:categoryId  " +
		 		" and cpc.candidatePartyFile.file.fileId = ua.file.fileId and ua.file.isDeleted != 'Y'  ");
		 if(locationType.longValue() == 1l){
			 str.append(" and ua.district.districtId in (:locationIds)");
		 }else{
			 str.append(" and ua.constituency.constituencyId in (:locationIds)");
		 }
		 
		 if(fromDate != null)
		 {
			 str.append(" and ua.file.fileDate >= :fromDate ");
		 }
		 if(toDate != null)
		 {
			 str.append(" and ua.file.fileDate <= :toDate ");
		 }
		 
		 if(locationType.longValue() == 1l){
			 str.append(" group by ua.district.districtId ");
		 }else{
			 str.append(" group by ua.constituency.constituencyId ");
		 }
		 Query query = getSession().createQuery(str.toString());	
		 query.setParameter("categoryId", categoryId);
		 query.setParameterList("locationIds", locationIds);
		 if(fromDate != null)
		 {
			 query.setDate("fromDate", fromDate);
		 }
		 if(toDate != null)
		 {
			 query.setDate("toDate", toDate);
		 }
		 return query.list();
	 }
	 
	 
	 public List<Object[]> getElectionIssues(Date fromDate,Date toDate,Long locationType,List<Long> locationIds,Long categoryId,List<Long> partyIds){
          StringBuilder str = new StringBuilder();
		 
		 str.append("select count(distinct cpc.candidatePartyFile.file.fileId),CASE WHEN cpc.candidatePartyFile.sourceParty.partyId is not null THEN sp.shortName ELSE dp.shortName END  ");
		 if(locationType.longValue() == 1l){
			 str.append(" , ua.district.districtId ");
		 }else{
			 str.append(" , ua.constituency.constituencyId ");
		 }
		 str.append("from UserAddress ua,CandidatePartyCategory cpc  Left Join cpc.candidatePartyFile.sourceParty sp Left Join cpc.candidatePartyFile.destinationParty dp where  cpc.gallary.gallaryId =:categoryId  " +
		 		"  and ((cpc.candidatePartyFile.sourceParty.partyId is not null) or (cpc.candidatePartyFile.sourceParty.partyId is null and cpc.candidatePartyFile.destinationParty.partyId is not null)) and cpc.candidatePartyFile.file.fileId = ua.file.fileId and ua.file.isDeleted != 'Y'  ");
		 if(locationType.longValue() == 1l){
			 str.append(" and ua.district.districtId in (:locationIds)");
		 }else{
			 str.append(" and ua.constituency.constituencyId in (:locationIds)");
		 }
		 
		 if(fromDate != null)
		 {
			 str.append(" and ua.file.fileDate >= :fromDate ");
		 }
		 if(toDate != null)
		 {
			 str.append(" and ua.file.fileDate <= :toDate ");
		 }
		 str.append(" and CASE WHEN cpc.candidatePartyFile.sourceParty.partyId is not null THEN sp.partyId ELSE dp.partyId END  in(:partyIds) ");
		 if(locationType.longValue() == 1l){
			 str.append(" group by ua.district.districtId ");
		 }else{
			 str.append(" group by ua.constituency.constituencyId ");
		 }
		 str.append("  ,CASE WHEN cpc.candidatePartyFile.sourceParty.partyId is not null THEN sp.partyId ELSE dp.partyId END ");
		 Query query = getSession().createQuery(str.toString());	
		 query.setParameter("categoryId", categoryId);
		 query.setParameterList("locationIds", locationIds);
		 if(fromDate != null)
		 {
			 query.setDate("fromDate", fromDate);
		 }
		 if(toDate != null)
		 {
			 query.setDate("toDate", toDate);
		 }
		 query.setParameterList("partyIds", partyIds);
		 return query.list();
	 }
	 
	 public List<Object[]> getCategoryWiseBenifit(Date fromDate,Date toDate,Long stateId,Long partyId){
		 StringBuilder queryStr = new StringBuilder();
		 //0 count,1 benifitId,2 categoryId,3 partyId
		 queryStr.append("select count(distinct cpc.candidatePartyFile.file.fileId),CASE WHEN cpc.candidatePartyFile.sourceParty.partyId is not null and cpc.candidatePartyFile.sourceParty.partyId =:partyId THEN cpc.candidatePartyFile.sourceBenefit.benefitId ELSE  " +
		 		" cpc.candidatePartyFile.destinationBenefit.benefitId END,cpc.gallary.gallaryId,"+partyId+"l from CandidatePartyCategory cpc ");
		 if(stateId != null && stateId.longValue() > 0 ){
			 queryStr.append(" ,UserAddress ua ");
		 }
		 queryStr.append(" where date(cpc.candidatePartyFile.file.fileDate) >= :fromDate and date(cpc.candidatePartyFile.file.fileDate) <= :toDate" +
		 		" and cpc.candidatePartyFile.file.isDeleted ='N' and (cpc.candidatePartyFile.sourceParty.partyId = :partyId or cpc.candidatePartyFile.destinationParty.partyId = :partyId ) ");
		 
		 if(stateId != null && stateId.longValue() > 0 ){
			 queryStr.append(" and cpc.candidatePartyFile.file.fileId =  ua.file.fileId and ua.state.stateId =:stateId ");
		 }
		 
		 queryStr.append("  group by cpc.gallary.gallaryId,CASE WHEN cpc.candidatePartyFile.sourceParty.partyId is not null and cpc.candidatePartyFile.sourceParty.partyId =:partyId THEN cpc.candidatePartyFile.sourceBenefit.benefitId ELSE cpc.candidatePartyFile.destinationBenefit.benefitId END");
		 Query query = getSession().createQuery(queryStr.toString());	
		 query.setParameter("partyId", partyId);
		 query.setDate("fromDate", fromDate);
		 query.setDate("toDate", toDate);
		 if(stateId != null && stateId.longValue() > 0 ){
			 query.setParameter("stateId", stateId);
		 }
		 return query.list();
		 
	 }
	 
	 public List<Object[]>  getCategoryBenifitWiseNews(Date fromDate,Date toDate,Long partyId,Long categoryId,Long benfitId,Long stateId){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append("select distinct cpc.candidatePartyFile.file.fileTitle,cpc.candidatePartyFile.file.font.fontId,cpc.candidatePartyFile.file.fileDescription,cpc.candidatePartyFile.file.descFont.fontId,cpc.candidatePartyFile.file.fileId from CandidatePartyCategory cpc ");
		 if(stateId != null && stateId.longValue() > 0 ){
			 queryStr.append(" ,UserAddress ua ");
		 }
		 queryStr.append(" where date(cpc.candidatePartyFile.file.fileDate) >= :fromDate and date(cpc.candidatePartyFile.file.fileDate) <= :toDate and cpc.candidatePartyFile.file.isDeleted !='Y' " +
			 " and ((cpc.candidatePartyFile.sourceParty.partyId = :partyId and cpc.candidatePartyFile.sourceBenefit.benefitId =:benfitId) or (cpc.candidatePartyFile.destinationParty.partyId = :partyId and cpc.candidatePartyFile.destinationBenefit.benefitId =:benfitId ))" +
			 " and  cpc.gallary.gallaryId =:categoryId ");
		 if(stateId != null && stateId.longValue() > 0 ){
			 queryStr.append(" and cpc.candidatePartyFile.file.fileId =  ua.file.fileId and ua.state.stateId =:stateId ");
		 }
		 queryStr.append(" order by cpc.candidatePartyFile.file.fileDate desc, cpc.candidatePartyFile.file.updatedDate desc");
		 Query query = getSession().createQuery(queryStr.toString());
		 query.setDate("fromDate", fromDate);
		 query.setDate("toDate", toDate);
		 query.setParameter("partyId", partyId);
		 query.setParameter("categoryId", categoryId);
		 query.setParameter("benfitId", benfitId);
		 if(stateId != null && stateId.longValue() > 0 ){
			 query.setParameter("stateId", stateId);
		 }
		 return query.list();
	 }
	 
	 public Long getCategoryBenifitWiseNewsCount(Date fromDate,Date toDate,Long partyId,Long categoryId,Long benfitId,Long stateId){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append("select count(distinct cpc.candidatePartyFile.file.fileId) from CandidatePartyCategory cpc ");
		 if(stateId != null && stateId.longValue() > 0 ){
			 queryStr.append(" ,UserAddress ua ");
		 }
		 queryStr.append(" where date(cpc.candidatePartyFile.file.fileDate) >= :fromDate and date(cpc.candidatePartyFile.file.fileDate) <= :toDate and cpc.candidatePartyFile.file.isDeleted !='Y' " +
			 " and ((cpc.candidatePartyFile.sourceParty.partyId = :partyId and cpc.candidatePartyFile.sourceBenefit.benefitId =:benfitId) or (cpc.candidatePartyFile.destinationParty.partyId = :partyId and cpc.candidatePartyFile.destinationBenefit.benefitId =:benfitId ))" +
			 " and  cpc.gallary.gallaryId =:categoryId ");
		 if(stateId != null && stateId.longValue() > 0 ){
			 queryStr.append(" and cpc.candidatePartyFile.file.fileId =  ua.file.fileId and ua.state.stateId =:stateId ");
		 }
		 queryStr.append(" order by cpc.candidatePartyFile.file.fileDate desc, cpc.candidatePartyFile.file.updatedDate desc");
		 Query query = getSession().createQuery(queryStr.toString());
		 query.setDate("fromDate", fromDate);
		 query.setDate("toDate", toDate);
		 query.setParameter("partyId", partyId);
		 query.setParameter("categoryId", categoryId);
		 query.setParameter("benfitId", benfitId);
		 if(stateId != null && stateId.longValue() > 0 ){
			 query.setParameter("stateId", stateId);
		 }
		 return (Long)query.uniqueResult();
	 }
}
