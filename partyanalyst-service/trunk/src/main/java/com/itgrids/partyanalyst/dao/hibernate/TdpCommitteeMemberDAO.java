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
		//0 role,1 image,2name,3membership,4tdpCommitteeMemberId,5cadreId,6tdpCommitteeRoleId
		Query query = getSession().createQuery("select model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.image,model.tdpCadre.firstname,model.tdpCadre.memberShipNo,model.tdpCommitteeMemberId, " +
				" model.tdpCadre.tdpCadreId,model.tdpCommitteeRole.tdpCommitteeRoleId from TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommitteeRoleId in(:committeeRoleIds)  and model.isActive ='Y'  order by model.tdpCommitteeRole.tdpRoles.order ");
		query.setParameterList("committeeRoleIds", committeeRoleIds);
		
		return query.list();
	}
	
	public List<Object[]> getAllCommitteeMembersInfoInALoc(Long locationLvl,Long locationVal){
		//0 role,1 image,2name,3membership,4tdpCommitteeMemberId,5cadreId,6tdpCommitteeRoleId,7committee Name
		Query query = getSession().createQuery("select model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.image,model.tdpCadre.firstname,model.tdpCadre.memberShipNo,model.tdpCommitteeMemberId, " +
				" model.tdpCadre.tdpCadreId,model.tdpCommitteeRole.tdpCommitteeRoleId,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name from TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId =:locationLvl " +
				" and  model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationVal and model.isActive ='Y'  order by model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId, model.tdpCommitteeRole.tdpRoles.order ");
		query.setParameter("locationLvl", locationLvl);
		query.setParameter("locationVal", locationVal);
		return query.list();
	}
	
	public List<Object[]> getMemberInfo(Long tdpCadreId){

		Query query = getSession().createQuery("select model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId, " +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId, " +
				" model.tdpCommitteeRole.tdpCommitteeRoleId, model.tdpCommitteeRole.tdpRoles.role    " +
				" from TdpCommitteeMember model where model.tdpCadreId =:tdpCadreId and model.isActive ='Y' ");
		query.setParameter("tdpCadreId", tdpCadreId);
	//	query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
	
	public List<Object[]> getMembersInfoByTdpCadreIdsList(List<Long> tdpCadreIdsList){

		Query query = getSession().createQuery("select model.tdpCadre.tdpCadreId, model.tdpCadre.voter.voterIDCardNo, model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId, " +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name, " +
				" model.tdpCommitteeRole.tdpCommitteeRoleId, model.tdpCommitteeRole.tdpRoles.role,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel    " +
				" from TdpCommitteeMember model where model.tdpCadreId in (:tdpCadreIdsList)  and model.isActive ='Y' ");
		
		query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
	//	query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
	
	public List<TdpCommitteeMember> getTdpCommitteeMemberByTdpCadreId(Long tdpCadreId){
		Query query = getSession().createQuery("select model from TdpCommitteeMember model where model.tdpCadreId =:tdpCadreId  and model.isActive ='Y'");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}
	
	/*public List<Object[]> getTdpCommitteeMemberForTdpCadreIdList(List<Long> tdpCadreIdsList)
	{
		String queryStr = " select distinct model.tdpCadreId ,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name," +
				" model.tdpCommitteeRole.tdpRoles.role, model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId, " +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,model.tdpCommitteeRole.tdpCommitteeRoleId,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel " +
				" from TdpCommitteeMember model where model.tdpCadreId in (:tdpCadreIdsList) and model.isActive = 'Y' group by model.tdpCadreId ";
		Query query = getSession().createQuery(queryStr);
		query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}*/
	
	public List<Object[]> getTdpCommitteeMemberForTdpCadreIdList(List<Long> tdpCadreIdsList)
	{
		String queryStr = " select TD.tdp_cadre_id, TBC.name, TR.role , TC.tdp_committee_level_id , TC.tdp_committee_level_value, " +
				" TCR.tdp_committee_role_id ,TCL.tdp_committee_level  from tdp_committee_member TCM, tdp_committee_role TCR, tdp_committee TC, tdp_cadre TD, tdp_roles TR, tdp_basic_committee TBC," +
				" tdp_committee_level TCL where " +
				" TD.tdp_cadre_id = TCM.tdp_cadre_id and TCM.tdp_committee_role_id = TCR.tdp_committee_role_id and " +
				" TCR.tdp_roles_id = TR.tdp_roles_id and TCR.tdp_committee_id = TC.tdp_committee_id and " +
				" TC.tdp_basic_committee_id = TBC.tdp_basic_committee_id and TC.tdp_committee_level_id = TCL.tdp_committee_level_id and " +
				" TCM.tdp_cadre_id in (:tdpCadreIdsList) and TCM.is_active='Y' group by TCM.tdp_cadre_id ";
		Query query = getSession().createSQLQuery(queryStr);
		query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
	
	public List<Object[]> getStartedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList){

		StringBuilder str = new StringBuilder();

		str.append("select count(distinct model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId), " +
		" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId " +
		" from TdpCommitteeMember model where ");
		if(locationlevelValueList != null && locationlevelValueList.size()>0)
		{
			str.append(" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:locationlevelValueList) ");
		}	
		else if(assemblyIds != null && assemblyIds.size()>0)
		{
			str.append(" model.tdpCommitteeRole.tdpCommittee.constituency.constituencyId in (:assemblyIds) ");
		}		
		else if(districtIds != null && districtIds.size()>0)
		{
			str.append(" model.tdpCommitteeRole.tdpCommittee.districtId in (:districtIds)  ");
		}
		else if(state != null)
		{
			str.append("  model.tdpCommitteeRole.tdpCommittee.state= :state ");
		}
		
		if(startDate !=null && endDate !=null){
			str.append(" and ( date(model.tdpCommitteeRole.tdpCommittee.startedDate)>=:startDate and date(model.tdpCommitteeRole.tdpCommittee.startedDate)<=:endDate ) " );
		}
		str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) and model.isActive ='Y' " +		
				" and model.tdpCommitteeRole.tdpCommittee.completedDate is null " +
				" group by " +
		        " model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		Query query = getSession().createQuery(str.toString());
		if(locationlevelValueList != null && locationlevelValueList.size()>0)
		{
			query.setParameterList("locationlevelValueList", locationlevelValueList);
		}	
		else if(assemblyIds != null && assemblyIds.size()>0)
		{
			query.setParameterList("assemblyIds", assemblyIds);
		}
		else if(districtIds != null && districtIds.size()>0)
		{
			query.setParameterList("districtIds", districtIds);
		}
		else if(state != null)
		{
			query.setParameter("state", state);
		}
		
		query.setParameterList("levelIds", levelIds);
		if(startDate !=null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		
		return query.list();
	}

	public Long getMembersCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate ,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList){

		StringBuilder str = new StringBuilder();

		str.append(" select count(distinct model.tdpCommitteeMemberId) " +
		" from TdpCommitteeMember model where ");
		if(locationlevelValueList != null && locationlevelValueList.size()>0)
		{
			str.append("  model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:locationlevelValueList) ");
		}	
		else if(assemblyIds != null && assemblyIds.size()>0)
		{
			str.append(" model.tdpCommitteeRole.tdpCommittee.constituency.constituencyId in (:assemblyIds) ");
		}		
		else if(districtIds != null && districtIds.size()>0)
		{
			str.append(" model.tdpCommitteeRole.tdpCommittee.districtId in (:districtIds) ");
		}		
		else if(state != null)
		{
			str.append(" model.tdpCommitteeRole.tdpCommittee.state= :state ");
		}
		if(startDate !=null && endDate !=null){
			
			str.append(" and ( date(model.insertedTime)>=:startDate and date(model.insertedTime)<=:endDate ) ");
		}
		str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) and model.isActive ='Y' ");

		Query query = getSession().createQuery(str.toString());
		
		if(locationlevelValueList != null && locationlevelValueList.size()>0)
		{
			query.setParameterList("locationlevelValueList", locationlevelValueList);
		}	
		else if(assemblyIds != null && assemblyIds.size()>0)
		{
			query.setParameterList("assemblyIds", assemblyIds);
		}
		else if(districtIds != null && districtIds.size()>0)
		{
			query.setParameterList("districtIds", districtIds);
		}
		else if(state != null)
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
				" model.tdpCadre.tdpCadreId, model.tdpCommitteeRole.tdpCommitteeRoleId,model.tdpCommitteeMemberId " +
				" from TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommitteeRoleId in(:committeeRoleIds) and model.isActive ='Y'  order by model.tdpCommitteeRole.tdpRoles.order ");
		query.setParameterList("committeeRoleIds", committeeRoleIds);
		
		return query.list();
	}
	public List<Object[]> getComitteeMembersByCommiteTypeAndLocation(Long levelId,List<Long> locationVals,Long committeeTypeId,String status)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId),model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue" +
				" from TdpCommitteeMember model" +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in(:locationVals)" +
				" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId and model.isActive = 'Y'");
		if(status.equalsIgnoreCase("Conform"))
		str.append("  and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'Y' and model.tdpCommitteeRole.tdpCommittee.completedDate is not null ");
		else if(status.equalsIgnoreCase("Started"))
		str.append(" and model.tdpCommitteeRole.tdpCommittee.startedDate is not null  and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'N' and model.tdpCommitteeRole.tdpCommittee.completedDate is null ");
		str.append(" group by model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameterList("locationVals", locationVals);
		query.setParameter("committeeTypeId", committeeTypeId);
		return query.list();
			
	}
	public List<Object[]> getComitteeMembersInfoByCommiteTypeAndLocation(Long levelId,Long locationVal,Long committeeTypeId,String status)
	{
		StringBuilder str = new StringBuilder();
		//19tehsilId, 20localElectionBodyId 21constituencyId
		str.append("select model.tdpCommitteeRole.tdpRoles.tdpRolesId,model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname,model.tdpCadre.image,model.tdpCadre.memberShipNo,model.tdpCommitteeMemberId,model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed," +
				" model.tdpCadre.casteState.caste.casteName,model.tdpCadre.gender,model.tdpCadre.age ,model.tdpCadre.dateOfBirth,model.tdpCadre.casteState.casteCategoryGroup.casteCategory.categoryName, model.tdpCadre.mobileNo,model.tdpCadre.voterId,model.tdpCadre.familyVoterId, " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name,constituency.name,model.tdpCadre.casteState.casteStateId  " +
				" ,tehsil.tehsilId,leb.localElectionBodyId,ward.constituencyId  " +
				" from TdpCommitteeMember model left join model.tdpCadre.userAddress.constituency constituency  left join model.tdpCadre.userAddress.tehsil tehsil " +
				" left join model.tdpCadre.userAddress.localElectionBody leb left join model.tdpCadre.userAddress.ward ward " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  and model.isActive = 'Y' and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationVal");
		if(committeeTypeId.longValue() !=0L)
			str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId ");
		else 
			str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId != 1 ");
		
		if(status.equalsIgnoreCase("Conform"))
			str.append("  and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'Y' and model.tdpCommitteeRole.tdpCommittee.completedDate is not null ");
		
		str.append(" order by model.tdpCommitteeRole.tdpRoles.tdpRolesId  ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("locationVal", locationVal);
		if(committeeTypeId.longValue() !=0L)
			query.setParameter("committeeTypeId", committeeTypeId);
		return query.list();
			
	}
	
	public List<Long> getTdpCommitteIds(Long levelId,Long locationVal,Long committeeTypeId)
	{
		Query query = getSession().createQuery("select distinct model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId from TdpCommitteeMember model" +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationVal" +
				" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId and model.isActive ='Y'  ");
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
	
	public List<Object[]> membersCountDistrictWise(List<Long> levelIds, Date startDate, Date endDate, List<Long> districtIds){
		 
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.district.districtId, model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId " +
				" from TdpCommitteeMember model " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in(:levelIds)" +
				" and model.tdpCommitteeRole.tdpCommittee.district.districtId in(:districtIds)" +
				//" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 1" +
				" and model.isActive = 'Y' ");
		if(startDate!=null){
			sb.append(" and date(model.tdpCommitteeRole.tdpCommittee.startedDate) >= :startDate ");
		}
		if(endDate!=null){
			sb.append(" and date(model.tdpCommitteeRole.tdpCommittee.startedDate) <= :endDate");
		}
		sb.append(" group by model.tdpCommitteeRole.tdpCommittee.district.districtId,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId " +
				" order by model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameterList("levelIds", levelIds);
		if(startDate!=null){
			query.setParameter("startDate", startDate);
		}
		if(endDate!=null){
			query.setParameter("endDate", endDate);
		}
		
		query.setParameterList("districtIds", districtIds);
		
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
	
	
	
	
	public List<Object[]> getMembersCountInCommitteeByLocation(String state,List<Long> levelIds,Long committeeId,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList){
		
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
		if(locationlevelValueList != null && locationlevelValueList.size()>0)
		{
			str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:locationlevelValueList) ");
		}	
		else if(assemblyIds != null && assemblyIds.size()>0)
		{
			str.append(" and model.tdpCommitteeRole.tdpCommittee.constituency.constituencyId in (:assemblyIds) ");
		}		
		else if(districtIds != null && districtIds.size()>0)
		{
			str.append(" and model.tdpCommitteeRole.tdpCommittee.districtId in (:districtIds) ");
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
		if(locationlevelValueList != null && locationlevelValueList.size()>0)
		{
			query.setParameterList("locationlevelValueList", locationlevelValueList);
		}	
		else if(assemblyIds != null && assemblyIds.size()>0)
		{
			query.setParameterList("assemblyIds", assemblyIds);
		}
		else if(districtIds != null && districtIds.size()>0)
		{
			query.setParameterList("districtIds", districtIds);
		}
        return query.list();
    }
	public Long getCommitteMembers(Long tdpCommitteeId)
	{
		Query query = getSession().createQuery("select count(model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId) from  TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId =:tdpCommitteeId and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'N' and model.isActive = 'Y' ");
		query.setParameter("tdpCommitteeId", tdpCommitteeId);
		return (Long) query.uniqueResult();	
	}
	public List<Object[]> getCommitteeDetails(Long committeeId){
		Query query = getSession().createQuery("select model.tdpCadreId,model.tdpCommitteeRole.tdpCommitteeRoleId " +
				" from TdpCommitteeMember model " + 
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId=:committeeId and model.isActive='Y'  ");
		query.setParameter("committeeId", committeeId);
		return query.list();
		
	}
	
	
public List<Object[]> membersCountConstituencyWise(List<Long> levelIds, Date startDate, Date endDate, List<Long> constiIds){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.constituency.constituencyId,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId " +
				" from TdpCommitteeMember model " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in (:levelIds)" +
				" and model.tdpCommitteeRole.tdpCommittee.constituency.constituencyId in (:constiIds)" +
				//" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 1" +
				" and model.isActive = 'Y' ");
				
		if(startDate!=null){
			sb.append(" and date(model.insertedTime) >= :startDate ");
		}
		if(endDate!=null){
			sb.append(" and date(model.insertedTime) <= :endDate");
		}
		sb.append(" group by model.tdpCommitteeRole.tdpCommittee.constituency.constituencyId,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameterList("levelIds", levelIds);
		if(startDate!=null){
			query.setParameter("startDate", startDate);
		}
		if(endDate!=null){
			query.setParameter("endDate", endDate);
		}
		
		query.setParameterList("constiIds", constiIds);
		
		return query.list();
	}

public List<Object[]> totalMainMembersCountLocationsWise(Long levelId, Date startDate, Date endDate,List<Long> levelValues){
	//0 count,1levelId
	StringBuilder sb = new StringBuilder();
	sb.append(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId " +
			" from TdpCommitteeMember model " +
			" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId = :levelId and model.isActive ='Y' " +
			" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:levelValues) ");
			//" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 1l ");
	
	if(startDate!=null){
		sb.append(" and date(model.insertedTime) >= :startDate ");
	}
	if(endDate!=null){
		sb.append(" and date(model.insertedTime) <= :endDate");
	}
	sb.append(" group by model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId ");
	
	Query query = getSession().createQuery(sb.toString());
	
	query.setParameter("levelId", levelId);
	if(startDate!=null){
		query.setParameter("startDate", startDate);
	}
	if(endDate!=null){
		query.setParameter("endDate", endDate);
	}
	
	query.setParameterList("levelValues",levelValues);
	
	return query.list();
}

public List<Object[]> getCommitteeMembersCountByLocationAndCommitteeType(Long levelId,List<Long> locationVals){
	StringBuilder str = new StringBuilder();
	str.append("select count(model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId)," +
			" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId," +
			" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue," +
			" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId " +
			" from TdpCommitteeMember model" +
			" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  " +
			" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in(:locationVals)" +
			//" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId " +
			" and model.isActive = 'Y'");
	
	str.append(" group by model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId," +
			" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue," +
			" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId");
	
	Query query = getSession().createQuery(str.toString());
	query.setParameter("levelId", levelId);
	query.setParameterList("locationVals", locationVals);
	
	return query.list();
		
}
	public List<Object[]> getCommitteePresidentAndVicePresidentsCount(List<Long> locationIds, Long locationLevel){
		StringBuilder str = new StringBuilder();
		str.append("select count(model.tdpCommitteeMemberId)," +
				//" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId," +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue," +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId " +
				" from TdpCommitteeMember model " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  " +
				" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in(:locationVals)" +
				//" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId " +
				" and model.isActive = 'Y'" +
				" and model.tdpCommitteeRole.tdpRoles.tdpRolesId in(1,3)");
		
		str.append(" group by " +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue," +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", locationLevel);
		query.setParameterList("locationVals", locationIds);
		
		return query.list();
	}

public List<Object[]> getAffiliCommMembersInfo(Set<Long> committeeRoleIds){
	//0 role,1 image,2name,3membership
	Query query = getSession().createQuery("select model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.image,model.tdpCadre.firstname,model.tdpCadre.memberShipNo," +
	" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name " +
	" from TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommitteeRoleId in(:committeeRoleIds) and model.isActive ='Y' order by model.tdpCommitteeRole.tdpRoles.order ");
	query.setParameterList("committeeRoleIds", committeeRoleIds);

	return query.list();
	}
public List<Object[]> getPresidentsAndVPInfoForCommittee(Long levelId,Long locationVal,Long committeeTypeId)
{
	StringBuilder str = new StringBuilder();
	str.append("select model.tdpCommitteeRole.tdpRoles.tdpRolesId,model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname,model.tdpCadre.image,model.tdpCadre.memberShipNo,model.tdpCommitteeMemberId,model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed" +
			" ,model.tdpCadre.casteState.caste.casteName,model.tdpCadre.gender,model.tdpCadre.age ,model.tdpCadre.dateOfBirth,model.tdpCadre.casteState.casteCategoryGroup.casteCategory.categoryName from TdpCommitteeMember model" +
			" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  and model.isActive = 'Y' and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationVal" +
			" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId " +
			" and model.tdpCommitteeRole.tdpRoles.tdpRolesId in(1,3) " +
			" order by model.tdpCommitteeRole.tdpRoles.tdpRolesId ");
	
	Query query = getSession().createQuery(str.toString());
	query.setParameter("levelId", levelId);
	query.setParameter("locationVal", locationVal);
	query.setParameter("committeeTypeId", committeeTypeId);
	return query.list();
		
}

public List<Object[]> getCommitteePresidentAndGS(List<Long> locationIds, Long locationLevel){
	StringBuilder str = new StringBuilder();
	str.append("select model.tdpCadreId," +
			//" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId," +
			" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue," +
			" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId " +
			" from TdpCommitteeMember model " +
			" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  " +
			" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in(:locationVals)" +
			//" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId " +
			" and model.isActive = 'Y'" +
			" and model.tdpCommitteeRole.tdpRoles.tdpRolesId in(1,3)");
	
	/*str.append(" group by" +
			"  model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId, " +
			" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue ");*/
	
	Query query = getSession().createQuery(str.toString());
	query.setParameter("levelId", locationLevel);
	query.setParameterList("locationVals", locationIds);
	
	return query.list();
}

public List<Object[]> getAllMembersInMainCommWithPresidentAndGeneralSecretaryRole(Long locationType,Long locationVal){
	//0 committee name,1 electoral name,2image,3membership,4cadreId
	Query query = getSession().createQuery("select distinct model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name,model.tdpCadre.firstname,model.tdpCadre.image,model.tdpCadre.memberShipNo, " +
				"  model.tdpCadre.tdpCadreId from TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId =:locationType " +
				"   and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationVal and model.tdpCommitteeRole.tdpRoles.tdpRolesId in(1,3)  and model.isActive ='Y'  " +
				" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId = 1 order by model.tdpCommitteeRole.tdpRoles.order ");
	query.setParameter("locationType", locationType);
	query.setParameter("locationVal", locationVal);
	return query.list();
}


public List<Object[]> getStartedCommitteesMembersCountByLocation(String state,List<Long> levelIds,Long committeeId,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList){
	
	StringBuilder str = new StringBuilder();
	
	str.append("select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId," +
			" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name " +				
			" from TdpCommitteeMember model where ");
	
	if(locationlevelValueList != null && locationlevelValueList.size()>0)
	{
		str.append(" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:locationlevelValueList) ");
	}	
	else if(assemblyIds != null && assemblyIds.size()>0)
	{
		str.append(" model.tdpCommitteeRole.tdpCommittee.constituency.constituencyId in (:assemblyIds) ");
	}		
	else if(districtIds != null && districtIds.size()>0)
	{
		str.append(" model.tdpCommitteeRole.tdpCommittee.districtId in (:districtIds)  ");
	}
	else if(state != null)
	{
		str.append("  model.tdpCommitteeRole.tdpCommittee.state= :state ");
	}
	
	if(startDate !=null && endDate !=null){
		str.append(" and ( date(model.tdpCommitteeRole.tdpCommittee.startedDate)>=:startDate and date(model.tdpCommitteeRole.tdpCommittee.startedDate)<=:endDate ) " );
	}
	
	str.append("and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
			" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId = :committeeId" +
			" and model.tdpCommitteeRole.tdpCommittee.completedDate is null  and model.isActive = 'Y' and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'N' " +
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
	if(locationlevelValueList != null && locationlevelValueList.size()>0)
	{
		query.setParameterList("locationlevelValueList", locationlevelValueList);
	}	
	else if(assemblyIds != null && assemblyIds.size()>0)
	{
		query.setParameterList("assemblyIds", assemblyIds);
	}
	else if(districtIds != null && districtIds.size()>0)
	{
		query.setParameterList("districtIds", districtIds);
	}
    return query.list();
}
	
	public List<Object[]> getCommitteeRolesGenderWiseDetailsByLocation(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr,Long descriptionLevelId)
	{
		StringBuilder quertyStr = new StringBuilder();
		quertyStr.append(" select distinct TC.gender , count(TC.gender) ");
		
		if(descriptionLevelId != null && descriptionLevelId.longValue() == 2L) // districtWise
		{
			quertyStr.append(", TCO.district.districtId,TCO.district.districtName ");
		}
		else if(descriptionLevelId != null && descriptionLevelId.longValue() == 3L) // constituencyWise
		{
			quertyStr.append(", TCO.constituency.constituencyId,TCO.constituency.name ");
		}
		
		quertyStr.append(" from TdpCadre TC, TdpCommitteeMember TCM, TdpCommitteeRole TCR, TdpRoles TR , TdpCommittee TCO,TdpBasicCommittee TBC ");
		quertyStr.append(" where ");
		quertyStr.append(" TCM.tdpCommitteeRoleId = TCR.tdpCommitteeRoleId and TCR.tdpRolesId = TR.tdpRolesId and TCM.tdpCadreId = TC.tdpCadreId ");
		quertyStr.append(" and TCR.tdpCommitteeId = TCO.tdpCommitteeId and TBC.tdpBasicCommitteeId = TCO.tdpBasicCommitteeId ");
		quertyStr.append(" and TCM.isActive ='Y' ");
		quertyStr.append(" and TC.isDeleted = 'N' and TC.enrollmentYear = 2014 ");
		if(userAccessType != null && userAccessType.equalsIgnoreCase("TS"))
		{
			quertyStr.append(" and (TCO.district.districtId between 1 and 10) ");
		}
		else if(userAccessType != null && userAccessType.equalsIgnoreCase("AP"))
		{
			quertyStr.append(" and (TCO.district.districtId between 11 and 23) ");
		}
		if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0) 
		{
			quertyStr.append(" and TBC.tdpCommitteeType.tdpCommitteeTypeId in (:committeeTypeIdsList) ");
		}
		if(positionIdsList != null && positionIdsList.size()>0) 
		{
			quertyStr.append(" and TR.tdpRolesId in (:positionIdsList) ");
		}
				
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			if(locationLevelId != null && locationLevelId.longValue() == 1L) // stateWise
			{
				quertyStr.append(" and TCO.district.state.stateId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 2L) // districtWise
			{
				quertyStr.append(" and TCO.district.districtId in (:locationIdsList) ");
			}			
			else if(locationLevelId != null && locationLevelId.longValue() == 3L) // constituencyWise
			{
				quertyStr.append(" and TCO.constituency.constituencyId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 5L) // village/ward wise
			{
				quertyStr.append(" and ( (TCO.tdpCommitteeLevelValue in (:locationIdsList) and TCO.tdpCommitteeLevelId = 6)  ");
				
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or (TCO.tdpCommitteeLevelValue in (:wardIdsList) and TCO.tdpCommitteeLevelId = 8 ) ");
				}
				quertyStr.append(" ) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 6L) // mandal/municipality/division wise
			{
				quertyStr.append(" and ( ( TCO.tdpCommitteeLevelValue in (:locationIdsList) and TCO.tdpCommitteeLevelId = 5 ) ");
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or ( TCO.tdpCommitteeLevelValue in (:wardIdsList) and TCO.tdpCommitteeLevelId = 7)  ");
				}
				quertyStr.append(" ) ");
			}
		}
		if(segrigatStr != null && segrigatStr.trim().length()>0)
		{
			if(segrigatStr.equalsIgnoreCase("VillageORWard"))
			{
				quertyStr.append(" and (TCO.tdpCommitteeLevelId = 6 or TCO.tdpCommitteeLevelId = 8)   ");
				
			}
			else if(segrigatStr.equalsIgnoreCase("MandalORTown"))
			{
				quertyStr.append(" and (TCO.tdpCommitteeLevelId = 5 or TCO.tdpCommitteeLevelId = 7)  ");
			}else if(segrigatStr.equalsIgnoreCase("district"))
			{
				quertyStr.append(" and TCO.tdpCommitteeLevelId = 11  ");
			}else if(segrigatStr.equalsIgnoreCase("state"))
			{
				quertyStr.append(" and TCO.tdpCommitteeLevelId = 10  ");
			}
			
		}
		quertyStr.append(" group by  ");
		if(descriptionLevelId != null && descriptionLevelId.longValue() == 2L) // districtWise
		{
			quertyStr.append(" TCO.district.districtId, ");
		}
		else if(descriptionLevelId != null && descriptionLevelId.longValue() == 3L) // constituencyWise
		{
			quertyStr.append(" TCO.constituency.constituencyId, ");
		}
		quertyStr.append(" TC.gender ");
		
		if(descriptionLevelId != null && descriptionLevelId.longValue() == 2L) // districtWise
		{
			quertyStr.append(" order by  TCO.district.districtName asc ");
		}
		else if(descriptionLevelId != null && descriptionLevelId.longValue() == 3L) // constituencyWise
		{
			quertyStr.append(" order by TCO.constituency.name asc  ");
		}
		
		Query query = getSession().createQuery(quertyStr.toString());
		
		if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0) 
		{
			query.setParameterList("committeeTypeIdsList", committeeTypeIdsList);
		}
		if(positionIdsList != null && positionIdsList.size()>0) 
		{
			query.setParameterList("positionIdsList", positionIdsList);
		}
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			query.setParameterList("locationIdsList", locationIdsList);
		}
		if(wardIdsList != null && wardIdsList.size()>0)
		{
			query.setParameterList("wardIdsList", wardIdsList);
		}
		return query.list();
	}
	
	public List<Object[]> getCommitteeRoleAgerangeWiseDetailsByLocationType(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr)
	{
		StringBuilder quertyStr = new StringBuilder();
		quertyStr.append(" select VAR.voterAgeRangeId ,VAR.ageRange,TC.gender , count(TC.gender) ");
		quertyStr.append(" from TdpCadre TC, TdpCommitteeMember TCM, TdpCommitteeRole TCR, TdpRoles TR , TdpCommittee TCO,TdpBasicCommittee TBC,VoterAgeRange VAR ");
		quertyStr.append(" where ");
		quertyStr.append(" TCM.tdpCommitteeRoleId = TCR.tdpCommitteeRoleId and TCR.tdpRolesId = TR.tdpRolesId and TCM.tdpCadreId = TC.tdpCadreId and TC.voterAgeRangeId = VAR.voterAgeRangeId and  ");
		quertyStr.append(" TCR.tdpCommitteeId = TCO.tdpCommitteeId and TBC.tdpBasicCommitteeId = TCO.tdpBasicCommitteeId ");
		quertyStr.append(" and TCM.isActive ='Y' ");
		quertyStr.append(" and TC.isDeleted = 'N' and TC.enrollmentYear = 2014 ");
		if(userAccessType != null && userAccessType.equalsIgnoreCase("TS"))
		{
			quertyStr.append(" and (TCO.district.districtId between 1 and 10) ");
		}
		else if(userAccessType != null && userAccessType.equalsIgnoreCase("AP"))
		{
			quertyStr.append(" and (TCO.district.districtId between 11 and 23) ");
		}
		if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0) 
		{
			quertyStr.append(" and TBC.tdpCommitteeType.tdpCommitteeTypeId in (:committeeTypeIdsList) ");
		}
		if(positionIdsList != null && positionIdsList.size()>0) 
		{
			quertyStr.append(" and TR.tdpRolesId in (:positionIdsList) ");
		}
				
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			if(locationLevelId != null && locationLevelId.longValue() == 1L) // stateWise
			{
				quertyStr.append(" and TCO.district.state.stateId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 2L) // districtWise
			{
				quertyStr.append(" and TCO.district.districtId in (:locationIdsList) ");
			}			
			else if(locationLevelId != null && locationLevelId.longValue() == 4L) // Parliament constituencyWise
			{
				quertyStr.append(" and UA.parliamentConstituency.constituencyId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 3L) // constituencyWise
			{
				quertyStr.append(" and TCO.constituency.constituencyId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 5L) // village/ward wise
			{
				quertyStr.append(" and ( (TCO.tdpCommitteeLevelValue in (:locationIdsList) and TCO.tdpCommitteeLevelId = 6)  ");
				
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or (TCO.tdpCommitteeLevelValue in (:wardIdsList) and TCO.tdpCommitteeLevelId = 8 ) ");
				}
				quertyStr.append(" ) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 6L) // mandal/municipality/division wise
			{
				quertyStr.append(" and ( ( TCO.tdpCommitteeLevelValue in (:locationIdsList) and TCO.tdpCommitteeLevelId = 5 ) ");
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or ( TCO.tdpCommitteeLevelValue in (:wardIdsList) and TCO.tdpCommitteeLevelId = 7)  ");
				}
				quertyStr.append(" ) ");
			}
		}
		
		if(segrigatStr != null && segrigatStr.trim().length()>0)
		{
			if(segrigatStr.equalsIgnoreCase("VillageORWard"))
			{
				quertyStr.append(" and (TCO.tdpCommitteeLevelId = 6 or TCO.tdpCommitteeLevelId = 8)   ");
				
			}
			else if(segrigatStr.equalsIgnoreCase("MandalORTown"))
			{
				quertyStr.append(" and (TCO.tdpCommitteeLevelId = 5 or TCO.tdpCommitteeLevelId = 7)  ");
			}else if(segrigatStr.equalsIgnoreCase("district"))
			{
				quertyStr.append(" and TCO.tdpCommitteeLevelId = 11  ");
			}else if(segrigatStr.equalsIgnoreCase("state"))
			{
				quertyStr.append(" and TCO.tdpCommitteeLevelId = 10  ");
			}
			
		}
		
		quertyStr.append(" group by VAR.voterAgeRangeId,TC.gender order by VAR.minValue asc");
		
		Query query = getSession().createQuery(quertyStr.toString());
		
		if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0) 
		{
			query.setParameterList("committeeTypeIdsList", committeeTypeIdsList);
		}
		if(positionIdsList != null && positionIdsList.size()>0) 
		{
			query.setParameterList("positionIdsList", positionIdsList);
		}
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			query.setParameterList("locationIdsList", locationIdsList);
		}
		if(wardIdsList != null && wardIdsList.size()>0)
		{
			query.setParameterList("wardIdsList", wardIdsList);
		}
		return query.list();
	}
	
	public List<Object[]> getCommitteeRoleCasteNameWiseDetailsByLocationType(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr)
	{
		StringBuilder quertyStr = new StringBuilder();
		quertyStr.append(" select CS.casteStateId ,C.casteName,TC.gender , count(TC.gender) ");
		quertyStr.append(" from TdpCadre TC, TdpCommitteeMember TCM, TdpCommitteeRole TCR, TdpRoles TR , TdpCommittee TCO,TdpBasicCommittee TBC , ");
		quertyStr.append(" CasteState CS, Caste C ");
		quertyStr.append(" where ");
		quertyStr.append(" TCM.tdpCommitteeRoleId = TCR.tdpCommitteeRoleId and TCR.tdpRolesId = TR.tdpRolesId and TCM.tdpCadreId = TC.tdpCadreId ");
		quertyStr.append(" and TCR.tdpCommitteeId = TCO.tdpCommitteeId and TBC.tdpBasicCommitteeId = TCO.tdpBasicCommitteeId ");
		quertyStr.append(" and TC.casteStateId = CS.casteStateId and CS.caste.casteId = C.casteId ");
		quertyStr.append(" and TCM.isActive ='Y' ");
		quertyStr.append(" and TC.isDeleted = 'N' and TC.enrollmentYear = 2014 ");
		if(userAccessType != null && userAccessType.equalsIgnoreCase("TS"))
		{
			quertyStr.append(" and (TCO.district.districtId between 1 and 10) ");
		}
		else if(userAccessType != null && userAccessType.equalsIgnoreCase("AP"))
		{
			quertyStr.append(" and (TCO.district.districtId between 11 and 23) ");
		}
		if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0) 
		{
			quertyStr.append(" and TBC.tdpCommitteeType.tdpCommitteeTypeId in (:committeeTypeIdsList) ");
		}
		if(positionIdsList != null && positionIdsList.size()>0) 
		{
			quertyStr.append(" and TR.tdpRolesId in (:positionIdsList) ");
		}
				
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			if(locationLevelId != null && locationLevelId.longValue() == 1L) // stateWise
			{
				quertyStr.append(" and TCO.district.state.stateId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 2L) // districtWise
			{
				quertyStr.append(" and TCO.district.districtId in (:locationIdsList) ");
			}			
			else if(locationLevelId != null && locationLevelId.longValue() == 4L) // Parliament constituencyWise
			{
				quertyStr.append(" and UA.parliamentConstituency.constituencyId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 3L) // constituencyWise
			{
				quertyStr.append(" and TCO.constituency.constituencyId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 5L) // village/ward wise
			{
				quertyStr.append(" and ( (TCO.tdpCommitteeLevelValue in (:locationIdsList) and TCO.tdpCommitteeLevelId = 6)  ");
				
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or (TCO.tdpCommitteeLevelValue in (:wardIdsList) and TCO.tdpCommitteeLevelId = 8 ) ");
				}
				quertyStr.append(" ) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 6L) // mandal/municipality/division wise
			{
				quertyStr.append(" and ( ( TCO.tdpCommitteeLevelValue in (:locationIdsList) and TCO.tdpCommitteeLevelId = 5 ) ");
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or ( TCO.tdpCommitteeLevelValue in (:wardIdsList) and TCO.tdpCommitteeLevelId = 7)  ");
				}
				quertyStr.append(" ) ");
			}
		}
		if(segrigatStr != null && segrigatStr.trim().length()>0)
		{
			if(segrigatStr.equalsIgnoreCase("VillageORWard"))
			{
				quertyStr.append(" and (TCO.tdpCommitteeLevelId = 6 or TCO.tdpCommitteeLevelId = 8)   ");
				
			}
			else if(segrigatStr.equalsIgnoreCase("MandalORTown"))
			{
				quertyStr.append(" and (TCO.tdpCommitteeLevelId = 5 or TCO.tdpCommitteeLevelId = 7)  ");
			}else if(segrigatStr.equalsIgnoreCase("district"))
			{
				quertyStr.append(" and TCO.tdpCommitteeLevelId = 11  ");
			}else if(segrigatStr.equalsIgnoreCase("state"))
			{
				quertyStr.append(" and TCO.tdpCommitteeLevelId = 10  ");
			}
			
		}
		quertyStr.append(" group by CS.casteStateId,TC.gender order by C.casteName asc");
		
		Query query = getSession().createQuery(quertyStr.toString());
		
		if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0) 
		{
			query.setParameterList("committeeTypeIdsList", committeeTypeIdsList);
		}
		if(positionIdsList != null && positionIdsList.size()>0) 
		{
			query.setParameterList("positionIdsList", positionIdsList);
		}
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			query.setParameterList("locationIdsList", locationIdsList);
		}
		if(wardIdsList != null && wardIdsList.size()>0)
		{
			query.setParameterList("wardIdsList", wardIdsList);
		}
		return query.list();
	}
	
	public List<Object[]> getCommitteeRoleCasteCategoryNameWiseDetailsByLocationType(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr)
	{
		StringBuilder quertyStr = new StringBuilder();
		quertyStr.append(" select distinct CC.casteCategoryId ,CC.categoryName,TC.gender , count(TC.gender) ");
		quertyStr.append(" from TdpCommitteeMember TCM, TdpCommitteeRole TCR, TdpRoles TR, TdpCadre TC, CasteState CS, Caste C, CasteCategoryGroup CCG,CasteCategory CC ");
		quertyStr.append(" , TdpCommittee TCO,TdpBasicCommittee TBC where TCM.tdpCommitteeRoleId = TCR.tdpCommitteeRoleId and TCR.tdpRolesId = TR.tdpRolesId and ");
		quertyStr.append(" TCM.tdpCadreId = TC.tdpCadreId and ");
		quertyStr.append(" TC.casteStateId = CS.casteStateId and CS.caste.casteId = C.casteId and CS.casteCategoryGroup.casteCategoryGroupId = CCG.casteCategoryGroupId and CC.casteCategoryId = CCG.casteCategory.casteCategoryId");
		quertyStr.append(" and TCR.tdpCommitteeId = TCO.tdpCommitteeId and TBC.tdpBasicCommitteeId = TCO.tdpBasicCommitteeId  ");

		quertyStr.append(" and CS.casteStateId not in (459,301,292,430) and TCM.isActive ='Y' ");
		quertyStr.append(" and TC.isDeleted = 'N' and TC.enrollmentYear = 2014 ");
		if(userAccessType != null && userAccessType.equalsIgnoreCase("TS"))
		{
			quertyStr.append(" and (TCO.district.districtId between 1 and 10) ");
		}
		else if(userAccessType != null && userAccessType.equalsIgnoreCase("AP"))
		{
			quertyStr.append(" and (TCO.district.districtId between 11 and 23) ");
		}
		if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0) 
		{
			quertyStr.append(" and TBC.tdpCommitteeType.tdpCommitteeTypeId in (:committeeTypeIdsList) ");
		}
		if(positionIdsList != null && positionIdsList.size()>0) 
		{
			quertyStr.append(" and TR.tdpRolesId in (:positionIdsList) ");
		}
				
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			if(locationLevelId != null && locationLevelId.longValue() == 1L) // stateWise
			{
				quertyStr.append(" and TCO.district.state.stateId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 2L) // districtWise
			{
				quertyStr.append(" and TCO.district.districtId in (:locationIdsList) ");
			}			
			else if(locationLevelId != null && locationLevelId.longValue() == 4L) // Parliament constituencyWise
			{
				quertyStr.append(" and UA.parliamentConstituency.constituencyId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 3L) // constituencyWise
			{
				quertyStr.append(" and TCO.constituency.constituencyId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 5L) // village/ward wise
			{
				quertyStr.append(" and ( (TCO.tdpCommitteeLevelValue in (:locationIdsList) and TCO.tdpCommitteeLevelId = 6)  ");
				
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or (TCO.tdpCommitteeLevelValue in (:wardIdsList) and TCO.tdpCommitteeLevelId = 8 ) ");
				}
				quertyStr.append(" ) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 6L) // mandal/municipality/division wise
			{
				quertyStr.append(" and ( ( TCO.tdpCommitteeLevelValue in (:locationIdsList) and TCO.tdpCommitteeLevelId = 5 ) ");
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or ( TCO.tdpCommitteeLevelValue in (:wardIdsList) and TCO.tdpCommitteeLevelId = 7)  ");
				}
				quertyStr.append(" ) ");
			}
		}
		if(segrigatStr != null && segrigatStr.trim().length()>0)
		{
			if(segrigatStr.equalsIgnoreCase("VillageORWard"))
			{
				quertyStr.append(" and (TCO.tdpCommitteeLevelId = 6 or TCO.tdpCommitteeLevelId = 8)   ");
				
			}
			else if(segrigatStr.equalsIgnoreCase("MandalORTown"))
			{
				quertyStr.append(" and (TCO.tdpCommitteeLevelId = 5 or TCO.tdpCommitteeLevelId = 7)  ");
			}else if(segrigatStr.equalsIgnoreCase("district"))
			{
				quertyStr.append(" and TCO.tdpCommitteeLevelId = 11  ");
			}else if(segrigatStr.equalsIgnoreCase("state"))
			{
				quertyStr.append(" and TCO.tdpCommitteeLevelId = 10  ");
			}
			
		}
		quertyStr.append(" group by CC.casteCategoryId,TC.gender order by TC.gender asc");
		
		Query query = getSession().createQuery(quertyStr.toString());
		
		if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0) 
		{
			query.setParameterList("committeeTypeIdsList", committeeTypeIdsList);
		}
		if(positionIdsList != null && positionIdsList.size()>0) 
		{
			query.setParameterList("positionIdsList", positionIdsList);
		}
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			query.setParameterList("locationIdsList", locationIdsList);
		}
		if(wardIdsList != null && wardIdsList.size()>0)
		{
			query.setParameterList("wardIdsList", wardIdsList);
		}
		return query.list();
	}
	
	public List<Object[]> getCasteCategoryInfoForLocations(Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,String userAccessType, String segrigatStr,String searchType)
	{
		StringBuilder quertyStr = new StringBuilder(); 
		quertyStr.append(" select ");

		quertyStr.append(" TCCI.location_id, TCCI.caste_category_id, TCCI.count  from tdp_cadre_caste_info TCCI where TCCI.caste_state_id not in (459,301,292,430)  ");
		
		if(userAccessType != null)
		{
			if(userAccessType.equalsIgnoreCase("AP"))
			{
				quertyStr.append(" and (TCCI.location_type like '%District%' and TCCI.location_id between 11 and 23)  ");
			}
			else if(userAccessType.equalsIgnoreCase("TS"))
			{
				quertyStr.append(" and (TCCI.location_type like '%District%' and TCCI.location_id between 1 and 10)  ");
			}
			else if(userAccessType.equalsIgnoreCase("ALL") || userAccessType.equalsIgnoreCase("STATE"))
			{
				quertyStr.append(" and (TCCI.location_type like '%District%' and TCCI.location_id between 1 and 23)  ");
			}
		}
		
		
		boolean isAreaWise = false;
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			if(locationLevelId != null && locationLevelId.longValue() == 1L) // stateWise
			{
				quertyStr.append(" and TCCI.location_type like '%District%' and TCCI.location_id between 1 and 23 ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 2L) // districtWise
			{
				quertyStr.append(" and TCCI.location_type like '%District%' and TCCI.location_id in (:locationIdsList) ");
			}			
			else if(locationLevelId != null && locationLevelId.longValue() == 3L) // constituencyWise
			{
				quertyStr.append(" and TCCI.location_type like '%Constituency%' and TCCI.location_id in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 5L) // village/ward wise
			{
				isAreaWise = true;
				quertyStr.append(" and ( TCCI.location_id in (:locationIdsList) ");
				
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or TCCI.location_id in (:wardIdsList) ");
				}
				quertyStr.append(" ) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 6L) // mandal/municipality/division wise
			{
				isAreaWise = true;
				quertyStr.append(" and  ( TCCI.location_id in (:locationIdsList) ");
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or TCCI.location_id in (:wardIdsList) ");
				}
				quertyStr.append(" ) ");
			}
		}
		if(isAreaWise && (segrigatStr != null && segrigatStr.trim().length()>0))
		{
			if(segrigatStr.equalsIgnoreCase("VillageORWard"))
			{
				quertyStr.append(" and ( TCCI.location_type like '%Panchayat%' or TCCI.location_type like '%Ward%' ) ");
				
			}
			else if(segrigatStr.equalsIgnoreCase("MandalORTown"))
			{
				quertyStr.append(" and ( TCCI.location_type like '%Teshil%' or TCCI.location_type like '%LocalBody%' ) ");
			}
		}

		Query query = getSession().createSQLQuery(quertyStr.toString());
		
		if( (locationLevelId != null && locationLevelId.longValue() != 1L) && (locationIdsList != null && locationIdsList.size()>0))
		{
			query.setParameterList("locationIdsList", locationIdsList);
		}
		if(wardIdsList != null && wardIdsList.size()>0)
		{
			query.setParameterList("wardIdsList", wardIdsList);
		}
		return query.list();
	}
	
	public List<Object[]> getCasteInfoForLocations(Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,String userAccessType, String segrigatStr,String searchType)
	{
		StringBuilder quertyStr = new StringBuilder(); 
		quertyStr.append(" select ");
		
		quertyStr.append(" TCCI.location_id, TCCI.caste_state_id, TCCI.count  from tdp_cadre_caste_info TCCI where   ");
		boolean isLocationEmpty = false;
		if(userAccessType != null)
		{
			if(userAccessType.equalsIgnoreCase("AP"))
			{
				isLocationEmpty = true;
				quertyStr.append(" (TCCI.location_type like '%District%' and TCCI.location_id between 11 and 23)   ");
			}
			else if(userAccessType.equalsIgnoreCase("TS"))
			{
				isLocationEmpty = true;
				quertyStr.append(" (TCCI.location_type like '%District%' and TCCI.location_id between 1 and 10)  ");
			}
			else if(userAccessType.equalsIgnoreCase("ALL") || userAccessType.equalsIgnoreCase("STATE"))
			{
				isLocationEmpty = true;
				quertyStr.append(" (TCCI.location_type like '%District%' and TCCI.location_id between 1 and 23)  ");
			}
			
		}
		
		boolean isAreaWise = false;
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			if(isLocationEmpty)
			{
				quertyStr.append(" and ");
			}
			
			if(locationLevelId != null && locationLevelId.longValue() == 1L) // stateWise
			{
				quertyStr.append("  TCCI.location_type like '%District%' and TCCI.location_id between 1 and 23 ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 2L) // districtWise
			{
				quertyStr.append("  TCCI.location_type like '%District%' and TCCI.location_id in (:locationIdsList) ");
			}			
			else if(locationLevelId != null && locationLevelId.longValue() == 3L) // constituencyWise
			{
				quertyStr.append("  TCCI.location_type like '%Constituency%' and TCCI.location_id in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 5L) // village/ward wise
			{
				isAreaWise = true;
				quertyStr.append("  ( TCCI.location_id in (:locationIdsList) ");
				
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or TCCI.location_id in (:wardIdsList) ");
				}
				quertyStr.append(" ) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 6L) // mandal/municipality/division wise
			{
				isAreaWise = true;
				quertyStr.append("   ( TCCI.location_id in (:locationIdsList) ");
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or TCCI.location_id in (:wardIdsList) ");
				}
				quertyStr.append(" ) ");
			}
		}
		if(isAreaWise && (segrigatStr != null && segrigatStr.trim().length()>0))
		{
			if(segrigatStr.equalsIgnoreCase("VillageORWard"))
			{
				quertyStr.append(" and ( TCCI.location_type like '%Panchayat%' or TCCI.location_type like '%Ward%' ) ");
				
			}
			else if(segrigatStr.equalsIgnoreCase("MandalORTown"))
			{
				quertyStr.append(" and ( TCCI.location_type like '%Teshil%' or TCCI.location_type like '%LocalBody%' ) ");
			}
		}

		Query query = getSession().createSQLQuery(quertyStr.toString());
		
		if( (locationLevelId != null && locationLevelId.longValue() != 1L) && (locationIdsList != null && locationIdsList.size()>0))
		{
			query.setParameterList("locationIdsList", locationIdsList);
		}
		if(wardIdsList != null && wardIdsList.size()>0)
		{
			query.setParameterList("wardIdsList", wardIdsList);
		}
		return query.list();
	}
	
	public List<Object[]> getCadreAgerangeInfoForLocations(Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,String userAccessType,String segrigatStr)
	{
		StringBuilder quertyStr = new StringBuilder();
		boolean isLocationEmpty = false;
		quertyStr.append(" select TCAGE.location_id,TCAGE.age_range_id, TCAGE.count ");
				
		quertyStr.append(" from tdp_cadre_agerange_info TCAGE where ");
		if(userAccessType.equalsIgnoreCase("AP"))
		{
			isLocationEmpty = true;
			quertyStr.append(" (TCAGE.location_type like '%District%' and TCAGE.location_id between 11 and 23)  ");
		}
		else if(userAccessType.equalsIgnoreCase("TS"))
		{
			isLocationEmpty = true;
			quertyStr.append(" (TCAGE.location_type like '%District%' and TCAGE.location_id between 1 and 10)  ");
		}
		else if(userAccessType.equalsIgnoreCase("ALL") || userAccessType.equalsIgnoreCase("STATE"))
		{
			isLocationEmpty = true;
			quertyStr.append(" (TCAGE.location_type like '%District%' and TCAGE.location_id between 1 and 23)  ");
		}
		
		boolean isAreaWise = false;
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			if(isLocationEmpty)
			{
				quertyStr.append(" and ");
			}
			if(locationLevelId != null && locationLevelId.longValue() == 1L) // stateWise
			{
				quertyStr.append("  TCAGE.location_type like '%District%' and TCAGE.location_id between 1 and 23 ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 2L) // districtWise
			{
				quertyStr.append("  TCAGE.location_type like '%District%' and TCAGE.location_id in (:locationIdsList) ");
			}			
			else if(locationLevelId != null && locationLevelId.longValue() == 3L) // constituencyWise
			{
				quertyStr.append("  TCAGE.location_type like '%Constituency%' and TCAGE.location_id in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 5L) // village/ward wise
			{
				isAreaWise = true;
				quertyStr.append("  ( TCAGE.location_id in (:locationIdsList) ");
				
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					
					quertyStr.append(" or TCAGE.location_id in (:wardIdsList) ");
				}
				quertyStr.append(" ) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 6L) // mandal/municipality/division wise
			{
				isAreaWise = true;
				quertyStr.append("  ( TCAGE.location_id in (:locationIdsList) ");
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or TCAGE.location_id in (:wardIdsList) ");
				}
				quertyStr.append(" ) ");
			}
		}
		if(isAreaWise && (segrigatStr != null && segrigatStr.trim().length()>0))
		{
			if(segrigatStr.equalsIgnoreCase("VillageORWard"))
			{
				quertyStr.append(" and ( TCAGE.location_type like '%Panchayat%' or TCAGE.location_type like '%Ward%' ) ");
				
			}
			else if(segrigatStr.equalsIgnoreCase("MandalORTown"))
			{
				quertyStr.append(" and ( TCAGE.location_type like '%Teshil%' or TCAGE.location_type like '%LocalBody%' ) ");
			}
		}

		Query query = getSession().createSQLQuery(quertyStr.toString());
		
		if( (locationLevelId != null && locationLevelId.longValue() != 1L) && (locationIdsList != null && locationIdsList.size()>0))
		{
			query.setParameterList("locationIdsList", locationIdsList);
		}
		if(wardIdsList != null && wardIdsList.size()>0)
		{
			query.setParameterList("wardIdsList", wardIdsList);
		}
		return query.list();
	}
	
public List<Object[]> membersCountMandalWise(List<Long> levelIds, Date startDate, Date endDate, List<Long> constiIds,String levelType){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(model.tdpCommitteeMemberId),");
		if(levelType.equalsIgnoreCase("mandal"))
		{
			sb.append(" p.tehsil.tehsilId");	
			sb.append(" from TdpCommitteeMember model,Panchayat p" +
		" where p.panchayatId = model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue ");
		}
		else if(levelType.equalsIgnoreCase("muncipality"))
		{
			
			sb.append(" c.localElectionBody.localElectionBodyId");	
			sb.append(" from TdpCommitteeMember model,Constituency c" +
		" where c.constituencyId = model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue ");
		}
		sb.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in (:levelIds)" +
				" and model.tdpCommitteeRole.tdpCommittee.constituency.constituencyId in (:constiIds)" +
				" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 1" +
				" and model.isActive = 'Y' ");
				
		if(startDate!=null){
			sb.append(" and date(model.insertedTime) >= :startDate ");
		}
		if(endDate!=null){
			sb.append(" and date(model.insertedTime) <= :endDate");
		}
		if(levelType.equalsIgnoreCase("mandal"))
			sb.append("  group by p.tehsil.tehsilId");
			else if(levelType.equalsIgnoreCase("muncipality"))
				sb.append(" group by c.localElectionBody.localElectionBodyId");
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameterList("levelIds", levelIds);
		if(startDate!=null){
			query.setParameter("startDate", startDate);
		}
		if(endDate!=null){
			query.setParameter("endDate", endDate);
		}
		
		query.setParameterList("constiIds", constiIds);
		
		return query.list();
	}

	public List<Object[]> getCommiteeMembersDetailsByPostionsAndCommiteeLevel(List<Long> committeeLevelIds,List<Long> committeeValueList,Long committeeId,List<Long> commiteeRoleIds,List<Long> districtIds,Integer startIndex,Integer maxIndex)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select distinct model.tdpCadreId,model.tdpCommitteeRole.tdpRoles.role from TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in (:committeeLevelIds) and model.isActive ='Y' and ");
		
		if(districtIds != null && districtIds.size()>0)
		{
			stringBuilder.append(" model.tdpCommitteeRole.tdpCommittee.districtId in(:districtIds) and ");
		}
		else if(committeeValueList != null && committeeValueList.size() > 0)
		{
			stringBuilder.append(" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in(:committeeValueList) and ");
		}
			
		
		if(committeeId != null && committeeId.longValue() != 0L)
			stringBuilder.append(" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId =:committeeId and " );
		
		stringBuilder.append(" model.tdpCommitteeRole.tdpRoles.tdpRolesId in (:commiteeRoleIds) ");
		
		Query query = getSession().createQuery(stringBuilder.toString());
		
		query.setParameterList("committeeLevelIds", committeeLevelIds);
		query.setParameterList("commiteeRoleIds", commiteeRoleIds);
		
		if(districtIds != null && districtIds.size()>0)
		{
			query.setParameterList("districtIds", districtIds);
		}
		else if(committeeValueList != null && committeeValueList.size() > 0)
		{
			query.setParameterList("committeeValueList", committeeValueList);
		}
		if(committeeId != null && committeeId.longValue() != 0L)
			query.setParameter("committeeId", committeeId);
		
		if(startIndex!=null)
			query.setFirstResult(startIndex);
			if(maxIndex != null)
			query.setMaxResults(maxIndex);
		return query.list();
	}
	
	public List<Object[]> getCommiteeMembersCountDetailsByPostionsAndCommiteeLevel(List<Long> committeeLevelIds,List<Long> committeeValueList,Long committeeId,List<Long> commiteeRoleIds,List<Long> districtIds,Integer startIndex,Integer maxIndex)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select model.tdpCommitteeRole.tdpRoles.tdpRolesId,model.tdpCommitteeRole.tdpRoles.role, count(distinct model.tdpCadreId)  from TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in (:committeeLevelIds) and model.isActive ='Y' and ");
		
		if(districtIds != null && districtIds.size()>0)
		{
			stringBuilder.append(" model.tdpCommitteeRole.tdpCommittee.districtId in(:districtIds) and ");
		}
		else if(committeeValueList != null && committeeValueList.size() > 0)
		{
			stringBuilder.append(" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in(:committeeValueList) and ");
		}
			
		
		if(committeeId != null && committeeId.longValue() != 0L)
			stringBuilder.append(" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId =:committeeId and " );
		
		stringBuilder.append(" model.tdpCommitteeRole.tdpRoles.tdpRolesId in (:commiteeRoleIds) group by model.tdpCommitteeRole.tdpRoles.tdpRolesId ");
		
		Query query = getSession().createQuery(stringBuilder.toString());
		
		query.setParameterList("committeeLevelIds", committeeLevelIds);
		query.setParameterList("commiteeRoleIds", commiteeRoleIds);
		
		if(districtIds != null && districtIds.size()>0)
		{
			query.setParameterList("districtIds", districtIds);
		}
		else if(committeeValueList != null && committeeValueList.size() > 0)
		{
			query.setParameterList("committeeValueList", committeeValueList);
		}
		if(committeeId != null && committeeId.longValue() != 0L)
			query.setParameter("committeeId", committeeId);
		
		if(startIndex!=null)
			query.setFirstResult(startIndex);
			if(maxIndex != null)
			query.setMaxResults(maxIndex);
		return query.list();
	}
	
	public List<Object[]> cadreMemberDetailsForPerformance(Long locationLevelId, Long locationLevelValue){
		Query query = getSession().createSQLQuery("SELECT distinct TD.memberShip_id,TD.first_name," +
				" TD.voter_id,TD.mobile_no, TD.age, TD.gender, C.caste_name, CC.category_name," +
				" V.voter_id_card_no,TBC.name, TR.role, CO.name, CO.constituency_id, B.part_no, B.booth_id, CPR.from_date , CPR.to_date" +
				" FROM tdp_committee TC," +
				" tdp_committee_role TCR," +
				" tdp_cadre TD," +
				" tdp_committee_member TCM, " +
				" caste_state CS," +
				" caste C, " +
				" caste_category_group CCG," +
				" caste_category CC, " +
				" voter V, " +
				" tdp_roles TR, " +
				" tdp_basic_committee TBC, " +
				" user_address UA, " +
				" booth B," +
				" constituency CO, " +
				" cadre_previous_roles CPR" +
				" WHERE " +
				" TCM.tdp_cadre_id = TD.tdp_cadre_id " +
				" AND TCM.tdp_committee_role_id = TCR.tdp_committee_role_id " +
				" AND TCR.tdp_committee_id = TC.tdp_committee_id " +
				" AND TC.tdp_committee_level_id = :locationLevelId" +//11
				" AND TC.tdp_committee_level_value = :locationLevelValue " +//17
				" AND TCM.is_active = 'Y' " +
				" and TD.caste_state_id = CS.caste_state_id" +
				" and CS.caste_id = C.caste_id " +
				" and CS.caste_category_group_id = CCG.caste_category_group_id " +
				" and CCG.caste_category_id = CC.caste_category_id " +
				" and TD.voter_id = V.voter_id " +
				" and TCR.tdp_roles_id = TR.tdp_roles_id " +
				" and TC.tdp_basic_committee_id = TBC.tdp_basic_committee_id " +
				" and TD.address_id = UA.user_address_id " +
				" and UA.booth_id = B.booth_id " +
				" and UA.constituency_id = CO.constituency_id " +
				" and CPR.tdp_cadre_id = TD.tdp_cadre_id " +
				" and CPR.is_deleted ='N' " +
				" order by TC.tdp_basic_committee_id, TR.tdp_roles_id, TD.first_name");
		
		query.setParameter("locationLevelId", locationLevelId);
		query.setParameter("locationLevelValue", locationLevelValue);
		
		return query.list();
	}
	
	public List<Object[]> cadreMemberBoothDetailsForPerformance(Long locationLevelId, Long locationLevelValue){
		Query query = getSession().createQuery("SELECT " +
				" distinct D.district_name," +
				"  CO.name ," +
				"  CO.constituency_id ," +
						" TD.first_name, B.part_no," +
						"  TD.mobile_no, V.voter_id_card_no, TD.voter_id " +
						" FROM tdp_committee TC," +
						" tdp_committee_role TCR," +
						" tdp_cadre TD, tdp_committee_member TCM, caste_state CS, caste C, caste_category_group CCG, " +
						" caste_category CC, " +
						" voter V, " +
						" tdp_roles TR, " +
						" tdp_basic_committee TBC, " +
						" user_address UA, " +
						" booth B, " +
						" constituency CO, " +
						" district D " +
						" WHERE " +
						" TCM.tdp_cadre_id = TD.tdp_cadre_id " +
						" AND TCM.tdp_committee_role_id = TCR.tdp_committee_role_id " +
						" AND TCR.tdp_committee_id = TC.tdp_committee_id " +
						" AND TC.tdp_committee_level_id = :locationLevelId " +
						" AND TC.tdp_committee_level_value = :locationLevelValue " +
						" AND TCM.is_active = 'Y' " +
						" and TD.caste_state_id = CS.caste_state_id " +
						" and CS.caste_id = C.caste_id " +
						" and CS.caste_category_group_id = CCG.caste_category_group_id " +
						" and CCG.caste_category_id = CC.caste_category_id " +
						" and TD.voter_id = V.voter_id " +
						" and TCR.tdp_roles_id = TR.tdp_roles_id " +
						" and TC.tdp_basic_committee_id = TBC.tdp_basic_committee_id " +
						" and TD.address_id = UA.user_address_id " +
						" and UA.booth_id = B.booth_id " +
						" and UA.constituency_id = CO.constituency_id " +
						" and CO.district_id = D.district_id " +
						" order by TC.tdp_basic_committee_id, TR.tdp_roles_id, TD.first_name");
		
		query.setParameter("locationLevelId", locationLevelId);
		query.setParameter("locationLevelValue", locationLevelValue);
		
		return query.list();
	}
	public List<Object[]> getPartyPositionBycadre(Long cadreId){
		
		Query query = getSession().createQuery(" select model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel," +
				" model.tdpCommitteeRole.tdpRoles.role,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue " +
				" from  TdpCommitteeMember model " +
				" where model.tdpCadre.tdpCadreId =:tdpCadreId " +
				" and model.isActive ='Y'");
		
		query.setParameter("tdpCadreId", cadreId);
		
		return  query.list();
	}
	
public List<Object[]> getPartyPositionsBycadreIdsList(List<Long> cadreIdsList){
		
		Query query = getSession().createQuery(" select model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel," +
				" model.tdpCommitteeRole.tdpRoles.role, model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name, model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId," +
				"  model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue, model.tdpCadre.tdpCadreId, model.tdpCommitteeRole.tdpCommittee.state" +
				" from  TdpCommitteeMember model " +
				" where model.tdpCadre.tdpCadreId in (:cadreIdsList) " +
				" and model.isActive ='Y'");
		
		query.setParameterList("cadreIdsList", cadreIdsList);
		
		return  query.list();
	}

	public Object[] getTdpCommitteeMemberPosition(Long cadreId){
		
		Query query = getSession().createQuery("select model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId, " +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name, " +
				" model.tdpCommitteeRole.tdpCommitteeRoleId, model.tdpCommitteeRole.tdpRoles.role,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel    " +
				" from TdpCommitteeMember model where model.tdpCadreId =:cadreId  and model.isActive ='Y' ");
		
		query.setParameter("cadreId", cadreId);
		
		return (Object[]) query.uniqueResult();
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValue(List<Long> locationLevelIdsList,List<Long> locationLevelValuesList)
	{
		Query query = getSession().createQuery(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId  from TdpCommitteeMember model " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in (:locationLevelIdsList) and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:locationLevelValuesList) " +
				" group by model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		
		query.setParameterList("locationLevelIdsList", locationLevelIdsList);
		query.setParameterList("locationLevelValuesList", locationLevelValuesList);
		
		return query.list();
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValue(Long levelId,Long locationValue,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		
		str.append(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId  from TdpCommitteeMember model " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationValue " +
				" and model.isActive ='Y' ");
		if(constituencyId != null && constituencyId > 0)
		 str.append(" and model.tdpCadre.userAddress.constituency.constituencyId =:constituencyId");
		
		str.append(" group by model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("locationValue", locationValue);
		
		if(constituencyId != null && constituencyId > 0)
			query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValueForVillage(Long levelId,Long tehsilId,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		
		str.append(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId  from TdpCommitteeMember model,Panchayat p " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId   " +
				" and model.isActive ='Y' ");
		if(constituencyId != null && constituencyId > 0)
		 str.append(" and model.tdpCadre.userAddress.constituency.constituencyId =:constituencyId");
		if(tehsilId != null && tehsilId > 0)
			str.append(" and p.tehsil.tehsilId =:tehsilId and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue = p.panchayatId");
		str.append(" group by model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("tehsilId", tehsilId);
		if(constituencyId != null && constituencyId > 0)
			query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValueForWards(Long levelId,Long localElectionBody,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		
		str.append(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId  from TdpCommitteeMember model,Constituency con " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId   " +
				" and model.isActive ='Y' ");
		if(constituencyId != null && constituencyId > 0)
		 str.append(" and model.tdpCadre.userAddress.constituency.constituencyId =:constituencyId");
		if(localElectionBody != null && localElectionBody > 0)
			str.append(" and con.localElectionBody.localElectionBodyId =:localElectionBody and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue = con.constituencyId ");
		str.append(" group by model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("localElectionBody", localElectionBody);
		if(constituencyId != null && constituencyId > 0)
			query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	public List<Object[]> getRoleWiseAllocatedMembersCount(List<Long> cadreIds){
		//0 count,1 id
		Query query = getSession().createQuery("select model.tdpCadre.tdpCadreId,model.tdpCommitteeRole.tdpRoles.tdpRolesId,model.tdpCommitteeRole.tdpRoles.role,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId from TdpCommitteeMember model where " +
				" model.tdpCadre.tdpCadreId in(:cadreIds) and model.isActive ='Y'  ");
		query.setParameterList("cadreIds", cadreIds);
		
		return query.list();
	}
	public List<Object[]> getMembersInfoByTdpCadreIdsList1(List<Long> tdpCadreIdsList){

		Query query = getSession().createQuery("select model.tdpCadre.tdpCadreId," +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId, " +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name, " +
				" model.tdpCommitteeRole.tdpCommitteeRoleId," +
				" model.tdpCommitteeRole.tdpRoles.role,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel    " +
				" from TdpCommitteeMember model where model.tdpCadreId in (:tdpCadreIdsList)  and model.isActive ='Y' ");
		
		query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
}
