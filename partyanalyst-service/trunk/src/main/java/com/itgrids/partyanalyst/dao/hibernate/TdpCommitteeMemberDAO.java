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
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,model.tdpCommitteeRole.tdpCommitteeRoleId " +
				" from TdpCommitteeMember model where model.tdpCadreId in (:tdpCadreIdsList) and model.isActive = 'Y' group by model.tdpCadreId ";
		Query query = getSession().createQuery(queryStr);
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
		str.append("select model.tdpCommitteeRole.tdpRoles.tdpRolesId,model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname,model.tdpCadre.image,model.tdpCadre.memberShipNo,model.tdpCommitteeMemberId,model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed," +
				" model.tdpCadre.casteState.caste.casteName,model.tdpCadre.gender,model.tdpCadre.age ,model.tdpCadre.dateOfBirth,model.tdpCadre.casteState.casteCategoryGroup.casteCategory.categoryName  " +
				" from TdpCommitteeMember model" +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  and model.isActive = 'Y' and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationVal" +
				" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId ");
		if(status.equalsIgnoreCase("Conform"))
			str.append("  and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'Y' and model.tdpCommitteeRole.tdpCommittee.completedDate is not null ");
		
		str.append(" order by model.tdpCommitteeRole.tdpRoles.tdpRolesId");
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
		sb.append(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.district.districtId " +
				" from TdpCommitteeMember model " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in(:levelIds)" +
				" and model.tdpCommitteeRole.tdpCommittee.district.districtId in(:districtIds)" +
				" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 1" +
				" and model.isActive = 'Y' ");
		if(startDate!=null){
			sb.append(" and date(model.tdpCommitteeRole.tdpCommittee.startedDate) >= :startDate ");
		}
		if(endDate!=null){
			sb.append(" and date(model.tdpCommitteeRole.tdpCommittee.startedDate) <= :endDate");
		}
		sb.append(" group by model.tdpCommitteeRole.tdpCommittee.district.districtId");
		
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
		sb.append(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.constituency.constituencyId " +
				" from TdpCommitteeMember model " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in (:levelIds)" +
				" and model.tdpCommitteeRole.tdpCommittee.constituency.constituencyId in (:constiIds)" +
				" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 1" +
				" and model.isActive = 'Y' ");
				
		if(startDate!=null){
			sb.append(" and date(model.insertedTime) >= :startDate ");
		}
		if(endDate!=null){
			sb.append(" and date(model.insertedTime) <= :endDate");
		}
		sb.append(" group by model.tdpCommitteeRole.tdpCommittee.constituency.constituencyId ");
		
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
	sb.append(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue " +
			" from TdpCommitteeMember model " +
			" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId = :levelId and model.isActive ='Y' " +
			" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:levelValues) " +
			" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 1l ");
	
	if(startDate!=null){
		sb.append(" and date(model.insertedTime) >= :startDate ");
	}
	if(endDate!=null){
		sb.append(" and date(model.insertedTime) <= :endDate");
	}
	sb.append(" group by model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue ");
	
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
	
	public List<Object[]> getCommitteeRoleCasteCategoryWiseDetailsByLocationType(List<Long> positionIdsList,List<Long> casteCategoryIdsList,List<Long> casteCategoryGroupIdsList, 
			List<Long> casteIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType)
	{
		StringBuilder quertyStr = new StringBuilder();
		
		quertyStr.append(" select CC.casteCategoryId,CC.categoryName,TC.gender,count(TC.gender) ");
		quertyStr.append(" from TdpCommitteeMember TCM, TdpCommitteeRole TCR, TdpRoles TR, TdpCadre TC, UserAddress UA, CasteState CS, Caste C, CasteCategoryGroup CCG,CasteCategory CC ");
		quertyStr.append(" , TdpCommittee TCO,TdpBasicCommittee TBC where TCM.tdpCommitteeRoleId = TCR.tdpCommitteeRoleId and TCR.tdpRolesId = TR.tdpRolesId and ");
		quertyStr.append(" TCM.tdpCadreId = TC.tdpCadreId and TC.userAddress.userAddressId = UA.userAddressId and  ");
		quertyStr.append(" TC.casteStateId = CS.casteStateId and CS.caste.casteId = C.casteId and CS.casteCategoryGroup.casteCategoryGroupId = CCG.casteCategoryGroupId and CC.casteCategoryId = CCG.casteCategory.casteCategoryId");
		quertyStr.append(" and TCR.tdpCommitteeId = TCO.tdpCommitteeId and TBC.tdpBasicCommitteeId = TCO.tdpBasicCommitteeId  ");
		
		if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0) 
		{
			quertyStr.append(" and TBC.tdpCommitteeType.tdpCommitteeTypeId in (:committeeTypeIdsList) ");
		}
		if(positionIdsList != null && positionIdsList.size()>0) 
		{
			quertyStr.append(" and TR.tdpRolesId in (:positionIdsList) ");
		}
		if(casteCategoryIdsList != null && casteCategoryIdsList.size()>0) 
		{
			quertyStr.append(" and CC.casteCategoryId in (:casteCategoryIdsList) ");
		}
		if(casteCategoryGroupIdsList != null && casteCategoryGroupIdsList.size()>0) 
		{
			quertyStr.append(" and CCG.casteCategoryGroupId in (:casteCategoryGroupIdsList) ");
		}
		if(casteIdsList != null && casteIdsList.size()>0) 
		{
			quertyStr.append(" and CS.casteStateId in (:casteIdsList) ");
		}
		
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			if(locationLevelId != null && locationLevelId.longValue() == 1L) // stateWise
			{
				quertyStr.append(" and UA.state.stateId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 2L) // districtWise
			{
				quertyStr.append(" and UA.district.districtId in (:locationIdsList) ");
			}			
			else if(locationLevelId != null && locationLevelId.longValue() == 3L) // Parliament constituencyWise
			{
				quertyStr.append(" and UA.parliamentConstituency.constituencyId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 4L) // constituencyWise
			{
				quertyStr.append(" and UA.constituency.constituencyId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 5L) // village/ward wise
			{
				quertyStr.append(" and ( UA.panchayat.panchayatId in (:locationIdsList) ");
				
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or UA.ward.constituencyId in (:wardIdsList) ");
				}
				quertyStr.append(" ) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 6L) // mandal/municipality/division wise
			{
				quertyStr.append(" and ( UA.tehsil.tehsilId in (:locationIdsList) ");
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or UA.localElectionBody.localElectionBodyId in (:wardIdsList) ");
				}
				quertyStr.append(" ) ");
			}
		}
		
		quertyStr.append(" group by CC.casteCategoryId,TC.gender order by TC.gender asc ");
		
		Query query = getSession().createQuery(quertyStr.toString());
		
		if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0) 
		{
			query.setParameterList("committeeTypeIdsList", committeeTypeIdsList);
		}
		if(positionIdsList != null && positionIdsList.size()>0) 
		{
			query.setParameterList("positionIdsList", positionIdsList);
		}
		if(casteCategoryIdsList != null && casteCategoryIdsList.size()>0) 
		{
			query.setParameterList("casteCategoryIdsList", casteCategoryIdsList);
		}
		if(casteCategoryGroupIdsList != null && casteCategoryGroupIdsList.size()>0) 
		{
			query.setParameterList("casteCategoryIdsList", casteCategoryGroupIdsList);
		}
		if(casteIdsList != null && casteIdsList.size()>0) 
		{
			query.setParameterList("casteIdsList", casteIdsList);
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
	
	public List<Object[]> getCommitteeRoleCasteWiseDetailsByLocationType(List<Long> positionIdsList,List<Long> casteCategoryIdsList,List<Long> casteCategoryGroupIdsList, 
			List<Long> casteIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType)
	{
		StringBuilder quertyStr = new StringBuilder();
		
		quertyStr.append(" select CS.casteStateId,C.casteName,TC.gender,count(TC.gender) ");
		quertyStr.append(" from TdpCommitteeMember TCM, TdpCommitteeRole TCR, TdpRoles TR, TdpCadre TC, UserAddress UA, CasteState CS, Caste C, CasteCategoryGroup CCG,CasteCategory CC ");
		quertyStr.append(" , TdpCommittee TCO,TdpBasicCommittee TBC where TCM.tdpCommitteeRoleId = TCR.tdpCommitteeRoleId and TCR.tdpRolesId = TR.tdpRolesId and ");
		quertyStr.append(" TCM.tdpCadreId = TC.tdpCadreId and TC.userAddress.userAddressId = UA.userAddressId and  ");
		quertyStr.append(" TC.casteStateId = CS.casteStateId and CS.caste.casteId = C.casteId and CS.casteCategoryGroup.casteCategoryGroupId = CCG.casteCategoryGroupId and CC.casteCategoryId = CCG.casteCategory.casteCategoryId");
		quertyStr.append(" and TCR.tdpCommitteeId = TCO.tdpCommitteeId and TBC.tdpBasicCommitteeId = TCO.tdpBasicCommitteeId  ");
		
		if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0) 
		{
			quertyStr.append(" and TBC.tdpCommitteeType.tdpCommitteeTypeId in (:committeeTypeIdsList) ");
		}
		if(positionIdsList != null && positionIdsList.size()>0) 
		{
			quertyStr.append(" and TR.tdpRolesId in (:positionIdsList) ");
		}
		if(casteCategoryIdsList != null && casteCategoryIdsList.size()>0) 
		{
			quertyStr.append(" and CC.casteCategoryId in (:casteCategoryIdsList) ");
		}
		if(casteCategoryGroupIdsList != null && casteCategoryGroupIdsList.size()>0) 
		{
			quertyStr.append(" and CCG.casteCategoryGroupId in (:casteCategoryGroupIdsList) ");
		}
		if(casteIdsList != null && casteIdsList.size()>0) 
		{
			quertyStr.append(" and CS.casteStateId in (:casteIdsList) ");
		}
		
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			if(locationLevelId != null && locationLevelId.longValue() == 1L) // stateWise
			{
				quertyStr.append(" and UA.state.stateId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 2L) // districtWise
			{
				quertyStr.append(" and UA.district.districtId in (:locationIdsList) ");
			}			
			else if(locationLevelId != null && locationLevelId.longValue() == 3L) // Parliament constituencyWise
			{
				quertyStr.append(" and UA.parliamentConstituency.constituencyId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 4L) // constituencyWise
			{
				quertyStr.append(" and UA.constituency.constituencyId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 5L) // village/ward wise
			{
				quertyStr.append(" and ( UA.panchayat.panchayatId in (:locationIdsList) ");
				
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or UA.ward.constituencyId in (:wardIdsList) ");
				}
				quertyStr.append(" ) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 6L) // mandal/municipality/division wise
			{
				quertyStr.append(" and ( UA.tehsil.tehsilId in (:locationIdsList) ");
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or UA.localElectionBody.localElectionBodyId in (:wardIdsList) ");
				}
				quertyStr.append(" ) ");
			}
		}
		
		quertyStr.append(" group by CS.casteStateId,TC.gender order by TC.gender asc ");
		
		Query query = getSession().createQuery(quertyStr.toString());
		
		if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0) 
		{
			query.setParameterList("committeeTypeIdsList", committeeTypeIdsList);
		}
		if(positionIdsList != null && positionIdsList.size()>0) 
		{
			query.setParameterList("positionIdsList", positionIdsList);
		}
		if(casteCategoryIdsList != null && casteCategoryIdsList.size()>0) 
		{
			query.setParameterList("casteCategoryIdsList", casteCategoryIdsList);
		}
		if(casteCategoryGroupIdsList != null && casteCategoryGroupIdsList.size()>0) 
		{
			query.setParameterList("casteCategoryIdsList", casteCategoryGroupIdsList);
		}
		if(casteIdsList != null && casteIdsList.size()>0) 
		{
			query.setParameterList("casteIdsList", casteIdsList);
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
	
	public List<Object[]> getCommitteeRoleAgeWiseDetailsByLocationType(List<Long> positionIdsList,List<Long> casteCategoryIdsList,List<Long> casteCategoryGroupIdsList, 
			List<Long> casteIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType)
	{
		StringBuilder quertyStr = new StringBuilder();
		
		quertyStr.append(" select VAR.voterAgeRangeId ,VAR.ageRange,TC.gender,count(*) ");
		quertyStr.append(" from TdpCommitteeMember TCM, TdpCommitteeRole TCR, TdpRoles TR, TdpCadre TC, UserAddress UA, CasteState CS, Caste C, CasteCategoryGroup CCG,CasteCategory CC ");
		quertyStr.append(" , TdpCommittee TCO,TdpBasicCommittee TBC,VoterAgeRange VAR where TCM.tdpCommitteeRoleId = TCR.tdpCommitteeRoleId and TCR.tdpRolesId = TR.tdpRolesId and ");
		quertyStr.append(" TCM.tdpCadreId = TC.tdpCadreId and TC.userAddress.userAddressId = UA.userAddressId and  ");
		quertyStr.append(" TC.casteStateId = CS.casteStateId and CS.caste.casteId = C.casteId and CS.casteCategoryGroup.casteCategoryGroupId = CCG.casteCategoryGroupId and CC.casteCategoryId = CCG.casteCategory.casteCategoryId");
		quertyStr.append(" and TCR.tdpCommitteeId = TCO.tdpCommitteeId and TBC.tdpBasicCommitteeId = TCO.tdpBasicCommitteeId and TC.voterAgeRangeId = VAR.voterAgeRangeId ");
		
		if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0) 
		{
			quertyStr.append(" and TBC.tdpCommitteeType.tdpCommitteeTypeId in (:committeeTypeIdsList) ");
		}
		if(positionIdsList != null && positionIdsList.size()>0) 
		{
			quertyStr.append(" and TR.tdpRolesId in (:positionIdsList) ");
		}
		if(casteCategoryIdsList != null && casteCategoryIdsList.size()>0) 
		{
			quertyStr.append(" and CC.casteCategoryId in (:casteCategoryIdsList) ");
		}
		if(casteCategoryGroupIdsList != null && casteCategoryGroupIdsList.size()>0) 
		{
			quertyStr.append(" and CCG.casteCategoryGroupId in (:casteCategoryGroupIdsList) ");
		}
		if(casteIdsList != null && casteIdsList.size()>0) 
		{
			quertyStr.append(" and CS.casteStateId in (:casteIdsList) ");
		}
		
		if(locationIdsList != null && locationIdsList.size()>0)
		{
			if(locationLevelId != null && locationLevelId.longValue() == 1L) // stateWise
			{
				quertyStr.append(" and UA.state.stateId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 2L) // districtWise
			{
				quertyStr.append(" and UA.district.districtId in (:locationIdsList) ");
			}			
			else if(locationLevelId != null && locationLevelId.longValue() == 3L) // Parliament constituencyWise
			{
				quertyStr.append(" and UA.parliamentConstituency.constituencyId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 4L) // constituencyWise
			{
				quertyStr.append(" and UA.constituency.constituencyId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 5L) // village/ward wise
			{
				quertyStr.append(" and ( UA.panchayat.panchayatId in (:locationIdsList) ");
				
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or UA.ward.constituencyId in (:wardIdsList) ");
				}
				quertyStr.append(" ) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 6L) // mandal/municipality/division wise
			{
				quertyStr.append(" and ( UA.tehsil.tehsilId in (:locationIdsList) ");
				if(wardIdsList != null && wardIdsList.size()>0)
				{
					quertyStr.append(" or UA.localElectionBody.localElectionBodyId in (:wardIdsList) ");
				}
				quertyStr.append(" ) ");
			}
		}
		
		quertyStr.append(" group by VAR.voterAgeRangeId,TC.gender order by VAR.minValue asc ");
		
		Query query = getSession().createQuery(quertyStr.toString());
		
		if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0) 
		{
			query.setParameterList("committeeTypeIdsList", committeeTypeIdsList);
		}
		if(positionIdsList != null && positionIdsList.size()>0) 
		{
			query.setParameterList("positionIdsList", positionIdsList);
		}
		if(casteCategoryIdsList != null && casteCategoryIdsList.size()>0) 
		{
			query.setParameterList("casteCategoryIdsList", casteCategoryIdsList);
		}
		if(casteCategoryGroupIdsList != null && casteCategoryGroupIdsList.size()>0) 
		{
			query.setParameterList("casteCategoryIdsList", casteCategoryGroupIdsList);
		}
		if(casteIdsList != null && casteIdsList.size()>0) 
		{
			query.setParameterList("casteIdsList", casteIdsList);
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
}
