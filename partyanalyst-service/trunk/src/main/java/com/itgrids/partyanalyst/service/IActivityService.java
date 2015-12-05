package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.ActivityVO;

public interface IActivityService {
	
	public Map<Long,ActivityVO> getAttributeListByQuestionnaireList(List<Long> questionnairesList);

}
