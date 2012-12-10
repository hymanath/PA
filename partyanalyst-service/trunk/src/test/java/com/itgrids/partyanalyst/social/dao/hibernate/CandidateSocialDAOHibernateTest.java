package com.itgrids.partyanalyst.social.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.social.dao.ICandidateSocialDAO;
import com.itgrids.partyanalyst.social.model.CandidateSocial;

public class CandidateSocialDAOHibernateTest extends BaseDaoTestCase{

	private ICandidateSocialDAO candidateSocialDAO;

	public void setCandidateSocialDAO(ICandidateSocialDAO candidateSocialDAO) {
		this.candidateSocialDAO = candidateSocialDAO;
	}

	public void test()
	{
		candidateSocialDAO.getAll();
	}
	
	public void testgetCandidateUrlDetails(){
		List<CandidateSocial> list=candidateSocialDAO.getCandidateUrlDetails(4975l);
		System.out.println(list.size());
	}
}
