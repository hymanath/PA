package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityQuestionAnswer;

public interface IActivityQuestionAnswerDAO extends GenericDao<ActivityQuestionAnswer, Long>{

	public List<Object[]> getActivityQuestionnairesCountsByLocation(SearchAttributeVO searchAttributeVO);
	public List<Object[]> getActivityQuestionnairesAttributeCountsByLocation(SearchAttributeVO searchAttributeVO,Long optionId);
	public List<Object[]> getActivityQuestionnairesCountsByDayWise(SearchAttributeVO searchAttributeVO);
	public List<Object[]> getActivityQuestionnairesAttributeCountsByDayWise(SearchAttributeVO searchAttributeVO,Long optionId);
}
