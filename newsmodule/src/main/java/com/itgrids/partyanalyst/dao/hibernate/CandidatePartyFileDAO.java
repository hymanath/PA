package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidatePartyFileDAO;
import com.itgrids.partyanalyst.model.CandidatePartyFile;
import com.itgrids.partyanalyst.model.File;

public class CandidatePartyFileDAO extends GenericDaoHibernate<CandidatePartyFile, Long> implements ICandidatePartyFileDAO{

	public CandidatePartyFileDAO() {
		super(CandidatePartyFile.class);
	}
	  public List<Object[]> getCandidateNamesByFileId(Long fileId){
		  
		  Query query = getSession().createQuery("select distinct model.sourceCandidate.lastname, model.destinationCandidate.lastname from CandidatePartyFile model where model.file.fileId =:fileId  and model.file.isDeleted != 'Y'");
		  query.setParameter("fileId", fileId);
		  return query.list();
	  }
	  
	public List<Object[]> getCandidatesNewsCount(){
		Query query = getSession().createQuery("select distinct count(model.file.fileId), model.candidate.candidateId,model.candidate.lastname from CandidatePartyFile model where " +
				" model.file.isDeleted != 'Y' and model.file.isPrivate != 'Y' group by model.candidate.candidateId order by model.candidate.lastname ");
		
		return query.list();
	}
	
	 public List<Object[]> getCandidateNamesByFileIds(Set<Long> fileIds){
		  
		  Query query = getSession().createQuery("select distinct model.file.fileId,model.sourceCandidate.lastname from CandidatePartyFile model where model.file.fileId in(:fileIds) and model.file.isDeleted != 'Y'");
		  query.setParameterList("fileIds", fileIds);
		  return query.list();
	  }
	 public List<Object[]> getSourceCandidates(){
			Query query = getSession().createQuery("select  count(distinct model.file.fileId),model.sourceCandidate.candidateId,model.sourceCandidate.lastname from CandidatePartyFile model where " +
					"(model.file.isDeleted != 'Y' or model.file.isDeleted is null) and (model.file.isPrivate != 'Y' or model.file.isPrivate is null) and (model.destinationCandidate.candidateId is null or model.sourceCandidate.candidateId != model.destinationCandidate.candidateId)  group by model.sourceCandidate.candidateId order by model.sourceCandidate.lastname ");
			
			return query.list();
		}
	 public List<Object[]> getDestinationCandidates(){
			Query query = getSession().createQuery("select  count(distinct model.file.fileId),model.destinationCandidate.candidateId,model.destinationCandidate.lastname from CandidatePartyFile model where " +
					"(model.file.isDeleted != 'Y' or model.file.isDeleted is null) and (model.file.isPrivate != 'Y' or model.file.isPrivate is null) group by model.destinationCandidate.candidateId order by model.destinationCandidate.lastname ");
			
			return query.list();
		}
	 
	 public List<File> getCandidateFileListByCandidateId(Long candidateId,Integer firstResult,Integer maxResult,String queryType, Date fromDate, Date toDate)
	  {
		  StringBuilder str = new StringBuilder();
			str.append(" select distinct (model.file) from CandidatePartyFile model where (model.sourceCandidate.candidateId = :candidateId or model.destinationCandidate.candidateId = :candidateId)");
			str.append(" and (model.file.isDeleted !='Y' or model.file.isDeleted is null)");
			if(queryType.equals("Public") || queryType.equals(""))
			  str.append(" and (model.file.isPrivate !='Y' or model.file.isPrivate is null)");
					
			else if(queryType.equals("Private"))
			  str.append("  and model.file.isPrivate='Y'");
			
			if(fromDate != null)
			 str.append(" and date(model.file.fileDate) >= :fromDate");
			if(toDate != null)
			 str.append(" and date(model.file.fileDate) <= :toDate");
			str.append(" order by model.file.fileDate desc ");
			Query query = getSession().createQuery(str.toString());
			query.setLong("candidateId", candidateId);
			
			 if(fromDate != null)
			  query.setParameter("fromDate", fromDate);
			 if(toDate != null)
			  query.setParameter("toDate", toDate);
			
			 if(firstResult != null)
			 query.setFirstResult(firstResult);
			 if(maxResult != null)
			 query.setMaxResults(maxResult);
			 return query.list();
	  }
	 
	 public List<Long> getFilesIdsByCandidateFileId(List<Long> candidateFileId)
	 {
		 	 
		 Query query = getSession().createQuery("select distinct (model.file.fileId) from CandidatePartyFile model " +
			 		" where model.candidatePartyFileId in (:candidateFileId) and model.file.isDeleted != 'Y'" );
		 query.setParameterList("candidateFileId", candidateFileId);
		 return query.list();
	 }
	 
	 @SuppressWarnings("unchecked")
	public List<Object[]> getKeywordsCountByFileIds(Set<Long> fileIds)
	{
		 Query query = getSession().createQuery("select distinct model.candidatePartyFile.file.fileId, model.keyword.type from CandidatePartyKeyword model " +
		 		"where model.candidatePartyFile.file.fileId in (:fileIds)" );
		 query.setParameterList("fileIds", fileIds);
		 return query.list();
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSourcePartyCommentsOnly(Long partyId,Long candidateId,Date fromDate,Date toDate)
	{
		StringBuffer sb = new StringBuffer();
		if(candidateId == null)
		{
			sb.append("select model.sourceParty.partyId,model.sourceParty.shortName,count(distinct model.file.fileId),cast(null as char),cast(null as char) ,  ");
		}
		else
		{
			sb.append("select  model.sourceCandidate.candidateId,model.sourceCandidate.lastname,count(distinct model.file.fileId),cast(null as char),cast(null as char) ,  ");
		}
		sb.append(" model.sourceParty.partyId,model.destinationParty.partyId,model.sourceCandidate.candidateId,model.destinationCandidate.candidateId  ");
		sb.append(" from CandidatePartyFile model where model.file.isDeleted != 'Y' ");
		if(partyId != null)
		{
			sb.append("  and model.sourceParty.partyId = :partyId and model.destinationParty.partyId is  null and model.destinationCandidate.candidateId is null ");
		}
		else
		{
			sb.append("   and model.destinationParty.partyId is  null and model.destinationCandidate.candidateId is null ");
		}
		if(candidateId == null)
		{
			sb.append(" and model.sourceCandidate.candidateId is null ");
		}
		else if( candidateId == 0l)
		{
			sb.append(" and model.sourceCandidate.candidateId is not null");
		}
		else
		{
			sb.append(" and  model.sourceCandidate.candidateId = :candidateId ");
		}
		
		if(fromDate != null){
			sb.append(" and date(model.file.fileDate) >= :fromDate ");
		}
		if(toDate != null){
			sb.append(" and date(model.file.fileDate) <= :toDate ");
		}
		
		
		if(candidateId == null ||  candidateId == 0l)
		{
			sb.append(" group by model.sourceCandidate.candidateId,model.destinationCandidate.candidateId,model.sourceParty.partyId,model.destinationParty.partyId");
		}
		else
		{
			sb.append(" group by model.sourceCandidate.candidateId,model.destinationCandidate.candidateId");
		}
		Query query = getSession().createQuery(sb.toString());
		if(partyId != null)
		{
			query.setParameter("partyId", partyId);
		}
		if(candidateId != null && candidateId != 0l)
		{
			query.setParameter("candidateId", candidateId);	
		}
		if(fromDate != null){
			query.setParameter("fromDate", fromDate);
		}
		if(toDate != null){
			query.setParameter("toDate",toDate);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSourcePartyComments(Long partyId,Long candidateId,Date fromDate,Date toDate)
	{
		StringBuffer sb = new StringBuffer();
		if(candidateId == null)
		{
			sb.append("select model.sourceParty.partyId,model.sourceParty.shortName,count(distinct model.file.fileId),model.destinationParty.partyId,model.destinationParty.shortName  , ");
		}
		else
		{
			sb.append("select  model.sourceCandidate.candidateId,model.sourceCandidate.lastname,count(distinct model.file.fileId),model.destinationParty.partyId,model.destinationParty.shortName,   ");
		}
		sb.append(" model.sourceParty.partyId,model.destinationParty.partyId,model.sourceCandidate.candidateId,cast(null as char)  ");
		sb.append(" from CandidatePartyFile model where model.file.isDeleted != 'Y'  ");
		if(partyId != null)
		{
			sb.append("  and model.sourceParty.partyId = :partyId and model.destinationParty.partyId is not null and model.destinationCandidate.candidateId is null ");
		}
		else
		{
			sb.append(" and model.destinationParty.partyId is not null and model.destinationCandidate.candidateId is null ");
		}
		if(candidateId == null)
		{
			sb.append(" and model.sourceCandidate.candidateId is null ");
		}
		else if( candidateId == 0l)
		{
			sb.append(" and model.sourceCandidate.candidateId is not null");
		}
		else
		{
			sb.append(" and  model.sourceCandidate.candidateId = :candidateId ");
		}
		
		if(fromDate != null){
			sb.append(" and date(model.file.fileDate) >= :fromDate ");
		}
		if(toDate != null){
			sb.append(" and date(model.file.fileDate) <= :toDate ");
		}
		
		if(candidateId == null ||  candidateId == 0l)
		{
			sb.append(" group by model.sourceCandidate.candidateId,model.destinationCandidate.candidateId,model.sourceParty.partyId,model.destinationParty.partyId");
		}
		else
		{
			sb.append(" group by model.sourceCandidate.candidateId,model.destinationParty.partyId");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(partyId != null)
		{
			query.setParameter("partyId", partyId);
		}
		if(candidateId != null && candidateId != 0l)
		{
			query.setParameter("candidateId", candidateId);	
		}
		
		if(fromDate != null){
			query.setParameter("fromDate", fromDate);
		}
		if(toDate != null){
			query.setParameter("toDate",toDate);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSourcePartyCandidateComments(Long partyId,Long candidateId,Date fromDate,Date toDate)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select model.sourceCandidate.candidateId,model.sourceCandidate.lastname,count(distinct model.file.fileId),model.destinationCandidate.candidateId,model.destinationCandidate.lastname,  ");
		sb.append(" model.sourceParty.partyId,model.destinationParty.partyId,model.sourceCandidate.candidateId,model.destinationCandidate.candidateId  ");
		sb.append(" from CandidatePartyFile model where model.file.isDeleted != 'Y' ");
		if(partyId != null)
		{
			sb.append(" and model.sourceParty.partyId = :partyId and model.destinationParty.partyId is not null and model.destinationCandidate.candidateId is not null ");
		}
		else
		{
			sb.append("  and model.destinationParty.partyId is not null and model.destinationCandidate.candidateId is not null ");
		}
		if(candidateId == null)
		{
			sb.append(" and model.sourceCandidate.candidateId is null ");
		}
		else if( candidateId == 0l)
		{
			sb.append(" and model.sourceCandidate.candidateId is not null");
		}
		else
		{
			sb.append(" and  model.sourceCandidate.candidateId = :candidateId");
		}
		
		if(fromDate != null){
			sb.append(" and date(model.file.fileDate) >= :fromDate ");
		}
		if(toDate != null){
			sb.append(" and date(model.file.fileDate) <= :toDate ");
		}
		if(candidateId == null || candidateId == 0l)
		{
			sb.append(" group by model.sourceCandidate.candidateId,model.destinationCandidate.candidateId,model.sourceParty.partyId,model.destinationParty.partyId");
		}
		else
		{
			sb.append(" group by model.sourceCandidate.candidateId,model.destinationCandidate.candidateId");
		}
		Query query = getSession().createQuery(sb.toString());
		if(partyId != null)
		{
			query.setParameter("partyId", partyId);
		}
		
		if(candidateId != null && candidateId != 0l)
		{
			query.setParameter("candidateId", candidateId);	
		}
		if(fromDate != null){
			query.setParameter("fromDate", fromDate);
		}
		if(toDate != null){
			query.setParameter("toDate", toDate);
		}
		
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getDestinationPartyCommentsOnly(Long partyId,Long candidateId,Date fromDate,Date toDate)
	{
		StringBuffer sb = new StringBuffer();
		if(candidateId == null)
		{
			sb.append("select cast(null as char),cast(null as char),count(distinct model.file.fileId),model.destinationParty.partyId,model.destinationParty.shortName ,  ");
		}
		else
		{
			sb.append("select cast(null as char),cast(null as char),count(distinct model.file.fileId),  model.destinationCandidate.candidateId,model.destinationCandidate.lastname, ");
		}
		sb.append(" model.sourceParty.partyId,model.destinationParty.partyId,model.sourceCandidate.candidateId,model.destinationCandidate.candidateId  ");
		sb.append(" from CandidatePartyFile model where model.file.isDeleted != 'Y' " );
		if(partyId != null)
		{
			sb.append(" and model.destinationParty.partyId = :partyId and model.sourceParty.partyId is   null and model.sourceCandidate.candidateId is  null");
		}
		else
		{
			sb.append(" and model.sourceParty.partyId is   null and model.sourceCandidate.candidateId is  null");
		}
		if(candidateId == null)
		{
			sb.append(" and model.destinationCandidate.candidateId is null ");
		}
		else if( candidateId == 0l)
		{
			sb.append(" and model.destinationCandidate.candidateId is not null");
		}
		else
		{
			sb.append(" and model.destinationCandidate.candidateId is not null and model.destinationCandidate.candidateId = :candidateId");
		}
		
		if(fromDate != null){
			sb.append(" and date(model.file.fileDate) >= :fromDate ");
		}
		if(toDate != null){
			sb.append(" and date(model.file.fileDate) <= :toDate ");
		}
		if(candidateId == null || candidateId == 0l)
		{
			sb.append(" group by model.sourceCandidate.candidateId,model.destinationCandidate.candidateId,model.sourceParty.partyId,model.destinationParty.partyId");
		}
		else
		{
			sb.append(" group by model.sourceCandidate.candidateId,model.destinationCandidate.candidateId");
		}
		Query query = getSession().createQuery(sb.toString());
		if(partyId != null)
		{
			query.setParameter("partyId", partyId);
		}
		if(candidateId != null && candidateId != 0l)
		{
			query.setParameter("candidateId", candidateId);	
		}
		if(fromDate != null){
			query.setParameter("fromDate", fromDate);
		}
		if(toDate != null){
			query.setParameter("toDate",toDate);
		}
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDestinationPartyComments(Long partyId,Long candidateId,Date fromDate,Date toDate)
	{	
		StringBuffer sb = new StringBuffer();
		if(candidateId == null)
		{
			sb.append("select model.sourceParty.partyId,model.sourceParty.shortName,count(distinct model.file.fileId),model.destinationParty.partyId,model.destinationParty.shortName,   ");
		}
		else
		{
			sb.append("select  model.sourceParty.partyId,model.sourceParty.shortName , count(distinct model.file.fileId), model.destinationCandidate.candidateId,model.destinationCandidate.lastname,");
		}
		sb.append("model.sourceParty.partyId,model.destinationParty.partyId,model.sourceCandidate.candidateId,model.destinationCandidate.candidateId  ");
		
		sb.append(" from CandidatePartyFile model where model.file.isDeleted != 'Y' " );
		if(partyId != null)
		{
			sb.append(" and model.destinationParty.partyId = :partyId and model.sourceParty.partyId is  not null and model.sourceCandidate.candidateId is  null");
		}
		else
		{
			sb.append(" and model.sourceParty.partyId is  not null and model.sourceCandidate.candidateId is  null");
		}
		if(candidateId == null)
		{
			sb.append(" and model.destinationCandidate.candidateId is null ");
		}
		else if( candidateId == 0l)
		{
			sb.append(" and model.destinationCandidate.candidateId is not null");
		}
		else
		{
			sb.append(" and model.destinationCandidate.candidateId is not null and model.destinationCandidate.candidateId = :candidateId");
		}
		if(fromDate != null){
			sb.append(" and date(model.file.fileDate) >= :fromDate ");
		}
		if(toDate != null){
			sb.append(" and date(model.file.fileDate) <= :toDate ");
		}
		
		sb.append(" group by model.sourceCandidate.candidateId,model.destinationCandidate.candidateId,model.sourceParty.partyId,model.destinationParty.partyId");
		Query query = getSession().createQuery(sb.toString());
		if(partyId != null)
		{
			query.setParameter("partyId", partyId);
		}
		if(candidateId != null && candidateId != 0l)
		{
			query.setParameter("candidateId", candidateId);	
		}
		
		if(fromDate != null){
			query.setParameter("fromDate", fromDate);
		}
		if(toDate != null){
			query.setParameter("toDate",toDate);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDestinationPartyCandidateComments(Long partyId,Long candidateId,Date fromDate,Date toDate)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select model.sourceCandidate.candidateId,model.sourceCandidate.lastname ,count(distinct model.file.fileId),model.destinationCandidate.candidateId,model.destinationCandidate.lastname,  ");
		sb.append(" model.sourceParty.partyId,model.destinationParty.partyId,model.sourceCandidate.candidateId,model.destinationCandidate.candidateId  ");
		sb.append(" from CandidatePartyFile model where model.file.isDeleted != 'Y' " );
		if(partyId != null)
		{
			sb.append(" and model.destinationParty.partyId = :partyId and model.sourceParty.partyId is  not null and model.sourceCandidate.candidateId is not null");
		}
		else
		{
			sb.append(" and model.sourceParty.partyId is  not null and model.sourceCandidate.candidateId is not null");
		}
		sb.append("    ");
		if(candidateId == null)
		{
			sb.append(" and model.destinationCandidate.candidateId is null ");
		}
		else if( candidateId == 0l)
		{
			sb.append(" and model.destinationCandidate.candidateId is not null");
		}
		else
		{
			sb.append(" and model.destinationCandidate.candidateId is not null and model.destinationCandidate.candidateId = :candidateId");
		}
		if(fromDate != null){
			sb.append(" and date(model.file.fileDate) >= :fromDate ");
		}
		if(toDate != null){
			sb.append(" and date(model.file.fileDate) <= :toDate ");
		}
		sb.append(" group by model.sourceCandidate.candidateId,model.destinationCandidate.candidateId,model.sourceParty.partyId,model.destinationParty.partyId");
		Query query = getSession().createQuery(sb.toString());
		if(partyId != null)
		{
			query.setParameter("partyId", partyId);
		}
		
		if(candidateId != null && candidateId != 0l)
		{
			query.setParameter("candidateId", candidateId);	
		}
		if(fromDate != null){
			query.setParameter("fromDate", fromDate);
		}
		if(toDate != null){
			query.setParameter("toDate",toDate);
		}
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSelectedNewsBySearchCriteria(String queryStr ,Date fromDate,Date toDate,  Integer startIndex , Integer maxIndex)
	{
		Query query = getSession().createQuery(queryStr.toString());
		
		 if(fromDate != null)
			  query.setDate("fromDate", fromDate);
			 if(toDate != null)
			  query.setDate("toDate", toDate);
		if(startIndex != null)
		{
			query.setFirstResult(startIndex);
		}
		if(maxIndex != null)
		{
			query.setMaxResults(maxIndex);
		}
		
		return query.list();
	}
	
	public Long getSelectedNewsCountBySearchCriteria(String queryStr,Date fromDate,Date toDate)
	{
		Query query = getSession().createQuery(queryStr.toString());
		 if(fromDate != null)
			  query.setDate("fromDate", fromDate);
			 if(toDate != null)
			  query.setDate("toDate", toDate);
		return (Long)query.uniqueResult();
	}

	 
	 public List<CandidatePartyFile> getInvolvedCandidatesInANews(Long fileId){
		 Query query = getSession().createQuery("select model from CandidatePartyFile model where model.file.fileId =:fileId");
		 query.setParameter("fileId", fileId);
		 return query.list();
	 }
	 
	 public List<CandidatePartyFile> getdetailsBySourceCandiIdForFile(Long fileId,Long candidateId,String queryType){
		 Query query = null;
		 if(queryType.equalsIgnoreCase("source")){
			 query =getSession().createQuery(" select model from CandidatePartyFile model where model.file.fileId =:fileId and model.sourceCandidate.candidateId = :candidateId");
		 }
		 else if(queryType.equalsIgnoreCase("destination")){
			 query =getSession().createQuery(" select model from CandidatePartyFile model where model.file.fileId =:fileId and model.destinationCandidate.candidateId = :candidateId");			
		 }
		 query.setParameter("fileId", fileId);
		 query.setParameter("candidateId", candidateId);
		 return query.list();
	 }
	 
	 
	 public List<Long> getCandidatePartyFileIds(Long fileId){
		 Query query = getSession().createQuery("select model.candidatePartyFileId from  CandidatePartyFile model where model.file.fileId = :fileId");
		 query.setParameter("fileId", fileId);
		 return query.list();
	 }
	 
	 public void deleteCandidatePartyFiles(Long fileId){
		 Query query = getSession().createQuery("delete from CandidatePartyFile model where model.file.fileId = :fileId");
		 query.setParameter("fileId",fileId);
		  query.executeUpdate();
	 }
	/* public int deleteCandidatePartyFileById(String queryType,Long candidateId){
		 Query query = null;
		 if(queryType.equalsIgnoreCase("source")){
			 query =getSession().createQuery(" delete from CandidatePartyFile model where model.sourceCandidate.candidateId = :candidateId");
		 }
		 else if(queryType.equalsIgnoreCase("destination")){
			 query =getSession().createQuery(" delete from CandidatePartyFile model where model.destinationCandidate.candidateId = :candidateId");
			
		 }
		 query.setParameter("candidateId", candidateId);
		 int i = query.executeUpdate();
		 return (Integer) i;
	 }*/
	 public Long getNewsCountBySelectedCriteria(String queryString,Date fromDate,Date toDate,Long partyId,Long candidateId){
		 Query query = getSession().createQuery(queryString);
		 if(candidateId != null && candidateId.longValue() > 0){
			 query.setParameter("candidateId", candidateId);
		 }else if(partyId != null && partyId.longValue() > 0){
			 query.setParameter("partyId", partyId);
		 }
		 if(fromDate != null){
			 query.setDate("fromDate", fromDate);
		 }
		 if(toDate != null){
			 query.setDate("toDate", toDate);
		 }
		 return (Long)query.uniqueResult();
	 }
	 public List<Object[]> getNewsCount(String queryString,Date fromDate,Date toDate,Long partyId,Long candidateId){
		 Query query = getSession().createQuery(queryString);
		 if(candidateId != null && candidateId.longValue() > 0){
			 query.setParameter("candidateId", candidateId);
		 }else if(partyId != null && partyId.longValue() > 0){
			 query.setParameter("partyId", partyId);
		 }
		 if(fromDate != null){
			 query.setDate("fromDate", fromDate);
		 }
		 if(toDate != null){
			 query.setDate("toDate", toDate);
		 }
		 return query.list();
	 }
	 
	 public List<Object[]> getNewsByCriteria(String queryString,Date fromDate,Date toDate,Long partyId,Long candidateId,Integer startIndex,Integer maxIndex){
		 Query query = getSession().createQuery(queryString);
		 if(candidateId != null && candidateId.longValue() > 0){
			 query.setParameter("candidateId", candidateId);
		 }else if(partyId != null && partyId.longValue() > 0){
			 query.setParameter("partyId", partyId);
		 }
		 if(fromDate != null){
			 query.setDate("fromDate", fromDate);
		 }
		 if(toDate != null){
			 query.setDate("toDate", toDate);
		 }
		 if(startIndex != null)
			 query.setFirstResult(startIndex);
		 if(maxIndex != null)
			 query.setMaxResults(maxIndex);
		 return query.list();
	 }
	 
	 @SuppressWarnings("unchecked")
		public Long tdpEffectOnOtherPartiesTotalCount(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate){
			 StringBuilder str = new StringBuilder();
				str.append("select count(distinct model.file.fileId) from CandidatePartyFile model ");
						str.append(addLocationToQuery(level,ids));
				 if(candidateId != null && candidateId.longValue() >0){
					str.append(" and model.sourceCandidate.candidateId = :candidateId ");
				 }else if(partyId != null && partyId.longValue() >0){
					str.append(" and model.sourceParty.partyId = :partyId and model.sourceParty.partyId != model.destinationParty.partyId ");
				 }
				 str.append(" and (model.destinationParty.partyId is not null or model.destinationCandidate.candidateId is not null ) ");
				 str.append(getLocationString(level,ids));
				 if(fromDate != null)
					 str.append(" and date(model.file.fileDate) >= :fromDate");
					if(toDate != null)
					 str.append(" and date(model.file.fileDate) <= :toDate");
					
				 Query query = getSession().createQuery(str.toString());
				
				 if(candidateId != null && candidateId.longValue() >0){
				    query.setParameter("candidateId", candidateId);
				 }else if(partyId != null && partyId.longValue() >0){
					query.setParameter("partyId", partyId);
				 }
				 if(fromDate != null)
					  query.setParameter("fromDate", fromDate);
				 if(toDate != null)
					  query.setParameter("toDate", toDate);
				 return (Long)query.uniqueResult();
		 }
		 
		 @SuppressWarnings("unchecked")
			public List<Object[]> tdpEffectOnOtherPartiesBenifitWiseCount(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate){
				 StringBuilder str = new StringBuilder();
					str.append("select count(distinct model.file.fileId),model.destinationBenefit.benefitId from CandidatePartyFile model ");
							str.append(addLocationToQuery(level,ids));
					 if(candidateId != null && candidateId.longValue() >0){
						str.append(" and model.sourceCandidate.candidateId = :candidateId ");
					 }else if(partyId != null && partyId.longValue() >0){
						str.append(" and model.sourceParty.partyId = :partyId and model.sourceParty.partyId != model.destinationParty.partyId ");
					 }
					 str.append(" and (model.destinationParty.partyId is not null or model.destinationCandidate.candidateId is not null ) and model.destinationBenefit.benefitId is not null ");
					 str.append(getLocationString(level,ids));
					    if(fromDate != null)
						 str.append(" and date(model.file.fileDate) >= :fromDate");
						if(toDate != null)
						 str.append(" and date(model.file.fileDate) <= :toDate");
						str.append(" group by model.destinationBenefit.benefitId ");
					 Query query = getSession().createQuery(str.toString());
					
					 if(candidateId != null && candidateId.longValue() >0){
					    query.setParameter("candidateId", candidateId);
					 }else if(partyId != null && partyId.longValue() >0){
						query.setParameter("partyId", partyId);
					 }
					 if(fromDate != null)
						  query.setParameter("fromDate", fromDate);
					 if(toDate != null)
						  query.setParameter("toDate", toDate);
					 return query.list();
			 }
			
		 @SuppressWarnings("unchecked")
			public List<Object[]> tdpEffectOnOthersPartyWiseTotalCount(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate){
				 StringBuilder str = new StringBuilder();
					str.append("select count(distinct model.file.fileId),model.destinationParty.partyId,model.destinationParty.shortName from CandidatePartyFile model ");
							str.append(addLocationToQuery(level,ids));
					 if(candidateId != null && candidateId.longValue() >0){
						str.append(" and model.sourceCandidate.candidateId = :candidateId ");
					 }else if(partyId != null && partyId.longValue() >0){
						str.append(" and model.sourceParty.partyId = :partyId and model.sourceParty.partyId != model.destinationParty.partyId ");
					 }
					 str.append(" and (model.destinationParty.partyId is not null or model.destinationCandidate.candidateId is not null ) ");
					 str.append(getLocationString(level,ids));
					    if(fromDate != null)
						 str.append(" and date(model.file.fileDate) >= :fromDate");
						if(toDate != null)
						 str.append(" and date(model.file.fileDate) <= :toDate");
						str.append(" group by model.destinationParty.partyId ");
					 Query query = getSession().createQuery(str.toString());
					
					 if(candidateId != null && candidateId.longValue() >0){
					    query.setParameter("candidateId", candidateId);
					 }else if(partyId != null && partyId.longValue() >0){
						query.setParameter("partyId", partyId);
					 }
					 if(fromDate != null)
						  query.setParameter("fromDate", fromDate);
					 if(toDate != null)
						  query.setParameter("toDate", toDate);
					 return query.list();
			 }
			 
		 @SuppressWarnings("unchecked")
			public List<Object[]> tdpEffectOnOthersPartyWiseBenifitCount(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate){
				 StringBuilder str = new StringBuilder();
					str.append("select count(distinct model.file.fileId),model.destinationBenefit.benefitId,model.destinationParty.partyId from CandidatePartyFile model ");
							str.append(addLocationToQuery(level,ids));
					 if(candidateId != null && candidateId.longValue() >0){
						str.append(" and model.sourceCandidate.candidateId = :candidateId ");
					 }else if(partyId != null && partyId.longValue() >0){
						str.append(" and model.sourceParty.partyId = :partyId and model.sourceParty.partyId != model.destinationParty.partyId ");
					 }
					 str.append(" and (model.destinationParty.partyId is not null or model.destinationCandidate.candidateId is not null ) and model.destinationBenefit.benefitId is not null ");
					 str.append(getLocationString(level,ids));
					    if(fromDate != null)
						 str.append(" and date(model.file.fileDate) >= :fromDate");
						if(toDate != null)
						 str.append(" and date(model.file.fileDate) <= :toDate");
						str.append(" group by model.destinationParty.partyId,model.destinationBenefit.benefitId ");
					 Query query = getSession().createQuery(str.toString());
					
					 if(candidateId != null && candidateId.longValue() >0){
					    query.setParameter("candidateId", candidateId);
					 }else if(partyId != null && partyId.longValue() >0){
						query.setParameter("partyId", partyId);
					 }
					 if(fromDate != null)
						  query.setParameter("fromDate", fromDate);
					 if(toDate != null)
						  query.setParameter("toDate", toDate);
					 return query.list();
			 }
			
		 @SuppressWarnings("unchecked")
			public Long otherPartiesOnTdpEffectTotalCount(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate){
				 StringBuilder str = new StringBuilder();
					str.append("select count(distinct model.file.fileId) from CandidatePartyFile model ");
							str.append(addLocationToQuery(level,ids));
				 if(candidateId != null && candidateId.longValue() >0){
					str.append(" and model.destinationCandidate.candidateId = :candidateId ");
				 }else if(partyId != null && partyId.longValue() >0){
					str.append(" and model.destinationParty.partyId = :partyId and model.sourceParty.partyId != model.destinationParty.partyId ");
				 }
				 str.append(" and (model.sourceParty.partyId is not null or model.sourceCandidate.candidateId is not null ) ");
				 str.append(getLocationString(level,ids));
				    if(fromDate != null)
					 str.append(" and date(model.file.fileDate) >= :fromDate");
					if(toDate != null)
					 str.append(" and date(model.file.fileDate) <= :toDate");

				 Query query = getSession().createQuery(str.toString());
				
				 if(candidateId != null && candidateId.longValue() >0){
				    query.setParameter("candidateId", candidateId);
				 }else if(partyId != null && partyId.longValue() >0){
					query.setParameter("partyId", partyId);
				 }
				 if(fromDate != null)
					  query.setParameter("fromDate", fromDate);
				 if(toDate != null)
					  query.setParameter("toDate", toDate);
				 return (Long)query.uniqueResult();
			 }
				 
		 @SuppressWarnings("unchecked")
			public List<Object[]> otherPartiesEffectOnTdpBenifitWise(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate){
				 StringBuilder str = new StringBuilder();
					str.append("select count(distinct model.file.fileId),model.destinationBenefit.benefitId from CandidatePartyFile model ");
							str.append(addLocationToQuery(level,ids));
					 if(candidateId != null && candidateId.longValue() >0){
						str.append(" and model.destinationCandidate.candidateId = :candidateId ");
					 }else if(partyId != null && partyId.longValue() >0){
						str.append(" and model.destinationParty.partyId = :partyId and model.sourceParty.partyId != model.destinationParty.partyId ");
					 }
					 str.append(" and (model.sourceParty.partyId is not null or model.sourceCandidate.candidateId is not null ) and model.destinationBenefit.benefitId is not null ");
					 str.append(getLocationString(level,ids));
					    if(fromDate != null)
						 str.append(" and date(model.file.fileDate) >= :fromDate");
						if(toDate != null)
						 str.append(" and date(model.file.fileDate) <= :toDate");
						str.append(" group by model.destinationBenefit.benefitId");
					 Query query = getSession().createQuery(str.toString());
					
					 if(candidateId != null && candidateId.longValue() >0){
					    query.setParameter("candidateId", candidateId);
					 }else if(partyId != null && partyId.longValue() >0){
						query.setParameter("partyId", partyId);
					 }
					 if(fromDate != null)
						  query.setParameter("fromDate", fromDate);
					 if(toDate != null)
						  query.setParameter("toDate", toDate);
					 return query.list();
			 }
					
		 @SuppressWarnings("unchecked")
			public List<Object[]> otherPartiesWiseEffectOnTdpTotalCount(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate){
				 StringBuilder str = new StringBuilder();
					str.append("select count(distinct model.file.fileId),model.sourceParty.partyId,model.sourceParty.shortName from CandidatePartyFile model " );
							str.append(addLocationToQuery(level,ids));
					 if(candidateId != null && candidateId.longValue() >0){
						str.append(" and model.destinationCandidate.candidateId = :candidateId ");
					 }else if(partyId != null && partyId.longValue() >0){
						str.append(" and model.destinationParty.partyId = :partyId and model.sourceParty.partyId != model.destinationParty.partyId ");
					 }
					 str.append(" and (model.sourceParty.partyId is not null or model.sourceCandidate.candidateId is not null ) ");
					 str.append(getLocationString(level,ids));
					    if(fromDate != null)
						 str.append(" and date(model.file.fileDate) >= :fromDate");
						if(toDate != null)
						 str.append(" and date(model.file.fileDate) <= :toDate");
						str.append(" group by model.sourceParty.partyId");
					 Query query = getSession().createQuery(str.toString());
					
					 if(candidateId != null && candidateId.longValue() >0){
					    query.setParameter("candidateId", candidateId);
					 }else if(partyId != null && partyId.longValue() >0){
						query.setParameter("partyId", partyId);
					 }
					 if(fromDate != null)
						  query.setParameter("fromDate", fromDate);
					 if(toDate != null)
						  query.setParameter("toDate", toDate);
					 return query.list();
			 }
					 
		 @SuppressWarnings("unchecked")
			public List<Object[]> otherPartiesWiseEffectOnTdpBenifitCount(Long partyId,Long candidateId,Long level,String ids,Date fromDate,Date toDate){
				 StringBuilder str = new StringBuilder();
					str.append("select count(distinct model.file.fileId),model.destinationBenefit.benefitId,model.sourceParty.partyId from CandidatePartyFile model ");
							str.append(addLocationToQuery(level,ids));
					 if(candidateId != null && candidateId.longValue() >0){
						str.append(" and model.destinationCandidate.candidateId = :candidateId ");
					 }else if(partyId != null && partyId.longValue() >0){
						str.append(" and model.destinationParty.partyId = :partyId and model.sourceParty.partyId != model.destinationParty.partyId ");
					 }
					 str.append(" and (model.sourceParty.partyId is not null or model.sourceCandidate.candidateId is not null ) and model.destinationBenefit.benefitId is not null ");
					 str.append(getLocationString(level,ids));
					    if(fromDate != null)
						 str.append(" and date(model.file.fileDate) >= :fromDate");
						if(toDate != null)
						 str.append(" and date(model.file.fileDate) <= :toDate");
						str.append(" group by model.sourceParty.partyId,model.destinationBenefit.benefitId");
					 Query query = getSession().createQuery(str.toString());
					
					 if(candidateId != null && candidateId.longValue() >0){
					    query.setParameter("candidateId", candidateId);
					 }else if(partyId != null && partyId.longValue() >0){
						query.setParameter("partyId", partyId);
					 }
					 if(fromDate != null)
						  query.setParameter("fromDate", fromDate);
					 if(toDate != null)
						  query.setParameter("toDate", toDate);
					 return query.list();
			 }
			
			public StringBuilder getLocationString(Long level,String ids){
				StringBuilder str = new StringBuilder();
				if(level != null && level.longValue() > 0 && ids != null && ids.trim().length() > 0){
					 if(level.longValue() == 1)
						 str.append(" and ua.district.districtId in( "+ids+")");
					 if(level.longValue() == 3)
						 str.append(" and ua.constituency.constituencyId in("+ids+")");
					 if(level.longValue() == 2)
						 str.append(" and ua.parliamentConstituency.constituencyId in ("+ids+")");
				 }
				return str;
			}
			
			public StringBuilder addLocationToQuery(Long level,String ids){
				StringBuilder str = new StringBuilder();
				if(level != null && level.longValue() > 0 && ids != null && ids.trim().length() > 0){
					 str.append(" ,UserAddress ua where model.file.fileId = ua.file.fileId and model.file.isDeleted != 'Y' ");
				 }else{
					 str.append(" where model.file.isDeleted != 'Y' ");
				 }
				return str;
			}
			
			public List<Object[]> getPoliticalActivitiesNews(Date fromDate,Date toDate,List<Long> categoryIds,List<Long> districtIds,Integer startIndex,Integer maxIndex){
				StringBuilder str = new StringBuilder();
				str.append("select distinct cpc.candidatePartyFile.file.fileId,CASE WHEN cpc.candidatePartyFile.sourceParty.partyId is not null THEN sp.shortName ELSE dp.shortName END," +
						" ua.district.districtId,ua.district.districtName,consti.constituencyId,consti.name,cpc.candidatePartyFile.file.fileTitle,cpc.candidatePartyFile.file.fileDescription,cpc.candidatePartyFile.file.fileDate,cpc.candidatePartyFile.file.font.fontId,cpc.candidatePartyFile.file.descFont.fontId,CASE WHEN cpc.candidatePartyFile.sourceParty.partyId is not null THEN sp.partyId ELSE dp.partyId END " +
						" from CandidatePartyCategory cpc Left Join cpc.candidatePartyFile.sourceParty sp Left Join cpc.candidatePartyFile.destinationParty dp,UserAddress ua Left Join ua.constituency consti where cpc.candidatePartyFile.file.fileId = ua.file.fileId and (cpc.candidatePartyFile.sourceParty.partyId is not null or (cpc.candidatePartyFile.sourceParty.partyId is null and cpc.candidatePartyFile.destinationParty.partyId is not null)) " +
						" and cpc.candidatePartyFile.file.isDeleted !='Y' and cpc.gallary.gallaryId in(:categoryIds) and ua.district.districtId in(:districtIds) ");
				if(fromDate != null && toDate != null){
					str.append(" and  cpc.candidatePartyFile.file.fileDate between :fromDate and :toDate ");
				}
				else if(fromDate != null){
					str.append(" and  cpc.candidatePartyFile.file.fileDate >= :fromDate ");
				}
				else if(toDate != null){
					str.append(" and  cpc.candidatePartyFile.file.fileDate <= :toDate ");
				}
				str.append(" order by ua.district.districtId,consti.constituencyId");
				
				Query query = getSession().createQuery(str.toString());
						
				if(fromDate != null){
				  query.setDate("fromDate", fromDate);
				}
				if(toDate != null){
				  query.setDate("toDate", toDate);
				}
				query.setParameterList("categoryIds", categoryIds);
				query.setParameterList("districtIds", districtIds);
				query.setFirstResult(startIndex);
				query.setMaxResults(maxIndex);
				return query.list();
			}
			public Long getPoliticalActivitiesNewsCount(Date fromDate,Date toDate,List<Long> categoryIds,List<Long> districtIds){
				StringBuilder str = new StringBuilder();
				str.append(" select distinct cpc.candidatePartyFile.file.fileId , CASE WHEN cpc.candidatePartyFile.sourceParty.partyId is not null THEN sp.shortName ELSE dp.shortName END " +
						" from CandidatePartyCategory cpc Left Join cpc.candidatePartyFile.sourceParty sp Left Join cpc.candidatePartyFile.destinationParty dp,UserAddress ua Left Join ua.constituency consti where cpc.candidatePartyFile.file.fileId = ua.file.fileId and (cpc.candidatePartyFile.sourceParty.partyId is not null or (cpc.candidatePartyFile.sourceParty.partyId is null and cpc.candidatePartyFile.destinationParty.partyId is not null)) " +
						" and cpc.candidatePartyFile.file.isDeleted !='Y' and cpc.gallary.gallaryId in(:categoryIds) and ua.district.districtId in(:districtIds) ");
				if(fromDate != null && toDate != null){
					str.append(" and  cpc.candidatePartyFile.file.fileDate between :fromDate and :toDate ");
				}
				else if(fromDate != null){
					str.append(" and  cpc.candidatePartyFile.file.fileDate >= :fromDate ");
				}
				else if(toDate != null){
					str.append(" and  cpc.candidatePartyFile.file.fileDate <= :toDate ");
				}
				
				Query query = getSession().createQuery(str.toString());
						
				if(fromDate != null){
				  query.setDate("fromDate", fromDate);
				}
				if(toDate != null){
				  query.setDate("toDate", toDate);
				}
				query.setParameterList("categoryIds", categoryIds);
				query.setParameterList("districtIds", districtIds);
				return new Long(query.list().size());
			}
			
			public List<Long> getDestinationDetails(Long partyId,Long fileId){
				Query query = getSession().createQuery("select cpf.candidatePartyFileId from CandidatePartyFile cpf where cpf.file.fileId =:fileId and cpf.sourceParty.partyId is null and cpf.destinationParty.partyId =:partyId   ");
				query.setParameter("partyId", partyId);
				query.setParameter("fileId", fileId);
				return query.list();
			}
			
			public List<Long> getSourceDetails(Long partyId,Long fileId){
				Query query = getSession().createQuery("select cpf.candidatePartyFileId from CandidatePartyFile cpf where cpf.file.fileId =:fileId and cpf.sourceParty.partyId =:partyId   ");
				query.setParameter("partyId", partyId);
				query.setParameter("fileId", fileId);
				return query.list();
			}
			
/*			public List<Object[]> getAllPoliticalActivitiesCount(Date fromDate,Date toDate,List<Long> locationIds,Long locationType,List<Long> partyIds){
				StringBuilder str = new StringBuilder();
				//0 count,1 partyName,2 keywordid,3 keyword,4 locationId,5 locationName
				str.append("select count(distinct cpk.candidatePartyFile.file.fileId),CASE WHEN cpk.candidatePartyFile.sourceParty.partyId is not null THEN sp.shortName ELSE dp.shortName END ,cpk.keyword.keywordId,cpk.keyword.type, ");
				if(locationType.longValue() == 1l){
				  str.append(" ua.district.districtId,ua.district.districtName ");
				}else{
				  str.append(" ua.constituency.constituencyId,ua.constituency.name ");
				}
				str.append(" from CandidatePartyKeyword cpk Left Join cpk.candidatePartyFile.sourceParty sp with sp.partyId in(:partyIds) Left Join cpk.candidatePartyFile.destinationParty dp with dp.partyId in(:partyIds),UserAddress ua where cpk.candidatePartyFile.file.fileId = ua.file.fileId and ((cpk.candidatePartyFile.sourceParty.partyId is not null) or (cpk.candidatePartyFile.sourceParty.partyId is null and cpk.candidatePartyFile.destinationParty.partyId is not null)) " +
						" and cpk.candidatePartyFile.file.isDeleted !='Y' and cpk.keyword.type in('Cadre','MLA/Incharge','MP/Incharge')  ");
				if(locationType.longValue() == 1l){
				   str.append(" and ua.district.districtId in(:locationIds) ");
				}else{
				   str.append(" and ua.constituency.constituencyId in(:locationIds) ");
				}
				if(fromDate != null && toDate != null){
					str.append(" and  cpk.candidatePartyFile.file.fileDate between :fromDate and :toDate ");
				}
				else if(fromDate != null){
					str.append(" and  cpk.candidatePartyFile.file.fileDate >= :fromDate ");
				}
				else if(toDate != null){
					str.append(" and  cpk.candidatePartyFile.file.fileDate <= :toDate ");
				}
				str.append(" group by ");
				if(locationType.longValue() == 1l){
					  str.append(" ua.district.districtId, ");
					}else{
					  str.append(" ua.constituency.constituencyId, ");
					}
				str.append(" CASE WHEN cpk.candidatePartyFile.sourceParty.partyId is not null THEN sp.partyId ELSE dp.partyId END,cpk.keyword.keywordId ");
				Query query = getSession().createQuery(str.toString());
						
				if(fromDate != null){
				  query.setDate("fromDate", fromDate);
				}
				if(toDate != null){
				  query.setDate("toDate", toDate);
				}
				query.setParameterList("locationIds", locationIds);
				query.setParameterList("partyIds", partyIds);
				return query.list();
			}*/
			public List<Object[]> getAllPoliticalActivitiesCount(Date fromDate,Date toDate,List<Long> locationIds,Long locationType,List<Long> partyIds,Long categoryId){
				StringBuilder str = new StringBuilder();
				//0 count,1 partyName,2 keywordid,3 keyword,4 locationId,5 locationName
				str.append("select count(distinct cpk.candidatePartyFile.file.fileId),CASE WHEN cpk.candidatePartyFile.sourceParty.partyId is not null THEN sp.shortName ELSE dp.shortName END ,cpk.keyword.keywordId,cpk.keyword.type, ");
				if(locationType.longValue() == 1l){
				  str.append(" ua.district.districtId,ua.district.districtName ");
				}else{
				  str.append(" ua.constituency.constituencyId,ua.constituency.name ");
				}
				
				str.append(" from CandidatePartyCategory cpc, CandidatePartyKeyword cpk Left Join cpk.candidatePartyFile.sourceParty sp Left Join cpk.candidatePartyFile.destinationParty dp,UserAddress ua where cpk.candidatePartyFile.file.fileId = ua.file.fileId and ((cpk.candidatePartyFile.sourceParty.partyId is not null) or (cpk.candidatePartyFile.sourceParty.partyId is null and cpk.candidatePartyFile.destinationParty.partyId is not null)) " +
						" and cpk.candidatePartyFile.file.isDeleted !='Y' and (cpk.keyword.type in('Cadre','MLA/Incharge','MP/Incharge') or cpk.keyword.type like '%MLA/Incharge%'  or cpk.keyword.type like '%MLA INCHARGE%'  or cpk.keyword.type like '%MP/Incharge%'  or cpk.keyword.type like '%MP incharge%' ) and cpc.candidatePartyFile.candidatePartyFileId = cpk.candidatePartyFile.candidatePartyFileId and  cpc.gallary.gallaryId =:categoryId ");
				
				if(locationType.longValue() == 1l){
				   str.append(" and ua.district.districtId in(:locationIds) ");
				}else{
				   str.append(" and ua.constituency.constituencyId in(:locationIds) ");
				}
				if(fromDate != null && toDate != null){
					str.append(" and  cpk.candidatePartyFile.file.fileDate between :fromDate and :toDate ");
				}
				else if(fromDate != null){
					str.append(" and  cpk.candidatePartyFile.file.fileDate >= :fromDate ");
				}
				else if(toDate != null){
					str.append(" and  cpk.candidatePartyFile.file.fileDate <= :toDate ");
				}
				str.append(" and CASE WHEN cpk.candidatePartyFile.sourceParty.partyId is not null THEN sp.partyId ELSE dp.partyId END  in(:partyIds) group by ");
				if(locationType.longValue() == 1l){
					  str.append(" ua.district.districtId, ");
					}else{
					  str.append(" ua.constituency.constituencyId, ");
					}
				str.append(" CASE WHEN cpk.candidatePartyFile.sourceParty.partyId is not null THEN sp.partyId ELSE dp.partyId END,cpk.keyword.keywordId ");

				Query query = getSession().createQuery(str.toString());
						
				if(fromDate != null){
				  query.setDate("fromDate", fromDate);
				}
				if(toDate != null){
				  query.setDate("toDate", toDate);
				}
				query.setParameterList("locationIds", locationIds);
				query.setParameterList("partyIds", partyIds);
				query.setParameter("categoryId",categoryId);
				return query.list();
			}
			
			public List<Object[]> getAllElectionCampanionCount(Date fromDate,Date toDate,List<Long> locationIds,Long locationType,List<Long> partyIds,Long categoryId){
				StringBuilder str = new StringBuilder();
				//0 count,1 partyName,2 keywordid,3 keyword,4 locationId,5 locationName
				str.append("select count(distinct cpk.candidatePartyFile.file.fileId),CASE WHEN cpk.candidatePartyFile.sourceParty.partyId is not null THEN sp.shortName ELSE dp.shortName END ,cpk.keyword.keywordId,cpk.keyword.type, ");
				if(locationType.longValue() == 1l){
				  str.append(" ua.district.districtId,ua.district.districtName ");
				}else{
				  str.append(" ua.constituency.constituencyId,ua.constituency.name ");
				}
				
				str.append(" from CandidatePartyCategory cpc, CandidatePartyKeyword cpk Left Join cpk.candidatePartyFile.sourceParty sp Left Join cpk.candidatePartyFile.destinationParty dp,UserAddress ua where cpk.candidatePartyFile.file.fileId = ua.file.fileId and ((cpk.candidatePartyFile.sourceParty.partyId is not null) or (cpk.candidatePartyFile.sourceParty.partyId is null and cpk.candidatePartyFile.destinationParty.partyId is not null)) " +
						" and cpk.candidatePartyFile.file.isDeleted !='Y' and ( cpk.keyword.type like '%MLA/Incharge%'  or cpk.keyword.type like '%MLA INCHARGE%'  or cpk.keyword.type like '%MP/Incharge%'  or cpk.keyword.type like '%MP incharge%')  and cpc.candidatePartyFile.candidatePartyFileId = cpk.candidatePartyFile.candidatePartyFileId and  cpc.gallary.gallaryId =:categoryId ");
				
				if(locationType.longValue() == 1l){
				   str.append(" and ua.district.districtId in(:locationIds) ");
				}else{
				   str.append(" and ua.constituency.constituencyId in(:locationIds) ");
				}
				if(fromDate != null && toDate != null){
					str.append(" and  cpk.candidatePartyFile.file.fileDate between :fromDate and :toDate ");
				}
				else if(fromDate != null){
					str.append(" and  cpk.candidatePartyFile.file.fileDate >= :fromDate ");
				}
				else if(toDate != null){
					str.append(" and  cpk.candidatePartyFile.file.fileDate <= :toDate ");
				}
				str.append(" and CASE WHEN cpk.candidatePartyFile.sourceParty.partyId is not null THEN sp.partyId ELSE dp.partyId END  in(:partyIds) group by ");
				if(locationType.longValue() == 1l){
					  str.append(" ua.district.districtId, ");
					}else{
					  str.append(" ua.constituency.constituencyId, ");
					}
				str.append(" CASE WHEN cpk.candidatePartyFile.sourceParty.partyId is not null THEN sp.partyId ELSE dp.partyId END,cpk.keyword.keywordId ");

				Query query = getSession().createQuery(str.toString());
						
				if(fromDate != null){
				  query.setDate("fromDate", fromDate);
				}
				if(toDate != null){
				  query.setDate("toDate", toDate);
				}
				query.setParameterList("locationIds", locationIds);
				query.setParameterList("partyIds", partyIds);
				query.setParameter("categoryId",categoryId);
				return query.list();
			}
}
