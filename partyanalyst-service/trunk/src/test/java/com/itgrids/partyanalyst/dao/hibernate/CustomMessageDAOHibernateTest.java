package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.CustomMessage;
import com.itgrids.partyanalyst.utils.IConstants;

public class CustomMessageDAOHibernateTest extends BaseDaoTestCase {

	private CustomMessageDAO customMessageDAO;

	public CustomMessageDAO getCustomMessageDAO() {
		return customMessageDAO;
	}

	public void setCustomMessageDAO(CustomMessageDAO customMessageDAO) {
		this.customMessageDAO = customMessageDAO;
	}
	
	/*public void testUsersRelation(){	
		List<Long> list = new ArrayList<Long>();
		list.add(5l);
		
		List<Long> usersList = new ArrayList<Long>();
		usersList.add(2l);
		usersList.add(6l);
		usersList.add(26l);
		usersList.add(28l);
		
		List<CustomMessage> detailsList = customMessageDAO.checkForRelationBetweenUsers(list,usersList);	
		for(CustomMessage result : detailsList){
			System.out.println(result.getCustomMessageId());
		}		
	}*/
	
	public void testUsersRelationBasedOnType(){	
		List<Long> list = new ArrayList<Long>();
		list.add(39l);
		
		List<Object> detailsList = customMessageDAO.getAllMessagesForUser(list,IConstants.PENDING);
		try{
			for(int i=0;i<detailsList.size();i++){
				Object[] parms = (Object[])detailsList.get(i);
				System.out.println(parms[0]+"\t"+parms[1]+"\t"+parms[2]+"\t"+parms[3]);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
}
