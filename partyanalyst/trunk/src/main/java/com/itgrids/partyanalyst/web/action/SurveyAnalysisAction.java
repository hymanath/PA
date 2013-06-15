package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ISurveyAnalysisService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SurveyAnalysisAction extends ActionSupport implements ServletRequestAware, ServletResponseAware,ServletContextAware{
	
	 private static final Logger LOG = Logger.getLogger(SurveyAnalysisAction.class);
     private ISurveyAnalysisService surveyAnalysisService;
     private ResultStatus resultStatus;
     private String task;
     private JSONObject jObj;
     private HttpSession session;
 	 private HttpServletRequest request;
 	 private HttpServletResponse response;; 
 	 private ServletContext context;
 	 private List<SelectOptionVO>  questionType;
 	 private List<QuestionsOptionsVO> questionsOptionsList;
 	 
	 

	public List<QuestionsOptionsVO> getQuestionsOptionsList() {
		return questionsOptionsList;
	}

	public void setQuestionsOptionsList(
			List<QuestionsOptionsVO> questionsOptionsList) {
		this.questionsOptionsList = questionsOptionsList;
	}

	public ISurveyAnalysisService getSurveyAnalysisService() {
		return surveyAnalysisService;
	}
		
	public void setSurveyAnalysisService(
			ISurveyAnalysisService surveyAnalysisService) {
		this.surveyAnalysisService = surveyAnalysisService;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public List<SelectOptionVO> getQuestionType() {
		return questionType;
	}

	public void setQuestionType(List<SelectOptionVO> questionType) {
		this.questionType = questionType;
	}

	public String execute(){
		 System.out.println("In");
		 return Action.SUCCESS;
	 }
	
	public String ajaxHandler()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
		 return ERROR;
		
		Long userId = user.getRegistrationID();
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
			LOG.error("Exception Occured in ");
		}
		if(jObj.getString("task").equalsIgnoreCase("saveSurveyData"))
		{
			Long scopeVal = jObj.getLong("scopeVal");
			Long stateId = jObj.getLong("stateId");
			Long districtId = jObj.getLong("districtId");
			Long constId = jObj.getLong("constituencyId");
			Long mandalId = jObj.getLong("mandalId");
			String name = jObj.getString("name");
			String desc = jObj.getString("description");
			String consType = jObj.getString("constituecyType");
			
			resultStatus = surveyAnalysisService.savesurveyDetails(name,desc,scopeVal,stateId,districtId,constId,mandalId,userId,consType);
		}
		return Action.SUCCESS;
	}

	
	 
	public String createNewQuestion(){
		try{
			LOG.debug("Entered into the getQuestionOptionTypes() method in SurveyAnalysisAction");
			questionType = surveyAnalysisService.getOptionTypes();
			questionType.add(0, new SelectOptionVO(0L,"Select"));
			}catch(Exception e){
				LOG.error("Entered into the getQuestionOptionTypes() method in SurveyAnalysisAction");	
			}
		return Action.SUCCESS;
	}
	
	public String saveSurveyQuestion()
	{
		try{
		jObj = new JSONObject(getTask());
		if(jObj.getString("task").equalsIgnoreCase("saveQuestion"))
		{
			JSONArray mainOptionsArray = jObj.getJSONArray("mainOptionsArray");
			QuestionsOptionsVO questionsOptionsVO = new QuestionsOptionsVO();
			String question = jObj.getString("question") ;
			String questionType = jObj.getString("questionType");
			Boolean showRemark = jObj.getBoolean("showRemark");
			List<OptionVO>  optionVOs = new ArrayList<OptionVO>(); 
			
			List<QuestionsOptionsVO> questionsOptionsList = new ArrayList<QuestionsOptionsVO>();
			for(int i=0;i<mainOptionsArray.length();i++)
			{
				JSONObject jSONObject= mainOptionsArray.getJSONObject(i);
			
				String option =jSONObject.getString("option"); 
				Boolean hasRemark = jSONObject.getBoolean("showRemark");
				Boolean hasSubQuestion = jSONObject.getBoolean("hasSubQuestion");
				OptionVO optionVO = new OptionVO();
				optionVO.setOption(option);
				optionVO.setHasRemark(hasRemark);
				
				if(hasSubQuestion == true)
				{
				JSONArray subOptionsArray = jSONObject.getJSONArray("subOptions");	
				String mainOptionquestionType = jSONObject.getString("subquestionType");
				String mainOptionquestion = jSONObject.getString("subquestion");
				Boolean mainOptionhasRemark =jSONObject.getBoolean("showRemark");
				optionVO.setHasSubQuestion(hasSubQuestion);
				optionVO.setSubquestion(mainOptionquestion);
				optionVO.setSubquestionType(mainOptionquestionType);
				optionVO.setHasRemark(mainOptionhasRemark);
				List<OptionVO>  suboptionVOs = new ArrayList<OptionVO>(); 
				for(int j=0;j<subOptionsArray.length();j++)
				{
					JSONObject jsonObject1 = subOptionsArray.getJSONObject(j);
					String suboption =jsonObject1.getString("option"); 
					Boolean subOptionhasRemark =jsonObject1.getBoolean("showRemark");
					OptionVO suboptionVO = new OptionVO();
					suboptionVO.setOption(suboption);
					suboptionVO.setHasRemark(subOptionhasRemark);
					suboptionVOs.add(suboptionVO);
					optionVO.setSubOptionList(suboptionVOs);
				}
				
				
				}
				optionVOs.add(optionVO);
			}
			questionsOptionsVO.setHasRemark(showRemark);
			questionsOptionsVO.setQuestion(question);
			questionsOptionsVO.setOptions(optionVOs);
			questionsOptionsVO.setQuestionType(questionType);
			questionsOptionsList.add(questionsOptionsVO);
			resultStatus = surveyAnalysisService.saveQuestion(questionsOptionsList);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("saveQuestionForMultipleText"))
		{
			JSONArray mainOptionsArray = jObj.getJSONArray("mainOptionsArray");
			String question = jObj.getString("question") ;
			String questionType = jObj.getString("questionType");
			Boolean showRemark = jObj.getBoolean("showRemark");
			QuestionsOptionsVO questionsOptionsVO = new QuestionsOptionsVO();
			List<OptionVO>  optionVOs = new ArrayList<OptionVO>(); 
			List<QuestionsOptionsVO> questionsOptionsList = new ArrayList<QuestionsOptionsVO>();
			for(int i=0;i<mainOptionsArray.length();i++)
			{
				JSONObject jSONObject= mainOptionsArray.getJSONObject(i);
				JSONArray subQuestionsArray = jSONObject.getJSONArray("subQuestions");	
				String option =jSONObject.getString("option"); 
				OptionVO optionVO = new OptionVO();
				optionVO.setOption(option);
				if(subQuestionsArray != null && subQuestionsArray.length() > 0)
				{
				List<OptionVO> suboptionVOs = new ArrayList<OptionVO>(); 
				for(int j=0;j<subQuestionsArray.length();j++)
				{
					JSONObject jsonObject1 = subQuestionsArray.getJSONObject(j);
					String mainOptionsubquestion = jsonObject1.getString("subquestion");
					OptionVO suboptionVO = new OptionVO();
					suboptionVO.setSubquestion(mainOptionsubquestion);
					suboptionVOs.add(suboptionVO);
					optionVO.setSubOptionList(suboptionVOs);
				}
				
				}
				optionVOs.add(optionVO);
			}
			questionsOptionsVO.setOptions(optionVOs);
			questionsOptionsVO.setQuestion(question);
			questionsOptionsVO.setHasRemark(showRemark);
			questionsOptionsVO.setQuestionType(questionType);
			questionsOptionsList.add(questionsOptionsVO);
			resultStatus = surveyAnalysisService.saveQuestionForMultipleText(questionsOptionsList);	
		}
		}
		catch (Exception e) {
		e.printStackTrace();	
		}
		return Action.SUCCESS;
	}
	
}
