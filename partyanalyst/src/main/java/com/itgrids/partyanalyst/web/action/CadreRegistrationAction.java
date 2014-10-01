package com.itgrids.partyanalyst.web.action;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
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
	private InputStream 						inputStream;
	
	

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


	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
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
			if(cadreRegistrationVO != null)
			{
				session = request.getSession();
				RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
				cadreRegistrationVO.setCreatedUserId(user.getRegistrationID());
				cadreRegistrationVO.setDob(convertToDateFormet(cadreRegistrationVO.getDobStr()));
				cadreRegistrationVO.setPartyMemberSince(convertToDateFormet(cadreRegistrationVO.getPartyMemberSinceStr()));
				
				List<CadrePreviousRollesVO> rolesVOList = cadreRegistrationVO.getPreviousRollesList();
				if(rolesVOList != null && rolesVOList.size() > 0)
				{
					List<CadrePreviousRollesVO> rolesList = new ArrayList<CadrePreviousRollesVO>();
					for (CadrePreviousRollesVO cadrePreviousRollesVO : rolesVOList) 
					{
						CadrePreviousRollesVO rolesVO = new CadrePreviousRollesVO();
						rolesVO.setDesignationLevelId(1l);
						rolesVO.setDesignationLevelValue(1l);
						rolesVO.setFromDate(convertToDateFormet(cadrePreviousRollesVO.getFromDateStr()));
						rolesVO.setToDate(convertToDateFormet(cadrePreviousRollesVO.getToDateStr()));
						rolesList.add(rolesVO);
					}
					cadreRegistrationVO.setPreviousRollesList(rolesList);
				}
				resultStatus = cadreRegistrationService.saveCadreRegistration(cadreRegistrationVO,"WEB");
				if(resultStatus.getResultCode() == ResultCodeMapper.SUCCESS){
					LOG.debug("fileuploades is sucess Method");
					if(resultStatus.getHost() != null && Long.valueOf(resultStatus.getHost()) > 0 ){
						inputStream = new StringBufferInputStream("SUCCESS" +"," +resultStatus.getHost() +",");
					}
				}
				else
					inputStream = new StringBufferInputStream("fail");
			}
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
			/*	
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
			{
				voterInfoVOList = null;			
				return ERROR;
			}*/
			
			jobj = new JSONObject(getTask());
			
			Long constituencyId = jobj.getLong("constituencyId");
			String searchType = jobj.getString("searchType");
			String candidateName = jobj.getString("candidateName");
			String houseNo = jobj.getString("houseNo");
			String voterCardNo = jobj.getString("voterCardNo");
			Long panchayatId = jobj.getLong("panchayatId");
			Long boothId = jobj.getLong("boothId");
			String vilagesCovered = jobj.getString("locationId");
			
			voterInfoVOList = cadreRegistrationService.getSearchDetailsCadreRegistration(constituencyId,searchType,candidateName,voterCardNo,houseNo,panchayatId,boothId,vilagesCovered);
			
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
			/*if(user == null)
				return Action.INPUT;*/
			Long stateTypeId = 0L; // 0 for All, 1 for AP, 2 for TG 
			Long stateId = 1L;

			selectOptionVOList = 	surveyDataDetailsService.getAssemblyConstituenciesByStateId(stateTypeId,stateId);
			
		} catch (Exception e) {
			LOG.error("Exception raised in tdpCadreSearchPage method in CadreRegistrationAction Action",e);
		}
	
		return Action.SUCCESS;
	}
		
	public String tdpCadreRegistrationPage()
	{

		LOG.info("Entered into tdpCadreRegistrationPage method in CadreRegistrationAction Action");
		try {
			
		/*	session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return ERROR;
			*/
			genericVOList = candidateUpdationDetailsService.gettingEducationDetails();
			selectOptionVOList = staticDataService.getAllOccupations();
			voterInfoVOList = cadreRegistrationService.getCandidateInfoBySearchCriteria(searchType,Long.valueOf(candidateId));
			
			
		} catch (Exception e) {
			LOG.error("Exception raised in tdpCadreRegistrationPage method in CadreRegistrationAction Action",e);
		}
	
		return Action.SUCCESS;
	}
	
	public String getconstituencyDetailsByConstiteuncy()
	{
		LOG.info("Entered into getconstituencyDetailsByConstiteuncy method in CadreRegistrationAction Action");
		try {
			jobj = new JSONObject(getTask());
			
			Long constituencyId = jobj.getLong("constituencyId");
			
			genericVOList = cadreRegistrationService.getConstiteuncyDetailsByConstiteuncy(constituencyId);
			
		} catch (Exception e) {
			LOG.info("Entered into getconstituencyDetailsByConstiteuncy method in CadreRegistrationAction Action");
		}
		return Action.SUCCESS;
	}
	
	public String getBoothsDetailsByLocation()
	{
		LOG.info("Entered into getconstituencyDetailsByConstiteuncy method in CadreRegistrationAction Action");
		try {
			jobj = new JSONObject(getTask());
			
			Long constituencyId = jobj.getLong("constituencyId");
			Long locationId = jobj.getLong("locationId");
			
			genericVOList = cadreRegistrationService.getBoothForPanchayats(constituencyId,locationId);
			
		} catch (Exception e) {			
			LOG.info("Entered into getconstituencyDetailsByConstiteuncy method in CadreRegistrationAction Action");
		}
		return Action.SUCCESS;
	}
	
	public String getBoothCoverdVillagesDetails()
	{
		LOG.info("Entered into getconstituencyDetailsByConstiteuncy method in CadreRegistrationAction Action");
		try {
			jobj = new JSONObject(getTask());
			
			JSONArray boothsArr = jobj.getJSONArray("boothsArr");
			List<Long> boothIds = new ArrayList<Long>();
			if(boothsArr.length()>0)
			{
				for(int i  = 0 ;i<boothsArr.length();i++)
				{
					boothIds.add(Long.valueOf(boothsArr.get(i).toString()));
				}
			}
			genericVOList = cadreRegistrationService.getBoothCoverdVillagesDetails(boothIds);
			
		} catch (Exception e) {			
			LOG.info("Entered into getconstituencyDetailsByConstiteuncy method in CadreRegistrationAction Action");
		}
		return Action.SUCCESS;
	}
	
	public String getOptionDetailsForCadre()
	{
		LOG.info("Entered into getOptionDetailsForCadre method in CadreRegistrationAction Action");
		try {			
			selectOptionVOList = cadreRegistrationService.getOptionDetailsForCadre();
			
		} catch (Exception e) {			
			LOG.info("Entered into getOptionDetailsForCadre method in CadreRegistrationAction Action");
		}
		return Action.SUCCESS;
		
	}
	
	public String getElectionOptionDetailsForCadre()
	{
		LOG.info("Entered into getOptionDetailsForCadre method in CadreRegistrationAction Action");
		try {			
			selectOptionVOList = cadreRegistrationService.getElectionOptionDetailsForCadre();
			
		} catch (Exception e) {			
			LOG.info("Entered into getOptionDetailsForCadre method in CadreRegistrationAction Action");
		}
		return Action.SUCCESS;
		
	}
	
}
