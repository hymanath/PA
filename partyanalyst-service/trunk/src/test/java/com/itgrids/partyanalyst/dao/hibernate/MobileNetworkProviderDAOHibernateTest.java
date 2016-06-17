
package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMobileNetworkProviderDAO;

public class MobileNetworkProviderDAOHibernateTest extends BaseDaoTestCase{
	
	private IMobileNetworkProviderDAO MobileNetworkProviderDAO;

	public IMobileNetworkProviderDAO getMobileNetworkProviderDAO() {
		return MobileNetworkProviderDAO;
	}
	public void setMobileNetworkProviderDAO(
			IMobileNetworkProviderDAO mobileNetworkProviderDAO) {
		MobileNetworkProviderDAO = mobileNetworkProviderDAO;
	}
	public void test(){
		
	}
}
