package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreRegistratedCountVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreReportVO;
import com.itgrids.partyanalyst.dto.CadreResponseVO;
import com.itgrids.partyanalyst.dto.ChildUserTypeVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.CommitteeDataVO;
import com.itgrids.partyanalyst.dto.CommitteeVO;
import com.itgrids.partyanalyst.dto.CoreDebateVO;
import com.itgrids.partyanalyst.dto.DashboardCommentVO;
import com.itgrids.partyanalyst.dto.EventDetailsVO;
import com.itgrids.partyanalyst.dto.HolidayListVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NewCadreRegistrationVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsDataVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
import com.itgrids.partyanalyst.dto.PaymentGatewayVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ToursBasicVO;
import com.itgrids.partyanalyst.dto.TrainingCampProgramVO;
import com.itgrids.partyanalyst.dto.UserDataVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.IAttendanceCoreDashBoardService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICoreDashboardCadreRegistrationService;
import com.itgrids.partyanalyst.service.ICoreDashboardEventsActivitiesService;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.service.ICoreDashboardMainService;
import com.itgrids.partyanalyst.service.ICoreDashboardPartyMeetingService;
import com.itgrids.partyanalyst.service.ICoreDashboardService;
import com.itgrids.partyanalyst.service.ICoreDashboardService1;
import com.itgrids.partyanalyst.service.ICoreDashboardToursService;
import com.itgrids.partyanalyst.service.INewsCoreDashBoardService;
import com.itgrids.partyanalyst.service.IPaymentGatewayService;
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
	private List<TrainingCampProgramVO> trainingCampProgramVOList;
	private List<UserTypeVO> activityMembersList;
	private PartyMeetingsVO partyMeetingsVO;
	private List<PartyMeetingsVO> partyMeetingsVOList;
	private List<PartyMeetingsDataVO> partyMeetingDataVOList;
	private PartyMeetingsDataVO partyMeetingDataVO;
	private CadreRegistratedCountVO cadreRegistratedCountVO;
	private ToursBasicVO toursBasicVO;
	private List<ToursBasicVO> toursDtlsList;
	//Attributes
	private ICoreDashboardService coreDashboardService;
	private ICoreDashboardService1 coreDashboardService1;
	private ICoreDashboardMainService coreDashboardMainService;
	private ICoreDashboardGenericService coreDashboardGenericService;
	private IAttendanceCoreDashBoardService attendanceCoreDashBoardService;
	private ICoreDashboardToursService coreDashboardToursService;
	
	private List<CoreDebateVO> codeDebateVoList;
	private INewsCoreDashBoardService newsCoreDashBoardService;
	private IdNameVO idNameVO; 
	private List<CadreReportVO> cadreDtlsList;
	
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
	/**
	 * Ending Payment Gateway required parameters
	 * 
	 */
	
	//setters And Getters
	
	
	public List<PartyMeetingsVO> getPartyMeetingsVOList() {
		return partyMeetingsVOList;
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
			String state = jObj.getString("state");
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			String dateString = jObj.getString("dateString");
			
			
			committeeDataVO = coreDashboardMainService.getCommitteesBasicCountReport(userAccessLevelId,userAccessLevelValues,state,committeeLevelBasedCommitteeIdsMap,dateString);
			
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
			
			String state = jObj.getString("state");
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
	
			String dateString = jObj.getString("dateString");
			
			
			userTypeVOList = coreDashboardMainService.getUserTypeWiseCommitteesCompletedCounts1(userId,activityMemberId,userTypeId,state,committeeLevelBasedCommitteeIdsMap,dateString);
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
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			String dateString = jObj.getString("dateString");
			
			committeeDataVOList = coreDashboardMainService.getLevelWiseBasicCommitteesCountReport(userAccessLevelId,userAccessLevelValues,state,committeeLevelBasedCommitteeIdsMap,dateString);
			
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
			
			committeeDataVOList = coreDashboardMainService.committeesPerformanceCohort(tdpCommitteeLevelIdsClicked,committeeLevelBasedCommitteeIdsMap,committeeStatus,userLocationLevelId,userLocationLevelValues,dateString,state);
			
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
			activityMembersList = coreDashboardGenericService.getAllItsSubUserTypeIdsByParentUserTypeId(jObj.getLong("parentUserTypeId"));
			
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
			
			String dateString = jObj.getString("dateString");
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			activityMembersList = coreDashboardMainService.getSelectedChildUserTypeMembers(parentActivityMemberId,childUserTypeIds,state,committeeLevelBasedCommitteeIdsMap,dateString);
			
		}catch(Exception e){
			LOG.error("Exception raised at getUserTypeWiseCommitteesCompletedCounts1() method of CoreDashBoard", e);
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
			
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			activityMembersList = coreDashboardMainService.getDirectChildActivityMemberCommitteeDetails(activityMemberId,userTypeId,state,committeeLevelBasedCommitteeIdsMap,dateString);
			
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
			String dateString = jObj.getString("dateString");
			Map<Long,List<Long>> committeeLevelBasedCommitteeIdsMap = getLevelWiseBasicCommittees(jObj);
			
			committeeDataVO = coreDashboardMainService.getTopPoorPerformancecommittees(activityMemberId,committeeLevelBasedCommitteeIdsMap,state,dateString);
			
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
			
			committeeDataVOList = coreDashboardMainService.getTopPoorCommitteeLocations(activityMemberId,committeeLevelBasedCommitteeIdsMap,state,dateString);
			
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
			
			List<Long> userAccessLevelValues=new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jObj.getJSONArray("userAccessLevelValuesArray");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			Long stateId = jObj.getLong("stateId");
			String dateStr = jObj.getString("dateStr");
			trainingCampProgramVO = coreDashboardMainService.getTrainingCampBasicDetailsCntOverview(userAccessLevelId,userAccessLevelValues,stateId,dateStr);
			
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
			trainingCampProgramVOList = coreDashboardMainService.getTrainingCampProgramsDetailsCntByUserType(userAccessLevelId,userAccessLevelValues,stateId,dateStr,userTypeId);
		
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
			Long stateId = jObj.getLong("stateId");
			String dateStr = jObj.getString("dateStr");
			userTypeVOList = coreDashboardMainService.getUserTypeWiseTotalEligibleAndAttendedCnt(userId,userTypeId,activityMemberId,userAccessLevelId,userAccessLevelValues,stateId,dateStr);
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
			activityMembersList = coreDashboardMainService.getSelectedChildTypeMembersForTrainingProgram(parentActivityMemberId,childUserTypeIds,userAccessLevelId,userAccessLevelValues,reportType,stateId,dateStr);
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
		 	activityMembersList = coreDashboardMainService.getSelectedChildTypeMembersForTrainingProgram(activityMemberId,childUserTypeIds,null,null,reportType,stateId,dateStr);
	 }catch(Exception e){
		 LOG.error("Exception raised at getDirectChildActivityTrainingProgramMemberDetails() method of CoreDashBoardAction", e); 
	 }
	 return Action.SUCCESS;
}
public String getTrainingProgramPoorCompletedLocationDtls(){
	  try{
			LOG.info("Entered into getTrainingProgramPoorCompletedLocationDtls()  of CoreDashboardAction");
			jObj = new JSONObject(getTask());
			Long userTypeId = jObj.getLong("userTypeId");
			Long activityMemberId = jObj.getLong("activityMemberId");
			 Long stateId = jObj.getLong("stateId");
			 String dateStr = jObj.getString("dateStr");
			trainingCampProgramVO = coreDashboardMainService.getTrainingProgramPoorCompletedLocationDtls(userTypeId,activityMemberId,stateId,dateStr);
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
		trainingCampProgramVOList = coreDashboardMainService.getTrainingCampProgramsBasicCountDetails(userAccessLevelId,userAccessLevelValues,stateId,dateStr);
 }catch(Exception e){
	 LOG.error("Exception raised at getTrainingProgramBasicCnt() method of CoreDashBoardAction", e); 
 }
 return Action.SUCCESS;
}
//Debate Action Methods

public String getPartyWiseTotalDebateDetails(){
	
	try{
		jObj = new JSONObject(getTask());
		codeDebateVoList = coreDashboardMainService.getPartyWiseTotalDebateDetails(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"));
		
	}catch (Exception e) {
		LOG.error("Exception raised at getPartyWiseTotalDebateDetails() method of CoreDashBoardAction", e);
	}
	
	return Action.SUCCESS;
}
public String getSpokesPersonWiseDebate(){
	
	try{
		
		jObj = new JSONObject(getTask());
		codeDebateVoList = coreDashboardMainService.getSpokesPersonWiseDebate(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("searchType"),jObj.getString("state"));
		
	}catch (Exception e) {
		LOG.error("Exception raised at getSpokesPersonWiseDebate() method of CoreDashBoardAction", e);
	}
	
	return Action.SUCCESS;
}
public String getScaleBasedPerformanceCohort(){

	try{
		
		jObj = new JSONObject(getTask());
		codeDebateVoList = coreDashboardMainService.getScaleBasedPerformanceCohort(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"));
		
	}catch (Exception e) {
		LOG.error("Exception raised at getScaleBasedPerformanceCohort() method of CoreDashBoardAction", e);
	}
	return Action.SUCCESS;
}
public String getCandidateOverAllPerformanceCohort(){
	
	try{
		
		jObj = new JSONObject(getTask());
		codeDebateVoList = coreDashboardMainService.getCandidateOverAllPerformanceCohort(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"));
		
	}catch (Exception e) {
		LOG.error("Exception raised at getCandidateOverAllPerformanceCohort() method of CoreDashBoardAction", e);
	}
	
	return Action.SUCCESS;
}
public String getChannelAndPartyWiseDetails(){

	try{
		
		jObj = new JSONObject(getTask());
		codeDebateVoList = coreDashboardMainService.getChannelAndPartyWiseDetails(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"));
		
	}catch (Exception e) {
		LOG.error("Exception raised at getChannelAndPartyWiseDetails() method of CoreDashBoardAction", e);
	}
	return Action.SUCCESS;
}
public String getRoleBasedPerformanceCohort(){
	
	try{
		
		jObj = new JSONObject(getTask());
		codeDebateVoList = coreDashboardMainService.getRoleBasedPerformanceCohort(jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("state"));
				
	}catch (Exception e) {
		LOG.error("Exception raised at getRoleBasedPerformanceCohort() method of CoreDashBoardAction", e);
	}
	
	return Action.SUCCESS;  
}

	
	public String getUserTypeWiseNewsCounts(){
		try {
			Long userId = null;
			HttpSession session = request.getSession();
			 RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			 if(user == null || user.getRegistrationID() == null){
				//return ERROR;
				 userId = 1L;
			 }else
				 userId = user.getRegistrationID();
			
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
			idNameVOsList = coreDashboardMainService.getDistrictWiseCampAttendedMembers(programIdList,stateId,dateStr);
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
			
			codeDebateVoList = coreDashboardMainService.getRolesPerformanceOfCandidate(jObj.getString("startDate"),jObj.getString("endDate"),roles,jObj.getString("state"));
					
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
 				idNameVoList = coreDashboardMainService.getLeaderShipCandidateDtlsPerDist(userAccessLevelId,userAccessLevelValues,stateId,distId,dateStr);
 			
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
			
			
			partyMeetingDataVOList = coreDashboardPartyMeetingService.getPartyMeetingsMainTypeOverViewData(partyMeetingMainTypeId,partyMeetingTypeIds,state,startDateString,endDateString);
			
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
		LOG.error("Exception raised at getUserTypeWiseTotalInviteeAndInviteeAttendedCnt() method of CoreDashBoard", e);
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
		activityMembersList = coreDashboardEventsActivitiesService.getSelectedChildMembersForEvents(parentActivityMemberId,childUserTypeIds,reportType,stateId,eventsIds);
	}catch (Exception e) {
		LOG.error("Exception raised at getSelectedChildTypeMembersForEvent() method of CoreDashBoard", e);
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
		activityMembersList = coreDashboardEventsActivitiesService.getSelectedChildMembersForEvents(activityMemberId,childUserTypeIds,reportType,stateId,eventsIds);
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
		eventDetailsVO = coreDashboardEventsActivitiesService.getEventPoorPerformanceLocation(userTypeId,stateId,activityMemberId,eventsIds); 
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
		
		codeDebateVoList = coreDashboardMainService.getCoreDebateBasicDetailsOfParty(jObj.getLong("partyId"),jObj.getString("startDate"),jObj.getString("endDate"),jObj.getString("searchType"));
		
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
		
		if(cadreRegistrationVO.getIsNewImageExist().equalsIgnoreCase("newImage")){
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
			
			PaymentGatewayVO pamentGateWayVO = paymentGatewayService.getPaymentBasicInfoByPaymentGateWayType(1L,responceVO.getMemberShipNo().trim(),responceVO.getRefNo().trim(),"2016 CADRE ONLINE REGISTRATION","NORMAL REGISTRATION",cadreRegistrationVO.getDeliveryLocation());			            
			inputStream = new StringBufferInputStream("SUCCESS" +"," +responceVO.getRefNo()+"," +//1
	                ""+responceVO.getMemberShipNo().trim()+"," +//2
	                ""+pamentGateWayVO.getOrderNo().trim()+"," +//3
	                ""+pamentGateWayVO.getCheckSum().trim()+"," +//4
	                ""+pamentGateWayVO.getRedirectURL().trim()+"," +//5
	                ""+pamentGateWayVO.getAmount().trim()+",");//6
			//inputStream = new StringBufferInputStream(status);
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
		if(AuthDesc != null){
			//membershipNo = md5Algoritm.generateMD5Decrypt(mn.toString().trim());
			membershipNo = mn.toString().trim();
			resultStatus = cadreRegistrationService.updatePaymenntStatus(1L,membershipNo,AuthDesc,"2016 CADRE REGISTRATION","NORMAL REGISTRATION",enrollMentNO);
			if(!AuthDesc.trim().equalsIgnoreCase("Y"))
				status="failure";
			else if(resultStatus != null && resultStatus.getResultCode()==0)
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
		
		
		idNameVOs = coreDashboardPartyMeetingService.getParyMeetingMemberDtls(partyMeetingMainTypeId,partyMeetingTypeIds,meetingId,state,startDateString,endDateString,status);
		
}catch(Exception e){
	LOG.error("Exception raised at getPartyMeetingsMainTypeOverViewData() method of CoreDashBoard", e);
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
		String option = jObj.getString("option");
		
		idNameVoList = coreDashboardMainService.getStateLevelCampAttendedDetails(programIdList,stateId,dateStr,option);   
		
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
		idNameVOsList = coreDashboardMainService.getStateLevelCampDetailsRepresentative(programIdList,stateId,dateStr);
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
		toursBasicVO = coreDashboardToursService.getToursBasicOverviewCountDetails(stateId,fromDate,toDate,activityMemberId);
	} catch (Exception e) {
		LOG.error("Exception raised at getToursBasicOverviewCountDetails() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
}
public String getDistrictWiseToursSubmitedDetails(){
	try {
		LOG.info("Entered into getToursBasicOverviewCountDetails()  of CoreDashboardAction");
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
}
