package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISettingsOptionDAO;
import com.itgrids.partyanalyst.model.SettingsOption;

public class SettingsOptionDAO extends GenericDaoHibernate<SettingsOption, Long> implements ISettingsOptionDAO{

	public SettingsOptionDAO() {
		super(SettingsOption.class);
		
	}

}
