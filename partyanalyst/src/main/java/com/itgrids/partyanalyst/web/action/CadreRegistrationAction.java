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

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadrePrintVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;
import com.itgrids.partyanalyst.dto.VoterInfoVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICandidateUpdationDetailsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.ISurveyDataDetailsService;
import com.itgrids.partyanalyst.util.IWebConstants;
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
	private SurveyCadreResponceVO				surveyCadreResponceVO;
	
	private List<VoterInfoVO> 					voterInfoVOList;
	private List<GenericVO> 					genericVOList;
	private List<SelectOptionVO> 				selectOptionVOList,constituencyesList,eletionTypesList;
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
	
	private EntitlementsHelper 					entitlementsHelper;
	private CadrePrintVO						cadrePrintVO;
	private List<CadrePrintVO>					basicVOList;
	private List<BasicVO>						constituencyList;
	private String								panchayatId;
	private String								boothId;
	private Boolean                             relativeTypeChecked;
	private Long                                relativeTypeId;
	
	public void setConstituencyList(List<BasicVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	
	public List<BasicVO> getConstituencyList() {
		return constituencyList;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

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

	
	public SurveyCadreResponceVO getSurveyCadreResponceVO() {
		return surveyCadreResponceVO;
	}

	public void setSurveyCadreResponceVO(SurveyCadreResponceVO surveyCadreResponceVO) {
		this.surveyCadreResponceVO = surveyCadreResponceVO;
	}

	
	public List<SelectOptionVO> getConstituencyesList() {
		return constituencyesList;
	}

	public void setConstituencyesList(List<SelectOptionVO> constituencyesList) {
		this.constituencyesList = constituencyesList;
	}

	
	public List<SelectOptionVO> getEletionTypesList() {
		return eletionTypesList;
	}

	public void setEletionTypesList(List<SelectOptionVO> eletionTypesList) {
		this.eletionTypesList = eletionTypesList;
	}

	
	public CadrePrintVO getCadrePrintVO() {
		return cadrePrintVO;
	}

	public void setCadrePrintVO(CadrePrintVO cadrePrintVO) {
		this.cadrePrintVO = cadrePrintVO;
	}

	
	public List<CadrePrintVO> getBasicVOList() {
		return basicVOList;
	}

	public void setBasicVOList(List<CadrePrintVO> basicVOList) {
		this.basicVOList = basicVOList;
	}

	
	public String getPanchayatId() {
		return panchayatId;
	}

	public void setPanchayatId(String panchayatId) {
		this.panchayatId = panchayatId;
	}

	public String getBoothId() {
		return boothId;
	}

	public void setBoothId(String boothId) {
		this.boothId = boothId;
	}
	
	public Boolean getRelativeTypeChecked() {
		return relativeTypeChecked;
	}

	public void setRelativeTypeChecked(Boolean relativeTypeChecked) {
		this.relativeTypeChecked = relativeTypeChecked;
	}

	public Long getRelativeTypeId() {
		return relativeTypeId;
	}

	public void setRelativeTypeId(Long relativeTypeId) {
		this.relativeTypeId = relativeTypeId;
	}

	public String execute()
	{
		try {
			LOG.info("Entered into execute method in CadreRegistrationAction Action");
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			
			if(entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATION_2014")){
				return Action.SUCCESS;
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in execute method in CadreRegistrationAction Action",e);
		}
		return Action.ERROR;
	}
	
	
	
	public String saveCadreDetails()
	{
		try {
			LOG.info("Entered into saveCadreDetails method in CadreRegistrationAction Action");
			if(cadreRegistrationVO != null)
			{
				session = request.getSession();
				RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
				if(user == null)
				{
					inputStream = new StringBufferInputStream("notlogged");
					return Action.SUCCESS;
				}
				cadreRegistrationVO.setCreatedUserId(user.getRegistrationID());
				if(cadreRegistrationVO.getDobStr() != null && cadreRegistrationVO.getDobStr().trim().length() > 0)
				cadreRegistrationVO.setDob(convertToDateFormet(cadreRegistrationVO.getDobStr()));
				if(cadreRegistrationVO.getPartyMemberSinceStr() != null && cadreRegistrationVO.getPartyMemberSinceStr().trim().length() > 0)
				cadreRegistrationVO.setPartyMemberSince(convertToDateFormet(cadreRegistrationVO.getPartyMemberSinceStr()));
				if(relativeTypeChecked != null){
					cadreRegistrationVO.setRelative(true);
					cadreRegistrationVO.setRelationTypeId(relativeTypeId);
				}
				List<CadrePreviousRollesVO> rolesVOList = cadreRegistrationVO.getPreviousRollesList();
				if(rolesVOList != null && rolesVOList.size() > 0)
				{
					List<CadrePreviousRollesVO> rolesList = new ArrayList<CadrePreviousRollesVO>();
					for (CadrePreviousRollesVO cadrePreviousRollesVO : rolesVOList) 
					{
						CadrePreviousRollesVO rolesVO = new CadrePreviousRollesVO();
						rolesVO.setDesignationLevelId(1l);
						rolesVO.setDesignationLevelValue(1l);
						if(cadrePreviousRollesVO.getFromDateStr() != null && cadrePreviousRollesVO.getFromDateStr().trim().length() > 0)
						rolesVO.setFromDate(convertToDateFormet(cadrePreviousRollesVO.getFromDateStr()));
						if(cadrePreviousRollesVO.getToDateStr() != null && cadrePreviousRollesVO.getToDateStr().trim().length() > 0)
						rolesVO.setToDate(convertToDateFormet(cadrePreviousRollesVO.getToDateStr()));
						rolesList.add(rolesVO);
					}
					cadreRegistrationVO.setPreviousRollesList(rolesList);
				}
				List<CadreRegistrationVO> cadreRegistrationVOList = new ArrayList<CadreRegistrationVO>();
				cadreRegistrationVO.setPath(IWebConstants.STATIC_CONTENT_FOLDER_URL);
				cadreRegistrationVOList.add(cadreRegistrationVO);
				surveyCadreResponceVO = cadreRegistrationService.saveCadreRegistration(cadreRegistrationVOList,"WEB");
				if(surveyCadreResponceVO.getResultCode() == ResultCodeMapper.SUCCESS){
					LOG.debug("fileuploades is sucess Method");
					if(surveyCadreResponceVO.getEnrollmentNumber() != null && surveyCadreResponceVO.getEnrollmentNumber().trim().length() > 0 ){
						inputStream = new StringBufferInputStream("SUCCESS" +"," +surveyCadreResponceVO.getEnrollmentNumber()  +",");
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
			String isPresentCadre = jobj.getString("isPresentCadre");
			
			voterInfoVOList = cadreRegistrationService.getSearchDetailsCadreRegistration(constituencyId,searchType,candidateName,voterCardNo,houseNo,panchayatId,boothId,isPresentCadre);
			
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
			
			if(entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATION_2014"))
			{
				Long stateTypeId = 0L; // 0 for All, 1 for AP, 2 for TG 
				Long stateId = 1L;

				selectOptionVOList = 	surveyDataDetailsService.getAssemblyConstituenciesByStateId(stateTypeId,stateId);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in tdpCadreSearchPage method in CadreRegistrationAction Action",e);
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
			
			if(entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATION_2014"))
			{
				constituencyesList = 	surveyDataDetailsService.getAssemblyConstituenciesByStateId(0l,1l);  
				genericVOList = candidateUpdationDetailsService.gettingEducationDetails();
				selectOptionVOList = staticDataService.getAllOccupations();
				eletionTypesList = cadreRegistrationService.getElectionOptionDetailsForCadre();
				voterInfoVOList = cadreRegistrationService.getCandidateInfoBySearchCriteria(searchType,Long.valueOf(candidateId));
				
				return Action.SUCCESS;
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in tdpCadreRegistrationPage method in CadreRegistrationAction Action",e);
		}
	
		return Action.INPUT;
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
	
	public String getElectionYearsByElectionType()
	{
		LOG.info("Entered into getOptionDetailsForCadre method in CadreRegistrationAction Action");
		try {		
			jobj = new JSONObject(getTask());
			selectOptionVOList = cadreRegistrationService.getElectionYearsByElectionType(jobj.getLong("eletionTypeId"));
			
		} catch (Exception e) {			
			LOG.info("Entered into getOptionDetailsForCadre method in CadreRegistrationAction Action");
		}
		return Action.SUCCESS;
		
	}
	public String getExistingCadreInfo(){
		LOG.info("Entered into getExistingCadreInfo method in CadreRegistrationAction Action");
		try {		
			jobj = new JSONObject(getTask());
			
			genericVOList = cadreRegistrationService.getExistingCadreInfo(jobj.getString("name"),jobj.getLong("constituencyId"),jobj.getLong("panchayatId"),jobj.getLong("boothId"),jobj.getString("isPresentCadre"));	
		}
		catch(Exception e){
			LOG.error("Exception raised in getExistingCadreInfo method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getCadrePrintDetails()
	{
		LOG.info("Entered into getOptionDetailsForCadre method in CadreRegistrationAction Action");
		try {		
			jobj = new JSONObject(getTask());
			cadrePrintVO = cadreRegistrationService.getCadreDetailsForPrinting(jobj.getString("memberNo"));
			
		} catch (Exception e) {			
			LOG.info("Entered into getCadrePrintDetails method in CadreRegistrationAction Action");
		}
		return Action.SUCCESS;
		
	}
	
	public String getSelectedLevelCadreDetails()
	{
		LOG.info("Entered into getOptionDetailsForCadre method in CadreRegistrationAction Action");
		try {		
			jobj = new JSONObject(getTask());
			basicVOList = cadreRegistrationService.getSelectedLevelCadreDetails(jobj.getLong("panchayatId"));
			
		} catch (Exception e) {			
			LOG.info("Entered into getSelectedLevelCadreDetails method in CadreRegistrationAction Action");
		}
		return Action.SUCCESS;
		
	}
	
	public String tagCardIdForNFCReader()
	{
		LOG.info("Entered into getOptionDetailsForCadre method in CadreRegistrationAction Action");
		try {		
			jobj = new JSONObject(getTask());
			searchType = cadreRegistrationService.tagCardIdForNFCReader(jobj.getString("cardNo"),jobj.getLong("voterId"));
			
		} catch (Exception e) {			
			LOG.info("Entered into tagCardIdForNFCReader method in CadreRegistrationAction Action");
		}
		return Action.SUCCESS;
		
	}
	
	public String cadreSurveyUserDetailsPage()
	{
		LOG.info("Entered into cadreSurveyUserDetailsPage method in CadreRegistrationAction Action");
		try {
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			
			if(entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATION_2014"))
			{
				genericVOList = cadreRegistrationService.getSurveyCadreUsersList();
				constituencyesList = 	surveyDataDetailsService.getAssemblyConstituenciesByStateId(0l,1l);
				selectOptionVOList = cadreRegistrationService.getSurveyCadreAssignedConstituencyList();
			}
			
			return Action.SUCCESS;
			
		} catch (Exception e) {			
			LOG.info("Entered into cadreSurveyUserDetailsPage method in CadreRegistrationAction Action");
		}
		return Action.ERROR;
	}
	
	
	public String saveNewCadreSurveyUser()
	{
		LOG.info("Entered into saveNewCadreSurveyUser method in CadreRegistrationAction Action");
		try {		
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user != null)
			{
				jobj = new JSONObject(getTask());
				resultStatus = cadreRegistrationService.saveNewCadreSurveyUser(user.getRegistrationID(),jobj.getString("surveyUserName"),jobj.getString("password"),jobj.getString("mobileNo"));
			}					
			else
			{
				resultStatus = new ResultStatus();
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setMessage("Please Longin to update details.");
			}
		}
		catch(Exception e){
			LOG.error("Exception raised in saveNewCadreSurveyUser method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
	}
	
	public String getSurveyCadreUsersList()
	{
		LOG.info("Entered into getSurveyCadreUsersList method in CadreRegistrationAction Action");
		try {		
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user != null)
			{
				genericVOList = cadreRegistrationService.getSurveyCadreUsersList();
			}					
			else
			{
				genericVOList = null;
			}
		}
		catch(Exception e){
			LOG.error("Exception raised in getSurveyCadreUsersList method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
	}
	
	public String getSurveyCadreAssignedConstituencyList()
	{
		LOG.info("Entered into getSurveyCadreAssignedConstituencyList method in CadreRegistrationAction Action");
		try {		
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user != null)
			{
				selectOptionVOList = cadreRegistrationService.getSurveyCadreAssignedConstituencyList();
			}					
			else
			{
				genericVOList = null;
			}
		}
		catch(Exception e){
			LOG.error("Exception raised in getSurveyCadreAssignedConstituencyList method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
	}
	
	public String getSurveyCadreAssignedUsersList()
	{
		LOG.info("Entered into getSurveyCadreAssignedConstituencyList method in CadreRegistrationAction Action");
		try {		
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user != null)
			{
				jobj = new JSONObject(getTask());
				genericVOList = cadreRegistrationService.getSurveyCadreAssignedUsersList(jobj.getLong("constituencyId"));
			}					
			else
			{
				genericVOList = null;
			}
		}
		catch(Exception e){
			LOG.error("Exception raised in getSurveyCadreAssignedConstituencyList method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
	}
	
	public String releaseCadreSurveyUser()
	{
		LOG.info("Entered into saveNewCadreSurveyUser method in CadreRegistrationAction Action");
		try {		
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user != null)
			{
				jobj = new JSONObject(getTask());
				resultStatus = cadreRegistrationService.releaseCadreSurveyUser(jobj.getLong("cadreSurveyUserAssignedId"));
			}					
			else
			{
				resultStatus = new ResultStatus();
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setMessage("Please Longin to update details.");
			}
		}
		catch(Exception e){
			LOG.error("Exception raised in saveNewCadreSurveyUser method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
	}
	
	public String assignUserForLocation()
	{
		LOG.info("Entered into assignUserForLocation method in CadreRegistrationAction Action");
		try {		
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user != null)
			{
				jobj = new JSONObject(getTask());
				resultStatus = cadreRegistrationService.assignUserForLocation(jobj.getLong("cadreSurveyUserId"),jobj.getLong("levelId"),jobj.getLong("levelValue"),jobj.getLong("constituencyId"),jobj.getString("tabNo"));
			}					
			else
			{
				resultStatus = new ResultStatus();
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				resultStatus.setMessage("Please Longin to update details.");
			}
		}
		catch(Exception e){
			LOG.error("Exception raised in assignUserForLocation method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
	}
	
	public String getSubRegionsInConstituency()
	{
		LOG.info("Entered into assignUserForLocation method in CadreRegistrationAction Action");
		try {		
			jobj = new JSONObject(getTask());
			selectOptionVOList = cadreRegistrationService.getSubRegionsInConstituency(jobj.getLong("constituencyId"),jobj.getString("scope"));
		}
		catch(Exception e){
			LOG.error("Exception raised in assignUserForLocation method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
	}
	
	
	public String isTabAssignedAlready()
	{
		LOG.info("Entered into assignUserForLocation method in CadreRegistrationAction Action");
		try {		
			jobj = new JSONObject(getTask());
			task = cadreRegistrationService.isTabAssignedAlready(jobj.getString("tabNo"));
		}
		catch(Exception e){
			LOG.error("Exception raised in assignUserForLocation method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
	}
	
	public String getAllRelationDetails(){
		
		try {		
			selectOptionVOList = cadreRegistrationService.getAllRelationDetails();
		}
		catch(Exception e){
			LOG.error("Exception raised in getAllRelationDetails method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
	}
	
	public String getConstiteuncyListForElection()
	{
		LOG.info("Entered into getConstiteuncyListForElection method in CadreRegistrationAction Action");
		try {		
			jobj = new JSONObject(getTask());
			constituencyList = cadreRegistrationService.constituencyListForElection(jobj.getLong("electionId"));
		}
		catch(Exception e){
			LOG.error("Exception raised in getConstiteuncyListForElection method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
	}
	
	public String getCandidateDetailsForElection()
	{
		LOG.info("Entered into getCandidateDetailsForElection method in CadreRegistrationAction Action");
		try {		
			jobj = new JSONObject(getTask());
			constituencyList = cadreRegistrationService.participatedCandList(jobj.getLong("electionId"),jobj.getLong("constituencyId"));
		}
		catch(Exception e){
			LOG.error("Exception raised in getCandidateDetailsForElection method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
	}
	
	public String getCadreLevelsForCadreSearch(){
		LOG.info("Entered into getOptionDetailsForCadre method in CadreRegistrationAction Action");
		try {			
			selectOptionVOList = cadreRegistrationService.getCadreLevelsForCadreSearch();
			
		} catch (Exception e) {			
			LOG.info("Entered into getOptionDetailsForCadre method in CadreRegistrationAction Action");
		}
		return Action.SUCCESS;
		
	}
	
	public String getCandidateInfoByNomination()
	{
		LOG.info("Entered into getCandidateInfoByNomination method in CadreRegistrationAction Action");
		try {		
			jobj = new JSONObject(getTask());
			selectOptionVOList = cadreRegistrationService.getCandidateInfoByNomination(jobj.getLong("electionId"),jobj.getLong("nominationId"));
		}
		catch(Exception e){
			LOG.error("Exception raised in getCandidateInfoByNomination method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
		
	}
}
