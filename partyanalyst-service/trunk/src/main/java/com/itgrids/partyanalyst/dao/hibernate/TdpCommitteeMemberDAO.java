package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.model.TdpCommitteeMember;
import com.itgrids.partyanalyst.utils.IConstants;

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
		
		Query query = getSession().createQuery("" +
		"select  model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.image,model.tdpCadre.firstname,model.tdpCadre.memberShipNo,model.tdpCommitteeMemberId, " +//4
		"        model.tdpCadre.tdpCadreId,model.tdpCommitteeRole.tdpCommitteeRoleId,model.status " +//7
		"from    TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommitteeRoleId in(:committeeRoleIds)  and model.isActive ='Y'  " +
		"order by model.tdpCommitteeRole.tdpRoles.order ");
		query.setParameterList("committeeRoleIds", committeeRoleIds);
		
		return query.list();
	}
	
	public List<Object[]> getAllCommitteeMembersInfoInALoc(Long locationLvl,Long locationVal){
		
		Query query = getSession().createQuery("" +
				" select model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.image,model.tdpCadre.firstname,model.tdpCadre.memberShipNo,model.tdpCommitteeMemberId, " +//4
				"        model.tdpCadre.tdpCadreId,model.tdpCommitteeRole.tdpCommitteeRoleId,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name,model.status " +//8
				" from   TdpCommitteeMember model " +
				" where  model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId =:locationLvl " +
				"        and  model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationVal and model.isActive ='Y' " +
				"        and  model.tdpCommitteeEnrollmentId =:committeeEnrollmentId  " +
				" order by model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId, model.tdpCommitteeRole.tdpRoles.order ");
		query.setParameter("locationLvl", locationLvl);
		query.setParameter("locationVal", locationVal);
		query.setParameter("committeeEnrollmentId", IConstants.CURRENT_ENROLLMENT_ID);
		return query.list();
	}
	
	public List<Object[]> getMemberInfo(Long tdpCadreId){

		Query query = getSession().createQuery("" +
				" select  model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId, " +
				"         model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,  " +
				"         model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId,  " +
				"         model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId, " +
				"         model.tdpCommitteeRole.tdpCommitteeRoleId, model.tdpCommitteeRole.tdpRoles.role    " +
				" from    TdpCommitteeMember model " +
			    " where   model.tdpCadreId =:tdpCadreId and model.isActive ='Y' and model.tdpCommitteeEnrollmentId = :tdpCommitteeEnrollmentId ");
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("tdpCommitteeEnrollmentId", IConstants.CURRENT_ENROLLMENT_ID);
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
		Query query = getSession().createQuery("select model from TdpCommitteeMember model where model.tdpCadreId =:tdpCadreId  and model.isActive ='Y' and model.tdpCommitteeEnrollmentId = :tdpCommitteeEnrollmentId ");
		query.setParameter("tdpCadreId", tdpCadreId);
		query.setParameter("tdpCommitteeEnrollmentId", IConstants.CURRENT_ENROLLMENT_ID);
		return query.list();
	}
	
	//GET MEMBER DETAILS BY CADREIDS
	public List<Object[]> getTdpCommitteeMemberForTdpCadreIdList(List<Long> tdpCadreIdsList)
	{
		String queryStr = " " +
		" select   TD.tdp_cadre_id, TBC.name, TR.role , TC.tdp_committee_level_id , TC.tdp_committee_level_value, " +
		"          TCR.tdp_committee_role_id ,TCL.tdp_committee_level  " +
		" from     tdp_committee_member TCM, tdp_committee_role TCR, tdp_committee TC, tdp_cadre TD, tdp_roles TR, tdp_basic_committee TBC,tdp_committee_level TCL " +
		" where    TD.tdp_cadre_id = TCM.tdp_cadre_id and TCM.tdp_committee_role_id = TCR.tdp_committee_role_id and " +
		"          TCR.tdp_roles_id = TR.tdp_roles_id and TCR.tdp_committee_id = TC.tdp_committee_id and " +
		"          TC.tdp_basic_committee_id = TBC.tdp_basic_committee_id and TC.tdp_committee_level_id = TCL.tdp_committee_level_id and " +
		"          TCM.tdp_cadre_id in (:tdpCadreIdsList) and TCM.is_active='Y'" +
		" group by TCM.tdp_cadre_id ";
		Query query = getSession().createSQLQuery(queryStr);
		query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
	
	public List<Object[]> getTdpCommitteeMemberForTdpCadreIdList(List<Long> tdpCadreIdsList , Long committeeEnrollmentId)
	{
		String queryStr = " " +
		" select   TD.tdp_cadre_id, TBC.name, TR.role , TC.tdp_committee_level_id , TC.tdp_committee_level_value, " +//4
		"          TCR.tdp_committee_role_id ,TCL.tdp_committee_level, TCM.status " +//7
		" from     tdp_committee_member TCM, tdp_committee_role TCR, tdp_committee TC, tdp_cadre TD, tdp_roles TR, tdp_basic_committee TBC,tdp_committee_level TCL " +
		" where    TD.tdp_cadre_id = TCM.tdp_cadre_id and TCM.tdp_committee_role_id = TCR.tdp_committee_role_id and " +
		"          TCR.tdp_roles_id = TR.tdp_roles_id and TCR.tdp_committee_id = TC.tdp_committee_id and " +
		"          TC.tdp_basic_committee_id = TBC.tdp_basic_committee_id and TC.tdp_committee_level_id = TCL.tdp_committee_level_id and " +
		"          TCM.tdp_cadre_id in (:tdpCadreIdsList) and TCM.is_active='Y' and TCM.tdp_committee_enrollment_id = :committeeEnrollmentId " +
		" group by TCM.tdp_cadre_id ";
		Query query = getSession().createSQLQuery(queryStr);
		query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		query.setParameter("committeeEnrollmentId",committeeEnrollmentId);
		return query.list();
	}
	//
	public List<Object[]> getStartedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList,
			List<Long> enrollmentIdsList){

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
		if(enrollmentIdsList != null && enrollmentIdsList.size()>0){
			str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeEnrollmentId in (:enrollmentIdsList) ");
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
		if(enrollmentIdsList != null && enrollmentIdsList.size()>0)
			query.setParameterList("enrollmentIdsList", enrollmentIdsList);
		
		return query.list();
	}

	public Long getMembersCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate ,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList ,List<Long> enrollmentIdsList){

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
		if(enrollmentIdsList != null && enrollmentIdsList.size()>0)
		{
			str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeEnrollmentId in (:enrollmentIdsList) ");
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
		if(enrollmentIdsList != null && enrollmentIdsList.size()>0)
		{
			query.setParameterList("enrollmentIdsList", enrollmentIdsList);
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
	public List<Object[]> getComitteeMembersByCommiteTypeAndLocation(Long levelId,List<Long> locationVals,Long committeeTypeId,String status,List<Long> committeeEnrollmentIdsLst,Date stDate,Date edDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId),model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue" +
				" from TdpCommitteeMember model" +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in(:locationVals)" +
				" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId and model.isActive = 'Y' and " +
				" model.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in(:committeeEnrollmentIdsLst) ");
		if(status.equalsIgnoreCase("Conform"))
		str.append("  and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'Y' and model.tdpCommitteeRole.tdpCommittee.completedDate is not null ");
		else if(status.equalsIgnoreCase("Started"))
		str.append(" and model.tdpCommitteeRole.tdpCommittee.startedDate is not null  and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'N' and model.tdpCommitteeRole.tdpCommittee.completedDate is null ");
		if(stDate != null && edDate != null){
		str.append( " and ( ( date(model.tdpCommitteeRole.tdpCommittee.startedDate) between :stDate and :edDate )  OR  ( date(model.tdpCommitteeRole.tdpCommittee.completedDate) between :stDate and :edDate ) ) ");
		}
		str.append(" group by model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameterList("locationVals", locationVals);
		query.setParameter("committeeTypeId", committeeTypeId);
		query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		if(stDate != null && edDate != null){
			query.setParameter("stDate", stDate);
		    query.setParameter("edDate", edDate);
		}
		return query.list();
			
	}
	public List<Object[]> getComitteeMembersInfoByCommiteTypeAndLocation(Long levelId,Long locationVal,Long committeeTypeId,String status,List<Long> committeeEnrollmentIdsLst,Date startDate,Date endDate)
	{
		String[] dataArr = status.split(":");
		StringBuilder str = new StringBuilder();
		//19tehsilId, 20localElectionBodyId 21constituencyId
		str.append("select model.tdpCommitteeRole.tdpCommitteeRoleId,model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname,model.tdpCadre.image," +
				"model.tdpCadre.memberShipNo,model.tdpCommitteeMemberId,model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed," +
				" model.tdpCadre.casteState.caste.casteName,model.tdpCadre.gender,model.tdpCadre.age ," +
				"model.tdpCadre.dateOfBirth,model.tdpCadre.casteState.casteCategoryGroup.casteCategory.categoryName, " +
				"model.tdpCadre.mobileNo,model.tdpCadre.voterId,model.tdpCadre.familyVoterId, " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name," +
				"constituency.name,model.tdpCadre.casteState.casteStateId  " +
				" ,tehsil.tehsilId,leb.localElectionBodyId,ward.constituencyId,model.status  " +
				" from TdpCommitteeMember model " +
				"left join model.tdpCadre.userAddress.constituency constituency  " +
				"left join model.tdpCadre.userAddress.tehsil tehsil " +
				" left join model.tdpCadre.userAddress.localElectionBody leb " +
				"left join model.tdpCadre.userAddress.ward ward " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  and model.isActive = 'Y' " +
				"and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationVal and " +
				" model.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in(:committeeEnrollmentIdsLst) ");
		if(committeeTypeId.longValue() !=0L)
			str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId ");
		else 
			str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId != 1 ");
		
		if(dataArr[0].toString().equalsIgnoreCase("Conform")){
			str.append("  and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'Y' and model.tdpCommitteeRole.tdpCommittee.completedDate is not null ");
		}else if(dataArr[0].toString().equalsIgnoreCase("Started")){
			str.append("  and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'N' and model.tdpCommitteeRole.tdpCommittee.completedDate is  null and model.tdpCommitteeRole.tdpCommittee.startedDate is not null ");
		}else if(dataArr[0].toString().equalsIgnoreCase("NotStarted")){
			str.append("  and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'N' and model.tdpCommitteeRole.tdpCommittee.completedDate is  null and model.tdpCommitteeRole.tdpCommittee.startedDate is  null ");
		}
		if(startDate != null && endDate != null && !dataArr[0].toString().equalsIgnoreCase("NotStarted")){
			str.append( " and (" +
					" (date(model.tdpCommitteeRole.tdpCommittee.startedDate) between :startDate and :endDate )  OR  (date(model.tdpCommitteeRole.tdpCommittee.completedDate) between :startDate and :endDate ) " +
					" )" );
			//str.append(" and date(model.tdpCommitteeRole.tdpCommittee.startedDate) between :startDate and :endDate ");
			}
			
		str.append(" order by model.tdpCommitteeRole.tdpRoles.tdpRolesId  ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("locationVal", locationVal);
		if(committeeTypeId.longValue() !=0L)
			query.setParameter("committeeTypeId", committeeTypeId);
		query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		if(startDate != null && endDate != null && !dataArr[0].toString().equalsIgnoreCase("NotStarted")){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
			
	}
	
	public List<Long> getTdpCommitteIds(Long levelId,Long locationVal,Long committeeTypeId,List<Long> committeeEnrollmentIdsLst,Date stDate,Date edDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId from TdpCommitteeMember model" +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationVal" +
				" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId and model.isActive ='Y' and " +
				" model.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in(:committeeEnrollmentIdsLst) ");
		if(stDate != null && edDate != null){
			str.append( " and ( ( date(model.tdpCommitteeRole.tdpCommittee.startedDate) between :stDate and :edDate )  OR  ( date(model.tdpCommitteeRole.tdpCommittee.completedDate) between :stDate and :edDate ) ) ");
		}
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("locationVal", locationVal);
		query.setParameter("committeeTypeId", committeeTypeId);
		if(stDate != null && edDate != null){
			query.setParameter("stDate", stDate);
		    query.setParameter("edDate", edDate);
		}
		
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size() > 0){
			query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		}
		return query.list();
			
	}
	public Integer updateTdpComitte(List<Long> tdpCommitteeIds)
	{
		Query query = getSession().createQuery("update TdpCommittee model set model.isCommitteeConfirmed = 'Y' where model.tdpCommitteeId in(:tdpCommitteeIds) ");
		query.setParameterList("tdpCommitteeIds", tdpCommitteeIds);
		
		return query.executeUpdate();	
	}
	
	public Integer deleteCadreRole(Long Id,List<Long> committeeEnrollmentIdsLst,Date stDate, Date edDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("update TdpCommitteeMember model set model.isActive = 'N' where model.tdpCommitteeMemberId =:Id ");// and " +
				/*" model.tdpCommitteeEnrollmentId in(:committeeEnrollmentIdsLst) ");
		if(stDate != null && edDate != null){
			str.append( " and ( ( date(model.tdpCommitteeRole.tdpCommittee.startedDate) between :stDate and :edDate )  OR  ( date(model.tdpCommitteeRole.tdpCommittee.completedDate) between :stDate and :edDate ) ) ");
		}*/
		Query query = getSession().createQuery(str.toString());
		query.setParameter("Id", Id);
		/*query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		if(stDate != null && edDate != null){
			query.setParameter("stDate", stDate);
		    query.setParameter("edDate", edDate);
		}*/
		
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
	
	public List<Object[]> membersCountDistrictWise(List<Long> levelIds, Date startDate, Date endDate, List<Long> districtIds,List<Long> committeeSpanTypeIdsList){
		 
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.district.districtId, model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId " +
				" from TdpCommitteeMember model " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in(:levelIds)" +
				" and model.tdpCommitteeRole.tdpCommittee.district.districtId in(:districtIds)" +
				//" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 1" +
				" and model.isActive = 'Y' ");
		if(startDate!=null && endDate!=null){
			sb.append(" and  date(model.tdpCommitteeRole.tdpCommittee.startedDate) between  :startDate and :endDate ");
		}
		/*if(endDate!=null){
			sb.append(" and date(model.tdpCommitteeRole.tdpCommittee.startedDate) <= :endDate ) ");
		}*/
		if(committeeSpanTypeIdsList != null && committeeSpanTypeIdsList.size()>0){
			sb.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeEnrollmentId in (:committeeSpanTypeIdsList) ");
		}
		sb.append(" group by model.tdpCommitteeRole.tdpCommittee.district.districtId,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId " +
				" order by model.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameterList("levelIds", levelIds);
		if(startDate!=null && endDate!=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		/*if(endDate!=null){
			query.setParameter("endDate", endDate);
		}*/
		if(committeeSpanTypeIdsList != null && committeeSpanTypeIdsList.size()>0)
			query.setParameterList("committeeSpanTypeIdsList", committeeSpanTypeIdsList);
		query.setParameterList("districtIds", districtIds);
		
		return query.list();
	}
	
	public List<Object[]> getCommitteStatusAndId(Long tdpCommitteMemberId,List<Long> committeeEnrollmentIdsLst,Date stDate,Date edDate)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId from  TdpCommitteeMember model where model.tdpCommitteeMemberId =:tdpCommitteMemberId and " +
				" model.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in(:committeeEnrollmentIdsLst) ");
		if(stDate != null && edDate != null){
			sb.append( " and ( ( date(model.tdpCommitteeRole.tdpCommittee.startedDate) between :stDate and :edDate )  OR  ( date(model.tdpCommitteeRole.tdpCommittee.completedDate) between :stDate and :edDate ) ) ");
		}
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("tdpCommitteMemberId", tdpCommitteMemberId);
		query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		if(stDate != null && edDate != null){
			query.setParameter("stDate", stDate);
		    query.setParameter("edDate", edDate);
		}
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
	
	
	
	
	public List<Object[]> getMembersCountInCommitteeByLocation(String stateId,List<Long> levelIds,Long committeeId,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList,List<Long> committeeSpanTypeIdsLsit){
		
		StringBuilder str = new StringBuilder();
		if(stateId.trim().equalsIgnoreCase("AP")){
			stateId="1";
		}else if(stateId.trim().equalsIgnoreCase("TS")){
			stateId="36";
		}		
		str.append("select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId," +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name " +				
				" from TdpCommitteeMember model where ");
		if(stateId != null)
		{
			str.append(" model.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId= :stateId ");
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
				" and model.tdpCommitteeRole.tdpCommittee.completedDate is not null and model.tdpCommitteeRole.tdpCommittee.startedDate is not null"); 
		if(committeeSpanTypeIdsLsit != null && committeeSpanTypeIdsLsit.size()>0)
			str.append(" and  model.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in(:committeeSpanTypeIdsLsit) ");
		str.append(" group by model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId," +
					" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue ");
		
       			
		Query query = getSession().createQuery(str.toString());
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
	  if(stateId != null)
		{
			query.setParameter("stateId", Long.valueOf(stateId));
		}
		if(committeeSpanTypeIdsLsit != null && committeeSpanTypeIdsLsit.size()>0)
		{
			query.setParameterList("committeeSpanTypeIdsLsit", committeeSpanTypeIdsLsit);
		}
        return query.list();
    }
	public Long getCommitteMembers(Long tdpCommitteeId)
	{
		Query query = getSession().createQuery("select count(model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId) from  TdpCommitteeMember model where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId =:tdpCommitteeId and model.isActive = 'Y' ");
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
	
	
public List<Object[]> membersCountConstituencyWise(List<Long> levelIds, Date startDate, Date endDate, List<Long> constiIds,String reqLocationTypeStr, List<Long>  committeeEnrollmentIdsLst){
		
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
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
			sb.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeEnrollmentId in (:committeeEnrollmentIdsLst) ");
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
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
			query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		}
		
		return query.list();
	}

public List<Object[]> totalMainMembersCountLocationsWise1(Long levelId, Date startDate, Date endDate,List<Long> levelValues,String reqLocationTypeStr,List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsList){
	//0 count,1levelId
//	levelValues.clear();
//	levelValues.add(195L);
	StringBuilder sb = new StringBuilder();
	sb.append(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId " +
			" from TdpCommitteeMember model " +
			" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId = :levelId and model.isActive ='Y' " +
			" and model.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:levelValues) ");
			//" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 1l ");
	
	if(startDate!=null){
		sb.append(" and date(model.insertedTime) >= :startDate ");
	}
	if(endDate!=null){
		sb.append(" and date(model.insertedTime) <= :endDate");
	}
	if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
		sb.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeEnrollmentId in (:committeeEnrollmentIdsLst) ");
	}
	sb.append(" group by model.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId ");
	
	Query query = getSession().createQuery(sb.toString());
	
	query.setParameter("levelId", levelId);
	if(startDate!=null){
		query.setParameter("startDate", startDate);
	}
	if(endDate!=null){
		query.setParameter("endDate", endDate);
	}
	
	query.setParameterList("levelValues",levelValues);
	if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
		query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
	}
	
	return query.list();
}


public List<Object[]> totalMainMembersCountLocationsWise(Long levelId, Date startDate, Date endDate,List<Long> levelValues,String reqLocationTypeStr,List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsList){
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
	if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
		sb.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeEnrollmentId in (:committeeEnrollmentIdsLst) ");
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
	if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
		query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
	}
	
	return query.list();
}

public List<Object[]> getCommitteeMembersCountByLocationAndCommitteeType(Long levelId,List<Long> locationVals,Long committeeTypeId,List<Long> enrollIdsList,Date startDate,Date endDate){
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
	if(committeeTypeId == 1)
	str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId =1");
	if(committeeTypeId == 2)
		str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId !=1");
	if(enrollIdsList != null && enrollIdsList.size() > 0l)
		str.append(" and model.tdpCommitteeEnrollmentId in (:enrollIdsList)");
	if(startDate != null && endDate != null){
		str.append( " and (" +
				" (date(model.tdpCommitteeRole.tdpCommittee.startedDate) between :startDate and :endDate )  OR  (date(model.tdpCommitteeRole.tdpCommittee.completedDate) between :startDate and :endDate ) " +
				" )" );
		}
		
		//str.append(" and date(model.tdpCommitteeRole.tdpCommittee.startedDate) between :startDate and :endDate ");
	str.append(" group by model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId," +
			" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue," +
			" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId");
	
	Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameterList("locationVals", locationVals);
	if(enrollIdsList != null && enrollIdsList.size() > 0l)
		query.setParameterList("enrollIdsList", enrollIdsList);
	if(startDate != null && endDate != null){
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
	}
	return query.list();
		
}
	public List<Object[]> getCommitteePresidentAndVicePresidentsCount(List<Long> locationIds, Long locationLevel,Long committeeTypeId,List<Long> enrollIdsList,Date startDate,Date endDate){
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
		if(committeeTypeId == 1)
			str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId =1");
		if(committeeTypeId == 2)
				str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId !=1");
		if(enrollIdsList != null && enrollIdsList.size() > 0l)
			str.append(" and model.tdpCommitteeEnrollmentId in (:enrollIdsList)");
		if(startDate != null && endDate != null){
			str.append( " and (" +
					" (date(model.tdpCommitteeRole.tdpCommittee.startedDate) between :startDate and :endDate )  OR  (date(model.tdpCommitteeRole.tdpCommittee.completedDate) between :startDate and :endDate ) " +
					" )" );
			}
			//str.append(" and date(model.tdpCommitteeRole.tdpCommittee.startedDate) between :startDate and :endDate");
		str.append(" group by " +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue," +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId");
		
		Query query = getSession().createQuery(str.toString());
			query.setParameter("levelId", locationLevel);
			query.setParameterList("locationVals", locationIds);
		if(enrollIdsList != null && enrollIdsList.size() > 0l)
			query.setParameterList("enrollIdsList", enrollIdsList);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		
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

public List<Object[]> getCommitteePresidentAndGS(List<Long> locationIds, Long locationLevel,Long committeeTypeId,List<Long> enrollIdsList,Date startDate,Date endDate){
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
	if(committeeTypeId == 1)
		str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId =1");
	if(committeeTypeId == 2)
			str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId !=1");
	if(enrollIdsList != null && enrollIdsList.size() > 0l)
		str.append(" and model.tdpCommitteeEnrollmentId in (:enrollIdsList)");
	if(startDate != null && endDate != null){
		str.append( " and ( " +
				"(date(model.tdpCommitteeRole.tdpCommittee.startedDate) between :startDate and :endDate )  OR  (date(model.tdpCommitteeRole.tdpCommittee.completedDate) between :startDate and :endDate )  " +
				" )" );
		}
		
	/*str.append(" group by" +
			"  model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId, " +
			" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue ");*/
	
	Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", locationLevel);
		query.setParameterList("locationVals", locationIds);
	if(enrollIdsList != null && enrollIdsList.size() > 0l)
		query.setParameterList("enrollIdsList", enrollIdsList);
	if(startDate != null && endDate != null){
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
	}
	return query.list();
}

public List<Object[]> getAllMembersInMainCommWithPresidentAndGeneralSecretaryRole(Long locationType,Long locationVal,String committeeMemberStatus){
	StringBuilder sb = new StringBuilder();
	
	sb.append("" +
	"select distinct model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name,model.tdpCadre.firstname,model.tdpCadre.image,model.tdpCadre.memberShipNo, " +//3
	"       model.tdpCadre.tdpCadreId , model.status " +//5
	"from   TdpCommitteeMember model " +
	"where  model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId =:locationType " +
	"       and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationVal and model.tdpCommitteeRole.tdpRoles.tdpRolesId in(1,3)  and model.isActive ='Y'  " +
	"       and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId = 1 and model.tdpCommitteeEnrollmentId =:committeeEnrollmentId ");
	if(committeeMemberStatus != null){
	   sb.append(" and model.status = :committeeMemberStatus ");	
	}
	sb.append(" order by model.tdpCommitteeRole.tdpRoles.order ");
	Query query = getSession().createQuery( sb.toString());
	query.setParameter("locationType", locationType);
	query.setParameter("locationVal", locationVal);
	query.setParameter("committeeEnrollmentId", IConstants.CURRENT_ENROLLMENT_ID);
	if(committeeMemberStatus != null){
		query.setParameter("committeeMemberStatus", committeeMemberStatus);	
	}
	return query.list();
}


public List<Object[]> getStartedCommitteesMembersCountByLocation(String stateId,List<Long> levelIds,Long committeeId,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList,List<Long> committeeSpanTypeIdsLsit ){
	
	StringBuilder str = new StringBuilder();
	
	if(stateId.trim().equalsIgnoreCase("AP")){
		stateId="1";
	}else if(stateId.trim().equalsIgnoreCase("TS")){
		stateId="36";
	}
	
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
	else if(stateId != null)
	{
		str.append(" model.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId= :stateId ");
	}
	
	if(startDate !=null && endDate !=null){
		str.append(" and ( date(model.tdpCommitteeRole.tdpCommittee.startedDate)>=:startDate and date(model.tdpCommitteeRole.tdpCommittee.startedDate)<=:endDate ) " );
	}
	
	str.append("and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
			" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId = :committeeId" +
			" and model.tdpCommitteeRole.tdpCommittee.completedDate is null  and model.isActive = 'Y' and model.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed = 'N' "); 
	if(committeeSpanTypeIdsLsit != null && committeeSpanTypeIdsLsit.size()>0)
		str.append(" and model.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in (:committeeSpanTypeIdsLsit) ");
	
	str.append(" group by model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId," +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue ");
	
	
	Query query = getSession().createQuery(str.toString());
	
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
	else if(stateId != null)
	{
		query.setParameter("stateId", Long.valueOf(stateId));
	}
	if(committeeSpanTypeIdsLsit != null && committeeSpanTypeIdsLsit.size()>0)
	{
		query.setParameterList("committeeSpanTypeIdsLsit", committeeSpanTypeIdsLsit);
	}
    return query.list();
}
	
	public List<Object[]> getCommitteeRolesGenderWiseDetailsByLocation(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,
			List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,
			String segrigatStr,Long descriptionLevelId,List<Long> enrollIdsList,Date startDate,Date endDate)
	{
		StringBuilder quertyStr = new StringBuilder();
		Long enrollId =enrollIdsList != null && enrollIdsList.size()>0 ?enrollIdsList.get(0):0L;
		quertyStr.append(" select distinct TC.tdpCadre.gender , count(distinct TC.tdpCadreId) ");
		
		if(descriptionLevelId != null && descriptionLevelId.longValue() == 2L) // districtWise
		{
			quertyStr.append(", TCO.userAddress.district.districtId,TCO.userAddress.district.districtName ");
		}
		else if(descriptionLevelId != null && descriptionLevelId.longValue() == 3L) // constituencyWise
		{
			quertyStr.append(", TCO.userAddress.constituency.constituencyId,TCO.userAddress.constituency.name ");
		}
		
		quertyStr.append(" from TdpCadreEnrollmentYear TC, TdpCommitteeMember TCM, TdpCommitteeRole TCR, TdpRoles TR , TdpCommittee TCO,TdpBasicCommittee TBC ");
		quertyStr.append(" where ");
		quertyStr.append(" TCM.tdpCommitteeRoleId = TCR.tdpCommitteeRoleId and TCR.tdpRolesId = TR.tdpRolesId and TCM.tdpCadreId = TC.tdpCadre.tdpCadreId ");
		quertyStr.append(" and TCR.tdpCommitteeId = TCO.tdpCommitteeId and TBC.tdpBasicCommitteeId = TCO.tdpBasicCommitteeId ");
		quertyStr.append(" and TCM.isActive ='Y' ");
		quertyStr.append(" and TC.tdpCadre.isDeleted = 'N' and TC.tdpCadre.enrollmentYear = 2014 ");
		if(enrollIdsList != null && enrollIdsList.size()>0)
		{
			/*if(enrollIdsList.contains(1L)){
				enrollId = 3l;
			}else if(enrollIdsList.contains(2L)){
				enrollId = 4l;
			}*/
			quertyStr.append(" and TCO.tdpCommitteeEnrollmentId =:enrollId ");
		}
		if(userAccessType != null && userAccessType.equalsIgnoreCase("TS"))
		{
			quertyStr.append(" and (TCO.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+")) ");
		}
		else if(userAccessType != null && userAccessType.equalsIgnoreCase("AP"))
		{
			quertyStr.append(" and (TCO.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+")) ");
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
				quertyStr.append(" and TCO.userAddress.district.state.stateId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 2L) // districtWise
			{
				quertyStr.append(" and TCO.userAddress.district.districtId in (:locationIdsList) ");
			}			
			else if(locationLevelId != null && locationLevelId.longValue() == 3L) // constituencyWise
			{
				quertyStr.append(" and TCO.userAddress.constituency.constituencyId in (:locationIdsList) ");
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
		/*if(enrollIdsList != null && enrollIdsList.size() > 0l)
			quertyStr.append(" and TCM.tdpCommitteeEnrollmentId in (:enrollIdsList)");*/
		
		if(startDate != null && endDate != null){
			quertyStr.append( " and (" +
					" (date(TCO.startedDate) between :startDate and :endDate )  OR  (date(TCO.completedDate) between :startDate and :endDate ) " +
					" )" );
			}
		quertyStr.append(" group by  ");
		if(descriptionLevelId != null && descriptionLevelId.longValue() == 2L) // districtWise
		{
			quertyStr.append(" TCO.userAddress.district.districtId, ");
		}
		else if(descriptionLevelId != null && descriptionLevelId.longValue() == 3L) // constituencyWise
		{
			quertyStr.append(" TCO.userAddress.constituency.constituencyId, ");
		}
		quertyStr.append(" TC.tdpCadre.gender ");
		
		if(descriptionLevelId != null && descriptionLevelId.longValue() == 2L) // districtWise
		{
			quertyStr.append(" order by  TCO.userAddress.district.districtName asc ");
		}
		else if(descriptionLevelId != null && descriptionLevelId.longValue() == 3L) // constituencyWise
		{
			quertyStr.append(" order by TCO.userAddress.constituency.name asc  ");
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
		if(enrollIdsList != null && enrollIdsList.size() > 0l)
			query.setParameter("enrollId", enrollId);
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	public List<Object[]> getCommitteeRoleAgerangeWiseDetailsByLocationType(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr,List<Long> enrollIdsList,Date startDate,Date endDate)
	{
		StringBuilder quertyStr = new StringBuilder();
		Long enrollId = 0l;
		quertyStr.append(" select VAR.voterAgeRangeId ,VAR.ageRange,TC.tdpCadre.gender , count(TC.tdpCadreId) ");
		quertyStr.append(" from TdpCadreEnrollmentYear TC, TdpCommitteeMember TCM, TdpCommitteeRole TCR, TdpRoles TR , TdpCommittee TCO,TdpBasicCommittee TBC,VoterAgeRange VAR ");
		quertyStr.append(" where ");
		quertyStr.append(" TCM.tdpCommitteeRoleId = TCR.tdpCommitteeRoleId and TCR.tdpRolesId = TR.tdpRolesId and TCM.tdpCadreId = TC.tdpCadre.tdpCadreId and TC.tdpCadre.voterAgeRangeId = VAR.voterAgeRangeId and  ");
		quertyStr.append(" TCR.tdpCommitteeId = TCO.tdpCommitteeId and TBC.tdpBasicCommitteeId = TCO.tdpBasicCommitteeId ");
		quertyStr.append(" and TCM.isActive ='Y' ");
		quertyStr.append(" and TC.tdpCadre.isDeleted = 'N' and TC.tdpCadre.enrollmentYear = 2014 ");
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
		if(enrollIdsList != null && enrollIdsList.size() > 0l){
			if(enrollIdsList.equals(1)){
				enrollId = 3l;
			}else{
				enrollId = 4l;
			}
			quertyStr.append(" and TCO.tdpCommitteeEnrollmentId = :enrollId ");
			//quertyStr.append(" and TCO.tdpCommitteeEnrollmentId in (:enrollIdsList) ");
		}
			
		
		if(startDate != null && endDate != null){
			quertyStr.append( " and (" +
					" (date(TCO.startedDate) between :startDate and :endDate )  OR  (date(TCO.completedDate) between :startDate and :endDate ) " +
					" )" );
			}
			
			
		quertyStr.append(" group by VAR.voterAgeRangeId,TC.tdpCadre.gender order by VAR.minValue asc");
		
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
		if(enrollIdsList != null && enrollIdsList.size() > 0l)
		{
			query.setParameter("enrollId", enrollId);
		}
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	public List<Object[]> getCommitteeRoleCasteNameWiseDetailsByLocationType(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr,List<Long> enrollIdsList,Date startDate,Date endDate)
	{
		StringBuilder quertyStr = new StringBuilder();
		
		quertyStr.append(" select CS.casteStateId ,C.casteName,TC.tdpCadre.gender , count(TC.tdpCadreId) ");
		quertyStr.append(" from TdpCadreEnrollmentYear TC, TdpCommitteeMember TCM, TdpCommitteeRole TCR, TdpRoles TR , TdpCommittee TCO,TdpBasicCommittee TBC , ");
		quertyStr.append(" CasteState CS, Caste C ");
		quertyStr.append(" where ");
		quertyStr.append(" TCM.tdpCommitteeRoleId = TCR.tdpCommitteeRoleId and TCR.tdpRolesId = TR.tdpRolesId and TCM.tdpCadreId = TC.tdpCadre.tdpCadreId ");
		quertyStr.append(" and TCR.tdpCommitteeId = TCO.tdpCommitteeId and TBC.tdpBasicCommitteeId = TCO.tdpBasicCommitteeId ");
		quertyStr.append(" and TC.tdpCadre.casteStateId = CS.casteStateId and CS.caste.casteId = C.casteId ");
		quertyStr.append(" and TCM.isActive ='Y' ");
		quertyStr.append(" and TC.tdpCadre.isDeleted = 'N' and TC.tdpCadre.enrollmentYear = 2014 ");
		
		if(enrollIdsList != null && enrollIdsList.size()>0)
		{
			
			quertyStr.append(" and TCO.tdpCommitteeEnrollmentId in (:enrollIdsList)  ");
		}
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
		/*if(enrollIdsList != null && enrollIdsList.size() > 0l)
			quertyStr.append(" and TCM.tdpCommitteeEnrollmentId in (:enrollIdsList)");*/
		if(startDate != null && endDate != null){
			quertyStr.append( " and (" +
					" (date(TCO.startedDate) between :startDate and :endDate )  OR  (date(TCO.completedDate) between :startDate and :endDate ) " +
					" )" );
			}
			
		quertyStr.append(" group by CS.casteStateId,TC.tdpCadre.gender order by C.casteName asc");
		
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
		if(enrollIdsList != null && enrollIdsList.size() > 0l)
		{
			query.setParameterList("enrollIdsList", enrollIdsList);
		}
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	public List<Object[]> getCommitteeRoleCasteCategoryNameWiseDetailsByLocationType(List<Long> positionIdsList,Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,List<Long> committeeTypeIdsList,String userAccessType,String segrigatStr,List<Long> enrollIdsList,Date startDate,Date endDate)
	{
		StringBuilder quertyStr = new StringBuilder();
		//Long enrollId = 0l;
		quertyStr.append(" select distinct CC.casteCategoryId ,CC.categoryName,TC.tdpCadre.gender , count(TC.tdpCadreId) ");
		quertyStr.append(" from TdpCommitteeMember TCM, TdpCommitteeRole TCR, TdpRoles TR, TdpCadreEnrollmentYear TC, CasteState CS, Caste C, CasteCategoryGroup CCG,CasteCategory CC ");
		quertyStr.append(" , TdpCommittee TCO,TdpBasicCommittee TBC where TCM.tdpCommitteeRoleId = TCR.tdpCommitteeRoleId and TCR.tdpRolesId = TR.tdpRolesId and ");
		quertyStr.append(" TCM.tdpCadreId = TC.tdpCadre.tdpCadreId and ");
		quertyStr.append(" TC.tdpCadre.casteStateId = CS.casteStateId and CS.caste.casteId = C.casteId and CS.casteCategoryGroup.casteCategoryGroupId = CCG.casteCategoryGroupId and CC.casteCategoryId = CCG.casteCategory.casteCategoryId");
		quertyStr.append(" and TCR.tdpCommitteeId = TCO.tdpCommitteeId and TBC.tdpBasicCommitteeId = TCO.tdpBasicCommitteeId  ");

		quertyStr.append(" and CS.casteStateId not in (459,301,292,430) and TCM.isActive ='Y' ");
		quertyStr.append(" and TC.tdpCadre.isDeleted = 'N' and TC.tdpCadre.enrollmentYear = 2014 ");
		if(enrollIdsList != null && enrollIdsList.size()>0) 
		{
			quertyStr.append(" and TCO.tdpCommitteeEnrollmentId in (:enrollIdsList) ");
		}
		if(userAccessType != null && userAccessType.equalsIgnoreCase("TS"))
		{
			quertyStr.append(" and (TCO.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ) ");
		}
		else if(userAccessType != null && userAccessType.equalsIgnoreCase("AP"))
		{
			quertyStr.append(" and (TCO.userAddress.district.districtId  in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+")) ");
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
				quertyStr.append(" and TCO.userAddress.district.state.stateId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 2L) // districtWise
			{
				quertyStr.append(" and TCO.userAddress.district.districtId in (:locationIdsList) ");
			}			
			else if(locationLevelId != null && locationLevelId.longValue() == 4L) // Parliament constituencyWise
			{
				quertyStr.append(" and UA.parliamentConstituency.constituencyId in (:locationIdsList) ");
			}
			else if(locationLevelId != null && locationLevelId.longValue() == 3L) // constituencyWise
			{
				quertyStr.append(" and TCO.userAddress.constituency.constituencyId in (:locationIdsList) ");
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
		/*if(enrollIdsList != null && enrollIdsList.size() > 0l)
			quertyStr.append(" and TCM.tdpCommitteeEnrollmentId in (:enrollIdsList)");*/
		if(startDate != null && endDate != null){
			quertyStr.append( " and (" +
					" (date(TCO.startedDate) between :startDate and :endDate )  OR  (date(TCO.completedDate) between :startDate and :endDate ) " +
					" )" );
			}
		quertyStr.append(" group by CC.casteCategoryId,TC.tdpCadre.gender order by TC.tdpCadre.gender asc");
		
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
		if(enrollIdsList != null && enrollIdsList.size() > 0l)
		{
			query.setParameterList("enrollIdsList", enrollIdsList);
		}
		if(startDate != null && endDate != null)
		{
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	public List<Object[]> getCasteCategoryInfoForLocations(Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,String userAccessType, String segrigatStr,String searchType,List<Long> enrollmentIdsList)
	{
		StringBuilder quertyStr = new StringBuilder(); 
		quertyStr.append(" select ");

		quertyStr.append(" TCCI.location_id, TCCI.caste_category_id, TCCI.count  from tdp_cadre_caste_info TCCI where TCCI.caste_state_id not in (459,301,292,430)  ");
		
		if(userAccessType != null)
		{
			if(userAccessType.equalsIgnoreCase("AP"))
			{
				quertyStr.append(" and (TCCI.location_type like '%District%' and TCCI.location_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") )  ");
			}
			else if(userAccessType.equalsIgnoreCase("TS"))
			{
				quertyStr.append(" and (TCCI.location_type like '%District%' and TCCI.location_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") )  ");
			}
			else if(userAccessType.equalsIgnoreCase("ALL") || userAccessType.equalsIgnoreCase("STATE"))
			{
				quertyStr.append(" and (TCCI.location_type like '%District%' and TCCI.location_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") )  ");
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
		if(enrollmentIdsList != null && enrollmentIdsList.size()>0)
		{
			quertyStr.append(" and TCCI.tdp_cadre_enrollment_id in (:enrollmentIdsList ) ");
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
		if(enrollmentIdsList != null && enrollmentIdsList.size()>0)
		{
			query.setParameterList("enrollmentIdsList", enrollmentIdsList);
		}
		return query.list();
	}
	
	public List<Object[]> getCasteInfoForLocations(Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,String userAccessType, String segrigatStr,String searchType,List<Long> enrollIdsList)
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
				quertyStr.append(" (TCCI.location_type like '%District%' and TCCI.location_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") )    ");
			}
			else if(userAccessType.equalsIgnoreCase("TS"))
			{
				isLocationEmpty = true;
				quertyStr.append(" (TCCI.location_type like '%District%' and and TCCI.location_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ) )  ");
			}
			else if(userAccessType.equalsIgnoreCase("ALL") || userAccessType.equalsIgnoreCase("STATE"))
			{
				isLocationEmpty = true;
				quertyStr.append(" (TCCI.location_type like '%District%' and TCCI.location_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ) )  ");
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
				quertyStr.append(" (TCCI.location_type like '%District%' and TCCI.location_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+","+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ) )  ");
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
		if(enrollIdsList != null && enrollIdsList.size() > 0l)
			quertyStr.append(" and TCCI.tdp_cadre_enrollment_id in (:enrollIdsList)");

		Query query = getSession().createSQLQuery(quertyStr.toString());
		
		if( (locationLevelId != null && locationLevelId.longValue() != 1L) && (locationIdsList != null && locationIdsList.size()>0))
		{
			query.setParameterList("locationIdsList", locationIdsList);
		}
		if(wardIdsList != null && wardIdsList.size()>0)
		{
			query.setParameterList("wardIdsList", wardIdsList);
		}
		if(enrollIdsList != null && enrollIdsList.size() > 0l)
		{
			query.setParameterList("enrollIdsList", enrollIdsList);
		}
		return query.list();
	}
	
	public List<Object[]> getCadreAgerangeInfoForLocations(Long locationLevelId, List<Long> locationIdsList,List<Long> wardIdsList,String userAccessType,String segrigatStr,List<Long> enrollIdsList)
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
		if(enrollIdsList != null && enrollIdsList.size()>0)
		{
			quertyStr.append(" and TCAGE.tdp_cadre_enrollment_id in (:enrollIdsList) ");
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
		if(enrollIdsList != null && enrollIdsList.size() > 0l)
		{
			query.setParameterList("enrollIdsList", enrollIdsList);
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

	public List<Object[]> getCommitteeMemberDetailsByPositionsForCentral(List<Long> committeeLevelIds,List<Long> commiteeRoleIds){
		Query query = getSession().createQuery(" select distinct model.tdpCadreId, " +
								" model.tdpCommitteeRole.tdpRoles.role " +
								" from TdpCommitteeMember model " +
								" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in (:committeeLevelIds) " +
								" and model.tdpCommitteeRole.tdpRoles.tdpRolesId in (:commiteeRoleIds) " +
								" and model.isActive ='Y' ");
		query.setParameterList("committeeLevelIds", committeeLevelIds);
		query.setParameterList("commiteeRoleIds", commiteeRoleIds);
		
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
				" and model.isActive ='Y' and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 ");
		
		query.setParameterList("cadreIdsList", cadreIdsList);
		
		return  query.list();
	}

	public List<Object[]> getTdpCommitteeMemberPosition(Long cadreId){
		
		Query query = getSession().createQuery("select model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId, " +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name, " +
				" model.tdpCommitteeRole.tdpCommitteeRoleId, model.tdpCommitteeRole.tdpRoles.role,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel    " +
				" from TdpCommitteeMember model where model.tdpCadreId =:cadreId  and model.isActive ='Y' ");
		
		query.setParameter("cadreId", cadreId);
		
		return  query.list();
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
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValue(Long levelId,Long locationValue,Long constituencyId,List<Long> committeeEnrollmentYrIds)
	{
		StringBuilder str = new StringBuilder();
		
		str.append(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId  from TdpCommitteeMember model " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationValue " +
				" and model.isActive ='Y' ");
		if(constituencyId != null && constituencyId > 0)
		 str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCadre.userAddress.constituency.constituencyId =:constituencyId");
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeEnrollmentYrIds) ");
		}
		
		str.append(" group by model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("locationValue", locationValue);
		
		if(constituencyId != null && constituencyId > 0)
			query.setParameter("constituencyId", constituencyId);
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			query.setParameterList("committeeEnrollmentYrIds", committeeEnrollmentYrIds);
		}
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValueForVillage(Long levelId,Long tehsilId,Long constituencyId,List<Long> committeeEnrollmentYrIds)
	{
		StringBuilder str = new StringBuilder();
		
		str.append(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId  from TdpCommitteeMember model,Panchayat p " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId   " +
				" and model.isActive ='Y' ");
		if(constituencyId != null && constituencyId > 0)
		 str.append(" and model.tdpCadre.userAddress.constituency.constituencyId =:constituencyId");
		if(tehsilId != null && tehsilId > 0)
			str.append(" and p.tehsil.tehsilId =:tehsilId and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue = p.panchayatId");
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeEnrollmentId in (:committeeEnrollmentYrIds) ");
		}
		str.append(" group by model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("tehsilId", tehsilId);
		if(constituencyId != null && constituencyId > 0)
			query.setParameter("constituencyId", constituencyId);
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			query.setParameterList("committeeEnrollmentYrIds", committeeEnrollmentYrIds);
		}
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValueForWards(Long levelId,Long localElectionBody,Long constituencyId,List<Long> committeeEnrollmentYrIds)
	{
		StringBuilder str = new StringBuilder();
		
		str.append(" select count(model.tdpCommitteeMemberId),model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId  from TdpCommitteeMember model,Constituency con " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId   " +
				" and model.isActive ='Y' ");
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeEnrollmentId in (:committeeEnrollmentYrIds) ");
		}
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
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			query.setParameterList("committeeEnrollmentYrIds", committeeEnrollmentYrIds);
		}
		
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
       StringBuilder sb =new StringBuilder();
       sb.append("select model.tdpCadre.tdpCadreId," +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId, " +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId,  " +
				" model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name, " +
				" model.tdpCommitteeRole.tdpCommitteeRoleId," +
				" model.tdpCommitteeRole.tdpRoles.role,model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel " +
				" from TdpCommitteeMember model" );
       sb.append(" where " );
       if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
       sb.append(" model.tdpCadreId in (:tdpCadreIdsList) and " );
       sb.append(" model.isActive ='Y' ");
       Query query = getSession().createQuery(sb.toString());
       if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
		query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		return query.list();
	}
	
	public List<Object[]> getAllCommitteeMembInfoInLocation(Long locationLvl,List<Long> locationVal){
		Query query = getSession().createQuery("select model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.firstname,model.tdpCadre.memberShipNo,model.tdpCommitteeMemberId, " +
				" model.tdpCadre.tdpCadreId,model.tdpCommitteeRole.tdpRolesId,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name," +
				" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue,model.tdpCadre.mobileNo " +
				" from TdpCommitteeMember model" +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId =:locationLvl " +
				" and  model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:locationVal) and model.isActive ='Y'" +
				" and model.tdpCommitteeRole.tdpRolesId in (1,3)  " );
		query.setParameter("locationLvl", locationLvl);
		query.setParameterList("locationVal", locationVal);
		return query.list();
	}
	
	/*public List<Object[]> getComitteeMembersInfoInActivity(Long levelId,Long locationVal,Long committeeTypeId)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("select model.tdpCommitteeRole.tdpRoles.tdpRolesId,model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.tdpCadreId,model.tdpCadre.firstname,model.tdpCadre.image, " +
				" model.tdpCadre.casteState.caste.casteName,model.tdpCadre.gender,model.tdpCadre.age,model.tdpCadre.casteState.casteCategoryGroup.casteCategory.categoryName, " +
				" model.tdpCadre.mobileNo,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name,constituency.name " +
				" from TdpCommitteeMember model left join model.tdpCadre.userAddress.constituency constituency " +
				" where model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId  and model.isActive = 'Y' and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationVal");
		if(committeeTypeId.longValue() !=0L)
			str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId = :committeeTypeId ");
		else 
			str.append(" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommitteeId != 1 ");
		
		
		str.append(" order by model.tdpCommitteeRole.tdpRoles.tdpRolesId  ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("locationVal", locationVal);
		if(committeeTypeId.longValue() !=0L)
			query.setParameter("committeeTypeId", committeeTypeId);
		return query.list();
			
	}*/
	
	public List<Object[]> getComitteeMembersInfoInActivity(Long levelId,Long locationVal,Long committeeTypeId)
	{
		Query query = getSession().createSQLQuery("select TR.tdp_roles_id,TR.role,TC.tdp_cadre_id,TC.first_name,TC.image,C.caste_name,TC.gender, " +
							" TC.age,CC.category_name,TC.mobile_no,TBC.name,CO.name " +
							" from tdp_committee_member TCM,tdp_committee_role TCR,tdp_roles TR,tdp_cadre TC,caste_state CS,caste C,caste_category_group CCG,caste_category CC, " +
							" tdp_committee TCO,tdp_basic_committee TBC,user_address UA,constituency CO " +
							" where TCM.tdp_committee_role_id = TCR.tdp_committee_role_id and TCR.tdp_roles_id = TR.tdp_roles_id and TCM.tdp_cadre_id = TC.tdp_cadre_id " +
							" and TC.caste_state_id = CS.caste_state_id and CS.caste_id = C.caste_id and CS.caste_category_group_id = CCG.caste_category_group_id " +
							" and CCG.caste_category_id = CC.caste_category_id and TCR.tdp_committee_id = TCO.tdp_committee_id " +
							" and TCO.tdp_basic_committee_id = TBC.tdp_basic_committee_id and TC.address_id = UA.user_address_id and UA.constituency_id = CO.constituency_id " +
							" and TCO.tdp_committee_level_id = :levelId and TCM.is_active = 'Y' and TCO.tdp_committee_level_value = :locationVal " +
							" and TCO.tdp_basic_committee_id = :committeeTypeId order by TR.tdp_roles_id ");
		
		query.setParameter("levelId", levelId);
		query.setParameter("locationVal", locationVal);
		query.setParameter("committeeTypeId", committeeTypeId);
		
		return query.list();
	}
	public List<Object[]> getDesignationsForCadreCommittee(List<Long> tdpCadreIds)
	{
		Query query=getSession().createQuery("select Tr.tdpCadre.tdpCadreId,Tr.tdpCommitteeRole.tdpRoles.tdpRolesId,Tr.tdpCommitteeRole.tdpRoles.role from TdpCommitteeMember Tr  where " +
				"Tr.tdpCadre.tdpCadreId in(:tdpCadreIds)" );
		query.setParameterList("tdpCadreIds", tdpCadreIds);
		return query.list();
	}
	public List<Object[]> getDesignationsForPublicRepresentative(List<Long> tdpCadreIds)
	{
		Query query=getSession().createQuery("select distinct tc.tdpCadre.tdpCadreId,pr.publicRepresentativeType.publicRepresentativeTypeId,pr.publicRepresentativeType.type  "+
				 "from  PublicRepresentative pr, " +
				"  TdpCadreCandidate tc where  pr.candidateId=tc.candidateId and  tc.tdpCadre.tdpCadreId in(:tdpCadreIds)");
		query.setParameterList("tdpCadreIds", tdpCadreIds);
		return query.list();
		
	}
	
	public List<Object[]> getTotalEligibleMembersForTrainingCampProgram(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId){
		
		StringBuilder queryStr = new StringBuilder();
	
		queryStr.append(" select TCED.trainingCampProgram.trainingCampProgramId,TCED.trainingCampProgram.programName,count(distinct TCM.tdpCadre.tdpCadreId) " +
				" from " +
				" TdpCommitteeMember TCM,TrainingCampEligbleDesignation TCED " +
				" where " +
				" TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId=TCED.tdpBasicCommittee.tdpBasicCommitteeId and " +
				" TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId=TCED.tdpCommitteeLevel.tdpCommitteeLevelId and " +
				" TCM.tdpCommitteeRole.tdpRoles.tdpRolesId=TCED.tdpRoles.tdpRolesId and " +
				" TCM.isActive='Y' and TCM.tdpCadre.isDeleted='N' " +
				" and TCM.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed='Y' and TCM.tdpCadre.enrollmentYear=2014 and TCM.tdpCadre.gender=TCED.gender");
		
		  if(stateId != null && stateId.longValue() > 0){
			  queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId=:stateId"); 
		   }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
             queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId in (:userAccessLevelValues)");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	             queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.district.districtId in (:userAccessLevelValues)");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	          queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	          queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:userAccessLevelValues)");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	             queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		  }
		   queryStr.append(" group by TCED.trainingCampProgram.trainingCampProgramId ");
		   
		   Query query = getSession().createQuery(queryStr.toString());
		   if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		   }
		   if(stateId != null && stateId.longValue() > 0){
			  query.setParameter("stateId", stateId); 
		   }
		return query.list();
	}
	
public List<Object[]> getLevelWiseTotalEligibleMembersForTrainingCampProgram(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId){
		
		StringBuilder queryStr = new StringBuilder();
	
		queryStr.append(" select TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,count(distinct TCM.tdpCadre.tdpCadreId) " +
				" from " +
				" TdpCommitteeMember TCM,TrainingCampEligbleDesignation TCED " +
				" where " +
				" TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId=TCED.tdpBasicCommittee.tdpBasicCommitteeId and " +
				" TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId=TCED.tdpCommitteeLevel.tdpCommitteeLevelId and " +
				" TCM.tdpCommitteeRole.tdpRoles.tdpRolesId=TCED.tdpRoles.tdpRolesId and " +
				" TCM.isActive='Y' and TCM.tdpCadre.isDeleted='N' " +
				" and TCM.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed='Y' and TCM.tdpCadre.enrollmentYear=2014 and TCM.tdpCadre.gender=TCED.gender");
		
		  if(stateId != null && stateId.longValue() > 0){
			  queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId=:stateId"); 
		   }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
             queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId in (:userAccessLevelValues)");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	             queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.district.districtId in (:userAccessLevelValues)");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	          queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	          queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:userAccessLevelValues)");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	             queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		  }
		   queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId ");
		   
		   Query query = getSession().createQuery(queryStr.toString());
		   if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		   }
		   if(stateId != null && stateId.longValue() > 0){
				  query.setParameter("stateId", stateId); 
		   }
		return query.list();
    }
public List<Object[]> getTotalEligibleMembersForTrainingCampProgramByDistrict(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId, String status, Long distId){
	
	StringBuilder queryStr = new StringBuilder();

	queryStr.append(" select ");
			if(status.equalsIgnoreCase("camp")){
				queryStr.append(" distinct " );
			}
			
	queryStr.append(" TCED.trainingCampProgram.trainingCampProgramId," + //0 
			" TCED.trainingCampProgram.programName," + //1 
			" TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId," + //2
			" TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtName,"); //3
			if(status.equalsIgnoreCase("camp")){
				queryStr.append(" TCM.tdpCadre.tdpCadreId ");//4
			}else{
				queryStr.append(" count(distinct TCM.tdpCadre.tdpCadreId) ");//4
			}
			
			
	queryStr.append(" from " + 
			" TdpCommitteeMember TCM,TrainingCampEligbleDesignation TCED " +
			" where " +
			" TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId=TCED.tdpBasicCommittee.tdpBasicCommitteeId and " +
			" TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId=TCED.tdpCommitteeLevel.tdpCommitteeLevelId and " +
			" TCM.tdpCommitteeRole.tdpRoles.tdpRolesId=TCED.tdpRoles.tdpRolesId and " +
			" TCM.isActive='Y' and TCM.tdpCadre.isDeleted='N' " +
			" and TCM.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed='Y' and TCM.tdpCadre.enrollmentYear=2014 and TCM.tdpCadre.gender=TCED.gender ");
	  if(stateId != null && stateId.longValue() > 0){
		  queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId=:stateId "); 
	  }
	  if(distId != null && distId.longValue() > 0){
		  queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId=:distId "); 
	  }
	  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
             queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
          queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
          queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
             queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
	         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
	         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
	         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
	  }
	  if(status.equalsIgnoreCase("leadership")){
		   queryStr.append(" group by TCED.trainingCampProgram.trainingCampProgramId,TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId ");
	  }
	  queryStr.append(" order by TCED.trainingCampProgram.trainingCampProgramId asc ");
	   
	   Query query = getSession().createQuery(queryStr.toString());
	   if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	   }
	   if(stateId != null && stateId.longValue() > 0){
			  query.setParameter("stateId", stateId); 
	   }
	   if(distId != null && distId.longValue() > 0){
		   query.setParameter("distId", distId);   
	   }
	return query.list();
}



public List<Object[]> getUserWiseTotalEligibleMembersForTrainingCampProgram(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId){
	
	StringBuilder queryStr = new StringBuilder();
         
	    queryStr.append(" select");
	  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
         queryStr.append(" TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId,");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
             queryStr.append(" TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId,");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
          queryStr.append(" TCM.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId, ");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
          queryStr.append(" TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId, ");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
             queryStr.append(" TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId,");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
	         queryStr.append(" TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId,"); 
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
	         queryStr.append(" TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId,"); 
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
	         queryStr.append(" TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId,"); 
	  }
	   queryStr.append(" count(distinct TCM.tdpCadre.tdpCadreId) " +
			" from " +
			" TdpCommitteeMember TCM,TrainingCampEligbleDesignation TCED " +
			" where " +
			" TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId=TCED.tdpBasicCommittee.tdpBasicCommitteeId and " +
			" TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId=TCED.tdpCommitteeLevel.tdpCommitteeLevelId and " +
			" TCM.tdpCommitteeRole.tdpRoles.tdpRolesId=TCED.tdpRoles.tdpRolesId and " +
			" TCM.isActive='Y' and TCM.tdpCadre.isDeleted='N' " +
			" and TCM.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed='Y' and TCM.tdpCadre.enrollmentYear=2014 and TCM.tdpCadre.gender=TCED.gender ");
	   if(stateId != null && stateId.longValue() > 0){
			  queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId=:stateId  "); 
	   }
	 /* if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
             queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
          queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
          queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
             queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
	         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
	         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
	         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
	  }*/
	 
	  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
         queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId ");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
             queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId ");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
          queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId ");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
          queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId ");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
             queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId ");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
	         queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId "); 
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
	         queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId "); 
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
	         queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId "); 
	  }
	     
	   Query query = getSession().createQuery(queryStr.toString());
	   /*if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	   }*/
	   if(stateId != null && stateId.longValue() > 0){
			  query.setParameter("stateId", stateId); 
	   }
	return query.list();
}

public List<Object[]> getTotalEligibleMembersForTrainingCampProgramByLocationType(Long userAccessLevelId,List<Long> userAccessLevelValues,String locationType,Long stateId){
	
	StringBuilder queryStr = new StringBuilder();

	        queryStr.append(" select ");
	        if(locationType != null && locationType.equalsIgnoreCase("District")){
	         queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.district.districtId,"); //1
	         queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.district.districtName,"); //2
	        } 
	        if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
	         queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId,"); //3
	  	     queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.name,"); //4
	  	    }
	        if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
		         queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId,");
		         queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilName,");
		     }
            if(locationType != null && locationType.equalsIgnoreCase("Village")){
	        queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId,");
	        queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatName,");
	        }
            if(locationType != null && locationType.equalsIgnoreCase("Ward")){
    	        queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId,");
    	        queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.name,");
    	    }
            if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
    	        queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId,");
    	        queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.name,");
    	    }
			queryStr.append(" count(distinct TCM.tdpCadre.tdpCadreId) " + //4
			" from " +
			" TdpCommitteeMember TCM,TrainingCampEligbleDesignation TCED " +
			" where " +
			" TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId=TCED.tdpBasicCommittee.tdpBasicCommitteeId and " +
			" TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId=TCED.tdpCommitteeLevel.tdpCommitteeLevelId and " +
			" TCM.tdpCommitteeRole.tdpRoles.tdpRolesId=TCED.tdpRoles.tdpRolesId and " +
			" TCM.isActive='Y' and TCM.tdpCadre.isDeleted='N' " +
			" and TCM.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed='Y' and TCM.tdpCadre.enrollmentYear=2014 and TCM.tdpCadre.gender=TCED.gender ");
		if(stateId != null && stateId.longValue() > 0){
				  queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId=:stateId"); 
	    }
	 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
             queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.district.districtId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
          queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
          queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
             queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
	         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
	         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
	         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
	  }
       if(locationType != null && locationType.equalsIgnoreCase("District")){
         queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.district.districtId order by TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.district.districtId"); //1
        } 
        if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
         queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId order by TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId"); //3
  	    }
        if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
     	   queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId order by TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId asc"); //1  
        }  
        if(locationType != null && locationType.equalsIgnoreCase("Village")){
     	   queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId order by TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId asc"); //1   
        }
        if(locationType != null && locationType.equalsIgnoreCase("Ward")){
          queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId order by TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId asc");	
        }
        if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
         queryStr.append(" group by TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId order by TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId asc");	
        }
	   Query query = getSession().createQuery(queryStr.toString());
	   if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	   }
	   if(stateId != null && stateId.longValue() > 0){
			  query.setParameter("stateId", stateId); 
	   }
	return query.list();
}

// eligible count
public List<Object[]> getTotalEligibleMembersForTrainingCampProgramByUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId, String status, Long locationId,String locationType,Long userType,String levelType,List<Long> trainingCampProgramIds){
	
	StringBuilder queryStr = new StringBuilder();

			        queryStr.append(" select ");
					if(status.equalsIgnoreCase("camp")){
						queryStr.append(" distinct " );
					}
		           queryStr.append(" TCED.trainingCampProgram.trainingCampProgramId," + //0 
                                   " TCED.trainingCampProgram.programName,"); //1 
			          
			  if(status.equalsIgnoreCase("leadership")){
				  if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
			    	      queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId,");
			    	      queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtName,"); 
			       }else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
			    	|| userType.longValue()==IConstants.MP_USER_TYPE_ID || userType.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID || userType.longValue()==IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
			    	 	  queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId,");
				    	  queryStr.append("TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.name,"); 
				   }else if(userType != null && userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
					     if(levelType != null && levelType.equalsIgnoreCase("tehsil")){
					     queryStr.append(" TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId,");
					     queryStr.append(" TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilName,");
					     }else if(levelType != null && levelType.equalsIgnoreCase("townDivision")){
					     queryStr.append(" TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId,");
					     queryStr.append(" TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.name,");
					     }
				   }
			  }
			  
	      	if(status.equalsIgnoreCase("camp")){
				queryStr.append(" TCM.tdpCadre.tdpCadreId ");//4
			}else{
				queryStr.append(" count(distinct TCM.tdpCadre.tdpCadreId) ");//4
			}
			
			
	       queryStr.append(" from " + 
			" TdpCommitteeMember TCM,TrainingCampEligbleDesignation TCED " +
			" where " +
			" TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId=TCED.tdpBasicCommittee.tdpBasicCommitteeId and " +
			" TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId=TCED.tdpCommitteeLevel.tdpCommitteeLevelId and " +
			" TCM.tdpCommitteeRole.tdpRoles.tdpRolesId=TCED.tdpRoles.tdpRolesId and " +
			" TCM.isActive='Y' and TCM.tdpCadre.isDeleted='N' " +
			" and TCM.tdpCommitteeRole.tdpCommittee.isCommitteeConfirmed='Y' and " +
			" TCM.tdpCadre.enrollmentYear=2014 and TCM.tdpCadre.gender=TCED.gender and " +
			" TCM.tdpCommitteeEnrollmentId = "+IConstants.TDP_COMMITTEE_ENROLLMENT_ID_16_18+" and " +
			" TCED.tdpCommitteeEnrollmentId = "+IConstants.TDP_COMMITTEE_ENROLLMENT_ID_16_18+" and " +
			" TCM.tdpCommitteeRole.tdpCommitteeEnrollmentId = "+IConstants.TDP_COMMITTEE_ENROLLMENT_ID_16_18+" and " +
			" TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeEnrollmentId = "+IConstants.TDP_COMMITTEE_ENROLLMENT_ID_16_18 );
	  if(stateId != null && stateId.longValue() > 0){
		  queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId=:stateId "); 
	  }
	  if(status != null && status.equalsIgnoreCase("camp")){
		  if(locationType != null && locationType.equalsIgnoreCase("District")){
			  if(locationId != null && locationId.longValue() > 0){
				  queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId=:locationId "); 
			  }
		  }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
			  if(locationId != null && locationId.longValue() > 0){
				  queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId=:locationId");  
			  }
		  }else if(locationType != null && locationType.equalsIgnoreCase("mandal")){
			  if(locationId != null && locationId.longValue() > 0){
				   queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId=:locationId"); 
			  } 
		  }else if(locationType != null && locationType.equalsIgnoreCase("townDivision")){
			  if(locationId != null && locationId.longValue() > 0){
				  queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId=:locationId");  
			  }
		  }
	  }
	   if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.state.stateId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
             queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
          queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
          queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
             queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
	         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
	         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
	  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
	         queryStr.append(" and TCM.tdpCommitteeRole.tdpCommittee.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
	  }
	   
	  if(trainingCampProgramIds != null && trainingCampProgramIds.size() > 0){
		 queryStr.append(" and TCED.trainingCampProgram.trainingCampProgramId in (:trainingCampProgramIds) ");
	  }
	   if(status.equalsIgnoreCase("leadership")){
		   
		  queryStr.append(" group by TCED.trainingCampProgram.trainingCampProgramId ");
		  if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
	    	  queryStr.append(",TCM.tdpCommitteeRole.tdpCommittee.userAddress.district.districtId");
	       }else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
	    	|| userType.longValue()==IConstants.MP_USER_TYPE_ID || userType.longValue()==IConstants.INCHARGE_MINISTER_USER_TYPE_ID || userType.longValue()==IConstants.PARLIAMENT_INCHARGE_USER_TYPE_ID){
	    	 	  queryStr.append(",TCM.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId");
		   }else if(userType != null && userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
			     if(levelType != null && levelType.equalsIgnoreCase("tehsil")){
			     queryStr.append(",TCM.tdpCommitteeRole.tdpCommittee.userAddress.tehsil.tehsilId");
			     }else if(levelType != null && levelType.equalsIgnoreCase("townDivision")){
			     queryStr.append(",TCM.tdpCommitteeRole.tdpCommittee.userAddress.localElectionBody.localElectionBodyId");
			     }
		   }
	  }
	  queryStr.append(" order by TCED.trainingCampProgram.trainingCampProgramId asc ");
	   
	   Query query = getSession().createQuery(queryStr.toString());
	   if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	   }
	   if(stateId != null && stateId.longValue() > 0){
			  query.setParameter("stateId", stateId); 
	   }
	   if(locationId != null && locationId.longValue() > 0){
		   query.setParameter("locationId", locationId);   
	   }
	   if(trainingCampProgramIds != null && trainingCampProgramIds.size() > 0){
		   query.setParameterList("trainingCampProgramIds", trainingCampProgramIds);
	   }
	return query.list();
}

    
	public List<Object[]> getFinalizedMembersInfoForCommitteeRoleIds(Set<Long> committeeRoleIds){
		Query query = getSession().createQuery("" +
	    "select   model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.image,model.tdpCadre.firstname,model.tdpCadre.memberShipNo,model.tdpCommitteeMemberId, " +//4
		"         model.tdpCadre.tdpCadreId,model.tdpCommitteeRole.tdpCommitteeRoleId,model.status  " +//7
		"from     TdpCommitteeMember model " +
		"where    model.tdpCommitteeRole.tdpCommitteeRoleId in(:committeeRoleIds)  and model.isActive ='Y' and model.status = 'F' " +
		"order by model.tdpCommitteeRole.tdpRoles.order ");
		query.setParameterList("committeeRoleIds", committeeRoleIds);
		return query.list();
	}
	
	public List<Object[]> getAllCommitteesMembersInfoInALocByStatus(Long locationLvl,Long locationVal,String committeeMemberStatus){
		
		StringBuilder sb = new StringBuilder();
		sb.append("select model.tdpCommitteeRole.tdpRoles.role,model.tdpCadre.image,model.tdpCadre.firstname,model.tdpCadre.memberShipNo,model.tdpCommitteeMemberId, " +
				"        model.tdpCadre.tdpCadreId,model.tdpCommitteeRole.tdpCommitteeRoleId,model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.name, model.status " +
				" from   TdpCommitteeMember model " +
				" where  model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId =:locationLvl " +
				"        and  model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue =:locationVal and model.isActive ='Y' " +
				"        and  model.tdpCommitteeEnrollmentId =:committeeEnrollmentId  ");
		if(committeeMemberStatus != null){
			sb.append(" and  model.status = :committeeMemberStatus ");
		}
		sb.append(" order by model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId, model.tdpCommitteeRole.tdpRoles.order ");
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("locationLvl", locationLvl);
		query.setParameter("locationVal", locationVal);
		query.setParameter("committeeEnrollmentId", IConstants.CURRENT_ENROLLMENT_ID);
		if(committeeMemberStatus != null){
			query.setParameter("committeeMemberStatus", committeeMemberStatus);
		}
		return query.list();
	}
	
	//Following 3 queries : For Members Count For Committees roles status wise.
	public Long getCandiCountForACommitteeRoleByStatus(Long committeeRoleId , String committeeMemberStatus){
		
		Query query = getSession().createQuery("" +
		"select count(distinct model.tdpCommitteeMemberId) "+
		"       from TdpCommitteeMember model where " +
		"       model.tdpCommitteeRole.tdpCommitteeRoleId = :committeeRoleId and model.isActive ='Y' and  model.status = :committeeMemberStatus ");
		query.setParameter("committeeRoleId", committeeRoleId);
		query.setParameter("committeeMemberStatus",committeeMemberStatus);
		return (Long)query.uniqueResult();
	}
	public List<Object[]> getStatusWiseCandiCountForACommitteeRole(Long committeeRoleId){
		Query query = getSession().createQuery("" +
		"select   model.status , count(distinct model.tdpCommitteeMemberId) "+
		"from     TdpCommitteeMember model  " +
		"where    model.tdpCommitteeRole.tdpCommitteeRoleId = :committeeRoleId and model.isActive ='Y' " +
		"group by model.status");
		query.setParameter("committeeRoleId", committeeRoleId);
		return query.list();
	}
	
	public List<Object[]> getRoleWiseProposedAndFinalizedMembersCounts(Set<Long> committeeRoleIds){
		
		Query query = getSession().createQuery("" +
		" select    model.tdpCommitteeRole.tdpCommitteeRoleId,model.status , count(distinct model.tdpCommitteeMemberId)  " +
		" from      TdpCommitteeMember model where " +
		"           model.tdpCommitteeRole.tdpCommitteeRoleId in(:committeeRoleIds) and model.isActive ='Y' " +
		" group by  model.tdpCommitteeRole.tdpCommitteeRoleId , model.status ");
		query.setParameterList("committeeRoleIds", committeeRoleIds);
		
		return query.list();
	}//END
	
	public List<Object[]> getCommitteeCreationDetails(Long committeeTypeId,List<Long> committeeLevlIdsList,
			List<Long> designationsList,Long locationLvlId,List<Long> loctnLevlValues,List<Long> committeeEnrollmntIds,Long stateId,String searchType){
		
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select  distinct");
		if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(10l)){
			 sb.append(" s.state_id as sId,s.state_name as sName,'' as dId,'' as dName,'' as cId,'' as cName,'' as mId,'' as mName,'' as lId,'' as lName,'' as vId,'' as vName,'' as wId,'' as wName, " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(11l)){
			 sb.append(" s.state_id as sId,s.state_name as sName,d.district_id as dId ,d.district_name  as dName ,'' as cId,'' as cName,'' as mId,'' as mName,'' as lId,'' as lName,'' as vId,'' as vName,'' as wId,'' as wName,  " );
		 }/*else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(4l)){
			 sb.append(" '','','','',c.constituency_id,c.name,'','','','','','','','', " );
		 }*/else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 2 ){
			sb.append(" s.state_id as sId,s.state_name as sName ,d.district_id as dId ,d.district_name  as dName ,c.constituency_id  as cId ,c.name as cName ,t.tehsil_id as mId ,t.tehsil_name as mName ,leb.local_election_body_id  as lId ,leb.name as lName ,p.panchayat_id as vId , " +
					" p.panchayat_name as vName , w.constituency_id as wId ,w.name as wName , " );
		}else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 3 ){
			sb.append(" s.state_id as sId,s.state_name as sName ,d.district_id as dId ,d.district_name  as dName ,c.constituency_id  as cId ,c.name as cName,t.tehsil_id as mId,t.tehsil_name as mName,leb.local_election_body_id as lId,leb.name as lName,'' as vId,'' as vName,'' as wId,'' as wName, " );
		}
		
		//14,15,16,17,18
		sb.append(" tc.tdp_basic_committee_id as tbcId,tcr.tdp_roles_id as trId,tr.role as tRole,tcr.max_members as count,tbc.name as tbcName " );
		
		sb.append(" FROM tdp_committee tc,tdp_committee_role tcr,tdp_basic_committee tbc,tdp_roles tr," +
				" user_address ua " +
				" left join state s on ua.state_id = s.state_id " +
				" left join district d on ua.district_id = d.district_id  " +
				" left join constituency c on ua.constituency_id = c.constituency_id " +
				" left join tehsil t on ua.tehsil_id = t.tehsil_id " +
				" left join local_election_body leb on ua.local_election_body = leb.local_election_body_id " +
				" left join panchayat p on ua.panchayat_id = p.panchayat_id " +
				" left join constituency w on ua.ward = w.constituency_id " +
				" where tcr.tdp_roles_id = tr.tdp_roles_id and  tc.address_id = ua.user_address_id and " +
				" tc.tdp_basic_committee_id = tbc.tdp_basic_committee_id and tc.tdp_committee_id = tcr.tdp_committee_id  ");
		
		
		if(committeeEnrollmntIds != null && committeeEnrollmntIds.size() > 0){
			sb.append(" and tc.tdp_committee_enrollment_id in (:ids) ");
		}
		if(committeeTypeId != null && committeeTypeId.longValue() > 0l){
			sb.append(" and tbc.tdp_committee_type_id = :id ");
		}
		if(designationsList != null && designationsList.size() > 0){
			sb.append(" and  tr.tdp_roles_id in (:desg) ");
		}
		
		if(committeeLevlIdsList != null && committeeLevlIdsList.size() > 0){
			sb.append(" and tc.tdp_committee_level_id in (:commLvl) ");
		}
		
		if(searchType != null && searchType.equalsIgnoreCase("started")){
			sb.append(" and tc.started_date is not null and  tc.is_committee_confirmed = 'N' ");
		}else if(searchType != null && searchType.equalsIgnoreCase("completed")){
			sb.append(" and tc.started_date is not null and  tc.is_committee_confirmed = 'Y' and tc.completed_date is not null ");
		}else if(searchType != null && searchType.equalsIgnoreCase("notYetStarted")){
			sb.append(" and tc.started_date is  null && tc.is_committee_confirmed = 'N' and tc.completed_date is  null ");
		}
		
		
		if(locationLvlId != null && locationLvlId.longValue() == 2l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0l)
			sb.append(" and ua.state_id in (:loc)  " );
		}else if(locationLvlId != null && locationLvlId.longValue() == 3l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0l)
			sb.append(" and ua.district_id in (:loc) " );
		}else if(locationLvlId != null && locationLvlId.longValue() == 4l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0l)
			sb.append(" and  c.constituency_id in (:loc)  " );
		}else if(locationLvlId != null && locationLvlId.longValue() == 5l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0l)
			sb.append(" and  t.tehsil_id in (:loc)  " );
		}else if(locationLvlId != null && locationLvlId.longValue() == 7l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0l)
			sb.append(" and  leb.local_election_body_id in (:loc)  " );
		}else if(locationLvlId != null && locationLvlId.longValue() == 6l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0l)
			sb.append(" and  p.panchayat_id in (:loc)  " );
		}else if(locationLvlId != null && locationLvlId.longValue() == 8l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0l)
			sb.append(" and  w.constituency_id in (:loc)  " );
		}
		
		sb.append(" group by " );
		
		if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(11l)){
			 sb.append(" d.district_name, " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(10l)){
			 sb.append(" s.state_name, " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(4l)){
			 sb.append(" c.constituency_id,c.name, " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 2 ){
			sb.append(" d.district_name,c.name,t.tehsil_name ,leb.name," +
					" p.panchayat_name, w.name, " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 3 ){
			sb.append(" t.tehsil_name ,leb.name, " );
		 }
		 
		sb.append(" tc.tdp_basic_committee_id,tcr.tdp_roles_id  " );
		
		sb.append(" order by ");
		
		 if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(11l)){
			 sb.append(" d.district_name,tcr.tdp_roles_id " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(10l)){
			 sb.append(" s.state_name,tcr.tdp_roles_id " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 2 ){
			sb.append(" d.district_name,c.name,t.tehsil_name ,leb.name,tcr.tdp_roles_id, " +
					" p.panchayat_name, w.name,tcr.tdp_roles_id " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 3 ){
			sb.append(" t.tehsil_name ,leb.name, tcr.tdp_roles_id " );
		 }
		
		
		Query query = getSession().createSQLQuery(sb.toString()).addScalar("sId",Hibernate.LONG)
				.addScalar("sName",Hibernate.STRING)
				.addScalar("dId",Hibernate.LONG)
				.addScalar("dName",Hibernate.STRING)
				.addScalar("cId",Hibernate.LONG)
				.addScalar("cName",Hibernate.STRING)
				.addScalar("mId",Hibernate.LONG)
				.addScalar("mName",Hibernate.STRING)
				.addScalar("lId",Hibernate.LONG)
				.addScalar("lName",Hibernate.STRING)
				.addScalar("vId",Hibernate.LONG)
				.addScalar("vName",Hibernate.STRING)
				.addScalar("wId",Hibernate.LONG)
				.addScalar("wName",Hibernate.STRING)
				.addScalar("tbcId",Hibernate.LONG)
				.addScalar("trId",Hibernate.LONG)
				.addScalar("tRole",Hibernate.STRING)
				.addScalar("count",Hibernate.LONG)
				.addScalar("tbcName",Hibernate.STRING);
		
		if(committeeLevlIdsList != null && committeeLevlIdsList.size() > 0){
			query.setParameterList("commLvl", committeeLevlIdsList);
		}
		if(committeeEnrollmntIds != null && committeeEnrollmntIds.size() > 0){
			query.setParameterList("ids", committeeEnrollmntIds);
		}
		if(designationsList != null && designationsList.size() > 0){
			query.setParameterList("desg", designationsList);
		}
		if(loctnLevlValues != null && loctnLevlValues.size() > 0l){
				query.setParameterList("loc", loctnLevlValues);
		}
		
		if(committeeTypeId != null && committeeTypeId.longValue() > 0l){
			query.setParameter("id", committeeTypeId);
		}
		return query.list();
		
	}
	
	public List<Object[]> getProposedAndFinalyzedCount(Long committeeTypeId,List<Long> committeeLevlIdsList,
			List<Long> designationsList,Long locationLvlId,List<Long> loctnLevlValues,List<Long> committeeEnrollmntIds,Long stateId,String searchType){
		
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select  distinct");
		if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(10l)){
			 sb.append(" s.state_id as sId,s.state_name as sName,'' as dId,'' as dName,'' as cId,'' as cName,'' as mId,'' as mName,'' as lId,'' as lName,'' as vId,'' as vName,'' as wId,'' as wName, " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(11l)){
			 sb.append(" s.state_id as sId,s.state_name as sName,d.district_id as dId ,d.district_name  as dName ,'' as cId,'' as cName,'' as mId,'' as mName,'' as lId,'' as lName,'' as vId,'' as vName,'' as wId,'' as wName,  " );
		 }/*else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(4l)){
			 sb.append(" '','','','',c.constituency_id,c.name,'','','','','','','','', " );
		 }*/else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 2 ){
			sb.append(" s.state_id as sId,s.state_name as sName ,d.district_id as dId ,d.district_name  as dName ,c.constituency_id  as cId ,c.name as cName ,t.tehsil_id as mId ,t.tehsil_name as mName ,leb.local_election_body_id  as lId ,leb.name as lName ,p.panchayat_id as vId , " +
					" p.panchayat_name as vName , w.constituency_id as wId ,w.name as wName , " );
		}else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 3 ){
			sb.append(" s.state_id as sId,s.state_name as sName,d.district_id as dId ,d.district_name  as dName ,c.constituency_id  as cId ,c.name as cName,t.tehsil_id as mId,t.tehsil_name as mName,leb.local_election_body_id as lId,leb.name as lName,'' as vId,'' as vName,'' as wId,'' as wName, " );
		}
		//14,15,16,17,18
		sb.append(" tc.tdp_basic_committee_id as tbcId,tcr.tdp_roles_id as trId,tr.role as tRole ,tcm.status as status,count(tcm.tdp_committee_member_id)  as count " );
		
		sb.append(" FROM tdp_committee tc,tdp_committee_role tcr,tdp_basic_committee tbc," +
				" tdp_committee_member tcm ,tdp_roles tr," +
				" user_address ua " +
				" left join state s on ua.state_id = s.state_id " +
				" left join district d on ua.district_id = d.district_id  " +
				" left join constituency c on ua.constituency_id = c.constituency_id " +
				" left join tehsil t on ua.tehsil_id = t.tehsil_id " +
				" left join local_election_body leb on ua.local_election_body = leb.local_election_body_id " +
				" left join panchayat p on ua.panchayat_id = p.panchayat_id " +
				" left join constituency w on ua.ward = w.constituency_id " +
				" where tcr.tdp_committee_role_id = tcm.tdp_committee_role_id and tcm.is_active='Y' and " +
				" tcr.tdp_roles_id = tr.tdp_roles_id and  tc.address_id = ua.user_address_id and " +
				" tc.tdp_basic_committee_id = tbc.tdp_basic_committee_id and tc.tdp_committee_id = tcr.tdp_committee_id   ");
		
		if(committeeTypeId != null && committeeTypeId.longValue() > 0l){
			sb.append(" and tbc.tdp_committee_type_id = :committeeTypeId ");
		}
		if(designationsList != null && designationsList.size() > 0){
			sb.append(" and  tr.tdp_roles_id in (:designationsList) ");
		}
		
		if(committeeLevlIdsList != null && committeeLevlIdsList.size() > 0){
			sb.append(" and tc.tdp_committee_level_id in (:committeeLevlIdsList) ");
		}
		
		if(committeeEnrollmntIds != null && committeeEnrollmntIds.size() > 0){
			sb.append(" and tc.tdp_committee_enrollment_id in (:committeeEnrollmntIds) ");
		}
		
		if(searchType != null && searchType.equalsIgnoreCase("started")){
			sb.append(" and tc.started_date is not null and  tc.is_committee_confirmed = 'N' ");
		}else if(searchType != null && searchType.equalsIgnoreCase("completed")){
			sb.append(" and tc.started_date is not null and  tc.is_committee_confirmed = 'Y' and tc.completed_date is not null ");
		}else if(searchType != null && searchType.equalsIgnoreCase("notYetStarted")){
			sb.append(" and tc.started_date is  null && tc.is_committee_confirmed = 'N' and tc.completed_date is  null ");
		}
		
		if(locationLvlId != null && locationLvlId.longValue() == 2l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0)
			sb.append(" and ua.state_id in (:loctnLevlValues)  " );
		}else if(locationLvlId != null && locationLvlId.longValue() == 3l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0)
			sb.append(" and ua.district_id in (:loctnLevlValues) " );
		}else if(locationLvlId != null && locationLvlId.longValue() == 4l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0)
			sb.append(" and  c.constituency_id in (:loctnLevlValues)  " );
		}else if(locationLvlId != null && locationLvlId.longValue() == 5l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0)
			sb.append(" and  t.tehsil_id in (:loctnLevlValues)  " );
		}else if(locationLvlId != null && locationLvlId.longValue() == 7l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0)
			sb.append(" and  leb.local_election_body_id in (:loctnLevlValues)  " );
		}else if(locationLvlId != null && locationLvlId.longValue() == 6l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0)
			sb.append(" and  p.panchayat_id in (:loctnLevlValues)  " );
		}else if(locationLvlId != null && locationLvlId.longValue() == 8l){
			if(loctnLevlValues != null && loctnLevlValues.size() > 0)
			sb.append(" and  w.constituency_id in (:loctnLevlValues)  " );
		}
		
		sb.append(" group by " );
		
		if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(3l)){
			 sb.append(" d.district_name, " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(4l)){
			 sb.append(" c.constituency_id,c.name, " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 2 ){
			sb.append(" d.district_name,c.name,t.tehsil_name ,leb.name," +
					" p.panchayat_name, w.name, " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 3 ){
			sb.append(" t.tehsil_name ,leb.name, " );
		 }
		 
		sb.append(" tc.tdp_basic_committee_id,tcr.tdp_roles_id,tcm.status  " );
		
		sb.append(" order by ");
		
		 if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(11l)){
			 sb.append(" d.district_name,tcr.tdp_roles_id " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 1  && committeeLevlIdsList.contains(10l)){
			 sb.append(" s.state_name,tcr.tdp_roles_id " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 2 ){
			sb.append(" d.district_name,c.name,t.tehsil_name ,leb.name,tcr.tdp_roles_id ," +
					" p.panchayat_name, w.name,tcr.tdp_roles_id " );
		 }else if(committeeLevlIdsList != null && committeeLevlIdsList.size() == 3 ){
			sb.append(" t.tehsil_name ,leb.name, tcr.tdp_roles_id " );
		 }
		
		
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("sId",Hibernate.LONG)
				.addScalar("sName",Hibernate.STRING)
				.addScalar("dId",Hibernate.LONG)
				.addScalar("dName",Hibernate.STRING)
				.addScalar("cId",Hibernate.LONG)
				.addScalar("cName",Hibernate.STRING)
				.addScalar("mId",Hibernate.LONG)
				.addScalar("mName",Hibernate.STRING)
				.addScalar("lId",Hibernate.LONG)
				.addScalar("lName",Hibernate.STRING)
				.addScalar("vId",Hibernate.LONG)
				.addScalar("vName",Hibernate.STRING)
				.addScalar("wId",Hibernate.LONG)
				.addScalar("wName",Hibernate.STRING)
				.addScalar("tbcId",Hibernate.LONG)
				.addScalar("trId",Hibernate.LONG)
				.addScalar("tRole",Hibernate.STRING)
				.addScalar("status",Hibernate.STRING)
				.addScalar("count",Hibernate.LONG);
		
		if(committeeLevlIdsList != null && committeeLevlIdsList.size() > 0){
			query.setParameterList("committeeLevlIdsList", committeeLevlIdsList);
		}
		if(committeeEnrollmntIds != null && committeeEnrollmntIds.size() > 0){
			query.setParameterList("committeeEnrollmntIds", committeeEnrollmntIds);
		}
		if(designationsList != null && designationsList.size() > 0){
			query.setParameterList("designationsList", designationsList);
		}
		
		if(loctnLevlValues != null && loctnLevlValues.size() > 0){
				query.setParameterList("loctnLevlValues", loctnLevlValues);
		}
		
		if(committeeTypeId != null && committeeTypeId.longValue() > 0l){
			query.setParameter("committeeTypeId", committeeTypeId);
		}
		
		
		return query.list();
		
	}
	public List<Object[]> getRoleWiseCommitteeMembersCount(Long tdpCommitteeId){
		Query query = getSession().createQuery("" +
	    " select model.tdpCommitteeRole.tdpRoles.tdpRolesId , model.tdpCommitteeRole.tdpRoles.role , count(distinct model.tdpCommitteeMemberId)  " +
	    " from   TdpCommitteeMember model " +
	    " where  model.tdpCommitteeRole.tdpCommitteeId = :tdpCommitteeId and model.isActive = 'Y' " +
	    " group by model.tdpCommitteeRole.tdpRoles.tdpRolesId " +
	    " order by model.tdpCommitteeRole.tdpRoles.tdpRolesId ");
		query.setParameter("tdpCommitteeId", tdpCommitteeId);
		return query.list();
	}
	
	public List<Object[]> getActiveMemberDetailsByCadreId(Long tdpCadreId){
		Query query = getSession().createQuery("select distinct model.tdpCommitteeMemberId," +
											" model.tdpCommitteeRole.tdpCommitteeRoleId," +
											" model.tdpCommitteeRole.tdpCommittee.tdpCommitteeId" +
											" from TdpCommitteeMember model,TdpCadreEnrollmentYear model1" +
											" where model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId" +
											" and model.tdpCadre.tdpCadreId = :tdpCadreId" +
											" and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014" +
											" and model1.isDeleted = 'N' and model1.enrollmentYear.enrollmentYearId = 4" +
											" and model.isActive = 'Y' and model.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId ="+IConstants.TDP_COMMITTEE_ENROLLMENT_ID_16_18 );
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
		
	}
	public List<Object[]> getFinilizedCommittesByRole(Long constituencyId,List<Long> levelIds,List<Long> levelValues,List<Long> basicCommitteeIds,List<Long> cmiteEnrlmntYearIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.tdpCommitteeRole.tdpRoles.tdpRolesId," +
				" count(model.tdpCommitteeMemberId)" +
				" from TdpCommitteeMember model" +
				" where model.isActive = 'Y' and model.status = 'F'");
		if(constituencyId != null && constituencyId.longValue() > 0l){
			sb.append(" and model.tdpCommitteeRole.tdpCommittee.userAddress.constituency.constituencyId =:constituencyId");
		}
		if(levelValues != null  && !levelValues.isEmpty() && levelValues.size() > 0){
			sb.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:levelValues)");
		} 
		if(levelIds != null && !levelIds.isEmpty() && levelIds.size() > 0){
			sb.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId in (:levelIds)");
		}
		if(basicCommitteeIds != null && !basicCommitteeIds.isEmpty() && basicCommitteeIds.size() >0){
			sb.append(" and model.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpCommitteeTypeId in (:basicCommitteeIds)");
		}
		if(cmiteEnrlmntYearIds != null && !cmiteEnrlmntYearIds.isEmpty() &&  cmiteEnrlmntYearIds.size() >0){
			sb.append(" and model.tdpCommitteeRole.tdpCommittee.tdpCommitteeEnrollmentId in (:cmiteEnrlmntYearIds)");
		}
		
		sb.append("group by model.tdpCommitteeRole.tdpRoles.tdpRolesId");
		
		Query query = getSession().createQuery(sb.toString());
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		if(levelValues != null && !levelValues.isEmpty() && levelValues.size() > 0)
			query.setParameterList("levelValues", levelValues);
		if(levelIds != null  && !levelIds.isEmpty() && levelIds.size() > 0)
			query.setParameterList("levelIds", levelIds);
		if(basicCommitteeIds != null && !basicCommitteeIds.isEmpty() && basicCommitteeIds.size() >0)
			query.setParameterList("basicCommitteeIds", basicCommitteeIds);
		if(cmiteEnrlmntYearIds != null && !cmiteEnrlmntYearIds.isEmpty() &&  cmiteEnrlmntYearIds.size() >0)
			query.setParameterList("cmiteEnrlmntYearIds", cmiteEnrlmntYearIds);
		
		return query.list();
	}
}
