package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONObject;
import org.springframework.web.context.ServletContextAware;

import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.OpinionPollVO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IOpinionPollService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class VotesPollAction extends ActionSupport implements ServletRequestAware, ServletContextAware {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(VotesPollAction.class);
	
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private ServletContext context;
	
	private IOpinionPollService opinionPollService;
	
	private OpinionPollVO opinionPollVO;
	private QuestionsOptionsVO questionsAndChoicesPercentage;
	
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	public QuestionsOptionsVO getQuestionsAndChoicesPercentage() {
		return questionsAndChoicesPercentage;
	}

	public void setQuestionsAndChoicesPercentage(
			QuestionsOptionsVO questionsAndChoicesPercentage) {
		this.questionsAndChoicesPercentage = questionsAndChoicesPercentage;
	}

	public OpinionPollVO getOpinionPollVO() {
		return opinionPollVO;
	}

	public void setOpinionPollVO(OpinionPollVO opinionPollVO) {
		this.opinionPollVO = opinionPollVO;
	}

	public IOpinionPollService getOpinionPollService() {
		return opinionPollService;
	}

	public void setOpinionPollService(IOpinionPollService opinionPollService) {
		this.opinionPollService = opinionPollService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
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
	
	public String execute () throws Exception 
	{
		return SUCCESS;
	}
	
	public String getAllPollsForTheDay()
	{
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				log.debug("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(jObj.getString("task").equals("getAllPolls")){
				opinionPollVO = opinionPollService.getAllPollsForTheDay();
			}
		}
		return SUCCESS;		
	}
	
	public String saveSelectedPoll()
	{
		if(task != null){
			try{
				jObj = new JSONObject(getTask());
				log.debug("Result From JSON:"+jObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(jObj.getString("task").equals("saveSelectedPoll")){
				questionsAndChoicesPercentage = opinionPollService.saveSelectionResultOfThePoll(jObj.getLong("questionId"),jObj.getLong("selectedPollId"));
				String chartName = "opinionPoll.png";
		        String chartPath = context.getRealPath("/")+ "charts\\" + chartName;
		        questionsAndChoicesPercentage.setImagePath(chartName);
				 ChartProducer.createBarChartForVotesPoll(questionsAndChoicesPercentage.getQuestion(), "Options", "Percentage", createDataset(questionsAndChoicesPercentage), chartPath);
			}
		}
		return SUCCESS;		
	}
	
	private CategoryDataset createDataset(QuestionsOptionsVO questionsAndChoicesPercentage) {       
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(OptionVO result: questionsAndChoicesPercentage.getOptions()){        
        		dataset.addValue(new BigDecimal(result.getPercentage()), result.getOption(), result.getOption());       	
        }
        return dataset;
    }
}
