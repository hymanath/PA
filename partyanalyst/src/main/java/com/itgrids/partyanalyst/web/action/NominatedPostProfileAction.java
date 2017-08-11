package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AddNotcadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadreEventsVO;
import com.itgrids.partyanalyst.dto.CadrePerformanceVO;
import com.itgrids.partyanalyst.dto.CastePositionVO;
import com.itgrids.partyanalyst.dto.EventDetailsVO;
import com.itgrids.partyanalyst.dto.EventFileUploadVO;
import com.itgrids.partyanalyst.dto.GeoLevelListVO;
import com.itgrids.partyanalyst.dto.GeoLevelReportVO;
import com.itgrids.partyanalyst.dto.GovtOrderVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.LocationsVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;
import com.itgrids.partyanalyst.dto.NominatedPostDetailsVO;
import com.itgrids.partyanalyst.dto.NominatedPostReferVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
import com.itgrids.partyanalyst.dto.NomintedPostMemberVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.INominatedPostMainDashboardService;
import com.itgrids.partyanalyst.service.INominatedPostProfileService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class NominatedPostProfileAction extends ActionSupport implements ServletRequestAware{
	
	private static final Logger LOG = Logger.getLogger(NominatedPostProfileAction.class);
	private HttpServletRequest 					request;
	private HttpSession 						session;
	private String 								task;
	private JSONObject							jObj;
	private INominatedPostProfileService        nominatedPostProfileService;
	private String 								status;
	private List<IdNameVO> 						idNameVOList;
	private List<NominatedPostVO> 				nominatePostList;
	private NomintedPostMemberVO 				nomintedPostMemberVO;
	private NominatedPostVO                     nominatedPostVO;
	private ResultStatus 						resultStatus;
	private InputStream 						inputStream;
	private List<LocationWiseBoothDetailsVO> 	locations;
	private ICadreCommitteeService				cadreCommitteeService;
	private AddNotcadreRegistrationVO 			addNotcadreRegistrationVO;
	private List<NominatedPostVO> 				candidatesList;
	private List<CadreCommitteeVO>              cadreCommitteeVOList;
	private NominatedPostReferVO 				nominatedPostReferVO;
	private IdNameVO							idNameVO;
	private List<CastePositionVO> castePositionVOList;
	private INominatedPostMainDashboardService        nominatedPostMainDashboardService;
	private NominatedPostDashboardVO nominatedPostDashboardVO;
	private Long lId;
	private Long stId;
	private String sts;
	private Long deptId;
	private Long boardId;
	private Long positionId;
	private Long searchLevelId;
	private Long searchLevelValue;
	private List<IdAndNameVO> idAndNameVOList;
	private List<NominatedPostDashboardVO> nominatedPostDashboardvoList;
	private List<NominatedPostVO> nominatedPostVOs;
	private CastePositionVO castePositionVO;
	private GovtOrderVO govtOrderVO;
	private List<NomintedPostMemberVO> nominatedPostMemberVOs; 
	private Long nominationPostCandidateId;
	private Long tdpCadreId;
	private Long applicationId;
	private CadrePerformanceVO cadrePerformanceVO;
	private List<LocationsVO> locationVoList;
	private List<GeoLevelListVO> geoLevelListVO;
	private List<CadreEventsVO> cadreReportVO;
	private List<CadrePerformanceVO> cadreTraingVO;
	private List<EventDetailsVO> eventDetilsList;
	private NominatedPostDetailsVO nominatedPostDetailsVO = new NominatedPostDetailsVO();
	

	public NominatedPostDetailsVO getNominatedPostDetailsVO() {
		return nominatedPostDetailsVO;
	}
	public void setNominatedPostDetailsVO(
			NominatedPostDetailsVO nominatedPostDetailsVO) {
		this.nominatedPostDetailsVO = nominatedPostDetailsVO;
	}
	public List<GeoLevelListVO> getGeoLevelListVO() {
		return geoLevelListVO;
	}
	public void setGeoLevelListVO(List<GeoLevelListVO> geoLevelListVO) {
		this.geoLevelListVO = geoLevelListVO;
	}
	public CadrePerformanceVO getCadrePerformanceVO() {
		return cadrePerformanceVO;
	}
	public void setCadrePerformanceVO(CadrePerformanceVO cadrePerformanceVO) {
		this.cadrePerformanceVO = cadrePerformanceVO;
	}
	public GovtOrderVO getGovtOrderVO() {
		return govtOrderVO;
	}
	public void setGovtOrderVO(GovtOrderVO govtOrderVO) {
		this.govtOrderVO = govtOrderVO;
	}
	public List<NominatedPostDashboardVO> getNominatedPostDashboardvoList() {
		return nominatedPostDashboardvoList;
	}
	public void setNominatedPostDashboardvoList(
			List<NominatedPostDashboardVO> nominatedPostDashboardvoList) {
		this.nominatedPostDashboardvoList = nominatedPostDashboardvoList;
	}
	public Long getSearchLevelId() {
		return searchLevelId;
	}
	public void setSearchLevelId(Long searchLevelId) {
		this.searchLevelId = searchLevelId;
	}
	public Long getSearchLevelValue() {
		return searchLevelValue;
	}
	public void setSearchLevelValue(Long searchLevelValue) {
		this.searchLevelValue = searchLevelValue;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	public IdNameVO getIdNameVO() {
		return idNameVO;
	}
	public void setIdNameVO(IdNameVO idNameVO) {
		this.idNameVO = idNameVO;
	}
	public NominatedPostReferVO getNominatedPostReferVO() {
		return nominatedPostReferVO;
	}
	public void setNominatedPostReferVO(NominatedPostReferVO nominatedPostReferVO) {
		this.nominatedPostReferVO = nominatedPostReferVO;
	}
	public NominatedPostDashboardVO getNominatedPostDashboardVO() {
		return nominatedPostDashboardVO;
	}
	public void setNominatedPostDashboardVO(
			NominatedPostDashboardVO nominatedPostDashboardVO) {
		this.nominatedPostDashboardVO = nominatedPostDashboardVO;
	}
	public String getSts() {
		return sts;
	}
	public void setSts(String sts) {
		this.sts = sts;
	}
	
	public Long getlId() {
		return lId;
	}
	public void setlId(Long lId) {
		this.lId = lId;
	}
	public Long getStId() {
		return stId;
	}
	public void setStId(Long stId) {
		this.stId = stId;
	}
	public List<CastePositionVO> getCastePositionVOList() {
		return castePositionVOList;
	}
	public void setCastePositionVOList(List<CastePositionVO> castePositionVOList) {
		this.castePositionVOList = castePositionVOList;
	}
	public INominatedPostMainDashboardService getNominatedPostMainDashboardService() {
		return nominatedPostMainDashboardService;
	}
	public void setNominatedPostMainDashboardService(
			INominatedPostMainDashboardService nominatedPostMainDashboardService) {
		this.nominatedPostMainDashboardService = nominatedPostMainDashboardService;
	}
	public List<CadreCommitteeVO> getCadreCommitteeVOList() {
		return cadreCommitteeVOList;
	}
	public void setCadreCommitteeVOList(List<CadreCommitteeVO> cadreCommitteeVOList) {
		this.cadreCommitteeVOList = cadreCommitteeVOList;
	}
	
	
	public AddNotcadreRegistrationVO getAddNotcadreRegistrationVO() {
		return addNotcadreRegistrationVO;
	}
	public void setAddNotcadreRegistrationVO(
			AddNotcadreRegistrationVO addNotcadreRegistrationVO) {
		this.addNotcadreRegistrationVO = addNotcadreRegistrationVO;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	public List<LocationWiseBoothDetailsVO> getLocations() {
		return locations;
	}
	public void setLocations(List<LocationWiseBoothDetailsVO> locations) {
		this.locations = locations;
	}
	
	public List<NominatedPostVO> getNominatePostList() {
		return nominatePostList;	}
	public void setNominatePostList(List<NominatedPostVO> nominatePostList) {
		this.nominatePostList = nominatePostList;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public NominatedPostVO getNominatedPostVO() {
		return nominatedPostVO;
	}
	public void setNominatedPostVO(NominatedPostVO nominatedPostVO) {
		this.nominatedPostVO = nominatedPostVO;
	}
	public NomintedPostMemberVO getNomintedPostMemberVO() {
		return nomintedPostMemberVO;
	}
	public void setNomintedPostMemberVO(NomintedPostMemberVO nomintedPostMemberVO) {
		this.nomintedPostMemberVO = nomintedPostMemberVO;
	}
	public List<IdNameVO> getIdNameVOList() {
		return idNameVOList;
	}
	public void setIdNameVOList(List<IdNameVO> idNameVOList) {
		this.idNameVOList = idNameVOList;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public INominatedPostProfileService getNominatedPostProfileService() {
		return nominatedPostProfileService;
	}
	public void setNominatedPostProfileService(
			INominatedPostProfileService nominatedPostProfileService) {
		this.nominatedPostProfileService = nominatedPostProfileService;
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
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public static Logger getLog() {
		return LOG;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	public List<NomintedPostMemberVO> getNominatedPostMemberVOs() {
		return nominatedPostMemberVOs;
	}
	public void setNominatedPostMemberVOs(
			List<NomintedPostMemberVO> nominatedPostMemberVOs) {
		this.nominatedPostMemberVOs = nominatedPostMemberVOs;
	}
	
	public Long getNominationPostCandidateId() {
		return nominationPostCandidateId;
	}
	public void setNominationPostCandidateId(Long nominationPostCandidateId) {
		this.nominationPostCandidateId = nominationPostCandidateId;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	
	public List<LocationsVO> getLocationVoList() {
		return locationVoList;
	}
	public void setLocationVoList(List<LocationsVO> locationVoList) {
		this.locationVoList = locationVoList;
	}
	public List<CadreEventsVO> getCadreReportVO() {
		return cadreReportVO;
	}
	public void setCadreReportVO(List<CadreEventsVO> cadreReportVO) {
		this.cadreReportVO = cadreReportVO;
	}
	public List<EventDetailsVO> getEventDetilsList() {
		return eventDetilsList;
	}
	public void setEventDetilsList(List<EventDetailsVO> eventDetilsList) {
		this.eventDetilsList = eventDetilsList;
	}
	public String nominatedPosts()
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("CREATE_NOMINATED_POST_ENTITLEMENT") || entitlements.contains("CREATE_NOMINATED_POST_ENTITLEMENT_ADMIN_GROUP")
					 || entitlements.contains("NOMINATED_POST_OVERVIEW_ENTITLEMENT"))){
				noaccess = true ;
			}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		}		
		return Action.SUCCESS;
	}
	public List<IdAndNameVO> getIdAndNameVOList() {
		return idAndNameVOList;
	}
	public void setIdAndNameVOList(List<IdAndNameVO> idAndNameVOList) {
		this.idAndNameVOList = idAndNameVOList;
	}
	public List<NominatedPostVO> getCandidatesList() {
		return candidatesList;
	}
	public void setCandidatesList(List<NominatedPostVO> candidatesList) {
		this.candidatesList = candidatesList;
	}
	
	public List<NominatedPostVO> getNominatedPostVOs() {
		return nominatedPostVOs;
	}
	public void setNominatedPostVOs(List<NominatedPostVO> nominatedPostVOs) {
		this.nominatedPostVOs = nominatedPostVOs;
	}
	public CastePositionVO getCastePositionVO() {
		return castePositionVO;
	}
	public void setCastePositionVO(CastePositionVO castePositionVO) {
		this.castePositionVO = castePositionVO;
	}
	public List<CadrePerformanceVO> getCadreTraingVO() {
		return cadreTraingVO;
	}
	public void setCadreTraingVO(List<CadrePerformanceVO> cadreTraingVO) {
		this.cadreTraingVO = cadreTraingVO;
	}
	public String getBoardLevels(){
		try{
			
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getBoardLevels();
			
		}catch (Exception e) {
			LOG.error("Entered into getBoardLevels Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getDepartments(){
		try{
			
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getDepartments(jObj.getLong("postType"),jObj.getLong("boardLevelId"),jObj.getLong("searchLevelValue"),
					jObj.getLong("searchLevelId"),jObj.getLong("positionId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getDepartments Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getDepartmentBoard(){
		try{
			
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getDepartmentBoard(jObj.getLong("depmtId"),jObj.getLong("boardLevelId"),jObj.getLong("searchLevelValue"),
					jObj.getLong("searchLevelId"),jObj.getLong("applicationId"),jObj.getLong("positionId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getDepartmentBoard Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getDepartmentBoardPositions(){
		try{
			
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getDepartmentBoardPositions(jObj.getLong("depmtId"),jObj.getLong("boardId"),jObj.getLong("boardLevelId"),jObj.getLong("searchLevelValue"),
					jObj.getLong("searchLevelId"),jObj.getLong("nominatedPostCandId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getDepartmentBoard Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getNominatedPostMemberDetails(){
		try{
			
			jObj = new JSONObject(getTask());
			
			nomintedPostMemberVO = nominatedPostProfileService.getNominatedPostMemberDetails(jObj.getLong("levelId"),jObj.getLong("levelValue"),jObj.getLong("departmentId"),
															jObj.getLong("boardId"),jObj.getLong("positionId"),jObj.getString("type"),jObj.getLong("searchLevelId"),jObj.getLong("statusId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getNominatedPostMemberDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String boardWiseNominatedPosts(){
		return Action.SUCCESS;
	}
	
	public String updateApplicationStatusDetails(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			Long userId = regVO.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			status = nominatedPostProfileService.updateApplicationStatusDetails(userId,jObj.getLong("nominatePostApplicationId"),jObj.getLong("statusId"),jObj.getString("comment"),
						jObj.getLong("levelId"),jObj.getLong("levelVal"),jObj.getLong("deptId"),jObj.getLong("boardId"),jObj.getLong("positionId"),jObj.getLong("candidateId"));
			
		}catch (Exception e) {
			LOG.error("Entered into updateApplicationStatusDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String savingNominatedPostProfileApplication(){
		try {
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			
			Map<File,String> mapfiles = new HashMap<File,String>();
			MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
		       Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
		       String fileUrl = "" ;
		       List<String> filePaths = null;
		   		while(fileParams.hasMoreElements())
		   		{
		   			String key = fileParams.nextElement();
		   			
				   			File[] files = multiPartRequestWrapper.getFiles(key);
				   			filePaths = new ArrayList<String>();
				   			int i = 0;
				   			if(files != null && files.length > 0)
				   			for(File f : files)
				   			{
				   				String[] extension  =multiPartRequestWrapper.getFileNames(key)[i].split("\\.");
				   	            String ext = "";
				   	            if(extension.length > 1){
				   	            	ext = extension[extension.length-1];
				   	            	mapfiles.put(f,ext);
				   	            	i++;
				   	            }
				   	        
				   			}
		   		}
		     
			resultStatus = nominatedPostProfileService.savingNominatedPostProfileApplication(nominatedPostVO,user.getRegistrationID(),mapfiles);
             
			if(resultStatus!=null){
				if(resultStatus.getResultCode() == 0){
					inputStream = new StringBufferInputStream(resultStatus.getMessage());
				}else if(resultStatus.getResultCode() == 1){
					inputStream = new StringBufferInputStream(resultStatus.getMessage());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised at savingNominatedPostProfileApplication", e);
		}
		
		return Action.SUCCESS;
	}
	public String savechangeAddressForNominatedPost()
	{
		try
		{
			jObj = new JSONObject(getTask());
		    NominatedPostVO Vo = new NominatedPostVO();
		    Vo.setId(jObj.getLong("tdpCadreId"));
		    Vo.setHno(jObj.getString("houseNo"));
		    Vo.setMobileNo(jObj.getString("mobileNo"));
		    Vo.setAddress1(jObj.getString("addressVal"));
		    Vo.setAddress2(jObj.getString("addressValue"));
		    Vo.setPincode(jObj.getString("pincode"));
		    Vo.setStateId(jObj.getLong("stateId"));
		    Vo.setDistrictId(jObj.getLong("districtId"));
		    Vo.setConstituencyId(jObj.getLong("constituencyId"));
		    Vo.setPanchayatId(jObj.getLong("panchayatId"));
		    Vo.setMandalId(jObj.getLong("mandalId"));
		    Vo.setNominatedCandId(jObj.getLong("nominatedCandidateId"));
		    
			status = nominatedPostProfileService.savechangeAddressForNominatedPost(Vo);
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in savechangeAddressForNominatedPost() in NominatedPostProfileAction ",e);
		}
		return Action.SUCCESS;
	}
	
	
	// adding new method
	public String saveNotCadreDetails()
	{
		try
		{
			
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			Map<File,String> mapfiles = new HashMap<File,String>();
			MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
		       Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
		       String fileUrl = "" ;
		       List<String> filePaths = null;
		   		while(fileParams.hasMoreElements())
		   		{
		   			String key = fileParams.nextElement();
		   			
				   			File[] files = multiPartRequestWrapper.getFiles(key);
				   			filePaths = new ArrayList<String>();
				   			if(files != null && files.length > 0)
				   			for(File f : files)
				   			{
				   				String[] extension  =multiPartRequestWrapper.getFileNames(key)[0].split("\\.");
				   	            String ext = "";
				   	            if(extension.length > 1){
				   	            	ext = extension[extension.length-1];
				   	            	mapfiles.put(f,ext);
				   	            }
				   	        
				   			}
		   		}
		     
			resultStatus = nominatedPostProfileService.saveNotcadreRegistrationPost(addNotcadreRegistrationVO,mapfiles,user.getRegistrationID());
			if(resultStatus!=null){
				if(resultStatus.getResultCode() == 0){
					inputStream = new StringBufferInputStream(resultStatus.getMessage());
				}else if(resultStatus.getResultCode() == 1){
					inputStream = new StringBufferInputStream(resultStatus.getMessage());
				}
			}
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in savechangeAddressForNominatedPost() in NominatedPostProfileAction ",e);
		}
		return Action.SUCCESS;
	}
	
	
	
	public String getPopulateApplicantDetailsForMember()
	{
		try
		{
			jObj = new JSONObject(getTask());
			Long tdpCadreId = jObj.getLong("globalCadreId");
			String searchType = jObj.getString("searchType");
			//Long nominateCandId = jObj.getLong("nominateCandId");
			nominatePostList = nominatedPostProfileService.getApplicantDetailsForMember(tdpCadreId,searchType);
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in savechangeAddressForNominatedPost() in NominatedPostProfileAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDistrictsForStateForNominated()
	{
		try
		{
		jObj = new JSONObject(getTask());
		Long stateId = jObj.getLong("stateId");
		idNameVOList = nominatedPostProfileService.getDistrictsForState(stateId);
		}catch(Exception e)
		{
			LOG.error("Exception Occured in savechangeAddressForNominatedPost() in NominatedPostProfileAction ",e);
		}
	 return Action.SUCCESS;
	}
	public String getVillagesForMandalId()
	{
		try
		{
		jObj = new JSONObject(getTask());
		Long mandalId = jObj.getLong("mandalId");
		idNameVOList = nominatedPostProfileService.getVillagesForMandalId(mandalId);
		}catch(Exception e)
		{
			LOG.error("Exception Occured in savechangeAddressForNominatedPost() in NominatedPostProfileAction ",e);
		}
	 return Action.SUCCESS;
	}
	public String getCandidateAppliedPostsByCadre()
	{
		try
		{
			jObj = new JSONObject(getTask());
			nomintedPostMemberVO = nominatedPostProfileService.getCandidateAppliedPostsByCadre(jObj.getLong("globalCadreId"),jObj.getString("searchType"),jObj.getLong("nominateCandId"));
		}catch(Exception e)
		{
			LOG.error("Exception Occured in savechangeAddressForNominatedPost() in NominatedPostProfileAction ",e);
		}
	 return Action.SUCCESS;
	}
	public String getNominatedPostApplicationReviewDetails()
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("CREATE_NOMINATED_POST_ENTITLEMENT") || entitlements.contains("CREATE_NOMINATED_POST_ENTITLEMENT_ADMIN_GROUP")
					 || entitlements.contains("NOMINATED_POST_OVERVIEW_ENTITLEMENT"))){
				noaccess = true ;
			}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		}		
		return Action.SUCCESS;
	}
	public String getNominatedPostPostionDetails(){
		try{
			jObj = new JSONObject(getTask());
			
			nominatePostList = nominatedPostProfileService.getNominatedPostPostionDetails(jObj.getLong("depmtId"),jObj.getLong("boardId"),jObj.getLong("positionId"),
					jObj.getLong("bLId"),jObj.getLong("lValue"),jObj.getLong("searchLevelId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getNominatedPostPostionDetails Action",e);
		}
		return Action.SUCCESS;
	}
	 public String nominatedPostUploadForm()
		{
			try{
				
				RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
				
				Long cadreId= new Long(request.getParameter("nominatedCandId"));
				
				EventFileUploadVO eventFileUploadVO = new EventFileUploadVO();
				Map<File,String> mapfiles = new HashMap<File,String>();
				MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
			       Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
			       String fileUrl = "" ;
			       List<String> filePaths = null;
			   		while(fileParams.hasMoreElements())
			   		{
			   			String key = fileParams.nextElement();
			   			
					   			File[] files = multiPartRequestWrapper.getFiles(key);
					   			filePaths = new ArrayList<String>();
					   			if(files != null && files.length > 0)
					   			for(File f : files)
					   			{
					   				String[] extension  =multiPartRequestWrapper.getFileNames(key)[0].split("\\.");
					   	            String ext = "";
					   	            if(extension.length > 1){
					   	            	ext = extension[extension.length-1];
					   	            	mapfiles.put(f,ext);
					   	            }
					   	        
					   			}
					   			
					   			
			   		}
			     
			   		resultStatus = nominatedPostProfileService.saveNominatedPostUploadFiles(mapfiles, cadreId);
				
			}catch (Exception e) {
				LOG.error("Exception Occured in nominatedPostUploadForm() method, Exception - ",e); 
			}
			return Action.SUCCESS;	
		}
	 
	 public String deleteNominatedUploadedFile()
		{
			try{
				
				jObj = new JSONObject(getTask());
				resultStatus = nominatedPostProfileService.deleteNominatedUploadedFile(jObj.getString("applicatnDocId"));
				
			}catch (Exception e) {
				LOG.error("Exception Occured in deleteNominatedUploadedFile() method, Exception - ",e); 
			}
			return Action.SUCCESS;
		}
	public String getBrdWisNominPstAppliedDepOrCorpDetails(){
		try{
			jObj = new JSONObject(getTask());
			
			nominatePostList = nominatedPostProfileService.getBrdWisNominPstAppliedDepOrCorpDetails(jObj.getLong("candidateId"),jObj.getString("searchType"));
			
		}catch (Exception e) {
			LOG.error("Entered into getBrdWisNominPstAppliedDepOrCorpDetails Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getReferCadreDetailsForCandidate(){
		try{
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getReferCadreDetailsForCandidate(jObj.getLong("applicationId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getReferCadreDetailsForCandidate Action",e);
		}
		return Action.SUCCESS;
	}

	public String getNominatedPostsOverview(){
		try {
			jObj = new JSONObject(getTask());
			Long levelId = jObj.getLong("levelId");
			String startDateStr = jObj.getString("startDateStr");
			String endDateStr = jObj.getString("endDateStr");
			Long stateId = jObj.getLong("stateId");
			
			nominatePostList = nominatedPostProfileService.getNominatedPostsStatusDetailsInAllLevels(levelId,startDateStr,endDateStr,stateId);
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getNominatedPostsOverview() in NominatedPostProfileAction ",e);
		}
		 return Action.SUCCESS;
	}
	
	public String getAllDeptsAndBoardsByLevel(){
		try{
			
			jObj = new JSONObject(getTask());
			Long levelId = jObj.getLong("levelId");
			JSONArray levelValueArr = jObj.getJSONArray("levelValues");
			
			List<Long> levelValues = new ArrayList<Long>(0);
			if(levelValueArr !=null && levelValueArr.length()>0){
				for (int i = 0; i < levelValueArr.length(); i++) {
					Long levelValue = Long.valueOf(levelValueArr.get(i).toString());
					levelValues.add(levelValue);
				}
			}
			
			String statusType = jObj.getString("statusType");
			String task = jObj.getString("task");
			
			
			Long searchLevelId = jObj.getLong("searchlevelId");
			Long searchlevelValue = jObj.getLong("searchlevelValue");
			idNameVOList = nominatedPostProfileService.getAllDeptsAndBoardsByLevel(levelId,levelValues,statusType,task,searchLevelId,searchlevelValue);
			
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getAllDeptsAndBoardsByLevel() in NominatedPostProfileAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDepartmentWiseBoardAndPositionDetails(){
		
		try{
			jObj = new JSONObject(getTask());
			Long levelId = jObj.getLong("levelId");
			JSONArray levelValueArr = jObj.getJSONArray("levelValues");
			List<Long> levelValues = new ArrayList<Long>(0);
			if(levelValueArr !=null && levelValueArr.length()>0){
				for (int i = 0; i < levelValueArr.length(); i++) {
					Long levelValue = Long.valueOf(levelValueArr.get(i).toString());
					levelValues.add(levelValue);
				}
			}
			Long dept = jObj.getLong("depts");
			Long board = jObj.getLong("boards");
			String statusType =  jObj.getString("statusType");
			String task = jObj.getString("task");
			
			List<Long> depts = new ArrayList<Long>(0);
			List<Long> boards = new ArrayList<Long>(0);
			depts.add(dept);
			boards.add(board);
			
			if(board !=null && board>0l){
				nominatePostList = nominatedPostProfileService.getDepartmentWiseBoardAndPositionDetails(levelId,levelValues,depts,boards,statusType,task);
			}else{
				nominatePostList = nominatedPostProfileService.getAnyDeptApplicationOverviewCountLocationWise(dept,0l,0l,levelId,levelValues,null,statusType);
			}
			
			
	}catch (Exception e) {
		LOG.error("Exception Occured in getDepartmentWiseBoardAndPositionDetails() in NominatedPostProfileAction ",e);
	}
	return Action.SUCCESS;
	}
  public String getNominatedReadyToFinalReview(){
	  RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("CREATE_NOMINATED_POST_ENTITLEMENT") || entitlements.contains("CREATE_NOMINATED_POST_ENTITLEMENT_ADMIN_GROUP")
					 || entitlements.contains("NOMINATED_POST_OVERVIEW_ENTITLEMENT"))){
				noaccess = true ;
			}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		}		
		return Action.SUCCESS;
  }
 public String getFinalReviewCandidateCountLocationWise(){
	 try{
		    jObj = new JSONObject(getTask());
		    List<Long> lctnLevelValueList = new ArrayList<Long>(0); 
		    Long LocationLevelId = jObj.getLong("LocationLevelId");
			Long departmentId = jObj.getLong("departmentId");
			Long boardId = jObj.getLong("boardId");
			String status = jObj.getString("status");
			JSONArray locationLevelValueArr = jObj.getJSONArray("locationLevelValueArr");
			    if(locationLevelValueArr != null && locationLevelValueArr.length()> 0){
			    	for(int i = 0;i<locationLevelValueArr.length();i++){
			    		lctnLevelValueList.add(new Long(locationLevelValueArr.getInt(i)));
			    	}
			    }
		 idNameVOList = nominatedPostProfileService.getFinalReviewCandidateCountLocationWise(LocationLevelId,lctnLevelValueList,departmentId,boardId,status); 
	 }catch(Exception e) {
		 LOG.error("Exception Occured in getFinalReviewCandidateCountLocationWise() in NominatedPostProfileAction ",e);
	}
   return Action.SUCCESS;	
 }
	public String getAllApplicationStatusList(){
		try{
			
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getAllApplicationStatusList();
			
		}catch (Exception e) {
			LOG.error("Entered into getAllApplicationStatusList Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String savingAnyPostCandidatesToPosition(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			Long userId = regVO.getRegistrationID();
			jObj = new JSONObject(getTask());
			if(jObj.getLong("statusId") == 3){
			status = nominatedPostProfileService.savingAnyPostCandidatesToPosition(userId,jObj.getLong("applicationId"),jObj.getLong("candidateId"),jObj.getLong("levelId"),
					jObj.getLong("levelVal"),jObj.getLong("deptId"),jObj.getLong("boardId"),jObj.getLong("positionId"),jObj.getLong("statusId"),jObj.getString("comment"));
			
			}else if(jObj.getLong("statusId") == 2){
				status = nominatedPostProfileService.savingStatusAsReject(userId,jObj.getLong("applicationId"),jObj.getLong("candidateId"),jObj.getLong("levelId"),
						jObj.getLong("levelVal"),jObj.getLong("statusId"),jObj.getString("comment"));	
			}
			
			}catch (Exception e) {
			LOG.error("Entered into savingAnyPostCandidatesToPosition Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String nominatedPostManagement(){
		
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("CREATE_NOMINATED_POST_ENTITLEMENT") || entitlements.contains("CREATE_NOMINATED_POST_ENTITLEMENT_ADMIN_GROUP")
					 || entitlements.contains("NOMINATED_POST_OVERVIEW_ENTITLEMENT"))){
				noaccess = true ;
			}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		}		
		return Action.SUCCESS;
	}
	
	public String getSubLevelForConstituency(){
		LOG.info("entered into getSubLevelForConstituency");
		try {
			
			jObj = new JSONObject(getTask());
			
			Long stateId = jObj.getLong("stateId");
			Long locationLevel = jObj.getLong("locationLevel");
			
			List<Long> distIds = new ArrayList<Long>();
			JSONArray jsonArray1 = jObj.getJSONArray("districtId");
			for (int i = 0; i < jsonArray1.length(); i++) {
				Long distId1 = Long.valueOf(jsonArray1.get(i).toString());
				distIds.add(distId1);
			}
			
			List<Long> constiIds = new ArrayList<Long>();
			JSONArray jsonArray2 = jObj.getJSONArray("constituencyId");
			for (int i = 0; i < jsonArray2.length(); i++) {
				Long constiId = Long.valueOf(jsonArray2.get(i).toString());
				constiIds.add(constiId);
			}
						
			locations = cadreCommitteeService.getSubLevelForConstituency(stateId,distIds,constiIds,locationLevel);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getSubLevelForConstituency() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
public String notCadresearch(){
		
		try {
			jObj = new JSONObject(getTask());
			cadreCommitteeVOList =nominatedPostProfileService.notCadresearch(jObj.getString("searchType"),jObj.getString("searchValue").trim());
		} catch (Exception e) {
			LOG.error("Exception raised at notCadresearch() method of NominatedPostProfileAction", e);
		}
	
	return Action.SUCCESS;
	}
	public String getCastesForAP(){
		try{
			
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getCastesForAP();
			
		}catch (Exception e) {
			LOG.error("Entered into getCastesForAP Action",e);
		}
		
		return Action.SUCCESS;
	}
public String getNotCadreDetailsById(){
		
		try {
			jObj = new JSONObject(getTask());
			cadreCommitteeVOList =nominatedPostProfileService.getNotCadreDetailsById(jObj.getLong("nominatedPostCandiId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getNotCadreDetailsById() method of NominatedPostProfileAction", e);
		}
	
	return Action.SUCCESS;
	}
	public String updateNominatedPostStatsDetails(){
		
		try {
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
				jObj = new JSONObject(getTask());
				Long deptId = jObj.getLong("deptId");
				Long boardId = jObj.getLong("boardId");
				Long levelId = jObj.getLong("levelId");
				Long statusId = jObj.getLong("statusId");

				/*Long positionId = jObj.getLong("positionId");
				
				Long searchLevelId = jObj.getLong("searchLevelId");
				Long searchLevelValue = jObj.getLong("searchLevelValue");
				Long statusId = jObj.getLong("statusId");*/
				
				
				List<Long> positions = new ArrayList<Long>();
				JSONArray positionArr = jObj.getJSONArray("positionArr");
				for (int i = 0; i < positionArr.length(); i++) {
					Long positon = Long.valueOf(positionArr.get(i).toString());
					positions.add(positon);
				}
				
				List<Long> levelValues = new ArrayList<Long>();
				JSONArray levelValuesArr = jObj.getJSONArray("levelValuesArr");
				for (int i = 0; i < levelValuesArr.length(); i++) {
					Long levelValue = Long.valueOf(levelValuesArr.get(i).toString());
					levelValues.add(levelValue);
				}
				Long sizeOfMember = jObj.getLong("sizeOfMember");
				
				
				resultStatus =nominatedPostProfileService.updateNominatedPostStatusDetails(deptId,boardId, positions, levelId,levelValues,statusId,regVO.getRegistrationID(),sizeOfMember);
		} catch (Exception e) {
			LOG.error("Exception raised at updateNominatedPostStatsDetails() method of NominatedPostProfileAction", e);
		}

	return Action.SUCCESS;
	}
	public String getNominatedPostMainDashboard(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		boolean noaccess = false;
		List<String> entitlements = null;
		if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
			entitlements = regVO.getEntitlements();
			if(!(entitlements.contains("CREATE_NOMINATED_POST_ENTITLEMENT") || entitlements.contains("CREATE_NOMINATED_POST_ENTITLEMENT_ADMIN_GROUP")
					 || entitlements.contains("NOMINATED_POST_OVERVIEW_ENTITLEMENT"))){
				noaccess = true ;
			}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		}		
		return Action.SUCCESS;
	}
	public String getLocationWiseCastePositionCount(){
			
			try {
				jObj = new JSONObject(getTask());
				castePositionVOList =nominatedPostMainDashboardService.getLocationWiseCastePositionCount(jObj.getLong("LocationLevelId"),jObj.getLong("positionId"),jObj.getLong("stateId"));
			} catch (Exception e) {
				LOG.error("Exception raised at getLocationWiseCastePositionCount() method of NominatedPostProfileAction", e);
			}
		
		return Action.SUCCESS;
		}
	public String getLocationWiseCasteGroupPositionCount(){
		
		try {
			jObj = new JSONObject(getTask());
			castePositionVOList =nominatedPostMainDashboardService.getLocationWiseCasteGroupPositionCount(jObj.getLong("LocationLevelId"),jObj.getLong("positionId"),jObj.getLong("stateId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseCasteGroupPositionCount() method of NominatedPostProfileAction", e);
		}

	return Action.SUCCESS;
	}
public String getAllPositionWiseStatus(){
		
		try {
				jObj = new JSONObject(getTask());
				Long positionId = jObj.getLong("positionId");
				
				nominatedPostDashboardVO =nominatedPostMainDashboardService.getAllPositionWiseStatus(positionId);
		} catch (Exception e) {
			LOG.error("Exception raised at getAllPositionWiseStatus() method of NominatedPostProfileAction", e);
		}

	return Action.SUCCESS;
	}
public String getAllPositions(){
	
	try {
			jObj = new JSONObject(getTask());
			
			idNameVOList =nominatedPostMainDashboardService.getPositions();
	} catch (Exception e) {
		LOG.error("Exception raised at getAllPositions() method of NominatedPostProfileAction", e);
	}

return Action.SUCCESS;
}
	public String getAllReferredMemberDetailsForPosition(){
		try{
			
			jObj = new JSONObject(getTask());
			
			nominatedPostReferVO = nominatedPostProfileService.getAllReferredMemberDetailsForPosition(jObj.getLong("levelId"),jObj.getLong("levelValue"),
							jObj.getLong("departmentId"),jObj.getLong("boardId"),jObj.getLong("positionId"),jObj.getLong("statusId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getAllReferredMemberDetailsForPosition Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getOverAllCommentsForCandidate(){
		try{
			
			jObj = new JSONObject(getTask());
			
			idNameVO = nominatedPostProfileService.getOverAllCommentsForCandidate(jObj.getLong("candidateId"),jObj.getLong("aplicationId"),jObj.getLong("postFinalId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getOverAllCommentsForCandidate Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String updateFinalyzationStatusForPost(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER"); 
			if(regVO==null){
				return "input";
			}
			Long userId = regVO.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			status = nominatedPostProfileService.updateFinalyzationStatusForPost(jObj.getLong("postFinalId"),jObj.getLong("statusId"),jObj.getString("comment"),userId,jObj.getLong("applicationId"),jObj.getLong("candidateId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getNominatedPostMemberDetails Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String updateWishListForCandidate(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			Long userId = regVO.getRegistrationID();
			jObj = new JSONObject(getTask());
			
			status = nominatedPostProfileService.updateWishListForCandidate(jObj.getLong("postFinalId"),jObj.getString("remark"),userId);
			
		}catch (Exception e) {
			LOG.error("Entered into updateWishListForCandidate Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getCastGroupList(){
		try{
			idAndNameVOList = nominatedPostMainDashboardService.getCastGroupList();  
			
		}catch (Exception e) {
			LOG.error("Entered into getCastGroupList method of NominatedPostProfileAction Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getApplicationStatusList(){
		try{
			idAndNameVOList = nominatedPostMainDashboardService.getApplicationStatusList();  
			
		}catch (Exception e) {
			LOG.error("Entered into getApplicationStatusList method of NominatedPostProfileAction Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getPositionList(){
		try{
			idAndNameVOList = nominatedPostMainDashboardService.getPositionList();  
			
		}catch (Exception e) {
			LOG.error("Entered into getPositionList method of NominatedPostProfileAction Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getLocationLevelList(){
		try{
			idAndNameVOList = nominatedPostMainDashboardService.getLocationLevelList();  
			
		}catch (Exception e) {
			LOG.error("Entered into getLocationLevelList method of NominatedPostProfileAction Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getDepartmentList(){ 
		try{
			jObj =new JSONObject(getTask());
			Long boardLevelId = jObj.getLong("boardLevelId");
			idAndNameVOList = nominatedPostMainDashboardService.getDepartmentList(boardLevelId);  
			
		}catch (Exception e) {
			LOG.error("Entered into getDepartmentList method of NominatedPostProfileAction Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getBoardList(){
		try{
			jObj =new JSONObject(getTask());
			List<Long> deptIds = new ArrayList<Long>();
			try {
				JSONArray deptArr = jObj.getJSONArray("deptId");
				if(deptArr != null && deptArr.length() > 0){
					for (int i = 0; i < deptArr.length(); i++) {
						deptIds.add(Long.parseLong(deptArr.get(i).toString()));
					}
				}
			} catch (Exception e) {
				Long deptId = jObj.getLong("deptId");
				deptIds.add(deptId);
			}
			Long boardLevelId =  jObj.getLong("boardLevelId");
			idAndNameVOList = nominatedPostMainDashboardService.getBoardsList(deptIds,boardLevelId);      
			
		}catch (Exception e) {
			LOG.error("Entered into getBoardList method of NominatedPostProfileAction Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getNominatedCandidateGroupByDist(){
		try{
			jObj = new JSONObject(getTask());
			
			Long positionId = jObj.getLong("positionId");
			Long locationLevelId = jObj.getLong("locationLevelId");
			Long deptId = jObj.getLong("deptId");
			Long corporationId = jObj.getLong("corporationId");
			Long castGroupId = jObj.getLong("castGroupId");
			Long positionStatusId = jObj.getLong("positionStatusId");
			Long stateId = jObj.getLong("stateId");
			String locationLevelName = jObj.getString("locationLevelName");     
			nominatedPostVOs = nominatedPostMainDashboardService.getNominatedPostCandidateLocationWiseDetails(positionId, locationLevelId, deptId, corporationId, castGroupId, positionStatusId, stateId, locationLevelName);  
			
		}catch(Exception e){
			LOG.error("Entered into getNominatedCandidateGroupByDist method of NominatedPostProfileAction Action",e);
		}
		return Action.SUCCESS;
	}

	
	public String getOverAllTotalCountsByPosition(){
		try{
			/*RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER"); 
			if(regVO==null){
				return "input";
			}
			Long userId = regVO.getRegistrationID();*/
			jObj = new JSONObject(getTask());
			
			nominatedPostDashboardVO = nominatedPostProfileService.getOverAllTotalCountsByPosition(jObj.getLong("positionId"), jObj.getLong("levelId"), jObj.getLong("deptId"), jObj.getLong("boardId"), jObj.getLong("casteGroupId"), jObj.getLong("applStatusId"),jObj.getLong("stateId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getOverAllTotalCountsByPosition Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getCasteGroupWiseCountsByPosition(){
		try{
			/*RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER"); 
			if(regVO==null){
				return "input";
			}
			Long userId = regVO.getRegistrationID();*/
			jObj = new JSONObject(getTask());
			
			nominatedPostDashboardvoList = nominatedPostProfileService.getCasteGroupWiseCountsByPosition(jObj.getLong("positionId"), jObj.getLong("levelId"), jObj.getLong("deptId"), jObj.getLong("boardId"), jObj.getLong("casteGroupId"), jObj.getLong("applStatusId"),jObj.getLong("stateId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getCasteGroupWiseCountsByPosition Action",e);
		}
		
		return Action.SUCCESS; 
	}
	
	public String getCasteWiseCountsByPosition(){
		try{
			/*RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER"); 
			if(regVO==null){
				return "input";
			}
			Long userId = regVO.getRegistrationID();*/
			jObj = new JSONObject(getTask());
			
			nominatedPostDashboardvoList = nominatedPostProfileService.getCasteWiseCountsByPosition(jObj.getLong("positionId"), jObj.getLong("levelId"), jObj.getLong("deptId"), jObj.getLong("boardId"), jObj.getLong("casteGroupId"), jObj.getLong("applStatusId"),jObj.getLong("stateId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getCasteWiseCountsByPosition Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getCasteWisePositionsCountsByPosition(){
		try{
			/*RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER"); 
			if(regVO==null){
				return "input";
			}
			Long userId = regVO.getRegistrationID();*/
			jObj = new JSONObject(getTask());
			
			nominatedPostDashboardvoList = nominatedPostProfileService.getCasteWisePositionsCountsByPosition(jObj.getLong("positionId"), jObj.getLong("levelId"), jObj.getLong("deptId"), jObj.getLong("boardId"), jObj.getLong("casteGroupId"), jObj.getLong("applStatusId"),jObj.getLong("casteId"),jObj.getLong("stateId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getCasteWisePositionsCountsByPosition Action",e);
		}
		
		return Action.SUCCESS;
	}
	
public String getNominatedPostCandidatePositionWiseDetails(){ 
		
		try {
			jObj = new JSONObject(getTask());
			Long positionId = jObj.getLong("positionId");
			Long boardLevelId = jObj.getLong("boardLevelId");
			Long deptId = jObj.getLong("deptId");
			Long boardId = jObj.getLong("boardId");
			Long castegroupId = jObj.getLong("castegroupId");
			Long positionStatusId = jObj.getLong("positionStatusId"); 
			Long stateId = jObj.getLong("stateId");
			Long locationId = jObj.getLong("locationId");  
			String locationLevelName = jObj.getString("locationLevelName");
			
			nominatedPostVOs = nominatedPostMainDashboardService.getNominatedPostCandidatePositionWiseDetails(positionId,boardLevelId,deptId,boardId,castegroupId,positionStatusId,stateId,locationId,locationLevelName);
		} catch (Exception e) {
			LOG.error("Exception raised at getNotCadreDetailsById() method of NominatedPostProfileAction", e); 
		}
	
	return Action.SUCCESS;
	}
public String getPositionAndApplicationDetailsCntPositionWise(){
	try{
		jObj = new JSONObject(getTask());
		castePositionVO = nominatedPostMainDashboardService.getPositionAndApplicationDetailsCntPositionWise(jObj.getLong("locationLevelId"),jObj.getLong("positionId"),jObj.getString("reportType"),jObj.getLong("stateId"));
	 }catch(Exception e) {
		 LOG.error("Entered into getPositionAndApplicationDetailsCntPositionWise() method of NominatedPostProfileAction Action",e);
	}
	return Action.SUCCESS;
}
public String getPositionAndApplicationDetailsCntLocationWise(){
	
	try{
		jObj = new JSONObject(getTask());
		castePositionVO = nominatedPostMainDashboardService.getPositionAndApplicationDetailsCntLocationWise(jObj.getLong("locationLevelId"),jObj.getLong("positionId"),jObj.getString("reportType"),jObj.getLong("stateId"));
	 }catch(Exception e) {
		 LOG.error("Entered into getPositionAndApplicationDetailsCntLocationWise() method of NominatedPostProfileAction Action",e);
	}
	return Action.SUCCESS;
}

public String validateVoterIdCardNo(){
	try{
		jObj = new JSONObject(getTask());
		resultStatus = nominatedPostProfileService.validateVoterIdCardNo(jObj.getString("voterIdCardNo"));
		
	}catch (Exception e) {
		LOG.error("Entered into validateVoterIdCardNo() method of NominatedPostProfileAction Action",e);
	}
	return Action.SUCCESS;
}
public String execute()
{
	RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
	if(regVO==null){
		return "input";
	}
	boolean noaccess = false;
	List<String> entitlements = null;
	if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
		entitlements = regVO.getEntitlements();
		if(!(entitlements.contains("CREATE_NOMINATED_POST_ENTITLEMENT") || entitlements.contains("CREATE_NOMINATED_POST_ENTITLEMENT_ADMIN_GROUP")
				 || entitlements.contains("NOMINATED_POST_OVERVIEW_ENTITLEMENT"))){
			noaccess = true ;
		}
	if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
		noaccess = false;
	}
	if(noaccess){
		return "error";
	}
	}		
	return Action.SUCCESS;
}

	public String getOpenedPositionsBoardLevels(){
		try{
			
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getOpenedPositionsBoardLevels();
			
		}catch (Exception e) {
			LOG.error("Entered into getOpenedPositionsBoardLevels Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getStatesForOpenedPositions(){
		try{
			
			jObj = new JSONObject(getTask());
			Long boardLevelId = jObj.getLong("boardLevelId");
			idNameVOList = nominatedPostProfileService.getStatesForOpenedPositions(boardLevelId);
			/*if(idNameVOList != null && idNameVOList.size() > 1)
				idNameVOList.add(0, new IdNameVO(0L,"Select District"));*/
			
		}catch (Exception e) {
			LOG.error("Entered into getStatesForOpenedPositions Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getOpenPositionDistrictsForState(){
		try{
			
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			Long boardLevelId = jObj.getLong("boardLevelId");
			idNameVOList = nominatedPostProfileService.getOpenPositionDistrictsForState(stateId,boardLevelId);
			
			
		}catch (Exception e) {
			LOG.error("Entered into getOpenPositionDistrictsForState Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getOpenPositionConstituenciesForDistrict(){
		try{
			
			jObj = new JSONObject(getTask());
			Long districtId = jObj.getLong("districtId");
			Long boardLevelId = jObj.getLong("boardLevelId");
			idNameVOList = nominatedPostProfileService.getOpenPositionConstituenciesForDistrict(districtId,boardLevelId);
			
			
		}catch (Exception e) {
			LOG.error("Entered into getOpenPositionConstituenciesForDistrict Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getMandalMuncilIdsForConstituency(){
		try{
			
			jObj = new JSONObject(getTask());
			Long constituencyId = jObj.getLong("constituencyId");
			Long boardLevlId = jObj.getLong("boardLevelId");
			locations = nominatedPostProfileService.getMandalMuncilIdsForConstituency(constituencyId,boardLevlId);
			
			
		}catch (Exception e) {
			LOG.error("Entered into getMandalMuncilIdsForConstituency Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getPanchaytWardForMandal(){
		try{
			
			jObj = new JSONObject(getTask());
			String mandalId = jObj.getString("mandalId");
			Long constituencyId = jObj.getLong("constituencyId");
			Long boardLevlId = jObj.getLong("boardLevelId");
			locations = nominatedPostProfileService.getPanchaytWardForMandal(mandalId,constituencyId,boardLevlId);
			
			
		}catch (Exception e) {
			LOG.error("Entered into getPanchaytWardForMandal Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getAnyDeptApplicationOverviewCountLocationWise(){
		try{
			jObj = new JSONObject(getTask());
			Long departmentId = jObj.getLong("departmentId");
			Long boardId = jObj.getLong("boardId");
			Long positionId = jObj.getLong("positionId");
			Long boardLevlId = jObj.getLong("boardLevelId");
			Long locationValue = jObj.getLong("locationValue");
			Long searchLevelId = jObj.getLong("searchLevelId");
			String status = jObj.getString("statuss");
			
			List<Long> locationValues= new ArrayList<Long>(0);
			  locationValues.add(locationValue);
			
              nominatedPostVOs = nominatedPostProfileService.getAnyDeptApplicationOverviewCountLocationWise(departmentId,boardId,positionId,boardLevlId,locationValues,searchLevelId,status);			
		}catch(Exception e){
			LOG.error("Entered into getAnyDeptApplicationOverviewCountLocationWise Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getPositionsForABoard(){
		try {
			jObj = new JSONObject(getTask());
			
			JSONArray arr = jObj.getJSONArray("locationLevelValueArr");
			List<Long> locationLevelValueList = new ArrayList<Long>(0);
			if(arr != null && arr.length() > 0){
				for(int i=0;i<arr.length();i++){
					locationLevelValueList.add(Long.parseLong(arr.getString(i)));
				}
			}
			
			
			idNameVOList = nominatedPostProfileService.getPositionsForABoard(jObj.getLong("locationLevelId"),locationLevelValueList,jObj.getLong("departmentId"),jObj.getLong("boardId"));
		} catch (Exception e) {
			LOG.error("Exception raised at getPositionsForABoard", e);
		}
		
		return Action.SUCCESS;
	}
	
	public String confirmGOForNominatedPosts(){
		try {
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}else{
				//govtOrderVO
				
				final HttpSession session = request.getSession();
				final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null || user.getRegistrationID() == null){
					return ERROR;
				}
				
				Map<File,String> mapfiles = new HashMap<File,String>(0);
				MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
			       Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
			       String fileUrl = "" ;
			       List<String> filePaths = null;
			   		while(fileParams.hasMoreElements())
			   		{
			   			String key = fileParams.nextElement();
			   			
					   			File[] files = multiPartRequestWrapper.getFiles(key);
					   			filePaths = new ArrayList<String>();
					   			if(files != null && files.length > 0)
					   			for(File f : files)
					   			{
					   				String[] extension  =multiPartRequestWrapper.getFileNames(key)[0].split("\\.");
					   	            String ext = "";
					   	            if(extension.length > 1){
					   	            	ext = extension[extension.length-1];
					   	            	mapfiles.put(f,ext);
					   	            }
					   	        
					   			}
			   		}
			   		
				resultStatus = nominatedPostProfileService.confirmGOForNominatedPosts(govtOrderVO,regVO.getRegistrationID(),mapfiles);
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception raised at confirmGO", e);
		}
		
		return Action.SUCCESS;
	}

	public String checkPositionAvailableOrNot(){
		try{
			jObj = new JSONObject(getTask());
			Long departmentId = jObj.getLong("departmentId");
			Long boardId = jObj.getLong("boardId");
			Long positionId = jObj.getLong("positionId");
			Long boardLevlId = jObj.getLong("boardLevelId");
			Long searchLevelValue = jObj.getLong("searchLevelValue");
			Long searchLevelId = jObj.getLong("searchLevelId");
			//String status = jObj.getString("statuss");
			
			  resultStatus = nominatedPostProfileService.checkPositionAvailableOrNot(departmentId,boardId,positionId,boardLevlId,searchLevelValue,searchLevelId);			
		}catch(Exception e){
			LOG.error("Entered into checkPositionAvailableOrNot Action",e);
		}
		return Action.SUCCESS;
	}
	public String getFinalReviewCandidateCountForLocation(){
		 try{
			    jObj = new JSONObject(getTask());
			    List<Long> lctnLevelValueList = new ArrayList<Long>(0); 
			    Long LocationLevelId = jObj.getLong("LocationLevelId");
				Long departmentId = jObj.getLong("departmentId");
				Long boardId = jObj.getLong("boardId");
				Long positionId = jObj.getLong("positionId");
				String status = jObj.getString("status");
				JSONArray locationLevelValueArr = jObj.getJSONArray("locationLevelValueArr");
				    if(locationLevelValueArr != null && locationLevelValueArr.length()> 0){
				    	for(int i = 0;i<locationLevelValueArr.length();i++){
				    		lctnLevelValueList.add(new Long(locationLevelValueArr.getInt(i)));
				    	}
				    }///1111
				    nominatedPostMemberVOs = nominatedPostProfileService.getFinalReviewCandidateCountForLocation(LocationLevelId, lctnLevelValueList, departmentId, boardId, positionId, status);
		 }catch(Exception e) {
			 LOG.error("Exception Occured in getFinalReviewCandidateCountForLocation() in NominatedPostProfileAction ",e);
		}
	   return Action.SUCCESS;	
	 }
	
	public String assginGOToNominationPostCandidate(){
		try {
			LOG.info("Entered into assginGOToNominationPostCandidate");
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}else{
				
				final HttpSession session = request.getSession();
				final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null || user.getRegistrationID() == null){
					return ERROR;
				}
				
				Map<File,String> mapfiles = new HashMap<File,String>(0);
				MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
			       Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
			       String fileUrl = "" ;
			       List<String> filePaths = null;
			   		while(fileParams.hasMoreElements())
			   		{
			   			String key = fileParams.nextElement();
			   			
					   			File[] files = multiPartRequestWrapper.getFiles(key);
					   			filePaths = new ArrayList<String>();
					   			if(files != null && files.length > 0)
					   			for(File f : files)
					   			{
					   				String[] extension  =multiPartRequestWrapper.getFileNames(key)[0].split("\\.");
					   	            String ext = "";
					   	            if(extension.length > 1){
					   	            	ext = extension[extension.length-1];
					   	            	mapfiles.put(f,ext);
					   	            }
					   	        
					   			}
			   		}
			   		
				resultStatus = nominatedPostProfileService.assginGOToNominationPostCandidate(govtOrderVO,regVO.getRegistrationID(),mapfiles);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at assginGOToNominationPostCandidate", e);
		}
		return Action.SUCCESS;
	}
	public String getGovtOrderIssued(){
		 try{  
			 return Action.SUCCESS;	
		 }catch(Exception e) {
			 LOG.error("Exception Occured in getGovtOrderIssued() in NominatedPostProfileAction ",e);
		}
	   return Action.SUCCESS;	
	 }  
	public String getFinalReviewCandidateCountForLocationFilter(){
		 try{//222
			    jObj = new JSONObject(getTask());
			    List<Long> lctnLevelValueList = new ArrayList<Long>(0); 
			    List<Long> deptList = new ArrayList<Long>(0);
			    List<Long> boardList = new ArrayList<Long>(0);
			    List<Long> positionList = new ArrayList<Long>(0);
			    JSONArray locationLevelValueArr = jObj.getJSONArray("locationLevelValueArr");
			    JSONArray departmentIdArray = jObj.getJSONArray("departmentIds");
			    JSONArray boardIdArray = jObj.getJSONArray("boardIds");
			    JSONArray positionIdArray = jObj.getJSONArray("positionIds");
				
			    if(locationLevelValueArr != null && locationLevelValueArr.length()> 0){
			    	for(int i = 0;i<locationLevelValueArr.length();i++){
			    		lctnLevelValueList.add(new Long(locationLevelValueArr.getInt(i)));
			    	}
			    }
			    if(departmentIdArray != null && departmentIdArray.length() > 0){
			    	for(int i=0;i<departmentIdArray.length();i++){
			    		deptList.add(new Long(departmentIdArray.getInt(i)));
			    	}
			    }
			    if(boardIdArray != null && boardIdArray.length() > 0){
			    	for(int i=0;i<boardIdArray.length();i++){
			    		boardList.add(new Long(boardIdArray.getInt(i)));
			    	}
			    }
			    if(positionIdArray != null && positionIdArray.length() > 0){
			    	for(int i=0;i<positionIdArray.length();i++){
			    		positionList.add(new Long(positionIdArray.getInt(i)));
			    	}
			    }
			    String fromDate = jObj.getString("fromDate");
			    String expireDate = jObj.getString("expireDate");
			    Long LocationLevelId = jObj.getLong("LocationLevelId");  
			    String status = jObj.getString("status");
			    nominatedPostMemberVOs = nominatedPostProfileService.getFinalReviewCandidateCountForLocationFilter(LocationLevelId, lctnLevelValueList, deptList, boardList, positionList, fromDate, expireDate, status);
			    
			  /*  jObj = new JSONObject(getTask());
			    List<Long> lctnLevelValueList = new ArrayList<Long>(0); 
			    Long LocationLevelId = jObj.getLong("LocationLevelId");
				Long departmentId = jObj.getLong("departmentId");
				Long boardId = jObj.getLong("boardId");
				Long positionId = jObj.getLong("positionId");
				String status = jObj.getString("status");
				JSONArray locationLevelValueArr = jObj.getJSONArray("locationLevelValueArr");
				    if(locationLevelValueArr != null && locationLevelValueArr.length()> 0){
				    	for(int i = 0;i<locationLevelValueArr.length();i++){
				    		lctnLevelValueList.add(new Long(locationLevelValueArr.getInt(i)));
				    	}
				    }///1111
				    nominatedPostMemberVOs = nominatedPostProfileService.getFinalReviewCandidateCountForLocation(LocationLevelId, lctnLevelValueList, departmentId, boardId, positionId, status);
		*/
				    
		 }catch(Exception e) {
			 LOG.error("Exception Occured in getFinalReviewCandidateCountForLocationFilter() in NominatedPostProfileAction ",e);
		}
	   return Action.SUCCESS;	
	 }
	public String isApplicationAlreadyShortlisted(){
		try{
			
			jObj = new JSONObject(getTask());
			
			status = nominatedPostProfileService.isApplicationAlreadyShortlisted(jObj.getLong("nominatePostApplicationId"),jObj.getLong("levelId"),jObj.getLong("levelVal"),jObj.getLong("deptId"),jObj.getLong("boardId"),jObj.getLong("positionId"),jObj.getLong("candidateId"));
			
		}catch (Exception e) {
			LOG.error("Entered into isApplicationAlreadyShortlisted Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	
	public String getNominatedPostCandidateDetils(){
		/*
		 * Long stateId,Long casteStateId,Long positionId,List<Long> postStatusIds,Long boardLevelId
		 */
		try{
			LOG.info("Entered into nominatedPostProfileAction of getNominatedPostCandidateDetils()");
			jObj = new JSONObject(getTask());
			List<Long> postStatusIds =new ArrayList<Long>();
			JSONArray postStatusIdsArr = jObj.getJSONArray("postStatusIdsLst");
			Long stateId = jObj.getLong("stateId");
			Long casteStateId = jObj.getLong("casteStateId");//
			Long positionId = jObj.getLong("positionId");//
			Long boardLevelId = jObj.getLong("boardLevelId");//
			Long casteCategryId = jObj.getLong("casteCategoryId");
			Long ageRangeTypeId = jObj.getLong("ageRangeTypeId");
			Long deptmentId = jObj.getLong("departmentId");
			Long corptionId = jObj.getLong("corporationId");
			//Long ageWisePostionId  = jObj.getLong("ageWisePostionId");
			String genderType = jObj.getString("genderType");
			Long locationId = jObj.getLong("locationId");
			String type = jObj.getString("type");
			//String type = jObj.getString("type");
			  if(postStatusIdsArr != null && postStatusIdsArr.length()> 0){
			    	for(int i = 0;i<postStatusIdsArr.length();i++){
			    		postStatusIds.add(new Long(postStatusIdsArr.getInt(i)));
			    	}
			    }
			  idNameVOList = nominatedPostMainDashboardService.getNominatedPostCandidateDetils(stateId,casteStateId,positionId,boardLevelId,casteCategryId,
					                                                       ageRangeTypeId,deptmentId,corptionId,genderType,postStatusIds,locationId,type);
					                                                                          
		}catch(Exception e){
			LOG.error("Entered into getNominatedPostCandidateDetils Action",e);
		}
		return Action.SUCCESS;
	}
	public String getNominatedPostDetails(){
		 try{
		    jObj = new JSONObject(getTask());
		    List<Long> lctnLevelValueList = new ArrayList<Long>(0); 
		    Long locationLevelId = jObj.getLong("locationLevelId");
			Long departmentId = jObj.getLong("departmentId");
			Long boardId = jObj.getLong("boardId");
			Long positionId = jObj.getLong("positionId");
			JSONArray locationLevelValueArr = jObj.getJSONArray("locationLevelValueArr");
			    if(locationLevelValueArr != null && locationLevelValueArr.length()> 0){
			    	for(int i = 0;i<locationLevelValueArr.length();i++){
			    		lctnLevelValueList.add(new Long(locationLevelValueArr.getInt(i)));
			    	}
			    }
			    nominatedPostDashboardVO = nominatedPostProfileService.getNominatedPostDetails(locationLevelId,lctnLevelValueList,departmentId,boardId,positionId); 
		 }catch(Exception e) {
			 LOG.error("Exception Occured in getNominatedPostDetails() in NominatedPostProfileAction ",e);
		}
	   return Action.SUCCESS;	
	 }
	public String getApplicationDocuments()
	{
		try
		{
			jObj = new JSONObject(getTask());
			idAndNameVOList = nominatedPostProfileService.getApplicationDocuments(jObj.getLong("globalCadreId"),jObj.getString("searchType"),jObj.getLong("nominateCandId"),jObj.getLong("applicationId"),jObj.getLong("statusId"),jObj.getString("applicationType"));
		}catch(Exception e)
		{
			LOG.error("Exception Occured in getApplicationDocuments() in NominatedPostProfileAction ",e);
		}
	 return Action.SUCCESS;
	}
	
	public String getAllAgeRangesByOrder()
	{
		try
		{
			jObj = new JSONObject(getTask());
			idAndNameVOList = nominatedPostProfileService.getAllAgeRangesByOrder();
		}catch(Exception e)
		{
			LOG.error("Exception Occured in getAllAgeRangesByOrder() in NominatedPostProfileAction ",e);
		}
	 return Action.SUCCESS;
	}
	
	public String getAllCasteDetailsForVoters()
	{
		try
		{
			jObj = new JSONObject(getTask());
			idAndNameVOList = nominatedPostProfileService.getAllCasteDetailsForVoters();
		}catch(Exception e)
		{
			LOG.error("Exception Occured in getAllCasteDetailsForVoters() in NominatedPostProfileAction ",e);
		}
	 return Action.SUCCESS;
	}
	
	public String getAllCasteCategoryDetails()
	{
		try
		{
			jObj = new JSONObject(getTask());
			idAndNameVOList = nominatedPostProfileService.getAllCasteCategoryDetails();
		}catch(Exception e)
		{
			LOG.error("Exception Occured in getAllCasteCategoryDetails() in NominatedPostProfileAction ",e);
		}
	 return Action.SUCCESS;
	}
	
	public String getEducationalQualifications()
	{
		try
		{
			jObj = new JSONObject(getTask());
			idAndNameVOList = nominatedPostProfileService.getEducationalQualifications();
		}catch(Exception e)
		{
			LOG.error("Exception Occured in getEducationalQualifications() in NominatedPostProfileAction ",e);
		}
	 return Action.SUCCESS;
	}
	public String getDepartmentBoardPositions1(){
		try{
			jObj =new JSONObject(getTask());
			List<Long> deptIds = new ArrayList<Long>();
			List<Long> boardIds = new ArrayList<Long>();
			List<Long> searchLevelValueIds = new ArrayList<Long>();
			Long boardLevelId = 0l;
			Long nominatedPostCandId = 0l;
			Long searchLevelId = 0l;
			try {
				JSONArray deptArr = jObj.getJSONArray("deptId");
				JSONArray boardArr = jObj.getJSONArray("boardId");
				JSONArray searchLevelValueArr = jObj.getJSONArray("searchLevelValue");
				searchLevelId =  jObj.getLong("searchLevelId");
				nominatedPostCandId =  jObj.getLong("nominatedPostCandId");
				boardLevelId =  jObj.getLong("boardLevelId");
				
				if(deptArr != null && deptArr.length() > 0){
					for (int i = 0; i < deptArr.length(); i++) {
						deptIds.add(Long.parseLong(deptArr.get(i).toString()));
					}
				}
				if(boardArr != null && boardArr.length() > 0){
					for (int i = 0; i < boardArr.length(); i++) {
						boardIds.add(Long.parseLong(boardArr.get(i).toString()));
					}
				}
				if(searchLevelValueArr != null && searchLevelValueArr.length() > 0){
					for (int i = 0; i < searchLevelValueArr.length(); i++) {
						searchLevelValueIds.add(Long.parseLong(searchLevelValueArr.get(i).toString()));
					}
				}
			} catch (Exception e) {
				Long deptId = jObj.getLong("deptId");
				deptIds.add(deptId);
				Long boardId = jObj.getLong("boardId");
				boardIds.add(boardId);
				Long searchLevelValue = jObj.getLong("searchLevelValue");
				searchLevelValueIds.add(searchLevelValue);
			}
			
			idNameVOList = nominatedPostProfileService.getDepartmentBoardPositions1(deptIds,boardIds,boardLevelId,searchLevelValueIds,searchLevelId,nominatedPostCandId);      
			
		}catch (Exception e) {
			LOG.error("Entered into getDepartmentBoardPositions1 method of NominatedPostProfileAction Action",e);
		}
		return Action.SUCCESS;
	}
	public String updateNominationPosts(){
		return Action.SUCCESS;
	}
	
	public String fileSaving(){
		try{
			final HttpSession session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
			//jObj = new JSONObject(getTask());
			String nominPstCandId = request.getParameter("nominationPostCandidateId");
			String cadreId = request.getParameter("tdpCadreId");
			String aplicationId = request.getParameter("applicationId");
			
			Long postCandId = Long.parseLong(nominPstCandId);
			Long tdpCadreId = Long.parseLong(cadreId);
			Long applicId = Long.parseLong(aplicationId);
			
			Map<File,String> mapfiles = new HashMap<File,String>();
			MultiPartRequestWrapper multiPartRequestWrapper = (MultiPartRequestWrapper)request;
		       Enumeration<String> fileParams = multiPartRequestWrapper.getFileParameterNames();
		       String fileUrl = "" ;
		       List<String> filePaths = null;
		   		while(fileParams.hasMoreElements())
		   		{
		   			String key = fileParams.nextElement();
		   			
				   			File[] files = multiPartRequestWrapper.getFiles(key);
				   			filePaths = new ArrayList<String>();
				   			if(files != null && files.length > 0)
				   			for(File f : files)
				   			{
				   				String[] extension  =multiPartRequestWrapper.getFileNames(key)[0].split("\\.");
				   	            String ext = "";
				   	            if(extension.length > 1){
				   	            	ext = extension[extension.length-1];
				   	            	mapfiles.put(f,ext);
				   	            }
				   	        
				   			}
		   		}
		     
			resultStatus = nominatedPostProfileService.savingNominatedPostDocumnets(postCandId,tdpCadreId,applicId,mapfiles,user.getRegistrationID());
			if(resultStatus!=null){
				if(resultStatus.getResultCode() == 0){
					inputStream = new StringBufferInputStream(resultStatus.getMessage());
				}else if(resultStatus.getResultCode() == 1){
					inputStream = new StringBufferInputStream(resultStatus.getMessage());
				}
			}
			
		}catch(Exception e){
			LOG.error("Entered into fileSaving method of NominatedPostProfileAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getLocationAndBoardLevelWisePostsData(){
		try{
			jObj = new JSONObject(getTask());
			Long postLevelId = jObj.getLong("postLevelId");
			Long casteGrpId = jObj.getLong("casteGrpId");
			Long casteId = jObj.getLong("casteId");
			Long ageRangeId = jObj.getLong("ageRangeId");
			Long positionId = jObj.getLong("positionId");
			String gender = jObj.getString("gender");
			Long stateId = jObj.getLong("stateId");
			String searchType = jObj.getString("searchType");
			JSONArray statusIds = jObj.getJSONArray("postStatusIdsLst");
			
			List<Long> postStatusIds = new ArrayList<Long>(0);
			if(statusIds !=null && statusIds.length()>0){
				for (int i = 0; i < statusIds.length(); i++) {
					Long locId = Long.valueOf(statusIds.get(i).toString());
					postStatusIds.add(locId);
				}
			}
			
			idAndNameVOList = nominatedPostMainDashboardService.getLocationAndBoardLevelWisePostsData(postLevelId,casteGrpId,casteId,
					ageRangeId,positionId,gender,stateId,searchType,postStatusIds);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Entered into  getLocationAndBoardLevelWisePostsData () of NominatedPostProfileAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getLocationAndBoardLevelWiseCasteCatgryPostsData(){
		try{
			jObj = new JSONObject(getTask());
			Long postLevelId = jObj.getLong("postLevelId");
			Long casteGrpId = jObj.getLong("casteGrpId");
			Long casteId = jObj.getLong("casteId");
			Long ageRangeId = jObj.getLong("ageRangeId");
			Long positionId = jObj.getLong("positionId");
			String gender = jObj.getString("gender");
			Long stateId = jObj.getLong("stateId");
			String searchType = jObj.getString("searchType");
			
			JSONArray locIdsArry = jObj.getJSONArray("locationIdsList");
			
			List<Long> locIdsList = new ArrayList<Long>(0);
			if(locIdsArry !=null && locIdsArry.length()>0){
				for (int i = 0; i < locIdsArry.length(); i++) {
					Long locId = Long.valueOf(locIdsArry.get(i).toString());
					locIdsList.add(locId);
				}
			}
			JSONArray statusIds = jObj.getJSONArray("postStatusIdsLst");
			
			List<Long> postStatusIds = new ArrayList<Long>(0);
			if(statusIds !=null && statusIds.length()>0){
				for (int i = 0; i < statusIds.length(); i++) {
					Long locId = Long.valueOf(statusIds.get(i).toString());
					postStatusIds.add(locId);
				}
			}
			String dataType = jObj.getString("dataType");
			
			nominatedPostDashboardvoList = nominatedPostMainDashboardService.getLocationAndBoardLevelWiseCasteCatgryPostsData(postLevelId,casteGrpId,casteId,ageRangeId,positionId,gender,stateId,searchType,locIdsList,dataType,postStatusIds);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Entered into getLocationAndBoardLevelWiseCasteCatgryPostsData method of NominatedPostProfileAction ",e);
		}
		return Action.SUCCESS;
	}

	public String getCadreDetailsReport(){
		try {
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			jObj = new JSONObject(getTask());
			List<Long> tdpCadreIdsList = new ArrayList<Long>(0);
			JSONArray tdpCadreIds = jObj.getJSONArray("tdpCadreIds");
			
			if(tdpCadreIds !=null && tdpCadreIds.length()>0){
				for (int i = 0; i < tdpCadreIds.length(); i++) {
					Long locId = Long.valueOf(tdpCadreIds.get(i).toString());
					tdpCadreIdsList.add(locId);
				}
			}
			
			cadrePerformanceVO = nominatedPostProfileService.getCadrePeoplePerformanceDetails(tdpCadreIdsList);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Entered into getCadreDetailsReport method of NominatedPostProfileAction ",e);
		}
	
		return Action.SUCCESS;
		
	}
	
	public String getCandidateLocationWiseDetails(){
		try{
			jObj = new JSONObject(getTask());
			Long postLevelId = jObj.getLong("postLevelId");
			Long casteGrpId = jObj.getLong("casteGrpId");
			Long casteId = jObj.getLong("casteId");
			Long ageRangeId = jObj.getLong("ageRangeId");
			Long positionId = jObj.getLong("positionId");
			String gender = jObj.getString("gender");
			Long stateId = jObj.getLong("stateId");
			String searchType = jObj.getString("searchType");
			
			JSONArray locIdsArry = jObj.getJSONArray("locationIdsList");
			
			List<Long> locIdsList = new ArrayList<Long>(0);
			if(locIdsArry !=null && locIdsArry.length()>0){
				for (int i = 0; i < locIdsArry.length(); i++) {
					Long locId = Long.valueOf(locIdsArry.get(i).toString());
					locIdsList.add(locId);
				}
			}
			JSONArray statusIds = jObj.getJSONArray("postStatusIdsLst");
			
			List<Long> postStatusIds = new ArrayList<Long>(0);
			if(statusIds !=null && statusIds.length()>0){
				for (int i = 0; i < statusIds.length(); i++) {
					Long locId = Long.valueOf(statusIds.get(i).toString());
					postStatusIds.add(locId);
				}
			}
			String casteCatgNm = jObj.getString("casteCatgNm");
			
			idNameVOList = nominatedPostMainDashboardService.getCandidateLocationWiseDetails(postLevelId,casteGrpId,casteId,ageRangeId,positionId,gender,stateId,searchType,locIdsList,postStatusIds,casteCatgNm);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Entered into getLocationAndBoardLevelWiseCasteCatgryPostsData method of NominatedPostProfileAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getLocationByDepartment(){
		try{
			jObj = new JSONObject(getTask());
			Long levelId = jObj.getLong("levelId");
			Long departmentId = jObj.getLong("departmentId");
			Long boardId = jObj.getLong("boardId");
			Long positionId = jObj.getLong("positionId");
		locationVoList = nominatedPostProfileService.getLocationByDepartment(levelId,departmentId,boardId,positionId);
			
		}catch(Exception e){
			e.printStackTrace();
		 LOG.error("Entered into getLocationByDepartment method of NominatedPostProfileAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getGeoLevelReportDetails(){
		try{
			jObj = new JSONObject(getTask());
			GeoLevelReportVO vo = new GeoLevelReportVO();
			vo.setBoardLevelId(jObj.getLong("levelId"));
			
			JSONArray statusIds = jObj.getJSONArray("statusIds");
			List<Long> appStatusIds = new ArrayList<Long>(0);
			if(statusIds !=null && statusIds.length()>0){
				for (int i = 0; i < statusIds.length(); i++) {
					Long locId = Long.valueOf(statusIds.get(i).toString());
					appStatusIds.add(locId);
				}
			}
			JSONArray casteIds = jObj.getJSONArray("casteIds");
			List<Long> casteIds1 = new ArrayList<Long>(0);
			if(casteIds !=null && casteIds.length()>0){
				for (int i = 0; i < casteIds.length(); i++) {
					Long casteId = Long.valueOf(casteIds.get(i).toString());
					casteIds1.add(casteId);
				}
			}
			JSONArray castCatgs = jObj.getJSONArray("casteCategoryIds");
			List<Long> castCatgIds = new ArrayList<Long>(0);
			if(castCatgs !=null && castCatgs.length()>0){
				for (int i = 0; i < castCatgs.length(); i++) {
					Long castCatgry = Long.valueOf(castCatgs.get(i).toString());
					castCatgIds.add(castCatgry);
				}
			}
			JSONArray ageRangeIds = jObj.getJSONArray("ageRangeIds");
			List<Long> ageRangeGrpIds = new ArrayList<Long>(0);
			if(ageRangeIds !=null && ageRangeIds.length()>0){
				for (int i = 0; i < ageRangeIds.length(); i++) {
					Long ageRangId = Long.valueOf(ageRangeIds.get(i).toString());
					ageRangeGrpIds.add(ageRangId);
				}
			}
			
			JSONArray positionIds = jObj.getJSONArray("positionIds");
			List<Long> positionIds1 = new ArrayList<Long>(0);
			if(positionIds !=null && positionIds.length()>0){
				for (int i = 0; i < positionIds.length(); i++) {
					Long posId = Long.valueOf(positionIds.get(i).toString());
					positionIds1.add(posId);
				}
			}
			JSONArray locationIds = jObj.getJSONArray("locationIds");
			List<Long> locationIds1 = new ArrayList<Long>(0);
			if(locationIds !=null && locationIds.length()>0){
				for (int i = 0; i < locationIds.length(); i++) {
					Long locId = Long.valueOf(locationIds.get(i).toString());
					locationIds1.add(locId);
				}
			}
			JSONArray distIdArr = jObj.getJSONArray("distIdArr");
			List<Long> distIds = new ArrayList<Long>(0);
			if(distIdArr !=null && distIdArr.length()>0){
				for (int i = 0; i < distIdArr.length(); i++) {
					Long distId = Long.valueOf(distIdArr.get(i).toString());
					distIds.add(distId);
				}
			}
			vo.setLocationIds(locationIds1);
			vo.setPositionIds(positionIds1);
			vo.setAgeRangeIds(ageRangeGrpIds);
			vo.setCasteGrpIds(castCatgIds);
			vo.setCasteIds(casteIds1);
			vo.setStatusIds(appStatusIds);
			vo.setGender(jObj.getString("gender"));
			vo.setLocationType(jObj.getString("locationType"));
			vo.setIsCasteChkd(jObj.getString("isCasteChkd"));
			vo.setIsPositionChkd(jObj.getString("isPositionChkd"));
			vo.setIsCasteGrpChkd(jObj.getString("isCasteGrpChkd"));
			vo.setIsGenderChkd(jObj.getString("isGenderChkd"));
			vo.setIsAgeRngeChkd(jObj.getString("isAgeRngeChkd"));
			vo.setStateId(jObj.getLong("stateId"));
			vo.setDistIds(distIds);
			geoLevelListVO = nominatedPostMainDashboardService.getGeoLevelReportDetails(vo);
			
		}catch(Exception e){
			e.printStackTrace();
		 LOG.error("Entered into getLocationByDepartment method of NominatedPostProfileAction ",e);
		}
		return Action.SUCCESS;
	}
	/*public String getCadreDetailedReportEventAttendee(){
		try{
			jObj = new JSONObject(getTask());
			Long parentEventId = jObj.getLong("parentEventId");
			Long cadreId = jObj.getLong("cadreId");
         JSONArray extraEventIdsArry = jObj.getJSONArray("extraEventIds");
			
			List<Long> extraEventIdsList = new ArrayList<Long>(0);
			if(extraEventIdsArry !=null && extraEventIdsArry.length()>0){
				for (int i = 0; i < extraEventIdsArry.length(); i++) {
					Long eventId = Long.valueOf(extraEventIdsArry.get(i).toString());
					extraEventIdsList.add(eventId);
				}
			}
			//cadreReportVO = nominatedPostProfileService.getCadreDetailedReportEventAttendee(parentEventId,cadreId,extraEventIdsList);
			
		}catch(Exception e){
			e.printStackTrace();
		 LOG.error("Entered into getCadreDetailedReportEventAttendee method of NominatedPostProfileAction ",e);
		}
		return Action.SUCCESS;
	}*/
	public String getAllAgeRangesList(){
		try{
			idAndNameVOList = nominatedPostMainDashboardService.getAllAgeRangesList();  
			
		}catch (Exception e) {
			LOG.error("Entered into getAllAgeRangesList method of NominatedPostProfileAction Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getCastListByCasteCatgryId(){
		try{
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
       JSONArray casteCatgryIdsArry = jObj.getJSONArray("casteCatgryId");
			
			List<Long> casteCatgryIdsList = new ArrayList<Long>(0);
			if(casteCatgryIdsArry !=null && casteCatgryIdsArry.length()>0){
				for (int i = 0; i < casteCatgryIdsArry.length(); i++) {
					Long casteCatgryId = Long.valueOf(casteCatgryIdsArry.get(i).toString());
					casteCatgryIdsList.add(casteCatgryId);
				}
			}
			//Long casteCatgryId = jObj.getLong("casteCatgryId");
			idAndNameVOList = nominatedPostMainDashboardService.getCastListByCasteCatgryId(casteCatgryIdsList,stateId);  
			
		}catch (Exception e) {
			LOG.error("Entered into getCastListByCasteCatgryId method of NominatedPostProfileAction Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getCampDetails(){
		try {
			
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			jObj = new JSONObject(getTask());
			List<Long> tdpCadreIdsList = new ArrayList<Long>(0);
			JSONArray tdpCadreIds = jObj.getJSONArray("tdpCadreIds");
			
			if(tdpCadreIds !=null && tdpCadreIds.length()>0){
				for (int i = 0; i < tdpCadreIds.length(); i++) {
					Long locId = Long.valueOf(tdpCadreIds.get(i).toString());
					tdpCadreIdsList.add(locId);
				}
			}
			
			cadreTraingVO = nominatedPostProfileService.getCampDetails(tdpCadreIdsList);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Entered into getCampDetails method of NominatedPostProfileAction ",e);
		}

		return Action.SUCCESS;
		
	}
	public String getMahanaduEventDetilsByCadreIds(){
		try{
			LOG.info("Enterd into nominatedPostProfileAction of getMahanaduEventDetilsByCadreIds");
			jObj = new JSONObject(getTask());
			Long parentEventId = jObj.getLong("parentEventId") ;
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			eventDetilsList = nominatedPostProfileService.getMahanaduEventDetilsByCadreIdDetils(parentEventId,tdpCadreId);
		}catch(Exception e){
			LOG.error("Exception Occured into nominatedPostProfileAction of getMahanaduEventDetilsByCadreIds");
		}
		return Action.SUCCESS;
	}
	public String getQuickApprovalNominatedPost(){
		 try{  
			 return Action.SUCCESS;	
		 }catch(Exception e) {
			 LOG.error("Exception Occured in getQuickApprovalNominatedPost() in NominatedPostProfileAction ",e);
		}
	   return Action.SUCCESS;	
	 }  
	public String saveNominatedPostProfileDtls(){
		try{
			LOG.info("Enterd into nominatedPostProfileAction of saveNominatedPostProfileDtlss");
			jObj = new JSONObject(getTask());
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO !=null){
				Long userId = regVO.getRegistrationID();
				resultStatus = nominatedPostProfileService.saveNominatedPostProfileDtls(nominatedPostDetailsVO,userId);
			} else {
				resultStatus = new ResultStatus();
				resultStatus.setMessage("Exception Occured.Pls Try Again");
				resultStatus.setResultCode(0);
			}
			
		}catch(Exception e){
			LOG.error("Exception Occured in saveNominatedPostProfileDtls() in NominatedPostProfileAction ",e);
		}
		return Action.SUCCESS;
	}
}
