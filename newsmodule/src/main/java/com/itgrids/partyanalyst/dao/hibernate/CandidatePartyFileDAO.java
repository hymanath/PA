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
	  public List<String> getCandidateNamesByFileId(Long fileId){
		  
		  Query query = getSession().createQuery("select distinct model.sourceCandidate.lastname from CandidatePartyFile model where model.file.fileId =:fileId  and model.file.isDeleted != 'Y'");
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
	public List<Object[]> getSourcePartyCommentsOnly(Long partyId,Long candidateId)
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
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSourcePartyComments(Long partyId,Long candidateId)
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
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSourcePartyCandidateComments(Long partyId,Long candidateId)
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
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getDestinationPartyCommentsOnly(Long partyId,Long candidateId)
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
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDestinationPartyComments(Long partyId,Long candidateId)
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
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDestinationPartyCandidateComments(Long partyId,Long candidateId)
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
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSelectedNewsBySearchCriteria(String queryStr , Integer startIndex , Integer maxIndex)
	{
		Query query = getSession().createQuery(queryStr.toString());
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
	
	public Long getSelectedNewsCountBySearchCriteria(String queryStr)
	{
		Query query = getSession().createQuery(queryStr.toString());
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
}
