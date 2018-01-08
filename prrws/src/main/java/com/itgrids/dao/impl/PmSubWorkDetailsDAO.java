package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmSubWorkDetailsDAO;
import com.itgrids.model.PmSubWorkDetails;

@Repository
public class PmSubWorkDetailsDAO extends GenericDaoHibernate<PmSubWorkDetails, Long> implements IPmSubWorkDetailsDAO {

	@Autowired
	SessionFactory sessionFactory;
	PmSubWorkDetailsDAO(){
		super(PmSubWorkDetails.class);
	}
	
	public List<Object[]> getPetitionSubWorksDetails(Long petitionId){
		StringBuilder str = new StringBuilder();
		str.append(" select  model.petitionId, model.costEstimation, model.grievanceDescrption, model.eOfficeId , pmSubject.pmSubjectId, pmSubSubject.pmSubjectId , " +//0,1,2,3,4,5
				" pmLead.pmLeadId,pmBriefLead.pmBriefLeadId,pmGrant.pmGrantId,pmStatus.pmStatusId,pmDepartment.pmDepartmentId,pmWorkType.pmWorkTypeId, " +//6,7,8,9,10,11
				" state.stateId,district.districtId,constituency.constituencyId,tehsil.tehsilId,localBody.localElectionBodyId,model.pmSubWorkDetailsId," +//12,13,14,15,16,17
				" model.uiBuildSeriesNo,model.locationScopeId,model.locationValue, " +//18,19,20
				//"'','','','','','',"+
				" state.stateName,district.districtName,constituency.name,tehsil.tehsilName,localBody.name,electionType.electionType," +//21,22,23,24,25,26
				//"'','','','','','','',''"+
				" pmLead.leadName,pmBriefLead.briefLead,pmGrant.pmGrantName,pmStatus.status,pmDepartment.department,pmWorkType.workType, pmSubject.subject,pmSubSubject.subject " +//27,28,29,30,31,32,33,34
				" ,model.uiBuildSeriesNo,panchayat.panchayatId,panchayat.panchayatName " +//35,36,37
				" ,model.workEndorsmentNo, model.endorsmentDate " +//38,39
				" from PmSubWorkDetails model " +
				" left join model.locationAddress address" +
				" left join address.state state " +
				" left join address.district district" +
				" left join address.constituency constituency" +
				" left join address.tehsil tehsil" +
				" left join address.panchayat panchayat" +
				" left join address.localElectionBody localBody " +
				" left join localBody.electionType electionType" +
				" left join model.pmSubject pmSubject " +
				" left join model.pmSubSubject pmSubSubject" +
				" left join model.pmLead pmLead " +
				" left join model.pmBriefLead pmBriefLead" +
				" left join model.pmGrant pmGrant " +
				" left join model.pmStatus pmStatus " +
				" left join model.pmDepartment pmDepartment " +
				" left join model.pmWorkType pmWorkType " +
				" where model.petitionId =:petitionId and model.isDeleted='N' ORDER BY model.pmSubWorkDetailsId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("petitionId", petitionId);
		return query.list();
	}
	
	
	public List<Object[]> getAllDistricts(Date fromDate,Date toDate,List<Long> deptIds,List<Long> desigIds,String desigType,List<Long> subjtIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.locationAddress.district.districtId");
		sb.append(",model.locationAddress.district.districtName ");
		sb.append(" from PmSubWorkDetails model,PmRepresenteeRefDetails model1  " +
				" where model1.pmRepresenteeDesignation.pmRepresentee.isDeleted='N' and  model.isDeleted='N' and model1.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId " );
		if(deptIds != null && deptIds.size() >0){
			sb.append(" and model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and (date(model.insertedTime) between :fromDate and :toDate ) ");
		}
		if(subjtIds != null && subjtIds.size() >0){
			sb.append(" and model.pmSubject.pmSubjectId in (:subjtIds) ");
		}
		if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("referral")){
			sb.append(" and model1.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}else if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("representee")){
			sb.append(" and model1.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}
				sb.append(" order by model.locationAddress.district.districtName asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size() >0){
			query.setParameterList("deptIds", deptIds);
		}
		if(desigIds != null && desigIds.size() >0){
			query.setParameterList("desigIds", desigIds);
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		
		if(subjtIds != null && subjtIds.size() >0){
			query.setParameterList("subjtIds", subjtIds);
		}
		return query.list();
	}
	
	public List<Object[]> getAllConstituenciesByDistricId(Date fromDate,Date toDate,List<Long> districtIds,  List<Long> deptIds,List<Long> pmDesignationIds,String type){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.locationAddress.constituency.constituencyId");
		sb.append(",model.locationAddress.constituency.name ");
		sb.append(" from PmSubWorkDetails model,PmRepresenteeRefDetails model1 where model1.pmRefCandidateDesignation.isDeleted='N' " +
				"and model.isDeleted='N'and model1.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId ");
		
		if(districtIds != null && districtIds.size() >0L ){ 
			sb.append("and model.locationAddress.district.districtId in (:districtIds) ");
		}
		if(deptIds != null && deptIds.size() >0L ){ 
			sb.append("and model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and (date(model.insertedTime) between :fromDate and :toDate ) ");
		}
		if(type != null  && type.equalsIgnoreCase("referral") && pmDesignationIds !=null && pmDesignationIds.size()>0){
			sb.append(" and model1.pmRefCandidateDesignation.pmDesignation.pmDesignationId in(:pmDesignationIds) ");
		}
	   else if (type != null  && type.equalsIgnoreCase("representee") && pmDesignationIds !=null && pmDesignationIds.size()>0){
		sb.append(" and model1.pmRepresenteeDesignation.pmDesignation.pmDesignationId in(:pmDesignationIds) ");
	    }
		sb.append( "order by model.locationAddress.constituency.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size() >0L ){ 
			query.setParameterList("deptIds", deptIds);
		}
		if(districtIds != null && districtIds.size() >0L){
			query.setParameterList("districtIds", districtIds);
		}
		if (fromDate != null && toDate != null) {
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(pmDesignationIds != null && pmDesignationIds.size() >0L){
			query.setParameterList("pmDesignationIds", pmDesignationIds);
		}
		return query.list();
	}
	public List<Object[]> getAllMandalsByDistricId(List<Long> constincyIdIds,List<Long> deptIds,Date fromDate,Date toDate,List<Long> desigIds,String desigType){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct tehsil.tehsilId");
		sb.append(",tehsil.tehsilName" +
				" ,localElectionBody.localElectionBodyId,localElectionBody.name," +
				"localElectionBody.electionTypeId ");
		sb.append(" from PmSubWorkDetails model left join model.locationAddress.localElectionBody localElectionBody left join model.locationAddress.tehsil tehsil" +
				",PmRepresenteeRefDetails model1 where  model.isDeleted='N'  and model1.pmRefCandidateDesignation.isDeleted='N' and  model.petition.petitionId = model1.petition.petitionId ");
		if(constincyIdIds != null && constincyIdIds.size() >0L ){ 
			sb.append(" and model.locationAddress.constituencyId in (:constincyIdIds) ");
		}
		if(deptIds != null && deptIds.size() >0){
			sb.append(" and model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate "); 
		}
		if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("referral")){
			sb.append(" and model1.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}else if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("representee")){
			sb.append(" and model1.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}
		sb.append( "order by tehsil.tehsilName asc,localElectionBody.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(constincyIdIds != null && constincyIdIds.size() >0L ){ 
			query.setParameterList("constincyIdIds", constincyIdIds);
		}
		if(deptIds != null && deptIds.size() >0){
			query.setParameterList("deptIds", deptIds);
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(desigIds != null && desigIds.size() >0){
			query.setParameterList("desigIds", desigIds);
		}
		return query.list();
	}
	
	public List<Object[]> getDepartmentsByWorks(List<Long> deptIds,Date fromDate,Date toDate,Long statusId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmDepartment.pmDepartmentId,model.pmDepartment.department "
				+ "from PmSubWorkDetails model where model.isDeleted='N' and model.pmDepartment.isDeleted='N' ");
		if(deptIds != null && deptIds.size()>0){
			sb.append(" and model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate "); 
		}
		if(statusId != null && statusId.longValue() >0l){
			sb.append(" and model.pmStatus.pmStatusId = :statusId ");
		}
		sb.append( "order by model.pmDepartment.orderNo asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size()>0){
			query.setParameterList("deptIds", deptIds);
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(statusId != null && statusId.longValue() >0l){
			query.setParameter("statusId", statusId);
		}
		return query.list();
	}

	public List<Long> getPmSubWorkDetailsIds(Long petitionId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct  model.pmSubWorkDetailsId from PmSubWorkDetails model where model.isDeleted='N' and model.petitionId =:petitionId ");
		Query query =getSession().createQuery(sb.toString());
		query.setParameter("petitionId", petitionId);
		return query.list();
	}
	
	public int updatePmsubWorkDetails(List<Long> subWorkDetailsIds,Date updateTime,Long userId){
		StringBuilder sb = new StringBuilder();
		sb.append(" update PmSubWorkDetails model set model.isDeleted='Y', model.updatedTime =:updateTime,model.updatedUserId =:userId "
				+ " where model.pmSubWorkDetailsId  in (:subWorkDetailsIds) ");
		Query query =getSession().createQuery(sb.toString());
		query.setParameterList("subWorkDetailsIds", subWorkDetailsIds);
		query.setParameter("updateTime", updateTime);
		query.setParameter("userId", userId);
		return query.executeUpdate();
	}

	public List<Object[]> getCompleteOrStatusOverviewDetails(List<Long> deptIds ,Date startDate,Date endDate,String type){
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(distinct model.pmSubWorkDetailsId),model.petition.petitionId, model.pmStatus.pmStatusId,model.pmStatus.status ");
		
		if(type != null && (type.equalsIgnoreCase("statusReferral") || type.equalsIgnoreCase("referral"))){
			sb.append(", model1.pmRefCandidateDesignation.pmDesignation.pmDesignationId,model1.pmRefCandidateDesignation.pmDesignation.designation  ");
		}/*else if(type != null && (type.equalsIgnoreCase("overallStatus") || type.equalsIgnoreCase("status"))){
			sb.append(", model.pmStatus.pmStatusId,model.pmStatus.status ");
		}*/else if(type != null && (type.equalsIgnoreCase("statusSubject") || type.equalsIgnoreCase("subject"))){
			sb.append(", model.pmSubject.pmSubjectId,model.pmSubject.subject ");
		}else if(type != null && (type.equalsIgnoreCase("statusDept") || type.equalsIgnoreCase("department"))){
			sb.append(", model.pmDepartment.pmDepartmentId,model.pmDepartment.department ");
		}
		sb.append("  from PmSubWorkDetails model  ");
		//if(type != null && (type.equalsIgnoreCase("statusReferral") || type.equalsIgnoreCase("referral"))){
			sb.append(" ,PmRepresenteeRefDetails model1,PmRefCandidateDesignation model2 where model1.petition.petitionId=model.petition.petitionId and ");
			sb.append(" model1.pmRefCandidateDesignation.pmDesignation.isDeleted='N' and  model1.pmRefCandidateDesignation.isDeleted = 'N' and model1.isDeleted='N' and model1.pmRepresentee.isDeleted = 'N'  " +
					" and model1.pmRefCandidate.isDeleted = 'N' and model2.pmRefCandidateId=model1.pmRefCandidateId and model.isDeleted='N' and  ");
		//}else{
		//	sb.append(" where ");
		//}
		sb.append("  model.pmDepartment.isDeleted='N' and ");
		//sb.append("  model.pmSubject.isDeleted='N' and  ");
		//if(type.equalsIgnoreCase("subject")){
			sb.append("  model.pmSubject.parentPmSubjectId is null ");
		//}
		if(deptIds != null && deptIds.size() >0){
			 sb.append(" and model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(startDate != null && endDate != null){
			 sb.append(" and date(model.insertedTime) between :startDate and :endDate "); 
		}
		sb.append(" group by   model.petition.petitionId, model.pmStatus.pmStatusId " );
		if(type != null && (type.equalsIgnoreCase("statusReferral") || type.equalsIgnoreCase("referral"))){
			sb.append(", model1.pmRefCandidateDesignation.pmDesignation.pmDesignationId  order by model1.pmRefCandidateDesignation.pmDesignation.preferrableOrderNO asc,model.pmStatus.orderNo asc ");
		}/*else if(type != null && (type.equalsIgnoreCase("overallStatus") || type.equalsIgnoreCase("status"))){
			sb.append(", model.pmStatus.pmStatusId ");
		}*/else if(type != null && (type.equalsIgnoreCase("statusSubject") || type.equalsIgnoreCase("subject"))){
			sb.append(", model.pmSubject.pmSubjectId  order by model.pmSubject.preferrableOrderNO asc,model.pmStatus.orderNo asc ");
		}else if(type != null && (type.equalsIgnoreCase("statusDept") || type.equalsIgnoreCase("department"))){
			sb.append(", model.pmDepartment.pmDepartmentId  order by model.pmDepartment.preferrableOrderNO asc,model.pmStatus.orderNo asc ");
		}else{
			sb.append(" order by model.pmStatus.orderNo asc ");
		}
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size() >0){
			query.setParameterList("deptIds", deptIds);
		}
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		return query.list();
	}
	
	public List<Object[]> getLeadWiseOverviewDetails(List<Long> deptIds ,Date startDate,Date endDate){
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(model.pmSubWorkDetailsId),model.pmLead.pmLeadId,model.pmLead.leadName, model.pmStatus.pmStatusId,model.pmStatus.status ");
		sb.append("  from PmSubWorkDetails model where  ");
		
		//sb.append("  model.pmSubject.isDeleted='N' and model.pmDepartment.isDeleted='N' and model.pmLead.isDeleted='N' and ");
		//sb.append("  model.pmSubject.parentPmSubjectId is null  ");
		if(deptIds != null && deptIds.size() >0){
			 sb.append("   model.pmDepartment.pmDepartmentId in (:deptIds)  ");
		}
		if(startDate != null && endDate != null){
			 sb.append(" and date(model.insertedTime) between :startDate and :endDate "); 
		}
		sb.append(" group by   model.petition.petitionId, model.pmStatus.pmStatusId ,model.pmLead.pmLeadId " );
		
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size() >0){
			query.setParameterList("deptIds", deptIds);
		}
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		return query.list();
	}
	
	public List<Long> getPetitionsSubWorksIdsList(List<Long>  petitionIdsList){
		if(petitionIdsList != null && petitionIdsList.size()>0){
			Query query = getSession().createQuery(" select distinct model.pmSubWorkDetailsId from PmSubWorkDetails model where model.petitionId in (:petitionIdsList) and model.isDeleted='N' ");
			query.setParameterList("petitionIdsList", petitionIdsList);
			return query.list();
		}
		return null;
	}
	
	public List<Object[]> getPetitionsDetailedSubWorksIdsList(List<Long>  petitionIdsList){
		if(petitionIdsList != null && petitionIdsList.size()>0){
			Query query = getSession().createQuery(" select distinct model.petitionId , model.pmSubWorkDetailsId ,model.pmStatusId model.pmSubWorkDetailsId model.pmStatusId from PmSubWorkDetails model where model.petitionId in (:petitionIdsList) and model.isDeleted='N' ");
			query.setParameterList("petitionIdsList", petitionIdsList);
			return query.list();
		}
		return null;
	}
	
	public int updatePetitionSubWorkStatusdetails(List<Long>  petitionIdsList,Date updatedDate,Long updatedUserId,Long pmStatusId){
		if(petitionIdsList != null && petitionIdsList.size()>0){
			Query query = getSession().createQuery(" update PmSubWorkDetails model set model.pmStatusId=:pmStatusId, " +
					" model.updatedTime =:updatedTime,model.updatedUserId = :updatedUserId where model.petitionId in (:petitionIdsList) and model.isDeleted='N' ");
			query.setParameterList("petitionIdsList", petitionIdsList);
			return query.executeUpdate();
		}
		return 0;
	}
	
	public List<Object[]> getSubjectsForSearchPage(List<Long> deptIds,Date fromDate,Date toDate,Long statusId,Long subjectId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmSubject.pmSubjectId,model.pmSubject.subject "
				+ "from PmSubWorkDetails model where model.isDeleted='N' and model.pmSubject.isDeleted='N' " +
				" and model.pmSubject.parentPmSubjectId is null ");
		if(deptIds != null && deptIds.size()>0){
			sb.append(" and model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate "); 
		}
		if(statusId != null && statusId.longValue() >0l){
			sb.append(" and model.pmStatus.pmStatusId = :statusId ");
		}
		
		if(subjectId != null && subjectId.longValue()>0l){
			sb.append(" and model.pmSubject.pmSubjectId = :subjectId ");
		}
		sb.append( "order by model.pmSubject.orderNo asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size()>0){
			query.setParameterList("deptIds", deptIds);
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(statusId != null && statusId.longValue() >0l){
			query.setParameter("statusId", statusId);
		}
		if(subjectId != null && subjectId.longValue()>0l){
			query.setParameter("subjectId", subjectId);
		}
		return query.list();
	}
}
