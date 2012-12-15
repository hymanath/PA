package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISettingsOptionDAO;

public class SettingsOptionDAOHibernateTest extends BaseDaoTestCase{
	
	private ISettingsOptionDAO settingsOptionDAO;

	public void setSettingsOptionDAO(ISettingsOptionDAO settingsOptionDAO) {
		this.settingsOptionDAO = settingsOptionDAO;
	}

}
