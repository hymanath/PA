package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateDesignationDAO;
import com.itgrids.partyanalyst.model.AppointmentCandidateDesignation;

public class AppointmentCandidateDesignationDAO extends GenericDaoHibernate<AppointmentCandidateDesignation, Long> implements IAppointmentCandidateDesignationDAO {

	public AppointmentCandidateDesignationDAO( ) {
		super(AppointmentCandidateDesignation.class);
	}

}
