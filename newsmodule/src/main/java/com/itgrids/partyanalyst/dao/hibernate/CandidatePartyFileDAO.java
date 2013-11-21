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
		  
		  Query query = getSession().createQuery("select distinct model.sourceCandidate.lastname from CandidatePartyFile model where model.file.fileId =:fileId ");
		  query.setParameter("fileId", fileId);
		  return query.list();
	  }
	  
	public List<Object[]> getCandidatesNewsCount(){
		Query query = getSession().createQuery("select distinct count(model.file.fileId), model.candidate.candidateId,model.candidate.lastname from CandidatePartyFile model where " +
				" model.file.isDeleted != 'Y' and model.file.isPrivate != 'Y' group by model.candidate.candidateId order by model.candidate.lastname ");
		
		return query.list();
	}
	
	 public List<Object[]> getCandidateNamesByFileIds(Set<Long> fileIds){
		  
		  Query query = getSession().createQuery("select distinct model.file.fileId,model.sourceCandidate.lastname from CandidatePartyFile model where model.file.fileId in(:fileIds) ");
		  query.setParameterList("fileIds", fileIds);
		  return query.list();
	  }
	 public List<Object[]> getSourceCandidates(){
			Query query = getSession().createQuery("select distinct count(model.file.fileId),model.sourceCandidate.candidateId,model.sourceCandidate.lastname from CandidatePartyFile model where " +
					"(model.file.isDeleted != 'Y' or model.file.isDeleted is null) and (model.file.isPrivate != 'Y' or model.file.isPrivate is null) group by model.sourceCandidate.candidateId order by model.sourceCandidate.lastname ");
			
			return query.list();
		}
	 public List<Object[]> getDestinationCandidates(){
			Query query = getSession().createQuery("select distinct count(model.file.fileId),model.destinationCandidate.candidateId,model.destinationCandidate.lastname from CandidatePartyFile model where " +
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
	 
	 
}
