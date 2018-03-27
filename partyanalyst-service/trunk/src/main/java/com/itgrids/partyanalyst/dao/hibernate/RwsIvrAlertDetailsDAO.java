package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
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
            		" model.ivrSatisfiedStatus,count(model.alertId),model.rwsIvrType.rwsIvrName ");
        
      
        str.append(" from RwsIvrAlertDetails model " +
        		" where model.isDeleted ='N' and model.alert.isDeleted ='N' and " +
        		" model.alert.govtDepartmentId =:govtDeptId and model.rwsIvrType.isDeleted ='N' and model.alert.alertFeedbackStatusId is not null");
        
	        if(fromDate !=null && toDate !=null){
	        	str.append(" and date(model.insertedTime) between :fromDate and :toDate ");
	        }
        
         str.append(" group by model.alert.userAddress.district.districtId,model.alert.alertFeedbackStatusId," +
        			" model.ivrSatisfiedStatus,model.rwsIvrTypeId ");
       
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
        	  		" count(model.alertId),date(model.alert.updatedTime) ");
        	  
          }else if(searchType != null && searchType.equalsIgnoreCase("monthWise")){
        		
        	  str.append("select  model.alert.alertFeedbackStatusId,model.rwsIvrTypeId," +
        	  		" model.ivrSatisfiedStatus, " +
        	  		" model.rwsIvrType.rwsIvrName,count(model.alertId), " +
        	  		" month(model.alert.updatedTime),year(model.alert.updatedTime) ");
          }
          
          str.append(" from RwsIvrAlertDetails model where model.alert.isDeleted='N' " +
          		" and model.isDeleted='N' and model.rwsIvrType.isDeleted='N' " +
          		" and model.alert.alertFeedbackStatus.isDeleted='N' " +
          		" and model.alert.govtDepartmentId =:govtDeptId ");
         
          if(fromDate !=null && toDate !=null){
            str.append(" and date(model.alert.updatedTime) between :fromDate and :toDate ");
          }
          
          if(searchType != null && searchType.equalsIgnoreCase("dayWise")){
            str.append(" group by model.alert.alertFeedbackStatusId,date(model.alert.updatedTime),model.rwsIvrTypeId");  
          }else if(searchType != null && searchType.equalsIgnoreCase("monthWise")){
            str.append(" group by model.alert.alertFeedbackStatusId,month(model.alert.updatedTime),year(model.alert.updatedTime),model.rwsIvrTypeId order by month(model.alert.updatedTime),year(model.alert.updatedTime) ");
          }
        Query query = getSession().createQuery(str.toString());
          query.setParameter("govtDeptId",49l);
          
          if(fromDate !=null && toDate !=null){
            query.setParameter("fromDate",fromDate);
            query.setParameter("toDate",toDate);
          }
        return  query.list();
      }
}
