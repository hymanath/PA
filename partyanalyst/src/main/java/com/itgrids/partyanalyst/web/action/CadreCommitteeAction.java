package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.CasteDetailsVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICandidateUpdationDetailsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.ITdpCadreReportService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreCommitteeAction   extends ActionSupport implements ServletRequestAware{
	
	private static final Logger         		LOG = Logger.getLogger(CadreCommitteeAction.class);
	private HttpServletRequest         			request;
	private HttpSession 						session;
	private ICadreCommitteeService   		 	cadreCommitteeService;
	private ICadreDetailsService 				cadreDetailsService;
	private IStaticDataService					staticDataService;
	private ICandidateUpdationDetailsService 	candidateUpdationDetailsService;
	private ICadreRegistrationService			cadreRegistrationService;
	private CadreCommitteeVO 					cadreCommitteeVO;
	private TdpCadreVO							tdpCadreVO;
	private Long 								tdpCadreId;
	private JSONObject							jObj;
	private String 								task;
	private List<String>						ageRangeList;
	private List<GenericVO>						genericVOList,educationList;
	private List<SelectOptionVO>				selectOptionVOList;
	private List<SelectOptionVO>				eletionTypesList;
	private List<SelectOptionVO>				cadreRolesVOList;
	private List<CasteDetailsVO>				casteDetailsVo;	
	
	private ITdpCadreReportService              tdpCadreReportService;
	private List<LocationWiseBoothDetailsVO>    locations;
	private LocationWiseBoothDetailsVO          membersInfo;
	private List<BasicVO>                       constituencies;
	private Long                                committeeMngtType;
	private EntitlementsHelper 					entitlementsHelper;
	private String 								panchayatId;
	private ResultStatus 						status;
	private String 								panchayatName;				
	private Long 								assemblyId;
	private Long 								committeeTypeId;
	private Long 								committeeId;
	private String 								result1,result2,result3,result4,result5,result6;
	private List<CadreRegisterInfo>             result;
	private ICadreDashBoardService              cadreDashBoardService;
	private List<Long>  						wardResult;
	private List<LocationWiseBoothDetailsVO>    locationWiseBoothDetailsVO;
	
	
	public String getResult4() {
		return result4;
	}

	public void setResult4(String result4) {
		this.result4 = result4;
	}

	public String getResult5() {
		return result5;
	}

	public void setResult5(String result5) {
		this.result5 = result5;
	}

	public String getResult6() {
		return result6;
	}

	public void setResult6(String result6) {
		this.result6 = result6;
	}

	public String getResult1() {
		return result1;
	}

	public void setResult1(String result1) {
		this.result1 = result1;
	}

	public String getResult2() {
		return result2;
	}

	public void setResult2(String result2) {
		this.result2 = result2;
	}

	public String getResult3() {
		return result3;
	}

	public void setResult3(String result3) {
		this.result3 = result3;
	}

	public Long getCommitteeTypeId() {
		return committeeTypeId;
	}

	public void setCommitteeTypeId(Long committeeTypeId) {
		this.committeeTypeId = committeeTypeId;
	}

	public Long getCommitteeId() {
		return committeeId;
	}

	public void setCommitteeId(Long committeeId) {
		this.committeeId = committeeId;
	}

	
	public List<LocationWiseBoothDetailsVO> getLocationWiseBoothDetailsVO() {
		return locationWiseBoothDetailsVO;
	}

	public void setLocationWiseBoothDetailsVO(
			List<LocationWiseBoothDetailsVO> locationWiseBoothDetailsVO) {
		this.locationWiseBoothDetailsVO = locationWiseBoothDetailsVO;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	public ResultStatus getStatus() {
		return status;
	}

	public void setStatus(ResultStatus status) {
		this.status = status;
	}

	public String getpanchayatId() {
		return panchayatId;
	}

	public void setpanchayatId(String panchayatId) {
		this.panchayatId = panchayatId;
	}
	
	
	
	public List<Long> getWardResult() {
		return wardResult;
	}

	public void setWardResult(List<Long> wardResult) {
		this.wardResult = wardResult;
	}

	public ICadreDashBoardService getCadreDashBoardService() {
		return cadreDashBoardService;
	}

	public void setCadreDashBoardService(
			ICadreDashBoardService cadreDashBoardService) {
		this.cadreDashBoardService = cadreDashBoardService;
	}

	public List<CadreRegisterInfo> getResult() {
		return result;
	}

	public void setResult(List<CadreRegisterInfo> result) {
		this.result = result;
	}

	public List<GenericVO> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<GenericVO> educationList) {
		this.educationList = educationList;
	}

	public List<CasteDetailsVO> getCasteDetailsVo() {
		return casteDetailsVo;
	}

	public void setCasteDetailsVo(List<CasteDetailsVO> casteDetailsVo) {
		this.casteDetailsVo = casteDetailsVo;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setCandidateUpdationDetailsService(
			ICandidateUpdationDetailsService candidateUpdationDetailsService) {
		this.candidateUpdationDetailsService = candidateUpdationDetailsService;
	}

	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}

	public List<GenericVO> getGenericVOList() {
		return genericVOList;
	}

	public void setGenericVOList(List<GenericVO> genericVOList) {
		this.genericVOList = genericVOList;
	}

	public List<SelectOptionVO> getSelectOptionVOList() {
		return selectOptionVOList;
	}

	public void setSelectOptionVOList(List<SelectOptionVO> selectOptionVOList) {
		this.selectOptionVOList = selectOptionVOList;
	}

	public List<SelectOptionVO> getEletionTypesList() {
		return eletionTypesList;
	}

	public void setEletionTypesList(List<SelectOptionVO> eletionTypesList) {
		this.eletionTypesList = eletionTypesList;
	}

	public List<SelectOptionVO> getCadreRolesVOList() {
		return cadreRolesVOList;
	}

	public void setCadreRolesVOList(List<SelectOptionVO> cadreRolesVOList) {
		this.cadreRolesVOList = cadreRolesVOList;
	}

	public List<String> getAgeRangeList() {
		return ageRangeList;
	}

	public void setAgeRangeList(List<String> ageRangeList) {
		this.ageRangeList = ageRangeList;
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

	public TdpCadreVO getTdpCadreVO() {
		return tdpCadreVO;
	}

	public void setTdpCadreVO(TdpCadreVO tdpCadreVO) {
		this.tdpCadreVO = tdpCadreVO;
	}

	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}

	public Long getTdpCadreId() {
		return tdpCadreId;
	}

	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}

	public CadreCommitteeVO getCadreCommitteeVO() {
		return cadreCommitteeVO;
	}

	public void setCadreCommitteeVO(CadreCommitteeVO cadreCommitteeVO) {
		this.cadreCommitteeVO = cadreCommitteeVO;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setCadreCommitteeService(ICadreCommitteeService cadreCommitteeService) {
			this.cadreCommitteeService = cadreCommitteeService;
	}
	
	public void setTdpCadreReportService(
			ITdpCadreReportService tdpCadreReportService) {
		this.tdpCadreReportService = tdpCadreReportService;
	}

	public List<LocationWiseBoothDetailsVO> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationWiseBoothDetailsVO> locations) {
		this.locations = locations;
	}

	public List<BasicVO> getConstituencies() {
		return constituencies;
	}

	public void setConstituencies(List<BasicVO> constituencies) {
		this.constituencies = constituencies;
	}
	
	public LocationWiseBoothDetailsVO getMembersInfo() {
		return membersInfo;
	}

	public void setMembersInfo(LocationWiseBoothDetailsVO membersInfo) {
		this.membersInfo = membersInfo;
	}

	public Long getCommitteeMngtType() {
		return committeeMngtType;
	}

	public void setCommitteeMngtType(Long committeeMngtType) {
		this.committeeMngtType = committeeMngtType;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public Long getAssemblyId() {
		return assemblyId;
	}

	public void setAssemblyId(Long assemblyId) {
		this.assemblyId = assemblyId;
	}

	public String execute()
	{	
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_COMMITTEE_MANAGEMENT")){
			noaccess = true ;
		}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		
		if(noaccess){
			return "error";
		}
		ageRangeList = cadreCommitteeService.getAgeRangeDetailsForCadre();
		genericVOList = cadreCommitteeService.getAllCasteDetailsForState();
		
		
		if(panchayatId == null) //default values for prepopulate fields
		{
			panchayatId = "0";
			committeeTypeId = 0L;
			committeeId = 0L;
			result3 = "0";
		}
		
		return Action.SUCCESS;
	}
	
	public List<BasicVO> getUserAccessConstituencies(){
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		return tdpCadreReportService.getAccessLocationValues(user.getAccessType(),Long.valueOf(user.getAccessValue().trim()));
	}
	
	public String getCommitteLocations(){
		try{
		   String locationType = request.getParameter("locationType");
		   constituencies = getUserAccessConstituencies();

		   locations = cadreCommitteeService.getLocationsList(constituencies.get(0).getId(),locationType);
		   
		}catch(Exception e){
			LOG.error("Exception rised in getComitteLocations",e);
		}
			return Action.SUCCESS;
		
	}
	
	public String getCadreProfileDetails()
	{
		try {
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO != null) 
			{
				genericVOList = candidateUpdationDetailsService.gettingEducationDetails();
				selectOptionVOList = staticDataService.getAllOccupations();
				eletionTypesList = cadreRegistrationService.getElectionOptionDetailsForCadre();
				cadreRolesVOList = cadreRegistrationService.getCadreLevelsForCadreSearch();
				educationList = cadreCommitteeService.getAllCasteDetailsForState();
				cadreCommitteeVO = cadreCommitteeService.getCadreDetailsByTdpCadreId(Long.valueOf(request.getParameter("tdpCadreId")));
				panchayatName = cadreCommitteeVO.getElectrolLocation();
				if(committeeMngtType != null && committeeMngtType.longValue() == 2l){
				   cadreCommitteeVO.setEligibleRoles(cadreCommitteeService.getCadreEligiableRoles(Long.valueOf(request.getParameter("tdpCadreId"))));
				   locations = cadreCommitteeService.getAllTdpCommitteeDesignations();
				}
				assemblyId = getUserAccessConstituencies().get(0).getId();
			} 
			else{
				return ERROR;
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in getCadreProfileDetails() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	public String getSearchDetails()
	{
		try {
			jObj = new JSONObject(getTask());
			Long locationLevel = jObj.getLong("locationLevel");
			Long locationValue = jObj.getLong("locationValue");
			String searchName = jObj.getString("searchName");
			String mobileNo = jObj.getString("mobileNo");
			
			Long casteStateId = jObj.getLong("casteStateId");
			String casteCategory = jObj.getString("casteCategory");
			Long fromAge = jObj.getLong("fromAge");
			Long toAge = jObj.getLong("toAge");
			String houseNo = jObj.getString("houseNo");
			String memberShipCardNo = jObj.getString("memberShipCardNo");
			String trNumber = jObj.getString("trNumber");
			String voterCardNo = jObj.getString("voterCardNo");
			String gender = jObj.getString("gender");
			
			tdpCadreVO = cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationLevel,locationValue, searchName,memberShipCardNo, 
					voterCardNo, trNumber, mobileNo,casteStateId,casteCategory,fromAge,toAge,houseNo,gender);
		} catch (Exception e) {
			LOG.error("Exception occured in getSearchDetails() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String generateOTP()
	{
		try {
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO != null) 
			{
				jObj = new JSONObject(getTask());
				Long mobileNo = jObj.getLong("mobileNo");
				task = cadreCommitteeService.genarateOTP(registrationVO.getRegistrationID(),mobileNo);
			} 
			else{
				return ERROR;
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in getSearchDetails() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String validateOTPForMobile()
	{
		try {
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if (registrationVO != null) 
			{
				jObj = new JSONObject(getTask());
				Long mobileNo = jObj.getLong("mobileNo");
				Long refNo = jObj.getLong("refNo");
				Long otpNo = jObj.getLong("otpNo");
				task = cadreCommitteeService.validateOTPForUser(registrationVO.getRegistrationID(),mobileNo,refNo,otpNo);
			} 
			else{
				return ERROR;
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in getSearchDetails() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getAllAffiliatedCommitties(){
		try {
			 String locationType = request.getParameter("locationType").trim();
			 String locationValue = request.getParameter("locationValue").trim();
			 Long locationLvl = null;
			 if(locationType.equalsIgnoreCase("mandal")){
				 if(locationValue.substring(0,1).trim().equalsIgnoreCase("1")){
					 locationLvl = 7l;
				 }else{
					 locationLvl = 5l;
				 }
			 }else{
				 if(locationValue.substring(0,1).trim().equalsIgnoreCase("1")){
					 locationLvl = 6l;
				 }else{
					 locationLvl = 8l;
				 }
			 }
			locations = cadreCommitteeService.getAllAffiliatedCommittiesInALocation(locationLvl,locationValue != null && locationValue.trim().length()>0 ?Long.valueOf(locationValue.substring(1)):0L);
		} catch (Exception e) {
			LOG.error("Exception occured in getAllAffiliatedCommitties() At CadreCommitteeAction ",e);
		}		
		return Action.SUCCESS;
	}
	
	public String getCommitteMembersInfo(){
		try {
			String committeeType = request.getParameter("committeeType").trim();
			if(committeeType.equalsIgnoreCase("main")){
				 String locationType = request.getParameter("locationType").trim();
				 String locationValue = request.getParameter("locationValue").trim();
				 Long locationLvl = null;
				 if(locationType.equalsIgnoreCase("mandal")){
					 if(locationValue.substring(0,1).trim().equalsIgnoreCase("1")){
						 locationLvl = 7l;
					 }else{
						 locationLvl = 5l;
					 }
				 }else{
					 if(locationValue.substring(0,1).trim().equalsIgnoreCase("1")){
						 locationLvl = 6l;
					 }else{
						 locationLvl = 8l;
					 }
				 }
				 membersInfo = cadreCommitteeService.getMainCommitteeMembersInfo(locationLvl,Long.valueOf(locationValue.substring(1)));
			}else{
				membersInfo = cadreCommitteeService.getCommitteeMembersInfo(Long.valueOf(request.getParameter("locationValue")));
			}
		} catch (Exception e) {
			LOG.error("Exception occured in getCommitteMembersInfo() At CadreCommitteeAction ",e);
		}		
		return Action.SUCCESS;
	}
	
	public String getTdpCommitteeDesignations(){
		try {
			locations = cadreCommitteeService.getAllTdpCommitteeDesignations();
		}catch (Exception e) {
			LOG.error("Exception occured in getTdpCommitteeDesignations() At CadreCommitteeAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getCasteDetailsBygroupId(){
		try{
			jObj = new JSONObject(getTask());
			genericVOList = cadreCommitteeService.getCadsteDetailsByGroupId(jObj.getLong("casteGroupId"));
		}
		catch(Exception e){	
			LOG.error("Exception occured in getCasteDetailsBygroupId() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String saveCadreCommitteDetails()
	{
		
		try{
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
			if(registrationVO == null)
			{
				return Action.ERROR;
			}
			
			jObj = new JSONObject(getTask());
			status = cadreCommitteeService.saveCadreCommitteDetails(registrationVO.getRegistrationID(),jObj.getLong("tdpCadreId"),jObj.getLong("roleId"));
		}
		catch(Exception e){	
			LOG.error("Exception occured in saveCadreCommitteDetails() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String allConstituencys(){
		try{
			if(task.equalsIgnoreCase("assemblyNames")){
				result = cadreDashBoardService.getAssemblyConstituencies(request.getParameter("type"));
				}
		}catch(Exception e){
				LOG.error("Exception rised in allConstituencys method ",e);
		}
		return Action.SUCCESS;
		}
	public String getPanchayatiesByMandalOrMuncipality(){
		
		try{
			jObj = new JSONObject(getTask());
			genericVOList = cadreCommitteeService.getPanchayatDetailsByMandalId(jObj.getLong("mandalId"));
		}
		catch(Exception e){	
			LOG.error("Exception occured in getPanchayatiesByMandalOrMuncipality() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;	
	}
	public String getBoothsInPanchayat(){
		try{
			jObj = new JSONObject(getTask());
			wardResult=cadreCommitteeService.getBoothsInPanchayatId(jObj.getLong("panchayatId"));
		}catch(Exception e){
			LOG.error("Exception occured in getPanchayatiesByMandalOrMuncipality() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	public String getMandalMunicCorpDetails(){
		
		try{
			jObj = new JSONObject(getTask());
			locationWiseBoothDetailsVO=cadreCommitteeService.getMandalMunicCorpDetails(jObj.getLong("constituencyId"));
		}catch(Exception e){
			LOG.error("Exception occured in getMandalMunicCorpDetails() At CadreCommitteeAction ",e);
		}
		
		return Action.SUCCESS;
	}
	public String sessionChecking(){
		
		session = request.getSession();	
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		
		return Action.SUCCESS;
	}
	
	public String assignCadreToCommitee()
	{	
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		boolean noaccess = false;
		if(regVO==null){
			return "input";
		}if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)request.getSession().getAttribute(IConstants.USER),"CADRE_COMMITTEE_MANAGEMENT")){
			noaccess = true ;
		}
		if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
			noaccess = false;
		}
		
		if(noaccess){
			return "error";
		}
		
		return Action.SUCCESS;
	}
}
