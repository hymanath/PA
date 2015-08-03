package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITrainingCampScheduleInviteeTrackDAO;
import com.itgrids.partyanalyst.model.TrainingCampScheduleInviteeTrack;

public class TrainingCampScheduleInviteeTrackDAO extends GenericDaoHibernate<TrainingCampScheduleInviteeTrack, Long> implements ITrainingCampScheduleInviteeTrackDAO{

	public TrainingCampScheduleInviteeTrackDAO() {
		super(TrainingCampScheduleInviteeTrack.class);
	}

}
