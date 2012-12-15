package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserPrivacySettingsDAO;
import com.itgrids.partyanalyst.model.UserPrivacySettings;

public class UserPrivacySettingsDAO extends GenericDaoHibernate<UserPrivacySettings, Long> implements IUserPrivacySettingsDAO{

	public UserPrivacySettingsDAO() {
		super(UserPrivacySettings.class);
		
	}
	
	
	public List<UserPrivacySettings> getUserSavedSettings(Long userId){
		
		return getHibernateTemplate().find(" from UserPrivacySettings model where model.user.userId = ? and model.content.contentId = 1",userId);
		
		
	}

}
