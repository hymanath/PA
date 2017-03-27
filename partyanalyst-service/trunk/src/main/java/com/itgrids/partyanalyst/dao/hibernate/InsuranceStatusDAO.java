package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IInsuranceStatusDAO;
import com.itgrids.partyanalyst.model.InsuranceStatus;

public class InsuranceStatusDAO extends GenericDaoHibernate<InsuranceStatus, Long> implements IInsuranceStatusDAO{

	public InsuranceStatusDAO() {
		super(InsuranceStatus.class);
	}

	public List<Object[]> getStatusAndInsuranceCompanyWiseComplaints(Long locationId,Set<Long> locationValuesSet,Long stateId,Long cadreYearId,Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select gis.grievance_insurance_status_id as statusId," +
					" gic.grievance_insurance_company_id as comapnyId," +
					" gic.company_name as compantName," +
					" cm.issue_type as issueType," +
					" count(distinct cm.Complaint_id) as count" +
					" from complaint_master cm,tdp_cadre_enrollment_year tcey,tdp_cadre tc,complaint_master_insurance_company cmic," +
					" grievance_insurance_company gic,grievance_insurance_status gis" +
				" where" +
					" cm.membership_id = tc.membership_id" +
					" and tc.tdp_cadre_id = tcey.tdp_cadre_id" +
					" and cmic.complaint_master_id = cm.Complaint_id" +
					" and gic.grievance_insurance_company_id = cmic.grievance_insurance_company_id" +
					" and cm.grievance_insurance_status_id = gis.grievance_insurance_status_id" +
					" and tcey.is_deleted = 'N' and tc.is_deleted = 'N'" +
					" and cm.type_of_issue = 'Insurance'" +
					" and cm.delete_status IS NULL" +
					" and (cm.Subject IS NOT NULL OR cm.Subject != '')");
		
		if(stateId != null && stateId > 0l)
			sb.append("and cm.state_id_cmp IN (:stateId)");
		else
			sb.append(" and cm.state_id_cmp IN (1,2)");
		
		if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 2L)
    		sb.append(" and cm.state_id_cmp IN (:locationValuesSet) ");
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 3L)
    		sb.append(" and cm.district_id IN (:locationValuesSet) ");
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 4L)
    		sb.append(" and cm.assembly_id IN (:locationValuesSet) ");
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 5L)
    		sb.append(" and cm.parliament_id IN (:locationValuesSet) ");
    	        
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
				.addScalar("count", Hibernate.LONG);
		
		if(stateId != null && stateId > 0l)
			query.setParameter("stateId", stateId);
		if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0)
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
					" and tcey.is_deleted = 'N' and tc.is_deleted = 'N'" +
					" and cm.type_of_issue = 'Insurance'" +
					" and cm.delete_status IS NULL" +
					" and (cm.Subject IS NOT NULL OR cm.Subject != '')");
		
		if(stateId != null && stateId > 0l)
			sb.append("and cm.state_id_cmp IN (:stateId)");
		else
			sb.append(" and cm.state_id_cmp IN (1,2)");
		
		if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 2L)
    		sb.append(" and cm.state_id_cmp IN (:locationValuesSet) ");
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 3L)
    		sb.append(" and cm.district_id IN (:locationValuesSet) ");
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 4L)
    		sb.append(" and cm.assembly_id IN (:locationValuesSet) ");
    	if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0 && locationId.longValue() == 5L)
    		sb.append(" and cm.parliament_id IN (:locationValuesSet) ");
    	        
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
    	
    	Query query = getSession().createSQLQuery(sb.toString()).addScalar("complaintId", Hibernate.LONG);
    	
    	if(stateId != null && stateId > 0l)
			query.setParameter("stateId", stateId);
		if(locationId != null && locationValuesSet != null && locationValuesSet.size() > 0)
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
					" cm.name as name,cm.mobile_no as mobileNo," +
					" cm.membership_id as membershipId," +
					" cm.district_id as districtId," +
					" cm.Location as districtName," +
					" cm.assembly_id as constId," +
					" cm.constituency as constituency," +
					" cm.Subject as subject," +
					" cm.description as description," +
					" cm.issue_type as issueType," +
					" gis.grievance_insurance_status_id statusId," +
					" gis.status as status," +
					" cm.Raised_Date as postedDate" +
					
					" from complaint_master cm,grievance_insurance_status gis" +
				" where cm.Complaint_id in (:complaintIds)");
					
    	
    	Query query = getSession().createSQLQuery(sb.toString())
    			.addScalar("complaintId", Hibernate.LONG).addScalar("name", Hibernate.STRING)
    			.addScalar("mobileNo", Hibernate.STRING).addScalar("membershipId", Hibernate.STRING)
    			.addScalar("districtId", Hibernate.LONG).addScalar("districtName", Hibernate.STRING)
    			.addScalar("constId", Hibernate.LONG).addScalar("constituency", Hibernate.STRING)
    			.addScalar("subject", Hibernate.STRING).addScalar("description", Hibernate.STRING)
    			.addScalar("issueType", Hibernate.STRING).addScalar("statusId", Hibernate.LONG)
    			.addScalar("status", Hibernate.STRING).addScalar("postedDate", Hibernate.STRING);
    	
    	query.setParameterList("complaintIds", complaintIds);
    	
    	return query.list();
	}
}
