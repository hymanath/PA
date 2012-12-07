package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;

public class BoothPublicationVoterDAOHiberbateTest extends BaseDaoTestCase{
	
private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	
public void setBoothPublicationVoterDAO(
		IBoothPublicationVoterDAO boothPublicationVoterDAO) {
	this.boothPublicationVoterDAO = boothPublicationVoterDAO;
}


/*public void testfindVotersCastInfoByConstituencyAndPublicationDate()
	{
		List voters = boothPublicationVoterDAO.findVotersCastInfoByConstituencyAndPublicationDate(232l,2l);
		System.out.println(voters.size());
		
		for(Object[] voter:(List<Object[]>)voters)
		{
			System.out.println(voter[0]);
			System.out.println(voter[1]);
			System.out.println(voter[2]);
			
		}
		
	}*/
	
/*public void testfindTotalVotersCastInfoByConstituencyAndPublicationDate()
	{
	Long value = boothPublicationVoterDAO.findTotalVotersCastInfoByConstituencyAndPublicationDate(232l,2l);
	System.out.println(value);
	}
	*/

/*public void testfindVotersCastInfoByBoothIdAndPublicationDate()
{
	List voters = boothPublicationVoterDAO.findVotersCastInfoByPanchayatAndPublicationDate(1l,1l);
	System.out.println(voters.size());
	
	for(Object[] voter:(List<Object[]>)voters)
	{
		System.out.println(voter[0]);
		System.out.println(voter[1]);
		System.out.println(voter[2]);
		
	}
}*/

/*public void testgetGenderWiseCountInConstituency()
{
	List list = boothPublicationVoterDAO. getGenderWiseCountInConstituency(232l,2l);
	for(Object[] voter:(List<Object[]>)list)
	{
		System.out.println(voter[0]);
		System.out.println(voter[1]);
		System.out.println(voter[2]);
	}
}*/

/*public void testfindVotersCastInfoByMandalAndPublicationDateBasedOnSearch()
{
	 List list = boothPublicationVoterDAO.findVotersCastInfoByMandalAndPublicationDate(835l, 2l);
	 for(Object[] voter:(List<Object[]>)list)
		{
			System.out.println(voter[0]);
			System.out.println(voter[1]);
			System.out.println(voter[2]);
		}
}*/

public void testgetVotersCastInfoFromLocalElectionBody()
{
	 List list = boothPublicationVoterDAO.getVotersCastInfoFromLocalElectionBody(31l,2l);
	 for(Object[] voter:(List<Object[]>)list)
	 {
		 System.out.println(voter[0]);
		 System.out.println(voter[1]);
		 System.out.println(voter[2]);
	 }
}
	
}
