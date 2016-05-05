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
    					" FROM grievance_insurance_status GIS,complaint_master CM" +
    					" LEFT JOIN tdp_cadre TC ON CM.membership_id = TC.membership_id,user_address UA" +
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
    		sb.append(" AND UA.tehsil_id = :locationValue");
    	else if(searchType.equalsIgnoreCase("leb"))
    		sb.append(" AND UA.local_election_body = :locationValue");
    	else if(searchType.equalsIgnoreCase("constituency"))
    		sb.append(" AND UA.constituency_id = :locationValue");
    	else if(searchType.equalsIgnoreCase("parliament"))
    		sb.append(" AND UA.parliament_constituency_id = :locationValue");
    	else if(searchType.equalsIgnoreCase("district"))
    		sb.append(" AND UA.district_id = :locationValue");
    	
    	sb.append(" GROUP BY CM.issue_type,GIS.grievance_insurance_status_id");
    	
    	Query query = getSession().createSQLQuery(sb.toString());
    	
    	if(searchType != "" && searchType != null)
    		query.setParameter("locationValue", locationValue);
    	
    	return query.list();
    }
    
    public List<Object[]> getAllGrievanceInsuranceStatus(){
    	Query query = getSession().createSQLQuery("select grievance_insurance_status_id," +
    							" status from grievance_insurance_status" +
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
    					" CM.description" +
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
    					" and (CM.Subject != '' OR CM.Subject IS NOT NULL)");
    	
    	if(locationType.equalsIgnoreCase("panchayat"))
    		sb.append(" AND UA.panchayat_id = :locationId");
    	else if(locationType.equalsIgnoreCase("mandal"))
    		sb.append(" AND UA.tehsil_id = :locationId");
    	else if(locationType.equalsIgnoreCase("leb"))
    		sb.append(" AND UA.local_election_body = :locationId");
    	else if(locationType.equalsIgnoreCase("assembly"))
    		sb.append(" AND UA.constituency_id = :locationId");
    	else if(locationType.equalsIgnoreCase("parliament"))
    		sb.append(" AND UA.parliament_constituency_id = :locationId");
    	else if(locationType.equalsIgnoreCase("district"))
    		sb.append(" AND UA.district_id = :locationId");
    	
    	Query query = getSession().createSQLQuery(sb.toString()).addScalar("first_name",Hibernate.STRING).addScalar("membership_id",Hibernate.STRING)
    			.addScalar("mobile_no",Hibernate.STRING).addScalar("Complaint_id",Hibernate.LONG).addScalar("Raised_Date",Hibernate.DATE)
    			.addScalar("type_of_issue",Hibernate.STRING).addScalar("grievance_insurance_status_id",Hibernate.LONG).addScalar("status",Hibernate.STRING)
    			.addScalar("updated_date",Hibernate.DATE).addScalar("tdp_cadre_id",Hibernate.LONG)
    			.addScalar("Subject",Hibernate.STRING).addScalar("description",Hibernate.STRING);
    			
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
}
