package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyCallStatus;

public interface ISurveyCallStatusDAO extends GenericDao<SurveyCallStatus,Long>{

	public Long getSurveyCallDtalsByVoterId(Long voterId);
}
