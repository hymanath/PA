package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyCompletedLocationsDetails;

public interface ISurveyCompletedLocationsDetailsDAO extends GenericDao<SurveyCompletedLocationsDetails,Long>{
	
	public List<Long> getSurveyCompletedLocationDetails(Long scopeId);
}
