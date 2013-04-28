package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.model.Voter;

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
			
			  List<?> anil=	userVoterDetailsDAO.getVotersIdsByHamletId(42l,1l);
			 List<?> hh =              userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(7l,anil);
			 List<Object[]> list = userVoterDetailsDAO.getVotersCountByGenderForLocalAreas(hh);
			System.out.println(list.size());
			for (Object[] objects : list) {
				System.out.println(objects[0]+"---"+objects[1]+"---"+"---"+objects[2]+"--");
				//System.out.println(objects[1]);
				//System.out.println(objects[2]);
			}
		List<?> anil=	userVoterDetailsDAO.getVotersIdsByHamletId(42l,1l);
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
				List<Object[]> voters = userVoterDetailsDAO.getAgeDataForBoothByHamlets(1l,8l,123020l,"boothHamlets",IConstants.MALE,IConstants.FEMALE,18l,25l,26l,35l,36l,45l,46l,60l);
				System.out.println(voters.size());
				System.out.println(voters);
				for (Object[] objects : voters) {
				System.out.println(objects[0]);
				System.out.println(objects[1]);	
				System.out.println(objects[2]);
				}
			}
		*/
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
		
		public void testGetBoothIdsByCustomWardId()
		{
			List<Long> list = userVoterDetailsDAO.getBoothIdsByCustomWardId(28858l,232l,8l);
			System.out.println(list.size());
		}
}
