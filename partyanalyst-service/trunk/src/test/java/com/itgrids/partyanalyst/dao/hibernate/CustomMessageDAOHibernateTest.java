package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.CustomMessage;

public class CustomMessageDAOHibernateTest extends BaseDaoTestCase {

	private CustomMessageDAO customMessageDAO;

	public CustomMessageDAO getCustomMessageDAO() {
		return customMessageDAO;
	}

	public void setCustomMessageDAO(CustomMessageDAO customMessageDAO) {
		this.customMessageDAO = customMessageDAO;
	}
	
	public void testUsersRelation(){		
		List<Long> usersList = new ArrayList<Long>();
		usersList.add(5l);
		usersList.add(6l);
		usersList.add(26l);
		usersList.add(28l);
		List<Object> detailsList = customMessageDAO.getRelationShipBetweenTheUsers(usersList,2l);	
		for(int i=0;i<detailsList.size();i++){
			Object[] parms = (Object[])detailsList.get(i);
			System.out.println(parms[0]+"\t"+parms[1]);
		}
		System.out.println(detailsList.size());
		
	}
}
