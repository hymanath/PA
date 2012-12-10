package com.itgrids.partyanalyst.dao.hibernate;


import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.ICandidatePhoneDAO;


public class CandidatePhoneDAOHibernateTest extends BaseDaoTestCase  {
	
	private ICandidatePhoneDAO candidatePhoneDAO;



	public void setCandidatePhoneDAO(ICandidatePhoneDAO candidatePhoneDAO) {
		this.candidatePhoneDAO = candidatePhoneDAO;
	}
	
/*	@SuppressWarnings("unchecked")
	public void testgetCandidateIdAndNumByPhoneTypeId(){
		
		List<Object[]> list =  candidatePhoneDAO.getCandidateIdAndNumByPhoneTypeId((long)1);
		Object[] obj = list.get(0);
		System.out.println(obj[0]);
		System.out.println(obj[1]);
	}*/
	
public void testgetCandidatePhoneDetails(){
	List  list = candidatePhoneDAO.getCandidatePhoneDetails((long) 4975);
	System.out.println(list.size());
}
}
