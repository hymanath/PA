package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityLocationParticipantDAO;
import com.itgrids.partyanalyst.model.ActivityLocationParticipant;

public class ActivityLocationParticipantDAO extends GenericDaoHibernate<ActivityLocationParticipant, Long> implements
		IActivityLocationParticipantDAO {

	public ActivityLocationParticipantDAO() {
		super(ActivityLocationParticipant.class);
	}

	

}
