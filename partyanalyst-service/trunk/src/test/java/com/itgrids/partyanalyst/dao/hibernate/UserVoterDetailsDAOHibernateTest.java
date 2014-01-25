package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import java.util.List;

import org.apache.xmlbeans.impl.tool.Extension.Param;
import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Locality;
import com.itgrids.partyanalyst.model.UserVoterDetails;





public class UserVoterDetailsDAOHibernateTest extends BaseDaoTestCase  {
	
	private IUserVoterDetailsDAO userVoterDetailsDAO;

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	
	/*public void testgetUserVoterDetails()
	{
		Object object=userVoterDetailsDAO.getUserVoterDetails(5105l,5l);
		System.out.println(object);
	}*/
		
		/*public void testgetCasteByVoterId()
		{
			List<Long> voterIDs = new ArrayList<Long>();
			voterIDs.add(459028l);
			voterIDs.add(459029l);
			//voterIDs.add(459030l);
			
			 * 
			 * boothsList =(List<Long>)(List<?>) boothPublicationVoterDAO.getVoterIdsBasedOnHamletId(panchayatId, boothId);
				List<?> filter =        userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(publicationDateId,boothsList);
			 * 
			
			  List<?> anil=	userVoterDetailsDAO.getVotersIdsByHamletId(42l,1l,IConstants.HAMLET);
			 List<?> hh =              userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(7l,anil);
			 List<Object[]> list = userVoterDetailsDAO.getVotersCountByGenderForLocalAreas(hh);
			System.out.println(list.size());
			for (Object[] objects : list) {
				System.out.println(objects[0]+"---"+objects[1]+"---"+"---"+objects[2]+"--");
				//System.out.println(objects[1]);
				//System.out.println(objects[2]);
			}
		List<?> anil=	userVoterDetailsDAO.getVotersIdsByHamletId(42l,1l,IConstants.HAMLET);
		 List<?> hh =              userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(7l,anil);
		 List<Object[]> list = userVoterDetailsDAO.getAgeWiseInfoForUser(hh);
			 for (Object[] objects : list) {
	           int length=objects.length;
			for(;;)
			{	
			 System.out.println(objects[--length]+"\t");
			 if(length==0)break;
			}
		}
		    List<Object> hamlets =  userVoterDetailsDAO.getHamletsIdsForUserByPanchayat(1l, 1l);
		    List<?> hh =              userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(7l,hamlets);
		    List<Object[]> list= userVoterDetailsDAO.getAgeDataForPanchayatUser(hh);
		    for (Object[] objects : list) {
		           int length=objects.length;
				for(;;)
				{	
				 System.out.println(objects[--length]+"\t");
				 if(length==0)break;
				}
			
			 List<Object[]> list= userVoterDetailsDAO.getAgeDataForBoothByHamlets(1l,8l,123020l,"boothHamlets");
System.out.println(list.size());
for (Object[] objects : list) {
    int length=objects.length;
	for(;;)
	{	
	 System.out.println(objects[--length]+"\t");
	 if(length==0)break;
	}
		
		}
		public void testGetPartyAndCasteDetails()
		{
			
			List<Object[]> list = userVoterDetailsDAO.getPartyAndCasteDetails(26438l, 1l);
			for (Object[] objects : list) {
				System.out.println(objects[0]);
				System.out.println(objects[1]);
				System.out.println(objects[2]);
				System.out.println(objects[3]);
			}
		}
		}    */
		
		/*public void testGetVoterDataForHamlet()
		{
			List<Object[]> voters = userVoterDetailsDAO.getVoterDataForHamlet(29l, 1l,0l, 1000l, "voterId", "asc");
			
			System.out.println(voters.size());
		}
		*/
/*		public void testGetAgeDataForPanchayatUser()
		{
			List<Object> hamlets =  userVoterDetailsDAO.getHamletsIdsForUserByPanchayat(4l, 1l);
			List hh = userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(8l,hamlets);
			//List<Object[]> voters = userVoterDetailsDAO.getAgeDataForPanchayatUser(hh);
			List<Object[]> voters = userVoterDetailsDAO.getAgeDataForPanchayatUser(hh,IConstants.MALE,IConstants.FEMALE,18l,25l,26l,35l,36l,45l,46l,60l);
			System.out.println(voters.size());
			System.out.println(voters);
			for (Object[] objects : voters) {
			System.out.println(objects[0]);
			System.out.println(objects[1]);	
			System.out.println(objects[2]);
			}
		}
		*/

	/*	public void testgetAgeDataForBoothByHamlets()
			{
			//List<Object[]> voters = userVoterDetailsDAO.getAgeDataForBoothByHamlets(1l,8l,123020l,"boothHamlets");
				List<Object[]> voters = userVoterDetailsDAO.getAgeDataForBoothByHamlets(232l,1l,8l,123020l,"boothHamlets",IConstants.MALE,IConstants.FEMALE,18l,25l,26l,35l,36l,45l,46l,60l);
				System.out.println(voters.size());
				System.out.println(voters);
				for (Object[] objects : voters) {
				System.out.println(objects[0]);
				System.out.println(objects[1]);	
				System.out.println(objects[2]);
				}
			}*/
	
		/*	public void testgetAgeWiseInfoForUser()
		{
			List<Object> hamlets =  userVoterDetailsDAO.getHamletsIdsForUserByPanchayat(4l, 1l);
			List hh = userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(8l,hamlets);
			//List<Object[]> voters = userVoterDetailsDAO.getAgeWiseInfoForUser(hh);
			List<Object[]> voters = userVoterDetailsDAO.getAgeWiseInfoForUser(hh,IConstants.MALE,IConstants.FEMALE,18l,25l,26l,35l,36l,45l,46l,60l);
			System.out.println(voters.size());
			System.out.println(voters);
			for (Object[] objects : voters) {
			System.out.println(objects[0]);
			System.out.println(objects[1]);	
			System.out.println(objects[2]);
			}
		}
	*/
		/*public void testgetAgeWiseInfoForUser()
		{
			List<Object> hamlets =  userVoterDetailsDAO.getHamletsIdsForUserByPanchayat(4l, 1l);
			//System.out.println(hamlets);
			List hh = userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(8l,hamlets);
			//List<Object[]> voters = userVoterDetailsDAO.getLocalityIdsForUser(28l,1l,hh);
			List<Object[]> voters = userVoterDetailsDAO.getWardsBYLocalElectionBodyId(83l,8l,1l);
			System.out.println(voters.size());
			System.out.println(voters);
			for (Object[] objects : voters) {
			System.out.println(objects[0]);
			System.out.println(objects[1]);	
			
			}
		}*/
		
		/*public void testGetBoothIdsByCustomWardId()
		{
			List<Long> list = userVoterDetailsDAO.getBoothIdsByCustomWardId(28858l,232l,8l);
			System.out.println(list.size());
		}*/
		
		/*public void testGetAllLocalitiesForHamletOrWard()
		{
			String queryStr = " model.ward.localElectionBody.localElectionBodyId =:id ";
			List<Object[]> list = userVoterDetailsDAO.getAllLocalitiesForHamletOrWard("muncipalityCustomWard",1l, 83l,8l, queryStr);
			System.out.println(list.size());
			for(Object[] params : list)
			 System.out.println(params[0]+" "+params[1]);
		}*/
		/*public void testGetPanchayatWiseHamletsAssignedDetailsInAConstituency()
		{
			List<Object[]> list = userVoterDetailsDAO.getPanchayatWiseHamletsAssignedDetailsInAConstituency(232l,8l);
			System.out.println(list.size());
			for(Object[] params : list)
			{
				System.out.println();
				for(Object object : params)
					System.out.print("\t"+object);
			}
		}*/
		
		/*public void testGetBoothsForCustomWard()
		{
			List<Object[]> list = userVoterDetailsDAO.getBoothsForCustomWard(28858l, 232l, 8l, 1l);
			if(list!= null && list.size() > 0)
				for(Object[] params : list)
					System.out.println(params[0]+"------ "+params[1]);
			
		}*/
		/*public void testGetPanchayatWiseHamletsAssignedDetails()
		{
			List<Object[]> list = userVoterDetailsDAO.getPanchayatWiseHamletsAssignedDetails(232l,8l, 1l);
			System.out.println(list.size());
			for(Object[] params : list)
			{
				System.out.println();
				for(Object object : params)
					System.out.print("\t"+object);
			}
		}*/
		
		
		/*public void testGetCadreCountForSelectedHamlet()
		{
			List<Long> values = userVoterDetailsDAO.getCountForSelectedTypeInHamlet(28863l,1l,"cadre","as");
			for (Long long1 : values) {
				System.out.println(long1);
			}
		}*/
		/*public void testGetCadreDetailsForSelectedHamlet()
		{
			List<Cadre> values = userVoterDetailsDAO.getCadreDetailsForSelectedHamlet(28863l,1l,0,10,"asc","voterId","");
			for (Cadre cadre : values) {
				System.out.println(cadre.getFirstName());
			}
		}*/
		/*public void testGetCadreDetailsForSelectedHamlet()
		{
			List<InfluencingPeople> values = userVoterDetailsDAO.getInfluencingPeopleDetailsForSelectedHamlet(33l,1l);
			for (InfluencingPeople cadre : values) {
				System.out.println(cadre.getFirstName());
			}
		}*/
		/*public void testGetCadreDetailsForSelectedHamlet()
		{
			List<Candidate> values = userVoterDetailsDAO.getCandidateDetailsForSelectedHamlet(33l,1l,0,10,"asc","voterId");
			for (Candidate candiadte : values) {
				System.out.println(candiadte.getFirstname());
			}
		}*/
		
		/*public void test()
		{
			List<Object[]> values = userVoterDetailsDAO.getVotersCountBasedOnGenderForSelectedWard(1l,28863l,7l);
			System.out.println(values.size());
			
			for (Object[] objects : values) {
				System.out.println(objects[0]);
				System.out.println(objects[1]);
			}
		}*/
		
		/*public void testgetDistinctWardsOfLocalElectionBodyId(){
			List<Object> result=userVoterDetailsDAO.getDistinctWardsOfLocalElectionBodyId(83l, 8l, 1l);
			System.out.println(result);
			
		}*/
		
		/*public void testGetAgeWiseDetailsInSelectdCustomWard()
		{
			List<Long> boothids = new ArrayList<Long>();
			boothids.add(122826l);
			boothids.add(122829l);
			boothids.add(122830l);
			boothids.add(122832l);
		
			List<Object[]> values = userVoterDetailsDAO.getAbove60AgeWiseDetailsInSelectdCustomWard(28858l,1l,8l,61l,232l);
			for (Object[] parms : values) {
				System.out.println(parms[0] +":"+ parms[1]);
			}
		}
		*/
		/*public void testGetBoothsInACustomWard()
		{
			List<Long> values = userVoterDetailsDAO.getBoothsInACustomWard(28858l,1l,8l,232l);
			
			for (Long parms : values) {
				System.out.println(parms);
			}
		}
		*/
		/*public void testGetCountAllVotersInACustomWard()
		{
			List<Long> boothids = new ArrayList<Long>();
			boothids.add(122826l);
			boothids.add(122829l);
			boothids.add(122830l);
			boothids.add(122832l);
			List<Object[]> count = userVoterDetailsDAO.getCountDetailsInSelectdCustomWard(boothids,1l,8l);
					for (Object[] objects : count) {
						System.out.println(objects[0] +":"+ objects[1]);
					}
		}
		
		public void testGetcasteForVoter()
		{
			List<Long> voterIds = new ArrayList<Long>();
			voterIds.add(112910l);
			voterIds.add(125300l);
			List<Object[]> values = userVoterDetailsDAO.getcasteForVoter(voterIds,1l);
			for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1]);	
			}
		}*/
		
		/*public void testgetGetHamletOrWardList()
		{
			List<Object[]> values = userVoterDetailsDAO.getHamletOrWardList(1l,181004l,"ward");
			for (Object[] parms : values) {
				System.out.println(parms[0] +":"+ parms[1]);
			}
		}*/
		
		/*public void testGetLocalAreaWiseAgeDetailsForCustomWard()
		{
			List<Object[]> list = userVoterDetailsDAO.getLocalAreaWiseAgeDetailsForCustomWard(309L, 8L, 29153L,1L, "18-22");
			System.out.println(list.size());
			for(Object[] params : list)
			 System.out.println(params[1]+" "+params[2]);
		}*/
		
		/*public void testgetLocalAreaWiseAgeDetails()
		{
			List<Object[]> list = userVoterDetailsDAO.getLocalAreaWiseAgeDetails(309L, 8L, 29153L,1L, 60l, 650l,"customWardLocalArea");
			System.out.println(list.size());
			for(Object[] params : list)
				 System.out.println(params[1]+" "+params[2]);
		}*/
		
		/*public void testgetLocalityByCustomWardId()
		{
			List<String> locality = userVoterDetailsDAO.getLocalityByCustomWardId(207L, 8L, 30638L,1L, "customWardLocalArea");
			System.out.println(locality.size());
			for(String name:locality)
			 System.out.println(name);
		}*/
		
		/*public void testGetWardsForMuncByConsIdAndUserId()
		{
			List<Object[]> list = userVoterDetailsDAO.getWardsForMuncByConsIdAndUserId(232l, 83l, 8l, 1l);
			System.out.println(list.size());
		}*/
		
		/*public void testgetCasteAssignedVotersList()
		{
			List<Object[]> list = userVoterDetailsDAO.getCasteAssignedVotersList(232l, 8l, IConstants.BOOTH);
			System.out.println(list.size());
					
		}*/
		
		/*public void testgetWardWiseTotalVotersCount()
		{
			List<Object[]> list = userVoterDetailsDAO.getWardWiseTotalVotersCount(232l, 8l, 83l, null,1L);
			System.out.println(list.size());
			for(Object[] params : list)
			 System.out.println(params[0]+" "+params[1]+" "+params[2]+" "+params[3]);
		}*/
		
		/*public void testGetWardsBYLocalElectionBodyId()
		{
			List<Object[]> list = userVoterDetailsDAO.getWardsBYLocalElectionBodyId(83l,8l,1l);
			System.out.println(list.size());
		}*/
		
		/*public void testgetHamletIdsListByUserIdAndConstituencyId()
		{
			List<Long> list = userVoterDetailsDAO.getHamletIdsListByUserIdAndConstituencyId(232l,8l, 1l);
			System.out.println(list.size());
			for(Long id:list)
			System.out.println("Hamlet_id --- "+id);
		}*/
		
		/*public void testgetHamletIdsListByMandalIdsList()
		{
			List<Long> mandalIdsList = new ArrayList<Long>(0);
			mandalIdsList.add(844l);
			mandalIdsList.add(836l);
			List<Object[]> list = userVoterDetailsDAO.getHamletIdsListByMandalIdsList(232l,8l, 1l,mandalIdsList,"mandalHamlets");
			System.out.println(list.size());
			if(list != null && list.size() > 0)
			 for(Object[] params:list)
		    	System.out.println(params[0]+" "+params[1]);
		}*/
		
	/*	public void testgetCasteDetailsOfVoterByBoothId()
		{
			List<Long> boothIds = new ArrayList<Long>();
			boothIds.add(122992l);
			boothIds.add(122993l);
			boothIds.add(122993l);
			boothIds.add(122994l);
			boothIds.add(123077l);
			List<Object[]> values = userVoterDetailsDAO.getCasteDetailsOfVoterByBooths(boothIds,8l,1l);
			for (Object[] objects : values) {
				System.out.println(objects[0] +":"+ objects[1] +":"+ objects[2]);
			}
		}*/
	
	/*public void testgetCasteInHamlet(){
	List<Long> panchayatIds = new ArrayList<Long>();
	panchayatIds.add(28666l);
	panchayatIds.add(28667l);
	List<Object[]> values = panchayatDAO.getCasteInHamlet(panchayatIds);
	for (Object[] parms : values) {
		System.out.println(parms[0] +":"+ parms[1]+":"+ parms[2]);
	}
	
}*/
	
	/*public void testgetVoterCountByHamlet(){
		List<Long> panchayatIds = new ArrayList<Long>();
		panchayatIds.add(28666l);
		panchayatIds.add(28667l);
		List<Object[]> values = userVoterDetailsDAO.getVoterCountByHamlet(panchayatIds,8l,1l);
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[2]);
		}
	}*/
	/*public void testForCaste()
	{
		List<Object[]> list = userVoterDetailsDAO.getCaste3();
		List<Object[]> list2 = userVoterDetailsDAO.getPanchayatWiseCasteAssigned();
		
		System.out.println(list.size());
		
		Map<String,List<SelectOptionVO>> map = new LinkedHashMap<String, List<SelectOptionVO>>(0);
		Map<String,Long> map2 = new LinkedHashMap<String,Long>();
		
		for(Object[] params : list2)
		{
			map2.put(params[0].toString(),(Long)params[1]);
		}
		
		for(Object[] params : list)
		{
			List<SelectOptionVO> selist = null;
			if(!map.containsKey(params[0].toString()))
				map.put(params[0].toString(),null);
			
			selist = map.get(params[0].toString());
			if(selist == null)
			{
				selist = new ArrayList<SelectOptionVO>(0);
			}
			if(selist.size() < 6)
			{
				SelectOptionVO vo = new SelectOptionVO();
				vo.setId((Long)params[2]);
				vo.setName(params[1].toString());
				Long casteCount = map2.get(params[0].toString());
				if(casteCount != null)
				{
					vo.setValue((new BigDecimal(vo.getId().doubleValue()*100/casteCount.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
				}
				selist.add(vo);
				map.put(params[0].toString(),selist);
			}
		}
		
		for(Map.Entry<String,List<SelectOptionVO>> entry : map.entrySet())
		{
			System.out.print(entry.getKey()+"\t");
			List<SelectOptionVO> castInf = entry.getValue();
			String str = "";
			for(SelectOptionVO so : castInf)
			{
				str += " "+so.getName()+"("+so.getId()+" - "+so.getValue()+"),";
			}
			str = str.substring(0, str.length()-1);
			System.out.println(str);
		}
			
	}*/
	
	/*public void testgetCasteDetailsOfVoterByBoothId()
	{

		List<Object[]> values = userVoterDetailsDAO.getCasteDetailsOfVoterByLocationId(399l,8l,1l,"panchayat");
		for (Object[] objects : values) {
			System.out.println(objects[0] +"\n"+ objects[1] +"\n"+ objects[2]+"\n"+ objects[3]);
		}
	}*/
	
	/*public void testGetUserVoterDetailsOfAConstituencyForAPublication()
	{
		List<UserVoterDetails> list = userVoterDetailsDAO.getUserVoterDetailsOfAConstituencyForAPublication(221l, 8l, 1l);
		System.out.println(list.size()); 
	}*/
	
	/*public void testgetCasteDetailsOfVoterByBoothIds()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(31804l);
		ids.add(31805l);
		ids.add(31806l);
		ids.add(31807l);
		ids.add(31808l);
		ids.add(31809l);
		ids.add(31810l);
		ids.add(31937l);
		List<Object[]> values = userVoterDetailsDAO.getCasteDetailsOfVoterByBoothIds(ids,8l,1l);
		System.out.println(values.size());
		for (Object[] parms : values) {
			System.out.println(parms[0] +":"+ parms[1] +":"+ parms[2]);
		}
	}*/
	
	
	/*public void testgetYoungVoterAgeDetailsForHamlet()
	{
		List<Long> locationIdsList = new ArrayList<Long>(0);
		locationIdsList.add(1l);
		List<Object[]> list = userVoterDetailsDAO.getYoungVoterAgeDetailsForHamlet(232l, 8l, locationIdsList, 1l, 18l, 22l);
		System.out.println(list.size());
	}*/

	/*public void testGetYoungerVoterDetailsForHamlet()
	{
	  List<Object[]> list = userVoterDetailsDAO.getYoungerVoterDetailsForHamlet(8l, 1l, 1l, "Hamlet", 18l, 25l);
	  System.out.println(list.size());
	  if(list != null)
	  for(Object[] params:list)
	   System.out.println(params[2]+" "+params[3]);
	}*/
	
	/*public void testgetYoungerVoterDetailsForHamlet()
	{
		List<Long> locationIds = new ArrayList<Long>(0);
		locationIds.add(1l);
		locationIds.add(2l);
		List<Object[]> list = userVoterDetailsDAO.getYoungerVoterDetailsForHamletBylocationIdsList(locationIds, 8l, 1l, "hamlet",18l, 22l);
		System.out.println(list.size());
	}*/

	/*public void testgetTotalVoterForHamletBylocationIdsList()
	{
		List<Long> locationIds = new ArrayList<Long>(0);
		locationIds.add(1l);
		locationIds.add(2l);
		List<Object[]> list = userVoterDetailsDAO.getTotalVoterForHamletBylocationIdsList(locationIds, 8l, 1l, "hamlet");
		System.out.println(list.size());
	}*/
	
	/*public void testgetYoungVotersForHamlet()
	{
		List<Object[]> list = userVoterDetailsDAO.getYoungVotersForHamlet(232l, 1l, 8l, 1l, 18l, 22l, "boothHamlets");
		System.out.println(list.size());
	}*/
	
	/*public void testgetTotalYoungVotersForHamlet()
	{
		List<Object[]> list = userVoterDetailsDAO.getTotalYoungVotersForHamlet(232l, 1l, 8l, 1l, "boothHamlets");
		System.out.println(list.size());
	}*/

	/*public void testgetYoungerVotersForLocality()
	{
		List<Object[]> list = userVoterDetailsDAO.getYoungerVotersForLocality(8l, 1l, 1l, "hamlet",18l,22l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetGenderWiseVotersCountForLocality()
	{
		System.out.println(userVoterDetailsDAO.getGenderWiseVotersCountForLocality(232l, 8l, 1l).size());
	}*/
	
	/*public void testgetLocalityIdsForHamletAndBooth()
	{
		List<Object[]> list = userVoterDetailsDAO.getLocalityIdsForHamletAndBooth(232l, 8l, 1l, "hamletLocalities");
		System.out.println(list.size());
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]);
	}*/
	
	/*public void testgetFamiliesCountForLocality()
	{
		List<Object[]> list = userVoterDetailsDAO.getFamiliesCountForLocality(232l, 8l, 1l);
		if(list != null && list.size() > 0)
		 for(Object[] params:list)
		  System.out.println(params[0]+" "+params[1]);
	}*/
	
	/*public void testgetLocalityWiseVoterAgeInfo()
	{
		List<Object[]> list = userVoterDetailsDAO.getLocalityWiseVoterAgeInfo(232l, 8l, 1l);
		System.out.println(list.size());
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]+" "+params[2]+" "+params[3]);
	}*/
	
	/*public void testgetLocalityWiseYoungVotersAgeInfo()
	{
		List<Object[]> list = userVoterDetailsDAO.getLocalityWiseYoungVotersAgeInfo(232l, 8l, 1l, 18l, 22l);
		System.out.println(list.size());
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]+" "+params[2]);
	}*/

	/*public void testgetLocalityWiseFamilyDetails()
	{
		List<Object[]> list = userVoterDetailsDAO.getLocalityWiseFamilyDetails(232l, 8l, 1l);
		System.out.println(list.size());
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]+" "+params[2]);
	}*/
	
	/*public void testgetLocalityWiseCasteDetails()
	{
		List<Object[]> list = userVoterDetailsDAO.getLocalityWiseCasteDetails(232l, 8l, 1l);
		System.out.println(list.size());
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]+" "+params[2]+" "+params[3]+" "+params[4]);
	}*/
	
	/*public void testgetLocalityTotalCastes()
	{
		List<Object[]> list = userVoterDetailsDAO.getLocalityTotalCastes(221l, 8l, 1l);
		System.out.println(list.size());
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]);
	}*/
	
	/*public void testGetCadreCaste()
	{
		List<Long> cadreIdsList = new ArrayList<Long>(0);
		cadreIdsList.add(1l);
		cadreIdsList.add(2l);
		List<Object[]> list = userVoterDetailsDAO.getCadreCaste(cadreIdsList);
		System.out.println(list.size());
		
	}*/
	
	/*public void testGetHamletBoothInfo()
	{
		List<Object[]> list = userVoterDetailsDAO.getHamletBoothInfo(228l,1l);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			int hbIndex = 0;
			StringBuilder strTemp = new StringBuilder();
			for(Object[] params : list)
			{
				try{
				strTemp.append("INSERT INTO hamlet_booth(hamlet_booth_id, hamlet_id, booth_id, publication_date_id) VALUES (");
				strTemp.append(++hbIndex+",");
				strTemp.append(params[0].toString()+",");
				strTemp.append(params[1].toString()+",");
				strTemp.append(params[2].toString()+");\n");
				}catch(Exception e)
				{
					
				}
			}
			
			System.out.println(strTemp.toString());
			
		}
	}*/
	
	/*public void testGetAllLocatiesInAConstituency()
	{
		List<Locality> list = userVoterDetailsDAO.getAllLocatiesInAConstituency(309l, 8l, 1l);
		System.out.println(list.size());
	}*/
	
	/*public void testgetmatchtedRecordsForACaste()
	{
		List<Long> voterIdsList = new ArrayList<Long>(0);
		voterIdsList.add(49l);
		List<Long> list = userVoterDetailsDAO.getMatchtedRecordsForACaste(1l,292l,voterIdsList);
		System.out.println(list.size());
	}*/
	
	/*public void testgetUnmatchtedRecordsForACaste()
	{
		List<Long> voterIdsList = new ArrayList<Long>(0);
		voterIdsList.add(6637843l);
		List<Long> list = userVoterDetailsDAO.getUnmatchtedRecordsForACaste(1l,292l,voterIdsList);
		System.out.println(list.size());
	}*/
	
	public void testUpdateCasteInsertType()
	{
		List<Long> userVoterDetailsIdsList = new ArrayList<Long>(0);
		userVoterDetailsIdsList.add(467369l);
		Integer updated = userVoterDetailsDAO.updateCasteInsertType(userVoterDetailsIdsList, 2l);
		System.out.println(updated);
	}
	
}
