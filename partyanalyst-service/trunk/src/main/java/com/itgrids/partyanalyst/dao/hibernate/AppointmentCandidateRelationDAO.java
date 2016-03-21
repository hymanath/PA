package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateRelationDAO;
import com.itgrids.partyanalyst.model.AppointmentCandidateRelation;

public class AppointmentCandidateRelationDAO extends
		GenericDaoHibernate<AppointmentCandidateRelation, Long> implements
		IAppointmentCandidateRelationDAO {

	public AppointmentCandidateRelationDAO(){
		super(AppointmentCandidateRelation.class);
	}

}
