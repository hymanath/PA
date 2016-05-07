package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.IvrOptions;
import com.itgrids.partyanalyst.model.IvrSurveyAnswer;

public interface IIvrSurveyAnswerDAO extends GenericDao<IvrSurveyAnswer, Long>{
	
	public List<Object[]> getIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(List<Long> surveyIds,Long respondentId);
	public List<Long> getIvrSurveyIdsByRespondantId(Long ivrRespondantId);
	public List<Object[]> getTotalIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(List<Long> surveyIds,Long respondentId);
	public List<Object[]> getUnAnsweredIvrSurveyAnswerInfoDetailsBySurveyListAndRespondentId(List<Long> surveyIds,Long respondentId);
	public List<Object[]> getIvrSurveyAnswerInfoForTdpCadre(Long ivrResondentId);
	public Long getCandateParticipatedSurveyCnt(Long ivrResondentId);
	public List<Object[]> getOptionsCountByQuestionIds(List<Long> surveyQuestionIds);
	public Long getIvrSurveyCountByDistrict(Long districtId);
	public List<Object[]> getOptionsCountByQuestionIdsForLocation(String locType,Long locationId);
}
