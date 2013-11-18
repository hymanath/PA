package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
	  
}
