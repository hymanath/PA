package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CategoryFeedbackVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.ComplaintStatusCountVO;
import com.itgrids.partyanalyst.dto.GrievanceAmountVO;
import com.itgrids.partyanalyst.dto.QuestionAnswerVO;
import com.itgrids.partyanalyst.dto.NtrTrustStudentVO;
import com.itgrids.partyanalyst.dto.RegisteredMembershipCountVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SimpleVO;
import com.itgrids.partyanalyst.dto.TdpCadreFamilyDetailsVO;
import com.itgrids.partyanalyst.dto.VerifierVO;
import com.itgrids.partyanalyst.dto.WebServiceResultVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.ITrainingCampService;
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
	private ITrainingCampService				trainingCampService;
	
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
	private ComplaintStatusCountVO           complaintStatusCountVO;
	private List<CommitteeBasicVO> 			committeeBasicVOList;
	private Long						    constituencyId;
	private List<BasicVO>					basicVoList;
	private BasicVO							basicVo;
	private List<NtrTrustStudentVO>        ntrTrustStudentVOList; 
	private List<QuestionAnswerVO>         finalList;
	private List<SimpleVO> 				   simpleVoList;
	private SimpleVO 					   simpleVO;
	private NtrTrustStudentVO				ntrTrustStudentVo;
	private List<CategoryFeedbackVO>		categoryFeedbackVoList;
	
	
	
	
	public List<CategoryFeedbackVO> getCategoryFeedbackVoList() {
		return categoryFeedbackVoList;
	}


	public void setCategoryFeedbackVoList(
			List<CategoryFeedbackVO> categoryFeedbackVoList) {
		this.categoryFeedbackVoList = categoryFeedbackVoList;
	}


	public NtrTrustStudentVO getNtrTrustStudentVo() {
		return ntrTrustStudentVo;
	}


	public void setNtrTrustStudentVo(NtrTrustStudentVO ntrTrustStudentVo) {
		this.ntrTrustStudentVo = ntrTrustStudentVo;
	}


	public SimpleVO getSimpleVO() {
		return simpleVO;
	}


	public void setSimpleVO(SimpleVO simpleVO) {
		this.simpleVO = simpleVO;
	}


	public ITrainingCampService getTrainingCampService() {
		return trainingCampService;
	}


	public void setTrainingCampService(ITrainingCampService trainingCampService) {
		this.trainingCampService = trainingCampService;
	}


	public List<SimpleVO> getSimpleVoList() {
		return simpleVoList;
	}


	public void setSimpleVoList(List<SimpleVO> simpleVoList) {
		this.simpleVoList = simpleVoList;
	}


	public List<QuestionAnswerVO> getFinalList() {
		return finalList;
	}


	public void setFinalList(List<QuestionAnswerVO> finalList) {
		this.finalList = finalList;
	}


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

	public ComplaintStatusCountVO getComplaintStatusCountVO() {
		return complaintStatusCountVO;
	}


	public void setComplaintStatusCountVO(
			ComplaintStatusCountVO complaintStatusCountVO) {
		this.complaintStatusCountVO = complaintStatusCountVO;
	}


	public void setWebServiceResultVO(WebServiceResultVO webServiceResultVO) {
		this.webServiceResultVO = webServiceResultVO;
	}


	public List<CommitteeBasicVO> getCommitteeBasicVOList() {
		return committeeBasicVOList;
	}


	public void setCommitteeBasicVOList(List<CommitteeBasicVO> committeeBasicVOList) {
		this.committeeBasicVOList = committeeBasicVOList;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}


	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public List<BasicVO> getBasicVoList() {
		return basicVoList;
	}


	public void setBasicVoList(List<BasicVO> basicVoList) {
		this.basicVoList = basicVoList;
	}

	public BasicVO getBasicVo() {
		return basicVo;
	}


	public void setBasicVo(BasicVO basicVo) {
		this.basicVo = basicVo;
	}
	
	public List<NtrTrustStudentVO> getNtrTrustStudentVOList() {
		return ntrTrustStudentVOList;
	}

	public void setNtrTrustStudentVOList(
			List<NtrTrustStudentVO> ntrTrustStudentVOList) {
		this.ntrTrustStudentVOList = ntrTrustStudentVOList;
	}


	public String execute(){
		
		try{
			session = request.getSession();
			if(memberShipId != null && memberShipId.trim().length() == 8 && (constituencyId == null || constituencyId.longValue() == 0))
			{
				cadreId = cadreDetailsService.getTdpCadreIdBymembershipId(memberShipId.trim());
			}
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
			String searchTypeStr=jObj.getString("searchTypeStr");
			String isPriority=jObj.getString("isPriority");
			Long boothId=jObj.getLong("boothId");
			String voterCardNo=jObj.getString("voterCardNo");
			Long constitencyId = jObj.getLong("constituencyId");
			String constiTypeStr = jObj.getString("constiTypeStr");
			verifierVO=cadreDetailsService.getTdpCadreSurveyDetails(cadreId,surveyId,searchTypeStr,boothId,isPriority,voterCardNo,constitencyId,constiTypeStr);
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
			Long pcId =null;
			String pcType = null;
			String pcidString=request.getParameter("pcId");
			if(pcidString.trim().length()>0){
				pcId = Long.parseLong(request.getParameter("pcId"));
				pcType = request.getParameter("pcType");
			}
			
			membershipCountVO = cadreDetailsService.getTotalMemberShipRegistrationsInCadreLocation(new Long(request.getParameter("tdpCadreId")),pcId,pcType);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getTotalMemberShipRegistrationsInCadreLocation() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getElectionPerformanceInCadreLocation()
	{
		try{
			
			membershipCountVOList = cadreDetailsService.getElectionPerformanceInCadreLocation(new Long(request.getParameter("tdpCadreId")),"");
			
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
	
	public String getCadreIdByMembershipId()
	{
		try{
			cadreId =  cadreDetailsService.getCadreIdByMembershipId(request.getParameter("membershipId"),new Long(request.getParameter("constituencyId")));
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getCadreIdByMembershipId() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getCategoryWiseStatusCount()
	{
		try{
			complaintStatusCountVO =  cadreDetailsService.getCategoryWiseStatusCount(new Long(request.getParameter("tdpCadreId")));
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getCategoryWiseStatusCount() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getLocationwiseCommitteesCount()
	{
		try{
			
			Long locationId=Long.parseLong(request.getParameter("locationId"));
			String electionType=request.getParameter("electionType");
			committeeBasicVOList =  cadreDetailsService.getLocationwiseCommitteesCount(request.getParameter("locationType"),new Long(request.getParameter("tdpCadreId")),electionType,locationId);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getCategoryWiseStatusCount() method, Exception - ",e);
		}
		
		return Action.SUCCESS;
	}
	public String getDeathsAndHospitalizationDetails(){
		try{
			jObj=new JSONObject(getTask());
			
			Long panchayatId=jObj.getLong("panchayatId");
			Long mandalId=jObj.getLong("mandalId");
			Long constituencyid=jObj.getLong("constituencyId");
			Long districtId=jObj.getLong("districtId");
			Long parlimentId=jObj.getLong("parliamentId");;
			
			verifierVO=cadreDetailsService.getDeathsAndHospitalizationDetails(panchayatId,mandalId,constituencyid,parlimentId,districtId);
		}catch (Exception e) {
			LOG.error("Exception Occured in getDeathsAndHospitalizationDetails() method, Exception - ",e);
		}
		
		return Action.SUCCESS;
	}
	public String getCadresDetailsOfDeathsAndHospitalization(){
		try{
			jObj=new JSONObject(getTask());
			
			Long locationId=jObj.getLong("locationId");
			String locationType=jObj.getString("locationType");
			Long insuranceTypeId=jObj.getLong("insuranceTypeId");
			
			cadreCommitteeMemberVO=cadreDetailsService.getCadresDetailsOfDeathsAndHospitalization(locationId,locationType,insuranceTypeId);
		}catch (Exception e) {
			LOG.error("Exception Occured in getCadresDetailsOfDeathsAndHospitalization() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	public String getParticipatedConstituency(){
		
		try{
			jObj=new JSONObject(getTask());
			Long tdpCadreId=jObj.getLong("tdpCadreId");
			
			basicVo=cadreDetailsService.getParticipatedConstituency(tdpCadreId);
		}catch (Exception e) {
			LOG.error("Exception Occured in getCadresDetailsOfDeathsAndHospitalization() method, Exception - ",e);
		}
		return Action.SUCCESS;
		
	}
	public String getNtrTrustStudentDetailsInstitutionWise(){
		
		try{
			jObj=new JSONObject(getTask());
			//Long tdpCadreId=jObj.getLong("tdpCadreId");
			
		org.json.JSONArray cadreArr = 	jObj.getJSONArray("cadreIdsArr");
		
		List<Long> cadreIds = new ArrayList<Long>();
		if(cadreArr !=null && cadreArr.length()>0){
			
			for (int i = 0; i < cadreArr.length(); i++) {
				cadreIds.add(Long.parseLong(cadreArr.get(i).toString()));
			}
			
		}
		
		//familyCadreIds.add(tdpCadreId);
		
		ntrTrustStudentVo=cadreDetailsService.getNtrTrustStudentDetailsInstitutionWise(cadreIds);
		}catch (Exception e) {
			LOG.error("Exception Occured in getCadresDetailsOfDeathsAndHospitalization() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	public String getStudentFormalDetailsByCadre(){
		try{
			jObj=new JSONObject(getTask());
			//Long tdpCadreId=jObj.getLong("tdpCadreId");
			Long institutionId=jObj.getLong("institutionId");
			
			org.json.JSONArray cadreArr = 	jObj.getJSONArray("cadreIds");
			
			List<Long> cadreIds = new ArrayList<Long>();
			if(cadreArr !=null && cadreArr.length()>0){
				
				for (int i = 0; i < cadreArr.length(); i++) {
					cadreIds.add(Long.parseLong(cadreArr.get(i).toString()));
				}
			}
			
			//familyCadreIds.add(tdpCadreId);
			
			ntrTrustStudentVOList=cadreDetailsService.getStudentFormalDetailsByCadre(cadreIds,institutionId);
		}catch (Exception e) {
			LOG.error("Exception Occured in getStudentFormalDetailsByCadre() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	
public String getCandidateAndConstituencySurveyResult(){
		
		try{
			jObj=new JSONObject(getTask());
			Long candidateId = jObj.getLong("candidateId");
			Long constituencyId = jObj.getLong("constituencyId");
			Long surveyId = jObj.getLong("surveyId");
			
			finalList=cadreDetailsService.getCandidateAndConstituencySurveyResult(candidateId,constituencyId,surveyId);
		}catch (Exception e) {
			LOG.error("Exception Occured in getCadresDetailsOfDeathsAndHospitalization() method, Exception - ",e);
		}
		return Action.SUCCESS;
		
	}
	public String getStatusCountOfCadreForInvitationAndAttendance(){
		
		try{
			jObj=new JSONObject(getTask());
			Long cadreId = jObj.getLong("tdpCadreId");
			
			simpleVoList=trainingCampService.getStatusCountOfCadreForInvitationAndAttendance(cadreId);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getStatusCountOfCadreForInvitationAndAttendance() method, Exception - ",e);
		}
		
		return Action.SUCCESS;
	}
	public String getAttendedTrainingCampBatchDetailsOfCadre(){
		
		try{
			jObj=new JSONObject(getTask());
			Long cadreId = jObj.getLong("tdpCadreId");
			Long programId = jObj.getLong("programId");
			
			simpleVO = trainingCampService.getAttendedTrainingCampBatchDetailsOfCadre(programId,cadreId);
			
		}catch(Exception e){
			LOG.error("Exception Occured in getAttendedTrainingCampBatchDetailsOfCadre() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	public String getRemarkSOfCadreByCallPurpose(){
		
		try{
			jObj=new JSONObject(getTask());
			Long cadreId = jObj.getLong("tdpCadreId");
			Long programId = jObj.getLong("programId");
			
			simpleVoList = trainingCampService.getRemarkSOfCadreByCallPurpose(programId,cadreId);
			
		}catch(Exception e){
			LOG.error("Exception Occured in getRemarkSOfCadreByCallPurpose() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
public String updateLeaderShip(){
		
		try{
			return "updateLeader";
		}catch(Exception e){
			LOG.error("Exception Occured in updateLeaderShip() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}

	public String getCategoryFeedBackAnswerForCadre(){
		try {
			LOG.info("Entered into getCategoryFeedBackAnswerForCadre");
			
			jObj=new JSONObject(getTask());
			
			Long cadreId = jObj.getLong("tdpCadreId");
			
			categoryFeedbackVoList =trainingCampService.getCategoryFeedBackAnswerForCadre(cadreId);
		} catch (Exception e) {
			LOG.error("Exception raised at getCategoryFeedBackAnswerForCadre", e);
		}
		
		return Action.SUCCESS;
	}

}
