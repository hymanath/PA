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
    " select  rel.childActivityMember.activityMemberId,rel.childActivityMember.tdpCadreId,rel.childActivityMember.memberName," +//2
    "         amat.userType.userTypeId, amat.userType.type," +//4
    "         amal.userLevel.userLevelId,amal.userLevel.level,amal.activityLocationValue," +//7
    "         rel.childActivityMember.imageUrl,amat.userType.shortName " +//9
    " from   ActivityMemberRelation rel ,ActivityMemberAccessType amat,ActivityMemberAccessLevel amal " +
    " where  rel.childActivityMember.activityMemberId = amat.activityMember.activityMemberId and " +
    "        rel.childActivityMember.activityMemberId = amal.activityMember.activityMemberId and " +
    "        rel.parentMemberId = :parentActivityMemberId and amat.userType.userTypeId in (:childUserTypeIds) and " +
    "        rel.isActive = 'Y' and amat.isActive = 'Y' and amal.isActive = 'Y' ");
	query.setParameter("parentActivityMemberId", parentActivityMemberId);
	query.setParameterList("childUserTypeIds", childUserTypeIds);
	return query.list();
	}
	
	public List<Object[]> checkChildActivityMembersByParents(List<Long> parentActivityMemberIds){
		
		Query query = getSession().createQuery("" +
		" select count(model.activityMemberRelationId) " +
		" from   ActivityMemberRelation model" +
		" where  model.parentMemberId in (:parentActivityMemberIds) and model.isActive ='Y' ");
		query.setParameterList("parentActivityMemberIds",parentActivityMemberIds);
		return query.list();
	}
	
	public List<Object[]> getAllChildUserTypeMembersAndParentUserTypeMembers(){
		
		Query query = getSession().createQuery("" +
	    " select   rel.parentActivityMember.activityMemberId," +//0
	    "          rel.childActivityMember.activityMemberId,rel.childActivityMember.tdpCadreId,rel.childActivityMember.memberName," +//3
	    "          amat.userType.userTypeId, amat.userType.type," +//5
	    "          amal.userLevel.userLevelId,amal.userLevel.level,amal.activityLocationValue," +//8
	    
	    "          rel.childActivityMember.imageUrl,amat.userType.shortName " +//10
	    " from     ActivityMemberRelation rel ,ActivityMemberAccessType amat,ActivityMemberAccessLevel amal " +
	    " where    rel.childActivityMember.activityMemberId = amat.activityMember.activityMemberId and " +
	    "          rel.childActivityMember.activityMemberId = amal.activityMember.activityMemberId and " +
	    "          rel.isActive = 'Y' and amat.isActive = 'Y' and amal.isActive = 'Y' " +
	    " order by amat.userType.orderNo ");
		return query.list();
	}
	
}
