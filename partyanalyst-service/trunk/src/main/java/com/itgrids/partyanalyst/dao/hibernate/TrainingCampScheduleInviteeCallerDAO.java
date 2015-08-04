package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeCallerDAO;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInviteeCaller;

public class TrainingCampScheduleInviteeCallerDAO extends GenericDaoHibernate<TrainingCampScheduleInviteeCaller, Long> implements ITrainingCampScheduleInviteeCallerDAO{

	public TrainingCampScheduleInviteeCallerDAO() {
		super(TrainingCampScheduleInviteeCaller.class);
		// TODO Auto-generated constructor stub
	}

}
