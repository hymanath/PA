package com.itgrids.partyanalyst.web.action;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.jfree.util.Log;
import org.json.JSONObject;


import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IOpinionPollService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.OpinionPollVO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;


public class OpinionPollPostAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(OpinionPollPostAction.class);
	
	private HttpServletRequest request;
	private ServletContext context;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	
	private String title;
	private String description;
	private String pollStartDate;
	private String pollEndDate;
	private String question;
	
	private IOpinionPollService opinionPollService;
	
	private List<OptionVO> optionVO;
	private List<QuestionsOptionsVO> questionsOptionsVO;
	private OpinionPollVO opinionPollVO;
	private String options;
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	private int resultStatus;
	
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


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getPollStartDate() {
		return pollStartDate;
	}


	public void setPollStartDate(String pollStartDate) {
		this.pollStartDate = pollStartDate;
	}


	public String getPollEndDate() {
		return pollEndDate;
	}


	public void setPollEndDate(String pollEndDate) {
		this.pollEndDate = pollEndDate;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


    public IOpinionPollService getOpinionPollService() {
		return opinionPollService;
	}


	public void setOpinionPollService(IOpinionPollService opinionPollService) {
		this.opinionPollService = opinionPollService;
	}


	public List<OptionVO> getOptionVO() {
		return optionVO;
	}


	public void setOptionVO(List<OptionVO> optionVO) {
		this.optionVO = optionVO;
	}


	public List<QuestionsOptionsVO> getQuestionsOptionsVO() {
		return questionsOptionsVO;
	}


	public void setQuestionsOptionsVO(List<QuestionsOptionsVO> questionsOptionsVO) {
		this.questionsOptionsVO = questionsOptionsVO;
	}


	public OpinionPollVO getOpinionPollVO() {
		return opinionPollVO;
	}


	public void setOpinionPollVO(OpinionPollVO opinionPollVO) {
		this.opinionPollVO = opinionPollVO;
	}
	
	public void setServletRequest(HttpServletRequest req) {
		request = req;

	}

	public void setServletContext(ServletContext context) {
		this.context = context;

	}

	
	public String getOptions() {
		return options;
	}


	public void setOptions(String options) {
		this.options = options;
	}
	
	public int getResultStatus() {
		return resultStatus;
	}


	public void setResultStatus(int resultStatus) {
		this.resultStatus = resultStatus;
	}


	public String execute()throws Exception 
	{
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		
		OpinionPollVO opinionPollVO2 = new OpinionPollVO();
		
		opinionPollVO2.setTitle(getTitle().trim());
		opinionPollVO2.setDescription(getDescription().trim());
		opinionPollVO2.setOpinionPollStartDate(sdf.parse(getPollStartDate()));
		opinionPollVO2.setOpinionPollEndDate(sdf.parse(getPollEndDate()));
		opinionPollVO2.setRegistration_id(regVO.getRegistrationID());
		
		List<OptionVO>  optionVOs = new ArrayList<OptionVO>(); 
		
		StringTokenizer st =  new StringTokenizer(getOptions(),"^");
		
		while(st.hasMoreElements())
		{
			OptionVO optionVO = new OptionVO();
			String str = st.nextElement().toString().trim();
	
			if(str.length() != 0)
			{
			  optionVO.setOption(str);
			  optionVOs.add(optionVO);
			}
			
		}
		
		List<QuestionsOptionsVO> questionsOptionsVOs =new ArrayList<QuestionsOptionsVO>();
		
		QuestionsOptionsVO questionsOptionsVO1 = new QuestionsOptionsVO();
	
		questionsOptionsVO1.setQuestion(getQuestion().trim());
		questionsOptionsVO1.setOptions(optionVOs);
						
	    questionsOptionsVOs.add(questionsOptionsVO1);
	    
	   	opinionPollVO2.setQuesitons(questionsOptionsVOs); 
	   
	   	boolean result = opinionPollService.createPoll(opinionPollVO2,regVO.getRegistrationID()); 
	   	
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
	
}
