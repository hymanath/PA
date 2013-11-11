package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidatePartyKeywordDAO;


public class CandidatePartyKeywordDAOHibernateTest extends BaseDaoTestCase{
	
  private ICandidatePartyKeywordDAO candidatePartyKeywordDAO;

  public void setCandidatePartyKeywordDAO(
		ICandidatePartyKeywordDAO candidatePartyKeywordDAO) {
	this.candidatePartyKeywordDAO = candidatePartyKeywordDAO;
  }
  
  public void test()
  {
	  candidatePartyKeywordDAO.getAll(); 
  }
  
}
