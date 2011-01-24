package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserCountryAccessInfoDAO;
import com.itgrids.partyanalyst.model.UserCountryAccessInfo;

public class UserCountryAccessInfoDAO extends GenericDaoHibernate<UserCountryAccessInfo, Long> implements IUserCountryAccessInfoDAO{

	public UserCountryAccessInfoDAO() {
		super(UserCountryAccessInfo.class);
	}
	
	public List findByUser(Long userGroupId){
		return getHibernateTemplate().find("select model.country.countryId, model.country.countryName " +
				"from UserCountryAccessInfo model where model.user.registrationId = ?", userGroupId);
	}

}
