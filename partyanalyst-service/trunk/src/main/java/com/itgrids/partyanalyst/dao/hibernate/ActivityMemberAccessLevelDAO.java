package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.model.ActivityMemberAccessLevel;

public class ActivityMemberAccessLevelDAO extends GenericDaoHibernate<ActivityMemberAccessLevel,Long> implements IActivityMemberAccessLevelDAO {
	
	public ActivityMemberAccessLevelDAO() {
		super(ActivityMemberAccessLevel.class);
	}
	
	public List<Object[]> getActivityMemberUserAccessLevelAndValues(Long userId){
		
		Query query = getSession().createQuery(" select model.activityMemberLevelId,model.userLevel.level,model.activityLocationValue from ActivityMemberAccessLevel model where model.activityMember.userId = :userId");
		
		query.setParameter("userId",userId);
		return query.list();
	}
	
	public List<Object[]> getLocationsByActivityMemberId(Long activityMemberId){
		
		Query query = getSession().createQuery(" select model.activityMemberLevelId,model.userLevel.level,model.activityLocationValue from ActivityMemberAccessLevel model where model.activityMember.activityMemberId = :activityMemberId");
		
		query.setParameter("activityMemberId",activityMemberId);
		return query.list();
	}
	  public List<Object[]> getLocationLevelAndValuesByActivityMembersId(Long activityMemberId){
		    StringBuilder queryStr = new StringBuilder();
		   queryStr.append("select model.userLevel.userLevelId,model.activityLocationValue from ActivityMemberAccessLevel model where model.isActive = 'Y' and model.activityMember.activityMemberId=:activityMemberId ");
		   Query query = getSession().createQuery(queryStr.toString());
		   query.setParameter("activityMemberId", activityMemberId);
		   return query.list();
	  }
}
