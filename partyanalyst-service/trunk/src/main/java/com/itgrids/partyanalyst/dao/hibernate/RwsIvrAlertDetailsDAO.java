package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IRwsIvrAlertDetailsDAO;
import com.itgrids.partyanalyst.model.RwsIvrAlertDetails;

public class RwsIvrAlertDetailsDAO extends GenericDaoHibernate<RwsIvrAlertDetails, Long> implements IRwsIvrAlertDetailsDAO{

	public RwsIvrAlertDetailsDAO() {
		super(RwsIvrAlertDetails.class);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getJalavaniIvrDetailsSummary(Date fromDate,Date toDate){
        StringBuilder str = new StringBuilder();
        	//0-locationId,1-locationName,2-feedbackStatusId,problemtypeId-3,satisifiedStatus-4,count-5
        	str.append(" select model.alert.userAddress.district.districtId,model.alert.userAddress.district.districtName," +
        			" model.alert.alertFeedbackStatusId,model.rwsIvrTypeId," +
            		" model.ivrSatisfiedStatus,count(distinct model.alertId),model.rwsIvrType.rwsIvrName,count(model1.ivrRespondentId),model1.rwsIvrStatus ");
        
      
        str.append(" from RwsIvrAlertDetails model,RwsIvrAlertDetailsRespondent model1 " +
        		" where model.rwsIvrAlertDetailsId = model1.rwsIvrAlertDetailsId and model1.isDeleted='N' " +
        		" and model.isDeleted ='N' and model.alert.isDeleted ='N' and " +
        		" model.alert.govtDepartmentId =:govtDeptId and model.rwsIvrType.isDeleted ='N' and model.alert.alertFeedbackStatusId is not null ");
        
	        if(fromDate !=null && toDate !=null){
	        	str.append(" and date(model.alert.createdTime) between :fromDate and :toDate ");
	        }
        
         str.append(" group by model.alert.userAddress.district.districtId,model.alert.alertFeedbackStatusId," +
        			" model.ivrSatisfiedStatus,model.rwsIvrTypeId,model1.rwsIvrStatus ");
       
        Query query = getSession().createQuery(str.toString());
          query.setParameter("govtDeptId",49l);
          
          if(fromDate !=null && toDate !=null){
            query.setDate("fromDate",fromDate);
            query.setDate("toDate",toDate);
          }
        return  query.list();
      }
	public List<Object[]> getJalavaniIvrSummaryGraphDetailsInfo(Date fromDate,Date toDate,String searchType){
        StringBuilder str = new StringBuilder();
          if(searchType != null && searchType.equalsIgnoreCase("dayWise")){
           //feedbackstatusId-0,rwsIvrTypeId-1,ivrSatisfiedStatus-2,rwsIvrName-3,count-4,date-5
        	  str.append("select model.alert.alertFeedbackStatusId,model.rwsIvrTypeId," +
        	  		" model.ivrSatisfiedStatus,model.rwsIvrType.rwsIvrName, " +
        	  		" count(model.alertId),date(model1.updatedTime) ");
        	  
          }else if(searchType != null && searchType.equalsIgnoreCase("monthWise")){
        		
        	  str.append("select  model.alert.alertFeedbackStatusId,model.rwsIvrTypeId," +
        	  		" model.ivrSatisfiedStatus, " +
        	  		" model.rwsIvrType.rwsIvrName,count(model.alertId), " +
        	  		" month(model1.updatedTime),year(model1.updatedTime) ");
          }
          
          str.append(" from RwsIvrAlertDetails model,AlertAssignedOfficerTrackingNew model1 " +
          		" where model.alertId = model1.alertId " +
          		" and model.alert.isDeleted='N' " +
          		" and model.isDeleted='N' and model.rwsIvrType.isDeleted='N' " +
          		" and model.alert.alertFeedbackStatus.isDeleted='N' and model.alert.alertFeedbackStatusId !=2 " +
          		" and model.alert.govtDepartmentId =:govtDeptId ");
         
          if(fromDate !=null && toDate !=null){
            str.append(" and date(model1.updatedTime) between :fromDate and :toDate ");
          }
          
          if(searchType != null && searchType.equalsIgnoreCase("dayWise")){
            str.append(" group by date(model1.updatedTime),model.rwsIvrTypeId");  
          }else if(searchType != null && searchType.equalsIgnoreCase("monthWise")){
            str.append(" group by month(model1.updatedTime),year(model1.updatedTime),model.rwsIvrTypeId " +
            		" order by month(model1.updatedTime),year(model1.updatedTime) ");
          }
        Query query = getSession().createQuery(str.toString());
          query.setParameter("govtDeptId",49l);
          
          if(fromDate !=null && toDate !=null){
            query.setParameter("fromDate",fromDate);
            query.setParameter("toDate",toDate);
          }
        return  query.list();
      }
	@SuppressWarnings("unchecked")
	public List<Long> getJalavaniIvrSummaryWiseClick(Date fromDate,Date toDate,Long statusId,Long probTypeId,Long districtId,String satisfiedStatus){
        StringBuilder str = new StringBuilder();
        str.append(" select model.alertId ");
      
        str.append(" from RwsIvrAlertDetails model " +
        		" where model.isDeleted ='N' and model.alert.isDeleted ='N' and " +
        		" model.alert.govtDepartmentId =:govtDeptId and model.rwsIvrType.isDeleted ='N' and model.alert.alertFeedbackStatusId is not null");
        
	        if(fromDate !=null && toDate !=null){
	        	str.append(" and date(model.alert.createdTime) between :fromDate and :toDate ");
	        }
	        if(statusId !=null && statusId.longValue() >0){
	        	str.append(" and model.alert.alertFeedbackStatusId =:statusId ");
	        }
	        if(probTypeId !=null && probTypeId.longValue() >0){
	        	str.append(" and model.rwsIvrTypeId =:probTypeId ");
	        }
	        if(districtId !=null && districtId.longValue() >0){
	        	str.append(" and model.alert.userAddress.district.districtId =:districtId ");
	        }
	        if(satisfiedStatus !=null && satisfiedStatus.trim() !=""){
	        	str.append(" and model.ivrSatisfiedStatus =:satisfiedStatus ");
	        }
	        
        Query query = getSession().createQuery(str.toString());
          query.setParameter("govtDeptId",49l);
          	if(statusId !=null && statusId.longValue() >0){
        	  query.setParameter("statusId",statusId);
	        }
	        if(probTypeId !=null && probTypeId.longValue() >0){
	        	query.setParameter("probTypeId",probTypeId);
	        }
          if(fromDate !=null && toDate !=null){
            query.setDate("fromDate",fromDate);
            query.setDate("toDate",toDate);
          }
          if(districtId !=null && districtId.longValue() >0){
        	  query.setParameter("districtId",districtId);
	      }
	      if(satisfiedStatus !=null && satisfiedStatus.trim() !=""){
	    	  query.setParameter("satisfiedStatus",satisfiedStatus);
	      }
        return  query.list();
      }
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getJalavaniIvrRespondantsGraphDetailsInfo(Date fromDate,Date toDate,String searchType){
        StringBuilder str = new StringBuilder();
        Query query =null;
          if(searchType != null && searchType.equalsIgnoreCase("dayWise")){
           //status-0,count-1,date-2
        	  str.append("select IARD.rws_ivr_status as status,count(distinct IARD.ivr_respondent_id) as count,date(aaotn.updated_time) as date");
        	  
          }else if(searchType != null && searchType.equalsIgnoreCase("monthWise")){
        	  //status-0,count-1,month-2,year-3
        	  str.append("select IARD.rws_ivr_status as status,count(distinct IARD.ivr_respondent_id) as count,Month(aaotn.updated_time) as month,year(aaotn.updated_time) as year");
          }
          str.append(" from rws_ivr_alert_details_respondent IARD, rws_ivr_alert_details RIA," +
          		"alert A,alert_assigned_officer_tracking_new aaotn ");
          
          str.append(" where IARD.rws_ivr_alert_details_id=RIA.rws_ivr_alert_details_id and RIA.alert_id=A.alert_id " +
          		" and IARD.is_deleted='N' and A.is_deleted='N' and A.govt_department_id=:govtDeptId and A.alert_id=aaotn.alert_id " +
          		" and A.alert_feedback_status_id !=2  ");
         
          if(fromDate !=null && toDate !=null){
            str.append(" and date(aaotn.updated_time) between :fromDate and :toDate ");
          }
          
          if(searchType != null && searchType.equalsIgnoreCase("dayWise")){
            str.append(" group by date(aaotn.updated_time),IARD.rws_ivr_status ");  
          }else if(searchType != null && searchType.equalsIgnoreCase("monthWise")){
            str.append(" group by Month(aaotn.updated_time),year(aaotn.updated_time),IARD.rws_ivr_status " +
            		"order by Month(aaotn.updated_time),year(aaotn.updated_time) ");
          }
          
          if(searchType != null && searchType.equalsIgnoreCase("dayWise")){
        	  query = getSession().createSQLQuery(str.toString())
        			  .addScalar("status",Hibernate.STRING)
        			  .addScalar("count",Hibernate.LONG)
        			  .addScalar("date",Hibernate.STRING);
          }else if(searchType != null && searchType.equalsIgnoreCase("monthWise")){
            	 query = getSession().createSQLQuery(str.toString()) 
            		  .addScalar("status",Hibernate.STRING)
           			  .addScalar("count",Hibernate.LONG)
           			  .addScalar("month",Hibernate.STRING)
           			  .addScalar("year",Hibernate.STRING);
          }
         
          query.setParameter("govtDeptId",49l);
          
          if(fromDate !=null && toDate !=null){
            query.setParameter("fromDate",fromDate);
            query.setParameter("toDate",toDate);
          }
        return  query.list();
      }
	/*public List<Object[]> getJalavaniIvrRespondantsGraphDetailsInfo(Date fromDate,Date toDate,String searchType){
        StringBuilder str = new StringBuilder();
          if(searchType != null && searchType.equalsIgnoreCase("dayWise")){
          //TypeId-0,Type-1,status-2,count-3,date-4
        	  str.append("select model.rwsIvrTypeId,model.rwsIvrType.rwsIvrName," +
        	  		" model.ivrSatisfiedStatus,count(distinct model1.ivrRespondentId),date(model.alert.updatedTime) ");
        	  
          }else if(searchType != null && searchType.equalsIgnoreCase("monthWise")){
        		
        	  str.append("select model.rwsIvrTypeId,model.rwsIvrType.rwsIvrName," +
        	  		" model.ivrSatisfiedStatus,count(distinct model1.ivrRespondentId), " +
        	  		" month(model.alert.updatedTime),year(model.alert.updatedTime) ");
          }
          
          str.append(" from RwsIvrAlertDetails model,RwsIvrAlertDetailsRespondent model1" +
          		" where model.rwsIvrAlertDetailsId = model1.rwsIvrAlertDetailsId and model.alert.isDeleted='N'" +
          		" and model1.isDeleted='N' " +
          		" and model.isDeleted='N' and model.rwsIvrType.isDeleted='N' " +
          		" and model.alert.alertFeedbackStatus.isDeleted='N' " +
          		" and model.alert.govtDepartmentId =:govtDeptId ");
         
          if(fromDate !=null && toDate !=null){
            str.append(" and date(model.alert.updatedTime) between :fromDate and :toDate ");
          }
          
          if(searchType != null && searchType.equalsIgnoreCase("dayWise")){
            str.append(" group by date(model.alert.updatedTime),model.rwsIvrTypeId");  
          }else if(searchType != null && searchType.equalsIgnoreCase("monthWise")){
            str.append(" group by month(model.alert.updatedTime),year(model.alert.updatedTime),model.rwsIvrTypeId order by month(model.alert.updatedTime),year(model.alert.updatedTime) ");
          }
        Query query = getSession().createQuery(str.toString());
          query.setParameter("govtDeptId",49l);
          
          if(fromDate !=null && toDate !=null){
            query.setParameter("fromDate",fromDate);
            query.setParameter("toDate",toDate);
          }
        return  query.list();
      }*/
}
