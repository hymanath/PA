package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.IvrOptions;
import com.itgrids.partyanalyst.model.IvrSurveyEntity;



public interface IIvrSurveyEntityDAO extends GenericDao<IvrSurveyEntity, Long>{
	
	public List<Object[]> getSurveyListByEntityType(Long entityTypeId);
	public List<Object[]> getSurveyEntityTypeDetails(List<Long> surveyIds);
	public List<Object[]> getSurveyEntityTypeAndCountDetails(List<Long> surveyIds,Long respondantId);
	public List<Object[]> getSurveyAnswerDetailsForActivity(List<Long> surveyIds,Long respondentId,List<Long> entityValuesList,String searchType);
	public List<Object[]> getSurveyCountforEntityType(List<Long> surveyIds,Long ivrRespondentId);
}
