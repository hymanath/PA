package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.AffiliatedCadreVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadrePrintVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.CadreRegistratedCountVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreReportVO;
import com.itgrids.partyanalyst.dto.CardPrintUserVO;
import com.itgrids.partyanalyst.dto.CardSenderVO;
import com.itgrids.partyanalyst.dto.CoreDashboardInsuranceVO;
import com.itgrids.partyanalyst.dto.FieldReportVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValuePairVO;
import com.itgrids.partyanalyst.dto.MeetingBasicDetailsVO;
import com.itgrids.partyanalyst.dto.MeetingDtlsVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsDataVO;
import com.itgrids.partyanalyst.dto.PaymentGatewayVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;
import com.itgrids.partyanalyst.dto.TdpCadreFamilyDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.dto.VoterInfoVO;
import com.itgrids.partyanalyst.dto.VoterSearchVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.service.ICadreRegistrationForOtherStatesService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICandidateUpdationDetailsService;
import com.itgrids.partyanalyst.service.ICoreDashboardCadreRegistrationService;
import com.itgrids.partyanalyst.service.ICoreDashboardInsuranceService;
import com.itgrids.partyanalyst.service.ICoreDashboardPartyMeetingService;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IPaymentGatewayService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.ISurveyDataDetailsService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.MD5Algoritm;
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
	private List<SelectOptionVO> 				selectOptionVOList,constituencyesList,eletionTypesList, cadreRolesVOList;
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
	private Boolean                             cadreUploadImgCadreType;
	private Boolean                             cadreUploadImgVoterType;
	private String								relativeVoterCardNo;
	
	private List<CadreRegisterInfo> cadreRegisterInfo=null;
	private String 								fromPath 	;						
	private String 								toPath 	;						
	private String								mobilenumber;
	private String								jobno;
	private String								status;
	private String								DoneTime;
	private String								messagepart;
	private String								sentStatus;
	private Long 								countDownTime;
	private ICadreRegistrationForOtherStatesService cadreRegistrationForOtherStatesService;
    private ICrossVotingEstimationService crossVotingEstimationService;
	private IConstituencyDAO constituencyDAO;
	private List<CadrePreviousRollesVO> eligibleRoles;
	private Long id1;//constituencyId
	private Long id2;//mandalId
	private Long id3;//boothId
	private Long id4;//voterId
	private Long id5;//familyVoterId
	private String voterType;
	private AddressVO addressVO;
	private String registeredOrNot;
	private List<CadreRegistrationVO> resultList = new ArrayList<CadreRegistrationVO>();
	private List<TdpCadreFamilyDetailsVO> familyDetails = new ArrayList<TdpCadreFamilyDetailsVO>();
	
	private CardPrintUserVO cardPrintUserVO;
	private AffiliatedCadreVO affiliatedCadreVO;
	private String membershipNo;
	private String enrollMentNO;
	private String mn;
	private String en;
	private String AuthDesc;
	private String Order_Id;
	private String Merchant_Id;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private MD5Algoritm md5Algoritm = new MD5Algoritm();
	private IPaymentGatewayService paymentGatewayService;
	private List<VoterVO> voterList = new ArrayList<VoterVO>();
	private List<TdpCadreVO> cadreList = new ArrayList<TdpCadreVO>();
	private List<IdAndNameVO> idAndNameVO;
	private CadreRegistratedCountVO cadreRegistratedCountVO;	
	private List<VoterSearchVO> voterVoList = new ArrayList<VoterSearchVO>();
	private List<List<UserTypeVO>> userTypeVOList;
	private List<CadreReportVO> cadreDtlsResultList;
	private ICoreDashboardCadreRegistrationService coreDashboardCadreRegistrationService;
	private CadreReportVO cadreReportVO;
	private List<UserTypeVO> activityMembersList;
	private List<CadreRegistratedCountVO> cadreRegistratedCountVOs;
	private IdAndNameVO nameVO;
	private List<CadreReportVO> cadreCnsttuncyList;
	private List<FieldReportVO> fieldReportVOs;
	private List<PartyMeetingsDataVO> partyMeetingDataVOList;
	private ICoreDashboardPartyMeetingService coreDashboardPartyMeetingService;
	private List<MeetingDtlsVO> meetingDtlsVOs;
	private List<List<MeetingDtlsVO>> listOfMeetingDtlsVOs ;
	private List<IdNameVO> idNameVOs;
	private List<MeetingBasicDetailsVO> basicDetailsVOs;
	private ICoreDashboardInsuranceService coreDashboardInsuranceService;
	private List<CoreDashboardInsuranceVO> coreDashboardInsuranceVOs;
	private List<KeyValuePairVO> keyValuePairVOList ;
	
	
	public List<KeyValuePairVO> getKeyValuePairVOList() {
		return keyValuePairVOList;
	}
	public void setKeyValuePairVOList(List<KeyValuePairVO> keyValuePairVOList) {
		this.keyValuePairVOList = keyValuePairVOList;
	}
	public List<CoreDashboardInsuranceVO> getCoreDashboardInsuranceVOs() {
		return coreDashboardInsuranceVOs;
	}
	public void setCoreDashboardInsuranceVOs(
			List<CoreDashboardInsuranceVO> coreDashboardInsuranceVOs) {
		this.coreDashboardInsuranceVOs = coreDashboardInsuranceVOs;
	}
	public ICoreDashboardInsuranceService getCoreDashboardInsuranceService() {
		return coreDashboardInsuranceService;
	}
	public void setCoreDashboardInsuranceService(
			ICoreDashboardInsuranceService coreDashboardInsuranceService) {
		this.coreDashboardInsuranceService = coreDashboardInsuranceService;
	}
	public List<List<MeetingDtlsVO>> getListOfMeetingDtlsVOs() {
		return listOfMeetingDtlsVOs;
	}
	public void setListOfMeetingDtlsVOs(
			List<List<MeetingDtlsVO>> listOfMeetingDtlsVOs) {
		this.listOfMeetingDtlsVOs = listOfMeetingDtlsVOs;
	}
	public List<MeetingBasicDetailsVO> getBasicDetailsVOs() {
		return basicDetailsVOs;
	}
	public void setBasicDetailsVOs(List<MeetingBasicDetailsVO> basicDetailsVOs) {
		this.basicDetailsVOs = basicDetailsVOs;
	}
	public List<IdNameVO> getIdNameVOs() {
		return idNameVOs;
	}
	public void setIdNameVOs(List<IdNameVO> idNameVOs) {
		this.idNameVOs = idNameVOs;
	}
	public List<MeetingDtlsVO> getMeetingDtlsVOs() {
		return meetingDtlsVOs;
	}
	public void setMeetingDtlsVOs(List<MeetingDtlsVO> meetingDtlsVOs) {
		this.meetingDtlsVOs = meetingDtlsVOs;
	}
	public ICoreDashboardPartyMeetingService getCoreDashboardPartyMeetingService() {
		return coreDashboardPartyMeetingService;
	}
	public void setCoreDashboardPartyMeetingService(
			ICoreDashboardPartyMeetingService coreDashboardPartyMeetingService) {
		this.coreDashboardPartyMeetingService = coreDashboardPartyMeetingService;
	}
	public List<PartyMeetingsDataVO> getPartyMeetingDataVOList() {
		return partyMeetingDataVOList;
	}
	public void setPartyMeetingDataVOList(
			List<PartyMeetingsDataVO> partyMeetingDataVOList) {
		this.partyMeetingDataVOList = partyMeetingDataVOList;
	}
	public List<VoterSearchVO> getVoterVoList() {
		return voterVoList;
	}
	public void setVoterVoList(List<VoterSearchVO> voterVoList) {
		this.voterVoList = voterVoList;
	}
	public List<TdpCadreVO> getCadreList() {
		return cadreList;
	}
	public void setCadreList(List<TdpCadreVO> cadreList) {
		this.cadreList = cadreList;
	}
	public List<VoterVO> getVoterList() {
		return voterList;
	}
	public void setVoterList(List<VoterVO> voterList) {
		this.voterList = voterList;
	}
	public IPaymentGatewayService getPaymentGatewayService() {
		return paymentGatewayService;
	}
	public void setPaymentGatewayService(
			IPaymentGatewayService paymentGatewayService) {
		this.paymentGatewayService = paymentGatewayService;
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
	
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	
	public String getAuthDesc() {
		return AuthDesc;
	}
	public void setAuthDesc(String authDesc) {
		AuthDesc = authDesc;
	}
	public String getOrder_Id() {
		return Order_Id;
	}
	public void setOrder_Id(String order_Id) {
		Order_Id = order_Id;
	}
	public String getMerchant_Id() {
		return Merchant_Id;
	}
	public void setMerchant_Id(String merchant_Id) {
		Merchant_Id = merchant_Id;
	}
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	
	public AffiliatedCadreVO getAffiliatedCadreVO() {
		return affiliatedCadreVO;
	}
	public void setAffiliatedCadreVO(AffiliatedCadreVO affiliatedCadreVO) {
		this.affiliatedCadreVO = affiliatedCadreVO;
	}
	public CardPrintUserVO getCardPrintUserVO() {
		return cardPrintUserVO;
	}
	public void setCardPrintUserVO(CardPrintUserVO cardPrintUserVO) {
		this.cardPrintUserVO = cardPrintUserVO;
	}
	public List<TdpCadreFamilyDetailsVO> getFamilyDetails() {
		return familyDetails;
	}
	public void setFamilyDetails(List<TdpCadreFamilyDetailsVO> familyDetails) {
		this.familyDetails = familyDetails;
	}
	public List<CadreRegistrationVO> getResultList() {
		return resultList;
	}
	public void setResultList(List<CadreRegistrationVO> resultList) {
		this.resultList = resultList;
	}

	public List<CadrePreviousRollesVO> getEligibleRoles() {
	return eligibleRoles;
	}

	public void setEligibleRoles(List<CadrePreviousRollesVO> eligibleRoles) {
	this.eligibleRoles = eligibleRoles;
	}
	
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public Long getCountDownTime() {
		return countDownTime;
	}

	public void setCountDownTime(Long countDownTime) {
		this.countDownTime = countDownTime;
	}

	public String getSentStatus() {
		return sentStatus;
	}

	public void setSentStatus(String sentStatus) {
		this.sentStatus = sentStatus;
	}

	public List<CadreRegisterInfo> getCadreRegisterInfo() {
		return cadreRegisterInfo;
	}

	public void setCadreRegisterInfo(List<CadreRegisterInfo> cadreRegisterInfo) {
		this.cadreRegisterInfo = cadreRegisterInfo;
	}

	public String getRelativeVoterCardNo() {
		return relativeVoterCardNo;
	}

	public void setRelativeVoterCardNo(String relativeVoterCardNo) {
		this.relativeVoterCardNo = relativeVoterCardNo;
	}

	public List<SelectOptionVO> getCadreRolesVOList() {
		return cadreRolesVOList;
	}

	public void setCadreRolesVOList(List<SelectOptionVO> cadreRolesVOList) {
		this.cadreRolesVOList = cadreRolesVOList;
	}

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

	public Boolean getCadreUploadImgCadreType() {
		return cadreUploadImgCadreType;
	}

	public void setCadreUploadImgCadreType(Boolean cadreUploadImgCadreType) {
		this.cadreUploadImgCadreType = cadreUploadImgCadreType;
	}

	public Boolean getCadreUploadImgVoterType() {
		return cadreUploadImgVoterType;
	}

	public void setCadreUploadImgVoterType(Boolean cadreUploadImgVoterType) {
		this.cadreUploadImgVoterType = cadreUploadImgVoterType;
	}

	
	public String getFromPath() {
		return fromPath;
	}

	public void setFromPath(String fromPath) {
		this.fromPath = fromPath;
	}

	public String getToPath() {
		return toPath;
	}

	public void setToPath(String toPath) {
		this.toPath = toPath;
	}
	
	

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getJobno() {
		return jobno;
	}

	public void setJobno(String jobno) {
		this.jobno = jobno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDoneTime() {
		return DoneTime;
	}

	public void setDoneTime(String doneTime) {
		DoneTime = doneTime;
	}

	public String getMessagepart() {
		return messagepart;
	}

	public void setMessagepart(String messagepart) {
		this.messagepart = messagepart;
	}

	public ICadreRegistrationForOtherStatesService getCadreRegistrationForOtherStatesService() {
		return cadreRegistrationForOtherStatesService;
	}

	public void setCadreRegistrationForOtherStatesService(
			ICadreRegistrationForOtherStatesService cadreRegistrationForOtherStatesService) {
		this.cadreRegistrationForOtherStatesService = cadreRegistrationForOtherStatesService;
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public Long getId1() {
		return id1;
	}

	public void setId1(Long id1) {
		this.id1 = id1;
	}

	public Long getId2() {
		return id2;
	}

	public void setId2(Long id2) {
		this.id2 = id2;
	}

	public Long getId3() {
		return id3;
	}

	public void setId3(Long id3) {
		this.id3 = id3;
	}

	public Long getId4() {
		return id4;
	}

	public void setId4(Long id4) {
		this.id4 = id4;
	}

	public Long getId5() {
		return id5;
	}

	public void setId5(Long id5) {
		this.id5 = id5;
	}

	public String getVoterType() {
		return voterType;
	}

	public void setVoterType(String voterType) {
		this.voterType = voterType;
	}

	public AddressVO getAddressVO() {
		return addressVO;
	}

	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}

	public String getRegisteredOrNot() {
		return registeredOrNot;
	}

	public void setRegisteredOrNot(String registeredOrNot) {
		this.registeredOrNot = registeredOrNot;
	}
	

	public List<IdAndNameVO> getIdAndNameVO() {
		return idAndNameVO;
	}
	public void setIdAndNameVO(List<IdAndNameVO> idAndNameVO) {
		this.idAndNameVO = idAndNameVO;
	}
	
	public CadreRegistratedCountVO getCadreRegistratedCountVO() {
		return cadreRegistratedCountVO;
	}
	public void setCadreRegistratedCountVO(
			CadreRegistratedCountVO cadreRegistratedCountVO) {
		this.cadreRegistratedCountVO = cadreRegistratedCountVO;
	}
	public List<List<UserTypeVO>> getUserTypeVOList() {
		return userTypeVOList;
	}
	public void setUserTypeVOList(List<List<UserTypeVO>> userTypeVOList) {
		this.userTypeVOList = userTypeVOList;
	}
	public ICoreDashboardCadreRegistrationService getCoreDashboardCadreRegistrationService() {
		return coreDashboardCadreRegistrationService;
	}
	public void setCoreDashboardCadreRegistrationService(
			ICoreDashboardCadreRegistrationService coreDashboardCadreRegistrationService) {
		this.coreDashboardCadreRegistrationService = coreDashboardCadreRegistrationService;
	}
	
	public List<UserTypeVO> getActivityMembersList() {
		return activityMembersList;
	}
	public void setActivityMembersList(List<UserTypeVO> activityMembersList) {
		this.activityMembersList = activityMembersList;
	}
	public List<CadreReportVO> getCadreDtlsResultList() {
		return cadreDtlsResultList;
	}
	public void setCadreDtlsResultList(List<CadreReportVO> cadreDtlsResultList) {
		this.cadreDtlsResultList = cadreDtlsResultList;
	}
	public CadreReportVO getCadreReportVO() {
		return cadreReportVO;
	}
	public void setCadreReportVO(CadreReportVO cadreReportVO) {
		this.cadreReportVO = cadreReportVO;
	}
	
	public List<CadreRegistratedCountVO> getCadreRegistratedCountVOs() {
		return cadreRegistratedCountVOs;
	}
	public void setCadreRegistratedCountVOs(
			List<CadreRegistratedCountVO> cadreRegistratedCountVOs) {
		this.cadreRegistratedCountVOs = cadreRegistratedCountVOs;
	}
	public IdAndNameVO getNameVO() {
		return nameVO;
	}
	public void setNameVO(IdAndNameVO nameVO) {
		this.nameVO = nameVO;
	}
	public List<CadreReportVO> getCadreCnsttuncyList() {
		return cadreCnsttuncyList;
	}
	public void setCadreCnsttuncyList(List<CadreReportVO> cadreCnsttuncyList) {
		this.cadreCnsttuncyList = cadreCnsttuncyList;
	}
	public List<FieldReportVO> getFieldReportVOs() {
		return fieldReportVOs;
	}
	public void setFieldReportVOs(List<FieldReportVO> fieldReportVOs) {
		this.fieldReportVOs = fieldReportVOs;
	}
	public String execute()
	{
		try {
			LOG.info("Entered into execute method in CadreRegistrationAction Action");
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			List<String> entitlements = null;
			if(user.getEntitlements() != null && user.getEntitlements().size()>0){
				entitlements = user.getEntitlements();
				if(entitlements.contains("CADRE_REGISTRATION_2014".trim())){
					return Action.SUCCESS;
				}
			/*if(entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATION_2014"))
			{
				return Action.SUCCESS;
			}*/
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
				if(cadreRegistrationVO.getPanchayatId() != null &&  Long.valueOf(cadreRegistrationVO.getPanchayatId().trim()).longValue() > 0){
					if(cadreRegistrationVO.getPanchayatId().substring(0,1).trim().equalsIgnoreCase("1")){
					  cadreRegistrationVO.setPanchayatId(cadreRegistrationVO.getPanchayatId().substring(1));
					}else if(cadreRegistrationVO.getPanchayatId().substring(0,1).trim().equalsIgnoreCase("2")){
						cadreRegistrationVO.setMuncipalityId(cadreRegistrationVO.getPanchayatId().substring(1));
						cadreRegistrationVO.setPanchayatId(null);
					}else{
						cadreRegistrationVO.setPanchayatId(null);
					}
				}
				if(cadreRegistrationVO.getDobStr() != null && cadreRegistrationVO.getDobStr().trim().length() > 0)
				cadreRegistrationVO.setDob(convertToDateFormet(cadreRegistrationVO.getDobStr()));
				if(cadreRegistrationVO.getPartyMemberSinceStr() != null && cadreRegistrationVO.getPartyMemberSinceStr().trim().length() > 0)
				cadreRegistrationVO.setPartyMemberSinceStr(cadreRegistrationVO.getPartyMemberSinceStr());
				if(relativeTypeChecked != null){
					cadreRegistrationVO.setRelative(true);
					cadreRegistrationVO.setRelationTypeId(relativeTypeId);
					if(relativeVoterCardNo != null && relativeVoterCardNo.trim().length() > 0){
						cadreRegistrationVO.setRelativeVoterId(relativeVoterCardNo);
						List<Long> ids = cadreRegistrationService.getVoterIdByVoterCard(relativeVoterCardNo.trim());
						if(ids.size() > 0 && ids.get(0)!= null){
							cadreRegistrationVO.setFamilyVoterId(ids.get(0));
						}
					}
					
				}
				if(cadreUploadImgCadreType != null){
					cadreRegistrationVO.setPhotoType("cadre");
				}else if(cadreUploadImgVoterType != null){
					cadreRegistrationVO.setPhotoType("voter");
				}else{
					cadreRegistrationVO.setPhotoType("new");
				}
				List<CadrePreviousRollesVO> rolesVOList = cadreRegistrationVO.getPreviousRollesList();
				if(rolesVOList != null && rolesVOList.size() > 0)
				{
					List<CadrePreviousRollesVO> rolesList = new ArrayList<CadrePreviousRollesVO>();
					for (CadrePreviousRollesVO cadrePreviousRollesVO : rolesVOList) 
					{
						CadrePreviousRollesVO rolesVO = new CadrePreviousRollesVO();
						
						if(cadrePreviousRollesVO!=null){
							//if(cadrePreviousRollesVO.getFromDateStr() != null && cadrePreviousRollesVO.getFromDateStr().trim().length() > 0)
							rolesVO.setFromDateStr(cadrePreviousRollesVO.getFromDateStr());
							//if(cadrePreviousRollesVO.getToDateStr() != null && cadrePreviousRollesVO.getToDateStr().trim().length() > 0)
							rolesVO.setToDateStr(cadrePreviousRollesVO.getToDateStr());
							rolesVO.setCadreCommitteeId(cadrePreviousRollesVO.getCadreCommitteeId());
							rolesVO.setCadreCommitteeLevelId(cadrePreviousRollesVO.getCadreCommitteeLevelId());
							rolesVO.setCadreRoleId(cadrePreviousRollesVO.getCadreRoleId());
							rolesList.add(rolesVO);
							
							cadreRegistrationVO.setPreviousRollesList(rolesList);
						}
					}
					
				}
				List<CadreRegistrationVO> cadreRegistrationVOList = new ArrayList<CadreRegistrationVO>();
				cadreRegistrationVO.setPath(IWebConstants.STATIC_CONTENT_FOLDER_URL);
				cadreRegistrationVOList.add(cadreRegistrationVO);
				surveyCadreResponceVO = cadreRegistrationService.saveCadreRegistration(cadreRegistrationVOList,"WEB");
				if(surveyCadreResponceVO.getResultCode() == ResultCodeMapper.SUCCESS){
					LOG.debug("fileuploades is sucess Method");
					if(surveyCadreResponceVO.getEnrollmentNumber() != null && surveyCadreResponceVO.getEnrollmentNumber().trim().length() > 0 ){
						inputStream = new StringBufferInputStream("SUCCESS" +"," +surveyCadreResponceVO.getEnrollmentNumber()  +"," +surveyCadreResponceVO.getMembershipNo()  +",");
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
			SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
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
			Integer startIndex = null;
			Integer maxIndex = null;
			try{
			 startIndex = jobj.getInt("startIndex");
			 maxIndex = jobj.getInt("maxIndex");
			}catch(Exception e){
				
			}
			
			voterInfoVOList = cadreRegistrationService.getSearchDetailsCadreRegistration(constituencyId,searchType,candidateName,voterCardNo,houseNo,panchayatId,boothId,isPresentCadre,startIndex,maxIndex);
			
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
			List<String> entitlements = null;
			if(user.getEntitlements() != null && user.getEntitlements().size()>0){
				entitlements = user.getEntitlements();
				if(entitlements.contains("CADRE_REGISTRATION_2014".trim())){
					Long stateTypeId = 0L; // 0 for All, 1 for AP, 2 for TG 
					Long stateId = 1L;
					
					if(user.getAccessType().equalsIgnoreCase("MLA") || user.getAccessType().equalsIgnoreCase("DISTRICT")){
						selectOptionVOList =	surveyDataDetailsService.getAssemblyOfLoggedUser(user.getAccessValue(),user.getAccessType());
					}else{
						selectOptionVOList = 	surveyDataDetailsService.getAssemblyConstituenciesByStateId(stateTypeId,stateId);
					}
					
				
		    		
		    		if(user.getRegistrationID().longValue() != 3930L) // party office userId
		    		{
		    			SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_AND_TIME_FORMAT_24HRS); 
			    		Date now = new DateUtilService().getCurrentDateAndTime();
			    		Date endDate = null;
			    		
		    			if(user.getAccessType().trim().equalsIgnoreCase(IConstants.STATE))
			    		{
			    			endDate = format.parse(IConstants.TG_CADRE_2014_END_DATE);
			    		}
			    		else if(user.getAccessType().trim().equalsIgnoreCase(IConstants.MLA))
			    		{
			    			Constituency constituency = constituencyDAO.get(Long.valueOf(user.getAccessValue()));
			    			if(constituency.getDistrict().getDistrictId() < 11L)
			    			{
			    				endDate = format.parse(IConstants.TG_CADRE_2014_END_DATE);
			    			}
			    			else
			    			{
			    				endDate = format.parse(IConstants.AP_CADRE_2014_END_DATE);
			    			}
			    			
			    			DoneTime = new SimpleDateFormat("dd-MM-yyyy").format(endDate);
			    		}
		    			
						Long diffInDates = endDate.getTime() - now.getTime() ;
					    Long remaingSeconds = diffInDates / 1000; // remaining seconds
					    
					    if(remaingSeconds >=0)
					    {
					    	countDownTime = remaingSeconds;
					    }
					    else
					    {
					    	countDownTime = 0L;
					    }
		    		}
				}
			}
			//if(entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATION_2014"))
			//{
			
			//}
			
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
				return INPUT;
			List<String> entitlements = null;
			if(user.getEntitlements() != null && user.getEntitlements().size()>0){
				entitlements = user.getEntitlements();
				if(entitlements.contains("CADRE_REGISTRATION_2014".trim())){
					constituencyesList = 	surveyDataDetailsService.getAssemblyConstituenciesByStateId(0l,1l);  
					genericVOList = candidateUpdationDetailsService.gettingEducationDetails();
					selectOptionVOList = staticDataService.getAllOccupations();
					eletionTypesList = cadreRegistrationService.getElectionOptionDetailsForCadre();
					cadreRolesVOList = cadreRegistrationService.getCadreLevelsForCadreSearch();
					voterInfoVOList = cadreRegistrationService.getCandidateInfoBySearchCriteria(searchType,Long.valueOf(candidateId),IWebConstants.STATIC_CONTENT_FOLDER_URL,constiteucnyId,null);
								
					/*if(user.getRegistrationID().longValue() != 3930L) // party office userId
		    		{
		    			SimpleDateFormat format = new SimpleDateFormat(IConstants.DATE_AND_TIME_FORMAT_24HRS); 
			    		Date now = new DateUtilService().getCurrentDateAndTime();
			    		Date endDate = null;
			    		
		    			if(user.getAccessType().trim().equalsIgnoreCase(IConstants.STATE))
			    		{
			    			endDate = format.parse(IConstants.TG_CADRE_2014_END_DATE);
			    		}
			    		else if(user.getAccessType().trim().equalsIgnoreCase(IConstants.MLA))
			    		{
			    			Constituency constituency = constituencyDAO.get(Long.valueOf(user.getAccessValue()));
			    			if(constituency.getDistrict().getDistrictId() < 11L)
			    			{
			    				endDate = format.parse(IConstants.TG_CADRE_2014_END_DATE);
			    			}
			    			else
			    			{
			    				endDate = format.parse(IConstants.AP_CADRE_2014_END_DATE);
			    			}	
			    			DoneTime = new SimpleDateFormat("dd-MM-yyyy").format(endDate);
			    		}
		    			
						Long diffInDates = endDate.getTime() - now.getTime() ;
					    Long remaingSeconds = diffInDates / 1000; // remaining seconds
					    
					    if(remaingSeconds >=0)
					    {
					    	countDownTime = remaingSeconds;
					    }
					    else
					    {
					    	countDownTime = 0L;
					    }
		    		}*/
					
					return Action.SUCCESS;
				}
			}
			//if(entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATION_2014"))
			//{
			
			//}
			
		} catch (Exception e) {
			LOG.error("Exception raised in tdpCadreRegistrationPage method in CadreRegistrationAction Action",e);
		}
	
		return Action.ERROR;
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
	
	public String getTehsilDetailsByConstiteuncy()
	{
		LOG.info("Entered into getTehsilDetailsByConstiteuncy method in CadreRegistrationAction Action");
		try {
			jobj = new JSONObject(getTask());
			
			Long constituencyId = jobj.getLong("constituencyId");
			String taskStr = jobj.getString("task");
			if(taskStr != null && taskStr.trim().equalsIgnoreCase("getBoothsForConstituency"))
			{
				genericVOList = cadreRegistrationForOtherStatesService.getBoothsByConstiteuncy(constituencyId);
			}
			else
			{
				genericVOList = cadreRegistrationForOtherStatesService.getTehsilByConstiteuncy(constituencyId);
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception rised in getTehsilDetailsByConstiteuncy",e);
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
	public String getBoothsDetailsByTehsil()
	{
		LOG.info("Entered into getBoothsDetailsByTehsil method in CadreRegistrationAction Action");
		try {
			jobj = new JSONObject(getTask());
			
			Long locationId = jobj.getLong("locationId");
			
			genericVOList = cadreRegistrationForOtherStatesService.getBoothsDetailsByTehsil(locationId);
			
		} catch (Exception e) {			
			LOG.error("Entered into getBoothsDetailsByTehsil method in CadreRegistrationAction Action");
		}
		return Action.SUCCESS;
	}
	public String getMultipleBoothsDetailsByLocation()
	{
		LOG.info("Entered into getconstituencyDetailsByConstiteuncy method in CadreRegistrationAction Action");
		try {
			jobj = new JSONObject(getTask());
			
			Long constituencyId = jobj.getLong("constituencyId");
			JSONArray locationIds = jobj.getJSONArray("locationId");
			List<Long> lctns = new ArrayList<Long>();
			
			for(int i=0; i<locationIds.length(); i++){
				Long lctn = Long.valueOf(locationIds.get(i).toString());
				lctns.add(lctn);
			}
			
			//Long locationId = jobj.getLong("locationId");
			
			genericVOList = cadreRegistrationService.getBoothsForMultipleLocations(constituencyId,lctns);
			
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
			
			genericVOList = cadreRegistrationService.getExistingCadreInfo(jobj.getString("name"),jobj.getLong("constituencyId"),jobj.getLong("panchayatId"),jobj.getLong("boothId"),jobj.getString("isPresentCadre"), jobj.getString("enrollmentNumber"));	
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
			List<String> entitlements = null;
			if(user.getEntitlements() != null && user.getEntitlements().size()>0){
				entitlements = user.getEntitlements();
				if((entitlements.contains("CADRE_REGISTRATION_2014".trim()))){
					genericVOList = cadreRegistrationService.getSurveyCadreUsersList();
					constituencyesList = 	surveyDataDetailsService.getAssemblyConstituenciesByStateId(0l,1l);
					selectOptionVOList = cadreRegistrationService.getSurveyCadreAssignedConstituencyList();
				}
			/*if(entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATION_2014"))
			{
				genericVOList = cadreRegistrationService.getSurveyCadreUsersList();
				constituencyesList = 	surveyDataDetailsService.getAssemblyConstituenciesByStateId(0l,1l);
				selectOptionVOList = cadreRegistrationService.getSurveyCadreAssignedConstituencyList();
			}*/
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
			constituencyList = cadreRegistrationService.constituencyListForElection(jobj.getLong("electionId"),jobj.getLong("constituencyId"));
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
	
	public String getCadreCommitteDetails()
	{
		LOG.info("Entered into getCadreCommitteDetails method in CadreRegistrationAction Action");
		try {		
			jobj = new JSONObject(getTask());
			selectOptionVOList = cadreRegistrationService.getCadreCommitteDetails(jobj.getLong("levelId"));
		}
		catch(Exception e){
			LOG.error("Exception raised in getCadreCommitteDetails method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
		
	}
	
	public String getCadreCommitteRoles()
	{
		LOG.info("Entered into getCadreCommitteRoles method in CadreRegistrationAction Action");
		try {		
			jobj = new JSONObject(getTask());
			selectOptionVOList = cadreRegistrationService.getCadreCommitteRoles(jobj.getLong("levelId"),jobj.getLong("committeeId"));
		}
		catch(Exception e){
			LOG.error("Exception raised in getCadreCommitteRoles method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;		
	}	
	public String getCadreImageByPreviousEnrolId(){
		try {		
			
			task = cadreRegistrationService.getCadreImageByPreviousEnrolId(request.getParameter("enrolmentId"),IWebConstants.STATIC_CONTENT_FOLDER_URL);
		}
		catch(Exception e){
			LOG.error("Exception raised in getCadreImageByPreviousEnrolId method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
	}
	public String getTdpCadreIdCardDetails(){
		LOG.info("Entered into getTdpCadreIdCardDetails method in CadreRegistrationAction Action");
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			
			if(user == null)
				return Action.INPUT;
			
			//if(entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATION_2014"))
			{
				Long stateTypeId = 0L; // 0 for All, 1 for AP, 2 for TG 
				Long stateId = 1L;

				selectOptionVOList = 	surveyDataDetailsService.getAssemblyConstituenciesByStateId(stateTypeId,stateId);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getTdpCadreIdCardDetails method in CadreRegistrationAction Action",e);
		}
	
		return Action.SUCCESS;
	}
	
	public void setCardSenderAndReceiverDetails(){
		LOG.info("Entered into setCardSenderAndReceiverDetails method in CadreRegistrationAction Action");
		
		try {
			
			jobj = new JSONObject(getTask());			
			CardSenderVO cardSenderVO=new CardSenderVO();
			cardSenderVO.setName(jobj.getString("name"));
			cardSenderVO.setMobileNumber(jobj.getString("mobileno"));
			cardSenderVO.setMessage(jobj.getString("message"));
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			cardSenderVO.setUserId(user.getRegistrationID());
						
			JSONArray cadreIds = jobj.getJSONArray("tdpcadreids");
			List<Long> cdrIds = new ArrayList<Long>();
			for(int i=0; i<cadreIds.length(); i++){
				Long lctn = Long.valueOf(cadreIds.get(i).toString());
				cdrIds.add(lctn);
			}
			
			cardSenderVO.setCadreIds(cdrIds);
			
			JSONArray mobiNums = jobj.getJSONArray("mobileNumbers");
			StringBuffer mobileNms = new StringBuffer(); 
			for(int i=0; i<mobiNums.length(); i++){
				String mob = mobiNums.get(i).toString();
				mobileNms.append(mob);
				mobileNms.append(",");
			}
			
			if(mobileNms.length()>1){
				mobileNms.substring(0,mobileNms.length() - 1);
			}
			
			cardSenderVO.setMobileNums(mobileNms.toString());
						
			surveyCadreResponceVO = cadreRegistrationService.tdpCardSenderSavingLogic(cardSenderVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised in setCardSenderAndReceiverDetails method in CadreRegistrationAction Action",e);
		}		
	}
	
	public String copySdCardDetails()
	{
		try {
			String pathSep = System.getProperty(IConstants.FILE_SEPARATOR);
			fromPath = fromPath.replaceAll("$",pathSep );
			toPath = toPath.replaceAll("$",pathSep );
			FileUtils.copyFile(new File(fromPath), new File(toPath));
			searchType = "success";
			LOG.error("SUCCESS FULLY COPIED");
		} catch (Exception e) {
			searchType = "failure";
			LOG.error("Exception raised in copySdCardDetails method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}	
	public String getDistrictsByStateWiseAction(){
		LOG.info("Entered into getDistrictsByStateWiseAction method in CadreRegistrationAction Action");
		
		try{
			jobj = new JSONObject(getTask());
			Long stateId = jobj.getLong("stateid");
			
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			
			String accessType = regVO.getAccessType();
			Long accessValue = Long.valueOf(regVO.getAccessValue());
			
			cadreRegisterInfo = cadreRegistrationService.getDistrictsByStateWiseAction(stateId);
			//cadreRegisterInfo = cadreRegistrationService.getDistrictsByStateBasedOnAccess(stateId,accessType,accessValue);
		}
		catch (Exception e) {
			LOG.error("Exception raised in getDistrictsByStateWiseAction method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	public String getConstsByStateWiseAction(){
		LOG.info("Entered into getConstsByStateWiseAction method in CadreRegistrationAction Action");
		
		try{
			jobj = new JSONObject(getTask());
			Long stateId = jobj.getLong("stateid");
			
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			
			String accessType = regVO.getAccessType();
			Long accessValue = Long.valueOf(regVO.getAccessValue());
			
			cadreRegisterInfo = cadreRegistrationService.getConstsByStateWiseAction(stateId,regVO);
			//cadreRegisterInfo = cadreRegistrationService.getConstituenciesByStateBasedOnAccess(stateId,accessType,accessValue);
		}
		catch (Exception e) {
			LOG.error("Exception raised in getConstsByStateWiseAction method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String updateSmsJobStatus(){
		LOG.info("Entered into updateSmsJobStatus method in CadreRegistrationAction Action");
		
		try{
			String mobileNumber = request.getParameter("mobilenumber");
			String jobno = request.getParameter("jobno");
			String dateTime = request.getParameter("DoneTime");
			int status = Integer.parseInt(request.getParameter("status"));
			
     
			sentStatus = cadreRegistrationService.updateSmsJobStatus(mobileNumber,jobno,dateTime,status);
			
		}
		catch (Exception e) {
			LOG.error("Exception raised in updateSmsJobStatus method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String saveCadreDetailsForOtherStates()
	{
		try {
			LOG.info("Entered into saveCadreDetailsForOtherStates method in CadreRegistrationAction Action");
			if(cadreRegistrationVO != null)
			{
				session = request.getSession();
				RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
				if(user == null)
				{
					inputStream = new StringBufferInputStream(",notlogged,");
					return Action.SUCCESS;
				}
				List<String> entitlements = null;
				if(user.getEntitlements() != null && user.getEntitlements().size()>0){
					entitlements = user.getEntitlements();
					if(!(entitlements.contains("CADRE_REGISTRATIONFOR_OTHERSTATES".trim()))){
						inputStream = new StringBufferInputStream(",notAccess,");
						return Action.SUCCESS;
					}
				/*if(!entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATIONFOR_OTHERSTATES"))
				{
					inputStream = new StringBufferInputStream(",notAccess,");
					return Action.SUCCESS;
				}*/
				cadreRegistrationVO.setCreatedUserId(user.getRegistrationID());
				if(cadreRegistrationVO.getPanchayatId() != null &&  Long.valueOf(cadreRegistrationVO.getPanchayatId().trim()).longValue() > 0){
					if(cadreRegistrationVO.getPanchayatId().substring(0,1).trim().equalsIgnoreCase("1")){
					  cadreRegistrationVO.setPanchayatId(cadreRegistrationVO.getPanchayatId().substring(1));
					}else if(cadreRegistrationVO.getPanchayatId().substring(0,1).trim().equalsIgnoreCase("2")){
						cadreRegistrationVO.setMuncipalityId(cadreRegistrationVO.getPanchayatId().substring(1));
						cadreRegistrationVO.setPanchayatId(null);
					}else{
						cadreRegistrationVO.setPanchayatId(null);
					}
				}
				if(cadreRegistrationVO.getDobStr() != null && cadreRegistrationVO.getDobStr().trim().length() > 0)
				cadreRegistrationVO.setDob(convertToDateFormet(cadreRegistrationVO.getDobStr()));
				if(cadreRegistrationVO.getPartyMemberSinceStr() != null && cadreRegistrationVO.getPartyMemberSinceStr().trim().length() > 0)
				cadreRegistrationVO.setPartyMemberSinceStr(cadreRegistrationVO.getPartyMemberSinceStr());
				if(relativeTypeChecked != null){
					cadreRegistrationVO.setRelative(true);
					cadreRegistrationVO.setRelationTypeId(relativeTypeId);
					if(relativeVoterCardNo != null && relativeVoterCardNo.trim().length() > 0){
						cadreRegistrationVO.setRelativeVoterId(relativeVoterCardNo);
						List<Long> ids = cadreRegistrationService.getVoterIdByVoterCard(relativeVoterCardNo.trim());
						if(ids.size() > 0 && ids.get(0)!= null){
							cadreRegistrationVO.setFamilyVoterId(ids.get(0));
						}
					}
					
				}
				if(cadreUploadImgVoterType != null){
					cadreRegistrationVO.setPhotoType("voter");
				}else{
					cadreRegistrationVO.setPhotoType("new");
				}
				
				List<CadreRegistrationVO> cadreRegistrationVOList = new ArrayList<CadreRegistrationVO>();
				cadreRegistrationVO.setPath(IWebConstants.STATIC_CONTENT_FOLDER_URL);
				surveyCadreResponceVO = new SurveyCadreResponceVO();
				  cadreRegistrationForOtherStatesService.tdpCadreSavingLogic(addressVO,cadreRegistrationVO,surveyCadreResponceVO,"new",true,cadreRegistrationVO.getRegistrationType());
				  if(surveyCadreResponceVO.getStatus().equalsIgnoreCase("alreadyRegistered")){
					  inputStream = new StringBufferInputStream(",alreadyRegistered,");
					  return Action.SUCCESS;
				  }else if(surveyCadreResponceVO.getStatus().equalsIgnoreCase("error")){
					  inputStream = new StringBufferInputStream(",regfailed,");
					  return Action.SUCCESS;
				  }else if(surveyCadreResponceVO.getStatus().equalsIgnoreCase("SUCCESS")){
					LOG.debug("fileuploades is sucess Method");
					if(surveyCadreResponceVO.getEnrollmentNumber() != null && surveyCadreResponceVO.getEnrollmentNumber().trim().length() > 0 ){
						inputStream = new StringBufferInputStream("SUCCESS" +"," +surveyCadreResponceVO.getEnrollmentNumber()  +",");
					}else{
						inputStream = new StringBufferInputStream(",regfailed,");
					}
				  }else{
					  inputStream = new StringBufferInputStream(",regfailed,");
				  }
			}
		}
		} catch (Exception e) {
			LOG.error("Exception raised in saveCadreDetailsForOtherStates method in CadreRegistrationAction Action",e);
			inputStream = new StringBufferInputStream(",regfailed,");
			  return Action.SUCCESS;
		}
		return Action.SUCCESS;
	}
	
	public String cadreEnrollment(){
		//id1 constituencyId
		//id2 mandalId
		//id3 boothId
		//id4 voterId
		//id5 familyVoterId
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		List<String> entitlements = null;
		if(user.getEntitlements() != null && user.getEntitlements().size()>0){
			entitlements = user.getEntitlements();
			if(!(entitlements.contains("CADRE_REGISTRATIONFOR_OTHERSTATES".trim()))){
				voterType = "voter";
				Long candidateId = id4;
				if(id5 != null && id5.longValue() > 0){
					voterType = "familyVoter";
					candidateId = id5;
				}
				registeredOrNot ="notRegistered";
				if(voterType.equalsIgnoreCase("voter")){
					String status = cadreRegistrationForOtherStatesService.checkVoterAlreadyRegisteredOrNot(candidateId);
					if(status.equalsIgnoreCase("alreadyRegistered")){
						registeredOrNot = "registered";
						return Action.SUCCESS;
					}
				}
				Long stateId = cadreRegistrationForOtherStatesService.getStateByConstituencyId(id1);
				constituencyesList = cadreRegistrationForOtherStatesService.getAllDistrictsByStateId(stateId);//all districts
				relativeTypeId = cadreRegistrationForOtherStatesService.getDistrictIdByConstituencyId(id1);//selected constituency districtId
				if(relativeTypeId != null){
				 cadreRolesVOList = cadreRegistrationForOtherStatesService.getAllMandalsInADistrict(relativeTypeId);//all mandals in selected district
				}
				if(cadreRolesVOList == null){
					cadreRolesVOList = new ArrayList<SelectOptionVO>();
				}
				genericVOList = candidateUpdationDetailsService.gettingEducationDetails();
				selectOptionVOList = staticDataService.getAllOccupations();
				voterInfoVOList = cadreRegistrationForOtherStatesService.getCandidateInfoBySearchCriteria(voterType,candidateId,id1);
			}
			return Action.SUCCESS;
		} else{
			return "error";
		}
	}
		//if(entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATIONFOR_OTHERSTATES"))
		//{
		   /* voterType = "voter";
			Long candidateId = id4;
			if(id5 != null && id5.longValue() > 0){
				voterType = "familyVoter";
				candidateId = id5;
			}
			registeredOrNot ="notRegistered";
			if(voterType.equalsIgnoreCase("voter")){
				String status = cadreRegistrationForOtherStatesService.checkVoterAlreadyRegisteredOrNot(candidateId);
				if(status.equalsIgnoreCase("alreadyRegistered")){
					registeredOrNot = "registered";
					return Action.SUCCESS;
				}
			}
			Long stateId = cadreRegistrationForOtherStatesService.getStateByConstituencyId(id1);
			constituencyesList = cadreRegistrationForOtherStatesService.getAllDistrictsByStateId(stateId);//all districts
			relativeTypeId = cadreRegistrationForOtherStatesService.getDistrictIdByConstituencyId(id1);//selected constituency districtId
			if(relativeTypeId != null){
			 cadreRolesVOList = cadreRegistrationForOtherStatesService.getAllMandalsInADistrict(relativeTypeId);//all mandals in selected district
			}
			if(cadreRolesVOList == null){
				cadreRolesVOList = new ArrayList<SelectOptionVO>();
			}
			genericVOList = candidateUpdationDetailsService.gettingEducationDetails();
			selectOptionVOList = staticDataService.getAllOccupations();
			voterInfoVOList = cadreRegistrationForOtherStatesService.getCandidateInfoBySearchCriteria(voterType,candidateId,id1);
			return Action.SUCCESS;*/
		//}
		/*	else{
			return "error";
		}*/
	//}
	
	public String saveCommitteCadreDetails()
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
				if(cadreRegistrationVO.getPanchayatId() != null &&  Long.valueOf(cadreRegistrationVO.getPanchayatId().trim()).longValue() > 0){
					if(cadreRegistrationVO.getPanchayatId().substring(0,1).trim().equalsIgnoreCase("1")){
					  cadreRegistrationVO.setPanchayatId(cadreRegistrationVO.getPanchayatId().substring(1));
					}else if(cadreRegistrationVO.getPanchayatId().substring(0,1).trim().equalsIgnoreCase("2")){
						cadreRegistrationVO.setMuncipalityId(cadreRegistrationVO.getPanchayatId().substring(1));
						cadreRegistrationVO.setPanchayatId(null);
					}else{
						cadreRegistrationVO.setPanchayatId(null);
					}
				}
				if(cadreRegistrationVO.getDobStr() != null && cadreRegistrationVO.getDobStr().trim().length() > 0)
				cadreRegistrationVO.setDob(convertToDateFormet(cadreRegistrationVO.getDobStr()));
				if(cadreRegistrationVO.getPartyMemberSinceStr() != null && cadreRegistrationVO.getPartyMemberSinceStr().trim().length() > 0)
				cadreRegistrationVO.setPartyMemberSinceStr(cadreRegistrationVO.getPartyMemberSinceStr());
				if(relativeTypeChecked != null){
					cadreRegistrationVO.setRelative(true);
					cadreRegistrationVO.setRelationTypeId(relativeTypeId);
					if(relativeVoterCardNo != null && relativeVoterCardNo.trim().length() > 0){
						cadreRegistrationVO.setRelativeVoterId(relativeVoterCardNo);
						List<Long> ids = cadreRegistrationService.getVoterIdByVoterCard(relativeVoterCardNo.trim());
						if(ids.size() > 0 && ids.get(0)!= null){
							cadreRegistrationVO.setFamilyVoterId(ids.get(0));
						}
					}
					
				}
				if(cadreUploadImgCadreType != null){
					cadreRegistrationVO.setPhotoType("cadre");
				}else if(cadreUploadImgVoterType != null){
					cadreRegistrationVO.setPhotoType("voter");
				}else{
					cadreRegistrationVO.setPhotoType("new");
				}
				
				//CADRE PREVIOUS ROLES IN COMMITTEE.
				List<CadrePreviousRollesVO> rolesVOList = cadreRegistrationVO.getPreviousRollesList();
				if(rolesVOList != null && rolesVOList.size() > 0)
				{
					List<CadrePreviousRollesVO> rolesList = new ArrayList<CadrePreviousRollesVO>();
					for (CadrePreviousRollesVO cadrePreviousRollesVO : rolesVOList) 
					{
						CadrePreviousRollesVO rolesVO = new CadrePreviousRollesVO();
						
						if(cadrePreviousRollesVO!=null){
							//if(cadrePreviousRollesVO.getFromDateStr() != null && cadrePreviousRollesVO.getFromDateStr().trim().length() > 0)
							rolesVO.setFromDateStr(cadrePreviousRollesVO.getFromDateStr());
							//if(cadrePreviousRollesVO.getToDateStr() != null && cadrePreviousRollesVO.getToDateStr().trim().length() > 0)
							rolesVO.setToDateStr(cadrePreviousRollesVO.getToDateStr());
							rolesVO.setCadreCommitteeId(cadrePreviousRollesVO.getCadreCommitteeId());
							rolesVO.setCadreCommitteeLevelId(cadrePreviousRollesVO.getCadreCommitteeLevelId());
							rolesVO.setCadreRoleId(cadrePreviousRollesVO.getCadreRoleId());
							rolesVO.setCommitteeLocationId(cadrePreviousRollesVO.getCommitteeLocationId());
							rolesList.add(rolesVO);
							
							cadreRegistrationVO.setPreviousRollesList(rolesList);
						}
					}
					
				}
				List<CadreRegistrationVO> cadreRegistrationVOList = new ArrayList<CadreRegistrationVO>();
				cadreRegistrationVO.setPath(IWebConstants.STATIC_CONTENT_FOLDER_URL);
				cadreRegistrationVOList.add(cadreRegistrationVO);
				surveyCadreResponceVO = cadreRegistrationService.saveCommitteCadreRegistration(user.getRegistrationID(),cadreRegistrationVOList,eligibleRoles,"WEB");
			}
		} catch (Exception e) {
			LOG.error("Exception raised in saveCadreDetails method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getExistingCadreInfoForCommittee(){
		LOG.info("Entered into getExistingCadreInfoForCommittee method in CadreRegistrationAction Action");
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		Long constId=Long.parseLong(user.getAccessValue());
		try {		
			jobj = new JSONObject(getTask());
			Long panchayatId=jobj.getLong("panchayatId");
			Long areaType=jobj.getLong("areaType");
			genericVOList = cadreRegistrationService.getExistingCadreInfoForCommittee(jobj.getString("name"),constId,panchayatId,jobj.getLong("boothId"),jobj.getString("isPresentCadre"), jobj.getString("enrollmentNumber"),areaType);	
		}
		catch(Exception e){
			LOG.error("Exception raised in getExistingCadreInfoForCommittee method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
	}
	
	public String tdpCadreSearchPageForOtherStates()
	{
		LOG.info("Entered into tdpCadreSearchPage method in CadreRegistrationAction Action");
		try {
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			
			if(user == null)
				return Action.INPUT;
			List<String> entitlements = null;
			if(user.getEntitlements() != null && user.getEntitlements().size()>0){
				entitlements = user.getEntitlements();
				if((entitlements.contains("CADRE_REGISTRATIONFOR_OTHERSTATES".trim()))){
					Long stateTypeId = 0L; // 0 for All, 1 for AP, 2 for TG 
					
					if(user.getAccessType().equalsIgnoreCase("MLA")){
						selectOptionVOList =	surveyDataDetailsService.getAssemblyOfLoggedUser(user.getAccessValue(),user.getAccessType());
					}else if(user.getAccessType().equalsIgnoreCase("STATE")){
						selectOptionVOList = 	surveyDataDetailsService.getAssemblyConstituenciesByStateId(stateTypeId,Long.valueOf(user.getAccessValue().trim()));
					}else if(user.getAccessType().equalsIgnoreCase("DISTRICT")){
						selectOptionVOList = 	surveyDataDetailsService.getAssemblyOfLoggedUser(user.getAccessValue(),user.getAccessType());
					}
				}else{
					return "error";
				}
			}
			/*if(entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATIONFOR_OTHERSTATES"))
			{
				Long stateTypeId = 0L; // 0 for All, 1 for AP, 2 for TG 
				
				if(user.getAccessType().equalsIgnoreCase("MLA")){
					selectOptionVOList =	surveyDataDetailsService.getAssemblyOfLoggedUser(user.getAccessValue(),user.getAccessType());
				}else if(user.getAccessType().equalsIgnoreCase("STATE")){
					selectOptionVOList = 	surveyDataDetailsService.getAssemblyConstituenciesByStateId(stateTypeId,Long.valueOf(user.getAccessValue().trim()));
				}else if(user.getAccessType().equalsIgnoreCase("DISTRICT")){
					selectOptionVOList = 	surveyDataDetailsService.getAssemblyOfLoggedUser(user.getAccessValue(),user.getAccessType());
				}
	    		
			}else{
				return "error";
			}*/
			
		} catch (Exception e) {
			LOG.error("Exception raised in tdpCadreSearchPage method in CadreRegistrationAction Action",e);
		}
	
		return Action.SUCCESS;
	}
	
	public String searchVoterAndCadreInfoForOtherStates(){
		LOG.info("Entered into searchVoterAndCadreInfoForOtherStates method in CadreRegistrationAction Action");
	
		try {
			jobj = new JSONObject(getTask());
			Long stateId = jobj.getLong("stateId");
			Long constituencyId = jobj.getLong("constituencyId");
			String candidateName = jobj.getString("candidateName");
			String houseNo = jobj.getString("houseNo");
			String voterCardNo = jobj.getString("voterCardNo");
			Long tehsilId = jobj.getLong("panchayatId");
			Long boothId = jobj.getLong("boothId");
			Integer startIndex = null;
			Integer maxIndex = null;
			try{
			 startIndex = jobj.getInt("startIndex");
			 maxIndex = jobj.getInt("maxIndex");
			}catch(Exception e){
				
			}
			
			voterInfoVOList = cadreRegistrationForOtherStatesService.getSearchDetailsCadreRegistration(stateId,constituencyId,candidateName,voterCardNo,
					                       houseNo,tehsilId,boothId,startIndex,maxIndex);
			
		} catch (Exception e) {
			LOG.error("Exception raised in searchVoterAndCadreInfoForOtherStates method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getAllMandalsInADistrict(){
		try {
			selectOptionVOList = cadreRegistrationForOtherStatesService.getAllMandalsInADistrict(Long.valueOf(request.getParameter("districtId")));
		} catch (Exception e) {
			LOG.error("Exception raised in getAllMandalsInADistrict method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCasteDetailsForCasteCategory()
	{
		try {
			selectOptionVOList = cadreRegistrationForOtherStatesService.getCasteDetailsByCasteCategoryId(Long.valueOf(request.getParameter("casteCategoryId")),Long.valueOf(request.getParameter("stateId")));
		} catch (Exception e) {
			LOG.error("Exception raised in getCasteDetailsForCasteCategory method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String tempararyCadreCardsRegistrationPage()
	{
		try {
			LOG.info("Entered into execute method in saveTempararyCadreCardsRegistrationPage Action");
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			List<String> entitlements = null;
			if(user.getEntitlements() != null && user.getEntitlements().size()>0){
				entitlements = user.getEntitlements();
				if((entitlements.contains("CADRE_REGISTRATION_2014".trim()) || entitlements.contains("OTHER_STATE_DELEGATE_REG".trim()))){
					return Action.SUCCESS;
				}
			/*if(entitlementsHelper.checkForEntitlementToViewReport(user,"CADRE_REGISTRATION_2014") || entitlementsHelper.checkForEntitlementToViewReport(user,"OTHER_STATE_DELEGATE_REG") )
			{
				return Action.SUCCESS;
			}*/
			}
		} catch (Exception e) {
			LOG.error("Exception raised in execute method in CadreRegistrationAction Action",e);
		}
		return Action.ERROR;
	}
	
	@SuppressWarnings("deprecation")
	public String saveTempararyCadreDetailsForOtherStates()
	{
		try {
			LOG.info("Entered into saveCadreDetailsForOtherStates method in CadreRegistrationAction Action");
			if(cadreRegistrationVO != null)
			{
				session = request.getSession();
				RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
				if(user == null)
				{
					inputStream = new StringBufferInputStream(",notlogged,");
					return Action.SUCCESS;
				}
				List<String> entitlements = null;
				if(user.getEntitlements() != null && user.getEntitlements().size()>0){
					entitlements = user.getEntitlements();
					if(!(entitlements.contains("OTHER_STATE_DELEGATE_REG".trim()))){
						inputStream = new StringBufferInputStream(",notAccess,");
						return Action.SUCCESS;
					}
				/*if(!entitlementsHelper.checkForEntitlementToViewReport(user,"OTHER_STATE_DELEGATE_REG"))
				{
					inputStream = new StringBufferInputStream(",notAccess,");
					return Action.SUCCESS;
				}*/
				cadreRegistrationVO.setCreatedUserId(user.getRegistrationID());
				if(cadreRegistrationVO.getPanchayatId() != null &&  Long.valueOf(cadreRegistrationVO.getPanchayatId().trim()).longValue() > 0){
					if(cadreRegistrationVO.getPanchayatId().substring(0,1).trim().equalsIgnoreCase("1")){
					  cadreRegistrationVO.setPanchayatId(cadreRegistrationVO.getPanchayatId().substring(1));
					}else if(cadreRegistrationVO.getPanchayatId().substring(0,1).trim().equalsIgnoreCase("2")){
						cadreRegistrationVO.setMuncipalityId(cadreRegistrationVO.getPanchayatId().substring(1));
						cadreRegistrationVO.setPanchayatId(null);
					}else{
						cadreRegistrationVO.setPanchayatId(null);
					}
				}
				if(cadreRegistrationVO.getDobStr() != null && cadreRegistrationVO.getDobStr().trim().length() > 0)
				cadreRegistrationVO.setDob(convertToDateFormet(cadreRegistrationVO.getDobStr()));
				if(cadreRegistrationVO.getPartyMemberSinceStr() != null && cadreRegistrationVO.getPartyMemberSinceStr().trim().length() > 0)
				cadreRegistrationVO.setPartyMemberSinceStr(cadreRegistrationVO.getPartyMemberSinceStr());
				if(relativeTypeChecked != null){
					cadreRegistrationVO.setRelative(true);
					cadreRegistrationVO.setRelationTypeId(relativeTypeId);
					if(relativeVoterCardNo != null && relativeVoterCardNo.trim().length() > 0){
						cadreRegistrationVO.setRelativeVoterId(relativeVoterCardNo);
						List<Long> ids = cadreRegistrationService.getVoterIdByVoterCard(relativeVoterCardNo.trim());
						if(ids.size() > 0 && ids.get(0)!= null){
							cadreRegistrationVO.setFamilyVoterId(ids.get(0));
						}
					}
					
				}
				if(cadreUploadImgVoterType != null){
					cadreRegistrationVO.setPhotoType("voter");
				}else{
					cadreRegistrationVO.setPhotoType("new");
				}
				
				List<CadreRegistrationVO> cadreRegistrationVOList = new ArrayList<CadreRegistrationVO>();
				cadreRegistrationVO.setPath(IWebConstants.STATIC_CONTENT_FOLDER_URL);
				surveyCadreResponceVO = new SurveyCadreResponceVO();
				  cadreRegistrationForOtherStatesService.tdpTempararyCadreSavingLogic(addressVO,cadreRegistrationVO,surveyCadreResponceVO,"new",true,cadreRegistrationVO.getRegistrationType());
				  if(surveyCadreResponceVO.getStatus().equalsIgnoreCase("alreadyRegistered")){
					  inputStream = new StringBufferInputStream(",alreadyRegistered,");
					  return Action.SUCCESS;
				  }else if(surveyCadreResponceVO.getStatus().equalsIgnoreCase("error")){
					  inputStream = new StringBufferInputStream(",regfailed,");
					  return Action.SUCCESS;
				  }else if(surveyCadreResponceVO.getStatus().equalsIgnoreCase("SUCCESS")){
					LOG.debug("fileuploades is sucess Method");
					if(surveyCadreResponceVO.getEnrollmentNumber() != null && surveyCadreResponceVO.getEnrollmentNumber().trim().length() > 0 ){
						inputStream = new StringBufferInputStream("SUCCESS" +"," +surveyCadreResponceVO.getEnrollmentNumber()  +",");
					}else{
						inputStream = new StringBufferInputStream(",regfailed,");
					}
				  }else{
					  inputStream = new StringBufferInputStream(",regfailed,");
				  }
			}
		}
		} catch (Exception e) {
			LOG.error("Exception raised in saveCadreDetailsForOtherStates method in CadreRegistrationAction Action",e);
			inputStream = new StringBufferInputStream(",regfailed,");
			  return Action.SUCCESS;
		}
		return Action.SUCCESS;
	}
	
	
	public String searchCadreForFamilyDetlsUpdation()
	{
		try {
			
			jobj = new JSONObject(getTask());			
			String membershipNo = jobj.getString("membershipNo");
			String voterId = jobj.getString("voterId");
			String mobileNo = jobj.getString("mobileNo");
			resultList = cadreRegistrationService.searchCadreDetailsForFamilyDetlsUpdate(mobileNo,voterId,membershipNo);
			
		} catch (Exception e) {
			LOG.info("Exception raised in searchCadreForFamilyDetlsUpdation ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getFamilyDetailsByCadreId()
	{
		try {
			
			jobj = new JSONObject(getTask());			
			Long tdpCadreId = jobj.getLong("tdpCadreId");
			
			familyDetails = cadreRegistrationService.getFamilyDetailsByCadreId(tdpCadreId);
			
		} catch (Exception e) {
			LOG.info("Exception raised in getFamilyDetailsByCadreId ",e);
		}
		return Action.SUCCESS;
	}
	public String getCadreReprintCount()
	{
		try{
			
			jobj = new JSONObject(getTask());
			
			List<Long> enrollmentYearIds = new ArrayList<Long>(0);
			JSONArray yearIds = jobj.getJSONArray("enrollmentYearIdsArr");
			if(yearIds != null && yearIds.length() > 0){
				for (int i = 0; i < yearIds.length(); i++) {
					enrollmentYearIds.add(Long.parseLong(yearIds.getString(i)));
				}
			}
			
			cardPrintUserVO =  cadreRegistrationService.getCadrePrintDetails(jobj.getString("startDate"),jobj.getString("endDate"),enrollmentYearIds);
		}
		catch (Exception e) {
			LOG.error(" Entered Into getCadreReprintCount",e);
		}
		return Action.SUCCESS;
	}
	public String getCadreWithFamilyDetailsOfEachCadre(){
		
		try{
			
			jobj = new JSONObject(getTask());
			
			
			resultList=cadreRegistrationService.getCadreWithFamilyDetailsOfEachCadre(jobj.getLong("cadreId"));
			
		}catch(Exception e){
			LOG.error(" Entered Into getCadreDetailsOfEachCadre",e);
		}
		
		return Action.SUCCESS;
		
	}
	
	public String saveAffliatedCadreDetails(){
		try {
			LOG.info("Entered into saveAffliatedCadreDetails method in CadreRegistrationAction Action");
			if(cadreRegistrationVO != null)
			{
				session = request.getSession();
				RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
				/*if(user == null)
				{
					inputStream = new StringBufferInputStream("notlogged");
					return Action.SUCCESS;
				}*/
				if(user != null)
					cadreRegistrationVO.setCreatedUserId(user.getRegistrationID());
				else
					cadreRegistrationVO.setCreatedUserId(1l);
				if(cadreRegistrationVO.getPanchayatId() != null &&  Long.valueOf(cadreRegistrationVO.getPanchayatId().trim()).longValue() > 0){
					if(cadreRegistrationVO.getPanchayatId().substring(0,1).trim().equalsIgnoreCase("1")){
					  cadreRegistrationVO.setPanchayatId(cadreRegistrationVO.getPanchayatId().substring(1));
					}else if(cadreRegistrationVO.getPanchayatId().substring(0,1).trim().equalsIgnoreCase("2")){
						cadreRegistrationVO.setMuncipalityId(cadreRegistrationVO.getPanchayatId().substring(1));
						cadreRegistrationVO.setPanchayatId(null);
					}else{
						cadreRegistrationVO.setPanchayatId(null);
					}
				}
				if(cadreRegistrationVO.getDobStr() != null && cadreRegistrationVO.getDobStr().trim().length() > 0){
					cadreRegistrationVO.setDob(convertToDateFormet(cadreRegistrationVO.getDobStr()));
				}
				
				//PARTY MEMBER SINCE
				/*if(cadreRegistrationVO.getPartyMemberSinceStr() != null && cadreRegistrationVO.getPartyMemberSinceStr().trim().length() > 0)
				cadreRegistrationVO.setPartyMemberSinceStr(cadreRegistrationVO.getPartyMemberSinceStr());*/
				
				if(relativeTypeChecked != null){
					cadreRegistrationVO.setRelative(true);
					cadreRegistrationVO.setRelationTypeId(relativeTypeId);
					if(relativeVoterCardNo != null && relativeVoterCardNo.trim().length() > 0){
						cadreRegistrationVO.setRelativeVoterId(relativeVoterCardNo);
						List<Long> ids = cadreRegistrationService.getVoterIdByVoterCard(relativeVoterCardNo.trim());
						if(ids.size() > 0 && ids.get(0)!= null){
							cadreRegistrationVO.setFamilyVoterId(ids.get(0));
						}
					}
					
				}
				if(cadreUploadImgCadreType != null){
					cadreRegistrationVO.setPhotoType("cadre");
				}else if(cadreUploadImgVoterType != null){
					cadreRegistrationVO.setPhotoType("voter");
				}else{
					cadreRegistrationVO.setPhotoType("new");
				}
				
				//PREVIOUS ROLES
				/*List<CadrePreviousRollesVO> rolesVOList = cadreRegistrationVO.getPreviousRollesList();
				if(rolesVOList != null && rolesVOList.size() > 0)
				{
					List<CadrePreviousRollesVO> rolesList = new ArrayList<CadrePreviousRollesVO>();
					for (CadrePreviousRollesVO cadrePreviousRollesVO : rolesVOList) 
					{
						CadrePreviousRollesVO rolesVO = new CadrePreviousRollesVO();
						
						if(cadrePreviousRollesVO!=null){
							//if(cadrePreviousRollesVO.getFromDateStr() != null && cadrePreviousRollesVO.getFromDateStr().trim().length() > 0)
							rolesVO.setFromDateStr(cadrePreviousRollesVO.getFromDateStr());
							//if(cadrePreviousRollesVO.getToDateStr() != null && cadrePreviousRollesVO.getToDateStr().trim().length() > 0)
							rolesVO.setToDateStr(cadrePreviousRollesVO.getToDateStr());
							rolesVO.setCadreCommitteeId(cadrePreviousRollesVO.getCadreCommitteeId());
							rolesVO.setCadreCommitteeLevelId(cadrePreviousRollesVO.getCadreCommitteeLevelId());
							rolesVO.setCadreRoleId(cadrePreviousRollesVO.getCadreRoleId());
							rolesList.add(rolesVO);
							
							cadreRegistrationVO.setPreviousRollesList(rolesList);
						}
					}
					
				}*/
				List<CadreRegistrationVO> cadreRegistrationVOList = new ArrayList<CadreRegistrationVO>();
				cadreRegistrationVO.setPath(IWebConstants.STATIC_CONTENT_FOLDER_URL);
				
				if(cadreRegistrationVO.getPerAddrsMandalId() != null && cadreRegistrationVO.getPerAddrsMandalId() > 0l){
					if(cadreRegistrationVO.getPerAddrsMandalId().toString().substring(0,1).equalsIgnoreCase("4")){//mandal
						cadreRegistrationVO.setPerAddrsMandalId(Long.parseLong(cadreRegistrationVO.getPerAddrsMandalId().toString().substring(1)));
					}
					else if(cadreRegistrationVO.getPerAddrsMandalId().toString().substring(0,1).equalsIgnoreCase("5")){//local election body
						cadreRegistrationVO.setPerAddrsLebId(Long.parseLong(cadreRegistrationVO.getPerAddrsMandalId().toString().substring(1)));
						cadreRegistrationVO.setPerAddrsMandalId(null);
					}
					else if(cadreRegistrationVO.getPerAddrsMandalId().toString().substring(0,1).equalsIgnoreCase("6")){//divison
						cadreRegistrationVO.setPerAddrsDivionId(Long.parseLong(cadreRegistrationVO.getPerAddrsMandalId().toString().substring(1)));
						cadreRegistrationVO.setPerAddrsMandalId(null);
					}
				}
				
				if(cadreRegistrationVO.getPerAddrsVillId() != null && cadreRegistrationVO.getPerAddrsVillId() > 0l){
					if(cadreRegistrationVO.getPerAddrsVillId().toString().substring(0, 1).equalsIgnoreCase("7")){//village
						cadreRegistrationVO.setPerAddrsVillId(Long.parseLong(cadreRegistrationVO.getPerAddrsVillId().toString().substring(1)));
					}
					else if(cadreRegistrationVO.getPerAddrsVillId().toString().substring(0, 1).equalsIgnoreCase("8")){//ward
						cadreRegistrationVO.setPerAddrsWardId(Long.parseLong(cadreRegistrationVO.getPerAddrsVillId().toString().substring(1)));
						cadreRegistrationVO.setPerAddrsVillId(null);
					}
				}
				
				cadreRegistrationVOList.add(cadreRegistrationVO);
				//surveyCadreResponceVO = cadreRegistrationService.saveAfflicatedCadreRegistration(cadreRegistrationVOList,"ONLINE");
				if(cadreRegistrationVO.getDataSourceType() != null && cadreRegistrationVO.getDataSourceType().trim().equalsIgnoreCase("ONLINE"))
					surveyCadreResponceVO = cadreRegistrationService.saveAfflicatedCadreRegistration(cadreRegistrationVOList,"ONLINE");
				else
					surveyCadreResponceVO = cadreRegistrationService.saveAfflicatedCadreRegistration(cadreRegistrationVOList,"WEB");
				
				if(surveyCadreResponceVO.getResultCode() == ResultCodeMapper.SUCCESS){/*
					LOG.debug("fileuploades is sucess Method");
					if(surveyCadreResponceVO.getEnrollmentNumber() != null && surveyCadreResponceVO.getEnrollmentNumber().trim().length() > 0 ){
						inputStream = new StringBufferInputStream("SUCCESS" +"," +surveyCadreResponceVO.getEnrollmentNumber()  +"," +surveyCadreResponceVO.getMembershipNo()  +",");
					}
				*/
			         /* String checkSumDetails = commonMethodsUtilService.getChecksumDetails("CADRE_2016"+surveyCadreResponceVO.getMembershipNo(),"100","https://www.mytdp.com/registrationSuccessAction.action?membershipNo="+surveyCadreResponceVO.getMembershipNo()+"&enrollMentNO="+surveyCadreResponceVO.getEnrollmentNumber()+"&status=success");
			            
			            inputStream = new StringBufferInputStream("SUCCESS" +"," +surveyCadreResponceVO.getEnrollmentNumber()+"," +
			                "" +surveyCadreResponceVO.getMembershipNo()+"," +//2
			                ""+"CADRE_2016"+surveyCadreResponceVO.getMembershipNo()+"," +//3
			                ""+checkSumDetails+"," +//4
			                ""+"https://www.mytdp.com/registrationSuccessAction.action?membershipNo="+surveyCadreResponceVO.getMembershipNo()+"&enrollMentNO="+surveyCadreResponceVO.getEnrollmentNumber()+"&status=success"+"," +//5
			                "100,");//6*/
					PaymentGatewayVO pamentGateWayVO = paymentGatewayService.getPaymentBasicInfoByPaymentGateWayType(1L,surveyCadreResponceVO.getMembershipNo().trim(),surveyCadreResponceVO.getEnrollmentNumber().trim(),"AFFILIATED CADRE REGISTRATION","NORMAL REGISTRATION","0");//0 means other extra charges like delivery charges	            
					inputStream = new StringBufferInputStream("SUCCESS" +"," +surveyCadreResponceVO.getEnrollmentNumber()+"," +//1
			                ""+surveyCadreResponceVO.getMembershipNo().trim()+"," +//2
			                ""+pamentGateWayVO.getOrderNo().trim()+"," +//3
			                ""+pamentGateWayVO.getCheckSum().trim()+"," +//4
			                ""+pamentGateWayVO.getRedirectURL().trim()+"," +//5
			                ""+pamentGateWayVO.getAmount().trim()+",");//6
			          }	
				else
					inputStream = new StringBufferInputStream("fail");
			}
		} catch (Exception e) {
			LOG.error("Exception raised in saveCadreDetails method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String searchVoterAndCadreInfoBySearchCriteriaRTC()
	{
		LOG.info("Entered into searchVoterAndCadreInfoBySearchCriteriaRTC method in CadreRegistrationAction Action");
	
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
			Long memberTypeId = jobj.getLong("memberTypeId");
			Integer startIndex = null;
			Integer maxIndex = null;
			try{
			 startIndex = jobj.getInt("startIndex");
			 maxIndex = jobj.getInt("maxIndex");
			}catch(Exception e){
				
			}
			
			voterInfoVOList = cadreRegistrationService.getSearchDetailsCadreRegistrationRTC(constituencyId,searchType,candidateName,voterCardNo,houseNo,panchayatId,boothId,isPresentCadre,startIndex,maxIndex,memberTypeId);
			
		} catch (Exception e) {
			LOG.error("Exception raised in searchVoterAndCadreInfoBySearchCriteriaRTC method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCadreCountsByTdpMemberType(){
		
		try {
			jobj = new JSONObject(getTask());
			
			String searchType = jobj.getString("searchType");
			
			affiliatedCadreVO = cadreRegistrationService.getCadreCountsByTdpMemberType(searchType);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getCadreCountsByTdpMemberType method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	public String registrationSuccess(){
		try {
			//enrollMentNO = md5Algoritm.generateMD5Decrypt(en.toString().trim());
			enrollMentNO = en.toString().trim();
				//membershipNo = md5Algoritm.generateMD5Decrypt(mn.toString().trim());
				membershipNo = mn.toString().trim();
				resultStatus = cadreRegistrationService.updatePaymenntStatus(1L,membershipNo,AuthDesc,"2016 CADRE REGISTRATION","NORMAL REGISTRATION",enrollMentNO);
				
				if(AuthDesc != null){
					if(!AuthDesc.trim().equalsIgnoreCase("Y"))
						status="failure";
					else if(AuthDesc.trim().equalsIgnoreCase("Y") && resultStatus != null && resultStatus.getResultCode()==0)
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
	
	public String getVotersBySearch(){
		try {
			jobj = new JSONObject(getTask());
			
			Long constituencyId = jobj.getLong("constituencyId");
			Long mandalId = jobj.getLong("mandalId");
			Long villageId = jobj.getLong("villageId");
			Long boothId = jobj.getLong("boothId");
			String name = jobj.getString("name");
			//String mobileNo = jobj.getString("mobileNo");
			String hNo = jobj.getString("hNo");
			String voterCrdNo = jobj.getString("voterCrdNo");
			
			voterVoList = cadreRegistrationService.getVotersBySearch(constituencyId, mandalId, villageId, boothId, name, hNo, voterCrdNo,null);
		} catch (Exception e) {
			LOG.error("Exception raised in registrationSuccess method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}

	public String getStateWiseDistrict() {

		try {
			jobj = new JSONObject(getTask());

			Long stateId = jobj.getLong("stateId");

			idAndNameVO = cadreRegistrationService.getStateWiseDistrict(stateId);

		} catch (Exception e) {
			LOG.error(
					"Exception raised in getStateWiseDistrict method in CadreRegistrationAction Action",
					e);
		}
		return Action.SUCCESS;
	}
	public String getDistrictWiseConstituency() {

		try {
			jobj = new JSONObject(getTask());

			Long districtId = jobj.getLong("districtId");

			idAndNameVO = cadreRegistrationService.getDistrictWiseConstituency(districtId);

		} catch (Exception e) {
			LOG.error(
					"Exception raised in getStateWiseDistrict method in CadreRegistrationAction Action",
					e);
		}
		return Action.SUCCESS;
	}
	public String getConstitencyWiseTehsil() {

		try {
			jobj = new JSONObject(getTask());

			Long constituencyId = jobj.getLong("consistencyId");

			idAndNameVO = cadreRegistrationService.getConstitencyWiseTehsil(constituencyId);

		} catch (Exception e) {
			LOG.error(
					"Exception raised in getConstitencyWiseTehsil method in CadreRegistrationAction Action",
					e);
		}
		return Action.SUCCESS;
	}
	public String getAllPanchayatsForMandal(){
		try{
			jobj=new JSONObject(getTask());
			idAndNameVO=cadreRegistrationService.getPanchayatOrConsList(jobj.getLong("mandalId"),jobj.getString("typeId"));
		}catch(Exception e){
			LOG.error("Entered into getAllPanchayatsForMandal method in CadreRegistrationAction....");
		}
		return Action.SUCCESS;
	}
  public String getAllBoothsForPanchayat(){
	  try{
		  jobj=new JSONObject(getTask());
		  idAndNameVO=cadreRegistrationService.getBoothsList(jobj.getLong("panchayatId"));
		  
	  }catch(Exception e){
		  LOG.error("Entered into getAllBoothsForPanchayat method in CadreRegistrationAction....");
	  }
	  return Action.SUCCESS;
  }

  public String newCadreRegistration(){
	  return Action.SUCCESS;
  }
  
  public String cadreOnlineRegistration(){
	  return Action.SUCCESS;
  }  
  public String cadreWebRegistration(){
	  try {
		  	session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute(IConstants.USER);
			if(user==null)
				return "input";
			boolean noaccess = false;
			List<String> entitlements = null;
			if(user != null && user.getEntitlements() != null && user.getEntitlements().size()>0){
				entitlements = user.getEntitlements();
				if(!(entitlements.contains("CADRE_WEB_REGISTRATION_2016") || entitlements.contains("CADRE_WEB_REGISTRATION_2016_ADMIN_USER"))){
					noaccess = true ;
				}
			}
			if(noaccess)
				return Action.ERROR;
	} catch (Exception e) {
		LOG.error("Entered into cadreWebRegistration method in CadreRegistrationAction....");
	}
	  return Action.SUCCESS;
  } 
  
  public String getTdpCadresBySearch(){
	  try {
		jobj = new JSONObject(getTask());
		
		String memberShipNo = jobj.getString("memberShipNo");
		String mobileNo = jobj.getString("mobileNo");
		String voterNo = jobj.getString("voterNo");
		//cadreList = cadreRegistrationService.getTdpCadresBySearch(memberShipNo, mobileNo, voterNo);
		
		// null--> the same call using for android web service call to search cadre people in user assigned constituencies only  
		cadreList = cadreRegistrationService.getTdpCadresBySearch(null,memberShipNo, mobileNo, voterNo);
	} catch (Exception e) {
		LOG.error("Entered into getTdpCadresBySearch method in CadreRegistrationAction....");
	}
	  return Action.SUCCESS;
  }
  
  public String getAllConstitencyList(){
	  try{
		  idAndNameVO=cadreRegistrationService.getStateWiseConstituency();
	  }catch(Exception e){
		  LOG.error("Entered into getAllConstitencyList method in CadreRegistrationAction.");
	  }
	  return Action.SUCCESS;
  }
  public String getTotalNewRenewalCadreStateWise(){
	  try{
		  jobj = new JSONObject(getTask());
		  String startDate = jobj.getString("startDate");
		  String endDate = jobj.getString("endDate");
		  Long activityMemberId = jobj.getLong("activityMemberId");
		  Long stateId = jobj.getLong("stateId"); 
		  cadreRegistratedCountVO = coreDashboardCadreRegistrationService.getTotalNewRenewalCadreStateWise(activityMemberId,stateId,startDate, endDate);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	  return Action.SUCCESS;
  }
  public String getUserTypeWiseTotalCadreRegistrationCount(){
	  try{
		  jobj = new JSONObject(getTask());
		  final HttpSession session = request.getSession();
			Long userId = null;
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				//return ERROR;
				userId = 1L;
			}
			else
				userId = user.getRegistrationID();
			
			Long activityMemberId = jobj.getLong("activityMemberId");
			Long stateId = jobj.getLong("stateId");
			Long userTypeId = jobj.getLong("userTypeId");
			String fromDate = jobj.getString("fromDate");
			String todate = jobj.getString("todate");  
			String sortingType = jobj.getString("sortingType");
			userTypeVOList = coreDashboardCadreRegistrationService.getUserTypeWiseTotalCadreRegistrationCount(activityMemberId,stateId,userTypeId,userId,fromDate,todate,sortingType);
	  }catch(Exception e){
		  LOG.error("Error occured at getUserTypeWiseTotalCadreRegistrationCount() in CadreRegistrationAction class",e);  
	  }
	  return Action.SUCCESS;
  }
  public String getCadreDetailsBasedOnUserType(){
	  try{
		  jobj = new JSONObject(getTask());
			Long activityMemberId = jobj.getLong("activityMemberId");
			Long stateId = jobj.getLong("stateId");
			Long userTypeId = jobj.getLong("userTypeId");
			String fromDate = jobj.getString("fromDate");
			String todate = jobj.getString("todate");
			String sortingType = jobj.getString("sortingType");
			cadreDtlsResultList = coreDashboardCadreRegistrationService.getCadreDetailsBasedOnUserType(activityMemberId,stateId,userTypeId,fromDate,todate,sortingType);
	  }catch(Exception e){
		  LOG.error("Error occured at getCadreDetailsBasedOnUserType() in CadreRegistrationAction class",e);  
	  }
	  return Action.SUCCESS;
  }
  public String getApAndTsConstituenciesDtls(){
	  try{
		  jobj = new JSONObject(getTask());
			String locationType = jobj.getString("locationType");
			Long stateId = jobj.getLong("stateId");
			String fromDate = jobj.getString("fromDate");
			String todate = jobj.getString("todate");
			Long accessLevelId = jobj.getLong("accessLevelId");
			List<Long> userAccessLevelValues = new CopyOnWriteArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jobj.getJSONArray("accessLevelValues");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			String isKuppamExcluded = jobj.getString("isKuppamExcluded");
			String sortingType = jobj.getString("sortingType");
			cadreDtlsResultList = coreDashboardCadreRegistrationService.getApAndTsConstituenciesDtls(stateId,locationType,fromDate,todate,accessLevelId,userAccessLevelValues,isKuppamExcluded,sortingType);
	  }catch(Exception e){
		  LOG.error("Error occured at getLocationWiseCadreDetails() in CadreRegistrationAction class",e);  
	  }
	  return Action.SUCCESS;
  }
  public String getApTsDistrictList(){
	  try{
		  cadreReportVO = coreDashboardCadreRegistrationService.getApAndTsDistrictList();
	  }catch(Exception e){
		  LOG.error("Error occured at getApTsDistrictList() in CadreRegistrationAction class",e);  
	  }
	  return Action.SUCCESS;
  }
  public String getOccupationList(){
	  try{
		  idAndNameVO=coreDashboardCadreRegistrationService.getOccupationList();
	  }catch(Exception e){
		  LOG.error("Entered into getOccupationList method in CadreRegistrationAction.");
	  }
	  return Action.SUCCESS;
}
  public String getSelectedChildTypeMembersForCadreRegistration(){
	  try{
		  jobj = new JSONObject(getTask());
		  Long parentActivityMemberId = jobj.getLong("parentActivityMemberId");
		  List<Long> childUserTypeIds=new ArrayList<Long>();
		  JSONArray childUserTypeIdsArray=jobj.getJSONArray("childUserTypeIdsArray");
		  if(childUserTypeIdsArray!=null &&  childUserTypeIdsArray.length()>0){
			  for( int i=0;i<childUserTypeIdsArray.length();i++){
				  childUserTypeIds.add(Long.valueOf(childUserTypeIdsArray.getString(i)));
			  }
		  }
		  Long stateId = jobj.getLong("stateId");
		  String fromDateStr = jobj.getString("fromDateStr");
		  String toDateStr = jobj.getString("toDateStr");
		  String sortingType = jobj.getString("sortingType");
		  activityMembersList = coreDashboardCadreRegistrationService.getSelectedChildTypeMembersForCadreRegistration(parentActivityMemberId, childUserTypeIds, stateId, fromDateStr, toDateStr,sortingType);

		  }catch(Exception e){
			  e.printStackTrace();
		  LOG.error("Error occured at getSelectedChildTypeMembersForCadreRegistration() in CadreRegistrationAction class",e);
		  }
		  return Action.SUCCESS;
	  }
  public String getSendOtpDetails(){
	  try{
		  jobj = new JSONObject(getTask());
		  status=coreDashboardCadreRegistrationService.generatingAndSavingOTPDetails(jobj.getLong("tdpCadreId"),jobj.getString("mobileNumber"));
	  }catch(Exception e){
		  e.printStackTrace();
		  LOG.error("Error occured at getSendOtpDetails() in CadreRegistrationAction class",e);
	  }
	  return Action.SUCCESS;
  }
  
  public String getOnlineCadreRegistrationVoterDetails(){
	  try{
		  jobj=new JSONObject(getTask());
		  voterVoList=cadreRegistrationService.getOnlineCadreRegistrationVoterDetails(jobj.getString("VoterCardNumber"));
	  }catch(Exception e){
		  e.printStackTrace();
		  LOG.error("Error occured at getOnlineCadreRegistrationVoterDetails() in CadreRegistrationAction class",e);
	  }
	  return Action.SUCCESS;
  }
  
  public String getOnliCadRegistrSearchVoteDetails(){
	  try{
		  jobj=new JSONObject(getTask());
		  Long constencyId=jobj.getLong("consistencyId");
		  Long mandalId=jobj.getLong("mandalId");
		  Long villageId=jobj.getLong("villageId");
		  Long boothId=jobj.getLong("boothId");
		  String type=jobj.getString("type");
		  String typeVal=jobj.getString("typeValue");
		  
		  voterVoList=cadreRegistrationService.getOnliCadRegistrSearchVoteDetails(constencyId,mandalId,villageId,boothId,type,typeVal);
	  }catch(Exception e){
		  e.printStackTrace();
		  LOG.error("Error occured at getOnliCadRegistrSearchVoteDetails() in CadreRegistrationAction class",e);
	  }
	  return Action.SUCCESS;
  }
  
  public String getSelectedChildTypeMembersForCadreReg(){
	  try{
		  jobj = new JSONObject(getTask());
		  Long parentActivityMemberId = jobj.getLong("parentActivityMemberId");
		  List<Long> childUserTypeIds=new ArrayList<Long>();
		  JSONArray childUserTypeIdsArray=jobj.getJSONArray("childUserTypeIdsArray");
		  if(childUserTypeIdsArray!=null &&  childUserTypeIdsArray.length()>0){
			  for( int i=0;i<childUserTypeIdsArray.length();i++){
				  childUserTypeIds.add(Long.valueOf(childUserTypeIdsArray.getString(i)));
			  }
		  }  
		  Long stateId = jobj.getLong("stateId");
		  String fromDateStr = jobj.getString("fromDateStr");
		  String toDateStr = jobj.getString("toDateStr");
		  String sortingType = jobj.getString("sortingType");
		  activityMembersList = coreDashboardCadreRegistrationService.getSelectedChildTypeMembersForCadreReg(parentActivityMemberId, childUserTypeIds, stateId, fromDateStr, toDateStr,sortingType);

	  }catch(Exception e){
		  e.printStackTrace();
		  LOG.error("Error occured at getSelectedChildTypeMembersForCadreReg() in CadreRegistrationAction class",e);
	  }
	  return Action.SUCCESS;
  }
  public String getStateDtls(){
	  try{
		  jobj = new JSONObject(getTask());
		  String startDate = jobj.getString("startDate");
		  String endDate = jobj.getString("endDate");
		  Long activityMemberId = jobj.getLong("activityMemberId");
		  Long stateId = jobj.getLong("stateId"); 
		  cadreRegistratedCountVO = coreDashboardCadreRegistrationService.getStateDtls(activityMemberId,stateId,startDate, endDate);
	  }catch(Exception e){
		  e.printStackTrace();  
	  }
	  return Action.SUCCESS;
  }
  public String getSourceOfRegistrationDtls(){
	  try{
		  jobj = new JSONObject(getTask());
		  String startDate = jobj.getString("startDate");
		  String endDate = jobj.getString("endDate");
		  Long activityMemberId = jobj.getLong("activityMemberId");
		  Long stateId = jobj.getLong("stateId"); 
		  cadreRegistratedCountVOs = coreDashboardCadreRegistrationService.getSourceOfRegistrationDtls(activityMemberId,stateId,startDate, endDate);
	  }catch(Exception e){  
		  e.printStackTrace();    
	  }
	  return Action.SUCCESS;
  }
  
  
  public String getOtpStatus(){
	  try{
		  jobj = new JSONObject(getTask());
		  status=coreDashboardCadreRegistrationService.getOtpStatus(jobj.getLong("tdpCadreId"),jobj.getString("otpTxt"));
	  }catch(Exception e){
		  e.printStackTrace();
		  LOG.error("Error occured at getOtpStatus() in CadreRegistrationAction class",e);
	  }
	  return Action.SUCCESS;
  }
  public String getDtlsOfBellowLvlMember(){  
	  try{
		  jobj = new JSONObject(getTask());
		  String startDate = jobj.getString("startDate");
		  String endDate = jobj.getString("endDate");
		  Long activityMemberId = jobj.getLong("activityMemberId");  
		  Long stateId = jobj.getLong("stateId"); 
		  cadreRegistratedCountVOs = coreDashboardCadreRegistrationService.getDtlsOfBellowLvlMember(activityMemberId,stateId,startDate, endDate);
	  }catch(Exception e){  
		  e.printStackTrace();     
	  }
	  return Action.SUCCESS;
  }
  public String getEnumerationDtlsForMem(){  
	  try{
		  jobj = new JSONObject(getTask());
		  String startDate = jobj.getString("startDate");
		  String endDate = jobj.getString("endDate");
		  Long activityMemberId = jobj.getLong("activityMemberId");  
		  Long stateId = jobj.getLong("stateId"); 
		  cadreRegistratedCountVO = coreDashboardCadreRegistrationService.getEnumerationDtlsForMem(activityMemberId,stateId,startDate, endDate);
	  }catch(Exception e){  
		  e.printStackTrace();           
	  }
	  return Action.SUCCESS;
  }
  public String getVoterInfo(){  
	  try{
		  jobj = new JSONObject(getTask());
		  String startDate = jobj.getString("startDate");
		  String endDate = jobj.getString("endDate");
		  Long activityMemberId = jobj.getLong("activityMemberId");  
		  Long stateId = jobj.getLong("stateId"); 
		  nameVO = coreDashboardCadreRegistrationService.getVoterInfo( activityMemberId, stateId, startDate,  endDate);
	  }catch(Exception e){    
		  e.printStackTrace();                 
	  }
	  return Action.SUCCESS;  
  }
  
 /* public String getAllDistrictsList(){
	  try{
		  idAndNameVO=cadreRegistrationService.getAllDistricts();
	  }catch(Exception e){
		  LOG.error("Entered into getAllDistrictsList method in CadreRegistrationAction.");
	  }
	  return Action.SUCCESS;
  }*/
  public String getConstituencyWiseReportBasedOnUserType(){  
	  try{
		  jobj = new JSONObject(getTask());
		  String startDate = jobj.getString("startDate");
		  String endDate = jobj.getString("endDate");
		  Long activityMemberId = jobj.getLong("activityMemberId");  
		  Long stateId = jobj.getLong("stateId"); 
		  String sortingType = jobj.getString("sortingType");
		  cadreCnsttuncyList = coreDashboardCadreRegistrationService.getConstituencyWiseReportBasedOnUserType(activityMemberId,stateId,startDate, endDate,sortingType);
	  }catch(Exception e){  
		  e.printStackTrace();  
		  LOG.error("Error occured at getConstituencyWiseReportBasedOnUserType() in CadreRegistrationAction class",e);
	  }
	  return Action.SUCCESS;
  }
  public String getTotalNewRenewalCadreStateWiseTS(){
	  try{
		  jobj = new JSONObject(getTask());
		  String startDate = jobj.getString("startDate");
		  String endDate = jobj.getString("endDate");
		  Long activityMemberId = jobj.getLong("activityMemberId");
		  Long stateId = jobj.getLong("stateId"); 
		  cadreRegistratedCountVO = coreDashboardCadreRegistrationService.getTotalNewRenewalCadreStateWiseTS(activityMemberId,stateId,startDate, endDate);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	  return Action.SUCCESS;
  }
  public String getStateDtlsTS(){  
	  try{
		  jobj = new JSONObject(getTask());
		  String startDate = jobj.getString("startDate");
		  String endDate = jobj.getString("endDate");
		  Long activityMemberId = jobj.getLong("activityMemberId");  
		  Long stateId = jobj.getLong("stateId"); 
		  cadreRegistratedCountVO = coreDashboardCadreRegistrationService.getStateDtlsTS(activityMemberId,stateId,startDate, endDate);
	  }catch(Exception e){
		  e.printStackTrace();    
	  }
	  return Action.SUCCESS;
  }
  public String getInFieldCount(){  
	  try{
		  jobj = new JSONObject(getTask());
		  String startDate = jobj.getString("startDate");
		  String endDate = jobj.getString("endDate");
		  //Long stateId = jobj.getLong("stateId"); 
		  nameVO = coreDashboardCadreRegistrationService.getInFieldCount(startDate, endDate);  
	  }catch(Exception e){ 
		  e.printStackTrace();      
	  }
	  return Action.SUCCESS;  
  }
  public String getHourWiseRegDtls(){  
	  try{
		  jobj = new JSONObject(getTask());
		
		  String option = jobj.getString("option");
		  Long stateId = jobj.getLong("stateId");   
		  fieldReportVOs = coreDashboardCadreRegistrationService.getHourWiseRegDtls(stateId, option);  
	  }catch(Exception e){   
		  e.printStackTrace();       
	  }
	  return Action.SUCCESS;
  }
  
  public String getStateWiseMandalMuncipalityNotStartedCount(){
	  try{
		  jobj = new JSONObject(getTask());
		  Long stateId = jobj.getLong("stateId");
		  cadreRegistratedCountVO = coreDashboardCadreRegistrationService.getStateWiseMandalMuncipalityNotStartedCount(stateId);
	  }catch(Exception e){
		  e.printStackTrace();   
	  }
	  return Action.SUCCESS;
  }
  public String getDistrictsByState(){
		LOG.info("Entered into getDistrictsByStateWiseAction method in CadreRegistrationAction Action");
		
		try{
			jobj = new JSONObject(getTask());
			String state = jobj.getString("stateid");
			Long stateId = null;
			if(state.equals("AP")){
				stateId = 1l;
			}else{
				stateId = 36l;
			}
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			
			String accessType = regVO.getAccessType();
			Long accessValue = Long.valueOf(regVO.getAccessValue());
			
			cadreRegisterInfo = cadreRegistrationService.getDistrictsByState(stateId);
		}
		catch (Exception e) {
			LOG.error("Exception raised in getDistrictsByStateWiseAction method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
  public String getTsDistrictDetails(){
	  try{
		  jobj = new JSONObject(getTask());
			String locationType = jobj.getString("locationType");
			Long stateId = jobj.getLong("stateId");
			String fromDate = jobj.getString("fromDate");
			String todate = jobj.getString("todate");
			Long accessLevelId = jobj.getLong("accessLevelId");
			List<Long> userAccessLevelValues = new ArrayList<Long>();
			JSONArray userAccessLevelValuesArray=jobj.getJSONArray("accessLevelValues");
			if(userAccessLevelValuesArray!=null &&  userAccessLevelValuesArray.length()>0){
				for( int i=0;i<userAccessLevelValuesArray.length();i++){
					userAccessLevelValues.add(Long.valueOf(userAccessLevelValuesArray.getString(i)));
				}
			}
			String sortingType = jobj.getString("sortingType");
			cadreDtlsResultList = coreDashboardCadreRegistrationService.getTsDistrictDetails(stateId,locationType,fromDate,todate,accessLevelId,userAccessLevelValues,sortingType);
	  }catch(Exception e){
		  LOG.error("Error occured at getLocationWiseCadreDetails() in CadreRegistrationAction class",e);  
	  }
	  return Action.SUCCESS;
  }
  public String getParyMeetingDetailsDistrictWise(){
		try{
			jobj = new JSONObject(getTask());  
			
			Long partyMeetingMainTypeId = jobj.getLong("partyMeetingMainTypeId");
			
			List<Long> partyMeetingTypeIds = new ArrayList<Long>();
			JSONArray partyMeetingTypeIdsArray=jobj.getJSONArray("partyMeetingTypeIds");
			if(partyMeetingTypeIdsArray!=null &&  partyMeetingTypeIdsArray.length()>0){
				for( int i=0;i<partyMeetingTypeIdsArray.length();i++){
					partyMeetingTypeIds.add(Long.valueOf(partyMeetingTypeIdsArray.getString(i)));
				}
			}
			  
			String state = jobj.getString("state");
			String startDateString = jobj.getString("startDateString");
			String endDateString   = jobj.getString("endDateString");
			Long partyMeetingId   = jobj.getLong("partyMeetingId");
			Long sessionId   = jobj.getLong("sessionId");
			meetingDtlsVOs = coreDashboardPartyMeetingService.getParyMeetingDetailsDistrictWise(partyMeetingMainTypeId,partyMeetingTypeIds,state,startDateString,endDateString,partyMeetingId,sessionId);
			
	}catch(Exception e){  
		LOG.error("Exception raised at getPartyMeetingsMainTypeOverViewData() method of CoreDashBoard", e);
	}
		return Action.SUCCESS;
  }
  public String getMeetingMemberDtls(){
		try{
			jobj = new JSONObject(getTask());  
			
			Long partyMeetingMainTypeId = jobj.getLong("partyMeetingMainTypeId");
			
			List<Long> partyMeetingTypeIds = new ArrayList<Long>();
			JSONArray partyMeetingTypeIdsArray=jobj.getJSONArray("partyMeetingTypeIds");
			if(partyMeetingTypeIdsArray!=null &&  partyMeetingTypeIdsArray.length()>0){
				for( int i=0;i<partyMeetingTypeIdsArray.length();i++){
					partyMeetingTypeIds.add(Long.valueOf(partyMeetingTypeIdsArray.getString(i)));
				}
			}
			  
			String state = jobj.getString("state");
			String startDateString = jobj.getString("startDateString");
			String endDateString   = jobj.getString("endDateString");
			Long partyMeetingId   = jobj.getLong("partyMeetingId");
			Long sessionId   = jobj.getLong("sessionId");
			String status   = jobj.getString("status");
			Long districtId   = jobj.getLong("districtId");
			boolean isNonInvitee = jobj.getBoolean("isNonInvitee");
			
			idNameVOs = coreDashboardPartyMeetingService.getMeetingMemberDtls(partyMeetingMainTypeId,partyMeetingTypeIds,state,startDateString,endDateString,partyMeetingId,sessionId,status,districtId,isNonInvitee);
			
	}catch(Exception e){    
		LOG.error("Exception raised at getMeetingMemberDtls() method of CoreDashBoard", e);
	}
		return Action.SUCCESS;
  }
  public String getPartyMeetingSession(){
		try{
			jobj = new JSONObject(getTask());
			Long partyMeetingId = jobj.getLong("partyMeetingId");    
			idNameVOs = coreDashboardPartyMeetingService.getPartyMeetingSession(partyMeetingId);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised at getPartyMeetingSession() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
  	public String getPublicRepAndcommitteeInviteeDtls(){
		
		try{
			
			jobj = new JSONObject(getTask());
			
			Long partyMeetingMainTypeId = jobj.getLong("partyMeetingMainTypeId");
			
			List<Long> partyMeetingTypeIds = new ArrayList<Long>();
			JSONArray partyMeetingTypeIdsArray=jobj.getJSONArray("partyMeetingTypeIds");
			if(partyMeetingTypeIdsArray!=null &&  partyMeetingTypeIdsArray.length()>0){
				for( int i=0;i<partyMeetingTypeIdsArray.length();i++){
					partyMeetingTypeIds.add(Long.valueOf(partyMeetingTypeIdsArray.getString(i)));
				}
			}
			List<Long> partyMeetingIds = new ArrayList<Long>();
			JSONArray partyMeetingIdsArr=jobj.getJSONArray("partyMeetingIds");
			if(partyMeetingIdsArr!=null &&  partyMeetingIdsArr.length()>0){
				for( int i=0;i<partyMeetingIdsArr.length();i++){
					partyMeetingIds.add(Long.valueOf(partyMeetingIdsArr.getString(i)));
				}
			}
			
			List<Long> categoryIds = new ArrayList<Long>();
			JSONArray categoryIdArr=jobj.getJSONArray("categoryIds");
			if(categoryIdArr!=null &&  categoryIdArr.length()>0){
				for( int i=0;i<categoryIdArr.length();i++){
					categoryIds.add(Long.valueOf(categoryIdArr.getString(i)));
				}
			}
			String state = jobj.getString("state");
			String startDateString = jobj.getString("startDateString");
			String endDateString   = jobj.getString("endDateString");
			String category = jobj.getString("category");
			String location = jobj.getString("location");
			Long sessionId = jobj.getLong("sessionId");
			idNameVOs = coreDashboardPartyMeetingService.getPublicRepAndcommitteeInviteeDtls(partyMeetingMainTypeId,partyMeetingTypeIds,state,startDateString,endDateString,partyMeetingIds,category,categoryIds,location,sessionId);
			
	}catch(Exception e){
		LOG.error("Exception raised at getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToSeeionWiseMeetingDtls() method of CoreDashBoard", e);
	}
	return Action.SUCCESS;
	}
  	public String finalizedPartyMeetingConducted(){
  		RegistrationVO regVO = (RegistrationVO) request.getSession().getAttribute("USER");
  	    if(regVO==null){
  	      return "input";
  	    }
  	    boolean noaccess = false;
  	    List<String> entitlements = null;
  	    if(regVO.getEntitlements() != null && regVO.getEntitlements().size()>0){
  	      entitlements = regVO.getEntitlements();
  	      if(!(entitlements.contains("PARTY_MEETING_THIRD_PARTY_UPDATION_ENTITLEMENT") || entitlements.contains("PARTY_MEETING_THIRD_PARTY_UPDATION_ADMIN_ENTITLEMENT"))){
  	        noaccess = true ;
  	      }
  	        
  	    if(regVO.getIsAdmin() != null && regVO.getIsAdmin().equalsIgnoreCase("true")){
  	      noaccess = false;
  	    }
  	    if(noaccess){
  	      return "error";
  	    }
  	  }
		return Action.SUCCESS;
	}
  	public String locationWiseMeetingDetails(){
		try{
			jobj = new JSONObject(getTask());
			Long state = jobj.getLong("state");
			String startDateString = jobj.getString("startDateString");
			String endDateString   = jobj.getString("endDateString");  
			Long activityMemberId = jobj.getLong("activityMemberId");
			Long partyMeetingMainTypeId = jobj.getLong("partyMeetingMainTypeId");
			Long partyMeetingGrpId = jobj.getLong("meetingGrpId");
			
			List<Long> partyMeetingTypeIds = new ArrayList<Long>();
			JSONArray partyMeetingTypeIdsArray=jobj.getJSONArray("partyMeetingTypeIds");
			if(partyMeetingTypeIdsArray!=null &&  partyMeetingTypeIdsArray.length()>0){
				for( int i=0;i<partyMeetingTypeIdsArray.length();i++){
					partyMeetingTypeIds.add(Long.valueOf(partyMeetingTypeIdsArray.getString(i)));
				}
			}
			
			List<Long> locLevelIdList = new ArrayList<Long>();
			JSONArray locLevelIdsArray=jobj.getJSONArray("locLevelIdList");
			if(locLevelIdsArray!=null &&  locLevelIdsArray.length()>0){
				for( int i=0;i<locLevelIdsArray.length();i++){
					locLevelIdList.add(Long.valueOf(locLevelIdsArray.getString(i)));
				}
			}  
			
			
			basicDetailsVOs = coreDashboardPartyMeetingService.locationWiseMeetingDetails(activityMemberId, partyMeetingMainTypeId, partyMeetingTypeIds, locLevelIdList, 
					state, startDateString,  endDateString,partyMeetingGrpId);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToSeeionWiseMeetingDtls() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
  	public String getDistWiseMeetingDtlsForDiffLevelOfMeetings(){
		try{
			jobj = new JSONObject(getTask());
			Long stateId = jobj.getLong("state");
			String startDateString = jobj.getString("startDateString");
			String endDateString   = jobj.getString("endDateString");  
			Long activityMemberId = jobj.getLong("activityMemberId");
			Long partyMeetingMainTypeId = jobj.getLong("partyMeetingMainTypeId");
			Long locLevelId = jobj.getLong("locLevelId");
			Long partyMeetingGroupId = jobj.getLong("partyMeetingGroupId");
			Long sessionId = jobj.getLong("sessionId");
			
			listOfMeetingDtlsVOs = coreDashboardPartyMeetingService.getDistWiseMeetingDtlsForDiffLevelOfMeetings(activityMemberId, partyMeetingMainTypeId, locLevelId, stateId, startDateString, endDateString, partyMeetingGroupId, sessionId);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToSeeionWiseMeetingDtls() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
  	public String getCmmttsAndPblcRprsnttvMembersInvitedAndAttendedToSeeionWiseMultiLocationMeetingDtls(){
		try{
			jobj = new JSONObject(getTask());
			Long stateId = jobj.getLong("state");
			String startDateString = jobj.getString("startDateString");
			String endDateString   = jobj.getString("endDateString"); 
			Long activityMemberId = jobj.getLong("activityMemberId");
			Long partyMeetingMainTypeId = jobj.getLong("partyMeetingMainTypeId");
			Long locLevelId = jobj.getLong("locLevelId");
			Long partyMeetingGroupId = jobj.getLong("partyMeetingGroupId");
			
			partyMeetingDataVOList = coreDashboardPartyMeetingService.getCmmttsAndPblcRprsnttvMembersInvitedAndAttendedToSeeionWiseMultiLocationMeetingDtls(activityMemberId, partyMeetingMainTypeId, locLevelId, stateId, startDateString, endDateString, partyMeetingGroupId);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToSeeionWiseMeetingDtls() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
  	
  	public String getDistWiseMeetingsBasedDtlsForDiffLevelOfMeetings(){
		try{
			jobj = new JSONObject(getTask());
			Long stateId = jobj.getLong("state");
			String startDateString = jobj.getString("startDateString");
			String endDateString   = jobj.getString("endDateString");  
			Long activityMemberId = jobj.getLong("activityMemberId");
			Long partyMeetingMainTypeId = jobj.getLong("partyMeetingMainTypeId");
			Long locLevelId = jobj.getLong("locLevelId");
			Long partyMeetingGroupId = jobj.getLong("partyMeetingGroupId");
			Long sessionId = jobj.getLong("sessionId");
			String type= jobj.getString("type");
			listOfMeetingDtlsVOs = coreDashboardPartyMeetingService.getDistWiseMeetingsBaseDtlsForDiffLevelOfMeetings(activityMemberId, partyMeetingMainTypeId, locLevelId, stateId, startDateString, endDateString, partyMeetingGroupId, sessionId,type);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteesAndPublicRepresentativeMembersInvitedAndAttendedToSeeionWiseMeetingDtls() method of CoreDashBoard", e);
		}
		return Action.SUCCESS;
	}
  	public String getDistrictWiseThenCategoryWiseInsuranceMemberCount(){
  		try{
  			jobj = new JSONObject(getTask());
  			Long activityMemberId = jobj.getLong("activityMemberId");
  			Long userTypeId = jobj.getLong("userTypeId");
  			Long stateId = jobj.getLong("stateId");
  			Long cadreEnrollmentYearId = jobj.getLong("cadreEnrollmentYearId");
  			Long locationId = jobj.getLong("locationId");
  			String status = jobj.getString("status");
  			String category = jobj.getString("category");
  			String fromDateStr = jobj.getString("fromDateStr");
  			String toDateStr = jobj.getString("toDateStr");
  			String sortingCondition = jobj.getString("sortingCondition");
  			String order = jobj.getString("order");
  			coreDashboardInsuranceVOs = coreDashboardInsuranceService.getDistrictWiseThenCategoryWiseInsuranceMemberCount(activityMemberId,userTypeId,stateId,cadreEnrollmentYearId,locationId,status,category,fromDateStr,toDateStr,sortingCondition,order);
  			
  		}catch(Exception e){
  			LOG.error("Exception raised at getDistrictWiseThenCategoryWiseInsuranceMemberCount() method of CadreRegistrationAction", e);
  		}
  		return Action.SUCCESS;
  	}
  	public String getConstituencyWiseThenCategoryWiseInsuranceMemberCount(){
  		try{
  			jobj = new JSONObject(getTask());
  			Long activityMemberId = jobj.getLong("activityMemberId");
  			Long userTypeId = jobj.getLong("userTypeId");
  			Long stateId = jobj.getLong("stateId");
  			Long cadreEnrollmentYearId = jobj.getLong("cadreEnrollmentYearId");
  			Long locationId = jobj.getLong("locationId");
  			String status = jobj.getString("status");
  			String category = jobj.getString("category");
  			String fromDateStr = jobj.getString("fromDateStr");
  			String toDateStr = jobj.getString("toDateStr");
  			String sortingCondition = jobj.getString("sortingCondition");
  			String order = jobj.getString("order");
  			coreDashboardInsuranceVOs = coreDashboardInsuranceService.getConstituencyWiseThenCategoryWiseInsuranceMemberCount(activityMemberId,userTypeId,stateId,cadreEnrollmentYearId,locationId,status,category,fromDateStr,toDateStr,sortingCondition,order);
  			
  		}catch(Exception e){
  			LOG.error("Exception raised at getConstituencyWiseThenCategoryWiseInsuranceMemberCount() method of CadreRegistrationAction", e);
  		}
  		return Action.SUCCESS;
  	}
  	public String getDistrictWiseThenStatusWiseInsuranceMemberCount(){
  		try{
  			jobj = new JSONObject(getTask());
  			Long activityMemberId = jobj.getLong("activityMemberId");
  			Long userTypeId = jobj.getLong("userTypeId");
  			Long stateId = jobj.getLong("stateId");
  			Long cadreEnrollmentYearId = jobj.getLong("cadreEnrollmentYearId");
  			Long locationId = jobj.getLong("locationId");
  			String status = jobj.getString("status");
  			String category = jobj.getString("category");
  			String fromDateStr = jobj.getString("fromDateStr");
  			String toDateStr = jobj.getString("toDateStr");
  			String sortingCondition = jobj.getString("sortingCondition");
  			String order = jobj.getString("order");
  			coreDashboardInsuranceVOs = coreDashboardInsuranceService.getDistrictWiseThenStatusWiseInsuranceMemberCount(activityMemberId,userTypeId,stateId,cadreEnrollmentYearId,locationId,status,category,fromDateStr,toDateStr,sortingCondition,order);
  			
  		}catch(Exception e){
  			LOG.error("Exception raised at getDistrictWiseThenStatusWiseInsuranceMemberCount() method of CadreRegistrationAction", e);
  		}
  		return Action.SUCCESS;
  	}
  	public String getConstituencyWiseThenStatusWiseInsuranceMemberCount(){
  		try{
  			jobj = new JSONObject(getTask());
  			Long activityMemberId = jobj.getLong("activityMemberId");
  			Long userTypeId = jobj.getLong("userTypeId");
  			Long stateId = jobj.getLong("stateId");
  			Long cadreEnrollmentYearId = jobj.getLong("cadreEnrollmentYearId");
  			Long locationId = jobj.getLong("locationId");
  			String status = jobj.getString("status");
  			String category = jobj.getString("category");
  			String fromDateStr = jobj.getString("fromDateStr");
  			String toDateStr = jobj.getString("toDateStr");
  			String sortingCondition = jobj.getString("sortingCondition");
  			String order = jobj.getString("order");
  			coreDashboardInsuranceVOs = coreDashboardInsuranceService.getConstituencyWiseThenStatusWiseInsuranceMemberCount(activityMemberId,userTypeId,stateId,cadreEnrollmentYearId,locationId,status,category,fromDateStr,toDateStr,sortingCondition,order);
  			
  		}catch(Exception e){ 
  			LOG.error("Exception raised at getConstituencyWiseThenStatusWiseInsuranceMemberCount() method of CadreRegistrationAction", e);
  		}
  		return Action.SUCCESS;
  	}
  	public String getLocationWiseThenCategoryWiseInsuranceMemberCountForTS(){
  		try{
  			jobj = new JSONObject(getTask());
  			Long stateId = jobj.getLong("stateId");
  			Long cadreEnrollmentYearId = jobj.getLong("cadreEnrollmentYearId");
  			Long locationId = jobj.getLong("locationId");
  			String status = jobj.getString("status");
  			String category = jobj.getString("category");
  			String fromDateStr = jobj.getString("fromDateStr");
  			String toDateStr = jobj.getString("toDateStr");
  			String type = jobj.getString("type");
  			String locationType = jobj.getString("locationType");
  			String sortingCondition = jobj.getString("sortingCondition");
  			String order = jobj.getString("order");
  			coreDashboardInsuranceVOs = coreDashboardInsuranceService.getLocationWiseThenCategoryWiseInsuranceMemberCountForTS(stateId,cadreEnrollmentYearId,locationId,status,category,fromDateStr,toDateStr,type,locationType,sortingCondition,order);
  			
  		}catch(Exception e){ 
  			LOG.error("Exception raised at getLocationWiseThenCategoryWiseInsuranceMemberCountForTS() method of CadreRegistrationAction", e);
  		}
  		return Action.SUCCESS;
  	}
  	public String getLocationWiseThenStatusWiseInsuranceMemberCountForTS(){
  		try{
  			jobj = new JSONObject(getTask());
  			Long stateId = jobj.getLong("stateId");
  			Long cadreEnrollmentYearId = jobj.getLong("cadreEnrollmentYearId");
  			Long locationId = jobj.getLong("locationId");
  			String status = jobj.getString("status");
  			String category = jobj.getString("category");
  			String fromDateStr = jobj.getString("fromDateStr");
  			String toDateStr = jobj.getString("toDateStr");
  			String type = jobj.getString("type");
  			String locationType = jobj.getString("locationType"); 
  			String sortingCondition = jobj.getString("sortingCondition");
  			String order = jobj.getString("order");
  			coreDashboardInsuranceVOs = coreDashboardInsuranceService.getLocationWiseThenStatusWiseInsuranceMemberCountForTS(stateId,cadreEnrollmentYearId,locationId,status,category,fromDateStr,toDateStr,type,locationType,sortingCondition,order);
  			
  		}catch(Exception e){ 
  			LOG.error("Exception raised at getLocationWiseThenStatusWiseInsuranceMemberCountForTS() method of CadreRegistrationAction", e);
  		}
  		return Action.SUCCESS;
  	}
  	
  	public String getStateWiseDistrictsForUsers() {

		try {
			jobj = new JSONObject(getTask());
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			Long stateId = jobj.getLong("stateId");

			keyValuePairVOList = cadreRegistrationService.getStateWiseDistrictsForUsers(stateId,user.getRegistrationID());

		} catch (Exception e) {
			LOG.error(
					"Exception raised in getStateWiseDistrict method in CadreRegistrationAction Action",
					e);
		}
		return Action.SUCCESS;
	}
  	
  	public String getConstituenciesByDistrictForUser() {

		try {
			jobj = new JSONObject(getTask());

			Long districtId = jobj.getLong("districtId");
			session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			keyValuePairVOList = cadreRegistrationService.getConstituenciesByDistrictForUser(districtId,user.getRegistrationID());

		} catch (Exception e) {
			LOG.error(
					"Exception raised in getStateWiseDistrict method in CadreRegistrationAction Action",
					e);
		}
		return Action.SUCCESS;
	}
}
