package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Surveyor;

public interface ISurveyorDAO extends GenericDao<Surveyor , Long>{

	public List<Surveyor> getSurveyorDetails();
	public List<Surveyor> getSurveyDataBySurveyorId(Long surveyorId);
}
