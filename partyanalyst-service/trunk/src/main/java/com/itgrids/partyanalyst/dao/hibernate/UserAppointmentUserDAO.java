package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserAppointmentUserDAO;
import com.itgrids.partyanalyst.model.UserAppointmentUser;

public class UserAppointmentUserDAO extends GenericDaoHibernate<UserAppointmentUser, Long> implements IUserAppointmentUserDAO{

	public UserAppointmentUserDAO() {
		super(UserAppointmentUser.class);
		
	}

	public List<Object[]> getAppointmentStatusByUserId(Long userId){
		
		Query query = getSession().createQuery("select model1.appointmentStatus.appointmentStatusId," +
									" model1.appointmentStatus.status," +
									" model.appointmentUserType.appointmentUserTypeId" +
									" from UserAppointmentUser model,AppointmentUserTypeAccessStatus model1" +
									" where model.appointmentUserType.appointmentUserTypeId = model1.appointmentUserType.appointmentUserTypeId" +
									" and model.user.userId = :userId");
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	public List<Long> getLoginUserAppointmentUserType(Long userId){
		
		Query query = getSession().createQuery("" +
				" select distinct model.appointmentUserTypeId " +
				" from   UserAppointmentUser model " +
				" where  model.user.userId = :userId " +
				" order by model.userAppointmentUserId desc ");
		query.setParameter("userId", userId);
		
		return query.list();
	}
}
