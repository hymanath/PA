package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.dto.DebateVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IDebateService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DebateAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(DebateAction.class); 
	private HttpServletRequest request;
	private HttpSession session;
	private IDebateService debateService;
	private String task;
	private JSONObject jObj;
	private String attributeName;
	private ResultStatus resultStatus;
	private DebateVO debateVO;
	private List<SelectOptionVO> channelList;
	private List<SelectOptionVO> telecastTimeList;
	private List<SelectOptionVO> observerList;
	private ICandidateDetailsService candidateDetailsService;
	private List<SelectOptionVO> partiesList;
	private List<SelectOptionVO> debateQuestionList;
	private List<SelectOptionVO> debateSmsQuestionList;
	private List<SelectOptionVO> debateParticipantRoleList;
	
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

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public DebateVO getDebateVO() {
		return debateVO;
	}

	public void setDebateVO(DebateVO debateVO) {
		this.debateVO = debateVO;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
		
	public List<SelectOptionVO> getDebateParticipantRoleList() {
		return debateParticipantRoleList;
	}


	public void setDebateParticipantRoleList(
			List<SelectOptionVO> debateParticipantRoleList) {
		this.debateParticipantRoleList = debateParticipantRoleList;
	}


	public List<SelectOptionVO> getDebateSmsQuestionList() {
		return debateSmsQuestionList;
	}


	public void setDebateSmsQuestionList(List<SelectOptionVO> debateSmsQuestionList) {
		this.debateSmsQuestionList = debateSmsQuestionList;
	}


	public List<SelectOptionVO> getDebateQuestionList() {
		return debateQuestionList;
	}


	public void setDebateQuestionList(List<SelectOptionVO> debateQuestionList) {
		this.debateQuestionList = debateQuestionList;
	}


	public List<SelectOptionVO> getPartiesList() {
		return partiesList;
	}


	public void setPartiesList(List<SelectOptionVO> partiesList) {
		this.partiesList = partiesList;
	}


	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}


	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}


	public List<SelectOptionVO> getObserverList() {
		return observerList;
	}


	public void setObserverList(List<SelectOptionVO> observerList) {
		this.observerList = observerList;
	}


	public List<SelectOptionVO> getTelecastTimeList() {
		return telecastTimeList;
	}


	public void setTelecastTimeList(List<SelectOptionVO> telecastTimeList) {
		this.telecastTimeList = telecastTimeList;
	}


	public void setDebateService(IDebateService debateService) {
		this.debateService = debateService;
	}


	public List<SelectOptionVO> getChannelList() {
		return channelList;
	}


	public void setChannelList(List<SelectOptionVO> channelList) {
		this.channelList = channelList;
	}


	public IDebateService getDebateService() {
		return debateService;
	}


	public String execute()
	{
		try {
			LOG.info("Entered into execute methon in DebateAction Class");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
			{
				return Action.ERROR;
			}
			channelList = debateService.getChannelDetails();
			telecastTimeList = debateService.getTelecastTimeDetails();
			observerList = debateService.getObserverDetails();
			partiesList = candidateDetailsService.getPartiesList();
			debateQuestionList = debateService.getDebateQuestionDetails();
			debateSmsQuestionList = debateService.getDebateSmsQuestionDetails();
			debateParticipantRoleList = debateService.getDebateParticipantRoleDetails();
		} catch (Exception e) {
			LOG.error("Exception occured in execute methon in DebateAction Class",e);
		}
		
		return Action.SUCCESS;
	}

	public String saveDebateDetial()
	{
		try 
		{
			LOG.info("Entered into saveDebateDetial methon in DebateAction Class");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
			{
				return Action.ERROR;
			}
			 DebateDetailsVO debateDetailsVO = new DebateDetailsVO();
			 resultStatus = debateService.saveDebateDetails(debateDetailsVO);
		} 
		catch (Exception e)
		{
			LOG.error("Exception occured in saveDebateDetial methon in DebateAction Class",e);
		}
		return Action.SUCCESS;
	}
	
	public String retriveDebateDetails()
	{
		try 
		{
			LOG.info("Entered into saveDebateDetial methon in DebateAction Class");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
			{
				return Action.ERROR;
			}
			debateVO = debateService.getDebateDetailsForSelected(Long.parseLong(request.getParameter("debateId")));
		} 
		catch (Exception e)
		{
			LOG.error("Exception occured in saveDebateDetial methon in DebateAction Class",e);
		}
		return Action.SUCCESS;
	}
	public String saveDebateRelatedAttributes(){
		LOG.info(" Entered into saveDebateRelatedAttributes() in DebateAction class. ");
		
		HttpSession session = request.getSession();
		RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
		if(regVo == null)
			return Action.ERROR;
		try {
				String attributeValue = request.getParameter("attributeName");
				String task = request.getParameter("task");
				
				if(task.equalsIgnoreCase("createNewRole")){
					resultStatus = debateService.saveNewRole(regVo.getRegistrationID(),attributeValue);
				}
				else if(task.equalsIgnoreCase("createNewCharacteristic")){
					resultStatus = debateService.saveNewCharacteristic(regVo.getRegistrationID(),attributeValue);
				}
				else if(task.equalsIgnoreCase("createNewDebateQuestion")){
					resultStatus = debateService.saveNewDebateQuestion(regVo.getRegistrationID(),attributeValue);
				}else if(task.equalsIgnoreCase("createNewChannel")){
					resultStatus   = debateService.insertChannelDetails(regVo.getRegistrationID(),attributeValue );
				}else if(task.equalsIgnoreCase("createNewObserver")){
					resultStatus = debateService.insertObserverDetails(regVo.getRegistrationID(),attributeValue);
				}
				
		} catch (Exception e) {
			LOG.error(" Exception occured in saveDebateRelatedAttributes() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
}
