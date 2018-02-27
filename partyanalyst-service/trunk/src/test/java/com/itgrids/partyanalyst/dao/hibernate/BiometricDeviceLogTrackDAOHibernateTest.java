package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBiometricDeviceLogTrackDAO;

public class BiometricDeviceLogTrackDAOHibernateTest extends BaseDaoTestCase{

	private IBiometricDeviceLogTrackDAO biometricDeviceLogTrackDAO;

	public void setBiometricDeviceLogTrackDAO(
			IBiometricDeviceLogTrackDAO biometricDeviceLogTrackDAO) {
		this.biometricDeviceLogTrackDAO = biometricDeviceLogTrackDAO;
	}
	
	public void test()
	{
		biometricDeviceLogTrackDAO.getAll();
	}
}
