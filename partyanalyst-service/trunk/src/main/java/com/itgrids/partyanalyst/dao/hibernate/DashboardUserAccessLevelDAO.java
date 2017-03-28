package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDashboardUserAccessLevelDAO;
import com.itgrids.partyanalyst.model.DashboardUserAccessLevel;

public class DashboardUserAccessLevelDAO extends GenericDaoHibernate<DashboardUserAccessLevel, Long> implements IDashboardUserAccessLevelDAO {
	
	public DashboardUserAccessLevelDAO() {
		super(DashboardUserAccessLevel.class);
	}
	
	public List<Object[]> getUserAccessLevelAndValues(Long userId){
		
		Query query = getSession().createQuery(" select model.userLevelId,model.userLevel.level,model.levelValue from DashboardUserAccessLevel model where model.user.userId = :userId ");
		
		query.setParameter("userId",userId);
		return query.list();
	}
	
	
}
