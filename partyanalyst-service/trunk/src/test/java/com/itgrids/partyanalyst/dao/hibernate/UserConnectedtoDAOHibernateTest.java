package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.utils.IConstants;

public class UserConnectedtoDAOHibernateTest extends BaseDaoTestCase {

	private UserConnectedtoDAO userConnectedtoDAO;

	public UserConnectedtoDAO getUserConnectedtoDAO() {
		return userConnectedtoDAO;
	}

	public void setUserConnectedtoDAO(UserConnectedtoDAO userConnectedtoDAO) {
		this.userConnectedtoDAO = userConnectedtoDAO;
	}
	
	/*public void test(){
		
			
		List<Long> originalList = new ArrayList<Long>();			
		originalList.add(2l);
		List<Long> newList = new ArrayList<Long>(originalList);
		List<Long> tempIds = new ArrayList<Long>();
		List<Long> unKnownPeople = new ArrayList<Long>();
		for(int i=0;i<3;i++){
			tempIds =  getUserIds(newList,originalList);
			System.out.println("1");
			if(i!=1){
				unKnownPeople.addAll(tempIds);
			}			
			newList.clear();
			newList.addAll(tempIds);	
			originalList.addAll(tempIds);
			System.out.println(newList);
		}		
		System.out.println("final--->"+unKnownPeople);
	}
	*/
	/*public List<Long> testgetUserIds(List<Long> userId,List<Long> ids){		
		Set<Long> setOfUserIds = new HashSet<Long>(0);
		List<Long> listOfUserIds = new ArrayList<Long>(0);
		try{
			List<Object> connectedPeopleIds = userConnectedtoDAO.getAllConnectedPeopleForUser(userId);
			for(int i=0;i<connectedPeopleIds.size();i++){
				Object[] parms = (Object[])connectedPeopleIds.get(i);
				setOfUserIds.add(new Long(parms[0].toString()));
				setOfUserIds.add(new Long(parms[1].toString()));
			}
			int c = 0/0;
			setOfUserIds.removeAll(userId);
			setOfUserIds.removeAll(ids);
			listOfUserIds.addAll(setOfUserIds);

		}catch(Exception e){
			e.printStackTrace();
		}
		return listOfUserIds;
	}*/
	/*public void testgetUSerIds()
	{
		List<Long> details = new ArrayList<Long>();
		details.add(411l);
	 List<Object[]> list = userConnectedtoDAO.getAllConnectedPeopleForUser(details);
	 for(int i=0;i<list.size();i++)
	 {
		 System.out.println(list.get(i));
	 }
	
	}*/
	
	
/*public void testgetAllConnectedPeopleForFreeUser()
	{
		List<Long> senderId = new ArrayList<Long>();
		senderId.add(1L);
		
		
		List<Object[]> list = userConnectedtoDAO.getAllConnectedPeopleForFreeUser(10l);
		
		for(Object[] params : list){
			System.out.println(params[1].toString());
		}
	}*/


/*public void testgetAllConnectedPeoplesForFreeUser()
{
	List<Object[]> list = userConnectedtoDAO.getAllConnectedPeoplesForFreeUser(10l);
	for(Object[] params : list)
	{
		System.out.println(params[1].toString());
	}
}
	
	public void testGetConnectedMembersCountForAFreeUser()
	{
		System.out.println(userConnectedtoDAO.getConnectedMembersCountForAFreeUser(411l));
	}*/
	
	public void testGetConnectedUsersCountForAUserInAFilterView()
	{
		List<Long> locationIds = new ArrayList<Long>(0);
		locationIds.add(323l);
		System.out.println(userConnectedtoDAO.getConnectedUsersCountForAUserInAFilterView(411l,locationIds,IConstants.CONSTITUENCY_LEVEL,"pr"));
	}
	
	
}
