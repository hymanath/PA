package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.DebateDetailsVO;
import com.itgrids.partyanalyst.dto.DebateVO;
import com.itgrids.partyanalyst.dto.ParticipantVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
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
	private List<SelectOptionVO> characteristicsList;
	private List<SelectOptionVO> rolesList;
	private String url;
	private List<SelectOptionVO> debateDetails;
	private Long debateId ;
	
	
	
	public Long getDebateId() {
		return debateId;
	}

	public void setDebateId(Long debateId) {
		this.debateId = debateId;
	}

	public List<SelectOptionVO> getDebateDetails() {
		return debateDetails;
	}

	public void setDebateDetails(List<SelectOptionVO> debateDetails) {
		this.debateDetails = debateDetails;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<SelectOptionVO> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<SelectOptionVO> rolesList) {
		this.rolesList = rolesList;
	}

	public List<SelectOptionVO> getCharacteristicsList() {
		return characteristicsList;
	}

	public void setCharacteristicsList(List<SelectOptionVO> characteristicsList) {
		this.characteristicsList = characteristicsList;
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
			characteristicsList = debateService.getCharacteristicsDetails();
			rolesList = debateService.getRolesList();
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
			 jObj = new JSONObject(getTask());
			 JSONObject debateObj = jObj.getJSONObject("debateDetails");
			 debateDetailsVO.setChannelId(debateObj.getLong("channelId"));
			 debateDetailsVO.setTelecasteTypeId(debateObj.getLong("telecastTimeId"));
			 String startDate = debateObj.getString("startTime");
			 String endDate = debateObj.getString("endTime");
			 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:MM");
			 debateDetailsVO.setStartDate(sdf.parse(startDate));
			 debateDetailsVO.setEndDate(sdf.parse(endDate));
			 debateDetailsVO.setDebateSummery(debateObj.getString("debetSummery"));
			 JSONArray subjectsArray = jObj.getJSONArray("subjectArray");
			 List<SelectOptionVO> subjectsList = new ArrayList<SelectOptionVO>();
			 for (int i = 0; i < subjectsArray.length(); i++) {
				 SelectOptionVO selectOptionVO = new SelectOptionVO();
				 selectOptionVO.setName(subjectsArray.get(i).toString());
				 subjectsList.add(selectOptionVO);
			 }
			 debateDetailsVO.setSubjectList(subjectsList);
			 JSONArray observersArray = jObj.getJSONArray("observer");
			 List<SelectOptionVO> observersList = new ArrayList<SelectOptionVO>();
			 for (int i = 0; i < observersArray.length(); i++) {
				 SelectOptionVO observerVO = new SelectOptionVO();
				 observerVO.setId(Long.valueOf(observersArray.get(i).toString()));
				 observersList.add(observerVO);
			 }
			 debateDetailsVO.setObserverList(observersList);
			 JSONArray particepentArray = jObj.getJSONArray("participant");
			 List<ParticipantVO> particepentList = new ArrayList<ParticipantVO>();
			 for (int i = 0; i < particepentArray.length(); i++) 
			 {
				 ParticipantVO participantVO = new ParticipantVO();
				 JSONObject particepentObj = (JSONObject) particepentArray.get(i);
				 participantVO.setId(particepentObj.getLong("candidateId"));
				 participantVO.setPartyId(particepentObj.getLong("partyId"));
				 participantVO.setSummery(particepentObj.getString("summery"));
				 
				 JSONArray scalsArray = particepentObj.getJSONArray("scale");
				 List<SelectOptionVO> scaleList = new ArrayList<SelectOptionVO>();
				 for (int j = 0; j < scalsArray.length(); j++) {
					 SelectOptionVO scaleVO = new SelectOptionVO();
					 JSONObject scaleObj = (JSONObject) scalsArray.get(j);
					 scaleVO.setId(scaleObj.getLong("scaleId"));
					 scaleVO.setPerc(scaleObj.getDouble("scaleTotal"));
					 scaleList.add(scaleVO);
				 }
				 participantVO.setScaleList(scaleList);
				 List<SelectOptionVO> rolesList = new ArrayList<SelectOptionVO>();
				 JSONArray rolesArray = (JSONArray) particepentObj.getJSONArray("participantRoles");
				 if(rolesArray.length() > 0)
				 {
					 for (int j = 0; j < rolesArray.length(); j++)
					 {
						 SelectOptionVO rolesVO = new SelectOptionVO();
						 rolesVO.setId(Long.valueOf(rolesArray.get(j).toString()));
						 rolesList.add(rolesVO);
					 }
					 participantVO.setRoleList(rolesList);
				 }
				 
				 List<SelectOptionVO> exprolesList = new ArrayList<SelectOptionVO>();
				 JSONArray exprolesArray = (JSONArray) particepentObj.getJSONArray("expparticipantRoles");
				 if(exprolesArray.length() > 0)
				 {
					 for (int j = 0; j < exprolesArray.length(); j++)
					 {
						 SelectOptionVO rolesVO = new SelectOptionVO();
						 rolesVO.setId(Long.valueOf(exprolesArray.get(j).toString()));
						 exprolesList.add(rolesVO);
					 }
					 participantVO.setExpRoleList(exprolesList);
					 
				 }
				 particepentList.add(participantVO);
			 }
			 debateDetailsVO.setParticipentsList(particepentList);
			 
			 JSONArray questionAnswerArray = jObj.getJSONArray("questionAnswer");
			 List<SelectOptionVO> questionAnswerList = new ArrayList<SelectOptionVO>();
			 for (int i = 0; i < questionAnswerArray.length(); i++)
			 {
				 SelectOptionVO questionAnswerVO = new SelectOptionVO();
				 JSONObject questionAnswerObj = (JSONObject) questionAnswerArray.get(i);
				 questionAnswerVO.setId(questionAnswerObj.getLong("questionId"));
				 questionAnswerVO.setName(questionAnswerObj.getString("answer"));
				 questionAnswerList.add(questionAnswerVO);
			 }
			 debateDetailsVO.setQuestionsList(questionAnswerList);
			 JSONArray smsQuestion = jObj.getJSONArray("smsPole");
			 List<SelectOptionVO> smsQuestionList = new ArrayList<SelectOptionVO>();
			
			 for (int i = 0; i < smsQuestion.length(); i++)
			 {
				 SelectOptionVO selectOptionVO = new SelectOptionVO();
				 JSONObject smsQuestionObj = (JSONObject) smsQuestion.get(i);
				 selectOptionVO.setName(smsQuestionObj.getString("questionId"));
				 smsQuestionList.add(selectOptionVO);
			 }
			 debateDetailsVO.setSmsQuestionList(smsQuestionList);
			 List<SelectOptionVO> smsOptionsList = new ArrayList<SelectOptionVO>();
			 for (int i = 0; i < smsQuestion.length(); i++)
			 {
				 SelectOptionVO selectOptionVO = new SelectOptionVO();
				 JSONObject smsQuestionObj = (JSONObject) smsQuestion.get(i);
				 //selectOptionVO.setId(smsQuestionObj.getLong("questionId"));
				 selectOptionVO.setName(smsQuestionObj.getString("option"));
				 selectOptionVO.setPerc(smsQuestionObj.getDouble("percentage"));
				 smsOptionsList.add(selectOptionVO);
			 }
			 debateDetailsVO.setSmaOptionsList(smsOptionsList);
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
			 jObj = new JSONObject(getTask());
			debateVO = debateService.getDebateDetailsForSelected(jObj.getLong("debateId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception occured in saveDebateDetial methon in DebateAction Class",e);
		}
		return Action.SUCCESS;
	}
	
	
	public String saveDebateRelatedAttributes()
	{
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
	
	public String updateFieldAttributes(){		
		LOG.info(" Entered into updateFieldAttributes() in DebateAction class. ");		
		HttpSession session = request.getSession();
		RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
		if(regVo == null)
			return Action.ERROR;
		try {
				String task = request.getParameter("task");
				
				if(task.equalsIgnoreCase("updateChannel")){
					channelList = debateService.getChannelDetails();
				}
				else if(task.equalsIgnoreCase("updateObserver")){
					channelList = debateService.getObserverDetails();
				}

		} catch (Exception e) {
			LOG.error(" Exception occured in updateFieldAttributes() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDebateDetailsBtDates()
	{
		try {
			LOG.info(" Entered into getDebateDetailsBtDates() in DebateAction class. ");
			jObj = new JSONObject(getTask());
			 HttpSession session = request.getSession();
				RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
				if(regVo == null)
					return Action.ERROR;
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String fromDateStr = jObj.getString("fronDate");
				String toDateStr  = jObj.getString("toDate");
				debateDetails = debateService.getDebateDetailsForSelectedDates(sdf.parse(fromDateStr), sdf.parse(toDateStr));
		} catch (Exception e) {
			LOG.error(" Exception occured in getDebateDetailsBtDates() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
	public String generateUrl()
	{
		try {
			LOG.info(" Entered into generateUrl() in DebateAction class. ");
			 jObj = new JSONObject(getTask());
			 HttpSession session = request.getSession();
				RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
				if(regVo == null)
					return Action.ERROR;
			Long reportId = jObj.getLong("reportId");
			Long userId = regVo.getRegistrationID();
			String path = request.getRequestURL().toString().replace("generateKeyReportAction.action","genereateReportAction.action?");
			url = debateService.genearetUrl(reportId, userId, path);
		} catch (Exception e) {
			LOG.error(" Exception occured in generateUrl() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
}
