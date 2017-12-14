package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.BoothInchargesVO;
import com.itgrids.partyanalyst.dto.CadreBasicVO;
import com.itgrids.partyanalyst.dto.CadreRegistratedCountVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreReportVO;
import com.itgrids.partyanalyst.dto.CadreResponseVO;
import com.itgrids.partyanalyst.dto.ChildUserTypeVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.CommitteeVO;
import com.itgrids.partyanalyst.dto.ComplaintMasterVO;
import com.itgrids.partyanalyst.dto.CoreDashboardInsuranceVO;
import com.itgrids.partyanalyst.dto.CoreDebateVO;
import com.itgrids.partyanalyst.dto.DashboardCommentVO;
import com.itgrids.partyanalyst.dto.EventDetailsVO;
import com.itgrids.partyanalyst.dto.HolidayListVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.InsuranceLagDaysVO;
import com.itgrids.partyanalyst.dto.InsuranceSimpleVO;
import com.itgrids.partyanalyst.dto.KaizalaDashboardVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.MeetingBasicDetailsVO;
import com.itgrids.partyanalyst.dto.MeetingVO;
import com.itgrids.partyanalyst.dto.NewCadreRegistrationVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsDataVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
import com.itgrids.partyanalyst.dto.PaymentGatewayVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SessionVO;
import com.itgrids.partyanalyst.dto.TabLoginAuthVO;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.dto.ToursOverviewDtlsvO;
import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
import com.itgrids.partyanalyst.dto.TrainingCampSurveyVO;
import com.itgrids.partyanalyst.dto.TrainingCampVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.IAlertService;
import com.itgrids.partyanalyst.service.IAttendanceCoreDashBoardService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICoreDashboardCadreRegistrationService;
import com.itgrids.partyanalyst.service.ICoreDashboardCoreService;
import com.itgrids.partyanalyst.service.ICoreDashboardEventsActivitiesService;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.service.ICoreDashboardInsuranceService;
import com.itgrids.partyanalyst.service.ICoreDashboardMainService;
import com.itgrids.partyanalyst.service.ICoreDashboardPartyMeetingService;
import com.itgrids.partyanalyst.service.ICoreDashboardService;
import com.itgrids.partyanalyst.service.ICoreDashboardService1;
import com.itgrids.partyanalyst.service.ICoreDashboardToursService;
import com.itgrids.partyanalyst.service.IKaizalaInfoService;
import com.itgrids.partyanalyst.service.INewsCoreDashBoardService;
import com.itgrids.partyanalyst.service.IPaymentGatewayService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CoreDashboardAction extends ActionSupport implements ServletRequestAware{
    
	private final static Logger LOG = Logger.getLogger(CoreDashboardAction.class);
	
	//Instance variables
	private HttpServletRequest request;
	private String task;
	private JSONObject jObj;
	private List<UserDataVO> userDataVOList;
	private UserDataVO userDataVO;
	private List<CommitteeBasicVO> committeeBasicVO;
	private List<CommitteeVO> committeeVOList;
	private CommitteeVO committeeVO ;
	private UserTypeVO userTypeVO;
	private List<CommitteeDataVO> committeeDataVOList;
	private CommitteeDataVO committeeDataVO;
	private List<List<UserTypeVO>> userTypeVOList;
	private TrainingCampProgramVO trainingCampProgramVO;
	private TrainingCampVO trainingCampVO;
	private List<TrainingCampProgramVO> trainingCampProgramVOList;
	private List<UserTypeVO> activityMembersList;
	private PartyMeetingsVO partyMeetingsVO;
	private List<PartyMeetingsVO> partyMeetingsVOList;
	private List<PartyMeetingsDataVO> partyMeetingDataVOList;
	private PartyMeetingsDataVO partyMeetingDataVO;
	private CadreRegistratedCountVO cadreRegistratedCountVO;
	private ToursBasicVO toursBasicVO;
	private List<ToursBasicVO> toursDtlsList;
	private List<List<ToursBasicVO>> memberList;
	private List<Long> programIdsList;
	//Attributes
	private ICoreDashboardService coreDashboardService;
	private ICoreDashboardService1 coreDashboardService1;
	private ICoreDashboardMainService coreDashboardMainService;
	private ICoreDashboardGenericService coreDashboardGenericService;
	private IAttendanceCoreDashBoardService attendanceCoreDashBoardService;
	private ICoreDashboardToursService coreDashboardToursService;
	private IAlertService alertService;
	private ICoreDashboardInsuranceService coreDashboardInsuranceService;
	private IKaizalaInfoService kaizalaInfoService;
	
	private ICoreDashboardCoreService coreDashboardCoreService;
	private List<CoreDebateVO> codeDebateVoList;
	private INewsCoreDashBoardService newsCoreDashBoardService;
	private IdNameVO idNameVO; 
	private List<CadreReportVO> cadreDtlsList;
	private CadreBasicVO cadreVO;

	private List<List<IdNameVO>> idNameVOsList;
    private ICoreDashboardPartyMeetingService coreDashboardPartyMeetingService;
    private ICoreDashboardEventsActivitiesService coreDashboardEventsActivitiesService;
    
	private ResultStatus 						resultStatus;
	private List<DashboardCommentVO> 						dashboardCommentVo;
	private String status;
	private Object object;
	
	private List<IdNameVO> idNameVoList;
	private List<IdAndNameVO> IdAndNameVOList;
	private List<ChildUserTypeVO> childUserTypeVOList;
	private List<EventDetailsVO>  eventDetailsVOList;
	private EventDetailsVO eventDetailsVO;
	private List<HolidayListVO> holidayListVOs;
	private String edTypeIdStr;
	private String bfIdStr;
	private String orgIdStr;
	private String orgType;
	private String callFrom;
	private String stIdx;
	private String edIdx;
	private String levelId;
	private String temp;
	private String state;
	private String sdat;
	private String edat;
	private String scops;
	private String npsStr;
	private String ediDistIdsStr;
	private String propIdsStr;
	private CadreRegistrationVO cadreRegistrationVO;
	private  Long constId;
	private String constName;
	private ICoreDashboardCadreRegistrationService coreDashboardCadreRegistrationService;
	private InputStream 						inputStream;
	private NewCadreRegistrationVO newCadreRegistrationVO;
	private MeetingVO meetingVO;
	private MeetingBasicDetailsVO meetingBasicDetailsVO;
	private List<PartyMeetingVO> partyMeetingVOList = new ArrayList<PartyMeetingVO>();
	private List<Long> partyMeetingLevelIds = new ArrayList<Long>();
	private List<SessionVO> sessionVOList = new ArrayList<SessionVO>();
	private AlertOverviewVO alertOverviewVO = new AlertOverviewVO();
	private List<TrainingCampSurveyVO> campSurveyVOs;
	
	/**
	 * starting Payment Gateway required parameters
	 */
	private ICadreRegistrationService cadreRegistrationService;
	private IPaymentGatewayService paymentGatewayService;
	private String membershipNo;
	private String enrollMentNO;
	private String mn;
	private String en;
	private String AuthDesc;
	private List<IdNameVO> idNameVOs;
	private List<TabLoginAuthVO> tabDetails;
	private String tabUserDetails;
	private ResultStatus 						partyMeetingStatus;
	/**
	 * Ending Payment Gateway required parameters
	 * 
	 */
	
	private List<CoreDashboardInsuranceVO> coreDashboardInsurancevoList = new ArrayList<CoreDashboardInsuranceVO>();
	private List<ComplaintMasterVO> complaintMastervoList = new ArrayList<ComplaintMasterVO>();
	private InsuranceLagDaysVO insuranceLagDaysVO;
	private InsuranceSimpleVO insuranceSimpleVO;
	private ComplaintMasterVO complaintMasterVO;
	private CoreDashboardInsuranceVO coreDashboardInsuranceVO;
	private BoothInchargesVO boothInchargeVo;
	private List<BoothInchargesVO> boothInchargesVOList;
	private ToursOverviewDtlsvO tourOverviewDtlsVO;
	private List<ToursOverviewDtlsvO> tourOverviewDtlsList;
	private List<KaizalaDashboardVO> kaizalaDashboardList;
	private List<KeyValueVO> keyValueVOList;
	private KaizalaDashboardVO kaizalaDashboardVO;
	
	//setters And Getters
	
	
	public List<Long> getProgramIdsList() {
		return programIdsList;
	}

	public KaizalaDashboardVO getKaizalaDashboardVO() {
		return kaizalaDashboardVO;
	}

	public void setKaizalaDashboardVO(KaizalaDashboardVO kaizalaDashboardVO) {
		this.kaizalaDashboardVO = kaizalaDashboardVO;
	}

	public TrainingCampVO getTrainingCampVO() {
		return trainingCampVO;
	}

	public void setTrainingCampVO(TrainingCampVO trainingCampVO) {
		this.trainingCampVO = trainingCampVO;
	}

	public void setProgramIdsList(List<Long> programIdsList) {
		this.programIdsList = programIdsList;
	}
	public ICoreDashboardCoreService getCoreDashboardCoreService() {
		return coreDashboardCoreService;
	}

	public void setCoreDashboardCoreService(
			ICoreDashboardCoreService coreDashboardCoreService) {
		this.coreDashboardCoreService = coreDashboardCoreService;
	}
	public List<List<ToursBasicVO>> getMemberList() {
		return memberList;
	}

	public List<BoothInchargesVO> getBoothInchargesVOList() {
		return boothInchargesVOList;
	}

	public void setBoothInchargesVOList(List<BoothInchargesVO> boothInchargesVOList) {
		this.boothInchargesVOList = boothInchargesVOList;
	}

	public CoreDashboardInsuranceVO getCoreDashboardInsuranceVO() {
		return coreDashboardInsuranceVO;
	}

	public void setCoreDashboardInsuranceVO(
			CoreDashboardInsuranceVO coreDashboardInsuranceVO) {
		this.coreDashboardInsuranceVO = coreDashboardInsuranceVO;
	}

	public ComplaintMasterVO getComplaintMasterVO() {
		return complaintMasterVO;
	}

	public void setComplaintMasterVO(ComplaintMasterVO complaintMasterVO) {
		this.complaintMasterVO = complaintMasterVO;
	}

	public InsuranceSimpleVO getInsuranceSimpleVO() {
		return insuranceSimpleVO;
	}

	public void setInsuranceSimpleVO(InsuranceSimpleVO insuranceSimpleVO) {
		this.insuranceSimpleVO = insuranceSimpleVO;
	}

	public InsuranceLagDaysVO getInsuranceLagDaysVO() {
		return insuranceLagDaysVO;
	}

	public void setInsuranceLagDaysVO(InsuranceLagDaysVO insuranceLagDaysVO) {
		this.insuranceLagDaysVO = insuranceLagDaysVO;
	}

	public List<ComplaintMasterVO> getComplaintMastervoList() {
		return complaintMastervoList;
	}

	public void setComplaintMastervoList(
			List<ComplaintMasterVO> complaintMastervoList) {
		this.complaintMastervoList = complaintMastervoList;
	}

	public List<CoreDashboardInsuranceVO> getCoreDashboardInsurancevoList() {
		return coreDashboardInsurancevoList;
	}

	public void setCoreDashboardInsurancevoList(
			List<CoreDashboardInsuranceVO> coreDashboardInsurancevoList) {
		this.coreDashboardInsurancevoList = coreDashboardInsurancevoList;
	}

	public ICoreDashboardInsuranceService getCoreDashboardInsuranceService() {
		return coreDashboardInsuranceService;
	}

	public void setCoreDashboardInsuranceService(
			ICoreDashboardInsuranceService coreDashboardInsuranceService) {
		this.coreDashboardInsuranceService = coreDashboardInsuranceService;
	}

	public List<SessionVO> getSessionVOList() {
		return sessionVOList;
	}

	public void setSessionVOList(List<SessionVO> sessionVOList) {
		this.sessionVOList = sessionVOList;
	}

	public List<Long> getPartyMeetingLevelIds() {
		return partyMeetingLevelIds;
	}

	public void setPartyMeetingLevelIds(List<Long> partyMeetingLevelIds) {
		this.partyMeetingLevelIds = partyMeetingLevelIds;
	}

	public List<PartyMeetingVO> getPartyMeetingVOList() {
		return partyMeetingVOList;
	}

	public void setPartyMeetingVOList(List<PartyMeetingVO> partyMeetingVOList) {
		this.partyMeetingVOList = partyMeetingVOList;
	}

	public MeetingBasicDetailsVO getMeetingBasicDetailsVO() {
		return meetingBasicDetailsVO;
	}

	public void setMeetingBasicDetailsVO(MeetingBasicDetailsVO meetingBasicDetailsVO) {
		this.meetingBasicDetailsVO = meetingBasicDetailsVO;
	}

	public MeetingVO getMeetingVO() {
		return meetingVO;
	}

	public void setMeetingVO(MeetingVO meetingVO) {
		this.meetingVO = meetingVO;
	}

	public void setMemberList(List<List<ToursBasicVO>> memberList) {
		this.memberList = memberList;
	}
	
	public List<PartyMeetingsVO> getPartyMeetingsVOList() {
		return partyMeetingsVOList;
	}
	public String getTabUserDetails() {
		return tabUserDetails;
	}

	public void setTabUserDetails(String tabUserDetails) {
		this.tabUserDetails = tabUserDetails;
	}

	public List<TabLoginAuthVO> getTabDetails() {
		return tabDetails;
	}

	public void setTabDetails(List<TabLoginAuthVO> tabDetails) {
		this.tabDetails = tabDetails;
	}

	public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}

	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}

	public String getMembershipNo() {
		return membershipNo;
	}

	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}

	public String getEnrollMentNO() {
		return enrollMentNO;
	}

	public void setEnrollMentNO(String enrollMentNO) {
		this.enrollMentNO = enrollMentNO;
	}

	public String getMn() {
		return mn;
	}

	public void setMn(String mn) {
		this.mn = mn;
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public String getAuthDesc() {
		return AuthDesc;
	}

	public void setAuthDesc(String authDesc) {
		AuthDesc = authDesc;
	}

	public IPaymentGatewayService getPaymentGatewayService() {
		return paymentGatewayService;
	}

	public void setPaymentGatewayService(
			IPaymentGatewayService paymentGatewayService) {
		this.paymentGatewayService = paymentGatewayService;
	}

	public NewCadreRegistrationVO getNewCadreRegistrationVO() {
		return newCadreRegistrationVO;
	}

	public void setNewCadreRegistrationVO(
			NewCadreRegistrationVO newCadreRegistrationVO) {
		this.newCadreRegistrationVO = newCadreRegistrationVO;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public CadreRegistrationVO getCadreRegistrationVO() {
		return cadreRegistrationVO;
	}

	public void setCadreRegistrationVO(CadreRegistrationVO cadreRegistrationVO) {
		this.cadreRegistrationVO = cadreRegistrationVO;
	}

	public List<IdAndNameVO> getIdAndNameVOList() {
		return IdAndNameVOList;
	}

	public void setIdAndNameVOList(List<IdAndNameVO> idAndNameVOList) {
		IdAndNameVOList = idAndNameVOList;
	}

	public void setPartyMeetingsVOList(List<PartyMeetingsVO> partyMeetingsVOList) {
		this.partyMeetingsVOList = partyMeetingsVOList;
	}

	public void setCoreDashboardService(ICoreDashboardService coreDashboardService) {
		this.coreDashboardService = coreDashboardService;
	}
	
	public void setCoreDashboardService1(ICoreDashboardService1 coreDashboardService1) {
		this.coreDashboardService1 = coreDashboardService1;
	}
	public TrainingCampProgramVO getTrainingCampProgramVO() {
		return trainingCampProgramVO;
	}

	public void setTrainingCampProgramVO(TrainingCampProgramVO trainingCampProgramVO) {
		this.trainingCampProgramVO = trainingCampProgramVO;
	}
  public List<TrainingCampProgramVO> getTrainingCampProgramVOList() {
		return trainingCampProgramVOList;
	}

	public void setTrainingCampProgramVOList(
			List<TrainingCampProgramVO> trainingCampProgramVOList) {
		this.trainingCampProgramVOList = trainingCampProgramVOList;
	}
   public PartyMeetingsVO getPartyMeetingsVO() {
		return partyMeetingsVO;
	}

	public void setPartyMeetingsVO(PartyMeetingsVO partyMeetingsVO) {
		this.partyMeetingsVO = partyMeetingsVO;
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
	
	public List<UserDataVO> getUserDataVOList() {
		return userDataVOList;
	}

	public void setUserDataVOList(List<UserDataVO> userDataVOList) {
		this.userDataVOList = userDataVOList;
	}

	public UserDataVO getUserDataVO() {
		return userDataVO;
	}

	public void setUserDataVO(UserDataVO userDataVO) {
		this.userDataVO = userDataVO;
	}

	public List<CommitteeBasicVO> getCommitteeBasicVO() {
		return committeeBasicVO;
	}

	public void setCommitteeBasicVO(List<CommitteeBasicVO> committeeBasicVO) {
		this.committeeBasicVO = committeeBasicVO;
	}
	
	public List<CommitteeVO> getCommitteeVOList() {
		return committeeVOList;
	}

	public void setCommitteeVOList(List<CommitteeVO> committeeVOList) {
		this.committeeVOList = committeeVOList;
	}
	
	public CommitteeVO getCommitteeVO() {
		return committeeVO;
	}

	public void setCommitteeVO(CommitteeVO committeeVO) {
		this.committeeVO = committeeVO;
	}
	
	public UserTypeVO getUserTypeVO() {
		return userTypeVO;
	}

	public void setUserTypeVO(UserTypeVO userTypeVO) {
		this.userTypeVO = userTypeVO;
	}
	
	public List<CommitteeDataVO> getCommitteeDataVOList() {
		return committeeDataVOList;
	}

	public void setCommitteeDataVOList(List<CommitteeDataVO> committeeDataVOList) {
		this.committeeDataVOList = committeeDataVOList;
	}

	public CommitteeDataVO getCommitteeDataVO() {
		return committeeDataVO;
	}

	public void setCommitteeDataVO(CommitteeDataVO committeeDataVO) {
		this.committeeDataVO = committeeDataVO;
	}
	
	
	public List<List<UserTypeVO>> getUserTypeVOList() {
		return userTypeVOList;
	}

	public void setUserTypeVOList(List<List<UserTypeVO>> userTypeVOList) {
		this.userTypeVOList = userTypeVOList;
	}
	
	public void setCoreDashboardMainService(
			ICoreDashboardMainService coreDashboardMainService) {
		this.coreDashboardMainService = coreDashboardMainService;
	}
	

	public void setCoreDashboardGenericService(
			ICoreDashboardGenericService coreDashboardGenericService) {
		this.coreDashboardGenericService = coreDashboardGenericService;
	}
	
	public List<UserTypeVO> getActivityMembersList() {
		return activityMembersList;
	}

	public void setActivityMembersList(List<UserTypeVO> activityMembersList) {
		this.activityMembersList = activityMembersList;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public List<DashboardCommentVO> getDashboardCommentVo() {
		return dashboardCommentVo;
	}

	public void setDashboardCommentVo(List<DashboardCommentVO> dashboardCommentVo) {
		this.dashboardCommentVo = dashboardCommentVo;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<PartyMeetingsDataVO> getPartyMeetingDataVOList() {
		return partyMeetingDataVOList;
	}

	public void setPartyMeetingDataVOList(
			List<PartyMeetingsDataVO> partyMeetingDataVOList) {
		this.partyMeetingDataVOList = partyMeetingDataVOList;
	}
	
	public void setPartyMeetingDataVO(PartyMeetingsDataVO partyMeetingDataVO) {
		this.partyMeetingDataVO = partyMeetingDataVO;
	}
    
	public PartyMeetingsDataVO getPartyMeetingDataVO() {
		return partyMeetingDataVO;
	}
	
	
	public String getEdTypeIdStr() {
		return edTypeIdStr;
	}

	public void setEdTypeIdStr(String edTypeIdStr) {
		this.edTypeIdStr = edTypeIdStr;
	}

	public String getBfIdStr() {
		return bfIdStr;
	}

	public void setBfIdStr(String bfIdStr) {
		this.bfIdStr = bfIdStr;
	}

	public String getOrgIdStr() {
		return orgIdStr;
	}

	public void setOrgIdStr(String orgIdStr) {
		this.orgIdStr = orgIdStr;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	
	public String getCallFrom() {
		return callFrom;
	}

	public void setCallFrom(String callFrom) {
		this.callFrom = callFrom;
	}

	public String getStIdx() {
		return stIdx;
	}

	public void setStIdx(String stIdx) {
		this.stIdx = stIdx;
	}

	public String getEdIdx() {
		return edIdx;
	}

	public void setEdIdx(String edIdx) {
		this.edIdx = edIdx;
	}
	
	
	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSdat() {
		return sdat;
	}

	public void setSdat(String sdat) {
		this.sdat = sdat;
	}

	public String getEdat() {
		return edat;
	}

	public void setEdat(String edat) {
		this.edat = edat;
	}

	public String getScops() {
		return scops;
	}

	public void setScops(String scops) {
		this.scops = scops;
	}

	public String getNpsStr() {
		return npsStr;
	}

	public void setNpsStr(String npsStr) {
		this.npsStr = npsStr;
	}
	
	public ICoreDashboardCadreRegistrationService getCoreDashboardCadreRegistrationService() {
		return coreDashboardCadreRegistrationService;
	}

	public void setCoreDashboardCadreRegistrationService(
			ICoreDashboardCadreRegistrationService coreDashboardCadreRegistrationService) {
		this.coreDashboardCadreRegistrationService = coreDashboardCadreRegistrationService;
	}
	
	
	public String getPropIdsStr() {
		return propIdsStr;
	}

	public void setPropIdsStr(String propIdsStr) {
		this.propIdsStr = propIdsStr;
	}
   public List<CadreReportVO> getCadreDtlsList() {
		return cadreDtlsList;
	}

	public void setCadreDtlsList(List<CadreReportVO> cadreDtlsList) {
		this.cadreDtlsList = cadreDtlsList;
	}
 	//Implementation method
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public List<CoreDebateVO> getCodeDebateVoList() {
		return codeDebateVoList;
	}

	public void setCodeDebateVoList(List<CoreDebateVO> codeDebateVoList) {
		this.codeDebateVoList = codeDebateVoList;
	}
	
	public INewsCoreDashBoardService getNewsCoreDashBoardService() {
		return newsCoreDashBoardService;
	}

	public void setNewsCoreDashBoardService(
			INewsCoreDashBoardService newsCoreDashBoardService) {
		this.newsCoreDashBoardService = newsCoreDashBoardService;
	}
	
	public void setIdNameVO(IdNameVO idNameVO) {
		this.idNameVO = idNameVO;
	}
	  

	public IdNameVO getIdNameVO() {
		return idNameVO;
	}
	
	
	public List<List<IdNameVO>> getIdNameVOsList() {
		return idNameVOsList;
	}

	public void setIdNameVOsList(List<List<IdNameVO>> idNameVOsList) {
		this.idNameVOsList = idNameVOsList;
	}
	
	
   public ICoreDashboardPartyMeetingService getCoreDashboardPartyMeetingService() {
		return coreDashboardPartyMeetingService;
	}

	public void setCoreDashboardPartyMeetingService(
			ICoreDashboardPartyMeetingService coreDashboardPartyMeetingService) {
		this.coreDashboardPartyMeetingService = coreDashboardPartyMeetingService;
	}
	
	

	public List<IdNameVO> getIdNameVoList() {
		return idNameVoList;
	}

	public void setIdNameVoList(List<IdNameVO> idNameVoList) {
		this.idNameVoList = idNameVoList;
	}
	public List<ChildUserTypeVO> getChildUserTypeVOList() {
		return childUserTypeVOList;
	}

	public void setChildUserTypeVOList(List<ChildUserTypeVO> childUserTypeVOList) {
		this.childUserTypeVOList = childUserTypeVOList;
	}
   public void setCoreDashboardEventsActivitiesService(
			ICoreDashboardEventsActivitiesService coreDashboardEventsActivitiesService) {
		this.coreDashboardEventsActivitiesService = coreDashboardEventsActivitiesService;
	}
   	public List<EventDetailsVO> getEventDetailsVOList() {
	return eventDetailsVOList;
   }

	public void setEventDetailsVOList(List<EventDetailsVO> eventDetailsVOList) {
		this.eventDetailsVOList = eventDetailsVOList;
	}
	
	public IAttendanceCoreDashBoardService getAttendanceCoreDashBoardService() {
		return attendanceCoreDashBoardService;
	}

	public void setAttendanceCoreDashBoardService(
			IAttendanceCoreDashBoardService attendanceCoreDashBoardService) {
		this.attendanceCoreDashBoardService = attendanceCoreDashBoardService;
	}
  public EventDetailsVO getEventDetailsVO() {
		return eventDetailsVO;
	}

	public void setEventDetailsVO(EventDetailsVO eventDetailsVO) {
		this.eventDetailsVO = eventDetailsVO;
	}
	
	public List<HolidayListVO> getHolidayListVOs() {
		return holidayListVOs;
	}

	public void setHolidayListVOs(List<HolidayListVO> holidayListVOs) {
		this.holidayListVOs = holidayListVOs;
	}
	
	public CadreRegistratedCountVO getCadreRegistratedCountVO() {
		return cadreRegistratedCountVO;
	}

	public void setCadreRegistratedCountVO(
			CadreRegistratedCountVO cadreRegistratedCountVO) {
		this.cadreRegistratedCountVO = cadreRegistratedCountVO;
	}
	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	public String getEdiDistIdsStr() {
		return ediDistIdsStr;
	}

	public void setEdiDistIdsStr(String ediDistIdsStr) {
		this.ediDistIdsStr = ediDistIdsStr;
	}
	
	public Long getConstId() {
		return constId;
	}

	public void setConstId(Long constId) {
		this.constId = constId;
	}

	public String getConstName() {
		return constName;
	}

	public void setConstName(String constName) {
		this.constName = constName;
	}
	
	public List<IdNameVO> getIdNameVOs() {
		return idNameVOs;
	}

	public void setIdNameVOs(List<IdNameVO> idNameVOs) {
		this.idNameVOs = idNameVOs;
	}
   public void setCoreDashboardToursService(ICoreDashboardToursService coreDashboardToursService) {
		this.coreDashboardToursService = coreDashboardToursService;
	}
  	public ToursBasicVO getToursBasicVO() {
	return toursBasicVO;
    }
  	public void setToursBasicVO(ToursBasicVO toursBasicVO) {
	this.toursBasicVO = toursBasicVO;
   }
   public List<ToursBasicVO> getToursDtlsList() {
		return toursDtlsList;
	}

	public void setToursDtlsList(List<ToursBasicVO> toursDtlsList) {
		this.toursDtlsList = toursDtlsList;
	}
 	public CadreBasicVO getCadreVO() {
		return cadreVO;
	}

	public void setCadreVO(CadreBasicVO cadreVO) {
		this.cadreVO = cadreVO;
	}
    	
	public ResultStatus getPartyMeetingStatus() {
		return partyMeetingStatus;
	}

	public void setPartyMeetingStatus(ResultStatus partyMeetingStatus) {
		this.partyMeetingStatus = partyMeetingStatus;
	}
   	public AlertOverviewVO getAlertOverviewVO() {
		return alertOverviewVO;
	}

	public void setAlertOverviewVO(AlertOverviewVO alertOverviewVO) {
		this.alertOverviewVO = alertOverviewVO;
	}

	public void setAlertService(IAlertService alertService) {
		this.alertService = alertService;
	}
	public BoothInchargesVO getBoothInchargeVo() {
		return boothInchargeVo;
	}

	public void setBoothInchargeVo(BoothInchargesVO boothInchargeVo) {
		this.boothInchargeVo = boothInchargeVo;
	}
    public ToursOverviewDtlsvO getTourOverviewDtlsVO() {
		return tourOverviewDtlsVO;
	}

	public void setTourOverviewDtlsVO(ToursOverviewDtlsvO tourOverviewDtlsVO) {
		this.tourOverviewDtlsVO = tourOverviewDtlsVO;
	}

	public List<ToursOverviewDtlsvO> getTourOverviewDtlsList() {
		return tourOverviewDtlsList;
	}

	public void setTourOverviewDtlsList(List<ToursOverviewDtlsvO> tourOverviewDtlsList) {
		this.tourOverviewDtlsList = tourOverviewDtlsList;
	}

	public IKaizalaInfoService getKaizalaInfoService() {
		return kaizalaInfoService;
	}

	public void setKaizalaInfoService(IKaizalaInfoService kaizalaInfoService) {
		this.kaizalaInfoService = kaizalaInfoService;
	}

	public List<KaizalaDashboardVO> getKaizalaDashboardList() {
		return kaizalaDashboardList;
	}

	public void setKaizalaDashboardList(
			List<KaizalaDashboardVO> kaizalaDashboardList) {
		this.kaizalaDashboardList = kaizalaDashboardList;
	}
	

	public List<TrainingCampSurveyVO> getCampSurveyVOs() {
		return campSurveyVOs;
	}

	public void setCampSurveyVOs(List<TrainingCampSurveyVO> campSurveyVOs) {
		this.campSurveyVOs = campSurveyVOs;
	}
	public List<KeyValueVO> getKeyValueVOList() {
		return keyValueVOList;
	}

	public void setKeyValueVOList(List<KeyValueVO> keyValueVOList) {
		this.keyValueVOList = keyValueVOList;
	}

	//business methods
	public String execute(){
		try {
			
			final HttpSession session = request.getSession();
			
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
		}catch(Exception e) {
			LOG.error("Exception raised at execute() in CoreDashBoard Action class", e);
		}
		return Action.SUCCESS;
	}
	public String sessionCheckingForCoreDashBoard(){
		try {
			
			final HttpSession session = request.getSession();
			
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			Long registrationId = null;
			/*if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}*/
			
			if(user != null && user.getRegistrationID() != null){
				registrationId = user.getRegistrationID();
			}
			else
				registrationId = 1L;
			
			userDataVO = coreDashboardService.getUserBasicDetails(registrationId);
			List<Long> diptIdList = attendanceCoreDashBoardService.getDeptIds();
			userDataVO.setDeptIdList(diptIdList);
			List<UserDataVO> committeeDataVOList = coreDashboardMainService.getbasicCommitteeDetails();
			if(committeeDataVOList!=null && committeeDataVOList.size()>0){
				userDataVO.setSubList(committeeDataVOList);
			}
			 List<AlertOverviewVO> resultList = alertService.getAlertStatus(1l);
			 List<AlertOverviewVO> enrollementList = alertService.getTdpCadreEnrollementYearIds();
			 if(resultList != null && resultList.size() > 0){
				 alertOverviewVO.getSubList().addAll(resultList);	 
			 }
			 if (enrollementList != null && enrollementList.size() > 0) {
				 alertOverviewVO.setEnrollementIdList(enrollementList);
			 }
		}catch(Exception e) {
			LOG.error("Exception raised at execute() in CoreDashBoard Action class", e);
		}
		return Action.SUCCESS;
	}
	
	public String newCoreDashboardPage(){
		try {
			
			final HttpSession session = request.getSession();
			
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			Long registrationId = null;
			if(user == null || user.getRegistrationID() == null)
				return ERROR;
			
			
			registrationId = user.getRegistrationID();
			
			boolean noaccess = false;
			List<String> entitlements = null;
			if(user.getEntitlements() != null && user.getEntitlements().size()>0){
				entitlements = user.getEntitlements();
				if(!(entitlements.contains("CORE_DASHBOARD_USER") || entitlements.contains("CORE_DASHBOARD_ADMIN_USER"))){
					noaccess = true ;
				}
			}
			
			if(noaccess)
				return "error";
			
			userDataVO = coreDashboardService.getUserBasicDetails(registrationId);
			List<Long> diptIdList = attendanceCoreDashBoardService.getDeptIds();
			userDataVO.setDeptIdList(diptIdList);
			List<UserDataVO> committeeDataVOList = coreDashboardMainService.getbasicCommitteeDetails();
			if(committeeDataVOList!=null && committeeDataVOList.size()>0)
				userDataVO.setSubList(committeeDataVOList);
		}catch(Exception e) {
			LOG.error("Exception raised at execute() in CoreDashBoard Action class", e);
		}
		return Action.SUCCESS;
	}
	
	public String getUserBasicDetails(){
		try{
			LOG.info("Entered into getUserBasicDetails()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			userDataVO = coreDashboardService.getUserBasicDetails(jObj.getLong("userId"));
			
		}catch(Exception e){
			LOG.error("Exception raised at getUserBasicDetails() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getCommitteesCumulativeBasicReportChart(){
		try{
			jObj = new JSONObject(getTask());
			
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			String state = jObj.getString("state");
			Long basicCommitteeId = jObj.getLong("basicCommitteeId");
			String startDateString = jObj.getString("startDateString");
			String endDateString = jObj.getString("endDateString");
			
			committeeVO = coreDashboardService.getCommitteesCumulativeBasicReportChart(userAccessLevelId,userAccessLevelValues,state,basicCommitteeId,startDateString,endDateString);
		}catch(Exception e){
			LOG.error("Exception raised at getMainCommitteeCountDetails() method of coreDashboardAction", e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getCommitteesCumulativeOverallReportCharts(){
		try{
			LOG.info("Entered into getCommitteesCumulaticeOverallReportCharts()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			String state = jObj.getString("state");
			
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			
			String startDateString = jObj.getString("startDateString");
			String endDateString = jObj.getString("endDateString");
			
			committeeVOList = coreDashboardService.getCommitteesCumulativeOverallReportCharts(userAccessLevelId,userAccessLevelValues,state,basicCommitteeIds,startDateString,endDateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteesCumulaticeOverallReportCharts() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getCommitteesComparativeBascicReportChart(){
		try{
			LOG.info("Entered into getCommitteesComparativeBascicReportChart()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			String state = jObj.getString("state");
			
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			
			String firstMonthString = jObj.getString("firstMonthString");
			String secondMonthString = jObj.getString("secondMonthString");
			
			committeeVOList = coreDashboardService.getCommitteesComparativeBascicReportChart(userAccessLevelId,userAccessLevelValues,state,basicCommitteeIds,firstMonthString,secondMonthString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteesComparativeBascicReportChart() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getCommitteesComparativeOverallReportChart(){
		try{
			LOG.info("Entered into levelWiseComparativeCountsByBasicCommittees()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			String state = jObj.getString("state");
			
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			
			String firstMonthString = jObj.getString("firstMonthString");
			String secondMonthString = jObj.getString("secondMonthString");
			
			committeeVOList = coreDashboardService.getCommitteesComparativeOverallReportChart(userAccessLevelId,userAccessLevelValues,state,basicCommitteeIds,firstMonthString,secondMonthString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteesComparativeOverallReportChart() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getLoggedInUserStructure(){
		try{
			LOG.info("Entered into getLoggedInUserStructure()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			userTypeVO = coreDashboardService.getLoggedInUserStructure(jObj.getLong("userId"));
			
		}catch(Exception e){
			LOG.error("Exception raised at getLoggedInUserStructure() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getDistrictWiseCommitteesCountReport(){
		try{
			LOG.info("Entered into getDistrictWiseCommitteesCountReport()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			String state = jObj.getString("state");
			
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			
			String startDateString = jObj.getString("startDateString");
			String endDateString = jObj.getString("endDateString");
			
			committeeDataVOList = coreDashboardService1.getDistrictWiseCommitteesCountReport(state,basicCommitteeIds,startDateString,endDateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getDistrictWiseCommitteesCountReport() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	///////////////////COREDASHBOARD COMMITTEES////////////////////////////////////
	
	
	public Map<Long,List<Long>> getLevelWiseBasicCommittees(JSONObject jObj){
		
		Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = new HashMap<Long, List<Long>>(0);
		try{
			
			JSONArray levelWiseBasicCommitteesArray = jObj.getJSONArray("levelWiseBasicCommitteesArray");
			
			if(levelWiseBasicCommitteesArray!=null &&  levelWiseBasicCommitteesArray.length()>0){
				
				for(int i=0;i<levelWiseBasicCommitteesArray.length();i++){
					
						JSONObject tdpCommitteeLevelObject= levelWiseBasicCommitteesArray.getJSONObject(i);
						
						JSONArray basicCommitteeIdsArray = tdpCommitteeLevelObject.getJSONArray("basicCommitteeIds");
						
						if(basicCommitteeIdsArray!=null && basicCommitteeIdsArray.length()>0){
							
							List<Long> committeeIds = new ArrayList<Long>();
							
							for(int j=0;j<basicCommitteeIdsArray.length();j++){
								
								committeeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(j)));
							}
							committeeLevelBasedCommitteeIdsMap.put(tdpCommitteeLevelObject.getLong("committeeLevelId"), committeeIds);
						}
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at getLevelWiseBasicCommittees() method of CoreDashBoard", e);
		}
		return committeeLevelBasedCommitteeIdsMap;
	}
	public String getCommitteesBasicCountReport(){
		
		try{
			LOG.info("Entered into getCommitteesBasicCountReport()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			List<Long> committeeEnrollmentYearsIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentYearArray =jObj.getJSONArray("committeeEnrollmentYearArray");
			if(committeeEnrollmentYearArray!=null &&  committeeEnrollmentYearArray.length()>0){
				for( int i=0;i<committeeEnrollmentYearArray.length();i++){
					committeeEnrollmentYearsIdsLst.add(Long.valueOf(committeeEnrollmentYearArray.getString(i)));
				}
			}
			
			String state = jObj.getString("state");
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			String dateString = jObj.getString("dateString");
			
			
			committeeDataVO = coreDashboardMainService.getCommitteesBasicCountReport(userAccessLevelId,userAccessLevelValues,state,committeeLevelBasedCommitteeIdsMap,dateString,committeeEnrollmentYearsIdsLst);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteesBasicCountReport() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String getUserTypeWiseCommitteesCompletedCounts(){
		
		try{
			LOG.info("Entered into getUserTypeWiseCommitteesCompletedCounts()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			
			Long userId = jObj.getLong("userId");
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long userTypeId = jObj.getLong("userTypeId");
			
			String state = jObj.getString("state");
			
			List<Long> basicCommitteeIds = new ArrayList<Long>();
			JSONArray basicCommitteeIdsArray=jObj.getJSONArray("basicCommitteeIdsArray");
			if(basicCommitteeIdsArray!=null &&  basicCommitteeIdsArray.length()>0){
				for( int i=0;i<basicCommitteeIdsArray.length();i++){
					basicCommitteeIds.add(Long.valueOf(basicCommitteeIdsArray.getString(i)));
				}
			}
			
			String startDateString = jObj.getString("startDateString");
			String endDateString = jObj.getString("endDateString");
			
			//userTypeVOList = coreDashboardService1.getUserTypeWiseCommitteesCompletedCounts(userId,activityMemberId,userTypeId,state,basicCommitteeIds,startDateString,endDateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getUserTypeWiseCommitteesCompletedCounts() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String getUserTypeWiseCommitteesCompletedCounts1(){
		LOG.info("Entered into getUserTypeWiseCommitteesCompletedCounts1()  of CoreDashboardAction");
		try{
			
			 Long userId = null; 
			 HttpSession session = request.getSession();
			 RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			 if(user == null || user.getRegistrationID() == null){
				//return ERROR;
				 userId = 1L;
			 }
			 else
				 userId = user.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long userTypeId = jObj.getLong("userTypeId");
			String committeType= jObj.getString("commiteType");
			String state = jObj.getString("state");
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
	
			String dateString = jObj.getString("dateString");
			List<Long> committeeEnrollmentYearsIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentYearArray =jObj.getJSONArray("committeeEnrollmentYearArray");
			if(committeeEnrollmentYearArray!=null &&  committeeEnrollmentYearArray.length()>0){
				for( int i=0;i<committeeEnrollmentYearArray.length();i++){
					committeeEnrollmentYearsIdsLst.add(Long.valueOf(committeeEnrollmentYearArray.getString(i)));
				}
			}
			
			userTypeVOList = coreDashboardMainService.getUserTypeWiseCommitteesCompletedCounts1(userId,activityMemberId,userTypeId,state,committeeLevelBasedCommitteeIdsMap,dateString,committeeEnrollmentYearsIdsLst,committeType);
		}catch(Exception e){
			LOG.error("Exception raised at getUserTypeWiseCommitteesCompletedCounts1() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String getLevelWiseBasicCommitteesCountReport(){
		
		try{
			LOG.info("Entered into getLevelWiseBasicCommitteesCountReport()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			String state = jObj.getString("state");
			String committeType= jObj.getString("commiteType"); 
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			String dateString = jObj.getString("dateString");
			
			List<Long> committeeEnrollmentYearsIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentYearArray =jObj.getJSONArray("committeeEnrollmentYearArray");
			if(committeeEnrollmentYearArray!=null &&  committeeEnrollmentYearArray.length()>0){
				for( int i=0;i<committeeEnrollmentYearArray.length();i++){
					committeeEnrollmentYearsIdsLst.add(Long.valueOf(committeeEnrollmentYearArray.getString(i)));
				}
			}
			committeeDataVOList = coreDashboardMainService.getLevelWiseBasicCommitteesCountReport(userAccessLevelId,userAccessLevelValues,state,committeeLevelBasedCommitteeIdsMap,dateString,committeeEnrollmentYearsIdsLst,committeType);
			
		}catch(Exception e){
			LOG.error("Exception raised at getLevelWiseBasicCommitteesCountReport() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String committeesPerformanceCohort(){
		try{
			LOG.info("Entered into committeesPerformanceCohort()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			
			
			List<Long> tdpCommitteeLevelIdsClicked = new ArrayList<Long>();
			JSONArray tdpCommitteeLevelIdsClickedArray=jObj.getJSONArray("tdpCommitteeLevelIdsClickedArray");
			if(tdpCommitteeLevelIdsClickedArray!=null &&  tdpCommitteeLevelIdsClickedArray.length()>0){
				for( int i=0;i<tdpCommitteeLevelIdsClickedArray.length();i++){
					tdpCommitteeLevelIdsClicked.add(Long.valueOf(tdpCommitteeLevelIdsClickedArray.getString(i)));
				}
			}
			
			
			String committeeStatus = jObj.getString("committeeStatus");
			Long userLocationLevelId = jObj.getLong("userLocationLevelId");
			String committeType= jObj.getString("commiteType"); 
			
			List<Long> userLocationLevelValues = new ArrayList<Long>();
			JSONArray userLocationLevelValuesArray=jObj.getJSONArray("userLocationLevelValuesArray");
			if(userLocationLevelValuesArray!=null &&  userLocationLevelValuesArray.length()>0){
				for( int i=0;i<userLocationLevelValuesArray.length();i++){
					userLocationLevelValues.add(Long.valueOf(userLocationLevelValuesArray.getString(i)));
				}
			}
			  
			String dateString = jObj.getString("dateString");
			String state = jObj.getString("state");
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			List<Long> committeeEnrollmentYearsIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentYearArray =jObj.getJSONArray("committeeEnrollmentYearArray");
			if(committeeEnrollmentYearArray!=null &&  committeeEnrollmentYearArray.length()>0){
				for( int i=0;i<committeeEnrollmentYearArray.length();i++){
					committeeEnrollmentYearsIdsLst.add(Long.valueOf(committeeEnrollmentYearArray.getString(i)));
				}
			}
			committeeDataVOList = coreDashboardMainService.committeesPerformanceCohort(tdpCommitteeLevelIdsClicked,committeeLevelBasedCommitteeIdsMap,committeeStatus,userLocationLevelId,userLocationLevelValues,dateString,state,committeeEnrollmentYearsIdsLst,committeType);
			
		}catch(Exception e){
			LOG.error("Exception raised at committeesPerformanceCohort() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	
	}
	public String getChildUserTypesByItsParentUserType(){
		try{
			
			jObj = new JSONObject(getTask());
			userDataVOList = coreDashboardGenericService.getChildUserTypesByItsParentUserType(jObj.getLong("parentUserTypeId"));
			
		}catch(Exception e){
			LOG.error("Exception raised at getChildUserTypesByItsParentUserType() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String getAllItsSubUserTypeIdsByParentUserTypeId(){
		try{
			
			jObj = new JSONObject(getTask());
			List<Long> userTypeIdsList = new ArrayList<Long>(0);
			try {
				JSONArray idArr = jObj.getJSONArray("parentUserTypeId");
				if(idArr != null && idArr.length()>0){
					for (int i = 0; i < idArr.length(); i++) {
						userTypeIdsList.add(idArr.get(i) != null ? Long.valueOf(idArr.get(i).toString()):0L);
					}
				}
				//activityMembersList = coreDashboardGenericService.getAllItsSubUserTypeIdsByParentUserTypeId(jObj.getLong("parentUserTypeId"));
			} catch (Exception e) {
				userTypeIdsList.add(jObj.getLong("parentUserTypeId"));
			}
			activityMembersList = coreDashboardGenericService.getAllItsSubUserTypeIdsByParentUserTypeId(userTypeIdsList);
		}catch(Exception e){
			LOG.error("Exception raised at getAllItsSubUserTypeIdsByParentUserTypeId() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getSelectedChildUserTypeMembers(){
		LOG.info("Entered into getSelectedChildUserTypeMembers()  of CoreDashboardAction");
		try{
			
			jObj = new JSONObject(getTask());
			
			Long parentActivityMemberId = jObj.getLong("parentActivityMemberId");
			
			List<Long> childUserTypeIds=new ArrayList<Long>();
			JSONArray childUserTypeIdsArray=jObj.getJSONArray("childUserTypeIdsArray");
			if(childUserTypeIdsArray!=null &&  childUserTypeIdsArray.length()>0){
				for( int i=0;i<childUserTypeIdsArray.length();i++){
					childUserTypeIds.add(Long.valueOf(childUserTypeIdsArray.getString(i)));
				}
			}
			
			String state = jObj.getString("state");
			String committeType= jObj.getString("commiteType");
			
			String dateString = jObj.getString("dateString");
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			List<Long> committeeEnrollmentYearsIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentYearArray =jObj.getJSONArray("committeeEnrollmentYearArray");
			if(committeeEnrollmentYearArray!=null &&  committeeEnrollmentYearArray.length()>0){
				for( int i=0;i<committeeEnrollmentYearArray.length();i++){
					committeeEnrollmentYearsIdsLst.add(Long.valueOf(committeeEnrollmentYearArray.getString(i)));
				}
			}
			activityMembersList = coreDashboardMainService.getSelectedChildUserTypeMembers(parentActivityMemberId,childUserTypeIds,state,committeeLevelBasedCommitteeIdsMap,dateString,committeeEnrollmentYearsIdsLst,committeType);
			
		}catch(Exception e){
			LOG.error("Exception raised at getSelectedChildUserTypeMembers() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String getDirectChildActivityMemberCommitteeDetails(){
		LOG.info("Entered into getDirectChildActivityMemberCommitteeDetails()  of CoreDashboardAction");
		try{
			
			jObj = new JSONObject(getTask());
			
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long userTypeId = jObj.getLong("userTypeId");
			
			String state = jObj.getString("state");
			
			String dateString = jObj.getString("dateString");
			String committeType= jObj.getString("commiteType");
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			List<Long> committeeEnrollmentYearsIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentYearArray =jObj.getJSONArray("committeeEnrollmentYearArray");
			if(committeeEnrollmentYearArray!=null &&  committeeEnrollmentYearArray.length()>0){
				for( int i=0;i<committeeEnrollmentYearArray.length();i++){
					committeeEnrollmentYearsIdsLst.add(Long.valueOf(committeeEnrollmentYearArray.getString(i)));
				}
			}
			activityMembersList = coreDashboardMainService.getDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId,state,committeeLevelBasedCommitteeIdsMap,dateString,committeeEnrollmentYearsIdsLst,committeType);
			
		}catch(Exception e){
			LOG.error("Exception raised at getDirectChildActivityMemberCommitteeDetails() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
public String getTopPoorPerformancecommittees(){
		LOG.info("Entered into getTopPoorPerformancecommittees()  of CoreDashboardAction");
		try{
			
			jObj = new JSONObject(getTask());
			
			Long activityMemberId = jObj.getLong("activityMemberId");
			
			String state = jObj.getString("state");
			String committeType = jObj.getString("commiteType");
			String dateString = jObj.getString("dateString");
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			List<Long> committeeEnrollmentYearsIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentYearArray =jObj.getJSONArray("committeeEnrollmentYearArray");
			if(committeeEnrollmentYearArray!=null &&  committeeEnrollmentYearArray.length()>0){
				for( int i=0;i<committeeEnrollmentYearArray.length();i++){
					committeeEnrollmentYearsIdsLst.add(Long.valueOf(committeeEnrollmentYearArray.getString(i)));
				}
			}
			committeeDataVO = coreDashboardMainService.getTopPoorPerformancecommittees(activityMemberId,committeeLevelBasedCommitteeIdsMap,state,dateString,committeeEnrollmentYearsIdsLst,committeType);
			
		}catch(Exception e){
			LOG.error("Exception raised at getTopPoorPerformancecommittees() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
}	
	public String getTopPoorCommitteeLocations(){
		LOG.info("Entered into getTopPoorCommitteeLocations()  of CoreDashboardAction");
		try{
			
			jObj = new JSONObject(getTask());
			
			Long activityMemberId = jObj.getLong("activityMemberId");
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			String state = jObj.getString("state");
			String dateString = jObj.getString("dateString");
			String committeType= jObj.getString("commiteType");
			
			List<Long> committeeEnrollmentYearsIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentYearArray =jObj.getJSONArray("committeeEnrollmentYearArray");
			if(committeeEnrollmentYearArray!=null &&  committeeEnrollmentYearArray.length()>0){
				for( int i=0;i<committeeEnrollmentYearArray.length();i++){
					committeeEnrollmentYearsIdsLst.add(Long.valueOf(committeeEnrollmentYearArray.getString(i)));
				}
			}
			committeeDataVOList = coreDashboardMainService.getTopPoorCommitteeLocations(activityMemberId,committeeLevelBasedCommitteeIdsMap,state,dateString,committeeEnrollmentYearsIdsLst,committeType);
			
		}catch(Exception e){
			LOG.error("Exception raised at getTopPoorCommitteeLocations() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
  public String getTrainingCampBasicDetailsCntOverview(){
	  try{
			LOG.info("Entered into getTotalEligibleAttendedAndNotAttenedOverviewCount()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			
			List<Long> programIdList = new ArrayList<Long>();
			JSONArray programIdArr=jObj.getJSONArray("programIdArr");  
			if(programIdArr!=null &&  programIdArr.length()>0){
				for( int i=0;i<programIdArr.length();i++){ 
					programIdList.add(Long.valueOf(programIdArr.getString(i)));
				}
			}
			
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			Long stateId = jObj.getLong("stateId");
			String dateStr = jObj.getString("dateStr");
			List<Long> enrollmentYearIds=new ArrayList<Long>();
			JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
			if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
				for( int i=0;i<enrollmentYearIdsArray.length();i++){
					enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
				}
			}
			trainingCampProgramVO = coreDashboardMainService.getTrainingCampBasicDetailsCntOverview(userAccessLevelId,userAccessLevelValues,stateId,dateStr,enrollmentYearIds,programIdList);
			
		}catch(Exception e){
			LOG.error("Exception raised at getTotalEligibleAttendedAndNotAttenedOverviewCount() method of CoreDashBoardAction", e);
		}
		return Action.SUCCESS;
  }
  public String getTrainingCampProgramsDetailsCntByUserType(){
	  try{
			LOG.info("Entered into getTotalEligibleAttendedAndNotAttenedOverviewCount()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			Long stateId = jObj.getLong("stateId");
			String dateStr = jObj.getString("dateStr");
			Long userTypeId = jObj.getLong("userTypeId");
			Long activityMemberId = jObj.getLong("activityMemberId");
			List<Long> enrollmentYearIds=new ArrayList<Long>();
			JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
			if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
				for( int i=0;i<enrollmentYearIdsArray.length();i++){
					enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
				}
			}
			List<Long> trainingCampProgramIds=new ArrayList<Long>();
			JSONArray trainingCampProgramIdArr=jObj.getJSONArray("trainingCampProgramIds");
			if(trainingCampProgramIdArr!=null &&  trainingCampProgramIdArr.length()>0){
				for( int i=0;i<trainingCampProgramIdArr.length();i++){
					trainingCampProgramIds.add(Long.valueOf(trainingCampProgramIdArr.getString(i)));
				}
			}
			trainingCampProgramVOList = coreDashboardMainService.getTrainingCampProgramsDetailsCntByUserType(userAccessLevelId,userAccessLevelValues,stateId,dateStr,userTypeId,activityMemberId,enrollmentYearIds,trainingCampProgramIds);
		}catch(Exception e){
			LOG.error("Exception raised at getTotalEligibleAttendedAndNotAttenedOverviewCount() method of CoreDashBoardAction", e);
		}
		return Action.SUCCESS;
  }
 public String getUserTypeWiseTotalEligibleAndAttendedCnt(){
	 
	 try{
		 
		 jObj = new JSONObject(getTask());
		 
		   final HttpSession session = request.getSession();
			
		   Long userId = null;
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				userId = 1L;
			}
			else
				userId = user.getRegistrationID();
		    //Long userId = jObj.getLong("userId");
			
			List<Long> programIdList = new ArrayList<Long>();
			JSONArray programIdArr=jObj.getJSONArray("programIdArr");  
			if(programIdArr!=null &&  programIdArr.length()>0){
				for( int i=0;i<programIdArr.length();i++){ 
					programIdList.add(Long.valueOf(programIdArr.getString(i)));
				}
			}
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long userTypeId = jObj.getLong("userTypeId");
		    Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			
			List<Long> enrollmentYrIds=new ArrayList<Long>();
			JSONArray enrollmentYrIdsArray=jObj.getJSONArray("enrollmentYrIds");
			if(enrollmentYrIdsArray!=null &&  enrollmentYrIdsArray.length()>0){
				for( int i=0;i<enrollmentYrIdsArray.length();i++){
					enrollmentYrIds.add(Long.valueOf(enrollmentYrIdsArray.getString(i)));
				}
			}
			Long stateId = jObj.getLong("stateId");
			String dateStr = jObj.getString("dateStr");
			userTypeVOList = coreDashboardMainService.getUserTypeWiseTotalEligibleAndAttendedCnt(userId,userTypeId,activityMemberId,userAccessLevelId,userAccessLevelValues,stateId,dateStr,enrollmentYrIds,programIdList);
	 }catch(Exception e){
		 LOG.error("Exception raised at getUserTypeWiseTotalEligibleAndAttendedCnt() method of CoreDashBoardAction", e); 
	 }
	 return Action.SUCCESS;
 }
public String getSelectedChildTypeMembersForTrainingProgram(){
	try{
		 
		    jObj = new JSONObject(getTask());
		 
		 	Long parentActivityMemberId = jObj.getLong("parentActivityMemberId");
		    Long userAccessLevelId = jObj.getLong("userAccessLevelId");
		    String reportType = jObj.getString("reportType");
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			List<Long> childUserTypeIds=new ArrayList<Long>();
			JSONArray childUserTypeIdsArray=jObj.getJSONArray("childUserTypeIdsArray");
			if(childUserTypeIdsArray!=null &&  childUserTypeIdsArray.length()>0){
				for( int i=0;i<childUserTypeIdsArray.length();i++){
					childUserTypeIds.add(Long.valueOf(childUserTypeIdsArray.getString(i)));
				}
			}
			
			Long stateId = jObj.getLong("stateId");
			String dateStr = jObj.getString("dateStr");
			List<Long> enrollmentYearIds=new ArrayList<Long>();
			JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
			if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
				for( int i=0;i<enrollmentYearIdsArray.length();i++){
					enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
				}
			}
			
			List<Long> programIdList = new ArrayList<Long>();
			JSONArray programIdArr=jObj.getJSONArray("programIdArr");  
			if(programIdArr!=null &&  programIdArr.length()>0){
				for( int i=0;i<programIdArr.length();i++){ 
					programIdList.add(Long.valueOf(programIdArr.getString(i)));
				}
			}
			activityMembersList = coreDashboardMainService.getSelectedChildTypeMembersForTrainingProgram(parentActivityMemberId,childUserTypeIds,userAccessLevelId,userAccessLevelValues,reportType,stateId,dateStr,enrollmentYearIds,programIdList);
	 }catch(Exception e){
		 LOG.error("Exception raised at getSelectedChildTypeMembersForTrainingProgram() method of CoreDashBoardAction", e); 
	 }
	 return Action.SUCCESS;
}
public String getDirectChildActivityTrainingProgramMemberDetails(){
	try{
		 
		    jObj = new JSONObject(getTask());
		 
		 	Long activityMemberId = jObj.getLong("activityMemberId");
		//	Long userTypeId = jObj.getLong("userTypeId");
			 String reportType = jObj.getString("reportType");
			 Long stateId = jObj.getLong("stateId");
			 String dateStr = jObj.getString("dateStr");
			List<Long> childUserTypeIds=new ArrayList<Long>();
			JSONArray childUserTypeIdsArray=jObj.getJSONArray("childUserTypeIdsArray");
			if(childUserTypeIdsArray!=null &&  childUserTypeIdsArray.length()>0){
				for( int i=0;i<childUserTypeIdsArray.length();i++){
					childUserTypeIds.add(Long.valueOf(childUserTypeIdsArray.getString(i)));
				}
			}
			List<Long> enrollmentYearIds=new ArrayList<Long>();
			JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
			if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
				for( int i=0;i<enrollmentYearIdsArray.length();i++){
					enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
				}
			}
			
			List<Long> programIdList = new ArrayList<Long>();
			JSONArray programIdArr=jObj.getJSONArray("programIdArr");  
			if(programIdArr!=null &&  programIdArr.length()>0){
				for( int i=0;i<programIdArr.length();i++){ 
					programIdList.add(Long.valueOf(programIdArr.getString(i)));
				}
			}
			
		 	activityMembersList = coreDashboardMainService.getSelectedChildTypeMembersForTrainingProgram(activityMemberId,childUserTypeIds,null,null,reportType,stateId,dateStr,enrollmentYearIds,programIdList);
	 }catch(Exception e){
		 LOG.error("Exception raised at getDirectChildActivityTrainingProgramMemberDetails() method of CoreDashBoardAction", e); 
	 }
	 return Action.SUCCESS;
}
public String getTrainingProgramPoorCompletedLocationDtls(){
	  try{
			LOG.info("Entered into getTrainingProgramPoorCompletedLocationDtls()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			List<Long> programIdList = new ArrayList<Long>();
			JSONArray programIdArr=jObj.getJSONArray("programIdArr");  
			if(programIdArr!=null &&  programIdArr.length()>0){
				for( int i=0;i<programIdArr.length();i++){ 
					programIdList.add(Long.valueOf(programIdArr.getString(i)));
				}
			}
			Long userTypeId = jObj.getLong("userTypeId");
			Long activityMemberId = jObj.getLong("activityMemberId");
			 Long stateId = jObj.getLong("stateId");
			 String dateStr = jObj.getString("dateStr");
			 List<Long> enrollmentYearIds=new ArrayList<Long>();
				JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
				if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
					for( int i=0;i<enrollmentYearIdsArray.length();i++){
						enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
					}
				}
			trainingCampProgramVO = coreDashboardMainService.getTrainingProgramPoorCompletedLocationDtls(userTypeId,activityMemberId,stateId,dateStr,enrollmentYearIds,programIdList);
		}catch(Exception e){
			LOG.error("Exception raised at getTrainingProgramPoorCompletedLocationDtls() method of CoreDashBoardAction", e);
		}
		return Action.SUCCESS;
}
public String getTrainingProgramBasicCnt(){
	
	try{
		 
	    jObj = new JSONObject(getTask());
	 
	     Long userAccessLevelId = jObj.getLong("userAccessLevelId");
		List<Long> userAccessLevelValues=new ArrayList<Long>();
		JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
		if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
			for( int i=0;i<userAccessLevelValuesArray.length();i++){
				userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
			}
		}
		 Long stateId = jObj.getLong("stateId");
		 String dateStr = jObj.getString("dateStr");
		 List<Long> enrollmentYearIds=new ArrayList<Long>();
			JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
			if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
				for( int i=0;i<enrollmentYearIdsArray.length();i++){
					enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
				}
			}
		trainingCampProgramVOList = coreDashboardMainService.getTrainingCampProgramsBasicCountDetails(userAccessLevelId,userAccessLevelValues,stateId,dateStr,enrollmentYearIds);
 }catch(Exception e){
	 LOG.error("Exception raised at getTrainingProgramBasicCnt() method of CoreDashBoardAction", e); 
 }
 return Action.SUCCESS;
}
//Debate Action Methods

public String getPartyWiseTotalDebateDetails(){
	
	try{
		jObj = new JSONObject(getTask());
		JSONArray debateLocationIdArry = jObj.getJSONArray("debateLocationIdArry");  
		List<Long> debateLocationIdList = new ArrayList<Long>();
		if(debateLocationIdArry != null && debateLocationIdArry.length() > 0){
			for (int i = 0; i < debateLocationIdArry.length(); i++){
				debateLocationIdList.add(Long.parseLong(debateLocationIdArry.getString(i)));          
			}  
		}
		JSONArray debateParticipantLocIdArry = jObj.getJSONArray("debateParticipantLocIdArry");  
		List<Long> debateParticipantLocationIdList = new ArrayList<Long>();
		if(debateParticipantLocIdArry != null && debateParticipantLocIdArry.length() > 0){
			for (int i = 0; i < debateParticipantLocIdArry.length(); i++){
				debateParticipantLocationIdList.add(Long.parseLong(debateParticipantLocIdArry.getString(i)));          
			}  
		}
		codeDebateVoList = coreDashboardMainService.getPartyWiseTotalDebateDetails(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"),debateLocationIdList,debateParticipantLocationIdList);
		
	}catch (Exception e) {
		LOG.error("Exception raised at getPartyWiseTotalDebateDetails() method of CoreDashBoardAction", e);
	}
	
	return Action.SUCCESS;
}
public String getSpokesPersonWiseDebate(){
	
	try{
		
		jObj = new JSONObject(getTask());
		JSONArray debateParticipantLocIdArry = jObj.getJSONArray("debateParticipantLocIdArry");  
		List<Long> debateParticipantLocationIdList = new ArrayList<Long>();
		if(debateParticipantLocIdArry != null && debateParticipantLocIdArry.length() > 0){
			for (int i = 0; i < debateParticipantLocIdArry.length(); i++){
				debateParticipantLocationIdList.add(Long.parseLong(debateParticipantLocIdArry.getString(i)));          
			}  
		}
		JSONArray debateLocationIdArry = jObj.getJSONArray("debateLocationIdArry");  
		List<Long> debateLocationIdList = new ArrayList<Long>();
		if(debateLocationIdArry != null && debateLocationIdArry.length() > 0){
			for (int i = 0; i < debateLocationIdArry.length(); i++){
				debateLocationIdList.add(Long.parseLong(debateLocationIdArry.getString(i)));          
			}  
		}
		codeDebateVoList = coreDashboardMainService.getSpokesPersonWiseDebate(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("searchType"),jObj.getString("state"),debateParticipantLocationIdList,debateLocationIdList);
		
	}catch (Exception e) {
		LOG.error("Exception raised at getSpokesPersonWiseDebate() method of CoreDashBoardAction", e);
	}
	
	return Action.SUCCESS;
}
public String getScaleBasedPerformanceCohort(){

	try{
		
		jObj = new JSONObject(getTask());
		JSONArray debateLocationIdArry = jObj.getJSONArray("debateLocationIdArry");  
		List<Long> debateLocationIdList = new ArrayList<Long>();
		if(debateLocationIdArry != null && debateLocationIdArry.length() > 0){
			for (int i = 0; i < debateLocationIdArry.length(); i++){
				debateLocationIdList.add(Long.parseLong(debateLocationIdArry.getString(i)));          
			}  
		}
		JSONArray debateParticipantLocationIdArray = jObj.getJSONArray("debateParticipantLocationIdArray");  
		List<Long> debateParticipantLocationIdList = new ArrayList<Long>();
		if(debateParticipantLocationIdArray != null && debateParticipantLocationIdArray.length() > 0){
			for (int i = 0; i < debateParticipantLocationIdArray.length(); i++){
				debateParticipantLocationIdList.add(Long.parseLong(debateParticipantLocationIdArray.getString(i)));          
			}  
		}
		codeDebateVoList = coreDashboardMainService.getScaleBasedPerformanceCohort(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"),debateLocationIdList,debateParticipantLocationIdList);
		
	}catch (Exception e) {
		LOG.error("Exception raised at getScaleBasedPerformanceCohort() method of CoreDashBoardAction", e);
	}
	return Action.SUCCESS;
}
public String getCandidateOverAllPerformanceCohort(){
	
	try{
		
		jObj = new JSONObject(getTask());
		JSONArray participantLocIdArry = jObj.getJSONArray("participantLocIdArry");  
		List<Long> participantLocationIdList = new ArrayList<Long>();
		if(participantLocIdArry != null && participantLocIdArry.length() > 0){
			for (int i = 0; i < participantLocIdArry.length(); i++){
				participantLocationIdList.add(Long.parseLong(participantLocIdArry.getString(i)));          
			}  
		}
		JSONArray debateLocationIdArry = jObj.getJSONArray("debateLocationIdArry");  
		List<Long> debateLocationIdList = new ArrayList<Long>();
		if(debateLocationIdArry != null && debateLocationIdArry.length() > 0){
			for (int i = 0; i < debateLocationIdArry.length(); i++){
				debateLocationIdList.add(Long.parseLong(debateLocationIdArry.getString(i)));          
			}  
		}
		codeDebateVoList = coreDashboardMainService.getCandidateOverAllPerformanceCohort(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"),participantLocationIdList,debateLocationIdList);
		
	}catch (Exception e) {
		LOG.error("Exception raised at getCandidateOverAllPerformanceCohort() method of CoreDashBoardAction", e);
	}
	
	return Action.SUCCESS;
}
public String getChannelAndPartyWiseDetails(){

	try{
		
		jObj = new JSONObject(getTask());
		JSONArray debateLocationIdArry = jObj.getJSONArray("debateLocationIdArry");  
		List<Long> debateLocationIdList = new ArrayList<Long>();
		if(debateLocationIdArry != null && debateLocationIdArry.length() > 0){
			for (int i = 0; i < debateLocationIdArry.length(); i++){
				debateLocationIdList.add(Long.parseLong(debateLocationIdArry.getString(i)));          
			}  
		}
		JSONArray debateParticipantLocIdArry = jObj.getJSONArray("debateParticipantLocIdArry");  
		List<Long> debateParticipantLocIdList = new ArrayList<Long>();
		if(debateParticipantLocIdArry != null && debateParticipantLocIdArry.length() > 0){
			for (int i = 0; i < debateParticipantLocIdArry.length(); i++){
				debateParticipantLocIdList.add(Long.parseLong(debateParticipantLocIdArry.getString(i)));          
			}  
		}
		//debateParticipantLocIdArry
		codeDebateVoList = coreDashboardMainService.getChannelAndPartyWiseDetails(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"),debateLocationIdList,debateParticipantLocIdList);
		
	}catch (Exception e) {
		LOG.error("Exception raised at getChannelAndPartyWiseDetails() method of CoreDashBoardAction", e);
	}
	return Action.SUCCESS;
}
public String getRoleBasedPerformanceCohort(){
	
	try{
		
		jObj = new JSONObject(getTask());
		JSONArray debateLocationIdArry = jObj.getJSONArray("debateLocationIdArry");  
		List<Long> debateLocationIdList = new ArrayList<Long>();
		if(debateLocationIdArry != null && debateLocationIdArry.length() > 0){
			for (int i = 0; i < debateLocationIdArry.length(); i++){
				debateLocationIdList.add(Long.parseLong(debateLocationIdArry.getString(i)));          
			}  
		}
		JSONArray debateParticipantLocationIdArray = jObj.getJSONArray("debateParticipantLocationIdArray");  
		List<Long> debateParticipantLocationIdList = new ArrayList<Long>();
		if(debateParticipantLocationIdArray != null && debateParticipantLocationIdArray.length() > 0){
			for (int i = 0; i < debateParticipantLocationIdArray.length(); i++){
				debateParticipantLocationIdList.add(Long.parseLong(debateParticipantLocationIdArray.getString(i)));          
			}  
		}
		codeDebateVoList = coreDashboardMainService.getRoleBasedPerformanceCohort(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"),debateLocationIdList,debateParticipantLocationIdList);
				
	}catch (Exception e) {
		LOG.error("Exception raised at getRoleBasedPerformanceCohort() method of CoreDashBoardAction", e);
	}
	
	return Action.SUCCESS;  
}

	
	public String getUserTypeWiseNewsCounts(){
		try {
			Long userId = 1L;
			
			//HttpSession session = request.getSession();
			
			/* RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			 if(user == null || user.getRegistrationID() == null){
				//return ERROR;
				 userId = 1L;
			 }else
				 userId = user.getRegistrationID();*/
			
			
			jObj = new JSONObject(getTask());
			
			List<Long> npIdsList = new ArrayList<Long>(0);
			
			if(jObj.getJSONArray("npIdsArr") != null && jObj.getJSONArray("npIdsArr").length()>0){
				for(int i=0;i<jObj.getJSONArray("npIdsArr").length();i++){
					npIdsList.add(Long.parseLong(jObj.getJSONArray("npIdsArr").getString(i)));
				}
			}
			
			List<Long> impactScopeIdsList = new ArrayList<Long>(0);
			
			if(jObj.getJSONArray("impactScopeIdsArr") != null && jObj.getJSONArray("impactScopeIdsArr").length()>0){
				for(int i=0;i<jObj.getJSONArray("impactScopeIdsArr").length();i++){
					impactScopeIdsList.add(Long.parseLong(jObj.getJSONArray("impactScopeIdsArr").getString(i)));
				}
			}
			
			userTypeVOList = newsCoreDashBoardService.getUserTypeWiseNewsCounts(userId,jObj.getLong("activityMemberId"),jObj.getLong("userTypeId"),
					jObj.getString("state"),jObj.getString("fromDate"),jObj.getString("toDate"),jObj.getLong("benefitId"),npIdsList,impactScopeIdsList);
		} catch (Exception e) {
			LOG.error("Exception raised at getUserTypeWiseNewsCounts", e);
		}
		return Action.SUCCESS;
	}
	
	public String getDistrictWiseCampAttendedMembers(){ 
		try {
			jObj = new JSONObject(getTask());
			List<Long> programIdList = new ArrayList<Long>();
			String dateStr = jObj.getString("dateStr");
			Long stateId = jObj.getLong("stateId");
			JSONArray programIdArr=jObj.getJSONArray("programIdArr");
			if(programIdArr!=null &&  programIdArr.length()>0){
				for( int i=0;i<programIdArr.length();i++){
					programIdList.add(Long.valueOf(programIdArr.getString(i))); 
				}
			}
			
			List<Long> enrollmentYrIds = new ArrayList<Long>();
			JSONArray enrollmentYrIdsArr=jObj.getJSONArray("enrollmentYrIds");
			if(enrollmentYrIdsArr!=null &&  enrollmentYrIdsArr.length()>0){
				for( int i=0;i<enrollmentYrIdsArr.length();i++){
					enrollmentYrIds.add(Long.valueOf(enrollmentYrIdsArr.getString(i))); 
				}
			}
			idNameVOsList = coreDashboardMainService.getDistrictWiseCampAttendedMembers(programIdList,stateId,dateStr,enrollmentYrIds);
		} catch (Exception e) {
			LOG.error("Exception raised at getDistrictWiseCampAttendedMembers", e);
		}
		return Action.SUCCESS; 
	}
	public String getPartyMeetingBasicCountDetails(){
		  try{
				LOG.info("Entered into getPartyMeetingBasicCountDetails()  of CoreDashboardAction");
				jObj = new JSONObject(getTask());
				Long activityMemberId = jObj.getLong("activityMemberId");
				Long stateId = jObj.getLong("stateId");
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				List<Long> partyMeetingTypeValues=new ArrayList<Long>();
				JSONArray partyMeetingTypeArray=jObj.getJSONArray("partyMeetingTypeArr");
				if(partyMeetingTypeArray!=null &&  partyMeetingTypeArray.length()>0){
					for( int i=0;i<partyMeetingTypeArray.length();i++){
						partyMeetingTypeValues.add(Long.valueOf(partyMeetingTypeArray.getString(i)));
					}
				}
				partyMeetingsVO = coreDashboardPartyMeetingService.getPartyMeetingBasicCountDetails(activityMemberId,stateId,fromDate,toDate,partyMeetingTypeValues);
			}catch(Exception e){
				LOG.error("Exception raised at getPartyMeetingBasicCountDetails() method of CoreDashBoardAction", e);
			}
			return Action.SUCCESS;
	}
	public String getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt(){
		  try{
				LOG.info("Entered into getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt()  of CoreDashboardAction");
				
				Long userId = null;
				
				HttpSession session = request.getSession();
				 RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				 if(user == null || user.getRegistrationID() == null){
					//return ERROR;
					 userId = 1L;
				 }
				 else
					 userId = user.getRegistrationID();
				 
				jObj = new JSONObject(getTask());
				Long userTypeId = jObj.getLong("userTypeId");
				Long activityMemberId = jObj.getLong("activityMemberId");
				 Long stateId = jObj.getLong("stateId");
				 String fromDate = jObj.getString("fromDate");
				 String toDate = jObj.getString("toDate");
				 
				 List<Long> partyMeetingTypeValues=new ArrayList<Long>();
					JSONArray partyMeetingTypeArray=jObj.getJSONArray("partyMeetingTypeArr");
					if(partyMeetingTypeArray!=null &&  partyMeetingTypeArray.length()>0){
						for( int i=0;i<partyMeetingTypeArray.length();i++){
							partyMeetingTypeValues.add(Long.valueOf(partyMeetingTypeArray.getString(i)));
						}
				}
				 userTypeVOList = coreDashboardPartyMeetingService.getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt(userId,userTypeId,activityMemberId,stateId,fromDate,toDate,partyMeetingTypeValues);
			}catch(Exception e){
				LOG.error("Exception raised at getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt() method of CoreDashBoardAction", e);
			}
			return Action.SUCCESS; 
		  
	}

public String savingDashboardComment()
{
	try
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		
		Long userId = null;
		if(regVO != null)
			userId = regVO.getRegistrationID();
		else
			userId = 1L;
		
		jObj = new JSONObject(getTask());
		DashboardCommentVO Vo = new DashboardCommentVO();
	    Vo.setDashBoardCommentId(jObj.getLong("dashboardCommentId"));
	    Vo.setDashboardComponentId(jObj.getLong("dashboardComponentId"));
	    Vo.setComment(jObj.getString("comment"));
	    //Vo.setUserId(regVO.getUser); 
	    resultStatus = coreDashboardService1.savingDashboardComment(Vo,userId);
		
	}catch(Exception e)
	{
		LOG.error("Exception Occured in savechangeAddressForNominatedPost() in NominatedPostProfileAction ",e);
	}
	return Action.SUCCESS;
}

public String displayDashboardComments(){
	try{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		
		Long userId = null;
		if(regVO != null)
			userId = regVO.getRegistrationID();
		else
			userId = 1L;
		jObj = new JSONObject(getTask());
		
		dashboardCommentVo = coreDashboardService1.displayDashboardComments(userId,jObj.getLong("dashBoardComponentId"));
		
	}catch (Exception e) {
		LOG.error("Entered into displayDashboardComments Action",e);
	}
	
	return Action.SUCCESS;
}
public String deleteDashBoardcomments()
{
	try{
		jObj = new JSONObject(getTask());
		status = coreDashboardService1.deleteDashBoardcomments(jObj.getLong("dashboardCommentId"));
	}
	catch (Exception e) {
		e.printStackTrace();
		LOG.error("Exception rised in deleteDashBoardcomments",e);
	}
	return Action.SUCCESS;	
}
public String getRolesPerformanceOfCandidate(){
	  try{
			
			jObj = new JSONObject(getTask());
			List<Long> roles = new ArrayList<Long>();
			
			Long roleId  = jObj.getLong("roleId");
			if(roleId !=null && roleId>0l){
				roles.add(roleId);
			}
			JSONArray participantLocIdArry = jObj.getJSONArray("participantLocIdArry");  
			List<Long> participantLocationIdList = new ArrayList<Long>();
			if(participantLocIdArry != null && participantLocIdArry.length() > 0){
				for (int i = 0; i < participantLocIdArry.length(); i++){
					participantLocationIdList.add(Long.parseLong(participantLocIdArry.getString(i)));          
				}  
			}
			JSONArray popupLocationIdArry = jObj.getJSONArray("debateLocationIdArry");  
			List<Long> debateLocationIdList = new ArrayList<Long>();
			if(popupLocationIdArry != null && popupLocationIdArry.length() > 0){
				for (int i = 0; i < popupLocationIdArry.length(); i++){
					debateLocationIdList.add(Long.parseLong(popupLocationIdArry.getString(i)));          
				}  
			}
			codeDebateVoList = coreDashboardMainService.getRolesPerformanceOfCandidate(jObj.getString("startDate"),jObj.getString("endDate"),roles,jObj.getString("state"),participantLocationIdList,debateLocationIdList);
					
		}catch (Exception e) {
			LOG.error("Exception raised at getRolesPerformanceOfCandidate() method of CoreDashBoardAction", e);
		}
		
		return Action.SUCCESS;
}

public String getDebateRolesNew(){
	try{
		
		idNameVoList = coreDashboardMainService.getDebateRolesNew();
	}
	catch (Exception e) {
		LOG.error("Exception rised in getDebateRolesNew",e);
	}
	return Action.SUCCESS;
}
public String getPartyMeetingTypeByPartyMeetingMainType(){
	 try{
			jObj = new JSONObject(getTask());
			partyMeetingsVOList = coreDashboardPartyMeetingService.getPartyMeetingTypeByPartyMeetingMainType(jObj.getLong("partyMeetingMainTypeId"));
		}catch (Exception e) {
			LOG.error("Exception raised at getPartyMeetingTypeByPartyMeetingMainType() method of CoreDashBoardAction", e);
		}
		return Action.SUCCESS;
}

public String getSelectedChildUserTypeMembersWithMeetingsCount(){
	LOG.info("Entered into getSelectedChildUserTypeMembersWithMeetingsCount()  of CoreDashboardAction");
	try{
		
		jObj = new JSONObject(getTask());
		
		Long parentActivityMemberId = jObj.getLong("parentActivityMemberId");
		
		List<Long> childUserTypeIds=new ArrayList<Long>();
		JSONArray childUserTypeIdsArray=jObj.getJSONArray("childUserTypeIdsArray");
		if(childUserTypeIdsArray!=null &&  childUserTypeIdsArray.length()>0){
			for( int i=0;i<childUserTypeIdsArray.length();i++){
				childUserTypeIds.add(Long.valueOf(childUserTypeIdsArray.getString(i)));
			}
		}
		
		String state = jObj.getString("state");
		String startDateString = jObj.getString("startDateString");
		String endDateString   = jObj.getString("endDateString");
		
		List<Long> partyMeetingTypeIds = new ArrayList<Long>();
		JSONArray partyMeetingTypeIdsArray=jObj.getJSONArray("partyMeetingTypeIds");
		if(partyMeetingTypeIdsArray!=null &&  partyMeetingTypeIdsArray.length()>0){
			for( int i=0;i<partyMeetingTypeIdsArray.length();i++){
				partyMeetingTypeIds.add(Long.valueOf(partyMeetingTypeIdsArray.getString(i)));
			}
		}
		
		activityMembersList = coreDashboardPartyMeetingService.getSelectedChildUserTypeMembersWithMeetingsCount(parentActivityMemberId,childUserTypeIds,state,startDateString,endDateString,partyMeetingTypeIds);
		
	}catch(Exception e){
		LOG.error("Exception raised at setMeetingsCountsToActivityMembers() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}

public String getDirectChildActivityMeetingMemberDetails()
{
	LOG.info("Entered into getDirectChildActivityMeetingMemberDetails()  of CoreDashboardAction");
	try{
		
		jObj = new JSONObject(getTask());
		
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long userTypeId = jObj.getLong("userTypeId");
		String state = jObj.getString("state");
		String startDateString = jObj.getString("startDateString");
		String endDateString   = jObj.getString("endDateString");
		
		List<Long> partyMeetingTypeIds = new ArrayList<Long>();
		JSONArray partyMeetingTypeIdsArray=jObj.getJSONArray("partyMeetingTypeIds");
		if(partyMeetingTypeIdsArray!=null &&  partyMeetingTypeIdsArray.length()>0){
			for( int i=0;i<partyMeetingTypeIdsArray.length();i++){
				partyMeetingTypeIds.add(Long.valueOf(partyMeetingTypeIdsArray.getString(i)));
			}
		}
		activityMembersList = coreDashboardPartyMeetingService.getDirectChildActivityMemberMeetingsDetails(activityMemberId,userTypeId,state,startDateString,endDateString,partyMeetingTypeIds);
		
	}catch(Exception e){
		LOG.error("Exception raised at getDirectChildActivityMeetingMemberDetails() method of CoreDashBoard", e);
	}
		return Action.SUCCESS;
}
public String getCandidateDtlsPerDist(){
	LOG.info("Entered into getCandidateDtlsPerDist()  of CoreDashboardAction");
	try{
		
		jObj = new JSONObject(getTask());
		
		Long distId = jObj.getLong("distId");
		Long programId = jObj.getLong("programId");
		Long stateId = jObj.getLong("stateId"); 
		String strDate = jObj.getString("dateStr");
		
		idNameVoList = coreDashboardMainService.getCandidateDtlsPerDist(distId,programId,stateId,strDate); 
		
	}catch(Exception e){
		LOG.error("Exception raised at getCandidateDtlsPerDist() method of CoreDashBoard", e);
	}
		return Action.SUCCESS;
}
 public String getTopPoorMeetingLocations(){
		LOG.info("Entered into getTopPoorMeetingLocations()  of CoreDashboardAction");
		try{
			
			jObj = new JSONObject(getTask());
			
			Long activityMemberId = jObj.getLong("activityMemberId");
			String state = jObj.getString("state");
			String startDateString = jObj.getString("startDateString");
			String endDateString   = jObj.getString("endDateString");
			
			List<Long> partyMeetingTypeIds = new ArrayList<Long>();
			JSONArray partyMeetingTypeIdsArray=jObj.getJSONArray("partyMeetingTypeIds");
			if(partyMeetingTypeIdsArray!=null &&  partyMeetingTypeIdsArray.length()>0){
				for( int i=0;i<partyMeetingTypeIdsArray.length();i++){
					partyMeetingTypeIds.add(Long.valueOf(partyMeetingTypeIdsArray.getString(i)));
				}
			}
			
			partyMeetingDataVOList = coreDashboardPartyMeetingService.getTopPoorMeetingLocations(activityMemberId,partyMeetingTypeIds,state,startDateString,endDateString);
			
	}catch(Exception e){
		LOG.error("Exception raised at getTopPoorMeetingLocations() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
  }
 public String getPartyMeetingCntDetailstLevelWiseByUserAccessLevel(){
	 LOG.info("Entered into getPartyMeetingCntDetailstLevelWiseByUserAccessLevel()  of CoreDashboardAction");
		try{
			
			jObj = new JSONObject(getTask());
			
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long stateId = jObj.getLong("stateId");
			String startDateString = jObj.getString("fromDate");
			String endDateString   = jObj.getString("toDate");
			
			List<Long> partyMeetingTypeIds = new ArrayList<Long>();
			JSONArray partyMeetingTypeIdsArray=jObj.getJSONArray("partyMeetingTypeArr");
			if(partyMeetingTypeIdsArray!=null &&  partyMeetingTypeIdsArray.length()>0){
				for( int i=0;i<partyMeetingTypeIdsArray.length();i++){
					partyMeetingTypeIds.add(Long.valueOf(partyMeetingTypeIdsArray.getString(i)));
				}
			}
			partyMeetingsVOList = coreDashboardPartyMeetingService.getPartyMeetingCntDetailstLevelWiseByUserAccessLevel(activityMemberId,stateId,startDateString,endDateString,partyMeetingTypeIds);
	}catch(Exception e){
		LOG.error("Exception raised at getPartyMeetingCntDetailstLevelWiseByUserAccessLevel() method of CoreDashBoard", e);
	}
	return Action.SUCCESS; 
	 
	 
 }
 public String getEventInviteeAttendeeCount(){
	 LOG.info("Entered into getEventInviteeAttendeeCount()  of CoreDashboardAction");
		try{
			
			jObj = new JSONObject(getTask());
			
			Long activityMemberId = jObj.getLong("activityMemberId");
			
			List<Long> eventIds = new ArrayList<Long>();
			JSONArray eventIdsArray=jObj.getJSONArray("partyMeetingTypeArr");
			if(eventIdsArray!=null &&  eventIdsArray.length()>0){
				for( int i=0;i<eventIdsArray.length();i++){
					eventIds.add(Long.valueOf(eventIdsArray.getString(i)));
				}
			}
			//IdAndNameVOList = coreDashboardService1.getEventInviteeAttendeeCount(eventIds,activityMemberId);
	}catch(Exception e){
		LOG.error("Exception raised at getEventInviteeAttendeeCount() method of coreDashboardAction", e);
	}
	return Action.SUCCESS; 
 }
 	
 	public String getPartyComparisonChildUserTypeMembers(){
 		try {
			jObj = new JSONObject(getTask());
			
			JSONArray arr = jObj.getJSONArray("npIdsArr");
			List<Long> npIdsList = new ArrayList<Long>(0);
			if(arr != null && arr.length() > 0){
				for(int i=0;i<arr.length();i++){
					npIdsList.add(Long.parseLong(arr.getString(i)));
				}
			}
			List<Long> list = new ArrayList<Long>(0);
			JSONArray arr1 = jObj.getJSONArray("childUserTypeIdArr");
			if(arr1 != null && arr1.length() > 0){
				for(int i=0;i<arr1.length();i++){
					list.add(Long.parseLong(arr1.getString(i)));
				}
			}
			JSONArray arr2 = jObj.getJSONArray("impactScopeIdsArr");
			List<Long> impactScopeIdsList = new ArrayList<Long>(0);
			if(arr2 != null && arr2.length() > 0){
				for(int i=0;i<arr2.length();i++){
					impactScopeIdsList.add(Long.parseLong(arr2.getString(i)));
				}
			}
			childUserTypeVOList = newsCoreDashBoardService.getPartyComparisonChildUserTypeMembers(jObj.getLong("parentActivityMemberId"),list,jObj.getString("state"),jObj.getString("startDate"),jObj.getString("endDate"),npIdsList,impactScopeIdsList);
		} catch (Exception e) {
			LOG.error("Exception riased at getPartyComparisonChildUserTypeMembers", e);
		}
		return Action.SUCCESS;
	}
 	public String getLeaderShipCandidateDtlsPerDist(){
 		  try{
 				LOG.info("Entered into getLeaderShipCandidateDtlsPerDist()  of CoreDashboardAction");
 				jObj = new JSONObject(getTask());
 				Long userAccessLevelId = jObj.getLong("userAccessLevelId");
 				
 				List<Long> userAccessLevelValues=new ArrayList<Long>();
 				JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
 				if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
 					for( int i=0;i<userAccessLevelValuesArray.length();i++){
 						userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
 					}
 				}
 				Long stateId = jObj.getLong("stateId");
 				Long distId = jObj.getLong("distId");
 				String dateStr = jObj.getString("dateStr");
 				List<Long> enrollmentYearIds=new ArrayList<Long>();
 				JSONArray enrollmentYearIdsArray=jObj.getJSONArray("enrollmentYearIdsList");
 				if(enrollmentYearIdsArray!=null &&  enrollmentYearIdsArray.length()>0){
 					for( int i=0;i<enrollmentYearIdsArray.length();i++){
 						enrollmentYearIds.add(Long.valueOf(enrollmentYearIdsArray.getString(i)));
 					}
 				}
 				List<Long> trainingCampProgramIds=new ArrayList<Long>();
 				JSONArray trainingCampProgramIdArr=jObj.getJSONArray("trainingCampProgramIds");
 				if(trainingCampProgramIdArr!=null &&  trainingCampProgramIdArr.length()>0){
 					for( int i=0;i<trainingCampProgramIdArr.length();i++){
 						trainingCampProgramIds.add(Long.valueOf(trainingCampProgramIdArr.getString(i)));
 					}
 				}
 				idNameVoList = coreDashboardMainService.getLeaderShipCandidateDtlsPerDist(userAccessLevelId,userAccessLevelValues,stateId,distId,dateStr,enrollmentYearIds,trainingCampProgramIds);
 			
 			}catch(Exception e){
 				LOG.error("Exception raised at getLeaderShipCandidateDtlsPerDist() method of CoreDashBoardAction", e);
 			}
 			return Action.SUCCESS;
 	  }
public String getPartyMeetingsMainTypeOverViewData(){
		
		try{
			
			jObj = new JSONObject(getTask());
			
			Long partyMeetingMainTypeId = jObj.getLong("partyMeetingMainTypeId");
			
			List<Long> partyMeetingTypeIds = new ArrayList<Long>();
			JSONArray partyMeetingTypeIdsArray=jObj.getJSONArray("partyMeetingTypeIds");
			if(partyMeetingTypeIdsArray!=null &&  partyMeetingTypeIdsArray.length()>0){
				for( int i=0;i<partyMeetingTypeIdsArray.length();i++){
					partyMeetingTypeIds.add(Long.valueOf(partyMeetingTypeIdsArray.getString(i)));
				}
			}
			
			String state = jObj.getString("state");
			String startDateString = jObj.getString("startDateString");
			String endDateString   = jObj.getString("endDateString");
			Long partyMeetingId = jObj.getLong("partyMeetingId");
			
			partyMeetingDataVOList = coreDashboardPartyMeetingService.getPartyMeetingsMainTypeOverViewData(partyMeetingMainTypeId,partyMeetingTypeIds,state,startDateString,endDateString,partyMeetingId);
			
	}catch(Exception e){
		LOG.error("Exception raised at getPartyMeetingsMainTypeOverViewData() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
  }
public String getParyMeetingTypeDetailsDistrictWise(){
	
	try{
		
		jObj = new JSONObject(getTask());
		
		Long partyMeetingMainTypeId = jObj.getLong("partyMeetingMainTypeId");
		
		List<Long> partyMeetingTypeIds = new ArrayList<Long>();
		JSONArray partyMeetingTypeIdsArray=jObj.getJSONArray("partyMeetingTypeIds");
		if(partyMeetingTypeIdsArray!=null &&  partyMeetingTypeIdsArray.length()>0){
			for( int i=0;i<partyMeetingTypeIdsArray.length();i++){
				partyMeetingTypeIds.add(Long.valueOf(partyMeetingTypeIdsArray.getString(i)));
			}
		}
		
		String state = jObj.getString("state");
		String startDateString = jObj.getString("startDateString");
		String endDateString   = jObj.getString("endDateString");
		
		
		partyMeetingDataVOList = coreDashboardPartyMeetingService.getParyMeetingTypeDetailsDistrictWise(partyMeetingMainTypeId,partyMeetingTypeIds,state,startDateString,endDateString);
		
}catch(Exception e){
	LOG.error("Exception raised at getParyMeetingTypeDetailsDistrictWise() method of CoreDashBoard", e);
}
return Action.SUCCESS;
}
public String getPartyCompareSubLevelMemberDetails(){
	try {
		jObj = new JSONObject(getTask());
		
		List<Long> npIdsList = new ArrayList<Long>(0);
		
		if(jObj.getJSONArray("npIdsArr") != null && jObj.getJSONArray("npIdsArr").length()>0){
			for(int i=0;i<jObj.getJSONArray("npIdsArr").length();i++){
				npIdsList.add(Long.parseLong(jObj.getJSONArray("npIdsArr").getString(i)));
			}
		}
		List<Long> impactScopeIdsList = new ArrayList<Long>(0);
		
		if(jObj.getJSONArray("impactScopeIdsArr") != null && jObj.getJSONArray("impactScopeIdsArr").length()>0){
			for(int i=0;i<jObj.getJSONArray("impactScopeIdsArr").length();i++){
				impactScopeIdsList.add(Long.parseLong(jObj.getJSONArray("impactScopeIdsArr").getString(i)));
			}
		}
		
		childUserTypeVOList = newsCoreDashBoardService.getPartyCompareSubLevelMemberDetails(jObj.getLong("activityMemberId"),jObj.getLong("userTypeId"),jObj.getString("state"),jObj.getString("startDate"),jObj.getString("endDate"),npIdsList,impactScopeIdsList);
		 
	} catch (Exception e) {
		LOG.error("Exception raised at getPartyCompareSubLevelMemberDetails", e);
	}
	return Action.SUCCESS;
}

public String getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetings(){
	
	try{
		
		jObj = new JSONObject(getTask());
		
		Long partyMeetingMainTypeId = jObj.getLong("partyMeetingMainTypeId");
		
		List<Long> partyMeetingTypeIds = new ArrayList<Long>();
		JSONArray partyMeetingTypeIdsArray=jObj.getJSONArray("partyMeetingTypeIds");
		if(partyMeetingTypeIdsArray!=null &&  partyMeetingTypeIdsArray.length()>0){
			for( int i=0;i<partyMeetingTypeIdsArray.length();i++){
				partyMeetingTypeIds.add(Long.valueOf(partyMeetingTypeIdsArray.getString(i)));
			}
		}
		
		String state = jObj.getString("state");
		String startDateString = jObj.getString("startDateString");
		String endDateString   = jObj.getString("endDateString");
		
		
		partyMeetingDataVO = coreDashboardPartyMeetingService.getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetings(partyMeetingMainTypeId,partyMeetingTypeIds,state,startDateString,endDateString);
		
}catch(Exception e){
	LOG.error("Exception raised at getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToMeetings() method of CoreDashBoard", e);
}
return Action.SUCCESS;
}
public String getEventBasicCntDtls(){
try{
		
		jObj = new JSONObject(getTask());
		
		Long activityMemberId = jObj.getLong("activityMemberId");
		
		List<Long> eventIds = new ArrayList<Long>();
		JSONArray eventIdsArray=jObj.getJSONArray("eventIds");
		if(eventIdsArray!=null &&  eventIdsArray.length()>0){
			for( int i=0;i<eventIdsArray.length();i++){
				eventIds.add(Long.valueOf(eventIdsArray.getString(i)));
			}
		}
		
		Long stateId = jObj.getLong("stateId");
		
		eventDetailsVOList = coreDashboardEventsActivitiesService.getEventBasicCountDetails(eventIds,activityMemberId,stateId);
		
}catch(Exception e){
	LOG.error("Exception raised at getEventBasicCntDtls() method of CoreDashBoard", e);
}
return Action.SUCCESS;
}
public String getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(){
try{
		
		jObj = new JSONObject(getTask());
		
		final HttpSession session = request.getSession();
		Long userId = null;
		final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null || user.getRegistrationID() == null){
			//return ERROR;
			userId = 1L;
		}
		else
			userId = user.getRegistrationID();
		
		Long activityMemberId = jObj.getLong("activityMemberId");
		
		List<Long> eventIds = new ArrayList<Long>();
		JSONArray eventIdsArray=jObj.getJSONArray("eventIds");
		if(eventIdsArray!=null &&  eventIdsArray.length()>0){
			for( int i=0;i<eventIdsArray.length();i++){
				eventIds.add(Long.valueOf(eventIdsArray.getString(i)));
			}
		}
		
		Long stateId = jObj.getLong("stateId");
		Long userTypeId = jObj.getLong("userTypeId");
		
		userTypeVOList = coreDashboardEventsActivitiesService.getUserTypeWiseTotalInviteeAndInviteeAttendedCnt(eventIds,activityMemberId,userId,userTypeId,stateId);
		
}catch(Exception e){
	LOG.error("Exception raised at getUserTypeWiseTotalInviteeAndInviteeAttendedCnt() method of CoreDashBoard", e);
}
return Action.SUCCESS;
}
public String getParyMeetingTypeDetailsPerDistrict(){
	
	try{
		
		jObj = new JSONObject(getTask()); 
		
		Long partyMeetingMainTypeId = jObj.getLong("partyMeetingMainTypeId");
		
		List<Long> partyMeetingTypeIds = new ArrayList<Long>();
		JSONArray partyMeetingTypeIdsArray=jObj.getJSONArray("partyMeetingTypeIds");
		if(partyMeetingTypeIdsArray!=null &&  partyMeetingTypeIdsArray.length()>0){
			for( int i=0;i<partyMeetingTypeIdsArray.length();i++){
				partyMeetingTypeIds.add(Long.valueOf(partyMeetingTypeIdsArray.getString(i)));
			}
		}
		
		String state = jObj.getString("state");
		String startDateString = jObj.getString("startDateString");
		String endDateString   = jObj.getString("endDateString");  
		Long distId = jObj.getLong("distId");  
		
		idNameVoList = coreDashboardPartyMeetingService.getParyMeetingTypeDetailsPerDistrict(partyMeetingMainTypeId,partyMeetingTypeIds,state,startDateString,endDateString,distId);
		
}catch(Exception e){  
	LOG.error("Exception raised at getParyMeetingTypeDetailsDistrictWise() method of CoreDashBoard", e);
}
return Action.SUCCESS;
}
public String getLatestDebate(){
	try{
		
		status = coreDashboardMainService.getLatestDebate();
		
	}catch(Exception e){
		LOG.error("Exception raised at getLatestDebate() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(){
	
	try{
		
		jObj = new JSONObject(getTask()); 
		
        Long activityMemberId = jObj.getLong("activityMemberId");
		
		List<Long> eventIds = new ArrayList<Long>();
		JSONArray eventIdsArray=jObj.getJSONArray("eventIds");
		if(eventIdsArray!=null &&  eventIdsArray.length()>0){
			for( int i=0;i<eventIdsArray.length();i++){
				eventIds.add(Long.valueOf(eventIdsArray.getString(i)));
			}
		}
		Long stateId = jObj.getLong("stateId");
		Long userTypeId = jObj.getLong("userTypeId");
		eventDetailsVOList = coreDashboardEventsActivitiesService.getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType(userTypeId,stateId,activityMemberId,eventIds);
	}catch (Exception e) {
		LOG.error("Exception raised at getLocationWiseByInviteeAttendedAndInviteeAttendedCntBasedOnUserType() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getSelectedChildTypeMembersForEvent(){
	try{
		
		jObj = new JSONObject(getTask()); 
	    Long parentActivityMemberId = jObj.getLong("parentActivityMemberId");
		
		List<Long> childUserTypeIds=new ArrayList<Long>();
		JSONArray childUserTypeIdsArray=jObj.getJSONArray("childUserTypeIdsArray");
		if(childUserTypeIdsArray!=null &&  childUserTypeIdsArray.length()>0){
			for( int i=0;i<childUserTypeIdsArray.length();i++){
				childUserTypeIds.add(childUserTypeIdsArray.getString(i) != null && !childUserTypeIdsArray.getString(i).trim().isEmpty() ?Long.valueOf(childUserTypeIdsArray.getString(i)):0L);
			}
		}
		String reportType = jObj.getString("reportType");
		Long stateId = jObj.getLong("stateId");
		List<Long> eventsIds = new ArrayList<Long>();
		JSONArray eventIdsArray=jObj.getJSONArray("eventIds");
		if(eventIdsArray!=null &&  eventIdsArray.length()>0){
			for( int i=0;i<eventIdsArray.length();i++){
				eventsIds.add(Long.valueOf(eventIdsArray.getString(i)));
			}
		}
		String searchType = jObj.getString("searchType");
		activityMembersList = coreDashboardEventsActivitiesService.getSelectedChildMembersForEvents(parentActivityMemberId,childUserTypeIds,reportType,stateId,eventsIds,searchType);
	}catch (Exception e) {
		LOG.error("Exception raised at getSelectedChildTypeMembersForEvent() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}

public String getSelectedChildTypeMembersForMultiLocationMeetings(){
	try{
		
		jObj = new JSONObject(getTask()); 
	    Long parentActivityMemberId = jObj.getLong("parentActivityMemberId");
		
		List<Long> childUserTypeIds=new ArrayList<Long>();
		JSONArray childUserTypeIdsArray=jObj.getJSONArray("childUserTypeIdsArray");
		if(childUserTypeIdsArray!=null &&  childUserTypeIdsArray.length()>0){
			for( int i=0;i<childUserTypeIdsArray.length();i++){
				childUserTypeIds.add(childUserTypeIdsArray.getString(i) != null && !childUserTypeIdsArray.getString(i).trim().isEmpty() ?Long.valueOf(childUserTypeIdsArray.getString(i)):0L);
			}
		}
		String reportType = jObj.getString("reportType");
		Long stateId = jObj.getLong("stateId");
		List<Long> eventsIds = new ArrayList<Long>();
		/*JSONArray eventIdsArray=jObj.getJSONArray("eventIds");
		if(eventIdsArray!=null &&  eventIdsArray.length()>0){
			for( int i=0;i<eventIdsArray.length();i++){
				eventsIds.add(Long.valueOf(eventIdsArray.getString(i)));
			}
		}*/
		
		Long partyMeetingMainTypeId = jObj.getLong("partyMeetingMainTypeId");
		Long partyMeetingLevelId = jObj.getLong("partyMeetingLevelId");
		Long meetingGrpId = jObj.getLong("meetingGrpId");
		String fromDateStr = jObj.getString("fromDateStr");
		String toDateStr = jObj.getString("toDateStr");
		
		//String searchType = jObj.getString("searchType");
		activityMembersList = coreDashboardEventsActivitiesService.getSelectedChildMembersForMultiLocationMeetings(parentActivityMemberId,childUserTypeIds,reportType,stateId,
									partyMeetingMainTypeId,partyMeetingLevelId,meetingGrpId,fromDateStr,toDateStr);
	}catch (Exception e) {
		LOG.error("Exception raised at getSelectedChildTypeMembersForEvent() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}

public String getLocationWiseSelectedChildMembersForMultiLocationMeetings(){
	try{
		
		jObj = new JSONObject(getTask()); 
	    Long locationLevelId = jObj.getLong("locationLevelId");
		
		List<Long> locationValues=new ArrayList<Long>();
		JSONArray locationValuesArray=jObj.getJSONArray("locationValuesArray");
		if(locationValuesArray!=null &&  locationValuesArray.length()>0){
			for( int i=0;i<locationValuesArray.length();i++){
				locationValues.add(locationValuesArray.getString(i) != null && !locationValuesArray.getString(i).trim().isEmpty() ?Long.valueOf(locationValuesArray.getString(i)):0L);
			}
		}
		/*String locationValuesArray = jObj.getString("locationValuesArray");
		String[] stringArr = locationValuesArray.split(",");
		if(stringArr != null && stringArr.length > 0){
			for (String string : stringArr) {
				locationValues.add(Long.valueOf(string));
			}
		}*/
		//String reportType = jObj.getString("reportType");
		Long stateId = jObj.getLong("stateId");
		//List<Long> eventsIds = new ArrayList<Long>();
		/*JSONArray eventIdsArray=jObj.getJSONArray("eventIds");
		if(eventIdsArray!=null &&  eventIdsArray.length()>0){
			for( int i=0;i<eventIdsArray.length();i++){
				eventsIds.add(Long.valueOf(eventIdsArray.getString(i)));
			}
		}*/
		
		Long partyMeetingMainTypeId = jObj.getLong("partyMeetingMainTypeId");
		Long partyMeetingLevelId = jObj.getLong("partyMeetingLevelId");
		Long meetingGrpId = jObj.getLong("meetingGrpId");
		String fromDateStr = jObj.getString("fromDateStr");
		String toDateStr = jObj.getString("toDateStr");
		
		//String searchType = jObj.getString("searchType");
		partyMeetingVOList = coreDashboardEventsActivitiesService.getLocationWiseSelectedChildMembersForMultiLocationMeetings(locationLevelId,locationValues,stateId,
									partyMeetingMainTypeId,partyMeetingLevelId,meetingGrpId,fromDateStr,toDateStr);
	}catch (Exception e) {
		LOG.error("Exception raised at getLocationWiseSelectedChildMembersForMultiLocationMeetings() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}

public String getPartyMeetingLevelIdsByAccessLevel(){
	try{
		
		jObj = new JSONObject(getTask()); 
	    Long locationLevelId = jObj.getLong("locationLevelId");
		
		List<Long> locationValues=new ArrayList<Long>();
		JSONArray locationValuesArray=jObj.getJSONArray("locationValuesArray");
		if(locationValuesArray!=null &&  locationValuesArray.length()>0){
			for( int i=0;i<locationValuesArray.length();i++){
				locationValues.add(locationValuesArray.getString(i) != null && !locationValuesArray.getString(i).trim().isEmpty() ?Long.valueOf(locationValuesArray.getString(i)):0L);
			}
		}
		//String reportType = jObj.getString("reportType");
		Long stateId = jObj.getLong("stateId");
		//List<Long> eventsIds = new ArrayList<Long>();
		/*JSONArray eventIdsArray=jObj.getJSONArray("eventIds");
		if(eventIdsArray!=null &&  eventIdsArray.length()>0){
			for( int i=0;i<eventIdsArray.length();i++){
				eventsIds.add(Long.valueOf(eventIdsArray.getString(i)));
			}
		}*/
		
		Long partyMeetingMainTypeId = jObj.getLong("partyMeetingMainTypeId");
		Long meetingGrpId = jObj.getLong("meetingGrpId");
		String fromDateStr = jObj.getString("fromDateStr");
		String toDateStr = jObj.getString("toDateStr");
		
		//String searchType = jObj.getString("searchType");
		partyMeetingLevelIds = coreDashboardEventsActivitiesService.getPartyMeetingLevelIdsByAccessLevel(locationLevelId,locationValues,stateId,
									partyMeetingMainTypeId,meetingGrpId,fromDateStr,toDateStr);
	}catch (Exception e) {
		LOG.error("Exception raised at getPartyMeetingLevelIdsByAccessLevel() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}

public String getDirectChildTypeMembersForEvent(){
	try{
		
		jObj = new JSONObject(getTask()); 
	    Long activityMemberId = jObj.getLong("activityMemberId");
		
		List<Long> childUserTypeIds=new ArrayList<Long>();
		JSONArray childUserTypeIdsArray=jObj.getJSONArray("childUserTypeIdsArray");
		if(childUserTypeIdsArray!=null &&  childUserTypeIdsArray.length()>0){
			for( int i=0;i<childUserTypeIdsArray.length();i++){
				childUserTypeIds.add(Long.valueOf(childUserTypeIdsArray.getString(i)));
			}
		}
		String reportType = jObj.getString("reportType");
		Long stateId = jObj.getLong("stateId");
		List<Long> eventsIds = new ArrayList<Long>();
		JSONArray eventIdsArray=jObj.getJSONArray("eventIds");
		if(eventIdsArray!=null &&  eventIdsArray.length()>0){
			for( int i=0;i<eventIdsArray.length();i++){
				eventsIds.add(Long.valueOf(eventIdsArray.getString(i)));
			}
		}
		String searchType = jObj.getString("searchType");
		activityMembersList = coreDashboardEventsActivitiesService.getSelectedChildMembersForEvents(activityMemberId,childUserTypeIds,reportType,stateId,eventsIds,searchType);
	}catch (Exception e) {
		LOG.error("Exception raised at getDirectChildTypeMembersForEvent() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getAttendanceOverViewForPartyOffice(){
	try{
		jObj = new JSONObject(getTask()); 
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");  
		idNameVO = attendanceCoreDashBoardService.getAttendanceOverViewForPartyOffice(fromDate,toDate);
	}catch (Exception e) {
		LOG.error("Exception raised at getAttendanceOverViewForPartyOffice() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getAttendanceOverViewForPartyOfficeWise(){   
	try{
		jObj = new JSONObject(getTask()); 
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		idNameVoList = attendanceCoreDashBoardService.getAttendanceOverViewForPartyOfficeWise(fromDate,toDate);
	}catch (Exception e) {
		LOG.error("Exception raised at getAttendanceOverViewForPartyOfficeWise() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getAttendanceOverViewForPartyOfficeDeptWise(){  
	try{
		jObj = new JSONObject(getTask()); 
		String fromDate = jObj.getString("fromDate");  
		String toDate = jObj.getString("toDate");
		idNameVoList = attendanceCoreDashBoardService.getAttendanceOverViewForPartyOfficeDeptWise(fromDate,toDate);
	}catch (Exception e) {
		LOG.error("Exception raised at getAttendanceOverViewForPartyOfficeDeptWise() method of CoreDashBoard", e);
	}
	return Action.SUCCESS; 
}

public String getEventPoorPerformanceLocation(){
	try{
		jObj = new JSONObject(getTask()); 
	    Long activityMemberId = jObj.getLong("activityMemberId");
		Long userTypeId = jObj.getLong("userTypeId");
		Long stateId = jObj.getLong("stateId");
		List<Long> eventsIds = new ArrayList<Long>();
		JSONArray eventIdsArray=jObj.getJSONArray("eventIds");
		if(eventIdsArray!=null &&  eventIdsArray.length()>0){
			for( int i=0;i<eventIdsArray.length();i++){
				eventsIds.add(Long.valueOf(eventIdsArray.getString(i)));
			}
		}
		String searchType = jObj.getString("searchType");
		eventDetailsVO = coreDashboardEventsActivitiesService.getEventPoorPerformanceLocation(userTypeId,stateId,activityMemberId,eventsIds,searchType); 
	 }catch(Exception e) {
		 LOG.error("Exception raised at getEventPoorPerformanceLocation() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getAttendeeDtlsOfficeWiseForDay(){  
	try{
		jObj = new JSONObject(getTask()); 
		String fromDate = jObj.getString("fromDate");  
		String toDate = jObj.getString("toDate");
		String type= jObj.getString("type");
		String status= jObj.getString("status");
		List<Long> officeIdList = new ArrayList<Long>();
		List<Long> deptIdList = new ArrayList<Long>();
		JSONArray officeIdsArray=jObj.getJSONArray("officeIdArr");
		JSONArray deptIdsArray=jObj.getJSONArray("deptIdArr");
		if(officeIdsArray!=null &&  officeIdsArray.length()>0){  
			for( int i=0;i<officeIdsArray.length();i++){
				officeIdList.add(Long.valueOf(officeIdsArray.getString(i)));
			}
		}
		if(deptIdsArray!=null &&  deptIdsArray.length()>0){
			for( int i=0;i<deptIdsArray.length();i++){
				deptIdList.add(Long.valueOf(deptIdsArray.getString(i)));
			}
		}
		if(type.equalsIgnoreCase("office")){
			idNameVoList = attendanceCoreDashBoardService.getAttendeeDtlsOfficeWiseForDay(fromDate,toDate,officeIdList,deptIdList);
		}else{
			idNameVoList = attendanceCoreDashBoardService.getAttendeeDtlsDeptWiseForDay(fromDate,toDate,officeIdList,deptIdList,status);
		}
		
		
	}catch (Exception e) {
		LOG.error("Exception raised at getAttendeeDtlsOfficeWiseForDay() method of CoreDashBoard", e);
	}
	return Action.SUCCESS; 
}
public String getTopAbsentAndIregular(){  
	try{
		jObj = new JSONObject(getTask()); 
		String fromDate = jObj.getString("fromDate");  
		String toDate = jObj.getString("toDate");
		
		List<Long> officeIdList = new ArrayList<Long>();
		List<Long> deptIdList = new ArrayList<Long>();
		JSONArray officeIdsArray=jObj.getJSONArray("officeIdArr");
		JSONArray deptIdsArray=jObj.getJSONArray("deptIdArr");
		if(officeIdsArray!=null &&  officeIdsArray.length()>0){  
			for( int i=0;i<officeIdsArray.length();i++){
				officeIdList.add(Long.valueOf(officeIdsArray.getString(i)));
			}
		}
		if(deptIdsArray!=null &&  deptIdsArray.length()>0){
			for( int i=0;i<deptIdsArray.length();i++){
				deptIdList.add(Long.valueOf(deptIdsArray.getString(i)));
			}
		}
		
		idNameVOsList = attendanceCoreDashBoardService.getTopAbsentAndIregular(fromDate,toDate,officeIdList,deptIdList);
		
	}catch (Exception e) {
		LOG.error("Exception raised at getAttendeeDtlsOfficeWiseForDay() method of CoreDashBoard", e);
	}
	return Action.SUCCESS; 
}
public String getTrainingRecentTime(){
	try{
		status = coreDashboardMainService.getTrainingCampRecentTime();
	}catch(Exception e){
		LOG.error("Exception raised at getTrainingRecentTime() method of CoreDashBoard", e);	
	}
	return Action.SUCCESS;
}
public String getMeetingRecentTime(){
	try{
		status = coreDashboardPartyMeetingService.getMeetingRecentTime();
	}catch(Exception e){
		LOG.error("Exception raised at getMeetingRecentTime() method of CoreDashBoard", e);	
	}
	return Action.SUCCESS;
}
public String getAttendanceCountForMulitDate(){  
	try{
		jObj = new JSONObject(getTask()); 
		String fromDate = jObj.getString("fromDate");  
		String toDate = jObj.getString("toDate");
		Long officeId = jObj.getLong("officeId");
		Long deptId = jObj.getLong("deptId");
		idNameVO = attendanceCoreDashBoardService.getAttendanceCountForMulitDate( officeId,  deptId,  fromDate,  toDate);
		
	}catch (Exception e) {
		LOG.error("Exception raised at getAttendanceCountForMulitDate() method of CoreDashBoard", e);
	}
	return Action.SUCCESS; 
}
public String getAttendanceCountForMulitDateTimeWise(){  
	try{
		jObj = new JSONObject(getTask()); 
		String fromDate = jObj.getString("fromDate");  
		String toDate = jObj.getString("toDate");
		Long officeId = jObj.getLong("officeId");
		Long deptId = jObj.getLong("deptId");   
		idNameVO = attendanceCoreDashBoardService.getAttendanceCountForMulitDateTimeWise( officeId,  deptId,  fromDate,  toDate);
		
	}catch (Exception e) {
		LOG.error("Exception raised at getAttendanceCountForMulitDate() method of CoreDashBoard", e);
	}
	return Action.SUCCESS; 
}
public String getAttendanceReportTimeToTime(){  
	try{
		jObj = new JSONObject(getTask()); 
		String fromDate = jObj.getString("fromDate");  
		String toDate = jObj.getString("toDate");
		Long officeId = jObj.getLong("officeId");  
		Long deptId = jObj.getLong("deptId");   
		idNameVoList = attendanceCoreDashBoardService.getAttendanceReportTimeToTime( officeId,  deptId,  fromDate,  toDate);
		
	}catch (Exception e) {
		LOG.error("Exception raised at getAttendanceCountForMulitDate() method of CoreDashBoard", e);
	}
	return Action.SUCCESS; 
}
public String getDateWisePresentAbsentDtls(){  
	try{
		jObj = new JSONObject(getTask()); 
		String fromDate = jObj.getString("fromDate");  
		String toDate = jObj.getString("toDate");
		Long officeId = jObj.getLong("officeId");  
		Long deptId = jObj.getLong("deptId");   
		holidayListVOs = attendanceCoreDashBoardService.getDateWisePresentAbsentDtls( officeId,  deptId,  fromDate,  toDate);
		
	}catch (Exception e) {
		LOG.error("Exception raised at getAttendanceCountForMulitDate() method of CoreDashBoard", e);
	}
	return Action.SUCCESS; 
}
public String getAttendanceCountForMulitDateForEmp(){  
	try{  
		jObj = new JSONObject(getTask()); 
		String fromDate = jObj.getString("fromDate");    
		String toDate = jObj.getString("toDate");  
		Long officeId = jObj.getLong("officeId");
		Long deptId = jObj.getLong("deptId");
		Long cadreId = jObj.getLong("cadreId");
		idNameVO = attendanceCoreDashBoardService.getAttendanceCountForMulitDateForEmp( officeId,  deptId,  fromDate,  toDate, cadreId);
		
	}catch (Exception e) {
		LOG.error("Exception raised at getAttendanceCountForMulitDate() method of CoreDashBoard", e);
	}
	return Action.SUCCESS; 
}
public String getAttendanceCountForMulitDateTimeWiseForEmp(){  
	try{
		jObj = new JSONObject(getTask()); 
		String fromDate = jObj.getString("fromDate");      
		String toDate = jObj.getString("toDate");
		Long officeId = jObj.getLong("officeId");
		Long deptId = jObj.getLong("deptId");   
		Long cadreId = jObj.getLong("cadreId");  
		idNameVO = attendanceCoreDashBoardService.getAttendanceCountForMulitDateTimeWiseForEmp( officeId,  deptId,  fromDate,  toDate, cadreId);
		 
	}catch (Exception e) { 
		LOG.error("Exception raised at getAttendanceCountForMulitDate() method of CoreDashBoard", e);
	}
	return Action.SUCCESS; 
}
public String getDateWisePresentAbsentDtlsForEmployee(){  
	try{
		jObj = new JSONObject(getTask()); 
		String fromDate = jObj.getString("fromDate");  
		String toDate = jObj.getString("toDate");
		Long officeId = jObj.getLong("officeId");  
		Long deptId = jObj.getLong("deptId");
		Long cadreId = jObj.getLong("cadreId"); 
		holidayListVOs = attendanceCoreDashBoardService.getDateWisePresentAbsentDtlsForEmployee( officeId,  deptId,  fromDate,  toDate, cadreId);  
		
	}catch (Exception e) {
		LOG.error("Exception raised at getAttendanceCountForMulitDate() method of CoreDashBoard", e);
	}
	return Action.SUCCESS; 
}

public String getCoreDebateBasicDetailsOfParty(){
	try{
		jObj = new JSONObject(getTask()); 
		JSONArray popupLocationIdArry = jObj.getJSONArray("popupLocationIdArry");  
		List<Long> debateLocationIdList = new ArrayList<Long>();
		if(popupLocationIdArry != null && popupLocationIdArry.length() > 0){
			for (int i = 0; i < popupLocationIdArry.length(); i++){
				debateLocationIdList.add(Long.parseLong(popupLocationIdArry.getString(i)));          
			}  
		}
		JSONArray debateParticipantLocIdArry = jObj.getJSONArray("participantLocIdArry");  
		List<Long> debateParticipantLocationIdList = new ArrayList<Long>();
		if(debateParticipantLocIdArry != null && debateParticipantLocIdArry.length() > 0){
			for (int i = 0; i < debateParticipantLocIdArry.length(); i++){
				debateParticipantLocationIdList.add(Long.parseLong(debateParticipantLocIdArry.getString(i)));          
			}  
		}
		codeDebateVoList = coreDashboardMainService.getCoreDebateBasicDetailsOfParty(jObj.getLong("partyId"),jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("searchType"),jObj.getLong("candidateId"),debateLocationIdList,debateParticipantLocationIdList,jObj.getLong("roleId"),jObj.getLong("designationId"),jObj.getString("type"));
		
	}catch(Exception e){
		LOG.error("Exception raised at getCoreDebateBasicDetailsOfParty() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String showArticles(){
	return "success";
}
public String showCadreRegistreredCount(){
    try{
      
    	String retrieveType = request.getParameter("retrieveType");
    	
      cadreRegistratedCountVO = coreDashboardCadreRegistrationService.showCadreRegistreredCount(retrieveType);
      
    }catch(Exception e){
      LOG.error("Exception raised at getCoreDebateBasicDetailsOfParty() method of CoreDashBoard", e);
    }
    return Action.SUCCESS;
  }
public String getRegistrationCountDtls(){  
    try{
    	jObj = new JSONObject(getTask()); 
    	String location = jObj.getString("location");
    	Long constId = jObj.getLong("constId");
    	String scope = jObj.getString("scope");
    	object = coreDashboardCadreRegistrationService.getRegistrationCountDtls(location,constId,scope);  
      
    }catch(Exception e){  
      LOG.error("Exception raised at getCoreDebateBasicDetailsOfParty() method of CoreDashBoard", e);
    }
    return Action.SUCCESS;
  }
public String getDistrictPartyMeetingCommentsDetails(){
	try {
		LOG.info("Entered into getPartyMeetingCommentsDetails()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		String meetingStatus = jObj.getString("meetingStatus");
		String meetingLevel = jObj.getString("meetingLevel");
		String isComment = jObj.getString("isComment");
		List<Long> partyMeetingTypeValues=new ArrayList<Long>();
		JSONArray partyMeetingTypeArray=jObj.getJSONArray("partyMeetingTypeArr");
		if(partyMeetingTypeArray!=null &&  partyMeetingTypeArray.length()>0){
			for( int i=0;i<partyMeetingTypeArray.length();i++){
				partyMeetingTypeValues.add(Long.valueOf(partyMeetingTypeArray.getString(i)));
			}
		}
		Long locationId = jObj.getLong("locationId");
		String locationType = jObj.getString("locationType");
		//partyMeetingsVOList = coreDashboardPartyMeetingService.getPartyMeetingCommentsDetails(activityMemberId,stateId,fromDate,toDate,partyMeetingTypeValues,meetingStatus,meetingLevel,isComment,locationId,locationType);
		sessionVOList = coreDashboardPartyMeetingService.getPartyMeetingsSessionWiseIndividualDetails(activityMemberId,stateId,fromDate,toDate,partyMeetingTypeValues,meetingStatus,meetingLevel,isComment,locationId,locationType);
		
	} catch (Exception e) {
		LOG.error("Exception raised at getPartyMeetingCommentsDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}

public String getPartyMeetingCommentsDetails(){
	try {
		LOG.info("Entered into getPartyMeetingCommentsDetails()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		String meetingStatus = jObj.getString("meetingStatus");
		String meetingLevel = jObj.getString("meetingLevel");
		String isComment = jObj.getString("isComment");
		List<Long> partyMeetingTypeValues=new ArrayList<Long>();
		JSONArray partyMeetingTypeArray=jObj.getJSONArray("partyMeetingTypeArr");
		if(partyMeetingTypeArray!=null &&  partyMeetingTypeArray.length()>0){
			for( int i=0;i<partyMeetingTypeArray.length();i++){
				partyMeetingTypeValues.add(Long.valueOf(partyMeetingTypeArray.getString(i)));
			}
		}
		Long locationId = jObj.getLong("locationId");
		String locationType = jObj.getString("locationType");
		partyMeetingsVOList = coreDashboardPartyMeetingService.getPartyMeetingCommentsDetails(activityMemberId,stateId,fromDate,toDate,partyMeetingTypeValues,meetingStatus,meetingLevel,isComment,locationId,locationType);
		
		//sessionVOList = coreDashboardPartyMeetingService.getPartyMeetingsSessionWiseIndividualDetails(activityMemberId,stateId,fromDate,toDate,partyMeetingTypeValues,meetingStatus,meetingLevel,isComment,locationId,locationType);
		
	} catch (Exception e) {
		LOG.error("Exception raised at getPartyMeetingCommentsDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}

public String getPartyMeetingComulativeCommentDetails(){
	try {
		LOG.info("Entered into getPartyMeetingCommentsDetails()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		String meetingStatus = jObj.getString("meetingStatus");
		String meetingLevel = jObj.getString("meetingLevel");
		String isComment = jObj.getString("isComment");
		List<Long> partyMeetingTypeValues=new ArrayList<Long>();
		JSONArray partyMeetingTypeArray=jObj.getJSONArray("partyMeetingTypeArr");
		if(partyMeetingTypeArray!=null &&  partyMeetingTypeArray.length()>0){
			for( int i=0;i<partyMeetingTypeArray.length();i++){
				partyMeetingTypeValues.add(Long.valueOf(partyMeetingTypeArray.getString(i)));
			}
		}
		String reportType = jObj.getString("reportType");
		Long locationId = jObj.getLong("locationId");
		String locationType = jObj.getString("locationType");
		partyMeetingsVOList = coreDashboardPartyMeetingService.getPartyMeetingComulativeCommentDetails(activityMemberId,stateId,fromDate,toDate,partyMeetingTypeValues,meetingStatus,meetingLevel,isComment,reportType,locationId,locationType);
	} catch (Exception e) {
		LOG.error("Exception raised at getPartyMeetingComulativeCommentDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getDistrictByState(){
	try {
		LOG.info("Entered into getDistrictByState()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		String meetingStatus = jObj.getString("meetingStatus");
		String meetingLevel = jObj.getString("meetingLevel");
		String isComment = jObj.getString("isComment");
		List<Long> partyMeetingTypeValues=new ArrayList<Long>();
		JSONArray partyMeetingTypeArray=jObj.getJSONArray("partyMeetingTypeArr");
		if(partyMeetingTypeArray!=null &&  partyMeetingTypeArray.length()>0){
			for( int i=0;i<partyMeetingTypeArray.length();i++){
				partyMeetingTypeValues.add(Long.valueOf(partyMeetingTypeArray.getString(i)));
			}
		}
		partyMeetingsVOList = coreDashboardPartyMeetingService.getDistrictByState(activityMemberId,stateId,fromDate,toDate,partyMeetingTypeValues,meetingStatus,meetingLevel,isComment);
	} catch (Exception e) {
		LOG.error("Exception raised at getDistrictByState() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getConstituencyByDistrict(){
	try {
		LOG.info("Entered into getConstituencyByDistrict()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long districtId = jObj.getLong("districtId");
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		String meetingStatus = jObj.getString("meetingStatus");
		String meetingLevel = jObj.getString("meetingLevel");
		String isComment = jObj.getString("isComment");
		List<Long> partyMeetingTypeValues=new ArrayList<Long>();
		JSONArray partyMeetingTypeArray=jObj.getJSONArray("partyMeetingTypeArr");
		if(partyMeetingTypeArray!=null &&  partyMeetingTypeArray.length()>0){
			for( int i=0;i<partyMeetingTypeArray.length();i++){
				partyMeetingTypeValues.add(Long.valueOf(partyMeetingTypeArray.getString(i)));
			}
		}
		partyMeetingsVOList = coreDashboardPartyMeetingService.getConstituencyByDistrictId(districtId,activityMemberId,stateId,fromDate,toDate,partyMeetingTypeValues,meetingStatus,meetingLevel,isComment);
	} catch (Exception e) {
		LOG.error("Exception raised at getConstituencyByDistrict() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getMandalByConstituency(){
	try {
		LOG.info("Entered into getMandalByConstituency()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long constituencyId = jObj.getLong("constituencyId");
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		String meetingStatus = jObj.getString("meetingStatus");
		String meetingLevel = jObj.getString("meetingLevel");
		String isComment = jObj.getString("isComment");
		List<Long> partyMeetingTypeValues=new ArrayList<Long>();
		JSONArray partyMeetingTypeArray=jObj.getJSONArray("partyMeetingTypeArr");
		if(partyMeetingTypeArray!=null &&  partyMeetingTypeArray.length()>0){
			for( int i=0;i<partyMeetingTypeArray.length();i++){
				partyMeetingTypeValues.add(Long.valueOf(partyMeetingTypeArray.getString(i)));
			}
		}
		partyMeetingsVOList = coreDashboardPartyMeetingService.getMandalByConstituyId(constituencyId,activityMemberId,stateId,fromDate,toDate,partyMeetingTypeValues, meetingStatus,meetingLevel,isComment);
	} catch (Exception e) {
		LOG.error("Exception raised at getMandalByConstituency() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getCadreRegistrationCountByConstituency(){  
    try{
    	jObj = new JSONObject(getTask()); 
    	String fromDate = jObj.getString("fromDate");
    	String toDate = jObj.getString("toDate");
    	Long constituencyId = jObj.getLong("constituencyId");
      object = coreDashboardCadreRegistrationService.getCadreRegistrationCountByConstituency(constituencyId,fromDate,toDate);
      
    }catch(Exception e){  
      LOG.error("Exception raised at getCadreRegistrationCountByConstituency() method of CoreDashBoard", e);
    }
    return Action.SUCCESS;
  }
public String getDaysByCadreRegistrationCount(){  
    try{
    	jObj = new JSONObject(getTask()); 
    	String fromDate = jObj.getString("fromDate");
    	String toDate = jObj.getString("toDate");
    	Long constituencyId = jObj.getLong("constituencyId");
    	Long tabUserInfoId = jObj.getLong("tabUserInfoId");
    	Long cadreSurveyUserId = jObj.getLong("cadreSurveyUserId");
      object = coreDashboardCadreRegistrationService.getDaysByCadreRegistrationCount(constituencyId,cadreSurveyUserId,tabUserInfoId,fromDate,toDate);
    }catch(Exception e){  
      LOG.error("Exception raised at getCadreRegistrationCountByConstituecny() method of CoreDashBoard", e);
    }
    return Action.SUCCESS;
  }


public String getEnumeratorsInfo(){
try{
  
  String retrieveType = request.getParameter("retrieveType");
  cadreRegistratedCountVO = coreDashboardCadreRegistrationService.getEnumeratorsInfo(retrieveType);
  
}catch(Exception e){
  LOG.error("Exception raised at getEnumeratorsInfo() method of CoreDashBoard", e);
}
return Action.SUCCESS;
}
public String getCadreLastUpdatedTime(){
	try{
		status = coreDashboardCadreRegistrationService.getCadreLastUpdatedTime();
	}catch(Exception e){
		LOG.error("Exception raised at getCadreLastUpdatedTime() method of CoreDashBoard", e);	
	}
	return Action.SUCCESS;
}
public String getNotReceiveRegistrationPerson(){  
    try{
    	jObj = new JSONObject(getTask()); 
    	String date = jObj.getString("date");
    	Long constituencyId = jObj.getLong("constituencyId");
      object = coreDashboardCadreRegistrationService.getNoRegistrationReceiveTabUserPersonCountByTimeWise(constituencyId,date);
      
    }catch(Exception e){  
      LOG.error("Exception raised at getNotReceiveRegistrationPerson() method of CoreDashBoard", e);
    }
    return Action.SUCCESS;
  }
public String getTabUserInfoDetails(){  
    try{
    	jObj = new JSONObject(getTask()); 
    	String tabUserInfoStrIds = jObj.getString("tabUserInfoStrIds");
      object = coreDashboardCadreRegistrationService.getTabUserInfoDetails(tabUserInfoStrIds);
    }catch(Exception e){  
      LOG.error("Exception raised at getTabUserInfoDetails() method of CoreDashBoard", e);
    }
    return Action.SUCCESS;
  }
public String getTrainingProgramMemberDtlsStatusWise(){
	try{
		jObj = new JSONObject(getTask());
		String dateStr = jObj.getString("dateStr");
		Long stateId = jObj.getLong("stateId");
		String status = jObj.getString("status");
		String designation = jObj.getString("designation");
		Long designationId = jObj.getLong("designationId");
		JSONArray programIdArr = jObj.getJSONArray("programIdArr");
		List<Long> programIdList = new ArrayList<Long>();
		for( int i=0;i<programIdArr.length();i++){
			programIdList.add(Long.valueOf(programIdArr.getString(i)));
		}
		idNameVoList = coreDashboardMainService.getTrainingProgramMemberDtlsStatusWise(programIdList,stateId,dateStr,status,designation,designationId);
	}catch(Exception e){
		LOG.error("Exception raised at getTrainingProgramMemberDtlsStatusWise() method of CoreDashBoardAction", e);
	}
	return Action.SUCCESS;
}

public String getRegistrationPersonDetails(){
	try{
		jObj = new JSONObject(getTask());
		String status = jObj.getString("status");
		Long voterId = jObj.getLong("voterId");
		Long familyVoterId = jObj.getLong("familyVoterId");
		Long cadreId = jObj.getLong("cadreId");
		
		newCadreRegistrationVO = coreDashboardCadreRegistrationService.getRegistrationPersonDetails(voterId,familyVoterId,cadreId,status);
	}catch(Exception e){
		LOG.error("Exception raised at getRegistrationPersonDetails() method of CoreDashBoardAction", e);
	}
	return Action.SUCCESS;
}
public String getStateLevelCampDetailsDayWise(){
	try{
		jObj = new JSONObject(getTask());
		Long stateId = jObj.getLong("stateId");
		String dateStr = jObj.getString("dateStr");
		JSONArray programIdArr = jObj.getJSONArray("programIdArr");  
		List<Long> programIdList = new ArrayList<Long>();
		for( int i=0;i<programIdArr.length();i++){
			programIdList.add(Long.valueOf(programIdArr.getString(i)));
		}
		idNameVOsList = coreDashboardMainService.getStateLevelCampDetailsDayWise(programIdList,stateId,dateStr);
		
	}catch(Exception e){
		LOG.error("Exception raised at getStateLevelCampDetailsDayWise() method of CoreDashBoardAction", e);
	}
	return Action.SUCCESS;
}

public String cadreRegistrationOverviewAction(){
	try {
			RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
			
			if(regVO==null){
				return "input";
			}
			boolean noaccess = false;
			List<String> entitlements = null;
			if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
				entitlements = regVO.getEntitlements();
				if(!(entitlements.contains("MEMBERSHIP_DRIVE_CONSTITUENCY_OVERVIEW_ENTITLEMENT") || entitlements.contains("MEMBERSHIP_DRIVE_CONSTITUENCY_OVERVIEW_ADMIN_ENTITLEMENT"))){
					noaccess = true ;
				}
		
			if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
				noaccess = false;
			}
			if(noaccess){
				return "error";  
			}
		}
		constId = Long.valueOf(regVO.getAccessValue());
		constName = regVO.getFirstName();
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return Action.SUCCESS;
}

public String getStatewisesCastNames(){
	  try {
		  jObj = new JSONObject(getTask());
		
		Long stateId = jObj.getLong("stateId");
		
		IdAndNameVOList = coreDashboardCadreRegistrationService.getStatewisesCastNames(stateId);
	} catch (Exception e) {
		LOG.error("Entered into getStatewisesCastNames method in CoreDashBoardAction");
	}
	  return Action.SUCCESS;
}

public String getEducationalQualifications(){
	  try {
		IdAndNameVOList = coreDashboardCadreRegistrationService.getEducationalQualifications();
	} catch (Exception e) {
		LOG.error("Entered into getEducationalQualifications method in getEducationalQualifications");
	}
	  return Action.SUCCESS;
}

public String getAllRelationDetails(){
	  try {
		IdAndNameVOList = coreDashboardCadreRegistrationService.getAllRelationDetails();
	} catch (Exception e) {
		LOG.error("Entered into getAllRelationDetails method in getEducationalQualifications");
	}
	  return Action.SUCCESS;
}
public String savingCadreDetails(){
	try {
		final HttpSession session = request.getSession();
		final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(!cadreRegistrationVO.getDataSourceType().equalsIgnoreCase("ONLINE") && (user == null || user.getRegistrationID() == null)){
			return ERROR;
		}
		
		//Map<File,String> mapfiles = new HashMap<File,String>();
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
			   	            	
			   	            	ImageAndStringConverter imageAndStringConverter = new ImageAndStringConverter();
								String imageBase64String  = imageAndStringConverter.convertImageFileToBase64String(f);
								cadreRegistrationVO.setImageBase64String(imageBase64String);
			   	            	//cadreRegistrationVO.setUploadImage(f);
			   	            	//mapfiles.put(f,ext);
			   	            }
			   	        
			   			}
	   		}
	   
		if(user != null)
			cadreRegistrationVO.setWebUserId(user.getRegistrationID());
		//cadreRegistrationVO.setDataSourceType("WEB");// srishailam : dont remove comment it , i already mentioned in web registration page as web for this property
		
		if(cadreRegistrationVO.getFamilyVoterId() != null && cadreRegistrationVO.getFamilyVoterId().longValue()>0L){
    		cadreRegistrationVO.setPhotoType(IConstants.CADRE_IMAGE_TYPE_NEW);
		}else if(cadreRegistrationVO.getIsNewImageExist().equalsIgnoreCase("newImage") ){
			cadreRegistrationVO.setPhotoType("NEW");
		}else if(cadreRegistrationVO.getIsNewImageExist().equalsIgnoreCase("existImage")){
			if(cadreRegistrationVO.getTdpCadreId() != null && cadreRegistrationVO.getTdpCadreId().longValue() > 0l){
				cadreRegistrationVO.setPhotoType("CADRE");
			}else if(cadreRegistrationVO.getVoterId() != null){
				cadreRegistrationVO.setPhotoType("VOTER");
			}
		}
		CadreResponseVO responceVO =coreDashboardCadreRegistrationService.savingCadreDetails(cadreRegistrationVO);
		if(responceVO!=null && responceVO.getSaveStatus().equalsIgnoreCase("Success")){
			
			if(cadreRegistrationVO.getDataSourceType() != null && cadreRegistrationVO.getDataSourceType().equalsIgnoreCase("WEB"))
				inputStream = new StringBufferInputStream("SUCCESS" +"," +responceVO.getRefNo()+"," +//1
		                ""+responceVO.getMemberShipNo().trim()+"");
			else{
				PaymentGatewayVO pamentGateWayVO = paymentGatewayService.getPaymentBasicInfoByPaymentGateWayType(1L,responceVO.getMemberShipNo().trim(),responceVO.getRefNo().trim(),"2016 CADRE ONLINE REGISTRATION","NORMAL REGISTRATION",cadreRegistrationVO.getDeliveryLocation());			            
				inputStream = new StringBufferInputStream("SUCCESS" +"," +responceVO.getRefNo()+"," +//1
						""+responceVO.getMemberShipNo().trim()+"," +//2
						""+pamentGateWayVO.getOrderNo().trim()+"," +//3
						""+pamentGateWayVO.getCheckSum().trim()+"," +//4
						""+pamentGateWayVO.getRedirectURL().trim()+"," +//5
						""+pamentGateWayVO.getAmount().trim()+",");//6
				//inputStream = new StringBufferInputStream(status);
			}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		LOG.error("Exception raised at savingCadreDetails", e);
	}
	
	return Action.SUCCESS;
}

public String updateTransactionTrackingDetals(){
	try {
		jObj = new JSONObject(getTask());
		String orderId = jObj.getString("orderId");
		status = paymentGatewayService.updateTransactionTrackingDetals(orderId,"STARTED");
	} catch (Exception e) {
		LOG.error("Exception raised at updateTransactionTrackingDetals", e);
	}
	return Action.SUCCESS;
}
public String registrationsSuccess(){
	try {
		//enrollMentNO = md5Algoritm.generateMD5Decrypt(en.toString().trim());
		enrollMentNO = en.toString().trim();
			//membershipNo = md5Algoritm.generateMD5Decrypt(mn.toString().trim());
			membershipNo = mn.toString().trim();
			resultStatus = cadreRegistrationService.updatePaymenntStatus(1L,membershipNo,AuthDesc,"2016 CADRE REGISTRATION","NORMAL REGISTRATION",enrollMentNO);
			
			if(AuthDesc != null){
				if(!AuthDesc.trim().equalsIgnoreCase("Y"))
					status="failure";
				else if(AuthDesc.trim().equalsIgnoreCase("Y"))// && resultStatus != null && resultStatus.getResultCode()==0)
					status="success";
				else
					status="failure";
			}
			else{
				status="failure";
			}
	
		
	} catch (Exception e) {
		LOG.error("Exception raised in registrationSuccess method in CadreRegistrationAction Action",e);
	}
	return Action.SUCCESS;
}
public String validateUpdateVoterDetails(){
	try {
		jObj = new JSONObject(getTask());
		String voterCardNo = jObj.getString("voterCardNo");
		
		newCadreRegistrationVO = coreDashboardCadreRegistrationService.validateUpdateVoterDetails(voterCardNo);
	} catch (Exception e) {
		LOG.error("Exception raised in validateUpdateVoterDetails method in CadreRegistrationAction Action",e);
	}
	return Action.SUCCESS;
}
public String getParyMeetingMemberDtls(){
	
	try{
		
		jObj = new JSONObject(getTask());
		
		Long meetingId = jObj.getLong("meetingId");
		Long partyMeetingMainTypeId = jObj.getLong("meetingMainTypeId");
		List<Long> partyMeetingTypeIds = new ArrayList<Long>();
		JSONArray partyMeetingTypeIdsArray=jObj.getJSONArray("meetingTypeIdArr");
		if(partyMeetingTypeIdsArray!=null &&  partyMeetingTypeIdsArray.length()>0){
			for( int i=0;i<partyMeetingTypeIdsArray.length();i++){
				partyMeetingTypeIds.add(Long.valueOf(partyMeetingTypeIdsArray.getString(i)));
			}
		}
		
		String state = jObj.getString("state");
		String startDateString = jObj.getString("startDateString");
		String endDateString   = jObj.getString("endDateString");    
		String status   = jObj.getString("status");
		String searchDesignation = jObj.getString("searchDesignation");
		
		idNameVOs = coreDashboardPartyMeetingService.getParyMeetingMemberDtls(partyMeetingMainTypeId,partyMeetingTypeIds,meetingId,state,startDateString,endDateString,status,searchDesignation);
		
}catch(Exception e){
	LOG.error("Exception raised at getParyMeetingMemberDtls() method of CoreDashBoard", e);
}
return Action.SUCCESS;
}
public String getStateLevelCampAttendedDetails(){  
	try {
		jObj = new JSONObject(getTask());
		List<Long> programIdList = new ArrayList<Long>();
		String dateStr = jObj.getString("dateStr");
		Long stateId = jObj.getLong("stateId");
		JSONArray programIdArr=jObj.getJSONArray("programIdArr");  
		if(programIdArr!=null &&  programIdArr.length()>0){
			for( int i=0;i<programIdArr.length();i++){
				programIdList.add(Long.valueOf(programIdArr.getString(i)));
			}
		}
		
		List<Long> enrollYrIds = new ArrayList<Long>();
		JSONArray enrollmntYrIdArr=jObj.getJSONArray("enrollmentYrIds");  
		if(enrollmntYrIdArr!=null &&  enrollmntYrIdArr.length()>0){
			for( int i=0;i<enrollmntYrIdArr.length();i++){
				enrollYrIds.add(Long.valueOf(enrollmntYrIdArr.getString(i)));
			}
		}
		String option = jObj.getString("option");
		
		idNameVoList = coreDashboardMainService.getStateLevelCampAttendedDetails(programIdList,stateId,dateStr,option,enrollYrIds);   
		
	} catch (Exception e) {
		LOG.error("Exception raised at getStateLevelCampAttendedDetails", e);   
	}
	return Action.SUCCESS; 
}
public String getStateLevelCampDetailsRepresentative(){
	try{
		jObj = new JSONObject(getTask());
		List<Long> programIdList = new ArrayList<Long>();
		String dateStr = jObj.getString("dateStr");
		Long stateId = jObj.getLong("stateId");
		JSONArray programIdArr=jObj.getJSONArray("programIdArr");
		if(programIdArr!=null &&  programIdArr.length()>0){
			for( int i=0;i<programIdArr.length();i++){
				programIdList.add(Long.valueOf(programIdArr.getString(i))); 
			}
		}
		List<Long> enrollYrIds = new ArrayList<Long>();
		JSONArray enrollmntYrIdArr=jObj.getJSONArray("enrollmentYrIds");  
		if(enrollmntYrIdArr!=null &&  enrollmntYrIdArr.length()>0){
			for( int i=0;i<enrollmntYrIdArr.length();i++){
				enrollYrIds.add(Long.valueOf(enrollmntYrIdArr.getString(i)));
			}
		}
		idNameVOsList = coreDashboardMainService.getStateLevelCampDetailsRepresentative(programIdList,stateId,dateStr,enrollYrIds);
	}catch(Exception e){
		LOG.error("Exception raised at getStateLevelCampAttendedDetails", e);
	}
	return Action.SUCCESS;      
}
public String getToursBasicOverviewCountDetails(){
	try {
		LOG.info("Entered into getToursBasicOverviewCountDetails()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		Long userTypeId = jObj.getLong("userTypeId");
		toursBasicVO = coreDashboardToursService.getToursBasicOverviewCountDetails(stateId,fromDate,toDate,activityMemberId,userTypeId);
	} catch (Exception e) {
		LOG.error("Exception raised at getToursBasicOverviewCountDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getDistrictWiseToursSubmitedDetails(){
	try {
		LOG.info("Entered into getDistrictWiseToursSubmitedDetails()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		Long userTypeId = jObj.getLong("userTypeId");
		toursDtlsList = coreDashboardToursService.getDistrictWiseToursSubmitedDetails(stateId,fromDate,toDate,activityMemberId,userTypeId);
	} catch (Exception e) {
		LOG.error("Exception raised at getDistrictWiseToursSubmitedDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getLocationWiseCadreInfoTodayDetails(){
	try {
		LOG.info("Entered into getLocationWiseCadreInfoTodayDetails()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long stateId = jObj.getLong("stateId");
		List<Long> locationList = new ArrayList<Long>();
		JSONArray locationIdsArr=jObj.getJSONArray("locationIdsArr");
		if(locationIdsArr!=null &&  locationIdsArr.length()>0){
			for( int i=0;i<locationIdsArr.length();i++){
				locationList.add(Long.valueOf(locationIdsArr.getString(i))); 
			}
		}
		cadreDtlsList = coreDashboardCadreRegistrationService.getLocationWiseCadreInfoTodayDetails(stateId,locationList);
	} catch (Exception e) {
		LOG.error("Exception raised at getLocationWiseCadreInfoTodayDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getMndlMncpalityTodayStatedNotStartedDetails(){
	try {
		LOG.info("Entered into getMndlMncpalityTodayStatedNotStartedDetails()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long stateId = jObj.getLong("stateId");
		List<Long> locationList = new ArrayList<Long>();
		JSONArray locationIdsArr=jObj.getJSONArray("locationIdsArr");
		if(locationIdsArr!=null &&  locationIdsArr.length()>0){
			for( int i=0;i<locationIdsArr.length();i++){
				locationList.add(Long.valueOf(locationIdsArr.getString(i))); 
			}
		}
		cadreDtlsList = coreDashboardCadreRegistrationService.getMandalMuncipalityStatedAndNotStatedDetails(stateId,locationList);
	} catch (Exception e) {
		LOG.error("Exception raised at getMndlMncpalityTodayStatedNotStartedDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getTodayAndYesterdayTabUserRgstrtnComparisonDetails(){
	try {
		LOG.info("Entered into getTodayAndYesterdayTabUserRgstrtnComparisonDetails()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long stateId = jObj.getLong("stateId");
		cadreDtlsList = coreDashboardCadreRegistrationService.getTodayAndYesterdayTabUserRgstrtnComparisonDetails(stateId);
	} catch (Exception e) {
		LOG.error("Exception raised at getTodayAndYesterdayTabUserRgstrtnComparisonDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getLocationWiseRegistrationSMSTracking(){
	try{
		
		status = coreDashboardCadreRegistrationService.getLocationWiseRegistrationSMSTracking();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return Action.SUCCESS;
}
public String getUserTrackingDtslBySurveyUserId(){
	try {
		LOG.info("Entered into getUserTrackingDtslBySurveyUserId()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long constId = jObj.getLong("constId");
		Long fieldUserId = jObj.getLong("fieldUserId");
		Long surveyUserId = jObj.getLong("surveyUserId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		Long fromTime = jObj.getLong("fromTime");
		Long toTime = jObj.getLong("toTime");
		cadreVO = coreDashboardCadreRegistrationService.getUserTrackingDtslBySurveyUserId(surveyUserId,fromDate,toDate,fieldUserId,constId,fromTime,toTime);
	} catch (Exception e) {
		LOG.error("Exception raised at getUserTrackingDtslBySurveyUserId() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getTopPoorToursLocationDetails(){
	try {
		LOG.info("Entered into getTopPoorToursLocationDetails()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		Long userTypeId = jObj.getLong("userTypeId");
		toursBasicVO = coreDashboardToursService.getTopPoorToursLocationDetails(activityMemberId,userTypeId,stateId,fromDate,toDate);
	} catch (Exception e) {
		LOG.error("Exception raised at getTopPoorToursLocationDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getTourSubmittedLeadersDetails(){
	try {
		LOG.info("Entered into getTourSubmittedLeadersDetails()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		String isTourSubmitted = jObj.getString("isTourSubmitted");
		List<Long> desgnatnIdsLst = new ArrayList<Long>();
		JSONArray designationIdsArr=jObj.getJSONArray("designationIds");
		if(designationIdsArr!=null &&  designationIdsArr.length()>0){
			for( int i=0;i<designationIdsArr.length();i++){
				desgnatnIdsLst.add(Long.valueOf(designationIdsArr.getString(i))); 
			}
		}
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		Long userTypeId = jObj.getLong("userTypeId");
		toursDtlsList = coreDashboardToursService.getTourSubmittedLeadersDetails(desgnatnIdsLst,isTourSubmitted,fromDate,toDate,activityMemberId,stateId,userTypeId);
	} catch (Exception e) {
		LOG.error("Exception raised at getTourSubmittedLeadersDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String showElectronicBulletins(){
	return "success";
}
public String cadreSurveyUserAssign(){
	  return Action.SUCCESS;
}

public String getDetailsByUserName(){
	
	try{
		LOG.info("Entered into getDetailsByUserName()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		String userName = jObj.getString("userName");
		tabDetails = coreDashboardToursService.getDetailsByUserName(userName);
		
	}catch(Exception e){
		LOG.error("Exception raised at getDetailsByUserName() method of CoreDashBoard", e);
	}
	
	return Action.SUCCESS;

}

public String getUpdatedIMEINumberDetails(){
	
	try {
		LOG.info("Entered into getUpdatedIMEINumberDetails()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		String imeiNo = jObj.getString("imeiNumber");
		tabDetails = coreDashboardToursService.getUpdatedIMEINumberDetails(imeiNo);

	} catch (Exception e) {
		LOG.error("Exception raised at getUpdatedIMEINumberDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}

public String getAssigndUsrDetails(){
	try {
		LOG.info("Entered into getAssigndUsrDetails()  of CoreDashboardAction");
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		Long loginUserId = null;
		if(regVO != null)
			loginUserId = regVO.getRegistrationID();
		
		jObj = new JSONObject(getTask());
		String userName=jObj.getString("userName");
		String imeiNo = jObj.getString("imeiNumber");
		tabUserDetails = coreDashboardToursService.savingTabUserDetails(loginUserId,userName,imeiNo);

	} catch (Exception e) {
		LOG.error("Exception raised at getAssigndUsrDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getUserTrackingDtslBySurveyUserId1(){
	try {
		LOG.info("Entered into getUserTrackingDtslBySurveyUserId()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		//Long constId = jObj.getLong("constId");
		//Long fieldUserId = jObj.getLong("fieldUserId");
		Long surveyUserId = jObj.getLong("surveyUserId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		cadreVO = coreDashboardCadreRegistrationService.getUserTrackingDtslBySurveyUserId(surveyUserId,fromDate,toDate,null,null,null,null);
	} catch (Exception e) {
		LOG.error("Exception raised at getUserTrackingDtslBySurveyUserId1() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String updateUserORIMEIDetails(){
	try {
		LOG.info("Entered into updateUserORIMEIDetails()  of CoreDashboardAction");
		jObj = new JSONObject(getTask()); 
		Long loginAuthId =jObj.getLong("loginAuthId");
		tabUserDetails = coreDashboardToursService.updateUserORIMEIDetails(loginAuthId);

	} catch (Exception e) {
		LOG.error("Exception raised at getUnLockUserName() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getToursBasicOverviewDtls(){
	try {
		LOG.info("Entered into getToursBasicOverviewDtls()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		Long userTypeId = jObj.getLong("userTypeId");
		toursBasicVO = coreDashboardToursService.getToursBasicOverviewDtls(stateId,fromDate,toDate,activityMemberId,userTypeId);
	} catch (Exception e) {
		LOG.error("Exception raised at getToursBasicOverviewDtls() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getDesignationWiseMembersDtls(){
	try {
		LOG.info("Entered into getDesignationWiseMembersDtls()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		Long userTypeId = jObj.getLong("userTypeId");
		memberList = coreDashboardToursService.getDesignationWiseMembersDtls(stateId,fromDate,toDate,activityMemberId,userTypeId);
	} catch (Exception e) {
		LOG.error("Exception raised at getDesignationWiseMembersDtls() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getDesignationWiseAverageTourPerformanceDtls(){
	try {
		LOG.info("Entered into getDesignationWiseMembersDtls()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		Long userTypeId = jObj.getLong("userTypeId");
		
		//filter parameter
		List<Long> desgnatnIdsLst = new ArrayList<Long>();
		JSONArray designationIdsArr=jObj.getJSONArray("designationIds");
		if(designationIdsArr!=null &&  designationIdsArr.length()>0){
			for( int i=0;i<designationIdsArr.length();i++){
				desgnatnIdsLst.add(Long.valueOf(designationIdsArr.getString(i))); 
			}
		}
		String isFilterApply = jObj.getString("isFilterApply");
		String filterType = jObj.getString("filterType");
		Double  ownDistValue = jObj.getDouble("ownDistValue");
		Double  ownCnsttuncyValue = jObj.getDouble("ownCnsttuncyValue");
		Double  ichargeDistrictValue = jObj.getDouble("ichargeDistrictValue");
		Double  incharegeConstituencyValue = jObj.getDouble("incharegeConstituencyValue");
		Double  govtWorkValue = jObj.getDouble("govtWorkValue");
		Double  complainceValue = jObj.getDouble("complainceValue");
		Double stateTourCategoryValue = jObj.getDouble("stateTourCategoryValue");
		Double anganwadiVisitValue = jObj.getDouble("anganwadiVisitValue");
		Double ownAreaValue = jObj.getDouble("ownAreaValue");
		Double inchargeParliamentValue = jObj.getDouble("inchargeParliamentValue");
		
		
		
		
		toursDtlsList = coreDashboardToursService.getDesignationWiseAverageTourPerformanceDtls(
																								stateId,fromDate,toDate,activityMemberId,
																								userTypeId,desgnatnIdsLst,isFilterApply,filterType,
																								ownDistValue,ownCnsttuncyValue,ichargeDistrictValue,
																								incharegeConstituencyValue,govtWorkValue,stateTourCategoryValue,
																								 anganwadiVisitValue,ownAreaValue,inchargeParliamentValue,complainceValue);
	} catch (Exception e) {
		LOG.error("Exception raised at getDesignationWiseMembersDtls() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToSeeionWiseMeetingDtls(){
	
	try{
		
		jObj = new JSONObject(getTask());
		
		Long partyMeetingMainTypeId = jObj.getLong("partyMeetingMainTypeId");
		
		List<Long> partyMeetingTypeIds = new ArrayList<Long>();
		JSONArray partyMeetingTypeIdsArray=jObj.getJSONArray("partyMeetingTypeIds");
		if(partyMeetingTypeIdsArray!=null &&  partyMeetingTypeIdsArray.length()>0){
			for( int i=0;i<partyMeetingTypeIdsArray.length();i++){
				partyMeetingTypeIds.add(Long.valueOf(partyMeetingTypeIdsArray.getString(i)));
			}
		}
		List<Long> partyMeetingIds = new ArrayList<Long>();
		JSONArray partyMeetingIdsArr=jObj.getJSONArray("partyMeetingIds");
		if(partyMeetingIdsArr!=null &&  partyMeetingIdsArr.length()>0){
			for( int i=0;i<partyMeetingIdsArr.length();i++){
				partyMeetingIds.add(Long.valueOf(partyMeetingIdsArr.getString(i)));
			}
		}
		
		String state = jObj.getString("state");
		String startDateString = jObj.getString("startDateString");
		String endDateString   = jObj.getString("endDateString");
		
		
		partyMeetingDataVOList = coreDashboardPartyMeetingService.getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedMeetingSessionWise(partyMeetingMainTypeId,partyMeetingTypeIds,state,startDateString,endDateString,partyMeetingIds);
		
}catch(Exception e){
	LOG.error("Exception raised at getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToSeeionWiseMeetingDtls() method of CoreDashBoard", e);
}
return Action.SUCCESS;
}
public String getIndividualPersonTourDetails(){
	try {
		LOG.info("Entered into getIndividualPersonTourDetails()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		Long candiateId = jObj.getLong("candiateId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		toursBasicVO = coreDashboardToursService.getIndividualPersonTourDetails(fromDate,toDate,candiateId);
	} catch (Exception e) {
		LOG.error("Exception raised at getIndividualPersonTourDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getTourLeaderDtlsBasedOnSelectionType(){
	try {
		LOG.info("Entered into getTourLeaderDtlsBasedOnSelectionType()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		Long userTypeId = jObj.getLong("userTypeId");
		
		//filter parameter
		List<Long> desgnatnIdsLst = new ArrayList<Long>();
		JSONArray designationIdsArr=jObj.getJSONArray("designationIds");
		if(designationIdsArr!=null &&  designationIdsArr.length()>0){
			for( int i=0;i<designationIdsArr.length();i++){
				desgnatnIdsLst.add(Long.valueOf(designationIdsArr.getString(i))); 
			}
		}
		Long locationScopeId = jObj.getLong("locationTypeId");
		Set<Long> locationValues = new HashSet<Long>(0);
		JSONArray locationValuesArr=jObj.getJSONArray("locationValuesArr");
		if(locationValuesArr!=null &&  locationValuesArr.length()>0){
			for( int i=0;i<locationValuesArr.length();i++){
				locationValues.add(Long.valueOf(locationValuesArr.getString(i))); 
			}
		}
		String type = jObj.getString("type");
		String filterType = jObj.getString("filterType");
		toursDtlsList = coreDashboardToursService.getTourLeaderDtlsBasedOnSelectionType(stateId,fromDate,toDate,activityMemberId,userTypeId,desgnatnIdsLst,filterType,locationScopeId,locationValues,type);
																							
	} catch (Exception e) {
		LOG.error("Exception raised at getTourLeaderDtlsBasedOnSelectionType() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}

public String getActivityDetails(){
	try {
		LOG.info("Entered into getActivityDetails()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		
		IdAndNameVOList = coreDashboardService.getActivityDetails(fromDate, toDate);
	} catch (Exception e) {
		LOG.error("Exception raised at getActivityDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}

public String getActivityOverAllSummary(){
	try {
		LOG.info("Entered into getActivityOverAllSummary()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		
		Long activityId = jObj.getLong("activityId");
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		Long userTypeId = jObj.getLong("userTypeId");
		
		IdAndNameVOList = coreDashboardService.getActivityOverAllSummary(activityId,activityMemberId,stateId,userTypeId);
	} catch (Exception e) {
		LOG.error("Exception raised at getActivityOverAllSummary() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String activitiesDistrictWiseCohort(){
	try {
		LOG.info("Entered into activitiesDistrictWiseCohort()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		
		List<Long> activityIdsLst = new ArrayList<Long>();
		JSONArray activityIdsArr=jObj.getJSONArray("activityId");
		if(activityIdsArr!=null &&  activityIdsArr.length()>0){
			for( int i=0;i<activityIdsArr.length();i++){
				activityIdsLst.add(Long.valueOf(activityIdsArr.getString(i))); 
			}
		}
		String fromDate = jObj.getString("fromDate");
		String toDate = jObj.getString("toDate");
		Long scopeId = jObj.getLong("scopeId");
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		Long userTypeId = jObj.getLong("userTypeId");
		IdAndNameVOList = coreDashboardService.activitiesDistrictWiseCohort(activityIdsLst,fromDate, toDate,scopeId,activityMemberId,stateId,userTypeId);
	} catch (Exception e) {
		LOG.error("Exception raised at activitiesDistrictWiseCohort() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
	
}

public String getDistrictWiseActivityCounts(){
	try {
		LOG.info("Entered into getActivityOverAllSummary()  of CoreDashboardAction");
		jObj = new JSONObject(getTask());
		
		Long districtId = jObj.getLong("districtId");
		Long activityScopeId = jObj.getLong("activity_scope_id");
		String searchType = jObj.getString("search_type");
		Long stateId = jObj.getLong("stateId");
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long userTypeId = jObj.getLong("userTypeId");
		String showType = jObj.getString("showType");
		
		eventDetailsVOList = coreDashboardService.getDistrictWiseActivityCounts(districtId,activityScopeId,searchType,stateId,activityMemberId,userTypeId,showType);
	} catch (Exception e) {
		LOG.error("Exception raised at getDistrictWiseActivityCounts() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getUserTypeActivityConductedCnt(){
try{
		
		jObj = new JSONObject(getTask());
		
		final HttpSession session = request.getSession();
		Long userId = null;
		final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null || user.getRegistrationID() == null){
			//return ERROR;
			userId = 1L;
		}
		else
			userId = user.getRegistrationID();
		
		Long activityMemberId = jObj.getLong("activityMemberId");
		
		List<Long> activityIds = new ArrayList<Long>();
		List<Long> activityLevelList = new ArrayList<Long>(0);
		JSONArray activityIdsArray=jObj.getJSONArray("activityIds");
		if(activityIdsArray!=null &&  activityIdsArray.length()>0){
			for( int i=0;i<activityIdsArray.length();i++){
				activityIds.add(Long.valueOf(activityIdsArray.getString(i)));
			}
		}
		JSONArray activityLevelIdsArray=jObj.getJSONArray("activityLevelIds");
		if(activityLevelIdsArray!=null &&  activityLevelIdsArray.length()>0){
			for( int i=0;i<activityLevelIdsArray.length();i++){
				activityLevelList.add(Long.valueOf(activityLevelIdsArray.getString(i)));
			}
		}
	    String fromDateStr = jObj.getString("fromDateStr");
	    String toDateStr = jObj.getString("toDateStr");
		Long stateId = jObj.getLong("stateId");
		Long userTypeId = jObj.getLong("userTypeId");
		
		userTypeVOList = coreDashboardEventsActivitiesService.getUserTypeActivityConductedCnt(activityIds,activityLevelList,activityMemberId,userId,userTypeId,stateId,fromDateStr,toDateStr);
		
}catch(Exception e){
	LOG.error("Exception raised at getUserTypeActivityConductedCnt() method of CoreDashBoard", e);
}
return Action.SUCCESS;
}
public String insertDataInToPartyMeetingStatusTable()
{
	try
	{
		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
		
		jObj = new JSONObject(getTask());
		partyMeetingStatus = coreDashboardPartyMeetingService.insertDataInToPartyMeetingStatusTable();
		
	}catch(Exception e)
	{
		LOG.info("\n\n pushDataToPartyMeetingStatusTable "); 
	}
	return Action.SUCCESS;
}

/*public String getSelectedChildTypeMembersForActivity(){
	try{
		
		jObj = new JSONObject(getTask()); 
	    Long parentActivityMemberId = jObj.getLong("parentActivityMemberId");
		
		List<Long> childUserTypeIds=new ArrayList<Long>();
		JSONArray childUserTypeIdsArray=jObj.getJSONArray("childUserTypeIdsArray");
		if(childUserTypeIdsArray!=null &&  childUserTypeIdsArray.length()>0){
			for( int i=0;i<childUserTypeIdsArray.length();i++){
				childUserTypeIds.add(Long.valueOf(childUserTypeIdsArray.getString(i)));
			}
		}
		String reportType = jObj.getString("reportType");
		Long stateId = jObj.getLong("stateId");
		List<Long> activitiesIds = new ArrayList<Long>();
		JSONArray activitiesIdsArray=jObj.getJSONArray("activityIds");
		if(activitiesIdsArray!=null &&  activitiesIdsArray.length()>0){
			for( int i=0;i<activitiesIdsArray.length();i++){
				activitiesIds.add(Long.valueOf(activitiesIdsArray.getString(i)));
			}
		}
		String searchType = jObj.getString("searchType");
		Long activtyScopeId = jObj.getLong("scopeId");
		activityMembersList = coreDashboardEventsActivitiesService.getSelectedChildMembersForActivties(parentActivityMemberId,childUserTypeIds,reportType,stateId,activitiesIds,searchType,activtyScopeId);
	}catch (Exception e) {
		LOG.error("Exception raised at getSelectedChildTypeMembersForActivity() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}*/

public String getCandidateWiseDebateDetailsOfCore(){
	try {
		
		jObj = new JSONObject(getTask()); 
		JSONArray debateLocationIdArry = jObj.getJSONArray("debateLocationIdArry");  
		List<Long> debateLocationIdList = new ArrayList<Long>();
		if(debateLocationIdArry != null && debateLocationIdArry.length() > 0){
			for (int i = 0; i < debateLocationIdArry.length(); i++){
				debateLocationIdList.add(Long.parseLong(debateLocationIdArry.getString(i)));          
			}  
		}
		JSONArray debateParticipantLocIdArry = jObj.getJSONArray("participantLocIdArry");  
		List<Long> debateParticipantLocationIdList = new ArrayList<Long>();
		if(debateParticipantLocIdArry != null && debateParticipantLocIdArry.length() > 0){
			for (int i = 0; i < debateParticipantLocIdArry.length(); i++){
				debateParticipantLocationIdList.add(Long.parseLong(debateParticipantLocIdArry.getString(i)));          
			}  
		}
		codeDebateVoList = coreDashboardMainService.getCandidateWiseDebateDetailsOfCore(jObj.getLong("partyId"),jObj.getString("startDate"),jObj.getString("endDate"),jObj.getLong("candidateId"),debateLocationIdList,debateParticipantLocationIdList,jObj.getLong("roleId"),jObj.getLong("designationId"));
		
	} catch (Exception e) {
		LOG.info("\n\n getCandidateWiseDebateDetailsOfCore ");
	}
	return Action.SUCCESS;
}
public String getCustomPartyMeetingsMainTypeOverViewDataDetails(){
	  try{
			LOG.info("Entered into getCustomPartyMeetingsMainTypeOverViewDataDetails()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long partyMeetingMAinTypeId = jObj.getLong("partyMeetingMAinTypeId");
			Long activityMemberId = jObj.getLong("activityMemberId");
	
			partyMeetingDataVOList = coreDashboardPartyMeetingService.getCustomPartyMeetingsMainTypeOverViewData(activityMemberId,partyMeetingMAinTypeId,stateId,fromDate,toDate);
		}catch(Exception e){
			LOG.error("Exception raised at getCustomPartyMeetingsMainTypeOverViewDataDetails() method of CoreDashBoardAction", e);
		}
		return Action.SUCCESS;
}
public String getDistrictsForCustomMeetingImgesLst(){
	 try{
		 
		 jObj = new JSONObject(getTask());
		    Long activityMemberId = jObj.getLong("activityMemberId");
			Long stateId = jObj.getLong("stateId");
			Long partyMeetingLevelId = jObj.getLong("partyMeetingLevelId");
			String startDateStr = jObj.getString("fromDate");
			String endDateStr = jObj.getString("toDate");
			Long meetingId = jObj.getLong("meetingId");
			Long meetingGrpId = jObj.getLong("meetingGrpId");
			Long locationvalue = jObj.getLong("locationValue");
			partyMeetingDataVOList = coreDashboardPartyMeetingService.getDistrictsForCustomMeetingImgesLst(activityMemberId,stateId,partyMeetingLevelId,startDateStr,endDateStr,meetingId,meetingGrpId,locationvalue);
	 }catch(Exception e){
		 LOG.error("Exception raised at getDistrictsForCustomMeetingImgesLst()", e);
	 }
	 return Action.SUCCESS;
}
public String getConstByDistrictIdForWiseCustomPartyMeetings(){
	 try{
		 
		 jObj = new JSONObject(getTask());
		 partyMeetingDataVOList = coreDashboardPartyMeetingService.getConstByDistrictIdForWiseCustomPartyMeetings(jObj.getLong("partyMeetingLevelId"),jObj.getLong("districtId"));
	 }catch(Exception e){
		 LOG.error("Exception raised at getConstByDistrictIdForWiseCustomPartyMeetings()", e);
	 }
	 return Action.SUCCESS;
}
public String getMandOrMuncByconstituencyIdForWiseCustomPartyMeetings(){
	 try{
		 
		 jObj = new JSONObject(getTask());
		 partyMeetingDataVOList = coreDashboardPartyMeetingService.getMandOrMuncByconstituencyIdForWiseCustomPartyMeetings(jObj.getLong("partyMeetingLevelId"),jObj.getLong("constituencyId"));
	 }catch(Exception e){
		 LOG.error("Exception raised at getMandOrMuncByconstituencyIdForWiseCustomPartyMeetings()", e);
	 }
	 return Action.SUCCESS;
}
public String getPanchayatOrWardsByMandalOrMuncIdForWiseCustomPartyMeetings(){
	 try{
		 
		 jObj = new JSONObject(getTask());
		 partyMeetingDataVOList = coreDashboardPartyMeetingService.getPanchayatOrWardsByMandalOrMuncIdForWiseCustomPartyMeetings(jObj.getLong("partyMeetingLevelId"),jObj.getLong("mandalOrMuncipalityId"));
	 }catch(Exception e){
		 LOG.error("Exception raised at getPanchayatOrWardsByMandalOrMuncIdForWiseCustomPartyMeetings()", e);
	 }
	 return Action.SUCCESS;
}
public String getCustomWisePartyMeetingDocuments()
{
	try {
		
		HttpSession session = request.getSession();
		RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
		jObj = new JSONObject(getTask());
		
		String fromDateStr = jObj.getString("fromDateStr");	
		String toDateStr = jObj.getString("toDateStr");
		int startIndex = jObj.getInt("startIndex");
		int maxIndex = jObj.getInt("maxIndex");
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		Long partyMeetingLevelId = jObj.getLong("partyMeetingLevelId");
		Long meetingId = jObj.getLong("meetingId");
		Long meetingGrpId = jObj.getLong("meetingGrpId");
		Long locationValue = jObj.getLong("locationValue");
		partyMeetingDataVOList = coreDashboardPartyMeetingService.getCustomWisePartyMeetingDocuments(fromDateStr,toDateStr,startIndex,maxIndex,activityMemberId,stateId,partyMeetingLevelId,meetingId,meetingGrpId,locationValue);
		
	} catch (Exception e) {
		LOG.error("Exception occured in getCustomWisePartyMeetingDocuments ",e);
	}
	return Action.SUCCESS;
}
public String getMultiLocationWiseMeetingGroupsData()
{
	try {
		
		HttpSession session = request.getSession();
		RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
		jObj = new JSONObject(getTask());
		
		String fromDateStr = jObj.getString("fromDateStr");	
		String toDateStr = jObj.getString("toDateStr");
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		Long partyMeetingMainTypeId = jObj.getLong("partyMeetingMainTypeId");
		
		meetingVO = coreDashboardPartyMeetingService.getMultiLocationWiseMeetingGroupsData( partyMeetingMainTypeId,
				 activityMemberId, fromDateStr, toDateStr, stateId);
		
	} catch (Exception e) {
		LOG.error("Exception occured in getMultiLocationWiseMeetingGroupsData ",e);
	}
	return Action.SUCCESS;
}
public String getPartyLevelIdWiseMeetingsCount()
{
	try {
		
		HttpSession session = request.getSession();
		RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
		jObj = new JSONObject(getTask());
		
		String fromDateStr = jObj.getString("fromDateStr");	
		String toDateStr = jObj.getString("toDateStr");
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		Long partyMeetingLevelId = jObj.getLong("partyMeetingLevelId");
		Long partyMeetnMainTypId = jObj.getLong("partyMeetingMainTypeId");
		Long partyMeetngGrpId = jObj.getLong("meetingGrpId");
		meetingBasicDetailsVO = coreDashboardPartyMeetingService.getPartyLevelIdWiseMeetingsCount( partyMeetnMainTypId,
				 activityMemberId, fromDateStr, toDateStr, stateId, partyMeetingLevelId, partyMeetngGrpId);
		
	} catch (Exception e) {
		LOG.error("Exception occured in getPartyLevelIdWiseMeetingsCount ",e);
	}
	return Action.SUCCESS;
}

public String getPartyLevelIdWiseMeetingsAttendanceDetails(){
	try {
		//HttpSession session = request.getSession();
		jObj = new JSONObject(getTask());
		
		String fromDateStr = jObj.getString("fromDateStr");	
		String toDateStr = jObj.getString("toDateStr");
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		//Long partyMeetingLevelId = jObj.getLong("partyMeetingLevelId");
		Long partyMeetnMainTypId = jObj.getLong("partyMeetingMainTypeId");
		Long partyMeetngId = jObj.getLong("partyMeetingId");
		Long partyMeetngGrpId = jObj.getLong("meetingGrpId");
		Long sessionTypeId = jObj.getLong("sessionTypeId");
		String cadreType = jObj.getString("cadreType");
		Long locationId = jObj.getLong("locationId"); 
		JSONArray levelIdsArr = jObj.getJSONArray("levelIdsLsit");
		List<Long> levelIdsList = new ArrayList<Long>(0);
		if(levelIdsArr != null && levelIdsArr.length()>0){
			for (int i = 0; i < levelIdsArr.length(); i++) {
				levelIdsList.add(levelIdsArr.get(i) != null?Long.valueOf(levelIdsArr.get(i).toString()):0L);
			}
		}
		idNameVO = coreDashboardPartyMeetingService.getPartyLevelIdWiseMeetingAttendanceDetails(partyMeetngId,partyMeetnMainTypId,
				 activityMemberId, fromDateStr, toDateStr, stateId, levelIdsList, partyMeetngGrpId,sessionTypeId,cadreType,locationId);
		
	} catch (Exception e) {
		LOG.error("Exception occured in getPartyLevelIdWiseMeetingsAttendanceDetails ",e);
	}
	return Action.SUCCESS;
}
public String getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails()
{
	try {
		
		HttpSession session = request.getSession();
		RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
		jObj = new JSONObject(getTask());
		
		String fromDateStr = jObj.getString("fromDateStr");	
		String toDateStr = jObj.getString("toDateStr");
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		Long cadreYearId = jObj.getLong("cadreYearId");
		
		coreDashboardInsurancevoList = coreDashboardInsuranceService.getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails(activityMemberId, cadreYearId, stateId, fromDateStr, toDateStr);
		
	} catch (Exception e) {
		LOG.error("Exception occured in getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails ",e);
	}
	return Action.SUCCESS;
}

public String getInsuraceStatusWiseComplaintsDetails()
{
	try {
		
		HttpSession session = request.getSession();
		RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
		jObj = new JSONObject(getTask());
		
		String fromDateStr = jObj.getString("fromDateStr");	
		String toDateStr = jObj.getString("toDateStr");
		Long activityMemberId = jObj.getLong("activityMemberId");
		Long stateId = jObj.getLong("stateId");
		Long cadreYearId = jObj.getLong("cadreYearId");
		String status = jObj.getString("statusStr");	
		String issueType = jObj.getString("issueType");
		Long companyId = jObj.getLong("companyId");
		
		complaintMastervoList = coreDashboardInsuranceService.getInsuraceStatusWiseComplaintsDetails(activityMemberId, cadreYearId, stateId, fromDateStr, toDateStr, status, companyId, issueType);
		
	} catch (Exception e) {
		LOG.error("Exception occured in getInsuraceCompanyAndTypeOfIssueWiseComplaintsDetails ",e);
	}
	return Action.SUCCESS;
}

	public String getUserTypeWiseTotalCadreInsuranceComplainctCnt(){
		  try{
				LOG.info("Entered into getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt()  of CoreDashboardAction");
				
				Long userId = null;
				HttpSession session = request.getSession();
				 RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				 if(user == null || user.getRegistrationID() == null){
					//return ERROR;
					 userId = 1L;
				 }
				 else
				userId = user.getRegistrationID();
				 
				jObj = new JSONObject(getTask());
				Long userTypeId = jObj.getLong("userTypeId");
				Long activityMemberId = jObj.getLong("activityMemberId");
				Long stateId = jObj.getLong("stateId");
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				Long cadreEnrollmentYearId = jObj.getLong("cadreEnrollmentYearId"); 
				 userTypeVOList = coreDashboardInsuranceService.getUserTypeWiseTotalCadreInsuranceComplainctCnt(activityMemberId,userId,userTypeId,stateId,cadreEnrollmentYearId,fromDate,toDate);
			}catch(Exception e){
				LOG.error("Exception raised at getUserTypeWiseTotalCadreInsuranceComplainctCnt() method of CoreDashBoardAction", e);
			}
			return Action.SUCCESS; 
		  
	}
	public String getSelectedChildMemberCadreInsuranceComplainctCnt(){
		try{
			    jObj = new JSONObject(getTask());
			 	Long parentActivityMemberId = jObj.getLong("parentActivityMemberId");
			 	List<Long> childUserTypeIds=new ArrayList<Long>();
				JSONArray childUserTypeIdsArray=jObj.getJSONArray("childUserTypeIdsArray");
				if(childUserTypeIdsArray!=null &&  childUserTypeIdsArray.length()>0){
					for( int i=0;i<childUserTypeIdsArray.length();i++){
						childUserTypeIds.add(Long.valueOf(childUserTypeIdsArray.getString(i)));
					}
				}
				String reportType = jObj.getString("reportType");
				Long stateId = jObj.getLong("stateId");
				String fromDate = jObj.getString("fromDate");
				String toDate = jObj.getString("toDate");
				Long cadreEnrollmentYearId = jObj.getLong("cadreEnrollmentYearId"); 
				activityMembersList = coreDashboardInsuranceService.getSelectedChildMemberCadreInsuranceComplainctCnt(parentActivityMemberId,childUserTypeIds,reportType,stateId,cadreEnrollmentYearId,fromDate,toDate);
		 }catch(Exception e){
			 LOG.error("Exception raised at getSelectedChildMemberCadreInsuranceComplainctCnt() method of CoreDashBoardAction", e); 
		 }
		 return Action.SUCCESS;
	}
	public String getDirectChildMemberCadreInsuranceComplainctCnt(){
		try{
		    jObj = new JSONObject(getTask());
		 	Long parentActivityMemberId = jObj.getLong("parentActivityMemberId");
		 	List<Long> childUserTypeIds=new ArrayList<Long>();
			JSONArray childUserTypeIdsArray=jObj.getJSONArray("childUserTypeIdsArray");
			if(childUserTypeIdsArray!=null &&  childUserTypeIdsArray.length()>0){
				for( int i=0;i<childUserTypeIdsArray.length();i++){
					childUserTypeIds.add(Long.valueOf(childUserTypeIdsArray.getString(i)));
				}
			}
			String reportType = jObj.getString("reportType");
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long cadreEnrollmentYearId = jObj.getLong("cadreEnrollmentYearId"); 
			activityMembersList = coreDashboardInsuranceService.getSelectedChildMemberCadreInsuranceComplainctCnt(parentActivityMemberId,childUserTypeIds,reportType,stateId,cadreEnrollmentYearId,fromDate,toDate);
		 }catch(Exception e){
			 LOG.error("Exception raised at getSelectedChildMemberCadreInsuranceComplainctCnt() method of CoreDashBoardAction", e); 
		 }
	 return Action.SUCCESS;
	}
	public String getCandiateWiseCadreInsurencaeDtls(){
		try{
		    jObj = new JSONObject(getTask());
		 	Long activityMemberId = jObj.getLong("activityMemberId");
		 	Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long cadreEnrollmentYearId = jObj.getLong("cadreEnrollmentYearId"); 
			activityMembersList = coreDashboardInsuranceService.getCandiateWiseCadreInsurencaeDtls(activityMemberId,stateId,cadreEnrollmentYearId,fromDate,toDate);
		 }catch(Exception e){
			 LOG.error("Exception raised at getCandiateWiseCadreInsurencaeDtls() method of CoreDashBoardAction", e); 
		 }
	 return Action.SUCCESS;
	}
	
	public String getLagDaysInsuranceComplaintsCounts()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			
			//String fromDateStr = jObj.getString("fromDateStr");	
			//String toDateStr = jObj.getString("toDateStr");
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long stateId = jObj.getLong("stateId");
			Long cadreYearId = jObj.getLong("cadreYearId");
			String status = jObj.getString("statusStr");	
			String issueType = jObj.getString("issueType");
			Long companyId = jObj.getLong("companyId");
			
			insuranceLagDaysVO = coreDashboardInsuranceService.getLagDaysInsuranceComplaintsCounts(activityMemberId, cadreYearId, stateId, status, companyId, issueType);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getLagDaysInsuranceComplaintsCounts ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getStatusTrackingDetailsOfInsuranceByComplaint()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			
			//String fromDateStr = jObj.getString("fromDateStr");	
			//String toDateStr = jObj.getString("toDateStr");
			Long complaintId = jObj.getLong("complaintId");
			/*Long stateId = jObj.getLong("stateId");
			Long cadreYearId = jObj.getLong("cadreYearId");
			String status = jObj.getString("statusStr");	
			String issueType = jObj.getString("issueType");
			Long companyId = jObj.getLong("companyId");*/
			
			insuranceSimpleVO = coreDashboardInsuranceService.getStatusTrackingDetailsOfInsuranceByComplaint(complaintId);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getStatusTrackingDetailsOfInsuranceByComplaint ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getComplaintScanCopyDetails()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			
			//String fromDateStr = jObj.getString("fromDateStr");	
			//String toDateStr = jObj.getString("toDateStr");
			Long complaintId = jObj.getLong("complaintId");
			/*Long stateId = jObj.getLong("stateId");
			Long cadreYearId = jObj.getLong("cadreYearId");
			String status = jObj.getString("statusStr");	
			String issueType = jObj.getString("issueType");
			Long companyId = jObj.getLong("companyId");*/
			
			complaintMasterVO = coreDashboardInsuranceService.getComplaintScanCopyDetails(complaintId);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getComplaintScanCopyDetails ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getRemarksByComplaint()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			
			//String fromDateStr = jObj.getString("fromDateStr");	
			//String toDateStr = jObj.getString("toDateStr");
			Long complaintId = jObj.getLong("complaintId");
			/*Long stateId = jObj.getLong("stateId");
			Long cadreYearId = jObj.getLong("cadreYearId");
			String status = jObj.getString("statusStr");	
			String issueType = jObj.getString("issueType");
			Long companyId = jObj.getLong("companyId");*/
			
			coreDashboardInsuranceVO = coreDashboardInsuranceService.getRemarksByComplaint(complaintId);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getRemarksByComplaint ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getComplaintResponsesByComplaint()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			
			//String fromDateStr = jObj.getString("fromDateStr");	
			//String toDateStr = jObj.getString("toDateStr");
			Long complaintId = jObj.getLong("complaintId");
			/*Long stateId = jObj.getLong("stateId");
			Long cadreYearId = jObj.getLong("cadreYearId");
			String status = jObj.getString("statusStr");	
			String issueType = jObj.getString("issueType");
			Long companyId = jObj.getLong("companyId");*/
			
			coreDashboardInsuranceVO = coreDashboardInsuranceService.getComplaintResponsesByComplaint(complaintId);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getComplaintResponsesByComplaint ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getInsuranceCompanyWiseOverviewAndStatusDetails()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			
			String fromDateStr = jObj.getString("fromDateStr");	
			String toDateStr = jObj.getString("toDateStr");
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long cadreYearId = jObj.getLong("cadreYearId");
			
			coreDashboardInsurancevoList = coreDashboardInsuranceService.getInsuranceCompanyWiseOverviewAndStatusDetails(activityMemberId, cadreYearId, fromDateStr, toDateStr);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getInsuranceCompanyWiseOverviewAndStatusDetails ",e);
		}
		return Action.SUCCESS;
	}
	public String getUserTypeWiseBoothCommitteesInchargeSummary(){
		LOG.info("Entered into getUserTypeWiseBoothCommitteesInchargeDetails()  of CoreDashboardAction");
		try{
			
			 Long userId = null; 
			 HttpSession session = request.getSession();
			 RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			 if(user == null || user.getRegistrationID() == null){
				//return ERROR;
				 userId = 1L;
			 }
			 else
				 userId = user.getRegistrationID();
			
			jObj = new JSONObject(getTask());
			
			Long activityMemberId = jObj.getLong("activityMemberId");
			
			String state = jObj.getString("state");
	
			String dateString = jObj.getString("dateString");
			List<Long> committeeEnrollmentYearsIdsLst = new ArrayList<Long>();
			JSONArray committeeEnrollmentYearArray =jObj.getJSONArray("committeeEnrollmentYearArray");
			if(committeeEnrollmentYearArray!=null &&  committeeEnrollmentYearArray.length()>0){
				for( int i=0;i<committeeEnrollmentYearArray.length();i++){
					committeeEnrollmentYearsIdsLst.add(Long.valueOf(committeeEnrollmentYearArray.getString(i)));
				}
			}
			
			boothInchargeVo = coreDashboardMainService.getUserTypeWiseBoothCommitteesInchargeDetails(activityMemberId,state,dateString,committeeEnrollmentYearsIdsLst);
		}catch(Exception e){
			LOG.error("Exception raised at getUserTypeWiseBoothCommitteesInchargeSummary() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getBoothCommitteeInchargesCount(){
	    LOG.info("Entered into getBoothCommitteeInchargesCount()  of CoreDashboardAction");
	    try{
	      
	       /*Long userId = null; 
	       HttpSession session = request.getSession();
	       RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
	       if(user == null || user.getRegistrationID() == null){
	        //return ERROR;
	         userId = 1L;
	       }
	       else
	         userId = user.getRegistrationID();*/
	      
	      jObj = new JSONObject(getTask());
	      
	      Long activityMemberId = jObj.getLong("activityMemberId");
	      
	      Long state = jObj.getLong("state");
	  
	      String dateString = jObj.getString("dateString");
	      List<Long> committeeEnrollmentYearsIdsLst = new ArrayList<Long>();
	      JSONArray committeeEnrollmentYearArray =jObj.getJSONArray("committeeEnrollmentYearArray");
	      if(committeeEnrollmentYearArray!=null &&  committeeEnrollmentYearArray.length()>0){
	        for( int i=0;i<committeeEnrollmentYearArray.length();i++){
	          committeeEnrollmentYearsIdsLst.add(Long.valueOf(committeeEnrollmentYearArray.getString(i)));
	        }
	      }
	      
	      boothInchargesVOList = coreDashboardMainService.getBoothCommitteeInchargesCount(activityMemberId,committeeEnrollmentYearsIdsLst,dateString,state);
	    }catch(Exception e){
	      LOG.error("Exception raised at getBoothCommitteeInchargesCount() method of CoreDashBoard", e);
	    }
	    return Action.SUCCESS;
	  }
	public String govtPartyCoreDashboardAction(){
		try {
		
		}catch(Exception e) {
			LOG.error("Exception raised at govtPartyCoreDashboardAction() in CoreDashBoard Action class", e);
		}
		return Action.SUCCESS;
	}
	
	public String getComplaintRaisedDetails()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			
			String fromDateStr = jObj.getString("fromDate");	
			String toDateStr = jObj.getString("toDate");
			Long levelId = jObj.getLong("locationTypeId");
			
			List<Long> locationValues=new ArrayList<Long>();
			JSONArray locationValuesArr=jObj.getJSONArray("locationValuesArr");
			if(locationValuesArr!=null &&  locationValuesArr.length()>0){
				for( int i=0;i<locationValuesArr.length();i++){
					locationValues.add(Long.valueOf(locationValuesArr.getString(i))); 
				}
			}
			String year = jObj.getString("year");
			String type = jObj.getString("type");	
			String grievanceType = jObj.getString("grievanceType");
			
			
			complaintMastervoList = coreDashboardInsuranceService.getComplaintRaisedDetails(levelId, locationValues, fromDateStr, toDateStr, type, grievanceType, year);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getComplaintRaisedDetails ",e);
		}
		return Action.SUCCESS;
	}
	public List<Long> getTrainingCampPrograms(Long enrollmentId){
		List<Long> finalList = new ArrayList<Long>();
		try {
			 finalList = coreDashboardMainService.getTrainingCampPrograms(enrollmentId);
		}catch(Exception e) {
			LOG.error("Exception raised at getTrainingCampProgramsAction() in CoreDashBoard Action class", e);
		}
		return finalList;
	}
	
	public String getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWise()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			List<Long> userAccessLevelValueList=new ArrayList<Long>();
			JSONArray userAccessLevelValueArr=jObj.getJSONArray("userAccessLevelValues");
			if(userAccessLevelValueArr!=null &&  userAccessLevelValueArr.length()>0){
				for( int i=0;i<userAccessLevelValueArr.length();i++){
					userAccessLevelValueList.add(Long.valueOf(userAccessLevelValueArr.getString(i))); 
				}
			}
			Long stateId = jObj.getLong("stateId");
			String fromDateStr = jObj.getString("fromDate");	
			String toDateStr = jObj.getString("toDate");
			
			List<Long> enrollmentYearIdList=new ArrayList<Long>();
			JSONArray enrollmentYearIdArr=jObj.getJSONArray("enrollmentYearIds");
			if(enrollmentYearIdArr!=null &&  enrollmentYearIdArr.length()>0){
				for( int i=0;i<enrollmentYearIdArr.length();i++){
					enrollmentYearIdList.add(Long.valueOf(enrollmentYearIdArr.getString(i))); 
				}
			}
			List<Long> programIdList =getTrainingCampPrograms(enrollmentYearIdList.get(0));
			
			
			
			trainingCampProgramVO = coreDashboardMainService.getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWise(userAccessLevelId, userAccessLevelValueList, stateId, fromDateStr, toDateStr, enrollmentYearIdList, programIdList,null);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getComplaintRaisedDetails ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCommitteeDetailedReports(){
		try {
			LOG.info("entered into CoreDashboardAction of getCommitteeDetailedReports() ");
			jObj = new JSONObject(getTask());
			
			String fromDateStr = jObj.getString("fromDate");	
			String toDateStr = jObj.getString("toDate");
			
			List<Long> enrollmentYearIdList = new ArrayList<Long>();
			
			JSONArray enrollmentYearIdArr = jObj.getJSONArray("enrollmentIdsLst");
			if(enrollmentYearIdArr!=null &&  enrollmentYearIdArr.length()>0){
				for( int i=0;i<enrollmentYearIdArr.length();i++){
					enrollmentYearIdList.add(Long.valueOf(enrollmentYearIdArr.getString(i))); 
				}
			}
			
			List<Long> basiccommitteeTypeIdsList = new ArrayList<Long>();
			JSONArray basiccommitteeTypeIdArr =jObj.getJSONArray("basiccommitteeTypeIdsList");
			if(basiccommitteeTypeIdArr!=null &&  basiccommitteeTypeIdArr.length()>0){
				for( int i=0;i<enrollmentYearIdArr.length();i++){
					basiccommitteeTypeIdsList.add(Long.valueOf(basiccommitteeTypeIdArr.getString(i))); 
				}
			}
			
			List<Long> committeeTypeIdsLst = new ArrayList<Long>();
			
			JSONArray committeeTypeIdsArr  =jObj.getJSONArray("committeeTypeIdsList");
			if(committeeTypeIdsArr!=null &&  committeeTypeIdsArr.length()>0){
				for( int i=0;i<enrollmentYearIdArr.length();i++){
					committeeTypeIdsLst.add(Long.valueOf(committeeTypeIdsArr.getString(i))); 
				}
			}
			Long locationScopeId = jObj.getLong("locationScopeId");
			
			Long committeeLevelId = jObj.getLong("committeeLevelId");
			
			List<Long> locationValues = new ArrayList<Long>();
			
			JSONArray locationValueArr  =jObj.getJSONArray("LocationValuesList");
			if(locationValueArr!=null &&  locationValueArr.length()>0){
				for( int i=0;i<locationValueArr.length();i++){
					locationValues.add(Long.valueOf(locationValueArr.getString(i))); 
				}
			}
			
			committeeDataVOList = coreDashboardMainService.getCommitteeDetailedReport(enrollmentYearIdList, committeeLevelId,fromDateStr, toDateStr, basiccommitteeTypeIdsList, committeeTypeIdsLst, locationScopeId, locationValues );
		} catch (Exception e) {
			LOG.error("Exception raised into CoreDashboardAction of getCommitteeDetailedReports()",e);
		}
		return Action.SUCCESS;
	}
	public String getDebateDesignationWiseTotalDebateDetails(){
		
		try{
			jObj = new JSONObject(getTask());
			JSONArray debateLocationIdArry = jObj.getJSONArray("debateLocationIdArry");  
			List<Long> debateLocationIdList = new ArrayList<Long>();
			if(debateLocationIdArry != null && debateLocationIdArry.length() > 0){
				for (int i = 0; i < debateLocationIdArry.length(); i++){
					debateLocationIdList.add(Long.parseLong(debateLocationIdArry.getString(i)));          
				}  
			}
			JSONArray debateParticipantLocIdArry = jObj.getJSONArray("debateParticipantLocIdArry");  
			List<Long> debateParticipantLocationIdList = new ArrayList<Long>();
			if(debateParticipantLocIdArry != null && debateParticipantLocIdArry.length() > 0){
				for (int i = 0; i < debateParticipantLocIdArry.length(); i++){
					debateParticipantLocationIdList.add(Long.parseLong(debateParticipantLocIdArry.getString(i)));          
				}  
			}
			codeDebateVoList = coreDashboardMainService.getDebateDesignationWiseTotalDebateDetails(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"),debateLocationIdList,debateParticipantLocationIdList);
			
		}catch (Exception e) {
			LOG.error("Exception raised at getDebateDesignationWiseTotalDebateDetails() method of CoreDashBoardAction", e);
		}
		
		return Action.SUCCESS;
	}
	public String getDesignationWiseCandidateOverAllPerformanceCohort(){
		
		try{
			
			jObj = new JSONObject(getTask());
			JSONArray participantLocIdArry = jObj.getJSONArray("participantLocIdArry");  
			List<Long> participantLocationIdList = new ArrayList<Long>();
			if(participantLocIdArry != null && participantLocIdArry.length() > 0){
				for (int i = 0; i < participantLocIdArry.length(); i++){
					participantLocationIdList.add(Long.parseLong(participantLocIdArry.getString(i)));          
				}  
			}
			JSONArray debateLocationIdArry = jObj.getJSONArray("debateLocationIdArry");  
			List<Long> debateLocationIdList = new ArrayList<Long>();
			if(debateLocationIdArry != null && debateLocationIdArry.length() > 0){
				for (int i = 0; i < debateLocationIdArry.length(); i++){
					debateLocationIdList.add(Long.parseLong(debateLocationIdArry.getString(i)));          
				}  
			}
			codeDebateVoList = coreDashboardMainService.getDesignationWiseCandidateOverAllPerformanceCohort(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"),participantLocationIdList,debateLocationIdList,jObj.getString("type"));
			
		}catch (Exception e) {
			LOG.error("Exception raised at getDesignationWiseCandidateOverAllPerformanceCohort() method of CoreDashBoardAction", e);
		}
		
		return Action.SUCCESS;
	}
	public String getCandiateWiseTourSubmittedDetails(){
		try {
			LOG.info("Entered into getToursBasicOverviewDtls()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long userTypeId = jObj.getLong("userTypeId");
			tourOverviewDtlsVO = coreDashboardToursService.getCandiateWiseTourSubmittedDetails(stateId,fromDate,toDate,activityMemberId,userTypeId);
		} catch (Exception e) {
			LOG.error("Exception raised at getCandiateWiseTourSubmittedDetails() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String getCandaiteDetailsByType(){
		try {
			LOG.info("Entered into getToursBasicOverviewDtls()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			Long userTypeId = jObj.getLong("userTypeId");
			String type = jObj.getString("type");
			tourOverviewDtlsList = coreDashboardToursService.getCandaiteDetailsByType(stateId, fromDate, toDate,activityMemberId, userTypeId,type);
		} catch (Exception e) {
			LOG.error("Exception raised at getCandaiteDetailsByType() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getLocationWiseCommitteeMemberDetails(){
		try {
			LOG.info("Entered into getToursBasicOverviewDtls()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			
			String name = jObj.getString("name");
			InputVO vo = new InputVO();
			vo.setName(name);
			vo.setActivityMemberId(jObj.getLong("activityMemberId"));
			kaizalaDashboardList = kaizalaInfoService.getLocationWiseCommitteeMemberDetails(vo);
		} catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseCommitteeMemberDetails() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String getOverAllCommitteeWiseMembersCounts(){
		try {
			LOG.info("Entered into getOverAllCommitteeWiseMembersCounts()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			
			InputVO vo = new InputVO();
			vo.setActivityMemberId(jObj.getLong("activityMemberId"));
			kaizalaDashboardVO = kaizalaInfoService.getOverAllCommitteeWiseMembersCounts(vo);
		} catch (Exception e) {
			LOG.error("Exception raised at getOverAllCommitteeWiseMembersCounts() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	
	public String getUserTypeWiseKaizalaCommitteeMemberDetailsCnt(){
		try {
			LOG.info("Entered into getUserTypeWiseKaizalaCommitteeMemberDetailsCnt()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long activityMemberId = jObj.getLong("activityMemberId");
			Long stateId = jObj.getLong("stateId");
			Long userId = 1L;
			Long userTypeId = jObj.getLong("userTypeId");
			
			InputVO vo = new InputVO();
			vo.setActivityMemberId(activityMemberId);
			vo.setStateId(stateId);
			vo.setUserId(userId);
			vo.setUserTypeId(userTypeId);
			userTypeVOList = kaizalaInfoService.getUserTypeWiseKaizalaCommitteeMemberDetailsCnt(vo);
		} catch (Exception e) {
			LOG.error("Exception raised at getUserTypeWiseKaizalaCommitteeMemberDetailsCnt() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
	public String getUserTypeWiseTotalEligibleAndAttendedCntActionForTrainingCamp(){
		 
		 try{
			 
			 jObj = new JSONObject(getTask());
			 
			   final HttpSession session = request.getSession();
				
			   Long userId = null;
				final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null || user.getRegistrationID() == null){
					userId = 1L;
				}
				else{
					userId = user.getRegistrationID();
				}
				List<Long> programIdList = new ArrayList<Long>();
				JSONArray programIdArr=jObj.getJSONArray("programIdArr");  
				if(programIdArr!=null &&  programIdArr.length()>0){
					for( int i=0;i<programIdArr.length();i++){ 
						programIdList.add(Long.valueOf(programIdArr.getString(i)));
					}
				}
				Long activityMemberId = jObj.getLong("activityMemberId");
				Long userTypeId = jObj.getLong("userTypeId");
			    Long userAccessLevelId = jObj.getLong("userAccessLevelId");
				List<Long> userAccessLevelValues=new ArrayList<Long>();
				JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
				if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
					for( int i=0;i<userAccessLevelValuesArray.length();i++){
						userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
					}
				}
				
				List<Long> enrollmentYrIds=new ArrayList<Long>();
				JSONArray enrollmentYrIdsArray=jObj.getJSONArray("enrollmentYrIds");
				if(enrollmentYrIdsArray!=null &&  enrollmentYrIdsArray.length()>0){
					for( int i=0;i<enrollmentYrIdsArray.length();i++){
						enrollmentYrIds.add(Long.valueOf(enrollmentYrIdsArray.getString(i)));
					}
				}
				Long stateId = jObj.getLong("stateId");
				String dateStr = jObj.getString("dateStr");
				userTypeVOList = coreDashboardCoreService.getUserTypeWiseTotalEligibleAndAttendedCnt(userId,userTypeId,activityMemberId,userAccessLevelId,userAccessLevelValues,stateId,dateStr,enrollmentYrIds,programIdList);
		 }catch(Exception e){
			 LOG.error("Exception raised at getUserTypeWiseTotalEligibleAndAttendedCnt() method of CoreDashBoardAction", e); 
		 }
		 return Action.SUCCESS;
	 }
	public String getProgramIds(){
		try {
			LOG.info("Entered into getProgramIds()  of CoreDashboardAction");
			
			keyValueVOList = coreDashboardCoreService.getProgramIdsList();
		} catch (Exception e) {
			LOG.error("Exception raised at getProgramIds() method of CoreDashboardAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getCadreCommiteeEnrollmentIds(){
		try {
			LOG.info("Entered into getCadreCommiteeEnrollmentIds()  of CoreDashboardAction");
			
			keyValueVOList = coreDashboardCoreService.getCadreCommiteeEnrollmentIds();
		} catch (Exception e) {
			LOG.error("Exception raised at getCadreCommiteeEnrollmentIds() method of CoreDashboardAction", e);
		}
		return Action.SUCCESS;
	}
	public String getTrainingCampBasicDetailsCntOverviewDayWise(){
		try {
			LOG.info("Entered into getTrainingCampBasicDetailsCntOverviewDayWise()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long globalActivityMemberId = jObj.getLong("globalActivityMemberId");
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			
			JSONArray enrollmentYearIdsArr = jObj.getJSONArray("enrollmentYearIds");  
			List<Long> enrollmentYearIdsList = new ArrayList<Long>();
			if(enrollmentYearIdsArr != null && enrollmentYearIdsArr.length() > 0){
				for (int i = 0; i < enrollmentYearIdsArr.length(); i++){
					enrollmentYearIdsList.add(Long.parseLong(enrollmentYearIdsArr.getString(i)));          
				}  
			}
			
			JSONArray programIdsArr = jObj.getJSONArray("programIds");  
			List<Long> programIdList = new ArrayList<Long>();
			if(programIdsArr != null && programIdsArr.length() > 0){
				for (int i = 0; i < programIdsArr.length(); i++){
					programIdList.add(Long.parseLong(programIdsArr.getString(i)));          
				}  
			}
			
			JSONArray committeeLevelIdArr = jObj.getJSONArray("committeeLevelIds");  
			List<Long> committeeLevelIdList = new ArrayList<Long>();
			if(committeeLevelIdArr != null && committeeLevelIdArr.length() > 0){
				for (int i = 0; i < committeeLevelIdArr.length(); i++){
					committeeLevelIdList.add(Long.parseLong(committeeLevelIdArr.getString(i)));          
				}  
			}
			
			trainingCampProgramVO = coreDashboardCoreService.getTrainingCampBasicDetailsCntOverviewDayWise(globalActivityMemberId,stateId,fromDate,toDate, enrollmentYearIdsList,programIdList,committeeLevelIdList);
		} catch (Exception e) {
			LOG.error("Exception raised at getTrainingCampBasicDetailsCntOverviewDayWise() method of CoreDashboardAction", e);
		}
		return Action.SUCCESS;
	}
	
	public String getFeedbackOnLeaders(){
		 try{
			 jObj = new JSONObject(getTask());
			 
			   final HttpSession session = request.getSession();
				
			   Long userId = null;
				final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null || user.getRegistrationID() == null){
					userId = 1L;
				}
				else{
					userId = user.getRegistrationID();
				}
				List<Long> programIdList = new ArrayList<Long>();
				JSONArray programIdArr=jObj.getJSONArray("programIdArr");  
				if(programIdArr!=null &&  programIdArr.length()>0){
					for( int i=0;i<programIdArr.length();i++){ 
						programIdList.add(Long.valueOf(programIdArr.getString(i)));
					}
				}
			    Long userAccessLevelId = jObj.getLong("userAccessLevelId");
				List<Long> userAccessLevelValues=new ArrayList<Long>();
				JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
				if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
					for( int i=0;i<userAccessLevelValuesArray.length();i++){
						userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
					}
				}
				Long traingCampEnrollmentYearId = jObj.getLong("traingCampEnrollmentYearId");
								
				List<Long> trainingCampLevelIds=new ArrayList<Long>();
				JSONArray trainingCampLevelIdArr=jObj.getJSONArray("trainingCampLevelIds");
				if(trainingCampLevelIdArr!=null &&  trainingCampLevelIdArr.length()>0){
					for( int i=0;i<trainingCampLevelIdArr.length();i++){
						trainingCampLevelIds.add(Long.valueOf(trainingCampLevelIdArr.getString(i)));
					}
				}
				
				
				Long stateId = jObj.getLong("stateId");
				String dateStr = jObj.getString("dateStr");
				Long groupType = jObj.getLong("groupType");
				campSurveyVOs = coreDashboardCoreService.getFeedbackOnLeaders(userAccessLevelId,userAccessLevelValues,programIdList,traingCampEnrollmentYearId,trainingCampLevelIds,groupType);
		 }catch(Exception e){
			 LOG.error("Exception raised at getUserTypeWiseTotalEligibleAndAttendedCnt() method of CoreDashBoardAction", e); 
		 }
		 return Action.SUCCESS;
	 }
	public String getTrainingCampFeedBackDetails(){
		 
		 try{
			 
			 jObj = new JSONObject(getTask());
			 
			   final HttpSession session = request.getSession();
				
			   Long userId = null;
				final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null || user.getRegistrationID() == null){
					userId = 1L;
				}
				else{
					userId = user.getRegistrationID();
				}
				List<Long> programIdList = new ArrayList<Long>();
				JSONArray programIdArr=jObj.getJSONArray("programIdArr");  
				if(programIdArr!=null &&  programIdArr.length()>0){
					for( int i=0;i<programIdArr.length();i++){ 
						programIdList.add(Long.valueOf(programIdArr.getString(i)));
					}
				}
				Long activityMemberId = jObj.getLong("activityMemberId");
				
			    /*Long userAccessLevelId = jObj.getLong("userAccessLevelId");
				List<Long> userAccessLevelValues=new ArrayList<Long>();
				JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
				if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
					for( int i=0;i<userAccessLevelValuesArray.length();i++){
						userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
					}
				}*/
				
				List<Long> enrollmentYrIds=new ArrayList<Long>();
				JSONArray enrollmentYrIdsArray=jObj.getJSONArray("enrollmentYrIds");
				if(enrollmentYrIdsArray!=null &&  enrollmentYrIdsArray.length()>0 && !enrollmentYrIdsArray.getString(0).equalsIgnoreCase("null")){
					for( int i=0;i<enrollmentYrIdsArray.length();i++){
						enrollmentYrIds.add(Long.valueOf(enrollmentYrIdsArray.getString(i)));
					}
				}
				Long stateId = jObj.getLong("stateId");
				String dateStr = jObj.getString("dateStr");
				String committeeLevel=jObj.getString("committeLevel");
				trainingCampVO = coreDashboardCoreService.getTrainingCampFeedBackDetails(activityMemberId, committeeLevel , stateId,  dateStr,  enrollmentYrIds,  programIdList);
		 }catch(Exception e){
			 LOG.error("Exception raised at getTrainingCampFeedBackDetails() method of CoreDashBoardAction", e); 
		 }
		 return Action.SUCCESS;
	 }
	public String getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWiseForFeedbackBlock()
	{
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO  user= (RegistrationVO) session.getAttribute("USER");
			jObj = new JSONObject(getTask());
			
			Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			List<Long> userAccessLevelValueList=new ArrayList<Long>();
			JSONArray userAccessLevelValueArr=jObj.getJSONArray("userAccessLevelValues");
			if(userAccessLevelValueArr!=null &&  userAccessLevelValueArr.length()>0){
				for( int i=0;i<userAccessLevelValueArr.length();i++){
					userAccessLevelValueList.add(Long.valueOf(userAccessLevelValueArr.getString(i))); 
				}
			}
			Long stateId = jObj.getLong("stateId");
			String fromDateStr = jObj.getString("fromDate");	
			String toDateStr = jObj.getString("toDate");
			
			List<Long> enrollmentYearIdList=new ArrayList<Long>();
			JSONArray enrollmentYearIdArr=jObj.getJSONArray("enrollmentYearIds");
			if(enrollmentYearIdArr!=null &&  enrollmentYearIdArr.length()>0){
				for( int i=0;i<enrollmentYearIdArr.length();i++){
					enrollmentYearIdList.add(Long.valueOf(enrollmentYearIdArr.getString(i))); 
				}
			}
			List<Long> programIdList = new ArrayList<Long>();
			JSONArray programIdArr=jObj.getJSONArray("programIdArr");  
			if(programIdArr!=null &&  programIdArr.length()>0){
				for( int i=0;i<programIdArr.length();i++){ 
					programIdList.add(Long.valueOf(programIdArr.getString(i)));
				}
			}
			String committeeLevel=jObj.getString("committeLevel");
			List<Long> levelList = new ArrayList<Long>();
			String[] levelStr =  committeeLevel.split(",");
			for(String param:levelStr){
				levelList.add(Long.parseLong(param));
			}
			
			trainingCampProgramVO = coreDashboardMainService.getTrainingCampBasicDetailsCntOverviewTrainingCampCenterWise(userAccessLevelId, userAccessLevelValueList, stateId, fromDateStr, toDateStr, enrollmentYearIdList, programIdList,levelList);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getComplaintRaisedDetails ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getTrainingCampFeedBackProgramWiseDetails(){
		 
		 try{
			 
			 jObj = new JSONObject(getTask());
			 
			   final HttpSession session = request.getSession();
				
			   Long userId = null;
				final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
				if(user == null || user.getRegistrationID() == null){
					userId = 1L;
				}
				else{
					userId = user.getRegistrationID();
				}
				List<Long> programIdList = new ArrayList<Long>();
				JSONArray programIdArr=jObj.getJSONArray("programIdArr");  
				if(programIdArr!=null &&  programIdArr.length()>0){
					for( int i=0;i<programIdArr.length();i++){ 
						programIdList.add(Long.valueOf(programIdArr.getString(i)));
					}
				}
				
			    Long userAccessLevelId = jObj.getLong("userAccessLevelId");
				List<Long> userAccessLevelValues=new ArrayList<Long>();
				JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
				if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
					for( int i=0;i<userAccessLevelValuesArray.length();i++){
						userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
					}
				}
				
				List<Long> enrollmentYrIds=new ArrayList<Long>();
				JSONArray enrollmentYrIdsArray=jObj.getJSONArray("enrollmentYrIds");
				if(enrollmentYrIdsArray!=null &&  enrollmentYrIdsArray.length()>0){
					for( int i=0;i<enrollmentYrIdsArray.length();i++){
						enrollmentYrIds.add(Long.valueOf(enrollmentYrIdsArray.getString(i)));
					}
				}
				JSONArray committeeLevelIdArr = jObj.getJSONArray("committeeLevelIds");  
				List<Long> committeeLevelIdList = new ArrayList<Long>();
				if(committeeLevelIdArr != null && committeeLevelIdArr.length() > 0){
					for (int i = 0; i < committeeLevelIdArr.length(); i++){
						committeeLevelIdList.add(Long.parseLong(committeeLevelIdArr.getString(i)));          
					}  
				}
				Long stateId = jObj.getLong("stateId");
				String dateStr = jObj.getString("dateStr");   
				campSurveyVOs = coreDashboardCoreService.getTrainingCampFeedBackDetailsProgramWise(programIdList,userAccessLevelId,userAccessLevelValues,enrollmentYrIds,committeeLevelIdList);
		 }catch(Exception e){
			 LOG.error("Exception raised at getTrainingCampFeedBackProgramWiseDetails() method of CoreDashBoardAction", e); 
		 }
		 return Action.SUCCESS;
	 }

public String getTrainingQuizDetails(){
	 
	 try{
		 
		 jObj = new JSONObject(getTask());
		 
		   final HttpSession session = request.getSession();
			
		   Long userId = null;
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				userId = 1L;
			}
			else{
				userId = user.getRegistrationID();
			}
			List<Long> programIdList = new ArrayList<Long>();
			JSONArray programIdArr=jObj.getJSONArray("programIdArr");  
			if(programIdArr!=null &&  programIdArr.length()>0){
				for( int i=0;i<programIdArr.length();i++){ 
					programIdList.add(Long.valueOf(programIdArr.getString(i)));
				}
			}
			
		    Long userAccessLevelId = jObj.getLong("userAccessLevelId");
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			
			List<Long> enrollmentYrIds=new ArrayList<Long>();
			JSONArray enrollmentYrIdsArray=jObj.getJSONArray("enrollmentYrIds");
			if(enrollmentYrIdsArray!=null &&  enrollmentYrIdsArray.length()>0){
				for( int i=0;i<enrollmentYrIdsArray.length();i++){
					enrollmentYrIds.add(Long.valueOf(enrollmentYrIdsArray.getString(i)));
				}
			}
			JSONArray committeeLevelIdArr = jObj.getJSONArray("committeeLevelIds");  
			List<Long> committeeLevelIdList = new ArrayList<Long>();
			if(committeeLevelIdArr != null && committeeLevelIdArr.length() > 0){
				for (int i = 0; i < committeeLevelIdArr.length(); i++){
					committeeLevelIdList.add(Long.parseLong(committeeLevelIdArr.getString(i)));          
				}  
			}
			Long stateId = jObj.getLong("stateId");
			String dateStr = jObj.getString("dateStr");   
			campSurveyVOs = coreDashboardCoreService.getTrainingQuizDetails(programIdList,userAccessLevelId,userAccessLevelValues,enrollmentYrIds,committeeLevelIdList);
	 }catch(Exception e){
		 LOG.error("Exception raised at getUserTypeWiseTotalEligibleAndAttendedCnt() method of CoreDashBoardAction", e); 
	 }
	 return Action.SUCCESS;
 }
}
//public List<TrainingCampSurveyVO> getFeedbackOnLeaders(Long userAccessLevelId, List<Long> userAccessLevelValues, List<Long> trainingProgramIds,Long traingCampEnrollmentYearId,List<Long> trainingCampLevelIds,Long groupType)