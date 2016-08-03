package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessTypeDAO;
import com.itgrids.partyanalyst.model.ActivityMemberAccessType;

public class ActivityMemberAccessTypeDAO extends GenericDaoHibernate<ActivityMemberAccessType,Long> implements IActivityMemberAccessTypeDAO {
	
	public ActivityMemberAccessTypeDAO() {
		super(ActivityMemberAccessType.class);
	}
	
	public List<Object[]> getActivityMemberUserAccessTypeByUserId(Long userId){
		
		Query query = getSession().createQuery("select model.userTypeId,model.userType.type from ActivityMemberAccessType model " +
											  "where  model.activityMember.userId = :userId");
		query.setParameter("userId",userId);
		return query.list();
	}

}
