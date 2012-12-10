package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.IPhoneTypeDAO;

public class PhoneTypeDAOHibernateTest  extends BaseDaoTestCase{
	
	private IPhoneTypeDAO phoneTypeDAO;

	

	public void setPhoneTypeDAO(IPhoneTypeDAO phoneTypeDAO) {
		this.phoneTypeDAO = phoneTypeDAO;
	}

	public void testGetPhoneTypeDAO(){
		
			Object obj=	phoneTypeDAO.getPhoneTypeNameByPhoneTypeId(1l);
			System.out.println(obj.toString());
	
		
	}
	
	
	

}
