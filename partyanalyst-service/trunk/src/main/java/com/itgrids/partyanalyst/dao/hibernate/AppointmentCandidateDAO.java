package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateDAO;
import com.itgrids.partyanalyst.model.AppointmentCandidate;

public class AppointmentCandidateDAO extends GenericDaoHibernate<AppointmentCandidate, Long> implements IAppointmentCandidateDAO {

	public AppointmentCandidateDAO() {
		super(AppointmentCandidate.class);
	}

}
