package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentStatusFlowDAO;
import com.itgrids.partyanalyst.model.AppointmentStatusFlow;

public class AppointmentStatusFlowDAO extends GenericDaoHibernate<AppointmentStatusFlow, Long> implements IAppointmentStatusFlowDAO {

	public AppointmentStatusFlowDAO() {
		super(AppointmentStatusFlow.class);
	}

	public List<Object[]> getUpdatedStatusForaAppointment(Long userTypeId,Long currentStatusId){
		/*select   model.to_status_id ,model1.status
			from    appointment_status_flow model,appointment_status model1,appointment_user_type_access_status model2
			where   model.to_status_id = model1.appointment_status_id 
			        and model2.appointment_status_id =  model.to_status_id
			        and model.from_status_id = 1   and model2.appointment_user_type_id = 1
			order by model1.order_no;*/
		Query query = getSession().createQuery("select model.toStatus.appointmentStatusId," +
									" model1.status" +
									" from AppointmentStatusFlow model,AppointmentStatus model1,AppointmentUserTypeAccessStatus model2" +
									" where model.toStatus.appointmentStatusId = model1.appointmentStatusId" +
									" and model2.appointmentStatus.appointmentStatusId = model.toStatus.appointmentStatusId" +
									" and model.fromStatus.appointmentStatusId = :currentStatusId" +
									" and model2.appointmentUserType.appointmentUserTypeId = :userTypeId" +
									" order by model1.orderNo");
		query.setParameter("userTypeId", userTypeId);
		query.setParameter("currentStatusId", currentStatusId);
		
		return query.list();
	}
}
