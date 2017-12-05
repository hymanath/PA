package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ActivityVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.BenefitCandidateVO;
import com.itgrids.partyanalyst.dto.BenefitVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreCountsVO;
import com.itgrids.partyanalyst.dto.CadreDetailsVO;
import com.itgrids.partyanalyst.dto.CadreHealthDetailsVO;
import com.itgrids.partyanalyst.dto.CadreLocationVO;
import com.itgrids.partyanalyst.dto.CadreReportVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CategoryFeedbackVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.ComplaintStatusCountVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.GrievanceAmountVO;
import com.itgrids.partyanalyst.dto.GrievanceDetailsVO;
import com.itgrids.partyanalyst.dto.GrievanceReportVO;
import com.itgrids.partyanalyst.dto.GrievanceSimpleVO;
import com.itgrids.partyanalyst.dto.IVRResponseVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ImportantLeadersVO;
import com.itgrids.partyanalyst.dto.IvrOptionsVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.MobileDetailsVO;
import com.itgrids.partyanalyst.dto.NtrTrustStudentVO;
import com.itgrids.partyanalyst.dto.QuestionAnswerVO;
import com.itgrids.partyanalyst.dto.RegisteredMembershipCountVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ReportVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SimpleVO;
import com.itgrids.partyanalyst.dto.TdpCadreFamilyDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.dto.ToursVO;
import com.itgrids.partyanalyst.dto.VerifierVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.WebServiceResultVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IAlertService;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.ICadreRegistrationForOtherStatesService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IToursService;
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
	private IVRResponseVO ivrResponseVO;
	private List<IVRResponseVO> ivrResponseVOlist;
	private  List<TdpCadreVO>      cadreList;
	private ICadreRegistrationService cadreRegistrationService;	
	private List<IvrOptionsVO> ivrOptionsVOList;
	private List<IvrOptionsVO> ivrOptionsList;
	private List<LocationVO>  cadreDetlsLst;
	private List<IdNameVO>	  idNameVoList;
	private List<GrievanceDetailsVO> grievDetailsVOList;
	private GrievanceDetailsVO grievanceDetailsvo;
	private List<CadreDetailsVO> cadreDetailsVO;
	private ICadreRegistrationForOtherStatesService cadreRegistrationForOtherStatesService;
	private List<CadreReportVO> cadreReportVOList = new ArrayList<CadreReportVO>(0);
	private List<GrievanceReportVO> grievanceReportVOList;
	private List<ReportVO> reportVOList;
	private CadreLocationVO cadreLocationVO;
	private List<CadreReportVO> cadreReportHealthVOList = new ArrayList<CadreReportVO>(0);
	private VoterCastInfoVO voterCastInfoVO ;
	private ConstituencyManagementVO constituencyManagementVO;
	private List<SelectOptionVO> selectOptList;
	private IAlertService alertService;
	private AlertVO alertVO;
	private ICandidateDetailsService candidateDetailsService;
	private ToursVO toursVO;
	private IToursService toursService;
	private List<BenefitVO> benefitVOList;
	private BenefitVO benefitVO;
	private List<BenefitCandidateVO> benefitCandidateVOList;
	private ToursBasicVO tourDetailsVO;
	private Long enrollmentYearId ;
	private List<IdAndNameVO> idAndNameVoList;
	private List<CadreHealthDetailsVO> cadreHealthDetailList;
	
	
	public List<IdAndNameVO> getIdAndNameVoList() {
		return idAndNameVoList;
	}
	public void setIdAndNameVoList(List<IdAndNameVO> idAndNameVoList) {
		this.idAndNameVoList = idAndNameVoList;
	}
	public Long getEnrollmentYearId() {
		return enrollmentYearId;
	}
	public void setEnrollmentYearId(Long enrollmentYearId) {
		this.enrollmentYearId = enrollmentYearId;
	}
	public List<BenefitCandidateVO> getBenefitCandidateVOList() {
		return benefitCandidateVOList;
	}
	public void setBenefitCandidateVOList(
			List<BenefitCandidateVO> benefitCandidateVOList) {
		this.benefitCandidateVOList = benefitCandidateVOList;
	}
	public BenefitVO getBenefitVO() {
		return benefitVO;
	}
	public void setBenefitVO(BenefitVO benefitVO) {
		this.benefitVO = benefitVO;
	}
	public List<BenefitVO> getBenefitVOList() {
		return benefitVOList;
	}
	public void setBenefitVOList(List<BenefitVO> benefitVOList) {
		this.benefitVOList = benefitVOList;
	}
	
	public IToursService getToursService() {
		return toursService;
	}
	public void setToursService(IToursService toursService) {
		this.toursService = toursService;
	}
	public ToursVO getToursVO() {
		return toursVO;
	}
	public void setToursVO(ToursVO toursVO) {
		this.toursVO = toursVO;
	}
	public AlertVO getAlertVO() {
		return alertVO;
	}
	public void setAlertVO(AlertVO alertVO) {
		this.alertVO = alertVO;
	}
	public IAlertService getAlertService() {
		return alertService;
	}
	public void setAlertService(IAlertService alertService) {
		this.alertService = alertService;
	}
	public List<SelectOptionVO> getSelectOptList() {
		return selectOptList;
	}
	public void setSelectOptList(List<SelectOptionVO> selectOptList) {
		this.selectOptList = selectOptList;
	}
	public ConstituencyManagementVO getConstituencyManagementVO() {
		return constituencyManagementVO;
	}
	public void setConstituencyManagementVO(
			ConstituencyManagementVO constituencyManagementVO) {
		this.constituencyManagementVO = constituencyManagementVO;
	}
	public VoterCastInfoVO getVoterCastInfoVO() {
		return voterCastInfoVO;
	}
	public void setVoterCastInfoVO(VoterCastInfoVO voterCastInfoVO) {
		this.voterCastInfoVO = voterCastInfoVO;
	}
	public List<GrievanceReportVO> getGrievanceReportVOList() {
		return grievanceReportVOList;
	}
	public void setGrievanceReportVOList(
			List<GrievanceReportVO> grievanceReportVOList) {
		this.grievanceReportVOList = grievanceReportVOList;
	}
	public List<CadreHealthDetailsVO> getCadreHealthDetailList() {
		return cadreHealthDetailList;
	}
	public void setCadreHealthDetailList(
			List<CadreHealthDetailsVO> cadreHealthDetailList) {
		this.cadreHealthDetailList = cadreHealthDetailList;
	}
	private List<GrievanceDetailsVO> grievanceDetailsVoList ;
	private GrievanceDetailsVO gerGrievanceDetailsVO;
	private GrievanceSimpleVO grievanceSampleVO;
	private List<GrievanceSimpleVO> grievanceSimplevoList;
	private MobileDetailsVO mobileDetailsVO;
	private List<ActivityVO> activityVOList;
	private ResultStatus resultStatus;
	private String status;
	private IdNameVO idNameVO;
	private List<List<CadreCountsVO>> cadreCountsVOsList;
	private List<CadreCountsVO> cadreCountsVOs;
	List<MahanaduEventVO> mahanaduEventVOList ;
	
	
	public List<CadreCountsVO> getCadreCountsVOs() {
		return cadreCountsVOs;
	}
	public void setCadreCountsVOs(List<CadreCountsVO> cadreCountsVOs) {
		this.cadreCountsVOs = cadreCountsVOs;
	}
	public List<List<CadreCountsVO>> getCadreCountsVOsList() {
		return cadreCountsVOsList;
	}
	public void setCadreCountsVOsList(List<List<CadreCountsVO>> cadreCountsVOsList) {
		this.cadreCountsVOsList = cadreCountsVOsList;
	}
	public IdNameVO getIdNameVO() {
		return idNameVO;
	}
	public void setIdNameVO(IdNameVO idNameVO) {
		this.idNameVO = idNameVO;
	}
	public List<MahanaduEventVO> getMahanaduEventVOList() {
		return mahanaduEventVOList;
	}
	public void setMahanaduEventVOList(List<MahanaduEventVO> mahanaduEventVOList) {
		this.mahanaduEventVOList = mahanaduEventVOList;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public MobileDetailsVO getMobileDetailsVO() {
		return mobileDetailsVO;
	}
	public void setMobileDetailsVO(MobileDetailsVO mobileDetailsVO) {
		this.mobileDetailsVO = mobileDetailsVO;
	}
	public List<ActivityVO> getActivityVOList() {
		return activityVOList;
	}
	public void setActivityVOList(List<ActivityVO> activityVOList) {
		activityVOList = activityVOList;
	}
	public List<GrievanceSimpleVO> getGrievanceSimplevoList() {
		return grievanceSimplevoList;
	}
	public void setGrievanceSimplevoList(
			List<GrievanceSimpleVO> grievanceSimplevoList) {
		this.grievanceSimplevoList = grievanceSimplevoList;
	}
	public GrievanceSimpleVO getGrievanceSampleVO() {
		return grievanceSampleVO;
	}
	public void setGrievanceSampleVO(GrievanceSimpleVO grievanceSampleVO) {
		this.grievanceSampleVO = grievanceSampleVO;
	}
	public GrievanceDetailsVO getGerGrievanceDetailsVO() {
		return gerGrievanceDetailsVO;
	}
	public void setGerGrievanceDetailsVO(GrievanceDetailsVO gerGrievanceDetailsVO) {
		this.gerGrievanceDetailsVO = gerGrievanceDetailsVO;
	}
	public List<GrievanceDetailsVO> getGrievanceDetailsVoList() {
		return grievanceDetailsVoList;
	}
	public void setGrievanceDetailsVoList(
			List<GrievanceDetailsVO> grievanceDetailsVoList) {
		this.grievanceDetailsVoList = grievanceDetailsVoList;
	}
	public GrievanceDetailsVO getGrievanceDetailsvo() {
		return grievanceDetailsvo;
	}
	public void setGrievanceDetailsvo(GrievanceDetailsVO grievanceDetailsvo) {
		this.grievanceDetailsvo = grievanceDetailsvo;
	}
	public List<GrievanceDetailsVO> getGrievDetailsVOList() {
		return grievDetailsVOList;
	}
	public void setGrievDetailsVOList(List<GrievanceDetailsVO> grievDetailsVOList) {
		this.grievDetailsVOList = grievDetailsVOList;
	}
	public List<CadreDetailsVO> getCadreDetailsVO() {
		return cadreDetailsVO;
	}
	public void setCadreDetailsVO(List<CadreDetailsVO> cadreDetailsVO) {
		this.cadreDetailsVO = cadreDetailsVO;
	}
	public List<IdNameVO> getIdNameVoList() {
		return idNameVoList;
	}
	public void setIdNameVoList(List<IdNameVO> idNameVoList) {
		this.idNameVoList = idNameVoList;
	}
	public List<IvrOptionsVO> getIvrOptionsList() {
		return ivrOptionsList;
	}
	public void setIvrOptionsList(List<IvrOptionsVO> ivrOptionsList) {
		this.ivrOptionsList = ivrOptionsList;
	}
	public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}
	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}
	public List<TdpCadreVO> getCadreList() {
		return cadreList;
	}
	public void setCadreList(List<TdpCadreVO> cadreList) {
		this.cadreList = cadreList;
	}
	public IVRResponseVO getIvrResponseVO() {
		return ivrResponseVO;
	}
	public void setIvrResponseVO(IVRResponseVO ivrResponseVO) {
		this.ivrResponseVO = ivrResponseVO;
	}
    

	public List<IVRResponseVO> getIvrResponseVOlist() {
		return ivrResponseVOlist;
	}
	public void setIvrResponseVOlist(List<IVRResponseVO> ivrResponseVOlist) {
		this.ivrResponseVOlist = ivrResponseVOlist;
	}
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

	

	public List<IvrOptionsVO> getIvrOptionsVOList() {
		return ivrOptionsVOList;
	}
	public void setIvrOptionsVOList(List<IvrOptionsVO> ivrOptionsVOList) {
		this.ivrOptionsVOList = ivrOptionsVOList;
	
	}
	
	
	
	public List<LocationVO> getCadreDetlsLst() {
		return cadreDetlsLst;
	}
	public void setCadreDetlsLst(List<LocationVO> cadreDetlsLst) {
		this.cadreDetlsLst = cadreDetlsLst;
	}
	
	
	public ICadreRegistrationForOtherStatesService getCadreRegistrationForOtherStatesService() {
		return cadreRegistrationForOtherStatesService;
	}
	public void setCadreRegistrationForOtherStatesService(
			ICadreRegistrationForOtherStatesService cadreRegistrationForOtherStatesService) {
		this.cadreRegistrationForOtherStatesService = cadreRegistrationForOtherStatesService;  
	}
	
	public List<CadreReportVO> getCadreReportVOList() {
		return cadreReportVOList;
	}
	public void setCadreReportVOList(List<CadreReportVO> cadreReportVOList) {
		this.cadreReportVOList = cadreReportVOList;
	}
	public List<ReportVO> getReportVOList() {
		return reportVOList;
	}
	public void setReportVOList(List<ReportVO> reportVOList) {
		this.reportVOList = reportVOList;
	}
	
	public CadreLocationVO getCadreLocationVO() {
		return cadreLocationVO;
	}
	public void setCadreLocationVO(CadreLocationVO cadreLocationVO) {
		this.cadreLocationVO = cadreLocationVO;
	}
	
	public List<CadreReportVO> getCadreReportHealthVOList() {
		return cadreReportHealthVOList;
	}
	public void setCadreReportHealthVOList(
			List<CadreReportVO> cadreReportHealthVOList) {
		this.cadreReportHealthVOList = cadreReportHealthVOList;
	}
	
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}
	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}
	public ToursBasicVO getTourDetailsVO() {
		return tourDetailsVO;
	}
	public void setTourDetailsVO(ToursBasicVO tourDetailsVO) {
		this.tourDetailsVO = tourDetailsVO;
	}
	public String execute(){
		
		try{
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO == null)
				return Action.INPUT;
			// kamal & sriahailam commented
			//if(memberShipId != null && memberShipId.trim().length() == 8 && (constituencyId == null || constituencyId.longValue() == 0))
			if(memberShipId != null && memberShipId.trim().length() >= 8)
			{
				cadreId = cadreDetailsService.getTdpCadreIdBymembershipId(memberShipId.trim());
			}
			
			if(cadreId != null && cadreId.longValue()>0L){
				basicVo = cadreDetailsService.getParticipatedConstituency(cadreId);
				cadreLocationVO = cadreDetailsService.getCadreBasicLocationDetails(cadreId);
				String isLeader = candidateDetailsService.checkForLeader(cadreId);
				cadreLocationVO.setIsLeader(isLeader);
				
			}
			
			List<String> entitlements = null;
			if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
				entitlements = regVO.getEntitlements();
				if(entitlements.contains("TDP_CADRE_DETAILS".trim())){
					cadreReportVOList = cadreDetailsService.getCadreReportDetails(cadreId);
					cadreReportHealthVOList = cadreDetailsService.getCadreHealthReport(cadreId);
					return "tdpCadreDetails";
				}
			
			/*if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"TDP_CADRE_DETAILS")){
				return "tdpCadreDetails";
			}*/
			}
		}catch(Exception e){
			LOG.error("Exception raised in execute  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
	
	public String cadreFormalDetailedInformation(){
		
		try {
			jObj=new JSONObject(getTask());
			
			cadreCommitteeMemberVO=cadreDetailsService.cadreFormalDetailedInformation(jObj.getLong("cadreId"),IConstants.CADRE_ENROLLMENT_NUMBER,1L);
			
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
	public String getTdpCadreIvrSurveyDetails(){
		
		try{
			jObj=new JSONObject(getTask());
			
			Long cadreId=jObj.getLong("cadreId");
			verifierVO=cadreDetailsService.getTdpCadreIvrSurveyDetails(cadreId);
		}catch(Exception e){
			LOG.error("Exception raised in getTdpCadreIvrSurveyDetails  method in CadreDetailsAction.",e);
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
			Long yearId =null;
			Long boothId =null;
			Long publicationId=null;
			String pcidString=request.getParameter("pcId");
			if(pcidString.trim().length()>0){
				pcId = Long.parseLong(request.getParameter("pcId"));
				pcType = request.getParameter("pcType");
			}
			 yearId = Long.parseLong(request.getParameter("yearId"));
			 publicationId = Long.parseLong(request.getParameter("publicationId"));
			 boothId = Long.parseLong(request.getParameter("boothId"));
			
			membershipCountVO = cadreDetailsService.getTotalMemberShipRegistrationsInCadreLocation(new Long(request.getParameter("tdpCadreId")),pcId,pcType,yearId,publicationId,boothId);
			
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
			
			List<Long> committeeEnrollmentYrIds = new ArrayList<Long>(0);
			
			jObj=new JSONObject(getTask());
			
			Long locationId=jObj.getLong("locationId");
			String electionType=jObj.getString("electionType");
			
			org.json.JSONArray arr = 	jObj.getJSONArray("committeeEnrollmentYrIds");
			
			if(arr !=null && arr.length()>0){
				
				for (int i = 0; i < arr.length(); i++) {
					committeeEnrollmentYrIds.add(Long.parseLong(arr.get(i).toString()));
				}
			}
			committeeBasicVOList =  cadreDetailsService.getLocationwiseCommitteesCount(jObj.getString("locationType"),jObj.getLong("tdpCadreId"),electionType,locationId,committeeEnrollmentYrIds);
			
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
			Long parlimentId=jObj.getLong("parliamentId");
			
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
	
	public String getIVRSummaryByTdpCadreId(){
		
		try{
			jObj=new JSONObject(getTask());
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			
		    ivrResponseVO=cadreDetailsService.getIVRSummaryByTdpCadreId(tdpCadreId);
			
		}catch(Exception e){
			LOG.error("Exception Occured in getIVRSummaryByTdpCadreId() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getTotalIVRDetailsByTdpCadreId(){
		
		try{
			jObj=new JSONObject(getTask());
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			int startIndex=jObj.getInt("startIndex");
			int maxIndex=jObj.getInt("maxIndex");
			ivrResponseVOlist=cadreDetailsService.getTotalIVRDetailsByTdpCadreId(tdpCadreId,startIndex,maxIndex);
			
		}catch(Exception e){
			LOG.error("Exception Occured in getTotalIVRDetailsByTdpCadreId() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}

	public String getAllTrainingCampDetails(){
		try{
			jObj=new JSONObject(getTask());
			 List<Long> enrollmentYearIds=new ArrayList<Long>();
				JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
				if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
					for( int i=0;i<enrollmentYearIdsArray.length();i++){
						enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
					}
				}
				List<Long> programYearIds=new ArrayList<Long>();
				 JSONArray programIdsArray=jObj.getJSONArray("programIdsList");
							if(programIdsArray!=null &&  programIdsArray.length()>0){
								for( int i=0;i<programIdsArray.length();i++){
									programYearIds.add(Long.valueOf(programIdsArray.getString(i)));
								}
							}
			simpleVoList=trainingCampService.getAllTrainingCampDetails(enrollmentYearIds,programYearIds);			
		}catch(Exception e){
			LOG.error("Exception Occured in getTotalIVRDetailsByTdpCadreId() method, Exception - ",e);
		}
		return Action.SUCCESS;
	}

	public String getCadreDetails()
	  {
		String param=null;
		param=getTask();
		
		try{
			jObj=new JSONObject(param);
			org.json.JSONArray membereTypeIdsList=jObj.getJSONArray("membereTypeIds");
			String searchTypeStr=jObj.getString("searchTypeStr");
			String startDate=jObj.getString("startDate");
			String toDate=jObj.getString("toDate");
			String searchDatType=jObj.getString("searchDatType");
			 List<Long>  idList=new ArrayList<Long>();
			 for(int i=0;i<membereTypeIdsList.length();i++){
				 Long id= Long.valueOf(membereTypeIdsList.get(i).toString());
				 idList.add(id);
			 }
			cadreList=cadreRegistrationService.getLocationwiseCadreRegistraionDetails(idList,searchTypeStr,startDate,toDate,searchDatType);
			 }catch(Exception e){
			LOG.error("Exception Occured in getCadreDetails() in CadreDetailsAction ",e);
		}
		return SUCCESS;
	}
	public String getTypeWiseIvrDetailsOFCadre(){
		try{
			
			jObj=new JSONObject(getTask());
			
			ivrOptionsVOList = cadreDetailsService.getTypeWiseIvrDetailsOFCadre(jObj.getLong("cadreId"));
			
		}catch(Exception e){
			LOG.error("Exception Occured in getTypeWiseIvrDetailsOFCadre() in CadreDetailsAction ",e);
		}
		
		return Action.SUCCESS;
	}

	public String getIvrSurveyInfoByTdpCadreId()
	  {
		try{
			jObj=new JSONObject(getTask());
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			Long entityTypeId = jObj.getLong("entityTypeId");
			String searchType = jObj.getString("searchType");
			
			ivrOptionsList=cadreDetailsService.getIvrSurveyInfoByTdpCadreId(tdpCadreId,entityTypeId,searchType);
		}
		catch(Exception e){
			LOG.error("Exception Occured in getCadreDetails() in CadreDetailsAction ",e);
		}
		return SUCCESS;
	}
	
	public String getCheckCandidateDetails(){
		try{
			jObj=new JSONObject(getTask());
			Long tdpCadreId=jObj.getLong("cadreId");
			cadreDetlsLst=cadreDetailsService.getCheckCandidateCadreDtls(tdpCadreId);
		}catch(Exception e){
			LOG.error("Exception Occured in getCheckCandidateDetails() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getTrainingCampAttendenceInfoInCadreLocation(){
		try{
			jObj=new JSONObject(getTask());
			
			Long boothId = jObj.getLong("boothId");
			Long panchayatId = jObj.getLong("panchayatId");
			Long wardId = jObj.getLong("wardId");
			Long mandalId = jObj.getLong("mandalId");
			Long lebId = jObj.getLong("lebId");
			Long constituencyId = jObj.getLong("constituencyId");
			Long parliamentid = jObj.getLong("parliamentid");
			Long districtid = jObj.getLong("districtid");
			
			idNameVoList = cadreDetailsService.getTrainingCampAttendenceInfoInCadreLocation(boothId, panchayatId, wardId, mandalId, lebId, constituencyId, parliamentid, districtid);
			
		}catch(Exception e){
			LOG.error("Exception Occured in getCheckCandidateDetails() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDeathAndHospitalizationDetails(){
		try{
			jObj=new JSONObject(getTask());
			
			Long panchayatId = jObj.getLong("panchayatId");
			Long mandalId = jObj.getLong("mandalId");
			Long lebId = jObj.getLong("lebId");
			Long constituencyid = jObj.getLong("constituencyId");
			Long districtId = jObj.getLong("districtId");
			Long parlimentId = jObj.getLong("parliamentId");
			
			grievanceDetailsvo = cadreDetailsService.getDeathsAndHospitalizationStatusWiseDetailsInCadreLocation(panchayatId, mandalId, lebId, constituencyid, parlimentId, districtId);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getDeathAndHospitalizationDetails() method, Exception - ",e);
		}
		
		return Action.SUCCESS;
	}
	
	/*public String getDeathsAndHospitalizationStatusWiseDetails(){
		try{
			jObj=new JSONObject(getTask());
			
			Long locationValue = jObj.getLong("locationValue");
			String searchType = jObj.getString("searchType");
			String issueType = jObj.getString("issueType");
			
			idNameVoList = cadreDetailsService.getDeathsAndHospitalizationStatusWiseDetails(locationValue, searchType, issueType);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getDeathsAndHospitalizationStatusWiseDetails() method, Exception - ",e);
		}
		
		return Action.SUCCESS;
	}*/
	
	
	public String getCandateParicipatedSurveyCount(){
		try{
			jObj=new JSONObject(getTask());
			Long tdpCadreId=jObj.getLong("cadreId");
			verifierVO=cadreDetailsService.getCandateParicipatedSurveyCount(tdpCadreId);
		}catch(Exception e){
			LOG.error("Exception Occured in getCandateParicipatedSurveyCount() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getGrievanceStatusDetails(){
		try{
			jObj=new JSONObject(getTask());
			
			Long assemblyId = jObj.getLong("assemblyId");
			Long parliamentid = jObj.getLong("parliamentid");
			Long districtid = jObj.getLong("districtId");
			
			grievanceDetailsVoList = cadreDetailsService.getGrievanceStatusByTypeOfIssueLocation( districtid, assemblyId, parliamentid);
			
		}catch(Exception e){
			LOG.error("Exception Occured in getCheckCandidateDetails() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getStatusCountDetailsForGrievance(){
		try{
			jObj=new JSONObject(getTask());
			
			Long assemblyId = jObj.getLong("assemblyId");
			Long parliamentid = jObj.getLong("parliamentid");
			Long districtid = jObj.getLong("districtId");
			
			gerGrievanceDetailsVO = cadreDetailsService.getGrievanceStatusByTypeOfIssueAndCompleteStatusDetails( districtid, assemblyId, parliamentid);
			
		}catch(Exception e){
			LOG.error("Exception Occured in getCheckCandidateDetails() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getComplaintsDetailsByLocationAndStatus(){
		try{
			jObj=new JSONObject(getTask());
			
			Long locationId = jObj.getLong("locationId");
			String locationType = jObj.getString("locationType");
			Long statusId = jObj.getLong("statusId");
			String issueType = jObj.getString("issueType");
			
			grievDetailsVOList = cadreDetailsService.getComplaintsDetailsByLocationAndStatus(locationId,locationType,statusId,issueType);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getDeathAndHospitalizationDetails() method, Exception - ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getSurveysOnCandidateCount(){
		try{
			jObj=new JSONObject(getTask());
			Long candidateId=jObj.getLong("candidateId");
			Long cadreId = jObj.getLong("cadreId");
			
			verifierVO=cadreDetailsService.getSurveysOnCandidateCount(candidateId,cadreId);
		}catch(Exception e){
			LOG.error("Exception Occured in getSurveysOnCandidateCount() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getSurveysOnCandidateDetails(){
		try{
			jObj=new JSONObject(getTask());
			Long candidateId=jObj.getLong("candidateId");
			Long cadreId = jObj.getLong("cadreId");
			Long stateId = jObj.getLong("stateId");
			Long districtId = jObj.getLong("districtId");
			Long constituencyId = jObj.getLong("constituencyId");
			Long mandalId = jObj.getLong("mandalId");
			Long lebId = jObj.getLong("lebId");
			Long panchayatId = jObj.getLong("panchayatId");
			Long wardId = jObj.getLong("wardId");
			
			//finalList=cadreDetailsService.getSurveysOnCandidateDetails(candidateId,cadreId);
			finalList = cadreDetailsService.getSurveysOnCandidateDetailsNew(candidateId, cadreId, stateId, districtId, constituencyId, mandalId, lebId, panchayatId, wardId);
		}catch(Exception e){
			LOG.error("Exception Occured in getSurveysOnCandidateDetails() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAppointmentsUserDetails(){
		try{
			jObj=new JSONObject(getTask());
			
			
			 List<Long> apptuserIds   = null;
			 
			JSONArray appointmentUserIds = jObj.getJSONArray("appointmentUserIds");
			  if(appointmentUserIds != null && appointmentUserIds.length() > 0){
				  apptuserIds = new ArrayList<Long>(); 
					for (int i = 0; i < appointmentUserIds.length(); i++) {
						apptuserIds.add(Long.parseLong(appointmentUserIds.get(i).toString()));
					}
			   }
			  
			  Long tdpCadreId=jObj.getLong("cadreId");
			  
			  cadreDetailsVO=cadreDetailsService.getAppointmentsUserDetails(apptuserIds,tdpCadreId);
			  
		}catch(Exception e){
			LOG.error("Exception Occured in getAppointmentsUserDetails() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getAllStatusDetailsByComplaint(){
		try{
			jObj=new JSONObject(getTask());
			
			Long complaintId = jObj.getLong("complaintId");
			
			grievanceSampleVO = cadreDetailsService.getAllStatusDetailsByComplaint(complaintId);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getAllStatusDetailsByComplaint() method, Exception - ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getStatusTrackingDetailsOfInsuranceByComplaint(){
		try{
			jObj=new JSONObject(getTask());
			
			Long complaintId = jObj.getLong("complaintId");
			
			grievanceSampleVO = cadreDetailsService.getStatusTrackingDetailsOfInsuranceByComplaint(complaintId);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getStatusTrackingDetailsOfInsuranceByComplaint() method, Exception - ",e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getComplaintsDetailsForGrievanceByLocationAndStatus(){
		try{
			jObj=new JSONObject(getTask());
			
			Long locationId = jObj.getLong("locationId");
			String locationType = jObj.getString("locationType");
			//Long statusId = jObj.getLong("statusId");
			String statusName = jObj.getString("statusName");
			String issueType = jObj.getString("issueType");
			
			grievDetailsVOList = cadreDetailsService.getComplaintsDetailsForGrievanceByLocationAndStatus(locationId,locationType,statusName,issueType);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getDeathAndHospitalizationDetails() method, Exception - ",e);
		}
		
		return Action.SUCCESS;
	}
	public String getIVRSurveysOnCandidateAreaCount(){
		try{
			jObj=new JSONObject(getTask());
			Long districtId=jObj.getLong("districtId");
			verifierVO=cadreDetailsService.getIVRSurveysOnCandidateAreaCount(districtId);
		}catch(Exception e){
			LOG.error("Exception Occured in getIVRSurveysOnCandidateAreaCount() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getIVRSurveysOnCandidateAreaDetails(){
		try{
			jObj=new JSONObject(getTask());
			Long districtId=jObj.getLong("districtId");
			Long constiId=jObj.getLong("constiId");
			Long parliamentId=jObj.getLong("parliamentId");
			Long boothId=jObj.getLong("boothId");
			
			verifierVO=cadreDetailsService.getIVRSurveysOnCandidateAreaDetails(districtId,constiId,parliamentId,boothId);
		}catch(Exception e){
			LOG.error("Exception Occured in getIVRSurveysOnCandidateAreaDetails() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getApprovedAmountDetailsByLocation(){
		try{
			jObj=new JSONObject(getTask());
			
			Long districtId=jObj.getLong("districtId");
			Long constiId=jObj.getLong("constiId");
			Long parliamentId=jObj.getLong("parliamentId");
			
			grievanceSimplevoList = cadreDetailsService.getApprovedAmountDetailsByLocation(constiId,parliamentId,districtId);
			
		}catch(Exception e){
			LOG.error("Exception Occured in getApprovedAmountDetailsByLocation() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getApprovedAmountDetailsForGovtAndWilfareByLocation(){
		try{
			jObj=new JSONObject(getTask());
			
			Long districtId=jObj.getLong("districtId");
			Long constiId=jObj.getLong("constiId");
			Long parliamentId=jObj.getLong("parliamentId");
			
			grievanceSimplevoList = cadreDetailsService.getApprovedAmountDetailsForGovtAndWilfareByLocation(constiId,parliamentId,districtId);
			
		}catch(Exception e){
			LOG.error("Exception Occured in getApprovedAmountDetailsForGovtAndWilfareByLocation() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getGrievanceBenifitsComplaintsInfoByLocation(){
		try{
			jObj=new JSONObject(getTask());
			
			Long locationId=jObj.getLong("locationId");
			String locationType = jObj.getString("locationType");
			String typeOfIssue = jObj.getString("typeOfIssue");
			String benifitType = jObj.getString("benifitType");
			
			grievanceDetailsVoList = cadreDetailsService.getGrievanceBenifitsComplaintsInfoByLocation(locationId, locationType, typeOfIssue, benifitType);
			
		}catch(Exception e){
			LOG.error("Exception Occured in getApprovedAmountDetailsForGovtAndWilfareByLocation() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getConstituencyByState(){
		
		try{
			
			jObj = new JSONObject(getTask());
			idNameVoList=cadreRegistrationForOtherStatesService.getConstituencyByState(jObj.getLong("stateId"));
			
		}catch(Exception e){
			LOG.error(" Entered Into getConstituencyByState",e);
		}
		
		return Action.SUCCESS;
		
	}
	
	public String getEventAttendanceOfCadre(){
		try{
			jObj=new JSONObject(getTask());
			
			idNameVoList=cadreDetailsService.getEventAttendanceOfCadre(jObj.getLong("cadreId"),jObj.getLong("eventId"));
		}catch(Exception e){
			LOG.error("Exception raised in getEventAttendanceOfCadre  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
	
	public String getMobileNumberDetailsByTdpCadre(){
		try{
			jObj=new JSONObject(getTask());
			
			mobileDetailsVO=cadreDetailsService.getMobileNumberDetailsByTdpCadre(jObj.getLong("cadreId"));
			
		}catch(Exception e){
			LOG.error("Exception raised in getMobileNumberDetailsByTdpCadre  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
	public String getCandateActivityAttendance(){
		try{
			jObj=new JSONObject(getTask());
			//activityVOList=cadreDetailsService.getCandateActivityAttendance(jObj.getLong("cadreId"),jObj.getLong("cadreId"));
			
			Long panchayatId = jObj.getLong("panchayatId");
			Long mandalId = jObj.getLong("mandalId");
			Long lebId = jObj.getLong("lebId");
			Long assemblyId = jObj.getLong("assemblyId");
			Long districtId = jObj.getLong("districtId");
			Long stateId = jObj.getLong("stateId");
			Long participatedAssemblyId = jObj.getLong("participatedAssemblyId");
			String activeCode = jObj.getString("activeCode");
			
			activityVOList=cadreDetailsService.getCandateActivityAttendance(jObj.getLong("cadreId"),jObj.getLong("activityLevelId"),panchayatId,mandalId,lebId,assemblyId,districtId,stateId,participatedAssemblyId,activeCode);
		}catch(Exception e){
			LOG.error("Exception raised in getEventAttendanceOfCadre  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}

	public String getAllPublicRepresentatives(){
		try{
			jObj=new JSONObject(getTask());
			idNameVoList = cadreDetailsService.getAllPublicRepresentatives();
			
		}catch(Exception e){
			LOG.error("Exception Occured in getCheckCandidateDetails() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
public String savePublicRepresentativeType(){
		
		try{
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO == null)
				return Action.INPUT;
			Long userId = regVO.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			
			Long cadreId = jObj.getLong("cadreId");
			String cadreName = jObj.getString("cadreName");
			String mobileNo = jObj.getString("mobileNo");
			Long publicRepTypeId = jObj.getLong("publicRepTypeId");
			Long locationScope = jObj.getLong("locationScope");
			Long locationVal = jObj.getLong("locationValue");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			
			ImportantLeadersVO vo = new ImportantLeadersVO();
			vo.setUserId(userId);
			vo.setTdpCadreId(cadreId);
			vo.setName(cadreName);
			vo.setMobileNo(mobileNo);
			vo.setImportantLeadersTypeId(publicRepTypeId);
			vo.setLocationScopeId(locationScope);
			vo.setLocationValue(locationVal);
			vo.setFromDate(fromDate);
			vo.setToDate(toDate);
			
			resultStatus = cadreDetailsService.saveNewPublicRepresentativeDetails(vo);
		}catch(Exception e){
			LOG.error("Exception Occured in savePublicRepresentativeType() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}



public String getAllImportantLeadersTypes(){
	try{
		jObj=new JSONObject(getTask());
		idNameVoList = cadreDetailsService.getAllImportantLeadersTypes();
		
	}catch(Exception e){
		LOG.error("Exception Occured in getAllImportantLeadersTypes() in CadreDetailsAction ",e);
	}
	return Action.SUCCESS;
}

public String getTehsilsInConstituency(){
	try{
		jObj=new JSONObject(getTask());
		Long constituencyId = jObj.getLong("constituencyId");
		idNameVoList = cadreDetailsService.getTehsilListByConstituency(constituencyId);
		
	}catch(Exception e){
		LOG.error("Exception Occured in getTehsilListByConstituency() in CadreDetailsAction ",e);
	}
	return Action.SUCCESS;
}

public String getpanchayatsInTehsil(){
	try{
		jObj=new JSONObject(getTask());
		Long mandalId = jObj.getLong("mandalId");
		idNameVoList = cadreDetailsService.getVillagesInMandal(mandalId);
		
	}catch(Exception e){
		LOG.error("Exception Occured in getVillagesInMandal() in CadreDetailsAction ",e);
	}
	return Action.SUCCESS;
}

public String getLocations(){
	try{
		jObj=new JSONObject(getTask());
		Long impLedTypeId = jObj.getLong("publicRepreTypeId");
		Long districtId = jObj.getLong("districtId");
		Long constituencyId = jObj.getLong("constituencyId");
		Long villageId = jObj.getLong("panchayatId");
		Long mandalId = jObj.getLong("mandalId");
		Long lebId = jObj.getLong("lebId");
		Long wardId = jObj.getLong("wardId");
		
		idNameVO = cadreDetailsService.getLocations(impLedTypeId, districtId, constituencyId, mandalId, lebId, villageId, wardId);
		
	}catch(Exception e){
		LOG.error("Exception Occured in getVillagesInMandal() in CadreDetailsAction ",e);
	}
	return Action.SUCCESS;
}
	
	public String getCadreLocationWiseEventAttendeeCounts(){
		try{
			jObj=new JSONObject(getTask());
			//activityVOList=cadreDetailsService.getCandateActivityAttendance(jObj.getLong("cadreId"),jObj.getLong("cadreId"));
			
			Long eventId = jObj.getLong("eventId");
			String startDate = jObj.getString("startdate");
			String endDate = jObj.getString("endDate");
			Long locationId = jObj.getLong("locationId");
			String locationValue = jObj.getString("locationValue");
			String searchType = jObj.getString("searchType");
			
			mahanaduEventVOList=cadreDetailsService.getCadreLocationWiseEventAttendeeCounts(eventId,startDate,endDate,locationId,locationValue,searchType);
		}catch(Exception e){
			LOG.error("Exception raised in getEventAttendanceOfCadre  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
	public String getMainEvents(){
		try{
			jObj=new JSONObject(getTask());
			
			idNameVoList=cadreDetailsService.getMainEvents();
		}catch(Exception e){
			LOG.error("Exception raised in getMainEvents  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
	public String getStartDateAndEndDate(){
		try{
			jObj=new JSONObject(getTask());
			
			basicVo=cadreDetailsService.getStartDateAndEndDate(jObj.getLong("eventId"));
		}catch(Exception e){
			LOG.error("Exception raised in getStartDateAndEndDate  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
	
	public String getImpCandAndPublRepresentativeDetailsByLocation(){
		try{
			jObj=new JSONObject(getTask());
			
			idNameVO=cadreDetailsService.getImpCandAndPublRepresentativeDetailsByLocation(jObj.getLong("locationId"),jObj.getString("searchType"));
		}catch(Exception e){
			LOG.error("Exception raised in getImpCandAndPublRepresentativeDetailsByLocation  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
public String saveCadreInformationDetails(){
	
	try{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		jObj = new JSONObject(getTask());
		Long tdpCadreId = jObj.getLong("tdpCadreId");
		String notes = jObj.getString("notes");
		Long userId = regVO.getRegistrationID();
		Long primaryTdpCadrenotesId = jObj.getLong("globalPrimaryId");
		resultStatus = cadreDetailsService.saveCadreNotesInformationDetails(tdpCadreId,notes,userId,primaryTdpCadrenotesId);
	}catch(Exception e){
		LOG.error("Exception Occured in savecadreInformationDetails() in CadreDetailsAction ",e);
	}
	return Action.SUCCESS;
}
public String getCadreInformationDetails(){
	
	try{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		jObj = new JSONObject(getTask());
		Long tdpCadreId = jObj.getLong("tdpCadreId");
		Integer startIndex = jObj.getInt("startIndex");
		Integer maxIndex = jObj.getInt("maxIndex");
		Long userId = regVO.getRegistrationID(); 
		basicVoList = cadreDetailsService.getcadreNotesInformationDetails(tdpCadreId,startIndex,maxIndex,null);
		
		if(basicVoList != null && basicVoList.size() > 0){
			basicVoList.get(0).setLevelId(userId);
		}
	}catch(Exception e){
		LOG.error("Exception Occured in getcadreInformationDetails() in CadreDetailsAction ",e);
	}
	return Action.SUCCESS;
}
public String deleteCadreNotesData()
{
	try
	{
		jObj = new JSONObject(getTask());
		Long cadreNotes = jObj.getLong("cadreNotes");
		status = cadreDetailsService.deleteCadreNotesData(cadreNotes);
		
	}catch(Exception e)
	{
		LOG.error("Exception Occured in deleteCadreNotesData() in CadreDetailsAction ",e);
	}
	return Action.SUCCESS;
}
public String updateCadreNotesInfrmationAction()
{
	try
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		jObj = new JSONObject(getTask());
		Long notesId = jObj.getLong("notesId");
		String notes = jObj.getString("notes");
		Long  UserId = regVO.getRegistrationID();
		resultStatus = cadreDetailsService.updateCadreNotesInfrmationAllDetails(notesId,notes,UserId);
		
	}catch(Exception e)
	{
		LOG.error("Exception Occured in deleteCadreNotesData() in CadreDetailsAction ",e);
	}
	return Action.SUCCESS;
}

	public String getRegionScopes()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			idNameVoList = cadreDetailsService.getRegionScopes();
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in getRegionScopes() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String saveImportantLeadersType()
	{
		try
		{
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			jObj = new JSONObject(getTask());
			String position = jObj.getString("position");
			Long levelId = jObj.getLong("levelId");
			Long userId = regVO.getRegistrationID();
			
			status = cadreDetailsService.saveImportantLeadersType(position,levelId,userId);
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in saveImportantLeadersType() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getGrievancePDFReport()
	{
		try
		{
			jObj = new JSONObject(getTask());
			grievanceReportVOList = cadreDetailsService.getGrievancePDFReport(jObj.getString("membershipId"));
			
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getGrievancePDFReport() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}
	
	/*public String checkPositionExists()
	{
		try
		{
			jObj = new JSONObject(getTask());
			
			status = cadreDetailsService.checkPositionExists(jObj.getString("position"));
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in checkPositionExists() in CadreDetailsAction ",e);
		}
		return Action.SUCCESS;
	}*/
	
	public String getNominatedPostReportFiles(){
		try{
			jObj = new JSONObject(getTask());
			Long tdpCadreId=jObj.getLong("tdpCadreId");
			reportVOList=cadreDetailsService.getNominatedPostReportFiles(tdpCadreId);
		}catch(Exception e){
		 LOG.error("Exception occured in getNominatedPostReportFilesAction ",e);
		}
		return Action.SUCCESS;
	}
public String getParliamentAndDistrictwiseCasteInformation(){
		try{
			constituencyManagementVO = new ConstituencyManagementVO();
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			if(regVO==null){
				return "input";
			}
			jObj = new JSONObject(getTask());
			String type = jObj.getString("type");
			Long locationId = jObj.getLong("id");
			Long publicationDateId = jObj.getLong("publicationDateId");
			Long userId = regVO.getRegistrationID();
			VoterCastInfoVO votersByCast = cadreDetailsService.getParliamentAndDistrictwiseCasteInformation(type,locationId,publicationDateId,userId);
			constituencyManagementVO.setVoterCastInfodetails(votersByCast);
		}catch(Exception e){
		 LOG.error("Exception occured in getNominatedPostReportFilesAction ",e);
		}
		return Action.SUCCESS;
}
public String getVolunteerCadreDetilasInformation(){
	try{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		if(regVO==null){
			return "input";
		}
		jObj = new JSONObject(getTask());
		Long cadreId = jObj.getLong("tdpcadreId");
		selectOptList = cadreDetailsService.getVolunteerCadreDetilasInformation(cadreId);
	}catch(Exception e){
	 LOG.error("Exception occured in getNominatedPostReportFilesAction ",e);
	}
	return Action.SUCCESS;
}



/*
 * auther : Srishailam Pittala
 * Date : 29th Dec, 2016
 * Description : to Get Cadre wise all alert Details
 * */

	public String getCadreAlertDetails(){
		
		try {
			
			jObj = new JSONObject(getTask());
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			Long stateId = jObj.getLong("stateId");
			String startDateStr = jObj.getString("startDateStr");
			String endDateStr = jObj.getString("endDateStr");
			String searchType = jObj.getString("searchType");
			Long alertTypeId = jObj.getLong("alertTypeId");
			
			 alertVO = alertService.getAlertDetailsBySearch(tdpCadreId,stateId,startDateStr,endDateStr,searchType,alertTypeId);
			
		} catch (Exception e) {
			 LOG.error("Exception occured in getCadreAlertDetails in CadreDetailsAction class  ",e);
		}
		return "success";
	}

	

/*
 * auther : Srishailam Pittala
 * Date : 30th Dec, 2016
 * Description : to Get Cadre selection wise alert Details
 * */

	public String getCandidateAlertDetails(){
		
		try {
			
			jObj = new JSONObject(getTask());
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			Long stateId = jObj.getLong("stateId");
			String startDateStr = jObj.getString("startDateStr");
			String endDateStr = jObj.getString("endDateStr");
			String searchType = jObj.getString("searchType");
			Long alertTypeId = jObj.getLong("alertTypeId");
			Long categoryId = jObj.getLong("categoryId");
			Long statusId = jObj.getLong("statusId");
			
			 alertVO = alertService.getCandidateAlertDetailsBySearch(tdpCadreId,stateId,startDateStr,endDateStr,searchType,alertTypeId,categoryId,statusId);
			
		} catch (Exception e) {
			 LOG.error("Exception occured in getCadreAlertDetails in CadreDetailsAction class  ",e);
		}
		return "success";
	}
	public String getLeaderDtlsInfo(){
		try {
			jObj = new JSONObject(getTask());
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			cadreCountsVOsList = candidateDetailsService.getLeaderDtlsInfo(tdpCadreId);
		} catch (Exception e) {
			 LOG.error("Exception occured in getLeaderDtlsInfo in CadreDetailsAction class  ",e);
		}
		return "success";
	}
	

	/*
	 * auther : Srishailam Pittala
	 * Date : 7th Jan, 2017
	 * Description : to Get Cadre wise all Tours Details
	 * */

		public String getCadretoursDetails(){
			
			try {
				
				jObj = new JSONObject(getTask());
				Long tdpCadreId = jObj.getLong("tdpCadreId");
				Long stateId = jObj.getLong("stateId");
				String startDateStr = jObj.getString("startDateStr");
				String endDateStr = jObj.getString("endDateStr");
				String searchType = jObj.getString("searchType");
				Long designationId = jObj.getLong("designationId");
				String searchMonth = jObj.getString("searchMonth");
				 toursVO = toursService.getToursDetailsBySearch(tdpCadreId,stateId,startDateStr,endDateStr,searchType,designationId,searchMonth);
				
			} catch (Exception e) {
				 LOG.error("Exception occured in getCadretoursDetails in CadreDetailsAction class  ",e);
			}
			return "success";
		}

		

	/*
	 * auther : Srishailam Pittala
	 * Date : 7th Jan, 2017
	 * Description : to Get Cadre selection wise Tours Details
	 * */

		public String getCandidateToursDetails(){
			
			try {
				
				jObj = new JSONObject(getTask());
				Long tdpCadreId = jObj.getLong("tdpCadreId");
				Long stateId = jObj.getLong("stateId");
				String startDateStr = jObj.getString("startDateStr");
				String endDateStr = jObj.getString("endDateStr");
				String searchType = jObj.getString("searchType");
				Long designationId = jObj.getLong("designationId");
				Long categoryId = jObj.getLong("categoryId");
				String searchMonth = jObj.getString("searchMonth");
				toursVO = toursService.getCandidateToursDetailsBySearch(tdpCadreId,stateId,startDateStr,endDateStr,searchType,designationId,categoryId,searchMonth);
				
			} catch (Exception e) {
				 LOG.error("Exception occured in getCandidateToursDetails in CadreDetailsAction class  ",e);
			}
			return "success";
		}
		
		
	public String getCandidateSubLocationDtls(){
		try {
			jObj = new JSONObject(getTask());
			Long distId = jObj.getLong("districtId");  
			cadreCountsVOs = candidateDetailsService.getCandidateSubLocationDtls(distId);
		} catch (Exception e) {
			 LOG.error("Exception occured in getLeaderDtlsInfo in CadreDetailsAction class  ",e);
		}
		return "success";
	}

	public String getBenefitDetailsAlongFamily(){
		try {
			jObj = new JSONObject(getTask());
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			
			List<Long> familyCadreIds = new ArrayList<Long>(0);
			//familyCadreIds.add(tdpCadreId);
			JSONArray arr = jObj.getJSONArray("familyCadreIdsArray");
			if(arr != null && arr.length() > 0){
				for (int i = 0; i < arr.length(); i++) {
					familyCadreIds.add(Long.parseLong(arr.getString(i)));
				}
			}
			benefitVO = cadreDetailsService.getBenefitDetailsAlongFamily(tdpCadreId,familyCadreIds);
		} catch (Exception e) {
			LOG.error("Exception occured in getBenefitDetailsAlongFamily in CadreDetailsAction class  ",e);
		}
		return "success";
	}
	
	public String getOwnAndParticipatedConstituenciesBenefitDetails(){
		try {
			jObj = new JSONObject(getTask());
			benefitVO = cadreDetailsService.getOwnAndParticipatedConstituenciesBenefitDetails(jObj.getLong("constituencyId"),jObj.getLong("pConstituencyId"));
		} catch (Exception e) {
			LOG.error("Exception occured in getOwnAndParticipatedConstituenciesBenefitDetails in CadreDetailsAction class  ",e);
		}
		return "success";
	}
	
	public String getLocalityBasedBenefitSchemesDetails(){
		try {
			jObj = new JSONObject(getTask());
			benefitVOList = cadreDetailsService.getLocalityBasedBenefitSchemesDetails(jObj.getLong("tdpCadreId"));
		} catch (Exception e) {
			LOG.error("Exception occured in getLocalityBasedBenefitSchemesDetails in CadreDetailsAction class  ",e);
		}
		return "success";
	}
	
	public String getBenefitSchemesMembersDetails(){
		try {
			jObj = new JSONObject(getTask());
			benefitCandidateVOList = cadreDetailsService.getBenefitSchemesMembersDetails(jObj.getLong("locationLevelValue"),jObj.getLong("benefitId"),jObj.getInt("minValue"),jObj.getInt("maxValue"));
		} catch (Exception e) {
			LOG.error("Exception occured in getBenefitSchemesMembersDetails in CadreDetailsAction class  ",e);
		}
		return "success";
	}
	
	public String getAllConstBenefitDetailsForADist(){
		try {
			jObj = new JSONObject(getTask());
			benefitVOList = cadreDetailsService.getAllConstBenefitDetailsForADist(jObj.getLong("distId"));
		} catch (Exception e) {
			LOG.error("Exception occured in getAllConstBenefitDetailsForADist in CadreDetailsAction class  ",e);
		}
		return "success";
	}
	public String getCadreTourDetails(){
		
		try {
			
			jObj = new JSONObject(getTask());
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			String startDateStr = jObj.getString("startDateStr");
			String endDateStr = jObj.getString("endDateStr");
			tourDetailsVO = toursService.getCadreTourDetails(tdpCadreId,startDateStr,endDateStr);
			
		} catch (Exception e) {
			 LOG.error("Exception occured in getCadreTourDetails in CadreDetailsAction class  ",e);
		}
		return "success";
	}
	
	public String getTdpCadreHealthDetailsByCadre(){
		
		try {
			
			jObj = new JSONObject(getTask());
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			
			idAndNameVoList = cadreDetailsService.getTdpCadreHealthDetailsByCadre(tdpCadreId);
			
		} catch (Exception e) {
			 LOG.error("Exception occured in getTdpCadreHealthDetailsByCadre in CadreDetailsAction class  ",e);
		}
		return "success";
	}
	public String getSurveyQuestionWithMarksDetailsByTDpCadreId(){
		
		try{
			jObj=new JSONObject(getTask());
			
			Long cadreId=jObj.getLong("cadreId");
			
			finalList = cadreDetailsService.getSurveyQuestionWithMarksDetailsByTDpCadreId(cadreId);
		}catch(Exception e){
			LOG.error("Exception raised in getSurveyQuestionWithMarksDetailsByTDpCadreId  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
	public String getTdpCadreByHealthCadreWiseDetails(){
		try{
			
			jObj = new JSONObject(getTask());
			Long tdpCadreId = jObj.getLong("tdpCadreId");
			cadreHealthDetailList = cadreDetailsService.getTdpCadreHealthDetailsByCadreIds(tdpCadreId);
		}catch(Exception e){
			 LOG.error("Exception occured in getTdpCadreByHealthCadreWiseDetails in CadreDetailsAction class  ",e);
		}
		
		return "success";
	}
public String getSurveyQuestionDetails(){
		
		try{
			jObj=new JSONObject(getTask());
			
			Long cadreId=jObj.getLong("cadreId");
			
			finalList = cadreDetailsService.getSurveyQuestionDetails(cadreId);
		}catch(Exception e){
			LOG.error("Exception raised in  getSurveyQuestionDetails method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
}
