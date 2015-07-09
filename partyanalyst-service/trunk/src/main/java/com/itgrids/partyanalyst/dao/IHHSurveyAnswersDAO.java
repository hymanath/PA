
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HHSurveyAnswers;
import com.itgrids.partyanalyst.model.HHSurveyQuestion;

public interface IHHSurveyAnswersDAO extends GenericDao<HHSurveyAnswers, Long>{
	public List<HHSurveyAnswers> getSurveyAnswersByHouseHoldId(Long houseHoldId);
	public int deleteAllPreviousAnswersByHouseHoldsId(Long houseHoldsId);
	
	public List<Object[]> getQuestionWiseSummary(Long questionId,Long constiutencyId);
	public List<Object[]> getQuestionWiseSummaryCount(Long questionId,Long constituencyId);
	public List<Object[]> getQuestionWiseSummaryCountByPanchayat(Long questionId,Long constituencyId);
	public List<Object[]> getHouseHoldsOfPanchayatWithOption(Long optionId,Long panchayatId);
	
	public List<Object[]> getVoterAndNonVotersUnderOption(Long optionId,Long panchayatId);
	public List<Object[]> getVoterAndNonVotersUnderOption1(Long optionId,Long panchayatId);
	public List<Object[]> getHouseHoldsOfPanchayatsWithOption(Long optionId,List<Long> panchayatIds);
	
}