package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Survey;

public interface ISurveyDAO extends GenericDao<Survey, Long> {

	public List<Object[]> getAllSurveys();
	
}
