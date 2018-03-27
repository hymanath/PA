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
}
