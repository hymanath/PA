package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeDAO;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInvitee;

public class TrainingCampScheduleInviteeDAO extends GenericDaoHibernate<TrainingCampScheduleInvitee, Long> implements ITrainingCampScheduleInviteeDAO{

	public TrainingCampScheduleInviteeDAO() {
		super(TrainingCampScheduleInvitee.class);
		// TODO Auto-generated constructor stub
	}

}
