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
				" where model.petitionId =:petitionId and model.petition.isDeleted='N' and model.isDeleted='N' ORDER BY model.pmSubWorkDetailsId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("petitionId", petitionId);
		return query.list();
	}
	
	
	public List<Object[]> getAllDistricts(List<Long> deptIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.locationAddress.district.districtId");
		sb.append(",model.locationAddress.district.districtName ");
		sb.append(" from PmSubWorkDetails model where model.isDeleted = 'N' " );
		if(deptIds != null && deptIds.size() >0){
			sb.append(" and model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
				sb.append(" order by model.locationAddress.district.districtName asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size() >0){
			query.setParameterList("deptIds", deptIds);
		}
		return query.list();
	}
	
	public List<Object[]> getAllConstituenciesByDistricId(List<Long> districtIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.locationAddress.constituency.constituencyId");
		sb.append(",model.locationAddress.constituency.name ");
		sb.append(" from PmSubWorkDetails model where model.isDeleted ='N' ");
		if(districtIds != null && districtIds.size() >0 ){ 
			sb.append("and model.locationAddress.districtId in (:districtIds) ");
		}
		sb.append( "order by model.locationAddress.constituency.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(districtIds != null && districtIds.size() >0 ){ 
			query.setParameterList("districtIds", districtIds);
		}
		return query.list();
	}
	public List<Object[]> getAllMandalsByDistricId(List<Long> constincyIdIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.locationAddress.tehsil.tehsilId");
		sb.append(",model.locationAddress.tehsil.tehsilName ");
		sb.append(" from PmSubWorkDetails model where model.isDeleted ='N' ");
		if(constincyIdIds != null && constincyIdIds.size() >0L ){ 
			sb.append("and model.locationAddress.constituencyId in (:constincyIdIds) ");
		}
		sb.append( "order by model.locationAddress.tehsil.tehsilName asc ");
		Query query =getSession().createQuery(sb.toString());
		if(constincyIdIds != null && constincyIdIds.size() >0L ){ 
			query.setParameterList("constincyIdIds", constincyIdIds);
		}
		return query.list();
	}
	
	public List<Object[]> getDepartmentsByWorks(){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmDepartment.pmDepartmentId,model.pmDepartment.department "
				+ "from PmSubWorkDetails model where model.isDeleted='N' and model.pmDepartment.isDeleted='N' "
				+ "order by model.pmDepartment.orderNo asc ");
		Query query =getSession().createQuery(sb.toString());
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
		sb.append(" select count(model.pmSubWorkDetailsId),model.petition.petitionId, model.pmStatus.pmStatusId,model.pmStatus.status ");
		
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
		if(type != null && (type.equalsIgnoreCase("statusReferral") || type.equalsIgnoreCase("referral"))){
			sb.append(" ,PmRepresenteeRefDetails model1 where model1.petition.petitionId=model.petition.petitionId and ");
			//sb.append(" model1.pmRefCandidateDesignation.pmDesignation.isDeleted='N' and model1.isDeleted='N' and  ");
		}else{
			sb.append(" where ");
		}
			
		//sb.append("  model.pmSubject.isDeleted='N' and model.pmDepartment.isDeleted='N' and ");
		sb.append("  model.pmSubject.parentPmSubjectId is null ");
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
		//sb.append("  model.pmSubject.parentPmSubjectId is null ");
		if(deptIds != null && deptIds.size() >0){
			 sb.append(" and model.pmDepartment.pmDepartmentId in (:deptIds) ");
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
	
}
