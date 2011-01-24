package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.model.UserConstituencyAccessInfo;

public class UserConstituencyAccessInfoDAO extends GenericDaoHibernate< UserConstituencyAccessInfo, Long> implements  IUserConstituencyAccessInfoDAO{

	public UserConstituencyAccessInfoDAO() {
		super(UserConstituencyAccessInfo.class);
	}
	
	public List findByUser(Long userId){
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name, " +
				"model.constituency.electionScope.electionType.electionType " +
				"from UserConstituencyAccessInfo model where model.user.registrationId = ?", userId);
	}
}
