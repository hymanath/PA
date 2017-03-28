package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
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
	
	public List<Object[]> getApplicationContextWiseSatuses(){
		
		StringBuilder str = new StringBuilder();
		
		 str.append(" SELECT UT.appointment_user_type_id as aptUserTypeId,UT.user_type as userType," +
				"F.appointment_status_id as aptStatusId,F.status as status,T.appointment_status_id as aptToStatusId,T.status as toStatus " +
				" FROM " +
				" appointment_status_flow ASF,appointment_status F,appointment_status T,appointment_user_type_access_status UA,appointment_user_type UT " +
				" WHERE " +
				" UA.appointment_user_type_id = UT.appointment_user_type_id AND " +
				" UA.appointment_status_id = ASF.to_status_id AND " +
				" ASF.to_status_id = T.appointment_status_id AND " +
				" ASF.from_status_id = F.appointment_status_id " +
				" ORDER BY UT.appointment_user_type_id,F.appointment_status_id,T.appointment_status_id");
				
				Query query = getSession().createSQLQuery(str.toString())
				 .addScalar("aptUserTypeId",Hibernate.LONG)
				 .addScalar("userType",Hibernate.STRING)
				 .addScalar("aptStatusId",Hibernate.LONG)
				 .addScalar("status",Hibernate.STRING)
				 .addScalar("aptToStatusId",Hibernate.LONG)
				 .addScalar("toStatus",Hibernate.STRING);
				
		return query.list();
	}
}
