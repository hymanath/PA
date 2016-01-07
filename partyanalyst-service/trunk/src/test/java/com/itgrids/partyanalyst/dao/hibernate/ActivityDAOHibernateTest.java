package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IActivityDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionnaireDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionnaireOptionDAO;
import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.ActivityWSVO;
import com.itgrids.partyanalyst.model.ActivityQuestionnaire;

public class ActivityDAOHibernateTest extends BaseDaoTestCase{
	
	private IActivityQuestionnaireDAO			activityQuestionnaireDAO;
	private IActivityQuestionnaireOptionDAO		activityQuestionnaireOptionDAO;

	public IActivityQuestionnaireDAO getActivityQuestionnaireDAO() {
		return activityQuestionnaireDAO;
	}
	public void setActivityQuestionnaireDAO(
			IActivityQuestionnaireDAO activityQuestionnaireDAO) {
		this.activityQuestionnaireDAO = activityQuestionnaireDAO;
	}
	
	public IActivityQuestionnaireOptionDAO getActivityQuestionnaireOptionDAO() {
		return activityQuestionnaireOptionDAO;
	}
	public void setActivityQuestionnaireOptionDAO(
			IActivityQuestionnaireOptionDAO activityQuestionnaireOptionDAO) {
		this.activityQuestionnaireOptionDAO = activityQuestionnaireOptionDAO;
	}
	public void test(){
		
		Long scopeId = 5l;
		List<Object[]> objList = activityQuestionnaireOptionDAO.getQuestionnaireOfScope(scopeId);
		ActivityWSVO finalVO = new ActivityWSVO(); 
		if(objList != null && objList.size() > 0){
			for (Object[] objects : objList) {
				ActivityWSVO mtchdVO = getMatchedActivityWSVO(finalVO.getAcitivityQuesList(),Long.valueOf(objects[6].toString()),"respondent");
				if(mtchdVO==null){
					mtchdVO = new ActivityWSVO();
					mtchdVO.setRespondentTypeId(Long.valueOf(objects[6].toString()));
					mtchdVO.setRespondentType(objects[7].toString());
					finalVO.getAcitivityQuesList().add(mtchdVO);
				}
				
				ActivityWSVO matchedVO = getMatchedActivityWSVO(mtchdVO.getQuestionList(),Long.valueOf(objects[0].toString()),"question");
				
				int number = mtchdVO.getQuestionList().size()+1;
				if(matchedVO == null){
					matchedVO = new ActivityWSVO();
					matchedVO.setQuestionId((Long)objects[0]);
					matchedVO.setQuestion(number+") "+objects[1].toString());
					matchedVO.setOptionTypeId((Long)objects[2]);
					matchedVO.setOptionType(objects[3].toString());
					mtchdVO.getQuestionList().add(matchedVO);
				}
				ActivityWSVO optionVO = new ActivityWSVO();
				optionVO.setOptionId((Long)objects[4]);
				optionVO.setOption(objects[5].toString());
				matchedVO.getOptionsList().add(optionVO);
			}
		}
		System.out.println("sasi");
	}
	
	public ActivityWSVO getMatchedActivityWSVO(List<ActivityWSVO> list,Long id, String fltr){
		if(id!=null && list!=null && !list.isEmpty()){
			if(!fltr.isEmpty() && fltr.equalsIgnoreCase("Respondent")){
				for(ActivityWSVO voIn : list){
					if(voIn.getRespondentTypeId().equals(id)){
						return voIn;
					}
				}
			}
			if(!fltr.isEmpty() && fltr.equalsIgnoreCase("Question")){
				for(ActivityWSVO voIn : list){
					if(voIn.getQuestionId().equals(id)){
						return voIn;
					}
				}
			}
		}
		return null;
	}
}
