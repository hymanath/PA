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
		
		Query query = getSession().createQuery(" select model.activityMemberLevelId,model.userLevel.level,model.activityLocationValue from ActivityMemberAccessLevel model where model.activityMember.userId = :userId and model.isActive = 'Y' ");
		
		query.setParameter("userId",userId);
		return query.list();
	}
	
	public List<Object[]> getLocationsByActivityMemberId(Long activityMemberId){
		
		Query query = getSession().createQuery(" select model.activityMemberLevelId,model.userLevel.level,model.activityLocationValue from ActivityMemberAccessLevel model where model.activityMember.activityMemberId = :activityMemberId and model.isActive = 'Y'");
		
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
	  public List<Object[]> getMemberIdMemberLvlAndLocationValueByTdpCadre(Long tdpCadreId){
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append(" Select " +
		  				  " AMAL.activityMember.activityMemberId, " +
		  				  " AMAL.activityMemberLevelId, " +
		  				  " AMAL.activityLocationValue, " +
		  				  " AMAL.activityMember.memberName, " +
		  				  " AMAL.userLevel.userLevelId " +
		  				  " from ActivityMemberAccessLevel AMAL " +
		  				  " where AMAL.activityMember.tdpCadreId = :tdpCadreId " +
		  				  " and AMAL.isActive = 'Y' " +
		  				  " order by AMAL.activityMember.activityMemberId");
		  Query query = getSession().createQuery(queryStr.toString());
		  query.setParameter("tdpCadreId", tdpCadreId);
		  return query.list();
		  
	  }
	  
	  public List<Object[]> getLocationLevelAndValuesByActivityMembersIdForOrganization(Long activityMemberId){
		   StringBuilder queryStr = new StringBuilder();
		   queryStr.append("select model.userLevel.userLevelId,model.activityLocationValue from ActivityMemberAccessLevel model where model.isActive = 'Y'" +
		   						" and model.activityMember.activityMemberId=:activityMemberId ");
		   Query query = getSession().createQuery(queryStr.toString());
		   query.setParameter("activityMemberId", activityMemberId);
		   return query.list();
	  }
	  public List<Object[]> getMemberDetailsByActivityMemberId(Long activityMemberId){
			
		  Query query = getSession().createQuery(" select model1.userType.userTypeId," +
		  										 " model1.userType.shortName," +
												 " model.activityMemberLevelId," +
												 " model.activityLocationValue," +
												 " model1.activityMemberId " +
												 " from " +
												 " ActivityMemberAccessLevel model,ActivityMemberAccessType model1 " +
												 " where " +
												 " model.activityMemberId=model1.activityMemberId and " +
												 " model.activityMember.activityMemberId = :activityMemberId " +
												 " and model.isActive = 'Y' and model1.isActive = 'Y'");
			
			query.setParameter("activityMemberId",activityMemberId);
			return query.list();
		}
}
