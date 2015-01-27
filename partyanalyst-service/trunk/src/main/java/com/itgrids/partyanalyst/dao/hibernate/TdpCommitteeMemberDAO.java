package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeMember;

  public class TdpCommitteeMemberDAO extends GenericDaoHibernate<TdpCommitteeMember, Long>  implements ITdpCommitteeMemberDAO {
	public TdpCommitteeMemberDAO() {
		super(TdpCommitteeMember.class);
	}
	
	public List<Object[]> getRoleWiseAllocatedMembersCount(Set<Long> committeeRoleIds){
		//0 count,1 id
		Query query = getSession().createQuery("select count(*),model.tdpCommitteeRole.tdpCommitteeRoleId from TdpCommitteeMember model where " +
				"model.tdpCommitteeRole.tdpCommitteeRoleId in(:committeeRoleIds) group by model.tdpCommitteeRole.tdpCommitteeRoleId ");
		query.setParameterList("committeeRoleIds", committeeRoleIds);
		
		return query.list();
	}
	
	public List<Object[]> getMembersInfo(Set<Long> committeeRoleIds){
		//0 role,1 image,2name,3membership
		Query query = getSession().createQuery("select model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.image,model.tdpCadre.firstname,model.tdpCadre.memberShipNo " +
				" from TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommitteeRoleId in(:committeeRoleIds) order by model.tdpCommitteeRole.tdpRoles.order ");
		query.setParameterList("committeeRoleIds", committeeRoleIds);
		
		return query.list();
	}
	
	public List<Object[]> getMemberInfo(Long tdpCadreId){

		Query query = getSession().createQuery("select model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId, " +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId, model.tdpCommitteeRole.tdpCommitteeRoleId    " +
				" from TdpCommitteeMember model where model.tdpCadreId =:tdpCadreId ");
		query.setParameter("tdpCadreId", tdpCadreId);
	//	query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
	public List<TdpCommitteeMember> getTdpCommitteeMemberByTdpCadreId(Long tdpCadreId){
		Query query = getSession().createQuery("select model from TdpCommitteeMember model where model.tdpCadreId =:tdpCadreId ");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
	
	public List<Object[]> getTdpCommitteeMemberForTdpCadreIdList(List<Long> tdpCadreIdsList)
	{
		String queryStr = " select distinct model.tdpCadreId ,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name," +
				" model.tdpCommitteeRole.tdpRoles.role, model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId, " +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue " +
				" from TdpCommitteeMember model where model.tdpCadreId in (:tdpCadreIdsList) group by model.tdpCadreId ";
		Query query = getSession().createQuery(queryStr);
		query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
}
