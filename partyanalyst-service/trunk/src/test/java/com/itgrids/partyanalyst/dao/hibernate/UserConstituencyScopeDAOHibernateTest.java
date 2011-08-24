package com.itgrids.partyanalyst.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IUserConstituencyScopeDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserConstituencyScopeDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserConstituencyScopeDAO userConstituencyScopeDAO;
	
	public void setUserConstituencyScopeDAO(
			IUserConstituencyScopeDAO userConstituencyScopeDAO) {
		this.userConstituencyScopeDAO = userConstituencyScopeDAO;
	}

	
	

      @Test
     public void testConstituencyId(){
    	  List annousDetails =  userConstituencyScopeDAO.getConstituencyId(60L);
    	  System.out.println(annousDetails.size());
    	  if(annousDetails.size()>0){
    			Object[] o	= (Object[])(annousDetails.get(0));
    			
    			System.out.println(o[0].toString());
    			System.out.println(o[1].toString());
    			
    			System.out.println(o[2].toString());
    			
    			System.out.println(o[3].toString());
    			}
}
}
