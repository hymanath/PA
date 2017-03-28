package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityMemberDAO;
import com.itgrids.partyanalyst.model.ActivityMember;

public class ActivityMemberDAO extends GenericDaoHibernate<ActivityMember,Long> implements IActivityMemberDAO {
	
	public ActivityMemberDAO() {
		super(ActivityMember.class);
	}
    
	public List<Object[]> getLoggedInUserBasicDetails(Long userId){
		
		Query query = getSession().createQuery("" +
		" select model.activityMember.activityMemberId," +//0
		"        model.userTypeId,model.userType.type, " +//2
		"        model1.userLevel.userLevelId,model1.userLevel.level,model1.activityLocationValue, " +//5
		"        model.activityMember.tdpCadreId,model.activityMember.memberName " +//7
		" from  ActivityMemberAccessType model , ActivityMemberAccessLevel model1" +
		" where model.activityMember.activityMemberId = model1.activityMember.activityMemberId and model1.isActive='Y' " +
		"       and model.activityMember.user.userId = :userId  ");
		query.setParameter("userId", userId);
		return query.list();
	}
	
	
	public List<Object[]> getActivityMemberDetails(Long activityMemberId){
		
		Query query = getSession().createQuery("" +
		" select model.activityMember.activityMemberId," +//0
		"        model.userTypeId,model.userType.type, " +//2
		"        model1.userLevel.userLevelId,model1.userLevel.level,model1.activityLocationValue, " +//5
		"        model.activityMember.tdpCadreId,model.activityMember.memberName," +//7
		"        model.activityMember.imageUrl,model.userType.shortName " +//9
		" from  ActivityMemberAccessType model , ActivityMemberAccessLevel model1" +
		" where model.activityMember.activityMemberId = model1.activityMember.activityMemberId and model1.isActive='Y' " +
		"       and model.activityMember.activityMemberId = :activityMemberId ");
		query.setParameter("activityMemberId", activityMemberId);
		return query.list();
	}
	
	public Long findActivityMemberIdByUserId(Long userId){
		
		Query query = getSession().createQuery(" select model.activityMemberId from ActivityMember model where model.userId = :userId ");
		query.setParameter("userId",userId);
		return (Long)query.uniqueResult();
	}
	public List<Long> checkForLeader(Long caderId){
		Query query = getSession().createQuery(" select model.activityMemberId from ActivityMember model where model.tdpCadreId = :caderId ");
		query.setParameter("caderId",caderId);
		return query.list();
	}
	
}
