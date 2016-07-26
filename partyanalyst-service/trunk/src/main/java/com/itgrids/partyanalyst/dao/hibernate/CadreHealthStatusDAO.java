package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreHealthStatusDAO;
import com.itgrids.partyanalyst.model.CadreHealthStatus;

public class CadreHealthStatusDAO extends GenericDaoHibernate<CadreHealthStatus, Long> implements ICadreHealthStatusDAO{

	public CadreHealthStatusDAO() {
		super(CadreHealthStatus.class);
	}
    
    public List<Object[]> getAllCadreHealthStatus(){
		
		Query query=getSession().createQuery(" select model.cadreHealthStatusId,model.status " +
				" from  CadreHealthStatus model " +
				" order by model.orderNo asc");
	    return query.list();
	}
    
    public List<Object[]> getDeathsAndHospitalizationStatusWiseDetails(Long locationValue,String searchType){
    	
    	/*
    	 * 
    	 * SELECT #distinct CM.membership_id,
				CM.issue_type,GIS.grievance_insurance_status_id,GIS.status
				,COUNT(CM.Complaint_id)
				FROM grievance_insurance_status GIS,complaint_master CM 
			    left join tdp_cadre TC on CM.membership_id = TC.membership_id,user_address UA
				WHERE CM.grievance_insurance_status_id = GIS.grievance_insurance_status_id 
				AND CM.delete_status IS NULL 
				and CM.issue_type = 'Death'
			    and CM.type_of_issue = 'Insurance'
			    and TC.address_id = UA.user_address_id
			    and UA.tehsil_id = 1102
			    #and CM.assembly_id = 282
			    and CM.state_id_cmp IN (1,2)
			    and (CM.Subject != '' OR CM.Subject IS NOT NULL)
				GROUP BY GIS.grievance_insurance_status_id;*/
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("SELECT CM.issue_type," +
    					" GIS.grievance_insurance_status_id," +
    					" GIS.status," +
    					" COUNT(CM.Complaint_id)" +
    					" FROM grievance_insurance_status GIS,complaint_master CM left join tdp_cadre TC on CM.membership_id = TC.membership_id,user_address UA " +
    					" WHERE CM.grievance_insurance_status_id = GIS.grievance_insurance_status_id" +
    					" AND CM.delete_status IS NULL" +
    					" AND CM.type_of_issue = 'Insurance'" +
    					" AND TC.address_id = UA.user_address_id" +
    					" AND CM.state_id_cmp IN (1,2)" +
    					" AND (CM.Subject != '' OR CM.Subject IS NOT NULL)");
    	
    	/*if(issueType.equalsIgnoreCase("Death"))
    		sb.append(" AND CM.issue_type = 'Death'");
    	else if(issueType.equalsIgnoreCase("Hospitalization"))
    	
    		sb.append(" AND CM.issue_type = 'Hospitalization'");*/
    	
    	if(searchType.equalsIgnoreCase("panchayat"))
    		sb.append(" AND UA.panchayat_id = :locationValue");
    	else if(searchType.equalsIgnoreCase("mandal"))
    		sb.append(" AND CM.tehsil_id = :locationValue");
    	else if(searchType.equalsIgnoreCase("leb"))
    		sb.append(" AND CM.local_election_body_id = :locationValue");
    	else if(searchType.equalsIgnoreCase("constituency"))
    		sb.append(" AND CM.assembly_id = :locationValue");
    	else if(searchType.equalsIgnoreCase("parliament"))
    		sb.append(" AND CM.parliament_id = :locationValue");
    	else if(searchType.equalsIgnoreCase("district"))
    		sb.append(" AND CM.district_id = :locationValue");
    	
    	sb.append(" GROUP BY CM.issue_type,GIS.grievance_insurance_status_id");
    	
    	Query query = getSession().createSQLQuery(sb.toString());
    	
    	if(searchType != "" && searchType != null)
    		query.setParameter("locationValue", locationValue);
    	
    	return query.list();
    }
    
    public List<Object[]> getAllGrievanceInsuranceStatus(){
    	Query query = getSession().createSQLQuery("select grievance_insurance_status_id," +
    							" status," +
    							" order_no" +
    							" from grievance_insurance_status" +
    							" order by order_no");
    	return query.list();
    }
    
    public List<Object[]> getComplaintsDetailsByLocationAndStatus(Long locationId,String locationType,Long insuranceStatId,String issueType){
    	/*
    	 * 
    	 * select TC.First_Name,TC.membership_id,TC.Mobile_No,CM.Subject,CM.description,CM.Complaint_id,
				CM.Raised_Date,CM.type_of_issue,GIS.status,CM.updated_date
				from grievance_insurance_status GIS,complaint_master CM
			    left join tdp_cadre TC on CM.membership_id = TC.membership_id,user_address UA
			    where GIS.grievance_insurance_status_id = CM.grievance_insurance_status_id
			    and UA.user_address_id = TC.address_id
			    and UA.tehsil_id = 1102
			    and GIS.grievance_insurance_status_id = 1
			    and CM.issue_type = 'Death'
				and CM.type_of_issue = 'Insurance'
			    and CM.state_id_cmp IN (1,2)
				and (CM.Subject != '' OR CM.Subject IS NOT NULL);
    	 */
    	StringBuilder sb = new StringBuilder();
    	sb.append("select TC.first_name," +
    					" TC.membership_id," +
    					" TC.mobile_no," +
    					" CM.Complaint_id," +
    					" date(CM.Raised_Date) as Raised_Date," +
    					" CM.type_of_issue," +
    					" GIS.grievance_insurance_status_id," +
    					" GIS.status," +
    					" date(CM.updated_date) as updated_date," +
    					" TC.tdp_cadre_id," +
    					" CM.Subject," +
    					" CM.description," +
    					" CM.Location," +
    					" CM.constituency," +
    					" CM.village_name," +
    					" CM.mandal_name" +
    					" from grievance_insurance_status GIS," +
    					" complaint_master CM" +
    					" left join tdp_cadre TC on CM.membership_id = TC.membership_id," +
    					" user_address UA" +
    					" where GIS.grievance_insurance_status_id = CM.grievance_insurance_status_id" +
    					" and UA.user_address_id = TC.address_id" +
    					" and GIS.grievance_insurance_status_id = :insuranceStatId" +
    					" and CM.issue_type = :issueType" +
    					" and CM.type_of_issue = 'Insurance'" +
    					" and CM.state_id_cmp IN (1,2)" +
    					" and (CM.Subject != '' OR CM.Subject IS NOT NULL)" +
    					" and CM.delete_status IS NULL");
    	
    	if(locationType.equalsIgnoreCase("panchayat"))
    		sb.append(" AND UA.panchayat_id = :locationId");
    	else if(locationType.equalsIgnoreCase("mandal"))
    		sb.append(" AND CM.tehsil_id = :locationId");
    	else if(locationType.equalsIgnoreCase("leb") || locationType.equalsIgnoreCase("muncipality"))
    		sb.append(" AND CM.local_election_body_id = :locationId");
    	else if(locationType.equalsIgnoreCase("assembly"))
    		sb.append(" AND CM.assembly_id = :locationId");
    	else if(locationType.equalsIgnoreCase("parliament"))
    		sb.append(" AND CM.parliament_id = :locationId");
    	else if(locationType.equalsIgnoreCase("district"))
    		sb.append(" AND CM.district_id = :locationId");
    	
    	Query query = getSession().createSQLQuery(sb.toString()).addScalar("first_name",Hibernate.STRING).addScalar("membership_id",Hibernate.STRING)
    			.addScalar("mobile_no",Hibernate.STRING).addScalar("Complaint_id",Hibernate.LONG).addScalar("Raised_Date",Hibernate.DATE)
    			.addScalar("type_of_issue",Hibernate.STRING).addScalar("grievance_insurance_status_id",Hibernate.LONG).addScalar("status",Hibernate.STRING)
    			.addScalar("updated_date",Hibernate.DATE).addScalar("tdp_cadre_id",Hibernate.LONG)
    			.addScalar("Subject",Hibernate.STRING).addScalar("description",Hibernate.STRING).addScalar("Location",Hibernate.STRING)
    			.addScalar("constituency",Hibernate.STRING).addScalar("village_name",Hibernate.STRING).addScalar("mandal_name",Hibernate.STRING);
    			
    	if(locationType != "" && locationType != null)
    		query.setParameter("locationId", locationId);
    	
    	query.setParameter("insuranceStatId", insuranceStatId);
    	query.setParameter("issueType", issueType);
    	
    	return query.list();
    }
    
    /*public List<Object[]> testKamal()
    {
    	return getSession().createSQLQuery("select TC.first_name,TC.membership_id,TC.mobile_no,CM.Complaint_id,date(CM.Raised_Date), from " +
    			"grievance_insurance_status GIS, complaint_master CM left outer join tdp_cadre TC on CM.membership_id = TC.membership_id left outer join " +
    			"user_address UA on TC.address_id = UA.user_address_id where GIS.grievance_insurance_status_id = CM.grievance_insurance_status_id and " +
    			"GIS.grievance_insurance_status_id = 1 and " +
    			"CM.issue_type = 'Death' and CM.type_of_issue = 'Insurance' and CM.state_id_cmp IN (1,2) and " +
    			"(CM.Subject != '' OR CM.Subject IS NOT NULL) AND UA.tehsil_id = 1102").list();
    	
    }*/
    
   /* public List<Object[]> getRequestRaisedTimeDetails(Long complaintId)
	{
		Query query = getSession().createSQLQuery("select CT.complaint_tracking_id," +
    										" date(CT.inserted_time) as inserted_time" +
    										" from complaint_tracking CT" +
    										" where CT.complaint_progress_status_id = 1" +
    										" and CT.Complaint_id = :complaintId").addScalar("complaint_tracking_id",Hibernate.LONG).addScalar("inserted_time",Hibernate.DATE);
    	
		query.setParameter("complaintId", complaintId);	
		
		return query.list();
	}
	
	public List<Object[]> getAllStatusDetails(Long complaintId)
	{
		Query query = getSession().createSQLQuery("select CT.complaint_tracking_id," +
											" CT.comment," +
											" date(CT.inserted_time) as inserted_time" +
											" from complaint_tracking CT" +
											" where CT.complaint_progress_status_id = 5" +
											" and CT.Complaint_id = :complaintId" +
											" group by CT.complaint_progress_status_id,CT.comment" +
											" order by CT.complaint_tracking_id").addScalar("complaint_tracking_id",Hibernate.LONG)
											.addScalar("comment",Hibernate.STRING).addScalar("inserted_time",Hibernate.DATE);
		
		query.setParameter("complaintId", complaintId);
		
		return query.list();
	}*/
	
	public List<Object[]> getAllStatusDetailsByComplaint(Long complaintId,String type)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("select distinct model.complaint_tracking_id," +
						" model.complaint_progress_status_id," +
						" model.comment," +
						" date(model.inserted_time) as inserted_time," +
						" model.name" +
						" from complaint_tracking model" +
						" where model.Complaint_id =:complaintId");
		
		if(type.equalsIgnoreCase("grievance")){
			sb.append(" and model.complaint_progress_status_id in (1,5) " );
		}
		else if(type.equalsIgnoreCase("insurance")){
			sb.append(" and model.complaint_progress_status_id in (1,11) " );
		}
		sb.append(" order by model.complaint_tracking_id asc ");
		
		Query query = getSession().createSQLQuery(sb.toString()).addScalar("complaint_tracking_id",Hibernate.LONG).addScalar("complaint_progress_status_id",Hibernate.LONG)
																.addScalar("comment",Hibernate.STRING).addScalar("inserted_time",Hibernate.DATE).addScalar("name",Hibernate.STRING);
		
		query.setParameter("complaintId", complaintId);
		return query.list();
	}
	
	public List<String> getCompletedStatusBycomplaintId(Long complaintId){
	
		Query query = getSession().createSQLQuery("select model.Completed_Status" +
									" from complaint_master model" +
									" where (model.delete_status !='0' or model.delete_status is null)" +
									" and model.Subject !='' and model.Complaint_id=:complaintId");
		
		query.setParameter("complaintId", complaintId);
		
		return query.list();
	}
	
	public List<String> getStatusBycomplaintIdForInsurance(Long complaintId){
		
		Query query = getSession().createSQLQuery("select model1.status" +
									" from complaint_master model,grievance_insurance_status model1" +
									" where model.grievance_insurance_status_id = model1.grievance_insurance_status_id" +
									" and (model.delete_status !='0' or model.delete_status is null)" +
									" and model.Subject !=''" +
									" and model.Complaint_id=:complaintId");
		
		query.setParameter("complaintId", complaintId);
		
		return query.list();
	}
	
	public List<Object[]> getComplaintsDetailsForGrievanceByLocationAndStatus(Long locationId,String locationType,String status,String issueType){
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("select CM.name," +
    					" CM.mobile_no," +
    					" CM.Complaint_id," +
    					" date(CM.Raised_Date) as Raised_Date," +
    					" CM.type_of_issue," +
    					" CM.Completed_Status," +
    					" date(CM.updated_date) as updated_date," +
    					" CM.Subject," +
    					" CM.description," +
    					" CM.Location," +
    					" CM.constituency," +
    					" CM.village_name," +
    					" CM.mandal_name" +
    					" from" +
    					" complaint_master CM" +
    					" where CM.type_of_issue = :issueType" +
    					" and CM.Completed_Status = :status" +
    					" and  CM.subject !=''" +
    					" and (CM.delete_status !='0' or CM.delete_status is null)");
    	
    	if(locationType.equalsIgnoreCase("assembly"))
    		sb.append(" AND CM.assembly_id = :locationId");
    	else if(locationType.equalsIgnoreCase("parliament"))
    		sb.append(" AND CM.parliament_id = :locationId");
    	else if(locationType.equalsIgnoreCase("district"))
    		sb.append(" AND CM.district_id = :locationId");
    	
    	Query query = getSession().createSQLQuery(sb.toString()).addScalar("name",Hibernate.STRING).addScalar("mobile_no",Hibernate.STRING)
    			.addScalar("Complaint_id",Hibernate.LONG).addScalar("Raised_Date",Hibernate.DATE).addScalar("type_of_issue",Hibernate.STRING)
    			.addScalar("Completed_Status",Hibernate.STRING).addScalar("updated_date",Hibernate.DATE).addScalar("Subject",Hibernate.STRING)
    			.addScalar("description",Hibernate.STRING).addScalar("Location",Hibernate.STRING).addScalar("constituency",Hibernate.STRING)
    			.addScalar("village_name",Hibernate.STRING).addScalar("mandal_name",Hibernate.STRING);
    			
    	if(locationType != "" && locationType != null)
    		query.setParameter("locationId", locationId);
    	
    	query.setParameter("status", status);
    	query.setParameter("issueType", issueType);
    	
    	return query.list();
    }
	
	public List<Object[]> getGrievanceRequestDetailsForBenifits(Long id,String searchType,String status){
		  
		  StringBuilder str = new StringBuilder();
		  
		  str.append("select CM.name," +
					" CM.mobile_no," +
					" CM.Complaint_id," +
					" date(CM.Raised_Date) as Raised_Date," +
					" CM.type_of_issue," +
					" CM.Completed_Status," +
					" date(CM.updated_date) as updated_date," +
					" CM.Subject," +
					" CM.description," +
					" CM.approved_amount," +
					" CM.actual_approved_amount" +
					" from" +
					" complaint_master CM" +
					" where ");
		  
		  if(searchType.equalsIgnoreCase("district"))
			   str.append(" CM.district_id =:id and  ");
		   
		   else if(searchType.equalsIgnoreCase("assembly"))
			   str.append(" CM.assembly_id = :id  and ");
		   
		   else if(searchType.equalsIgnoreCase("parliament"))
			   str.append(" CM.parliament_id= :id and ");
		  
		  str.append(" CM.type_of_issue != 'Insurance'");
		  str.append(" and (CM.delete_status !='0' or CM.delete_status is null )" +
		  		" and CM.state_id_cmp in(1,2) and ");
		  str.append(" CM.Subject != '' ");
		  str.append(" and ((CM.issue_type ='Personal' and  CM.expected_amount is not null and CM.expected_amount !='' )" +
		  		" or (CM.issue_type ='CM Relief' and CM.expected_amount is not null and CM.expected_amount != '') or " +
		  		" (CM.issue_type ='Health' and CM.health_amount is not null and CM.health_amount != '') or " +
		  		" (CM.issue_type ='Financial Support' and CM.expected_amount is not null and CM.expected_amount !=''))" +
		  		" and CM.Completed_Status = :status ");
		  //str.append(" group by model.Completed_Status ");
			   Query query = getSession().createSQLQuery(str.toString()).addScalar("name",Hibernate.STRING).addScalar("mobile_no",Hibernate.STRING)
		    			.addScalar("Complaint_id",Hibernate.LONG).addScalar("Raised_Date",Hibernate.DATE)
		    			.addScalar("type_of_issue",Hibernate.STRING).addScalar("Completed_Status",Hibernate.STRING)
		    			.addScalar("updated_date",Hibernate.DATE).addScalar("Subject",Hibernate.STRING).addScalar("description",Hibernate.STRING)
		    			.addScalar("approved_amount",Hibernate.STRING).addScalar("actual_approved_amount",Hibernate.STRING);
			   
			   query.setParameter("id", id);
			   query.setParameter("status", status);
		  
		return query.list();
		  
	  }
	
	public List<Object[]> getApprovedAmountDetailsByLocation(Long locationId,String locationType){
		StringBuilder sb = new StringBuilder();
		sb.append("select CM.type_of_issue," +
							" CM.issue_type," +
							" count(CM.Complaint_id)," +
							" sum(CM.approved_amount)" +
							" from complaint_master CM" +
							" where CM.Completed_Status = 'completed'" +
							" and (CM.delete_status !='0' or CM.delete_status is null)" +
							" and (CM.Subject != '' or CM.Subject is not null)" +
							" and CM.state_id_cmp in (1,2)");
		if(locationType.equalsIgnoreCase("assembly"))
			sb.append(" and CM.assembly_id = :locationId");
		else if(locationType.equalsIgnoreCase("parliament"))
			sb.append(" and CM.parliament_id = :locationId");
		else if(locationType.equalsIgnoreCase("district"))
			sb.append(" and CM.district_id = :locationId");
		
		sb.append(" group by CM.type_of_issue,CM.issue_type");
		
		Query query = getSession().createSQLQuery(sb.toString());
		if(locationType != null)
			query.setParameter("locationId", locationId);
		
		return query.list();
	}
	
	public List<Object[]> getApprovedAmountDetailsForGovtAndWilfareByLocation(Long locationId,String locationType){
		StringBuilder sb = new StringBuilder();
		sb.append("select CM.type_of_issue," +
							" count(CM.Complaint_id)," +
							" sum(CM.approved_amount)" +
							" from complaint_master CM" +
							" where CM.Completed_Status = 'completed'" +
							" and (CM.delete_status !='0' or CM.delete_status is null)" +
							" and (CM.Subject != '' or CM.Subject is not null)" +
							" and CM.state_id_cmp in (1,2)" +
							" and CM.approved_amount is not null and CM.approved_amount > 0");
		if(locationType.equalsIgnoreCase("assembly"))
			sb.append(" and CM.assembly_id = :locationId");
		else if(locationType.equalsIgnoreCase("parliament"))
			sb.append(" and CM.parliament_id = :locationId");
		else if(locationType.equalsIgnoreCase("district"))
			sb.append(" and CM.district_id = :locationId");
		
		sb.append(" group by CM.type_of_issue");
		
		Query query = getSession().createSQLQuery(sb.toString());
		if(locationType != null)
			query.setParameter("locationId", locationId);
		
		return query.list();
	}
	
	public List<Object[]> getApprovedAmountDetailsForWilfareByLocation(Long locationId,String locationType){
		StringBuilder sb = new StringBuilder();
		sb.append("select CM.type_of_issue," +
							" count(CM.Complaint_id)" +
							" from complaint_master CM" +
							" where CM.Completed_Status = 'completed'" +
							" and (CM.delete_status !='0' or CM.delete_status is null)" +
							" and (CM.Subject != '' or CM.Subject is not null)" +
							" and CM.state_id_cmp in (1,2)" +
							" and CM.support_purpose = 'Seat'");
		if(locationType.equalsIgnoreCase("assembly"))
			sb.append(" and CM.assembly_id = :locationId");
		else if(locationType.equalsIgnoreCase("parliament"))
			sb.append(" and CM.parliament_id = :locationId");
		else if(locationType.equalsIgnoreCase("district"))
			sb.append(" and CM.district_id = :locationId");
		
		sb.append(" group by CM.type_of_issue");
		
		Query query = getSession().createSQLQuery(sb.toString());
		if(locationType != null)
			query.setParameter("locationId", locationId);
		
		return query.list();
	}
	
	public List<Object[]> getGrievanceBenifitsDetailsByLocation(Long locationId,String locationType,String typeOfIssue,String otherBenifit){
		StringBuilder sb = new StringBuilder();
		sb.append("select CM.name," +
    					" CM.mobile_no," +
    					" CM.Complaint_id," +
    					" date(CM.Raised_Date) as Raised_Date," +
    					" CM.type_of_issue," +
    					" date(CM.updated_date) as updated_date," +
    					" CM.Subject," +
    					" CM.description," +
    					" CM.Location," +
    					" CM.constituency," +
    					" CM.village_name," +
    					" CM.mandal_name," +
    					" CM.support_purpose," +
    					" CM.approved_amount," +
    					" CM.issue_type");
    					
    	sb.append(" from complaint_master CM" +
						" where CM.Completed_Status = 'completed'" +
						" and (CM.delete_status !='0' or CM.delete_status is null)" +
						" and (CM.Subject != '' or CM.Subject is not null)" +
						" and CM.state_id_cmp in (1,2)" +
						" and CM.type_of_issue = :typeOfIssue");
		if(locationType.equalsIgnoreCase("assembly"))
			sb.append(" and CM.assembly_id = :locationId");
		else if(locationType.equalsIgnoreCase("parliament"))
			sb.append(" and CM.parliament_id = :locationId");
		else if(locationType.equalsIgnoreCase("district"))
			sb.append(" and CM.district_id = :locationId");
		
		if(otherBenifit.equalsIgnoreCase("other"))
			sb.append(" and CM.support_purpose = 'Seat'");
		else if(otherBenifit.equalsIgnoreCase("total"))
			sb.append(" and CM.approved_amount is not null and CM.approved_amount > 0");
		
		Query query = getSession().createSQLQuery(sb.toString()).addScalar("name",Hibernate.STRING).addScalar("mobile_no",Hibernate.STRING)
				.addScalar("Complaint_id",Hibernate.LONG).addScalar("Raised_Date",Hibernate.DATE).addScalar("type_of_issue",Hibernate.STRING)
				.addScalar("updated_date",Hibernate.DATE).addScalar("Subject",Hibernate.STRING).addScalar("description",Hibernate.STRING)
				.addScalar("Location",Hibernate.STRING).addScalar("constituency",Hibernate.STRING).addScalar("village_name",Hibernate.STRING)
				.addScalar("mandal_name",Hibernate.STRING).addScalar("support_purpose",Hibernate.STRING).addScalar("approved_amount",Hibernate.STRING)
				.addScalar("issue_type",Hibernate.STRING);
		
		query.setParameter("locationId", locationId);
		query.setParameter("typeOfIssue", typeOfIssue);
		
		return query.list();
	}
	
	public List<Object[]> getNominatedPostComplaintPDF(String membershipNo)
	{
		Query query = getSession().createSQLQuery("select model.issue_type as issueType,model.Completed_Status as status,date(model.Raised_Date) as raisedDate,model.Complaint_id as complaintId from complaint_master model" +
				" where (model.delete_status !='0' or model.delete_status is null) and model.subject !=''" +
				" and model.issue_type = 'Nominated posts' " +
				" and model.membership_id = :membershipNo")
				.addScalar("issueType",Hibernate.STRING)
				.addScalar("status",Hibernate.STRING)
				.addScalar("raisedDate",Hibernate.STRING)
				.addScalar("complaintId",Hibernate.LONG);
				query.setParameter("membershipNo", membershipNo);
		return query.list();
	}
	
	public List<Object[]> getNominatedComplaintScanCopies(List<Long> complaintIds){
		Query query = getSession().createSQLQuery(" select SC.scanned_copy_id as id,SC.scanned_copy_path as path,date(CSC.inserted_time) as insertedTime,CM.Complaint_id as complaintId from complaint_scanned_copy CSC,complaint_master CM,scanned_copy SC " +
				" where SC.is_deleted = 'N'  and (CM.delete_status !='0' or CM.delete_status is null) and CM.subject !='' " +
				" and CSC.scanned_copy_id = SC.scanned_copy_id and CM.Complaint_id =  CSC.complaint_id " +
				" and CM.Complaint_id in(:complaintIds) " +
				" and CM.issue_type = 'Nominated posts' order by SC.scanned_copy_id desc ")
				.addScalar("id",Hibernate.LONG)
				.addScalar("path",Hibernate.STRING)
				.addScalar("insertedTime",Hibernate.DATE)
				.addScalar("complaintId",Hibernate.LONG);
		query.setParameterList("complaintIds", complaintIds);
		return query.list();
	}
	public List<Object[]> getNominatedPostScanCopyForComplaint(List<Long> complaintIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.scan_copy as path,model.Complaint_id as complaintId from complaint_master model where " +
				" (model.delete_status !='0' or model.delete_status is null) and model.subject !='' " +
				" and model.Complaint_id in(:complaintIds) ");
		Query query = getSession().createSQLQuery(str.toString())
				.addScalar("path",Hibernate.STRING)
				.addScalar("complaintId",Hibernate.LONG);
		query.setParameterList("complaintIds", complaintIds);
		return query.list();	
	}
	
}
