package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMobileNetworkProviderDAO;
import com.itgrids.partyanalyst.dao.impl.IActivityAttendanceDAO;

public class MobileNetworkTypeDAOHibernateTest extends BaseDaoTestCase{

	private IMobileNetworkProviderDAO MobileNetworkTypeDAO;

	public IMobileNetworkProviderDAO getMobileNetworkTypeDAO() {
		return MobileNetworkTypeDAO;
	}

	public void setMobileNetworkTypeDAO(
			IMobileNetworkProviderDAO mobileNetworkTypeDAO) {
		MobileNetworkTypeDAO = mobileNetworkTypeDAO;
	}

	public void test()
	{
	}
}
