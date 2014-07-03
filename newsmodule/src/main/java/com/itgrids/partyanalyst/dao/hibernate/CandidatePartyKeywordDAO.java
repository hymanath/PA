package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidatePartyKeywordDAO;
import com.itgrids.partyanalyst.model.CandidatePartyKeyword;
import com.itgrids.partyanalyst.model.File;

public class CandidatePartyKeywordDAO extends GenericDaoHibernate<CandidatePartyKeyword, Long> implements ICandidatePartyKeywordDAO{

	public CandidatePartyKeywordDAO() {
		super(CandidatePartyKeyword.class);
	}

	@SuppressWarnings("unchecked")
	public List<CandidatePartyKeyword> getCandidatePartyKeywordList(Long keywordId){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select distinct model from CandidatePartyKeyword model where model.keyword.keywordId =:keywordId ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("keywordId", keywordId);		
		return query.list();
	}	
	public CandidatePartyKeyword getCandidateFileDetails(Long candidateFileId,Long newKeywordID){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select distinct model  from CandidatePartyKeyword model where model.candidatePartyFile.candidatePartyFileId = :candidateFileId and model.keyword.keywordId =:newKeywordID ");
		Query query = getSession().createQuery(queryString.toString());
		
		query.setParameter("candidateFileId", candidateFileId);	
		query.setParameter("newKeywordID", newKeywordID);	
		
		return (CandidatePartyKeyword) query.uniqueResult();
	}

	public void removeDublicateData(Long candidatePartyKeywordId,Long keywordId){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" delete from CandidatePartyKeyword model where model.candidatePartyKeywordId =:candidatePartyKeywordId and model.keyword.keywordId =:keywordId");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("candidatePartyKeywordId", candidatePartyKeywordId);
		query.setParameter("keywordId", keywordId);
		 query.executeUpdate();	
	}	

	public void removeKeywordsList(Long keywordId){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" delete from Keyword model where model.keywordId = :keywordId ");
		Query query = getSession().createQuery(queryString.toString());	
		query.setParameter("keywordId", keywordId);
		query.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	public List<Long> getCandidatePartyKeywordListByUserwise(Long candidatePartyFileId,Long keywordId){
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select distinct model.candidatePartyKeywordId from CandidatePartyKeyword model where model.candidatePartyFile.candidatePartyFileId =:candidatePartyFileId and " +
				"				model.keyword.keywordId =:keywordId ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("candidatePartyFileId", candidatePartyFileId);
		query.setParameter("keywordId", keywordId);
		return query.list();
	}

	public List<Long> getCandidateFileIds(Long keywordId){
		
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select distinct model.candidatePartyFile.candidatePartyFileId from CandidatePartyKeyword model where model.keyword.keywordId =:keywordId ");
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("keywordId", keywordId);
		return query.list();
		
	}
	
	 @SuppressWarnings("unchecked")
		public List<File> getAllTheNewsForAUserBasedByUserId(String userType,Long userId,Date fromDate,Date toDate,Long importanceId,Long regionValue,List<Long> keywordIds,Integer startIndex,Integer maxIndex)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append("select distinct model.candidatePartyFile.file from CandidatePartyKeyword model ");
	
			 if(regionValue != 1)
			  str.append(" ,UserAddress ua ");
			 str.append("where model.candidatePartyFile.file.isDeleted !='Y' ");
			 if(!"Admin".equalsIgnoreCase(userType))
			 str.append("and model.candidatePartyFile.file.user.userId = :userId ");
			 if(importanceId != 0)
			 str.append("and model.candidatePartyFile.file.newsImportance.newsImportanceId = :importanceId ");
			 if(regionValue != 1)
			 str.append("and model.candidatePartyFile.file.fileId = ua.file.fileId and ua.regionScopes.regionScopesId = :regionValue ") ; 
			 if(keywordIds !=null && keywordIds.size() > 0)
			 str.append("and model.keyword.keywordId in (:keywordIds) ") ; 
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
			 if(keywordIds !=null && keywordIds.size() > 0)
				 query.setParameterList("keywordIds", keywordIds);
			 if(startIndex != null)
			   query.setFirstResult(startIndex);
			 if(maxIndex != null)
			 query.setMaxResults(maxIndex);
			return query.list();
			 
		}
	 
	 @SuppressWarnings("unchecked")
		public List<File> getAllTheNewsForAUserBasedByUserIdForALocation(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,List<Long> keywordIds,Long importanceId,Integer startIndex,Integer maxIndex)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append("select distinct model.candidatePartyFile.file from CandidatePartyKeyword model ");
			 if(regionValue.longValue() == 1l || regionValue.longValue() == 2l || regionValue.longValue() == 3l || regionValue.longValue() == 4l){
				 str.append(" ,UserAddress ua ");
			 }
			 str.append("  where model.candidatePartyFile.file.isDeleted !='Y' ");
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
			 if(keywordIds !=null && keywordIds.size() > 0)
				 str.append("and model.keyword.keywordId in (:keywordIds) ") ;
			 if(fromDate != null)
				 str.append("and date(model.candidatePartyFile.file.fileDate) >= :fromDate ");
			 if(toDate != null)
				 str.append("and date(model.candidatePartyFile.file.fileDate) <= :toDate "); 
			 str.append(" group by model.candidatePartyFile.file.fileId order by model.candidatePartyFile.file.fileDate desc ");
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
			 if(keywordIds !=null && keywordIds.size() > 0)
				 query.setParameterList("keywordIds", keywordIds);
			 if(importanceId != 0)
				 query.setParameter("importanceId", importanceId);
			 if(startIndex != null)
			   query.setFirstResult(startIndex);
			 if(maxIndex != null)
			   query.setMaxResults(maxIndex);
			return query.list();
			 
		}
	 
	 @SuppressWarnings("unchecked")
		public Long getAllTheNewsCountForAUserBasedByUserId(String userType,Long userId,Date fromDate,Date toDate,Long importanceId,Long regionValue,List<Long> keywordIds)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append("select count(distinct model.candidatePartyFile.file.fileId) from CandidatePartyKeyword model ");
			 if(regionValue != 1)
				 str.append(" ,UserAddress ua ");
			 str.append("where model.candidatePartyFile.file.isDeleted !='Y' ");
			 if(!"Admin".equalsIgnoreCase(userType))
			 str.append("and model.candidatePartyFile.file.user.userId = :userId ");
			 if(importanceId != 0)
			 str.append("and model.candidatePartyFile.file.newsImportance.newsImportanceId = :importanceId ");
			 if(regionValue != 1)
			 str.append("and model.candidatePartyFile.file.fileId = ua.file.fileId and ua.regionScopes.regionScopesId = :regionValue ") ; 
			 if(keywordIds !=null && keywordIds.size() > 0)
			 str.append("and model.keyword.keywordId in (:keywordIds) ") ; 
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
			 if(keywordIds !=null && keywordIds.size() > 0)
				 query.setParameterList("keywordIds", keywordIds);
			
			return (Long) query.uniqueResult();
			 
		}
	 public List<Object[]> getCandidatePartyKeywordsByFileIds(Long candidatePartyFileId){
			StringBuffer query = new StringBuffer();
			query.append("select model.candidatePartyKeywordId,model.keyword.keywordId from CandidatePartyKeyword model where model.candidatePartyFile.candidatePartyFileId = :candidatePartyFileId");
			Query queryObj = getSession().createQuery(query.toString());
			queryObj.setParameter("candidatePartyFileId", candidatePartyFileId);
			
			 return queryObj.list();

	 }	 
	 public void deleteCandidatePartyKeywords(List<Long> candidatePartyFileIds){
		 Query query = getSession().createQuery("delete from CandidatePartyKeyword model where model.candidatePartyFile.candidatePartyFileId in(:candidatePartyFileIds)");
		 query.setParameterList("candidatePartyFileIds", candidatePartyFileIds);
		 query.executeUpdate();
	 }
	 
	 @SuppressWarnings("unchecked")
		public Long getAllTheNewsForAUserBasedByUserIdForALocationCount(String userType,Long userId,Date fromDate,Date toDate,Long regionValue,Long location,List<Long> locationIds,List<Long> keywordIds,Long importanceId)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append("select count(distinct model.candidatePartyFile.file.fileId) from CandidatePartyKeyword model ");
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
			 if(keywordIds !=null && keywordIds.size() > 0)
				 str.append("and model.keyword.keywordId in (:keywordIds) ") ;
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
			 if(keywordIds !=null && keywordIds.size() > 0)
				 query.setParameterList("keywordIds", keywordIds);
			 if(importanceId != 0)
				 query.setParameter("importanceId", importanceId);
			 return (Long) query.uniqueResult();
			 
		}
		public List<String> getCandidatePartyKeywordByFileId(Long fileId){
			StringBuffer queryString = new StringBuffer();
			queryString.append(" select distinct model.keyword.type from CandidatePartyKeyword model where model.candidatePartyFile.file.fileId =:fileId  ");
			Query query = getSession().createQuery(queryString.toString());
			 query.setParameter("fileId", fileId);
			return query.list();
		}
 
		public List<Object[]> getTotalKeyWordsCount()
		{
			Query query = getSession().createQuery(" select count(distinct model.candidatePartyFile.file.fileId),model.keyword.type,model.keyword.keywordId from CandidatePartyKeyword model where  model.candidatePartyFile.file.isDeleted !='Y' group by  model.keyword.keywordId");
			return query.list();
		}
		 
		 public List<Object[]> getKeyWords(List<Long> fileIds)
		 {
			 Query query = getSession().createQuery("select distinct model.candidatePartyFile.file.fileId,model.keyword.type from CandidatePartyKeyword model where model.candidatePartyFile.file.fileId in (:fileIds)");
			 query.setParameterList("fileIds", fileIds);
			 return query.list();
		 }

	 
	 public List<File> getFileListByCandidateId(Long candidateId,Integer firstResult,Integer maxResult,String queryType, Date fromDate, Date toDate,List<Long> keywordIdsList)
	  {
		  StringBuilder str = new StringBuilder();
			str.append(" select distinct (model.candidatePartyFile.file) from CandidatePartyKeyword model where (model.candidatePartyFile.sourceCandidate.candidateId = :candidateId or model.candidatePartyFile.destinationCandidate.candidateId = :candidateId)");
			str.append(" and (model.candidatePartyFile.file.isDeleted !='Y' or model.candidatePartyFile.file.isDeleted is null)");
			if(queryType.equals("Public") || queryType.equals(""))
			  str.append(" and (model.candidatePartyFile.file.isPrivate !='Y' or model.candidatePartyFile.file.isPrivate is null)");
					
			else if(queryType.equals("Private"))
			  str.append("  and model.candidatePartyFile.file.isPrivate='Y'");
			
			if(fromDate != null)
			 str.append(" and date(model.candidatePartyFile.file.fileDate) >= :fromDate");
			if(toDate != null)
			 str.append(" and date(model.candidatePartyFile.file.fileDate) <= :toDate");
		
			if(keywordIdsList != null && keywordIdsList.size() > 0)
			str.append(" and model.keyword.keywordId in (:keywordIdsList)");
				
			 str.append(" order by model.candidatePartyFile.file.fileDate desc ");
			Query query = getSession().createQuery(str.toString());
				 
			 query.setLong("candidateId", candidateId);
			
			 if(fromDate != null)
			  query.setParameter("fromDate", fromDate);
			 if(toDate != null)
			  query.setParameter("toDate", toDate);
			 
			 if(keywordIdsList != null && keywordIdsList.size() > 0)
			  query.setParameterList("keywordIdsList", keywordIdsList);
			 
			 if(firstResult != null)
			 query.setFirstResult(firstResult);
			 if(maxResult != null)
			 query.setMaxResults(maxResult);
			 return query.list();
	  }	 
	 
	 
	 @SuppressWarnings("unchecked")
		public List<Object[]> getCandidateRelatedkeywords(Long candidateId,Date fromDate, Date toDate,String newsType)
		 {
			 StringBuilder str = new StringBuilder();
			 str.append("select distinct model.keyword.keywordId,model.keyword.type  from CandidatePartyKeyword model where (model.candidatePartyFile.sourceCandidate.candidateId=:candidateId) or (model.candidatePartyFile.destinationCandidate.candidateId=:candidateId) ");
			 
          str.append("and model.candidatePartyFile.file.isDeleted != 'Y' ");
        
			 if(fromDate != null)
				 str.append(" and model.candidatePartyFile.file.fileDate >= :fromDate ");
			 if(toDate != null)
				 str.append(" and model.candidatePartyFile.file.fileDate <= :toDate ");
          
		  str.append(" order by model.keyword.type ");
			 Query query = getSession().createQuery(str.toString());
			 
			 query.setParameter("candidateId", candidateId);
			 if(fromDate != null)
				  query.setParameter("fromDate", fromDate);
				 if(toDate != null)
				  query.setParameter("toDate", toDate);
			 
			 
			 return query.list();
		 }
		
		public List<Object[]> getKeywordsByFileIds(Set<Long> fileIds){
			StringBuilder str = new StringBuilder();
			 str.append("select distinct cpk.candidatePartyFile.file.fileId,cpk.keyword.type from CandidatePartyKeyword cpk where cpk.candidatePartyFile.file.fileId in(:fileIds) ");
			 Query query = getSession().createQuery(str.toString());
			 query.setParameterList("fileIds", fileIds);
			 
			 return query.list();
		}
		
		public List<String> getExistingKeywords(List<String> keywords,List<Long> candidatePartyFileIds){
			StringBuilder str = new StringBuilder();
			 str.append("select distinct cpk.keyword.type from CandidatePartyKeyword cpk where cpk.candidatePartyFile.candidatePartyFileId in(:candidatePartyFileIds) and cpk.keyword.type in(:keywords) ");
			 Query query = getSession().createQuery(str.toString());
			 query.setParameterList("candidatePartyFileIds", candidatePartyFileIds);
			 query.setParameterList("keywords", keywords);
			 return query.list();
		}
		
		public List<Object[]> getAllExistingKeywords(List<String> keywords,List<Long> candidatePartyFileIds){
			StringBuilder str = new StringBuilder();
			 str.append("select distinct cpk.candidatePartyFile.candidatePartyFileId,cpk.keyword.type from CandidatePartyKeyword cpk where cpk.candidatePartyFile.candidatePartyFileId in(:candidatePartyFileIds) and cpk.keyword.type in(:keywords) ");
			 Query query = getSession().createQuery(str.toString());
			 query.setParameterList("candidatePartyFileIds", candidatePartyFileIds);
			 query.setParameterList("keywords", keywords);
			 return query.list();
		}
		
		public int updateCandidatePartyKeyword(List<Long> keywordIds,Long newKeywordId){
			Query query = getSession().createQuery("update CandidatePartyKeyword model set model.keyword.keywordId =:newKeywordId where model.keyword.keywordId in(:keywordIds) ");
			query.setParameterList("keywordIds", keywordIds);
			query.setParameter("newKeywordId", newKeywordId);
			  return query.executeUpdate();
		}
}
