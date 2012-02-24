package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IKeyCandidateDAO;

public class KeyCandidateDAOHibernateTest extends BaseDaoTestCase{
	IKeyCandidateDAO keyCandidateDAO;

	public IKeyCandidateDAO getKeyCandidateDAO() {
		return keyCandidateDAO;
	}

	public void setKeyCandidateDAO(
			IKeyCandidateDAO keyCandidateDAO) {
		this.keyCandidateDAO = keyCandidateDAO;
	}
	public void testGetCandidatesBasedOnPartyId(){
		List<Object[]> candidates = keyCandidateDAO.getCandidatesBasedOnPartyId("", 1l, 362l);
		
		for(Object[] params : candidates){
			System.out.println(new Long(params[0].toString()));
			System.out.println(params[1].toString());
			
		}
		System.out.println(candidates.size());
	}
	
	/*public void testGetCountCandidate()
	{
		Object object = assignKeyCandidateDAO.getCountCandidate(358l);
		System.out.println(object);
	}*/

}
