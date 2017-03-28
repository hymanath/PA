package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDashboardUserAccessTypeDAO;
import com.itgrids.partyanalyst.model.DashboardUserAccessType;

public class DashboardUserAccessTypeDAO extends GenericDaoHibernate<DashboardUserAccessType, Long> implements IDashboardUserAccessTypeDAO {
	
	public DashboardUserAccessTypeDAO() {
		super(DashboardUserAccessType.class);
	}
	
	public List<Object[]> getUserTypeByUserId(Long userId){
		
		Query query = getSession().createQuery(" select model.userTypeId,model.userType.type from DashboardUserAccessType model where model.user.userId = :userId ");
		
		query.setParameter("userId",userId);
		return query.list();
	}
}
