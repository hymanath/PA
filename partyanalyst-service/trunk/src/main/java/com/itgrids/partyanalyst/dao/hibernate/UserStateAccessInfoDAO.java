package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

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
	
	public Integer deleteAllStateAccess(Long userId) {		
		StringBuilder query = new StringBuilder();
		query.append(" delete from UserStateAccessInfo model ");
		query.append(" where model.user.registrationId = ? ");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, userId);
		return queryObject.executeUpdate();		
	}
}
