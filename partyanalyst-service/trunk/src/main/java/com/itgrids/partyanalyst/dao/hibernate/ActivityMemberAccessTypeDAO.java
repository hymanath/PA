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
		
		Query query = getSession().createQuery("select model.userTypeId,model.userType.type,model.activityMember.activityMemberId from ActivityMemberAccessType model " +
											  " where  model.activityMember.userId = :userId and model.isActive = 'Y' ");
		query.setParameter("userId",userId);
		return query.list();
	}

	public List<Object[]> getAllActivityMembersOfGSAndDistAndMpUserTypes(List<Long> childUserTypeIds){
		
		Query query = getSession().createQuery("" +
			    " select  amat.activityMember.activityMemberId,amat.activityMember.tdpCadreId,amat.activityMember.memberName," +//2
			    "         amat.userType.userTypeId, amat.userType.type," +//4
			    "         amal.userLevel.userLevelId,amal.userLevel.level,amal.activityLocationValue," +//7
			    "         amat.activityMember.imageUrl,amat.userType.shortName " +//9
			    " from    ActivityMemberAccessType amat,ActivityMemberAccessLevel amal " +
			    " where   amal.activityMember.activityMemberId = amat.activityMember.activityMemberId and " +
			     "        amat.userType.userTypeId in (:childUserTypeIds) and " +
			     "        amat.isActive='Y' and amal.isActive='Y' ");
				query.setParameterList("childUserTypeIds", childUserTypeIds);
				return query.list();
	}
	
	public Object[] getUserAccessTypeAndActivityMemberIdByUserId(Long userId){
		Query query = getSession().createQuery("select model.activityMember.activityMemberId,model.userTypeId,model.userType.type " +
				                               "from ActivityMemberAccessType model " +
											  " where  model.activityMember.userId = :userId and " +
											  "        model.isActive = 'Y' ");
		query.setParameter("userId",userId);
		return (Object[])query.uniqueResult();
	}
	public List<Object[]> getDesignation(List<Long> activityMemberIdList){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select AMAT.activityMember.activityMemberId, " +
						" AMAT.userType.userTypeId, " +
						" AMAT.userType.type " +
						" from ActivityMemberAccessType AMAT " +
						" where " +
						" AMAT.activityMember.activityMemberId in (:activityMemberIdList) and " +
						" AMAT.isActive = 'Y' ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("activityMemberIdList", activityMemberIdList);
		return query.list();
	}
	  public List<Object[]> getMemberIdMemberLvlAndLocationValueByTdpCadre(Long tdpCadreId){
		  StringBuilder queryStr = new StringBuilder();
		  Query query = getSession().createQuery("" +
				    " select  amat.activityMember.activityMemberId,amat.activityMember.memberName," +//2
				    "         amat.userType.userTypeId, amat.userType.type," +//4
				    "         amal.userLevel.userLevelId,amal.userLevel.level,amal.activityLocationValue " +//7
				    " from    ActivityMemberAccessType amat,ActivityMemberAccessLevel amal " +
				    " where   amal.activityMember.activityMemberId = amat.activityMember.activityMemberId and " +
				    "        amat.activityMember.tdpCadreId in (:tdpCadreId) and " +
				    "        amat.isActive='Y' and amal.isActive='Y' ");
					query.setParameter("tdpCadreId", tdpCadreId);
					return query.list();
		  
	  }
}