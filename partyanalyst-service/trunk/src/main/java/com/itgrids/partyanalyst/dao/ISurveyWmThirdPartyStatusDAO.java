package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyWmThirdPartyStatus;

public interface ISurveyWmThirdPartyStatusDAO extends GenericDao<SurveyWmThirdPartyStatus, Long>{
	public List<Object[]> getStatusTypes();
}
