package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyCompletedConstituency;

public interface ISurveyCompletedConstituencyDAO extends GenericDao<SurveyCompletedConstituency, Long>{
	public List<Object[]> getSurveyCompletedConstituencyDetails();
	public List<Object[]> getConstituencyCompletionStatusByConstituencyId(Long constituencyId);
}
