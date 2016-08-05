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

	public List<Object[]> getAllActivityMembersOfGSAndDistAndMpUserTypes(List<Long> childUserTypeIds){
		
		Query query = getSession().createQuery("" +
			    " select  amat.activityMember.activityMemberId,amat.activityMember.tdpCadreId,amat.activityMember.tdpCadre.firstname," +//2
			    "         amat.userType.userTypeId, amat.userType.type," +//4
			    "         amal.userLevel.userLevelId,amal.userLevel.level,amal.activityLocationValue," +//7
			    "         amat.activityMember.tdpCadre.image " +//8
			    " from    ActivityMemberAccessType amat,ActivityMemberAccessLevel amal " +
			    " where   amal.activityMember.activityMemberId = amat.activityMember.activityMemberId and " +
			     "        amat.userType.userTypeId in (:childUserTypeIds) ");
				query.setParameterList("childUserTypeIds", childUserTypeIds);
				return query.list();
	}
	
}
