package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserPrivacySettings;

public interface IUserPrivacySettingsDAO extends GenericDao<UserPrivacySettings, Long>{
	
	public List<UserPrivacySettings> getUserSavedSettings(Long userId);
	
	public Long getUserPrivicyViewDetails(Long userId);

}
