package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;

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
		
		public void testgetCasteByVoterId()
		{
			List<Long> voterIDs = new ArrayList<Long>();
			voterIDs.add(459028l);
			voterIDs.add(459029l);
			//voterIDs.add(459030l);
			List<?> anil=	userVoterDetailsDAO.getVotersIdsByHamletId(42l,1l);
			 List<?> hh =              userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(7l,anil);
			 List<Object[]> list = userVoterDetailsDAO.getVotersCountByGenderForLocalAreas(hh);
			System.out.println(list.size());
			for (Object[] objects : list) {
				System.out.println(objects[0]+"---"+objects[1]+"---"+"---"+objects[2]+"--");
				//System.out.println(objects[1]);
				//System.out.println(objects[2]);
			}
	/*	List<?> anil=	userVoterDetailsDAO.getVotersIdsByHamletId(42l,1l);
		 List<?> hh =              userVoterDetailsDAO.getVoterIdsBasedOnVoterIdsAndPublication(7l,anil);
		 List<Object[]> list = userVoterDetailsDAO.getLocalityIdsForUser(42l, 1l,hh);
			 for (Object[] objects : list) {
	           int length=objects.length;
			for(;;)
			{	
			 System.out.println(objects[--length]+"\t");
			 if(length==0)break;
			}
		}*/
		}
		/*public void testGetPartyAndCasteDetails()
		{
			
			List<Object[]> list = userVoterDetailsDAO.getPartyAndCasteDetails(26438l, 1l);
			for (Object[] objects : list) {
				System.out.println(objects[0]);
				System.out.println(objects[1]);
				System.out.println(objects[2]);
				System.out.println(objects[3]);
			}
		}*/
		
		
}
