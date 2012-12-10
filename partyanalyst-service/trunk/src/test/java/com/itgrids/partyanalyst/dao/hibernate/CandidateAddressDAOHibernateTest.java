package com.itgrids.partyanalyst.dao.hibernate;


import java.util.List;
import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateAddressDAO;
import com.itgrids.partyanalyst.dao.IAddressTypeDAO;
import com.itgrids.partyanalyst.model.CandidateAddress;

public class CandidateAddressDAOHibernateTest extends BaseDaoTestCase{
	
	private ICandidateAddressDAO candidateAddressDAO;

	

	public void setCandidateAddressDAO(ICandidateAddressDAO candidateAddressDAO) {
		this.candidateAddressDAO = candidateAddressDAO;
	}
	
/*public void testgetCandidateDetailsByCandidateId(){
		
		List<CandidateAddress> list = candidateAddressDAO.getCandidateAddressDetailsByCandidateId(57l);
		for(CandidateAddress params : list)
		{
			//System.out.println(params.getAddress().getDistrict().get);
		}
	}*/
	
	/*public void testgetCandidateDetails(){
		List  list = candidateAddressDAO.getCandidateAddressDetails((long) 4975);
		System.out.println(list.size());
		
	}*/

public void testgetCandidateAddressByAddressId()
{
	Object value = candidateAddressDAO.getCandidateAddressByAddressId(110l);
	System.out.println(value);

}
}
