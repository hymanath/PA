package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

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
	
	public List<Long> getUserIds(List<Long> userId,List<Long> ids){		
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
	
	public void testGetCountOfAllConnectedPeopleForUser()
	{
		List<Long> senderId = new ArrayList<Long>();
		senderId.add(1L);
		
		
		String userId = userConnectedtoDAO.getCountOfAllConnectedPeopleForUser(senderId);
		
		System.out.println(userId);
	}
	
}
