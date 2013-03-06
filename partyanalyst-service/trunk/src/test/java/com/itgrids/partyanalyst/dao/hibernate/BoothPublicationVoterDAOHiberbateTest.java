
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 System.out.println("start");
	long startPrevElecTime = System.currentTimeMillis();
   List<Object[]> list = boothPublicationVoterDAO.getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation(1l,"constituency",232l,7l,232l);
    System.out.println(list.size());
     long endPrevElecTime = System.currentTimeMillis();
     long diff = endPrevElecTime-startPrevElecTime;
     System.out.println("time to execute Voters Cast Details in millsec :"+diff+" in seconds:"+diff/1000+"");

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

	/*public void testGetBoothIdAndVoterIdByConstituencyInAPublication()
	{
		List<Object[]> list = boothPublicationVoterDAO.getPartNoAndVoterIdByConstituencyInAPublication(232l,7l);
		System.out.println(list.size());
	}*/

	/*public void testUpdateSerialNoByVoterId()
	{
		System.out.println(boothPublicationVoterDAO.updateSerialNoByVoterId(1l,1l));
	}*/

	/*public void testGetVotersInABooth()
	{
		List<String> partNosList = new ArrayList<String>(0);
		//partNosList.add("141");
		//partNosList.add("142");
		//partNosList.add("143");
		//partNosList.add("144");
		//partNosList.add("145");
		partNosList.add("205");
		List<Voter> votersList = boothPublicationVoterDAO.getVotersInABoothsList(partNosList,163l,8l);
		List<String> houseNoList = new ArrayList<String>(0);
		
		int count = 0;
		
		Map<String,Long> houseNoMap = new HashMap<String,Long>(0); 
		for(Voter voter : votersList)
		{
			if(houseNoMap.get(voter.getHouseNo()) == null)
			{
				houseNoMap.put(voter.getHouseNo(), 0L);
			}
			Long hCount = houseNoMap.get(voter.getHouseNo());
			houseNoMap.put(voter.getHouseNo(), hCount.longValue()+1);
		}
		
		for(Voter voter :votersList)
		{
			if(!houseNoList.contains(voter.getHouseNo()))
			{
				System.out.println();
				houseNoList.add(voter.getHouseNo());
				System.out.print("\t#"+voter.getHouseNo());
				System.out.print("\t"+houseNoMap.get(voter.getHouseNo()));
				count = 0;
			}
			
			if(count > 0 && count % 3 == 0)
			{
				System.out.println();
				System.out.print("\t#"+voter.getHouseNo());
				System.out.print("\t"+houseNoMap.get(voter.getHouseNo()));
			}
			count++;
			System.out.print("\t"+voter.getName());
			System.out.print("\t"+voter.getGender());
			System.out.print("\t"+voter.getAge());
		}
	}
*/
   /*public void testGetSerialNoByVoterIdsList()
   {
	   List<Long> voterIdsList = new ArrayList<Long>(0);
	   voterIdsList.add(1l);
	   List<Object[]> list = boothPublicationVoterDAO.getSerialNoByVoterIdsList(voterIdsList);
	   if(list != null && list.size() > 0)
	   {
		   for(Object[] params : list)
			   System.out.println(params[0] +" "+params[1]);
	   }
   }*/
	
	/*public void testGetBoothPublicationVoterIdsByVoterIdsList()
	{
		List<Long> voterIdsList = new ArrayList<Long>(0);
		voterIdsList.add(1l);
		voterIdsList.add(2l);
		List<Long> list = boothPublicationVoterDAO.getBoothPublicationVoterIdsByVoterIdsList(voterIdsList,8l);
		
		System.out.println(list.size());
		
	}*/

	public void testDeleteByIdsList()
	{
		List<Long> voterIdsList = new ArrayList<Long>(0);
		voterIdsList.add(1l);
		System.out.println(boothPublicationVoterDAO.deleteByIdsList(voterIdsList));
		
	}
}
