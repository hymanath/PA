package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.GrievanceAmountVO;
import com.itgrids.partyanalyst.dto.RegisteredMembershipCountVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.TdpCadreFamilyDetailsVO;
import com.itgrids.partyanalyst.dto.VerifierVO;
import com.itgrids.partyanalyst.dto.WebServiceResultVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreDetailsAction extends ActionSupport implements ServletRequestAware{
	
	private static final Logger LOG = Logger.getLogger(CadreDetailsAction.class);
	private HttpServletRequest 					request;
	private HttpSession 						session;
	private String 								task;
	private JSONObject							jObj;
	private ICadreDetailsService  				cadreDetailsService;
	private CadreCommitteeMemberVO              cadreCommitteeMemberVO;   
	private List<CadreCommitteeMemberVO>      	cadreCommitteeMemberVOList;
	private Long 								cadreId;
	private VerifierVO							verifierVO;
	private EntitlementsHelper 					entitlementsHelper;
	
	private List<CandidateDetailsVO>			candidateDetailsVOs;
	private String 								memberShipId;
	private RegisteredMembershipCountVO         membershipCountVO;
	private List<RegisteredMembershipCountVO> membershipCountVOList;
	private List<GrievanceAmountVO>           grievanceAmountVOList;
	private List<TdpCadreFamilyDetailsVO>     familyDetails;
	private WebServiceResultVO				  webServiceResultVO;
	

	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
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
	
	public ICadreDetailsService getCadreDetailsService() {
		return cadreDetailsService;
	}

	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}

	public CadreCommitteeMemberVO getCadreCommitteeMemberVO() {
		return cadreCommitteeMemberVO;
	}

	public void setCadreCommitteeMemberVO(
			CadreCommitteeMemberVO cadreCommitteeMemberVO) {
		this.cadreCommitteeMemberVO = cadreCommitteeMemberVO;
	}
	public List<CadreCommitteeMemberVO> getCadreCommitteeMemberVOList() {
		return cadreCommitteeMemberVOList;
	}


	public void setCadreCommitteeMemberVOList(
			List<CadreCommitteeMemberVO> cadreCommitteeMemberVOList) {
		this.cadreCommitteeMemberVOList = cadreCommitteeMemberVOList;
	}

	public Long getCadreId() {
		return cadreId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	public VerifierVO getVerifierVO() {
		return verifierVO;
	}
	public void setVerifierVO(VerifierVO verifierVO) {
		this.verifierVO = verifierVO;
	}
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public List<CandidateDetailsVO> getCandidateDetailsVOs() {
		return candidateDetailsVOs;
	}


	public void setCandidateDetailsVOs(List<CandidateDetailsVO> candidateDetailsVOs) {
		this.candidateDetailsVOs = candidateDetailsVOs;
	}
	
	public String getMemberShipId() {
		return memberShipId;
	}


	public void setMemberShipId(String memberShipId) {
		this.memberShipId = memberShipId;
	}


	public RegisteredMembershipCountVO getMembershipCountVO() {
		return membershipCountVO;
	}


	public void setMembershipCountVO(RegisteredMembershipCountVO membershipCountVO) {
		this.membershipCountVO = membershipCountVO;
	}


	public List<RegisteredMembershipCountVO> getMembershipCountVOList() {
		return membershipCountVOList;
	}


	public void setMembershipCountVOList(
			List<RegisteredMembershipCountVO> membershipCountVOList) {
		this.membershipCountVOList = membershipCountVOList;
	}


	public List<GrievanceAmountVO> getGrievanceAmountVOList() {
		return grievanceAmountVOList;
	}


	public void setGrievanceAmountVOList(
			List<GrievanceAmountVO> grievanceAmountVOList) {
		this.grievanceAmountVOList = grievanceAmountVOList;
	}

	public List<TdpCadreFamilyDetailsVO> getFamilyDetails() {
		return familyDetails;
	}


	public void setFamilyDetails(List<TdpCadreFamilyDetailsVO> familyDetails) {
		this.familyDetails = familyDetails;
	}
	
	public WebServiceResultVO getWebServiceResultVO() {
		return webServiceResultVO;
	}


	public void setWebServiceResultVO(WebServiceResultVO webServiceResultVO) {
		this.webServiceResultVO = webServiceResultVO;
	}


	public String execute(){
		
		try{
			session = request.getSession();
			if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"TDP_CADRE_DETAILS")){
				return "tdpCadreDetails";
			}
			
		}catch(Exception e){
			LOG.error("Exception raised in execute  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
	
	public String cadreFormalDetailedInformation(){
		
		try {
			jObj=new JSONObject(getTask());
			
			cadreCommitteeMemberVO=cadreDetailsService.cadreFormalDetailedInformation(jObj.getLong("cadreId"));
			
		} catch (Exception e) {
			LOG.error("Exception raised in cadreFormalDetailedInformation  method in CadreDetailsAction.",e);
		}
		
		return Action.SUCCESS;
	}
	public String complaintDetailsOfCadre(){
		
		try {
			jObj=new JSONObject(getTask());
			
			cadreCommitteeMemberVO=cadreDetailsService.complaintDetailsOfCadre(jObj.getLong("cadreId"),jObj.getString("membershipId"));
			
		} catch (Exception e) {
			LOG.error("Exception raised in cadreFormalDetailedInformation  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
	public String getEventDetailsOfCadre(){
		try{
			jObj=new JSONObject(getTask());
			
			cadreCommitteeMemberVOList=cadreDetailsService.getEventDetailsOfCadre(jObj.getLong("cadreId"));
		}catch(Exception e){
			LOG.error("Exception raised in getEventDetailsOfCadre  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
	public String getTdpCadreSurveyDetails(){
		
		try{
			jObj=new JSONObject(getTask());
			
			Long cadreId=jObj.getLong("cadreId");
			Long surveyId=jObj.getLong("surveyId");
			
			verifierVO=cadreDetailsService.getTdpCadreSurveyDetails(cadreId,surveyId);
		}catch(Exception e){
			LOG.error("Exception raised in getEventDetailsOfCadre  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
	public String getCandidateElectDetatails(){
		
		try{
			jObj=new JSONObject(getTask());
			
			candidateDetailsVOs=cadreDetailsService.getCandidateElectDetatails(jObj.getLong("cadreId"));
			//candidateDetailsService.getCandidateElectionDetails();
			
		}catch (Exception e) {
			LOG.error("Exception raised in getCandidateElectDetatails  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getTotalMemberShipRegistrationsInCadreLocation()
	{
		try{
			
			membershipCountVO = cadreDetailsService.getTotalMemberShipRegistrationsInCadreLocation(new Long(request.getParameter("tdpCadreId")));
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getTotalMemberShipRegistrationsInCadreLocation() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getElectionPerformanceInCadreLocation()
	{
		try{
			
			membershipCountVOList = cadreDetailsService.getElectionPerformanceInCadreLocation(new Long(request.getParameter("tdpCadreId")));
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getElectionPerformanceInCadreLocation() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getApprovedFinancialSupprotForCadre()
	{
		try{
			grievanceAmountVOList = cadreDetailsService.getApprovedFinancialSupprotForCadre(new Long(request.getParameter("tdpCadreId")));
		}catch (Exception e) {
			LOG.error("Exception Occured in getApprovedFinancialSupprotForCadre() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCadreFamilyDetails()
	{
		try{
			familyDetails = cadreDetailsService.getCadreFamilyDetails(new Long(request.getParameter("tdpCadreId")));	
		}catch (Exception e) {
			LOG.error("Exception Occured in getCadreFamilyDetails() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCandidateAndLocationSummaryNews(){
		
		try{
			jObj=new JSONObject(getTask());
			
			Long candidateId=jObj.getLong("candidateId");
			String locationType=jObj.getString("locationType");
			Long locationId=jObj.getLong("locationId");
			String startDate=jObj.getString("startDate");
			String endDate=jObj.getString("endDate");

			webServiceResultVO=cadreDetailsService.getCandidateAndLocationSummaryNews(startDate,endDate,locationType,locationId,candidateId);	
		}catch (Exception e) {
			LOG.error("Exception Occured in getCandidateAndLocationSummaryNews() method, Exception - ",e);
		}
		return Action.SUCCESS;
		
	}
	
}
