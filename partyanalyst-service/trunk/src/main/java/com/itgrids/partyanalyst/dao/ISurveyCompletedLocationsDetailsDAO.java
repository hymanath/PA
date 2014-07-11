package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyCompletedLocationsDetails;

public interface ISurveyCompletedLocationsDetailsDAO extends GenericDao<SurveyCompletedLocationsDetails,Long>{
	
	public List<Object[]> getSurveyCompletedLocationDetails(Long scopeId);
	public List<Object[]> getSurveyCompletedBoothsDetails(List<Long> boothIds);
	public List<Object[]> getSurveyCompletedConstituencyDetails();
	public void deleteBoothCompletionDataOfContituency(List<Long> boothIds);
	public void deleteConstituencyCompletionData();


}
