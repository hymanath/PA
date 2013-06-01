package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.service.impl.CustomVoterGroupAnalysisService;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.service.ICustomVoterGroupAnalysisService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CustomVoterGroupAnalysisAction extends ActionSupport implements ServletRequestAware{

	private HttpSession session;
	
	private HttpServletRequest request;
	
	private String task;
	private InfluencingPeopleBeanVO influencingPeopleBeanVO;
	private VoterInfo voterInfo;
	JSONObject jobj;
	private static final Logger log = Logger.getLogger(VotersAnalysisAction.class);
	private ICustomVoterGroupAnalysisService customVoterGroupAnalysisService;
	private VotersDetailsVO votersDetailsVO;
	private List<VoterCastInfoVO> castInfoVOsList;
	private Long customVoterGroupId;
	private List<VoterVO> votersData,votersList,voters;
	private VoterVO voterVO;
	private ConstituencyManagementVO constituencyManagementVO;
	
	@Override
	public void setServletRequest(HttpServletRequest arg) {
		this.request = arg;
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

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getJobj() {
		return jobj;
	}

	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}
	
	public ICustomVoterGroupAnalysisService getCustomVoterGroupAnalysisService() {
		return customVoterGroupAnalysisService;
	}

	public void setCustomVoterGroupAnalysisService(
			ICustomVoterGroupAnalysisService customVoterGroupAnalysisService) {
		this.customVoterGroupAnalysisService = customVoterGroupAnalysisService;
	}

	public VotersDetailsVO getVotersDetailsVO() {
		return votersDetailsVO;
	}

	public void setVotersDetailsVO(VotersDetailsVO votersDetailsVO) {
		this.votersDetailsVO = votersDetailsVO;
	}

	public List<VoterCastInfoVO> getCastInfoVOsList() {
		return castInfoVOsList;
	}

	public void setCastInfoVOsList(List<VoterCastInfoVO> castInfoVOsList) {
		this.castInfoVOsList = castInfoVOsList;
	}
	
	public Long getCustomVoterGroupId() {
		return customVoterGroupId;
	}

	public void setCustomVoterGroupId(Long customVoterGroupId) {
		this.customVoterGroupId = customVoterGroupId;
	}

	public InfluencingPeopleBeanVO getInfluencingPeopleBeanVO() {
		return influencingPeopleBeanVO;
	}

	public void setInfluencingPeopleBeanVO(
			InfluencingPeopleBeanVO influencingPeopleBeanVO) {
		this.influencingPeopleBeanVO = influencingPeopleBeanVO;
	}

	public VoterInfo getVoterInfo() {
		return voterInfo;
	}

	public void setVoterInfo(VoterInfo voterInfo) {
		this.voterInfo = voterInfo;
	}

	public List<VoterVO> getVotersData() {
		return votersData;
	}

	public void setVotersData(List<VoterVO> votersData) {
		this.votersData = votersData;
	}

	public VoterVO getVoterVO() {
		return voterVO;
	}

	public void setVoterVO(VoterVO voterVO) {
		this.voterVO = voterVO;
	}

	public ConstituencyManagementVO getConstituencyManagementVO() {
		return constituencyManagementVO;
	}

	public void setConstituencyManagementVO(
			ConstituencyManagementVO constituencyManagementVO) {
		this.constituencyManagementVO = constituencyManagementVO;
	}

	public List<VoterVO> getVotersList() {
		return votersList;
	}

	public void setVotersList(List<VoterVO> votersList) {
		this.votersList = votersList;
	}

	public List<VoterVO> getVoters() {
		return voters;
	}

	public void setVoters(List<VoterVO> voters) {
		this.voters = voters;
	}

	public String execute()
	{
		return ActionSupport.SUCCESS;
	}
	
	public String ajaxHandler()
	{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
		 return ERROR;
		Long userId = user.getRegistrationID();
		try{
		  jobj = new JSONObject(getTask());
		}catch (Exception e) {
		  e.printStackTrace();
		  Log.error("Exception Occured in ajaxHandler() method, Exception - "+e);
		}
		if(jobj.getString("task").equalsIgnoreCase("getCasteWiseCustomVotersCount"))
			castInfoVOsList = customVoterGroupAnalysisService.getCasteWiseCustomVotersCount(jobj.getLong("customVoterGroupId"),userId);
		
		return ActionSupport.SUCCESS;
	}
	public String getAgeWiseCustomVotersInGroup(){
		Long userId =null;
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		else  
			userId= user.getRegistrationID();
		String param;
		param = getTask();
		
		votersDetailsVO=new VotersDetailsVO();
		try{
			jobj = new JSONObject(param);
			Long customGroupId = Long.parseLong(jobj.getString("customGroupId"));
			Long publicationDateId=Long.parseLong(jobj.getString("publicationDateId"));
			votersDetailsVO=customVoterGroupAnalysisService.getAgeWiseCustomVotersInGroup(userId,customGroupId,publicationDateId);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception Occured in getAgeWiseCustomVotersInGroup() Method,Exception is- ",e);
		}
		
		return SUCCESS;
	}

	public String getVoterDetailsForCustomGroup()
	{
		try{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		Long userId = user.getRegistrationID();
		Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
		String order = request.getParameter("dir");
		String columnName = request.getParameter("sort");
		Integer maxRecords = Integer.parseInt(request.getParameter("results"));
		List<VoterVO> votersList = null;
		constituencyManagementVO = new ConstituencyManagementVO();
		Long customerGroupId = request.getParameter("customvoterGroupId") != null ? Long.parseLong(request.getParameter("customvoterGroupId")):0l;
		//Long publicationId = request.getParameter("publicationId") != null ? 0l:Long.parseLong(request.getParameter("publicationId"));
		votersList = customVoterGroupAnalysisService.getVoterDetailsForCustomVoterGroup(1l,startIndex,
				maxRecords, order, columnName,userId,8l);
		constituencyManagementVO.setVoterDetails(votersList);
		if(votersList != null && votersList.size() > 0)
		constituencyManagementVO.setVoterDetailsCount(votersList.get(0).getTotalVoters());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getVoterDataForCustomGroup()
	{
		try {
			LOG.debug("Entered into the getvoterData() method in VotersEditAction");
			//jObj = new JSONObject(param);
			session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			Long userId = null;
			if(user != null && user.getRegistrationID() != null)
			userId = user.getRegistrationID();
			Long customVoterGroupId = request.getParameter("customVoterGroupId")!=null ?Long.parseLong(request.getParameter("customVoterGroupId")):0l;
			String publicationId = request.getParameter("publicationId");
			String startIndex = request.getParameter("startIndex");
			String maxIndex = request.getParameter("results");
			String sort = request.getParameter("sort");
			String dir = request.getParameter("dir");
			VoterDataVO voterDataVO = new VoterDataVO();
			voterDataVO.setPublicationId(Long.valueOf(publicationId));
			
			voterDataVO.setStartIndex(Long.valueOf(startIndex));
			voterDataVO.setMaxIndex(Long.valueOf(maxIndex));
			voterDataVO.setDir(dir);
			voterDataVO.setCustomVoterGroupId(customVoterGroupId);
			
			voterDataVO.setSort(sort);
			List<Long> categories = new ArrayList<Long>();
			if(request.getParameter("reqfields").trim().length() >0){
				String[] idsArray = request.getParameter("reqfields").trim().split(",");
			    for(String id : idsArray){
                   if(id.equalsIgnoreCase("party")){
                	   voterDataVO.setPartyPresent(true);
			    	}else if(id.equalsIgnoreCase("cast")){
			    		voterDataVO.setCastePresent(true);
			    	}
			    	else{
			    		categories.add(new Long(id));
			    	}
			    }
			} 
		votersData = customVoterGroupAnalysisService.getVoterDataForCustomGroup(voterDataVO , userId , categories);
			voterVO = new VoterVO();
			if(votersData != null && votersData.size() > 0)
			{
				voterVO.setVotersList(votersData);
				voterVO.setTotalVoters(votersData.get(0).getTotalVoters());
			}
			
		} catch (Exception e) {
			LOG.error("Error occured in the getvoterData() method in VotersEditAction", e);
		}
	   return Action.SUCCESS;
	}
	
	public String getTotalVotersInfo()
	{
		try{
			jobj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			if(user == null)
				return ERROR;
			Long userId = null;
			if(user != null && user.getRegistrationID() != null)
			userId = user.getRegistrationID();
			
			voterInfo = customVoterGroupAnalysisService.getTotalVotersDetailsbyCustomVoterGroup(userId,jobj.getLong("customVoterGroupId"),jobj.getLong("publicationDateId"));
			
		}
			
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 return Action.SUCCESS;
	}
	
	public String getInfluencingPeopleCount()
	{
		try{
			String param;
			param = getTask();
			jobj = new JSONObject(param);
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			Long userId =  regVO.getRegistrationID();
			Long customVoterGroupId = jobj.getLong("customVoterGroupId");
			Long publicationId = jobj.getLong("publicationDateId");
			influencingPeopleBeanVO = customVoterGroupAnalysisService.getInfluencingPeopleCount(userId,customVoterGroupId,publicationId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 return Action.SUCCESS;
	}
	

	public String getVotersDetails()
	{
		try {
			LOG.debug("Entered Into getInfluencingPeopleVotersDetails() method in InflencingCadreCountsDisplayAction Class");
			HttpSession session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			if(user == null)
				return ERROR;
			String param;
			long userId = user.getRegistrationID();
			param = getTask();
			Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
			String order = request.getParameter("dir");
			String columnName = request.getParameter("sort");
			Integer maxRecords = Integer.parseInt(request.getParameter("results"));
			Long customVoterGroupId =request.getParameter("customVoterGroupId")!= null ?Long.parseLong(request.getParameter("customVoterGroupId") ): 0l;
			
			Long publicationDateId =request.getParameter("publicationDateId")!= null ?Long.parseLong(request.getParameter("publicationDateId") ): 0l; 
			String btnName = request.getParameter("btnName");
			//voters = customVoterGroupAnalysisService.showVoterDetailsForSelcetedType(userId,customVoterGroupId,publicationDateId,btnName,startIndex,maxRecords,order,columnName);
			voters = customVoterGroupAnalysisService.showVoterDetailsForSelcetedType(userId,customVoterGroupId,publicationDateId,btnName,startIndex,maxRecords,order,columnName);
			constituencyManagementVO = new ConstituencyManagementVO();
			constituencyManagementVO.setVoterDetails(voters);
			constituencyManagementVO.setVoterDetailsCount(0l);
			if(voters != null && voters.size() > 0 )
			{
				constituencyManagementVO.setVoterDetailsCount(voters.get(0).getTotalVoters());
			}
			return Action.SUCCESS;
		} catch (Exception e) {
			LOG.debug("Exception Raised in getInfluencingPeopleVotersDetails() method in InflencingCadreCountsDisplayAction Class");
			return ERROR;
		}
		
	}

}
