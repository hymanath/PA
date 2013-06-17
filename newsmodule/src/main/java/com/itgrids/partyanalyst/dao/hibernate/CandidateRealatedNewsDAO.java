package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateRealatedNewsDAO;
import com.itgrids.partyanalyst.model.CandidateRealatedNews;

public class CandidateRealatedNewsDAO extends GenericDaoHibernate<CandidateRealatedNews, Long> implements 
ICandidateRealatedNewsDAO {
	
	
	public CandidateRealatedNewsDAO() {
		super(CandidateRealatedNews.class);
	}
	
	public List<Object[]> getCandidates(){
		Query queryObj=getSession().createQuery("select distinct model.candidate.candidateId,model.candidate.firstname,model.candidate.lastname,model.candidate.lastname " +
				"from CandidateRealatedNews model ");
		return queryObj.list();
	}

}
