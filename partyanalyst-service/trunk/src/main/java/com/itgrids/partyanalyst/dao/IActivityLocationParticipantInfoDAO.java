package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityLocationParticipantInfo;

public interface IActivityLocationParticipantInfoDAO extends GenericDao<ActivityLocationParticipantInfo, Long> {
	
	public List<Object[]> getCoveredPeopleOfActivity(Long activityId,Long locationScope, Set<Long> locationValues);

	public void callProcedureofactivitySp();

}
