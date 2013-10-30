package com.itgrids.partyanalyst.web.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONObject;
import org.springframework.web.context.ServletContextAware;

import com.itgrids.partyanalyst.dto.CommentVO;
import com.itgrids.partyanalyst.dto.OpinionPollVO;
import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.QuestionsOptionsVO;
import com.itgrids.partyanalyst.helper.ChartProducer;
import com.itgrids.partyanalyst.service.IOpinionPollService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class CompleteResultForAPollAction extends ActionSupport implements
		ServletRequestAware, ServletContextAware  {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GetAllPollsAction.class);
	
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private ServletContext context;
	
	
	private IOpinionPollService opinionPollService;
	
	private QuestionsOptionsVO questionsOptionsVO;
	private Long questionId;
	
	private OpinionPollVO opinionPollVO;
	
	private String comments;
	private List<CommentVO> commentList;
		
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	
	public QuestionsOptionsVO getQuestionsOptionsVO() {
		return questionsOptionsVO;
	}
	public void setQuestionsOptionsVO(QuestionsOptionsVO questionsOptionsVO) {
		this.questionsOptionsVO = questionsOptionsVO;
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
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
		
	public IOpinionPollService getOpinionPollService() {
		return opinionPollService;
	}
	public void setOpinionPollService(IOpinionPollService opinionPollService) {
		this.opinionPollService = opinionPollService;
	}
	
	public OpinionPollVO getOpinionPollVO() {
		return opinionPollVO;
	}
	public void setOpinionPollVO(OpinionPollVO opinionPollVO) {
		this.opinionPollVO = opinionPollVO;
	}
	

	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	


	public List<CommentVO> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentVO> commentList) {
		this.commentList = commentList;
	}

	public String execute () throws Exception 
	{
		String cPath = request.getContextPath();
		
		questionsOptionsVO = opinionPollService.getQuestionAndPercentageOfVotesForChoices(questionId);
		String chartName = "opinionPoll_questionId_"+questionsOptionsVO.getQuestionId()+"_detailed.png";
		String chartPath;
		
		if(cPath.contains("PartyAnalyst"))
			chartPath = context.getRealPath("/")+ "charts\\" + chartName;
		else
		    chartPath = IWebConstants.CHART_URL_IN_SERVER + chartName;
		
		questionsOptionsVO.setImagePath(chartName);
		ChartProducer.createBarChartForVotesPoll(questionsOptionsVO.getQuestion(), "", "", createDataset(questionsOptionsVO), chartPath,"completeResults");
		
		if(comments.equalsIgnoreCase("getComments"))
			commentList = opinionPollService.getCommentsnOpinionPollByQuestionId(questionId);
			
		
		Cookie[] cookieArray = request.getCookies();
		
		boolean availabiity = false;
		
		if(cookieArray == null){
			opinionPollVO = opinionPollService.getAllPollsForTheDay();
			opinionPollVO.setAvaliability(availabiity);		
		}
		else{			
		     for(int i=0;i<cookieArray.length;i++){
			   Cookie cookie = cookieArray[i];
			    if(cookie.getName().equalsIgnoreCase("hasPolled"))
				 availabiity = true;
			
		     }
		
			if(availabiity){			
				opinionPollVO = opinionPollService.getDetailsOfTheLatestOpinionPoll();
				opinionPollVO.setAvaliability(availabiity);
			}		
			else{
				opinionPollVO = opinionPollService.getAllPollsForTheDay();
				opinionPollVO.setAvaliability(availabiity);	
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
	
	public String getAllQuestionAndPercentageOfVotesForChoices(){
		
		 List<QuestionsOptionsVO> list=opinionPollService.getAllQuestionAndPercentageOfVotesForChoices();
		 
		 return SUCCESS;
		
	}
}
