package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateDesignationDAO;
import com.itgrids.partyanalyst.model.AppointmentCandidateDesignation;

public class AppointmentCandidateDesignationDAO extends GenericDaoHibernate<AppointmentCandidateDesignation, Long> implements IAppointmentCandidateDesignationDAO {

	public AppointmentCandidateDesignationDAO( ) {
		super(AppointmentCandidateDesignation.class);
	}
	public List<Object[]> getAppCandidateDesigList(){
		Query query=getSession().createQuery("select model.appointmentCandidateDesignationId, model.designation from AppointmentCandidateDesignation model");
		return query.list();
	}

}
