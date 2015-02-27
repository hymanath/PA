package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.ByeElectionVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeReportVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CommitteeSummaryVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ITdpCadreReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.webservice.android.abstractservice.IWebServiceHandlerService1;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CommitteeDashBoardAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3145620802823411748L;
	private HttpServletRequest         			request;
	private EntitlementsHelper 					entitlementsHelper;
	private JSONObject							jObj;
	private String 								task,pageAccessType;
	private LocationWiseBoothDetailsVO          locationWiseBoothDetailsVO;
	private ICadreCommitteeService				cadreCommitteeService;
	@Autowired 
	private IWebServiceHandlerService1 			webServiceHandlerService1;
	private static final Logger         		LOG = Logger.getLogger(CommitteeDashBoardAction.class);
	
	private CadreCommitteeReportVO          	cadreCommitteeReportVO;
	private List<CommitteeSummaryVO>   		 	districtWiseSummaryList,constiWiseSummaryList;
	private List<CadreCommitteeReportVO> 		cadreCommitteeReportVOList;
	private List<CadreCommitteeMemberVO> 		cadreCommitteeMemberVOList;
	private List<IdNameVO>  					idNameVOList;
	private CommitteeSummaryVO 					constiSummaryVO;
	private ITdpCadreReportService 				tdpCadreReportService;
	private String 								accessConstituency;
	private Long								accessConstituencyId;
	private ICadreRegistrationService 			cadreRegistrationService;
	private CadreCommitteeMemberVO 				boothsInfo;
	private ByeElectionVO 						byeEleInfo;
	
	public ByeElectionVO getByeEleInfo() {
		return byeEleInfo;
	}
	public void setByeEleInfo(ByeElectionVO byeEleInfo) {
		this.byeEleInfo = byeEleInfo;
	}
	
	public String getPageAccessType() {
		return pageAccessType;
	}
	public void setPageAccessType(String pageAccessType) {
		this.pageAccessType = pageAccessType;
	}
	
	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}
	public String getAccessConstituency() {
		return accessConstituency;
	}
	public void setAccessConstituency(String accessConstituency) {
		this.accessConstituency = accessConstituency;
	}
	public Long getAccessConstituencyId() {
		return accessConstituencyId;
	}
	public void setAccessConstituencyId(Long accessConstituencyId) {
		this.accessConstituencyId = accessConstituencyId;
	}
	public ITdpCadreReportService getTdpCadreReportService() {
		return tdpCadreReportService;
	}
	public void setTdpCadreReportService(
			ITdpCadreReportService tdpCadreReportService) {
		this.tdpCadreReportService = tdpCadreReportService;
	}
	public CommitteeSummaryVO getConstiSummaryVO() {
		return constiSummaryVO;
	}
	public void setConstiSummaryVO(CommitteeSummaryVO constiSummaryVO) {
		this.constiSummaryVO = constiSummaryVO;
	}
	public List<CommitteeSummaryVO> getConstiWiseSummaryList() {
		return constiWiseSummaryList;
	}
	public void setConstiWiseSummaryList(
			List<CommitteeSummaryVO> constiWiseSummaryList) {
		this.constiWiseSummaryList = constiWiseSummaryList;
	}
	public List<CommitteeSummaryVO> getDistrictWiseSummaryList() {
		return districtWiseSummaryList;
	}
	public void setDistrictWiseSummaryList(
			List<CommitteeSummaryVO> districtWiseSummaryList) {
		this.districtWiseSummaryList = districtWiseSummaryList;
	}
	public List<CadreCommitteeReportVO> getCadreCommitteeReportVOList() {
		return cadreCommitteeReportVOList;
	}
	public void setCadreCommitteeReportVOList(
			List<CadreCommitteeReportVO> cadreCommitteeReportVOList) {
		this.cadreCommitteeReportVOList = cadreCommitteeReportVOList;
	}
	public LocationWiseBoothDetailsVO getLocationWiseBoothDetailsVO() {
		return locationWiseBoothDetailsVO;
	}
	public void setLocationWiseBoothDetailsVO(
			LocationWiseBoothDetailsVO locationWiseBoothDetailsVO) {
		this.locationWiseBoothDetailsVO = locationWiseBoothDetailsVO;
	}
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public CadreCommitteeReportVO getCadreCommitteeReportVO() {
		return cadreCommitteeReportVO;
	}
	public void setCadreCommitteeReportVO(
			CadreCommitteeReportVO cadreCommitteeReportVO) {
		this.cadreCommitteeReportVO = cadreCommitteeReportVO;
	}
	
	
	public List<CadreCommitteeMemberVO> getCadreCommitteeMemberVOList() {
		return cadreCommitteeMemberVOList;
	}
	public void setCadreCommitteeMemberVOList(
			List<CadreCommitteeMemberVO> cadreCommitteeMemberVOList) {
		this.cadreCommitteeMemberVOList = cadreCommitteeMemberVOList;
	}
	
	
	public List<IdNameVO> getIdNameVOList() {
		return idNameVOList;
	}
	public void setIdNameVOList(List<IdNameVO> idNameVOList) {
		this.idNameVOList = idNameVOList;
	}
	
	public CadreCommitteeMemberVO getBoothsInfo() {
		return boothsInfo;
	}
	public void setBoothsInfo(CadreCommitteeMemberVO boothsInfo) {
		this.boothsInfo = boothsInfo;
	}
	public String execute(){
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}if(!(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TDP_COMMITTEE_ADMIN") ||
				entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TDP_COMMITTEE_AREAWISE_ACCESS"))){
			noaccess = true ;
		}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		
		
		pageAccessType = cadreCommitteeService.userAccessTypeDetailsForDashBoard(regVO.getRegistrationID(),regVO.getAccessType(),Long.valueOf(regVO.getAccessValue().trim()));
		String accessType = regVO.getAccessType();
		if(accessType.equalsIgnoreCase("MLA") || accessType.equalsIgnoreCase("MP"))
		{
			pageAccessType = accessType;
		}
		return Action.SUCCESS;
	}
	
	
	public String getDashBoardLocationWiseDetailsAction(){
		try{
			
			boolean noaccess = false;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO != null)
			{
				String accessType = regVO.getAccessType();
				Long accessValue = Long.valueOf(regVO.getAccessValue());
				
				jObj = new JSONObject(getTask());
				JSONArray levelIdsArr = jObj.getJSONArray("levelIdsArr");			
				List<Long> levelIds = new ArrayList<Long>();
					
				if(levelIdsArr !=null && levelIdsArr.length() >0){
					for(int i=0; i<levelIdsArr.length(); i++ ){
						levelIds.add(Long.valueOf(levelIdsArr.get(i).toString().trim()));
					}
				}   
				String state =jObj.getString("state");
				String startDate=jObj.getString("startDate");
				String endDate=jObj.getString("endDate");
				
				cadreCommitteeReportVO = cadreCommitteeService.getCommitteeDetailsByLocation(state,levelIds,startDate,endDate,regVO.getRegistrationID(),accessType,accessValue);
			}
			else
			{
				noaccess = true;
			}
			
			if(noaccess)
					return Action.ERROR;
			
		}catch(Exception e){
			LOG.error("Exception Occured In getDashBoardLocationWiseDetailsAction method "+e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getTotalCommitteeCntsByState(){
		try{
			boolean noaccess = false;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO != null)
			{
				String accessType = regVO.getAccessType();
				Long accessValue = Long.valueOf(regVO.getAccessValue());
				
				jObj = new JSONObject(getTask());
				
				String state =jObj.getString("state");
				
				cadreCommitteeReportVO = cadreCommitteeService.getTotalCommitteeDetailsByLocation(state,regVO.getRegistrationID(),accessType,accessValue);
			}
			else{
				noaccess = true;
			}
			
			if(noaccess){
				return "error";
			}
			
		}catch(Exception e){
			LOG.error("Exception Occured In getDashBoardLocationWiseDetailsAction method "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getDistrictWiseCommittesSummary(){
		LOG.debug(" Entered Into getDistrictWiseCommittesSummary");
		try{
			
			boolean noaccess = false;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO != null)
			{
				String accessType = regVO.getAccessType();
				Long accessValue = Long.valueOf(regVO.getAccessValue());
				
				jObj = new JSONObject(getTask());
				String state =jObj.getString("state");
				String mandalCheck = jObj.getString("mandalCheck");
				String villageCheck =  jObj.getString("villageCheck");
				String startDate = jObj.getString("startDate");
				String endDate = jObj.getString("endDate");
				
				districtWiseSummaryList = cadreCommitteeService.getDistrictWiseCommittesSummary(state, startDate,endDate,regVO.getRegistrationID(),accessType,accessValue,mandalCheck,villageCheck);
			}
			else{
				noaccess = true;
			}
			
			if(noaccess){
				return Action.ERROR;
			}
			
		}catch (Exception e) {
			LOG.error(" Exception Raised In getDistrictWiseCommittesSummary" +e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getStartedAffliCommitteesCountByLocation(){
		try{
			jObj = new JSONObject(getTask());
			JSONArray levelIdsArr = jObj.getJSONArray("levelIdsArr");			
			List<Long> levelIds = new ArrayList<Long>();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO != null)
			{
				String accessType = regVO.getAccessType();
				Long accessValue = Long.valueOf(regVO.getAccessValue());
					
			if(levelIdsArr !=null && levelIdsArr.length() >0){
				for(int i=0; i<levelIdsArr.length(); i++ ){
					levelIds.add(Long.valueOf(levelIdsArr.get(i).toString().trim()));
				}
			}
			String state =jObj.getString("state");
			String startdate=jObj.getString("startDate");
			String endDate=jObj.getString("endDate");
			String committeeType = jObj.getString("committeeType");
			cadreCommitteeReportVOList = cadreCommitteeService.getStartedAffliCommitteesCountByLocation(state,levelIds,startdate,endDate,accessType,accessValue,regVO.getRegistrationID(), committeeType);
			}
			
		}catch(Exception e){
			LOG.error("Exception Occured In getDashBoardLocationWiseDetailsAction method "+e);
		}
		return Action.SUCCESS;
	}
	
	public String getMembersRangeCountByLocation(){
		try{
			RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
			jObj = new JSONObject(getTask());
			JSONArray levelIdsArr = jObj.getJSONArray("levelIdsArr");			
			List<Long> levelIds = new ArrayList<Long>();
				
			if(levelIdsArr !=null && levelIdsArr.length() >0){
				for(int i=0; i<levelIdsArr.length(); i++ ){
					levelIds.add(Long.valueOf(levelIdsArr.get(i).toString().trim()));
				}
			}
			String state =jObj.getString("state");
			Long committeeId = jObj.getLong("committeeId");
			String startdate=jObj.getString("startDate");
			String endDate=jObj.getString("endDate");
			String committeeType = jObj.getString("committeeType");
			String accessType = regVo.getAccessType();
			Long accessValue = Long.valueOf(regVo.getAccessValue());
			cadreCommitteeReportVOList = cadreCommitteeService.getMembersRangeCountByLocation(state,levelIds,committeeId,startdate,endDate,accessType,accessValue,regVo.getRegistrationID(),committeeType);
		
			
		}catch(Exception e){
			LOG.error("Exception Occured In getDashBoardLocationWiseDetailsAction method "+e);
		}
		return Action.SUCCESS;
	}



	
	public String getConstituencyWiseCommittesSummary(){
		LOG.debug(" Entered Into getConstituencyWiseCommittesSummary");
		try{
			boolean noaccess = false;
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO != null)
			{
				String accessType = regVO.getAccessType();
				Long accessValue = Long.valueOf(regVO.getAccessValue());
				
				jObj = new JSONObject(getTask());
				String state =jObj.getString("state");
				String mandalCheck = jObj.getString("mandalCheck");
				String villageCheck =  jObj.getString("villageCheck");
				String startDate = jObj.getString("startDate");
				String endDate = jObj.getString("endDate");
			
				constiWiseSummaryList = cadreCommitteeService.getConstituencyWiseCommittesSummary(state, startDate, endDate,regVO.getRegistrationID(),accessType,accessValue,mandalCheck,villageCheck);
			}
			else
			{
				noaccess = true;
			}
			
			if(noaccess)
				return Action.ERROR;
			
		}catch (Exception e) {
			LOG.error(" Exception Raised In getConstituencyWiseCommittesSummary" +e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getSummaryDetailsPopUp(){
		try{
			jObj = new JSONObject(getTask());
			districtWiseSummaryList = cadreCommitteeService.getSummaryDetails(jObj.getString("constituencyId"));
		}catch(Exception e){
			LOG.error("Exception Occured In getSummaryDetailsPopUp method "+e);			
		}
		return Action.SUCCESS;
	}
	public String gettingMandalAndMuncipalAndDivisonSummaryPopUp(){
		try{
			jObj = new JSONObject(getTask());
			districtWiseSummaryList= cadreCommitteeService.gettingMandalAndMuncipalAndDivisonSummary(jObj.getString("constituencyId"));
		}catch(Exception e){
			LOG.error("Exception Occured In gettingMandalAndMuncipalAndDivisonSummary method "+e);			
		}
		return Action.SUCCESS;
	}
public String getCommitteeDetailsByStatusPopUp(){

		
		try{
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("memberCnt"))
			cadreCommitteeMemberVOList = cadreCommitteeService.getCommitteeDetailsByStatus(jObj.getLong("basicCommitteetypeId"),jObj.getString("status"),jObj.getLong("levelId"),jObj.getString("constituencyId"));
			else if(jObj.getString("task").equalsIgnoreCase("memberInfo"))
				cadreCommitteeMemberVOList = cadreCommitteeService.getCommitteeMemberDetails(jObj.getLong("basicCommitteetypeId"),jObj.getLong("locationId"),jObj.getLong("levelId"),jObj.getString("status"));
			else if(jObj.getString("task").equalsIgnoreCase("committeComplete"))
				cadreCommitteeMemberVOList = cadreCommitteeService.setCommitteConfirmation(jObj.getLong("basicCommitteetypeId"),jObj.getLong("locationId"),jObj.getLong("levelId"));
			else if(jObj.getString("task").equalsIgnoreCase("deleterole"))
				cadreCommitteeMemberVOList = cadreCommitteeService.deleteCadreRole(jObj.getLong("tdpcommitteeMemberId"));
		}catch(Exception e){
			LOG.error("Exception occured in getCommitteeDetailsByStatus() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}

public String constituencyCommitteeSummaryAction()
{
	RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
	boolean noaccess = false;
	boolean cadreUser = false;
	
	if(regVO==null){
		return "input";
	}if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_COMMITTEE_MANAGEMENT") && !entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TDP_COMMITTEE_ADMIN")){
		noaccess = true ;
	}
	
	if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_COMMITTEE_MANAGEMENT")){
		cadreUser = true;
		List<BasicVO> accLoc = getUserAccessConstituencies();
		accessConstituency = accLoc.get(0).getName();
		accessConstituencyId = accLoc.get(0).getId();
	}
	
	if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TDP_COMMITTEE_AREAWISE_ACCESS")){
		noaccess = false ;
	}
	if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
		noaccess = false;
	}
	
	if(noaccess){
		return "error";
	}
	
	
	
	return Action.SUCCESS;
}

public List<BasicVO> getUserAccessConstituencies(){
	HttpSession session = request.getSession();
	RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
	return tdpCadreReportService.getAccessLocationValues(user.getAccessType(),Long.valueOf(user.getAccessValue().trim()));	
}

public String getAllDistricts(){
	try{
		
		idNameVOList= cadreCommitteeService.getAllDistricts();
	}catch(Exception e){
		LOG.error("Exception Occured In getAllDistricts method "+e);			
	}
	return Action.SUCCESS;
}
public String getAllConstituencysForADistrict(){
	try{
		Long districtId=Long.parseLong(request.getParameter("districtId"));
		idNameVOList= cadreCommitteeService.getAllConstituencysForADistrict(districtId);
	}catch(Exception e){
		LOG.error("Exception Occured In getAllConstituencysForADistrict method "+e);			
	}
	return Action.SUCCESS;
}

	public String getConstituencyCommitteeSummary(){
		try{
			jObj = new JSONObject(getTask());
			constiSummaryVO = cadreCommitteeService.getConstituencySummary(jObj.getLong("reportType"), jObj.getLong("constituencyId"));
		}catch(Exception e){
			LOG.error("Exception Occured In getSummaryDetailsPopUp method "+e);			
		}
		return Action.SUCCESS;
	} 
	public String gettingCadreDetails(){

		try{
			jObj = new JSONObject(getTask());
			if(jObj.getString("type").equalsIgnoreCase("electrols"))
			cadreCommitteeMemberVOList = cadreCommitteeService.getElectrolsOfPanchayatAndWards(jObj.getLong("locationId"),jObj.getLong("locationType"),jObj.getLong("basicCommitteeTypeId"));
			else 
			cadreCommitteeMemberVOList = cadreCommitteeService.getComitteeMembersInfoByCommiteTypeAndLocation(jObj.getLong("locationId"),jObj.getLong("locationType"),jObj.getLong("basicCommitteeTypeId"),"status");
			
		}catch(Exception e){
			LOG.error("Exception occured in getCommitteeDetailsByStatus() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String saveFieldMessages(){		
		try{ 
			CadreRegistrationVO vo = new CadreRegistrationVO();
			vo.setMobileNumber(request.getParameter("mobilenumber"));
			vo.setArea(request.getParameter("message"));
			LOG.error("In Action mobilenumber"+vo.getMobileNumber()+",message"+vo.getArea());
			if((vo.getMobileNumber() != null && vo.getMobileNumber().trim().length() >0) || (vo.getArea() != null && vo.getArea().trim().length() >0)){
				accessConstituency = webServiceHandlerService1.saveStatus(vo);
				return Action.SUCCESS;
			}else{
				accessConstituency =  "Invalid Inputs !";
				return Action.SUCCESS;
			}
		}
		catch(Exception e){
			LOG.error("Exception occured in saveFieldMessages ",e);
			accessConstituency =  "Error Occured";
		}
	
	   return Action.SUCCESS;
	}
	
	public String getBoothsCurrentStatus(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			boolean noaccess = false;
			
			jObj = new JSONObject(getTask());
			//if(jObj.getString("task").equalsIgnoreCase("summary"))
				 //boothsInfo = cadreRegistrationService.getBoothsCurrentStatusSummary(new Long(regVO.getAccessValue()));
			//else
		//  boothsInfo = cadreRegistrationService.getBoothsCurrentStatus(new Long(regVO.getAccessValue()));
		}catch(Exception e){
			LOG.error("Exception occured in getBoothsCurrentStatus ",e);
		}
	   return Action.SUCCESS;
	}
	
	public String currentBoothsStatus(){
		boolean noaccess = false;
		RegistrationVO regVo = (RegistrationVO) request.getSession().getAttribute("USER");
		
		if(regVo==null){
			return "input";
		}if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"TIRUPATHI_BYEELECTION")){
			noaccess = true ;
		}
		if(regVo.getIsAdmin() != null && regVo.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		if(noaccess){
			return "error";
		}
		   return Action.SUCCESS;
		}
	public String getByeElectionBoothsCurrentStatus(){
		try{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			boolean noaccess = false;
			
			jObj = new JSONObject(getTask());
			/*if(jObj.getString("task").equalsIgnoreCase("ByeElesummary"))
				 byeEleInfo = cadreRegistrationService.getByeEleBoothsCurrentStatusSummary(new Long(regVO.getAccessValue()));*/
			 if(jObj.getString("task").equalsIgnoreCase("ByeEleInfo"))
				//byeEleInfo = cadreRegistrationService.getByeEleBoothsCurrentStatus(new Long(regVO.getAccessValue()));
				byeEleInfo= cadreRegistrationService.getByeEleBoothsCurrentStatusReport(new Long(regVO.getAccessValue()),jObj.getLong("typeId"),jObj.getString("type"));
			else if(jObj.getString("task").equalsIgnoreCase("ByeElesummaryStatusInfo"))
				byeEleInfo= cadreRegistrationService.getByeEleBoothsCurrentStatusSummaryInfo1(new Long(regVO.getAccessValue()),jObj.getString("status"),jObj.getLong("typeId"),jObj.getString("type"));
			else if(jObj.getString("task").equalsIgnoreCase("byeEleerrorInfo"))
				byeEleInfo= cadreRegistrationService.getByeEleBoothsErrorInfo();
			else if(jObj.getString("task").equalsIgnoreCase("byeElelatestUpdates"))
				byeEleInfo= cadreRegistrationService.getByeElelatestUpdates(jObj.getInt("startIndex"),jObj.getInt("maxIndex"));
			
			
			
		}catch(Exception e){
			LOG.error("Exception occured in getBoothsCurrentStatus ",e);
		}
	   return Action.SUCCESS;
	}
	
	public String getClustesAndDivisionNames(){
		try{
			jObj = new JSONObject(getTask());
			cadreCommitteeMemberVOList = cadreRegistrationService.getClustesAndDivisionNames(jObj.getLong("typeId"));
		}catch(Exception e){
			LOG.error("Exception occured in getClustesAndDivisionNames ",e);
		}
	   return Action.SUCCESS;
	}
	
	public String getMessagesInfo(){
		try{
			Integer startIndex = null;
			Integer maxIndex = null;
			String startIndexStr = request.getParameter("startIndex").trim();
			String maxIndexStr = request.getParameter("maxIndex").trim();
			if(startIndexStr.length() > 0){
				startIndex = Integer.parseInt(startIndexStr);
			}
			if(maxIndexStr.length() > 0){
				maxIndex = Integer.parseInt(maxIndexStr);
			}
			request.getParameter("maxIndex");
			byeEleInfo = cadreRegistrationService.getMessagesInfo(startIndex,maxIndex);
		}catch(Exception e){
			LOG.error("Exception getMessagesInfo ",e);
		}
		return Action.SUCCESS;
	}
}
