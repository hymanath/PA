package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.itgrids.partyanalyst.dao.IInsuranceStatusDAO;
import com.itgrids.partyanalyst.dto.CadreInsuranceInputVO;
import com.itgrids.partyanalyst.model.InsuranceStatus;
import com.itgrids.partyanalyst.utils.IConstants;

public class InsuranceStatusDAO extends GenericDaoHibernate<InsuranceStatus, Long> implements IInsuranceStatusDAO{

	public InsuranceStatusDAO() {
		super(InsuranceStatus.class);
	}

	public List<Object[]> getStatusAndInsuranceCompanyWiseComplaints(Long locationId,Set<Long> locationValuesSet,Long stateId,Long cadreYearId,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select " +
					" gis.grievance_insurance_status_id as statusId," +
					" gic.grievance_insurance_company_id as comapnyId," +
					" gic.company_name as compantName," +
					" cm.issue_type as issueType," +
					" count(distinct cm.Complaint_id) as count," +
					" sum(cm.approved_amount) as amount" +
					" from complaint_master cm,tdp_cadre_enrollment_year tcey,tdp_cadre tc,complaint_master_insurance_company cmic," +
					" grievance_insurance_company gic,grievance_insurance_status gis" +
				" where" +
					" cm.membership_id = tc.membership_id" +
					" and tc.tdp_cadre_id = tcey.tdp_cadre_id" +
					" and cmic.complaint_master_id = cm.Complaint_id" +
					" and gic.grievance_insurance_company_id = cmic.grievance_insurance_company_id" +
					" and cm.grievance_insurance_status_id = gis.grievance_insurance_status_id" +
					//" and tcey.is_deleted = 'N' and tc.is_deleted = 'N'" +
					" and cm.type_of_issue = 'Insurance'" +
					" and cm.delete_status IS NULL" +
					" and (cm.Subject IS NOT NULL OR cm.Subject != '')");
		
		if(stateId != null && stateId > 0l)
			sb.append("and cm.state_id_cmp IN (:stateId)");
		else
			sb.append(" and cm.state_id_cmp IN (1,2)");
		
		if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 2L){
    		//sb.append(" and cm.state_id_cmp IN (:locationValuesSet) ");
		}else if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 3L){
    		sb.append(" and cm.district_id IN (:locationValuesSet) ");
		}else if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 4L){
    		sb.append(" and cm.assembly_id IN (:locationValuesSet) ");
		}else if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 5L){
    		sb.append(" and cm.parliament_id IN (:locationValuesSet) ");
		}      
    	if(cadreYearId != null && cadreYearId.longValue() > 0l)
    		sb.append(" and tcey.enrollment_year_id = :cadreYearId");
    	if(fromDate != null && toDate != null)
    		sb.append(" and date(cm.Raised_Date) between :fromDate and :toDate");
    	sb.append(" group by gis.grievance_insurance_status_id,gic.grievance_insurance_company_id,cm.issue_type");
    	
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("statusId", Hibernate.LONG)
				.addScalar("comapnyId", Hibernate.LONG)
				.addScalar("compantName", Hibernate.STRING)
				.addScalar("issueType", Hibernate.STRING)
				.addScalar("count", Hibernate.LONG)
				.addScalar("amount", Hibernate.LONG);
		
		if(stateId != null && stateId > 0l)
			query.setParameter("stateId", stateId);
		if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() != 2L)
			query.setParameterList("locationValuesSet", locationValuesSet);
		if(cadreYearId != null && cadreYearId.longValue() > 0l)
    		query.setParameter("cadreYearId", cadreYearId);
    	if(fromDate != null && toDate != null){
    		query.setDate("fromDate", fromDate);
    		query.setDate("toDate", toDate);
    	}
		
		return query.list();
	}
	
	public List<Object[]> getAllInsuranceCompanies(){
		Query query = getSession().createSQLQuery("select model.grievance_insurance_company_id as id,model.company_name as name" +
									" from grievance_insurance_company model").addScalar("id", Hibernate.LONG).addScalar("name", Hibernate.STRING);
		
		return query.list();
	}
	
	public List<Long> getStatusAndInsuranceCompanyWiseComplaintIds(Long locationId,Set<Long> locationValuesSet,Long stateId,Long cadreYearId,Date fromDate,Date toDate,
			List<Long> statusIds,Long companyId,String issueType){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct cm.Complaint_id as complaintId" +
					" from complaint_master cm,tdp_cadre_enrollment_year tcey,tdp_cadre tc,complaint_master_insurance_company cmic," +
					" grievance_insurance_company gic,grievance_insurance_status gis" +
				" where" +
					" cm.membership_id = tc.membership_id" +
					" and tc.tdp_cadre_id = tcey.tdp_cadre_id" +
					" and cmic.complaint_master_id = cm.Complaint_id" +
					" and gic.grievance_insurance_company_id = cmic.grievance_insurance_company_id" +
					" and cm.grievance_insurance_status_id = gis.grievance_insurance_status_id" +
				//	" and tcey.is_deleted = 'N' and tc.is_deleted = 'N'" +
					" and cm.type_of_issue = 'Insurance'" +
					" and cm.delete_status IS NULL" +
					" and (cm.Subject IS NOT NULL OR cm.Subject != '')");
		
		if(stateId != null && stateId > 0l)
			sb.append("and cm.state_id_cmp IN (:stateId)");
		//else
		//	sb.append(" and cm.state_id_cmp IN (1,2)");
		
		if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 2L){
    		//sb.append(" and cm.state_id_cmp IN (:locationValuesSet) ");
			
		}else if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 3L){
    		sb.append(" and cm.district_id IN (:locationValuesSet) ");
		}else if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 4L){
    		sb.append(" and cm.assembly_id IN (:locationValuesSet) ");
		}else if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 5L){
    		sb.append(" and cm.parliament_id IN (:locationValuesSet) ");
		}     
    	if(cadreYearId != null && cadreYearId.longValue() > 0l)
    		sb.append(" and tcey.enrollment_year_id = :cadreYearId");
    	if(fromDate != null && toDate != null)
    		sb.append(" and date(cm.Raised_Date) between :fromDate and :toDate");
    	if(statusIds != null && !statusIds.isEmpty())
    		sb.append(" and gis.grievance_insurance_status_id in (:statusIds)");
    	if(companyId != null && companyId.longValue() > 0l)
    		sb.append(" and gic.grievance_insurance_company_id = :companyId");
    	if(issueType != null && issueType.trim().length() > 0)
    		sb.append(" and cm.issue_type = :issueType");
    	/*if(cadreYearId != null && cadreYearId.longValue() == 2l){
    		sb.append(" and year(cm.date_of_incident) between '2012' and '2014'"); 
    	}else if(cadreYearId != null && cadreYearId.longValue() == 3l){
    		sb.append(" and year(cm.date_of_incident) between '2014' and '2016'"); 
    	}else if(cadreYearId != null && cadreYearId.longValue() == 4l){
    		sb.append(" and year(cm.date_of_incident) between '2016' and '2018'"); 
    	}*/
    	
    	Query query = getSession().createSQLQuery(sb.toString()).addScalar("complaintId", Hibernate.LONG);
    	
    	if(stateId != null && stateId > 0l)
			query.setParameter("stateId", stateId);
		if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0  && locationId.longValue() != 2L)
			query.setParameterList("locationValuesSet", locationValuesSet);
		if(cadreYearId != null && cadreYearId.longValue() > 0l)
    		query.setParameter("cadreYearId", cadreYearId);
    	if(fromDate != null && toDate != null){
    		query.setDate("fromDate", fromDate);
    		query.setDate("toDate", toDate);
    	}
    	if(statusIds != null && !statusIds.isEmpty())
    		query.setParameterList("statusIds", statusIds);
    	if(companyId != null && companyId.longValue() > 0l)
    		query.setParameter("companyId", companyId);
    	if(issueType != null && issueType.trim().length() > 0)
    		query.setParameter("issueType", issueType);
    	
    	return query.list();
	}
	
	public List<Object[]> getStatusAndInsuranceCompanyWiseComplaintDetails(List<Long> complaintIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct cm.Complaint_id as complaintId," +
					" tc.first_name as name,tc.mobile_no as mobileNo," +
					" tc.membership_id as membershipId," +
					" d.district_id as districtId," +
					" d.district_name as districtName," +
					" c.constituency_id as constId," +
					" c.name as constituency," +
					" cm.Subject as subject," +
					" cm.description as description," +
					" cm.issue_type as issueType," +
					" gis.grievance_insurance_status_id statusId," +
					" gis.status as status," +
					" cm.Raised_Date as postedDate," +
					" t.tehsil_id as tehsilId," +
					" t.tehsil_name as tehsilName," +
					" leb.local_election_body_id as lebId," +
					" leb.name as lebName," +
					" p.panchayat_id as pancId," +
					" p.panchayat_name as pancName," +
					" w.constituency_id as wardId," +
					" w.name as wardName" +
					
					" from complaint_master cm,grievance_insurance_status gis,tdp_cadre tc,user_address ua" +
					" left join district d on ua.district_id = d.district_id" +
					" left join constituency c on ua.constituency_id = c.constituency_id" +
					" left join tehsil t on ua.tehsil_id = t.tehsil_id" +
					" left join local_election_body leb on ua.local_election_body = leb.local_election_body_id" +
					" left join panchayat p on ua.panchayat_id = p.panchayat_id" +
					" left join constituency w on ua.ward = w.constituency_id" +
				" where cm.grievance_insurance_status_id = gis.grievance_insurance_status_id" +
					" and cm.membership_id = tc.membership_id" +
					" and tc.address_id = ua.user_address_id" +
					" and cm.Complaint_id in (:complaintIds)");
					
    	
    	Query query = getSession().createSQLQuery(sb.toString())
    			.addScalar("complaintId", Hibernate.LONG).addScalar("name", Hibernate.STRING)
    			.addScalar("mobileNo", Hibernate.STRING).addScalar("membershipId", Hibernate.STRING)
    			.addScalar("districtId", Hibernate.LONG).addScalar("districtName", Hibernate.STRING)
    			.addScalar("constId", Hibernate.LONG).addScalar("constituency", Hibernate.STRING)
    			.addScalar("subject", Hibernate.STRING).addScalar("description", Hibernate.STRING)
    			.addScalar("issueType", Hibernate.STRING).addScalar("statusId", Hibernate.LONG)
    			.addScalar("status", Hibernate.STRING).addScalar("postedDate", Hibernate.STRING)
    			.addScalar("tehsilId", Hibernate.LONG).addScalar("tehsilName", Hibernate.STRING)
    			.addScalar("lebId", Hibernate.LONG).addScalar("lebName", Hibernate.STRING)
    			.addScalar("pancId", Hibernate.LONG).addScalar("pancName", Hibernate.STRING)
    			.addScalar("wardId", Hibernate.LONG).addScalar("wardName", Hibernate.STRING);
    	
    	query.setParameterList("complaintIds", complaintIds);
    	
    	return query.list();
	}
	public List<Object[]> getLatestComplaintResponsesForComplaintIds(List<Long> complaintIds){
		Query query = getSession().createSQLQuery("select model.Complaint_id as complaintId," +
											" model.Complaint_description as comment," +
											" model.created_at as createdDate" +
											" from complaint_responses model" +
											" where model.Complaint_id in (:complaintIds)" +
											" order by model.complaint_responseID asc")
											.addScalar("complaintId", Hibernate.LONG)
											.addScalar("comment", Hibernate.STRING)
											.addScalar("createdDate", Hibernate.STRING);
		
		query.setParameterList("complaintIds", complaintIds);
		return query.list();
	}
	
	//Complaint Details Starts
	
	public List<Object[]> getAllStatusDetailsByComplaint(Long complaintId,String type)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.complaint_tracking_id as complaintTrackingId," +
					" model.complaint_progress_status_id as statusId," +
					" model.comment as comment," +
					" date(model.inserted_time) as insertedDate," +
					" model.name as name" +
				" from complaint_tracking model" +
				" where model.Complaint_id =:complaintId");
		
		if(type.equalsIgnoreCase("grievance")){
			sb.append(" and model.complaint_progress_status_id in (1,5)" );
		}
		else if(type.equalsIgnoreCase("insurance")){
			sb.append(" and model.complaint_progress_status_id in (1,11)" );
		}
		sb.append(" order by model.complaint_tracking_id asc");
		
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("complaintTrackingId", Hibernate.LONG)
				.addScalar("statusId", Hibernate.LONG)
				.addScalar("comment", Hibernate.STRING)
				.addScalar("insertedDate", Hibernate.STRING)
				.addScalar("name", Hibernate.STRING);
		
		/*Query query = getSession().createQuery("select " +
		    " distinct model.complaintTrackingId,model.complaintProgressStatusId, model.comment, date(model.insertedTime),model.cadre.firstName,model.cadre.lastName" +
		    " from ComplaintTracking model " + 
			" where model.complaintProgressStatusId in (1,5) and model.complaintId =:complaintId " +
			" order by model.complaintTrackingId asc ");*/
		query.setParameter("complaintId", complaintId);
		return query.list();
	}
	
	public List<Object[]> getInsuranceStatus()
	{
		Query query = getSession().createSQLQuery("select model.grievance_insurance_status_id as statusId," +
										" model.status as status," +
										" model.order_no as orderNo" +
										" from grievance_insurance_status model")
										.addScalar("statusId", Hibernate.LONG)
										.addScalar("status", Hibernate.STRING)
										.addScalar("orderNo", Hibernate.LONG);
		return query.list();
	}
	
	public List<String> getStatusBycomplaintIdForInsurance(Long complaintId){
		
		Query query = getSession().createSQLQuery("select gis.status as status" +
							" from complaint_master cm,grievance_insurance_status gis" +
							" where cm.grievance_insurance_status_id = gis.grievance_insurance_status_id" +
							" and (cm.delete_status !='0' or cm.delete_status is null) and cm.Subject !=''" +
							" and cm.Complaint_id = :complaintId ")
							.addScalar("status", Hibernate.STRING);
		
		query.setParameter("complaintId", complaintId);
		return query.list();
	}
	
	public List<Object[]> getComplaintScanCopies(Long complaintId){
		Query query = getSession().createSQLQuery(" select sc.scanned_copy_id as copyId," +
										" sc.scanned_copy_path as path," +
										" csc.inserted_time as insertedTime" +
									" from complaint_scanned_copy csc,scanned_copy sc,complaint_master cm " +
									" where csc.scanned_copy_id = sc.scanned_copy_id" +
										" and csc.complaint_id = cm.Complaint_id" +
										" and cm.Complaint_id =:complaintId" +
										" and sc.is_deleted = 'N'" +
										" and (cm.delete_status !='0' or cm.delete_status is null) and cm.Subject !=''" +
										" order by sc.scanned_copy_id desc ")
										.addScalar("copyId", Hibernate.LONG)
										.addScalar("path", Hibernate.STRING)
										.addScalar("insertedTime", Hibernate.STRING);
		query.setParameter("complaintId", complaintId);
		
		return query.list();
	}
	
	public List getScanCopyForComplaint(Long complaintId)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.scan_copy as scanCopy" +
					" from complaint_master model" +
					" where (model.delete_status !='0' or model.delete_status is null) and model.Subject !='' " +
					" and model.Complaint_id = :complaintId ");
		Query query = getSession().createSQLQuery(str.toString()).addScalar("scanCopy", Hibernate.STRING);
		query.setParameter("complaintId", complaintId);
		return query.list();	
	}
	
	public List<Object[]> getSubjectAndDescForComplaint(Long complaintId){
		Query query = getSession().createSQLQuery("select cm.Subject as subject,cm.description as description" +
										" from complaint_master cm" +
										" where cm.Complaint_id = :complaintId" +
										" and (cm.delete_status !='0' or cm.delete_status is null) and cm.Subject !=''")
										.addScalar("subject", Hibernate.STRING)
										.addScalar("description", Hibernate.STRING);
		
		query.setParameter("complaintId", complaintId);
		return query.list();
	}
	
	public List<Object[]> getRemarks(Long complaintId){
		Query query = getSession().createSQLQuery("select model.comment as comment,model.name as name,model.inserted_time as insertedTime" +
							" from complaint_tracking model,complaint_master cm" +
							" where model.Complaint_id = cm.Complaint_id" +
							" and model.Complaint_id = :complaintId and model.complaint_progress_status_id = 2 " +
							" and (cm.delete_status !='0' or cm.delete_status is null) and cm.Subject !='' order by model.inserted_time desc")
							.addScalar("comment", Hibernate.STRING).addScalar("name", Hibernate.STRING).addScalar("insertedTime", Hibernate.STRING);
		query.setParameter("complaintId", complaintId);
		return query.list();
	}
	
	public List<Object[]> getComplaintResponsesByComplaintId(Long complaintId)
	{
		Query query = getSession().createSQLQuery(" select model.Complaint_description as description,model2.first_name as firstName," +
				" model.created_at as created,model2.image as image,cm.Complaint_id as complaintId" +
				" from complaint_responses model,cadre model2,complaint_master cm " +
				" where model.Reply_UserID = model2.cadre_id" +
				" and model.Complaint_id = cm.Complaint_id" +
				" and model.Complaint_id = :complaintId " +
				" and (cm.delete_status !='0' or cm.delete_status is null) and cm.Subject !=''" +
				" order by model.complaint_responseID desc")
				.addScalar("description", Hibernate.STRING).addScalar("firstName", Hibernate.STRING)
				.addScalar("created", Hibernate.STRING).addScalar("image", Hibernate.STRING).addScalar("complaintId", Hibernate.LONG);
		
		query.setParameter("complaintId", complaintId);
		
		return query.list();
	}
	
	//Complaint Details Ends
	  public List<Object[]> getLocationWiseComplaintCntBasedOnUserAccessLevel(CadreInsuranceInputVO inputVO){
		
		 StringBuilder queryStr = new StringBuilder();
	     queryStr.append("select");
	     if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	       queryStr.append("  state.state_id as stateId,state.state_name as stateName,");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" district.district_id as districtId,district.district_name as districtName,");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" parliamentConstituency.constituency_id as parliamentConstituencyId,parliamentConstituency.name as parliamentConName,");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append(" constituency.constituency_id as assemblyId,constituency.name as constituencyName,");  
		 }
	  
	     queryStr.append(" CM.issue_type as issueType,GIS.grievance_insurance_status_id as grievanceInsuranceStatusId,COUNT(distinct CM.Complaint_id) as count,sum(CM.approved_amount) as approvedAmount ");
	     queryStr.append(" FROM complaint_master CM,grievance_insurance_status GIS ");
	     
	     if(inputVO.getEnrollmentYearId() != null && inputVO.getEnrollmentYearId().longValue() > 0){
	    	 queryStr.append(",tdp_cadre TC,tdp_cadre_enrollment_year EY ");
	     }
	     
	     if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	       queryStr.append(",state as state WHERE state.state_id = CM.state_id_cmp ");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(",district as district WHERE district.district_id = CM.district_id ");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(",constituency as parliamentConstituency WHERE parliamentConstituency.constituency_id = CM.parliament_id");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append(",constituency as constituency WHERE constituency.constituency_id = CM.assembly_id ");  
		 }
	   
	     queryStr.append(" AND CM.grievance_insurance_status_id = GIS.grievance_insurance_status_id AND"+
					     " CM.type_of_issue = 'Insurance'  ");
	     if(inputVO.getEnrollmentYearId() != null && inputVO.getEnrollmentYearId().longValue() > 0){
		   queryStr.append(" AND CM.membership_id = TC.membership_id AND "+
	                       " TC.tdp_cadre_id = EY.tdp_cadre_id  ");

	     }
		 queryStr.append(" AND CM.delete_status IS NULL AND "+
					     " (CM.`Subject` IS NOT NULL OR CM.`Subject` != '') ");
	
	     if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0){
			 queryStr.append(" and CM.state_id_cmp =:stateId ");
		 }
	     if(inputVO.getEnrollmentYearId() != null && inputVO.getEnrollmentYearId().longValue() > 0){
	    	 queryStr.append(" and EY.enrollment_year_id =:enrollmentYearId ");
	     }
	     if(inputVO.getFromDate() !=null && inputVO.getToDate() !=null){
			 queryStr.append(" and date(CM.Raised_Date) between :startDate and :endDate  ");
		 }
	     if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		 	//queryStr.append(" and state.state_id in (:userAccessLevelValues)");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		    queryStr.append(" and district.district_id in (:userAccessLevelValues)");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		    queryStr.append(" and parliamentConstituency.constituency_id in (:userAccessLevelValues)");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		 	 queryStr.append(" and constituency.constituency_id in (:userAccessLevelValues)");  
		 }
	     
	     if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	         queryStr.append("  group by state.state_id");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" group by district.district_id");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" group by parliamentConstituency.constituency_id");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append(" group by constituency.constituency_id");  
		 }
	     queryStr.append(",CM.issue_type,GIS.grievance_insurance_status_id");
         SQLQuery sqlQuery = getSession().createSQLQuery(queryStr.toString());
	     if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		     sqlQuery.addScalar("stateId",Hibernate.LONG); 
		     sqlQuery.addScalar("stateName",Hibernate.STRING);
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			 sqlQuery.addScalar("districtId",Hibernate.LONG);
			 sqlQuery.addScalar("districtName",Hibernate.STRING);
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 sqlQuery.addScalar("parliamentConstituencyId",Hibernate.LONG); 
			 sqlQuery.addScalar("parliamentConName",Hibernate.STRING);
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 sqlQuery.addScalar("assemblyId",Hibernate.LONG);
			 sqlQuery.addScalar("constituencyName",Hibernate.STRING);
		 }
	     sqlQuery.addScalar("issueType",Hibernate.STRING);
	     sqlQuery.addScalar("grievanceInsuranceStatusId",Hibernate.LONG);
	     sqlQuery.addScalar("count",Hibernate.LONG);
	     sqlQuery.addScalar("approvedAmount",Hibernate.LONG);
	     
	     if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0){
		   sqlQuery.setParameter("stateId", inputVO.getStateId());	
		 }
	     if(inputVO.getEnrollmentYearId() != null && inputVO.getEnrollmentYearId().longValue() > 0){
	    	 sqlQuery.setParameter("enrollmentYearId", inputVO.getEnrollmentYearId());
	     }
		 if(inputVO.getUserAccessLevelValues() != null && inputVO.getUserAccessLevelValues().size() > 0 && inputVO.getUserAccessLevelId().longValue() != IConstants.STATE_LEVEl_ACCESS_ID){
		    sqlQuery.setParameterList("userAccessLevelValues", inputVO.getUserAccessLevelValues());
		 }
		 if(inputVO.getFromDate() !=null && inputVO.getToDate() !=null){
			  sqlQuery.setDate("startDate", inputVO.getFromDate());
			  sqlQuery.setDate("endDate", inputVO.getToDate());
		 }
		 return sqlQuery.list();
	}
	 public List<String> getAllIssueType(Long stateId){
		  StringBuffer queryStr = new StringBuffer();
		  queryStr.append(" select distinct cm.issue_type as issueType  from complaint_master cm " +
		  		          " where type_of_issue = 'Insurance' AND"+ 
		                  " delete_status IS NULL AND "+
		                  " (`Subject` IS NOT NULL OR `Subject` != '')");
		 if(stateId != null && stateId.longValue() > 0){
		  queryStr.append(" AND state_id_cmp =:stateId");
		 }
		 SQLQuery sqlQuery = getSession().createSQLQuery(queryStr.toString());
		 sqlQuery.addScalar("issueType",Hibernate.STRING);
		 if(stateId != null && stateId.longValue() > 0){
			 sqlQuery.setParameter("stateId", stateId);	 
		 }
		 return sqlQuery.list();
	 }
	 public List<Object[]> getAllGrievanceInsuranceStatus(){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select GIS.grievance_insurance_status_id as grievanceInsuranceStatusId," +
		 		        " GIS.status as status from grievance_insurance_status GIS");
		  SQLQuery sqlQuery = getSession().createSQLQuery(queryStr.toString());
		  sqlQuery.addScalar("grievanceInsuranceStatusId",Hibernate.LONG);
		  sqlQuery.addScalar("status",Hibernate.STRING);
		  return sqlQuery.list();
	 }
	
	 public List<Object[]> getLocationWiseComplaintAndBenefitMemberCntBasedOnUserAccessLevel(CadreInsuranceInputVO inputVO,String resultType){
			
		 StringBuilder queryStr = new StringBuilder();
	     queryStr.append("select");
	     if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	          queryStr.append(" state.state_id as stateId,state.state_name as stateName,");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" district.district_id as districtId,district.district_name as districtName,");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" parliamentConstituency.constituency_id as parliamentConstituencyId,parliamentConstituency.name as parliamentConName,");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 if(inputVO.getActivityMemerId() != null && (inputVO.getActivityMemerId().longValue() == 4l || inputVO.getActivityMemerId().longValue() == 5l)){
			  queryStr.append(" district.district_id as districtId,district.district_name as districtName, ");  
			 }else{
			  queryStr.append(" constituency.constituency_id as assemblyId,constituency.name as constituencyName,");	 
			 }
		 }
	     queryStr.append(" CM.issue_type as issueType,GIS.grievance_insurance_status_id as grievanceInsuranceStatusId,COUNT(distinct CM.Complaint_id) as count ");
	     if(resultType.equalsIgnoreCase("complaintCnt")){
	    	queryStr.append(",sum(CM.approved_amount) as approvedAmount"); 
	     }
	      queryStr.append(" FROM complaint_master CM,grievance_insurance_status GIS ");
	      if(inputVO.getEnrollmentYearId() != null && inputVO.getEnrollmentYearId().longValue() > 0){
	    	 queryStr.append(",tdp_cadre TC,tdp_cadre_enrollment_year EY ");
	      }
	     
	     if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	       queryStr.append(",state as state WHERE state.state_id = CM.state_id_cmp ");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(",district as district WHERE district.district_id = CM.district_id ");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(",constituency as parliamentConstituency WHERE parliamentConstituency.constituency_id = CM.parliament_id");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 if(inputVO.getActivityMemerId() != null && (inputVO.getActivityMemerId().longValue() == 4l || inputVO.getActivityMemerId().longValue() == 5l)){
				  queryStr.append(",constituency as constituency,district as district WHERE constituency.constituency_id = CM.assembly_id and district.district_id=constituency.district_id "); 
			 }else{
				 queryStr.append(",constituency as constituency WHERE constituency.constituency_id = CM.assembly_id ");	 
			 }
		 }
		     queryStr.append(" AND CM.grievance_insurance_status_id = GIS.grievance_insurance_status_id AND"+
				     " CM.type_of_issue = 'Insurance'  ");
			 if(inputVO.getEnrollmentYearId() != null && inputVO.getEnrollmentYearId().longValue() > 0){
			   queryStr.append(" AND CM.membership_id = TC.membership_id AND "+
			                   " TC.tdp_cadre_id = EY.tdp_cadre_id  ");
			
			 }
			 queryStr.append(" AND CM.delete_status IS NULL AND "+
						     " (CM.`Subject` IS NOT NULL OR CM.`Subject` != '') ");
	        /*   queryStr.append(" AND CM.grievance_insurance_status_id = GIS.grievance_insurance_status_id AND"+
					     " CM.type_of_issue = 'Insurance' AND "+
					     " CM.membership_id = TC.membership_id AND "+
					     " TC.tdp_cadre_id = EY.tdp_cadre_id AND "+
					     " CM.delete_status IS NULL AND "+
					     " (CM.`Subject` IS NOT NULL OR CM.`Subject` != '') ");*/
	
	     if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0){
			 queryStr.append(" and CM.state_id_cmp =:stateId ");
		 }
	     if(inputVO.getEnrollmentYearId() != null && inputVO.getEnrollmentYearId().longValue() > 0){
	    	 queryStr.append(" and EY.enrollment_year_id =:enrollmentYearId ");
	     }
	     if(inputVO.getFromDate() !=null && inputVO.getToDate() !=null){
			 queryStr.append(" and date(CM.Raised_Date) between :startDate and :endDate  ");
		 }
	     if(resultType.equalsIgnoreCase("BenefitCnt")){
	    	queryStr.append(" and CM.approved_amount > 0 "); 
	     }
	   
	     if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		 	//queryStr.append(" and state.state_id in (:userAccessLevelValues)");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		    queryStr.append(" and district.district_id in (:userAccessLevelValues)");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		    queryStr.append(" and parliamentConstituency.constituency_id in (:userAccessLevelValues)");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			queryStr.append(" and constituency.constituency_id in (:userAccessLevelValues)");  
		 }
	     
	     if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	         queryStr.append("  group by state.state_id");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" group by district.district_id");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" group by parliamentConstituency.constituency_id");  
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 if(inputVO.getActivityMemerId() != null && (inputVO.getActivityMemerId().longValue() == 4l || inputVO.getActivityMemerId().longValue() == 5l)){
				  queryStr.append(" group by district.district_id "); 
			 }else{
			      queryStr.append(" group by constituency.constituency_id");  
			 }
		 }
	     queryStr.append(",CM.issue_type,GIS.grievance_insurance_status_id");
         SQLQuery sqlQuery = getSession().createSQLQuery(queryStr.toString());
	     if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		        sqlQuery.addScalar("stateId",Hibernate.LONG); 
		        sqlQuery.addScalar("stateName",Hibernate.STRING);
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			    sqlQuery.addScalar("districtId",Hibernate.LONG);
			    sqlQuery.addScalar("districtName",Hibernate.STRING);
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			    sqlQuery.addScalar("parliamentConstituencyId",Hibernate.LONG);
			    sqlQuery.addScalar("parliamentConName",Hibernate.STRING);
		 }else if(inputVO.getUserAccessLevelId() != null && inputVO.getUserAccessLevelId().longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 if(inputVO.getActivityMemerId() != null && (inputVO.getActivityMemerId().longValue() == 4l || inputVO.getActivityMemerId().longValue() == 5l)){
				sqlQuery.addScalar("districtId",Hibernate.LONG);
				sqlQuery.addScalar("districtName",Hibernate.STRING);
			 }else{
				sqlQuery.addScalar("assemblyId",Hibernate.LONG);
				sqlQuery.addScalar("constituencyName",Hibernate.STRING);
			}
		 }
	     sqlQuery.addScalar("issueType",Hibernate.STRING);
	     sqlQuery.addScalar("grievanceInsuranceStatusId",Hibernate.LONG);
	     sqlQuery.addScalar("count",Hibernate.LONG);
	     if(resultType.equalsIgnoreCase("complaintCnt")){
	    	 sqlQuery.addScalar("approvedAmount",Hibernate.LONG); 
		 }
	     
	     if(inputVO.getStateId() != null && inputVO.getStateId().longValue() > 0){
		   sqlQuery.setParameter("stateId", inputVO.getStateId());	
		 }
	     if(inputVO.getEnrollmentYearId() != null && inputVO.getEnrollmentYearId().longValue() > 0){
	    	 sqlQuery.setParameter("enrollmentYearId", inputVO.getEnrollmentYearId());
	     }
		 if(inputVO.getUserAccessLevelValues() != null && inputVO.getUserAccessLevelValues().size() > 0 && inputVO.getUserAccessLevelId().longValue() != IConstants.STATE_LEVEl_ACCESS_ID ){
		    sqlQuery.setParameterList("userAccessLevelValues", inputVO.getUserAccessLevelValues());
		 }
		 if(inputVO.getFromDate() !=null && inputVO.getToDate() !=null){
			  sqlQuery.setDate("startDate", inputVO.getFromDate());
			  sqlQuery.setDate("endDate", inputVO.getToDate());
		 }
		 return sqlQuery.list();
	}
	 public List<Object[]> getDistrictWiseThenCategoryWiseInsuranceMemberCount(Long levelId, Set<Long> locationValuesSet, Long userTypeId, Long stateId, Long cadreEnrollmentYearId, Long locationId, List<Long> statusIdList, String category, Date fromDate, Date toDate, String type,String filter){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append("select");
		 if(levelId.longValue() == 2L){
			 queryStr.append(" DIST.district_id as locId, " +
	 				 		 " DIST.district_name as locName "); 
		 }else if(levelId.longValue() == 3L){
			 queryStr.append(" DIST.district_id as locId, " +
	 				 		 " DIST.district_name as locName "); 
		 }else if(levelId.longValue() == 4L){
			 queryStr.append(" CM.assembly_id as locId, " +
				 		 	 " CON.name as locName "); 
		 }else{
			 if(userTypeId != null && userTypeId.longValue() == 3L){
				 queryStr.append(" DIST.district_id as locId, " +
		 				 		 " DIST.district_name as locName "); 
			 }else{
				 queryStr.append(" CM.assembly_id as locId, " +
 				 		 		 " CON.name as locName "); 
			 }
		 }
		 
		 if(type.equalsIgnoreCase("status")){
			 queryStr.append(" ,GIS.grievance_insurance_status_id as issueType,COUNT(distinct CM.Complaint_id) as count, SUM(CM.approved_amount) as amount");
		 }else{
			 queryStr.append(" ,CM.issue_type as issueType ,COUNT(distinct CM.Complaint_id) as count, SUM(CM.approved_amount) as amount");
		 }
		 
		 
		 queryStr.append(" FROM complaint_master CM, grievance_insurance_status GIS, district DIST ");
		 
		 if(levelId.longValue() == 5L || levelId.longValue() == 4L){
			 queryStr.append(" ,constituency CON ");
		 }
		 
		 if(cadreEnrollmentYearId != null && cadreEnrollmentYearId.longValue() > 0){
			 queryStr.append(" ,tdp_cadre TC,tdp_cadre_enrollment_year TCEY "); 
		 }
		 
		
		 if(levelId != null && levelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			 queryStr.append(" WHERE DIST.district_id = CM.district_id ");  
		 }else if(levelId != null && levelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			 queryStr.append(" WHERE DIST.district_id = CM.district_id ");  
		 }else if(levelId != null && levelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 queryStr.append(" WHERE CM.district_id = DIST.district_id AND CON.constituency_id = CM.parliament_id  ");  
		 }else if(levelId != null && levelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 queryStr.append(" WHERE CM.district_id = DIST.district_id AND CON.constituency_id = CM.assembly_id ");  
		 }
		 if(filter.equalsIgnoreCase("filter")){
			queryStr.append(" AND CM.approved_amount > 0 "); 
		 }
		 if(stateId != null && stateId.longValue() > 0L){
			 queryStr.append(" AND state_id_cmp =:stateId ");  
		 }else{
			 queryStr.append(" AND state_id_cmp in (1,2) ");
		 }
		 
		 queryStr.append(" AND CM.grievance_insurance_status_id = GIS.grievance_insurance_status_id "+
				 		 " AND CM.type_of_issue = 'Insurance'  " );
		 if(cadreEnrollmentYearId != null && cadreEnrollmentYearId.longValue() > 0){
			 queryStr.append(" AND CM.membership_id = TC.membership_id  "+
				 		 	 " AND TC.tdp_cadre_id = TCEY.tdp_cadre_id  " +
				 		 	 " AND TCEY.enrollment_year_id = :cadreEnrollmentYearId ");
		 }
		 queryStr.append(" AND CM.delete_status IS NULL  "+
				 		 " AND (CM.`Subject` IS NOT NULL OR CM.`Subject` != '') ");
		
		 if(statusIdList != null && statusIdList.size() > 0){
			 queryStr.append(" AND GIS.grievance_insurance_status_id in (:statusIdList) ");
		 }
		 if(category != null && !category.trim().isEmpty() && category.trim().length() > 0){
			 queryStr.append(" AND CM.issue_type like '%"+category+"%' ");
		 }
		 if(fromDate != null && toDate != null){
			 queryStr.append(" AND date(CM.Raised_Date) between :fromDate and :toDate  ");
		 }
		 
		 
		 if(levelId != null && levelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			 if(locationId != null && locationId.longValue() > 0L){
				 queryStr.append(" and CM.district_id =:locationId ");  
			 }else{
				 queryStr.append(" and CM.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+")");  
			 }
		 }else if(levelId != null && levelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			 if(locationId != null && locationId.longValue() > 0L){
				 queryStr.append(" and CM.district_id =:locationId "); 
			 }else{
				 queryStr.append(" and CM.district_id in (:locationValuesSet)");
			 }
		 }else if(levelId != null && levelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 if(locationId != null && locationId.longValue() > 0L){
				 queryStr.append(" and CM.parliament_id in (:locationValuesSet) and CM.assembly_id = :locationId "); 
			 }else{
				 queryStr.append(" and CM.parliament_id in (:locationValuesSet)");
			 }
		 }else if(levelId != null && levelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 if(userTypeId != null && userTypeId.longValue() == 3L){
				 if(locationId != null && locationId.longValue() > 0L){
					 queryStr.append(" and CM.district_id =:locationId and CM.assembly_id in (:locationValuesSet) ");  
				 }else{
					 queryStr.append(" and CM.assembly_id in (:locationValuesSet) ");  
				 }
			 }else{
				 if(locationId != null && locationId.longValue() > 0L){
					 queryStr.append(" and CM.assembly_id =:locationId ");  
				 }else{
					 queryStr.append(" and CM.assembly_id in (:locationValuesSet)");  
				 }
			 }
		 }
		 
		 if(levelId != null && levelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			 if(type.equalsIgnoreCase("status")){
				 queryStr.append("  group by DIST.district_id, GIS.grievance_insurance_status_id");
			 }else{
				 queryStr.append("  group by DIST.district_id, CM.issue_type");
			 }
	           
		 }else if(levelId != null && levelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			 if(type.equalsIgnoreCase("status")){
				 queryStr.append("  group by DIST.district_id, GIS.grievance_insurance_status_id");
			 }else{
				 queryStr.append(" group by DIST.district_id, CM.issue_type");  
			 }
		     
		 }else if(levelId != null && levelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 if(type.equalsIgnoreCase("status")){
				 queryStr.append("  group by CM.assembly_id, GIS.grievance_insurance_status_id");
			 }else{
				 queryStr.append(" group by CM.assembly_id, CM.issue_type");  
			 }
		     
		 }else if(levelId != null && levelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 if(userTypeId != null && userTypeId.longValue() == 3L){
				 if(type.equalsIgnoreCase("status")){
					 queryStr.append("  group by DIST.district_id, GIS.grievance_insurance_status_id");
				 }else{
					 queryStr.append(" group by DIST.district_id, CM.issue_type");   
				 }
				  
			 }else{
				 if(type.equalsIgnoreCase("status")){
					 queryStr.append("  group by CM.assembly_id, GIS.grievance_insurance_status_id");
				 }else{
					 queryStr.append(" group by CM.assembly_id, CM.issue_type");    
				 }
				 
			 }
		 }
		 
		
		 SQLQuery query = getSession().createSQLQuery(queryStr.toString());
		 
		 query.addScalar("locId", Hibernate.LONG);
		 query.addScalar("locName",Hibernate.STRING);
		 if(type.equalsIgnoreCase("status")){
			 query.addScalar("issueType",Hibernate.LONG);
		 }else{
			 query.addScalar("issueType",Hibernate.STRING);
		 }
		 query.addScalar("count",Hibernate.LONG);
		 query.addScalar("amount",Hibernate.LONG);
		 
		 if(stateId != null && stateId.longValue() > 0L){
			 query.setParameter("stateId", stateId);
		 }
		 
		 if(cadreEnrollmentYearId != null && cadreEnrollmentYearId.longValue() > 0){
			 query.setParameter("cadreEnrollmentYearId", cadreEnrollmentYearId);
		 }
		 
		 if(statusIdList != null && statusIdList.size() > 0){
			 query.setParameterList("statusIdList", statusIdList);
		 }
		 
		 if(fromDate != null && toDate != null){
			 query.setDate("fromDate",fromDate);
			 query.setDate("toDate",toDate);
		 }
		 
		 if(levelId != null && levelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			 if(locationId != null && locationId.longValue() > 0L){
				 query.setParameter("locationId", locationId);  
			 }
		 }else if(levelId != null && levelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			 if(locationId != null && locationId.longValue() > 0L){
				 query.setParameter("locationId", locationId); 
			 }else{
				 query.setParameterList("locationValuesSet", locationValuesSet);  
			 }
		 }else if(levelId != null && levelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 if(locationId != null && locationId.longValue() > 0L){
				 query.setParameter("locationId", locationId);
				 query.setParameterList("locationValuesSet", locationValuesSet);
			 }else{
				 query.setParameterList("locationValuesSet", locationValuesSet);
			 }
		 }else if(levelId != null && levelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 if(userTypeId != null && userTypeId.longValue() == 3L){
				 if(locationId != null && locationId.longValue() > 0L){
					 query.setParameter("locationId", locationId);
					 query.setParameterList("locationValuesSet", locationValuesSet);
				 }else{
					 query.setParameterList("locationValuesSet", locationValuesSet);
				 }
			 }else{
				 if(locationId != null && locationId.longValue() > 0L){
					 query.setParameter("locationId", locationId); 
				 }else{
					 query.setParameterList("locationValuesSet", locationValuesSet);
				 }
			 }
		 }
		 return query.list();
	 }
	 public List<Object[]> getConstituencyWiseThenCategoryWiseInsuranceMemberCount(Long levelId, Set<Long> locationValuesSet, Long userTypeId, Long stateId, Long cadreEnrollmentYearId, Long locationId, List<Long> statusIdList, String category, Date fromDate, Date toDate, String type,String filter){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append("select");
		
		 queryStr.append(" CM.assembly_id as locId, " +
				 		 	 " CON.name as locName "); 
		
		 
		 if(type.equalsIgnoreCase("status")){
			 queryStr.append(" ,GIS.grievance_insurance_status_id as issueType ,COUNT(distinct CM.Complaint_id) as count, SUM(CM.approved_amount) as amount");
		 }else{
			 queryStr.append(" ,CM.issue_type as issueType ,COUNT(distinct CM.Complaint_id) as count, SUM(CM.approved_amount) as amount");
		 }
		 
		 queryStr.append(" FROM complaint_master CM, grievance_insurance_status GIS, district DIST ");
		
		 queryStr.append(" ,constituency CON ");
			 
		 if(cadreEnrollmentYearId != null && cadreEnrollmentYearId.longValue() > 0){
			 queryStr.append(" ,tdp_cadre TC,tdp_cadre_enrollment_year TCEY "); 
		 }
		
		
		 queryStr.append(" WHERE CM.district_id = DIST.district_id AND CON.constituency_id = CM.assembly_id "); 
		 if(filter.equalsIgnoreCase("filter")){
				queryStr.append(" AND CM.approved_amount > 0 "); 
		 }
		 
		 if(stateId != null && stateId.longValue() > 0L){
			 queryStr.append(" AND state_id_cmp =:stateId ");  
		 }
		 
		 queryStr.append(" AND CM.grievance_insurance_status_id = GIS.grievance_insurance_status_id "+
				 		 " AND CM.type_of_issue = 'Insurance'  " );
		 if(cadreEnrollmentYearId != null && cadreEnrollmentYearId.longValue() > 0){
			 queryStr.append(" AND CM.membership_id = TC.membership_id  "+
				 		 	 " AND TC.tdp_cadre_id = TCEY.tdp_cadre_id  " +
				 		 	 " AND TCEY.enrollment_year_id = :cadreEnrollmentYearId ");
		 }
		 queryStr.append(" AND CM.delete_status IS NULL  "+
				 		 " AND (CM.`Subject` IS NOT NULL OR CM.`Subject` != '') ");
		
		 if(statusIdList != null && statusIdList.size() > 0){
			 queryStr.append(" AND GIS.grievance_insurance_status_id in (:statusIdList) ");
		 }
		 if(category != null && !category.trim().isEmpty() && category.trim().length() > 0){
			 queryStr.append(" AND CM.issue_type like '%"+category+"%' ");
		 }
		 
		 if(fromDate != null && toDate != null){
			 queryStr.append(" AND date(CM.Raised_Date) between :fromDate and :toDate  ");
		 }
		 
		 
		 if(levelId != null && levelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			 if(locationId != null && locationId.longValue() > 0L){
				 queryStr.append(" and CM.assembly_id =:locationId ");  
			 }else{
				 queryStr.append(" and CM.district_id in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+")");  
			 }
		 }else if(levelId != null && levelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			 if(locationId != null && locationId.longValue() > 0L){
				 queryStr.append(" and CM.assembly_id =:locationId "); 
			 }else{
				 queryStr.append(" and CM.district_id in (:locationValuesSet)");
			 }
		 }else if(levelId != null && levelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 if(userTypeId != null && userTypeId.longValue() == 3L){
				 if(locationId != null && locationId.longValue() > 0L){
					 queryStr.append(" and CM.assembly_id =:locationId ");  
				 }else{
					 queryStr.append(" and CM.assembly_id in (:locationValuesSet) ");  
				 }
			 }
		 }
		 
		 if(type.equalsIgnoreCase("status")){
			 queryStr.append("  group by CM.assembly_id, GIS.grievance_insurance_status_id");
		 }else{
			 queryStr.append(" group by CM.assembly_id, CM.issue_type ");
		 }
		 
		
		 SQLQuery query = getSession().createSQLQuery(queryStr.toString());
				 query.addScalar("locId", Hibernate.LONG);
				 query.addScalar("locName",Hibernate.STRING);
				 if(type.equalsIgnoreCase("status")){
					 query.addScalar("issueType",Hibernate.LONG);
				 }else{
					 query.addScalar("issueType",Hibernate.STRING);
				 }
				 query.addScalar("count",Hibernate.LONG);
				 query.addScalar("amount",Hibernate.LONG);
		 
		 if(stateId != null && stateId.longValue() > 0L){
			 query.setParameter("stateId", stateId);
		 }
		 
		 if(cadreEnrollmentYearId != null && cadreEnrollmentYearId.longValue() > 0){
			 query.setParameter("cadreEnrollmentYearId", cadreEnrollmentYearId);
		 }
		 
		 if(statusIdList != null && statusIdList.size() > 0){
			 query.setParameterList("statusIdList", statusIdList);
		 }
		 
		 if(fromDate != null && toDate != null){
			 query.setDate("fromDate",fromDate);
			 query.setDate("toDate",toDate);
		 }
		 
		 if(levelId != null && levelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			 if(locationId != null && locationId.longValue() > 0L){
				 query.setParameter("locationId", locationId);
			 }
		 }else if(levelId != null && levelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			 if(locationId != null && locationId.longValue() > 0L){
				 query.setParameter("locationId", locationId);
			 }else{
				 query.setParameterList("locationValuesSet", locationValuesSet); 
			 }
		 }else if(levelId != null && levelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 if(userTypeId != null && userTypeId.longValue() == 3L){
				 if(locationId != null && locationId.longValue() > 0L){
					 query.setParameter("locationId", locationId);
				 }else{
					 query.setParameterList("locationValuesSet", locationValuesSet); 
				 }
			 }
		 }
		 
		 return query.list();
	 }
	 //swadhin
	 public List<Object[]> getLocationWiseThenCategoryWiseInsuranceMemberCountForTS(Long stateId, Long cadreEnrollmentYearId, Long locationId, List<Long> statusIdList, String category, Date fromDate, Date toDate, String type, String locationType,String filter){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append("select");
		 if(locationType.equalsIgnoreCase("constituency")){
			 queryStr.append(" CM.assembly_id as locId, " +
		 		 	 		 " CON.name as locName "); 
		 }else{
			 queryStr.append(" DIST.district_id as locId, " +
				 		 	 " DIST.district_name as locName "); 
		 }
		 
		
		 if(type.equalsIgnoreCase("status")){
			 queryStr.append(" ,GIS.grievance_insurance_status_id as issueType,COUNT(distinct CM.Complaint_id) as count, SUM(CM.approved_amount) as amount");
		 }else{
			 queryStr.append(" ,CM.issue_type as issueType ,COUNT(distinct CM.Complaint_id) as count, SUM(CM.approved_amount) as amount");
		 }
		 
		 
		 queryStr.append(" FROM complaint_master CM, grievance_insurance_status GIS, district DIST ");
		 
		 if(locationType.equalsIgnoreCase("constituency")){
			 queryStr.append(" ,constituency CON ");
		 }
		 
		 if(cadreEnrollmentYearId != null && cadreEnrollmentYearId.longValue() > 0){
			 queryStr.append(" ,tdp_cadre TC,tdp_cadre_enrollment_year TCEY "); 
		 }
		 
		
		 if(locationType.equalsIgnoreCase("constituency")){
			 queryStr.append(" WHERE CM.district_id = DIST.district_id AND CON.constituency_id = CM.assembly_id and CON.election_scope_id = 2 and CON.deform_date is null ");  
		 }else{
			 queryStr.append(" WHERE CM.district_id = DIST.district_id ");
		 }
		 
		 if(filter.equalsIgnoreCase("filter")){
				queryStr.append(" AND CM.approved_amount > 0 "); 
		 }
		 
		 if(stateId != null && stateId.longValue() > 0L){
			 queryStr.append(" AND state_id_cmp =:stateId ");  
		 }
		 
		 queryStr.append(" AND CM.grievance_insurance_status_id = GIS.grievance_insurance_status_id "+
				 		 " AND CM.type_of_issue = 'Insurance'  " );
		 if(cadreEnrollmentYearId != null && cadreEnrollmentYearId.longValue() > 0){
			 queryStr.append(" AND CM.membership_id = TC.membership_id  "+
				 		 	 " AND TC.tdp_cadre_id = TCEY.tdp_cadre_id  " +
				 		 	 " AND TCEY.enrollment_year_id = :cadreEnrollmentYearId ");
		 }
		 queryStr.append(" AND CM.delete_status IS NULL  "+
				 		 " AND (CM.`Subject` IS NOT NULL OR CM.`Subject` != '') ");
		
		 if(statusIdList != null && statusIdList.size() > 0){
			 queryStr.append(" AND GIS.grievance_insurance_status_id in (:statusIdList) ");
		 }
		 if(category != null && !category.trim().isEmpty() && category.trim().length() > 0){
			 queryStr.append(" AND CM.issue_type like '%"+category+"%' ");
		 }
		 if(fromDate != null && toDate != null){
			 queryStr.append(" AND date(CM.Raised_Date) between :fromDate and :toDate  ");
		 }
		 if(locationId != null && locationId.longValue() > 0L){
			 if(locationType.equalsIgnoreCase("constituency")){
				 queryStr.append(" and CM.assembly_id =:locationId ");
			 }else{
				 queryStr.append(" and CM.district_id =:locationId");
			 }
			 
		 }else{
			 queryStr.append(" and CM.district_id in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
		 }
		  
		 
		 
		 if(locationType.equalsIgnoreCase("constituency")){
			 if(type.equalsIgnoreCase("status")){
				 queryStr.append("  group by CM.assembly_id, GIS.grievance_insurance_status_id");
			 }else{
				 queryStr.append(" group by CM.assembly_id, CM.issue_type ");  
			 }
		 }else{
			 if(type.equalsIgnoreCase("status")){
				 queryStr.append("  group by DIST.district_id, GIS.grievance_insurance_status_id");
			 }else{
				 queryStr.append(" group by DIST.district_id, CM.issue_type");  
			 }
		 }
		 SQLQuery query = getSession().createSQLQuery(queryStr.toString());
		 
		 query.addScalar("locId", Hibernate.LONG);
		 query.addScalar("locName",Hibernate.STRING);
		 if(type.equalsIgnoreCase("status")){
			 query.addScalar("issueType",Hibernate.LONG);
		 }else{
			 query.addScalar("issueType",Hibernate.STRING);
		 }
		 query.addScalar("count",Hibernate.LONG);
		 query.addScalar("amount",Hibernate.LONG);
		 
		 if(stateId != null && stateId.longValue() > 0L){
			 query.setParameter("stateId", stateId);
		 }
		 
		 if(cadreEnrollmentYearId != null && cadreEnrollmentYearId.longValue() > 0){
			 query.setParameter("cadreEnrollmentYearId", cadreEnrollmentYearId);
		 }
		 
		 if(statusIdList != null && statusIdList.size() > 0){
			 query.setParameterList("statusIdList", statusIdList);
		 }
		 
		 if(fromDate != null && toDate != null){
			 query.setDate("fromDate",fromDate);
			 query.setDate("toDate",toDate);
		 }  
		 if(locationId != null && locationId.longValue() > 0L){
			 query.setParameter("locationId", locationId);
		 }
		 return query.list();
	 }

	
	public List<Object[]> getConstituencyWiseInsuranceStatusCounts(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year,Long yearId) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
		sb.append(" SELECT ");
		sbm.append(" FROM complaint_master CM,grievance_insurance_status GIS,district D,constituency C ,tdp_cadre_enrollment_year tcey,state S ");
		sbe.append("WHERE CM.grievance_insurance_status_id = GIS.grievance_insurance_status_id AND "
				+ " CM.district_id = D.district_id AND "
				+ " CM.assembly_id = C.constituency_id AND "
				+ " CM.type_of_issue = 'Insurance' AND " +
				  " CM.state_id_cmp = S.state_id AND "
				+ " CM.delete_status IS NULL AND "
				+ " (CM.Subject IS NOT NULL OR CM.Subject != '')  ");
		sbg.append(" GROUP BY ");
		if(locationTypeId!=null && locationTypeId==2l && locationValues!=null && locationValues.size() > 0){
			sb.append(" S.state_id as typeId,S.state_name as typeName,GIS.status as status,GIS.grievance_insurance_status_id as statusId,COUNT(CM.Complaint_id) as count");
			sbe.append(" AND CM.state_id_cmp in(:locationValues)");
			sbg.append(" S.state_id ");
		}else if(locationTypeId!=null && locationTypeId==3l && locationValues!=null && locationValues.size() > 0){
			sb.append(" D.district_id as typeId,D.district_name as typeName,GIS.status as status,GIS.grievance_insurance_status_id as statusId,COUNT(CM.Complaint_id) as count");
			sbe.append(" AND CM.district_id in(:locationValues)");
			sbg.append(" D.district_id ");
		}else if(locationTypeId!=null && locationTypeId==4l && locationValues!=null && locationValues.size() > 0){
			sb.append(" C.constituency_id as typeId,C.name as typeName,GIS.status as status,GIS.grievance_insurance_status_id as statusId,COUNT(CM.Complaint_id) as count ");
			sbe.append(" AND CM.assembly_id in(:locationValues)");
			sbg.append(" C.constituency_id");
		}else if(locationTypeId!=null && locationTypeId==10l && locationValues!=null && locationValues.size() > 0){
			sb.append(" C.constituency_id as typeId,C.name as typeName,GIS.status as status,GIS.grievance_insurance_status_id as statusId,COUNT(CM.Complaint_id) as count ");
			sbe.append(" AND CM.parliament_id in(:locationValues)");
			sbg.append(" C.constituency_id");
		}else if(locationTypeId!=null && locationTypeId==5l && locationValues!=null && locationValues.size() > 0){
			sb.append(" T.tehsil_id as typeId,T.tehsil_name as typeName,GIS.status as status,GIS.grievance_insurance_status_id as statusId,COUNT(CM.Complaint_id) as count ");
			sbm.append(" ,tehsil T " );
			sbe.append(" AND  CM.tehsil_id =  T.tehsil_id AND CM.tehsil_id in(:locationValues)");
			sbg.append(" T.tehsil_id ");
		}else if(locationTypeId!=null && locationTypeId==7l && locationValues!=null && locationValues.size() > 0){
			sb.append(" leb.local_election_body_id as typeId,leb.name as typeName,GIS.status as status,GIS.grievance_insurance_status_id as statusId,COUNT(CM.Complaint_id) as count ");
			sbm.append(" ,local_election_body leb " );
			sbe.append(" AND leb.local_election_body_id =CM.local_election_body_id  AND CM.local_election_body_id in(:locationValues) ");
			sbg.append(" leb.local_election_body_id ");
		}
		if(fromDate !=null && toDate !=null){
	   		sbe.append(" AND (date(CM.Raised_Date) between :startDate and  :endDate )");
	   	}
		if(yearId != null && yearId.longValue() > 0l){
			sbe.append(" AND tcey.enrollment_year_id = :yearId ");
		}
		sbe.append(" AND CM.user_id=tcey.tdp_cadre_id " );
		/*if(year != null && !year.trim().isEmpty()){
			sb.append(" and year(CM.Raised_Date) =:year ");   
 	    }*/
		sbg.append("  ,GIS.grievance_insurance_status_id; ");
		sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("typeId",Hibernate.LONG)
				.addScalar("typeName",Hibernate.STRING)
				.addScalar("status",Hibernate.STRING)
				.addScalar("statusId",Hibernate.LONG)
				.addScalar("count",Hibernate.LONG);
		if(locationTypeId!=null && locationTypeId.longValue()>0){
			query.setParameterList("locationValues",locationValues);
		}
		/*if(year !=null && !year.trim().isEmpty()){
 			query.setParameter("year", year);
		}*/
		if(fromDate !=null && toDate !=null){
   		query.setDate("startDate", fromDate);
   		query.setDate("endDate", toDate);
   	}
		if(yearId != null && yearId.longValue() > 0l){
			query.setParameter("yearId", yearId);
		}
		return query.list();
	}

	
	public List<Object[]> getGrivenceTrustStatusCounts(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year,Long yearId) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
		sb.append(" SELECT");
		sbm.append(" FROM complaint_master CM,district D,constituency C,tdp_cadre_enrollment_year tcey,state S ");
		sbe.append(" WHERE  CM.type_of_issue IN('Govt','Party','Welfare','Trust Education Support') and "
				+ " CM.delete_status IS NULL AND (CM.Subject IS NOT NULL OR CM.Subject != '') ");
		sbg.append(" GROUP BY ");
		if(locationTypeId!=null && locationTypeId==2l  && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append(" S.state_id as typeId,CM.Completed_Status as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.state_id_cmp in(:locationValues)");
			sbg.append(" S.state_id ");
		}else if(locationTypeId!=null && locationTypeId==3l  && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append(" D.district_id as typeId,CM.Completed_Status as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.district_id in(:locationValues)");
			sbg.append(" D.district_id ");
		}else if(locationTypeId!=null && locationTypeId==4l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " C.constituency_id as typeId,CM.Completed_Status as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count " );
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.assembly_id in(:locationValues)");
			sbg.append(" C.constituency_id ");
		}else if(locationTypeId!=null && locationTypeId==10l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " C.constituency_id as typeId,CM.Completed_Status as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count " );
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id  AND CM.parliament_id in(:locationValues)");
			sbg.append(" C.constituency_id ");
		}else if(locationTypeId!=null && locationTypeId==5l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " T.tehsil_id as typeId,CM.Completed_Status as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count " );
			sbm.append(" ,tehsil T ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.tehsil_id = T.tehsil_id  AND CM.tehsil_id in(:locationValues)");
			sbg.append(" T.tehsil_id ");
		}else if(locationTypeId!=null && locationTypeId==7l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " leb.local_election_body_id as typeId,CM.Completed_Status as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count " );
			sbm.append(" ,local_election_body leb ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.local_election_body_id = leb.local_election_body_id AND CM.local_election_body_id in(:locationValues)");
			sbg.append(" leb.local_election_body_id ");
		}
		if(fromDate !=null && toDate !=null){
	   		sbe.append(" AND (date(CM.Raised_Date) between :startDate and  :endDate ) ");
	   	}
		
		if(yearId != null && yearId.longValue() > 0l){
			sbe.append(" AND tcey.enrollment_year_id = :yearId ");
		}
		sbe.append(" AND CM.user_id=tcey.tdp_cadre_id " );
		/*if(year != null && !year.trim().isEmpty()){
			sbe.append(" and year(CM.Raised_Date) =:year ");   
 	    }*/
		sbg.append("  ,CM.type_of_issue,CM.Completed_Status; ");
		sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("typeId",Hibernate.LONG)
				.addScalar("status",Hibernate.STRING)
				.addScalar("typeOfIssue",Hibernate.STRING)
				.addScalar("count",Hibernate.LONG);
		if(locationTypeId!=null && locationTypeId.longValue()>0){
			query.setParameterList("locationValues", locationValues);
		}
		/*if(year !=null && !year.trim().isEmpty()){
 			query.setParameter("year", Integer.parseInt(year));
		}*/
		if(fromDate !=null && toDate !=null){
   		query.setDate("startDate", fromDate);
   		query.setDate("endDate", toDate);
   	}
		if(yearId != null && yearId.longValue() > 0l){
			query.setParameter("yearId", yearId);
		}
		return query.list();
	}

	@Override
	public List<String> getGrivenceStatus() {
		Query query = getSession().createSQLQuery("select distinct Completed_Status from complaint_master where Completed_Status is not null");
		return query.list();
	}
	
	@Override
	public List<Object[]> grievanceInsuranceStatusId() {
		Query query = getSession().createSQLQuery("select grievance_insurance_status_id, status from grievance_insurance_status ");
		return query.list();
	}
}
