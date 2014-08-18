package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyConstituency;

public interface ISurveyConstituencyDAO extends GenericDao<SurveyConstituency, Long>{
	public List<Object[]> getSurveyConstituencies();
	public List<Object[]> getDistrictWiseSurveyConstituenciesCount();
}
