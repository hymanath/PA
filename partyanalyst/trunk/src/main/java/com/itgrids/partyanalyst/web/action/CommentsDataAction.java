package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ElectionCommentsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICommentsDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CommentsDataAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(CommentsDataAction.class);
	private HttpServletRequest request;
	private String task = null;
	JSONObject jObj = null;
	private ICommentsDataService commentsDataService;
	private ElectionCommentsVO electionCommentsVO;
	private List<SelectOptionVO> commentsClassificationList;                         
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
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

	public ICommentsDataService getCommentsDataService() {
		return commentsDataService;
	}

	public void setCommentsDataService(ICommentsDataService commentsDataService) {
		this.commentsDataService = commentsDataService;
	}	
	
	public ElectionCommentsVO getElectionCommentsVO() {
		return electionCommentsVO;
	}

	public void setElectionCommentsVO(ElectionCommentsVO electionCommentsVO) {
		this.electionCommentsVO = electionCommentsVO;
	}
	
	public void setCommentsClassificationList(
			List<SelectOptionVO> commentsClassificationList) {
		this.commentsClassificationList = commentsClassificationList;
	}

	public List<SelectOptionVO> getCommentsClassificationList() {
		return commentsClassificationList;
	}

	public String execute () throws Exception 
	{
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long registrationId = null;
		String userType = "";
		
		HttpSession session = request.getSession();
		RegistrationVO regVO = session.getAttribute(IConstants.USER) != null?(RegistrationVO)session.getAttribute(IConstants.USER):null;
		
		if(regVO != null){
			registrationId = regVO.getRegistrationID();
			userType = regVO.getUserStatus();	
		}
		
		if(jObj.getString("task").equalsIgnoreCase("addNewComment"))
		{

			electionCommentsVO = new ElectionCommentsVO();
			String electionYear = jObj.getString("year");
			String electionType = jObj.getString("electionType");
			String commentedBy =  jObj.getString("postedBy");
			String commentDesc = jObj.getString("commentDesc");
			Long electionId = new Long(jObj.getString("electionId"));
			Long  partyId = new Long(jObj.getString("partyId"));
			Long candidateId = new Long(jObj.getString("candidateId"));
			Long constituencyId = new Long(jObj.getString("constituencyId"));
			String category = jObj.getString("category");
			Long commentCategoryId = new Long(jObj.getString("commentCategoryId"));
			
			if(log.isDebugEnabled())
			{	
				log.debug("electionYear:"+electionYear);
				log.debug("electionType:"+electionType);
				log.debug("commentedBy:"+commentedBy);
				log.debug("commentDesc:"+commentDesc);
				log.debug("electionId:"+electionId);
				log.debug("partyId:"+partyId);
				log.debug("category:"+category);
			} 
			
			if(category.equals("party"))
			{			
				log.debug(category+"block:");
				electionCommentsVO.setPartyCommentsSaved(commentsDataService.savePartyCommentsToDB(electionType, electionYear, electionId, 
						partyId, commentDesc, commentedBy, commentCategoryId));
				log.debug("After Save!!!!!!!!!!!!!!!!");
				log.debug("Saved Party Comments Obj::::::::::::::"+electionCommentsVO.getPartyCommentsSaved());				
				
			} if(category.equals("candidate"))
			{
				log.debug(category+"block:");				
				electionCommentsVO.setCandidateCommentsSaved(commentsDataService.saveCandidateCommentsToDB(electionType, electionYear, 
						electionId,constituencyId, candidateId,commentDesc,commentedBy,commentCategoryId, registrationId, userType ));
				log.debug("After Save!!!!!!!!!!!!!!!!");
				log.debug("Saved Candidate Comments Obj::::::::::::::"+electionCommentsVO.getCandidateCommentsSaved());
				
			} if(category.equals("constituency"))
			{
				log.debug(category+"block:");
			}
		}
		if(jObj.getString("task").equalsIgnoreCase("getPreviousComments"))
		{
			electionCommentsVO = new ElectionCommentsVO();
			List <ElectionCommentsVO> commentsListFromService = new ArrayList<ElectionCommentsVO>(); 
			
			String electionYear = jObj.getString("year");
			String electionType = jObj.getString("electionType");
			Long electionId = new Long(jObj.getString("electionId"));
			Long constituencyId = new Long(jObj.getString("constituencyId"));
			Long candidateId = new Long(jObj.getString("candidateId"));
			
			if(log.isDebugEnabled())
			{	
				log.debug("electionYear:"+electionYear);
				log.debug("electionType:"+electionType);
				log.debug("candidateId:"+candidateId);
				log.debug("electionId:"+electionId);
				log.debug("constituencyId:"+constituencyId);
				
			}
			
			commentsListFromService = commentsDataService.getCandidateCommentsData(electionType, electionYear, electionId, 
					constituencyId, candidateId, IConstants.CANDIDATE_COMMENTS_CONSTITUENCY, registrationId, userType);
			//getCandidateCommentsData(String electionType,String electionYear,Long electionId,Long constituencyId,Long candidateId,String categoryType);
			if(commentsListFromService != null && commentsListFromService.size()>0)
			{
				electionCommentsVO = commentsListFromService.get(0);
			}
		}
		
	return Action.SUCCESS;
	}
	
	public String getCommentsClassifications() throws Exception
	{
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(log.isDebugEnabled())
				log.debug(jObj);			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("getCommentsClassificationsList"))
		{
			commentsClassificationList = new ArrayList<SelectOptionVO>();
			String rank = jObj.getString("rank");
			commentsClassificationList = commentsDataService.getCandidateCommentsCategoryStatics(new Long(rank));
			log.debug("commentsClassificationList Size::::::::::::::::" + commentsClassificationList.size());
		}	
		
		return Action.SUCCESS;
	}
	
}
