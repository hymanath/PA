package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidatePartyKeywordDAO;
import com.itgrids.partyanalyst.model.CandidatePartyKeyword;


public class CandidatePartyKeywordDAOHibernateTest extends BaseDaoTestCase{
	
  private ICandidatePartyKeywordDAO candidatePartyKeywordDAO;

  public void setCandidatePartyKeywordDAO(
		ICandidatePartyKeywordDAO candidatePartyKeywordDAO) {
	this.candidatePartyKeywordDAO = candidatePartyKeywordDAO;
  }
  
 /* public void test()
  {
	  candidatePartyKeywordDAO.getAll(); 
  }
  */
  public void testremoveDublicateData()
  {
	 // @SuppressWarnings("unchecked")
	//List<CandidatePartyKeyword> list= (List<CandidatePartyKeyword>) candidatePartyKeywordDAO.getCandidateFileDetails(1L,2L);
	  //Long no = candidatePartyKeywordDAO.removeKeywordsList(9L);
	  //List<Object[]> list = candidatePartyKeywordDAO.getCandidatePartyKeywordListByUserwise();
	  List<CandidatePartyKeyword> list = candidatePartyKeywordDAO.getCandidatePartyKeywordList(1L);
	  System.out.println(list.size());
	  System.out.println(list);


  }

}
