package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
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
				"model.tdpCommitteeRole.tdpCommitteeRoleId in(:committeeRoleIds) and model.isActive ='Y' group by model.tdpCommitteeRole.tdpCommitteeRoleId ");
		query.setParameterList("committeeRoleIds", committeeRoleIds);
		
		return query.list();
	}
	
	public List<Object[]> getMembersInfo(Set<Long> committeeRoleIds){
		//0 role,1 image,2name,3membership
		Query query = getSession().createQuery("select model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.image,model.tdpCadre.firstname,model.tdpCadre.memberShipNo " +
				" from TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommitteeRoleId in(:committeeRoleIds)  and model.isActive ='Y'  order by model.tdpCommitteeRole.tdpRoles.order ");
		query.setParameterList("committeeRoleIds", committeeRoleIds);
		
		return query.list();
	}
	
	public List<Object[]> getMemberInfo(Long tdpCadreId){

		Query query = getSession().createQuery("select model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId, " +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId, model.tdpCommitteeRole.tdpCommitteeRoleId    " +
				" from TdpCommitteeMember model where model.tdpCadreId =:tdpCadreId and model.isActive ='Y'");
		query.setParameter("tdpCadreId", tdpCadreId);
	//	query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
	public List<TdpCommitteeMember> getTdpCommitteeMemberByTdpCadreId(Long tdpCadreId){
		Query query = getSession().createQuery("select model from TdpCommitteeMember model where model.tdpCadreId =:tdpCadreId  and model.isActive ='Y'");
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
	public List<Object[]> getStartedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate){

		StringBuilder str = new StringBuilder();

		str.append("select count(distinct model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId), " +
		" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId " +
		" from TdpCommitteeMember model where ");
		str.append(" model.tdpCommitteeRole.tdpCommittee.state= :state ");
		str.append("and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) and model.isActive ='Y' and " +
				" model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'N' and date(model.tdpCommitteeRole.tdpCommittee.startedDate)>=:startDate and date(model.tdpCommitteeRole.tdpCommittee.startedDate)<=:endDate ) group by " +
		" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("state", state);
		query.setParameterList("levelIds", levelIds);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.list();
	}

	public Long getMembersCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate){

		StringBuilder str = new StringBuilder();

		str.append(" select count(distinct model.tdpCommitteeMemberId) " +
		" from TdpCommitteeMember model where ");
		if(state != null)
		{
		str.append(" model.tdpCommitteeRole.tdpCommittee.state= :state ");
		}
		if(startDate !=null && endDate !=null){
			
			str.append(" and ( date(model.insertedTime)>=:startDate and date(model.insertedTime)<=:endDate ) ");
		}
		str.append("and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) and model.isActive ='Y' ");

		Query query = getSession().createQuery(str.toString());
		if(state != null)
		{
		query.setParameter("state", state);
		}
		if(startDate !=null && endDate !=null){
		 query.setParameter("startDate", startDate);
		 query.setParameter("endDate", endDate);
		}
		query.setParameterList("levelIds",levelIds);
		return (Long) query.uniqueResult();
	}
	
	
	
	public List<Object[]> getMembersInfoForRequest(Set<Long> committeeRoleIds){
		//0 role,1 image,2name,3membership
		Query query = getSession().createQuery("select model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.image,model.tdpCadre.firstname,model.tdpCadre.memberShipNo," +
				" model.tdpCadre.tdpCadreId, model.tdpCommitteeRole.tdpCommitteeRoleId " +
				" from TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommitteeRoleId in(:committeeRoleIds) order by model.tdpCommitteeRole.tdpRoles.order ");
		query.setParameterList("committeeRoleIds", committeeRoleIds);
		
		return query.list();
	}
	public List<Object[]> getComitteeMembersByCommiteTypeAndLocation(List<Long> levelIds,List<Long> locationVals,Long committeeTypeId,String status)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId),model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue" +
				" from TdpCommitteeMember model" +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in(:levelIds)  and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in(:locationVals)" +
				" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId and model.isActive = 'Y'");
		if(status.equalsIgnoreCase("Conform"))
		str.append(" and model.tdpCommitteeRole.tdpCommittee.startedDate is not null and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'Y' and model.tdpCommitteeRole.tdpCommittee.completedDate is not null ");
		else if(status.equalsIgnoreCase("Started"))
		str.append(" and model.tdpCommitteeRole.tdpCommittee.startedDate is not null  and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'N' and model.tdpCommitteeRole.tdpCommittee.completedDate is null ");
		str.append(" group by model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("levelIds", levelIds);
		query.setParameterList("locationVals", locationVals);
		query.setParameter("committeeTypeId", committeeTypeId);
		return query.list();
			
	}
	public List<Object[]> getComitteeMembersInfoByCommiteTypeAndLocation(Long levelId,Long locationVal,Long committeeTypeId,String status)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCommitteeRole.tdpRoles.tdpRolesId,model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname,model.tdpCadre.image,model.tdpCadre.memberShipNo,model.tdpCommitteeMemberId,model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed" +
				" from TdpCommitteeMember model" +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  and model.isActive = 'Y' and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationVal" +
				" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId ");
		if(status.equalsIgnoreCase("Conform"))
			str.append(" and model.tdpCommitteeRole.tdpCommittee.startedDate is not null and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'Y' and model.tdpCommitteeRole.tdpCommittee.completedDate is not null ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("locationVal", locationVal);
		query.setParameter("committeeTypeId", committeeTypeId);
		return query.list();
			
	}
	
	public List<Long> getTdpCommitteIds(Long levelId,Long locationVal,Long committeeTypeId)
	{
		Query query = getSession().createQuery("select distinct model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId from TdpCommitteeMember model" +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationVal" +
				" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId ");
		query.setParameter("levelId", levelId);
		query.setParameter("locationVal", locationVal);
		query.setParameter("committeeTypeId", committeeTypeId);
		return query.list();
			
	}
	public Integer updateTdpComitte(List<Long> tdpCommitteeIds)
	{
		Query query = getSession().createQuery("update TdpCommittee model set model.isCommitteeConfirmed = 'Y' where model.tdpCommitteeId in(:tdpCommitteeIds) ");
		query.setParameterList("tdpCommitteeIds", tdpCommitteeIds);
		
		return query.executeUpdate();	
	}
	
	public Integer deleteCadreRole(Long Id)
	{
		Query query = getSession().createQuery("update TdpCommitteeMember model set model.isActive = 'N' where model.tdpCommitteeMemberId =:Id ");
		query.setParameter("Id", Id);
		return query.executeUpdate();	
	}
	
	public List<Object[]> getStartedCommitteesCountInALocation(Long constituencyId){
		//0count ,1 basic committeeId,2basic committee name,3committeeType
		Query query = getSession().createQuery("select count(distinct model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId),model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId," +
				"model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from TdpCommitteeMember model " +
				" where  model.tdpCommitteeRole.tdpCommittee.constituency.constituencyId=:constituencyId and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in(6,8) and model.isActive ='Y' group by " +
				"model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId ");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	
	public List<Object[]> getLocationWiseStartedCount(List<Long> locationIds,Long locationTypeId){
		Query query = getSession().createQuery("select TB.tdpBasicCommitteeId,TB.name,count(*) from TdpCommitteeMember TM, " +
				"TdpCommitteeRole TR, TdpCommittee TC, TdpBasicCommittee TB where TM.tdpCommitteeRoleId = TR.tdpCommitteeRoleId " +
				"and TR.tdpCommitteeId = TC.tdpCommitteeId and TB.tdpBasicCommitteeId = TC.tdpBasicCommitteeId and " +
				"TC.tdpCommitteeLevelId = :locationTypeId and TC.tdpCommitteeLevelValue in (:locationIds) and TM.isActive = 'Y' group by TB.tdpBasicCommitteeId ");
		query.setParameterList("locationIds", locationIds);
		query.setParameter("locationTypeId", locationTypeId);
		return query.list();
	}
	
	public List<Object[]> getVillageStartedCount(Long constituencyId){
		Query query = getSession().createQuery("select TB.tdpBasicCommitteeId,TB.name,count(*) from TdpCommitteeMember TM, " +
				"TdpCommitteeRole TR, TdpCommittee TC, TdpBasicCommittee TB where TM.tdpCommitteeRoleId = TR.tdpCommitteeRoleId " +
				"and TR.tdpCommitteeId = TC.tdpCommitteeId and TB.tdpBasicCommitteeId = TC.tdpBasicCommitteeId and " +
				"TC.tdpCommitteeLevelId in (6,8) and TC.constituency.constituencyId = :constituencyId and TM.isActive = 'Y' group by TB.tdpBasicCommitteeId ");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	/*public List<Object[]> getMandalTotalCommittees(Long constituencyId,List mandalIds){
		Query query = getSession().createQuery("select TB.tdpBasicCommitteeId,TB.name,count(*) from TdpCommitteeMember TM, " +
				"TdpCommitteeRole TR, TdpCommittee TC, TdpBasicCommittee TB where TM.tdpCommitteeRoleId = TR.tdpCommitteeRoleId " +
				"and TR.tdpCommitteeId = TC.tdpCommitteeId and TB.tdpBasicCommitteeId = TC.tdpBasicCommitteeId and " +
				"TC.tdpCommitteeLevelId = 5 and TC.tdpCommitteeLevelValue in (:ids) group by TB.tdpBasicCommitteeId ");
		query.setParameterList("ids", mandalIds);
		return query.list();
	}
	
	public List<Object[]> getMuncipalTotalCommittees(Long constituencyId,List muncipalIds){
		Query query = getSession().createQuery("select TB.tdpBasicCommitteeId,TB.name,count(*) from TdpCommitteeMember TM, " +
				"TdpCommitteeRole TR, TdpCommittee TC, TdpBasicCommittee TB where TM.tdpCommitteeRoleId = TR.tdpCommitteeRoleId " +
				"and TR.tdpCommitteeId = TC.tdpCommitteeId and TB.tdpBasicCommitteeId = TC.tdpBasicCommitteeId and " +
				"TC.tdpCommitteeLevelId = 7 and TC.tdpCommitteeLevelValue in (:ids) group by TB.tdpBasicCommitteeId ");
		query.setParameterList("ids", muncipalIds);
		return query.list();
	}
	
	public List<Object[]> getDivisonTotalCommittees(Long constituencyId,List divisionIds){
		Query query = getSession().createQuery("select TB.tdpBasicCommitteeId,TB.name,count(*) from TdpCommitteeMember TM, " +
				"TdpCommitteeRole TR, TdpCommittee TC, TdpBasicCommittee TB where TM.tdpCommitteeRoleId = TR.tdpCommitteeRoleId " +
				"and TR.tdpCommitteeId = TC.tdpCommitteeId and TB.tdpBasicCommitteeId = TC.tdpBasicCommitteeId and " +
				"TC.tdpCommitteeLevelId = 9 and TC.tdpCommitteeLevelValue in (:ids) group by TB.tdpBasicCommitteeId ");
		query.setParameterList("ids", divisionIds);
		return query.list();
	}*/
	
	public List<Object[]> basicCommitteeDetails(){
		Query query = getSession().createQuery("select model.tdpBasicCommitteeId,model.name from TdpBasicCommittee model");
		return query.list();
	}
	
	
	public List<Object[]> getCommitteStatusAndId(Long tdpCommitteMemberId)
	{
		Query query = getSession().createQuery("select model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId from  TdpCommitteeMember model where model.tdpCommitteeMemberId =:tdpCommitteMemberId ");
		query.setParameter("tdpCommitteMemberId", tdpCommitteMemberId);
		return query.list();	
	}
	public List<Object[]> getStartedAffliCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate ){

		StringBuilder str = new StringBuilder();

		str.append("select count(distinct model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId), " +
		" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId " +
		" from TdpCommitteeMember model where ");
		str.append(" model.tdpCommitteeRole.tdpCommittee.state= :state ");
		if(startDate !=null && endDate !=null){
			str.append(" and ( date(model.tdpCommitteeRole.tdpCommittee.completedDate)>=:startDate and date(model.tdpCommitteeRole.tdpCommittee.completedDate)<=:endDate ) ");
		}
		str.append("and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) and model.isActive ='Y' " +
				" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 2 and" +
				"  model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'Y' " +
				"group by model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("state", state);		
		query.setParameterList("levelIds", levelIds);
		if(startDate != null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		return query.list();
	}
	
	
	
	
	public List<Object[]> getMembersCountInCommitteeByLocation(String state,List<Long> levelIds,Long committeeId,Date startDate,Date endDate){
		
		StringBuilder str = new StringBuilder();
		
		str.append("select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId," +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name " +				
				" from TdpCommitteeMember model where ");
		if(state != null)
		{
			str.append(" model.tdpCommitteeRole.tdpCommittee.state= :state ");
		}
		if(startDate !=null && endDate !=null){
			
			str.append(" and ( date(model.tdpCommitteeRole.tdpCommittee.completedDate)>=:startDate and date(model.tdpCommitteeRole.tdpCommittee.completedDate)<=:endDate ) ");
		}
		str.append("and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId = :committeeId" +
				" and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'Y'  and model.isActive = 'Y' " +
				" group by model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId," +
					" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue ");		
       			
		Query query = getSession().createQuery(str.toString());
		if(state != null)
		{
			query.setParameter("state", state);
		}
		if(startDate != null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		query.setParameter("committeeId", committeeId);
		query.setParameterList("levelIds",levelIds);
        return query.list();
    }
	public Long getCommitteMembers(Long tdpCommitteeId)
	{
		Query query = getSession().createQuery("select count(model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId) from  TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId =:tdpCommitteeId and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'N' and model.isActive = 'Y' ");
		query.setParameter("tdpCommitteeId", tdpCommitteeId);
		return (Long) query.uniqueResult();	
	}
}
