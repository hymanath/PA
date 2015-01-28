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
	public List<Object[]> getStartedCommitteesCountByLocation(String state,List<Long> levelIds){

		StringBuilder str = new StringBuilder();

		str.append("select count(distinct model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId), " +
		" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId " +
		" from TdpCommitteeMember model where ");
		str.append(" model.state= :state ");
		str.append("and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) and model.isActive ='Y' group by " +
		" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("state", state);
		
		query.setParameterList("levelIds", levelIds);
		return query.list();
	}

	public Long getMembersCountByLocation(String state,List<Long> levelIds){

		StringBuilder str = new StringBuilder();

		str.append(" select count(distinct model.tdpCommitteeMemberId) " +
		" from TdpCommitteeMember model where ");
		if(state != null)
		{
		str.append(" model.state= :state ");
		}
		str.append("and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) and model.isActive ='Y' ");

		Query query = getSession().createQuery(str.toString());
		if(state != null)
		{
		query.setParameter("state", state);
		}
		query.setParameterList("levelIds",levelIds);
		return (Long) query.uniqueResult();
	}
	
	
	
	public List<Object[]> getMembersCountInCommitteeByLocation(String state,List<Long> levelIds){
		
		StringBuilder str = new StringBuilder();
		
		str.append("select count(distinct model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId," +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name,model.tdpCommitteeRole.tdpCommitteeRoleId " +				
				" from TdpCommitteeMember model where ");
		if(state != null)
		{
			str.append(" model.state= :state ");
		}
		
		str.append("and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'Y'  and model.isActive = 'Y' " +
				" group by model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId ");		
       			
		Query query = getSession().createQuery(str.toString());
		if(state != null)
		{
			query.setParameter("state", state);
		}
		query.setParameterList("levelIds",levelIds);
        return query.list();
    }
	public List<Object[]> getMembersInfoForRequest(Set<Long> committeeRoleIds){
		//0 role,1 image,2name,3membership
		Query query = getSession().createQuery("select model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.image,model.tdpCadre.firstname,model.tdpCadre.memberShipNo," +
				" model.tdpCadre.tdpCadreId, model.tdpCommitteeRole.tdpCommitteeRoleId " +
				" from TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommitteeRoleId in(:committeeRoleIds) order by model.tdpCommitteeRole.tdpRoles.order ");
		query.setParameterList("committeeRoleIds", committeeRoleIds);
		
		return query.list();
	}
}
