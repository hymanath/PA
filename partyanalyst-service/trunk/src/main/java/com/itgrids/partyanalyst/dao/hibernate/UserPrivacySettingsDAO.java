package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserPrivacySettingsDAO;
import com.itgrids.partyanalyst.model.UserPrivacySettings;

public class UserPrivacySettingsDAO extends GenericDaoHibernate<UserPrivacySettings, Long> implements IUserPrivacySettingsDAO{

	public UserPrivacySettingsDAO() {
		super(UserPrivacySettings.class);
		
	}

}
