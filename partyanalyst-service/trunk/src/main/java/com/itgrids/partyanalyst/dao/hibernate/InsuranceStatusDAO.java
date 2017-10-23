package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

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
		sb.append(" SELECT distinct");
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
	
	public List<Object[]> getConstituencyWiseInsuranceWiseIssueTypeCounts(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year,Long yearId) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
		sb.append(" SELECT ");
		sbm.append(" FROM complaint_master CM,district D,constituency C ,tdp_cadre_enrollment_year tcey,state S ");
		sbe.append(" WHERE "
				+ " CM.district_id = D.district_id AND "
				+ " CM.assembly_id = C.constituency_id AND "
				+ " CM.type_of_issue = 'Insurance' AND " +
				  " CM.state_id_cmp = S.state_id AND "
				+ " CM.delete_status IS NULL AND "
				+ " (CM.Subject IS NOT NULL OR CM.Subject != '')  ");
		sbg.append(" GROUP BY ");
		if(locationTypeId!=null && locationTypeId==2l && locationValues!=null && locationValues.size() > 0){
			sb.append(" S.state_id as typeId,S.state_name as typeName,CM.issue_type as issueType,CM.Subject as subject,COUNT(CM.Complaint_id) as count");
			sbe.append(" AND CM.state_id_cmp in(:locationValues)");
			sbg.append(" S.state_id ");
		}else if(locationTypeId!=null && locationTypeId==3l && locationValues!=null && locationValues.size() > 0){
			sb.append(" D.district_id as typeId,D.district_name as typeName,CM.issue_type  as issueType,CM.Subject as subject,COUNT(CM.Complaint_id) as count");
			sbe.append(" AND CM.district_id in(:locationValues)");
			sbg.append(" D.district_id ");
		}else if(locationTypeId!=null && locationTypeId==4l && locationValues!=null && locationValues.size() > 0){
			sb.append(" C.constituency_id as typeId,C.name as typeName,CM.issue_type as issueType,CM.Subject as subject,COUNT(CM.Complaint_id) as count ");
			sbe.append(" AND CM.assembly_id in(:locationValues)");
			sbg.append(" C.constituency_id");
		}else if(locationTypeId!=null && locationTypeId==10l && locationValues!=null && locationValues.size() > 0){
			sb.append(" C.constituency_id as typeId,C.name as typeName,CM.issue_type as issueType,CM.Subject as subject,COUNT(CM.Complaint_id) as count ");
			sbe.append(" AND CM.parliament_id in(:locationValues)");
			sbg.append(" C.constituency_id");
		}else if(locationTypeId!=null && locationTypeId==5l && locationValues!=null && locationValues.size() > 0){
			sb.append(" T.tehsil_id as typeId,T.tehsil_name as typeName,CM.issue_type as issueType,CM.Subject as subject,COUNT(CM.Complaint_id) as count ");
			sbm.append(" ,tehsil T " );
			sbe.append(" AND  CM.tehsil_id =  T.tehsil_id AND CM.tehsil_id in(:locationValues)");
			sbg.append(" T.tehsil_id ");
		}else if(locationTypeId!=null && locationTypeId==7l && locationValues!=null && locationValues.size() > 0){
			sb.append(" leb.local_election_body_id as typeId,leb.name as typeName,CM.issue_type as issueType,CM.Subject as subject,COUNT(CM.Complaint_id) as count ");
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
		sbg.append("  ,CM.issue_type,CM.Subject; ");
		sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("typeId",Hibernate.LONG)
				.addScalar("typeName",Hibernate.STRING)
				.addScalar("issueType",Hibernate.STRING)
				.addScalar("subject",Hibernate.STRING)
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
	
	public List<Object[]> getGrivenceIssueTypeCounts(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year,Long yearId) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
		sb.append(" SELECT distinct");
		sbm.append(" FROM complaint_master CM,district D,constituency C,tdp_cadre_enrollment_year tcey,state S ");
		sbe.append(" WHERE  CM.type_of_issue IN('Govt','Party','Welfare') and "
				+ " CM.delete_status IS NULL AND (CM.Subject IS NOT NULL OR CM.Subject != '') and CM.issue_type is not null ");
		sbg.append(" GROUP BY ");
		if(locationTypeId!=null && locationTypeId==2l  && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append(" S.state_id as typeId,CM.issue_type as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.state_id_cmp in(:locationValues)");
			sbg.append(" S.state_id ");
		}else if(locationTypeId!=null && locationTypeId==3l  && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append(" D.district_id as typeId,CM.issue_type as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.district_id in(:locationValues)");
			sbg.append(" D.district_id ");
		}else if(locationTypeId!=null && locationTypeId==4l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " C.constituency_id as typeId,CM.issue_type as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count " );
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.assembly_id in(:locationValues)");
			sbg.append(" C.constituency_id ");
		}else if(locationTypeId!=null && locationTypeId==10l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " C.constituency_id as typeId,CM.issue_type as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count " );
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id  AND CM.parliament_id in(:locationValues)");
			sbg.append(" C.constituency_id ");
		}else if(locationTypeId!=null && locationTypeId==5l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " T.tehsil_id as typeId,CM.issue_type as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count " );
			sbm.append(" ,tehsil T ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.tehsil_id = T.tehsil_id  AND CM.tehsil_id in(:locationValues)");
			sbg.append(" T.tehsil_id ");
		}else if(locationTypeId!=null && locationTypeId==7l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " leb.local_election_body_id as typeId,CM.issue_type as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count " );
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
		sbg.append("  ,CM.type_of_issue,CM.issue_type; ");
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
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllTypeOfIssues() {
		Query query = getSession().createSQLQuery("select distinct issue_type,type_of_issue from complaint_master where issue_type is not null");
		return query.list();
	}
	
	public List<Object[]> getLevelValuesByLevel(Long locationTypeId,List<Long> locationValues) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		
		sb.append(" SELECT distinct");
		sbm.append(" FROM complaint_master CM,district D,constituency C,state S,tehsil T,local_election_body leb");
		sbm.append(" WHERE CM.district_id between 11 and 23 ");
		if(locationTypeId!=null && locationTypeId==2l  && locationTypeId.longValue()>0){
			sb.append(" D.district_id as typeId,D.district_name as name,'' as id,'' as name1");
			sbm.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.state_id_cmp in(:locationValues)");
		}else if(locationTypeId!=null && locationTypeId==3l  && locationTypeId.longValue()>0){
			sb.append("  C.constituency_id as typeId,C.name as name,'' as id,'' as name1");
			sbm.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.district_id in(:locationValues)");
		}else if(locationTypeId!=null && locationTypeId==4l && locationTypeId.longValue()>0){
			sb.append( "  T.tehsil_id as typeId,T.tehsil_name as name,leb.local_election_body_id as id,leb.name as name1");
			sbm.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.tehsil_id = T.tehsil_id AND CM.local_election_body_id = leb.local_election_body_id AND CM.assembly_id in(:locationValues)");
		}else if(locationTypeId!=null && locationTypeId==10l && locationTypeId.longValue()>0){
			sb.append( " C.constituency_id as typeId,C.name as name,'' as id,'' as name1" );
			sbm.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.parliament_id in(:locationValues)");
		}/*else if(locationTypeId!=null && locationTypeId==5l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " T.tehsil_id as typeId,CM.Completed_Status as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count " );
			sbm.append(" ,tehsil T ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.tehsil_id = T.tehsil_id  AND CM.tehsil_id in(:locationValues)");
			sbg.append(" T.tehsil_id ");
		}else if(locationTypeId!=null && locationTypeId==7l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " leb.local_election_body_id as typeId,CM.Completed_Status as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count " );
			sbm.append(" ,local_election_body leb ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.local_election_body_id = leb.local_election_body_id AND CM.local_election_body_id in(:locationValues)");
			sbg.append(" leb.local_election_body_id ");
		}*/
		sb.append(sbm.toString());
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("typeId",Hibernate.LONG)
				.addScalar("name",Hibernate.STRING)
				.addScalar("id",Hibernate.LONG)
				.addScalar("name1",Hibernate.STRING);
		
		if(locationTypeId!=null && locationTypeId.longValue()>0){
			query.setParameterList("locationValues", locationValues);
		}
		return query.list();
	}
	
	public List<Object[]> getGrievanceTypeCounts(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year,Long yearId) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
		sb.append(" SELECT distinct");
		sbm.append(" FROM complaint_master CM,district D,constituency C,tdp_cadre_enrollment_year tcey,state S ");
		sbe.append(" WHERE  CM.type_of_issue IN('Govt','Party','Welfare') and "
				+ " CM.delete_status IS NULL AND (CM.Subject IS NOT NULL OR CM.Subject != '') and CM.issue_type is not null" +
				" AND CM.district_id between 11 and 23 ");
		sbg.append(" GROUP BY ");
		if(locationTypeId!=null && locationTypeId==2l  && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append("  D.district_id as typeId,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.state_id_cmp in(:locationValues)");
			sbg.append(" D.district_id ");
		}else if(locationTypeId!=null && locationTypeId==3l  && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append(" C.constituency_id as typeId,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.district_id in(:locationValues)");
			sbg.append(" C.constituency_id ");
		}else if(locationTypeId!=null && locationTypeId==4l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " T.tehsil_id as typeId,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count,leb.local_election_body_id as id  " );
			sbm.append(" ,tehsil T,local_election_body leb ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id  AND CM.tehsil_id = T.tehsil_id AND CM.local_election_body_id = leb.local_election_body_id AND CM.assembly_id in(:locationValues)");
			sbg.append(" T.tehsil_id,leb.local_election_body_id ");
		}else if(locationTypeId!=null && locationTypeId==10l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " C.constituency_id as typeId,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id " );
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id  AND CM.parliament_id in(:locationValues)");
			sbg.append(" C.constituency_id ");
		}else if(locationTypeId!=null && locationTypeId==5l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " p.panchayat_id as typeId,CM.issue_type as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id " );
			sbm.append(" ,panchayat p ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.panchayat_id = p.panchayat_id  AND CM.tehsil_id in(:locationValues)");
			sbg.append(" p.panchayat_id ");
		}else if(locationTypeId!=null && locationTypeId==7l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " ward.constituency_id as typeId,CM.issue_type as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id " );
			sbm.append(" ,constituency ward ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.ward = ward.constituency_id AND CM.local_election_body_id in(:locationValues)");
			sbg.append(" ward.constituency_id ");
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
		sbg.append("  ,CM.type_of_issue");
		sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("typeId",Hibernate.LONG)
				.addScalar("typeOfIssue",Hibernate.STRING)
				.addScalar("count",Hibernate.LONG)
				.addScalar("id",Hibernate.LONG);
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
	
	public List<Object[]> getInsuranceTypeCounts(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year,Long yearId) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
		sb.append(" SELECT distinct");
		sbm.append(" FROM complaint_master CM,district D,constituency C,tdp_cadre_enrollment_year tcey,state S ");
		sbe.append(" WHERE  CM.type_of_issue IN('Insurance') and "
				+ " CM.delete_status IS NULL AND (CM.Subject IS NOT NULL OR CM.Subject != '') and CM.issue_type is not null" +
				" AND CM.district_id between 11 and 23 ");
		sbg.append(" GROUP BY ");
		if(locationTypeId!=null && locationTypeId==2l  && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append("  D.district_id as typeId,CM.issue_type as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.state_id_cmp in(:locationValues)");
			sbg.append(" D.district_id ");
		}else if(locationTypeId!=null && locationTypeId==3l  && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append(" C.constituency_id as typeId,CM.issue_type as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.district_id in(:locationValues)");
			sbg.append(" C.constituency_id ");
		}else if(locationTypeId!=null && locationTypeId==4l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " T.tehsil_id as typeId,CM.issue_type as typeOfIssue,COUNT(CM.Complaint_id) as count,leb.local_election_body_id as id  " );
			sbm.append(" ,tehsil T,local_election_body leb ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id  AND CM.tehsil_id = T.tehsil_id AND CM.local_election_body_id = leb.local_election_body_id AND CM.assembly_id in(:locationValues)");
			sbg.append(" T.tehsil_id,leb.local_election_body_id ");
		}else if(locationTypeId!=null && locationTypeId==10l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " C.constituency_id as typeId,CM.issue_type as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id " );
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id  AND CM.parliament_id in(:locationValues)");
			sbg.append(" C.constituency_id ");
		}else if(locationTypeId!=null && locationTypeId==5l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " p.panchayat_id as typeId,CM.issue_type as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id " );
			sbm.append(" ,panchayat p ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.panchayat_id = p.panchayat_id  AND CM.tehsil_id in(:locationValues)");
			sbg.append(" p.panchayat_id ");
		}else if(locationTypeId!=null && locationTypeId==7l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " ward.constituency_id as typeId,CM.issue_type as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id " );
			sbm.append(" ,constituency ward ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.ward = ward.constituency_id AND CM.local_election_body_id in(:locationValues)");
			sbg.append(" ward.constituency_id ");
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
		sbg.append("  ,CM.issue_type");
		sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("typeId",Hibernate.LONG)
				.addScalar("typeOfIssue",Hibernate.STRING)
				.addScalar("count",Hibernate.LONG)
				.addScalar("id",Hibernate.LONG);
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
	
	public List<Object[]> getNTRTrustTypeCounts(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year,Long yearId) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
		sb.append(" SELECT distinct");
		sbm.append(" FROM complaint_master CM,district D,constituency C,tdp_cadre_enrollment_year tcey,state S ");
		sbe.append(" WHERE  CM.support_purpose IN('Fee Concession','Seat') and "
				+ " CM.delete_status IS NULL AND (CM.Subject IS NOT NULL OR CM.Subject != '') and CM.issue_type is not null" +
				" AND CM.district_id between 11 and 23 ");
		sbg.append(" GROUP BY ");
		if(locationTypeId!=null && locationTypeId==2l  && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append("  D.district_id as typeId,CM.support_purpose as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.state_id_cmp in(:locationValues)");
			sbg.append(" D.district_id ");
		}else if(locationTypeId!=null && locationTypeId==3l  && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append(" C.constituency_id as typeId,CM.support_purpose as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.district_id in(:locationValues)");
			sbg.append(" C.constituency_id ");
		}else if(locationTypeId!=null && locationTypeId==4l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " T.tehsil_id as typeId,CM.support_purpose as typeOfIssue,COUNT(CM.Complaint_id) as count,leb.local_election_body_id as id  " );
			sbm.append(" ,tehsil T,local_election_body leb ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id  AND CM.tehsil_id = T.tehsil_id AND CM.local_election_body_id = leb.local_election_body_id AND CM.assembly_id in(:locationValues)");
			sbg.append(" T.tehsil_id,leb.local_election_body_id ");
		}else if(locationTypeId!=null && locationTypeId==10l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " C.constituency_id as typeId,CM.support_purpose as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id " );
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id  AND CM.parliament_id in(:locationValues)");
			sbg.append(" C.constituency_id ");
		}else if(locationTypeId!=null && locationTypeId==5l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " p.panchayat_id as typeId,CM.issue_type as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id " );
			sbm.append(" ,panchayat p ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.panchayat_id = p.panchayat_id  AND CM.tehsil_id in(:locationValues)");
			sbg.append(" p.panchayat_id ");
		}else if(locationTypeId!=null && locationTypeId==7l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " ward.constituency_id as typeId,CM.issue_type as status,CM.type_of_issue as typeOfIssue,COUNT(CM.Complaint_id) as count,'' as id " );
			sbm.append(" ,constituency ward ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.ward = ward.constituency_id AND CM.local_election_body_id in(:locationValues)");
			sbg.append(" ward.constituency_id ");
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
		sbg.append("  ,CM.support_purpose");
		sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("typeId",Hibernate.LONG)
				.addScalar("typeOfIssue",Hibernate.STRING)
				.addScalar("count",Hibernate.LONG)
				.addScalar("id",Hibernate.LONG);
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
	
	public List<Object[]> getLocationWiseGrivenceTrustIssueTypesCounts(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year,Long yearId) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		StringBuilder sbe = new StringBuilder();
		StringBuilder sbg = new StringBuilder();
		sb.append(" SELECT");
		sbm.append(" FROM complaint_master CM,district D,constituency C,tdp_cadre_enrollment_year tcey,state S ");
		sbe.append(" WHERE  CM.type_of_issue in('Trust Education Support' ) and  (CM.district_id between 11 and 23) and "
				+ " CM.delete_status IS NULL AND (CM.Subject IS NOT NULL OR CM.Subject != '') ");
		sbg.append(" GROUP BY ");
		if(locationTypeId!=null && locationTypeId==2l  && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append(" S.state_id as typeId,CM.support_purpose as supportPurpose,CM.support_for as supportFor,COUNT(CM.Complaint_id) as count ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id ");
			//sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.state_id_cmp in(:locationValues)");
			sbg.append(" S.state_id, ");
		}else if(locationTypeId!=null && locationTypeId==3l  && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append(" D.district_id as typeId,CM.support_purpose as supportPurpose,CM.support_for as supportFor,COUNT(CM.Complaint_id) as count ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.district_id in(:locationValues)");
			sbg.append(" D.district_id, ");
		}else if(locationTypeId!=null && locationTypeId==4l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " C.constituency_id as typeId,CM.support_purpose as supportPurpose,CM.support_for as supportFor,COUNT(CM.Complaint_id) as count " );
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.assembly_id in(:locationValues)");
			sbg.append(" C.constituency_id, ");
		}else if(locationTypeId!=null && locationTypeId==10l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " C.constituency_id as typeId,CM.support_purpose as supportPurpose,CM.support_for as supportFor,COUNT(CM.Complaint_id) as count " );
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id  AND CM.parliament_id in(:locationValues)");
			sbg.append(" C.constituency_id,");
		}else if(locationTypeId!=null && locationTypeId==5l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " T.tehsil_id as typeId,CM.support_purpose as supportPurpose,CM.support_for as supportFor,COUNT(CM.Complaint_id) as count " );
			sbm.append(" ,tehsil T ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.tehsil_id = T.tehsil_id  AND CM.tehsil_id in(:locationValues)");
			sbg.append(" T.tehsil_id, ");
		}else if(locationTypeId!=null && locationTypeId==7l && locationTypeId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append( " leb.local_election_body_id as typeId,CM.support_purpose as supportPurpose,CM.support_for as supportFor,COUNT(CM.Complaint_id) as count " );
			sbm.append(" ,local_election_body leb ");
			sbe.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.local_election_body_id = leb.local_election_body_id AND CM.local_election_body_id in(:locationValues)");
			sbg.append(" leb.local_election_body_id, ");
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
		sbg.append("  CM.support_purpose,CM.support_for; ");
		sb.append(sbm.toString()).append(sbe.toString()).append(sbg.toString());
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("typeId",Hibernate.LONG)
				.addScalar("supportFor",Hibernate.STRING)
				.addScalar("supportPurpose",Hibernate.STRING)
				.addScalar("count",Hibernate.LONG);
		if(locationTypeId!=null && locationTypeId.longValue()>0 && locationTypeId.longValue() !=2L){
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
	
	public List<Long> getComplaintIdsByGrievanceType(Long locationId,List<Long> locationValues,Date fromDate,Date toDate,String type,String grievanceType,Long yearId){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT distinct CM.Complaint_id as complaintId");
		sb.append(" FROM complaint_master CM,district D,constituency C,tdp_cadre_enrollment_year tcey,state S,tehsil T,local_election_body leb ");
		sb.append(" WHERE CM.delete_status IS NULL AND (CM.Subject IS NOT NULL OR CM.Subject != '') and CM.issue_type is not null" +
				" AND CM.district_id between 11 and 23 ");
		
		/*sb.append("select distinct cm.Complaint_id as complaintId" +
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
					" and (cm.Subject IS NOT NULL OR cm.Subject != '')");*/
		
		/*if(stateId != null && stateId > 0l)
			sb.append("and cm.state_id_cmp IN (:stateId)")*/;		//else
		//	sb.append(" and cm.state_id_cmp IN (1,2)");
		
		/*if(locationId != null && locationValues != null && locationValues.size() > 0 && locationId.longValue() == 3L){
    		sb.append(" and cm.district_id IN (:locationValues) ");
		}else if(locationId != null && locationValues != null && locationValues.size() > 0 && locationId.longValue() == 4L){
    		sb.append(" and cm.assembly_id IN (:locationValues) ");
		}else if(locationId != null && locationValues != null && locationValues.size() > 0 && locationId.longValue() == 5L){
    		sb.append(" and cm.tehsil_id IN (:locationValues) ");
		}else if(locationId != null && locationValues != null && locationValues.size() > 0 && locationId.longValue() == 7L){
    		sb.append(" and cm.local_election_body_id IN (:locationValues) ");
		}
		else */
		if(locationId!=null && locationId==3l  && locationId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.district_id in(:locationValues)");
		}else if(locationId!=null && locationId==4l && locationId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id   AND CM.assembly_id in(:locationValues)");
		}else if(locationId!=null && locationId==5l && locationId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.tehsil_id = T.tehsil_id  AND CM.tehsil_id in(:locationValues)");
		}else if(locationId!=null && locationId==7l && locationId.longValue()>0 && locationValues!=null && locationValues.size() > 0){
			sb.append(" AND CM.state_id_cmp = S.state_id AND CM.district_id = D.district_id AND CM.assembly_id = C.constituency_id AND CM.local_election_body_id = leb.local_election_body_id AND CM.local_election_body_id in(:locationValues)");
		}
		
    	if(fromDate !=null && toDate !=null){
    		sb.append(" AND (date(CM.Raised_Date) between :fromDate and  :toDate ) ");
	   	}
    	if(yearId != null && yearId.longValue() > 0l){
    		sb.append(" AND tcey.enrollment_year_id = :yearId ");
		}
    	if(type != null && type.trim().equalsIgnoreCase("Grievance")){
    		sb.append(" and CM.type_of_issue = :grievanceType");
    	}else if(type != null && type.trim().equalsIgnoreCase("Insurance")){
    		sb.append(" and CM.issue_type = :grievanceType");
    	}else if(type != null && type.trim().equalsIgnoreCase("Trust")){
    		sb.append(" and CM.support_purpose = :grievanceType");
    	}
    	
    	/*if(cadreYearId != null && cadreYearId.longValue() == 2l){
    		sb.append(" and year(cm.date_of_incident) between '2012' and '2014'"); 
    	}else if(cadreYearId != null && cadreYearId.longValue() == 3l){
    		sb.append(" and year(cm.date_of_incident) between '2014' and '2016'"); 
    	}else if(cadreYearId != null && cadreYearId.longValue() == 4l){
    		sb.append(" and year(cm.date_of_incident) between '2016' and '2018'"); 
    	}*/
    	
    	Query query = getSession().createSQLQuery(sb.toString()).addScalar("complaintId", Hibernate.LONG);
    	
    	
		if(locationId != null && locationValues != null && locationValues.size() > 0  && locationId.longValue() != 2L)
			query.setParameterList("locationValues", locationValues);
		if(fromDate != null && toDate != null){
    		query.setDate("fromDate", fromDate);
    		query.setDate("toDate", toDate);
    	}
    	if(type != null)
    		query.setParameter("grievanceType", grievanceType);
    	if(yearId != null && yearId.longValue() > 0l){
			query.setParameter("yearId", yearId);
		}
    	
    	return query.list();
	}
	
	public List<Object[]> getComplaintIdsByGrievanceType(List<Long> complaintIds,String type){
		StringBuilder sb = new StringBuilder();
		StringBuilder sbm = new StringBuilder();
		sb.append(" SELECT distinct CM.Complaint_id as complaintId," +
				" CM.name as name,CM.mobile_no as mobileNO,CM.Location as districtName,CM.constituency as constName,CM.mandal_name as mandalName," +
				"CM.village_name as villName,CM.description as description,CM.Raised_Date as date,CM.Subject as subject");
		if(type != null && type.trim().equalsIgnoreCase("Grievance")){
			sb.append(",CM.Completed_Status as type");
		}else if(type != null && type.trim().equalsIgnoreCase("Insurance")){
			sb.append(",gis.status as type ");
		}else if(type != null && type.trim().equalsIgnoreCase("Trust")){
			sb.append(",CM.Completed_Status as type ");
		}
		sb.append(" FROM complaint_master CM");
		sbm.append(" WHERE CM.delete_status IS NULL AND (CM.Subject IS NOT NULL OR CM.Subject != '') AND CM.issue_type is not null");
		if(type != null && type.trim().equalsIgnoreCase("Insurance")){
			sb.append(",grievance_insurance_status gis");
			sbm.append(" AND CM.grievance_insurance_status_id = gis.grievance_insurance_status_id");
		}
			sbm.append(" AND CM.Complaint_id in (:complaintIds)");
		sb.append(sbm.toString());
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("complaintId", Hibernate.LONG)
				.addScalar("name", Hibernate.STRING)
				.addScalar("mobileNO", Hibernate.STRING)
				.addScalar("districtName", Hibernate.STRING)
				.addScalar("constName", Hibernate.STRING)
				.addScalar("mandalName", Hibernate.STRING)
				.addScalar("villName", Hibernate.STRING)
				.addScalar("description", Hibernate.STRING)
				.addScalar("date", Hibernate.STRING)
				.addScalar("subject", Hibernate.STRING)
				.addScalar("type", Hibernate.STRING);
    	
		query.setParameterList("complaintIds", complaintIds);
		
    	return query.list();
	}
	
	 public List<Object[]> getAllTrustIssueTypes(){
		  StringBuilder sb = new StringBuilder();
		  sb.append("SELECT DISTINCT support_purpose ,support_for from complaint_master order by support_for;");
		  Query query = getSession().createSQLQuery(sb.toString());
		  return query.list(); 
	  }
	 public List<Object[]> getAllInsuranceGrievanceTpes(){
	     StringBuilder sb = new StringBuilder();
		  sb.append(" SELECT DISTINCT CM.issue_type as issueType ,CM.Subject as subject  from complaint_master CM where CM.type_of_issue='Insurance';");
		  Query query = getSession().createSQLQuery(sb.toString())
				  .addScalar("issueType", Hibernate.STRING)
				  .addScalar("subject", Hibernate.STRING);
		  return query.list(); 
	 }
	
	 public List<Object[]> getGrievanceTypeOfIssueIssueTypeAndStatusComplaintCnt(Long locationTypeId,List<Long> locationValues,Long stateId,Date fromDate,Date toDate) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select cm.type_of_issue as typeOfIssue," +
						" cm.issue_type as issueType," +
						" cm.Completed_Status as completeStatus," +
						" COUNT(cm.Complaint_id) as complaintCount " +
						" from complaint_master cm " +
						" where " +
						" cm.type_of_issue IN ('Party','Govt','Welfare') and (cm.delete_status IS NULL OR cm.delete_status != 0) " +
						" AND cm.state_id_cmp IN (:stateId) AND cm.Subject IS NOT NULL AND cm.Subject != ''" +
						" AND cm.issue_type is not null " +
						" AND cm.issue_type !='' ");
		

		if (locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0) {

			if (locationTypeId == IConstants.DISTRICT_SCOPE_ID) {
				queryStr.append(" AND cm.district_id in(:locationValues) ");
			} else if (locationTypeId == IConstants.CONSTITUENCY_SCOPE_ID) {
				queryStr.append(" AND cm.assembly_id in(:locationValues) ");
			} else if (locationTypeId == IConstants.PARLIAMENT_CONSTITUENCY_SCOPE_ID) {
				queryStr.append(" AND cm.parliament_id in(:locationValues) ");
			} else if (locationTypeId == IConstants.MUNICIPAL_CORP_GMC_SCOPE_ID) {
				queryStr.append(" AND cm.local_election_body_id in(:locationValues) ");
			} else if (locationTypeId == IConstants.TEHSIL_SCOPE_ID) {
				queryStr.append(" AND cm.tehsil_id in(:locationValues) ");
			} else if (locationTypeId == IConstants.VILLAGE_SCOPE_ID) {
				queryStr.append(" AND cm.panchayat_id in(:locationValues) ");
			}
		}
		
		if(fromDate !=null && toDate !=null){
			queryStr.append(" AND date(cm.Raised_Date) between :fromDate and  :toDate  ");
	   	}
		queryStr.append(" group by cm.type_of_issue,cm.issue_type,cm.Completed_Status");
		queryStr.append(" order by cm.type_of_issue,cm.issue_type,cm.Completed_Status ");

		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("typeOfIssue", Hibernate.STRING)
				.addScalar("issueType", Hibernate.STRING)
				.addScalar("completeStatus", Hibernate.STRING)
				.addScalar("complaintCount", Hibernate.LONG);
		
        query.setParameter("stateId", stateId);
		if (locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId.longValue() !=2l && locationValues != null && locationValues.size() > 0) {
			query.setParameterList("locationValues", locationValues);
		}
		if(fromDate != null && toDate != null){
    		query.setDate("fromDate", fromDate);
    		query.setDate("toDate", toDate);
    	}
		return query.list();
	}
	 public List<Object[]> getGrievanceDepartmentWiseComplaintCnt(Long locationTypeId,List<Long> locationValues,Long stateId,Date fromDate,Date toDate) {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select cm.deptname as deptName," +
							" cm.Completed_Status as completeStatus," +
							" COUNT(cm.Complaint_id) as complaintCount " +
							" from complaint_master cm " +
							" where " +
							" cm.type_of_issue IN ('Party','Govt','Welfare') and (cm.delete_status IS NULL OR cm.delete_status != 0) " +
							" AND cm.state_id_cmp IN (:stateId) AND cm.Subject IS NOT NULL AND cm.Subject != '' " +
							" AND cm.deptname is not null AND cm.deptname !='' ");
			

			if (locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0) {

				if (locationTypeId == IConstants.DISTRICT_SCOPE_ID) {
					queryStr.append(" AND cm.district_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.CONSTITUENCY_SCOPE_ID) {
					queryStr.append(" AND cm.assembly_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.PARLIAMENT_CONSTITUENCY_SCOPE_ID) {
					queryStr.append(" AND cm.parliament_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.MUNICIPAL_CORP_GMC_SCOPE_ID) {
					queryStr.append(" AND cm.local_election_body_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.TEHSIL_SCOPE_ID) {
					queryStr.append(" AND cm.tehsil_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.VILLAGE_SCOPE_ID) {
					queryStr.append(" AND cm.panchayat_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.WARD_SCOPE_ID) {
					queryStr.append(" AND cm.ward in(:locationValues) ");
				}
			}
			if(fromDate !=null && toDate !=null){
				queryStr.append(" AND date(cm.Raised_Date) between :fromDate and  :toDate  ");
		   	}
			queryStr.append(" group by cm.deptname,cm.Completed_Status ");
			queryStr.append(" order by cm.deptname,cm.Completed_Status ");

			Query query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("deptName", Hibernate.STRING)
					.addScalar("completeStatus", Hibernate.STRING)
					.addScalar("complaintCount", Hibernate.LONG);
			
	        query.setParameter("stateId", stateId);
			if (locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId.longValue() !=2l && locationValues != null && locationValues.size() > 0) {
				query.setParameterList("locationValues", locationValues);
			}
			if(fromDate != null && toDate != null){
	    		query.setDate("fromDate", fromDate);
	    		query.setDate("toDate", toDate);
	    	}
			return query.list();
		}
	 public List<Object[]> getGrievanceAmountByIssueType(Long locationTypeId,List<Long> locationValues,Long stateId,Date fromDate,Date toDate) {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select cm.type_of_issue as typeOfIssue," +
							" cm.issue_type as issueType," +
							" cm.Completed_Status as completeStatus," +
							" COUNT(cm.Complaint_id) as complaintCount," +
							" SUM(cm.expected_amount) as exceptedAmount," +
							" SUM(cm.approved_amount) as approvedAmount " +
							" from complaint_master cm " +
							" where " +
							" cm.type_of_issue IN ('Party','Govt','Welfare') and (cm.delete_status IS NULL OR cm.delete_status != 0) " +
							" AND cm.state_id_cmp IN (:stateId) AND cm.Subject IS NOT NULL AND cm.Subject != '' " +
							" AND cm.approved_amount >=0 AND cm.Completed_Status IN ('approved','completed') ");
			

			if (locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0) {

				if (locationTypeId == IConstants.DISTRICT_SCOPE_ID) {
					queryStr.append(" AND cm.district_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.CONSTITUENCY_SCOPE_ID) {
					queryStr.append(" AND cm.assembly_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.PARLIAMENT_CONSTITUENCY_SCOPE_ID) {
					queryStr.append(" AND cm.parliament_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.MUNICIPAL_CORP_GMC_SCOPE_ID) {
					queryStr.append(" AND cm.local_election_body_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.TEHSIL_SCOPE_ID) {
					queryStr.append(" AND cm.tehsil_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.VILLAGE_SCOPE_ID) {
					queryStr.append(" AND cm.panchayat_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.WARD_SCOPE_ID) {
					queryStr.append(" AND cm.ward in(:locationValues) ");
				}
			}
			if(fromDate !=null && toDate !=null){
				queryStr.append(" AND date(cm.Raised_Date) between :fromDate and  :toDate  ");
		   	}
			queryStr.append(" group by cm.type_of_issue,cm.issue_type,cm.Completed_Status");
			queryStr.append(" order by cm.type_of_issue,cm.issue_type,cm.Completed_Status ");

			Query query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("typeOfIssue", Hibernate.STRING)
					.addScalar("issueType", Hibernate.STRING)
					.addScalar("completeStatus", Hibernate.STRING)
					.addScalar("complaintCount", Hibernate.LONG)
					.addScalar("exceptedAmount", Hibernate.LONG)
					.addScalar("approvedAmount", Hibernate.LONG);
			
	        query.setParameter("stateId", stateId);
			if (locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId.longValue() !=2l && locationValues != null && locationValues.size() > 0) {
				query.setParameterList("locationValues", locationValues);
			}
			if(fromDate != null && toDate != null){
	    		query.setDate("fromDate", fromDate);
	    		query.setDate("toDate", toDate);
	    	}
			return query.list();
		}

	public List<Object[]> getLocationWiseGrievanceComplaintCnt(Long filterScopeId, List<Long> filterScopeValues, Long stateId,String groupType,Date fromDate,Date toDate) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select");

	    StringBuilder locationQueryStr = prepareSelectQueryBasedLocationLevel(groupType);
	     if (locationQueryStr.length() > 0) {
	    	 queryStr.append(locationQueryStr);
	     }

		queryStr.append(" cm.type_of_issue as typeOfIssue,COUNT(cm.Complaint_id) as complaintCount ");

		queryStr.append(" FROM complaint_master cm ");

		StringBuilder dynamicTableQuery = gettingDynamicTableBasedOnLocationLevel(groupType);
		
		 if (dynamicTableQuery.length() > 0) {
			 queryStr.append(dynamicTableQuery);
		 }
		
		queryStr.append(" where cm.type_of_issue IN ('Party','Govt','Welfare') and (cm.delete_status IS NULL OR cm.delete_status != 0) AND "
						+ " cm.state_id_cmp IN (:stateId) AND cm.Subject IS NOT NULL AND cm.Subject != '' ");

		StringBuilder dynamicTablJoinQuery = gettingDynamicTableJoinedBasedOnLocationLevel(groupType);
		
		if (dynamicTablJoinQuery.length() > 0) {
			queryStr.append(dynamicTablJoinQuery);
		}
		
		StringBuilder filterQueryStr = prepareFilterQueryBasedLocationLevel(filterScopeId, filterScopeValues);
		 if (filterQueryStr.length() > 0) {
			 queryStr.append(filterQueryStr);
			 
		 }

		
		if(fromDate !=null && toDate !=null){
			queryStr.append(" AND date(cm.Raised_Date) between :fromDate and  :toDate  ");
	   	}
		
		StringBuilder groupQuery = prepareGroupQueryBasedLocationLevel(groupType);
		
		 if (queryStr.length()  > 0) {
			 queryStr.append(groupQuery);
			 queryStr.append(",cm.type_of_issue");
		 }
		

		Session session = getSession();
		SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
		if (groupType != null && groupType.equalsIgnoreCase("District")) {
			sqlQuery.addScalar("districtId", Hibernate.LONG);
			sqlQuery.addScalar("districtName", Hibernate.STRING);
		} else if (groupType != null && groupType.equalsIgnoreCase("Constituency")) {
			sqlQuery.addScalar("assemblyId", Hibernate.LONG);
			sqlQuery.addScalar("assemblyName", Hibernate.STRING);
		} else if (groupType != null && groupType.equalsIgnoreCase("Mandal")) {
			sqlQuery.addScalar("tehsilId", Hibernate.LONG);
			sqlQuery.addScalar("tehsilName", Hibernate.STRING);
		} else if (groupType != null && groupType.equalsIgnoreCase("Village")) {
			sqlQuery.addScalar("panchayatId", Hibernate.LONG);
			sqlQuery.addScalar("panchayatName", Hibernate.STRING);
		} else if (groupType != null && groupType.equalsIgnoreCase("parliament")) {
			sqlQuery.addScalar("parliamentId", Hibernate.LONG);
			sqlQuery.addScalar("parliamentName", Hibernate.STRING);
		} else if (groupType != null && groupType.equalsIgnoreCase("TownDivision")) {
			sqlQuery.addScalar("localElectionBoydId", Hibernate.LONG);
			sqlQuery.addScalar("localElectionBoydName", Hibernate.STRING);
		} else if (groupType != null && groupType.equalsIgnoreCase("ward")) {
			sqlQuery.addScalar("wardId", Hibernate.LONG);
			sqlQuery.addScalar("wardName", Hibernate.STRING);
		}
		sqlQuery.addScalar("typeOfIssue", Hibernate.STRING);
		sqlQuery.addScalar("complaintCount", Hibernate.LONG);

		sqlQuery.setParameter("stateId", stateId);
		if (filterScopeId != null && filterScopeId.longValue() > 0l && filterScopeId.longValue() !=2l && filterScopeValues != null && filterScopeValues.size() > 0) {
			sqlQuery.setParameterList("filterScopeValues", filterScopeValues);
		}
		if(fromDate != null && toDate != null){
			sqlQuery.setDate("fromDate", fromDate);
			sqlQuery.setDate("toDate", toDate);
    	}
		return sqlQuery.list();
   }
	 public List<Object[]> getInsuranceIssueTypeAndStatusWiseComplaintCnt(Long locationTypeId,List<Long> locationValues,Long stateId,Date fromDate,Date toDate) {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
							" cm.issue_type as issueType," +
							" GIS.grievance_insurance_status_id as grievanceInsuranceStatusId," +
							" GIS.status as status," +
							" COUNT(cm.Complaint_id) as complaintCount " +
							" from complaint_master cm,grievance_insurance_status GIS " +
							" where " +
							" cm.grievance_insurance_status_id = GIS.grievance_insurance_status_id " +
							" AND cm.type_of_issue IN ('Insurance') AND (cm.delete_status IS NULL OR cm.delete_status != 0) " +
							" AND cm.state_id_cmp IN (:stateId) AND cm.Subject IS NOT NULL AND cm.Subject != ''" +
							" AND cm.issue_type is not null AND cm.issue_type in ('Death','Hospitalization') " +
							" AND cm.issue_type !='' ");
			

			if (locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0) {

				if (locationTypeId == IConstants.DISTRICT_SCOPE_ID) {
					queryStr.append(" AND cm.district_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.CONSTITUENCY_SCOPE_ID) {
					queryStr.append(" AND cm.assembly_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.PARLIAMENT_CONSTITUENCY_SCOPE_ID) {
					queryStr.append(" AND cm.parliament_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.MUNICIPAL_CORP_GMC_SCOPE_ID) {
					queryStr.append(" AND cm.local_election_body_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.TEHSIL_SCOPE_ID) {
					queryStr.append(" AND cm.tehsil_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.VILLAGE_SCOPE_ID) {
					queryStr.append(" AND cm.panchayat_id in(:locationValues) ");
				}
			}
			
			if(fromDate !=null && toDate !=null){
				queryStr.append(" AND date(cm.Raised_Date) between :fromDate and  :toDate  ");
		   	}
			queryStr.append(" group by cm.issue_type,GIS.grievance_insurance_status_id ");
			queryStr.append(" order by GIS.grievance_insurance_status_id asc ");

			Query query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("issueType", Hibernate.STRING)
					.addScalar("grievanceInsuranceStatusId", Hibernate.LONG)
					.addScalar("status", Hibernate.STRING)
					.addScalar("complaintCount", Hibernate.LONG);
			
	        query.setParameter("stateId", stateId);
			if (locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId.longValue() !=2l && locationValues != null && locationValues.size() > 0) {
				query.setParameterList("locationValues", locationValues);
			}
			if(fromDate != null && toDate != null){
	    		query.setDate("fromDate", fromDate);
	    		query.setDate("toDate", toDate);
	    	}
			return query.list();
		}
	 public List<Object[]> getLocationWiseInsuranceIssueTypeComplaintCnt(Long filterScopeId, List<Long> filterScopeValues, Long stateId,String groupType,Date fromDate,Date toDate) {

			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select");

			StringBuilder locationQueryStr = prepareSelectQueryBasedLocationLevel(groupType);
			if (locationQueryStr.length() > 0) {
				queryStr.append(locationQueryStr);
			}

			queryStr.append(" cm.issue_type as issueType," +
							" GIS.grievance_insurance_status_id as grievanceInsuranceStatusId," +
							" GIS.status as status," +
							" COUNT(cm.Complaint_id) as complaintCount ");

			queryStr.append(" FROM complaint_master cm,grievance_insurance_status GIS ");

			StringBuilder dynamicTableQuery = gettingDynamicTableBasedOnLocationLevel(groupType);

			if (dynamicTableQuery.length() > 0) {
				queryStr.append(dynamicTableQuery);
			}

			queryStr.append(" where cm.grievance_insurance_status_id = GIS.grievance_insurance_status_id AND " +
							" cm.type_of_issue IN ('Insurance') and (cm.delete_status IS NULL OR cm.delete_status != 0) AND "
							+ " cm.state_id_cmp IN (:stateId) AND cm.Subject IS NOT NULL AND cm.Subject != '' " 
							+ " AND cm.issue_type is not null AND cm.issue_type in ('Death','Hospitalization') ");

			StringBuilder dynamicTablJoinQuery = gettingDynamicTableJoinedBasedOnLocationLevel(groupType);
			
			if (dynamicTablJoinQuery.length() > 0) {
				queryStr.append(dynamicTablJoinQuery);
			}
			
			StringBuilder filterQueryStr = prepareFilterQueryBasedLocationLevel(filterScopeId, filterScopeValues);
			if (filterQueryStr.length() > 0) {
				queryStr.append(filterQueryStr);
			}

			if (fromDate != null && toDate != null) {
				queryStr.append(" AND date(cm.Raised_Date) between :fromDate and  :toDate  ");
			}

			StringBuilder groupQuery = prepareGroupQueryBasedLocationLevel(groupType);

			if (queryStr.length() > 0) {
				queryStr.append(groupQuery);
				queryStr.append(",cm.issue_type,GIS.grievance_insurance_status_id");
			}

			Session session = getSession();
			SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
			if (groupType != null && groupType.equalsIgnoreCase("District")) {
				sqlQuery.addScalar("districtId", Hibernate.LONG);
				sqlQuery.addScalar("districtName", Hibernate.STRING);
			} else if (groupType != null && groupType.equalsIgnoreCase("Constituency")) {
				sqlQuery.addScalar("assemblyId", Hibernate.LONG);
				sqlQuery.addScalar("assemblyName", Hibernate.STRING);
			} else if (groupType != null && groupType.equalsIgnoreCase("Mandal")) {
				sqlQuery.addScalar("tehsilId", Hibernate.LONG);
				sqlQuery.addScalar("tehsilName", Hibernate.STRING);
			} else if (groupType != null && groupType.equalsIgnoreCase("Village")) {
				sqlQuery.addScalar("panchayatId", Hibernate.LONG);
				sqlQuery.addScalar("panchayatName", Hibernate.STRING);
			} else if (groupType != null && groupType.equalsIgnoreCase("parliament")) {
				sqlQuery.addScalar("parliamentId", Hibernate.LONG);
				sqlQuery.addScalar("parliamentName", Hibernate.STRING);
			} else if (groupType != null && groupType.equalsIgnoreCase("TownDivision")) {
				sqlQuery.addScalar("localElectionBoydId", Hibernate.LONG);
				sqlQuery.addScalar("localElectionBoydName", Hibernate.STRING);
			} else if (groupType != null && groupType.equalsIgnoreCase("ward")) {
				sqlQuery.addScalar("wardId", Hibernate.LONG);
				sqlQuery.addScalar("wardName", Hibernate.STRING);
			}
			sqlQuery.addScalar("issueType", Hibernate.STRING);
			sqlQuery.addScalar("grievanceInsuranceStatusId", Hibernate.LONG);
			sqlQuery.addScalar("status", Hibernate.STRING);
			sqlQuery.addScalar("complaintCount", Hibernate.LONG);

			sqlQuery.setParameter("stateId", stateId);
			if (filterScopeId != null && filterScopeId.longValue() > 0l && filterScopeId.longValue() !=2l && filterScopeValues != null && filterScopeValues.size() > 0) {
				sqlQuery.setParameterList("filterScopeValues", filterScopeValues);
			}
			if(fromDate != null && toDate != null){
				sqlQuery.setDate("fromDate", fromDate);
				sqlQuery.setDate("toDate", toDate);
	    	}
			return sqlQuery.list();
	   }
	 public List<Object[]> getTrustEducationComplaintCnt(Long locationTypeId,List<Long> locationValues,Long stateId,Date fromDate,Date toDate) {
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
							" cm.support_purpose as supportPurpose," +
							" cm.support_for as supportFor," +
							" cm.Completed_Status as status," +
							" COUNT(cm.Complaint_id) as complaintCount " +
							" from complaint_master cm " +
							" where " +
							" cm.type_of_issue IN ('Trust Education Support') AND (cm.delete_status IS NULL OR cm.delete_status != 0) " +
							" AND cm.state_id_cmp IN (:stateId) AND cm.Subject IS NOT NULL AND cm.Subject != '' ");
			

			if (locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0) {

				if (locationTypeId == IConstants.DISTRICT_SCOPE_ID) {
					queryStr.append(" AND cm.district_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.CONSTITUENCY_SCOPE_ID) {
					queryStr.append(" AND cm.assembly_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.PARLIAMENT_CONSTITUENCY_SCOPE_ID) {
					queryStr.append(" AND cm.parliament_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.MUNICIPAL_CORP_GMC_SCOPE_ID) {
					queryStr.append(" AND cm.local_election_body_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.TEHSIL_SCOPE_ID) {
					queryStr.append(" AND cm.tehsil_id in(:locationValues) ");
				} else if (locationTypeId == IConstants.VILLAGE_SCOPE_ID) {
					queryStr.append(" AND cm.panchayat_id in(:locationValues) ");
				}
			}
			
			if(fromDate !=null && toDate !=null){
				queryStr.append(" AND date(cm.Raised_Date) between :fromDate and  :toDate  ");
		   	}
			queryStr.append(" group by cm.support_purpose,cm.support_for,cm.Completed_Status ");
			queryStr.append(" order by cm.support_purpose,cm.support_for,cm.Completed_Status ");

			Query query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("supportPurpose", Hibernate.STRING)
					.addScalar("supportFor", Hibernate.STRING)
					.addScalar("status", Hibernate.STRING)
					.addScalar("complaintCount", Hibernate.LONG);
			
	        query.setParameter("stateId", stateId);
			if (locationTypeId != null && locationTypeId.longValue() > 0l && locationTypeId.longValue() !=2l && locationValues != null && locationValues.size() > 0) {
				query.setParameterList("locationValues", locationValues);
			}
			if(fromDate != null && toDate != null){
	    		query.setDate("fromDate", fromDate);
	    		query.setDate("toDate", toDate);
	    	}
			return query.list();
		}
	 public List<Object[]> getLocationWiseTrustEducationComplaintCnt(Long filterScopeId, List<Long> filterScopeValues, Long stateId,String groupType,Date fromDate,Date toDate) {

			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select");

			StringBuilder locationQueryStr = prepareSelectQueryBasedLocationLevel(groupType);
			if (locationQueryStr.length() > 0) {
				queryStr.append(locationQueryStr);
			}

			queryStr.append(" cm.support_purpose as supportPurpose," +
							" cm.Completed_Status as status," +
							" COUNT(cm.Complaint_id) as complaintCount ");

			queryStr.append(" FROM complaint_master cm ");

			StringBuilder dynamicTableQuery = gettingDynamicTableBasedOnLocationLevel(groupType);

			if (dynamicTableQuery.length() > 0) {
				queryStr.append(dynamicTableQuery);
			}

			queryStr.append(" where  " +
							" cm.type_of_issue IN ('Trust Education Support') and (cm.delete_status IS NULL OR cm.delete_status != 0) AND "
						  + " cm.state_id_cmp IN (:stateId) AND cm.Subject IS NOT NULL AND cm.Subject != '' ");

			StringBuilder dynamicTablJoinQuery = gettingDynamicTableJoinedBasedOnLocationLevel(groupType);
			
			if (dynamicTablJoinQuery.length() > 0) {
				queryStr.append(dynamicTablJoinQuery);
			}
			
			StringBuilder filterQueryStr = prepareFilterQueryBasedLocationLevel(filterScopeId, filterScopeValues);
			if (filterQueryStr.length() > 0) {
				queryStr.append(filterQueryStr);
			}

			if (fromDate != null && toDate != null) {
				queryStr.append(" AND date(cm.Raised_Date) between :fromDate and  :toDate  ");
			}

			StringBuilder groupQuery = prepareGroupQueryBasedLocationLevel(groupType);

			if (queryStr.length() > 0) {
				queryStr.append(groupQuery);
				queryStr.append(",cm.support_purpose,cm.Completed_Status ");
			}

			Session session = getSession();
			SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
			if (groupType != null && groupType.equalsIgnoreCase("District")) {
				sqlQuery.addScalar("districtId", Hibernate.LONG);
				sqlQuery.addScalar("districtName", Hibernate.STRING);
			} else if (groupType != null && groupType.equalsIgnoreCase("Constituency")) {
				sqlQuery.addScalar("assemblyId", Hibernate.LONG);
				sqlQuery.addScalar("assemblyName", Hibernate.STRING);
			} else if (groupType != null && groupType.equalsIgnoreCase("Mandal")) {
				sqlQuery.addScalar("tehsilId", Hibernate.LONG);
				sqlQuery.addScalar("tehsilName", Hibernate.STRING);
			} else if (groupType != null && groupType.equalsIgnoreCase("Village")) {
				sqlQuery.addScalar("panchayatId", Hibernate.LONG);
				sqlQuery.addScalar("panchayatName", Hibernate.STRING);
			} else if (groupType != null && groupType.equalsIgnoreCase("parliament")) {
				sqlQuery.addScalar("parliamentId", Hibernate.LONG);
				sqlQuery.addScalar("parliamentName", Hibernate.STRING);
			} else if (groupType != null && groupType.equalsIgnoreCase("TownDivision")) {
				sqlQuery.addScalar("localElectionBoydId", Hibernate.LONG);
				sqlQuery.addScalar("localElectionBoydName", Hibernate.STRING);
			} else if (groupType != null && groupType.equalsIgnoreCase("ward")) {
				sqlQuery.addScalar("wardId", Hibernate.LONG);
				sqlQuery.addScalar("wardName", Hibernate.STRING);
			}
			sqlQuery.addScalar("supportPurpose", Hibernate.STRING);
			sqlQuery.addScalar("status", Hibernate.STRING);
			sqlQuery.addScalar("complaintCount", Hibernate.LONG);

			sqlQuery.setParameter("stateId", stateId);
			if (filterScopeId != null && filterScopeId.longValue() > 0l && filterScopeId.longValue() !=2l && filterScopeValues != null && filterScopeValues.size() > 0) {
				sqlQuery.setParameterList("filterScopeValues", filterScopeValues);
			}
			if(fromDate != null && toDate != null){
				sqlQuery.setDate("fromDate", fromDate);
				sqlQuery.setDate("toDate", toDate);
	    	}
			return sqlQuery.list();
	   }
	  private StringBuilder prepareSelectQueryBasedLocationLevel(String groupType){
			
		  StringBuilder queryStr = new StringBuilder();
		  
		  if (groupType != null && groupType.equalsIgnoreCase("District")) {
				queryStr.append(" d.district_id as districtId,");
				queryStr.append(" d.district_name as districtName,");
			} else if (groupType != null && groupType.equalsIgnoreCase("Constituency")) {
				queryStr.append(" c.constituency_id as assemblyId,");
				queryStr.append(" c.name as assemblyName,");
			} else if (groupType != null && groupType.equalsIgnoreCase("Parliament")) {
				queryStr.append(" pc.constituency_id as parliamentId,");
				queryStr.append(" pc.name as parliamentName,");
			} else if (groupType != null && groupType.equalsIgnoreCase("TownDivision")) {
				queryStr.append(" leb.local_election_body_id as localElectionBoydId,");
				queryStr.append(" leb.name as localElectionBoydName,");
			} else if (groupType != null && groupType.equalsIgnoreCase("Mandal")) {
				queryStr.append(" t.tehsil_id as tehsilId,");
				queryStr.append(" t.tehsil_name as tehsilName,");
			} else if (groupType != null && groupType.equalsIgnoreCase("Village")) {
				queryStr.append(" p.panchayat_id as panchayatId,");
				queryStr.append(" p.panchayat_name as panchayatName,");
			} else if (groupType != null && groupType.equalsIgnoreCase("ward")) {
				queryStr.append(" ward.constituency_id as wardId,");
				queryStr.append(" ward.name as wardName,");
			}
		return queryStr;
	}
	  private StringBuilder gettingDynamicTableBasedOnLocationLevel(String groupType){
			
		  StringBuilder queryStr = new StringBuilder();
		  
		   if (groupType != null && groupType.equalsIgnoreCase("District")) {
				queryStr.append(",district d");
			} else if (groupType != null && groupType.equalsIgnoreCase("Constituency")) {
				queryStr.append(",constituency c ");
			} else if (groupType != null && groupType.equalsIgnoreCase("Mandal")) {
				queryStr.append(",tehsil t ");
			} else if (groupType != null && groupType.equalsIgnoreCase("Village")) {
				queryStr.append(",panchayat p ");
			} else if (groupType != null && groupType.equalsIgnoreCase("parliament")) {
				queryStr.append(",constituency pc ");
			} else if (groupType != null && groupType.equalsIgnoreCase("TownDivision")) {
				queryStr.append(",local_election_body leb ");
			} else if (groupType != null && groupType.equalsIgnoreCase("ward")) {
				queryStr.append(",constituency ward ");
			}
		return queryStr;
	}
	  private StringBuilder gettingDynamicTableJoinedBasedOnLocationLevel(String groupType){
			
		  StringBuilder queryStr = new StringBuilder();
		  
		   if (groupType != null && groupType.equalsIgnoreCase("District")) {
			   queryStr.append(" and cm.district_id=d.district_id");
			} else if (groupType != null && groupType.equalsIgnoreCase("Constituency")) {
				 queryStr.append(" and cm.assembly_id=c.constituency_id ");
			} else if (groupType != null && groupType.equalsIgnoreCase("Mandal")) {
				queryStr.append(" and cm.tehsil_id=t.tehsil_id ");
			} else if (groupType != null && groupType.equalsIgnoreCase("Village")) {
				queryStr.append(" and cm.panchayat_id=p.panchayat_id "); 
			} else if (groupType != null && groupType.equalsIgnoreCase("parliament")) {
				queryStr.append(" and cm.parliament_id = pc.constituency_id ");
			} else if (groupType != null && groupType.equalsIgnoreCase("TownDivision")) {
				queryStr.append(" and cm.local_election_body_id=leb.local_election_body_id "); 
			} else if (groupType != null && groupType.equalsIgnoreCase("ward")) {
				queryStr.append(" and cm.ward=c.constituency_id"); 
			}
		   
		   
		return queryStr;
	}
	  
	  
	 private StringBuilder prepareFilterQueryBasedLocationLevel(Long filterScopeId, List<Long> filterScopeValues){
			
		     StringBuilder queryStr = new StringBuilder();
		  
		     if (filterScopeId != null && filterScopeValues != null && filterScopeValues.size() > 0) {
				if (filterScopeId.longValue() == IConstants.DISTRICT_SCOPE_ID) {
					queryStr.append(" AND cm.district_id in (:filterScopeValues)");
				} else if (filterScopeId.longValue() == IConstants.PARLIAMENT_CONSTITUENCY_SCOPE_ID) {
					queryStr.append(" AND cm.parliament_constituency_id in (:filterScopeValues)");
				} else if (filterScopeId.longValue() == IConstants.CONSTITUENCY_SCOPE_ID) {
					queryStr.append(" AND cm.constituency_id in (:filterScopeValues)");
				} else if (filterScopeId.longValue() == IConstants.TEHSIL_SCOPE_ID) {
					queryStr.append(" AND cm.tehsil_id in (:filterScopeValues)");
				} else if (filterScopeId.longValue() == IConstants.MUNICIPAL_CORP_GMC_SCOPE_ID) { // town/division
					queryStr.append(" AND cm.local_election_body in (:filterScopeValues)");
				} else if (filterScopeId.longValue() == IConstants.VILLAGE_SCOPE_ID) {
					queryStr.append(" AND cm.panchayat_id in (:filterScopeValues)");
				} else if (filterScopeId.longValue() == IConstants.WARD_SCOPE_ID) {
					queryStr.append(" AND cm.ward in (:filterScopeValues)");
				}
			}
		return queryStr;
	}
	 private StringBuilder prepareGroupQueryBasedLocationLevel(String groupType){
			
		  StringBuilder queryStr = new StringBuilder();
		  
		   if (groupType != null && groupType.equalsIgnoreCase("District")) {
				queryStr.append(" group by d.district_id ");
			} else if (groupType != null && groupType.equalsIgnoreCase("Constituency")) {
				queryStr.append(" group by c.constituency_id ");
			} else if (groupType != null && groupType.equalsIgnoreCase("Mandal")) {
				queryStr.append(" group by t.tehsil_id ");
			} else if (groupType != null && groupType.equalsIgnoreCase("Village")) {
				queryStr.append(" group by p.panchayat_id ");
			} else if (groupType != null && groupType.equalsIgnoreCase("parliament")) {
				queryStr.append(" group by pc.constituency_id ");
			} else if (groupType != null && groupType.equalsIgnoreCase("TownDivision")) {
				queryStr.append(" group by leb.local_election_body_id");
			} else if (groupType != null && groupType.equalsIgnoreCase("ward")) {
				queryStr.append(" group by ward.ward ");
			}
		return queryStr;
	}
}
