package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.IvrOptions;
import com.itgrids.partyanalyst.model.IvrSurveyEntity;



public interface IIvrSurveyEntityDAO extends GenericDao<IvrSurveyEntity, Long>{
	
	public List<Object[]> getSurveyEntityTypeDetails(List<Long> surveyIds);
	public List<Object[]> getSurveyEntityTypeAndCountDetails(List<Long> surveyIds,Long respondantId);
}
