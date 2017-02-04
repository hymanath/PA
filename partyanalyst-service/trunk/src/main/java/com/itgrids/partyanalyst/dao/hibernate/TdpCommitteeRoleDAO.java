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
}
