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
import com.itgrids.dto.PetitionsInputVO;
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
	
	public List<Object[]> get111RepresentativeSearchWiseDetails(InputVO inputVO,Date fromDate,Date toDate,Set<Long> petitionIdsList,String type){
		StringBuilder sb = new StringBuilder();
		String  filterValue = inputVO.getFilterValue();
		Long searchLevelId = inputVO.getSearchLevelId();
		List<Long> searchLevelValues = inputVO.getSearchLvlVals();
		String filterType = inputVO.getFilterType();
		
		sb.append(" SELECT ");

		sb.append(" p.petition_id,CONCAT('#',date(p.representation_date)) ,CONCAT('#',date(sub.inserted_time)), ");
		sb.append(" p.endorsment_no,p.representee_type,p.no_of_works, ");
		sb.append(" repd1.designation,sub.pm_sub_work_details_id,p.pm_status_id, ");
		sb.append(" dis.district_name,c.name,t.tehsil_name, p1.panchayat_name, ");
		sb.append(" sub.grievance_description,sub.cost_estimation,rcand.name,refd.designation ");

		sb.append(" from  ");

		sb.append(" pm_sub_work_details sub  ");
		sb.append(" LEFT JOIN pm_status st on sub.pm_status_id = st.pm_status_id and st.is_deleted='N'  ");
		sb.append(" LEFT JOIN pm_department d  on d.pm_department_id = sub.pm_department_id and d.is_deleted='N' ");
		sb.append(" LEFT JOIN pm_subject ps on sub.pm_subject_id = ps.pm_subject_id and ps.is_deleted='N' ");
		sb.append(" LEFT JOIN location_address la on sub.address_id = la.location_address_id  ");
		sb.append(" LEFT JOIN state s on s.state_id = la.state_id  ");
		sb.append(" LEFT JOIN district dis on la.district_id = dis.district_id  ");
		sb.append(" LEFT JOIN constituency c on la.constituency_id = c.constituency_id  ");
		sb.append(" LEFT JOIN tehsil t on la.tehsil_id = t.tehsil_id  ");
		sb.append(" LEFT JOIN panchayat p1 on la.panchayat_id = p1.panchayat_id , ");

		sb.append(" petition p ");
		sb.append(" LEFT JOIN pm_status s1 on p.pm_status_id = s1.pm_status_id and s1.is_deleted='N'  ");
		sb.append(" left join pm_representee_ref_details ref on ref.petition_id = p.petition_id and ref.is_deleted='N'  ");
		sb.append(" LEFT JOIN pm_ref_cand_designation ref_des on ref.pm_ref_candidate_designation_id = ref_des.pm_ref_cand_designation_id   and ref_des.is_deleted='N'  ");

		sb.append(" LEFT JOIN pm_ref_candidate rcand on ref.pm_ref_candidate_id = rcand.pm_ref_candidate_id and rcand.is_deleted='N'  ");
		sb.append(" LEFT JOIN location_address refL	on rcand.address_id = refL.location_address_id  ");
		sb.append(" LEFT JOIN state refs on refs.state_id = refL.state_id  ");
		sb.append(" LEFT JOIN district refdis on refL.district_id = refdis.district_id  ");
		sb.append(" LEFT JOIN constituency refc on refL.constituency_id = refc.constituency_id  ");
		sb.append(" LEFT JOIN tehsil reft on refL.tehsil_id = reft.tehsil_id  ");
		sb.append(" LEFT JOIN panchayat refp1 on refL.panchayat_id = refp1.panchayat_id ");
		
		sb.append(" LEFT JOIN pm_representee rep on ref.pm_representee_id = rep.pm_representee_id   and rep.is_deleted='N'  ");
		sb.append(" LEFT JOIN location_address repL	on rep.address_id = repL.location_address_id  ");
		sb.append(" LEFT JOIN state reps on reps.state_id = repL.state_id  ");
		sb.append(" LEFT JOIN district repdis on repdis.district_id = repL.district_id  ");
		sb.append(" LEFT JOIN constituency repc on repc.constituency_id = repL.constituency_id  ");
		sb.append(" LEFT JOIN tehsil rept on rept.tehsil_id = repL.tehsil_id  ");
		sb.append(" LEFT JOIN panchayat repp1 on repp1.panchayat_id = repL.panchayat_id ");

		sb.append(" LEFT JOIN pm_representee_designation repd on repd.pm_representee_designation_id = ref.pm_representtee_designation_id  and repd.is_deleted='N'  ");
		sb.append(" LEFT JOIN pm_designation refd on repd.pm_designation_id = refd.pm_designation_id  and refd.is_deleted='N'   ");
		sb.append(" LEFT JOIN pm_designation repd1 on repd1.pm_designation_id = repd.pm_designation_id  and repd1.is_deleted='N' ");

		sb.append(" where  ");
		sb.append(" p.petition_id = sub.petition_id and  ");
		sb.append(" sub.pm_department_id = d.pm_department_id and  ");
		sb.append(" p.is_deleted='N' and sub.is_deleted='N' and  ");
		sb.append(" d.is_deleted='N' and  d.pm_department_id in (17,22,27,34)   ");

		
		if(filterType != null && (filterType.equalsIgnoreCase("referrelDesignation") 
				|| filterType.equalsIgnoreCase("referralName")) && searchLevelId != null && searchLevelId.longValue()>0L){
			if(searchLevelId.longValue() ==2L){
				sb.append(" and  s.state_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==3L){
				sb.append(" and  d.district_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==4L){
				sb.append(" and  c.constituency_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==5L){
				sb.append(" and  t.tehsil_id in (:searchLevelValues) ");
			}
		}else if(filterType != null && filterType.equalsIgnoreCase("representeeDesignation") && searchLevelId != null && searchLevelId.longValue()>0L ){
			if(searchLevelId.longValue() ==2L){
				sb.append(" and  s.state_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==3L){
				sb.append(" and  d.district_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==4L){
				sb.append(" and  c.constituency_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==5L){
				sb.append(" and  t.tehsil_id in (:searchLevelValues) ");
			}
		}else if(filterType != null && filterType.equalsIgnoreCase("referral") && searchLevelId != null && searchLevelId.longValue()>0L){
			if(searchLevelId.longValue() ==2L){
				sb.append(" and  s.state_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==3L){
				sb.append(" and  d.district_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==4L){
				sb.append(" and  c.constituency_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==5L){
				sb.append(" and  t.tehsil_id in (:searchLevelValues) ");
			}
		}else if(filterType != null && filterType.equalsIgnoreCase("representee")){
			if(searchLevelId.longValue() ==2L){
				sb.append(" and  s.state_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==3L){
				sb.append(" and  d.district_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==4L){
				sb.append(" and  c.constituency_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==5L){
				sb.append(" and  t.tehsil_id in (:searchLevelValues) ");
			}
		}else{
			if(searchLevelId.longValue() ==2L){
				sb.append(" and  s.state_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==3L){
				sb.append(" and  d.district_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==4L){
				sb.append(" and  c.constituency_id in (:searchLevelValues) ");
			}else if(searchLevelId.longValue() ==5L){
				sb.append(" and  t.tehsil_id in (:searchLevelValues) ");
			}
		}
		
		
		
		if(petitionIdsList != null && petitionIdsList.size()>0){
			if(inputVO.getRadioSelection() != null && inputVO.getRadioSelection().trim().equalsIgnoreCase("petition"))
				sb.append(" and p.petition_id in (:petitionIdsList)  ");
			else
				sb.append(" and model1.pmSubWorkDetailsId in (:petitionIdsList)  ");
		}else if(inputVO.getIdsList().contains(92L)){//if data entry operator
			sb.append(" and (p.insertedUserId =:insertedUserId or p.isOldData='Y') ");
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
			}
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
			sb.append(" and d.pm_department_id in (:filterValue) ");
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
			sb.append(" and d.pm_department_id in (:deptIds) ");
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
				",district1.districtId,district1.districtName,constituency1.constituencyId,constituency1.name,tehsil1.tehsilId,tehsil1.tehsilName " +//26,27 ,28,29  ,30,31
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
		//if(filterType != null && (filterType.equalsIgnoreCase("all") || filterType.equalsIgnoreCase("email") || filterType.equalsIgnoreCase("mobile") || 
		//		filterType.equalsIgnoreCase("endorsmentNO") || filterType.equalsIgnoreCase("all") || filterType.equalsIgnoreCase("all")) ){
		//			sb.append(" left join model1.locationAddress locationAddress " );		
		/*}else if(filterType != null && (filterType.equalsIgnoreCase("work") && searchLevelId != null && searchLevelId.longValue()>0L)){
			sb.append(" left join model1.locationAddress locationAddress " );
		}else */if(filterType != null && (filterType.equalsIgnoreCase("referrelDesignation") 
				|| filterType.equalsIgnoreCase("referralName")) && searchLevelId != null && searchLevelId.longValue()>0L){
			sb.append(" left join model.pmRefCandidate.address locationAddress " );
		}else if(filterType != null && filterType.equalsIgnoreCase("representeeDesignation") && searchLevelId != null && searchLevelId.longValue()>0L ){
			sb.append(" left join model.pmRepresenteeDesignation.pmRepresentee.userAddress locationAddress " );
		}else if(filterType != null && filterType.equalsIgnoreCase("referral") && searchLevelId != null && searchLevelId.longValue()>0L){
			sb.append(" left join model.pmRefCandidate.address locationAddress " );
		}else if(filterType != null && filterType.equalsIgnoreCase("representee")){
			sb.append(" left join model.pmRepresentee.userAddress locationAddress " );
		}else{
			sb.append(" left join model1.locationAddress locationAddress " );
		}
		
		//if(searchLevelId != null && searchLevelId.longValue()>0L){
		sb.append("  left join locationAddress.state state " +
					" left join locationAddress.district district " +
					" left join locationAddress.constituency constituency " +
					" left join locationAddress.tehsil tehsil " +
					" left join locationAddress.localElectionBody localElectionBody " );
					//" left join locationAddress.panchayat panchayat  ");
		//}
		
		sb.append(" left join model1.locationAddress locationAddress1 " );
		sb.append(" left join locationAddress1.state state1 " +
				" left join locationAddress1.district district1 " +
				" left join locationAddress1.constituency constituency1 " +
				" left join locationAddress1.tehsil tehsil1 " +
				" left join locationAddress1.localElectionBody localElectionBody1 " );
				//" left join locationAddress1.panchayat panchayat1  ");
	
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
	
	public List<Object[]> getPetitionsDetailsForPdf(PetitionsInputVO inputVO){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT ");
		sb.append(" p.petition_id as petition_id,CONCAT('#',date(p.representation_date))as representation_date ,CONCAT('#',date(p.endorsment_date)) as endorsment_date, ");
		sb.append(" p.endorsment_no as endorsment_no,p.representee_type as representee_type ,p.no_of_works as no_of_works, ");
		sb.append(" sub.pm_sub_work_details_id as pm_sub_work_details_id ,p.pm_status_id as pm_status_id, ");
		sb.append(" st.state_id as state_id,dis.district_id as district_id,c.constituency_id as constituency_id,t.tehsil_id as tehsil_id, p1.panchayat_id as panchayat_id, ");
		sb.append(" st.state_name as state_name,dis.district_name as district_name,c.name as cname ,t.tehsil_name as tehsil_name, p1.panchayat_name as panchayat_name, ");
		sb.append(" sub.grievance_description as grievance_description ,sub.cost_estimation as cost_estimation,repd1.designation as repDesignation,rep.name as repname ,  ");//21
		sb.append(" refd.designation as refDesignation,rcand.name as refName ,off.name as officerName , ");//24
		sb.append(" d.pm_department_id as pm_department_id, d.department as departmentName ");//25,26
		sb.append(" from  ");
		sb.append(" pm_sub_work_details sub  ");
		sb.append(" LEFT JOIN pm_department d  on d.pm_department_id = sub.pm_department_id and d.is_deleted='N' ");
		sb.append(" LEFT JOIN pm_subject ps on sub.pm_subject_id = ps.pm_subject_id and ps.is_deleted='N' ");
		sb.append(" LEFT JOIN location_address la on sub.address_id = la.location_address_id  ");
		sb.append(" LEFT JOIN state st on la.state_id = st.state_id  " +
				  " LEFT JOIN district dis on la.district_id = dis.district_id  ");
		sb.append(" LEFT JOIN constituency c on la.constituency_id = c.constituency_id  ");
		sb.append(" LEFT JOIN tehsil t on la.tehsil_id = t.tehsil_id  ");
		sb.append(" LEFT JOIN panchayat p1 on la.panchayat_id = p1.panchayat_id , ");
		sb.append(" petition p ");
		sb.append(" left join pm_representee_ref_details ref on ref.petition_id = p.petition_id and ref.is_deleted='N'  ");
		sb.append(" LEFT JOIN pm_ref_cand_designation ref_des on ref.pm_ref_candidate_designation_id = ref_des.pm_ref_cand_designation_id   and ref_des.is_deleted='N'  ");
		sb.append(" LEFT JOIN pm_ref_candidate rcand on ref.pm_ref_candidate_id = rcand.pm_ref_candidate_id and rcand.is_deleted='N'  ");
		sb.append(" LEFT JOIN pm_representee rep on ref.pm_representee_id = rep.pm_representee_id   and rep.is_deleted='N'  ");
		sb.append(" LEFT JOIN pm_representee_designation repd on repd.pm_representee_designation_id = ref.pm_representtee_designation_id  and repd.is_deleted='N'  ");
		sb.append(" LEFT JOIN pm_designation refd on repd.pm_designation_id = refd.pm_designation_id  and refd.is_deleted='N'   ");
		sb.append(" LEFT JOIN pm_designation repd1 on repd1.pm_designation_id = repd.pm_designation_id  and repd1.is_deleted='N', ");
		sb.append(" pm_petition_assigned_officer a ");
		sb.append(" LEFT JOIN pm_dept_designation_officer o on a.pm_dept_designation_officer_id = o.pm_dept_designation_officer_id  ");
		sb.append(" LEFT JOIN pm_dept_designation dd on o.pm_dept_designation_id  =dd.pm_dept_designation_id and dd.is_deleted='N' ");
		sb.append(" LEFT JOIN pm_officer_designation pod on dd.pm_officer_designation_id = pod.pm_officer_designation_id  ");
		sb.append(" LEFT JOIN pm_officer off on o.pm_officer_id = off.pm_officer_id   ");
		sb.append(" where  ");
		sb.append(" p.petition_id = sub.petition_id and  ");
		sb.append(" sub.pm_department_id = d.pm_department_id and  ");
		sb.append(" p.is_deleted='N' and sub.is_deleted='N' and  ");
		sb.append(" p.petition_id = a.petition_id and d.is_deleted='N' and  ");
		sb.append(" a.pm_petition_assigned_officer_id in ( ");
		sb.append(" SELECT max(o1.pm_petition_assigned_officer_id) from pm_petition_assigned_officer o1 where o1.is_deleted='N' GROUP BY o1.petition_id ");
		sb.append(" )   ");
		
		if( inputVO.getConstituencyIdsList() != null &&  inputVO.getConstituencyIdsList().size()>0)
			sb.append(" and c.constituency_id in (:constituencyIdsList)  ");
		if( inputVO.getDeptIdsList() != null &&  inputVO.getDeptIdsList().size()>0)
			sb.append("  and  d.pm_department_id in (:deptIdsList)   ");
		if( inputVO.getStatusIdsList() != null &&  inputVO.getStatusIdsList().size()>0)
			sb.append("  and  sub.pm_status_id in (:statusIdsList)   ");
		if( inputVO.getFromDate() != null &&  inputVO.getEndDate() != null)
			sb.append(" and (date(sub.inserted_time) between :startDate and :endDate)  ");
		if( inputVO.getSubjectIdsList() != null &&  inputVO.getSubjectIdsList().size()>0)
			sb.append("  and  sub.pm_subject_id in (:subjectIdsList)   ");
		if( inputVO.getSubSubjectIdsList() != null &&  inputVO.getSubSubjectIdsList().size()>0)
			sb.append("  and  sub.pm_sub_subject_id in (:subSubjectIdsList)   ");
		
		Query query = getSession().createSQLQuery(sb.toString());
				/*.addScalar("petition_id", StandardBasicTypes.LONG)
				.addScalar("representation_date", StandardBasicTypes.STRING)
				.addScalar("endorsment_date", StandardBasicTypes.STRING)
				.addScalar("endorsment_no", StandardBasicTypes.STRING)
				.addScalar("representee_type", StandardBasicTypes.STRING)
				.addScalar("no_of_works", StandardBasicTypes.LONG)
				.addScalar("pm_sub_work_details_id", StandardBasicTypes.LONG)
				.addScalar("pm_status_id", StandardBasicTypes.LONG)
				.addScalar("state_id", StandardBasicTypes.LONG)
				.addScalar("district_id", StandardBasicTypes.LONG)
				.addScalar("constituency_id", StandardBasicTypes.LONG)
				.addScalar("tehsil_id", StandardBasicTypes.LONG)
				.addScalar("panchayat_id", StandardBasicTypes.LONG)
				.addScalar("state_name", StandardBasicTypes.STRING)
				.addScalar("district_name", StandardBasicTypes.STRING)
				.addScalar("cname", StandardBasicTypes.STRING)
				.addScalar("tehsil_name", StandardBasicTypes.STRING)
				.addScalar("panchayat_name", StandardBasicTypes.STRING)
				.addScalar("grievance_description", StandardBasicTypes.STRING)
				.addScalar("cost_estimation", StandardBasicTypes.STRING)
				.addScalar("repDesignation", StandardBasicTypes.STRING)
				.addScalar("repname", StandardBasicTypes.STRING)
				.addScalar("refDesignation", StandardBasicTypes.STRING)
				.addScalar("refName", StandardBasicTypes.STRING)3
				.addScalar("officerName", StandardBasicTypes.STRING);*/
		if( inputVO.getConstituencyIdsList() != null &&  inputVO.getConstituencyIdsList().size()>0)
			query.setParameterList("constituencyIdsList", inputVO.getConstituencyIdsList());
		if( inputVO.getDeptIdsList() != null &&  inputVO.getDeptIdsList().size()>0)
			query.setParameterList("deptIdsList", inputVO.getDeptIdsList());
		if( inputVO.getStatusIdsList() != null &&  inputVO.getStatusIdsList().size()>0)
			query.setParameterList("statusIdsList", inputVO.getStatusIdsList());
		if( inputVO.getSubjectIdsList() != null &&  inputVO.getSubjectIdsList().size()>0)
			query.setParameterList("subjectIdsList", inputVO.getSubjectIdsList());
		if( inputVO.getSubSubjectIdsList() != null &&  inputVO.getSubSubjectIdsList().size()>0)
			query.setParameterList("subSubjectIdsList", inputVO.getSubSubjectIdsList());
		if( inputVO.getFromDate() != null &&  inputVO.getEndDate() != null){
			query.setParameter("startDate", inputVO.getFromDate());
			query.setParameter("endDate", inputVO.getEndDate());
		}
		return query.list();
	}
}
