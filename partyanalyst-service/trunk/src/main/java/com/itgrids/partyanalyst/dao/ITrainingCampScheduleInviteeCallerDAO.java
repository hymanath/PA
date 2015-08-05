package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampScheduleInviteeCaller;

public interface ITrainingCampScheduleInviteeCallerDAO extends GenericDao<TrainingCampScheduleInviteeCaller, Long>{
	public List<Object[]> getScheduleWiseCallStatusCount(Long callerId,Long callPurposeId);
	public List<Object[]> getBatchWiseWiseCallStatusCount(Long callerId,Long callPurposeId);
}
