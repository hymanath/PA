package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeVO;
import com.itgrids.partyanalyst.dto.CasteDetailsVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
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
	private List<GenericVO>						genericVOList;
	private List<SelectOptionVO>				selectOptionVOList;
	private List<SelectOptionVO>				eletionTypesList;
	private List<SelectOptionVO>				cadreRolesVOList;
	private List<CasteDetailsVO>				casteDetailsVo;	
	
	private ITdpCadreReportService              tdpCadreReportService;
	private List<LocationWiseBoothDetailsVO>    locations;
	private LocationWiseBoothDetailsVO          membersInfo;
	private List<BasicVO>                       constituencies;
	private Long                                committeeMngtType;
	
	
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

	public String execute()
	{	
		ageRangeList = cadreCommitteeService.getAgeRangeDetailsForCadre();
		casteDetailsVo = cadreRegistrationService.getAllCastes();
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
				casteDetailsVo = cadreRegistrationService.getAllCastes();
				cadreCommitteeVO = cadreCommitteeService.getCadreDetailsByTdpCadreId(Long.valueOf(request.getParameter("tdpCadreId")));
				if(committeeMngtType != null && committeeMngtType.longValue() == 1l){
				   cadreCommitteeVO.setEligibleRoles(cadreCommitteeService.getCadreEligiableRoles(Long.valueOf(request.getParameter("tdpCadreId"))));
				   locations = cadreCommitteeService.getAllTdpCommitteeDesignations();
				}
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
					 locationLvl = 5l;
				 }else{
					 locationLvl = 2l;
				 }
			 }else{
				 if(locationValue.substring(0,1).trim().equalsIgnoreCase("1")){
					 locationLvl = 3l;
				 }else{
					 locationLvl = 6l;
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
						 locationLvl = 5l;
					 }else{
						 locationLvl = 2l;
					 }
				 }else{
					 if(locationValue.substring(0,1).trim().equalsIgnoreCase("1")){
						 locationLvl = 3l;
					 }else{
						 locationLvl = 6l;
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
}
