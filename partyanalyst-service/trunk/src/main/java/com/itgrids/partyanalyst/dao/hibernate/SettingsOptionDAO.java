package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISettingsOptionDAO;
import com.itgrids.partyanalyst.model.SettingsOption;

public class SettingsOptionDAO extends GenericDaoHibernate<SettingsOption, Long> implements ISettingsOptionDAO{

	public SettingsOptionDAO() {
		super(SettingsOption.class);
		
	}
	
	public List<SettingsOption> getAllSettingsOptions(){
		
		
		return getHibernateTemplate().find("from SettingsOption");
		
		
	}

}
