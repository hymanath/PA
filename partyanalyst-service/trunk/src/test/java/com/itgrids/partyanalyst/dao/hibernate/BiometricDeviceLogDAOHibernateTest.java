package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBiometricDeviceLogDAO;

public class BiometricDeviceLogDAOHibernateTest extends BaseDaoTestCase{

	private IBiometricDeviceLogDAO biometricDeviceLogDAO;

	public void setBiometricDeviceLogDAO(
			IBiometricDeviceLogDAO biometricDeviceLogDAO) {
		this.biometricDeviceLogDAO = biometricDeviceLogDAO;
	}
	
	/*public void test()
	{
		biometricDeviceLogDAO.getAll();
	}*/
	
	public void testGetMaxLogIdForAMonth()
	{
		Date date = new Date();
		//Integer max = biometricDeviceLogDAO.getMaxLogIdForAMonth(2018,4);
		//System.out.println(max);
		System.out.println(1900+date.getYear()+"-"+(date.getMonth()+1));
	}
}
