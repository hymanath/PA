package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IActivityLevelDAO;
import com.itgrids.partyanalyst.dao.IMobileConnectionTypeDAO;

public class MobileConnectionTypeDAOHibernateTest extends BaseDaoTestCase{
	
	private IMobileConnectionTypeDAO MobileConnectionTypeDAO;

	public IMobileConnectionTypeDAO getMobileConnectionTypeDAO() {
		return MobileConnectionTypeDAO;
	}

	public void setMobileConnectionTypeDAO(
			IMobileConnectionTypeDAO mobileConnectionTypeDAO) {
		MobileConnectionTypeDAO = mobileConnectionTypeDAO;
	}
	public void test(){
		
	}
}
