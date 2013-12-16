package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
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
  /*public void testremoveDublicateData()
  {
	 // @SuppressWarnings("unchecked")
	//List<CandidatePartyKeyword> list= (List<CandidatePartyKeyword>) candidatePartyKeywordDAO.getCandidateFileDetails(1L,2L);
	  //Long no = candidatePartyKeywordDAO.removeKeywordsList(9L);
	  //List<Object[]> list = candidatePartyKeywordDAO.getCandidatePartyKeywordListByUserwise();
	  List<CandidatePartyKeyword> list = candidatePartyKeywordDAO.getCandidatePartyKeywordList(1L);
	  System.out.println(list.size());
	  System.out.println(list);


  }*/

  public void testgetKeyWords()
  {
	  List<Long> fileIds = new ArrayList<Long>();
	  fileIds.add(41006l);
	  fileIds.add(41005l);
	  fileIds.add(41004l);
	  fileIds.add(41003l);
	  fileIds.add(41000l);
	  fileIds.add(40999l);
	  fileIds.add(40998l);
	  fileIds.add(40997l);
	  fileIds.add(40996l);
	  fileIds.add(40995l);
	  List<Object[]> values = candidatePartyKeywordDAO.getKeyWords(fileIds);
	  System.out.println(values.size());
	  for (Object[] objects : values) {
		System.out.println(objects[0] +":"+ objects[1]);
	}
  }
}
