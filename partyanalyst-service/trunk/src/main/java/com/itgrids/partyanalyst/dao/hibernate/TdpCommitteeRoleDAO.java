package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeRoleDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeRole;

public class TdpCommitteeRoleDAO extends GenericDaoHibernate<TdpCommitteeRole, Long>  implements ITdpCommitteeRoleDAO {
	public TdpCommitteeRoleDAO() {
		super(TdpCommitteeRole.class);
	}
	
	public List<Object[]> getAllCommitteeRoles(Long committeeId){
		Query query = getSession().createQuery("" +
		" select model.tdpCommitteeRoleId, model.tdpRoles.role, model.maxMembers , model.roleType , model.minProposeMembers , model.maxProposeMembers " +
		" from   TdpCommitteeRole model " +
		" where  model.tdpCommittee.tdpCommitteeId =:committeeId " +
		" order  by model.tdpRoles.order");
		query.setParameter("committeeId", committeeId);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllCommitteeRoles(Long committeeId,List<Long> enrollmentIds,Date startDate,Date endDate){
		StringBuilder sb = new StringBuilder();
		sb.append("" +
		" select model.tdpCommitteeRoleId, model.tdpRoles.role, model.maxMembers , model.roleType , model.minProposeMembers , model.maxProposeMembers " +
		" from   TdpCommitteeRole model " +
" where  model.tdpCommittee.tdpCommitteeId =:committeeId ");
		
		if(enrollmentIds != null && enrollmentIds.size() > 0){
			sb.append("  and model.tdpCommitteeEnrollmentId in (:enrollmentIds) ");
		}
		if(startDate != null && endDate != null){
			sb.append( " and (" +
					" (date(model.tdpCommittee.startedDate) between :startDate and :endDate )  OR  (date(model.tdpCommittee.completedDate) between :startDate and :endDate ) " +
					" )" );
			//sb.append(" and date(model.tdpCommittee.startedDate) between :startDate and :endDate");
			}
		sb.append(" order  by model.tdpRoles.order");
		Query query = getSession().createQuery(sb.toString());
			query.setParameter("committeeId", committeeId);
			if(enrollmentIds != null && enrollmentIds.size() > 0){
				query.setParameterList("enrollmentIds", enrollmentIds);
			}
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	
	public List<Object[]> getDetailsForTdpCommitteRoleId(Long roleId){
		Query query = getSession().createQuery(" select model.tdpCommitteeRoleId," +
				" model.tdpCommitteeId," +
				" model.tdpRolesId," +
				" model.maxMembers," +
				" model.updatedTime" +
				" from TdpCommitteeRole model" +
				" where model.tdpCommitteeRoleId =:roleId ");
		
		query.setParameter("roleId", roleId);
		return query.list();
	}
	
	public int updateMaxPosForCommitteeRoleId(Long roleId, Long maxPos, Date updatedTime){
		Query query = getSession().createQuery(" update TdpCommitteeRole model set model.maxMembers = :maxPos, " +
				" model.updatedTime=:updatedTime" +
				" where model.tdpCommitteeRoleId = :roleId ");
		
		query.setParameter("maxPos", maxPos);
		query.setParameter("roleId", roleId);
		query.setDate("updatedTime", updatedTime);
		
		return query.executeUpdate();
	}
	
	public String getCommitteeStatus(Long tdpCommitteeRole){
		Query query = getSession().createQuery(" select distinct model.tdpCommittee.isCommitteeConfirmed from TdpCommitteeRole model  " +
				" where model.tdpCommitteeRoleId = :tdpCommitteeRole ");
		query.setParameter("tdpCommitteeRole", tdpCommitteeRole);
		
		return (String)query.uniqueResult();
	}
	public List<Object[]> getMaxCountsForACommittee(Long committeeId){
		 /*select tdp_committee_role_id,max_members from tdp_committee_role where tdp_committee_id=281;*/
		Query query = getSession().createQuery("select model.tdpCommitteeRoleId,model.maxMembers,model.tdpRoles.role from TdpCommitteeRole model where model.tdpCommittee.tdpCommitteeId =:tdpCommitteeId");
		query.setParameter("tdpCommitteeId", committeeId);
		return query.list();
	}
	
	public List<Object[]> getAllAffiliCommitteeRoles(List<Long> affiliIds){
		//0committeeRoleid,1role name,2max nos
		Query query = getSession().createQuery("select model.tdpCommitteeRoleId,model.tdpRoles.role,model.maxMembers from TdpCommitteeRole model where " +
				" model.tdpCommittee.tdpCommitteeId in(:affiliIds) order by model.tdpRoles.order");
		query.setParameterList("affiliIds", affiliIds);
		return query.list();
	}
	
	public String getTdpCommitteeRoleType(Long tdpCommitteeRoleId){
		Query query = getSession().createQuery("" +
		" select model.roleType " +
		" from   TdpCommitteeRole model " +
		" where  model.tdpCommitteeRoleId = :tdpCommitteeRoleId ");
		query.setParameter("tdpCommitteeRoleId", tdpCommitteeRoleId);
		return (String)query.uniqueResult();
	}
	
	public List<Object[]> getTdpRoles(List<Long> committeeEnrollmntIds,List<Long> committeeLevlIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append( " select distinct model.tdpRoles.tdpRolesId,model.tdpRoles.role from TdpCommitteeRole model  where " );
		
		if(committeeLevlIdsList != null && committeeLevlIdsList.size() > 0){
			sb.append( " model.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:committeeLevlIdsList) ");
		}
		
		if(committeeEnrollmntIds != null && committeeEnrollmntIds.size() >0){
			sb.append( " and model.tdpCommitteeEnrollmentId in (:committeeEnrollmntIds) " );
		}
				sb.append( "  order by model.tdpRoles.order " );
				
				Query query = getSession().createQuery(sb.toString());
				
				if(committeeLevlIdsList != null && committeeLevlIdsList.size() > 0){
					query.setParameterList("committeeLevlIdsList", committeeLevlIdsList);
				}
				if(committeeEnrollmntIds != null && committeeEnrollmntIds.size() > 0){
					query.setParameterList("committeeEnrollmntIds", committeeEnrollmntIds);
				}
				
				return query.list();
		
	}
	
	public List<Object[]> getAllRolesInACommittee(Long tdpCommitteeId){
		Query query = getSession().createQuery("" +
		" select  model.tdpRoles.tdpRolesId , model.tdpRoles.role ,model.tdpCommittee.committeeConfirmRuleId , model.maxMembers " +
		" from    TdpCommitteeRole model " +
		" where   model.tdpCommitteeId = :tdpCommitteeId  " +
		" order by model.tdpRoles.tdpRolesId ");
		query.setParameter("tdpCommitteeId", tdpCommitteeId);
		return query.list();
		
	}
	public List<Object[]> getTotalCommitteesByRole(Long constituencyId,List<Long> levelIds,List<Long> lvelValues,List<Long> basicCommiteIds,List<Long> cmiteEnrlmntYearIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.tdpRoles.tdpRolesId," +
				" sum(model.maxMembers)" +
				" from TdpCommitteeRole model" +
				" where ");
		if(constituencyId != null && constituencyId.longValue() > 0l){
			sb.append(" model.tdpCommittee.userAddress.constituency.constituencyId =:constituencyId");
		}
		if(lvelValues != null && !lvelValues.isEmpty() && lvelValues.size() > 0){
			sb.append(" and model.tdpCommittee.tdpCommitteeLevelValue in (:lvelValues)");
		} 
		if(levelIds != null && !levelIds.isEmpty() && levelIds.size() > 0){
			sb.append(" and model.tdpCommittee.tdpCommitteeLevelId in (:levelIds)");
		}
		if(basicCommiteIds != null && !basicCommiteIds.isEmpty() &&  basicCommiteIds.size() >0){
			sb.append(" and model.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId in (:basicCommiteIds)");
		}
		if(cmiteEnrlmntYearIds != null && !cmiteEnrlmntYearIds.isEmpty() &&  cmiteEnrlmntYearIds.size() >0){
			sb.append(" and model.tdpCommittee.tdpCommitteeEnrollmentId in (:cmiteEnrlmntYearIds)");
		}
		
		sb.append("group by model.tdpRoles.tdpRolesId");
		
		Query query = getSession().createQuery(sb.toString());
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		if(lvelValues != null && !lvelValues.isEmpty() &&  lvelValues.size() > 0)
			query.setParameterList("lvelValues", lvelValues);
		if(levelIds != null && !levelIds.isEmpty() &&  levelIds.size() > 0)
			query.setParameterList("levelIds", levelIds);
		if(basicCommiteIds != null && !basicCommiteIds.isEmpty() &&  basicCommiteIds.size() >0)
			query.setParameterList("basicCommiteIds", basicCommiteIds);
		if(cmiteEnrlmntYearIds != null && !cmiteEnrlmntYearIds.isEmpty() &&  cmiteEnrlmntYearIds.size() >0)
			query.setParameterList("cmiteEnrlmntYearIds", cmiteEnrlmntYearIds);
		return query.list();
	}
}
