package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.DebateAnalysisVO;
import com.itgrids.partyanalyst.dto.DebateDetailsVO;
import com.itgrids.partyanalyst.dto.DebatePartyWiseCountVO;
import com.itgrids.partyanalyst.dto.DebateTopicVO;
import com.itgrids.partyanalyst.dto.DebateVO;
import com.itgrids.partyanalyst.dto.ParticipantVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IDebateAnalysisService;
import com.itgrids.partyanalyst.service.IDebateService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DebateAction extends ActionSupport implements ServletRequestAware
{
	/**
	 * 
	 */
	private static final long 			serialVersionUID = 1L;
	private static final Logger 		LOG = Logger.getLogger(DebateAction.class); 
	
	
	private HttpServletRequest 			request;
	private HttpSession 				session;

	private JSONObject 					jObj;
	
	private String 						attributeName;
	private String 						task;
	private String 						status;
	private String 						key;
	private Long 						debateId ;
	
	private ResultStatus 				resultStatus;
	
	private DebateVO 					debateVO;
	
	private List<SelectOptionVO> 		channelList;
	private List<SelectOptionVO> 		telecastTimeList;
	private List<SelectOptionVO> 		observerList;
	private List<SelectOptionVO> 		partiesList,partiesList1;
	private List<SelectOptionVO> 		debateQuestionList;
	private List<SelectOptionVO> 		debateSmsQuestionList;
	private List<SelectOptionVO> 		debateParticipantRoleList;
	private List<SelectOptionVO> 		characteristicsList;
	private List<SelectOptionVO> 		rolesList;
	private List<SelectOptionVO> 		candidatesList;
	private List<SelectOptionVO> 		questionOptionsList;
	private List<SelectOptionVO> 		partyWiseList;
	private List<SelectOptionVO> 		candidateWiseList;
	private List<SelectOptionVO> 		debateDetails;

	private ICandidateDetailsService 	candidateDetailsService;
	private IDebateService 				debateService;
	
	private IDebateAnalysisService      debateAnalysisService;
	
	private List<DebateTopicVO>         debateReportList;
	private List<DebateAnalysisVO>      debateAnalysisList;
	private List<DebatePartyWiseCountVO>overllPartyWiseList;
	
	private String fromDate;
	private String toDate;
	private int startIndex;
	private int results;
	private String sort;
	private String dir ;
	private String channel;
	private String partyId;
	private String candidateId;
	private List<SelectOptionVO> districtsList1;
	private List<SelectOptionVO> parlConstiList1;
	private List<SelectOptionVO> assemConstiList1;
	private IStaticDataService staticDataService;

	private List<DebateVO> debateVOList;
	
	
	public List<DebateVO> getDebateVOList() {
		return debateVOList;
	}

	public void setDebateVOList(List<DebateVO> debateVOList) {
		this.debateVOList = debateVOList;
	}

	public List<SelectOptionVO> getPartiesList1() {
		return partiesList1;
	}

	public void setPartiesList1(List<SelectOptionVO> partiesList1) {
		this.partiesList1 = partiesList1;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public List<SelectOptionVO> getDistrictsList1() {
		return districtsList1;
	}

	public void setDistrictsList1(List<SelectOptionVO> districtsList1) {
		this.districtsList1 = districtsList1;
	}

	public List<SelectOptionVO> getParlConstiList1() {
		return parlConstiList1;
	}

	public void setParlConstiList1(List<SelectOptionVO> parlConstiList1) {
		this.parlConstiList1 = parlConstiList1;
	}

	public List<SelectOptionVO> getAssemConstiList1() {
		return assemConstiList1;
	}

	public void setAssemConstiList1(List<SelectOptionVO> assemConstiList1) {
		this.assemConstiList1 = assemConstiList1;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}


	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public List<SelectOptionVO> getCandidatesList() {
		return candidatesList;
	}

	public void setCandidatesList(List<SelectOptionVO> candidatesList) {
		this.candidatesList = candidatesList;
	}

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

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	
	public List<SelectOptionVO> getQuestionOptionsList() {
		return questionOptionsList;
	}

	public void setQuestionOptionsList(List<SelectOptionVO> questionOptionsList) {
		this.questionOptionsList = questionOptionsList;
	}

	public List<SelectOptionVO> getPartyWiseList() {
		return partyWiseList;
	}

	public void setPartyWiseList(List<SelectOptionVO> partyWiseList) {
		this.partyWiseList = partyWiseList;
	}

	public List<SelectOptionVO> getCandidateWiseList() {
		return candidateWiseList;
	}

	public void setCandidateWiseList(List<SelectOptionVO> candidateWiseList) {
		this.candidateWiseList = candidateWiseList;
	}

	
	public List<DebateTopicVO> getDebateReportList() {
		return debateReportList;
	}

	public void setDebateReportList(List<DebateTopicVO> debateReportList) {
		this.debateReportList = debateReportList;
	}

	
	public IDebateAnalysisService getDebateAnalysisService() {
		return debateAnalysisService;
	}

	public void setDebateAnalysisService(
			IDebateAnalysisService debateAnalysisService) {
		this.debateAnalysisService = debateAnalysisService;
	}

	
	public List<DebateAnalysisVO> getDebateAnalysisList() {
		return debateAnalysisList;
	}

	public void setDebateAnalysisList(List<DebateAnalysisVO> debateAnalysisList) {
		this.debateAnalysisList = debateAnalysisList;
	}

	
	public List<DebatePartyWiseCountVO> getOverllPartyWiseList() {
		return overllPartyWiseList;
	}

	public void setOverllPartyWiseList(
			List<DebatePartyWiseCountVO> overllPartyWiseList) {
		this.overllPartyWiseList = overllPartyWiseList;
	}

	public String execute()
	{
		try 
		{
			LOG.info("Entered into execute methon in DebateAction Class");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
			{
				return Action.ERROR;
			}
			/*if(!regVO.getUserRoles().contains("DEBATE")){
	        	return "noAccess";
			}*/
			executeBasicDetails();
		}
		catch (Exception e) 
		{
			LOG.error("Exception occured in execute methon in DebateAction Class",e);
		}
		
		return Action.SUCCESS;
	}

	 public static Comparator<SelectOptionVO> sortList = new Comparator<SelectOptionVO>()
	 {
		  
	  public int compare(SelectOptionVO newsCountVO, SelectOptionVO newsCountVO2)
		{
		   return (newsCountVO.getId()).compareTo(newsCountVO2.getId());
		}
    };
		    
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
			 
			 debateDetailsVO.setDebateId(debateId);
			 JSONObject debateObj = jObj.getJSONObject("debateDetails");
			 debateDetailsVO.setChannelId(debateObj.getLong("channelId"));
			 //debateDetailsVO.setTelecasteTypeId(debateObj.getLong("telecastTimeId"));
			 String startDate = debateObj.getString("startTime");
			 String endDate = debateObj.getString("endTime");
			 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			 debateDetailsVO.setStartDate(sdf.parse(startDate));
			 debateDetailsVO.setEndDate(sdf.parse(endDate));
			 debateDetailsVO.setDebateSummery(debateObj.getString("debetSummery"));
			 debateDetailsVO.setType(jObj.getString("type"));
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
				 participantVO.setLocationId(particepentObj.getLong("locationId"));
				 participantVO.setSummery(particepentObj.getString("summery")!= null && particepentObj.getString("summery").trim().length() > 0 ?particepentObj.getString("summery"):null );
				 
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
						 
						 if(Long.valueOf(exprolesArray.get(j).toString()) > 0l)
						 {
							 SelectOptionVO rolesVO = new SelectOptionVO();
							 rolesVO.setId(Long.valueOf(exprolesArray.get(j).toString()));
							 exprolesList.add(rolesVO);
						 }
						 
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
			 debateDetailsVO.setUserId(regVO.getRegistrationID());
			 debateDetailsVO.setYoutubeUrl(debateObj.getString("youtubeUrl"));
			 debateDetailsVO.setStateId(jObj.getLong("stateId"));
			 //debateDetailsVO.setDebateCandidateLocationId(jObj.getLong("debateCandidateLocationId"));
			 /*JSONArray candidatesArray = jObj.getJSONArray("candidatesArray");
			 List<SelectOptionVO> candidatesList = new ArrayList<SelectOptionVO>();
			 for (int i = 0; i < candidatesArray.length(); i++) {
				 SelectOptionVO selectOptionVO = new SelectOptionVO();
				 selectOptionVO.setName(candidatesArray.get(i).toString());
				 candidatesList.add(selectOptionVO);
			 }*/
			 debateDetailsVO.setCandidatesList(candidatesList);
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
			LOG.info("Entered into retriveDebateDetails methon in DebateAction Class");
			/*session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
			{
				return Action.ERROR;
			}*/
			 jObj = new JSONObject(getTask());
			debateVO = debateService.getDebateDetailsForSelected(jObj.getLong("debateId"),jObj.getLong("stateId"));
		} 
		catch (Exception e)
		{
			LOG.error("Exception occured in retriveDebateDetails methon in DebateAction Class",e);
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
				
		} 
		catch (Exception e)
		{
			LOG.error(" Exception occured in saveDebateRelatedAttributes() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
	
	public String updateFieldAttributes()
	{		
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
		try 
		{
			LOG.info(" Entered into getDebateDetailsBtDates() in DebateAction class. ");

			 HttpSession session = request.getSession();
				RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
				if(regVo == null)
					return Action.ERROR;
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				
				//debateVO = debateService.getDebateDetailsForSelectedCriteria(sdf.parse(fromDate), sdf.parse(toDate),channel,partyId,candidateId, sort,dir, startIndex, results);
				
				 jObj = new JSONObject(getTask());
				 String startDate = jObj.getString("fromDate");
				 String endDate = jObj.getString("toDate");
				
				 JSONArray channelArr = jObj.getJSONArray("channelIdArr");
				
				 List<Long> channelIds = new ArrayList<Long>(0);
					
					if( channelArr != null && channelArr.length() >0 )
					{
						for(int i=0;i<channelArr.length();i++)
						{
							if(channelArr.get(i) != null)
							{
								String channelId = channelArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
								
								if(channelId.length() > 0 && !channelId.equalsIgnoreCase("0"))
								{
									channelIds.add(Long.valueOf(channelId));
								}
							}
						}
					}
					
					if(channelIds != null && channelIds.size() == 1)
					{
						Long channelId = channelIds.get(0);
						
						if(channelId.longValue() == 0L)
						{
							channelIds = null;
						}
					}
					
					else if(channelIds.size() == 0)
					{
						channelIds = null;						
					}
					
					
				 JSONArray partyArr = jObj.getJSONArray("partyIdArr");
					
					List<Long> partyIds = new ArrayList<Long>(0);
					
					if( partyArr != null && partyArr.length() >0 )
					{
						for(int i=0;i<partyArr.length();i++)
						{
							if(partyArr.get(i) != null)
							{
								String partyId = partyArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
								
								if(partyId.length() > 0 && !partyId.equalsIgnoreCase("0"))
								{
									partyIds.add(Long.valueOf(partyId));
								}
							}
						}
					}
					
					if(partyIds != null && partyIds.size() == 1)
					{
						Long partyId = partyIds.get(0);
						
						if(partyId.longValue() == 0L)
						{
							partyIds = null;
						}
					}
					else if(partyIds.size() == 0)
					{
						partyIds = null;						
					}
					
				 JSONArray candidateArr = jObj.getJSONArray("candidateIdArr");
				 
				 List<Long> candidateIds = new ArrayList<Long>(0);
					
					if( candidateArr != null && candidateArr.length() >0 )
					{
						for(int i=0;i<candidateArr.length();i++)
						{
							if(candidateArr.get(i) != null)
							{
								String candidateId = candidateArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
								
								if(candidateId.length() > 0 && !candidateId.equalsIgnoreCase("0"))
								{
									candidateIds.add(Long.valueOf(candidateId));
								}
							}
						}
					}
					
					if(candidateIds != null && candidateIds.size() == 1)
					{
						Long candidateId = candidateIds.get(0);
						
						if(candidateId.longValue() == 0L)
						{
							candidateIds = null;
						}
					}
					
					else if(candidateIds.size() == 0)
					{
						candidateIds = null;						
					}
					
				 String sortBy = " ";
				 String direction = " ";
				 int minIndex  =  jObj.getInt("startIndex");
				 int maxResults  = jObj.getInt("maxIndex");
				 Long stateId =jObj.getLong("stateId");
				//debateVO = debateService.getDebateDetailsForSelectedCriteria(sdf.parse(startDate), sdf.parse(endDate),selectedChannelId,selectedPartyId,politicianId, sortBy,direction, minIndex, maxResults);
				 debateVO = debateService.getDebateDetailsForSelectedCriteria(sdf.parse(startDate), sdf.parse(endDate),channelIds,partyIds,candidateIds, sortBy,direction, minIndex, maxResults,stateId);
		} 
		catch (Exception e)
		{
			LOG.error(" Exception occured in getDebateDetailsBtDates() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
	public String generateUrl()
	{
		try
		{
			LOG.info(" Entered into generateUrl() in DebateAction class. ");
			 jObj = new JSONObject(getTask());
			 HttpSession session = request.getSession();
				RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
				if(regVo == null)
					return Action.ERROR;
			Long debateId = jObj.getLong("debateId");
			String description = jObj.getString("description");
			Long stateId = jObj.getLong("stateId");
			Long userId = regVo.getRegistrationID();
			String path = request.getRequestURL().toString().replace("generateKeyReportAction.action","genereateReportAction.action?");
			status = debateService.saveDebateReportForPdf(userId,debateId,description, path,stateId);
		}
		catch (Exception e) 
		{
			LOG.error(" Exception occured in generateUrl() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
	
	public String deleteDebateReport()
	{
		try 
		{
			LOG.info(" Entered into deleteDebateReport() in DebateAction class. ");
			jObj = new JSONObject(getTask());
			 HttpSession session = request.getSession();
				RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
				if(regVo == null)
					return Action.ERROR;
			status = debateService.deleteDebateReportUrl(jObj.getString("key"));
		} 
		catch (Exception e)
		{
			LOG.error(" Exception occured in deleteDebateReport() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
	
	public String generateReport()
	{
		return Action.SUCCESS;
	}
	public String debateReport(){
		return Action.SUCCESS;
	}
	
	public String getCandidatesListForDebate()
	{
		try 
		{
			LOG.info(" Entered into deleteDebateReport() in DebateAction class. ");
			jObj = new JSONObject(getTask());
			
			JSONArray partyArr = jObj.getJSONArray("partyArr");
			
			List<Long> partyIds = new ArrayList<Long>(0);
			
			if( partyArr != null && partyArr.length() >0 )
			{
				for(int i=0;i<partyArr.length();i++)
				{
					if(partyArr.get(i) != null)
					{
						String partyId = partyArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
						
						if(partyId.length() > 0)
						{
							partyIds.add(Long.valueOf(partyId));
						}
					}
				}
			}
			
			candidatesList = debateService.getCandidatesForDebate(partyIds);
			
		} catch (Exception e)
		{
			LOG.error(" Exception occured in deleteDebateReport() in DebateAction class. ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getSmsQuestionOPtions()
	{
		try 
		{
			LOG.info(" Entered into getSmsQuestionOPtions() in DebateAction class. ");
			questionOptionsList = debateService.getDebateSmsQuestionDetails();
		} catch (Exception e)
		{
			LOG.error(" Exception occured in getSmsQuestionOPtions() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getDebateAnalysisDetails()
	{
		try 
		{
			LOG.info(" Entered into getDebateScalingAnalysisForPartyWise() in DebateAction class. ");
			HttpSession session = request.getSession();
			RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
			if(regVo == null)
			return Action.ERROR;
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			jObj = new JSONObject(getTask());
			String fromDateStr = jObj.getString("fromDate");
			String toDateStr = jObj.getString("toDate");
			Long stateId =jObj.getLong("stateId");
			if(jObj.getString("task").equalsIgnoreCase("smsPole"))
			{
				debateDetails = debateService.getDebateSMSQuestions(jObj.getString("fromDate"),jObj.getString("toDate"),stateId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("candidate"))
			{
				debateDetails = debateService.getDebateAnalysisBycandidateForScaling(sdf.parse(fromDateStr),sdf.parse(toDateStr),stateId);;
			}
			else
			{
				debateDetails = debateService.getDebateAnalysisByPartyForScaling(sdf.parse(fromDateStr),sdf.parse(toDateStr),stateId);;
			}
			
		}
		catch (Exception e)
		{
			LOG.error(" Exception occured in getDebateScalingAnalysisForPartyWise() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
	
	public String searchResultsForDebate()
	{
		try
		{
			LOG.info(" Entered into searchResultsForDebate() in DebateAction class. ");
			HttpSession session = request.getSession();
			RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
			if(regVo == null)
			return Action.ERROR;
			
			jObj = new JSONObject(getTask());
			debateDetails = debateService.getSearchriteriaForDebate(jObj.getString("searchString"));
		}
		catch (Exception e)
		{
			LOG.error(" Exception occured in searchResultsForDebate() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
	
	
	public String createCandidate()
	{
		try
		{
			LOG.info(" Entered into createCandidate() in DebateAction class. ");
			HttpSession session = request.getSession();
			RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
			if(regVo == null)
			return Action.ERROR;
			
			jObj = new JSONObject(getTask());
			resultStatus = debateService.createCandidate(jObj.getLong("partyId"), jObj.getString("name"),jObj.getLong("stateId"));
		}
		catch (Exception e)
		{
			LOG.error(" Exception occured in createCandidate() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
	public void  executeBasicDetails()
	{
		try 
		{
			LOG.info("Entered into executeBasicDetails methon in DebateAction Class");
			
			channelList = debateService.getChannelDetails();
			telecastTimeList = debateService.getTelecastTimeDetails();
			observerList = debateService.getObserverDetails();
			partiesList = candidateDetailsService.getPartiesList();
			debateQuestionList = debateService.getDebateQuestionDetails();
			debateSmsQuestionList = debateService.getDebateSmsQuestionDetails();
			debateParticipantRoleList = debateService.getDebateParticipantRoleDetails();
			characteristicsList = debateService.getCharacteristicsDetails();
			rolesList = debateService.getRolesList();
			//channelList.add(new SelectOptionVO(0l,"Select Channel"));
			telecastTimeList.add(new SelectOptionVO(0l,"Select Telecast Time"));
			observerList.add(new SelectOptionVO(0l,"Select Observer"));
			//partiesList.add(new SelectOptionVO(0l,"Select Party"));
			Collections.sort(channelList, sortList);
			Collections.sort(telecastTimeList, sortList);
			Collections.sort(observerList, sortList);
			
			ConstituencyInfoVO constituencyInfoVO = staticDataService.getConstituenciesByElectionTypeAndStateId(2L,1L);
			ConstituencyInfoVO parliamantConstis = staticDataService.getConstituenciesByElectionTypeAndStateId(1L,1L);
			districtsList1 =  staticDataService.getDistricts(1l);
			parlConstiList1 = parliamantConstis.getConstituencies();
		    assemConstiList1 = constituencyInfoVO.getConstituencies();
		     
			Collections.sort(partiesList, sortList);
		}
		catch (Exception e) 
		{
			LOG.error("Exception occured in executeBasicDetails methon in DebateAction Class",e);
		}
	}
	
	public String editDebateDetails()
	{
		try 
		{
			LOG.info("Entered into editDebateDetails methon in DebateAction Class");
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
			{
				return Action.ERROR;
			}
			executeBasicDetails();
		}
		catch (Exception e) 
		{
			LOG.error("Exception occured in editDebateDetails methon in DebateAction Class",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String deleteSelectedDEbate()
	{
		try
		{
			LOG.info(" Entered into createCandidate() in DebateAction class. ");
			HttpSession session = request.getSession();
			RegistrationVO regVo = (RegistrationVO) session.getAttribute("USER");
			if(regVo == null)
			return Action.ERROR;
			
			jObj = new JSONObject(getTask());
			status = debateService.deleteSelectedDEbate(jObj.getLong("debateId"));
		}
		catch (Exception e)
		{
			LOG.error(" Exception occured in createCandidate() in DebateAction class. ",e);
		}
		return Action.SUCCESS;
	}
	public String getPartiesListByStateId()
	{
		try {
			jObj=new JSONObject(getTask());
			partiesList1 = candidateDetailsService.getPartiesListByStateId(jObj.getLong("stateId"));
			
			return Action.SUCCESS;
		} catch (Exception e) {
			LOG.error(" Exception occured in getPartiesListByStateId() in DebateAction class. "+e);
		}
		
		
		return Action.SUCCESS;
	}
	
	public String getPartyWiseTotalDebatesAndScalesService()
	{
		try {
			jObj=new JSONObject(getTask());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			 String startDate = jObj.getString("fromDate");
			 String endDate = jObj.getString("toDate");
			
			 JSONArray channelArr = jObj.getJSONArray("channelIdArr");
			
			 List<Long> channelIds = new ArrayList<Long>(0);
				
				if( channelArr != null && channelArr.length() >0 )
				{
					for(int i=0;i<channelArr.length();i++)
					{
						if(channelArr.get(i) != null)
						{
							String channelId = channelArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(channelId.length() > 0 && !channelId.equalsIgnoreCase("0"))
							{
								channelIds.add(Long.valueOf(channelId));
							}
						}
					}
				}
				
				if(channelIds != null && channelIds.size() == 1)
				{
					Long channelId = channelIds.get(0);
					
					if(channelId.longValue() == 0L)
					{
						channelIds = null;
					}
				}
				
				else if(channelIds.size() == 0)
				{
					channelIds = null;						
				}
				
				
			 JSONArray partyArr = jObj.getJSONArray("partyIdArr");
				
				List<Long> partyIds = new ArrayList<Long>(0);
				
				if( partyArr != null && partyArr.length() >0 )
				{
					for(int i=0;i<partyArr.length();i++)
					{
						if(partyArr.get(i) != null)
						{
							String partyId = partyArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(partyId.length() > 0 && !partyId.equalsIgnoreCase("0"))
							{
								partyIds.add(Long.valueOf(partyId));
							}
						}
					}
				}
				
				if(partyIds != null && partyIds.size() == 1)
				{
					Long partyId = partyIds.get(0);
					
					if(partyId.longValue() == 0L)
					{
						partyIds = null;
					}
				}
				else if(partyIds.size() == 0)
				{
					partyIds = null;						
				}
				
			 JSONArray candidateArr = jObj.getJSONArray("candidateIdArr");
			 
			 List<Long> candidateIds = new ArrayList<Long>(0);
				
				if( candidateArr != null && candidateArr.length() >0 )
				{
					for(int i=0;i<candidateArr.length();i++)
					{
						if(candidateArr.get(i) != null)
						{
							String candidateId = candidateArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(candidateId.length() > 0 && !candidateId.equalsIgnoreCase("0"))
							{
								candidateIds.add(Long.valueOf(candidateId));
							}
						}
					}
				}
				
				if(candidateIds != null && candidateIds.size() == 1)
				{
					Long candidateId = candidateIds.get(0);
					
					if(candidateId.longValue() == 0L)
					{
						candidateIds = null;
					}
				}
				
				else if(candidateIds.size() == 0)
				{
					candidateIds = null;						
				}
				Long stateId = jObj.getLong("stateId");
				debateReportList = debateAnalysisService.getPartyCandidatePerfortmanceTopicWise(sdf.parse(startDate),sdf.parse(endDate),channelIds,partyIds,candidateIds,stateId);
				
			//debateReportList = debateAnalysisService.getPartyCandidatePerfortmanceTopicWise();
			
			return Action.SUCCESS;
		} catch (Exception e) {
			LOG.error(" Exception occured in getPartiesListByStateId() in DebateAction class. "+e);
		}
		
		
		return Action.SUCCESS;
	}
	
	public String partyWiseCandidatePerformance()
	{
		try {
			jObj=new JSONObject(getTask());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			 String startDate = jObj.getString("fromDate");
			 String endDate = jObj.getString("toDate");
			
			 JSONArray channelArr = jObj.getJSONArray("channelIdArr");
			
			 List<Long> channelIds = new ArrayList<Long>(0);
				
				if( channelArr != null && channelArr.length() >0 )
				{
					for(int i=0;i<channelArr.length();i++)
					{
						if(channelArr.get(i) != null)
						{
							String channelId = channelArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(channelId.length() > 0 && !channelId.equalsIgnoreCase("0"))
							{
								channelIds.add(Long.valueOf(channelId));
							}
						}
					}
				}
				
				if(channelIds != null && channelIds.size() == 1)
				{
					Long channelId = channelIds.get(0);
					
					if(channelId.longValue() == 0L)
					{
						channelIds = null;
					}
				}
				
				else if(channelIds.size() == 0)
				{
					channelIds = null;						
				}
				
				
			 JSONArray partyArr = jObj.getJSONArray("partyIdArr");
				
				List<Long> partyIds = new ArrayList<Long>(0);
				
				if( partyArr != null && partyArr.length() >0 )
				{
					for(int i=0;i<partyArr.length();i++)
					{
						if(partyArr.get(i) != null)
						{
							String partyId = partyArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(partyId.length() > 0 && !partyId.equalsIgnoreCase("0"))
							{
								partyIds.add(Long.valueOf(partyId));
							}
						}
					}
				}
				
				if(partyIds != null && partyIds.size() == 1)
				{
					Long partyId = partyIds.get(0);
					
					if(partyId.longValue() == 0L)
					{
						partyIds = null;
					}
				}
				else if(partyIds.size() == 0)
				{
					partyIds = null;						
				}
				
			 JSONArray candidateArr = jObj.getJSONArray("candidateIdArr");
			 
			 List<Long> candidateIds = new ArrayList<Long>(0);
				
				if( candidateArr != null && candidateArr.length() >0 )
				{
					for(int i=0;i<candidateArr.length();i++)
					{
						if(candidateArr.get(i) != null)
						{
							String candidateId = candidateArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(candidateId.length() > 0 && !candidateId.equalsIgnoreCase("0"))
							{
								candidateIds.add(Long.valueOf(candidateId));
							}
						}
					}
				}
				
				if(candidateIds != null && candidateIds.size() == 1)
				{
					Long candidateId = candidateIds.get(0);
					
					if(candidateId.longValue() == 0L)
					{
						candidateIds = null;
					}
				}
				
				else if(candidateIds.size() == 0)
				{
					candidateIds = null;						
				}
				
			Long stateId = jObj.getLong("stateId");	
			debateAnalysisList = debateAnalysisService.partyWiseCandidatePerformance(sdf.parse(startDate),sdf.parse(endDate),channelIds,partyIds,candidateIds,stateId);
			
			return Action.SUCCESS;
		} catch (Exception e) {
			LOG.error(" Exception occured in getPartiesListByStateId() in DebateAction class. "+e);
		}
		
		
		return Action.SUCCESS;
	}
	
	public String getPartyWiseOverAllPerformance()
	{
		try {  
			jObj=new JSONObject(getTask());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			 String startDate = jObj.getString("fromDate");
			 String endDate = jObj.getString("toDate");
			
			 JSONArray channelArr = jObj.getJSONArray("channelIdArr");
			
			 List<Long> channelIds = new ArrayList<Long>(0);
				
				if( channelArr != null && channelArr.length() >0 )
				{
					for(int i=0;i<channelArr.length();i++)
					{
						if(channelArr.get(i) != null)
						{
							String channelId = channelArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(channelId.length() > 0 && !channelId.equalsIgnoreCase("0"))
							{
								channelIds.add(Long.valueOf(channelId));
							}
						}
					}
				}
				
				if(channelIds != null && channelIds.size() == 1)
				{
					Long channelId = channelIds.get(0);
					
					if(channelId.longValue() == 0L)
					{
						channelIds = null;
					}
				}
				
				else if(channelIds.size() == 0)
				{
					channelIds = null;						
				}
				
				
			 JSONArray partyArr = jObj.getJSONArray("partyIdArr");
				
				List<Long> partyIds = new ArrayList<Long>(0);
				
				if( partyArr != null && partyArr.length() >0 )
				{
					for(int i=0;i<partyArr.length();i++)
					{
						if(partyArr.get(i) != null)
						{
							String partyId = partyArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(partyId.length() > 0 && !partyId.equalsIgnoreCase("0"))
							{
								partyIds.add(Long.valueOf(partyId));
							}
						}
					}
				}
				
				if(partyIds != null && partyIds.size() == 1)
				{
					Long partyId = partyIds.get(0);
					
					if(partyId.longValue() == 0L)
					{
						partyIds = null;
					}
				}
				else if(partyIds.size() == 0)
				{
					partyIds = null;						
				}
				
			 JSONArray candidateArr = jObj.getJSONArray("candidateIdArr");
			 
			 List<Long> candidateIds = new ArrayList<Long>(0);
				
				if( candidateArr != null && candidateArr.length() >0 )
				{
					for(int i=0;i<candidateArr.length();i++)
					{
						if(candidateArr.get(i) != null)
						{
							String candidateId = candidateArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(candidateId.length() > 0 && !candidateId.equalsIgnoreCase("0"))
							{
								candidateIds.add(Long.valueOf(candidateId));
							}
						}
					}
				}
				
				if(candidateIds != null && candidateIds.size() == 1)
				{
					Long candidateId = candidateIds.get(0);
					
					if(candidateId.longValue() == 0L)
					{
						candidateIds = null;
					}
				}
				
				else if(candidateIds.size() == 0)
				{
					candidateIds = null;						
				}
				
				Long stateId = jObj.getLong("stateId");
				overllPartyWiseList = debateAnalysisService.getPartyWiseOverAllPerformance(sdf.parse(startDate),sdf.parse(endDate),channelIds,partyIds,candidateIds,stateId);
			
			//overllPartyWiseList = debateAnalysisService.getPartyWiseOverAllPerformance();
			
			return Action.SUCCESS;
		} catch (Exception e) {
			LOG.error(" Exception occured in getPartiesListByStateId() in DebateAction class. "+e);
		}
		
		
		return Action.SUCCESS;
	}
	
	public String getPartyWiseStrongAndWeakTopicAndCandidates()
	{
		try {
			jObj=new JSONObject(getTask());
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

			 String startDate = jObj.getString("fromDate");
			 String endDate = jObj.getString("toDate");
			
			 JSONArray channelArr = jObj.getJSONArray("channelIdArr");
			
			 List<Long> channelIds = new ArrayList<Long>(0);
				
				if( channelArr != null && channelArr.length() >0 )
				{
					for(int i=0;i<channelArr.length();i++)
					{
						if(channelArr.get(i) != null)
						{
							String channelId = channelArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(channelId.length() > 0 && !channelId.equalsIgnoreCase("0"))
							{
								channelIds.add(Long.valueOf(channelId));
							}
						}
					}
				}
				
				if(channelIds != null && channelIds.size() == 1)
				{
					Long channelId = channelIds.get(0);
					
					if(channelId.longValue() == 0L)
					{
						channelIds = null;
					}
				}
				
				else if(channelIds.size() == 0)
				{
					channelIds = null;						
				}
				
				
			 JSONArray partyArr = jObj.getJSONArray("partyIdArr");
				
				List<Long> partyIds = new ArrayList<Long>(0);
				
				if( partyArr != null && partyArr.length() >0 )
				{
					for(int i=0;i<partyArr.length();i++)
					{
						if(partyArr.get(i) != null)
						{
							String partyId = partyArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(partyId.length() > 0 && !partyId.equalsIgnoreCase("0"))
							{
								partyIds.add(Long.valueOf(partyId));
							}
						}
					}
				}
				
				if(partyIds != null && partyIds.size() == 1)
				{
					Long partyId = partyIds.get(0);
					
					if(partyId.longValue() == 0L)
					{
						partyIds = null;
					}
				}
				else if(partyIds.size() == 0)
				{
					partyIds = null;						
				}
				
			 JSONArray candidateArr = jObj.getJSONArray("candidateIdArr");
			 
			 List<Long> candidateIds = new ArrayList<Long>(0);
				
				if( candidateArr != null && candidateArr.length() >0 )
				{
					for(int i=0;i<candidateArr.length();i++)
					{
						if(candidateArr.get(i) != null)
						{
							String candidateId = candidateArr.get(i).toString().trim().replace("[", "").replace("]", "").replace("\"", "").trim();
							
							if(candidateId.length() > 0 && !candidateId.equalsIgnoreCase("0"))
							{
								candidateIds.add(Long.valueOf(candidateId));
							}
						}
					}
				}
				
				if(candidateIds != null && candidateIds.size() == 1)
				{
					Long candidateId = candidateIds.get(0);
					
					if(candidateId.longValue() == 0L)
					{
						candidateIds = null;
					}
				}
				
				else if(candidateIds.size() == 0)
				{
					candidateIds = null;						
				}
				
				Long stateId = jObj.getLong("stateId");
				debateReportList = debateAnalysisService.getPartyWiseStrongAndWeakTopicAndCandidates(sdf.parse(startDate),sdf.parse(endDate),channelIds,partyIds,candidateIds,stateId);
			
			//debateReportList = debateAnalysisService.getPartyWiseStrongAndWeakTopicAndCandidates();
			
			return Action.SUCCESS;
		} catch (Exception e) {
			LOG.error(" Exception occured in getPartiesListByStateId() in DebateAction class. "+e);
		}
		
		
		return Action.SUCCESS;
	}
	
	public String getTotalAttendedDebatesOfCadre(){
		
		try{
			jObj = new JSONObject(getTask());
			debateVOList = debateService.getTotalAttendedDebatesOfCadre(jObj.getLong("tdpCadreId"),jObj.getLong("stateId"));
			
		}catch (Exception e) {
			LOG.error(" Exception occured in getTotalAttendedDebatesOfCadre() in DebateAction class. "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getPartyWisePerformanceOfCadre(){
		try{
			
			jObj = new JSONObject(getTask());
			
			overllPartyWiseList = debateService.getPartyWiseOverAllPerformance(jObj.getLong("tdpCadreId"),null);
			
		}catch (Exception e) {
			LOG.error(" Exception occured in getPartyWiseOverAllPerformance() in DebateAction class. "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getPartyWiseStrongAndWeakTopicOfCadre(){
		try{
			
			jObj = new JSONObject(getTask());
			
			debateReportList = debateService.getPartyWiseStrongAndWeakTopicAndCandidates(jObj.getLong("tdpCadreId"));
			
		}catch (Exception e) {
			LOG.error(" Exception occured in getPartyWiseStrongAndWeakTopicByCandidates() in DebateAction class. "+e);
		}
		return Action.SUCCESS; 
	}
	
	public String getTotalAttendedDebatesOfCadreNew(){
		try{			
			jObj = new JSONObject(getTask());			
			debateId = debateService.getTotalAttendedDebatesOfCadreNew(jObj.getLong("tdpCadreId"));
			
		}catch (Exception e) {
			LOG.error(" Exception occured in getPartyWiseStrongAndWeakTopicByCandidates() in DebateAction class. "+e);
		}
		return Action.SUCCESS; 
	}
	public String getEachCharacterWiseMaxScale(){
		try{			
			jObj = new JSONObject(getTask());			
			debateReportList = debateService.getEachCharacterWiseMaxScale();
			
		}catch (Exception e) {
			LOG.error(" Exception occured in getEachCharacterWiseMaxScale() in DebateAction class. "+e);
		}
		return Action.SUCCESS; 
	}
}
