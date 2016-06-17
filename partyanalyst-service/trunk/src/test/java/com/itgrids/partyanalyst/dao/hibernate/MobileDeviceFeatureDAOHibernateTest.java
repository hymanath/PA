package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMobileDeviceFeatureDAO;

public class MobileDeviceFeatureDAOHibernateTest extends BaseDaoTestCase{
	
	private IMobileDeviceFeatureDAO		MobileDeviceFeatureDAO;

	public IMobileDeviceFeatureDAO getMobileDeviceFeatureDAO() {
		return MobileDeviceFeatureDAO;
	}

	public void setMobileDeviceFeatureDAO(
			IMobileDeviceFeatureDAO mobileDeviceFeatureDAO) {
		MobileDeviceFeatureDAO = mobileDeviceFeatureDAO;
	}
	public void test()
	{}
}
