package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentManageUserDAO;
import com.itgrids.partyanalyst.model.AppointmentManageUser;
import com.itgrids.partyanalyst.model.AppointmentUser;

public class AppointmentManageUserDAO extends GenericDaoHibernate<AppointmentManageUser, Long> implements IAppointmentManageUserDAO{
	
	public AppointmentManageUserDAO() {
		super(AppointmentManageUser.class);
	}
	
	@Override
	public List<Object[]> getAppointmentUsersDtlsByUserId(Long userId) {
	   Query query=getSession().createQuery(" select model.appointmentUser.appointmenUserId,model.appointmentUser.name," +
	   		" model.appointmentUser.mobile,model.appointmentUser.uniqueIdPrefix  " +
	   		" from AppointmentManageUser model where model.user.userId=:userId");
	    query.setParameter("userId", userId);
		return query.list();
	}
	
	
}
