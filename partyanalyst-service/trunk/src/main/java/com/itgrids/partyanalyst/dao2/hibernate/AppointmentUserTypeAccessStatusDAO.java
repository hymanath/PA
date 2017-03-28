package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentUserTypeAccessStatusDAO;
import com.itgrids.partyanalyst.model.AppointmentUserTypeAccessStatus;

public class AppointmentUserTypeAccessStatusDAO extends GenericDaoHibernate<AppointmentUserTypeAccessStatus, Long> implements IAppointmentUserTypeAccessStatusDAO {

	public AppointmentUserTypeAccessStatusDAO() {
		super(AppointmentUserTypeAccessStatus.class);
	}

	/*public List<Object[]> getAppointmentStatusByUserType(Long appointmentUserTypeId){
		
		Query query = getSession().createQuery("select model.appointmentStatus.appointmentStatusId," +
									" model.appointmentStatus.status" +
									" from AppointmentUserTypeAccessStatus model" +
									" where model.appointmentUserType.appointmentUserTypeId = :appointmentUserTypeId" +
									" order by model.appointmentStatus.orderNo asc");
		query.setParameter("appointmentUserTypeId", appointmentUserTypeId);
		
		return query.list();
	}*/
}
