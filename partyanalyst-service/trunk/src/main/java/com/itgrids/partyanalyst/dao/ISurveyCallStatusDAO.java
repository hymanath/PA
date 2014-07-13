package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyCallStatus;

public interface ISurveyCallStatusDAO extends GenericDao<SurveyCallStatus,Long>{

	public Long getSurveyCallDtalsByVoterId(Long voterId);
	public List<Object[]> getStatusListForUser(List<Long> userIds,List<Long> boothIds,Long userTypeId);
}
