package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyUserTabAssign;

public interface ISurveyUserTabAssignDAO  extends GenericDao<SurveyUserTabAssign, Long>
{
	public List<Object[]> getSurveyTabsBySurveyUserId(Long leaderId);
	
	public List<Object[]> getSurveyTabsBySurveyUserIdsList(List<Long> surveyUserIdsList);
	
}
