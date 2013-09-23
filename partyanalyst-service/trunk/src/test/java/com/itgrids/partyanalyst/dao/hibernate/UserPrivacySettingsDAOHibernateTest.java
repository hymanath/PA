package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserPrivacySettingsDAO;

public class UserPrivacySettingsDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserPrivacySettingsDAO userPrivacySettingsDAO;

	public void setUserPrivacySettingsDAO(
			IUserPrivacySettingsDAO userPrivacySettingsDAO) {
		this.userPrivacySettingsDAO = userPrivacySettingsDAO;
	}
	
	public void testgetUserPrivicyViewDetails()
	{
		Long value = userPrivacySettingsDAO.getUserPrivicyViewDetails(1l);
		System.out.println(value);
	}

}
