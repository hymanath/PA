
package com.itgrids.partyanalyst.dao.hibernate;

import java.io.Console;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.utils.IConstants;

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
		List<Object[]> list = boothPublicationVoterDAO.getVotersCountByPublicationId("mandal",844l,7l);;
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
	
	/*public void testGetCastAndGenderWiseVotersCountByPublicationIdInALocation()
	{
		List<Object[]> list = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdInALocation(1l,"constituency",232l,6l);
		
		System.out.println(list.size());
		
		for(Object[] params :list)
		{
			//System.out.println(params[0]+"  --  "+params[1]+"  --  "+params[2]);
			System.out.println(params[4]);
		}
	}
*/
/*public void testgetVoterDetailsByCasteState()
{
	 //List<Voter> voter = boothPublicationVoterDAO.getVoterDetailsByCasteState(204l,2l,1l);
	 //System.out.println(voter.size());
}*/

/*public void testgetPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation()
{
List<Object[]> list = boothPublicationVoterDAO.getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation(1l,"constituency",232l,6l);
System.out.println(list.size());
}*/

/*public void testgetPartyWiseCastCategoryVotersCount()
{
	List<Object[]> list = boothPublicationVoterDAO.getCastCategoryWiseVotersCountByPublicationIdInALocation(1l, "constituency", 232l, 6l);
	System.out.println(list.size());
}*/

/*public void testGetCastWiseCount()

{
	List<Object[]> list = boothPublicationVoterDAO.getCastWiseCount(1l, "panchayat", 1l, 7l);
	System.out.println(list.size());
	for(Object[] data:list){
		System.out.println(data[0]+" "+data[1]);
	}
}*/

/*public void testGetPartyWiseCount()
{
	List<Object[]> list = boothPublicationVoterDAO.getPartyWiseCount(1l, "panchayat", 1l, 7l);
	System.out.println(list.size());
	for(Object[] data:list){
		System.out.println(data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
	}
}
*/

/*public void testgetConstituencies()
{
	 List<Object[]> list = boothPublicationVoterDAO.getConstituencies();
	 System.out.println(list.size());
	 for(Object[] params:list)
	 {
		System.out.println(params[0]);
		System.out.println(params[1]);
	 }
}*/

/*public void testGetConstituenciesIds()
{
	List<Long> constituencyIds = boothPublicationVoterDAO.getConstituenciesIds();
	System.out.println(constituencyIds.size());
	if(constituencyIds != null && constituencyIds.size() >0)
		System.out.println(constituencyIds.get(0));
}*/

/*public void testFindVotersGenderWiseCountByPublicationIdInALocation()
{
	List<Object[]> list = boothPublicationVoterDAO.findVotersGenderWiseCountByPublicationIdInALocation(IConstants.CONSTITUENCY,232l,7l);
	System.out.println(list.size());
	
	for(Object[] params : list)
		System.out.println(params[0]+" -- "+params[1]);
}*/

	/*public void testFindFamiliesCountByPublicationIdInALocation()
	{
		List<Long> list = boothPublicationVoterDAO.findFamiliesCountByPublicationIdInALocation(IConstants.CONSTITUENCY, 233l, 8l);
		System.out.println(list.size());
		Long total = 0L;
		for(Long l : list)
		{
			System.out.println(l);
			total = total + l;
		}
		System.out.println(total);
	}*/
	//getAllImpFamilesCount
	
	/*public void testGetAllImpFamilesCount()
	{
		List<Long> list = boothPublicationVoterDAO.getAllImpFamilesCount(IConstants.BOOTH, 122084l, 7l);
		System.out.println(list.size());
		for(Long l :list)
			System.out.println(l);
	}*/

	/*public void testGetVotersCountInAAgeRange()
	{
		System.out.println(boothPublicationVoterDAO.getVotersCountInAAgeRange(IConstants.BOOTH, 122084l,7l,16l, 25l,"F"));
		System.out.println(boothPublicationVoterDAO.getVotersCountInAAgeRange(IConstants.BOOTH, 122084l,7l,26l, 35l,"F"));
		System.out.println(boothPublicationVoterDAO.getVotersCountInAAgeRange(IConstants.BOOTH, 122084l,7l,36l, 45l,"F"));
		System.out.println(boothPublicationVoterDAO.getVotersCountInAAgeRange(IConstants.BOOTH, 122084l,7l,46l, 60l,"F"));
		System.out.println(boothPublicationVoterDAO.getVotersCountInAAgeRange(IConstants.BOOTH, 122084l,7l,60l, null,"F"));
	}*/
	
	/*public void test()
	{
		List<Object[]> list = boothPublicationVoterDAO.getPublicationDetailsBasedOnConstituency(232l);
		System.out.println(list.size());
	}*/
	/*public void testFindVoterContactDetails()
	{
		List<BoothPublicationVoter> list = boothPublicationVoterDAO.findVoterContactDetails(1l);
		for (BoothPublicationVoter objects : list) {
			
			System.out.println(objects);
		}
	}*/
	/*public void testGetFamileyMembersDetailsForHouseNo()
	{
		List<Object[]> list = boothPublicationVoterDAO.getFamileyMembersDetailsForHouseNo("000",121884l,1l);
		for (Object[] objects : list) {
			
			System.out.println(objects[0]);
			System.out.println(objects[1]);
			System.out.println(objects[2]);
		}
	}*/
	/*public void test()
	{
		List<Object[]> list = boothPublicationVoterDAO.getVotersCountDetailsInSpecifiedRangeForLocalElectionBodyByPublicationDateId(83l,7l,46L, 60L);
		System.out.println(list.size());
	}*/

	/*public void testGetVoterPublicationIdsBetweenTwoPublications()
	{
		List<Long> list = boothPublicationVoterDAO.getVoterPublicationIdsBetweenTwoPublications(1l,8l);
		System.out.println(list.size());
		
		for(Long l : list)
			System.out.println(l);
	}*/

	/*public void testGetPreviousPublicationIds()
	{
		List<Long> list = boothPublicationVoterDAO.getPreviousPublicationIds(8l);
		for(Long l : list)
		System.out.println(l);
	}*/

	/*public void testGetVotersByBoothId()
	{
		List<Voter> list = boothPublicationVoterDAO.getVotersByBoothId(121884l);
		System.out.println(list.size());
	}*/

	public void testGetBoothIdAndVoterIdByConstituencyInAPublication()
	{
		List<Object[]> list = boothPublicationVoterDAO.getPartNoAndVoterIdByConstituencyInAPublication(232l,7l);
		System.out.println(list.size());
	}
	
}
