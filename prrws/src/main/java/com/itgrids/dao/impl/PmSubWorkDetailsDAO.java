package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmSubWorkDetailsDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.PmSubWorkDetails;

@Repository
public class PmSubWorkDetailsDAO extends GenericDaoHibernate<PmSubWorkDetails, Long> implements IPmSubWorkDetailsDAO {

	@Autowired
	SessionFactory sessionFactory;
	PmSubWorkDetailsDAO(){
		super(PmSubWorkDetails.class);
	}
	
	public List<Object[]> getPetitionSubWorksDetails(Long petitionId,List<Long> userAccesseDeptIds){
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
				" where model.petitionId =:petitionId and model.isDeleted='N' ");
		if(userAccesseDeptIds != null && userAccesseDeptIds.size()>0){
			str.append(" and model.pmDepartmentId in (:userAccesseDeptIds) ");
		}
		
		str.append(" ORDER BY model.pmSubWorkDetailsId  ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("petitionId", petitionId);
		if(userAccesseDeptIds != null && userAccesseDeptIds.size()>0){
			query.setParameterList("userAccesseDeptIds", userAccesseDeptIds);
		}
		
		return query.list();
	}
	
	
	public List<Object[]> getAllDistricts(Date fromDate,Date toDate,List<Long> deptIds,List<Long> desigIds,String desigType,List<Long> subjtIds,List<Long> statIds,List<Long> leadIdsList,Set<Long> petitionIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.locationAddress.district.districtId");
		sb.append(",model.locationAddress.district.districtName ");
		sb.append(" from PmSubWorkDetails model,PmRepresenteeRefDetails model1  " +
				" where model1.pmRepresenteeDesignation.pmRepresentee.isDeleted='N' and  model.isDeleted='N' and model1.isDeleted='N' " +
				"and model.petition.petitionId = model1.petition.petitionId and model1.petition.isDeleted='N' " );
		if(deptIds != null && deptIds.size() >0){
			sb.append(" and model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and (date(model.insertedTime) between :fromDate and :toDate ) ");
		}
		if(subjtIds != null && subjtIds.size() >0){
			sb.append(" and model.pmSubject.pmSubjectId in (:subjtIds) ");
		}
		if(leadIdsList != null && leadIdsList.size() >0){
			sb.append(" and model.pmBriefLead.pmBriefLeadId in (:leadIdsList) ");
		}
		if(statIds != null && statIds.size() >0){
			sb.append(" and model.pmStatus.pmStatusId in (:statIds) ");
		}	
		
		if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("referral")){
			sb.append(" and model1.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}else if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("representee")){
			sb.append(" and model1.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}
		if(petitionIdsList != null && petitionIdsList.size() >0){
			sb.append(" and model.petition.petitionId in (:petitionIdsList) ");

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
		
		if(statIds != null && statIds.size() >0){
			query.setParameterList("statIds", statIds);
		}
		if(petitionIdsList != null && petitionIdsList.size() >0){
			query.setParameterList("petitionIdsList", petitionIdsList);
		}
		if(leadIdsList != null && leadIdsList.size() >0){
			query.setParameterList("leadIdsList", leadIdsList);
		}
		return query.list();
	}
	
	public List<Object[]> getAllConstituenciesByDistricId(Date fromDate,Date toDate,List<Long> districtIds,  List<Long> deptIds,List<Long> pmDesignationIds,String type, List<Long> statIds,Set<Long> petitionIdsLst,List<Long> subjectIdsLst,List<Long> leadIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.locationAddress.constituency.constituencyId");
		sb.append(",model.locationAddress.constituency.name ");
		sb.append(" from PmSubWorkDetails model,PmRepresenteeRefDetails model1 where model1.pmRefCandidateDesignation.isDeleted='N' " +
				 " and model.isDeleted='N'and model1.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId " +
				" and model1.petition.isDeleted='N' ");
		
		if(districtIds != null && districtIds.size() >0L ){ 
			sb.append("and model.locationAddress.district.districtId in (:districtIds) ");
		}
		if(deptIds != null && deptIds.size() >0L ){ 
			sb.append("and model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(subjectIdsLst != null && subjectIdsLst.size() >0){
			sb.append("and model.pmSubject.pmSubjectId in (:subjectIdsLst) ");
		}
		if(leadIdsList != null && leadIdsList.size() >0){
			sb.append(" and model.pmBriefLead.pmBriefLeadId in (:leadIdsList) ");
		}
		if(statIds != null && statIds.size() >0){
			sb.append(" and model.pmStatus.pmStatusId in (:statIds) ");
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
		
		if(petitionIdsLst != null && petitionIdsLst.size() >0){
			sb.append(" and model.petition.petitionId in (:petitionIdsLst) ");
		}
		sb.append( "order by model.locationAddress.constituency.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size() >0L ){ 
			query.setParameterList("deptIds", deptIds);
		}
		if(districtIds != null && districtIds.size() >0L){
			query.setParameterList("districtIds", districtIds);
		}
		if(subjectIdsLst != null && subjectIdsLst.size() >0){
			query.setParameterList("subjectIdsLst", subjectIdsLst);
		}
		if(leadIdsList != null && leadIdsList.size() >0){
			query.setParameterList("leadIdsList", leadIdsList);
		}
		if(statIds != null && statIds.size() >0){
			query.setParameterList("statIds", statIds);
		}
		if (fromDate != null && toDate != null) {
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(pmDesignationIds != null && pmDesignationIds.size() >0L){
			query.setParameterList("pmDesignationIds", pmDesignationIds);
		}
		if(petitionIdsLst != null && petitionIdsLst.size() >0){
			query.setParameterList("petitionIdsLst", petitionIdsLst);
		}
		return query.list();
	}
	public List<Object[]> getAllMandalsByDistricId(List<Long> constincyIdIds,List<Long> deptIds,Date fromDate,Date toDate,List<Long> desigIds,String desigType,List<Long> statIds,Set<Long> petitionIdsLst,List<Long> subjectIdsLst,List<Long> leadIdsLst){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct tehsil.tehsilId");
		sb.append(",tehsil.tehsilName" +
				" ,localElectionBody.localElectionBodyId,localElectionBody.name," +
				"localElectionBody.electionTypeId ");
		sb.append(" from PmSubWorkDetails model left join model.locationAddress.localElectionBody localElectionBody left join model.locationAddress.tehsil tehsil" +
				",PmRepresenteeRefDetails model1 where  model.isDeleted='N'  and model1.pmRefCandidateDesignation.isDeleted='N' and  model.petition.petitionId = model1.petition.petitionId " +
				" and model1.petition.isDeleted='N' ");
		if(constincyIdIds != null && constincyIdIds.size() >0L ){ 
			sb.append(" and model.locationAddress.constituencyId in (:constincyIdIds) ");
		}
		if(deptIds != null && deptIds.size() >0){
			sb.append(" and model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(statIds != null && statIds.size() >0){
		     sb.append(" and model.pmStatus.pmStatusId in (:statIds) ");
		}
		if(subjectIdsLst != null && subjectIdsLst.size() >0){
			sb.append(" and model.pmSubject.pmSubjectId in(:subjectIdsLst) ");
		}
		if(leadIdsLst != null && leadIdsLst.size() >0){
			sb.append(" and model.pmBriefLead.pmBriefLeadId in (:leadIdsLst) ");
		}
		
		if(petitionIdsLst != null && petitionIdsLst.size() >0){
			 sb.append(" and model.petition.petitionId in (:petitionIdsLst) ");
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
		if(statIds != null && statIds.size() >0){
			query.setParameterList("statIds", statIds);
		}
		if(subjectIdsLst != null && subjectIdsLst.size() >0){
			query.setParameterList("subjectIdsLst", subjectIdsLst);
		}
		if(leadIdsLst != null && leadIdsLst.size() >0){
			query.setParameterList("leadIdsLst", leadIdsLst);
		}
		if(petitionIdsLst != null && petitionIdsLst.size() >0){
			query.setParameterList("petitionIdsLst", petitionIdsLst);
		}
		return query.list();
	}
	
	public List<Object[]> getDepartmentsByWorks(List<Long> deptIds,Date fromDate,Date toDate,List<Long> statusIds,Set<Long> petitionIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmDepartment.pmDepartmentId,model.pmDepartment.department "
				+ "from PmSubWorkDetails model where model.isDeleted='N' and model.pmDepartment.isDeleted='N' " +
				" and model.petition.isDeleted='N' ");
		if(deptIds != null && deptIds.size()>0){
			sb.append(" and model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate "); 
		}
		if(statusIds != null && statusIds.size() >0){
			sb.append(" and model.pmStatus.pmStatusId in (:statusId) ");
		}
		if(petitionIdsList != null && petitionIdsList.size() >0){
			sb.append(" and model.petition.petitionId in(:petitionIdsList) ");
		}
		sb.append( "order by model.pmDepartment.department asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size()>0){
			query.setParameterList("deptIds", deptIds);
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(statusIds != null && statusIds.size() >0){
			query.setParameterList("statusIds", statusIds);
		}
		if(petitionIdsList != null && petitionIdsList.size() >0){
			query.setParameterList("petitionIdsList", petitionIdsList);
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
	public List<Object[]> getCompleteOrStatusOverviewDetails(List<Long> deptIds ,Date startDate,Date endDate,String type,Set<Long> petitionsIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append(" select  distinct model.pmSubWorkDetailsId," +//0
				" model.petition.petitionId," +//1
				"  model.pmStatus.pmStatusId," +//2
				"model.pmStatus.status ");//3
		sb.append(",round(model.costEstimation,2) as sum " );//4
		//sb.append(",round(sum(model.costEstimation),2) " );//4
		
		//if(type != null && (type.equalsIgnoreCase("statusReferral") || type.equalsIgnoreCase("referral"))){
			sb.append(", pmDesignation.pmDesignationId" +//5
					" ,pmDesignation.designation" + //6
					",pmDesignation.preferrableOrderNO  ");//7
		//}/*else if(type != null && (type.equalsIgnoreCase("overallStatus") || type.equalsIgnoreCase("status"))){
			/*sb.append(", model.pmStatus.pmStatusId," +//8
					" model.pmStatus.status ");//9
*/		//}*/else if(type != null && (type.equalsIgnoreCase("statusSubject") || type.equalsIgnoreCase("subject"))){       
			sb.append(", pmSubject.pmSubjectId," +//8
					" pmSubject.subject," + //9
					" pmSubject.preferrableOrderNO ");//10
		//}else if(type != null && (type.equalsIgnoreCase("statusDept") || type.equalsIgnoreCase("department"))){
			sb.append(",pmDepartment.pmDepartmentId," +//11
					" pmDepartment.department," +//12
					" pmDepartment.preferrableOrderNO ");//13
		//}else{
			//sb.append(",'','','' ");
		//}
		
		sb.append("  from PmSubWorkDetails model,PmRepresenteeRefDetails model1 " );
		sb.append(" left join  model1.pmRepresenteeDesignation model4 ");
				sb.append(" left join model1.pmRefCandidateDesignation model2 " );
		sb.append("  left join  model.pmDepartment pmDepartment left join model.pmSubject pmSubject ");
		sb.append(" left join model2.pmDesignation pmDesignation ");
		//if(type != null && (type.equalsIgnoreCase("statusReferral") || type.equalsIgnoreCase("referral"))){
		sb.append("  where ");
		//	sb.append(" model.petition.petitionId in (3,38,96,107,110,199,326,384,506,1100,1107,1141,1142,1337,1452,1483,1699,1742,100094,100126,100209,100227,100245) and ");
		sb.append("  model.isDeleted='N'  and model1.isDeleted='N' " );
			sb.append("and  model1.petition.petitionId=model.petition.petitionId and model1.petition.isDeleted='N'");
		//	sb.append("  and model2.isDeleted='N' and model4.isDeleted='N' " );
							/*sb.append(" model1.pmRefCandidateDesignation.pmRefCandidateDesignationId=model2.pmRefCandidateDesignationId  " +
					"  and model2.pmRefCandidateId=model1.pmRefCandidateId and model1.petition.isDeleted='N' ");
		sb.append(" and model1.pmRepresentee.isDeleted = 'N'  " +
					" and model1.pmRefCandidate.isDeleted = 'N'     ");
		sb.append("  and model.pmDepartment.isDeleted='N'  ");
		sb.append("  and model.pmSubject.isDeleted='N'   ");
		sb.append(" and  model.pmSubject.parentPmSubjectId is null ");*/
		
		if(deptIds != null && deptIds.size() >0){
			 sb.append(" and pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(startDate != null && endDate != null){
			 sb.append(" and date(model.insertedTime) between :startDate and :endDate "); 
		}
		if(petitionsIdsList != null && petitionsIdsList.size()>0){
			sb.append(" and model.petition.petitionId in (:petitionsIdsList) ");
		}
		
		//sb.append(" group by model.petition.petitionId, model.pmStatus.pmStatusId " );
		//sb.append(", model2.pmDesignation.pmDesignationId,model.pmSubject.pmSubjectId,model.pmDepartment.pmDepartmentId ");
		//sb.append(" order by model.pmStatus.orderNo asc ," +
			//	"model2.pmDesignation.preferrableOrderNO asc,model.pmSubject.preferrableOrderNO asc,model.pmDepartment.preferrableOrderNO asc ");
		//sb.append(", model2.pmDesignation.pmDesignationId,pmSubject.pmSubjectId,pmDepartment.pmDepartmentId order by " +
			//	" model2.pmDesignation.preferrableOrderNO asc,pmSubject.preferrableOrderNO asc,pmDepartment.preferrableOrderNO asc,model.pmStatus.orderNo asc ");
		if(type != null && (type.equalsIgnoreCase("statusReferral") || type.equalsIgnoreCase("referral"))){
			//sb.append(", model2.pmDesignation.pmDesignationId  order by model2.pmDesignation.preferrableOrderNO asc,model.pmStatus.orderNo asc ");
		}/*else if(type != null && (type.equalsIgnoreCase("overallStatus") || type.equalsIgnoreCase("status"))){
			sb.append(", model.pmStatus.pmStatusId ");
		}*/else if(type != null && (type.equalsIgnoreCase("statusSubject") || type.equalsIgnoreCase("subject"))){
			//sb.append(", pmSubject.pmSubjectId  order by pmSubject.preferrableOrderNO asc,model.pmStatus.orderNo asc ");
		}else if(type != null && (type.equalsIgnoreCase("statusDept") || type.equalsIgnoreCase("department"))){
			//sb.append(", pmDepartment.pmDepartmentId  order by pmDepartment.preferrableOrderNO asc,model.pmStatus.orderNo asc ");
		}else{
			 //sb.append(" order by model.pmStatus.orderNo asc ");
		}
		sb.append(" order by model.costEstimation ");
		/*
		 * Query query =getSession().createQuery(" select  distinct  model.petition.petitionId,  model.pmStatus.pmStatusId,model.pmStatus.status ," +
				"round(model.costEstimation,2) as sum , '','',''  , " +
				"model.pmSubject.pmSubjectId, model.pmSubject.subject, model.pmSubject.preferrableOrderNO ,model.pmDepartment.pmDepartmentId, model.pmDepartment.department, " +
				"model.pmDepartment.preferrableOrderNO " +
				"   from " +
				" PmSubWorkDetails model  where model.petition.petitionId in " +
				"(3,38,96,107,110,199,326,384,506,1100,1107,1141,1142,1337,1452,1483,1699,1742,100094,100126,100209,100227,100245)  and model.isDeleted='N'  and" +
				" model.pmDepartment.pmDepartmentId in (17,27,22,34)  order by model.costEstimation  ".toString()).list();
				*/
		/*
		Query query =getSession().createQuery(" select  distinct model1.petition.petitionId , pmDesignation.pmDesignationId ,pmDesignation.designation, pmDesignation.preferrableOrderNO  " +
				//"model.pmSubject.pmSubjectId, model.pmSubject.subject, model.pmSubject.preferrableOrderNO ,model.pmDepartment.pmDepartmentId, model.pmDepartment.department, " +
				//"model.pmDepartment.preferrableOrderNO " +
				"   from " +
				" PmRepresenteeRefDetails model1  " +
				//" left join  model1.pmRepresenteeDesignation model4 " +
				" left join model1.pmRefCandidateDesignation model2    " +
				" left join model2.pmDesignation pmDesignation   where model1.petition.petitionId in " +
				"(3,38,96,107,110,199,326,384,506,1100,1107,1141,1142,1337,1452,1483,1699,1742,100094,100126,100209,100227,100245)  and model1.isDeleted='N'  and" +
				" model1.isDeleted='N' and model1.petition.isDeleted='N'  and model2.isDeleted='N'   ".toString()).list();
				*/
		
		Query query =getSession().createQuery(sb.toString());
		
		if(deptIds != null && deptIds.size() >0){
			query.setParameterList("deptIds", deptIds);
		}
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(petitionsIdsList != null && petitionsIdsList.size()>0){
			query.setParameterList("petitionsIdsList", petitionsIdsList);
		}
		
		return query.list();
	}
	
	public List<Object[]> getLeadWiseOverviewDetails(List<Long> deptIds ,Date startDate,Date endDate,Set<Long> petitionsIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.pmSubWorkDetailsId,model.pmBriefLead.pmBriefLeadId,model.pmBriefLead.briefLead, model.pmStatus.pmStatusId,model.pmStatus.status ");
		sb.append(", model.petition.petitionId,round(model.costEstimation,2)  from PmSubWorkDetails model where model.isDeleted='N'" +
				" and model.petition.isDeleted='N' ");
		
		//sb.append("  and model.pmSubject.isDeleted='N' and model.pmDepartment.isDeleted='N' and model.pmSubject.parentPmSubjectId is null    ");
		//sb.append(" and model.pmLead.isDeleted='N' ");
		if(deptIds != null && deptIds.size() >0){
			 sb.append("  and model.pmDepartment.pmDepartmentId in (:deptIds)  ");
		}
		if(startDate != null && endDate != null){
			 sb.append(" and date(model.insertedTime) between :startDate and :endDate "); 
		}
		if(petitionsIdsList != null && petitionsIdsList.size()>0){
			sb.append(" and model.petition.petitionId in (:petitionsIdsList) ");
		}
		//sb.append(" group by   model.petition.petitionId, model.pmStatus.pmStatusId ,model.pmBriefLead.pmBriefLeadId " );
		
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size() >0){
			query.setParameterList("deptIds", deptIds);
		}
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(petitionsIdsList != null && petitionsIdsList.size()>0){
			query.setParameterList("petitionsIdsList", petitionsIdsList);
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
			Query query = getSession().createQuery(" select distinct model.petitionId , model.pmSubWorkDetailsId ,model.pmStatusId from PmSubWorkDetails model where model.petitionId in (:petitionIdsList) and model.isDeleted='N' ");
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
	
	public List<Object[]> getSubjectsForSearchPage(List<Long> deptIds,Date fromDate,Date toDate,List<Long> statusIds,Long subjectId,Set<Long> petitionIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmSubject.pmSubjectId,model.pmSubject.subject "
				+ "from PmSubWorkDetails model where model.isDeleted='N' and model.pmSubject.isDeleted='N' " +
				" and model.pmSubject.parentPmSubjectId is null and model.petition.isDeleted='N' ");
		if(deptIds != null && deptIds.size()>0){
			sb.append(" and model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate "); 
		}
		if(statusIds != null && statusIds.size() >0){
			sb.append(" and model.pmStatus.pmStatusId in (:statusIds) ");
		}
		
		if(subjectId != null && subjectId.longValue()>0l){
			sb.append(" and model.pmSubject.pmSubjectId = :subjectId ");
		}
		if(petitionIdsList != null && petitionIdsList.size() >0){
			sb.append(" and  model.petition.petitionId in(:petitionIdsList) ");
		}
		sb.append( "order by model.pmSubject.subject asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size()>0){
			query.setParameterList("deptIds", deptIds);
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(statusIds != null && statusIds.size() >0){
			query.setParameterList("statusIds", statusIds);
		}
		if(subjectId != null && subjectId.longValue()>0l){
			query.setParameter("subjectId", subjectId);
		}
		if(petitionIdsList != null && petitionIdsList.size() >0){
			query.setParameterList("petitionIdsList", petitionIdsList);
		}
		return query.list();
	}
	
	public List<Object[]> getReferralWiseOverviewDetails(InputVO inputVO,Date startDate,Date endDate,Set<Long> petitionsIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.pmSubWorkDetailsId,model.petition.petitionId, model.pmStatus.pmStatusId,model.pmStatus.status ");
		sb.append(", model1.pmRefCandidateDesignation.pmDesignation.pmDesignationId,model1.pmRefCandidateDesignation.pmDesignation.designation  ");
		
		//if(inputVO.getDesignationIds() != null && inputVO.getDesignationIds().size()>0){
			//sb.append(", model1.pmRefCandidateDesignation.pmRefCandidate.pmRefCandidateId,model1.pmRefCandidateDesignation.pmRefCandidate.name  ");
		sb.append(", model1.pmRefCandidateDesignation.pmRefCandidateDesignationId,model1.pmRefCandidateDesignation.pmRefCandidate.name  ");
		//}
			sb.append(", round(model.costEstimation,2),model1.pmRefCandidateDesignation.pmDesignation.preferrableOrderNO ");	
		sb.append("  from PmSubWorkDetails model  ");
		sb.append(" ,PmRepresenteeRefDetails model1" );
		//sb.append(" ,PmRefCandidateDesignation model2 ");
		sb.append(" where model1.petition.petitionId=model.petition.petitionId and model1.isDeleted='N'" +
				"  and model.isDeleted='N' and model1.petition.isDeleted='N' ");
		sb.append("  and model1.pmRefCandidateDesignation.isDeleted='N' and model1.pmRepresenteeDesignation.isDeleted='N' " );
				/*sb.append(" and model1.petition.petitionId=model.petition.petitionId  ");
		sb.append(" and model1.isDeleted='N'  and model2.pmRefCandidateId=model1.pmRefCandidateId  " +
				"and model1.petition.isDeleted='N' and  model2.pmRefCandidateDesignationId=model1.pmRefCandidateDesignation.pmRefCandidateDesignationId ");
		
		sb.append(" and model.pmDepartment.isDeleted='N' " +
				"and  model1.pmRefCandidateDesignation.isDeleted = 'N' and model1.pmRepresentee.isDeleted = 'N' and model1.pmRefCandidate.isDeleted = 'N' ");
		sb.append(" and  model.pmSubject.isDeleted='N'   ");
		sb.append("  and model.pmSubject.parentPmSubjectId is null ");*/
		if(inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size() >0){
			 sb.append(" and model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(startDate != null && endDate != null){
			 sb.append(" and date(model.insertedTime) between :startDate and :endDate "); 
		}
		if(inputVO.getDesignationIds() != null && inputVO.getDesignationIds().size() >0 && inputVO.getDesignationIds().get(0) > 0){
			sb.append(" and  model1.pmRefCandidateDesignation.pmDesignation.pmDesignationId=:desigIds ");
		}
		if(inputVO.getLightVendorIdList() != null && inputVO.getLightVendorIdList().size() > 0){
			sb.append(" and model.pmBriefLead.pmBriefLeadId in (:briefLeadIds) ");
		}
		if(inputVO.getDesignationIds() != null && inputVO.getDesignationIds().size() >0 && inputVO.getDesignationIds().get(0) == 0){
			sb.append(" and  model1.pmRefCandidateDesignation.pmDesignation.pmDesignationId not in (1,2,4,7) ");
		}
		if(petitionsIdsList != null && petitionsIdsList.size()>0){
			sb.append(" and model.petition.petitionId in (:petitionsIdsList) ");
		}
		
		//sb.append(" group by   model.petition.petitionId, model.pmStatus.pmStatusId,model1.pmRefCandidateDesignation.pmRefCandidateDesignationId " );
				//",model1.pmRefCandidateDesignation.pmDesignation.pmDesignationId " );
		/*if(inputVO.getDesignationIds() != null && inputVO.getDesignationIds().size()>0){
			sb.append(", model1.pmRefCandidateDesignation.pmRefCandidate.pmRefCandidateId ");
		}*/
		
		sb.append(" order by model1.pmRefCandidateDesignation.pmDesignation.preferrableOrderNO asc " );
		Query query =getSession().createQuery(sb.toString());
		if(inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size() >0){
			query.setParameterList("deptIds", inputVO.getDeptIdsList());
		}
		if(inputVO.getDesignationIds() != null && inputVO.getDesignationIds().size() >0 && inputVO.getDesignationIds().get(0) > 0){
			query.setParameterList("desigIds", inputVO.getDesignationIds());
		}
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(inputVO.getLightVendorIdList() != null && inputVO.getLightVendorIdList().size() > 0){
			query.setParameterList("briefLeadIds", inputVO.getLightVendorIdList());
		}
		if(petitionsIdsList != null && petitionsIdsList.size()>0){
			query.setParameterList("petitionsIdsList", petitionsIdsList);
		}
		return query.list();
	}

	@Override
	public List<Object[]> getPmBriefLeadIds(List<Long> deptIds) {
		StringBuilder sb = new StringBuilder();
		 sb.append(" select distinct model.pmBriefLead.pmBriefLeadId, model.pmBriefLead.briefLead from PmSubWorkDetails model ,PmRepresenteeRefDetails model1 " +
		 		" where model.petitionId = model1.petitionId and model.pmBriefLead.pmBriefLeadId is not null ");
		 sb.append("  and model1.pmRefCandidateDesignation.pmDesignation.isDeleted='N' and  model1.pmRefCandidateDesignation.isDeleted = 'N' " +
					"and model1.isDeleted='N' and model1.pmRepresentee.isDeleted = 'N'  " +
					" and model1.pmRefCandidate.isDeleted = 'N'  and model.isDeleted='N' and model.petition.isDeleted='N'   ");
		 if(deptIds != null && deptIds.size()>0){
		 sb.append(" and model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		 Query query = getSession().createQuery(sb.toString());
		 if(deptIds != null && deptIds.size()>0){
			 query.setParameterList("deptIds", deptIds);
		 }
		 return query.list();		
	}
	
	public List<Object[]> getAllWorksLatesStatusDetails(Long petitionId){
		StringBuilder sb = new StringBuilder();
		 sb.append(" select model.petitionId, model.pmSubWorkDetailsId,model.pmStatusId,model.petition.pmStatusId, model.petition.insertedTime,"
		 		+ "  model.petition.insertedUserId from PmSubWorkDetails model where model.isDeleted='N' and  model.petitionId =:petitionId and model.petition.isDeleted='N' ");
		 Query query = getSession().createQuery(sb.toString());
		 query.setParameter("petitionId", petitionId);
		 return query.list();
	}
	
	public Object[] getMaxEndorsementAndTempEndorsementNos(){
		StringBuilder sb = new StringBuilder();
		sb.append("select max(workEndorsmentNo),max(tempEndorsNo) from PmSubWorkDetails model where model.isDeleted='N' ");
		 Query query = getSession().createQuery(sb.toString());
		 return (Object[])query.uniqueResult();
	}
	
	public int saveTempEndorseNo(Long petitionId,List<Long> subWorkDetailsIds,String tempEndorsNo,Long userId,Date updateTime){
		StringBuilder sb = new StringBuilder();
		sb.append(" update PmSubWorkDetails model set model.tempEndorsNo=:tempEndorsNo, model.updatedTime =:updateTime,model.updatedUserId =:userId "
				+ " where model.pmSubWorkDetailsId  in (:subWorkDetailsIds) and model.petitionId =:petitionId  ");
		
		Query query =getSession().createQuery(sb.toString());
		query.setParameterList("subWorkDetailsIds", subWorkDetailsIds);
		query.setParameter("userId", userId);
		query.setParameter("updateTime", updateTime);
		query.setParameter("petitionId", petitionId);
		query.setParameter("tempEndorsNo", tempEndorsNo);
		return query.executeUpdate();
	}
	public String getMaxEndirseNoAndValidatingEndorseNo(String endorseNo){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.workEndorsmentNo from PmSubWorkDetails model where model.isDeleted='N'" +
				" and model.workEndorsmentNo is not null and model.petition.isDeleted='N' ");
		if(endorseNo != null && !endorseNo.isEmpty()){
			sb.append(" and model.workEndorsmentNo = :endorseNo ");
		}
		 Query query = getSession().createQuery(sb.toString());
		 if(endorseNo != null && !endorseNo.isEmpty()){
			 query.setParameter("endorseNo", endorseNo);
		 }
		 return (String)query.uniqueResult();
	}
	public List<Object[]> getChildOfficersByParentOfficerId(List<Long> deptIds,Date fromDate,Date toDate,List<Long> statusIds,List<Long> pmOfficerids,Set<Long> petitionIdsList,Set<Long> pmOffcrDesigids){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmDepartmentDesignationOfficer.pmOfficer.pmOfficerId" +
				",model.pmDepartmentDesignationOfficer.pmOfficer.name" +
				//",model.pmDepartmentDesignationOfficer.pmDepartmentDesignation.pmDepartment.shortName" +
				//",model.pmDepartmentDesignationOfficer.pmDepartmentDesignation.pmOfficerDesignation.designation "+
				 " from PmPetitionAssignedOfficer model where model.isDeleted='N' and model.pmSubWorkDetails.pmSubject.isDeleted='N' " +
				" and model.pmSubWorkDetails.pmSubject.parentPmSubjectId is null and model.petition.isDeleted='N' ");
		if(deptIds != null && deptIds.size()>0){
			sb.append(" and model.pmDepartmentDesignationOfficer.pmDepartmentDesignation.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(pmOffcrDesigids != null && pmOffcrDesigids.size()>0){
			sb.append(" and model.pmDepartmentDesignationOfficer.pmDepartmentDesignation.pmOfficerDesignation.pmOfficerDesignationId in (:pmOffcrDesigids) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.insertedTime) between :fromDate and :toDate "); 
		}
		if(statusIds != null && statusIds.size() >0){
			sb.append(" and model.pmSubWorkDetails.pmStatus.pmStatusId in (:statusIds) ");
		}
		
		if(pmOfficerids != null && pmOfficerids.size()>0){
			sb.append(" and model.pmDepartmentDesignationOfficer.pmOfficer.pmOfficerId in (:pmOfficerids) ");
		}
		if(petitionIdsList != null && petitionIdsList.size() >0){
			sb.append(" and  model.petition.petitionId in(:petitionIdsList) ");
		}
		//sb.append( "order by model.pmSubject.orderNo asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size()>0){
			query.setParameterList("deptIds", deptIds);
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(statusIds != null && statusIds.size() >0){
			query.setParameterList("statusIds", statusIds);
		}
		if(pmOfficerids != null && pmOfficerids.size()>0){
			query.setParameter("pmOfficerids", pmOfficerids);
		}
		if(petitionIdsList != null && petitionIdsList.size() >0){
			query.setParameterList("petitionIdsList", petitionIdsList);
		}
		if(pmOffcrDesigids != null && pmOffcrDesigids.size()>0){
			query.setParameterList("pmOffcrDesigids", pmOffcrDesigids);
		}
		return query.list();
	}
	
	public List<Object[]> getLocationWiseRepresentationsOverviewDetails(InputVO inputVO,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		
		
		sb.append(" select model.petition.petitionId,model.pmSubWorkDetailsId, model.costEstimation ");
		if(inputVO.getAssetType() != null && inputVO.getAssetType().equalsIgnoreCase("district")){
			sb.append(" ,model.locationAddress.district.districtId,model.locationAddress.district.districtName ");
		}else if(inputVO.getAssetType() != null && inputVO.getAssetType().equalsIgnoreCase("constituency")){
			sb.append(" ,model.locationAddress.constituency.constituencyId,model.locationAddress.constituency.name ");
			sb.append(" ,model.locationAddress.district.districtId,model.locationAddress.district.districtName ");
		}
		sb.append(" from PmSubWorkDetails as  model" +
				//",PmRepresenteeRefDetails as model1  " +
				" where model.isDeleted='N' " +
				" and model.petition.isDeleted='N'  ");
		
		//sb.append("  and model1.pmRefCandidateDesignation.isDeleted='N' and model1.pmRepresenteeDesignation.isDeleted='N'" +
				//" and model1.petition.petitionId = model.petition.petitionId and  model1.isDeleted ='N' " );
		if(inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size() >0){
			sb.append(" and  model.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(inputVO.getStatusIds() != null && inputVO.getStatusIds().size()>0){
			sb.append(" and  model.pmStatus.pmStatusId in (:statusIds) ");
		}
		if(inputVO.getLightVendorIdList() != null && inputVO.getLightVendorIdList().size()>0){
			sb.append(" and model.pmSubject.pmSubjectId in (:subjectIds) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model.petition.insertedTime) between :fromDate and :toDate ");
		}
		Query query = getSession().createQuery(sb.toString());
		
		if(inputVO.getDeptIdsList() != null && inputVO.getDeptIdsList().size() >0){
			query.setParameterList("deptIds", inputVO.getDeptIdsList());
		}
		if(inputVO.getStatusIds() != null && inputVO.getStatusIds().size()>0){
			query.setParameterList("statusIds", inputVO.getStatusIds());
		}
		if(inputVO.getLightVendorIdList() != null && inputVO.getLightVendorIdList().size()>0){
			query.setParameterList("subjectIds", inputVO.getLightVendorIdList());
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		return query.list();
	}
}
