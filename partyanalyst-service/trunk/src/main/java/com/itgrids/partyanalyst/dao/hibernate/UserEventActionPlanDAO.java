package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserEventActionPlanDAO;
import com.itgrids.partyanalyst.model.UserEventActionPlan;

public class UserEventActionPlanDAO extends GenericDaoHibernate<UserEventActionPlan, Long> implements IUserEventActionPlanDAO {

	public UserEventActionPlanDAO() {
		super(UserEventActionPlan.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<UserEventActionPlan> findByUserEventsId(Long userEventsId) {
		
		return getHibernateTemplate().find("from UserEventActionPlan model where model.userEvents.userEventsId = ?", userEventsId) ;
	}

	
	
}
