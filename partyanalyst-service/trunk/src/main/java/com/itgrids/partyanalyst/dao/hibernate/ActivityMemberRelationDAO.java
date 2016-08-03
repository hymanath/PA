package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityMemberRelationDAO;
import com.itgrids.partyanalyst.model.ActivityMemberRelation;

public class ActivityMemberRelationDAO extends GenericDaoHibernate<ActivityMemberRelation,Long> implements IActivityMemberRelationDAO {
	
	public ActivityMemberRelationDAO() {
		super(ActivityMemberRelation.class);
	}
	
	public List<Object[]> getChildUserTypeMembers(Long parentActivityMemberId,List<Long> childUserTypeIds){
	
	Query query = getSession().createQuery("" +
    " select  rel.childActivityMember.activityMemberId,rel.childActivityMember.tdpCadreId,rel.childActivityMember.tdpCadre.firstname," +//2
    "         amat.userType.userTypeId, amat.userType.type," +//4
    "         amal.userLevel.userLevelId,amal.userLevel.level,amal.activityLocationValue " +//7
    " from   ActivityMemberRelation rel ,ActivityMemberAccessType amat,ActivityMemberAccessLevel amal " +
    " where  rel.childActivityMember.activityMemberId = amat.activityMember.activityMemberId and " +
    "        rel.childActivityMember.activityMemberId = amal.activityMember.activityMemberId and " +
    "        rel.parentMemberId = :parentActivityMemberId and amat.userType.userTypeId in (:childUserTypeIds) ");
	query.setParameter("parentActivityMemberId", parentActivityMemberId);
	query.setParameterList("childUserTypeIds", childUserTypeIds);
	return query.list();
	}
	
	public Long checkChildActivityMembersByParents(List<Long> parentActivityMemberIds){
		
		Query query = getSession().createQuery(" select count(model.activityMemberRelationId) from ActivityMemberRelation model where model.parentMemberId in (:parentActivityMemberIds) ");
		query.setParameterList("parentActivityMemberIds",parentActivityMemberIds);
		return (Long)query.uniqueResult();
	}
	
	
}
