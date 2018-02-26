package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TrainingCampInviteeConstituency;

public interface ITrainingCampInviteeConstituencyDAO extends GenericDao<TrainingCampInviteeConstituency, Long> {
	public List<Long> getExtraConstituencyList(List<Long> trainingCompProgramIds);
}
