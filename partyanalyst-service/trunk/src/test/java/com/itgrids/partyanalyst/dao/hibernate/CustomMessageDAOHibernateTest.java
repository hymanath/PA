package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	
	public void testUsersRelation(){	
		
		
		List<Long> usersList = new ArrayList<Long>();
		usersList.add(44l);
		usersList.add(43l);	
		usersList.add(42l);	
		usersList.add(41l);	
		usersList.add(40l);	
		usersList.add(39l);	
		usersList.add(26l);

		List<Long> list = new ArrayList<Long>();
		list.add(39l);
		
		List<Object> detailsList = customMessageDAO.getRelationShipBetweenTheUsers(usersList,39l,IConstants.ALL);	
		for(int i=0;i<detailsList.size();i++){
			Object[] parms = (Object[])detailsList.get(i);
			System.out.println(parms[0]+"\t"+parms[1]);
		}	
		
	}
	
/*	public void testUsersRelationBasedOnType(){	
		List<Long> list = new ArrayList<Long>();
		list.add(39l);
		List<Long> delist = new ArrayList<Long>();
		delist.add(42l);
		delist.add(44l);
		try{
			java.util.Date updatedDate = new java.util.Date();
			String DATE_FORMAT = IConstants.DATE_PATTERN;
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			String strDateNew = sdf.format(updatedDate) ;
			updatedDate = sdf.parse(strDateNew);
			int detailsList = customMessageDAO.updateRelationBetweenUsers(list,delist,3l,updatedDate);
			System.out.println(detailsList);	
			setComplete();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	*/
	
}
