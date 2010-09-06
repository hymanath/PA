package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserStateAccessInfoDAO;
import com.itgrids.partyanalyst.model.UserStateAccessInfo;

public class UserStateAccessInfoDAO extends GenericDaoHibernate<UserStateAccessInfo, Long> implements IUserStateAccessInfoDAO{

	public UserStateAccessInfoDAO() {
		super(UserStateAccessInfo.class);
	}
	
	public List findByUser(Long userId){
		return getHibernateTemplate().find("select model.state.stateId, model.state.stateName " +
				"from UserStateAccessInfo model where model.user.registrationId = ?", userId);
	}

}
