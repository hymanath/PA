package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IActivityQuestionnaireDAO;
import com.itgrids.partyanalyst.dao.IActivityQuestionnaireOptionDAO;
import com.itgrids.partyanalyst.dao.IActivityTabUserLocationDAO;
import com.itgrids.partyanalyst.dto.ActivityLocationVO;
import com.itgrids.partyanalyst.dto.ActivityMainVO;
import com.itgrids.partyanalyst.dto.ActivityScopeVO;
import com.itgrids.partyanalyst.dto.ActivityWSVO;

public class ActivityDAOHibernateTest extends BaseDaoTestCase{
	
	private IActivityQuestionnaireDAO			activityQuestionnaireDAO;
	private IActivityQuestionnaireOptionDAO		activityQuestionnaireOptionDAO;
	private IActivityTabUserLocationDAO			activityTabUserLocationDAO;
	
	

	
	public IActivityTabUserLocationDAO getActivityTabUserLocationDAO() {
		return activityTabUserLocationDAO;
	}
	public void setActivityTabUserLocationDAO(
			IActivityTabUserLocationDAO activityTabUserLocationDAO) {
		this.activityTabUserLocationDAO = activityTabUserLocationDAO;
	}
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
		
		/*List<Long> scopeIds = new ArrayList<Long>();
		scopeIds.add(5l);
		
		List<Object[]> objList = activityQuestionnaireOptionDAO.getQuestionnaireOfScope(scopeIds);
		ActivityWSVO finalVO = new ActivityWSVO(); 
		List<ActivityQuestionVO> 						 questsList 		= new ArrayList<ActivityQuestionVO>();
		List<ActivityOptionVO> 		 					 optnsList 			= new ArrayList<ActivityOptionVO>();
		List<ActivityQuestionnairVO>					 actvtyQustnr 		= new ArrayList<ActivityQuestionnairVO>();
		List<ActivityQuestionnairOptionVO> 				 actvtyQustnrOptn 	= new ArrayList<ActivityQuestionnairOptionVO>();
		
		List<Long> qlist =  new ArrayList<Long>();
		List<Long> oplist =  new ArrayList<Long>();
		if(objList != null && objList.size() > 0){
			for(Object[] obj:objList){
				if(!qlist.contains(Long.valueOf(obj[0].toString()))){
					ActivityQuestionVO qvo = new ActivityQuestionVO();
					qvo.setQuestionId(obj[0]!=null?Long.valueOf(obj[0].toString()):null);
					qvo.setQuestion(obj[1]!=null?obj[1].toString():"");
					qlist.add(obj[0]!=null?Long.valueOf(obj[0].toString()):null);
					questsList.add(qvo);
				}
				if(!optnsList.contains(Long.valueOf(obj[0].toString()))){
					ActivityOptionVO optnVO = new ActivityOptionVO();
					optnVO.setOptionId(obj[5]!=null?Long.valueOf(obj[5].toString()):null);
					optnVO.setOption(obj[6]!=null?obj[6].toString():"");
					oplist.add(obj[5]!=null?Long.valueOf(obj[5].toString()):null);
					optnsList.add(optnVO);
				}
				
				ActivityQuestionnairVO	actvtyQustnrVO 			= new ActivityQuestionnairVO();
				actvtyQustnrVO.setQuestionId(obj[0]!=null?Long.valueOf(obj[0].toString()):null); 
				actvtyQustnrVO.setOptionTypeId(obj[3]!=null?Long.valueOf(obj[3].toString()):null);
				actvtyQustnrVO.setOrderNo(obj[2]!=null?Long.valueOf(obj[2].toString()):null);
				actvtyQustnrVO.setParentQuestionnairId(obj[12]!=null?Long.valueOf(obj[12].toString()):null);
				actvtyQustnrVO.setRespondentTypeId(obj[8]!=null?Long.valueOf(obj[8].toString()):null);
				actvtyQustnrVO.setQuestionnairId(obj[11]!=null?Long.valueOf(obj[11].toString()):null);
				actvtyQustnr.add(actvtyQustnrVO);
				
				ActivityQuestionnairOptionVO actvtyQustnrOptnVO = new ActivityQuestionnairOptionVO();
				actvtyQustnrOptnVO.setActivityQuestionnairId(obj[11]!=null?Long.valueOf(obj[11].toString()):null);
				actvtyQustnrOptnVO.setOptionId(obj[5]!=null?Long.valueOf(obj[5].toString()):null);
				actvtyQustnrOptnVO.setOrderNo(obj[7]!=null?Long.valueOf(obj[7].toString()):null);
				actvtyQustnrOptn.add(actvtyQustnrOptnVO);
			}
		}
		finalVO.setQuestionList(questsList);
		finalVO.setOptionsList(optnsList);
		finalVO.setQuestnairList(actvtyQustnr);
		finalVO.setQuestnairOptnsList(actvtyQustnrOptn);
		
		System.out.println("sasi");*/
		
		ActivityWSVO finalVO = new ActivityWSVO();
		List<Object[]> list = activityTabUserLocationDAO.getUserActivityDetailsByUserId(1l);
		Set<Long> scpIds = new HashSet<Long>();
		List<Long> actvtyIds = new ArrayList<Long>();
		List<ActivityMainVO> activites = new ArrayList<ActivityMainVO>();
		List<ActivityScopeVO> actvtyScpes = new ArrayList<ActivityScopeVO>();
		List<ActivityLocationVO> actvtyLctns = new ArrayList<ActivityLocationVO>();
		
		if(list != null && list.size() > 0){
			for (Object[] obj : list) {
					Long scopeId = (Long) (obj[2] != null ? obj[2]:0l);
					scpIds.add(scopeId);
					
					if(!actvtyIds.contains(Long.valueOf(obj[3].toString()))){
						ActivityMainVO actvty = new ActivityMainVO();
						actvty.setActivityId(obj[3] != null ? Long.valueOf(obj[3].toString()):null);
						actvty.setActivityName(obj[4] != null ? obj[4].toString():"");
						actvtyIds.add(obj[3] != null ? Long.valueOf(obj[3].toString()):null);
						activites.add(actvty);
					}
					
					
					ActivityScopeVO actvty = new ActivityScopeVO();
					actvty.setActivityId(obj[3] != null ? Long.valueOf(obj[3].toString()):null);
					actvty.setActivityScopeId(obj[2] != null ? Long.valueOf(obj[2].toString()):null);
					actvty.setActivityLevelId(obj[8] != null ? Long.valueOf(obj[8].toString()):null);
					actvty.setStartDate(obj[9] != null ? obj[9].toString():"");
					actvty.setEndDate(obj[10] != null ? obj[10].toString():"");
					actvtyScpes.add(actvty);
					
					ActivityLocationVO actvtyLctn = new ActivityLocationVO();
					actvtyLctn.setActivityScopeId(obj[2] != null ? Long.valueOf(obj[2].toString()):null);
					actvtyLctn.setLocationLevel(obj[5] != null ? Long.valueOf(obj[5].toString()):null);
					actvtyLctn.setLocationValue(obj[6] != null ? Long.valueOf(obj[6].toString()):null);
					actvtyLctn.setActivityLocationInfoId(obj[11] != null ? Long.valueOf(obj[11].toString()):null);
					actvtyLctn.setPlannedStartDate(obj[12] != null ? obj[12].toString():"");
					actvtyLctn.setPlannedEndDate(obj[14] != null ? obj[14].toString():"");
					actvtyLctn.setConductedStartDate(obj[13] != null ? obj[13].toString():"");
					actvtyLctn.setConductedEndDate(obj[15] != null ? obj[15].toString():"");
					actvtyLctns.add(actvtyLctn);
					
			}
		}
		
		finalVO.setActivities(activites);
		finalVO.setActivityScopeList(actvtyScpes);
		finalVO.setActivityLocationsList(actvtyLctns);
		
		System.out.println("sasi");
	}
	
	
}
