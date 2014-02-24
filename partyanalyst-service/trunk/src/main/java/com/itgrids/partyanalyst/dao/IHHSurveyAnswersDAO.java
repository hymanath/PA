
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HHSurveyAnswers;
import com.itgrids.partyanalyst.model.HHSurveyQuestion;

public interface IHHSurveyAnswersDAO extends GenericDao<HHSurveyAnswers, Long>{
	public List<HHSurveyAnswers> getSurveyAnswersByHouseHoldId(Long houseHoldId);
	public int deleteAllPreviousAnswersByHouseHoldsId(Long houseHoldsId);
	
}