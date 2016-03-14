package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.IvrSurveyService;

public interface IIvrSurveyServiceDAO extends GenericDao<IvrSurveyService, Long>{

	public Object[] getSurveyServiceBySurveyId(Long ivrSurveyServiceId);
}
