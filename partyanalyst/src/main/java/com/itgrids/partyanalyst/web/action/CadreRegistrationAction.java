package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterInfoVO;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICandidateUpdationDetailsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.ISurveyDataDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreRegistrationAction  extends ActionSupport implements ServletRequestAware{

	private static final Logger         		LOG = Logger.getLogger(CadreRegistrationAction.class);
	private HttpServletRequest         			request;
	private HttpSession 						session;
	private ICadreRegistrationService   		cadreRegistrationService;
	
	private String 								task;
	private JSONObject                  		jobj;
	private CadreRegistrationVO					cadreRegistrationVO;
	private List<CadrePreviousRollesVO> 		previousRollesList;
	private List<CadrePreviousRollesVO> 		previousElectionssList;
	private ResultStatus						resultStatus;
	
	private List<VoterInfoVO> 					voterInfoVOList;
	private List<GenericVO> 					genericVOList;
	private List<SelectOptionVO> 				selectOptionVOList;
	private ICandidateUpdationDetailsService	candidateUpdationDetailsService;
	private IStaticDataService					staticDataService;
	private ISurveyDataDetailsService			surveyDataDetailsService;
	
	private String								searchType;
	private String 								candidateId;
	private String 								constiteucnyId;
	private String 								candidateName;
	private String 								voterCardNo;
	private String								houseNo;
	
	

	public String getConstiteucnyId() {
		return constiteucnyId;
	}

	public void setConstiteucnyId(String constiteucnyId) {
		this.constiteucnyId = constiteucnyId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getVoterCardNo() {
		return voterCardNo;
	}

	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public ISurveyDataDetailsService getSurveyDataDetailsService() {
		return surveyDataDetailsService;
	}

	public void setSurveyDataDetailsService(
			ISurveyDataDetailsService surveyDataDetailsService) {
		this.surveyDataDetailsService = surveyDataDetailsService;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public JSONObject getJobj() {
		return jobj;
	}

	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<VoterInfoVO> getVoterInfoVOList() {
		return voterInfoVOList;
	}

	public void setVoterInfoVOList(List<VoterInfoVO> voterInfoVOList) {
		this.voterInfoVOList = voterInfoVOList;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
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

	public ICandidateUpdationDetailsService getCandidateUpdationDetailsService() {
		return candidateUpdationDetailsService;
	}

	public void setCandidateUpdationDetailsService(
			ICandidateUpdationDetailsService candidateUpdationDetailsService) {
		this.candidateUpdationDetailsService = candidateUpdationDetailsService;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}

	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public CadreRegistrationVO getCadreRegistrationVO() {
		return cadreRegistrationVO;
	}


	public void setCadreRegistrationVO(CadreRegistrationVO cadreRegistrationVO) {
		this.cadreRegistrationVO = cadreRegistrationVO;
	}


	public List<CadrePreviousRollesVO> getPreviousRollesList() {
		return previousRollesList;
	}


	public void setPreviousRollesList(List<CadrePreviousRollesVO> previousRollesList) {
		this.previousRollesList = previousRollesList;
	}


	public List<CadrePreviousRollesVO> getPreviousElectionssList() {
		return previousElectionssList;
	}


	public void setPreviousElectionssList(
			List<CadrePreviousRollesVO> previousElectionssList) {
		this.previousElectionssList = previousElectionssList;
	}


	public String execute()
	{
		try {
			LOG.info("Entered into execute method in CadreRegistrationAction Action");
		} catch (Exception e) {
			LOG.error("Exception raised in execute method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	
	
	public String saveCadreDetails()
	{
		try {
			LOG.info("Entered into saveCadreDetails method in CadreRegistrationAction Action");
			/*CadreRegistrationVO cadreRegistrationVO = new CadreRegistrationVO();
			cadreRegistrationVO.setVoterName("PRASAD");
			cadreRegistrationVO.setDob(convertToDateFormet("22-12-2014"));
			cadreRegistrationVO.setGender("M");
			cadreRegistrationVO.setRelativeName("PRASAD");
			cadreRegistrationVO.setVoterId(13812457l);
			cadreRegistrationVO.setPreviousEnrollmentNumber("12345678");
			cadreRegistrationVO.setPartyMemberSince(convertToDateFormet("22-12-2014"));
			cadreRegistrationVO.setBloodGroupId(3l);
			cadreRegistrationVO.setCasteId(23l);
			cadreRegistrationVO.setEducationId(2l);
			cadreRegistrationVO.setOccupationId(2l);
			cadreRegistrationVO.setMobileNumber("9494302021");
			cadreRegistrationVO.setPreviousEnrollmentNumber("123456");
			cadreRegistrationVO.setHouseNo("123");
			
			CadrePreviousRollesVO rolesVO1 = new CadrePreviousRollesVO();
			
			rolesVO1.setDesignationLevelId(1l);
			rolesVO1.setDesignationLevelValue(1l);
			rolesVO1.setFromDate(convertToDateFormet("22-12-2014"));
			rolesVO1.setToDate(convertToDateFormet("22-12-2014"));
			
			
			CadrePreviousRollesVO rolesVO2 = new CadrePreviousRollesVO();
			
			rolesVO2.setDesignationLevelId(2l);
			rolesVO2.setDesignationLevelValue(2l);
			rolesVO2.setFromDate(convertToDateFormet("22-12-2014"));
			rolesVO2.setToDate(convertToDateFormet("22-12-2014"));
			
			
			CadrePreviousRollesVO electionVO = new CadrePreviousRollesVO();
			
			electionVO.setElectionTypeId(1l);
			electionVO.setConstituencyId(232l);
			
			List<CadrePreviousRollesVO> 	previousRollesList = new ArrayList<CadrePreviousRollesVO>();
			List<CadrePreviousRollesVO> 	previousElectionssList = new ArrayList<CadrePreviousRollesVO>();
			
			
			previousRollesList.add(rolesVO1);
			previousRollesList.add(rolesVO2);
			previousElectionssList.add(electionVO);
			
			cadreRegistrationVO.setPreviousRollesList(previousRollesList);
			cadreRegistrationVO.setPreviousParicaptedElectionsList(previousElectionssList);*/
			
			resultStatus = cadreRegistrationService.saveCadreRegistration(cadreRegistrationVO);
		} catch (Exception e) {
			LOG.error("Exception raised in saveCadreDetails method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public Date convertToDateFormet(String dateStr)
	{
		Date date = null;
		try {
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			date = originalFormat.parse(dateStr);
		} catch (Exception e) {
			LOG.error("Exception raised in convertToDateFormet method in CadreRegistrationAction Action",e);
		}
		return date;
		
	}

	public String searchVoterAndCadreInfoBySearchCriteria()
	{
		LOG.info("Entered into searchVoterAndCadreInfoBySearchCriteria method in CadreRegistrationAction Action");
	
		try {
			
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
			{
				voterInfoVOList = null;			
				return ERROR;
			}
			
			jobj = new JSONObject(getTask());
			
			Long constituencyId = jobj.getLong("constituencyId");
			String searchType = jobj.getString("searchType");
			String candidateName = jobj.getString("candidateName");
			String houseNo = jobj.getString("houseNo");
			String voterCardNo = jobj.getString("voterCardNo");
			
			voterInfoVOList = cadreRegistrationService.getSearchDetailsCadreRegistration(constituencyId,searchType,candidateName,voterCardNo,houseNo);
			
		} catch (Exception e) {
			LOG.error("Exception raised in searchVoterAndCadreInfoBySearchCriteria method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}

	public String tdpCadreSearchPage()
	{
		LOG.info("Entered into tdpCadreSearchPage method in CadreRegistrationAction Action");
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
				return Action.INPUT;
			
			selectOptionVOList = 	surveyDataDetailsService.getAllAssemblyConstituenciesByStateId();
			
		} catch (Exception e) {
			LOG.error("Exception raised in tdpCadreSearchPage method in CadreRegistrationAction Action",e);
		}
	
		return Action.SUCCESS;
	}
	
	
	public String tdpCadreSearchDetailsPage()
	{

		LOG.info("Entered into tdpCadreSearchDetailsPage method in CadreRegistrationAction Action");
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
				return Action.INPUT;
			
		} catch (Exception e) {
			LOG.error("Exception raised in tdpCadreSearchDetailsPage method in CadreRegistrationAction Action",e);
		}
	
		return Action.SUCCESS;
	}
	
	
	public String tdpCadreRegistrationPage()
	{

		LOG.info("Entered into tdpCadreRegistrationPage method in CadreRegistrationAction Action");
		try {
			
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return ERROR;
			
			genericVOList = candidateUpdationDetailsService.gettingEducationDetails();
			selectOptionVOList = staticDataService.getAllOccupations();
			voterInfoVOList = cadreRegistrationService.getCandidateInfoBySearchCriteria(searchType,Long.valueOf(candidateId));
			
			
		} catch (Exception e) {
			LOG.error("Exception raised in tdpCadreRegistrationPage method in CadreRegistrationAction Action",e);
		}
	
		return Action.SUCCESS;
	}
	
	
}
