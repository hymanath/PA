package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

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
			
			 str.append(" and model.gallary.isDelete = 'false' ");
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
				" where model.gallary.gallaryId = :gallaryId");
		query.setParameter("gallaryId", gallaryId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list(); 
	}
	
	
	public Long getCountForNewsInASelectedGallery(Long gallaryId)
	{
		Query query = getSession().createQuery("select count( distinct model.candidatePartyFile.file.fileId) from CandidatePartyCategory model " +
				" where model.gallary.gallaryId = :gallaryId");
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
		return getHibernateTemplate().find("select  model.gallary.gallaryId , model.gallary.name ,model.gallary.description , count(distinct model.candidatePartyFile.file.fileId) from CandidatePartyCategory model group by model.gallary.gallaryId");
	}
	
	
	 @SuppressWarnings("unchecked")
		public List<File> getAllTheNewsForAUserBasedByUserId(String userType,Long userId,Date fromDate,Date toDate,Long importanceId,Long regionValue,List<Long> gallaryIds,Integer startIndex,Integer maxIndex)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append("select distinct model.candidatePartyFile.file from CandidatePartyCategory model where model.candidatePartyFile.file.isDeleted !='Y' ");
			 if(!"Admin".equalsIgnoreCase(userType))
			 str.append("andmodel.candidatePartyFile.file.user.userId = :userId ");
			 if(importanceId != 0)
			 str.append("and model.candidatePartyFile.file.newsImportance.newsImportanceId = :importanceId ");
			 if(regionValue != 1)
			 str.append("and model.candidatePartyFile.file.regionScopes.regionScopesId = :regionValue ") ; 
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
		public List<File> getAllTheNewsForAUserBasedByUserIdForALocation(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,List<Long> gallaryIds,Integer startIndex,Integer maxIndex)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append("select distinct model.candidatePartyFile.file from CandidatePartyCategory model where model.candidatePartyFile.file.isDeleted !='Y' ");
			 if(!"Admin".equalsIgnoreCase(userType))
			 str.append("and model.candidatePartyFile.file.user.userId = :userId ");
			 if(regionValue.longValue() == 1l){
			   str.append("and model.candidatePartyFile.file.userAddress.state.stateId = :location ");
			 }else if(regionValue.longValue() == 2l){
			   str.append("and model.candidatePartyFile.file.userAddress.district.districtId = :location ");
			 }else if(regionValue.longValue() == 3l){
			   str.append("and model.candidatePartyFile.file.userAddress.constituency.constituencyId in (:location) ");
			 }else if(regionValue.longValue() == 4l){
			   str.append("and model.candidatePartyFile.file.userAddress.constituency.constituencyId = :location ");
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
			 str.append("select count(distinct model.candidatePartyFile.file.fileId) from CandidatePartyCategory model where model.candidatePartyFile.file.isDeleted !='Y' ");
			 if(!"Admin".equalsIgnoreCase(userType))
			 str.append("andmodel.candidatePartyFile.file.user.userId = :userId ");
			 if(importanceId != 0)
			 str.append("and model.candidatePartyFile.file.newsImportance.newsImportanceId = :importanceId ");
			 if(regionValue != 1)
			 str.append("and model.candidatePartyFile.file.regionScopes.regionScopesId = :regionValue ") ; 
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
		public Long getAllTheNewsCountForAUserBasedByUserIdForALocation(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,List<Long> gallaryIds)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append("select count(distinct model.candidatePartyFile.file.fileId) from CandidatePartyCategory model where model.candidatePartyFile.file.isDeleted !='Y' ");
			 if(!"Admin".equalsIgnoreCase(userType))
			 str.append("and model.candidatePartyFile.file.user.userId = :userId ");
			 if(regionValue.longValue() == 1l){
			   str.append("and model.candidatePartyFile.file.userAddress.state.stateId = :location ");
			 }else if(regionValue.longValue() == 2l){
			   str.append("and model.candidatePartyFile.file.userAddress.district.districtId = :location ");
			 }else if(regionValue.longValue() == 3l){
			   str.append("and model.candidatePartyFile.file.userAddress.constituency.constituencyId in (:location) ");
			 }else if(regionValue.longValue() == 4l){
			   str.append("and model.candidatePartyFile.file.userAddress.constituency.constituencyId = :location ");
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
			
			return (Long) query.uniqueResult();
			 
		}
	 
}
