package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.model.Registration;
import com.itgrids.partyanalyst.service.IHomePageQuestionsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.*;
public class HomePageQuestionsPostAction extends ActionSupport implements
		ServletRequestAware,ServletContextAware {

	
	private HttpServletRequest request;
	private ServletContext context;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	
	private String questionStartDate;
	private String questionEndDate;
	private String question;
	private String answers;
	
	
	private EntitlementsHelper entitlementsHelper;
	private HomePageQuestionsVO homePageQuestionsVO;
	List<QuestionsOptionsVO> answerOptionsVOs=new ArrayList<QuestionsOptionsVO>();
	private IHomePageQuestionsService homePageQuestionsService;
	private List<HomePageQuestionsVO> homePageQuestionVOs;	
	
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	private int resultStatus;
	
	public void setServletRequest(HttpServletRequest req) {
		request = req;

	}

	public void setServletContext(ServletContext context) {
		this.context = context;

	}
	public String getQuestionStartDate() {
		return questionStartDate;
	}

	public void setQuestionStartDate(String questionStartDate) {
		this.questionStartDate = questionStartDate;
	}

	public String getQuestionEndDate() {
		return questionEndDate;
	}

	public void setQuestionEndDate(String questionEndDate) {
		this.questionEndDate = questionEndDate;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public int getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(int resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	
	public String getAnswers() {
		return answers;
	}

	

	public void setAnswers(String answers) {
		this.answers = answers;
	}
	
	public List<QuestionsOptionsVO> getAnswerOptionsVOs() {
		return answerOptionsVOs;
	}

	public void setAnswerOptionsVOs(List<QuestionsOptionsVO> answerOptionsVOs) {
		this.answerOptionsVOs = answerOptionsVOs;
	}



	public IHomePageQuestionsService getHomePageQuestionsService() {
		return homePageQuestionsService;
	}

	public void setHomePageQuestionsService(
			IHomePageQuestionsService homePageQuestionsService) {
		this.homePageQuestionsService = homePageQuestionsService;
	}

	public HomePageQuestionsVO getHomePageQuestionsVO() {
		return homePageQuestionsVO;
	}

	public void setHomePageQuestionsVO(HomePageQuestionsVO homePageQuestionsVO) {
		this.homePageQuestionsVO = homePageQuestionsVO;
	}

	
	public void setHomePageQuestionVOs(List<HomePageQuestionsVO> homePageQuestionVOs) {
		this.homePageQuestionVOs = homePageQuestionVOs;
	}

	public List<HomePageQuestionsVO> getHomePageQuestionVOs() {
		return homePageQuestionVOs;
	}

	public String execute()throws Exception{
		
		
		session=request.getSession();
		RegistrationVO registration=(RegistrationVO)session.getAttribute("USER");
		
		homePageQuestionsVO=new HomePageQuestionsVO();
		homePageQuestionsVO.setQuestion(getQuestion());
		homePageQuestionsVO.setQuestionStartDate(sdf.parse(getQuestionStartDate()));
		homePageQuestionsVO.setQuestionEndDate(sdf.parse(getQuestionEndDate()));
		
		
		
		StringTokenizer stringTokenizer=new StringTokenizer(answers,"^");
		QuestionsOptionsVO questionsOptionsVO = new QuestionsOptionsVO();
		while(stringTokenizer.hasMoreTokens()){
		questionsOptionsVO=new QuestionsOptionsVO();
		questionsOptionsVO.setAnswerKey(stringTokenizer.nextToken());
		questionsOptionsVO.setAnswerValue(stringTokenizer.nextToken());
		answerOptionsVOs.add(questionsOptionsVO);
		}
		
		
		homePageQuestionsVO.setAnswerOptionsVOs(answerOptionsVOs);
		
		Boolean result=homePageQuestionsService.saveHomePageQuestions(homePageQuestionsVO,registration.getRegistrationID());
		if(result == false){	
	   		resultStatus = 1;
			return ERROR;
		}
		else
		{
		  resultStatus = 0;
		}
		
		
		return SUCCESS;
	}
	

	public String ajaxCallHandler(){
		
		try {
			jObj = new JSONObject(getTask());
			
			if((jObj.getString("task").equalsIgnoreCase("getQuestions"))){
				
				
				homePageQuestionVOs=homePageQuestionsService.getQuestionsForTheDay();
			}
		} 
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
		
	

}
