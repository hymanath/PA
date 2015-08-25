package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampScheduleInviteeTrack;

public interface ITrainingCampScheduleInviteeTrackDAO extends GenericDao<TrainingCampScheduleInviteeTrack, Long>{
	public List<Object[]> getScheduleConfirmationDetails(Long purposeId,Long userId);
}
