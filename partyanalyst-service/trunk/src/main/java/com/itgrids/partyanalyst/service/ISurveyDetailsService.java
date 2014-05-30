package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ISurveyDetailsService {

	public List<SelectOptionVO> getAllSurveys();
	public ResultStatus saveSurveyDetails(Long userId,Long surveyId);
	public Map<String,String> getSurveyDetailsByRegion(Long regionId);

}
