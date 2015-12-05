package com.itgrids.partyanalyst.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IActivityAttributeQuestionnaireInfoDAO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.service.IActivityService;

public class ActivityService implements IActivityService{

	private static final Logger   LOG = Logger.getLogger(ActivityService.class);
	
	private IActivityAttributeQuestionnaireInfoDAO activityAttributeQuestionnaireInfoDAO;
	
	
	public IActivityAttributeQuestionnaireInfoDAO getActivityAttributeQuestionnaireInfoDAO() {
		return activityAttributeQuestionnaireInfoDAO;
	}
	public void setActivityAttributeQuestionnaireInfoDAO(
			IActivityAttributeQuestionnaireInfoDAO activityAttributeQuestionnaireInfoDAO) {
		this.activityAttributeQuestionnaireInfoDAO = activityAttributeQuestionnaireInfoDAO;
	}


	public Map<Long,ActivityVO> getAttributeListByQuestionnaireList(List<Long> questionnairesList){
		
		Map<Long,ActivityVO> finalMap = new LinkedHashMap<Long, ActivityVO>();
		
		//0.questionnaireId,1.attributeId,2.attributeName
		List<Object[]> list = activityAttributeQuestionnaireInfoDAO.getAttributeListByQuestionnaires(questionnairesList);
		
		if(list != null && list.size() > 0){
			for (Object[] objects : list) {
				ActivityVO vo = new ActivityVO();
				
				Long questionnaireId = (Long) (objects[0] != null ? objects[0]:0l);
				vo.setId((Long) (objects[1] != null ? objects[1]:0l));
				vo.setName(objects[2] != null ? objects[2].toString():"");
				
				finalMap.put(questionnaireId, vo);
			}
		}
		
		return finalMap;
	}
}
