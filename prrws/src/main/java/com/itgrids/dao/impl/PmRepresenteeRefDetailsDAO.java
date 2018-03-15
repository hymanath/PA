package com.itgrids.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRepresenteeRefDetailsDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.PmRepresenteeRefDetails;
@Repository
public class PmRepresenteeRefDetailsDAO extends GenericDaoHibernate<PmRepresenteeRefDetails, Long> implements IPmRepresenteeRefDetailsDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PmRepresenteeRefDetailsDAO() {
		super(PmRepresenteeRefDetails.class);
	}

	public List<Object[]> getPmRepresenteRefDetails(Long petitionId){
		StringBuilder str = new StringBuilder();
		str.append(" select pmRepresentee.name,pmRepresentee.mobileNo, pmRepresentee.email,pmRepresentee.voterCardNo , " +//0,1,2,3
				" state.stateId, district.districtId,constituency.constituencyId,tehsil.tehsilId,localBody.localElectionBodyId, " +//4,5,6,7,8
				" pmRefCandidate.pmRefCandidateId,pmRefCandidate.name,pmRefCandidate.mobileNo,pmRefCandidate.email,pmRefCandidate.partyId,pmRefCandidate.partyName, " +
				" pmRefCandidate.imagePath, " +//9,10,11,12,13,14,15
				" refState.stateId, refDistrict.districtId,refConstituency.constituencyId,refTehsil.tehsilId,refLocalBody.localElectionBodyId, " +//16,17,18,19,20
				" petition.petitionId, petition.endorsmentNo,petition.representationDate,petition.endorsmentDate,petition.workName,petition.estimationCost," +//21,22,23,24,25,26
				" petition.noOfWorks,pmStatus.pmStatusId,pmStatus.status,pmRepresentee.pmRepresenteeId , pmRepresentee.pmRefCandidateId,model.pmRepresenteeRefDetailsId, " +//27,28,29,30,31,32
				" pmDesignation.pmDesignationId, pmDesignation.designation,refPmDesignation.pmDesignationId, refPmDesignation.designation, " +//33,34,35,36
				" state.stateName, district.districtName,constituency.name,tehsil.tehsilName,localBody.name,electionType.electionType," +//37,38,39,40,41,42
				" refState.stateName, refDistrict.districtName,refConstituency.name,refTehsil.tehsilName,refLocalBody.name,refElectionType.electionType,pmRefCandidate.tdpCadreId " +//43,44,45,46,47,48,49
				" ,model.petition.representeeType" +//50
				",pmRepresentee.tdpCadreId, " +//51
				" natState.stateName, natDistrict.districtName,natConstituency.name,natTehsil.tehsilName,natLocalBody.name,natElectionType.electionType," +//52,53,54,55,56,57
				" natState.stateId, natDistrict.districtId,natConstituency.constituencyId,natTehsil.tehsilId,natLocalBody.localElectionBodyId " +//58,59,60,61,62
				" ,panchayat.panchayatId,panchayat.panchayatName,refPanchayat.panchayatId,refPanchayat.panchayatName,natPanchayat.panchayatId,natPanchayat.panchayatName "+//63,64,65,66,67,68
				" ,pmRepresentee.imagePath,petition.isOldData " +//69,70
				" from PmRepresenteeRefDetails model " +
				" left join model.pmRepresentee pmRepresentee " +
				" left join pmRepresentee.userAddress userAddress " +
				" left join userAddress.state state " +
				" left join userAddress.district district " +
				" left join userAddress.constituency constituency" +
				" left join userAddress.tehsil tehsil  " +
				" left join userAddress.panchayat panchayat " +
				" left join userAddress.localElectionBody localBody " +
				" left join localBody.electionType electionType" +
				" " +
				" left join model.pmRefCandidate pmRefCandidate "+
				" left join pmRefCandidate.address refUserAddress " +
				" left join refUserAddress.state refState " +
				" left join refUserAddress.district refDistrict " +
				" left join refUserAddress.constituency refConstituency" +
				" left join refUserAddress.tehsil refTehsil  " +
				" left join refUserAddress.panchayat refPanchayat " +
				" left join refUserAddress.localElectionBody refLocalBody " +
				" left join refLocalBody.electionType refElectionType" +
				" " +
				" left join pmRefCandidate.nativAddress nativAddress " +
				" left join nativAddress.state natState " +
				" left join nativAddress.district natDistrict " +
				" left join nativAddress.constituency natConstituency" +
				" left join nativAddress.tehsil natTehsil  " +
				" left join nativAddress.panchayat natPanchayat " +
				" left join nativAddress.localElectionBody natLocalBody " +
				" left join natLocalBody.electionType natElectionType" +
				" " +
				" left join model.petition petition " + 
				" left join petition.pmStatus pmStatus " +
				" " +
				" left join model.pmRepresenteeDesignation pmRepresenteeDesignation " +
				" left join pmRepresenteeDesignation.pmDesignation pmDesignation" +
				" " +
				" left join model.pmRefCandidateDesignation pmRefCandidateDesignation " +
				" left join pmRefCandidateDesignation.pmDesignation refPmDesignation " );
		str.append(" where model.petitionId =:petitionId and model.isDeleted='N' ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("petitionId", petitionId);	
		return query.list();
	}
	public List<Object[]> getRepresentativeSearchWiseDetails(InputVO inputVO,Date fromDate,Date toDate,Set<Long> petitionIdsList,String type){
		StringBuilder sb = new StringBuilder();
		String  filterValue = inputVO.getFilterValue();
		Long searchLevelId = inputVO.getSearchLevelId();
		List<Long> searchLevelValues = inputVO.getSearchLvlVals();
		String filterType = inputVO.getFilterType();
		if(type != null && type.equalsIgnoreCase("count"))
			sb.append(" select model.petition.petitionId,model1.pmSubWorkDetailsId,model1.pmStatusId ");
		else{
			sb.append(" select model.petition.petitionId,model.petition.endorsmentNo,date(model1.endorsmentDate)," +//0,1,2
				" model.petition.estimationCost,model.pmRepresentee.name,model.pmRefCandidate.name," +//3,4,5
				"model.petition.noOfWorks,model.pmRefCandidateDesignation.pmDesignation.designation," +//6,7
				" date(model.petition.representationDate),date(model1.updatedTime),model1.pmStatus.pmStatusId" +//8,9,10
				",model.petition.workName,model1.costEstimation,model1.grievanceDescrption,model1.workEndorsmentNo,model1.pmSubWorkDetailsId " +//11,12,13,14,15
				" ,model1.pmStatus.status ,model.petition.pmStatusId ,pmSubject.subject,pmSubSubject.subject " +//16,17,18,19
				" ,pmBriefLead.briefLead,pmDepartment.department,model.petition.representeeType,pmGrant.pmGrantName" +//20,21,22,23
				",pmWorkType.workType,model.pmRepresenteeDesignation.pmDesignation.designation  "+//24,25
				",district.districtId,district.districtName,constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName " +//26,27 ,28,29  ,30,31
				",pmSubject.pmSubjectId,pmSubSubject.pmSubjectId,pmLead.leadName,pmBriefLead.pmBriefLeadId " ); // 32,33,34,35
		}
		
		
		sb.append("  from PmRepresenteeRefDetails as model "+//, PmPetitionAssignedOfficer assignedOfficer " +
				//"left outer join model.pmRefCandidateDesignation model2" +
				//" left outer join model.pmRefCandidate pmRefCandidate" +
				" ,PmSubWorkDetails as model1 ");
		if(filterType != null && (filterType.equalsIgnoreCase("pmOfficer"))){
			sb.append(" ,PmPetitionAssignedOfficer PAO ");
		}
				sb.append(" left join model1.pmSubject pmSubject" +
						" left join model1.pmSubSubject pmSubSubject " +
						" left join model1.pmBriefLead pmBriefLead " +
						" left join model1.pmDepartment pmDepartment " +
						" left join model1.pmGrant pmGrant " +
						" left join model1.pmWorkType pmWorkType " +
						" left join model1.pmLead pmLead ");
				
		//if(filterType != null && (filterType.equalsIgnoreCase("work") || 
			//	filterType.equalsIgnoreCase("department") || filterType.equalsIgnoreCase("subject")) && searchLevelId != null && searchLevelId.longValue()>0L){
			sb.append(" left join model1.locationAddress locationAddress " );
		/*}else*/ if(filterType != null && (filterType.equalsIgnoreCase("referrelDesignation") 
				|| filterType.equalsIgnoreCase("referralName")) && searchLevelId != null && searchLevelId.longValue()>0L){
			sb.append(" left join model.pmRefCandidate.address locationAddress " );
		}else if(filterType != null && filterType.equalsIgnoreCase("representeeDesignation") && searchLevelId != null && searchLevelId.longValue()>0L ){
			sb.append(" left join model.pmRepresenteeDesignation.pmRepresentee.userAddress locationAddress " );
		}else if(filterType != null && filterType.equalsIgnoreCase("referral") && searchLevelId != null && searchLevelId.longValue()>0L){
			sb.append(" left join model.pmRefCandidate.address locationAddress " );
		}/*else if(filterType != null && filterType.equalsIgnoreCase("representee")){
			sb.append(" left join model.pmRepresentee.userAddress locationAddress " );
		}*/else if(searchLevelId != null && searchLevelId.longValue()>0L){
			sb.append(" left join model.pmRepresentee.userAddress locationAddress " );
		}
		
		//if(searchLevelId != null && searchLevelId.longValue()>0L){
		sb.append("  left join locationAddress.state state " +
					" left join locationAddress.district district " +
					" left join locationAddress.constituency constituency " +
					" left join locationAddress.tehsil tehsil " +
					" left join locationAddress.localElectionBody localElectionBody " );
					//" left join locationAddress.panchayat panchayat  ");
		//}
		
		//sb.append(" where   model.petitionId = assignedOfficer.petitionId and assignedOfficer.pmDepartmentDesignationOfficer.pmOfficerId in (:pmOfficerIdsList)  and model.isDeleted ='N'  and model1.petition.petitionId = model.petition.petitionId and model1.isDeleted='N' " +
		sb.append(" where   model.isDeleted ='N'  and model1.petition.petitionId = model.petition.petitionId and model1.isDeleted='N' " +
				" and model.petition.isDeleted='N' " );
		sb.append("  and model.pmRefCandidateDesignation.isDeleted='N' and model.pmRepresenteeDesignation.isDeleted='N' " );
		if(filterType != null && (filterType.equalsIgnoreCase("pmOfficer"))){
			sb.append(" and  PAO.petition.petitionId = model.petition.petitionId and PAO.isDeleted='N' ");
		}
		/*sb.append(" and  model.pmRefCandidateDesignation.isDeleted = 'N' and model1.petition.isDeleted='N' ");
		sb.append("  and model.pmRefCandidate.isDeleted = 'N' and model.pmRepresentee.isDeleted = 'N'  ");
		sb.append(" and model2.pmRefCandidateId=model.pmRefCandidateId "); 
		sb.append("  and model1.pmSubject.parentPmSubjectId is null " );
		sb.append("and  model2.pmRefCandidateDesignationId=model.pmRefCandidateDesignation.pmRefCandidateDesignationId" +
				" and  model1.pmDepartment.isDeleted='N' " +
				"and model1.pmSubject.isDeleted='N' and model2.isDeleted='N' ");*/
		
		if(petitionIdsList != null && petitionIdsList.size()>0){
			if(inputVO.getRadioSelection() != null && inputVO.getRadioSelection().trim().equalsIgnoreCase("petition"))
				sb.append(" and model.petition.petitionId in (:petitionIdsList)  ");
			else
				sb.append(" and model1.pmSubWorkDetailsId in (:petitionIdsList)  ");
		}else if(inputVO.getIdsList().contains(92L)){//if data entry operator
			sb.append(" and (model.petition.insertedUserId =:insertedUserId or model.petition.isOldData='Y') ");
		}
		
		if(searchLevelId != null && searchLevelId.longValue()>0L && searchLevelValues != null && searchLevelValues.size()>0){
			if(searchLevelId.longValue() ==2L){
				sb.append(" and  state.stateId in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==3L){
				sb.append(" and  district.districtId in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==4L){
				sb.append(" and  constituency.constituencyId in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==5L){
				sb.append(" and  tehsil.tehsilId in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==7L){
				sb.append(" and  localElectionBody.localElectionBodyId in (:searchLevelValues) ");
			}/*else if(searchLevelId.longValue() ==6L){
				sb.append(" and  panchayat.panchayatId=:searchLevelValue ");
			}*/
		}
		if(filterType != null && filterType.equalsIgnoreCase("name") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model.pmRepresentee.name like '%"+filterValue+"%' ");
		}else if(filterType != null && filterType.equalsIgnoreCase("email") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model.pmRepresentee.email like '%"+filterValue+"%' ");
		}else if(filterType != null && filterType.equalsIgnoreCase("mobile") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model.pmRepresentee.mobileNo =:filterValue ");
		}else if(filterType != null && filterType.equalsIgnoreCase("endorsmentNO") && filterValue != null && !filterValue.isEmpty()){
			//sb.append(" and model1.workEndorsmentNo =:filterValue ");
			sb.append(" and model.petition.endorsmentNo =:filterValue ");
		}else if(filterType != null && filterType.equalsIgnoreCase("department") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model1.pmDepartment.pmDepartmentId in (:filterValue) ");
		}else if(filterType != null && filterType.equalsIgnoreCase("subject") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model1.pmSubject.pmSubjectId in (:filterValue) ");
		}else if(filterType != null && filterType.equalsIgnoreCase("referrelDesignation") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:filterValue) ");
		}else if(filterType != null && filterType.equalsIgnoreCase("representeeDesignation") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model.pmRepresenteeDesignation.pmDesignation.pmDesignationId in (:filterValue) ");
		}else if(filterType != null && filterType.equalsIgnoreCase("referralName") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model.pmRefCandidateDesignation.pmRefCandidateDesignationId in (:filterValue) ");
		}else if(filterType != null && filterType.equalsIgnoreCase("pmOfficer") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and PAO.pmDepartmentDesignationOfficer.pmOfficer.pmOfficerId in (:filterValue) ");
		}else if(filterType != null && filterType.equalsIgnoreCase("lead") && filterValue != null && !filterValue.isEmpty()){
			sb.append(" and model1.pmBriefLead.pmBriefLeadId in (:filterValue) ");
		}
		
		if(filterType != null && !filterType.equalsIgnoreCase("department") && inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size()>0){
			sb.append(" and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(inputVO.getFromRange() != null && inputVO.getToRange() != null){
			sb.append(" and model.petition.noOfWorks between :fromNoOfWorks and :toNoOfWorks " );
		}
		if(inputVO.getMinVal() != null && inputVO.getMaxVal() != null){
			sb.append(" and model.petition.estimationCost between :minEstimationCost and :maxEstimationCost " );
		}
		if(fromDate != null && toDate != null){
			sb.append(" and (date(model.petition.insertedTime) between :fromDate and :toDate) " );
		}
		if(inputVO.getStatusIds() != null && inputVO.getStatusIds().size()>0){
			if(filterType != null && filterType.equalsIgnoreCase("pmOfficer") && filterValue != null && !filterValue.isEmpty()){
				sb.append(" and PAO.pmStatus.pmStatusId in (:statusIds) ");
			}else{
				sb.append(" and model1.pmStatus.pmStatusId in (:statusIds) ");
			}
		}
		if(inputVO.getGovtSchmeIdsList() != null && inputVO.getGovtSchmeIdsList().size()>0){
			sb.append(" and model1.pmDepartment.pmDepartmentId in (:dashBrdDeptIds) ");
		}
		if(inputVO.getSourceIdsList() != null && inputVO.getSourceIdsList().size()>0){
			sb.append(" and model1.pmSubject.pmSubjectId in (:dashBrdSubjIds) ");
		}
		if(inputVO.getBriefLeadIdsList() != null && inputVO.getBriefLeadIdsList().size()>0){
			sb.append(" and model1.pmBriefLeadId in (:briefLeadIdsList) ");
		}
		if(inputVO.getDesignationIds() != null && inputVO.getDesignationIds().size()>0){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:dashBrdRefDesigIds) ");
		}
		if(inputVO.getSubProgramIdsList() != null && inputVO.getSubProgramIdsList().size()>0){
			sb.append(" and model.pmRefCandidateDesignation.pmRefCandidateDesignationId in (:dashBrdRefCanId) ");
		}
		
		if(inputVO != null && inputVO.getSearchDeptIdsList() != null && inputVO.getSearchDeptIdsList().size()>0)
			sb.append(" and model1.pmDepartmentId in (:pmDepartmentIdsList) ");
				
		if(inputVO.getRadioSelection() != null && inputVO.getRadioSelection().trim().equalsIgnoreCase("petition"))
			sb.append("  group by model.petitionId ");
		else
			sb.append("  group by model1.pmSubWorkDetailsId  ");
		
		if(type != null && !type.equalsIgnoreCase("count")){
			sb.append(" order by model.petitionId desc, model.petition.insertedTime desc ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(searchLevelId != null && searchLevelId.longValue()>0L && searchLevelValues != null && searchLevelValues.size()>0){
			query.setParameterList("searchLevelValues", searchLevelValues);
		}
		if(filterType != null && !filterType.equalsIgnoreCase("name") && !filterType.equalsIgnoreCase("email") && filterValue != null && !filterValue.isEmpty()
				  && (filterType.equalsIgnoreCase("mobile") || filterType.equalsIgnoreCase("endorsmentNO") )){
			query.setParameter("filterValue", filterValue);
		}else if(filterValue != null && !filterValue.isEmpty() && !filterType.equalsIgnoreCase("name") && !filterType.equalsIgnoreCase("email")){
			String[] strArr = filterValue.split(",");
			List<Long> filterVals = new ArrayList<Long>();
			if(strArr != null && strArr.length >0){
				for(String str :strArr){
					filterVals.add(Long.valueOf(str));
				}
			}
			query.setParameterList("filterValue", filterVals);
		}
		if(inputVO.getFromRange() != null && inputVO.getToRange() != null){
			query.setParameter("fromNoOfWorks", inputVO.getFromRange());
			query.setParameter("toNoOfWorks", inputVO.getToRange());
		}
		if(inputVO.getMinVal() != null && inputVO.getMaxVal() != null){
			query.setParameter("minEstimationCost", inputVO.getMinVal());
			query.setParameter("maxEstimationCost", inputVO.getMaxVal());
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(inputVO.getStatusIds() != null && inputVO.getStatusIds().size()>0){
			query.setParameterList("statusIds", inputVO.getStatusIds());
		}
		if(filterType != null && !filterType.equalsIgnoreCase("department") && inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size()>0){
			query.setParameterList("deptIds", inputVO.getDeptIdsList());
		}
		if(petitionIdsList != null && petitionIdsList.size()>0){
			query.setParameterList("petitionIdsList",petitionIdsList);
		}else if(inputVO.getIdsList().contains(92L)){//if data entry operator
			query.setParameter("insertedUserId",inputVO.getLocationId());
		}
		if(inputVO.getGovtSchmeIdsList() != null && inputVO.getGovtSchmeIdsList().size()>0){
			query.setParameterList("dashBrdDeptIds", inputVO.getGovtSchmeIdsList());
		}
		if(inputVO.getSourceIdsList() != null && inputVO.getSourceIdsList().size()>0){
			query.setParameterList("dashBrdSubjIds", inputVO.getSourceIdsList());
		}
		if(inputVO.getDesignationIds() != null && inputVO.getDesignationIds().size()>0){
			query.setParameterList("dashBrdRefDesigIds", inputVO.getDesignationIds());
		}
		if(inputVO.getSubProgramIdsList() != null && inputVO.getSubProgramIdsList().size()>0){
			query.setParameterList("dashBrdRefCanId", inputVO.getSubProgramIdsList());
		}
		if(inputVO.getIdsList() != null && inputVO.getIdsList().size()>0)
			;//query.setParameterList("pmOfficerIdsList", inputVO.getIdsList());
		if(type != null && !type.equalsIgnoreCase("count")){
			if(petitionIdsList == null || petitionIdsList.size()==0){
				query.setFirstResult(inputVO.getFirstIndex());
				query.setMaxResults(inputVO.getMaxResult());
			}
		}
		if(inputVO.getBriefLeadIdsList() != null && inputVO.getBriefLeadIdsList().size()>0)
			query.setParameterList("briefLeadIdsList", inputVO.getBriefLeadIdsList());
		if(inputVO != null && inputVO.getSearchDeptIdsList() != null && inputVO.getSearchDeptIdsList().size()>0)
			query.setParameterList("pmDepartmentIdsList", inputVO.getSearchDeptIdsList());
		return query.list();
	}
	public List<Long> getPmRepresenteeRefDetailsIds(Long petitionId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresenteeRefDetailsId from PmRepresenteeRefDetails model where model.isDeleted ='N' and model.petitionId =:petitionId ");
		Query query=getSession().createQuery(sb.toString());
		query.setParameter("petitionId", petitionId);
		return query.list();
	}
	public int updatePmRepresenteRefDetails(List<Long> representeRefDetailsIds,Date updatedTime,Long userId){
		StringBuilder sb = new StringBuilder();
		sb.append(" update PmRepresenteeRefDetails model set model.isDeleted ='Y', model.updatedTime=:updatedTime,model.updatedUserId= :userId "
				+ "where model.pmRepresenteeRefDetailsId in (:representeRefDetailsIds) ");
		Query query=getSession().createQuery(sb.toString());
		query.setParameterList("representeRefDetailsIds", representeRefDetailsIds);
		query.setParameter("updatedTime", updatedTime);
		query.setParameter("userId", userId);
		return query.executeUpdate();
	}	
	public Long getRepresenteeDetailsByPetitonId(Long petitionId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresenteeId from PmRepresenteeRefDetails model where model.isDeleted ='N' and model.petitionId =:petitionId ");
		Query query=getSession().createQuery(sb.toString());
		query.setParameter("petitionId", petitionId);
		return (Long) query.uniqueResult();
	}
	
	public List<Long> getPmReferralCandidateIdsByDesigIds(List<Long> desigIds,Long refCandId,List<Long> statusIds,List<Long> deptIdsList){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct model.pmRefCandidateDesignation.pmRefCandidate.pmRefCandidateId from PmRepresenteeRefDetails model," +
				"PmSubWorkDetails model1 where model.isDeleted ='N' and model1.petition.petitionId=model.petition.petitionId and model1.isDeleted ='N' ");
		if(desigIds!=null && desigIds.size() >0){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}
		if(refCandId != null && refCandId.longValue()>0l){
			sb.append("  and  model.pmRefCandidateDesignation.pmRefCandidate.pmRefCandidateId = :refCandId ");
		}
		if(statusIds != null && statusIds.size() >0){
			sb.append("  and  model1.pmStatus.pmStatusId in (:statusIds) ");
		}
		if(deptIdsList != null && deptIdsList.size() >0){
			sb.append(" and model1.pmDepartment.pmDepartmentId in(:deptIdsList) ");
		}
		Query query = getSession().createQuery(sb.toString());
		if(desigIds!=null && desigIds.size() >0){
			query.setParameterList("desigIds", desigIds);
		}
		if(refCandId != null && refCandId.longValue()>0l){
			query.setParameter("refCandId", refCandId);
		}
		if(statusIds != null && statusIds.size() >0){
			query.setParameterList("statusIds", statusIds);
		}
		if(deptIdsList != null && deptIdsList.size() >0){
			query.setParameterList("deptIdsList", deptIdsList);
		}
		return query.list();
	}
}
