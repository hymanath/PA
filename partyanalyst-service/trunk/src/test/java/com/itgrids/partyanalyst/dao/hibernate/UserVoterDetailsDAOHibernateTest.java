package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.utils.IConstants;

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

		public void testgetAgeDataForBoothByHamlets()
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
			}
	
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
		
	/*	public void testgetDistinctWardsOfLocalElectionBodyId(){
			List<Object> result=userVoterDetailsDAO.getDistinctWardsOfLocalElectionBodyId(83l, 8l, 1l);
			System.out.println(result);
		}
		
		public void testGetAgeWiseDetailsInSelectdCustomWard()
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
}
