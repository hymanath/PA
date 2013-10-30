package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.ImportantFamiliesInfoVo;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterDataVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.VoterInfo;
import com.itgrids.partyanalyst.service.ICustomVoterGroupAnalysisService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CustomVoterGroupAnalysisAction extends ActionSupport implements ServletRequestAware{

	private HttpSession session;
	
	private HttpServletRequest request;
	
	private String task;
	private InfluencingPeopleBeanVO influencingPeopleBeanVO;
	private VoterInfo voterInfo;
	JSONObject jobj;
	private static final Logger log = Logger.getLogger(CustomVoterGroupAnalysisAction.class);
	private ICustomVoterGroupAnalysisService customVoterGroupAnalysisService;
	private VotersDetailsVO votersDetailsVO;
	private List<VoterCastInfoVO> castInfoVOsList,customGroupWiseCasteVotersList;
	private Long customVoterGroupId;
	
	private List<VoterVO> votersData,votersList,voters;
	private VoterVO voterVO;
	private ConstituencyManagementVO constituencyManagementVO;
	private Long publicationDateId;
	private Long casteStateId;
	private Long casteId;
	private String casteName;
	private String casteCategoryName;
	private String groupName;
	private List<VoterHouseInfoVO> customVotersList;
	private String areaType;
	private Long locationValue;
	private VoterCastInfoVO voterCastInfoVO;
	private ImportantFamiliesInfoVo importantFamiliesInfoVo;
	private List<VotersDetailsVO> customVoterAgeCountList;
	private String locationName;
	private Long constituencyId;
	private Long attributeId;
	private String gender;
	private Long categoryValueId;
	
	private VotersInfoForMandalVO votersInfoForMandalVO;
	
	private  ImportantFamiliesInfoVo importantFamiliesDetails;
	


	public VotersInfoForMandalVO getVotersInfoForMandalVO() {
		return votersInfoForMandalVO;
	}

	public void setVotersInfoForMandalVO(VotersInfoForMandalVO votersInfoForMandalVO) {
		this.votersInfoForMandalVO = votersInfoForMandalVO;
	}

	public ImportantFamiliesInfoVo getImportantFamiliesDetails() {
		return importantFamiliesDetails;
	}

	public void setImportantFamiliesDetails(
			ImportantFamiliesInfoVo importantFamiliesDetails) {
		this.importantFamiliesDetails = importantFamiliesDetails;
	}

	public Long getCategoryValueId() {
		return categoryValueId;
	}

	public void setCategoryValueId(Long categoryValueId) {
		this.categoryValueId = categoryValueId;
	}

	public Long getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	private IVotersAnalysisService votersAnalysisService;
	private List<SelectOptionVO> groupsList;

	public List<SelectOptionVO> getGroupsList() {
		return groupsList;
	}

	public void setGroupsList(List<SelectOptionVO> groupsList) {
		this.groupsList = groupsList;
	}
	

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}
	
	public ImportantFamiliesInfoVo getImportantFamiliesInfoVo() {
		return importantFamiliesInfoVo;
	}

	public void setImportantFamiliesInfoVo(
			ImportantFamiliesInfoVo importantFamiliesInfoVo) {
		this.importantFamiliesInfoVo = importantFamiliesInfoVo;
	}

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

	public Long getPublicationDateId() {
		return publicationDateId;
	}

	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}

	
	public List<VoterCastInfoVO> getCustomGroupWiseCasteVotersList() {
		return customGroupWiseCasteVotersList;
	}

	public void setCustomGroupWiseCasteVotersList(
			List<VoterCastInfoVO> customGroupWiseCasteVotersList) {
		this.customGroupWiseCasteVotersList = customGroupWiseCasteVotersList;
	}
	public Long getCasteStateId() {
		return casteStateId;
	}

	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}

	public Long getCasteId() {
		return casteId;
	}

	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}

	public String getCasteName() {
		return casteName;
	}

	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}

	public String getCasteCategoryName() {
		return casteCategoryName;
	}

	public void setCasteCategoryName(String casteCategoryName) {
		this.casteCategoryName = casteCategoryName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<VoterHouseInfoVO> getCustomVotersList() {
		return customVotersList;
	}

	public void setCustomVotersList(List<VoterHouseInfoVO> customVotersList) {
		this.customVotersList = customVotersList;
	}
	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}

	public VoterCastInfoVO getVoterCastInfoVO() {
		return voterCastInfoVO;
	}

	public void setVoterCastInfoVO(VoterCastInfoVO voterCastInfoVO) {
		this.voterCastInfoVO = voterCastInfoVO;
	}

	public List<VotersDetailsVO> getCustomVoterAgeCountList() {
		return customVoterAgeCountList;
	}

	public void setCustomVoterAgeCountList(
			List<VotersDetailsVO> customVoterAgeCountList) {
		this.customVoterAgeCountList = customVoterAgeCountList;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String execute()
	{
		if(customVoterGroupId != null)
		 groupName = customVoterGroupAnalysisService.getGroupNameByGroupId(customVoterGroupId);
		
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
		else if(jobj.getString("task").equalsIgnoreCase("getCustomGroupWiseCasteVoters"))
			customGroupWiseCasteVotersList = customVoterGroupAnalysisService.getCustomGroupWiseVoterCasteDetails(userId,jobj.getString("areaType"),jobj.getLong("locationValue"));
		else if(jobj.getString("task").equalsIgnoreCase("getCasteWiseCustomVoters"))
			customVotersList = customVoterGroupAnalysisService.getCustomVoterDetails(jobj.getLong("casteStateId"), jobj.getLong("casteId"), jobj.getLong("customVoterGroupId"),userId);
		else if(jobj.getString("task").equalsIgnoreCase("getCustomVoterAgeDetails"))
			customVoterAgeCountList = customVoterGroupAnalysisService.getCustomVotersAgeDetails(jobj.getLong("constituencyId"), jobj.getLong("id"), jobj.getLong("publicationDateId"),jobj.getString("areaType"), userId);
		
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
		Long publicationId = request.getParameter("publicationId") != null ? Long.parseLong(request.getParameter("publicationId")):0l;
		votersList = customVoterGroupAnalysisService.getVoterDetailsForCustomVoterGroup(customerGroupId,startIndex,
				maxRecords, order, columnName,userId,publicationId);
		constituencyManagementVO.setVoterDetails(votersList);
		if(votersList != null && votersList.size() > 0)
		constituencyManagementVO.setVoterDetailsCount(votersList.get(0).getTotalVoters());
		}
		catch(Exception e)
		{
			Log.error("Exception Occured in getVoterDetailsForCustomGroup() method of CustomVoterGroupAnalysis Action", e);
		}
		return Action.SUCCESS;
	}
	
	public String getVoterDataForCustomGroup()
	{
		try {
			LOG.debug("Entered into the getVoterDataForCustomGroup() method in VotersEditAction");
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
			LOG.error("Error occured in the getVoterDataForCustomGroup() method in customVoterGroupAnalysis", e);
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
			if(jobj.getString("task").equalsIgnoreCase("getVotersCountForAttribute"))
			{
				Long casteId = jobj.getLong("casteId");
				Long publicationId = jobj.getLong("publicationDateId");
				Long categoryId = jobj.getLong("categoryValueId");
				Long locationId = jobj.getLong("locationValue");
				String areaType = jobj.getString("areaType");
				
				voterInfo = customVoterGroupAnalysisService.getTotalVotersDetailsbyCategoryAndCaste(categoryId,casteId,locationId,publicationId,areaType,userId);	
			}
			else if(jobj.getString("task").equalsIgnoreCase("getVotersCount"))
			voterInfo = customVoterGroupAnalysisService.getTotalVotersDetailsbyCustomVoterGroup(userId,jobj.getLong("customVoterGroupId"),jobj.getLong("publicationDateId"));
			
		}
			
		catch(Exception e)
		{
			Log.error("Exception Occured in getTotalVotersInfo() method of customVoterGroup analysis Action ", e);
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
			
			if(jobj.getString("task").equalsIgnoreCase("getInfluencingPeopleCount"))
			{
			Long customVoterGroupId = jobj.getLong("customVoterGroupId");
			Long publicationId = jobj.getLong("publicationDateId");
			influencingPeopleBeanVO = customVoterGroupAnalysisService.getInfluencingPeopleCount(userId,customVoterGroupId,publicationId);
			}
			else if(jobj.getString("task").equalsIgnoreCase("getInfluencingPeopleCountForCategoryAndCaste"))
			{
				Long casteId = jobj.getLong("casteId");
				Long publicationId = jobj.getLong("publicationDateId");
				Long categoryId = jobj.getLong("categoryValueId");
				Long locationId = jobj.getLong("locationValue");
				String areaType = jobj.getString("areaType");
				String gender = jobj.getString("gender");
				influencingPeopleBeanVO = customVoterGroupAnalysisService.getInfluencingPeopleCountByCategoryAndCaste(categoryId,casteId,locationId,publicationId,areaType,gender,userId);	
			}
		}
		catch(Exception e)
		{
			Log.error("Exception Occured in getInfluencingPeopleCount() of customVoterGroupAnalysis Action", e);
		}
		 return Action.SUCCESS;
	}
	

	public String getVotersDetails()
	{
		try {
			LOG.debug("Entered Into getVotersDetails() method in InflencingCadreCountsDisplayAction Class");
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
			LOG.debug("Exception Raised in getVotersDetails() method in InflencingCadreCountsDisplayAction Class");
			return ERROR;
		}
		
	}
	
	public String getCustomvoterFamilyDetails(){
		String param;
		param= getTask();
		try{
			jobj = new JSONObject(param);
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO == null)
				return ERROR;
			Long userId             = regVO.getRegistrationID();

			importantFamiliesInfoVo = customVoterGroupAnalysisService.getCustomVoterImpFamilyDetails(jobj.getLong("customVoterGroupId"),jobj.getLong("publicationDateId"),userId);
			
		}catch(Exception e){
			e.printStackTrace();
			Log.error("Exception occured in getCustomvoterFamilyDetails() Method,Exception is - "+ e);
		}

		return Action.SUCCESS;
	}

	public String getVotersCountForPartyByCustomGroup(){
		String param = null;
		param = getTask();
		System.out.println("param:"+param);	
		
		try{
			param = getTask();
			System.out.println("param:"+param);	
			jobj=new JSONObject(param);
			
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			Long userId =  regVO.getRegistrationID();
			Long custGroupId = jobj.getLong("custGroupId");
			if(jobj.getString("task").equalsIgnoreCase("getVotersCountForPartyByCustomGroup")){
				voterCastInfoVO = new VoterCastInfoVO();
				voterCastInfoVO = customVoterGroupAnalysisService.getVotersCountForPartyByCustomGroup(userId, custGroupId);
				
			}
		
		}catch (ParseException e) {
		e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	public String getVoterDetailsForAttribute()
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
			//Long categoryValueId = request.getParameter("customvoterGroupId") != null ? Long.parseLong(request.getParameter("customvoterGroupId")):0l;
			Long categoryValueId = request.getParameter("categoryValueId") != null ? Long.parseLong(request.getParameter("categoryValueId")):0l;
			Long casteId = request.getParameter("casteId") != null ? Long.parseLong(request.getParameter("casteId")):0l;
			String gender = request.getParameter("gender");
			String locationType =  request.getParameter("areaType");
			Long locationId = request.getParameter("locationValue") != null ? Long.parseLong(request.getParameter("locationValue")):0l;
			Long publicationId = request.getParameter("publicationDateId") != null ? Long.parseLong(request.getParameter("publicationDateId")):0l;
			votersList = customVoterGroupAnalysisService.getVoterDetailsForAttribute(categoryValueId,casteId,gender,startIndex,
					maxRecords, order, columnName,userId,locationType,locationId,publicationId);
			constituencyManagementVO.setVoterDetails(votersList);
			if(votersList != null && votersList.size() > 0)
			constituencyManagementVO.setVoterDetailsCount(votersList.get(0).getTotalVoters());
			}
			catch(Exception e)
			{
				Log.error("Exception Occured in getVoterDetailsForAttribute() method of CustomVoterGroupAnalysis Action", e);
			}
			return Action.SUCCESS;
		
	}
	
	public String getVoterDataForAttribute()
	{
		try {
			LOG.debug("Entered into the getVoterDataForCustomGroup() method in VotersEditAction");
			//jObj = new JSONObject(param);
			session = request.getSession();
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			Long userId = null;
			if(user != null && user.getRegistrationID() != null)
			userId = user.getRegistrationID();
			Long categoryValueId = request.getParameter("categoryValueId") != null ? Long.parseLong(request.getParameter("categoryValueId")):0l;
			Long casteId = request.getParameter("casteId") != null ? Long.parseLong(request.getParameter("casteId")):0l;
			String gender = request.getParameter("gender");
			String startIndex = request.getParameter("startIndex");
			String maxIndex = request.getParameter("results");
			String sort = request.getParameter("sort");
			String dir = request.getParameter("dir");
			String locationType =  request.getParameter("areaType");
			Long locationId = request.getParameter("locationValue") != null ? Long.parseLong(request.getParameter("locationValue")):0l;
			Long publicationId = request.getParameter("publicationDateId") != null ? Long.parseLong(request.getParameter("publicationDateId")):0l;
			VoterDataVO voterDataVO = new VoterDataVO();
			voterDataVO.setGender(gender);
			voterDataVO.setCategoryId(categoryValueId);
			voterDataVO.setCasteId(casteId);
			voterDataVO.setStartIndex(Long.valueOf(startIndex));
			voterDataVO.setMaxIndex(Long.valueOf(maxIndex));
			voterDataVO.setDir(dir);
			voterDataVO.setBuildType(locationType);
			voterDataVO.setId(locationId);
			voterDataVO.setPublicationId(publicationId);
			
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
		votersData = customVoterGroupAnalysisService.getVoterDataForAttribute(voterDataVO , userId , categories);
			voterVO = new VoterVO();
			if(votersData != null && votersData.size() > 0)
			{
				voterVO.setVotersList(votersData);
				voterVO.setTotalVoters(votersData.get(0).getTotalVoters());
			}
			
		} catch (Exception e) {
			LOG.error("Error occured in the getVoterDataForCustomGroup() method in customVoterGroupAnalysis", e);
		}
	   return Action.SUCCESS;
	}
	
	public String getVotersDetailsForAttribute()
	{
		try {
			LOG.debug("Entered Into getVotersDetails() method in InflencingCadreCountsDisplayAction Class");
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
			Long userVoterCategoryValue =request.getParameter("userVoterCategoryValue")!= null ?Long.parseLong(request.getParameter("userVoterCategoryValue") ): 0l;
			Long publicationDateId =request.getParameter("publicationDateId")!= null ?Long.parseLong(request.getParameter("publicationDateId") ): 0l; 
			String btnName = request.getParameter("btnName");
			Long locationValue = request.getParameter("locationValue")!= null ?Long.parseLong(request.getParameter("locationValue") ): 0l;
			Long casteId = request.getParameter("casteId")!= null ?Long.parseLong(request.getParameter("casteId") ): 0l;
			String gender = request.getParameter("gender");
			String areaType = request.getParameter("maintype");
			VoterDataVO voterDataVO = new VoterDataVO();
			voterDataVO.setCategoryId(userVoterCategoryValue);
			voterDataVO.setPublicationId(publicationDateId);
			voterDataVO.setGender(gender);
			voterDataVO.setCasteId(casteId);
			voterDataVO.setBuildType(areaType);
			voterDataVO.setId(locationValue);
			voterDataVO.setStartIndex(Long.valueOf(startIndex));
			voterDataVO.setMaxIndex(Long.valueOf(maxRecords));
			voterDataVO.setDir(order);
			voterDataVO.setSort(columnName);
			
			voters = customVoterGroupAnalysisService.showVoterDetailsForSelcetedTypeByCasteAndCategoryId(userId,voterDataVO,btnName);
			constituencyManagementVO = new ConstituencyManagementVO();
			constituencyManagementVO.setVoterDetails(voters);
			constituencyManagementVO.setVoterDetailsCount(0l);
			if(voters != null && voters.size() > 0 )
			{
				constituencyManagementVO.setVoterDetailsCount(voters.get(0).getTotalVoters());
			}
			return Action.SUCCESS;
		} catch (Exception e) {
			LOG.debug("Exception Raised in getVotersDetails() method in InflencingCadreCountsDisplayAction Class");
			return ERROR;
		}
		
	}
	public String getCustomVoterGroups()
	{
		
		String param = null;
		param = getTask();
		try {
			jobj = new JSONObject(param);
			System.out.println(jobj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		Long constituencyId = jobj.getLong("constituencyId");
		Long id = jobj.getLong("id");
		String groupType = jobj.getString("groupType");
		Long userId = user.getRegistrationID();
		
		groupsList = customVoterGroupAnalysisService.getCustomVoterGroups(constituencyId,id,groupType,userId);
		
		return Action.SUCCESS;
		
	}
	
	public String getVotersCountInfoActionForCustomVoterGroup()
	{
		
		try
		{
			String param = null;
			param = getTask();
				jobj = new JSONObject(param);
				
				HttpSession session = request.getSession();
				RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null)
					return ERROR;
			
				votersInfoForMandalVO = customVoterGroupAnalysisService.getCustomGroupWiseVoterDetailsForAMAndalOrMuncipality(jobj.getLong("id") , user.getRegistrationID());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return Action.SUCCESS;
		
	}
	
	public String showNewsGallariesAction()
	{
		return Action.SUCCESS;
		
	}
	
	public String getCustomVoterFamilyDetailsForMandalOrMuncipality()
	{
		try
		{
			String param = null;
			param = getTask();
				jobj = new JSONObject(param);
				
				HttpSession session = request.getSession();
				RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null)
					return ERROR;
		
				importantFamiliesDetails = customVoterGroupAnalysisService
				.getCustomVoterFamilyDetailsForMandalOrMuncipality(jobj.getLong("locationValue"), user.getRegistrationID());
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		return Action.SUCCESS;
		
	}
	
	public String getCustomVoterGroupsFamilyDetails()
	{
		
		return Action.SUCCESS;
	}
	
}
