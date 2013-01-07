package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Voter;

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

/*public void testgetVotersCastInfoFromLocalElectionBody()
{
	 List list = boothPublicationVoterDAO.getVotersCastInfoFromLocalElectionBody(31l,2l);
	 for(Object[] voter:(List<Object[]>)list)
	 {
		 System.out.println(voter[0]);
		 System.out.println(voter[1]);
		 System.out.println(voter[2]);
	 }
}*/

/*public void testgetVoterDetailsByCaste()
{
	List<Voter> list =boothPublicationVoterDAO.getVoterDetailsByCaste(206l,2l,"YERUKULA");
	for(Voter params :list)
	{
		System.out.println(params.getFirstName());
	}
	
}*/

	/*public void testGetVotersCountByPublicationId()
	{
		List<Object[]> list = boothPublicationVoterDAO.getVotersCountByPublicationId("constituency",232l,6l);;
		System.out.println(list.size());
		System.out.println(list.get(0)[0]+" -- "+list.get(0)[1]);
		System.out.println(list.get(1)[0]+" -- "+list.get(1)[1]);
	}*/
	
	/*public void testFindVotersCountByPublicationIdInALocation()
	{
		Long count = boothPublicationVoterDAO.findVotersCountByPublicationIdInALocation("localElectionBody",83l,6l);
		System.out.println(count);
	}*/

	/*public void testGetCastCategoryWiseVotersCountByPublicationIdInALocation()
	{
		List<Object[]> list = boothPublicationVoterDAO.getCastCategoryWiseVotersCountByPublicationIdInALocation(1l,"constituency",232l,6l);
		System.out.println(list.size());
		
		for(Object[] params :list)
		{
			System.out.println(params[0]+"  --  "+params[1]);
		}
	}*/
	
	public void testGetCastAndGenderWiseVotersCountByPublicationIdInALocation()
	{
		List<Object[]> list = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(1l,"constituency",232l,6l);
		
		System.out.println(list.size());
		
		for(Object[] params :list)
		{
			//System.out.println(params[0]+"  --  "+params[1]+"  --  "+params[2]);
			System.out.println(params[4]);
		}
	}

/*public void testgetVoterDetailsByCasteState()
{
	 //List<Voter> voter = boothPublicationVoterDAO.getVoterDetailsByCasteState(204l,2l,1l);
	 //System.out.println(voter.size());
}*/

}
