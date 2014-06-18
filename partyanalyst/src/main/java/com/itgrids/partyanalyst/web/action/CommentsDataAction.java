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
	private static final Logger LOG = Logger.getLogger(CommentsDataAction.class);
	private HttpServletRequest request;
	private String task = null;
	JSONObject jObj = null;
	private ICommentsDataService commentsDataService;
	private ElectionCommentsVO electionCommentsVO;
	private List<SelectOptionVO> commentsClassificationList;                         
	private Boolean hasFreeUserRole;
	private Boolean hasPartyAnalystUserRole;
	
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
	
	public Boolean getHasFreeUserRole() {
		return hasFreeUserRole;
	}

	public void setHasFreeUserRole(Boolean hasFreeUserRole) {
		this.hasFreeUserRole = hasFreeUserRole;
	}

	public Boolean getHasPartyAnalystUserRole() {
		return hasPartyAnalystUserRole;
	}

	public void setHasPartyAnalystUserRole(Boolean hasPartyAnalystUserRole) {
		this.hasPartyAnalystUserRole = hasPartyAnalystUserRole;
	}

	public String execute () throws Exception 
	{
		String param = null;
		param = getTask();
		try {
			jObj = new JSONObject(param);
			if(LOG.isDebugEnabled())
				LOG.debug(jObj);			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Long registrationId = null;
		String userType = "";
		
		HttpSession session = request.getSession();
		RegistrationVO regVO = session.getAttribute(IConstants.USER) != null?(RegistrationVO)session.getAttribute(IConstants.USER):null;
		hasFreeUserRole = (Boolean)session.getAttribute("hasFreeUserRole");
		hasPartyAnalystUserRole = (Boolean)session.getAttribute("hasPartyAnalystUserRole");
		
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
			Long electionId = Long.valueOf(jObj.getString("electionId"));
			Long  partyId = Long.valueOf(jObj.getString("partyId"));
			Long candidateId = Long.valueOf(jObj.getString("candidateId"));
			Long constituencyId = Long.valueOf(jObj.getString("constituencyId"));
			String category = jObj.getString("category");
			Long commentCategoryId = Long.valueOf(jObj.getString("commentCategoryId"));
			Long severity = jObj.getLong("reasonSeverityvalue");
			if(LOG.isDebugEnabled())
			{	
				LOG.debug("electionYear:"+electionYear);
				LOG.debug("electionType:"+electionType);
				LOG.debug("commentedBy:"+commentedBy);
				LOG.debug("commentDesc:"+commentDesc);
				LOG.debug("electionId:"+electionId);
				LOG.debug("partyId:"+partyId);
				LOG.debug("category:"+category);
			} 
			
			if(category.equals("party"))
			{			
				LOG.debug(category+"block:");
				electionCommentsVO.setPartyCommentsSaved(commentsDataService.savePartyCommentsToDB(electionType, electionYear, electionId, 
						partyId, commentDesc, commentedBy, commentCategoryId,hasFreeUserRole,hasPartyAnalystUserRole));
				LOG.debug("After Save!!!!!!!!!!!!!!!!");
				LOG.debug("Saved Party Comments Obj::::::::::::::"+electionCommentsVO.getPartyCommentsSaved());				
				
			} if(category.equals("candidate"))
			{
				LOG.debug(category+"block:");				
				/*electionCommentsVO.setCandidateCommentsSaved(commentsDataService.saveCandidateCommentsToDB(electionType, electionYear, 
						electionId,constituencyId, candidateId,commentDesc,commentedBy,commentCategoryId, registrationId, userType, severity));*/
				
				electionCommentsVO.setCandidateCommentsSaved(commentsDataService.saveCandidateCommentsToDB(electionType, electionYear, 
						electionId,constituencyId, candidateId,commentDesc,commentedBy,commentCategoryId, registrationId, userType, severity,hasFreeUserRole,hasPartyAnalystUserRole));
				
				LOG.debug("After Save!!!!!!!!!!!!!!!!");
				LOG.debug("Saved Candidate Comments Obj::::::::::::::"+electionCommentsVO.getCandidateCommentsSaved());
				
			} if(category.equals("constituency"))
			{
				LOG.debug(category+"block:");
			}
		}
		if(jObj.getString("task").equalsIgnoreCase("getPreviousComments"))
		{
			electionCommentsVO = new ElectionCommentsVO();
			List <ElectionCommentsVO> commentsListFromService = new ArrayList<ElectionCommentsVO>(); 
			
			String electionYear = jObj.getString("year");
			String electionType = jObj.getString("electionType");
			Long electionId = Long.valueOf(jObj.getString("electionId"));
			Long constituencyId = Long.valueOf(jObj.getString("constituencyId"));
			Long candidateId = Long.valueOf(jObj.getString("candidateId"));
			
			if(LOG.isDebugEnabled())
			{	
				LOG.debug("electionYear:"+electionYear);
				LOG.debug("electionType:"+electionType);
				LOG.debug("candidateId:"+candidateId);
				LOG.debug("electionId:"+electionId);
				LOG.debug("constituencyId:"+constituencyId);
				
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
			if(LOG.isDebugEnabled())
				LOG.debug(jObj);			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(jObj.getString("task").equalsIgnoreCase("getCommentsClassificationsList"))
		{
			commentsClassificationList = new ArrayList<SelectOptionVO>();
			String rank = jObj.getString("rank");
			commentsClassificationList = commentsDataService.getCandidateCommentsCategoryStatics(Long.valueOf(rank));
			LOG.debug("commentsClassificationList Size::::::::::::::::" + commentsClassificationList.size());
		}	
		
		return Action.SUCCESS;
	}
	
}
