package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidatePartyFileDAO;
import com.itgrids.partyanalyst.model.CandidatePartyFile;

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
		Query query = getSession().createQuery("select count(model.file.fileId), model.candidate.candidateId,model.candidate.lastname from CandidatePartyFile model where " +
				" model.file.isDeleted != 'Y' and model.file.isPrivate != 'Y' group by model.candidate.candidateId order by model.candidate.lastname ");
		
		return query.list();
	}
	
	 public List<Object[]> getCandidateNamesByFileIds(Set<Long> fileIds){
		  
		  Query query = getSession().createQuery("select distinct model.file.fileId,model.sourceCandidate.lastname from CandidatePartyFile model where model.file.fileId in(:fileIds) ");
		  query.setParameterList("fileIds", fileIds);
		  return query.list();
	  }
}
