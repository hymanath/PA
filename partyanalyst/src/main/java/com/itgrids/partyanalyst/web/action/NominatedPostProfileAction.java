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

import com.itgrids.partyanalyst.dto.EventFileUploadVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
import com.itgrids.partyanalyst.dto.NomintedPostMemberVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
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
	private String status;
	private List<IdNameVO> 						idNameVOList;
	private List<NominatedPostVO> 				nominatePostList;
	private NomintedPostMemberVO 				nomintedPostMemberVO;
	private NominatedPostVO                     nominatedPostVO;
	private ResultStatus 						resultStatus;
	private InputStream 						inputStream;
	private List<LocationWiseBoothDetailsVO> locations;
	private ICadreCommitteeService	cadreCommitteeService;

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
	public String nominatedPosts()
	{
		return Action.SUCCESS;
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
			
			idNameVOList = nominatedPostProfileService.getDepartments(jObj.getLong("postType"));
			
		}catch (Exception e) {
			LOG.error("Entered into getDepartments Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getDepartmentBoard(){
		try{
			
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getDepartmentBoard(jObj.getLong("depmtId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getDepartmentBoard Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String getDepartmentBoardPositions(){
		try{
			
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getDepartmentBoardPositions(jObj.getLong("depmtId"),jObj.getLong("boardId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getDepartmentBoard Action",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getNominatedPostMemberDetails(){
		try{
			
			jObj = new JSONObject(getTask());
			
			nomintedPostMemberVO = nominatedPostProfileService.getNominatedPostMemberDetails(jObj.getLong("levelId"),jObj.getLong("levelValue"),jObj.getLong("departmentId"),
															jObj.getLong("boardId"),jObj.getLong("positionId"),jObj.getString("type"));
			
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
			
			status = nominatedPostProfileService.updateApplicationStatusDetails(userId,jObj.getLong("nominatePostApplicationId"),jObj.getLong("statusId"),jObj.getString("comment"));
			
		}catch (Exception e) {
			LOG.error("Entered into getNominatedPostMemberDetails Action",e);
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
		    
			status = nominatedPostProfileService.savechangeAddressForNominatedPost(Vo);
			
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
			nominatePostList = nominatedPostProfileService.getApplicantDetailsForMember(tdpCadreId);
			
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
			nomintedPostMemberVO = nominatedPostProfileService.getCandidateAppliedPostsByCadre(jObj.getLong("globalCadreId"));
		}catch(Exception e)
		{
			LOG.error("Exception Occured in savechangeAddressForNominatedPost() in NominatedPostProfileAction ",e);
		}
	 return Action.SUCCESS;
	}
	public String getNominatedPostApplicationReviewDetails()
	{
	 return Action.SUCCESS;
	}
	public String getNominatedPostPostionDetails(){
		try{
			jObj = new JSONObject(getTask());
			
			nominatePostList = nominatedPostProfileService.getNominatedPostPostionDetails(jObj.getLong("depmtId"),jObj.getLong("boardId"),jObj.getLong("positionId"),
					jObj.getLong("bLId"),jObj.getLong("lValue"));
			
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
			
			nominatePostList = nominatedPostProfileService.getBrdWisNominPstAppliedDepOrCorpDetails(jObj.getLong("candidateId"));
			
		}catch (Exception e) {
			LOG.error("Entered into getBrdWisNominPstAppliedDepOrCorpDetails Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getReferCadreDetailsForCandidate(){
		try{
			jObj = new JSONObject(getTask());
			
			idNameVOList = nominatedPostProfileService.getReferCadreDetailsForCandidate(jObj.getLong("candidateId"));
			
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
			Long levelValue = jObj.getLong("levelValues");
			
			List<Long> levelValues = new ArrayList<Long>(0);
			levelValues.add(levelValue);
			
			idNameVOList = nominatedPostProfileService.getAllDeptsAndBoardsByLevel(levelId,levelValues);
			
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getAllDeptsAndBoardsByLevel() in NominatedPostProfileAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDepartmentWiseBoardAndPositionDetails(){
		
		try{
			jObj = new JSONObject(getTask());
			Long levelId = jObj.getLong("levelId");
			Long levelValue = jObj.getLong("levelValues");
			Long dept = jObj.getLong("depts");
			Long board = jObj.getLong("boards");
			
			List<Long> levelValues = new ArrayList<Long>(0);
			List<Long> depts = new ArrayList<Long>(0);
			List<Long> boards = new ArrayList<Long>(0);
			levelValues.add(levelValue);
			depts.add(dept);
			boards.add(board);
			
			nominatePostList = nominatedPostProfileService.getDepartmentWiseBoardAndPositionDetails(levelId,levelValues,depts,boards);
	}catch (Exception e) {
		LOG.error("Exception Occured in getDepartmentWiseBoardAndPositionDetails() in NominatedPostProfileAction ",e);
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
			
			status = nominatedPostProfileService.savingAnyPostCandidatesToPosition(userId,jObj.getLong("applicationId"),jObj.getLong("candidateId"),jObj.getLong("levelId"),
					jObj.getLong("levelVal"),jObj.getLong("deptId"),jObj.getLong("boardId"),jObj.getLong("positionId"),jObj.getLong("statusId"),jObj.getString("comment"));
			
		}catch (Exception e) {
			LOG.error("Entered into savingAnyPostCandidatesToPosition Action",e);
		}
		
		return Action.SUCCESS;
	}
	public String nominatedPostManagement(){
		
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
	
}
