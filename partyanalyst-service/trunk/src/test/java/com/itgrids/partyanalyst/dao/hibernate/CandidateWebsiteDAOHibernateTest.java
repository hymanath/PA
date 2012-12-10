package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateWebsiteDAO;
import com.itgrids.partyanalyst.model.CandidateWebsite;

public class CandidateWebsiteDAOHibernateTest  extends BaseDaoTestCase{

	private ICandidateWebsiteDAO candidateWebsiteDAO;
	

	public ICandidateWebsiteDAO getCandidateWebsiteDAO() {
		return candidateWebsiteDAO;
	}

	public void setCandidateWebsiteDAO(ICandidateWebsiteDAO candidateWebsiteDAO) {
		this.candidateWebsiteDAO = candidateWebsiteDAO;
	}
	public void testgetCandidateWebsiteDetails1(){
		List<CandidateWebsite> list=candidateWebsiteDAO.getCandidateWebsiteDetails1(4975l);
		System.out.println(list.get(0));
		
		
	}

	
}
