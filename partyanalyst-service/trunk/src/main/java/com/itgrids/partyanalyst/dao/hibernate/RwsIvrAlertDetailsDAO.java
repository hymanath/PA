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

	
	/*@SuppressWarnings("unchecked")
	public List<Object[]> getJalavaniIvrDetailsSummary(Date fromDate,Date toDate,String searchType){
        StringBuilder str = new StringBuilder();
        if(searchType !=null && searchType.equalsIgnoreCase("overall")){
        	//0-feedbackStatusId,problemtypeId-1,satisifiedStatus-2,count-3
        	str.append(" select model.alert.alertFeedbackStatusId,model.rwsIvrTypeId," +
            		" model.ivrSatisfiedStatus,count(model.alertId) ");
        	
        }else if(searchType !=null && searchType.equalsIgnoreCase("locationWise")){
        	//0-locationId,1-locationName,2-feedbackStatusId,problemtypeId-3,satisifiedStatus-4,count-5
        	str.append(" select model.alert.userAddress.district.districtId,model.alert.userAddress.district.districtName," +
        			" model.alert.alertFeedbackStatusId,model.rwsIvrTypeId," +
            		" model.ivrSatisfiedStatus,count(model.alertId) ");
        }
        
        if(fromDate !=null && toDate !=null){
        	str.append(" and date(model.insertedTime) between :fromDate and :toDate ");
        }
        str.append(" from RwsIvrAlertDetails model where model.isDeleted ='N' and model.alert.isDeleted ='N' and " +
        		" model.alert.govtDepartmentId =:govtDeptId ");
        if(searchType !=null && searchType.equalsIgnoreCase("overall")){
        	
        	str.append(" group by model.alert.alertFeedbackStatusId,model.ivrSatisfiedStatus,model.rwsIvrTypeId ");
        	
        }else if(searchType !=null && searchType.equalsIgnoreCase("locationWise")){
        	str.append(" group by model.Alert.userAddress.district.districtId,model.alert.alertFeedbackStatusId," +
        			" model.ivrSatisfiedStatus,model.rwsIvrTypeId ");
        }
       
        Query query = getSession().createQuery(str.toString());
          query.setParameter("govtDeptId",49l);
          
          if(fromDate !=null && toDate !=null){
            query.setParameter("fromDate",fromDate);
            query.setParameter("toDate",toDate);
          }
        return  query.list();
      }*/
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getJalavaniIvrDetailsSummary(Date fromDate,Date toDate){
        StringBuilder str = new StringBuilder();
       /* if(searchType !=null && searchType.equalsIgnoreCase("overall")){
        	//0-feedbackStatusId,problemtypeId-1,satisifiedStatus-2,count-3
        	str.append(" select model.alert.alertFeedbackStatusId,model.rwsIvrTypeId," +
            		" model.ivrSatisfiedStatus,count(model.alertId) ");
        	
        }else if(searchType !=null && searchType.equalsIgnoreCase("locationWise")){*/
        	//0-locationId,1-locationName,2-feedbackStatusId,problemtypeId-3,satisifiedStatus-4,count-5
        	str.append(" select model.alert.userAddress.district.districtId,model.alert.userAddress.district.districtName," +
        			" model.alert.alertFeedbackStatusId,model.rwsIvrTypeId," +
            		" model.ivrSatisfiedStatus,count(model.alertId),model.rwsIvrType.rwsIvrName ");
       // }
        
        if(fromDate !=null && toDate !=null){
        	str.append(" and date(model.insertedTime) between :fromDate and :toDate ");
        }
        str.append(" from RwsIvrAlertDetails model where model.isDeleted ='N' and model.alert.isDeleted ='N' and " +
        		" model.alert.govtDepartmentId =:govtDeptId and model.rwsIvrType.isDeleted ='N' ");
       /* if(searchType !=null && searchType.equalsIgnoreCase("overall")){
        	
        	str.append(" group by model.alert.alertFeedbackStatusId,model.ivrSatisfiedStatus,model.rwsIvrTypeId ");
        	
        }else if(searchType !=null && searchType.equalsIgnoreCase("locationWise")){
        	*/str.append(" group by model.Alert.userAddress.district.districtId,model.alert.alertFeedbackStatusId," +
        			" model.ivrSatisfiedStatus,model.rwsIvrTypeId ");
      //  }
       
        Query query = getSession().createQuery(str.toString());
          query.setParameter("govtDeptId",49l);
          
          if(fromDate !=null && toDate !=null){
            query.setParameter("fromDate",fromDate);
            query.setParameter("toDate",toDate);
          }
        return  query.list();
      }
}
