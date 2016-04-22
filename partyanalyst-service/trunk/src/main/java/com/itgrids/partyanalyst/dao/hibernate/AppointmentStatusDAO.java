package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentStatusDAO;
import com.itgrids.partyanalyst.model.AppointmentStatus;

public class AppointmentStatusDAO extends GenericDaoHibernate<AppointmentStatus, Long> implements IAppointmentStatusDAO {

	public AppointmentStatusDAO() {
		super(AppointmentStatus.class);
	}
	public List<Object[]> getAppointmentStatusList(){
		Query query=getSession().createQuery(" select model.appointmentStatusId,model.status from AppointmentStatus model order by model.appointmentStatusId");
		return query.list();
	}
	
	public List<Object[]> getAllAppointmentStatus(){
	    Query query=getSession().createQuery(" select model.appointmentStatusId,model.status from AppointmentStatus model order by model.orderNo");
	    return query.list();
   }
	  
   public List<String> getAllStatus(){
    Query query=getSession().createQuery(" select model.status from AppointmentStatus model order by model.orderNo");
    return query.list();
  }
   
   public List<Object[]> getStatusDetailsByIds(List<Long> statusIds){
	   
	    Query query=getSession().createQuery(" select model.appointmentStatusId,model.status " +
	    		"from AppointmentStatus model " +
	    		"where model.appointmentStatusId in (:statusIds) order by model.orderNo");
	    
	    query.setParameterList("statusIds",statusIds );
	    return query.list();
  }
   
 }
