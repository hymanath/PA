package com.itgrids.partyanalyst.service.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBloodGroupDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreCardNumberUpdationDAO;
import com.itgrids.partyanalyst.dao.ICadreCommitteeDAO;
import com.itgrids.partyanalyst.dao.ICadreCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.ICadreCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICadreLevelDAO;
import com.itgrids.partyanalyst.dao.ICadreMissedCallCampaignDAO;
import com.itgrids.partyanalyst.dao.ICadreParticipatedElectionDAO;
import com.itgrids.partyanalyst.dao.ICadrePreviousRolesDAO;
import com.itgrids.partyanalyst.dao.ICadreRegSyncAccessUsersDAO;
import com.itgrids.partyanalyst.dao.ICadreRegistrationAllowAreasDAO;
import com.itgrids.partyanalyst.dao.ICadreRolesDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssignDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.ICadreTabRecordsStatusDAO;
import com.itgrids.partyanalyst.dao.ICardPrintUserDAO;
import com.itgrids.partyanalyst.dao.ICardReceiverDAO;
import com.itgrids.partyanalyst.dao.ICardSenderDAO;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IDynamicKeysDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.IErrorStatusSmsDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyDesignationDAO;
import com.itgrids.partyanalyst.dao.IRegistrationStatusDAO;
import com.itgrids.partyanalyst.dao.IRegistrationStatusTempDAO;
import com.itgrids.partyanalyst.dao.IRtcDepotDAO;
import com.itgrids.partyanalyst.dao.ISmsJobStatusDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITabRecordsStatusDAO;
import com.itgrids.partyanalyst.dao.ITabUserKeysDAO;
import com.itgrids.partyanalyst.dao.ITabUserLoginDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreBackupDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreFamilyDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreFamilyInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreHistoryDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreOnlineDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreTeluguNamesDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreTravelInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreVerfiedDataDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.ITdpMemberTypeDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITirupathiByeleMobileBoothDAO;
import com.itgrids.partyanalyst.dao.ITirupatiByelectionDAO;
import com.itgrids.partyanalyst.dao.ITwoWayMessageDAO;
import com.itgrids.partyanalyst.dao.ITwoWaySmsMobileDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.dao.IUserFeedbackDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVerifiedDataRequestDAO;
import com.itgrids.partyanalyst.dao.IVerifiedDataResponseDAO;
import com.itgrids.partyanalyst.dao.IVerifiedDataStatusDAO;
import com.itgrids.partyanalyst.dao.IVerifyAccessUsersDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterNamesDAO;
import com.itgrids.partyanalyst.dao.IVoterRelationDAO;
import com.itgrids.partyanalyst.dao.IZebraPrintDetailsDAO;
import com.itgrids.partyanalyst.dao.hibernate.TdpCadreLocationDAO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.AffiliatedCadreVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.ByeElectionVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreFamilyVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.dto.CadrePrintVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreTabRecordsStatusVO;
import com.itgrids.partyanalyst.dto.CadreTravelsVO;
import com.itgrids.partyanalyst.dto.CardNFCDetailsVO;
import com.itgrids.partyanalyst.dto.CardPrintUserVO;
import com.itgrids.partyanalyst.dto.CardSenderVO;
import com.itgrids.partyanalyst.dto.CasteDetailsVO;
import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.dto.GISUserTrackingVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.KeyValuePairVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.MissedCallCampaignVO;
import com.itgrids.partyanalyst.dto.MissedCallsDetailsVO;
import com.itgrids.partyanalyst.dto.PartyMeetingWSVO;
import com.itgrids.partyanalyst.dto.PaymentGatewayVO;
import com.itgrids.partyanalyst.dto.PaymentTransactionVO;
import com.itgrids.partyanalyst.dto.RegistrationQueriesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.RtcUnionInputVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SinkVO;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;
import com.itgrids.partyanalyst.dto.TabRecordsStatusVO;
import com.itgrids.partyanalyst.dto.TdpCadreFamilyDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.UserDetailsVO;
import com.itgrids.partyanalyst.dto.VoterInfoVO;
import com.itgrids.partyanalyst.dto.VoterSearchVO;
import com.itgrids.partyanalyst.model.BloodGroup;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.CadreCardNumberUpdation;
import com.itgrids.partyanalyst.model.CadreMissedCallCampaign;
import com.itgrids.partyanalyst.model.CadreParticipatedElection;
import com.itgrids.partyanalyst.model.CadrePreviousRoles;
import com.itgrids.partyanalyst.model.CadreSurveyUser;
import com.itgrids.partyanalyst.model.CadreSurveyUserAssignDetails;
import com.itgrids.partyanalyst.model.CadreTabRecordsStatus;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CardReceiver;
import com.itgrids.partyanalyst.model.CardSender;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.ErrorStatusSms;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Occupation;
import com.itgrids.partyanalyst.model.Panchayat;
import com.itgrids.partyanalyst.model.RegistrationStatus;
import com.itgrids.partyanalyst.model.RegistrationStatusTemp;
import com.itgrids.partyanalyst.model.SmsHistory;
import com.itgrids.partyanalyst.model.SmsJobStatus;
import com.itgrids.partyanalyst.model.TabRecordsStatus;
import com.itgrids.partyanalyst.model.TabUserKeys;
import com.itgrids.partyanalyst.model.TabUserLoginDetails;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.TdpCadreBackupDetails;
import com.itgrids.partyanalyst.model.TdpCadreEnrollmentYear;
import com.itgrids.partyanalyst.model.TdpCadreFamilyDetails;
import com.itgrids.partyanalyst.model.TdpCadreFamilyInfo;
import com.itgrids.partyanalyst.model.TdpCadreHistory;
import com.itgrids.partyanalyst.model.TdpCadreLocation;
import com.itgrids.partyanalyst.model.TdpCadreOnline;
import com.itgrids.partyanalyst.model.TdpCadreTeluguNames;
import com.itgrids.partyanalyst.model.TdpCadreTravelInfo;
import com.itgrids.partyanalyst.model.TdpCadreVerfiedData;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.TirupatiByelection;
import com.itgrids.partyanalyst.model.TwoWayMessage;
import com.itgrids.partyanalyst.model.TwoWaySmsMobile;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.UserFeedback;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.VerifiedDataRequest;
import com.itgrids.partyanalyst.model.VerifiedDataResponse;
import com.itgrids.partyanalyst.model.VerifiedDataStatus;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterNames;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IPaymentGatewayService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IRtcUnionService;
import com.itgrids.partyanalyst.service.ISmsSenderService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.CommonUtilsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;
import com.itgrids.partyanalyst.utils.MD5Algoritm;


public class CadreRegistrationService implements ICadreRegistrationService {
	
	private static final Logger LOG = Logger.getLogger(CadreRegistrationService.class);
	

	private ITdpCadreDAO                    tdpCadreDAO ;
	private TransactionTemplate             transactionTemplate;
	private ICadrePreviousRolesDAO		    cadrePreviousRolesDAO;
	private ICadreParticipatedElectionDAO	cadreParticipatedElectionDAO;
	private IBoothPublicationVoterDAO 		boothPublicationVoterDAO;
	private ICountryDAO						countryDAO;
	private IStateDAO						stateDAO;
	private IUserVoterDetailsDAO			userVoterDetailsDAO; 
	private IUserAddressDAO					userAddressDAO;
	private DateUtilService					dateUtilService;

	private ICadreDAO 						cadreDAO;
	private IVoterDAO						voterDAO;
	private IConstituencyDAO				constituencyDAO; 					
	private ITehsilDAO 						tehsilDAO;
	private IPanchayatDAO 					panchayatDAO;
	private ILocalElectionBodyDAO 			localElectionBodyDAO;
	private IBoothDAO						boothDAO;
	private ICasteStateDAO					casteStateDAO;
	private IBloodGroupDAO					bloodGroupDAO;
	
	private IElectionDAO 					electionDAO;
	private ICadreLevelDAO					cadreLevelDAO;
	private IPartyDesignationDAO 			partyDesignationDAO;
	private IElectionTypeDAO				electionTypeDAO;
	private ITdpCadreFamilyDetailsDAO		tdpCadreFamilyDetailsDAO;
	private ImageAndStringConverter 		imageAndStringConverter = new ImageAndStringConverter();
	
	private ICadreSurveyUserAssignDetailsDAO  	cadreSurveyUserAssignDetailsDAO;
	private ICadreSurveyUserDAO 				cadreSurveyUserDAO;
	private IRegionServiceData 					regionServiceDataImp;
	private IOccupationDAO 						occupationDAO;
	private IConstituencyElectionDAO 			constituencyElectionDAO;
	private INominationDAO 						nominationDAO;
	private IVoterRelationDAO voterRelationDAO;
	private ICadreCommitteeDAO					cadreCommitteeDAO;
	private ICadreCommitteeLevelDAO				cadreCommitteeLevelDAO;
	private ICadreRolesDAO						cadreRolesDAO;
	private ICadreCommitteeRoleDAO				cadreCommitteeRoleDAO;
	private ITdpCadreBackupDetailsDAO			tdpCadreBackupDetailsDAO;
	
	private ICardSenderDAO						cardSenderDAO;
	private ICardReceiverDAO					cardReceiverDAO;
	
	private ICasteDAO casteDAO;
	private IVoterNamesDAO 						voterNamesDAO;
	private ITdpCadreOnlineDAO                  tdpCadreOnlineDAO;
	private ISmsJobStatusDAO					smsJobStatusDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IDistrictDAO districtDAO;
	private ITabRecordsStatusDAO tabRecordsStatusDAO;
	private ITabUserLoginDetailsDAO tabUserLoginDetailsDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private ITdpCadreTeluguNamesDAO				tdpCadreTeluguNamesDAO;
	private ITdpCadreVerfiedDataDAO             tdpCadreVerfiedDataDAO;
	private ITabUserKeysDAO tabUserKeysDAO;
	private IDynamicKeysDAO 				dynamicKeysDAO;
	private IVerifyAccessUsersDAO 			verifyAccessUsersDAO;
	private IUserDAO userDAO ;
	private IVerifiedDataStatusDAO  verifiedDataStatusDAO;
	private IVerifiedDataRequestDAO verifiedDataRequestDAO;
	private IVerifiedDataResponseDAO verifiedDataResponseDAO;
	private CommonUtilsService commonUtilsService;
	private ICadreRegSyncAccessUsersDAO cadreRegSyncAccessUsersDAO;
	private ITdpCadreTravelInfoDAO tdpCadreTravelInfoDAO;
	private ITdpCadreHistoryDAO tdpCadreHistoryDAO;
	private CadreCommitteeService cadreCommitteeService;
	private ITdpCommitteeRoleDAO tdpCommitteeRoleDAO;
	private IRegistrationStatusDAO registrationStatusDAO;
	private ITwoWaySmsMobileDAO twoWaySmsMobileDAO;
	
	private ITirupatiByelectionDAO tirupatiByelectionDAO;

	private IErrorStatusSmsDAO errorStatusSmsDAO;
	private IRegistrationStatusTempDAO registrationStatusTempDAO;
	private ITirupathiByeleMobileBoothDAO tirupathiByeleMobileBoothDAO;
	private ITwoWayMessageDAO twoWayMessageDAO;
	private ICadreMissedCallCampaignDAO cadreMissedCallCampaignDAO;
	private IZebraPrintDetailsDAO zebraPrintDetailsDAO;
	private ICadreCardNumberUpdationDAO cadreCardNumberUpdationDAO;
	private ICardPrintUserDAO cardPrintUserDAO ;
	private ITdpCadreFamilyInfoDAO tdpCadreFamilyInfoDAO;
	private List<SelectOptionVO> regionsList;
	private TdpCadreLocationDAO tdpCadreLocationDAO;
	private IRtcUnionService rtcUnionService;
	private IRtcDepotDAO rtcDepotDAO;
	private ITdpMemberTypeDAO tdpMemberTypeDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private IUserFeedbackDAO userFeedbackDAO;
	private IMailService mailService;
	private ISmsSenderService smsSenderService;
	private MD5Algoritm md5Algoritm = new MD5Algoritm();
	private IPaymentGatewayService paymentGatewayService;
	private ICadreDetailsService cadreDetailsService;
	private IDelimitationConstituencyMandalDetailsDAO delimitationConstituencyMandalDetailsDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private ICadreTabRecordsStatusDAO cadreTabRecordsStatusDAO;
	private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;
	private ITdpCadreEnrollmentInfoDAO tdpCadreEnrollmentInfoDAO;
	private ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO;
	private ICadreRegistrationAllowAreasDAO cadreRegistrationAllowAreasDAO;
	private IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO;
	private IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO;
	
	
	/*private IPrintedCardDetailsDAO printedCardDetailsDAO;   
	
	public IPrintedCardDetailsDAO getPrintedCardDetailsDAO() {
		return printedCardDetailsDAO;
	}

	public void setPrintedCardDetailsDAO(
			IPrintedCardDetailsDAO printedCardDetailsDAO) {
		this.printedCardDetailsDAO = printedCardDetailsDAO;
	}*/
	
	
	public void setUserConstituencyAccessInfoDAO(
			IUserConstituencyAccessInfoDAO userConstituencyAccessInfoDAO) {
		this.userConstituencyAccessInfoDAO = userConstituencyAccessInfoDAO;
	}

	public IUserDistrictAccessInfoDAO getUserDistrictAccessInfoDAO() {
		return userDistrictAccessInfoDAO;
	}

	public void setUserDistrictAccessInfoDAO(
			IUserDistrictAccessInfoDAO userDistrictAccessInfoDAO) {
		this.userDistrictAccessInfoDAO = userDistrictAccessInfoDAO;
	}

	public void setCadreTabRecordsStatusDAO(
			ICadreTabRecordsStatusDAO cadreTabRecordsStatusDAO) {
		this.cadreTabRecordsStatusDAO = cadreTabRecordsStatusDAO;
	}
	
	public ICadreRegistrationAllowAreasDAO getCadreRegistrationAllowAreasDAO() {
		return cadreRegistrationAllowAreasDAO;
	}

	public void setCadreRegistrationAllowAreasDAO(
			ICadreRegistrationAllowAreasDAO cadreRegistrationAllowAreasDAO) {
		this.cadreRegistrationAllowAreasDAO = cadreRegistrationAllowAreasDAO;
	}

	public ITdpCadreEnrollmentYearDAO getTdpCadreEnrollmentYearDAO() {
		return tdpCadreEnrollmentYearDAO;
	}

	public void setTdpCadreEnrollmentYearDAO(
			ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO) {
		this.tdpCadreEnrollmentYearDAO = tdpCadreEnrollmentYearDAO;
	}

	public ITdpCadreEnrollmentInfoDAO getTdpCadreEnrollmentInfoDAO() {
		return tdpCadreEnrollmentInfoDAO;
	}

	public void setTdpCadreEnrollmentInfoDAO(
			ITdpCadreEnrollmentInfoDAO tdpCadreEnrollmentInfoDAO) {
		this.tdpCadreEnrollmentInfoDAO = tdpCadreEnrollmentInfoDAO;
	}

	public IAssemblyLocalElectionBodyWardDAO getAssemblyLocalElectionBodyWardDAO() {
		return assemblyLocalElectionBodyWardDAO;
	}

	public void setAssemblyLocalElectionBodyWardDAO(
			IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
		this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
	}

	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}	

	public ISmsSenderService getSmsSenderService() {
		return smsSenderService;
	}

	public IPaymentGatewayService getPaymentGatewayService() {
		return paymentGatewayService;
	}

	public void setPaymentGatewayService(
			IPaymentGatewayService paymentGatewayService) {
		this.paymentGatewayService = paymentGatewayService;
	}

	public void setSmsSenderService(ISmsSenderService smsSenderService) {
		this.smsSenderService = smsSenderService;
	}

	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

	public ICadreMissedCallCampaignDAO getCadreMissedCallCampaignDAO() {
		return cadreMissedCallCampaignDAO;
	}

	public IUserFeedbackDAO getUserFeedbackDAO() {
		return userFeedbackDAO;
	}

	public void setUserFeedbackDAO(IUserFeedbackDAO userFeedbackDAO) {
		this.userFeedbackDAO = userFeedbackDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public ITdpMemberTypeDAO getTdpMemberTypeDAO() {
		return tdpMemberTypeDAO;
	}

	public void setTdpMemberTypeDAO(ITdpMemberTypeDAO tdpMemberTypeDAO) {
		this.tdpMemberTypeDAO = tdpMemberTypeDAO;
	}

	public ITdpCadreFamilyInfoDAO getTdpCadreFamilyInfoDAO() {
		return tdpCadreFamilyInfoDAO;
	}

	public void setTdpCadreFamilyInfoDAO(
			ITdpCadreFamilyInfoDAO tdpCadreFamilyInfoDAO) {
		this.tdpCadreFamilyInfoDAO = tdpCadreFamilyInfoDAO;
	}

	public ICardPrintUserDAO getCardPrintUserDAO() {
		return cardPrintUserDAO;
	}

	public void setCardPrintUserDAO(ICardPrintUserDAO cardPrintUserDAO) {
		this.cardPrintUserDAO = cardPrintUserDAO;
	}

	public ICadreCardNumberUpdationDAO getCadreCardNumberUpdationDAO() {
		return cadreCardNumberUpdationDAO;
	}

	public void setCadreCardNumberUpdationDAO(
			ICadreCardNumberUpdationDAO cadreCardNumberUpdationDAO) {
		this.cadreCardNumberUpdationDAO = cadreCardNumberUpdationDAO;
	}

	public void setCadreMissedCallCampaignDAO(
			ICadreMissedCallCampaignDAO cadreMissedCallCampaignDAO) {
		this.cadreMissedCallCampaignDAO = cadreMissedCallCampaignDAO;
	}

	public ITirupathiByeleMobileBoothDAO getTirupathiByeleMobileBoothDAO() {
		return tirupathiByeleMobileBoothDAO;
	}

	public void setTirupathiByeleMobileBoothDAO(
			ITirupathiByeleMobileBoothDAO tirupathiByeleMobileBoothDAO) {
		this.tirupathiByeleMobileBoothDAO = tirupathiByeleMobileBoothDAO;
	}
	
	public IErrorStatusSmsDAO getErrorStatusSmsDAO() {
		return errorStatusSmsDAO;
	}

	public IRegistrationStatusTempDAO getRegistrationStatusTempDAO() {
		return registrationStatusTempDAO;
	}

	public void setRegistrationStatusTempDAO(
			IRegistrationStatusTempDAO registrationStatusTempDAO) {
		this.registrationStatusTempDAO = registrationStatusTempDAO;
	}

	public void setErrorStatusSmsDAO(IErrorStatusSmsDAO errorStatusSmsDAO) {
		this.errorStatusSmsDAO = errorStatusSmsDAO;
	}

	public ITdpCadreTravelInfoDAO getTdpCadreTravelInfoDAO() {
		return tdpCadreTravelInfoDAO;
	}

	public ITirupatiByelectionDAO getTirupatiByelectionDAO() {
		return tirupatiByelectionDAO;
	}

	public void setTirupatiByelectionDAO(
			ITirupatiByelectionDAO tirupatiByelectionDAO) {
		this.tirupatiByelectionDAO = tirupatiByelectionDAO;
	}

	public void setTwoWaySmsMobileDAO(ITwoWaySmsMobileDAO twoWaySmsMobileDAO) {
		this.twoWaySmsMobileDAO = twoWaySmsMobileDAO;
	}

	public void setRegistrationStatusDAO(
			IRegistrationStatusDAO registrationStatusDAO) {
		this.registrationStatusDAO = registrationStatusDAO;
	}

	public void setCadreCommitteeService(CadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}

	public void setTdpCadreTravelInfoDAO(
			ITdpCadreTravelInfoDAO tdpCadreTravelInfoDAO) {
		this.tdpCadreTravelInfoDAO = tdpCadreTravelInfoDAO;
	}
	
	public void setCommonUtilsService(CommonUtilsService commonUtilsService) {
		this.commonUtilsService = commonUtilsService;
	}

	public void setCadreRegSyncAccessUsersDAO(
			ICadreRegSyncAccessUsersDAO cadreRegSyncAccessUsersDAO) {
		this.cadreRegSyncAccessUsersDAO = cadreRegSyncAccessUsersDAO;
	}

	public void setVerifiedDataRequestDAO(
			IVerifiedDataRequestDAO verifiedDataRequestDAO) {
		this.verifiedDataRequestDAO = verifiedDataRequestDAO;
	}

	public void setVerifiedDataResponseDAO(
			IVerifiedDataResponseDAO verifiedDataResponseDAO) {
		this.verifiedDataResponseDAO = verifiedDataResponseDAO;
	}

	public ITdpCadreTeluguNamesDAO getTdpCadreTeluguNamesDAO() {
		return tdpCadreTeluguNamesDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setDynamicKeysDAO(IDynamicKeysDAO dynamicKeysDAO) {
		this.dynamicKeysDAO = dynamicKeysDAO;
	}

	public void setVerifyAccessUsersDAO(IVerifyAccessUsersDAO verifyAccessUsersDAO) {
		this.verifyAccessUsersDAO = verifyAccessUsersDAO;
	}

	public void setTdpCadreVerfiedDataDAO(
			ITdpCadreVerfiedDataDAO tdpCadreVerfiedDataDAO) {
		this.tdpCadreVerfiedDataDAO = tdpCadreVerfiedDataDAO;
	}

	public void setTdpCadreTeluguNamesDAO(
			ITdpCadreTeluguNamesDAO tdpCadreTeluguNamesDAO) {
		this.tdpCadreTeluguNamesDAO = tdpCadreTeluguNamesDAO;
	}

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	public void setTabUserLoginDetailsDAO(
			ITabUserLoginDetailsDAO tabUserLoginDetailsDAO) {
		this.tabUserLoginDetailsDAO = tabUserLoginDetailsDAO;
	}

	public void setTabRecordsStatusDAO(ITabRecordsStatusDAO tabRecordsStatusDAO) {
		this.tabRecordsStatusDAO = tabRecordsStatusDAO;
	}

	public void setBoothsSort(Comparator<Object[]> boothsSort) {
		this.boothsSort = boothsSort;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public ISmsJobStatusDAO getSmsJobStatusDAO() {
		return smsJobStatusDAO;
	}

	public void setSmsJobStatusDAO(ISmsJobStatusDAO smsJobStatusDAO) {
		this.smsJobStatusDAO = smsJobStatusDAO;
	}

	public void setVoterNamesDAO(IVoterNamesDAO voterNamesDAO) {
		this.voterNamesDAO = voterNamesDAO;
	}

	public ICasteDAO getCasteDAO() {
		return casteDAO;
	}

	public void setCasteDAO(ICasteDAO casteDAO) {
		this.casteDAO = casteDAO;
	}

	public void setCardSenderDAO(ICardSenderDAO cardSenderDAO) {
		this.cardSenderDAO = cardSenderDAO;
	}

	public void setCardReceiverDAO(ICardReceiverDAO cardReceiverDAO) {
		this.cardReceiverDAO = cardReceiverDAO;
	}

	public void setCadreCommitteeRoleDAO(
			ICadreCommitteeRoleDAO cadreCommitteeRoleDAO) {
		this.cadreCommitteeRoleDAO = cadreCommitteeRoleDAO;
	}

	public void setTdpCadreBackupDetailsDAO(
			ITdpCadreBackupDetailsDAO tdpCadreBackupDetailsDAO) {
		this.tdpCadreBackupDetailsDAO = tdpCadreBackupDetailsDAO;
	}
	
	public ICadreCommitteeDAO getCadreCommitteeDAO() {
		return cadreCommitteeDAO;
	}

	public void setCadreCommitteeDAO(ICadreCommitteeDAO cadreCommitteeDAO) {
		this.cadreCommitteeDAO = cadreCommitteeDAO;
	}

	public ICadreCommitteeLevelDAO getCadreCommitteeLevelDAO() {
		return cadreCommitteeLevelDAO;
	}

	public void setCadreCommitteeLevelDAO(
			ICadreCommitteeLevelDAO cadreCommitteeLevelDAO) {
		this.cadreCommitteeLevelDAO = cadreCommitteeLevelDAO;
	}

	public ICadreRolesDAO getCadreRolesDAO() {
		return cadreRolesDAO;
	}

	public void setCadreRolesDAO(ICadreRolesDAO cadreRolesDAO) {
		this.cadreRolesDAO = cadreRolesDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public void setOccupationDAO(IOccupationDAO occupationDAO) {
		this.occupationDAO = occupationDAO;
	}

	public void setImageAndStringConverter(
			ImageAndStringConverter imageAndStringConverter) {
		this.imageAndStringConverter = imageAndStringConverter;
	}

	
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}


	public void setCadreSurveyUserAssignDetailsDAO(
			ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO) {
		this.cadreSurveyUserAssignDetailsDAO = cadreSurveyUserAssignDetailsDAO;
	}

	public void setCadreSurveyUserDAO(ICadreSurveyUserDAO cadreSurveyUserDAO) {
		this.cadreSurveyUserDAO = cadreSurveyUserDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setCadrePreviousRolesDAO(
			ICadrePreviousRolesDAO cadrePreviousRolesDAO) {
		this.cadrePreviousRolesDAO = cadrePreviousRolesDAO;
	}

	public void setCadreParticipatedElectionDAO(
			ICadreParticipatedElectionDAO cadreParticipatedElectionDAO) {
		this.cadreParticipatedElectionDAO = cadreParticipatedElectionDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public void setCountryDAO(ICountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}

	public void setBloodGroupDAO(IBloodGroupDAO bloodGroupDAO) {
		this.bloodGroupDAO = bloodGroupDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public void setCadreLevelDAO(ICadreLevelDAO cadreLevelDAO) {
		this.cadreLevelDAO = cadreLevelDAO;
	}

	public void setPartyDesignationDAO(IPartyDesignationDAO partyDesignationDAO) {
		this.partyDesignationDAO = partyDesignationDAO;
	}

	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}

	public void setTdpCadreFamilyDetailsDAO(
			ITdpCadreFamilyDetailsDAO tdpCadreFamilyDetailsDAO) {
		this.tdpCadreFamilyDetailsDAO = tdpCadreFamilyDetailsDAO;
	}

	public IVoterRelationDAO getVoterRelationDAO() {
		return voterRelationDAO;
	}

	public void setVoterRelationDAO(IVoterRelationDAO voterRelationDAO) {
		this.voterRelationDAO = voterRelationDAO;
	}
	
	
	public ITdpCadreOnlineDAO getTdpCadreOnlineDAO() {
		return tdpCadreOnlineDAO;
	}

	public void setTdpCadreOnlineDAO(ITdpCadreOnlineDAO tdpCadreOnlineDAO) {
		this.tdpCadreOnlineDAO = tdpCadreOnlineDAO;
	}

	public ITabUserKeysDAO getTabUserKeysDAO() {
		return tabUserKeysDAO;
	}

	public void setTabUserKeysDAO(ITabUserKeysDAO tabUserKeysDAO) {
		this.tabUserKeysDAO = tabUserKeysDAO;
	}

	

	public void setTwoWayMessageDAO(ITwoWayMessageDAO twoWayMessageDAO) {
		this.twoWayMessageDAO = twoWayMessageDAO;
	}

	public void setVerifiedDataStatusDAO(
			IVerifiedDataStatusDAO verifiedDataStatusDAO) {
		this.verifiedDataStatusDAO = verifiedDataStatusDAO;
	}

	public void setTdpCadreHistoryDAO(ITdpCadreHistoryDAO tdpCadreHistoryDAO) {
		this.tdpCadreHistoryDAO = tdpCadreHistoryDAO;
	}

	public void setTdpCommitteeRoleDAO(ITdpCommitteeRoleDAO tdpCommitteeRoleDAO) {
		this.tdpCommitteeRoleDAO = tdpCommitteeRoleDAO;
	}
    
	public void setZebraPrintDetailsDAO(IZebraPrintDetailsDAO zebraPrintDetailsDAO) {
		this.zebraPrintDetailsDAO = zebraPrintDetailsDAO;
	}

	public static void setSortData(Comparator<ByeElectionVO> sortData) {
		CadreRegistrationService.sortData = sortData;
	}
	
	

	public List<SelectOptionVO> getRegionsList() {
		return regionsList;
	}

	public void setRegionsList(List<SelectOptionVO> regionsList) {
		this.regionsList = regionsList;
	}
	
	public TdpCadreLocationDAO getTdpCadreLocationDAO() {
		return tdpCadreLocationDAO;
	}

	public void setTdpCadreLocationDAO(TdpCadreLocationDAO tdpCadreLocationDAO) {
		this.tdpCadreLocationDAO = tdpCadreLocationDAO;
	}
    
	public void setRtcUnionService(IRtcUnionService rtcUnionService) {
		this.rtcUnionService = rtcUnionService;
	}
    
	public void setRtcDepotDAO(IRtcDepotDAO rtcDepotDAO) {
		this.rtcDepotDAO = rtcDepotDAO;
	}
	

	public IDelimitationConstituencyMandalDetailsDAO getDelimitationConstituencyMandalDetailsDAO() {
		return delimitationConstituencyMandalDetailsDAO;
	}

	public void setDelimitationConstituencyMandalDetailsDAO(
			IDelimitationConstituencyMandalDetailsDAO delimitationConstituencyMandalDetailsDAO) {
		this.delimitationConstituencyMandalDetailsDAO = delimitationConstituencyMandalDetailsDAO;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
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

	public TdpCadreBackupDetails updateRequestDetailsForBackup(final List<CadreRegistrationVO> inputResponseList,final String registrationType)
	{
		LOG.info("Entered into updateRequestDetailsForBackup in CadreRegistrationService service");
		TdpCadreBackupDetails returnData = null;
		if(inputResponseList != null && inputResponseList.size() > 0)
		{
		try {
			returnData = (TdpCadreBackupDetails)transactionTemplate.execute(new TransactionCallback() {
			 public Object doInTransaction(TransactionStatus status) {
				 TdpCadreBackupDetails tdpCadreBackupDetails = new TdpCadreBackupDetails();
				for (CadreRegistrationVO inputResponse : inputResponseList)
				{
					/*String cadreBasicInfo = "Voter Name " + ":" + inputResponse.getVoterName() + "-" +"Date Of Birth "+ ":" + inputResponse.getDob() +"-"+ "Gender "+ ":" +inputResponse.getGender()+ "-" +"Relative Name"+ ":" + inputResponse.getRelativeName() +"-" +"VoterCardNumber"+ ":" + inputResponse.getVoterCardNo() + "-" + "H.NO" + ":" + inputResponse.getHouseNo() + "-" +"Party Member Since" + ":" +inputResponse.getPartyMemberSince() + "-" + "Blood Group " + ":" + inputResponse.getBloodGroupId() + "-" + "Street/hamle" + ":" +inputResponse.getStreet() +"-" +"Caste" + ":" + inputResponse.getCasteId() + "-" + "Mobile No" + ":" + inputResponse.getMobileNumber() + "-" + "Education" + ":" +inputResponse.getEducationId() + "-" + "Occupation " + ":" +inputResponse.getOccupationId() + "-" + "Previous Enroll Ment No " + ":" + inputResponse.getPreviousEnrollmentNumber();
					String previousRoles = "";
					String previousElections = "";
					String familyDetails = "";
				
					if(inputResponse.getPreviousRollesList() != null && inputResponse.getPreviousRollesList().size() > 0)
					{
						for (CadrePreviousRollesVO electionVO : inputResponse.getPreviousRollesList())
						{
							previousElections = previousElections + "Designation Level"+ ":" +electionVO.getDesignationLevelId() + "-" + "Designation Level Value" + ":" + electionVO.getDesignationLevelValue() + "-" + "From Date" + ":" + electionVO.getFromDate() + "-" + "To Date" + ":" + electionVO.getToDate()+" :  " + "committe level ID" + ":" + electionVO.getCadreCommitteeLevelId()+" : " + "committe Role ID" + ":" + electionVO.getCadreRoleId()+" : " + "committe Id" + ":" + electionVO.getCadreCommitteeId()+" ::";
						}
					}
					if(inputResponse.getPreviousParicaptedElectionsList() != null && inputResponse.getPreviousParicaptedElectionsList().size() > 0)
					{
						for (CadrePreviousRollesVO electionVO : inputResponse.getPreviousParicaptedElectionsList())
						{
							previousRoles = previousRoles + "Election Id" + ":" +electionVO.getElectionTypeId() + "-" + "Constituency Id" + ":" + electionVO.getConstituencyId()+" : " +"Candidate Id" + ":" + electionVO.getCandidateId() + ":: ";
						}
					}
					if(inputResponse.getCadreFamilyDetails() != null && inputResponse.getCadreFamilyDetails() .size() > 0)
					{
						for (CadreFamilyVO familyVO : inputResponse.getCadreFamilyDetails())
						{
							familyDetails = familyDetails + "Voter Id" + ":" +familyVO.getVoterId() + "-" + "Occupation Id" + ":" + familyVO.getOccupationId()+" : " +"education Id" + ":" + familyVO.getEducationId() + ":: ";
						}
					}*/
					//Gson gson = new Gson();
					 
					//System.out.println(gson.toJson(inputResponse));
					
					if(StringUtils.hasText(inputResponse.getMemberTypeId()))
						tdpCadreBackupDetails.setTdpMemberTypeId(Long.valueOf(inputResponse.getMemberTypeId()));
					
					tdpCadreBackupDetails.setRefNo(inputResponse.getRefNo());
					//tdpCadreBackupDetails.setCadreBasicInfo(cadreBasicInfo);
					//tdpCadreBackupDetails.setCadrePreviousRoles(previousRoles);
					//tdpCadreBackupDetails.setCadrePreviousElections(previousElections);
					tdpCadreBackupDetails.setUpdatedBy(inputResponse.getUpdatedUserId() != null ? inputResponse.getUpdatedUserId().longValue():0L);
					tdpCadreBackupDetails.setDataSourceType(registrationType);
					//tdpCadreBackupDetails.setFamilyDetails(familyDetails);
					tdpCadreBackupDetails.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					//tdpCadreBackupDetails.setJsonObject(gson.toJson(inputResponse));
					tdpCadreBackupDetails = tdpCadreBackupDetailsDAO.save(tdpCadreBackupDetails);
					
				}
				return tdpCadreBackupDetails;
				}
			      
			});
			return returnData;
			}
		 catch (Exception e) {
		LOG.error("exception occured in updateRequestDetailsForBackup in CadreRegistrationService service",e);
		return null;
		}
		}
		return returnData;
	}
	
	/**
	 * This service is used for saving cadre data from tab as well web also
	 * @author Prasad Thiragabathina
	 * @date 26-09-2014
	 * @param cadreRegistrationVOList
	 * @return surveyCadreResponceVO
	 */

	public SurveyCadreResponceVO saveCadreRegistration(final List<CadreRegistrationVO> cadreRegistrationVOList,final String registrationType){
		final SurveyCadreResponceVO surveyCadreResponceVO = new SurveyCadreResponceVO();
		
		TdpCadreBackupDetails tdpCadreBackupDetails= null;
		if(IConstants.ENABLE_LOGS_SAVE)
		{
			Date d1 = new Date();
			tdpCadreBackupDetails = updateRequestDetailsForBackup(cadreRegistrationVOList,registrationType);
			Date d2 = new Date();
			LOG.error(d2.getTime()-d1.getTime() + "IN MILLI SECONDS");
		}
		
		try {
			LOG.info("Entered into saveCadreRegistration in CadreRegistrationService service");
			
			
			
						if(cadreRegistrationVOList != null && cadreRegistrationVOList.size() >0)
						{
							for (CadreRegistrationVO cadreRegistrationVO : cadreRegistrationVOList)
							{
								Boolean flag = true;
								if(cadreRegistrationVO != null)
								{
									if(cadreRegistrationVO.getVoterId() != null && cadreRegistrationVO.getVoterId().trim().length() > 0 && Long.valueOf(cadreRegistrationVO.getVoterId()) > 0)
									{
										flag = false;
										List<TdpCadre> voterIdsList = tdpCadreDAO.getVoterByVoterId(Long.valueOf(cadreRegistrationVO.getVoterId()),1L);
										if(voterIdsList.size()  == 0)
										{
											TdpCadre tdpCadre = new TdpCadre();
											tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
										}
										else
										{
											if(voterIdsList.size() > 0)
											{
												List<Long> existingVoters = new ArrayList<Long>();
												
												for (TdpCadre tdpCadre : voterIdsList) 
												{
													existingVoters.add(tdpCadre.getTdpCadreId());
												}
												boolean needUpdate = true;
												try{
												if(cadreRegistrationVO.getFamilyVoterId() != null && voterIdsList.get(0) != null && voterIdsList.get(0).getVoterId() != null && cadreRegistrationVO.getFamilyVoterId().longValue() > 0 && cadreRegistrationVO.getFamilyVoterId().longValue() == voterIdsList.get(0).getVoterId().longValue()){
													needUpdate = false;
												}
												}catch(Exception e){
													
												}
												if(needUpdate){
													cadrePreviousRolesDAO.inActiveCadreRollesDetailsById(existingVoters);
													cadreParticipatedElectionDAO.inActiveCadreElectionDetailsById(existingVoters);
													tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(existingVoters);
													//tdpCadreDAO.inActiveTdpCadreByCadreIds(existingVoters);
												}
												//emptyTdpCadreData(voterIdsList.get(0));
												
												TdpCadre tdpCadre = new TdpCadre();
												
												if(needUpdate){
													tdpCadre = voterIdsList.get(0);
													saveDataToHistoryTable(tdpCadre);
												    tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"update",false);
												}else{
													tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
												}
												
												/*
												cadrePreviousRolesDAO.inActiveCadreRollesDetailsById(voterIdsList.get(0).getTdpCadreId());
												cadreParticipatedElectionDAO.inActiveCadreElectionDetailsById(voterIdsList.get(0).getTdpCadreId());
												tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(voterIdsList.get(0).getTdpCadreId());
												emptyTdpCadreData(voterIdsList.get(0));
												tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,voterIdsList.get(0),"update",false);
												*/
											}
											
										}
										
									}										
									/*else
									{
										TdpCadre tdpCadre = new TdpCadre();
										tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new");
									}*/
									if(flag)
									{
										if(cadreRegistrationVO.getVoterCardNumber() != null && !cadreRegistrationVO.getVoterCardNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getVoterCardNumber().trim().length() > 0)
										{
											List<Long> voterCardNumbersList = voterDAO.getVoterId(cadreRegistrationVO.getVoterCardNumber());
											if(voterCardNumbersList != null && voterCardNumbersList.size() > 0)
											{
												Long voterId = voterCardNumbersList.get(0);
												List<TdpCadre> voterIdsList = tdpCadreDAO.getVoterByVoterId(voterId,1L);
												if(voterIdsList.size()  == 0)
												{
													TdpCadre tdpCadre = new TdpCadre();
													tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
												}
												else
												{
													if(voterIdsList.size() > 0)
													{

														List<Long> existingVoters = new ArrayList<Long>();
														
														for (TdpCadre tdpCadre : voterIdsList) 
														{
															existingVoters.add(tdpCadre.getTdpCadreId());
														}
														boolean needUpdate = true;
														try{
														if(cadreRegistrationVO.getFamilyVoterId() != null && voterIdsList.get(0) != null && voterIdsList.get(0).getVoterId() != null && cadreRegistrationVO.getFamilyVoterId().longValue() > 0 && cadreRegistrationVO.getFamilyVoterId().longValue() == voterIdsList.get(0).getVoterId().longValue()){
															needUpdate = false;
														}
														}catch(Exception e){
															
														}
														if(needUpdate){
															cadrePreviousRolesDAO.inActiveCadreRollesDetailsById(existingVoters);
															cadreParticipatedElectionDAO.inActiveCadreElectionDetailsById(existingVoters);
															tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(existingVoters);
															//tdpCadreDAO.inActiveTdpCadreByCadreIds(existingVoters);
															//emptyTdpCadreData(voterIdsList.get(0));
														}
														TdpCadre tdpCadre = new TdpCadre();
														if(needUpdate){
															tdpCadre = voterIdsList.get(0);
															saveDataToHistoryTable(tdpCadre);
														    tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"update",false);
														}else{
															tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
														}
														/*
														cadrePreviousRolesDAO.inActiveCadreRollesDetailsById(voterIdsList.get(0).getTdpCadreId());
														cadreParticipatedElectionDAO.inActiveCadreElectionDetailsById(voterIdsList.get(0).getTdpCadreId());
														tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(voterIdsList.get(0).getTdpCadreId());
														emptyTdpCadreData(voterIdsList.get(0));
														tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,voterIdsList.get(0),"update",false);
														*/
													}
												}
											}else{
												TdpCadre tdpCadre = new TdpCadre();
												tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
											}
										}else if(cadreRegistrationVO.getCadreId() != null && cadreRegistrationVO.getCadreId().longValue() > 0){
											TdpCadre cadre = null;
											try{
												cadre = tdpCadreDAO.get(cadreRegistrationVO.getCadreId());
											}catch(Exception e){
												LOG.error(e);
											}
											if(cadre != null){
												
												List<Long> existingVoters = new ArrayList<Long>();
												existingVoters.add(cadreRegistrationVO.getCadreId());
												cadrePreviousRolesDAO.inActiveCadreRollesDetailsById(existingVoters);
												cadreParticipatedElectionDAO.inActiveCadreElectionDetailsById(existingVoters);
												tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(existingVoters);
												saveDataToHistoryTable(cadre);
												tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,cadre,"update",false);
											}else{
												TdpCadre tdpCadre = new TdpCadre();
												tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
											}
										}
									    else
										{
									    	TdpCadre tdpCadre = new TdpCadre();
											tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
										}
									}
								    
									
									
								}
							}
						
							
						}
				

		} catch (Exception e) {
			if(tdpCadreBackupDetails != null)
			{
				TdpCadreBackupDetails details =  tdpCadreBackupDetailsDAO.get(tdpCadreBackupDetails.getTdpCadreBackupDetailsId());
				if(details != null)
				{
					details.setException(e.getStackTrace().toString());
					tdpCadreBackupDetailsDAO.save(details);
				}
			}
			surveyCadreResponceVO.setResultCode(ResultCodeMapper.FAILURE);
			surveyCadreResponceVO.setStatus("EXCEPTION");
			LOG.error("Exception raised in saveCadreRegistration in CadreRegistrationService service", e);
		}
		
		return surveyCadreResponceVO;
	}
	/*
	 public SurveyCadreResponceVO saveCadreRegistration(final List<CadreRegistrationVO> cadreRegistrationVOList,final String registrationType)
	{
		final SurveyCadreResponceVO surveyCadreResponceVO = new SurveyCadreResponceVO();
		
		try {
			LOG.info("Entered into saveCadreRegistration in CadreRegistrationService service");
			
			updateRequestDetailsForBackup(cadreRegistrationVOList,registrationType);
			
						if(cadreRegistrationVOList != null && cadreRegistrationVOList.size() >0)
						{
							for (CadreRegistrationVO cadreRegistrationVO : cadreRegistrationVOList)
							{
								Boolean flag = true;
								if(cadreRegistrationVO != null)
								{
									if(cadreRegistrationVO.getVoterId() != null && Long.valueOf(cadreRegistrationVO.getVoterId()) > 0)
									{
										flag = false;
										List<TdpCadre> voterIdsList = tdpCadreDAO.getVoterByVoterId(Long.valueOf(cadreRegistrationVO.getVoterId()));
										if(voterIdsList.size()  == 0)
										{
											TdpCadre tdpCadre = new TdpCadre();
											tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
										}
										else
										{
											if(voterIdsList.size() > 0)
											{
												List<Long> existingVoters = new ArrayList<Long>();
												
												for (TdpCadre tdpCadre : voterIdsList) 
												{
													existingVoters.add(tdpCadre.getTdpCadreId());
												}
												cadrePreviousRolesDAO.inActiveCadreRollesDetailsById(existingVoters);
												cadreParticipatedElectionDAO.inActiveCadreElectionDetailsById(existingVoters);
												//tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(existingVoters);
												emptyTdpCadreData(voterIdsList.get(0));
												tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,voterIdsList.get(0),"update",false);
											}
											
										}
										
									}
									/*else
									{
										TdpCadre tdpCadre = new TdpCadre();
										tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new");
									}*/
								/*	if(flag)
									{
										if(cadreRegistrationVO.getVoterCardNumber() != null && !cadreRegistrationVO.getVoterCardNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getVoterCardNumber().trim().length() > 0)
										{
											List<Long> voterCardNumbersList = voterDAO.getVoterId(cadreRegistrationVO.getVoterCardNumber());
											if(voterCardNumbersList != null && voterCardNumbersList.size() > 0)
											{
												Long voterId = voterCardNumbersList.get(0);
												List<TdpCadre> voterIdsList = tdpCadreDAO.getVoterByVoterId(voterId);
												if(voterIdsList.size()  == 0)
												{
													TdpCadre tdpCadre = new TdpCadre();
													tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
												}
												else
												{
													if(voterIdsList.size() > 0)
													{
														List<Long> existingVoters = new ArrayList<Long>();
														
														for (TdpCadre tdpCadre : voterIdsList) 
														{
															existingVoters.add(tdpCadre.getTdpCadreId());
														}
														
														cadrePreviousRolesDAO.inActiveCadreRollesDetailsById(existingVoters);
														cadreParticipatedElectionDAO.inActiveCadreElectionDetailsById(existingVoters);
														tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(existingVoters);
														//emptyTdpCadreData(voterIdsList.get(0));
														tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,voterIdsList.get(0),"update",false);
													}
												}
											}else{
												TdpCadre tdpCadre = new TdpCadre();
												tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",true);
											}
										}
									    else
										{
									    	TdpCadre tdpCadre = new TdpCadre();
											tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",true);
										}
									}
								    
									
									
								}
							}
						
							
						}
				

		} catch (Exception e) {
			surveyCadreResponceVO.setResultCode(ResultCodeMapper.FAILURE);
			surveyCadreResponceVO.setStatus("EXCEPTION");
			LOG.error("Exception raised in saveCadreRegistration in CadreRegistrationService service", e);
		}
		
		return surveyCadreResponceVO;
	}
	*/
	
	/**
	 * @author Prasad Thiragabathina
	 * @date 26-09-2014
	 * @param registrationType
	 * @param cadreRegistrationVOList
	 * @param cadreRegistrationVO
	 * @param surveyCadreResponceVO
	 */
	public void tdpCadreSavingLogic(final String registrationType,final List<CadreRegistrationVO> cadreRegistrationVOList ,final CadreRegistrationVO cadreRegistrationVO, final SurveyCadreResponceVO surveyCadreResponceVO,TdpCadre tdpCadreNew,String insertTypeNew,final boolean statusVar)
	{
		/*try {	*/
		
		if(registrationType.equalsIgnoreCase("ONLINE") && cadreRegistrationVO.getOrderId() != null && cadreRegistrationVO.getOrderId().trim().length() > 0){
			List<TdpCadre> tdpCadres = tdpCadreDAO.checkOnlineAccountExistsOrNot(cadreRegistrationVO.getOrderId());
			if(tdpCadres != null && tdpCadres.size() > 0 && tdpCadres.get(0) != null){
				tdpCadreNew = tdpCadres.get(0);
				insertTypeNew ="update";
			}
		}
		final String insertType = insertTypeNew;
		final TdpCadre tdpCadre = tdpCadreNew;
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					TdpCadre  tdpCadre1 = null;
					
					if(registrationType != null && !registrationType.equalsIgnoreCase("null") && registrationType.trim().length() > 0 && !insertType.equalsIgnoreCase("update"))
					{
						if(tdpCadre.getDataSourceType() == null){
						  tdpCadre.setDataSourceType(registrationType.trim().toUpperCase());
						}
					}
					if(cadreRegistrationVO.getVoterName() != null && !cadreRegistrationVO.getVoterName().equalsIgnoreCase("null") && cadreRegistrationVO.getVoterName().trim().length() > 0)
					{
						tdpCadre.setFirstname(cadreRegistrationVO.getVoterName());
					}
					if(cadreRegistrationVO.getDobStr() != null && cadreRegistrationVO.getDobStr().trim().length() > 0 && !cadreRegistrationVO.getDobStr().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setDateOfBirth(convertToDateFormet(cadreRegistrationVO.getDobStr().toString()));
					}
					if(cadreRegistrationVO.getGender() != null && !cadreRegistrationVO.getGender().equalsIgnoreCase("null") && cadreRegistrationVO.getGender().trim().length() > 0)
					{
						if(cadreRegistrationVO.getVoterId() != null && cadreRegistrationVO.getVoterId().trim().length() > 0 && Long.valueOf(cadreRegistrationVO.getVoterId()) > 0)
						{
							String gen = voterDAO.get(Long.valueOf(cadreRegistrationVO.getVoterId())).getGender();
							if(gen.equalsIgnoreCase("Male") || gen.equalsIgnoreCase("m")){
								if(cadreRegistrationVO.getGender().equalsIgnoreCase("Male") || cadreRegistrationVO.getGender().equalsIgnoreCase("m")){
									tdpCadre.setGender("M");
								}
								else
								{
									surveyCadreResponceVO.setErrorCode("VOTER GENDER MISS MATCH");
								}
							}else if(gen.equalsIgnoreCase("female") || gen.equalsIgnoreCase("f")){
								if(cadreRegistrationVO.getGender().equalsIgnoreCase("female") || cadreRegistrationVO.getGender().equalsIgnoreCase("f")){
									tdpCadre.setGender("F");
								}
								else
								{
									surveyCadreResponceVO.setErrorCode("VOTER GENDER MISS MATCH");
								}
							}
						}
						else
						{
							tdpCadre.setGender(cadreRegistrationVO.getGender());
						}
						
					}
					if(cadreRegistrationVO.getRelativeName() != null && !cadreRegistrationVO.getRelativeName().equalsIgnoreCase("null") && cadreRegistrationVO.getRelativeName().trim().length() > 0)
					{
						tdpCadre.setRelativename(cadreRegistrationVO.getRelativeName());
					}else{
						tdpCadre.setRelativename(null);
					}
					if(cadreRegistrationVO.getVoterId() != null && Long.valueOf(cadreRegistrationVO.getVoterId()) > 0)
					{
						tdpCadre.setVoterId(Long.valueOf(cadreRegistrationVO.getVoterId()));
					}
					else if(cadreRegistrationVO.getVoterCardNumber() != null && !cadreRegistrationVO.getVoterCardNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getVoterCardNumber().trim().length() > 0)
					{
						List<Long> voterCardNumbersList = voterDAO.getVoterId(cadreRegistrationVO.getVoterCardNumber());
						if(voterCardNumbersList != null && voterCardNumbersList.size() > 0)
						{
							tdpCadre.setVoterId(voterCardNumbersList.get(0));
						}
						/*else
						{
							surveyCadreResponceVO.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
							surveyCadreResponceVO.setStatus("DATA NOT FOUND");
							return;
						}*/
						
					}
					else
					{
						surveyCadreResponceVO.setErrorCode("VOTER ID DOES NOT EXISTS");
					}
					if(cadreRegistrationVO.getAge() != null && cadreRegistrationVO.getAge() > 0)
					{
						tdpCadre.setAge(cadreRegistrationVO.getAge());
					}
					else if(tdpCadre.getVoterId() != null)
					{
						tdpCadre.setAge(voterDAO.get(tdpCadre.getVoterId()).getAge());
					}
					else
					{
						tdpCadre.setAge(null);
						
					}
					
					if(cadreRegistrationVO.getPreviousEnrollmentNumber() != null && !cadreRegistrationVO.getPreviousEnrollmentNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getPreviousEnrollmentNumber().trim().length() > 0)
					{
						List<String> preDetailsList = tdpCadreDAO.getExistingCadreMemberDetails(cadreRegistrationVO.getPreviousEnrollmentNumber());
						if(preDetailsList == null)
						{
							surveyCadreResponceVO.setErrorCode("PREVIOUS ENROLLMENT NUMBER DOES NOT EXISTS");
						}
						else
						{
							tdpCadre.setPreviousEnrollmentNo(cadreRegistrationVO.getPreviousEnrollmentNumber());
						}
						
					}else{
						tdpCadre.setPreviousEnrollmentNo(null);
					}
					if(cadreRegistrationVO.getPartyMemberSinceStr() != null && cadreRegistrationVO.getPartyMemberSinceStr().trim().length() > 0 && !cadreRegistrationVO.getPartyMemberSinceStr().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setPartyMemberSince(convertToDateFormet(cadreRegistrationVO.getPartyMemberSinceStr()));
					}else{
						tdpCadre.setPartyMemberSince(null);
					}
					if(cadreRegistrationVO.getBloodGroupId() != null && cadreRegistrationVO.getBloodGroupId().longValue() > 0)
					{
						tdpCadre.setBloodGroupId(cadreRegistrationVO.getBloodGroupId());
					}else{
						tdpCadre.setBloodGroupId(null);
					}
					
					if(cadreRegistrationVO.getCasteId() != null && cadreRegistrationVO.getCasteId() > 0)
					{
						tdpCadre.setCasteStateId(cadreRegistrationVO.getCasteId());
					}else{
						tdpCadre.setCasteStateId(null);
					}
					
					if(cadreRegistrationVO.getMobileNumber() != null && !cadreRegistrationVO.getMobileNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getMobileNumber().trim().length() > 0)
					{
						tdpCadre.setMobileNo(cadreRegistrationVO.getMobileNumber());
					}else{
						tdpCadre.setMobileNo(null);
					}
					
					if(cadreRegistrationVO.getEmailId() != null && !cadreRegistrationVO.getEmailId().equalsIgnoreCase("null") && cadreRegistrationVO.getEmailId().trim().length()>0)
					{
						tdpCadre.setEmailId(cadreRegistrationVO.getEmailId().trim());
					}
					else{
						tdpCadre.setEmailId(null);
					}
					
					if(cadreRegistrationVO.getEducationId() != null && cadreRegistrationVO.getEducationId().longValue() > 0)
					{
						tdpCadre.setEducationId(cadreRegistrationVO.getEducationId());
					}else{
						tdpCadre.setEducationId(null);
					}
					
					if(cadreRegistrationVO.getOccupationId() != null && cadreRegistrationVO.getOccupationId().longValue() > 0)
					{
						tdpCadre.setOccupationId(cadreRegistrationVO.getOccupationId());
					}else{
						tdpCadre.setOccupationId(null);
					}
					if(cadreRegistrationVO.getHouseNo() != null && !cadreRegistrationVO.getHouseNo().equalsIgnoreCase("null") && cadreRegistrationVO.getHouseNo().trim().length() > 0)
					{
						tdpCadre.setHouseNo(cadreRegistrationVO.getHouseNo());
					}else{
						tdpCadre.setHouseNo(null);
					}
					/*if(cadreRegistrationVO.getPreviousEnrollmentNumber() != null && !cadreRegistrationVO.getPreviousEnrollmentNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getPreviousEnrollmentNumber().trim().length() > 0)
					{
						tdpCadre.setPreviousEnrollmentNo(cadreRegistrationVO.getPreviousEnrollmentNumber());
					}*/
					if(cadreRegistrationVO.getCreatedUserId() != null && cadreRegistrationVO.getCreatedUserId().longValue() > 0  )
					{
					  if(!insertType.equalsIgnoreCase("update")){
						if(registrationType.equalsIgnoreCase("TAB")){
						  if(tdpCadre.getInsertedUserId() == null && tdpCadre.getInsertedWebUserId() == null){
						      tdpCadre.setInsertedUserId(cadreRegistrationVO.getCreatedUserId().longValue());
						  }else{
							  tdpCadre.setUpdatedUserId(cadreRegistrationVO.getCreatedUserId().longValue());
						  }
						}else{
							if(tdpCadre.getInsertedUserId() == null && tdpCadre.getInsertedWebUserId() == null){
							    tdpCadre.setInsertedWebUserId(cadreRegistrationVO.getCreatedUserId().longValue());
							}else{
								tdpCadre.setUpdatedWebUserId(cadreRegistrationVO.getCreatedUserId().longValue());
							}
						}
					  }else{
					    if(registrationType.equalsIgnoreCase("TAB")){
						     tdpCadre.setUpdatedUserId(cadreRegistrationVO.getCreatedUserId().longValue());
						}else{
							 tdpCadre.setUpdatedWebUserId(cadreRegistrationVO.getCreatedUserId().longValue());
						}
					  }
					}
					/*if(cadreRegistrationVO.getUpdatedUserId() != null && cadreRegistrationVO.getUpdatedUserId().longValue() > 0)
					{
						if(registrationType.equalsIgnoreCase("TAB")){
						     tdpCadre.setUpdatedUserId(cadreRegistrationVO.getUpdatedUserId().longValue());
						}else{
							 tdpCadre.setUpdatedWebUserId(cadreRegistrationVO.getUpdatedUserId().longValue());
						}
					}*/
					
					UserAddress userAddress = new UserAddress();
					
					if(tdpCadre.getVoterId() != null && tdpCadre.getVoterId().longValue() > 0)
					{
						getVoterAddressDetails(tdpCadre.getVoterId(),userAddress,cadreRegistrationVO);
					}
					else if(cadreRegistrationVO.getFamilyVoterId() != null && cadreRegistrationVO.getFamilyVoterId().longValue() > 0)
					{
						getVoterAddressDetails(cadreRegistrationVO.getFamilyVoterId(),userAddress,cadreRegistrationVO);
					}
					if(userAddress.getBooth() == null)
					{
						if(cadreRegistrationVO.getConstituencyId() != null && cadreRegistrationVO.getConstituencyId().trim().length() > 0 && !cadreRegistrationVO.getConstituencyId().trim().equalsIgnoreCase("null") && Long.valueOf(cadreRegistrationVO.getConstituencyId()) > 0)
						{
							//userAddress = new UserAddress();
							if(Long.valueOf(cadreRegistrationVO.getConstituencyId()) > 0)
							{
								userAddress.setConstituency(constituencyDAO.get(Long.valueOf(cadreRegistrationVO.getConstituencyId())));
								userAddress.setCountry(countryDAO.get(1l));
								userAddress.setState(stateDAO.get(1l));
								userAddress.setDistrict(constituencyDAO.get(Long.valueOf(cadreRegistrationVO.getConstituencyId())).getDistrict());
								if(cadreRegistrationVO.getStreet() != null && cadreRegistrationVO.getStreet().trim().length() > 0 && !cadreRegistrationVO.getStreet().trim().equalsIgnoreCase("null"))
								{
									userAddress.setStreet(cadreRegistrationVO.getStreet());
								}
								if(cadreRegistrationVO.getPanchayatId() != null && cadreRegistrationVO.getPanchayatId().trim().length() > 0 && !cadreRegistrationVO.getPanchayatId().trim().equalsIgnoreCase("null"))
								{
									if(Long.valueOf(cadreRegistrationVO.getPanchayatId()) > 0)
									{
										userAddress.setPanchayatId(Long.valueOf(cadreRegistrationVO.getPanchayatId()));
										userAddress.setTehsil(panchayatDAO.get(Long.valueOf(cadreRegistrationVO.getPanchayatId())).getTehsil());
									}
									
								}
								if(cadreRegistrationVO.getMandalId() != null && cadreRegistrationVO.getMandalId().trim().length() > 0 && Long.valueOf(cadreRegistrationVO.getMandalId().trim()).longValue() > 0l)
								{
										userAddress.setTehsil(tehsilDAO.get(Long.valueOf(cadreRegistrationVO.getMandalId().trim())));
									
								}
								if(cadreRegistrationVO.getWardId() != null && cadreRegistrationVO.getWardId().trim().length() > 0 && Long.valueOf(cadreRegistrationVO.getWardId().trim()).longValue() > 0l)
								{
										userAddress.setWard(constituencyDAO.get(Long.valueOf(cadreRegistrationVO.getWardId().trim())));
									
								}
								List<Booth> booths = null;
								if(tdpCadre.getVoterId() != null && tdpCadre.getVoterId().longValue() > 0){
								   booths = boothPublicationVoterDAO.getVoterAddressDetails(tdpCadre.getVoterId());
								}
								if(cadreRegistrationVO.getBoothId() != null && cadreRegistrationVO.getBoothId().trim().length() > 0 && !cadreRegistrationVO.getBoothId().trim().equalsIgnoreCase("null"))
								{
									if(Long.valueOf(cadreRegistrationVO.getBoothId()) > 0)
									{
										userAddress.setBooth(boothDAO.get(Long.valueOf(cadreRegistrationVO.getBoothId())));
									}
									else
									{
										if(booths != null && booths.size() > 0)
										{
											userAddress.setBooth(booths.get(0));
										}
										
									}
								}
								if(cadreRegistrationVO.getMuncipalityId() !=null && cadreRegistrationVO.getMuncipalityId().trim().length() > 0 && !cadreRegistrationVO.getMuncipalityId().trim().equalsIgnoreCase("null"))
								{
									if(Long.valueOf(cadreRegistrationVO.getMuncipalityId()) > 0)
									{
										userAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(cadreRegistrationVO.getMuncipalityId())));
									}
									
								}
								List<Hamlet> hamletWardList = null;
								if(tdpCadre.getVoterId() != null){
								 hamletWardList = userVoterDetailsDAO.getHamletByVoterId(tdpCadre.getVoterId());
								}
								if(hamletWardList != null && hamletWardList.size() > 0)
								{
									userAddress.setHamlet(hamletWardList.get(0));
								}
								if(booths != null && booths.size() > 0)
								{
									if(booths.get(0).getLocalBodyWard() != null)
									{
										userAddress.setWard(booths.get(0).getLocalBodyWard());
									}
									else
									{
										List<Constituency> wardsList = null;
										if(tdpCadre.getVoterId() != null){
										 wardsList =  userVoterDetailsDAO.getWardByVoterId(tdpCadre.getVoterId());
										}
										if(wardsList != null && wardsList.size() > 0)
										{
											userAddress.setWard(wardsList.get(0));
										}
									}
								}
								
							}
							
						}
						
					}
					
						
					userAddress = userAddressDAO.save(userAddress);
					tdpCadre.setUserAddress(userAddress);	
					TdpCadreOnline tdpCadreOnline = null;
					if(registrationType.equalsIgnoreCase("ONLINE")){
						tdpCadre.setCardNo(cadreRegistrationVO.getVoterCardNo());
						tdpCadreOnline = new TdpCadreOnline(); 
						tdpCadreOnline.setOrderId(cadreRegistrationVO.getOrderId());
						tdpCadreOnline.setArea(cadreRegistrationVO.getArea());
						tdpCadreOnline.setAddress(cadreRegistrationVO.getPermanentAddress());
						tdpCadreOnline.setPincode(cadreRegistrationVO.getPincode());
						tdpCadreOnline.setDeliveryMode(cadreRegistrationVO.getDeliveryMode());
						tdpCadreOnline.setShipCountry(cadreRegistrationVO.getShipCountry());
						tdpCadreOnline.setShipAddress(cadreRegistrationVO.getShipAddress());
						tdpCadreOnline.setEmail(cadreRegistrationVO.getEmail());
						tdpCadreOnline.setPermanentAddress(cadreRegistrationVO.getAddress());
						tdpCadreOnline.setOnlineId(cadreRegistrationVO.getOnlineId());
						tdpCadreOnline = tdpCadreOnlineDAO.save(tdpCadreOnline);
						
					}
					tdpCadre.setTdpCadreOnline(tdpCadreOnline);
					try{
						if(cadreRegistrationVO.getVoterTeluguName()!= null && cadreRegistrationVO.getVoterTeluguName().trim().length() > 0 && tdpCadre.getVoterId() != null &&  tdpCadre.getVoterId().longValue() > 0){
							List<VoterNames> voterNames = voterNamesDAO.gerVoterNamesObjByVoterId(tdpCadre.getVoterId());
							
							if(voterNames != null && voterNames.size() > 0 && voterNames.get(0) != null){
								for(VoterNames voterName:voterNames){
									if(voterName != null){
										voterName.setFirstName(cadreRegistrationVO.getVoterTeluguName().trim());
										voterName.setLastName("");
										voterName.getVoterNamesId();
										
										//voterNamesDAO.updateVoterName(voterName.getFirstName(), voterName.getVoterNamesId());
										voterNamesDAO.save(voterName);
									}
								}
							}else{
								VoterNames voterName = new VoterNames();
								//voterName.setVoter(voterDAO.get(tdpCadre.getVoterId()));
								voterName.setVoterId(tdpCadre.getVoterId());
								voterName.setFirstName(cadreRegistrationVO.getVoterTeluguName().trim());
								voterName.setLastName("");
								voterNamesDAO.save(voterName);
							}
							
							
						}
						if(userAddress.getConstituency() != null && userAddress.getConstituency().getConstituencyId() != null){
							cadreRegistrationVO.setConstituencyId( userAddress.getConstituency().getConstituencyId().toString());
						}
						
					}catch(Exception e){
						LOG.error(e);
					}
					
					if(cadreRegistrationVO.getNomineeName() != null && cadreRegistrationVO.getNomineeName().trim().length() > 0 && !cadreRegistrationVO.getNomineeName().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setNomineeName(cadreRegistrationVO.getNomineeName());
					}
					if(cadreRegistrationVO.getAadheerNo() != null && cadreRegistrationVO.getAadheerNo().trim().length() > 0 && !cadreRegistrationVO.getAadheerNo().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setAadheerNo(cadreRegistrationVO.getAadheerNo());
					}
					if(cadreRegistrationVO.getVoterRelationId() != null && cadreRegistrationVO.getVoterRelationId().longValue() > 0)
					{
						tdpCadre.setVoterRelationId(cadreRegistrationVO.getVoterRelationId());
					}
					if(cadreRegistrationVO.getCadreType() != null && cadreRegistrationVO.getCadreType().trim().length() > 0 && cadreRegistrationVO.getCadreType().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setCadreType(cadreRegistrationVO.getCadreType());
					}
					if(!insertType.equalsIgnoreCase("update") && tdpCadre.getInsertedTime() == null){
					   tdpCadre.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					}
					tdpCadre.setUpdatedTime(dateUtilService.getCurrentDateAndTime());		
					tdpCadre.setEnrollmentYear(IConstants.CADRE_ENROLLMENT_NUMBER);
					if(cadreRegistrationVO.getLongititude() != null && !cadreRegistrationVO.getLongititude().equalsIgnoreCase("null") && cadreRegistrationVO.getLongititude().trim().length() > 0)
					{
						tdpCadre.setLongititude(cadreRegistrationVO.getLongititude());
					}
					
					if(cadreRegistrationVO.getLatitude() != null && !cadreRegistrationVO.getLatitude().equalsIgnoreCase("null") && cadreRegistrationVO.getLatitude().trim().length() > 0)
					{
						tdpCadre.setLatitude(cadreRegistrationVO.getLatitude());
					}
					tdpCadre.setIsDeleted("N");
					
					if(cadreRegistrationVO.getSurveyTimeStr() != null && cadreRegistrationVO.getSurveyTimeStr().trim().length() > 0 && !cadreRegistrationVO.getSurveyTimeStr().trim().equalsIgnoreCase("null"))
					{
						//tdpCadre.setSurveyTime(convertToDateFormet(cadreRegistrationVO.getSurveyTimeStr()));
						try {
							SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_AND_TIME_FORMAT_24HRS);
							//System.out.println(sdf.parse(cadreRegistrationVO.getSurveyTimeStr()));
							//System.out.println(1);
							if(sdf.parse(cadreRegistrationVO.getSurveyTimeStr()).before(tdpCadre.getInsertedTime())){
								//System.out.println(sdf.parse(cadreRegistrationVO.getSurveyTimeStr()));
								if(tdpCadre.getSurveyTime() == null){
									//System.out.println("2");
								   tdpCadre.setSurveyTime(sdf.parse(cadreRegistrationVO.getSurveyTimeStr()));
								}
							}else
							{//System.out.println("3");
								if(tdpCadre.getSurveyTime() == null)	
								tdpCadre.setSurveyTime(tdpCadre.getInsertedTime());
								tdpCadre.setRefSurveyTime(sdf.parse(cadreRegistrationVO.getSurveyTimeStr()));
							}
						} catch (Exception e) {
							LOG.error(e);
						}
						
					}else if( insertType.equalsIgnoreCase("new") && registrationType != null && (registrationType.equalsIgnoreCase("WEB") )){
						if(tdpCadre.getSurveyTime() == null){
						   tdpCadre.setSurveyTime(tdpCadre.getInsertedTime());
						}
					}
					
					if(cadreRegistrationVO.getNomineeAge() != null && cadreRegistrationVO.getNomineeAge().longValue() > 0)
					{
						tdpCadre.setNomineeAge(cadreRegistrationVO.getNomineeAge());
					}
					if(cadreRegistrationVO.getNomineeGender() != null && cadreRegistrationVO.getNomineeGender().trim().length() > 0 &&  !cadreRegistrationVO.getNomineeGender().trim().equalsIgnoreCase("null"))
					{
						if(cadreRegistrationVO.getNomineeGender().trim().equalsIgnoreCase("1"))
						{
							tdpCadre.setNomineeGender("Male");
						}
						else
						{
							tdpCadre.setNomineeGender("Female");
						}
						
					}
					/*synchronized (surveyCadreResponceVO) {
						String memberNumber = tdpCadreDAO.getLatestMemberNumber();
						if(memberNumber == null)
						{
							tdpCadre.setMemberShipNo("10000001");
							surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getMemberShipNo());
						}
						else
						{
							tdpCadre.setMemberShipNo(String.valueOf((Long.valueOf(memberNumber) + 1l)));
							surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getMemberShipNo());
						}
					}*/
					if(cadreRegistrationVO.getUniqueKey() != null && !cadreRegistrationVO.getUniqueKey().equalsIgnoreCase("null") && cadreRegistrationVO.getUniqueKey().trim().length() > 0)
					{
						tdpCadre.setUniqueKey(cadreRegistrationVO.getUniqueKey());
						if(cadreRegistrationVOList.size() == 1)
						{
							surveyCadreResponceVO.setUniqueKey(cadreRegistrationVO.getUniqueKey());
							surveyCadreResponceVO.setVoterName(cadreRegistrationVO.getVoterName());
							surveyCadreResponceVO.setVoterId(Long.valueOf(cadreRegistrationVO.getVoterId()));
							surveyCadreResponceVO.setRelativeName(cadreRegistrationVO.getRelativeName());
						}
					}
					if(cadreRegistrationVO.getCandidateAadherNo() != null && cadreRegistrationVO.getCandidateAadherNo().trim().length() > 0 && !cadreRegistrationVO.getCandidateAadherNo().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setCadreAadherNo(cadreRegistrationVO.getCandidateAadherNo());
					}else{
						tdpCadre.setCadreAadherNo(null);
					}
					if(cadreRegistrationVO.getCadrePrevYear() != null && cadreRegistrationVO.getCadrePrevYear().trim().length() > 0 && !cadreRegistrationVO.getCadrePrevYear().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setPreviousMembershipYear(cadreRegistrationVO.getCadrePrevYear());
					}
					if(cadreRegistrationVO.getPhotoType() != null && cadreRegistrationVO.getPhotoType().trim().length() > 0 && !cadreRegistrationVO.getPhotoType().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setPhotoType(cadreRegistrationVO.getPhotoType() );
					}
					else
					{
						tdpCadre.setPhotoType("VOTER");
					}
					if(cadreRegistrationVO.getNameType() != null && cadreRegistrationVO.getNameType().trim().length() > 0 && !cadreRegistrationVO.getNameType().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setNameType(cadreRegistrationVO.getNameType() );
					}
					else
					{
						tdpCadre.setNameType("VOTER");
					}
					if(cadreRegistrationVO.getRelationType() != null && cadreRegistrationVO.getRelationType().trim().length() > 0 && !cadreRegistrationVO.getRelationType().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setRelativeType(cadreRegistrationVO.getRelationType());
					}				
					if(cadreRegistrationVO.isRelative()){
					   tdpCadre.setIsRelative("Y");
					  
					}
					
					if(cadreRegistrationVO.getFamilyVoterId() != null && cadreRegistrationVO.getFamilyVoterId().longValue() > 0 && registrationType.equalsIgnoreCase("TAB"))
					{
						tdpCadre.setFamilyVoterId(cadreRegistrationVO.getFamilyVoterId());
						try {
							if(cadreRegistrationVO.getVoterId() != null && cadreRegistrationVO.getVoterId().trim().length() > 0)
							{
								if(cadreRegistrationVO.getFamilyVoterId().longValue() == Long.valueOf(cadreRegistrationVO.getVoterId().toString()).longValue())
								{
									tdpCadre.setVoterId(null);
									tdpCadre.setGender(cadreRegistrationVO.getGender());
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						tdpCadre.setIsRelative("Y");
						tdpCadre.setRelationTypeId(cadreRegistrationVO.getRelationTypeId());
						/*if(cadreRegistrationVO.getRelationType() != null && cadreRegistrationVO.getRelationType().trim().length() >0 && !cadreRegistrationVO.getRelationType().trim().equalsIgnoreCase("null"))
						{
							tdpCadre.setRelationType(voterRelationDAO.get(Long.valueOf(cadreRegistrationVO.getRelationType())));
						}*/
						
					}else{
						   tdpCadre.setIsRelative("N");
						   tdpCadre.setRelationTypeId(null);
						}
					
					tdpCadre.setCardNo(cadreRegistrationVO.getVoterCardNumber());
					
					if(statusVar){
						tdpCadre.setNoVoterId("Y");
						
						if(cadreRegistrationVO.getVoterCardNumber() != null && cadreRegistrationVO.getVoterCardNumber().trim().length()>0){
						   Long count = tdpCadreDAO.checkCardNoExistsOrNot(cadreRegistrationVO.getVoterCardNumber());
						   if(count.longValue() > 0){
						      tdpCadre.setIsDeleted("Y");
						      tdpCadre.setIsDuplicate("Y");
						   }
						}
					}
					if(cadreRegistrationVO.getFamilyVoterId()!=null && registrationType.equalsIgnoreCase("WEB")){
						tdpCadre.setFamilyVoterId(cadreRegistrationVO.getFamilyVoterId());
						tdpCadre.setIsRelative("Y");
						 tdpCadre.setRelationTypeId(cadreRegistrationVO.getRelationTypeId());
					}
					boolean noSms = false;
					if(registrationType != null && (registrationType.equalsIgnoreCase("WEB") || registrationType.equalsIgnoreCase("ONLINE")) && insertType.equalsIgnoreCase("new")){
						String userId = "0000";
						if(cadreRegistrationVO.getCreatedUserId() != null){
						   userId = cadreRegistrationVO.getCreatedUserId().toString();
						}
						String ref = getReferenceNo(userId,registrationType);
						
							if(tdpCadre.getRefNo() == null || tdpCadre.getRefNo().trim().length() == 0){
							  ref = getUniueRefNo(ref,registrationType);
							  tdpCadre.setRefNo(ref);
							}
							cadreRegistrationVO.setRefNo(tdpCadre.getRefNo());
							
							
							surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
							//uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre);
							  tdpCadre1 = tdpCadreDAO.save(tdpCadre);	
						
					}else if(registrationType != null && (registrationType.equalsIgnoreCase("WEB") || registrationType.equalsIgnoreCase("ONLINE")) && !insertType.equalsIgnoreCase("new")){
						surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
						tdpCadre1 = tdpCadreDAO.save(tdpCadre);	
				    }else{
					  if(insertType.equalsIgnoreCase("new")){
							 							 
						     tdpCadre.setRefNo(cadreRegistrationVO.getRefNo());
						     /*if(registrationType.equalsIgnoreCase("TAB")){
						    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						    	 try{
						    	 Date lastDate = sdf.parse("2014-12-26");
						    	 if(tdpCadre.getSurveyTime().compareTo(lastDate) >0){
						    		 noSms = true;
						    		 tdpCadre.setIsDeleted("AR");
						    	 }
						    	 }catch(Exception e){
						    		LOG.error(e);
						    	 }
						     }*/
						       
								surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
								//uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre);
								  tdpCadre1 = tdpCadreDAO.save(tdpCadre);	
						  
					  }else{
						  //tdpCadre.setRefNo(cadreRegistrationVO.getRefNo());
						  //uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre);
						  surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
						  
						    tdpCadre1= tdpCadreDAO.save(tdpCadre);	
					  }
					}
					   if(tdpCadre1.getMemberShipNo() == null || tdpCadre1.getMemberShipNo().trim().length() == 0){
						   Long distId =1l;
						   if(userAddress.getDistrict() != null){
							   distId = userAddress.getDistrict().getDistrictId();
						   }
						  String membershipNo = getMemberShipNo(distId,tdpCadre1.getTdpCadreId());
						  tdpCadre1.setMemberShipNo(membershipNo);
						}
					   uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre1);
					   tdpCadre1 = tdpCadreDAO.save(tdpCadre1);
					   
					   
					//SAVING THE TELUGU NAME OF NON VOTER -- START //SASI
					if(tdpCadre1.getVoterId() == null && cadreRegistrationVO.getVoterTeluguName()!= null && cadreRegistrationVO.getVoterTeluguName().trim().length() > 0){
						TdpCadreTeluguNames model = new TdpCadreTeluguNames();
						model.setTeluguName(cadreRegistrationVO.getVoterTeluguName().trim());
						model.setTdpCadreId(tdpCadre1.getTdpCadreId());
						model.setEnglishName(cadreRegistrationVO.getVoterName());
						model.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						tdpCadreTeluguNamesDAO.save(model);
					}
					//SAVING THE TELUGU NAME OF NON VOTER -- END
					   
					surveyCadreResponceVO.setEnrollmentNumber(tdpCadre1.getRefNo());
					List<CadrePreviousRollesVO> previousRollesPartList = cadreRegistrationVO.getPreviousRollesList();
					if(previousRollesPartList != null && previousRollesPartList.size() > 0)
					{
						for (CadrePreviousRollesVO rolesVO : previousRollesPartList)
						{
							if(rolesVO != null)
							{
								if(tdpCadre1 != null)
								{
									CadrePreviousRoles cadrePreviousRoles = new CadrePreviousRoles();
									cadrePreviousRoles.setTdpCadreId(tdpCadre1.getTdpCadreId());
									
								
										if(rolesVO.getCadreCommitteeId() != null && rolesVO.getCadreCommitteeId().longValue() > 0 && rolesVO.getCadreCommitteeLevelId() != null && rolesVO.getCadreCommitteeLevelId().longValue() > 0 && rolesVO.getCadreRoleId() != null && rolesVO.getCadreRoleId().longValue() > 0 )
										{
											List<Long> cadreCommotteRoleIds = cadreCommitteeRoleDAO.getCadreCommitteRoleIdBySelection(rolesVO.getCadreCommitteeLevelId(), rolesVO.getCadreRoleId(), rolesVO.getCadreCommitteeId());
											if(cadreCommotteRoleIds != null && cadreCommotteRoleIds.size() > 0)
											{
												cadrePreviousRoles.setCadreCommitteeRoleId(cadreCommotteRoleIds.get(0));
											}
											
										}
										if(rolesVO.getFromDateStr() != null && rolesVO.getFromDateStr().trim().length() > 0 && !rolesVO.getFromDateStr().trim().equalsIgnoreCase("null"))
										{
											cadrePreviousRoles.setFromDate(convertToDateFormet(rolesVO.getFromDateStr()));
										}
										if(rolesVO.getToDateStr() != null && rolesVO.getToDateStr().trim().length() > 0 && !rolesVO.getToDateStr().trim().equalsIgnoreCase("null"))
										{
											cadrePreviousRoles.setToDate(convertToDateFormet(rolesVO.getToDateStr()));
										}
										cadrePreviousRoles.setInsertedDate(dateUtilService.getCurrentDateAndTime());
										cadrePreviousRoles.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
										cadrePreviousRoles.setIsDeleted("N");
										cadrePreviousRolesDAO.save(cadrePreviousRoles);
									//}
									
								}
							}
							
						}
					}
					
					List<CadrePreviousRollesVO> previousElectionPartList = cadreRegistrationVO.getPreviousParicaptedElectionsList();
					if(previousElectionPartList != null && previousElectionPartList.size() > 0)
					{
						for (CadrePreviousRollesVO electionVO : previousElectionPartList)
						{
							if(electionVO != null)
							{
								if(tdpCadre1 != null)
								{
									if(electionVO.getConstituencyId() != null && electionVO.getConstituencyId().trim().length()  > 0)
									{
										CadreParticipatedElection cadreParticipatedElection = new CadreParticipatedElection();
										
										cadreParticipatedElection.setTdpCadreId(tdpCadre1.getTdpCadreId());
										if(electionVO.getElectionTypeId() != null && electionVO.getElectionTypeId().trim().length() > 0)
										{
											if(Long.valueOf(electionVO.getElectionTypeId()) > 0)
											{
												cadreParticipatedElection.setElectionId(Long.valueOf(electionVO.getElectionTypeId()));
											}
											
										}
										if(electionVO.getConstituencyId() != null && electionVO.getConstituencyId().trim().length()> 0)
										{
											if(Long.valueOf(electionVO.getConstituencyId()).longValue() > 0)
											{
												cadreParticipatedElection.setConstituencyId(Long.valueOf(electionVO.getConstituencyId()));
											}
											
										}
										if(electionVO.getCandidateId() != null && electionVO.getCandidateId().trim().length()> 0)
										{   if(Long.valueOf(electionVO.getCandidateId()).longValue() > 0)
										   {
												cadreParticipatedElection.setCandidateId((Long.valueOf(electionVO.getCandidateId())));
										   }
										}
										cadreParticipatedElection.setIsDeleted("N");
										cadreParticipatedElectionDAO.save(cadreParticipatedElection);
									}
									
								}
							}
							
							
						}
					}
					
					List<CadreFamilyVO> cadreFamilyDetailsList = cadreRegistrationVO.getCadreFamilyDetails();
					if(cadreFamilyDetailsList != null && cadreFamilyDetailsList.size() > 0)
					{
						for (CadreFamilyVO cadreFamilyVO : cadreFamilyDetailsList) 
						{
							if(tdpCadre1 != null)
							{
								if(cadreFamilyVO != null)
								{
									//if((cadreFamilyVO.getOccupationId() != null && cadreFamilyVO.getOccupationId().longValue() > 0) || cadreFamilyVO.getEducationId() != null && cadreFamilyVO.getEducationId().longValue() > 0)
									//{
										TdpCadreFamilyDetails tdpCadreFamilyDetails = new TdpCadreFamilyDetails();
										tdpCadreFamilyDetails.setTdpCadreId(tdpCadre1.getTdpCadreId());
										if(cadreFamilyVO.getVoterName() != null && !cadreFamilyVO.getVoterName().equalsIgnoreCase("null") && cadreFamilyVO.getVoterName().trim().length() > 0)
										{
											tdpCadreFamilyDetails.setVoterName(cadreFamilyVO.getVoterName());
										}
										if(cadreFamilyVO.getOccupationId() != null && cadreFamilyVO.getOccupationId().longValue() > 0l)
										{
											tdpCadreFamilyDetails.setOccupationId(cadreFamilyVO.getOccupationId());
										}
										if(cadreFamilyVO.getEducationId() != null && cadreFamilyVO.getEducationId().longValue() > 0l)
										{
											tdpCadreFamilyDetails.setEducationId(cadreFamilyVO.getEducationId());
										}
										if(cadreFamilyVO.getAge() != null && cadreFamilyVO.getAge().longValue() > 0l)
										{
											tdpCadreFamilyDetails.setAge(cadreFamilyVO.getAge());
										}
										if(cadreFamilyVO.getGender() != null && cadreFamilyVO.getGender().trim().length() > 0)
										{
											tdpCadreFamilyDetails.setGender(cadreFamilyVO.getGender().trim());
										}
										if(cadreFamilyVO.getFamilyRelationId() != null && cadreFamilyVO.getFamilyRelationId().longValue() > 0)
										{
											tdpCadreFamilyDetails.setFamilyRelationId(cadreFamilyVO.getFamilyRelationId().longValue());
										}
										if(cadreFamilyVO.getVoterId() != null && cadreFamilyVO.getVoterId().longValue() > 0l)
										{
											tdpCadreFamilyDetails.setVoterId(cadreFamilyVO.getVoterId());
											try{
												Voter voter = voterDAO.get(cadreFamilyVO.getVoterId());
												if(tdpCadreFamilyDetails.getGender() == null){
													tdpCadreFamilyDetails.setGender(voter.getGender());
												}
												if(tdpCadreFamilyDetails.getAge() == null){
													tdpCadreFamilyDetails.setAge(voter.getAge());
												}
											}catch(Exception e){
												
											}
										}
										else
										{
											if(cadreFamilyVO.getVoterCadreNO() != null && !cadreFamilyVO.getVoterCadreNO().equalsIgnoreCase("null") && cadreFamilyVO.getVoterCadreNO().trim().length() > 0)
											{
												List<Long> voterCardNumbersList = voterDAO.getVoterId(cadreFamilyVO.getVoterCadreNO());
												if(voterCardNumbersList != null && voterCardNumbersList.size() > 0)
												{
													tdpCadreFamilyDetails.setVoterId(voterCardNumbersList.get(0));
													Voter voter = voterDAO.get(voterCardNumbersList.get(0));
													if(tdpCadreFamilyDetails.getGender() == null){
														tdpCadreFamilyDetails.setGender(voter.getGender());
													}
													if(tdpCadreFamilyDetails.getAge() == null){
														tdpCadreFamilyDetails.setAge(voter.getAge());
													}
												}
												
											}
										}
										tdpCadreFamilyDetails.setIsDeleted("N");
										tdpCadreFamilyDetails.setInsertedDate(dateUtilService.getCurrentDateAndTime());
										tdpCadreFamilyDetails.setUpdatedDate(dateUtilService.getCurrentDateAndTime());	
										tdpCadreFamilyDetailsDAO.save(tdpCadreFamilyDetails);
									//}
									
								}
								
							}
							
						}
					}
					surveyCadreResponceVO.setMembershipNo(tdpCadre.getMemberShipNo());
					
					if(surveyCadreResponceVO.getMembershipNo() == null )
					{
						surveyCadreResponceVO.setMembershipNo(tdpCadre1.getMemberShipNo());
					}
					
					surveyCadreResponceVO.setStatus("SUCCESS");
					surveyCadreResponceVO.setResultCode(ResultCodeMapper.SUCCESS);
					if(insertType.equalsIgnoreCase("new") && cadreRegistrationVO.getMobileNumber() != null && cadreRegistrationVO.getMobileNumber().trim().length() > 0 && cadreRegistrationVO.getRefNo() != null && !noSms){
					   //sendSMS(cadreRegistrationVO.getMobileNumber().trim(), "Thank You for registering as TDP cadre.For further queries use Ref No "+cadreRegistrationVO.getRefNo());
						if(!statusVar){
								Boolean flag = true;
							try{
								
								if(cadreRegistrationVO.getFamilyVoterId() != null && cadreRegistrationVO.getFamilyVoterId().toString().trim().length() > 0 && !cadreRegistrationVO.getFamilyVoterId().toString().trim().equalsIgnoreCase("null") && cadreRegistrationVO.getFamilyVoterId().longValue() > 0)
								{
									Long  count = tdpCadreDAO.checkForFamilyExists(cadreRegistrationVO.getUniqueKey());
									if(count > 1)
									{
										flag = false;
									}
									
								}
								
								if(flag)
								{
									String jobCode = sendSMSInTelugu(cadreRegistrationVO.getMobileNumber().trim(), getUniCodeMessage(StringEscapeUtils.unescapeJava("\u0C2A\u0C3E\u0C30\u0C4D\u0C1F\u0C40 \u0C38\u0C2D\u0C4D\u0C2F\u0C24\u0C4D\u0C35\u0C02 \u0C24\u0C40\u0C38\u0C41\u0C15\u0C41\u0C28\u0C4D\u0C28\u0C02\u0C26\u0C41\u0C15\u0C41 \u0C27\u0C28\u0C4D\u0C2F\u0C35\u0C3E\u0C26\u0C2E\u0C32\u0C41. ")+cadreRegistrationVO.getRefNo()));
									Long tdpCadreId = tdpCadre1.getTdpCadreId();
									if(tdpCadreId!=null){
										if(tdpCadre1.getMobileNo()!=null){
											jobCode = jobCode.replace("OK:", "");
											SmsJobStatus smsJobStatus = new SmsJobStatus();
											smsJobStatus.setTdpCadreId(tdpCadreId);
											
											smsJobStatus.setMobileNumber(tdpCadre1.getMobileNo());
											smsJobStatus.setJobCode(jobCode);
											smsJobStatus.setFromTask("Registration");
											
											//smsJobStatusDAO.save(smsJobStatus);
											//tdpCadreDAO.updateSmsJobCode(tdpCadreId, jobCode.trim());
										}
									}
								}
								
								
							}catch (Exception e) {
								LOG.error("Exception Raised while sending SMS"+e);
							}
							
						}
					}
				}
				});
				
		/*} catch (Exception e) {
			LOG.error("Exception raised in tdpCadreSavingLogic in CadreRegistrationService service", e);
		}*/
	}
	/**
	 * 
	 * @param voterCardNo
	 * @param url
	 * @param uploadImageContentType
	 * @param uploadImage
	 * @return
	 */
	public String uploadCadreImage(String  voterCardNo,String url,String uploadImageContentType,File uploadImage)
	{
		try{
			String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			
			String filePath = url + "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator;
			
			LOG.info("Cadre File Path -- "+filePath);
			
			BufferedImage image = ImageIO.read(uploadImage);
			
			
			if(image == null)
				return null;
			LOG.info("Image is Read");
			String constiName[] = uploadImageContentType.split("/");
			String fileName = filePath+voterCardNo.toString()+"."+constiName[1];
			LOG.info("file name -- "+fileName);
			//String imageName =  cadreId.toString()+"."+constiName[1];
			
			FileImageOutputStream filName = new FileImageOutputStream(new File(fileName));
			
			ImageIO.write(image, constiName[1],filName);
			LOG.info("file uploaded");
            filName.close();
            return "success";
		}
		catch(Exception e)
		{
			LOG.error("Exception raised in uploadCadreImage in CadreRegistrationService service", e);
			return null;
			
		}
	}
	
	
	public void emptyTdpCadreData(TdpCadre tdpCadre)
	{
		try {
			tdpCadre.setPreviousEnrollmentNo(null);
			tdpCadre.setMobileNo(null);
			tdpCadre.setBloodGroupId(null);
			tdpCadre.setOccupationId(null);
			tdpCadre.setEducationId(null);
			tdpCadre.setCasteStateId(null);
		} catch (Exception e) {
			LOG.error("Exception raised in emptyTdpCadreData in CadreRegistrationService service", e);
		}
	}
	/**
	 * @author Prasad Thiragabathina
	 * @date 26-09-2014
	 * @param voterId
	 * @param returnList
	 */
	public void getVoterAddressDetails(Long voterId,UserAddress userAddress,CadreRegistrationVO cadreRegistrationVO)
	{
		try {
			LOG.info("Entered into getVoterAddressDetails in CadreRegistrationService service");
			
			List<Booth> locationDetails = boothPublicationVoterDAO.getVoterAddressDetails(voterId);
			if(locationDetails != null && locationDetails.size() > 0)
			{
				Booth booth = locationDetails.get(0);
				if(booth != null)
				{
					userAddress.setBooth(booth);
					userAddress.setCountry(countryDAO.get(1l));
					userAddress.setState(stateDAO.get(1l));
					if(booth.getConstituency() != null)
					{
						userAddress.setConstituency(booth.getConstituency());
					}
					if(booth.getConstituency() != null && booth.getConstituency().getDistrict() != null)
					{
						userAddress.setDistrict(booth.getConstituency().getDistrict());
					}
					if(booth.getTehsil() != null)
					{
						userAddress.setTehsil(booth.getTehsil());
					}
					if(booth.getLocalBody() != null)
					{
						userAddress.setLocalElectionBody(booth.getLocalBody());
					}
					if(booth.getPanchayat() != null)
					{
						userAddress.setPanchayatId(booth.getPanchayat().getPanchayatId());
					}
					if(cadreRegistrationVO != null && cadreRegistrationVO.getPanchayatId() != null && cadreRegistrationVO.getPanchayatId().trim().length() > 0 && !cadreRegistrationVO.getPanchayatId().trim().equalsIgnoreCase("null"))
					{
						if(Long.valueOf(cadreRegistrationVO.getPanchayatId().trim()) > 0)
						{
							userAddress.setPanchayatId(Long.valueOf(cadreRegistrationVO.getPanchayatId().trim().trim()));
							userAddress.setTehsil(panchayatDAO.get(Long.valueOf(cadreRegistrationVO.getPanchayatId().trim())).getTehsil());
						}
						
					}
					if(cadreRegistrationVO != null &&  cadreRegistrationVO.getWardId() != null && cadreRegistrationVO.getWardId().trim().length() > 0 && Long.valueOf(cadreRegistrationVO.getWardId().trim()).longValue() > 0l)
					{
							userAddress.setWard(constituencyDAO.get(Long.valueOf(cadreRegistrationVO.getWardId().trim())));
						
					}
					else if(booth.getLocalBodyWard() != null)
					{
						userAddress.setWard(booth.getLocalBodyWard());
					}
					else
					{
						List<Constituency> wardsList =  userVoterDetailsDAO.getWardByVoterId(voterId);
						if(wardsList != null && wardsList.size() > 0)
						{
							userAddress.setWard(wardsList.get(0));
						}
					}
					
					List<Hamlet> hamletWardList = userVoterDetailsDAO.getHamletByVoterId(voterId);
					if(hamletWardList != null && hamletWardList.size() > 0)
					{
						userAddress.setHamlet(hamletWardList.get(0));
					}
					if(cadreRegistrationVO != null && cadreRegistrationVO.getStreet() != null && !cadreRegistrationVO.getStreet().equalsIgnoreCase("null") && cadreRegistrationVO.getStreet().trim().length() > 0)
					{
						userAddress.setStreet(cadreRegistrationVO.getStreet());
					}
					
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getVoterAddressDetails in CadreRegistrationService service", e);
		}
	}
	

	public List<VoterInfoVO> getSearchDetailsCadreRegistration(Long constituencyId, String seachType, String candidateName, String voterCardId, String houseNo,Long panchayatId,Long boothId,String isPresentCadre,Integer startIndex,Integer maxIndex)
	{
		String cadrePath="images/cadre_images/";
		//String voterPath="voter_images/"+constituencyId+"/Part";
		StringBuilder searchQuery = new StringBuilder();
		List<VoterInfoVO> returnList = null;
		List searchList = null;
		Long count = 0l;
		SimpleDateFormat format  = new SimpleDateFormat("yy-MM-dd");
		
		try {

			
			if(seachType.equalsIgnoreCase("voter"))
			{
				if(candidateName != null && candidateName.trim().length()>0)
				{
					searchQuery.append(" BPV.voter.name like '%"+candidateName+"%' and");
				}
				if(voterCardId != null  && voterCardId.trim().length()>0)
				{
					searchQuery.append("  BPV.voter.voterIDCardNo like '%"+voterCardId+"%' and");
				}
				if(houseNo != null  && houseNo.trim().length()>0)
				{
					searchQuery.append(" BPV.voter.houseNo like '%"+houseNo+"%' and" );
				}

				searchList = boothPublicationVoterDAO.getVotersDetailsForCadreRegistratiobByconstituencId(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID,searchQuery.toString(),panchayatId,boothId,isPresentCadre,startIndex,maxIndex);
				 count = boothPublicationVoterDAO.getVotersDetailsForCadreRegistratiobByconstituencIdCount(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID,searchQuery.toString(),panchayatId,boothId,isPresentCadre);
				
				List<Long> voterIds = new ArrayList<Long>();
				if(searchList != null && searchList.size()>0 )
				{
					returnList = new ArrayList<VoterInfoVO>();
					
					for (Object param : searchList)
					{
						
						Object[] voter = (Object[]) param;
						
						voterIds.add(voter[0] != null ? Long.valueOf(voter[0].toString().trim()):0L);
						
						VoterInfoVO vo = new VoterInfoVO();
						vo.setId(voter[0] != null ? Long.valueOf(voter[0].toString().trim()):0L);
						vo.setVoterId(voter[0] != null ? Long.valueOf(voter[0].toString().trim()):0L);
						vo.setName(voter[1] != null ? voter[1].toString().trim():"");
						vo.setRelativeName(voter[2] != null ? voter[2].toString().trim():"");
						vo.setAge(voter[3] != null ? voter[3].toString().trim():"");
						vo.setHouseNo(voter[4] != null ? voter[4].toString().trim():"");
						vo.setRelationType(voter[5] != null ? voter[5].toString().trim():"");
						vo.setGender(voter[6] != null ? voter[6].toString().trim():"");
						vo.setVoterCardNo(voter[7]!=null ?voter[7].toString().trim():"");
						vo.setImage(voter[9] != null ? IConstants.VOTER_IMG_FOLDER_PATH+"/"+voter[9].toString():"");
						vo.setIsRegistered("N");						
						returnList.add(vo);
					}
				}
				
				if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0") && voterIds.size()>0)
				{
					List<Long> tdpCadreVoterIds = tdpCadreDAO.getVoterDetailsByVoterIds(voterIds);
					
					if(tdpCadreVoterIds != null && tdpCadreVoterIds.size()>0)
					{
						for (Long voterId : tdpCadreVoterIds) 
						{
							VoterInfoVO voterVO = getmatchedVOByVoterId(returnList,voterId);
							if(voterVO != null)
							{
								voterVO.setIsRegistered("Y");
							}
						}
						
					}
				}
				
			}
			
			else if(seachType.equalsIgnoreCase("cadre"))
			{
				
				if(candidateName != null && candidateName.trim().length()>0)
				{
					searchQuery.append(" TC.firstname like '%"+candidateName+"%' and");
				}
				if(voterCardId != null  && voterCardId.trim().length()>0)
				{
					searchQuery.append("  TC.voter.voterIDCardNo like '%"+voterCardId+"%' and");
				}
				if(houseNo != null  && houseNo.trim().length()>0)
				{
					searchQuery.append("  TC.houseNo like '%"+houseNo+"%' and" );
				}

				
				searchList = tdpCadreDAO.getCadreDetailsForCadreRegistratiobByconstituencId(constituencyId, searchQuery.toString(), panchayatId, boothId, isPresentCadre,startIndex,maxIndex);
				 count = tdpCadreDAO.getCadreDetailsForCadreRegistratiobByconstituencIdCount(constituencyId, searchQuery.toString(), panchayatId, boothId, isPresentCadre);
				
				if(searchList != null && searchList.size()>0 )
				{
					returnList = new ArrayList<VoterInfoVO>();
					
					for (Object voter1 : searchList)
					{
						Object[] voter = (Object[]) voter1;
						
						VoterInfoVO vo = new VoterInfoVO();
						vo.setId(voter[0] != null ? Long.valueOf(voter[0].toString().trim()):0L);
						vo.setCadreId(voter[0] != null ? Long.valueOf(voter[0].toString().trim()):0L);
						vo.setName(voter[1] != null ? voter[1].toString().trim():" -- ");
						vo.setRelativeName(voter[2] != null ? voter[2].toString().trim():" -- ");
						//vo.setAge(voter[3] != null ? voter[3].toString().trim():" -- ");
						vo.setHouseNo(voter[6] != null ? voter[6].toString().trim():" -- ");
						vo.setGender(voter[5] != null ? voter[5].toString().trim():" -- ");
						vo.setRelationType(voter[9] != null ? voter[9].toString().trim():"");
						
						vo.setImage(voter[10]!=null ?cadrePath+voter[10].toString().trim():null);
						String dateOfBirth = 	voter[3] != null ? voter[3].toString().substring(0,10):" "	;
						
						if(dateOfBirth != null && dateOfBirth.trim().length()>0)
						{
							Calendar startDate = new GregorianCalendar();
							Calendar endDate = new GregorianCalendar();
							
							startDate.setTime(format.parse(dateOfBirth.trim()));
							
							endDate.setTime(new Date());

							int diffYear = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
							
							vo.setAge(String.valueOf(diffYear));
						}
						else if(voter[4] != null && voter[4].toString().trim().length()>0 )
						{
							vo.setAge(voter[4].toString());
						}
						
						if(voter[8] != null)
						{
							try {
								
								Voter voterEntity = voterDAO.getVoterByVoterID(voter[8]!= null? Long.valueOf(voter[8].toString()):0L);
								
								if(voterEntity != null)
								{
									vo.setName(voterEntity.getName() != null ? voterEntity.getName().toString():" -- ");
									vo.setRelativeName(voterEntity.getRelativeName() != null ? voterEntity.getRelativeName().toString():" -- ");
									vo.setAge(voterEntity.getAge() != null ? voterEntity.getAge().toString():"--");
									vo.setHouseNo(voterEntity.getHouseNo() != null ? voterEntity.getHouseNo().toString():" -- ");
									vo.setGender(voterEntity.getGender() != null ? voterEntity.getGender().toString():" -- ");
									vo.setRelationType(voterEntity.getRelationshipType() != null ? voterEntity.getRelationshipType().toString():" -- ");
									vo.setVoterId(voterEntity.getVoterId()!= null ? voterEntity.getVoterId():0L);
								}
							} catch (Exception e) {}
							
						}
						
						returnList.add(vo);
					}
				}
				
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getSearchDetailsCadreRegistration in CadreRegistrationService service", e);
		}
		if(returnList !=null && returnList.size() > 0){
			returnList.get(0).setCount(count);
		}
		return returnList;
	}
	
	public List<VoterInfoVO> getCandidateInfoBySearchCriteria(String searchType, Long candidateId,String staticContentLoc,String constituencyId,Long tdpMemberTypeId)
	{
		List<VoterInfoVO> returnList = null;
		VoterInfoVO vo = null;
		try {
			String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			Long voterId = 0L;
			String houseNo = null;
			List<VoterInfoVO> familyList = null;
			List<VoterInfoVO> existingFamilyInfo = null;
			List<GenericVO> existingParticipationDetails = null;
			List<GenericVO> existingrollsDetails = null;
			
			if(candidateId != null && candidateId != 0L)
			{
				if(searchType.equalsIgnoreCase("voter"))
				{
					Voter voter = voterDAO.get(candidateId);
					
					if(voter != null )
					{
						returnList = new ArrayList<VoterInfoVO>();
						vo = new VoterInfoVO();
						
						vo.setVoterId(voter.getVoterId());
						vo.setName(voter.getName() != null ? voter.getName():"");
						vo.setHouseNo(voter.getHouseNo() != null ? voter.getHouseNo().toString():"");
						vo.setRelativeName(voter.getRelativeName() != null ? voter.getRelativeName():"");
						vo.setRelationType(voter.getRelationshipType() != null ? voter.getRelationshipType():"");
						vo.setAge(voter.getAge() != null? voter.getAge().toString():"");
						vo.setDateOfBirth(voter.getDateOfBirth() != null ? voter.getDateOfBirth().toString():"");
						vo.setGender(voter.getGender());
						vo.setVoterCardNo(voter.getVoterIDCardNo() != null ? voter.getVoterIDCardNo() :"");
						if(constituencyId != null){
							List<String> partNos = boothPublicationVoterDAO.getPartNoForRTCRegistration(Long.valueOf(constituencyId), voter.getVoterId());
							if(voter.getVoterIDCardNo() != null && partNos.size() > 0 && partNos.get(0) != null){
								String filePath = staticContentLoc + IConstants.VOTER_IMG_FOLDER_PATH+pathSeperator+(voter.getImagePath() != null ? voter.getImagePath() : "");
								if(checkFileExistingOrNot(filePath)){
									vo.setVoterImagePresent(true);
									vo.setVoterImage(IConstants.VOTER_IMG_FOLDER_PATH+pathSeperator+(voter.getImagePath() != null ? voter.getImagePath() : ""));
								}
							}
					   }
						
						
						List<UserVoterDetails> voterList = userVoterDetailsDAO.getVoterDetailsByUserIdAndVoterId(voter.getVoterId(),1L);
						
						if(voterList != null && voterList.size()>0)
						{
							String mobile = null;
							for (UserVoterDetails userVoterDetails : voterList)
							{
								mobile = userVoterDetails.getMobileNo() != null ? userVoterDetails.getMobileNo():"";
								if(mobile != null && (mobile.trim().length()>=10 && mobile.trim().length() <=12))
									break;
							}
							vo.setMobileNo(mobile != null ? mobile:"");
						}
						
						voterId = voter.getVoterId();
						houseNo = voter.getHouseNo() != null ? voter.getHouseNo().toString():"";
						
						List<TdpCadre> tdpCadreList=null;
						if(tdpMemberTypeId==null){
							tdpCadreList = tdpCadreDAO.getNormalCadreDetailsByVoterId(voterId);
						}else if(tdpMemberTypeId != null){
							tdpCadreList = tdpCadreDAO.getAffliatedCadreByVoterIdAndMemberType(voterId,tdpMemberTypeId);
						}
						
						if(tdpCadreList != null && tdpCadreList.size()>0)
						{
							TdpCadre tdpCadre =  (TdpCadre) tdpCadreList.get(0);
							
							if(tdpCadre != null)
							{
								Long tdpCadreId = tdpCadre.getTdpCadreId();
								
								
								vo.setName(tdpCadre.getFirstname() != null ? tdpCadre.getFirstname():"");
								vo.setCandidateAadharNo(tdpCadre.getCadreAadherNo() != null ? tdpCadre.getCadreAadherNo() :"");
								vo.setFmlyVtrId(tdpCadre.getFamilyVoterId() != null ? tdpCadre.getFamilyVoterId():0L);
								vo.setNameType(tdpCadre.getNameType());
								vo.setEmailId(tdpCadre.getEmailId() != null ? tdpCadre.getEmailId():"");
								vo.setSchoolName(tdpCadre.getSchoolName() != null ? tdpCadre.getSchoolName():"");
								
								List<Object[]> familyVoterInfo = voterDAO.getVoterInfoByVoterId(tdpCadre.getFamilyVoterId());
								
								if(familyVoterInfo != null && familyVoterInfo.size()>0)
								{
									for (Object[] param : familyVoterInfo) 
									{
										vo.setFmlyVCardNo(param[0] != null ? param[0].toString():"");
									}
								}
								
								vo.setHouseNo(tdpCadre.getHouseNo() != null ? tdpCadre.getHouseNo().toString():"");
								vo.setRelativeName(tdpCadre.getRelativename() != null ? tdpCadre.getRelativename():"");
								vo.setRelationType(tdpCadre.getRelativeType() != null ? tdpCadre.getRelativeType():"");
								
								if(tdpCadre.getAge() != null)
								{
									vo.setAge(tdpCadre.getAge() != null? tdpCadre.getAge().toString():"");
								}
								else if(tdpCadre.getDateOfBirth() != null)
								{
									
									Date today = new Date();
									Date DOB = tdpCadre.getDateOfBirth();

									Calendar startCalendar = new GregorianCalendar();
									startCalendar.setTime(DOB);
									Calendar endCalendar = new GregorianCalendar();
									endCalendar.setTime(today);
	
									int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
									//int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
									
									vo.setAge(diffYear != 0 ? Long.valueOf(Integer.toString(diffYear)).toString():"");
								}
								
								if(tdpCadre.getGender() != null)
								{
									if(tdpCadre.getGender().equalsIgnoreCase("MALE") || tdpCadre.getGender().equalsIgnoreCase("M"))
									{
										vo.setGender("Male");
									}
									else if(tdpCadre.getGender().equalsIgnoreCase("FEMALE") || tdpCadre.getGender().equalsIgnoreCase("F"))
									{
										vo.setGender("Female");
									}
									
								}
								
								vo.setBlodGroupId(tdpCadre.getBloodGroup() != null ? tdpCadre.getBloodGroupId():0L);

								vo.setCasteId(tdpCadre.getCasteState() != null ? tdpCadre.getCasteState().getCasteStateId():0L);							
								vo.setCasteName(tdpCadre.getCasteState() != null ? tdpCadre.getCasteState().getCaste().getCasteName().toString():"");	
								vo.setDateOfBirth(tdpCadre.getDateOfBirth() != null ? new SimpleDateFormat("yyyy-MM-dd").format(tdpCadre.getDateOfBirth()):"");
								vo.setOccupationId(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupationId():0L);
								vo.setOccupation(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupation():"");
								
								vo.setEducation(tdpCadre.getEducationalQualifications() != null ? tdpCadre.getEducationalQualifications().getEduQualificationId().toString():"0");
								
								vo.setLocation(tdpCadre.getUserAddress() != null ? (tdpCadre.getUserAddress().getStreet() != null ?tdpCadre.getUserAddress().getStreet().toString():""):"");
								vo.setMobileNo(tdpCadre.getMobileNo() != null ? tdpCadre.getMobileNo():"");
								if(tdpCadre.getPreviousEnrollmentNo() != null){
									if(tdpCadre.getPreviousEnrollmentNo().toString().trim().length() > 8){
										vo.setMemberShipId(tdpCadre.getPreviousEnrollmentNo().toString().trim().substring(tdpCadre.getPreviousEnrollmentNo().toString().trim().length()-8));
									}else{
										vo.setMemberShipId(tdpCadre.getPreviousEnrollmentNo().toString());
									}
								}else{
									vo.setMemberShipId("");
								}
								//vo.setMemberShipId(tdpCadre.getPreviousEnrollmentNo() != null && tdpCadre.getPreviousEnrollmentNo().length()>4 ? tdpCadre.getPreviousEnrollmentNo().toString().substring(4):"");
								vo.setActiveDate(tdpCadre.getPartyMemberSince() != null ? new SimpleDateFormat("yyyy-MM-dd").format(tdpCadre.getPartyMemberSince()):"");
								if(tdpCadre.getIsRelative() != null && tdpCadre.getIsRelative().equalsIgnoreCase("Y")){
								  vo.setRelative("true");
								  vo.setRelationTypeId(tdpCadre.getRelationType() != null ? tdpCadre.getRelationType().getVoterRelationId() : 0l);
								}if(tdpMemberTypeId == null || tdpMemberTypeId.longValue()<2){
									existingFamilyInfo =  getExistingCadreFamilyInfo(tdpCadreId);
									existingParticipationDetails = getExistingCadreParticipationInfo(tdpCadreId);
									existingrollsDetails = getExistingRollsInfo(tdpCadreId);
								}
								vo.setAadharNo(tdpCadre.getAadheerNo() != null ? tdpCadre.getAadheerNo() : "");
								vo.setNomineeName(tdpCadre.getNomineeName() != null ? tdpCadre.getNomineeName() : "");
								vo.setNomineAge(tdpCadre.getNomineeAge() != null ? tdpCadre.getNomineeAge().toString() : "");
								vo.setVoterRelationId(tdpCadre.getVoterRelationId() != null ? tdpCadre.getVoterRelationId() : 0l);
								if(tdpCadre.getImage() != null && tdpCadre.getImage().trim().length() > 0){
								   vo.setImage("images/"+ IConstants.CADRE_IMAGES + "/" + tdpCadre.getImage());
								}
								if(tdpCadre.getNomineeGender() != null && tdpCadre.getNomineeGender().trim().equalsIgnoreCase("MALE"))
								{
									vo.setNomineeGender(1l);
								}
								else if(tdpCadre.getNomineeGender() != null && tdpCadre.getNomineeGender().trim().equalsIgnoreCase("FEMALE"))
								{
									vo.setNomineeGender(2l);
								}
								else
								{
									vo.setNomineeGender(0l);
								}
								
								//setting rtc affliated values
								if(tdpCadre.getIdCardNo() != null){
									vo.setEmployeeId(tdpCadre.getIdCardNo());
								}
								if(tdpCadre.getDesignationId() != null && tdpCadre.getDesignationId() > 0l){
									vo.setDesignationId(tdpCadre.getDesignationId());
								}
								if(tdpCadre.getTdpCadreLocation() != null && tdpCadre.getTdpCadreLocationId() != null && tdpCadre.getTdpCadreLocationId() > 0l){
									
									if(tdpCadre.getTdpCadreLocation().getRtcZoneId() != null && tdpCadre.getTdpCadreLocation().getRtcZoneId() > 0l){
										vo.setZoneId(tdpCadre.getTdpCadreLocation().getRtcZoneId());
										vo.setRegionsList(rtcUnionService.getRegionsOfZone(tdpCadre.getTdpCadreLocation().getRtcZoneId()));
									}
									
									if(tdpCadre.getTdpCadreLocation().getRtcRegionId() != null && tdpCadre.getTdpCadreLocation().getRtcRegionId() > 0l){
										vo.setRegionId(tdpCadre.getTdpCadreLocation().getRtcRegionId());
										vo.setDepotsList(rtcUnionService.getDepotsOfRegion(tdpCadre.getTdpCadreLocation().getRtcRegionId()));
									}
									if(tdpCadre.getTdpCadreLocation().getRtcDepotId() != null && tdpCadre.getTdpCadreLocation().getRtcDepotId() > 0l){
										vo.setDepotId(tdpCadre.getTdpCadreLocation().getRtcDepotId());
									}
								} 
								
								if(tdpCadre.getUserAddress().getStreet() != null && tdpCadre.getUserAddress().getStreet().trim() != ""){
									vo.setStreet(tdpCadre.getUserAddress().getStreet());
								}
								
								if(tdpCadre.getUserAddress().getAddressLane1() != null && !tdpCadre.getUserAddress().getAddressLane1().isEmpty()){
									vo.setLandmark(tdpCadre.getUserAddress().getAddressLane1());
								}
								if(tdpCadre.getUserAddress().getPinCode() != null && !tdpCadre.getUserAddress().getPinCode().isEmpty()){
									vo.setPincode(tdpCadre.getUserAddress().getPinCode());
								}
								
							}
						}
						
					}
				}
				else if(searchType.equalsIgnoreCase("cadre"))
				{
					
					TdpCadre tdpCadre = tdpCadreDAO.get(candidateId);
					
					if(tdpCadre != null )
					{
						returnList = new ArrayList<VoterInfoVO>();
						vo = new VoterInfoVO();
						try {
							Long tdpCadreId = tdpCadre.getTdpCadreId();
							
							
							
							vo.setNameType(tdpCadre.getNameType());
							vo.setCadreId(tdpCadre.getTdpCadreId());
							vo.setDateOfBirth(tdpCadre.getDateOfBirth() != null ? new SimpleDateFormat("yyyy-MM-dd").format(tdpCadre.getDateOfBirth()):"");
							vo.setName(tdpCadre.getFirstname() != null ? tdpCadre.getFirstname():"");
							vo.setRelativeName(tdpCadre.getRelativename() != null ? tdpCadre.getRelativename():"");
							vo.setRelationType(tdpCadre.getRelativeType() != null ? tdpCadre.getRelativeType():"");
							vo.setEmailId(tdpCadre.getEmailId() != null ? tdpCadre.getEmailId():"");
							vo.setSchoolName(tdpCadre.getSchoolName() != null ? tdpCadre.getSchoolName():"");
							
							if(tdpCadre.getGender() != null)
							{
								if(tdpCadre.getGender().equalsIgnoreCase("MALE") || tdpCadre.getGender().equalsIgnoreCase("M"))
								{
									vo.setGender("Male");
								}
								else if(tdpCadre.getGender().equalsIgnoreCase("FEMALE") || tdpCadre.getGender().equalsIgnoreCase("F"))
								{
									vo.setGender("Female");
								}
								
							}
							
							vo.setCandidateAadharNo(tdpCadre.getCadreAadherNo() != null ? tdpCadre.getCadreAadherNo() :"");
							vo.setFmlyVtrId(tdpCadre.getFamilyVoterId() != null ? tdpCadre.getFamilyVoterId():0L);
							
							List<Object[]> familyVoterInfo = voterDAO.getVoterInfoByVoterId(tdpCadre.getFamilyVoterId());
							
							if(familyVoterInfo != null && familyVoterInfo.size()>0)
							{
								for (Object[] param : familyVoterInfo) 
								{
									vo.setFmlyVCardNo(param[0] != null ? param[0].toString():"");
								}
							}
							
							vo.setHouseNo(tdpCadre.getHouseNo() != null ? tdpCadre.getHouseNo().toString():"");
						
							
							if(tdpCadre.getAge() != null)
							{
								vo.setAge(tdpCadre.getAge() != null? tdpCadre.getAge().toString():"");
							}
							else if(tdpCadre.getDateOfBirth() != null)
							{
								
								Date today = new Date();
								Date DOB = tdpCadre.getDateOfBirth();

								Calendar startCalendar = new GregorianCalendar();
								startCalendar.setTime(DOB);
								Calendar endCalendar = new GregorianCalendar();
								endCalendar.setTime(today);

								int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
								//int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
								
								vo.setAge(diffYear != 0 ? Long.valueOf(Integer.toString(diffYear)).toString():"");
								
									
							}
							
							vo.setBlodGroupId(tdpCadre.getBloodGroup() != null ? tdpCadre.getBloodGroupId():0L);
							vo.setCasteId(tdpCadre.getCasteStateId() != null ? tdpCadre.getCasteStateId():0L);							
							vo.setCasteName(tdpCadre.getCasteState() != null ? tdpCadre.getCasteState().getCaste().getCasteName().toString():"");	
							
							vo.setOccupationId(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupationId():0L);
							vo.setOccupation(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupation():"");
							
							vo.setEducation(tdpCadre.getEducationalQualifications() != null ? tdpCadre.getEducationalQualifications().getEduQualificationId().toString():"0");
							
							vo.setLocation(tdpCadre.getUserAddress() != null ? (tdpCadre.getUserAddress().getStreet() != null ?tdpCadre.getUserAddress().getStreet().toString():""):"");
							vo.setMobileNo(tdpCadre.getMobileNo() != null ? tdpCadre.getMobileNo():"");
							if(tdpCadre.getPreviousEnrollmentNo() != null){
								if(tdpCadre.getPreviousEnrollmentNo().toString().trim().length() > 8){
									vo.setMemberShipId(tdpCadre.getPreviousEnrollmentNo().toString().trim().substring(tdpCadre.getPreviousEnrollmentNo().toString().trim().length()-8));
								}else{
									vo.setMemberShipId(tdpCadre.getPreviousEnrollmentNo().toString());
								}
							}else{
								vo.setMemberShipId("");
							}
							//vo.setMemberShipId(tdpCadre.getPreviousEnrollmentNo() != null  && tdpCadre.getPreviousEnrollmentNo().length()>4 ? tdpCadre.getPreviousEnrollmentNo().toString().substring(4):"");
							vo.setActiveDate(tdpCadre.getPartyMemberSince() != null ? new SimpleDateFormat("yyyy-MM-dd").format(tdpCadre.getPartyMemberSince()):"");
							if(tdpCadre.getIsRelative() != null && tdpCadre.getIsRelative().equalsIgnoreCase("Y")){
							  vo.setRelative("true");
							  vo.setRelationTypeId(tdpCadre.getRelationType() != null ? tdpCadre.getRelationType().getVoterRelationId() : 0l);
							  
							  if(tdpMemberTypeId != null){
								  vo.setRelationTypeId(tdpCadre.getRelationTypeId() != null ? tdpCadre.getRelationTypeId(): 0l);
							  }
							}
														
							vo.setAadharNo(tdpCadre.getAadheerNo() != null ? tdpCadre.getAadheerNo() : "");
							vo.setNomineeName(tdpCadre.getNomineeName() != null ? tdpCadre.getNomineeName() : "");
							vo.setNomineAge(tdpCadre.getNomineeAge() != null ? tdpCadre.getNomineeAge().toString() : "");
							vo.setVoterRelationId(tdpCadre.getVoterRelationId() != null ? tdpCadre.getVoterRelationId() : 0l);
							vo.setTeluguRelativeName(tdpCadre.getNameLocal() != null? tdpCadre.getNameLocal():"");
							if(tdpCadre.getImage() != null && tdpCadre.getImage().trim().length() > 0){
								if(tdpCadre.getEnrollmentYear() != null && tdpCadre.getEnrollmentYear().longValue() == 2014l){
								   vo.setImage("images/"+ IConstants.CADRE_IMAGES + "/" + tdpCadre.getImage());
								}else{
									String filePath = staticContentLoc + "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator +tdpCadre.getImage();
									if(checkFileExistingOrNot(filePath)){
										vo.setCadreImagePresent(true);
										vo.setCadreImage("images/"  + IConstants.CADRE_IMAGES + "/" +tdpCadre.getImage());
									}
								}
							 }
							if(tdpCadre.getNomineeGender() != null && tdpCadre.getNomineeGender().trim().equalsIgnoreCase("MALE"))
							{
								vo.setNomineeGender(1l);
							}
							else if(tdpCadre.getNomineeGender() != null && tdpCadre.getNomineeGender().trim().equalsIgnoreCase("FEMALE"))
							{
								vo.setNomineeGender(2l);
							}
							else
							{
								vo.setNomineeGender(0l);
							}
							
							if(tdpCadre.getVoter() != null)									
							{
								vo.setVoterId(tdpCadre.getVoter() != null ? tdpCadre.getVoter().getVoterId(): 0L);
								vo.setVoterCardNo(tdpCadre.getVoter() != null ? tdpCadre.getVoter().getVoterIDCardNo():"");
								
								voterId = tdpCadre.getVoter() != null ? tdpCadre.getVoter().getVoterId(): 0L;
								houseNo = tdpCadre.getVoter().getHouseNo() != null ? tdpCadre.getVoter().getHouseNo().toString():"";	
								
								if(tdpCadre.getPreviousEnrollmentNo() != null){
									String filePath = staticContentLoc + "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + tdpCadre.getPreviousEnrollmentNo()+".jpg";
									if(checkFileExistingOrNot(filePath)){
										vo.setCadreImagePresent(true);
										vo.setCadreImage("images/"  + IConstants.CADRE_IMAGES + "/" + tdpCadre.getPreviousEnrollmentNo()+".jpg");
									}
								}
								Voter voter = tdpCadre.getVoter();
								//Long constiId = tdpCadre.getUserAddress().getConstituency().getConstituencyId();
								if(voter != null){
									//List<String> partNos = boothPublicationVoterDAO.getPartNo(constiId, voter.getVoterId());
									//if(partNos.size() > 0 && partNos.get(0) != null && voter.getVoterIDCardNo() != null){
										String filePath = staticContentLoc +IConstants.VOTER_IMG_FOLDER_PATH+pathSeperator+(voter.getImagePath() != null ? voter.getImagePath() : "");
										if(checkFileExistingOrNot(filePath)){
											vo.setVoterImagePresent(true);
											vo.setVoterImage(IConstants.VOTER_IMG_FOLDER_PATH+pathSeperator+(voter.getImagePath() != null ? voter.getImagePath() : ""));
										//}
									}
							   }
							}
							
							
							if(tdpCadre.getVoter() == null && tdpCadre.getVoterId() != null && tdpMemberTypeId != null ){
								
								List<Object[]> voterIdAndCardNo = voterDAO.getVoterCardNoByVoterId(tdpCadre.getVoterId());
								if(voterIdAndCardNo!= null && voterIdAndCardNo.size()>0){
									Object[] obj = voterIdAndCardNo.get(0);
									vo.setVoterCardNo(obj[1]!=null ? obj[1].toString() :"");
								}
							}
							
							
							
							if(tdpCadre.getUserAddress().getStreet() != null && tdpCadre.getUserAddress().getStreet().trim() != ""){
								vo.setStreet(tdpCadre.getUserAddress().getStreet());
							}
							
							if(tdpCadre.getUserAddress().getAddressLane1() != null && !tdpCadre.getUserAddress().getAddressLane1().isEmpty()){
								vo.setLandmark(tdpCadre.getUserAddress().getAddressLane1());
							}
							if(tdpCadre.getUserAddress().getPinCode() != null && !tdpCadre.getUserAddress().getPinCode().isEmpty()){
								vo.setPincode(tdpCadre.getUserAddress().getPinCode());
							}
							existingFamilyInfo =  getExistingCadreFamilyInfo(tdpCadreId);
							existingParticipationDetails = getExistingCadreParticipationInfo(tdpCadreId);
							existingrollsDetails = getExistingRollsInfo(tdpCadreId);
							
							if(voterId==null || voterId == 0l){
								List<String> names= tdpCadreTeluguNamesDAO.getTeluguVoterNameByTdpCadreId(tdpCadreId);
								if(names!=null && names.size()>0){
									vo.setTeluguName(names.get(0));
								}
							}
							
							//setting rtc affliated values
							if(tdpCadre.getIdCardNo() != null){
								vo.setEmployeeId(tdpCadre.getIdCardNo());
							}
							if(tdpCadre.getDesignationId() != null && tdpCadre.getDesignationId() > 0l){
								vo.setDesignationId(tdpCadre.getDesignationId());
							}
							if(tdpCadre.getTdpCadreLocationId() != null && tdpCadre.getTdpCadreLocationId() > 0l){
								
								if(tdpCadre.getTdpCadreLocation().getRtcZoneId() != null && tdpCadre.getTdpCadreLocation().getRtcZoneId() > 0l){
									vo.setZoneId(tdpCadre.getTdpCadreLocation().getRtcZoneId());
									vo.setRegionsList(rtcUnionService.getRegionsOfZone(tdpCadre.getTdpCadreLocation().getRtcZoneId()));
								}
								
								if(tdpCadre.getTdpCadreLocation().getRtcRegionId() != null && tdpCadre.getTdpCadreLocation().getRtcRegionId() > 0l){
									vo.setRegionId(tdpCadre.getTdpCadreLocation().getRtcRegionId());
									vo.setDepotsList(rtcUnionService.getDepotsOfRegion(tdpCadre.getTdpCadreLocation().getRtcRegionId()));
								}
								if(tdpCadre.getTdpCadreLocation().getRtcDepotId() != null && tdpCadre.getTdpCadreLocation().getRtcDepotId() > 0l){
									vo.setDepotId(tdpCadre.getTdpCadreLocation().getRtcDepotId());
								}
							} 

						} catch (Exception e) {
								LOG.error("Exception raised in getCandidateInfoBySearchCriteria in CadreRegistrationService service", e);
							}

					}
				}
				
				if(voterId != null && voterId.longValue() != 0L)
				{
					List<Long> votersList = new ArrayList<Long>();
					votersList.add(voterId);
                    List<VoterNames> voterNamesList = voterNamesDAO.gerVoterNamesObjByVoterId(voterId);
					
					if(voterNamesList != null && voterNamesList.size() > 0 && voterNamesList.get(0) != null)
					{
						VoterNames voterNames = voterNamesList.get(0);
						String voterName ="";
						if(voterNames.getFirstName() != null){
							voterName = voterNames.getFirstName();
						}
						if(voterNames.getLastName() != null){
							voterName = voterName+" "+voterNames.getLastName();
						}
						vo.setTeluguName(voterName);
					}
					List<Object[]> voterInfo = boothPublicationVoterDAO.getBoothIdsDetailsOfVoterIds(votersList, IConstants.AFFILIATED_VOTER_PUBLICATION_ID);
					if(tdpMemberTypeId == null || tdpMemberTypeId.longValue()<2){
					if(voterInfo != null && voterInfo.size()>0)
					{
						Object boothPublicationVoter = voterInfo.get(0);
						Object[] boothInfo = (Object[]) boothPublicationVoter;
						
						Long boothId = boothInfo[2] != null ?Long.valueOf(boothInfo[2].toString()):0L;
						
						familyList = new ArrayList<VoterInfoVO>();

						if(existingFamilyInfo != null && existingFamilyInfo.size()>0)
						{
							for (VoterInfoVO family : existingFamilyInfo) 
							{
								
								if(family.getVoterId() == null || family.getVoterId().longValue() != voterId.longValue())
								{
									VoterInfoVO fmilyVO = new VoterInfoVO();
									fmilyVO.setVoterId(family.getVoterId() != null ? family.getVoterId():0L);
									fmilyVO.setName(family.getName() != null? family.getName():"");	
									fmilyVO.setGender(family.getGender() != null ? family.getGender():"");
									fmilyVO.setAge(family.getAge() != null ? family.getAge():"");								
									fmilyVO.setVoterCardNo(family.getVoterCardNo() != null ?family.getVoterCardNo():"");											
									fmilyVO.setEducation(family.getEducation());
									fmilyVO.setOccupation(family.getOccupation());
									fmilyVO.setOccupationId(family.getOccupationId());
									
									familyList.add(fmilyVO);
								}
							}
						}
						else 
						{
							if((houseNo != null && houseNo.toString().trim().length()>0) && (boothId != null && boothId.longValue() >0L))
							{
								List<Object[]> familyInfo = boothPublicationVoterDAO.getFamilyDetaislByHouseNoAndBoothId(boothId,houseNo);
								
								if(familyInfo != null && familyInfo.size()>0)
								{
									for (Object[] family : familyInfo) 
									{
										Long familyVoterID = family[0] != null ? Long.valueOf(family[0].toString().trim()):0L;
										
										if( familyVoterID.longValue() != voterId.longValue())
										{
											VoterInfoVO fmilyVO = new VoterInfoVO();
											fmilyVO.setVoterId(family[0] != null ? Long.valueOf(family[0].toString().trim()):0L);
											fmilyVO.setName(family[1] != null ? family[1].toString():"");
											//fmilyVO.setRelativeName(family[2] != null ? family[2].toString():"");
											//fmilyVO.setRelationType(family[3] != null ? family[3].toString():"");
											fmilyVO.setGender(family[4] != null ? family[4].toString():"");
											fmilyVO.setAge(family[5] != null ? family[5].toString():"");								
											fmilyVO.setVoterCardNo(family[6] != null ? family[6].toString():"");
											/*
											if(existingFamilyInfo != null && existingFamilyInfo.size()>0)
											{
												VoterInfoVO existingFamilyMember = getmatchedVOByVoterId(existingFamilyInfo,family[0] != null ? Long.valueOf(family[0].toString().trim()):0L);
												
												if(existingFamilyMember != null)
												{
													fmilyVO.setEducation(existingFamilyMember.getEducation());
													fmilyVO.setOccupation(existingFamilyMember.getOccupation());
													fmilyVO.setOccupationId(existingFamilyMember.getOccupationId());
													existingFamilyInfo.remove(existingFamilyMember);
												}										
											}
											*/
											familyList.add(fmilyVO);
										}
									}
								}
							}
						}
						vo.setVoterInfoVOList(familyList);
					} 
					}
				}
			}
			else
			{
				returnList = new ArrayList<VoterInfoVO>();
				vo = new VoterInfoVO();
				vo.setCadreId(0L);
				vo.setDateOfBirth("");	
				vo.setHouseNo("");
				vo.setName("");
				vo.setRelativeName("");
				vo.setRelationType("");
				vo.setAge("");
				vo.setGender(null);						
				vo.setVoterId(0L);
				vo.setVoterCardNo("");
				vo.setCandidateAadharNo("");
				vo.setFmlyVtrId(0L);
				vo.setFmlyVCardNo("");
				vo.setBlodGroupId(0L);						
				vo.setCasteId(0L);							
				vo.setCasteName("");						
				vo.setOccupationId(0L);
				vo.setOccupation("");						
				vo.setEducation("0");						
				vo.setLocation("");
				vo.setMobileNo("");
				vo.setEmailId("");
				vo.setMemberShipId("");
				vo.setActiveDate("");
				vo.setAadharNo("");
				vo.setNomineeName("");
				vo.setVoterRelationId(0L);
				vo.setNomineAge("");
				vo.setNomineeGender(0L);



			}
			
			List<Object[]> castesList = casteStateDAO.getAllCasteDetailsForVoters(1L); // for AP state
			
			List<GenericVO> stateCasteList = new ArrayList<GenericVO>();
			
			if(castesList != null && castesList.size()>0){
				for (Object[] cast : castesList) {
					GenericVO castevo = new  GenericVO();
					castevo.setId(cast[0] != null ? (Long) cast[0]:0L);
					castevo.setName(cast[1] != null ? cast[1].toString():"");
					
					stateCasteList.add(castevo);
				}
				
				vo.setGenericVOList(stateCasteList);			
			}
			
			List<SelectOptionVO> bloodGroups = null;
			
			List<BloodGroup> list = bloodGroupDAO.getAll();
			
			if(list != null && list.size() > 0)
			{
				bloodGroups = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				for(BloodGroup bloodGroup : list)
				{
					selectOptionVO = new SelectOptionVO(bloodGroup.getBloodGroupId(),
							bloodGroup.getBloodGroup());
					bloodGroups.add(selectOptionVO);
				}
				
				vo.setSelectOptionVOList(bloodGroups);
			}
			
			vo.setPreviousParticipationInfoList(existingParticipationDetails);
			vo.setCadreRolesList(existingrollsDetails);
			
			
			returnList.add(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getCandidateInfoBySearchCriteria in CadreRegistrationService service", e);
		}
		if(vo.getVoterId() == null){
			vo.setVoterId(0l);
		}
		return returnList;
	}
	
	public List<BasicVO> constituencyListForElection(Long electionId,Long constituencyId)
	{
		List<BasicVO> returnList = new ArrayList<BasicVO>();
		try {
			//Election election = electionDAO.get(electionId);
			
			String electionType  = null;
			List<String> electionTypeList = electionDAO.getElectionTypeByElectionId(electionId);
			if(electionTypeList != null && electionTypeList.size() > 0){
				electionType = electionTypeList.get(0);
			}
			
			List<Object[]> constituencyList = null;
			if(electionType != null )
			{
				//String electionType = election.getElectionScope().getElectionType().getElectionType();
				
				if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
				{
					constituencyList = constituencyElectionDAO.getConstituenciesByElectionId(electionId);
				}
				else if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE) || electionType.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE))
				{
					constituencyList = constituencyElectionDAO.getConstituenciesByElectionIdForMuncipal(electionId,constituencyId);
				}
				else 
				{
					constituencyList = constituencyElectionDAO.getConstituenciesByElectionIdForPanchayat(electionId,constituencyId);
				}
			}
			
			/*List<Long> constiIds = new ArrayList<Long>();
			
			if(constituencyList != null && constituencyList.size() > 0)
			{
				for (Object[] objects : constituencyList)
				{
					if(objects[0] != null) 
					{
						Constituency constituency = constituencyDAO.get((Long)objects[0]);
						
						if(constituency.getLocalElectionBody() != null)
						{
							if(!constiIds.contains(constituency.getLocalElectionBody().getLocalElectionBodyId()))
							{
								constiIds.add(constituency.getLocalElectionBody().getLocalElectionBodyId());
								
								BasicVO basicVO = new BasicVO();
								basicVO.setId(constituency.getLocalElectionBody().getLocalElectionBodyId());
								basicVO.setName(constituency.getLocalElectionBody().getName() +" "+constituency.getLocalElectionBody().getElectionType().getElectionType());
								returnList.add(basicVO);
							}
						}							
						else if(constituency.getState().getStateId() == 1L)
						{
							BasicVO basicVO = new BasicVO();
							basicVO.setId(constituency.getConstituencyId());
							basicVO.setName(constituency.getName());
							returnList.add(basicVO);
						}
					}
				}
			}*/
			
			List<Long> reqConstIds = new ArrayList<Long>();
			if(constituencyList != null && constituencyList.size() > 0){
				for(Object[] obj : constituencyList){
					if(obj[0] != null){
						reqConstIds.add((Long)obj[0]);
					}
				}
			}
			
			Map<Long , Object[]> detailsMap = new HashMap<Long, Object[]>();
			int noOfRecords = 50;
			if(reqConstIds != null && reqConstIds.size() > 0){
				int totalCount =  reqConstIds.size();
				if(totalCount <=  noOfRecords)
		        {	
			    	List<Object[]> details= constituencyDAO.getConstLebDetailsByConstIds(reqConstIds);
			    	if(details != null && details.size() > 0){
			    		for(Object[] obj : details){
			    			if(obj[0] != null){
			    				detailsMap.put((Long)obj[0], obj);
			    			}
			    		}
			    	}
			    }else{
			    	int quotient = (int) (totalCount / noOfRecords);
			    	int checkCount = quotient + 1;
			    	for(int i=0 ; i<checkCount ; i++){
			    		
			    		int fromIndex = i * noOfRecords;
			    		int toIndex = 0;
			    		if(i == checkCount - 1){
			    			toIndex = fromIndex + (totalCount % noOfRecords );
			    		}else{
			    			toIndex = fromIndex + noOfRecords;
			    		}
			    		
			    		//System.out.println(fromIndex + " - " +toIndex );
			    		List<Long> sublist = reqConstIds.subList(fromIndex, toIndex);
			    		if(sublist != null && sublist.size() > 0){
			    			List<Object[]> details= constituencyDAO.getConstLebDetailsByConstIds(sublist);
			    			if(details != null && details.size() > 0){
					    		for(Object[] obj : details){
					    			if(obj[0] != null){
					    				detailsMap.put((Long)obj[0], obj);
					    			}
					    		}
					    	}
		    			}
			    	}
			    }
			}
			
			
			List<Long> constiIds = new ArrayList<Long>();
			if(detailsMap != null && detailsMap.size() > 0){
				for( Map.Entry<Long, Object[]>   detailsEntry: detailsMap.entrySet() ){
					Object[] details = detailsEntry.getValue();
					if(details != null){
						
						if(details[2] != null){//lebId
							Long lebId = (Long)details[2];
							if(!constiIds.contains(lebId))
							{
								constiIds.add(lebId);
								
								BasicVO basicVO = new BasicVO();
								basicVO.setId(lebId);
								basicVO.setName(details[3].toString() +" "+details[5].toString());
								returnList.add(basicVO);
							}
						}else if(details[6] != null && ((Long)details[6]).longValue() == 1l){
						   
								BasicVO basicVO = new BasicVO();
								basicVO.setId((Long)details[0]);
								basicVO.setName(details[1]!=null ? details[1].toString() :"");
								returnList.add(basicVO);
						 }
				     }
				}
			}
			
			Collections.sort(returnList, new Comparator<BasicVO>() {
				public int compare(BasicVO o1, BasicVO o2)
				{
				return o1.getName().compareTo(o2.getName());
				}

				
				}); 
		} catch (Exception e) {
			LOG.error("Exception raised in constituencyListForElection in CadreRegistrationService service", e);
		}
		
		return returnList;
	}
	
	public List<BasicVO> participatedCandList(Long electionId,Long constituencyId)
	{
		List<BasicVO> returnList = new ArrayList<BasicVO>();
		try {

			List<Object[]>	candidatesInfo = nominationDAO.getNominatedCandidateInfoForAConstituency(constituencyId,electionId);
			
			if(candidatesInfo != null && candidatesInfo.size() > 0)
			{
				for (Object[] objects : candidatesInfo)
				{
					if(objects[0] != null)
					{
						BasicVO basicVO = new BasicVO();
						basicVO.setId((Long)objects[0]);
						basicVO.setName(objects[1] != null ? objects[1].toString() : " - " +objects[2].toString());
						returnList.add(basicVO);
					}
					
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in constituencyListForElection in CadreRegistrationService service", e);
		}
		
		return returnList;
		
	}
	
	public List<GenericVO> getCandidateDetailsForElection(Long candidateId, Long electionId)
	{
		List<GenericVO> returnList = new ArrayList<GenericVO>();
		
		try {
			
			List<Object[]> participationInfo = nominationDAO.getCandidateResultsByCandidateInfo(candidateId,electionId);
			
			if(participationInfo != null && participationInfo.size()>0)
			{
				for (Object[] participation : participationInfo)
				{
					GenericVO vo = new GenericVO();
					vo.setId(participation[0] != null ? Long.valueOf(participation[0].toString().trim()):0L);		//electionScopeId
					vo.setCount(participation[1] != null ? Long.valueOf(participation[1].toString().trim()):0L);	// election Id
					vo.setRank(participation[2] != null ? Long.valueOf(participation[2].toString().trim()):0L);		// constituencyId
					vo.setName(participation[3] != null ?participation[3].toString().trim():"");					// party name
					vo.setPercent(participation[4] != null ?participation[4].toString().trim():"0L");				
					
					List<SelectOptionVO> electionTypeList = getElectionYearsByElectionType(vo.getId());
					List<GenericVO> electionsList = new ArrayList<GenericVO>();
					
					if(electionTypeList != null && electionTypeList.size()>0)
					{
						for (SelectOptionVO selectOptionVO : electionTypeList) 
						{
							GenericVO electionVo = new GenericVO();
							electionVo.setId(selectOptionVO.getId());
							electionVo.setName(selectOptionVO.getName());
							
							electionsList.add(electionVo);	
						}
						
						vo.setGenericVOList(electionsList);						
					}
					
					returnList.add(vo);
				}	
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getCandidateDetailsForElection in CadreRegistrationService service", e);
		}
		
		return returnList;
	}
	
	public List<SelectOptionVO> getCandidateInfoByNomination(Long electionId,Long nominationId)
	{
		List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		try {
			List<Object[]> candidateList  = nominationDAO.getCandidateResultsByCandidateInfo(nominationId,electionId);
			System.out.println(candidateList.size());
			
			if(candidateList != null && candidateList.size() > 0)
			{
				for (Object[] param : candidateList)
				{
					SelectOptionVO vo = new SelectOptionVO();
					
					vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L); // election type Id
					vo.setName(param[1] != null ? param[1].toString():"");  // election Type
					
					vo.setOrderId(param[2] != null ? Long.valueOf(param[2].toString()):0L);  // electionId 
					vo.setLocation(param[3] != null ? param[3].toString() +" ("+(param[4] != null ? param[4].toString():"")+")":""); // election Year
					
					vo.setMainAccountId(param[5] != null ? Long.valueOf(param[5].toString()):0L); //constituency Id
					vo.setPanchayatName(param[6] != null ? param[6].toString():""); //constituency Name
					
					vo.setPartno(param[7] != null ? param[7].toString():""); // partyname 
					vo.setMandalName(param[8] != null ? param[8].toString():""); // party Id
					
					returnList.add(vo);
					
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in constituencyListForElection in CadreRegistrationService service", e);
		}
		
		return returnList;
		
	}
	
	public List<GenericVO> getExistingCadreParticipationInfo(Long tdpCadreId)
	{
		List<GenericVO> returnList = new ArrayList<GenericVO>();
		
		try {			
			List<Object[]> participationInfo = cadreParticipatedElectionDAO.getPreviousParticipationInfoByTdpCadreId(tdpCadreId);
			Long electionScopeId = 0L;
			if(participationInfo != null && participationInfo.size()>0)
			{
				for (Object[] participation : participationInfo)
				{
					GenericVO vo = new GenericVO();
					if(participation[0] != null)
					{
						Election eleciton = (Election) participation[0];
						vo.setId(eleciton.getElectionScope().getElectionScopeId());		//electionScopeId
						vo.setDesc(eleciton.getElectionScope().getElectionType().getElectionType());
						
						vo.setCount(eleciton.getElectionId());
						vo.setName(eleciton.getElectionYear()+" ("+eleciton.getElecSubtype()+" )");
						
						electionScopeId = eleciton.getElectionScope().getElectionScopeId();
					}
					//vo.setId(participation[0] != null ? Long.valueOf(participation[0].toString().trim()):0L);		//electionScopeId
					//vo.setCount(participation[1] != null ? Long.valueOf(participation[1].toString().trim()):0L);	// election Id
					vo.setRank(participation[1] != null ? Long.valueOf(participation[1].toString().trim()):0L);		// constituencyId
					//vo.setNomineeAge(participation[3] != null ? Long.valueOf(participation[3].toString().trim()):0L); // candidate Id
					if(vo.getRank() != null && vo.getRank() != 0L)
					{
						Constituency constituency = constituencyDAO.get(vo.getRank());					
						vo.setCaste(constituency.getName());
					}
					
					if(participation[2] != null)
					{
						Candidate candidate = (Candidate) participation[2];
						vo.setNomineeAge(candidate.getCandidateId()); // candidate Id
					}
					
					List<BasicVO> constituencyList = constituencyListForElection(vo.getCount(),vo.getRank());
					
					if(constituencyList != null && constituencyList.size()>0)
					{
						vo.getBasicVO().setHamletCasteInfo(constituencyList);
					}
					
					List<BasicVO> participatedCandList = participatedCandList(vo.getCount(),vo.getRank());
					
					if(participatedCandList != null && participatedCandList.size()>0)
					{
							BasicVO returnVO = getMatchedVOByAge(participatedCandList, Long.valueOf(vo.getNomineeAge()));
							
							if(returnVO != null)
							{
								vo.setNomineeName(returnVO.getName());
							}
						vo.getBasicVO().setHamletVoterInfo(participatedCandList);
					}
					
					List<SelectOptionVO> electionTypeList = getElectionYearsByElectionType(electionScopeId);
					List<GenericVO> electionsList = new ArrayList<GenericVO>();
					
					if(electionTypeList != null && electionTypeList.size()>0)
					{
						for (SelectOptionVO selectOptionVO : electionTypeList) 
						{
							GenericVO electionVo = new GenericVO();
							electionVo.setId(selectOptionVO.getId());
							electionVo.setName(selectOptionVO.getName());
							
							electionsList.add(electionVo);	
						}
						
						vo.setGenericVOList(electionsList);						
						returnList.add(vo);
						
					}
					else
					{
						returnList.add(vo);
					}
					
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getExistingCadreParticipationInfo in CadreRegistrationService service", e);
		}
		
		return returnList;
	}
	
	public BasicVO getMatchedVOByAge(List<BasicVO> list, Long age)
	{
		BasicVO returnVO = null;
		try {
			for (BasicVO basicVO : list) 
			{
				if(basicVO.getId().longValue() == age)
				{
					return basicVO;	
				}
					
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getMatchedVOByAge in CadreRegistrationService service", e);
		}
		return returnVO;
	}
	public List<GenericVO> getExistingRollsInfo(Long tdpCadreId)
	{
		List<GenericVO> returnList = new ArrayList<GenericVO>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat yyMMddFt = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			
			List<Object[]> participationInfo = cadrePreviousRolesDAO.getexistingRolesForTdpCadreByTdpCadreId(tdpCadreId);
			
			if(participationInfo != null && participationInfo.size()>0)
			{
				for (Object[] participation : participationInfo)
				{
					GenericVO vo = new GenericVO();
					vo.setId(participation[0] != null ? Long.valueOf(participation[0].toString().trim()):0L);		//Committe Level Id
					vo.setCount(participation[1] != null ? Long.valueOf(participation[1].toString().trim()):0L);	// Committe Id
					
					List<BasicVO> committeeList = new ArrayList<BasicVO>();					
					committeeList.add(new BasicVO(0L," Select Committee "));
					
					List<BasicVO> rolesList =  new ArrayList<BasicVO>();
					rolesList.add(new BasicVO(0L," Select Role "));
					
					List<Object[]> results = cadreCommitteeRoleDAO.getCommitteeRolesByLevelId(vo.getId());
					
					if(results != null &&results.size()>0)
					{
						for(Object[] result:results)
						{
							BasicVO vo1 = new BasicVO();
							vo1.setId((Long)result[0]);
							vo1.setName(result[1].toString());
							committeeList.add(vo1);
						}
						
						vo.getBasicVO().setHamletCasteInfo(committeeList);
					}
					
					List<Object[]> results1 = cadreCommitteeRoleDAO.getCommitteeRolesByLevelIdAndCommitteeId(vo.getId(),vo.getCount());
					
					if(results1 != null && results1.size()>0)
					{
						for(Object[] result:results1)
						{
							BasicVO vo2 = new BasicVO();
							vo2.setId((Long)result[0]);
							vo2.setName(result[1].toString());
							rolesList.add(vo2);
						}
						vo.getBasicVO().setPanchayatVoterInfo(rolesList);
					}
					
					vo.setRank(participation[2] != null ? Long.valueOf(participation[2].toString().trim()):0L);	// Committe role id
					vo.setStartTime(participation[3] != null ?yyMMddFt.format(format1.parse(participation[3].toString())):"");		// from date
					vo.setEndTime(participation[4] != null ? yyMMddFt.format(format1.parse(participation[4].toString())):"");		// to date 
					
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getExistingCadreParticipationInfo in CadreRegistrationService service", e);
		}
		
		return returnList;
	}
	

	public VoterInfoVO getmatchedVOByVoterId(List<VoterInfoVO> existingFamilyList, Long voterId)
	{
		VoterInfoVO voterVO = null;
		try {
			if(existingFamilyList != null && existingFamilyList.size()>0)
			{
				for (VoterInfoVO voterInfoVO : existingFamilyList) 
				{
					if(voterInfoVO.getVoterId().longValue() == voterId.longValue())
					{
						return voterInfoVO;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getmatchedVOByVoterId in CadreRegistrationService service",e);
		}
		return voterVO;
	}
	
	public List<VoterInfoVO> getExistingCadreFamilyInfo(Long tdpCadreId)
	{
		List<VoterInfoVO> returnList = null;	
		
		try {
			    //0voterId,1educationId,2occupationId,3voterName,4age,5gender
				List<Object[]> cadreFamilyInfo = tdpCadreFamilyDetailsDAO.getCadreFamilyDetailsBytdpCadreId(tdpCadreId);

				if(cadreFamilyInfo != null && cadreFamilyInfo.size()>0)
				{
					returnList = new ArrayList<VoterInfoVO>();
					
					for (Object[] voter : cadreFamilyInfo) 
					{
						VoterInfoVO voterVO = new VoterInfoVO();
						
						voterVO.setName(voter[3] != null ?voter[3].toString():"");
						voterVO.setAge(voter[4] != null ?voter[4].toString():"");
						voterVO.setGender(voter[5] != null ?voter[5].toString():"");
						
						if((Long)voter[0] != null)
						{
							Voter voter1 = voterDAO.get((Long)voter[0]);
							voterVO.setVoterId(voter1 != null ? voter1.getVoterId():0L);
							if(voterVO.getName() == null || voterVO.getName().trim().length() == 0){
							     voterVO.setName(voter1.getName() != null ? voter1.getName():"");
							}
							if(voterVO.getAge() == null || voterVO.getAge().trim().length() == 0){
								voterVO.setAge(voter1.getAge() != null ? voter1.getAge().toString():"");
							}
							if(voterVO.getGender() == null || voterVO.getGender().trim().length() == 0){
								voterVO.setGender(voter1.getGender() != null ? voter1.getGender():"");
							}
							voterVO.setVoterCardNo(voter1.getVoterIDCardNo() != null ? voter1.getVoterIDCardNo():"");
						}
						voterVO.setEducation(voter[1] != null ? voter[1].toString().trim():"");
						voterVO.setOccupationId(voter[2] != null ? Long.valueOf(voter[2].toString().trim()):0L);
						
						
						String occupation = occupationDAO.getOccupationNameByOccupationId(voterVO.getOccupationId());
						voterVO.setOccupation(occupation != null ? occupation.trim():"");
						
						returnList.add(voterVO);
					}
				}

			return returnList;
			
		} catch (Exception e)
		{
			LOG.error("Exception raised in getExistingCadreFamilyInfo in CadreRegistrationService service",e);
			return null;
		}
		
	}

	public List<GenericVO> getConstiteuncyDetailsByConstiteuncy(Long constituencyId)
	{
		LOG.info("entered into getConstiteuncyDetailsByConstiteuncy in CadreRegistrationService service");
		List<GenericVO> returnList = null;
		try {
			Constituency constituency = constituencyDAO.get(constituencyId);
			if(constituency != null)
			{
				String araaType = constituency.getAreaType();
				
				List<Object[]> tehsilList = tehsilDAO.findTehsilsByConstituencyIdAndPublicationDateId(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID); // 11
				
				List<Long> tehsilIds = new ArrayList<Long>();
				returnList = new ArrayList<GenericVO>();
				
				if(tehsilList != null && tehsilList.size()>0)
				{
					for (Object[] param : tehsilList) 
					{
						tehsilIds.add(param[0] != null ? Long.valueOf(param[0].toString()):0L);
					}
				}
				
				if(araaType != null && araaType.equalsIgnoreCase("RURAL") || araaType.equalsIgnoreCase("RURAL-URBAN"))
				{					
					if(tehsilIds != null && tehsilIds.size()>0)
					{
						List<Object[]> panchyats = panchayatDAO.getAllPanchaytesInAConstituency(tehsilIds);
						
						if(panchyats != null && panchyats.size()>0)
						{
							for (Object[] param : panchyats) 
							{
								GenericVO vo = new GenericVO();
								
								vo.setId(param[0] != null ? Long.valueOf("1"+param[0].toString()):0L);
								vo.setName(param[1] != null ? param[1].toString():"");
								
								returnList.add(vo);

							}
						}
						
						List<Object[]> localelectinbody = boothDAO.getAllLocalBodies(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID);
						
						if(localelectinbody != null && localelectinbody.size()>0)
						{
							for (Object[] param : localelectinbody)
							{
								GenericVO vo = new GenericVO();
								
								vo.setId(param[0] != null ? Long.valueOf("2"+param[0].toString()):0L);
								vo.setName(param[1] != null ? param[1].toString():"");
								
								returnList.add(vo);
								
							}
						}
					}
					
					
				}
				else if(araaType != null && araaType.equalsIgnoreCase("URBAN"))
				{		
		 		if(constituency != null )
					{
							GenericVO vo = new GenericVO();
							
							vo.setId(Long.valueOf("3"+constituency.getConstituencyId()));
							vo.setName(constituency.getName());
							
							returnList.add(vo);
					}
					
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getConstiteuncyDetailsByConstiteuncy in CadreRegistrationService service", e);
		}
		return returnList;
		
	}
	
	public List<GenericVO> getBoothForPanchayats(Long constituencyId, Long locationId)
	{
		LOG.info("Exception raised in getBoothForPanchayats in CadreRegistrationService service");
		List<GenericVO> returnList = new ArrayList<GenericVO>();
		List<Object[]> locations = new ArrayList<Object[]>();
		try {
			
			if(locationId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				List<Long> ids = new ArrayList<Long>();
				ids.add(Long.valueOf(locationId.toString().substring(1)));
				locations = boothDAO.getAllBoothsInPanchayat(ids, IConstants.VOTER_DATA_PUBLICATION_ID);
			}else if(locationId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				List<Long> ids = new ArrayList<Long>();
				ids.add(Long.valueOf(locationId.toString().substring(1)));
				locations =  boothDAO.getAllBoothsInMuncipalities(ids, IConstants.VOTER_DATA_PUBLICATION_ID);
			}else if(locationId.toString().substring(0,1).trim().equalsIgnoreCase("3")){
				locations =  boothDAO.getAllBoothsInUrban(Long.valueOf(locationId.toString().substring(1)), IConstants.VOTER_DATA_PUBLICATION_ID);
			}
			for (Object[] param : locations)
			{
				GenericVO vo = new GenericVO();
				
				vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L);
				vo.setName(param[1] != null ? "Booth - "+param[1].toString() + "  ("+(param[2] != null? param[2].toString()+") ":""):"");
				
				returnList.add(vo);
				
			}
			/*Constituency constituency = constituencyDAO.get(constituencyId);
			if(constituency != null)
			{
				String araaType = constituency.getAreaType();

				List<Object[]> tehsilList = tehsilDAO.findTehsilsByConstituencyIdAndPublicationDateId(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID); // 11
				
				List<Long> tehsilIds = new ArrayList<Long>();
				returnList = new ArrayList<GenericVO>();
				
				if(tehsilList != null && tehsilList.size()>0)
				{
					for (Object[] param : tehsilList) 
					{
						tehsilIds.add(param[0] != null ? Long.valueOf(param[0].toString()):0L);
					}
				}
				
				
				if(araaType != null && araaType.equalsIgnoreCase("RURAL") || araaType.equalsIgnoreCase("RURAL-URBAN"))
				{	
					List<Object[]> localelectinbody = localElectionBodyDAO.getMuncipalitiesAndCorporationsInAConstituency(tehsilIds);
					Long localElectionBodyId = 0L;
					if(localelectinbody != null && localelectinbody.size()>0)
					{
						for (Object[] param : localelectinbody)
						{
							localElectionBodyId = param[0] != null ? Long.valueOf(param[0].toString()):0L;
						}
					}
					
					if(localElectionBodyId != null && localElectionBodyId.longValue() != 0L && localElectionBodyId == locationId)
					{
						List<Object[]> boothList = boothDAO.getAllBoothsInAMuncipality(localElectionBodyId, IConstants.VOTER_DATA_PUBLICATION_ID);
						
						if(boothList != null && boothList.size()>0)
						{
							for (Object[] param : boothList)
							{
								GenericVO vo = new GenericVO();
								
								vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L);
								vo.setName(param[1] != null ? "Booth - "+param[1].toString() + "  ("+(param[2] != null? param[2].toString()+") ":""):"");
								
								returnList.add(vo);
								
							}
						}
						
					}
					else
					{
						tehsilIds.clear();
						tehsilIds.add(locationId); // panchayathId
						
						List<Object[]> boothList = boothDAO.getBoothIdsByPanchayatIdsInAPublication(tehsilIds,  IConstants.VOTER_DATA_PUBLICATION_ID);
						
						if(boothList != null && boothList.size()>0)
						{
							for (Object[] param : boothList)
							{
								GenericVO vo = new GenericVO();
								
								vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L);
								vo.setName(param[2] != null ? "Booth - "+param[2].toString() + "  ("+(param[3] != null? param[3].toString()+") ":""):"");
								
								returnList.add(vo);
								
							}
						}
						
					}
				}
				else
				{
					List<Object[]> boothList = boothDAO.getAllBoothsInUrban(constituencyId, IConstants.VOTER_DATA_PUBLICATION_ID);
					
					if(boothList != null && boothList.size()>0)
					{
						for (Object[] param : boothList)
						{
							GenericVO vo = new GenericVO();
							
							vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L);
							vo.setName(param[1] != null ? "Booth - "+param[1].toString() + "  ("+(param[2] != null? param[2].toString()+") ":""):"");
							
							returnList.add(vo);
							
						}
					}
				}
			}*/
			
		} catch (Exception e) {
			LOG.error("Exception raised in getBoothForPanchayats in CadreRegistrationService service", e);
		}
		return returnList; 
	}
	
	public List<GenericVO> getBoothCoverdVillagesDetails(List<Long> boothIds)
	{
		LOG.info("Exception raised in getBoothForPanchayats in CadreRegistrationService service");
		List<GenericVO> returnList = null;
		try {
						
			List<Object[]> boothList = boothDAO.getBoothsByBoothIdsList(boothIds);
			
			if(boothList != null && boothList.size()>0)
			{
				returnList = new ArrayList<GenericVO>();
				
				for (Object[] param : boothList)
				{
					GenericVO vo = new GenericVO();
					
					vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L);
					vo.setName(param[2] != null ? param[2].toString():"");
					
					returnList.add(vo);
					
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getBoothCoverdVillagesDetails in CadreRegistrationService service", e);
		}
		
		return returnList;
	}
	
	
	public List<SelectOptionVO> getOptionDetailsForCadre()
	{
		List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		SelectOptionVO mainVO = new SelectOptionVO();
		
		try{
			List<Object[]> cadreLevelsList = cadreLevelDAO.getCadreLevelDetails();
			List<SelectOptionVO> cadreLevels = null;
			
			if(cadreLevelsList != null && cadreLevelsList.size()>0)
			{
				cadreLevels = new ArrayList<SelectOptionVO>();
				
				for (Object[] param : cadreLevelsList) 
				{
					SelectOptionVO vo = new SelectOptionVO();
					vo.setId(param[0] != null ? Long.valueOf(param[0].toString().trim()):0L);
					vo.setName(param[1] != null ? param[1].toString().trim():"");
					
					cadreLevels.add(vo);
				}
			}
			
			mainVO.setSelectOptionsList(cadreLevels);
			
			
			List<Object[]> designationList = partyDesignationDAO.getAllPartyDesignation();
			List<SelectOptionVO> designations = null;
			
			if(designationList != null && designationList.size()>0)
			{
				designations = new ArrayList<SelectOptionVO>();
				
				for (Object[] param : designationList) 
				{
					SelectOptionVO vo = new SelectOptionVO();
					vo.setId(param[0] != null ? Long.valueOf(param[0].toString().trim()):0L);
					vo.setName(param[1] != null ? param[1].toString().trim():"");
					
					designations.add(vo);
				}
			}
			
			mainVO.setSelectOptionsList1(designations);
			
		
			returnList.add(mainVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getOptionDetailsForCadre in CadreRegistrationService service", e);
		}
		
		return returnList;
		
	}
	
	
	
	public List<SelectOptionVO> getElectionOptionDetailsForCadre()
	{
		List<SelectOptionVO> returnList = null;

		try{
						
			List<ElectionType> electionTypeList = electionTypeDAO.getElectionTypeList();
	
			if(electionTypeList != null && electionTypeList.size()>0)
			{
				returnList = new ArrayList<SelectOptionVO>();
				
				for (ElectionType electionType : electionTypeList) 
				{
					SelectOptionVO vo = new SelectOptionVO();
					vo.setId(electionType.getElectionTypeId() != null ? electionType.getElectionTypeId():0L);
					vo.setName(electionType.getElectionType() != null ? electionType.getElectionType():"");
					
					returnList.add(vo);
				}
			}
			
		//	mainVO.setSelectOptionsList(electionTypes);
			
			

			/*List<Object[]> electionIdList = electionDAO.findElectionYearsForElectionTypeAndStateId(2L,1L);
			
			List<SelectOptionVO> electionList = null;
			
			if(electionIdList != null && electionIdList.size()>0)
			{
				electionList = new ArrayList<SelectOptionVO>();
				
				for (Object[] param : electionIdList) 
				{
					SelectOptionVO vo = new SelectOptionVO();
					vo.setId(param[0] != null ? Long.valueOf(param[0].toString().trim()):0L);
					vo.setName(param[1] != null ? param[1].toString().trim():"");
					
					electionList.add(vo);
				}
			}
			
			mainVO.setSelectOptionsList1(electionList);*/
			
			//returnList.add(mainVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getOptionDetailsForCadre in CadreRegistrationService service", e);
		}
		
		return returnList;
		
	}
	
	/**
	 * This Service is used for getting election yesrs by election type
	 * @param electionTypeId
	 * @return returnList
	 */
	public List<SelectOptionVO> getElectionYearsByElectionType(Long electionTypeId)
	{
		List<SelectOptionVO> returnList = null;
		try {
			
			@SuppressWarnings("unchecked")
			List<Object[]> electionYearsList = electionDAO.findElectionYearsForElectionTypeAndStateId(electionTypeId,1l);
			if(electionYearsList != null && electionYearsList.size() > 0)
			{
				returnList = new ArrayList<SelectOptionVO>();
				for (Object[] objects : electionYearsList)
				{
					if(objects[0] != null)
					{
						SelectOptionVO selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)objects[0]);
						selectOptionVO.setName(objects[1] != null ? objects[1].toString() +"("+ objects[2].toString() +")" : "");
						returnList.add(selectOptionVO);
					}
					
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getElectionYearsByElectionType in CadreRegistrationService service", e);
		}
		return returnList;
	}
	
	public List<GenericVO> getExistingCadreInfo(String candidateName,Long constituencyId,Long panchayatId,Long boothId,String isPresentCadre,String enrollmentNo){
 		LOG.info("Entered into getExistingCadreInfo method in CadreRegistrationService class");
 		List<GenericVO>  returnList = null;
 		try {
			
 			List<Object[]> cadreInfo = tdpCadreDAO.getexistringCadreInfoByLocation(candidateName,constituencyId,panchayatId,boothId,isPresentCadre,enrollmentNo);
				if(cadreInfo != null && cadreInfo.size()>0)
				{
					returnList = new ArrayList<GenericVO>();
					for (Object[] param : cadreInfo)
					{
						GenericVO vo = new GenericVO();
						
						vo.setName(param[0] != null ? param[0].toString().trim():"");
						vo.setDesc(param[1] != null ? param[1].toString().trim():"");
						if(param[2] != null){
							if(param[2].toString().trim().length() > 8){
								vo.setCaste(param[2].toString().trim().substring(param[2].toString().trim().length()-8));
							}else{
								vo.setCaste(param[2].toString());
							}
						}else{
							vo.setCaste("");
						}
						//vo.setCaste(param[2] != null ? param[2].toString().trim():"");
						
						vo.setId(param[3] != null ? Long.valueOf(param[3].toString().trim()):0L);
						
						returnList.add(vo);
					}
				}
				
		} catch (Exception e) {
			LOG.error("Entered into getExistingCadreInfo method in CadreRegistrationService class",e);
		}
 		
 		return returnList;
 	}
	public String sendSMS(String mobileNo,String message){

		HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
			Integer.parseInt("30000"));
	
		boolean isEnglish = true;
		
		PostMethod post = new PostMethod("http://smscountry.com/SMSCwebservice_Bulk.aspx");
		
		post.addParameter("User",IConstants.ADMIN_USERNAME_FOR_SMS);
		post.addParameter("passwd",IConstants.ADMIN_PASSWORD_FOR_SMS);
		//post.addParameter("sid",IConstants.ADMIN_SENDERID_FOR_SMS);
	    post.addParameter("mobilenumber", mobileNo);
		post.addParameter("message", message);
		post.addParameter("mtype", isEnglish ? "N" : "OL");
		post.addParameter("DR", "Y");
		
		/* PUSH the URL */
		try 
		{
			int statusCode = client.executeMethod(post);
			
			if (statusCode != HttpStatus.SC_OK) {
				LOG.error("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
				return "error";
			}
			else{
				return "success";
			}

		}catch (Exception e) {
				LOG.error("Exception rised in sending sms while cadre registration",e);
				return "exception";
		} finally {
				post.releaseConnection();
		}
		
	}
	
	public String getRandomNo(){
		String number ="";
		Random randomNum = new Random();
		number = number+randomNum.nextInt(99999999);
		if(number.length()<8){
			for(int i=number.length();i<6;i++){
				number="0"+number;
			}
		}
		return number;
	}
	
	public String getReferenceNo(String userId,String registrationType){
		if(userId.length()> 4){
			userId = userId.substring(0,4);
		}else if(userId.length() < 4){
			if(userId.length() == 1){
				userId ="000"+userId;
			}else if(userId.length() == 2){
				userId ="00"+userId;
			}else if(userId.length() == 3){
				userId ="0"+userId;
			}
		}
		String ref="TR-W-"+userId+"-";
		if(registrationType.equalsIgnoreCase("ONLINE")){
			ref="TR-O-"+userId+"-";
		}
		else if(registrationType.equalsIgnoreCase("TAB")){
			ref="TR-T-"+userId+"-";
		}
		
		return ref;
	}
	
	public String getUniueRefNo(String ref,String dataSource){
		String randomNo = null;
		
	    randomNo = ref+getRandomNo();
		
		return randomNo;
	}
	
	private  String sendSMSInTelugu1(String mobileNo,String message){
		
		try {
			if(!IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver")){
	        	return "error"; 
	        }
			HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
			client.getHttpConnectionManager().getParams().setConnectionTimeout(
				Integer.parseInt("30000"));
		
			boolean isEnglish = false;
			
			PostMethod post = new PostMethod("http://smscountry.com/SMSCwebservice_Bulk.aspx");
			
			post.addParameter("User",IConstants.ADMIN_USERNAME_FOR_SMS);
			post.addParameter("passwd",IConstants.ADMIN_PASSWORD_FOR_SMS);
			post.addParameter("sid",IConstants.ADMIN_SENDERID_FOR_SMS);
		    post.addParameter("mobilenumber", mobileNo);
			post.addParameter("message", message);
			post.addParameter("mtype", isEnglish ? "N" : "OL");
			post.addParameter("DR", "Y");
			
			/* PUSH the URL */
			try 
			{
				int statusCode = client.executeMethod(post);
				
				if (statusCode != HttpStatus.SC_OK) {
					LOG.error("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
					return "error";
				}
				else{
					return "success";
				}

			}catch (Exception e) {
					LOG.error("Exception rised in sendSMSInTelugu1 sms while cadre registration for "+mobileNo+"",e);
					return "exception";
			} finally {
					post.releaseConnection();
			}
		} catch (Exception e) {
			LOG.error("Exception Raised while Getting JobCode of SMS" + e);
		}
		return null;
	}
	
	
	public  String sendSMSInTelugu(String mobileNo,String msg){
		
		try {
			
			if(!IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver")){
	        	return "error"; 
	        }
			
			String postData="";
			String retval = "";
			//give all Parameters In String String User ="User_Name";
			String passwd = IConstants.ADMIN_PASSWORD_FOR_SMS;
			String mobilenumber = mobileNo;
			String message = msg;
			String sid = IConstants.ADMIN_SENDERID_FOR_SMS;
			String mtype = "OL";
			String DR = "Y";
			postData += "User=" + URLEncoder.encode(IConstants.ADMIN_USERNAME_FOR_SMS,"UTF-8") + "&passwd=" + passwd + "&mobilenumber=" + mobilenumber + "&message=" + URLEncoder.encode(message,"UTF-8") + "&sid=" + sid + "&mtype=" + mtype + "&DR=" + DR;
			URL url = new URL("http://smscountry.com/SMSCwebservice_Bulk.aspx");
			//URL url = new URL("http://smscountry.com/SMSCwebservice_Bulk.aspx");
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			// If You Are Behind The Proxy Server Set IP And PORT else Comment Below 4 Lines
			//Properties sysProps = System.getProperties();
			//sysProps.put("proxySet", "true");
			//sysProps.put("proxyHost", "Proxy Ip");
			//sysProps.put("proxyPort", "PORT");
			urlconnection.setRequestMethod("POST");
			urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			urlconnection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(urlconnection.getOutputStream());
			out.write(postData);
			out.close();
			BufferedReader in = new BufferedReader( new InputStreamReader(urlconnection.getInputStream()));
			String decodedString;
			while ((decodedString = in.readLine()) != null) {
			retval += decodedString;
			}
			in.close();
			//System.out.println(retval);
			return retval;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			LOG.error("Exception Raised while Getting JobCode of SMS" + e);
			return "00000";
		} 
	}


	public ResultStatus saveNewCadreSurveyUser(final Long userId, final String surveyUserName, final String  password, final String mobileNo)
	{
		ResultStatus status = new ResultStatus();
		try {			
			List<Long> existingUserIds = cadreSurveyUserDAO.getUserByUserNameAndPassword(surveyUserName, password);
			
			if(existingUserIds == null || existingUserIds.size() == 0)
			{
				transactionTemplate.execute(new TransactionCallbackWithoutResult()
				{
					public void doInTransactionWithoutResult(TransactionStatus status) 
					{
						DateUtilService dateUtilService = new DateUtilService();
						
							CadreSurveyUser cadreSurveyUser = new CadreSurveyUser();
							
							cadreSurveyUser.setUserName(surveyUserName);
							cadreSurveyUser.setPassword(password);
							cadreSurveyUser.setMobileNo(mobileNo);
							cadreSurveyUser.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							cadreSurveyUser.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							cadreSurveyUser.setIsDeleted("N");
							
							cadreSurveyUserDAO.save(cadreSurveyUser);
					}
				});
				
				status.setMessage("Cadre Survey User Created Successfully...");
				status.setResultCode(ResultCodeMapper.SUCCESS);
			}
			else
			{
				status.setMessage("Cadre Survey User already exist.");
				status.setResultCode(ResultCodeMapper.FAILURE);
			}
		} catch (Exception e) {
			status.setMessage(" Error occured while saving new Cadre Survey User details.");
			status.setResultCode(ResultCodeMapper.FAILURE);
			
			LOG.error("exception occured in saveNewCadreSurveyUser method in CadreRegistrationService class",e);
		}
		return status;
	}
	
	public List<GenericVO> getSurveyCadreUsersList()
	{
		LOG.info("Entered into getSurveyCadreUsersList method in CadreRegistrationService class");
		
		List<GenericVO> returnList = new ArrayList<GenericVO>();
		try {
			List<Long> assignedUsersIds = cadreSurveyUserAssignDetailsDAO.getCadreSurveyAssignUsersList();
			
			if(assignedUsersIds != null && assignedUsersIds.size()>0)
			{
				List<Object[]> notAssignedUsers = cadreSurveyUserDAO.getCadreSurveyUsersList(assignedUsersIds);
				
				if(notAssignedUsers != null && notAssignedUsers.size()>0)
				{
					for (Object[] user : notAssignedUsers)
					{
						GenericVO cadreSurveyVO = new GenericVO();
						cadreSurveyVO.setId(user[0] != null ? Long.valueOf(user[0].toString().trim()):0L);
						cadreSurveyVO.setName(user[1] != null ? user[1].toString().trim():"");
						
						returnList.add(cadreSurveyVO);
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("exception occured in getSurveyCadreUsersList method in CadreRegistrationService class",e);
		}
		return returnList;
	}	
	
	public List<SelectOptionVO> getSurveyCadreAssignedConstituencyList()
	{
		LOG.info("Entered into getSurveyCadreAssignedConstituencyList method in CadreRegistrationService class");
		List<SelectOptionVO> returnList = null;
		try {
			List<Object[]> assignedConstituencies = cadreSurveyUserAssignDetailsDAO.getCadreSurveyAssignConstituencyList();			
						
			if(assignedConstituencies != null && assignedConstituencies.size()>0)
			{
				returnList = new ArrayList<SelectOptionVO>();
				
				for (Object[] user : assignedConstituencies)
				{
					SelectOptionVO cadreSurveyVO = new SelectOptionVO();
					cadreSurveyVO.setId(user[0] != null ? Long.valueOf(user[0].toString().trim()):0L);
					cadreSurveyVO.setName(user[1] != null ? user[1].toString().trim():"");
					
					returnList.add(cadreSurveyVO);
				}
			}
		} catch (Exception e) {
			LOG.error("exception occured in getSurveyCadreAssignedConstituencyList method in CadreRegistrationService class",e);
		}
		return returnList;
	}
	
	public List<GenericVO> getSurveyCadreAssignedUsersList(Long constiteuncyId)
	{
		LOG.info("Entered into getSurveyCadreAssignedUsersList method in CadreRegistrationService class");
		List<GenericVO> returnList = null;
		try {
			List<Object[]> assignedUsers = cadreSurveyUserAssignDetailsDAO.getCadreSurveyAssignUsersListByConstituency(constiteuncyId);			
						
			if(assignedUsers != null && assignedUsers.size()>0)
			{
				returnList = new ArrayList<GenericVO>();
				
				for (Object[] user : assignedUsers)
				{
					GenericVO cadreSurveyVO = new GenericVO();
					cadreSurveyVO.setId(user[0] != null ? Long.valueOf(user[0].toString().trim()):0L);
					cadreSurveyVO.setName(user[1] != null ? user[1].toString().trim():"");
					
					returnList.add(cadreSurveyVO);
				}
			}
		} catch (Exception e) {
			LOG.error("exception occured in getSurveyCadreAssignedUsersList method in CadreRegistrationService class",e);
		}
		return returnList;
	}
	
	public ResultStatus releaseCadreSurveyUser(final Long cadreSurveyUserAssignedId)
	{
		ResultStatus status = new ResultStatus();
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult()
			{
				public void doInTransactionWithoutResult(TransactionStatus status) 
				{
					CadreSurveyUserAssignDetails cadreSurveyUserAssignDetails = cadreSurveyUserAssignDetailsDAO.get(cadreSurveyUserAssignedId);
					if(cadreSurveyUserAssignDetails != null)
					{
						cadreSurveyUserAssignDetails.setIsDeleted("Y");
						cadreSurveyUserAssignDetails.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
						
						cadreSurveyUserAssignDetailsDAO.save(cadreSurveyUserAssignDetails);
					}
				}
			});
			
			status.setMessage("Cadre Survey User released Successfully...");
			status.setResultCode(ResultCodeMapper.SUCCESS);
			
		} catch (Exception e) {
			status.setMessage(" Error occured while releasing Cadre Survey User .");
			status.setResultCode(ResultCodeMapper.FAILURE);
			
			LOG.error("exception occured in releaseCadreSurveyUser method in CadreRegistrationService class",e);
		}
		return status;
	}
	
	public String isTabAssignedAlready(String TabNo)
	{
		String status  = null;
		try {
			List<Long> existingUserIds = cadreSurveyUserAssignDetailsDAO.isTabAssignedAlready(TabNo);
			if(existingUserIds != null && existingUserIds.size()>0)
			{
				status ="Tab No already Assigned.";
			}
			else 
			{
				status ="Tab No available for Assign.";
			}
	} catch (Exception e) {
		LOG.error("exception occured in getSubRegionsInConstituency method in CadreRegistrationService class",e);
	}
	return status;
		
	}
	
	public ResultStatus assignUserForLocation(final Long surveyUserId, final Long levelId, final Long levelValue,final Long constituencyId,final String TabNo)
	{
		ResultStatus status = new ResultStatus();
		try {			
			List<Long> existingUserIds = cadreSurveyUserAssignDetailsDAO.checkIsAlreadyAssigned(surveyUserId, levelId,levelValue,constituencyId);
			List<Long> tabExistCount = cadreSurveyUserAssignDetailsDAO.isTabAssignedAlready(TabNo);
			
			if((existingUserIds == null || existingUserIds.size() == 0) && (tabExistCount == null || tabExistCount.size() == 0))
			{
				transactionTemplate.execute(new TransactionCallbackWithoutResult()
				{
					public void doInTransactionWithoutResult(TransactionStatus status) 
					{
						DateUtilService dateUtilService = new DateUtilService();
						
							CadreSurveyUserAssignDetails cadreSurveyUserAssignDetails = new CadreSurveyUserAssignDetails();
							
							cadreSurveyUserAssignDetails.setCadreSurveyUserId(surveyUserId);
							cadreSurveyUserAssignDetails.setConstituencyId(constituencyId);
							cadreSurveyUserAssignDetails.setLevelId(levelId);
							cadreSurveyUserAssignDetails.setLevelValue(levelValue);
							cadreSurveyUserAssignDetails.setTabNo(TabNo);
							
							cadreSurveyUserAssignDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							cadreSurveyUserAssignDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							cadreSurveyUserAssignDetails.setIsDeleted("N");
							
							cadreSurveyUserAssignDetailsDAO.save(cadreSurveyUserAssignDetails);
					}
				});
				
				status.setMessage("Cadre Survey User Assigned Successfully...");
				status.setResultCode(ResultCodeMapper.SUCCESS);
			}
			else if(existingUserIds != null && existingUserIds.size() > 0)
			{
				status.setMessage("Cadre Survey User already Assigned.");
				status.setResultCode(ResultCodeMapper.FAILURE);
			}
			else if(tabExistCount != null && tabExistCount.size() > 0)
			{
				status.setMessage(" Tab No already Assigned .");
				status.setResultCode(ResultCodeMapper.FAILURE);
			}
		} catch (Exception e) {
			status.setMessage(" Error occured while Assigning Cadre Survey User details.");
			status.setResultCode(ResultCodeMapper.FAILURE);
			
			LOG.error("exception occured in saveNewCadreSurveyUser method in CadreRegistrationService class",e);
		}
		return status;
	}
	
	
	public List<SelectOptionVO> getSubRegionsInConstituency(Long constituencyId, String scope) 
	{
		List<SelectOptionVO> subRegionsList = new ArrayList<SelectOptionVO>();
		try {
				if(scope != null && scope.equalsIgnoreCase(IConstants.CONST_TYPE_RURAL))
				{
					subRegionsList = regionServiceDataImp.getTehsilsInConstituency(constituencyId);
				} else if(scope != null && scope.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE))
				{
					subRegionsList = regionServiceDataImp.getLocalElectionBodiesByUrbanType(constituencyId, 5L);
				} 
				else if(scope != null && scope.equalsIgnoreCase(IConstants.CORPORATION_ELECTION_TYPE))	
				{
					subRegionsList = regionServiceDataImp.getLocalElectionBodiesByUrbanType(constituencyId, 6L);
				}
				
		} catch (Exception e) {
			LOG.error("exception occured in getSubRegionsInConstituency method in CadreRegistrationService class",e);
		}
		
		return subRegionsList;
	}
	
	public String  getUniCodeMessage(String message){
	        String returnMessage = "";
			for(int i=0;i<message.length();i++){
				String character = Integer.toHexString(message.charAt(i));
				if(character.length() == 1){
					returnMessage=returnMessage+"000"+character;
				}else if(character.length() == 2){
					returnMessage=returnMessage+"00"+character;
				}else if(character.length() == 3){
					returnMessage=returnMessage+"0"+character;
				}else{
					returnMessage=returnMessage+""+character;
				}
				
			}
		return returnMessage; 
	}
	
	private String getMemberShipNo(Long districtId,Long id){
		if(id.longValue() > 9999999l){
			id = id-7000000l;
		}
		/*String memberShipNo ="AP14";
		if(districtId != null && districtId.longValue() < 11l){
			memberShipNo = "TS14";
		}*/
		String randomNo = "0"+id;
		
		return randomNo;
	}
	
	public void uploadProfileImage(CadreRegistrationVO cadreRegistrationVO,String registrationType,TdpCadre tdpCadre){
		LOG.error("PHOTOTYPE: "+cadreRegistrationVO.getPhotoType());
		LOG.error("EnrollmentNumber: "+cadreRegistrationVO.getPreviousEnrollmentNumber());
		LOG.error("VoterId: "+tdpCadre.getVoterId());
		LOG.error("ConstituencyId: "+cadreRegistrationVO.getConstituencyId());
		if(cadreRegistrationVO.getPhotoType() != null && cadreRegistrationVO.getPhotoType().trim().equalsIgnoreCase("cadre")){
			LOG.error("1");
		  if(cadreRegistrationVO.getPreviousEnrollmentNumber() != null && cadreRegistrationVO.getPreviousEnrollmentNumber().trim().length() > 0){
			  LOG.error("2");
			  String reqImage = getCadreImage(cadreRegistrationVO.getPreviousEnrollmentNumber().trim());
			  if(reqImage != null){
				  LOG.error("3");
				  String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
				  String destinationPath = IConstants.STATIC_CONTENT_FOLDER_URL+"images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
				  String sourcePath =   IConstants.STATIC_CONTENT_FOLDER_URL+"images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + reqImage;
				  String status = copyFile(sourcePath,destinationPath);
				  LOG.error("CADRE: SP:"+sourcePath+" DP"+destinationPath);
				   if(status.equalsIgnoreCase("success")){
					   LOG.error("4");
					   tdpCadre.setImage(tdpCadre.getMemberShipNo()+".jpg");
					   LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
				   }else{
					   LOG.error("5");
					   if(tdpCadre.getVoterId() != null){
							Voter voter = voterDAO.get(tdpCadre.getVoterId());
							if(voter != null){
								//List<String> partNos = boothPublicationVoterDAO.getPartNo(Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()), voter.getVoterId());
								//if(partNos.size() > 0 && partNos.get(0) != null && voter.getVoterIDCardNo() != null){
								   sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +IConstants.VOTER_IMG_FOLDER_PATH+"/"+(voter.getImagePath() != null ? voter.getImagePath() : "");
								   LOG.error("CADRENOTVOTER: SP:"+sourcePath+" DP:"+destinationPath+" VOTERID: "+voter.getVoterId());
								    status = copyFile(sourcePath,destinationPath);
								    LOG.error("Status : "+status);
								   if(status.equalsIgnoreCase("success")){
									   tdpCadre.setImage(tdpCadre.getMemberShipNo()+".jpg");
									   LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
								   }
								//}
						   }
					  }
				   }
			  }else{
				  LOG.error("6");
				   if(tdpCadre.getVoterId() != null){
					   LOG.error("7");
						Voter voter = voterDAO.get(tdpCadre.getVoterId());
						 String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
						if(voter != null && cadreRegistrationVO.getConstituencyId() != null && Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()).longValue() > 0){
							List<String> partNos = boothPublicationVoterDAO.getPartNo(Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()), voter.getVoterId());
							LOG.error("partNos size : "+partNos.size());
							if(partNos.size() > 0 && partNos.get(0) != null && voter.getVoterIDCardNo() != null){
								 String destinationPath = IConstants.STATIC_CONTENT_FOLDER_URL+"images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
							   String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +IConstants.VOTER_IMG_FOLDER_PATH+pathSeperator+(voter.getImagePath() != null ? voter.getImagePath() : "");
							   LOG.error("CADRENOTVOTER: SP:"+sourcePath+" DP:"+destinationPath+" VOTERID: "+voter.getVoterId());
							    String status = copyFile(sourcePath,destinationPath);
							    LOG.error("Status : "+status);
							   if(status.equalsIgnoreCase("success")){
								   tdpCadre.setImage(tdpCadre.getMemberShipNo()+".jpg");
								   LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
							   }
							}
					   }
				  }
			   }
		  }else{
			  LOG.error("8");
			   if(tdpCadre.getVoterId() != null){
				   LOG.error("9");
					Voter voter = voterDAO.get(tdpCadre.getVoterId());
					 String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
					if(voter != null && cadreRegistrationVO.getConstituencyId() != null && Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()).longValue() > 0){
						List<String> partNos = boothPublicationVoterDAO.getPartNo(Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()), voter.getVoterId());
						LOG.error("partNos size : "+partNos.size());
						if(partNos.size() > 0 && partNos.get(0) != null && voter.getVoterIDCardNo() != null){
							 String destinationPath = IConstants.STATIC_CONTENT_FOLDER_URL+"images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
						   String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +IConstants.VOTER_IMG_FOLDER_PATH+"/"+(voter.getImagePath() != null ? voter.getImagePath() : "");
						   LOG.error("CADRENOTVOTER: SP:"+sourcePath+" DP:"+destinationPath+" VOTERID: "+voter.getVoterId());
						    String status = copyFile(sourcePath,destinationPath);
						    LOG.error("Status : "+status);
						   if(status.equalsIgnoreCase("success")){
							   tdpCadre.setImage(tdpCadre.getMemberShipNo()+".jpg");
							   LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
						   }
						}
				   }
			  }
		   }
		}else if(cadreRegistrationVO.getPhotoType() != null && cadreRegistrationVO.getPhotoType().trim().equalsIgnoreCase("voter") ){
			  LOG.error("10");
		  if(tdpCadre.getVoterId() != null){
			  LOG.error("11");
			    String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
				String destinationPath = IConstants.STATIC_CONTENT_FOLDER_URL+ "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
				Voter voter = voterDAO.get(tdpCadre.getVoterId());
				if(voter != null && cadreRegistrationVO.getConstituencyId() != null && Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()).longValue() > 0){
					  LOG.error("12");
					List<String> partNos = boothPublicationVoterDAO.getPartNo(Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()), voter.getVoterId());
					LOG.error("partNos size : "+partNos.size());
					if(partNos.size() > 0 && partNos.get(0) != null && voter.getVoterIDCardNo() != null){
					   String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +IConstants.VOTER_IMG_FOLDER_PATH+pathSeperator+(voter.getImagePath() != null ? voter.getImagePath() : "");
					   LOG.error("VOTER: SP:"+sourcePath+" DP:"+destinationPath+" VOTERID: "+voter.getVoterId());
					   String status = copyFile(sourcePath,destinationPath);
					   LOG.error("Status : "+status);
					   if(status.equalsIgnoreCase("success")){
						   tdpCadre.setImage(tdpCadre.getMemberShipNo()+".jpg");
						   LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
					   }
					}
			   }
		  }
		}else{		
			  LOG.error("13");
			if(cadreRegistrationVO.getUploadImage() != null && !cadreRegistrationVO.getUploadImage().toString().equalsIgnoreCase("null")){
				  LOG.error("14");
				 LOG.error("IMAGE: MS:"+tdpCadre.getMemberShipNo());
				String result = uploadCadreImage(tdpCadre.getMemberShipNo() , cadreRegistrationVO.getPath() , cadreRegistrationVO.getUploadImageContentType() , cadreRegistrationVO.getUploadImage());
				if(result != null){
					tdpCadre.setImage(tdpCadre.getMemberShipNo()+"."+cadreRegistrationVO.getUploadImageContentType().split("/")[1]);
				}
			}else if(cadreRegistrationVO.getAbsolutePath() != null && cadreRegistrationVO.getAbsolutePath().trim().length() > 0){
				try{
					String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
					String destinationPath = IConstants.STATIC_CONTENT_FOLDER_URL+ "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
                    boolean status = false;
					URL head1 = new URL(cadreRegistrationVO.getAbsolutePath()+".jpg");
					if(checkUrlExit(head1)){
						status = copyNewImg(head1,destinationPath);
					}
					URL head2 = new URL(cadreRegistrationVO.getAbsolutePath()+".JPG");
					if(!status && checkUrlExit(head2)){
						status = copyNewImg(head2,destinationPath);
					}
					URL head3 = new URL(cadreRegistrationVO.getAbsolutePath()+".jpeg");
					if(!status && checkUrlExit(head3)){
						status = copyNewImg(head3,destinationPath);
					}
					URL head4 = new URL(cadreRegistrationVO.getAbsolutePath()+".JPEG");
					if(!status && checkUrlExit(head4)){
						status = copyNewImg(head4,destinationPath);
					}
					URL head5 = new URL(cadreRegistrationVO.getAbsolutePath()+".png");
					if(!status && checkUrlExit(head5)){
						status = copyNewImg(head5,destinationPath);
					}
					URL head6 = new URL(cadreRegistrationVO.getAbsolutePath()+".PNG");
					if(!status && checkUrlExit(head6)){
						status = copyNewImg(head6,destinationPath);
					}
					if(status){
						tdpCadre.setImage(tdpCadre.getMemberShipNo()+".jpg");
						tdpCadre.setPhotoType("NEW");
					}
				}catch(Exception e){
					 LOG.error(e);
				}
			}
			else if(cadreRegistrationVO.getPhotoType() != null  && cadreRegistrationVO.getPhotoType().trim().equalsIgnoreCase("new")  && cadreRegistrationVO.getImageBase64String() != null && 
					cadreRegistrationVO.getImageBase64String().trim().length() > 0){
				  LOG.error("15");
					String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
					String filePath = IConstants.STATIC_CONTENT_FOLDER_URL + "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
					//String filePath = "D:/" + tdpCadre.getMemberShipNo()+".jpg";
					cadreRegistrationVO.setImageBase64String(cadreRegistrationVO.getImageBase64String().replace("_", "/"));
					cadreRegistrationVO.setImageBase64String(cadreRegistrationVO.getImageBase64String().replace("-", "+"));
					boolean status = imageAndStringConverter.convertBase64StringToImage(cadreRegistrationVO.getImageBase64String(),filePath);
					//System.out.println(cadreRegistrationVO.getImageBase64String());
					 LOG.error("BASE64: DP:"+filePath);
					 try{
					 if(cadreRegistrationVO.getImageBase64String().length() > 55){
					     LOG.error("BASE64FIRST50C: "+cadreRegistrationVO.getImageBase64String().substring(0, 50));
					 }else{
						 LOG.error("BASE64FIRST50C: "+cadreRegistrationVO.getImageBase64String());
					 }
					 }catch(Exception ex){
						 
					 }
					if(status){
						tdpCadre.setImage(tdpCadre.getMemberShipNo()+".jpg");
						LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
					}
			}else{
				  LOG.error("16");
				 if(tdpCadre.getVoterId() != null){
					  LOG.error("17");
					    String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
						String destinationPath = IConstants.STATIC_CONTENT_FOLDER_URL+ "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
						Voter voter = voterDAO.get(tdpCadre.getVoterId());
						if(voter != null && cadreRegistrationVO.getConstituencyId() != null && Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()).longValue() > 0){
							List<String> partNos = boothPublicationVoterDAO.getPartNo(Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()), voter.getVoterId());
							LOG.error("partNos size : "+partNos.size());
							if(partNos.size() > 0 && partNos.get(0) != null && voter.getVoterIDCardNo() != null){
							   String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +IConstants.VOTER_IMG_FOLDER_PATH+pathSeperator+(voter.getImagePath() != null ? voter.getImagePath() : "");
							   LOG.error("VOTERN: SP:"+sourcePath+" DP:"+destinationPath+" VOTERID: "+voter.getVoterId());
							   String status = copyFile(sourcePath,destinationPath);
							   LOG.error("Status : "+status);
							   if(status.equalsIgnoreCase("success")){
								   tdpCadre.setImage(tdpCadre.getMemberShipNo()+".jpg");
								   LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
							   }
							}
					   }
				  }
			}
		}
		
	}
	
	
	public boolean checkUrlExit(URL url) throws Exception{
		URLConnection connection = url.openConnection();
        connection.connect();
        HttpURLConnection httpConnection = (HttpURLConnection) connection;
        int code = httpConnection.getResponseCode();
        if(code == 200){
        	return true;
        }else{
        	return false;
        }
	}
	
	public boolean copyNewImg(URL head ,String destinationPath)  throws Exception{
		BufferedImage image1 = ImageIO.read(head.openStream());
    	if(image1!=null){
    		ImageIO.write(image1, "jpg",new File(destinationPath));
    		return true;
    	}
    	return false;
	}
	/**
	 * This Service is used for cadre print cadre details
	 * @param memberCardNo
	 * @author Prasad Thiragabathina
	 * @date 07-10-2014
	 * @return returnVO
	 * 
	 */
	public CadrePrintVO getCadreDetailsForPrinting(String memberCardNo)
	{
		CadrePrintVO returnVO = null;
		try {
			
			List<Object[]> voterIdDetails = tdpCadreDAO.getCadreDetailsByMemberId(memberCardNo);
			if(voterIdDetails != null && voterIdDetails.size() > 0)
			{
				Long voterId = (Long)voterIdDetails.get(0)[1];
				UserAddress userAddress = new UserAddress()	;
				returnVO = new CadrePrintVO();
				getVoterAddressDetails(voterId,userAddress,null);
				returnVO.setVillageName(userAddress.getPanchayat() != null ? StringEscapeUtils.unescapeJava(userAddress.getPanchayat().getLocalName()  )  + StringEscapeUtils.unescapeJava("\u0C17\u0C4D\u0C30\u0C3E\u0C2E\u0C02"): "");
				returnVO.setMandalName(userAddress.getTehsil() != null ?  StringEscapeUtils.unescapeJava(userAddress.getTehsil().getLocalName() ) + StringEscapeUtils.unescapeJava("\u0C2E\u0C02\u0C21\u0C32\u0C02"):"");
				returnVO.setConstituencyName(userAddress.getConstituency() != null ?  StringEscapeUtils.unescapeJava(userAddress.getConstituency().getLocalName() ) + StringEscapeUtils.unescapeJava("\u0C28\u0C3F") + "||" : "");
				returnVO.setDistrictName(userAddress.getDistrict() != null ?  StringEscapeUtils.unescapeJava(userAddress.getDistrict().getLocalName() ) + StringEscapeUtils.unescapeJava("\u0C1C\u0C3F\u0C32\u0C4D\u0C32\u0C3E"):"");
				returnVO.setFirstCode(voterIdDetails.get(0)[0] != null ? voterIdDetails.get(0)[0].toString() : "");
				returnVO.setVoterName(userAddress.getPanchayatId() != null ? panchayatDAO.get(userAddress.getPanchayatId()).getPanchayatName() : "");
				returnVO.setRelativeName(voterIdDetails.get(0)[3] != null ? voterIdDetails.get(0)[3].toString() : "");
				returnVO.setVoterId(voterIdDetails.get(0)[4] != null ? (Long)voterIdDetails.get(0)[4] : 0l);
				if(voterIdDetails.get(0)[5] != null){
					if(voterIdDetails.get(0)[5].toString().trim().length() > 8){
						returnVO.setVoterCardNo(voterIdDetails.get(0)[5].toString().trim().substring(voterIdDetails.get(0)[5].toString().trim().length()-8));
					}else{
						returnVO.setVoterCardNo(voterIdDetails.get(0)[5].toString());
					}
				}else{
					returnVO.setVoterCardNo("");
				}
				//returnVO.setVoterCardNo(voterIdDetails.get(0)[5] != null ? voterIdDetails.get(0)[5].toString() : "");
				returnVO.setVillageEng(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getPanchayatName() : "");
				returnVO.setMandalEng(userAddress.getTehsil() != null ?  userAddress.getTehsil().getTehsilName() :"");
				returnVO.setConstiEng(userAddress.getConstituency() != null ?  userAddress.getConstituency().getName()  : "");
				returnVO.setDistrictEng(userAddress.getDistrict() != null ?  userAddress.getDistrict().getDistrictName() :"");
				returnVO.setVoterImgPath(voterIdDetails.get(0)[6] != null ? "https://mytdp.com/"+IConstants.VOTER_IMG_FOLDER_PATH+"/"+voterIdDetails.get(0)[6] : "");
				if(userAddress.getConstituency() != null && userAddress.getBooth() != null)
				{
					//String url = "https://mytdp.com/voter_images/"+userAddress.getConstituency().getConstituencyId().toString().trim()+"/"+"Part"+userAddress.getBooth().getPartNo().trim()+"/"+returnVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";
					//returnVO.setVoterImgPath(url);
					List<String> names = voterNamesDAO.getVoterTeluguNames((Long)voterIdDetails.get(0)[4] );
					if(names != null && names.size() > 0)
					{
						returnVO.setVoterName(names.get(0));
						/*String name = "";
						if( names.get(0)[0] != null && names.get(0)[0] .toString().trim().length() > 0)
						{
							name = names.get(0)[0].toString() ;
							name = name +   "  " ;
						}
						if(names.get(0)[1] != null && names.get(0)[1] .toString().trim().length() > 0)
						{
							name = name +  names.get(0)[1].toString() ;
						}
						
						if(name.trim().length() > 0)
						//name = name.replaceAll(",", " ").replaceAll(".", " ");
*/						
					}
				}
				returnVO.setVillage(userAddress.getPanchayatId() != null ? panchayatDAO.get(userAddress.getPanchayatId()).getLocalName() : "");
				returnVO.setMandal(userAddress.getTehsil() != null ?  userAddress.getTehsil().getLocalName() :"");
				returnVO.setConstituency(userAddress.getConstituency() != null ?  userAddress.getConstituency().getLocalName() : "");
				returnVO.setConstituencyType(userAddress.getConstituency() != null ? userAddress.getConstituency().getAreaType() : "");
				returnVO.setDistrict(userAddress.getDistrict() != null ?  userAddress.getDistrict().getLocalName():"");
				returnVO.setMuncipalityName(userAddress.getLocalElectionBody() != null ? userAddress.getLocalElectionBody().getNameLocal() : "" );
				
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getCadreDetailsForPrinting in CadreRegistrationService service", e);
		}
		return returnVO;
	}
	
	
	/**
	 * This Service is used for getting panchayat wise cadre details for getting voter details
	 * @param panchayatId
	 * @author Prasad Thiragabathina
	 * @date 08-10-2014
	 * @return returnList
	 */
	public List<CadrePrintVO> getSelectedLevelCadreDetails(Long panchayatId)
	{
		List<CadrePrintVO> returnList = null;
		try {
		 List<Object[]> result = tdpCadreDAO.getPanchayatWiseCadreDetails(panchayatId);
		 if(result != null && result.size() > 0)
		 {
			 //String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			 returnList = new ArrayList<CadrePrintVO>();
			 Long count = 1l;
			 
			 for (Object[] objects : result)
			 {
				 CadrePrintVO cadrePrintVO = new CadrePrintVO();
				 	Long voterId = (Long)objects[1];
					UserAddress userAddress = new UserAddress()	;
					getVoterAddressDetails(voterId,userAddress,null);
					cadrePrintVO.setCadrePrintVOId(count);
					count++;
					cadrePrintVO.setVillageName(userAddress.getPanchayat() != null ? StringEscapeUtils.unescapeJava(userAddress.getPanchayat().getLocalName()  )  + StringEscapeUtils.unescapeJava("\u0C17\u0C4D\u0C30\u0C3E\u0C2E\u0C02"): "");
					cadrePrintVO.setMandalName(userAddress.getTehsil() != null ?  StringEscapeUtils.unescapeJava(userAddress.getTehsil().getLocalName() ) + StringEscapeUtils.unescapeJava("\u0C2E\u0C02\u0C21\u0C32\u0C02"):"");
					cadrePrintVO.setConstituencyName(userAddress.getConstituency() != null ?  StringEscapeUtils.unescapeJava(userAddress.getConstituency().getLocalName() ) + StringEscapeUtils.unescapeJava("\u0C28\u0C3F") + "||" : "");
					cadrePrintVO.setDistrictName(userAddress.getDistrict() != null ?  StringEscapeUtils.unescapeJava(userAddress.getDistrict().getLocalName() ) + StringEscapeUtils.unescapeJava("\u0C1C\u0C3F\u0C32\u0C4D\u0C32\u0C3E"):"");
					if(objects[0] != null){
						if(objects[0].toString().trim().length() > 8){
							cadrePrintVO.setFirstCode(objects[0].toString().trim().substring(objects[0].toString().trim().length()-8));
						}else{
							cadrePrintVO.setFirstCode(objects[0].toString());
						}
					}else{
						cadrePrintVO.setFirstCode("");
					}
					//cadrePrintVO.setFirstCode(objects[0] != null ? objects[0].toString() : "");
					cadrePrintVO.setVoterName(objects[2] != null ? objects[2].toString() : "");
					cadrePrintVO.setRelativeName(objects[3] != null ? objects[3].toString() : "");
					cadrePrintVO.setVoterId(objects[4] != null ? (Long)objects[4] : 0l);
					cadrePrintVO.setVoterCardNo(objects[5] != null ? objects[5].toString() : "");
					cadrePrintVO.setRefNumber(objects[6] != null ? objects[6].toString() : "");
					cadrePrintVO.setCardNumber(objects[7] != null ? objects[7].toString() : "");
					cadrePrintVO.setVillageEng(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getPanchayatName() : "");
					cadrePrintVO.setMandalEng(userAddress.getTehsil() != null ?  userAddress.getTehsil().getTehsilName() :"");
					cadrePrintVO.setConstiEng(userAddress.getConstituency() != null ?  userAddress.getConstituency().getName()  : "");
					cadrePrintVO.setDistrictEng(userAddress.getDistrict() != null ?  userAddress.getDistrict().getDistrictName() :"");
					cadrePrintVO.setRefNumber(objects[6] != null ? objects[6].toString() : "");
					cadrePrintVO.setCardNumber(objects[7] != null ? objects[7].toString() : "");
					cadrePrintVO.setImage(objects[8]!= null ? objects[8].toString() :  "");
					cadrePrintVO.setVoterImgPath(objects[9] != null ? "https://mytdp.com/"+IConstants.VOTER_IMG_FOLDER_PATH+"/"+objects[9].toString() : "");
					returnList.add(cadrePrintVO);
			 }
		 }
		} catch (Exception e) {
			LOG.error("Exception raised in getCadreDetailsForPrinting in CadreRegistrationService service", e);
		}
		return returnList;
	}
	
	/**
	 * This Service is used for tagging NFC Card reader details into our db  
	 * @param cardNumber
	 * @author Prasad Thiragabathina
	 * @date 08-10-2014
	 * @return
	 */
	public String tagCardIdForNFCReader(String cardNumber,Long voterId)
	{
		String status = "fail";
		try {
			
			List<String> checkForNumber =tdpCadreDAO.chechForCardNumber(cardNumber);
			if(checkForNumber.size() == 0)
			{
				Integer count = tdpCadreDAO.updateNFCCardNumberByVoterId(voterId,cardNumber);
				if(count != null && count.longValue() > 0)
				{
					status = "success";
				}
			}
			else
			{
				status = "CardAssigned";
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in tagCardIdForNFCReader in CadreRegistrationService service", e);
		}
		return status;
	}
	
	public List<SelectOptionVO> getCadreLevelsForCadreSearch(){
		LOG.debug("In getCadreLevelsForCadreSearch ");
		List<SelectOptionVO> finalList = new ArrayList<SelectOptionVO>();
		try{
			List<Object[]> cdrCmmtList = cadreCommitteeDAO.getAllCadreCommittees();
			List<Object[]> cdrCmmtLvlList = cadreCommitteeLevelDAO.getAllCadreCommitteeLevels();
			List<Object[]> cdrRlsList = cadreRolesDAO.getAllCadreRoles();
			
			SelectOptionVO finalVO1 = new SelectOptionVO();
			List<SelectOptionVO> tempList1= new ArrayList<SelectOptionVO>();
			tempList1.add(new SelectOptionVO(0L,"Select Committee"));
			
			SelectOptionVO finalVO2 = new SelectOptionVO();
			List<SelectOptionVO> tempList2= new ArrayList<SelectOptionVO>();
			tempList2.add(new SelectOptionVO(0L,"Select Level"));
			
			SelectOptionVO finalVO3 = new SelectOptionVO();
			List<SelectOptionVO> tempList3= new ArrayList<SelectOptionVO>();
			tempList3.add(new SelectOptionVO(0L,"Select Role"));
			
			if(cdrCmmtList!=null && cdrCmmtList.size()>0)
			{				
				for(Object[] temp:cdrCmmtList)
				{
					tempList1.add(new SelectOptionVO(Long.valueOf(temp[0].toString()),temp[1].toString()));
				}
			}
			
			finalVO1.setName("CadreCommitteeList");
			finalVO1.setSelectOptionsList(tempList1);
			finalList.add(finalVO1);
			
			if(cdrCmmtLvlList!=null && cdrCmmtLvlList.size()>0){
				for(Object[] temp:cdrCmmtLvlList)
				{
					tempList2.add(new SelectOptionVO(Long.valueOf(temp[0].toString()),temp[1].toString()));
				}
				
			}
			
			finalVO2.setName("CadreCommitteeLevelsList");
			finalVO2.setSelectOptionsList(tempList2);
			finalList.add(finalVO2);
			
			if(cdrRlsList!=null && cdrRlsList.size()>0)
			{
				for(Object[] temp:cdrRlsList)
				{
					tempList3.add(new SelectOptionVO(Long.valueOf(temp[0].toString()),temp[1].toString()));
				}
			}
			
			finalVO3.setName("CadreRolesList");
			finalVO3.setSelectOptionsList(tempList3);
			finalList.add(finalVO3);
			
		}catch (Exception e) {
			LOG.error("Exception Raised in getCadreLevelsForCadreSearch "+ e);
		}
		return finalList;
	}

	public List<SelectOptionVO> getAllRelationDetails(){
		List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		try{
		List<Object[]> results = voterRelationDAO.getAllRelationDetails();
		for(Object[] result:results){
			SelectOptionVO vo = new SelectOptionVO();
			vo.setId((Long)result[0]);
			vo.setName(result[1].toString());
			returnList.add(vo);
		}
		}catch(Exception e){
			LOG.error("Exception raised in getAllRelationDetails in CadreRegistrationService service", e);
		}
		return returnList;
	}

	public List<SelectOptionVO> getCadreCommitteDetails(Long levelId){
		List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		try{
		List<Object[]> results = cadreCommitteeRoleDAO.getCommitteeRolesByLevelId(levelId);
		for(Object[] result:results){
			SelectOptionVO vo = new SelectOptionVO();
			vo.setId((Long)result[0]);
			vo.setName(result[1].toString());
			returnList.add(vo);
		}
		}catch(Exception e){
			LOG.error("Exception raised in getAllRelationDetails in CadreRegistrationService service", e);
		}
		return returnList;
	}
	
	public List<SelectOptionVO> getCadreCommitteRoles(Long levelId,Long committeeId)
	{
		List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		try{
		List<Object[]> results = cadreCommitteeRoleDAO.getCommitteeRolesByLevelIdAndCommitteeId(levelId,committeeId);
		for(Object[] result:results){
			SelectOptionVO vo = new SelectOptionVO();
			vo.setId((Long)result[0]);
			vo.setName(result[1].toString());
			returnList.add(vo);
		}
		}catch(Exception e){
			LOG.error("Exception raised in getAllRelationDetails in CadreRegistrationService service", e);
		}
		return returnList;
	}
		
	public String copyFile(String sourcePath,String destinationPath){
		//synchronized ("copyFile"){
	 try{
		File file = new File(sourcePath);
		if(file.exists()){
			FileUtils.copyFile(file,  new File(destinationPath));
			LOG.error("Copy success");
			return "success";
		}
	  }catch(Exception e){
		  LOG.error("Exception raised in copyFile ", e);
		  LOG.error("Copy error");
		  return "error";
	  }
	// }
	 return "failure";
	}
	
	public boolean checkFileExistingOrNot(String path){
		File f = new File(path);
		if(f.exists()) { 
			return true;
		}else{
			return false;
		}
	}
	
	public String getCadreImage(String enrolmentId){
	  List<String> cadreImages = tdpCadreDAO.getCadreImageByPreviousEnrolId(enrolmentId);
	  String reqImage = null;
	  for(String img:cadreImages){
		  if(reqImage != null){
			  break;
		  }else if(img != null && img.trim().length() > 0){
			  reqImage = img.trim();
		  }
	  }
	  return reqImage;
	}
	
	public String getCadreImageByPreviousEnrolId(String enrolmentId,String staticContentLoc){
		try{
			 String reqImage = getCadreImage(enrolmentId);
			 String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			 if(checkFileExistingOrNot(staticContentLoc + "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + reqImage)){
				 return "images/" + IConstants.CADRE_IMAGES + "/" + reqImage;
			 }else{
			    return null;
			 }
		}catch(Exception e){
			 LOG.error("Exception raised in getCadreImageByPreviousEnrolId ", e);
		}
		return null;
	}
	public List<GenericVO> getBoothsForMultipleLocations(Long constituencyId, List<Long> locationIds){
		LOG.info("Exception raised in getBoothdForMultipleLocations in CadreRegistrationService service");
		List<GenericVO> returnList = new ArrayList<GenericVO>();
		try {
			List<Long> panchayatIds = new ArrayList<Long>();
			List<Long> localBodyIds = new ArrayList<Long>();
			List<Long> constiIds = new ArrayList<Long>();
			for(Long id:locationIds){
				if(id != null && id.longValue() > 0){
					if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
						panchayatIds.add(Long.valueOf(id.toString().substring(1)));
					}else if(id.toString().substring(0,1).trim().equalsIgnoreCase("2")){
						localBodyIds.add(Long.valueOf(id.toString().substring(1)));
					}else if(id.toString().substring(0,1).trim().equalsIgnoreCase("3")){
						constiIds.add(Long.valueOf(id.toString().substring(1)));
					}
				}
			}
			List<Object[]> boothsList = new ArrayList<Object[]>();
			if(panchayatIds.size() > 0){
				boothsList.addAll(boothDAO.getAllBoothsInPanchayat(panchayatIds, IConstants.VOTER_DATA_PUBLICATION_ID));
			}
			if(localBodyIds.size() > 0){
				boothsList.addAll(boothDAO.getAllBoothsInMuncipalities(localBodyIds,IConstants.VOTER_DATA_PUBLICATION_ID));
			}
            if(constiIds.size() > 0){
            	boothsList.addAll(boothDAO.getAllBoothsInUrban(constiIds.get(0),IConstants.VOTER_DATA_PUBLICATION_ID));
			}
            Collections.sort(boothsList,boothsSort);
            for (Object[] param : boothsList){
				GenericVO vo = new GenericVO();
				
				vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L);
				vo.setName(param[1] != null ? "Booth-"+param[1].toString() + "  ("+(param[2] != null? param[2].toString()+") ":""):"");
				
				returnList.add(vo);
			}
			/*if(constituency != null)
			{
				String araaType = constituency.getAreaType();

				List<Object[]> tehsilList = tehsilDAO.findTehsilsByConstituencyIdAndPublicationDateId(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID); // 11
				
				List<Long> tehsilIds = new ArrayList<Long>();
				returnList = new ArrayList<GenericVO>();
				
				if(tehsilList != null && tehsilList.size()>0)
				{
					for (Object[] param : tehsilList) 
					{
						tehsilIds.add(param[0] != null ? Long.valueOf(param[0].toString()):0L);
					}
				}
				
				
				if(araaType != null && araaType.equalsIgnoreCase("RURAL") || araaType.equalsIgnoreCase("RURAL-URBAN")){	
					List<Object[]> localelectinbody = localElectionBodyDAO.getMuncipalitiesAndCorporationsInAConstituency(tehsilIds);
					List<Long> localElectionBodyIds = new ArrayList<Long>();
					if(localelectinbody != null && localelectinbody.size()>0)
					{
						for (Object[] param : localelectinbody){
							if(param[0]!=null){
								localElectionBodyIds.add(Long.valueOf(param[0].toString()));
							}
						}
					}
					
					List<Long> lclElectnBdyIds = new ArrayList<Long>();
					List<Long> thslIds = new ArrayList<Long>();
					for(Long lctnId:locationIds){
						if(localElectionBodyIds.contains(lctnId)){
							lclElectnBdyIds.add(lctnId);
						}else{
							thslIds.add(lctnId);
						}
					}
					
					if(lclElectnBdyIds != null && lclElectnBdyIds.size()>0){
						List<Object[]> boothList = boothDAO.getAllBoothsInMuncipalities(lclElectnBdyIds, IConstants.VOTER_DATA_PUBLICATION_ID);
						
						if(boothList != null && boothList.size()>0){
							for (Object[] param : boothList){
								GenericVO vo = new GenericVO();
								
								vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L);
								vo.setName(param[1] != null ? "Booth - "+param[1].toString() + "  ("+(param[2] != null? param[2].toString()+") ":""):"");
								
								returnList.add(vo);
							}
						}
					}
					
					else{
						List<Object[]> boothList = boothDAO.getBoothIdsByPanchayatIdsInAPublication(thslIds,  IConstants.VOTER_DATA_PUBLICATION_ID);
						if(boothList != null && boothList.size()>0){
							for (Object[] param : boothList){
								GenericVO vo = new GenericVO();
								
								vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L);
								vo.setName(param[2] != null ? "Booth - "+param[2].toString() + "  ("+(param[3] != null? param[3].toString()+") ":""):"");
								
								returnList.add(vo);
							}
						}
					}
				}
				else{
					List<Object[]> boothList = boothDAO.getAllBoothsInUrban(constituencyId, IConstants.VOTER_DATA_PUBLICATION_ID);
					
					if(boothList != null && boothList.size()>0)	{
						for (Object[] param : boothList){
							GenericVO vo = new GenericVO();
							
							vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L);
							vo.setName(param[1] != null ? "Booth - "+param[1].toString() + "  ("+(param[2] != null? param[2].toString()+") ":""):"");
							
							returnList.add(vo);
						}
					}
				}
			}*/
			
		} catch (Exception e) {
			LOG.error("Exception raised in getBoothdForMultipleLocations in CadreRegistrationService service", e);
		}
		return returnList; 
	}
	public String checkNFCNumberForVoterId(Long voterId)
	{
		String status = "fail";
		try {
			
			String number =tdpCadreDAO.checkNFCnumberForVoter(voterId);
			
				if(number == null )
				{
					status = "success";
				}
				else if( number.isEmpty()){
					status = "success";
				}
			
				else
				{
					status = "CardAssigned";
				}
			
		} catch (Exception e) {
			LOG.error("Exception raised in checkNFCNumberForVoterId in CadreRegistrationService service", e);
		}
		return status;
	}
		
	/**
	 * @author Sreenivas
	 * @date 21-10-2014
	 * @param CardSenderVO
	 * @param surveyCadreResponceVO
	 */
	public SurveyCadreResponceVO tdpCardSenderSavingLogic(final CardSenderVO cardSenderVO){
		final SurveyCadreResponceVO surveyCadreResponceVO = new SurveyCadreResponceVO();
		try {	
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					CardSender cardSender = new CardSender();
					if(cardSenderVO.getName() != null && !cardSenderVO.getName().equalsIgnoreCase("null") && cardSenderVO.getName().trim().length() > 0)
					{
						cardSender.setName(cardSenderVO.getName());
					}
					if(cardSenderVO.getMobileNumber() != null && !cardSenderVO.getMobileNumber().equalsIgnoreCase("null") && cardSenderVO.getMobileNumber().trim().length() > 0)
					{
						cardSender.setMobileNo(cardSenderVO.getMobileNumber());
					}
					if(cardSenderVO.getMessage() != null && !cardSenderVO.getMessage().equalsIgnoreCase("null") && cardSenderVO.getMessage().trim().length() > 0)
					{
						cardSender.setMessage(StringEscapeUtils.escapeJava(cardSenderVO.getMessage()));
					}
					if(cardSenderVO.getUserId() != null){
						cardSender.setUserId(cardSenderVO.getUserId());
					}
					cardSender.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					cardSender.setIsDeleted("N");
					
					CardSender cardSender1=cardSenderDAO.save(cardSender);
					if(cardSender1!=null){
						List<Long> tdpCardreIdsList=cardSenderVO.getCadreIds();
						if(tdpCardreIdsList!=null && tdpCardreIdsList.size()>0){
							for(Long tdpCadreId:tdpCardreIdsList){
								CardReceiver cardReceiver=new CardReceiver();
								cardReceiver.setCardSenderId(cardSender1.getCardSenderId());
								cardReceiver.setTdpCadreId(tdpCadreId);
								cardReceiver.setIsDeleted("N");
								cardReceiverDAO.save(cardReceiver);
							}
						}
						
						tdpCadreDAO.updateDispatchStatus(tdpCardreIdsList);
					}
					
					
			
					surveyCadreResponceVO.setStatus("SUCCESS");
					surveyCadreResponceVO.setResultCode(ResultCodeMapper.SUCCESS);
					
				}
				
			});
			String mobileNms = cardSenderVO.getMobileNums();
			//sendSMSInTelugu(mobileNms, getUniCodeMessage(StringEscapeUtils.unescapeJava("\u0C24\u0C46\u0C32\u0C41\u0C17\u0C41 \u0C26\u0C47\u0C36\u0C02 \u0C2A\u0C3E\u0C30\u0C4D\u0C1F\u0C40 \u0C15\u0C3E\u0C30\u0C4D\u0C2F\u0C15\u0C30\u0C4D\u0C24\u0C17\u0C3E \u0C28\u0C2E\u0C4B\u0C26\u0C41 \u0C1A\u0C47\u0C38\u0C41\u0C15\u0C41\u0C28\u0C4D\u0C28\u0C02\u0C26\u0C41\u0C15\u0C41 \u0C27\u0C28\u0C4D\u0C2F\u0C35\u0C3E\u0C26\u0C3E\u0C32\u0C41. \u0C2E\u0C40 \u0C2F\u0C4A\u0C15\u0C4D\u0C15 \u0C30\u0C3F\u0C2B\u0C30\u0C46\u0C28\u0C4D\u0C38\u0C4D \u0C28\u0C46\u0C02\u0C2C\u0C30\u0C4D : Please Contact - "+cardSenderVO.getName()+" ")+cardSenderVO.getMobileNumber()));
			String retVal = sendSMSInTelugu(mobileNms, getUniCodeMessage(StringEscapeUtils.unescapeJava(cardSenderVO.getMessage()+" - "+cardSenderVO.getName()+" ")+cardSenderVO.getMobileNumber()));
			
		} catch (Exception e) {
			surveyCadreResponceVO.setResultCode(ResultCodeMapper.FAILURE);
			surveyCadreResponceVO.setStatus("EXCEPTION");
			LOG.error("Exception raised in tdpCardSenderSavingLogic in CadreRegistrationService service", e);
		}
		
		
		return surveyCadreResponceVO;
	}
	
	public String delinkNFCNumber(String cardNumber,Long voterId)
	{
		String status = "fail";
		try {
				
			Integer count = tdpCadreDAO.updateNFCCardNumberByVoterIdForDelink(voterId,cardNumber);
			if(count != null && count.longValue() > 0)
			{
				status = "success";
			}			
			else
			{
				status = "failed";
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in delinkNFCNumber in CadreRegistrationService service", e);
		}
		return status;
	}
	public  Comparator<Object[]> boothsSort = new Comparator<Object[]>(){		  
		  public int compare(Object[] result1, Object[] result2)
			{
			   return ((Long.valueOf(result1[1].toString().trim())).intValue()) - ((Long.valueOf(result2[1].toString().trim())).intValue());
			}
		};
	
	public List<CasteDetailsVO> getAllCastes()
	{
	
	List<CasteDetailsVO> castesList =new ArrayList<CasteDetailsVO>();

	//List<Object[]> list = casteDAO.getCastes();
	//List<Object[]> list1 = casteStateDAO.getAllCasteInfo();
		try{
			
			/*if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					CasteDetailsVO vo = new CasteDetailsVO();
					vo.setCasteId((Long)params[0]);
					vo.setCastName(params[1] != null ? params[1].toString() : "");
					castesList.add(vo);
				}
			}*/
			List<Object[]> list1 = casteStateDAO.getAllCastesInfo();
			if(list1 != null && list1.size() > 0)
			{
				for(Object[] params : list1)
				{
					CasteDetailsVO vo = new CasteDetailsVO();
					vo.setCastStateId((Long)params[0]);
					vo.setCasteId((Long)params[1]);
					vo.setCasteCategoryGroupId((Long)params[2]);
					vo.setStateId((Long)params[3]);
					vo.setCastName(params[4] != null ? params[4].toString() : "");
					castesList.add(vo);
				}
			}
			
		}
		catch (Exception e) {
			LOG.error("Exception raised in getAllCastes in CadreRegistrationService service", e);
		}
		return castesList;
	}
	
	
	public List<CadrePrintVO> getSelectedLevelCadreDetails1(Long panchayatId,String type)
	{
		List<CadrePrintVO> returnList = null;
		try {
		 List<Object[]> result = tdpCadreDAO.getPanchayatWiseCadreDetails1(panchayatId,type);
		 if(result != null && result.size() > 0)
		 {
			 //String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			 returnList = new ArrayList<CadrePrintVO>();
			 Long count = 1l;
			 
			 for (Object[] objects : result)
			 {
				 CadrePrintVO cadrePrintVO = new CadrePrintVO();
				 	Long voterId = (Long)objects[1];
					UserAddress userAddress = new UserAddress()	;
					getVoterAddressDetails(voterId,userAddress,null);
					cadrePrintVO.setCadrePrintVOId(count);
					count++;
					cadrePrintVO.setVillageName(userAddress.getPanchayat() != null ? StringEscapeUtils.unescapeJava(userAddress.getPanchayat().getLocalName()  )  + StringEscapeUtils.unescapeJava("\u0C17\u0C4D\u0C30\u0C3E\u0C2E\u0C02"): "");
					cadrePrintVO.setMandalName(userAddress.getTehsil() != null ?  StringEscapeUtils.unescapeJava(userAddress.getTehsil().getLocalName() ) + StringEscapeUtils.unescapeJava("\u0C2E\u0C02\u0C21\u0C32\u0C02"):"");
					cadrePrintVO.setConstituencyName(userAddress.getConstituency() != null ?  StringEscapeUtils.unescapeJava(userAddress.getConstituency().getLocalName() ) + StringEscapeUtils.unescapeJava("\u0C28\u0C3F") + "||" : "");
					cadrePrintVO.setDistrictName(userAddress.getDistrict() != null ?  StringEscapeUtils.unescapeJava(userAddress.getDistrict().getLocalName() ) + StringEscapeUtils.unescapeJava("\u0C1C\u0C3F\u0C32\u0C4D\u0C32\u0C3E"):"");
					if(objects[0] != null){
						if(objects[0].toString().trim().length() > 8){
							cadrePrintVO.setFirstCode(objects[0].toString().trim().substring(objects[0].toString().trim().length()-8));
						}else{
							cadrePrintVO.setFirstCode(objects[0].toString());
						}
					}else{
						cadrePrintVO.setFirstCode("");
					}
					//cadrePrintVO.setFirstCode(objects[0] != null ? objects[0].toString() : "");
					cadrePrintVO.setVoterName(objects[2] != null ? objects[2].toString() : "");
					cadrePrintVO.setRelativeName(objects[3] != null ? objects[3].toString() : "");
					cadrePrintVO.setVoterId(objects[4] != null ? (Long)objects[4] : 0l);
					cadrePrintVO.setVoterCardNo(objects[5] != null ? objects[5].toString() : "");
					cadrePrintVO.setRefNumber(objects[6] != null ? objects[6].toString() : "");
					cadrePrintVO.setCardNumber(objects[7] != null ? objects[7].toString() : "");
					cadrePrintVO.setVillageEng(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getPanchayatName() : "");
					cadrePrintVO.setMandalEng(userAddress.getTehsil() != null ?  userAddress.getTehsil().getTehsilName() :"");
					cadrePrintVO.setConstiEng(userAddress.getConstituency() != null ?  userAddress.getConstituency().getName()  : "");
					cadrePrintVO.setDistrictEng(userAddress.getDistrict() != null ?  userAddress.getDistrict().getDistrictName() :"");
					cadrePrintVO.setRefNumber(objects[6] != null ? objects[6].toString() : "");
					cadrePrintVO.setCardNumber(objects[7] != null ? objects[7].toString() : "");
					if(objects[9] != null ){
						
						cadrePrintVO.setPhotoType(objects[9].toString());
						
						if(cadrePrintVO.getPhotoType().equalsIgnoreCase("NEW")){
							if(objects[8] != null){
								String url = "https://mytdp.com/images/cadre_images/"+objects[8].toString();
								cadrePrintVO.setImage(url);
							}
						}
						else if(cadrePrintVO.getPhotoType().equalsIgnoreCase("CADRE")){
							if(objects[8] != null){
								String url = "https://mytdp.com/images/cadre_images/"+objects[8].toString();
								cadrePrintVO.setImage(url);
							}
						}
						else if(cadrePrintVO.getPhotoType().equalsIgnoreCase("VOTER")){
								cadrePrintVO.setImage(objects[9] != null ? "https://mytdp.com/"+IConstants.VOTER_IMG_FOLDER_PATH+"/"+objects[9].toString() : "");
						}
					}
					/*else{
						cadrePrintVO.setPhotoType("");
					}
				   if(objects[8] != null){
						String cadreUrl = "https://mytdp.com/images/cadre_images/"+objects[8].toString();
						cadrePrintVO.setCadreImgPath(cadreUrl);
					}
					
					if(userAddress.getConstituency() != null && userAddress.getBooth() !=null)
					{
						String url = "https://mytdp.com/voter_images/"+userAddress.getConstituency().getConstituencyId().toString().trim()+"/"+"Part"+userAddress.getBooth().getPartNo().trim()+"/"+cadrePrintVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";;
						//String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +""+"\\"+userAddress.getConstituency().getConstituencyId().toString().trim()+"\\"+"Part"+userAddress.getBooth().getPartNo().trim()+"\\"+cadrePrintVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";
						cadrePrintVO.setVoterImgPath(url);
					}*/
					
					
					returnList.add(cadrePrintVO);
			 }
		 }
		} catch (Exception e) {
			LOG.error("Exception raised in getCadreDetailsForPrinting in CadreRegistrationService service", e);
		}
		return returnList;
	}
	
	public List<Long> getVoterIdByVoterCard(String voterCardId){
		return voterDAO.getVoterId(voterCardId);
	}
	public List<CadreRegisterInfo> getDistrictsByStateWiseAction(Long stateId)
	{
		List<CadreRegisterInfo> cadreRegisterInfoList=null;
		List<Object[]> returnList = null;
		//Long distId;
		try {
				returnList = tdpCadreDAO.getDistrictsByStateWiseAction(stateId);
				if(returnList !=null && returnList.size()>0)
				{
					cadreRegisterInfoList = new ArrayList<CadreRegisterInfo>();
					for (Object[] objects : returnList) 
					{
						CadreRegisterInfo cadreRegisterInfo = new CadreRegisterInfo();
						cadreRegisterInfo = new CadreRegisterInfo();
						cadreRegisterInfo.setId(Long.valueOf(objects[0].toString()));
						cadreRegisterInfo.setName(objects[1].toString());
						
						cadreRegisterInfoList.add(cadreRegisterInfo);
					}
				}
			}
			catch (Exception e) {
				LOG.error("Exception raised in getDistrictsByStateWiseAction in CadreRegistrationService service", e);
			}
		return cadreRegisterInfoList;
	}
	
	public List<CadreRegisterInfo> getDistrictsByStateBasedOnAccess(Long stateId,String accessLevel,Long accessValue)
	{
		List<CadreRegisterInfo> cadreRegisterInfoList = new ArrayList<CadreRegisterInfo>(0);
		List<Object[]> list = new ArrayList<Object[]>(0);
		try{
			if(accessLevel.equalsIgnoreCase(IConstants.MLA))
			{
				Long districtId = constituencyDAO.getDistrictIdByConstituencyId(accessValue).get(0);
				District district = districtDAO.get(districtId);
				Object[] objArr = {district.getDistrictId(),district.getDistrictName()};
				list.add(objArr);
			}
			else if(accessLevel.equalsIgnoreCase(IConstants.MP))
			{
				list = (List<Object[]>)delimitationConstituencyAssemblyDetailsDAO.findDistrictsOfParliamentConstituency(accessValue);
			}
			else if(accessLevel.endsWith(IConstants.DISTRICT))
			{
				District district = districtDAO.get(accessValue);
				Object[] objArr = {district.getDistrictId(),district.getDistrictName()};
				list.add(objArr);
			}
			else if(accessLevel.endsWith(IConstants.STATE))
			{
				list = tdpCadreDAO.getDistrictsByStateWiseAction(stateId);
			}
			return cadreRegisterInfoList;
		}catch(Exception e)
		{
			LOG.error("Exception raised in getDistrictsByStateWiseAction in CadreRegistrationService service", e);
			return cadreRegisterInfoList;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CadreRegisterInfo> getConstsByStateWiseAction(Long stateId,RegistrationVO userVO)
	{
		List<CadreRegisterInfo> cadreRegisterInfoList=null;
		List<Object[]> returnList = null;
		//Long distId;
		try {
				if(userVO == null)
					returnList = tdpCadreDAO.getConstsByStateWiseAction(stateId);
				else{
					returnList = (List<Object[]>)userConstituencyAccessInfoDAO.findByElectionScopeUserState(2L,userVO.getRegistrationID(),1L);
				}
				if(returnList !=null && returnList.size()>0)
				{
					cadreRegisterInfoList = new ArrayList<CadreRegisterInfo>();
					for (Object[] objects : returnList) 
					{
						CadreRegisterInfo cadreRegisterInfo = new CadreRegisterInfo();
						cadreRegisterInfo = new CadreRegisterInfo();
						cadreRegisterInfo.setId(Long.valueOf(objects[0].toString()));
						cadreRegisterInfo.setName(objects[1].toString());
						
						cadreRegisterInfoList.add(cadreRegisterInfo);
					}
				}
			}
			catch (Exception e) {
				LOG.error("Exception raised in getDistrictsByStateWiseAction in CadreRegistrationService service", e);
			}
		return cadreRegisterInfoList;
	}
	
	@SuppressWarnings("unchecked")
	public List<CadreRegisterInfo> getConstituenciesByStateBasedOnAccess(Long stateId,String accessLevel,Long accessValue)
	{
		List<CadreRegisterInfo> cadreRegisterInfoList = null;
		List<Object[]> list = new ArrayList<Object[]>(0);
		try{
			if(accessLevel.equalsIgnoreCase(IConstants.MLA))
			{
				Constituency constituency = constituencyDAO.get(accessValue);
				Object[] objArr = {constituency.getConstituencyId(),constituency.getName()};
				list.add(objArr);
			}
			else if(accessLevel.equalsIgnoreCase(IConstants.MP))
			{
				list = (List<Object[]>)delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituencies(accessValue);
			}
			else if(accessLevel.endsWith(IConstants.DISTRICT))
			{
				list = constituencyDAO.getConstituenciesByDistrictId(accessValue);
			}
			else if(accessLevel.endsWith(IConstants.STATE))
			{
				list = tdpCadreDAO.getConstsByStateWiseAction(stateId);
			}
			
			if(list !=null && list.size()>0)
			{
				cadreRegisterInfoList = new ArrayList<CadreRegisterInfo>();
				for (Object[] objects : list) 
				{
					CadreRegisterInfo cadreRegisterInfo = new CadreRegisterInfo();
					cadreRegisterInfo = new CadreRegisterInfo();
					cadreRegisterInfo.setId(Long.valueOf(objects[0].toString()));
					cadreRegisterInfo.setName(objects[1].toString());
					
					cadreRegisterInfoList.add(cadreRegisterInfo);
				}
			}
			return cadreRegisterInfoList;
		}catch(Exception e)
		{
			LOG.error("Exception raised in getConstituenciesByStateBasedOnAccess in CadreRegistrationService service", e);
			return cadreRegisterInfoList;
		}
	}
	
	public List<CadrePrintVO> getSelectedLevelCadreDetailsBySelection(CadrePrintInputVO input){

		List<CadrePrintVO> returnList = null;
		try {
		 //List<Object[]> result = tdpCadreDAO.getPanchayatWiseCadreDetails1(panchayatId,type);
			 List<Object[]> finalResult = new ArrayList<Object[]>();
			
			 List<Object[]> result = tdpCadreDAO.getCadreDetailsForSelection(input);
			 List<Object[]> result1 = tdpCadreDAO.getCadreDetailsForSelectionByFamilyVoterId(input);
			 if(result !=null && result.size()>0)
				 finalResult.addAll(result);
			 if(result1 !=null && result1.size()>0)
				 finalResult.addAll(result1);
		 
			 if(finalResult != null && finalResult.size() > 0)
			 {
			 //String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			 returnList = new ArrayList<CadrePrintVO>();
			 Long count = 1l;
			 
			 for (Object[] objects : finalResult)
			 {
				 CadrePrintVO cadrePrintVO = new CadrePrintVO();
				 	Long voterId = (Long)objects[1];
					UserAddress userAddress = new UserAddress()	;
					getVoterAddressDetails(voterId,userAddress,null);
					cadrePrintVO.setCadrePrintVOId(count);
					count++;
					cadrePrintVO.setVillageName(userAddress.getPanchayat() != null ? StringEscapeUtils.unescapeJava(userAddress.getPanchayat().getLocalName()  )  + StringEscapeUtils.unescapeJava("\u0C17\u0C4D\u0C30\u0C3E\u0C2E\u0C02"): "");
					cadrePrintVO.setMandalName(userAddress.getTehsil() != null ?  StringEscapeUtils.unescapeJava(userAddress.getTehsil().getLocalName() ) + StringEscapeUtils.unescapeJava("\u0C2E\u0C02\u0C21\u0C32\u0C02"):"");
					cadrePrintVO.setConstituencyName(userAddress.getConstituency() != null ?  StringEscapeUtils.unescapeJava(userAddress.getConstituency().getLocalName() ) + StringEscapeUtils.unescapeJava("\u0C28\u0C3F") + "||" : "");
					cadrePrintVO.setDistrictName(userAddress.getDistrict() != null ?  StringEscapeUtils.unescapeJava(userAddress.getDistrict().getLocalName() ) + StringEscapeUtils.unescapeJava("\u0C1C\u0C3F\u0C32\u0C4D\u0C32\u0C3E"):"");
					if(objects[0] != null){
						if(objects[0].toString().trim().length() > 8){
							cadrePrintVO.setFirstCode(objects[0].toString().trim().substring(objects[0].toString().trim().length()-8));
						}else{
							cadrePrintVO.setFirstCode(objects[0].toString());
						}
					}else{
						cadrePrintVO.setFirstCode("");
					}
					//cadrePrintVO.setFirstCode(objects[0] != null ? objects[0].toString() : "");
					cadrePrintVO.setVoterName(objects[2] != null ? objects[2].toString() : "");
					cadrePrintVO.setRelativeName(objects[3] != null ? objects[3].toString() : "");
					cadrePrintVO.setVoterId(objects[4] != null ? (Long)objects[4] : 0l);
					cadrePrintVO.setVoterCardNo(objects[5] != null ? objects[5].toString() : "");
					cadrePrintVO.setRefNumber(objects[6] != null ? objects[6].toString() : "");
					cadrePrintVO.setCardNumber(objects[7] != null ? objects[7].toString() : "");
					cadrePrintVO.setVillageEng(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getPanchayatName() : "");
					cadrePrintVO.setMandalEng(userAddress.getTehsil() != null ?  userAddress.getTehsil().getTehsilName() :"");
					cadrePrintVO.setConstiEng(userAddress.getConstituency() != null ?  userAddress.getConstituency().getName()  : "");
					cadrePrintVO.setDistrictEng(userAddress.getDistrict() != null ?  userAddress.getDistrict().getDistrictName() :"");
					cadrePrintVO.setRefNumber(objects[6] != null ? objects[6].toString() : "");
					cadrePrintVO.setCardNumber(objects[7] != null ? objects[7].toString() : "");
					if(objects[9] != null ){
						
						cadrePrintVO.setPhotoType(objects[9].toString());
						
						if(cadrePrintVO.getPhotoType().equalsIgnoreCase("NEW")){
					
								//String url = "https://mytdp.com/images/cadre_images/"+objects[8].toString();
								cadrePrintVO.setImage((objects[8] != null  ? "https://mytdp.com/images/cadre_images/"+objects[8].toString() : ""));
								cadrePrintVO.setImgPath1((objects[8] != null  ? "https://mytdp.com/images/cadre_images/"+objects[8].toString() : ""));
								cadrePrintVO.setImgPath2("https://mytdp.com/"+IConstants.VOTER_IMG_FOLDER_PATH+cadreDetailsService.getVoterImageUrlByVoterId(voterId));
						}
						else if(cadrePrintVO.getPhotoType().equalsIgnoreCase("CADRE")){							
								//String url = "https://mytdp.com/images/cadre_images/"+objects[8].toString();
								cadrePrintVO.setImage((objects[8] != null  ? "https://mytdp.com/images/cadre_images/"+objects[8].toString() : ""));
								cadrePrintVO.setImgPath1((objects[8] != null  ? "https://mytdp.com/images/cadre_images/"+objects[8].toString() : ""));								
								cadrePrintVO.setImgPath2("https://mytdp.com/"+IConstants.VOTER_IMG_FOLDER_PATH+cadreDetailsService.getVoterImageUrlByVoterId(voterId));
						}
						else if(cadrePrintVO.getPhotoType().equalsIgnoreCase("VOTER")){
								String url = "https://mytdp.com/"+IConstants.VOTER_IMG_FOLDER_PATH+"/"+cadreDetailsService.getVoterImageUrlByVoterId(voterId);
								cadrePrintVO.setImage(url);								
								cadrePrintVO.setImgPath1(objects[8] != null  ? "https://mytdp.com/images/cadre_images/"+objects[8].toString() : "");
								cadrePrintVO.setImgPath2(objects[8] != null  ? "https://mytdp.com/images/cadre_images/"+objects[8].toString() : "");
						}
					}
					/*else{
						cadrePrintVO.setPhotoType("");
					}*/
				   /*if(objects[8] != null){
						String cadreUrl = "https://mytdp.com/images/cadre_images/"+objects[8].toString();
						cadrePrintVO.setImage(cadreUrl);
					}
					
					if(userAddress.getConstituency() != null && userAddress.getBooth() !=null)
					{
						String url = "https://mytdp.com/voter_images/"+userAddress.getConstituency().getConstituencyId().toString().trim()+"/"+"Part"+userAddress.getBooth().getPartNo().trim()+"/"+cadrePrintVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";;
						//String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +""+"\\"+userAddress.getConstituency().getConstituencyId().toString().trim()+"\\"+"Part"+userAddress.getBooth().getPartNo().trim()+"\\"+cadrePrintVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";
						cadrePrintVO.setVoterImgPath(url);
					}*/
					
					
					returnList.add(cadrePrintVO);
			 }
		 }
					
				
		} catch (Exception e) {
			LOG.error("Exception raised in getCadreDetailsForPrinting in CadreRegistrationService service", e);
		}
		return returnList;
	
	}
	
	public String updateSmsJobStatus(String mobile,String jobcode,String dateTime,int status){
		LOG.debug(" Entered into updateSmsJobStatus ");
		try {
			String statusCode="";
			switch (status) {
			 case 0:
				statusCode = "pending";
				break;
             case 1:
	             statusCode = "Submitted To Carrier";
	             break;
	         case 2:
	             statusCode = "Un Delivered";
	             break;
	         case 3:
	             statusCode = "Delivered";
	             break;
	         case 4:
	             statusCode = "Expired";
	             break;
	         case 5:
	             statusCode = "Deleted";
	             break;
	         case 6:
	             statusCode = "Accepted";
	             break;
	         case 7:
	             statusCode = "Unknown";
	             break;
	         case 8:
	             statusCode = "Rejected";
	             break;
	         case 9:
	             statusCode = "Message Sent";
	             break;
	         case 10:
	             statusCode = "Opted Out";
	             break;
	         case 11:
	             statusCode = "Invalid Mobile Number";
	             break;
	         default:
	             statusCode = "Not Available";
	             break;
			}
			
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			SimpleDateFormat formatDt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss ");
			//Date date = originalFormat.parse(dateTime);
			Date date = originalFormat.parse(dateTime);
			//int updatedCount = tdpCadreDAO.updateSmsSentStatus(mobile, jobcode, dateTime, statusCode);
			//int updatedCount = smsJobStatusDAO.updateSmsSentStatus(mobile, jobcode, date, statusCode);
			 
		} catch (Exception e) {
			LOG.error("Exception Raised in updateSmsJobStatus "+e);
			return "failed";
		}
		return "success";
	}
	
	public String updateTabUserDetails(List<TabRecordsStatusVO> tabRecordsStatusVOList)
	{
		
		String status = null;
		try {
			
			if(tabRecordsStatusVOList != null && tabRecordsStatusVOList.size()>0)
			{
				
					for (TabRecordsStatusVO recordsStatusVO :tabRecordsStatusVOList) 
					{
						TabRecordsStatus tabRecordsStatus = new TabRecordsStatus();
						tabRecordsStatus.setCadreSurveyUserId(recordsStatusVO.getUserId());
						tabRecordsStatus.setCompletedRecords(recordsStatusVO.getTotalRecords()); // completed records
						tabRecordsStatus.setPendingRecords(recordsStatusVO.getPendingRecords());
						tabRecordsStatus.setTabIMEINo(recordsStatusVO.getTabIMEINo());
						tabRecordsStatus.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
						tabRecordsStatus.setDuplicateRecords(recordsStatusVO.getDuplicateRecords());
						tabRecordsStatus.setCurrPendingRecords(recordsStatusVO.getCurrPendingRecords());
						tabRecordsStatus.setCurrTotalRecords(recordsStatusVO.getCurrTotalRecords());
						tabRecordsStatus.setCurrDuplicateRecords(recordsStatusVO.getCurrDuplicateRecords());
						tabRecordsStatus.setPrevPendingRecords(recordsStatusVO.getPrevPendingRecords());
						tabRecordsStatus.setPrevTotalRecords(recordsStatusVO.getPrevTotalRecords());
						tabRecordsStatus.setPrevDuplicateRecords(recordsStatusVO.getPrevDuplicateRecords());
						tabRecordsStatus.setCurrLoginUser(recordsStatusVO.getActualUsrId());
						
						tabRecordsStatusDAO.save(tabRecordsStatus);
				}
				status = "success";
			}
			else
			{
				status = "failure";
			}
			
		} catch (Exception e) {
			status = "failure";
			LOG.error("Exception Raised in updateTabUserDetails "+e);
		}
		return status;
	}
	
	public String  updateTabLoginUserDetails(TabRecordsStatusVO recordsStatusVO)
	{
		
		String status = null;
		try {
			
			if(recordsStatusVO != null)
			{
						TabUserLoginDetails tabUserLoginDetails = new TabUserLoginDetails();
						tabUserLoginDetails.setCadreSurveyUserId(recordsStatusVO.getUserId());
						tabUserLoginDetails.setUserName(recordsStatusVO.getName());
						tabUserLoginDetails.setMobileNo(recordsStatusVO.getMobileNo());
						tabUserLoginDetails.setTabIMEINo(recordsStatusVO.getTabIMEINo());
						tabUserLoginDetails.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
						
						tabUserLoginDetailsDAO.save(tabUserLoginDetails);
						
						status = "success";
			}
			else
			{
				status = "failure";
			}
			
		} catch (Exception e) {
			status = "failure";
			LOG.error("Exception Raised in updateTabUserDetails "+e);
		}
		return status;
	}
	
	public List<CadrePrintVO> getTDPCadreDetailsBySearch(CadrePrintInputVO input){
		
		List<CadrePrintVO> finalList = new ArrayList<CadrePrintVO>();
		try{
			String date = input.getDate();
			String trNo = input.getTrNo();
			String constituency = input.getConstituency();
			Long constiNo = input.getConstituecyNo();
			String mobileNo = input.getMobileNo();
			Long constiId = input.getConstituencyId();
			
			StringBuffer sb = new StringBuffer();
			SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Date srvyDt = null;
			/*if(date!=null){
				srvyDt = originalFormat.parse(date);
			}*/
			
			/*if(date!=null && date.trim().length()>0){
				sb.append(" and date(model.surveyTime) =:surveyDate");
			}*/
			if(trNo!=null && trNo.trim().length()>0){
				sb.append(" and model.refNo =:trNo");
			}
			
			if(mobileNo!=null && mobileNo.trim().length()>0){
				sb.append(" and model.mobileNo = :mobileNo");
			}

			if(constiNo!=null){
				sb.append(" and model.userAddress.constituency.constituencyId =:constituencyId");
			}
			
			Set<String> voterMemberCards = new HashSet<String>();
			Set<String> nonVoterMemberCards = new HashSet<String>();
			
			List<String> memberCards = tdpCadreDAO.getCardNumbers(sb.toString(), constiNo, mobileNo, trNo, srvyDt);
			List<String> memberCardsForNonVoters = tdpCadreDAO.getCardNumbersForNonVoters(sb.toString(), constiNo, mobileNo, trNo, srvyDt);
			List<String> memberCardsOnline = tdpCadreDAO.getCardNumbersForOnlineCadre(sb.toString(), constiNo, mobileNo, trNo, srvyDt);
			List<String> memberCardsForNonVotersOnline = tdpCadreDAO.getCardNumbersForNonVotersForOnlineCadre(sb.toString(), constiNo, mobileNo, trNo, srvyDt);
			
			if(memberCards!=null && memberCards.size()>0)
				voterMemberCards.addAll(memberCards);
			if(memberCardsOnline!=null && memberCardsOnline.size()>0)
				voterMemberCards.addAll(memberCardsOnline);
			
			if(memberCardsForNonVoters!=null && memberCardsForNonVoters.size()>0)
				nonVoterMemberCards.addAll(memberCardsForNonVoters);
			if(memberCardsForNonVotersOnline !=null && memberCardsForNonVotersOnline.size()>0)
				nonVoterMemberCards.addAll(memberCardsForNonVotersOnline);
			
			if(voterMemberCards!=null && voterMemberCards.size()>0){
				List<String> voterMemberCardsList = new ArrayList<String>(voterMemberCards);
				List<Object[]> vtrDetails = tdpCadreDAO.getCadreDetailsByMemberShipId(voterMemberCardsList);
				if(vtrDetails != null && vtrDetails.size() > 0){
					for(Object[] obj:vtrDetails){
						Long voterId = Long.valueOf(obj[1].toString());
						UserAddress userAddress = new UserAddress()	;
						CadrePrintVO returnVO = new CadrePrintVO();
						
						Long userAddressId = 0l;
						if(obj[12]!=null){
							userAddressId = Long.valueOf(obj[12].toString());
						} 
						
						//getVoterAddressDetails(voterId,userAddress,null);
						getUserAddressForCadreRegistered(userAddressId,userAddress,null);
						if(obj[0] != null){
							if(obj[0].toString().trim().length() > 8){
								returnVO.setFirstCode(obj[0].toString().trim().substring(obj[0].toString().trim().length()-8));
							}else{
								returnVO.setFirstCode(obj[0].toString());
							}
						}else{
							returnVO.setFirstCode("");
						}
						//returnVO.setFirstCode(obj[0] != null ? obj[0].toString() : "");
						//int size = returnVO.getFirstCode().length();
						returnVO.setSecondCode(returnVO.getFirstCode());
						returnVO.setVoterId(obj[4] != null ?(Long)obj[4] : 0l);
						returnVO.setVoterCardNo(obj[5] != null ? obj[5].toString() : "");
						returnVO.setDataSourceType(obj[6] != null ? obj[6].toString() : "");
						returnVO.setTdpCadreId(obj[7] != null ? Long.valueOf(obj[7].toString()) : 0l);
						returnVO.setRefNumber(obj[8] != null ? obj[8].toString() : "");
						returnVO.setMobileNo(obj[9] != null ? obj[9].toString() : "");
						if(obj[10] != null)
						{
							String photoType = obj[10].toString();
							if(photoType.equalsIgnoreCase("NEW"))
							{
								String url = "https://mytdp.com/images/cadre_images/"+obj[11].toString();
								returnVO.setVoterImgPath(url);
							
							}
							else if(photoType.equalsIgnoreCase("CADRE"))
							{							
									String url = "https://mytdp.com/images/cadre_images/"+obj[11].toString();
									returnVO.setVoterImgPath(url);
							}
							else
							{
								returnVO.setVoterImgPath("https://mytdp.com/"+IConstants.VOTER_IMG_FOLDER_PATH+"/"+cadreDetailsService.getVoterImageUrlByVoterId(voterId));
							}
						}
						
						if(returnVO.getVoterId()==null){
							List<String> names = tdpCadreTeluguNamesDAO.getTeluguVoterNameByTdpCadreId(returnVO.getTdpCadreId());
							if(names != null && names.size() > 0){
								returnVO.setVoterName(names.get(0));
							}
						}else{
							List<String> names = voterNamesDAO.getVoterTeluguNames((Long)obj[4] );
							if(names != null && names.size() > 0){
								returnVO.setVoterName(names.get(0));
							}
						}
						
						//returnVO.setVillage(userAddress.getPanchayatId() != null ? panchayatDAO.get(userAddress.getPanchayatId()).getLocalName() : "");
						returnVO.setVillage(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getLocalName() : "");
						returnVO.setMandal(userAddress.getTehsil() != null ?  userAddress.getTehsil().getLocalName() :"");
						returnVO.setConstituency(userAddress.getConstituency() != null ?  userAddress.getConstituency().getLocalName() : "");
						returnVO.setConstituencyType(userAddress.getConstituency() != null ? userAddress.getConstituency().getAreaType() : "");
						returnVO.setDistrict(userAddress.getDistrict() != null ?  userAddress.getDistrict().getLocalName():"");
						returnVO.setMuncipalityName(userAddress.getLocalElectionBody() != null ? userAddress.getLocalElectionBody().getNameLocal() : "" );
						
						finalList.add(returnVO);
					}
					
				}
			}
			
			if(nonVoterMemberCards !=null && nonVoterMemberCards.size()>0){
				List<String> nonVoterMemberCardsList = new ArrayList<String>(nonVoterMemberCards);
				List<Object[]> vtrDetails = tdpCadreDAO.getCadreDetailsByMemberShipIdForNonVoters(nonVoterMemberCardsList);
				if(vtrDetails != null && vtrDetails.size() > 0){
					for(Object[] obj:vtrDetails){
						//Long voterId = Long.valueOf(obj[1].toString());
						UserAddress userAddress = new UserAddress()	;
						CadrePrintVO returnVO = new CadrePrintVO();
						Long userAddressId = 0l;
						if(obj[10]!=null){
							userAddressId = Long.valueOf(obj[10].toString());
						} 
						getUserAddressForCadreRegistered(userAddressId,userAddress,null);
						if(obj[0] != null){
							if(obj[0].toString().trim().length() > 8){
								returnVO.setFirstCode(obj[0].toString().trim().substring(obj[0].toString().trim().length()-8));
							}else{
								returnVO.setFirstCode(obj[0].toString());
							}
						}else{
							returnVO.setFirstCode("");
						}
						//returnVO.setFirstCode(obj[0] != null ? obj[0].toString() : "");
						//int size = returnVO.getFirstCode().length();
						returnVO.setSecondCode(returnVO.getFirstCode());
						/*returnVO.setVoterId(obj[4] != null ?(Long)obj[4] : 0l);
						returnVO.setVoterCardNo(obj[5] != null ? obj[5].toString() : "");*/
						returnVO.setDataSourceType(obj[4] != null ? obj[4].toString() : "");
						returnVO.setTdpCadreId(obj[5] != null ? Long.valueOf(obj[5].toString()) : 0l);
						returnVO.setRefNumber(obj[6] != null ? obj[6].toString() : "");
						returnVO.setMobileNo(obj[7] != null ? obj[7].toString() : "");
						if(obj[8] != null)
						{
							String photoType = obj[8].toString();
							if(photoType.equalsIgnoreCase("NEW"))
							{
								String url = "https://mytdp.com/images/cadre_images/"+obj[9].toString();
								returnVO.setVoterImgPath(url);
							
							}
							else if(photoType.equalsIgnoreCase("CADRE"))
							{							
									String url = "https://mytdp.com/images/cadre_images/"+obj[9].toString();
									returnVO.setVoterImgPath(url);
							}
							else
							{
								String url = "https://mytdp.com/"+IConstants.VOTER_IMG_FOLDER_PATH+"/"+userAddress.getConstituency().getConstituencyId().toString().trim()+"/"+"Part"+userAddress.getBooth().getPartNo().trim()+"/"+returnVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";
								returnVO.setVoterImgPath(url);
							}
						}
						
						if(returnVO.getVoterId()==null){
							List<String> names = tdpCadreTeluguNamesDAO.getTeluguVoterNameByTdpCadreId(returnVO.getTdpCadreId());
							if(names != null && names.size() > 0){
								returnVO.setVoterName(names.get(0));
							}
						}else{
							List<String> names = voterNamesDAO.getVoterTeluguNames((Long)obj[4] );
							if(names != null && names.size() > 0){
								returnVO.setVoterName(names.get(0));
							}
						}
						
						//returnVO.setVillage(userAddress.getPanchayatId() != null ? panchayatDAO.get(userAddress.getPanchayatId()).getLocalName() : "");
						returnVO.setVillage(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getLocalName() : "");
						returnVO.setMandal(userAddress.getTehsil() != null ?  userAddress.getTehsil().getLocalName() :"");
						returnVO.setConstituency(userAddress.getConstituency() != null ?  userAddress.getConstituency().getLocalName() : "");
						returnVO.setConstituencyType(userAddress.getConstituency() != null ? userAddress.getConstituency().getAreaType() : "");
						returnVO.setDistrict(userAddress.getDistrict() != null ?  userAddress.getDistrict().getLocalName():"");
						returnVO.setMuncipalityName(userAddress.getLocalElectionBody() != null ? userAddress.getLocalElectionBody().getNameLocal() : "" );
						
						finalList.add(returnVO);
					}
					
				}
			}
			
		
	}catch(Exception e){
		LOG.error("Exception Raised in getTDPCadreDetailsBySearch",e);
	}
		
		return finalList;
	}
	
	
	
public List<CadrePrintVO> getTDPCadreDetailsForSearch(CadrePrintInputVO input){
	
	List<CadrePrintVO> finalList = new ArrayList<CadrePrintVO>();
	try{
		
		//USER CHECKING
		if(input.getUname() == null || input.getUname().trim().length() == 0){
			CadrePrintVO returnVO = new CadrePrintVO();
			returnVO.setStatus("Invalid");
			finalList.add(returnVO);
			 return finalList;
	    }
	    List validCheck = cardPrintUserDAO.checkUserEixsts(input.getUname(),input.getPwd());;
	   if(validCheck == null || validCheck.size() == 0){
		 CadrePrintVO returnVO = new CadrePrintVO();
		 returnVO.setStatus("Invalid");
		 finalList.add(returnVO);
		 return finalList;
	   }
	 
	   //GET INPUTS
	   
	 	//String date = input.getDate();
		String trNo = input.getTrNo();
		
		String mobileNo = input.getMobileNo();
		
		//String constituency = input.getConstituency();
		//Long constiId = input.getConstituencyId();
		Long constiNo = input.getConstituecyNo();
		
		Long distId = input.getDistrictId();
		Long mandalId = input.getMandalId();
		Long townId = input.getTownId();
		Long enrollmentId = input.getEnrollmentId();
		
		if(enrollmentId == null)
			enrollmentId = IConstants.PRESENT_CADRE_ENROLLMENT_YEAR;
		
		String isOtherState = input.getIsOtherState();
		
		Date srvyDt = null;
		/*SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(date!=null){
			srvyDt = originalFormat.parse(date);
		}
		if(date!=null && date.trim().length()>0){
			sb.append(" and date(model.surveyTime) =:surveyDate");
		}*/
		
		//Query building
		StringBuffer sb = new StringBuffer();
		if(trNo!=null && trNo.trim().length()>0){
			sb.append(" and model.refNo =:trNo");
		}
		
		if(mobileNo!=null && mobileNo.trim().length()>0){
			sb.append(" and model.mobileNo = :mobileNo");
		}

		if(constiNo != null && constiNo > 0){
			sb.append(" and model.userAddress.constituency.constituencyId =:constituencyId");
		}
		if(distId != null && distId > 0){
			sb.append(" and model.userAddress.constituency.district.districtId =:districtId");
		}
		if(mandalId != null && mandalId > 0){
			sb.append(" and model.userAddress.tehsil.tehsilId =:tehsilId");
		}
		
		if(townId != null && townId > 0){
			sb.append(" and model.userAddress.localElectionBody.localElectionBodyId = :townId ");
		}
		else
			townId = null;
		
		if(input.getMemberShipNumber() != null && input.getMemberShipNumber().trim().length() > 0)
		{
			/*String memberShipNumber = "AP14"+input.getMemberShipNumber() ;
			String memberShipNumber1 = "TS14"+input.getMemberShipNumber() ;
		 	sb.append(" and (model.memberShipNo ='"+input.getMemberShipNumber().trim()+"' OR model.memberShipNo ='"+memberShipNumber.trim()+"' OR model.memberShipNo ='"+memberShipNumber1.trim()+"') ");*/
			sb.append(" and model.memberShipNo ='"+input.getMemberShipNumber().trim()+"' ");
		}
		
		if(input.getName() != null && input.getName().trim().length() > 0)
		{
			sb.append(" and model.firstname like '%"+input.getName().trim()+"%' ");
		}
		
		if(input.getRelativeName() != null && input.getRelativeName().trim().length() > 0)
		{
			sb.append(" and model.relativename like '%"+input.getRelativeName().trim()+"%' ");
		}
		
		if(input.getVoterIdCardNo() != null && input.getVoterIdCardNo().trim().length() > 0)
		{
			sb.append(" and model.voter.voterIDCardNo = '"+input.getVoterIdCardNo().trim()+"' ");
		}
		
		if(enrollmentId != null)
			sb.append(" and model1.enrollmentYear.enrollmentYearId = "+enrollmentId+" ");
		
		//Get MembershipIds For voter and family voter ids scenarion search.
		Set<String> voterMemberCards = new HashSet<String>();
		Set<String> nonVoterMemberCards = new HashSet<String>();
		List<String> memberCards = null;
		List<String> memberCardsForNonVoters  = null;
		
		if(isOtherState == null)
			isOtherState = "false";
		else if(isOtherState != null && isOtherState.equalsIgnoreCase("true"))
			isOtherState = "true";
		else if(isOtherState != null && isOtherState.equalsIgnoreCase("yes"))
			isOtherState = "true";
		else 
			isOtherState = "false";
		
		if(isOtherState.equalsIgnoreCase("false"))
		{
			 memberCards = tdpCadreDAO.getCardNumbersForSearch(sb.toString(), constiNo, mobileNo, trNo, srvyDt,distId,mandalId,townId);//vid registered
			 memberCardsForNonVoters = tdpCadreDAO.getNonVoterCardNumbersForSearch(sb.toString(), constiNo, mobileNo, trNo, srvyDt,distId,mandalId,townId);//fvid registered
 		}
		else if(isOtherState.equalsIgnoreCase("true"))
		{
			 memberCards = tdpCadreDAO.getOtherSttateCardNumbersForSearch(sb.toString(), constiNo, mobileNo, trNo, srvyDt,distId,mandalId,townId);//vid registerd
			 memberCardsForNonVoters = tdpCadreDAO.getOtherSttateNonVoterCardNumbersForSearch(sb.toString(), constiNo, mobileNo, trNo, srvyDt,distId,mandalId,townId);//fvid registered
		}
		
		if(memberCards!=null && memberCards.size()>0)
			voterMemberCards.addAll(memberCards);
		
		if(memberCardsForNonVoters!=null && memberCardsForNonVoters.size()>0)
			nonVoterMemberCards.addAll(memberCardsForNonVoters);
		
		//
		if(voterMemberCards!=null && voterMemberCards.size()>0 ){
			
			List<String> voterMemberCardsList = new ArrayList<String>(voterMemberCards);
			List<Object[]> vtrDetails = null;
			
			if(isOtherState.equalsIgnoreCase("false"))
			{
				vtrDetails = tdpCadreDAO.getCadrePartialDetailsByMemberShip(voterMemberCardsList);
			}
			else if(isOtherState.equalsIgnoreCase("true"))
			{
				vtrDetails = tdpCadreDAO.getOtherStateCadrePartialDetailsByMemberShip(voterMemberCardsList);
			}

			if(vtrDetails != null && vtrDetails.size() > 0){
				for(Object[] obj:vtrDetails){
					//Long voterId = Long.valueOf(obj[1].toString());
					UserAddress userAddress = new UserAddress()	;
					CadrePrintVO returnVO = new CadrePrintVO();
					Long userAddressId = 0l;
					if(obj[0] != null){
						if(obj[0].toString().trim().length() > 8){
							returnVO.setFirstCode(obj[0].toString().trim().substring(obj[0].toString().trim().length()-8));
						}else{
							returnVO.setFirstCode(obj[0].toString());
						}
					}else{
						returnVO.setFirstCode("");
					}
					//returnVO.setFirstCode(obj[0] != null ? obj[0].toString() : "");
					//int size = returnVO.getFirstCode().length();
					returnVO.setSecondCode(returnVO.getFirstCode());
					returnVO.setVoterId(obj[3] != null ?(Long)obj[3] : 0l);
					returnVO.setVoterCardNo(obj[4] != null ? obj[4].toString() : "");
					returnVO.setTdpCadreId((Long)obj[7]);
					returnVO.setMobileNo(obj[5] != null ? obj[5].toString() : "");
					returnVO.setCardNumber(obj[8] != null ? obj[8].toString() : "");
					if(returnVO.getCardNumber() != null && !returnVO.getCardNumber().isEmpty())
						returnVO.setPrintStatus("reprint");
					else
						returnVO.setPrintStatus("print");
					
					if(isOtherState.equalsIgnoreCase("false"))
					{
						if(returnVO.getVoterId()==null){
							List<String> names = tdpCadreTeluguNamesDAO.getTeluguVoterNameByTdpCadreId(returnVO.getTdpCadreId());
							if(names != null && names.size() > 0){
								returnVO.setVoterName(names.get(0));
							}
						}else{
							List<String> names = voterNamesDAO.getVoterTeluguNames((Long)obj[3] );
							if(names != null && names.size() > 0){
								returnVO.setVoterName(names.get(0));
							}
						}
						
						if(obj[6]!=null){
							userAddressId = Long.valueOf(obj[6].toString());
							userAddress = userAddressDAO.get(userAddressId);
							if(userAddress.getPanchayatId() != null)
							returnVO.setVillage(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getLocalName() : "");
						}						
					}
					else
					{
						returnVO.setVoterName(obj[2] != null ? obj[2].toString() : "");
						if(obj[6]!=null){
							userAddressId = Long.valueOf(obj[6].toString());
							userAddress = userAddressDAO.get(userAddressId);
							returnVO.setVillage(userAddress.getState() != null ? userAddress.getState().getStateName().toUpperCase()+" STATE DELEGATE ":" OTHER STATE DELEGATE");
						}
						else
						{
							returnVO.setVillage(" OTHER STATE DELEGATE ");
						}
					}
					//returnVO.setVillage(userAddress.getPanchayatId() != null ? panchayatDAO.get(userAddress.getPanchayatId()).getLocalName() : "");
					/*if(obj[6]!=null){
							userAddressId = Long.valueOf(obj[6].toString());
							userAddress = userAddressDAO.get(userAddressId);
							if(userAddress.getPanchayatId() != null)
							returnVO.setVillage(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getLocalName() : "");
						}	
					*/					
					returnVO.setStatus("success");	
					finalList.add(returnVO);
				}
			
			}
		}
		
		if(nonVoterMemberCards !=null && nonVoterMemberCards.size()>0){
			List<String> nonVoterMemberCardsList = new ArrayList<String>(nonVoterMemberCards);
			List<Object[]> vtrDetails = null;
			if(isOtherState.equalsIgnoreCase("false"))
			{
				vtrDetails = tdpCadreDAO.getCadrePartialDetailsByMemberShipIdForNonVoters(nonVoterMemberCardsList);
			}
			else if(isOtherState.equalsIgnoreCase("true"))
			{
				vtrDetails = tdpCadreDAO.getOtherStateCadrePartialDetailsByMemberShipIdForNonVoters(nonVoterMemberCardsList);
			}
			if(vtrDetails != null && vtrDetails.size() > 0){
				for(Object[] obj:vtrDetails){
					//Long voterId = Long.valueOf(obj[1].toString());
					UserAddress userAddress = new UserAddress()	;
					CadrePrintVO returnVO = new CadrePrintVO();
					Long userAddressId = 0l;
					
					//getUserAddressForCadreRegistered(userAddressId,userAddress,null);
					if(obj[0] != null){
						if(obj[0].toString().trim().length() > 8){
							returnVO.setFirstCode(obj[0].toString().trim().substring(obj[0].toString().trim().length()-8));
						}else{
							returnVO.setFirstCode(obj[0].toString());
						}
					}else{
						returnVO.setFirstCode("");
					}
					//returnVO.setFirstCode(obj[0] != null ? obj[0].toString() : "");
					//int size = returnVO.getFirstCode().length();
					returnVO.setSecondCode(returnVO.getFirstCode());
					returnVO.setTdpCadreId((Long)obj[3]);
					returnVO.setMobileNo(obj[4] != null ? obj[4].toString() : "");
					returnVO.setCardNumber(obj[6] != null ? obj[6].toString() : "");
					if(returnVO.getCardNumber() != null && !returnVO.getCardNumber().isEmpty())
					returnVO.setPrintStatus("reprint");
					else
						returnVO.setPrintStatus("print");
					if(isOtherState.equalsIgnoreCase("false"))
					{
						if(returnVO.getVoterId()==null){
							List<String> names = tdpCadreTeluguNamesDAO.getTeluguVoterNameByTdpCadreId(returnVO.getTdpCadreId());
							if(names != null && names.size() > 0){
								returnVO.setVoterName(names.get(0));
							}
						}
						if(obj[5]!=null){
							userAddressId = Long.valueOf(obj[5].toString());
							userAddress = userAddressDAO.get(userAddressId);
							if(userAddress.getPanchayatId() != null)
							returnVO.setVillage(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getLocalName() : "");
						} 
					}
					else
					{
						returnVO.setVoterName(obj[2] != null ? obj[2].toString() : "");	
						if(obj[5]!=null){
							userAddressId = Long.valueOf(obj[5].toString());
							userAddress = userAddressDAO.get(userAddressId);
							returnVO.setVillage(userAddress.getState() != null ? userAddress.getState().getStateName().toUpperCase()+" STATE DELEGATE ":" OTHER STATE DELEGATE");
						} else
						{
							returnVO.setVillage(" OTHER STATE DELEGATE ");
						}
					}
					//returnVO.setVillage(userAddress.getPanchayatId() != null ? panchayatDAO.get(userAddress.getPanchayatId()).getLocalName() : "");
					/*
					 * if(obj[5]!=null){
							userAddressId = Long.valueOf(obj[5].toString());
							userAddress = userAddressDAO.get(userAddressId);
							if(userAddress.getPanchayatId() != null)
							returnVO.setVillage(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getLocalName() : "");
						} 
					*/
					
					returnVO.setStatus("success");	
					finalList.add(returnVO);
				}
				
			}
		}
		
	
}catch(Exception e){
	LOG.error("Exception Raised in getTDPCadreDetailsBySearch",e);
}
	
	return finalList;
}
	

public List<CadrePrintVO> getTDPCadreDetailsByMemberShip(CadrePrintInputVO input){
	
	List<CadrePrintVO> finalList = new ArrayList<CadrePrintVO>();
	try{
		
		//USER CHECKING
		if(input.getUname() == null || input.getUname().trim().length()  == 0)
		 {
				CadrePrintVO returnVO = new CadrePrintVO();
				returnVO.setStatus("Invalid");
				finalList.add(returnVO);
				 return finalList;
		   
		 }
		 List validCheck = cardPrintUserDAO.checkUserEixsts(input.getUname(),input.getPwd());
		 if(validCheck == null || validCheck.size() == 0)
		 {
			 CadrePrintVO returnVO = new CadrePrintVO();
			 returnVO.setStatus("Invalid");
			 finalList.add(returnVO);
			 return finalList;
		 }
		 
		 Long enrollmentId = input.getEnrollmentId();
			
		if(enrollmentId == null)
			enrollmentId = IConstants.PRESENT_CADRE_ENROLLMENT_YEAR;
			
		 //QUERY BUILDING
		StringBuffer sb = new StringBuffer();
		
		String isOtherState = input.getIsOtherState();
		if(isOtherState == null)
			isOtherState = "false";
		else if(isOtherState != null && isOtherState.equalsIgnoreCase("true"))
			isOtherState = "true";
		else if(isOtherState != null && isOtherState.equalsIgnoreCase("yes"))
			isOtherState = "true";
		else 
			isOtherState = "false";
		
		if(isOtherState.trim().equalsIgnoreCase("false"))
		{
			if(input.getMemberShipNumber() != null && input.getMemberShipNumber().trim().length() > 0)
			{
				/*String memberShipNumber = "AP14"+input.getMemberShipNumber() ;
				String memberShipNumber1 = "TS14"+input.getMemberShipNumber() ;
			 	StringBuilder queryStr = new StringBuilder();
			 	sb.append(" and ( model.memberShipNo ='"+input.getMemberShipNumber().trim()+"' OR  model.memberShipNo ='"+memberShipNumber.trim()+"' OR model.memberShipNo ='"+memberShipNumber1.trim()+"') ");*/
				sb.append(" and  model.memberShipNo ='"+input.getMemberShipNumber().trim()+"' ");
			}
		}
		else
		{
			if(input.getMemberShipNumber() != null && input.getMemberShipNumber().trim().length() > 0)
			{
				String memberShipNumber1 = input.getMemberShipNumber() ;
			 	sb.append(" and (model.memberShipNo ='"+memberShipNumber1.trim()+"') ");
			}
		}
		
		if(enrollmentId != null)
			sb.append(" and model1.enrollmentYear.enrollmentYearId = "+enrollmentId+" ");
		
		//QUERIES
		Set<String> voterMemberCards = new HashSet<String>();
		Set<String> nonVoterMemberCards = new HashSet<String>();
		
		List<String> memberCards = null;
		List<String> memberCardsForNonVoters = null;
		
		if(isOtherState.equalsIgnoreCase("false"))
		{
			 memberCards = tdpCadreDAO.getCardNumbersForSearch(sb.toString(),  null, null, null, null,null,null,null);
			 memberCardsForNonVoters = tdpCadreDAO.getNonVoterCardNumbersForSearch(sb.toString(), null, null, null, null,null,null,null);
		}
		else if(isOtherState.equalsIgnoreCase("true"))
		{
			 memberCards = tdpCadreDAO.getOtherSttateCardNumbersForSearch(sb.toString(), null, null, null, null,null,null,null);
			 memberCardsForNonVoters = tdpCadreDAO.getOtherSttateNonVoterCardNumbersForSearch(sb.toString(), null, null, null, null,null,null,null);
		}
		
		if(memberCards!=null && memberCards.size()>0)
			voterMemberCards.addAll(memberCards);
		
		if(memberCardsForNonVoters!=null && memberCardsForNonVoters.size()>0)
			nonVoterMemberCards.addAll(memberCardsForNonVoters);
		
		if(voterMemberCards!=null && voterMemberCards.size()>0 ){
			
			List<String> voterMemberCardsList = new ArrayList<String>(voterMemberCards);
			List<Object[]> vtrDetails = null;
			if(isOtherState.trim().equalsIgnoreCase("false"))
			{
				vtrDetails = tdpCadreDAO.getCadreDetailsByMemberShipId(voterMemberCardsList);
			}
			else if(isOtherState.trim().equalsIgnoreCase("true"))
			{
				vtrDetails = tdpCadreDAO.getOtherStateCadreDetailsByMemberShipId(voterMemberCardsList);
			}
			if(vtrDetails != null && vtrDetails.size() > 0){
				for(Object[] obj:vtrDetails){
					//Long voterId = Long.valueOf(obj[1].toString());
					
					UserAddress userAddress = new UserAddress()	;
					CadrePrintVO returnVO = new CadrePrintVO();
					
					Long userAddressId = 0l;
					if(obj[12]!=null){
						userAddressId = Long.valueOf(obj[12].toString());
					} 
					
					//getVoterAddressDetails(voterId,userAddress,null);
					
					getUserAddressForCadreRegistered(userAddressId,userAddress,null);
					if(obj[0] != null){
						if(obj[0].toString().trim().length() > 8){
							returnVO.setFirstCode(obj[0].toString().trim().substring(obj[0].toString().trim().length()-8));
						}else{
							returnVO.setFirstCode(obj[0].toString());
						}
					}else{
						returnVO.setFirstCode("");
					}
					//returnVO.setFirstCode(obj[0] != null ? obj[0].toString() : "");
					//int size = returnVO.getFirstCode().length();
					returnVO.setSecondCode(returnVO.getFirstCode());
					returnVO.setVoterId(obj[4] != null ?(Long)obj[4] : 0l);
					returnVO.setVoterCardNo(obj[5] != null ? obj[5].toString() : "");
					returnVO.setDataSourceType(obj[6] != null ? obj[6].toString() : "");
					returnVO.setTdpCadreId(obj[7] != null ? Long.valueOf(obj[7].toString()) : 0l);
					returnVO.setRefNumber(obj[8] != null ? obj[8].toString() : "");
					if(obj[13] != null && !obj[13] .toString().isEmpty())
						returnVO.setPrintStatus("reprint");
						else
							returnVO.setPrintStatus("print");	
					returnVO.setMobileNo(obj[9] != null ? obj[9].toString() : "");
					try{
						if(obj[10] != null){
							String photoType = obj[10].toString();
							/*if(photoType.equalsIgnoreCase("NEW"))
							{
								String url = "https://mytdp.com/images/cadre_images/"+obj[11].toString();
								returnVO.setVoterImgPath(url);
							
							}
							else if(photoType.equalsIgnoreCase("CADRE"))
							{							
									String url = "https://mytdp.com/images/cadre_images/"+obj[11].toString();
									returnVO.setVoterImgPath(url);
							}
							else
							{
								returnVO.setVoterImgPath("https://mytdp.com/"+IConstants.VOTER_IMG_FOLDER_PATH+"/"+cadreDetailsService.getVoterImageUrlByVoterId((Long)obj[4]));
							}*/
							if(obj[11] != null && obj[11].toString().trim().length() > 0){
								String url = "https://mytdp.com/images/cadre_images/"+obj[11].toString();
								returnVO.setVoterImgPath(url);
							}
						}
					}
					catch(Exception e)
					{
						LOG.error(e);
					}
					if(isOtherState.equalsIgnoreCase("false"))
					{
						if(returnVO.getVoterId()==null){
							List<String> names = tdpCadreTeluguNamesDAO.getTeluguVoterNameByTdpCadreId(returnVO.getTdpCadreId());
							if(names != null && names.size() > 0){
								returnVO.setVoterName(names.get(0));
							}
						}else{
							List<String> names = voterNamesDAO.getVoterTeluguNames((Long)obj[4] );
							if(names != null && names.size() > 0){
								returnVO.setVoterName(names.get(0));
							}
						}
						
						returnVO.setVillage(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getLocalName() : "");
						returnVO.setMandal(userAddress.getTehsil() != null ?  userAddress.getTehsil().getLocalName() :"");
						returnVO.setConstituency(userAddress.getConstituency() != null ?  userAddress.getConstituency().getLocalName() : "");
						returnVO.setConstituencyType(userAddress.getConstituency() != null ? userAddress.getConstituency().getAreaType() : "");
						returnVO.setDistrict(userAddress.getDistrict() != null ?  userAddress.getDistrict().getLocalName():"");
						returnVO.setMuncipalityName(userAddress.getLocalElectionBody() != null ? userAddress.getLocalElectionBody().getNameLocal() : "" );
					}
					else
					{
						returnVO.setVoterName(obj[2] != null ? obj[2].toString() : "");		
						returnVO.setVillage(userAddress.getLocalArea() != null ? userAddress.getLocalArea().toUpperCase() : "");
					}
					//returnVO.setVillage(userAddress.getPanchayatId() != null ? panchayatDAO.get(userAddress.getPanchayatId()).getLocalName() : "");
					
					returnVO.setStatus("success");	
					finalList.add(returnVO);
				}
			}	
			
		}
		
		if(nonVoterMemberCards !=null && nonVoterMemberCards.size()>0){
			List<String> nonVoterMemberCardsList = new ArrayList<String>(nonVoterMemberCards);
			//List<Object[]> vtrDetails = tdpCadreDAO.getCadreDetailsByMemberShipIdForNonVoters(nonVoterMemberCardsList);
			List<Object[]> vtrDetails = null;
			if(isOtherState.trim().equalsIgnoreCase("false"))
			{
				vtrDetails = tdpCadreDAO.getCadreDetailsByMemberShipIdForNonVoters(nonVoterMemberCardsList);
			}
			else if(isOtherState.trim().equalsIgnoreCase("true"))
			{
				vtrDetails = tdpCadreDAO.getOtherStateCadreDetailsByMemberShipIdForNonVoters(nonVoterMemberCardsList);
			}
			if(vtrDetails != null && vtrDetails.size() > 0){
				for(Object[] obj:vtrDetails){
					//Long voterId = Long.valueOf(obj[1].toString());
					UserAddress userAddress = new UserAddress()	;
					CadrePrintVO returnVO = new CadrePrintVO();
					Long userAddressId = 0l;
					if(obj[10]!=null){
						userAddressId = Long.valueOf(obj[10].toString());
					} 
					getUserAddressForCadreRegistered(userAddressId,userAddress,null);
					if(obj[0] != null){
						if(obj[0].toString().trim().length() > 8){
							returnVO.setFirstCode(obj[0].toString().trim().substring(obj[0].toString().trim().length()-8));
						}else{
							returnVO.setFirstCode(obj[0].toString());
						}
					}else{
						returnVO.setFirstCode("");
					}
					//returnVO.setFirstCode(obj[0] != null ? obj[0].toString() : "");
					//int size = returnVO.getFirstCode().length();
					returnVO.setSecondCode(returnVO.getFirstCode());
					/*returnVO.setVoterId(obj[4] != null ?(Long)obj[4] : 0l);
					returnVO.setVoterCardNo(obj[5] != null ? obj[5].toString() : "");*/
					returnVO.setDataSourceType(obj[4] != null ? obj[4].toString() : "");
					returnVO.setTdpCadreId(obj[5] != null ? Long.valueOf(obj[5].toString()) : 0l);
					returnVO.setRefNumber(obj[6] != null ? obj[6].toString() : "");
					if(obj[11] != null && !obj[11] .toString().isEmpty())
						returnVO.setPrintStatus("reprint");
						else
							returnVO.setPrintStatus("print");	
					returnVO.setMobileNo(obj[7] != null ? obj[7].toString() : "");
					if(obj[11] != null && !obj[11] .toString().isEmpty())
						returnVO.setPrintStatus("reprint");
						else
							returnVO.setPrintStatus("print");	
					try{
						 if(obj[8] != null){
						
							String photoType = obj[8].toString();
							/*if(photoType.equalsIgnoreCase("NEW"))
							{
								String url = "https://mytdp.com/images/cadre_images/"+obj[9].toString();
								returnVO.setVoterImgPath(url);
							
							}
							else if(photoType.equalsIgnoreCase("CADRE"))
							{							
									String url = "https://mytdp.com/images/cadre_images/"+obj[9].toString();
									returnVO.setVoterImgPath(url);
							}
							else
							{
								String url = "https://mytdp.com/"+IConstants.VOTER_IMG_FOLDER_PATH+"/"+userAddress.getConstituency().getConstituencyId().toString().trim()+"/"+"Part"+userAddress.getBooth().getPartNo().trim()+"/"+returnVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";
								returnVO.setVoterImgPath(url);
							}*/
							if(obj[9] != null && obj[9].toString().trim().length() > 0){
								String url = "https://mytdp.com/images/cadre_images/"+obj[9].toString();
								returnVO.setVoterImgPath(url);
							}
							
						}
						else if(obj[8] == null && isOtherState.equalsIgnoreCase("true"))
						{
							if(obj[9] != null && obj[9].toString().trim().length() > 0){
								returnVO.setVoterImgPath("https://mytdp.com/images/cadre_images/"+obj[9].toString());
							}
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
					if(isOtherState.equalsIgnoreCase("false"))
					{
						if(returnVO.getVoterId()==null){
							List<String> names = tdpCadreTeluguNamesDAO.getTeluguVoterNameByTdpCadreId(returnVO.getTdpCadreId());
							if(names != null && names.size() > 0){
								returnVO.setVoterName(names.get(0));
							}
						}else{
							List<String> names = voterNamesDAO.getVoterTeluguNames((Long)obj[4] );
							if(names != null && names.size() > 0){
								returnVO.setVoterName(names.get(0));
							}
						}
						
						returnVO.setVillage(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getLocalName() : "");
						returnVO.setMandal(userAddress.getTehsil() != null ?  userAddress.getTehsil().getLocalName() :"");
						returnVO.setConstituency(userAddress.getConstituency() != null ?  userAddress.getConstituency().getLocalName() : "");
						returnVO.setConstituencyType(userAddress.getConstituency() != null ? userAddress.getConstituency().getAreaType() : "");
						returnVO.setDistrict(userAddress.getDistrict() != null ?  userAddress.getDistrict().getLocalName():"");
						returnVO.setMuncipalityName(userAddress.getLocalElectionBody() != null ? userAddress.getLocalElectionBody().getNameLocal() : "" );
					}
					else
					{
						returnVO.setVoterName(obj[2] != null ? obj[2].toString() : "");	
						returnVO.setVillage(userAddress.getLocalArea() != null ? userAddress.getLocalArea().toUpperCase() : "");
					}
					//returnVO.setVillage(userAddress.getPanchayatId() != null ? panchayatDAO.get(userAddress.getPanchayatId()).getLocalName() : "");
					
					returnVO.setStatus("success");	
					finalList.add(returnVO);
				}
				
			}
		}
		
	
}catch(Exception e){
	LOG.error("Exception Raised in getTDPCadreDetailsBySearch",e);
}
	
	return finalList;
}




	public String updatePrintedCardDetails(final List<CardNFCDetailsVO> inputList){
		LOG.debug("Entered Into updatePrintedCardDetails");
		String returnMsg = "";
		try {
			
			if(inputList!=null && inputList.size()>0){
				
				
				try{
					returnMsg = (String) transactionTemplate.execute(new TransactionCallback() {
						 public Object doInTransaction(TransactionStatus status) {
							 for(CardNFCDetailsVO cardNFCDetailsVO: inputList)
							 {		
								 List<String> checkForNumber =tdpCadreDAO.chechForCardNumber(cardNFCDetailsVO.getNfcNumber());
									if(checkForNumber.size() == 0)
									{
										Integer count =  tdpCadreDAO.updateNFCCardNumberByTdpCadreId(cardNFCDetailsVO.getTdpCadreId(),cardNFCDetailsVO.getNfcNumber());
									}
								 	
							 }
							 return "SUCCESS";
						 }});
				}catch (Exception e) {
					LOG.error("Exception Raised in updatePrintedCardDetails" + e);
					returnMsg = "FAIL";
				}
			}else{
				returnMsg = "FAIL";
			}
			
		} catch (Exception e) {
			returnMsg = "EXCEPTION";
			LOG.error("Exception Raised in updatePrintedCardDetails",e);
		}
		
		
		return returnMsg;
	}
	public String updatePrintedCardInfo(final List<CardNFCDetailsVO> inputList){
		LOG.debug("Entered Into updatePrintedCardDetails");
		final DateUtilService date = new DateUtilService();
		String returnMsg = "";
		try {
			if(inputList!=null && inputList.size()>0){
				
				if(inputList.get(0).getUname() == null || inputList.get(0).getUname().trim().length()  == 0)
				 {
						CadrePrintVO returnVO = new CadrePrintVO();
						returnMsg = "Invalid";
						return returnMsg;
				   
				 }
				final List validCheck = cardPrintUserDAO.checkUserEixsts(inputList.get(0).getUname(),inputList.get(0).getPwd());
				 if(validCheck == null || validCheck.size() == 0)
				 {
					 CadrePrintVO returnVO = new CadrePrintVO();
					 returnMsg = "Invalid";
					 return returnMsg;
				 }
				
				try{
					returnMsg = (String) transactionTemplate.execute(new TransactionCallback() {
						 public Object doInTransaction(TransactionStatus status) {
							 for(CardNFCDetailsVO cardNFCDetailsVO: inputList)
							 {		
								 List<Object[]> cardNumber = tdpCadreDAO.checkCardNumberExists(cardNFCDetailsVO.getTdpCadreId());
								 CadreCardNumberUpdation  cadreCardNumberUpdation = new CadreCardNumberUpdation();
								 
								 Integer count =  tdpCadreDAO.updateNFCCardNumberByTdpCadreId(cardNFCDetailsVO.getTdpCadreId(),cardNFCDetailsVO.getNfcNumber());
								 if(count != null && count > 0)
								 {
									 Object[] object= cardNumber.get(0);
									 if(object[0] != null && !object[0].toString().isEmpty())
									 cadreCardNumberUpdation.setCardNumber(object[0].toString());
									 cadreCardNumberUpdation.setTdpCadreId(cardNFCDetailsVO.getTdpCadreId());
									 cadreCardNumberUpdation.setInsertedTime(date.getCurrentDateAndTime());
									 cadreCardNumberUpdation.setUpdatedTime(date.getCurrentDateAndTime());
									 cadreCardNumberUpdation.setCardPrintUserId((Long)validCheck.get(0));
									 cadreCardNumberUpdationDAO.save(cadreCardNumberUpdation);	
									 TdpCadre tdpCadre = tdpCadreDAO.get(cardNFCDetailsVO.getTdpCadreId());
									 // Image Updataion
									 if(cardNFCDetailsVO.getImageBase64String() != null && 
											 cardNFCDetailsVO.getImageBase64String().trim().length() > 0){
											  LOG.error("15");
												String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
												String filePath = IConstants.STATIC_CONTENT_FOLDER_URL + "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + object[1].toString()+".jpg";
												//String filePath = "D:/" + tdpCadre.getMemberShipNo()+".jpg";

												cardNFCDetailsVO.setImageBase64String(cardNFCDetailsVO.getImageBase64String().replace("_", "/"));
												cardNFCDetailsVO.setImageBase64String(cardNFCDetailsVO.getImageBase64String().replace("-", "+"));
												boolean imgStatus = imageAndStringConverter.convertBase64StringToImage(cardNFCDetailsVO.getImageBase64String(),filePath);
												//System.out.println(cadreRegistrationVO.getImageBase64String());
												 LOG.error("BASE64: DP:"+filePath);
												 try{
												 if(cardNFCDetailsVO.getImageBase64String().length() > 55){
												     LOG.error("BASE64FIRST50C: "+cardNFCDetailsVO.getImageBase64String().substring(0, 50));
												 }else{
													 LOG.error("BASE64FIRST50C: "+cardNFCDetailsVO.getImageBase64String());
												 }
												 }catch(Exception ex){
													
												 }
												
												 if(imgStatus){
													 	
														tdpCadre.setImage(tdpCadre.getMemberShipNo()+".jpg");
														tdpCadreDAO.save(tdpCadre);
														LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
													}
									 } 
												 
													if(cardNFCDetailsVO.getVoterName() != null && cardNFCDetailsVO.getVoterName().trim().length() > 0){
														if(tdpCadre.getVoterId() == null)
														{
															List model  = tdpCadreTeluguNamesDAO.getModelByTdpCadreId(cardNFCDetailsVO.getTdpCadreId());
															if(model != null && model.size() > 0 )
															{
																TdpCadreTeluguNames tdpCadreTeluguNames = tdpCadreTeluguNamesDAO.get((Long) model.get(0));
																tdpCadreTeluguNames.setTeluguName(cardNFCDetailsVO.getVoterName() );
																tdpCadreTeluguNamesDAO.save(tdpCadreTeluguNames);
															}
															else
															{
																TdpCadreTeluguNames tdpCadreTeluguNames = new TdpCadreTeluguNames();
																tdpCadreTeluguNames.setTeluguName(cardNFCDetailsVO.getVoterName());
																tdpCadreTeluguNames.setTdpCadreId(cardNFCDetailsVO.getTdpCadreId());
																tdpCadreTeluguNamesDAO.save(tdpCadreTeluguNames);
															}
														}
														else
														{	
															VoterNames voterNames = null;
															List model1 = voterNamesDAO.gerVoterNamesModelByVoterId(tdpCadre.getVoterId());
															
															if(model1 != null && model1.size() > 0)
																voterNames = voterNamesDAO.get((Long) model1.get(0));
															else
																voterNames = new VoterNames(); 
																
															voterNames.setFirstName(cardNFCDetailsVO.getVoterName());
															voterNames.setVoterId(tdpCadre.getVoterId());
															voterNames.setLastName("");
															voterNamesDAO.save(voterNames);
															
														}
													}
							
								 }
							 }
							 return "SUCCESS";
						 }});
				}catch (Exception e) {
					LOG.error("Exception Raised in updatePrintedCardInfo" + e);
					returnMsg = "FAIL";
				}
			}else{
				returnMsg = "FAIL";
			}
			
		} catch (Exception e) {
			returnMsg = "EXCEPTION";
			LOG.error("Exception Raised in updatePrintedCardInfo",e);
		}
		
		
		return returnMsg;
	}
	
	
	/**
	 * 
	 * @param inputs
	 * @return returnVO
	 */
	/*public List<SinkVO> sinkMissingData(List<SinkVO> inputs)
	{
		
		LOG.error("IN SINK SERVICE");
		Long familyCount = 0l;
		String familyStr = "";
		Integer newFamilyVoters = 0;
		if(inputs != null)
		{
			LOG.error(inputs.toArray());
		}
		else
		{
			LOG.error("Empty");
		}
		List<SinkVO> returnList = new ArrayList<SinkVO>();
		try 
		{
			Map<Long,List<String>> voterUidMap = new HashMap<Long, List<String>>();
			Set<Long> usIds = new java.util.HashSet<Long>();
			Set<String> uniqueids = new java.util.HashSet<String>();
			
			for(SinkVO sinkVO : inputs)
			{
				if(sinkVO.getUsId() != null)
				{
					usIds.add(sinkVO.getUsId());
				}
				
				
				if(sinkVO.getFid() != null && sinkVO.getFid().toString().trim().length() > 0 && !sinkVO.getFid().toString().trim().equalsIgnoreCase("null") && sinkVO.getFid().longValue() > 0)
				{
					familyCount = familyCount + 1;
					familyStr = familyStr + "," + sinkVO.getUid();
					List<Object[]> result = tdpCadreDAO.checkForExists(sinkVO.getUid());
					if(result != null && result.size() > 0)
					{
						for (Object[] objects : result)
						{
							if(objects[1] != null)
							{
								if(objects[1].toString().equalsIgnoreCase("NA"))
								{
									sinkVO.setStatus("duplicate");
									sinkVO.setVid(0l);
									returnList.add(sinkVO);
								}
								else if(objects[1].toString().equalsIgnoreCase("H"))
								{
									sinkVO.setStatus("false");
									sinkVO.setVid(0l);
									returnList.add(sinkVO);
								}
								else if(objects[1].toString().equalsIgnoreCase("Y"))
								{
									sinkVO.setStatus("false");
									sinkVO.setVid(0l);
									returnList.add(sinkVO);
								}
							}
						}
						
					}
					else
					{
						sinkVO.setStatus("false");
						sinkVO.setVid(0l);
						returnList.add(sinkVO);
					}
					Long count = tdpCadreDAO.checkForExists(sinkVO.getUid());
					if(count > 0)
					{
						sinkVO.setStatus("duplicate");
						sinkVO.setVid(0l);
						returnList.add(sinkVO);
					}
					else
					{
						uniqueids.add(sinkVO.getUid());
						sinkVO.setStatus("false");
						sinkVO.setVid(0l);
						returnList.add(sinkVO);
					}
					
				}
				else if(sinkVO.getVid() != null && sinkVO.getVid().toString().trim().length() > 0 && !sinkVO.getVid().toString().trim().equalsIgnoreCase("null")  && sinkVO.getVid().longValue() > 0)
				{
					List<String> uidsList = voterUidMap.get(sinkVO.getVid());
					if(uidsList == null)
					{
						uidsList = new ArrayList<String>();
						voterUidMap.put(sinkVO.getVid(), uidsList);
					}
					uidsList.add(sinkVO.getUid());
				}
			}
			
			Set<Long> voterIds = voterUidMap.keySet();
			
			LOG.error("TOTAL RECORDS : " + inputs.size());
			if(uniqueids != null && uniqueids.size() > 0)
			{
				Integer count1 = tdpCadreDAO.updateDetails(new ArrayList<String>(uniqueids));
				//LOG.error("FAMILY RECORDS : " + count1);
				newFamilyVoters = uniqueids.size() - count1;
				LOG.error("FAMILY RECORDS : " + uniqueids.size());
			}
			Integer matchedCount = 0;
			Integer missingCount = 0;
			String str = "";
			if(voterIds != null && voterIds.size() > 0)
			{
				List<Object[]> matchedVoters = tdpCadreDAO.getMissingDetails(voterIds);
				if(matchedVoters != null && matchedVoters.size() > 0)
				{
					LOG.error("MATCHED RECORDS : " + matchedVoters.size());
					LOG.error("MISSING RECORDS : " + (voterIds.size() - matchedVoters.size()));
					matchedCount = matchedVoters.size();
					missingCount = voterIds.size() - matchedVoters.size();
					for(Object[] obj : matchedVoters)
					{
						if(obj[0] != null)
						{
							voterUidMap.remove((Long)obj[0]);
						}
						
					}
				}
			}
			
			
			
			if(voterUidMap != null && voterUidMap.size() > 0)
			{
				for (Long voterId : voterUidMap.keySet())
				{
					List<String> uids = voterUidMap.get(voterId);
					if(uids != null)
					{
						SinkVO returnVO = new SinkVO();
						returnVO.setStatus("false");
						returnVO.setVid(voterId);
						returnVO.setUid(uids.get(0));
						returnVO.setFid(0l);
						returnList.add(returnVO);
						
						str = str + "," +returnVO.getUid();
					}
				}
			}
			
			//SAVING REQUESTED AND RESPONCE DATA FOR BACKUP PURPOSE
			
			
					try {
						String usrStr = "";
						if(usIds != null)
						{
							for (Long usId : usIds)
							{
								usrStr = usrStr + "::"  +  usId;
							}
						}
						TdpCadreVerfiedData tdpCadreVerfiedData = new TdpCadreVerfiedData();
						Gson gson = new Gson();
						tdpCadreVerfiedData.setRequestData(gson.toJson(inputs));
						tdpCadreVerfiedData.setResponceData(gson.toJson(returnList));
						tdpCadreVerfiedData.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						tdpCadreVerfiedData.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						tdpCadreVerfiedData.setReqSize(Long.valueOf(inputs.size()));
						tdpCadreVerfiedData.setResSize(Long.valueOf(returnList.size()));
						tdpCadreVerfiedData.setMatchedCount(Long.valueOf(matchedCount));
						tdpCadreVerfiedData.setMissingData(Long.valueOf(missingCount));
						tdpCadreVerfiedData.setFamilyCount(familyCount);
						tdpCadreVerfiedData.setNewFamilyCount(Long.valueOf(newFamilyVoters));
						tdpCadreVerfiedData.setFamilySinkUids(familyStr);
						tdpCadreVerfiedData.setMissingIds(str);
						tdpCadreVerfiedData.setUserId(usrStr);
						tdpCadreVerfiedDataDAO.save(tdpCadreVerfiedData);
					} catch (Exception e) {
						LOG.error("Exception Raised in updatePrintedCardDetails",e);
					}
			
		} 
		catch (Exception e) 
		{
			returnList = null;
			LOG.error("Exception Raised in updatePrintedCardDetails",e);
		}
		return returnList;
	}*/
	
	
	public List<SinkVO> sinkMissingData(List<SinkVO> inputs)
	{
		
		LOG.error("IN SINK SERVICE");
		
		Map<String,Set<String>> uniqueDupMap = null;
		Set<String> falseList = new java.util.HashSet<String>();
		Set<String>  dupList = new java.util.HashSet<String>();
		List<SinkVO> returnList = new ArrayList<SinkVO>();
		Set<Long> userIds = new HashSet<Long>();
		Map<String,SinkVO> allData = new HashMap<String,SinkVO>();
		Long tdpCadreVerfiedDataId = null;
		if(inputs != null && inputs.size() > 0)
		{
			LOG.error(inputs.toArray());
		}
		else
		{
			LOG.error("Empty");
			return null;
		}
		
		try 
		{
			Map<Long,List<String>> voterUidMap = new HashMap<Long, List<String>>();// HERE WE ARE 
			Set<Long> usIds = new java.util.HashSet<Long>();
			Set<String> uniqueids = new java.util.HashSet<String>();
			Set<String> newUniqueids = new java.util.HashSet<String>();
			for(SinkVO sinkVO : inputs)
			{
				if(sinkVO.getUsId() != null)
				{
					usIds.add(sinkVO.getUsId());
				}
			}	
			String access = getVerifyUserDetails(new ArrayList<Long>(usIds));
		if(access.equalsIgnoreCase("ALLOW"))
		{
			for(SinkVO sinkVO : inputs)
			{
			
				allData.put(sinkVO.getUid(), sinkVO);
				
				if(sinkVO.getFid() != null && sinkVO.getFid().toString().trim().length() > 0 && !sinkVO.getFid().toString().trim().equalsIgnoreCase("null") && sinkVO.getFid().longValue() > 0)
				{
					
					uniqueids.add(sinkVO.getUid());
					newUniqueids.add(sinkVO.getUid());
					userIds.add(sinkVO.getUsId());
				}
				else if(sinkVO.getVid() != null && sinkVO.getVid().toString().trim().length() > 0 && !sinkVO.getVid().toString().trim().equalsIgnoreCase("null")  && sinkVO.getVid().longValue() > 0)
				{
					List<String> uidsList = voterUidMap.get(sinkVO.getVid());
					if(uidsList == null)
					{
						uidsList = new ArrayList<String>();
						voterUidMap.put(sinkVO.getVid(), uidsList);
					}
					uidsList.add(sinkVO.getUid());
				}
			}
			Map<String,String> existingRecordsMap = new HashMap<String,String>();
			Set<String> nonDuplicateKeys = new HashSet<String>();
			if(uniqueids.size() > 0){
			      existingRecordsMap = getExistingFamilyRecordStatus(new ArrayList<String>(uniqueids),userIds);
			      for(String uniqueKey:existingRecordsMap.keySet()){
			    	  String status = existingRecordsMap.get(uniqueKey);
			    	  if(status!= null && status.equalsIgnoreCase("duplicate")){
			    	      uniqueids.remove(uniqueKey);//removing all keys contain status duplicate
			    	      newUniqueids.remove(uniqueKey);//removing all keys contain status duplicate
			    	  }else{
			    		  newUniqueids.remove(uniqueKey);//removing all existing keys in db
			    		  nonDuplicateKeys.add(uniqueKey);//adding all non pending status keys
			    	  }
			    	  
			      }
			}
			Set<Long> voterIds = voterUidMap.keySet();
			
			LOG.error("TOTAL RECORDS : " + inputs.size());
			
			// updating all new keys family details into History.
			if(newUniqueids != null && newUniqueids.size() > 0)
			{
				Integer count1 =  tdpCadreDAO.updateFamilyDetailsWithHistory(new ArrayList<String>(newUniqueids), new ArrayList<Long>(userIds));
			}
			
			
			
			if(uniqueids != null && uniqueids.size() > 0)
			{
					List<Object[]> result = tdpCadreDAO.getFamilyDetails(new ArrayList<String>(uniqueids));
					
					if(result != null && result.size() > 0)
					{
						uniqueDupMap = new HashMap<String, Set<String>>();
						for (Object[] objects : result)
						{
								String familyVoterId = objects[0] != null ? objects[0].toString().trim().toLowerCase() : IConstants.EMPTY_STRING;
								String voterName = objects[1] != null ? objects[1].toString().trim().toLowerCase() : IConstants.EMPTY_STRING;
								String relativeName = objects[2] != null ? objects[2].toString().trim().toLowerCase() : IConstants.EMPTY_STRING;
								String houseNo = objects[3] != null ? objects[3].toString().trim().toLowerCase() : IConstants.EMPTY_STRING;
								String mobileNo = objects[4] != null ? objects[4].toString().trim().toLowerCase() : IConstants.EMPTY_STRING;
								
								String key = familyVoterId +"-"+ voterName +"-"+ relativeName +"-"+ houseNo +"-"+ mobileNo;
								//'PRASAD-THAGIBHATHINA-HOUSENUM-9885675456-089786567' HFT-098789-098 
								//'PRASAD-THAGIBHATHINA-HOUSENUM-9885675456-089786567' HFT-098789-099 
								Set<String> unikeys =  uniqueDupMap.get(key);
								if(unikeys == null)
								{
									unikeys = new java.util.HashSet<String>();
								}
								unikeys.add(objects[5].toString());
								uniqueDupMap.put(key, unikeys);
						}
						
					}
				}			
				if(uniqueDupMap != null && uniqueDupMap.size() > 0)
				{
					
					for (Map.Entry<String, Set<String>> entry  : uniqueDupMap.entrySet())
					{
						Set<String> result = entry.getValue();
						if(result.size() == 1)
						{
							falseList.add(new ArrayList<String>(result).get(0));
						}
						else
						{
							String reqKey = "";
							for (String key:result) 
							{
								if(reqKey.length() == 0 && nonDuplicateKeys.contains(key)){
									reqKey = key;
								}else{
									dupList.add(key);
								}
								/*if(index == result.size()-1)
								{
									falseList.add(new ArrayList<String>(result).get(index));
								}
								else
								{
									dupList.add(new ArrayList<String>(result).get(index));
								}*/
							}
							if(reqKey.length() == 0){
								String rKey = new ArrayList<String>(result).get(result.size()-1);
								falseList.add(rKey);
								dupList.remove(rKey);
							}else{
								falseList.add(reqKey);
							}
						}
					}
					if(dupList != null && dupList.size() > 0)
					{
						Integer updatedNARecords = 	tdpCadreDAO.updateDetailsToDuplicate(new ArrayList<String>(dupList));
					}
					
					
					
				}
			if(dupList.size() > 0){
				//contains duplicate record keys already inserted in db
				List<String> dupExistingList = new ArrayList<String>();
				//contains duplicate record keys not present in db
				List<String> dupNewList = new ArrayList<String>();
				for(String key:dupList){
					if(nonDuplicateKeys.contains(key)){
						dupExistingList.add(key);
					}else{
						dupNewList.add(key);
					}
					SinkVO vo = allData.get(key);
					vo.setStatus("duplicate");
					returnList.add(vo);
				}
				if(dupExistingList.size() > 0){
					//updating status as duplicate to existing records
					verifiedDataStatusDAO.updateStatus(dupExistingList, new ArrayList<Long>(userIds),dateUtilService.getCurrentDateAndTime(),"duplicate");
				}
				if(dupNewList.size() > 0){
					//adding new records with duplicate status
					for(String duplicateKey:dupNewList){
						SinkVO duplicateRecord = allData.get(duplicateKey);
						if(duplicateRecord != null){
							VerifiedDataStatus verifiedDataResponse = new VerifiedDataStatus();
							verifiedDataResponse.setFamilyVoterId(duplicateRecord.getFid());
							verifiedDataResponse.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							verifiedDataResponse.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							verifiedDataResponse.setStatus("duplicate");
							verifiedDataResponse.setUniqueKey(duplicateKey);
							verifiedDataResponse.setUserId(duplicateRecord.getUsId());
							
							verifiedDataStatusDAO.save(verifiedDataResponse);
						}
					}
				}
			}
			if(falseList.size() > 0){
				//here we are adding existing records in db with false state
				List<String> falseExistingList = new ArrayList<String>();
				//here we are adding new records 
				List<String> falseNewList = new ArrayList<String>();
				for(String key:falseList){
					if(nonDuplicateKeys.contains(key)){
						String status = existingRecordsMap.get(key);
						if( status == null ||  status.equalsIgnoreCase("false")){
						   falseExistingList.add(key);
						}
					}else{
						falseNewList.add(key);
					}
				}
				if(falseExistingList.size() > 0){
					List<String> existingKeys = tdpCadreDAO.getExistingRecordsInfo(falseExistingList,new ArrayList<Long>(userIds));
					verifiedDataStatusDAO.updateStatus(existingKeys, new ArrayList<Long>(userIds),dateUtilService.getCurrentDateAndTime(),"true");
					falseExistingList.removeAll(existingKeys);
					if(falseExistingList.size() > 0){
					  for(String duplicateKey:falseExistingList){
						  SinkVO vo = allData.get(duplicateKey);
							vo.setStatus("false");
							returnList.add(vo);
					  }
					}
				}
				if(falseNewList.size() > 0){
					for(String duplicateKey:falseNewList){
						SinkVO duplicateRecord = allData.get(duplicateKey);
						if(duplicateRecord != null){
							VerifiedDataStatus verifiedDataResponse = new VerifiedDataStatus();
							verifiedDataResponse.setFamilyVoterId(duplicateRecord.getFid());
							verifiedDataResponse.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							verifiedDataResponse.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							verifiedDataResponse.setStatus("false");
							verifiedDataResponse.setUniqueKey(duplicateKey);
							verifiedDataResponse.setUserId(duplicateRecord.getUsId());
							
							verifiedDataStatusDAO.save(verifiedDataResponse);
							SinkVO vo = allData.get(duplicateKey);
							vo.setStatus("false");
							returnList.add(vo);
						}
					}
			   }
			}
			Integer matchedCount = 0;
			Integer missingCount = 0;
			String str = "";
			if(voterIds != null && voterIds.size() > 0)
			{
				List<Object[]> matchedVoters = tdpCadreDAO.getMissingDetails(voterIds);
				if(matchedVoters != null && matchedVoters.size() > 0)
				{
					LOG.error("MATCHED RECORDS : " + matchedVoters.size());
					LOG.error("MISSING RECORDS : " + (voterIds.size() - matchedVoters.size()));
					matchedCount = matchedVoters.size();
					missingCount = voterIds.size() - matchedVoters.size();
					for(Object[] obj : matchedVoters)
					{
						if(obj[0] != null)
						{
							voterUidMap.remove((Long)obj[0]);
						}
						
					}
				}
			}
			
			/*if(inputs != null && inputs.size() > 0)
			{
				for (SinkVO vo : inputs) 
				{
					if(vo.getFid() != null && vo.getFid().toString().trim().length() > 0 && !vo.getFid().toString().trim().equalsIgnoreCase("null") && vo.getFid().longValue() > 0)
					{
						if(dupList.contains(vo.getUid()))
						{
							vo.setStatus("duplicate");
						}
						else
						{
							vo.setStatus("false");
						}
						returnList.add(vo);
					}
					
					//newFamilyVoters = (familyCount) - (dupList.size() + falseList.size());
					
				}
			}*/
			
			if(voterUidMap != null && voterUidMap.size() > 0)
			{
				for (Long voterId : voterUidMap.keySet())
				{
					List<String> uids = voterUidMap.get(voterId);
					if(uids != null)
					{
						SinkVO returnVO = new SinkVO();
						returnVO.setStatus("false");
						returnVO.setVid(voterId);
						returnVO.setUid(uids.get(0));
						returnVO.setFid(0l);
						returnList.add(returnVO);
						
						str = str + "," +returnVO.getUid();
					}
				}
			}
			
			//SAVING REQUESTED AND RESPONCE DATA FOR BACKUP PURPOSE
			
					try 
					{
						String usrStr = "";
						if(usIds != null)
						{
							for (Long usId : usIds)
							{
								usrStr = usrStr + "::"  +  usId;
							}
						}
						TdpCadreVerfiedData tdpCadreVerfiedData = new TdpCadreVerfiedData();
						//Gson gson = new Gson();
						//tdpCadreVerfiedData.setRequestData(gson.toJson(inputs));
						//tdpCadreVerfiedData.setResponceData(gson.toJson(returnList));
						tdpCadreVerfiedData.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						tdpCadreVerfiedData.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						tdpCadreVerfiedData.setReqSize(Long.valueOf(inputs.size()));
						tdpCadreVerfiedData.setResSize(Long.valueOf(returnList.size()));
						tdpCadreVerfiedData.setMatchedCount(Long.valueOf(matchedCount));
						tdpCadreVerfiedData.setMissingData(Long.valueOf(missingCount));
						//tdpCadreVerfiedData.setFamilyCount(familyCount);
						//tdpCadreVerfiedData.setNewFamilyCount(newFamilyVoters);
						//tdpCadreVerfiedData.setFamilySinkUids(familyStr);
						tdpCadreVerfiedData.setMissingIds(str);
						tdpCadreVerfiedData.setUserId(usrStr);
						tdpCadreVerfiedData = tdpCadreVerfiedDataDAO.save(tdpCadreVerfiedData);
						tdpCadreVerfiedDataId = tdpCadreVerfiedData.getTdpCadreVerfiedDataId();
					}
					catch (Exception e) 
					{
						LOG.error("Exception Raised in updatePrintedCardDetails Back up saving",e);
					}
		   }
		} 
		catch (Exception e) 
		{
			returnList = null;
			LOG.error("Exception Raised in updatePrintedCardDetails",e);
		}
		String saveKey = commonUtilsService.getDynamicValueOfAKey(IConstants.SAVE_VERIFY_DATA);
		if(saveKey != null && saveKey.equalsIgnoreCase(IConstants.ON))
		{
			saveVerifyRequestData(inputs,tdpCadreVerfiedDataId);
			saveVerifyResponceData(returnList, tdpCadreVerfiedDataId);
		}
		return returnList;
	}
	
	public ResultStatus saveVerifyRequestData(List<SinkVO> list,Long tdpCadreVerfiedDataId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			if(list != null && list.size() > 0)
			{
				for(SinkVO sinkVO : list)
				{
					try{
						VerifiedDataRequest verifiedDataRequest = new VerifiedDataRequest();
						verifiedDataRequest.setTdpCadreVerfiedDataId(tdpCadreVerfiedDataId);
						verifiedDataRequest.setCadreSurveyUserId(sinkVO.getUsId());
						verifiedDataRequest.setFamilyVoterId(sinkVO.getFid());
						verifiedDataRequest.setVoterId(sinkVO.getVid());
						verifiedDataRequest.setUniqueKey(sinkVO.getUid());
						verifiedDataRequest.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						verifiedDataRequest.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						verifiedDataRequestDAO.save(verifiedDataRequest);
					}catch(Exception e)
					{
						LOG.error(e);
					}
				}
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		}catch(Exception e)
		{
			LOG.error("Exception occured in saveVerifyRequestData Method");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
		}
		return resultStatus;
	}
	
	public ResultStatus saveVerifyResponceData(List<SinkVO> list,Long tdpCadreVerfiedDataId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			if(list != null && list.size() > 0)
			{
				for(SinkVO sinkVO : list)
				{
					try{
						VerifiedDataResponse verifiedDataResponse = new VerifiedDataResponse();
						verifiedDataResponse.setTdpCadreVerfiedDataId(tdpCadreVerfiedDataId);
						verifiedDataResponse.setCadreSurveyUserId(sinkVO.getUsId());
						verifiedDataResponse.setFamilyVoterId(sinkVO.getFid());
						verifiedDataResponse.setVoterId(sinkVO.getVid());
						verifiedDataResponse.setUniqueKey(sinkVO.getUid());
						verifiedDataResponse.setStatus(sinkVO.getStatus());
						verifiedDataResponse.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						verifiedDataResponse.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						verifiedDataResponseDAO.save(verifiedDataResponse);
					}catch(Exception e)
					{
						LOG.error(e);
					}
				}
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		}catch(Exception e)
		{
			LOG.error("Exception occured in saveVerifyResponceData Method");
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setExceptionEncountered(e);
		}
		return resultStatus;
	}
	
	/*public void buildDuplicateLogic(Map<Long,List<SinkFamilyVO>> sinkFamilyMap,List<SinkVO> returnList)
	{
		try {
			Map<String,List<SinkFamilyVO>> duplicateVotersMap = new HashMap<String, List<SinkFamilyVO>>();
			Set<String> uniqueKeys = new java.util.HashSet<String>();
			for(Long familyVoterId : sinkFamilyMap.keySet())
			{
				
				List<SinkFamilyVO> familyList = sinkFamilyMap.get(familyVoterId);
				
				if(familyList != null && familyList.size() == 1)
				{
					SinkFamilyVO sinkFamilyVO = familyList.get(0);
					SinkVO returnVO = new SinkVO();
					returnVO.setStatus("false");
					returnVO.setUid(sinkFamilyVO.getUniqueKey());
					returnVO.setFid(sinkFamilyVO.getFamilyVoterId());
					returnVO.setVid(0l);
					returnList.add(returnVO);
				
				}
				else
				{
					for(SinkFamilyVO vo : familyList)
					{
						
						String key = vo.getFamilyVoterId() +"-"+ vo.getFirstName() +"-"+ vo.getRelativeName() +"-"+ vo.getHouseNo() +"-"+ vo.getMobileNo(); 
						List<SinkFamilyVO> duplicateNameList = duplicateVotersMap.get(key);
						if(duplicateNameList == null)
						{
							duplicateNameList = new ArrayList<SinkFamilyVO>();
							duplicateVotersMap.put(key, duplicateNameList);
						}
						SinkFamilyVO dupVO = new SinkFamilyVO();
						dupVO.setFamilyVoterId(vo.getFamilyVoterId());
						dupVO.setFirstName(vo.getFirstName());
						dupVO.setRelativeName(vo.getRelativeName());
						dupVO.setHouseNo(vo.getHouseNo());
						dupVO.setMobileNo(vo.getMobileNo());
						dupVO.setUniqueKey(vo.getUniqueKey());
						duplicateNameList.add(dupVO);
						uniqueKeys.add(vo.getUniqueKey());
					}
				}
			}
			
		   
		    
		    
			if(duplicateVotersMap != null && duplicateVotersMap.size() > 0)
			{
				if(uniqueKeys != null && uniqueKeys.size() > 0)
				{
					Integer updatedNARecords = 	tdpCadreDAO.updateDetailsToDuplicate(new ArrayList<String>(uniqueKeys));
				}
				 
				for(String key : duplicateVotersMap.keySet())
				{
					List<SinkFamilyVO> list = duplicateVotersMap.get(key);
					if(list != null && list.size() > 0)
					{
						for (int index = 0; index < list.size(); index++)
						{
							SinkFamilyVO vo = list.get(index);
							SinkVO returnVO = new SinkVO();
							if(index == list.size()-1)
							{
								returnVO.setStatus("false");
							}
							else
							{
								returnVO.setStatus("duplicate");
							}
							returnVO.setUid(vo.getUniqueKey());
							returnVO.setFid(vo.getFamilyVoterId());
							returnVO.setVid(0l);
						}
						
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}*/
	/**
	 * @author Sasi
	 * @date 26-11-2014
	 * @param userAddressId
	 * @return void 
	 */
	public void getUserAddressForCadreRegistered(Long userAddressId,UserAddress userAddress,CadreRegistrationVO cadreRegistrationVO){
		try {
			LOG.info("Entered into getUserAddressForCadreRegistered in CadreRegistrationService service");
			
			//List<Booth> locationDetails = boothPublicationVoterDAO.getVoterAddressDetails(voterId);
			List<UserAddress> locationDetails = userAddressDAO.getUserAddressByUserAddressId(userAddressId);
			if(locationDetails != null && locationDetails.size() > 0){
				UserAddress address = locationDetails.get(0);
				if(address!=null){
					if(address.getState() != null)
					{
						Long stateId = address.getState().getStateId();
						if(stateId.longValue() != 1L) //AP/TS
						{
							userAddress.setLocalArea(address.getState().getStateName()+" STATE DELEGATE ");
						}
						else
						{
							userAddress.setBooth(address.getBooth());
							userAddress.setConstituency(address.getConstituency());
							userAddress.setDistrict(address.getDistrict());
							userAddress.setState(address.getState());
							userAddress.setPanchayat(address.getPanchayat());
							userAddress.setWard(address.getWard());
							userAddress.setTehsil(address.getTehsil());
							userAddress.setHamlet(address.getHamlet());
							userAddress.setStreet(address.getStreet());
							userAddress.setParliamentConstituency(address.getParliamentConstituency());
							userAddress.setLocalElectionBody(address.getLocalElectionBody());
							userAddress.setLocalArea(address.getLocalArea());
						}
					}
					else
					{
						userAddress.setLocalArea(" OTHER STATE DELEGATE ");
					}
				}
				
			}
			else
			{
				userAddress.setLocalArea("  OTHER STATE DELEGATE ");
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getUserAddressForCadreRegistered in CadreRegistrationService service", e);
		}
	}
	
	public String  saveTabUsersLoginKeyDetails(TabRecordsStatusVO recordsStatusVO)
	{
		
		String status = null;
		try {
			
			if(recordsStatusVO != null && recordsStatusVO.getTabRecordsStatusVOList() != null && recordsStatusVO.getTabRecordsStatusVOList().size() > 0)
			{
				   for(TabRecordsStatusVO key:recordsStatusVO.getTabRecordsStatusVOList()){
				        TabUserKeys tabUserKeys = new TabUserKeys();
				        tabUserKeys.setImei(recordsStatusVO.getName());
				        tabUserKeys.setUserId(key.getUserId());
				        tabUserKeys.setKeys(key.getName());
				        tabUserKeys.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
				        tabUserKeys.setLogInUserId(recordsStatusVO.getUserId());
						
						tabUserKeysDAO.save(tabUserKeys);
				   }
						
			}
			status = "success";
			
		} catch (Exception e) {
			status = "failure";
			LOG.error("Exception Raised in saveTabUsersLoginKeyDetails "+e);
		}
		return status;
	}
	
	public TdpCadreVO searchTdpCadreDetailsBySearchCriteria(Long constituencyId,String searchName,String memberShipCardNo, String voterCardNo, String trNumber, String mobileNo)
	{
		TdpCadreVO returnVO = new TdpCadreVO();
    	try {
			
				StringBuilder queryStr = new StringBuilder();			
				
			/*	if(searchName != null && searchName.trim().length()>0 && !searchName.trim().equalsIgnoreCase("0") && !searchName.equalsIgnoreCase("null"))
				{
					queryStr.append(" and model.firstname like '%"+searchName+"%' ");
					
					if(constituencyId != null && constituencyId.longValue() != 0L && !constituencyId.toString().equalsIgnoreCase("null"))
					{
						queryStr.append(" and model.userAddress.constituency.constituencyId =:constituencyId ");
					}
					else
					{
						returnVO.setErrorStr(" Constituency is Required.");
						return returnVO;
					}
					
				}*/
				
				
				if((memberShipCardNo == null || memberShipCardNo.trim().length()==0  || memberShipCardNo.trim().equalsIgnoreCase("0") || memberShipCardNo.equalsIgnoreCase("null"))
						&& (mobileNo != null && mobileNo.trim().length()>0  && !mobileNo.trim().equalsIgnoreCase("0") && !mobileNo.equalsIgnoreCase("null")))
				{							
					queryStr.append(" and (model.mobileNo like '"+mobileNo.trim()+"') ");
				}
				if(memberShipCardNo != null && memberShipCardNo.trim().length()>0  && !memberShipCardNo.trim().equalsIgnoreCase("0") && !memberShipCardNo.equalsIgnoreCase("null"))
				{
					queryStr.append(" and (model.memberShipNo = '"+memberShipCardNo.trim()+"') ");
					
					if(mobileNo != null && mobileNo.trim().length()>0  && !mobileNo.trim().equalsIgnoreCase("0") && !mobileNo.equalsIgnoreCase("null"))
					{							
						queryStr.append(" or (model.mobileNo like '"+mobileNo.trim()+"') ");
					}
				}
				
				if(voterCardNo != null && voterCardNo.trim().length()>0  && !voterCardNo.trim().equalsIgnoreCase("0") && !voterCardNo.equalsIgnoreCase("null"))
				{
					queryStr.append(" and (model.voter.voterIDCardNo like '"+voterCardNo.trim()+"') ");
				}
				if(trNumber != null && trNumber.trim().length()>0 && !trNumber.trim().equalsIgnoreCase("0") && !trNumber.equalsIgnoreCase("null"))
				{
					queryStr.append(" and (model.refNo like '"+trNumber.trim()+"') ");
				}
				
				List<Object[]> cadreList = tdpCadreDAO.getTdpCadreDetailsBySearchCriteriaForCallCenter(constituencyId, queryStr.toString());
				
				List<TdpCadreVO> returnLsit = new ArrayList<TdpCadreVO>();
				if(cadreList != null && cadreList.size()>0)
				{
					SimpleDateFormat format  = new SimpleDateFormat("yy-MM-dd");
					for (Object[] cadre : cadreList) 
					{
						TdpCadreVO cadreVO = new TdpCadreVO();

						cadreVO.setId(cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L);
						cadreVO.setCadreName(cadre[1] != null ? cadre[1].toString():"");
						cadreVO.setRelativeName(cadre[2] != null ? cadre[2].toString():"");
						cadreVO.setGender(cadre[3] != null ? cadre[3].toString():"");
						if(cadre[4] != null){
							if(cadre[4].toString().trim().length() > 8){
								cadreVO.setMemberShipNo(cadre[4].toString().trim().substring(cadre[4].toString().trim().length()-8));
							}else{
								cadreVO.setMemberShipNo(cadre[4].toString());
							}
						}else{
							cadreVO.setMemberShipNo("");
						}
						//cadreVO.setMemberShipNo(cadre[4] != null  && cadre[4].toString().trim().length()>4  ? cadre[4].toString().substring(4):"");
						cadreVO.setTrNo(cadre[5] != null ? cadre[5].toString():"");
						cadreVO.setMobileNo(cadre[6] != null ? cadre[6].toString():"");
						cadreVO.setImageURL(cadre[7] != null ? cadre[7].toString():"");
						//cadreVO.setVoterCardNo(cadre[8] != null ? cadre[8].toString():"");

						if(cadre[11] != null && cadre[11].toString().trim().length()>0) 
						{
							cadreVO.setConstituency(cadre[11] != null ? cadre[11].toString().trim():"");					
						}
						
						if(cadre[12] != null && cadre[12].toString().trim().length()>0) 
						{
							Voter voter = voterDAO.get(cadre[12] != null ? Long.valueOf(cadre[12].toString().trim()):0L);
							if(voter != null)
							{
								cadreVO.setAge(voter.getAge());
								cadreVO.setVoterCardNo(voter.getVoterIDCardNo() != null ? voter.getVoterIDCardNo().toString():"");
							}						
						}
						if(cadre[13] != null && cadre[13].toString().trim().length()>0) 
						{
							Occupation occupation = occupationDAO.get(cadre[13] != null ? Long.valueOf(cadre[13].toString().trim()):0L);
							if(occupation != null)
							{
								cadreVO.setOccupation(occupation.getOccupation());
							}						
						}
						
						if(cadre[9] != null)
						{
							cadreVO.setAge(cadre[9] != null ? Long.valueOf(cadre[9].toString().trim()):0L);
						}
						else if((cadreVO.getAge() == null || cadreVO.getAge().toString().trim().length()<=0) && cadre[10]  != null)
						{
							String dateOfBirth = 	cadre[10] != null ? cadre[10].toString().substring(0,10):" "	;
							
							if(dateOfBirth != null && dateOfBirth.trim().length()>0)
							{
								Calendar startDate = new GregorianCalendar();
								Calendar endDate = new GregorianCalendar();
								
								startDate.setTime(format.parse(dateOfBirth.trim()));
								
								endDate.setTime(new Date());

								int diffYear = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
								
								cadreVO.setAge(Long.valueOf(String.valueOf(diffYear)));
							}
						}
						
						returnLsit.add(cadreVO);
					}
					
					returnVO.setResponseStatus("SUCCESS");					
					returnVO.setTotalCount(Long.valueOf(String.valueOf(returnLsit.size())));
					returnVO.setTdpCadreDetailsList(returnLsit);
				}
				else
				{
					if(memberShipCardNo != null && memberShipCardNo.trim().length()>0  && !memberShipCardNo.trim().equalsIgnoreCase("0") && !memberShipCardNo.equalsIgnoreCase("null"))
					{
						if(mobileNo != null && mobileNo.trim().length()>0  && !mobileNo.trim().equalsIgnoreCase("0") && !mobileNo.equalsIgnoreCase("null"))
						{
							returnVO.setResponseStatus(" No Cadre information is available with this Search details...");
						}
						else
						{
							returnVO.setResponseStatus(memberShipCardNo+" MemberShip Card Number is not Registered for any Cadre...");
						}
					}					
					else if(mobileNo != null && mobileNo.trim().length()>0  && !mobileNo.trim().equalsIgnoreCase("0") && !mobileNo.equalsIgnoreCase("null"))
					{	
						returnVO.setResponseStatus(mobileNo+" Mobile Number is not Registered for any Cadre...");
					}
				}
				returnVO.setResponseCode("");
		} catch (Exception e) {
			LOG.error("Exception raised in searchTdpCadreDetailsBySearchCriteria  method in WebServiceHandlerService",e);
			returnVO.setResponseStatus("FAILURE");
			returnVO.setResponseCode("SERVER ISSUE");			
		}
    	
    	return returnVO;
	}
	
	/**
	 * This Service is user for getting user details for verify cadre data details
	 * @param userId
	 * @return
	 */
	public String getVerifyUserDetails(List<Long> userIds)
	{
		String status = "";
		try {
			List<String> result = verifyAccessUsersDAO.getUserStatus(userIds);
			if(result != null && result.size() > 0)
			{
				if(result.contains("ALLOW"))
				{
					status = "ALLOW";
				}
				/*for (String string : result)
				{
					if(string != null && string.trim().toString().equalsIgnoreCase("ALLOW"))
					{
						status = "ALLOW";
					}
				}*/
				
			}
			else
			{
				status = "DISALLOW";
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getVerifyUserDetails ",e);
		}
		return status;
	}
	
	/**
	 * This Service is user for getting dynamic key value
	 * @param userId
	 * @return
	 */
	public String getDynamicKeyStatus(String key)
	{
		String status = "";
		try {
			List<String> result = dynamicKeysDAO.getDynamicKeyValue(key);
			if(result != null && result.size() > 0)
			{
				status = result.get(0);
			}
			else
			{
				status = null;
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getVerifyUserDetails ",e);
		}
		return status;
	}
	
	
	public List<UserDetailsVO> getCadreSurveyUserDetails(List<UserDetailsVO> cadreSurveyUserIds){
		List<UserDetailsVO> result = new ArrayList<UserDetailsVO>();
		
		try{
			List<Long> tabUserIds = new ArrayList<Long>();
			List<Long> webUserIds = new ArrayList<Long>();
			for(UserDetailsVO vo :cadreSurveyUserIds){
				if(vo.getName().equalsIgnoreCase("tab"))
				{
					if(!tabUserIds.contains(vo.getId()))
						tabUserIds.add(vo.getId());
				}
				else
				{
					if(!webUserIds.contains(vo.getId()))
						webUserIds.add(vo.getId());
				}
			}
			List<Object[]> cadreSurveyUserDetails  = new ArrayList<Object[]>();
			List<Object[]> webUsers = new ArrayList<Object[]>();
			if(tabUserIds != null && tabUserIds.size() > 0){
				 cadreSurveyUserDetails = cadreSurveyUserDAO.getCadreSurveyUserList(tabUserIds);
			}
			if(webUserIds != null && webUserIds.size() > 0){
				 webUsers = userDAO.getUserNames(webUserIds);
			}
			if(cadreSurveyUserDetails != null && cadreSurveyUserDetails.size() > 0){
				for(Object[] details:cadreSurveyUserDetails){
					UserDetailsVO vo1 = new UserDetailsVO();
					vo1.setId((Long)details[0]);
					vo1.setName(details[1]!= null ? details[1].toString() :"");
					result.add(vo1);
				}
			}
			if(webUsers != null && webUsers.size() > 0){
				for(Object[] user:webUsers){
					UserDetailsVO vo2 = new UserDetailsVO();
					vo2.setId((Long)user[0]);
					String fname = user[1]!= null ? user[1].toString() :"";
					String lname = user[2]!= null ? user[2].toString() :"";
					vo2.setName(fname +" " + lname);
					result.add(vo2);
				}
			}
		}catch(Exception e){
			LOG.error("Exception rised in getcadreSurveyUserDetails", e);
		}
		return result;
	}

	public Map<String,String> getExistingFamilyRecordStatus(List<String> uniqueKeys,Set<Long> userIds){
		Map<String,String> existingRecordsMap = new HashMap<String,String>();
		//0 uniqueKey,1 status
		List<Object[]> existingRecordsList = verifiedDataStatusDAO.getExistingFamilyRecordStatus(uniqueKeys, new ArrayList<Long>(userIds));
		for(Object[] existingRecord:existingRecordsList){
			existingRecordsMap.put(existingRecord[0].toString(),existingRecord[1].toString());
		}
	    return existingRecordsMap;
	}
	
	public boolean checkHasAccess(Long userId){
		Long count = cadreRegSyncAccessUsersDAO.checkHasAccess(userId);
		if(count.longValue() > 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
	public String updateCadreTravelDiscountDetails(final CadreTravelsVO input){
		LOG.debug("Entered Into updateCadreTravelDiscountDetails");
		String returnMsg = "";
		try {
			if(input != null ){
				try{
					returnMsg = (String) transactionTemplate.execute(new TransactionCallback() {
						 public Object doInTransaction(TransactionStatus status) {
							String status1 = "";							
							Boolean membershipCheck = commonUtilsService.checkValidMember(input.getMembershipNo());
							
							if(membershipCheck){
								TdpCadreTravelInfo tdpCadreTravelInfo  = new TdpCadreTravelInfo();
								tdpCadreTravelInfo.setCustId(Long.valueOf(input.getCustomerId()));
								if(input.getMembershipNo() != null){
									if(input.getMembershipNo().toString().trim().length() > 8){
										tdpCadreTravelInfo.setMembershipNo(input.getMembershipNo().toString().trim().substring(input.getMembershipNo().toString().trim().length()-8));
									}else{
										tdpCadreTravelInfo.setMembershipNo(input.getMembershipNo().toString());
									}
								}else{
									tdpCadreTravelInfo.setMembershipNo("");
								}
								//tdpCadreTravelInfo.setMembershipNo(input.getMembershipNo() != null && input.getMembershipNo().toString().trim().length()>4  ? input.getMembershipNo().substring(4):"");
										if(input.getTicketsCount() == null || input.getTicketsCount().isEmpty() || input.getTicketsCount().equals("0"))
										{
											status1 = "No of Ticketes Required";
											return status1; 
										}
										if(input.getDateOfJourney() == null || input.getDateOfJourney().isEmpty())
										{
											status1 = "Date Of Journey Required";
											return status1;
										}
										
									    tdpCadreTravelInfo.setTicketsCount(Long.valueOf(input.getTicketsCount()));
										if(input.getTicketCost() != null && !input.getTicketCost().isEmpty())
										{
										tdpCadreTravelInfo.setTicketCost(Double.valueOf(input.getTicketCost()));
										tdpCadreTravelInfo.setTotalAmount(Double.valueOf(input.getTicketCost()) * tdpCadreTravelInfo.getTicketsCount());
										}
										if(input.getDiscountPerc() != null && !input.getDiscountPerc().isEmpty() && !input.getDiscountPerc().equals("0"))
										{
											tdpCadreTravelInfo.setDiscountPerc(Double.valueOf(input.getDiscountPerc()));
											tdpCadreTravelInfo.setDiscountAmount((tdpCadreTravelInfo.getTotalAmount() * tdpCadreTravelInfo.getDiscountPerc())/100);
										}
										if(tdpCadreTravelInfo.getDiscountAmount() != 0.0 )
											tdpCadreTravelInfo.setAmountAfterDiscount(tdpCadreTravelInfo.getTotalAmount() - tdpCadreTravelInfo.getDiscountAmount());
										else
											tdpCadreTravelInfo.setAmountAfterDiscount(tdpCadreTravelInfo.getTotalAmount());
										
									tdpCadreTravelInfo.setTdpCadreTravelsId(1L);
									tdpCadreTravelInfo.setTdpCadreId(tdpCadreDAO.checkMemberExists(input.getMembershipNo()));
									tdpCadreTravelInfo.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
									tdpCadreTravelInfo.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
									
									tdpCadreTravelInfo.setDateOfJourney(convertToDateFormat(input.getDateOfJourney()));
									tdpCadreTravelInfo.setIsDeleted("N");
									tdpCadreTravelInfo = tdpCadreTravelInfoDAO.save(tdpCadreTravelInfo);
									status1 = "SUCCESS";
									
									
							}
							else{
								status1 = "MemberShip Card Number is not Registered for any Cadre...";
							}
							return status1;
						 }});
				}catch (Exception e) {
					LOG.error("Exception Raised in updateCadreTravelDiscountDetails" + e);
					returnMsg = "FAIL";
				}
			}else{
				returnMsg = "FAIL";
			}
			
		} catch (Exception e) {
			returnMsg = "EXCEPTION";
			LOG.error("Exception Raised in updatePrintedCardDetails",e);
		}		
		return returnMsg;
	}
	
	
	
	public String cancellationOfTicketDetails(final CadreTravelsVO input){
		LOG.debug("Entered Into cancellationOfTicketDetails");
		String returnMsg = "";
		try {
			if(input != null ){
				try{
					returnMsg = (String) transactionTemplate.execute(new TransactionCallback() {
						public Object doInTransaction(TransactionStatus status) {
							
						String status1 = "";
						List<TdpCadreTravelInfo> info = tdpCadreTravelInfoDAO.checkCustomerId(Long.valueOf(input.getCustomerId()));
						
						if(info != null && info.size() > 0){
							for(TdpCadreTravelInfo tdpCadreTravelInfo :info){
								if(Long.valueOf(input.getTicketsCount()) <= tdpCadreTravelInfo.getTicketsCount()){
									tdpCadreTravelInfo.setTicketsCount(tdpCadreTravelInfo.getTicketsCount() - Long.valueOf(input.getTicketsCount()));
									tdpCadreTravelInfo.setTotalAmount(tdpCadreTravelInfo.getTicketCost() * tdpCadreTravelInfo.getTicketsCount());
									if(tdpCadreTravelInfo.getCancelledTicketsCount()  == null)
									tdpCadreTravelInfo.setCancelledTicketsCount(0 + Long.valueOf(input.getTicketsCount()));
									else 
									tdpCadreTravelInfo.setCancelledTicketsCount( tdpCadreTravelInfo.getCancelledTicketsCount()  + Long.valueOf(input.getTicketsCount()));
									tdpCadreTravelInfo.setAmountAfterDiscount(tdpCadreTravelInfo.getTotalAmount() - tdpCadreTravelInfo.getDiscountAmount());
									tdpCadreTravelInfo.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
									tdpCadreTravelInfo.setIsDeleted("N");
									tdpCadreTravelInfo = tdpCadreTravelInfoDAO.save(tdpCadreTravelInfo);
									status1 = "SUCCESS";
								}
								else{
									status1 = "Enter Cancel Tickets Count Correctly";
								}
							}
						}
						else{
							status1  = "Enter Valid Customer Id";
						}
						return status1;
					}});
				}catch (Exception e) {
					LOG.error("Exception Raised in updatePrintedCardDetails" + e);
					returnMsg = "FAIL";
				}
			}else{
				returnMsg = "FAIL";
			}
			
		} catch (Exception e) {
			returnMsg = "EXCEPTION";
			LOG.error("Exception Raised in cancellationOfTicketDetails",e);
		}		
		return returnMsg;
	}
	
	public Date convertToDateFormat(String dateStr)
	{
		Date date = null;
		try {
			SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			date = originalFormat.parse(dateStr);
		} catch (Exception e) {
			LOG.error("Exception raised in convertToDateFormat method in CadreRegistrationAction Action",e);
		}
		return date;
		
	}
	
	public TdpCadreVO searchTdpCadreDetailsByVoterCardNo(String voterCardNo, String isFamilyVoter)
	{
		TdpCadreVO returnVO = new TdpCadreVO();
    	try {
    			StringBuilder queryStr = new StringBuilder();			
				if(voterCardNo != null && voterCardNo.trim().length()>0  && !voterCardNo.trim().equalsIgnoreCase("0") && !voterCardNo.equalsIgnoreCase("null"))
				{
					if(isFamilyVoter.equalsIgnoreCase("true"))
						queryStr.append(" and (model.familyVoter.voterIDCardNo like '"+voterCardNo.trim()+"') ");
					else
						queryStr.append(" and (model.voter.voterIDCardNo like '"+voterCardNo.trim()+"') ");
							
				}
				List<Object[]> cadreList = tdpCadreDAO.getTdpCadreDetailsByVoterCardNoForCallCenter(queryStr.toString());
				
				List<TdpCadreVO> returnLsit = new ArrayList<TdpCadreVO>();
				if(cadreList != null && cadreList.size()>0)
				{
					SimpleDateFormat format  = new SimpleDateFormat("yy-MM-dd");
					for (Object[] cadre : cadreList) 
					{
						TdpCadreVO cadreVO = new TdpCadreVO();

						cadreVO.setId(cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L);
						cadreVO.setCadreName(cadre[1] != null ? cadre[1].toString():"");
						cadreVO.setRelativeName(cadre[2] != null ? cadre[2].toString():"");
						cadreVO.setGender(cadre[3] != null ? cadre[3].toString():"");
						if(cadre[4] != null){
							if(cadre[4].toString().trim().length() > 8){
								cadreVO.setMemberShipNo(cadre[4].toString().trim().substring(cadre[4].toString().trim().length()-8));
							}else{
								cadreVO.setMemberShipNo(cadre[4].toString());
							}
						}else{
							cadreVO.setMemberShipNo("");
						}
						//cadreVO.setMemberShipNo(cadre[4] != null  && cadre[4].toString().trim().length()>4 ? cadre[4].toString().substring(4):"");
						cadreVO.setTrNo(cadre[5] != null ? cadre[5].toString():"");
						cadreVO.setMobileNo(cadre[6] != null ? cadre[6].toString():"");
						cadreVO.setImageURL(cadre[7] != null ? cadre[7].toString():"");
						//cadreVO.setVoterCardNo(cadre[8] != null ? cadre[8].toString():"");

						if(cadre[11] != null && cadre[11].toString().trim().length()>0) 
						{
							cadreVO.setConstituency(cadre[11] != null ? cadre[11].toString().trim():"");					
						}
						
						if(cadre[12] != null && cadre[12].toString().trim().length()>0) 
						{
							Voter voter = voterDAO.get(cadre[12] != null ? Long.valueOf(cadre[12].toString().trim()):0L);
							if(voter != null)
							{
								cadreVO.setAge(voter.getAge());
								cadreVO.setVoterCardNo(voter.getVoterIDCardNo() != null ? voter.getVoterIDCardNo().toString():"");
							}						
						}
						if(cadre[13] != null && cadre[13].toString().trim().length()>0) 
						{
							Occupation occupation = occupationDAO.get(cadre[13] != null ? Long.valueOf(cadre[13].toString().trim()):0L);
							if(occupation != null)
							{
								cadreVO.setOccupation(occupation.getOccupation());
							}						
						}
						
						if(cadre[9] != null)
						{
							cadreVO.setAge(cadre[9] != null ? Long.valueOf(cadre[9].toString().trim()):0L);
						}
						else if((cadreVO.getAge() == null || cadreVO.getAge().toString().trim().length()<=0) && cadre[10]  != null)
						{
							String dateOfBirth = 	cadre[10] != null ? cadre[10].toString().substring(0,10):" "	;
							
							if(dateOfBirth != null && dateOfBirth.trim().length()>0)
							{
								Calendar startDate = new GregorianCalendar();
								Calendar endDate = new GregorianCalendar();
								
								startDate.setTime(format.parse(dateOfBirth.trim()));
								
								endDate.setTime(new Date());

								int diffYear = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
								
								cadreVO.setAge(Long.valueOf(String.valueOf(diffYear)));
							}
						}
						
						returnLsit.add(cadreVO);
					}
					
					returnVO.setResponseStatus("SUCCESS");					
					returnVO.setTotalCount(Long.valueOf(String.valueOf(returnLsit.size())));
					returnVO.setTdpCadreDetailsList(returnLsit);
				}
				
				returnVO.setResponseCode("");
		} catch (Exception e) {
			LOG.error("Exception raised in searchTdpCadreDetailsBySearchCriteria  method in WebServiceHandlerService",e);
			returnVO.setResponseStatus("FAILURE");
			returnVO.setResponseCode("SERVER ISSUE");			
		}
    	
    	return returnVO;
	}
	
	public void saveDataToHistoryTable(TdpCadre tdpCadre){
		TdpCadreHistory history = new TdpCadreHistory();
		history.setTdpCadreId(tdpCadre.getTdpCadreId()) ;
		history.setVoterId(tdpCadre.getVoterId());
		history.setMemberShipNo(tdpCadre.getMemberShipNo()) ;
		history.setFirstname(tdpCadre.getFirstname()) ;
		history.setLastname(tdpCadre.getLastname()) ;
		history.setRelativename(tdpCadre.getRelativename()) ;
		history.setHouseNo(tdpCadre.getHouseNo());
		history.setImage(tdpCadre.getImage());
		history.setMobileNo(tdpCadre.getMobileNo());
		history.setLandMobileNo(tdpCadre.getLandMobileNo());
		history.setBloodGroupId(tdpCadre.getBloodGroupId());
		history.setGender(tdpCadre.getGender());
		history.setOccupationId(tdpCadre.getOccupationId());
		history.setEducationId(tdpCadre.getEducationId());
		history.setDateOfBirth(tdpCadre.getDateOfBirth());
		history.setAge(tdpCadre.getAge());
		history.setCasteStateId(tdpCadre.getCasteStateId());
		history.setPartyMemberSince(tdpCadre.getPartyMemberSince());
		history.setPreviousEnrollmentNo(tdpCadre.getPreviousEnrollmentNo());
		history.setInsertedTime(tdpCadre.getInsertedTime());
		history.setUpdatedTime(tdpCadre.getUpdatedTime());
		history.setEnrollmentYear(tdpCadre.getEnrollmentYear());
		history.setUpdatedUserId(tdpCadre.getUpdatedUserId());
		history.setInsertedUserId(tdpCadre.getInsertedUserId());
		history.setSurveyTime(tdpCadre.getSurveyTime());
		history.setIsDeleted(tdpCadre.getIsDeleted());
		history.setLatitude(tdpCadre.getLatitude());
		history.setLongititude(tdpCadre.getLongititude());
		history.setDataSourceType(tdpCadre.getDataSourceType());
		history.setUniqueKey(tdpCadre.getUniqueKey());
		history.setRefNo(tdpCadre.getRefNo());
		history.setUpdatedWebUserId(tdpCadre.getUpdatedWebUserId());
		history.setInsertedWebUserId(tdpCadre.getInsertedWebUserId());
		history.setCardNumber(tdpCadre.getCardNumber());
		history.setNomineeName(tdpCadre.getNomineeName());
		history.setNomineeAge(tdpCadre.getNomineeAge());
		history.setNomineeGender(tdpCadre.getNomineeGender());
		history.setAadheerNo(tdpCadre.getAadheerNo());
		history.setVoterRelationId(tdpCadre.getVoterRelationId());
		history.setCadreType(tdpCadre.getCadreType());
		history.setRelativeType(tdpCadre.getRelativeType());
		history.setPhotoType(tdpCadre.getPhotoType());
		history.setNameType(tdpCadre.getNameType());
		history.setIsRelative(tdpCadre.getIsRelative());
		history.setRelationTypeId(tdpCadre.getRelationTypeId());
		history.setCadreAadherNo(tdpCadre.getCadreAadherNo());
		history.setFamilyVoterId(tdpCadre.getFamilyVoterId());
		history.setPreviousMembershipYear(tdpCadre.getPreviousMembershipYear());
		history.setDispatchStatus(tdpCadre.getDispatchStatus());
		history.setNoVoterId(tdpCadre.getNoVoterId());
		history.setIsDuplicate(tdpCadre.getIsDuplicate());
		history.setCardNo(tdpCadre.getCardNo());
		history.setMemberId(tdpCadre.getMemberId());
		history.setNameLocal(tdpCadre.getNameLocal());
		history.setRefSurveyTime(tdpCadre.getRefSurveyTime());
		history.setConstituencyId(tdpCadre.getConstituencyId());
		history.setEmailId(tdpCadre.getEmailId());
		history.setIsPrintReady(tdpCadre.getIsPrintReady());
		if(tdpCadre.getTdpCadreOnline() != null){
		  history.setTdpCadreOnlineId(tdpCadre.getTdpCadreOnline().getTdpCadreOnlineId());
		}
		tdpCadreHistoryDAO.save(history);
	}
	
	public SurveyCadreResponceVO saveCommitteCadreRegistration(final Long userId,final List<CadreRegistrationVO> cadreRegistrationVOList,final List<CadrePreviousRollesVO> eligibleRoles,final String registrationType){
		final SurveyCadreResponceVO surveyCadreResponceVO = new SurveyCadreResponceVO();
	  synchronized("CADRECOMMITTEEADDCANDIDATE"){
		if(IConstants.ENABLE_LOGS_SAVE)
		{
			//Date d1 = new Date();
			updateRequestDetailsForBackup(cadreRegistrationVOList,registrationType);
			//Date d2 = new Date();
			//LOG.error(d2.getTime()-d1.getTime() + "IN MILLI SECONDS");
		}
		
		if(eligibleRoles != null && eligibleRoles.size()>0)
		{
			CadrePreviousRollesVO roleVO = eligibleRoles.get(0); 
			Long cadreRoleId = roleVO.getCadreRoleId();
			if(cadreRoleId != null)
			{
				String status = tdpCommitteeRoleDAO.getCommitteeStatus(cadreRoleId);
				if(status.equalsIgnoreCase("Y")){
					surveyCadreResponceVO.setStatus(" This Committee Is Already Confirmed, You Cannot Add Or Update Committee Members Info ");
					surveyCadreResponceVO.setResultCode(2);
					return surveyCadreResponceVO;
				}
				
				
			}
		}
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) 
				{
				 if(cadreRegistrationVOList != null && cadreRegistrationVOList.size() >0)
				 {
					for (CadreRegistrationVO cadreRegistrationVO : cadreRegistrationVOList)
					{
						if(cadreRegistrationVO != null)
						{
							if(cadreRegistrationVO.getTdpCadreId() != null && cadreRegistrationVO.getTdpCadreId().toString().trim().length()>0 && cadreRegistrationVO.getTdpCadreId().longValue() > 0 ) 
							{
								// for cadre committee details updations
								TdpCadre existingTdpCadre = tdpCadreDAO.get(cadreRegistrationVO.getTdpCadreId());
								
								List<TdpCadre> tdpCadresList = new ArrayList<TdpCadre>();
								tdpCadresList.add(existingTdpCadre);

								if(tdpCadresList.size() > 0)
								{
									List<Long> tdpCadreIds = new ArrayList<Long>();
									
									for (TdpCadre tdpCadre : tdpCadresList) 
									{
										tdpCadreIds.add(tdpCadre.getTdpCadreId());
									}
									
									cadrePreviousRolesDAO.inActiveCadreRollesDetailsById(tdpCadreIds);
									cadreParticipatedElectionDAO.inActiveCadreElectionDetailsById(tdpCadreIds);
										
									TdpCadre tdpCadre = tdpCadresList.get(0);
									saveDataToHistoryTable(tdpCadre);
									
									surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getMemberShipNo());
									if(cadreRegistrationVO.getGender() != null)
										tdpCadre.setGender(cadreRegistrationVO.getGender());
									if(cadreRegistrationVO.getDobStr() != null)
										tdpCadre.setDateOfBirth(convertToDateFormet( cadreRegistrationVO.getDobStr()));
									if(cadreRegistrationVO.getAge() != null)
										tdpCadre.setAge(cadreRegistrationVO.getAge());
									if(cadreRegistrationVO.getCandidateAadherNo() != null)
										tdpCadre.setCadreAadherNo(cadreRegistrationVO.getCandidateAadherNo());
									if(cadreRegistrationVO.getGender() != null)
										tdpCadre.setGender(cadreRegistrationVO.getGender());
									if(cadreRegistrationVO.getMobileNumber() != null)
										tdpCadre.setMobileNo(cadreRegistrationVO.getMobileNumber());
									if(cadreRegistrationVO.getCasteId() != null)
										tdpCadre.setCasteStateId(cadreRegistrationVO.getCasteId());
									if(cadreRegistrationVO.getEducationId() != null && cadreRegistrationVO.getEducationId().longValue() > 0)
										tdpCadre.setEducationId(cadreRegistrationVO.getEducationId());
									if(cadreRegistrationVO.getOccupationId() != null  && cadreRegistrationVO.getOccupationId().longValue() > 0)
										tdpCadre.setOccupationId(cadreRegistrationVO.getOccupationId());
									if(cadreRegistrationVO.getEmailId() != null)
										tdpCadre.setEmailId(cadreRegistrationVO.getEmailId());
									if(cadreRegistrationVO.getPreviousEnrollmentNumber() != null)
										tdpCadre.setPreviousEnrollmentNo(cadreRegistrationVO.getPreviousEnrollmentNumber());
									if(cadreRegistrationVO.getIsSmartPhone() != null)
										tdpCadre.setMobileType(cadreRegistrationVO.getIsSmartPhone());
									tdpCadre.setUpdatedWebUserId(cadreRegistrationVO.getCreatedUserId());
									tdpCadre.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
									tdpCadreDAO.save(tdpCadre);
									
									UserAddress userAddress = tdpCadre.getUserAddress();
									
									if(userAddress != null)
									{
										if(cadreRegistrationVO.getStreet() != null)
											userAddress.setStreet(cadreRegistrationVO.getStreet());
										userAddressDAO.save(userAddress);
									}
									
									List<CadrePreviousRollesVO> previousRollesPartList = cadreRegistrationVO.getPreviousRollesList();
									if(previousRollesPartList != null && previousRollesPartList.size() > 0)
									{
										for (CadrePreviousRollesVO rolesVO : previousRollesPartList)
										{
											if(rolesVO != null)
											{
												if(tdpCadre != null)
												{
													CadrePreviousRoles cadrePreviousRoles = new CadrePreviousRoles();
													cadrePreviousRoles.setTdpCadreId(tdpCadre.getTdpCadreId());
												
														if(rolesVO.getCadreCommitteeId() != null && rolesVO.getCadreCommitteeId().longValue() > 0 && rolesVO.getCadreCommitteeLevelId() != null && rolesVO.getCadreCommitteeLevelId().longValue() > 0 && rolesVO.getCadreRoleId() != null && rolesVO.getCadreRoleId().longValue() > 0 )
														{
															List<Long> cadreCommotteRoleIds = cadreCommitteeRoleDAO.getCadreCommitteRoleIdBySelection(rolesVO.getCadreCommitteeLevelId(), rolesVO.getCadreRoleId(), rolesVO.getCadreCommitteeId());
															if(cadreCommotteRoleIds != null && cadreCommotteRoleIds.size() > 0)
															{
																cadrePreviousRoles.setCadreCommitteeRoleId(cadreCommotteRoleIds.get(0));
															}
														}
														if(rolesVO.getFromDateStr() != null && rolesVO.getFromDateStr().trim().length() > 0 && !rolesVO.getFromDateStr().trim().equalsIgnoreCase("null"))
														{
															cadrePreviousRoles.setFromDate(convertToDateFormet(rolesVO.getFromDateStr()));
														}
														if(rolesVO.getToDateStr() != null && rolesVO.getToDateStr().trim().length() > 0 && !rolesVO.getToDateStr().trim().equalsIgnoreCase("null"))
														{
															cadrePreviousRoles.setToDate(convertToDateFormet(rolesVO.getToDateStr()));
														}
														cadrePreviousRoles.setInsertedDate(dateUtilService.getCurrentDateAndTime());
														cadrePreviousRoles.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
														cadrePreviousRoles.setIsDeleted("N");
														cadrePreviousRoles.setCommitteeLocationId(rolesVO.getCommitteeLocationId());
														cadrePreviousRolesDAO.save(cadrePreviousRoles);
												}
											}
											
										}
									}
									
									List<CadrePreviousRollesVO> previousElectionPartList = cadreRegistrationVO.getPreviousParicaptedElectionsList();
									if(previousElectionPartList != null && previousElectionPartList.size() > 0)
									{
										for (CadrePreviousRollesVO electionVO : previousElectionPartList)
										{
											if(electionVO != null)
											{
												if(tdpCadre != null)
												{
													if(electionVO.getConstituencyId() != null && electionVO.getConstituencyId().trim().length()  > 0)
													{
														CadreParticipatedElection cadreParticipatedElection = new CadreParticipatedElection();
														
														cadreParticipatedElection.setTdpCadreId(tdpCadre.getTdpCadreId());
														if(electionVO.getElectionTypeId() != null && electionVO.getElectionTypeId().trim().length() > 0)
														{
															if(Long.valueOf(electionVO.getElectionTypeId()) > 0)
															{
																cadreParticipatedElection.setElectionId(Long.valueOf(electionVO.getElectionTypeId()));
															}
															
														}
														if(electionVO.getConstituencyId() != null && electionVO.getConstituencyId().trim().length()> 0)
														{
															if(Long.valueOf(electionVO.getConstituencyId()).longValue() > 0)
															{
																cadreParticipatedElection.setConstituencyId(Long.valueOf(electionVO.getConstituencyId()));
															}
															
														}
														if(electionVO.getCandidateId() != null && electionVO.getCandidateId().trim().length()> 0)
														{   if(Long.valueOf(electionVO.getCandidateId()).longValue() > 0)
														   {
																cadreParticipatedElection.setCandidateId((Long.valueOf(electionVO.getCandidateId())));
														   }
														}
														cadreParticipatedElection.setIsDeleted("N");
														cadreParticipatedElectionDAO.save(cadreParticipatedElection);
													}
													
												}
											}
										}
									}
									
									if(eligibleRoles != null && eligibleRoles.size()>0)
									{
										CadrePreviousRollesVO roleVO = eligibleRoles.get(0); 
										Long cadreRoleId = roleVO.getCadreRoleId();
										if(cadreRoleId != null)
										{
											ResultStatus resultStatus = cadreCommitteeService.saveCadreCommitteDetails(userId, cadreRegistrationVO.getTdpCadreId(), cadreRoleId);
											surveyCadreResponceVO.setResultCode(resultStatus.getResultCode());
											surveyCadreResponceVO.setStatus(resultStatus.getMessage());
										}
										else
										{
											surveyCadreResponceVO.setResultCode(0);
											surveyCadreResponceVO.setStatus("Cadre Details Updated Successfully");
										}
									}
								}
								
							}
						}
					}
				}
			}
		});
			//surveyCadreResponceVO.setResultCode(ResultCodeMapper.SUCCESS);
		} catch (Exception e) {
			surveyCadreResponceVO.setResultCode(ResultCodeMapper.FAILURE);
			surveyCadreResponceVO.setStatus("Error Occured While Updating Details...");
			LOG.error("Exception raised in saveCommitteCadreRegistration in CadreRegistrationService service", e);
		}
		
		return surveyCadreResponceVO;
	 }
	}
	
	public List<GenericVO> getExistingCadreInfoForCommittee(String candidateName,Long constituencyId,Long panchayatId,Long boothId,String isPresentCadre,String enrollmentNo,Long areaId){
 		LOG.info("Entered into getExistingCadreInfoForCommittee method in CadreRegistrationService class");
 		List<GenericVO>  returnList = null;
 		try {
			
 			List<Object[]> cadreInfo = tdpCadreDAO.getexistringCadreInfoByLocationForCommittee(candidateName,constituencyId,panchayatId,boothId,isPresentCadre,enrollmentNo,areaId);
				if(cadreInfo != null && cadreInfo.size()>0)
				{
					returnList = new ArrayList<GenericVO>();
					for (Object[] param : cadreInfo)
					{
						GenericVO vo = new GenericVO();
						
						vo.setName(param[0] != null ? param[0].toString().trim():"");
						vo.setDesc(param[1] != null ? param[1].toString().trim():"");
						if(param[2] != null){
							if(param[2].toString().trim().length() > 8){
								vo.setCaste(param[2].toString().trim().substring(param[2].toString().trim().length()-8));
							}else{
								vo.setCaste(param[2].toString());
							}
						}else{
							vo.setCaste("");
						}
						//vo.setCaste(param[2] != null ? param[2].toString().trim():"");
						
						vo.setId(param[3] != null ? Long.valueOf(param[3].toString().trim()):0L);
						
						returnList.add(vo);
					}
				}
				
		} catch (Exception e) {
			LOG.error("Entered into getExistingCadreInfoForCommittee method in CadreRegistrationService class",e);
		}
 		
 		return returnList;
 	}
	public String saveRegistrationStatus(CadreRegistrationVO vo){
		String errorReason = "";
		RegistrationStatus status = new RegistrationStatus();
		ErrorStatusSms errorStatus = new ErrorStatusSms();
		TwoWaySmsMobile twoWaySmsMobile = null;
		String reqMobileNo = vo.getMobileNumber().trim();
		String message = vo.getArea().trim();
		if(message.length() > 2 && message.subSequence(0, 3).toString().equalsIgnoreCase("msg")){
			saveMessageInfo(reqMobileNo,message);
			return "success";
		}
		boolean validNo = isMobNo(reqMobileNo);
		if(validNo == false)
		{
			errorReason = "InValid Mobile No";
			saveErroInfo(message,reqMobileNo,twoWaySmsMobile,errorReason);
			return "success";
		}
		
		
		if(message.isEmpty() || message == null || !message.contains(" "))
		{
			if(message.isEmpty())
			errorReason = "empty message";
			else if(message == null)
			errorReason = "message is null";
			else if(!message.contains(" "))
		     errorReason = "message is incorrect format";	
			saveErroInfo(message,reqMobileNo,null,errorReason);
			return "success";
		}
		
		else
		{
		
			 StringTokenizer st = new StringTokenizer(message, " ");
		        StringBuffer sb1 = new StringBuffer();
		        while(st.hasMoreElements()){
		            sb1.append(st.nextElement()).append(" ");
		        }
		
			String[] split = sb1.toString().split(" ");
			String partNo = split[0];		
			String count = split[1];
			if(count== null || count.isEmpty())
			{
				saveErroInfo(message,reqMobileNo,twoWaySmsMobile,"Invalid Message");
			return "success";
			}
			if(reqMobileNo.length() == 10){
				List<TwoWaySmsMobile> twoWaySmsMobileList = twoWaySmsMobileDAO.getMobileInfo(reqMobileNo);
				if(twoWaySmsMobileList.size() > 0 && twoWaySmsMobileList.get(0) != null){
					twoWaySmsMobile = twoWaySmsMobileList.get(0);
				}
			}
				
			 boolean boothPartNo = isAlpha(partNo); boolean polledVotes = isAlpha(count);
			 if(boothPartNo == false || polledVotes == false)
			 {
				 if(boothPartNo == false)
				     errorReason = "Invalid partNo";
				 else if(polledVotes == false)
				     errorReason = "Invalid polledVotes";
				saveErroInfo(message,reqMobileNo,twoWaySmsMobile,errorReason);
				return "success";
			 }
		
		Booth booth = boothDAO.getBoothByPartNoAndPublicationIdInAConstituency(partNo,291l,12l);
		if(booth == null)
		{
			
			     errorReason = "Booth not available";
			saveErroInfo(message,reqMobileNo,twoWaySmsMobile,errorReason);
			return "success";
		}
		else
		{
			if(booth.getTotalVoters() == null)
			{
				  errorReason = "TotalVoters null";
				saveErroInfo(message,reqMobileNo,twoWaySmsMobile,errorReason);
				return "success";	
			}
			if(booth.getTotalVoters().longValue() < Long.valueOf(count))
			{
				 errorReason = "Polled Voters is greter than Total voters";
				saveErroInfo(message,reqMobileNo,twoWaySmsMobile,errorReason);
				return "success";
			}
				
				status.setRegistrationCount(count);
				if(twoWaySmsMobile != null){
					status.setTwoWaySmsMobile(twoWaySmsMobile);
				}	
				status.setBooth(boothDAO.get((Long)booth.getBoothId()));
				
				if(status.getBooth().getLocalBody() != null){
					status.setBoothType("Urban");
				}else{
					status.setBoothType("Rural");
				}
				status.setMobile(vo.getMobileNumber().trim());
				status.setIsDeleted("N");
				status.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				registrationStatusDAO.save(status);
				try {
					if(booth.getBoothId()!=null && booth.getBoothId()>0){
						List<String> mobileNos = tirupathiByeleMobileBoothDAO.getMobileNosOfBooth(booth.getBoothId());
						StringBuilder sb = new StringBuilder();
						
						if(mobileNos!=null && mobileNos.size()>0){
							for(String str:mobileNos){
								sb.append(str.toString());
								sb.append(",");
							}
						}
						
						if(sb.toString().length()>0){
							String	mobiles = sb.toString().substring(0,sb.toString().length() - 1);
							String percentage = (new BigDecimal(Long.valueOf(count)*(100.0)/booth.getTotalVoters())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();

							sendSMS(mobiles, "  In Booth "+ booth.getPartNo() +" Total Polled Votes :"+ count +" and Polling Percentage : "+ percentage +" %");
						}
						
					}
				} catch (Exception e) {
					LOG.error(" Exception raised While Sending SMS In saveRegistrationStatus " + e);
				}
			try{
				RegistrationStatusTemp registrationStatusTemp = new RegistrationStatusTemp();
				String percentage = "";
				if(booth.getTotalVoters() != null && booth.getTotalVoters() > 0)
				percentage = (new BigDecimal(new Long(count)*(100.0)/booth.getTotalVoters())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();				
				String tempmessage ="Booth-"+partNo+", Polled Votes " +count+"("+percentage+"%)";
				registrationStatusTemp.setMessage(tempmessage);
				registrationStatusTemp.setType("PolledVotes");
				registrationStatusTemp.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				registrationStatusTemp.setBoothId(booth.getBoothId());
				registrationStatusTempDAO.save(registrationStatusTemp);
			}
			catch(Exception e)
			{
				LOG.error(e);
			}
			
		}
       }
		return "success";
     
   }
	

     

	public  boolean isMobNo(String str) {
		String regex = "\\d{10}";
        Pattern mobNO = Pattern.compile(regex);
        Matcher matcher = mobNO.matcher(str);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }
	private void saveMessageInfo(String reqMobileNo,String message){
		String reqMsg = "";
		String partNo = "";
		if(message.length() > 4){
			reqMsg = message.substring(3).trim();
			String[] details = reqMsg.split(" ");
			if(details.length > 1){
				partNo = details[0].trim();
				int i = 0;
				reqMsg = "";
				for(String msg:details){
					if(i != 0){
				      reqMsg = reqMsg+" "+msg;
					}
					i++;
				}
			}
		}else{
			reqMsg = message;
		}
		Booth booth = null;
		if(partNo.length() > 0){
		  booth = boothDAO.getBoothByPartNoAndPublicationIdInAConstituency(partNo,291l,12l);
		  if(booth == null){
			  reqMsg = message;
		  }
		}
		TwoWaySmsMobile twoWaySmsMobile = null;
		if(reqMobileNo.length() == 10){
			List<TwoWaySmsMobile> twoWaySmsMobileList = twoWaySmsMobileDAO.getMobileInfo(reqMobileNo);
			if(twoWaySmsMobileList.size() > 0 && twoWaySmsMobileList.get(0) != null){
				twoWaySmsMobile = twoWaySmsMobileList.get(0);
			}
		}
		TwoWayMessage twoWayMessage = new TwoWayMessage();
		twoWayMessage.setMessage(reqMsg);
		twoWayMessage.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		twoWayMessage.setTwoWaySmsMobile(twoWaySmsMobile);
		twoWayMessage.setBooth(booth);
		twoWayMessage.setMobileNo(reqMobileNo);
		if(booth != null){
		   twoWayMessage.setPartNo(booth.getPartNo());
		}
		twoWayMessage.setActualMsg(message);
		twoWayMessage.setIsDeleted("N");
		twoWayMessageDAO.save(twoWayMessage);
		try{
			RegistrationStatusTemp registrationStatusTemp = new RegistrationStatusTemp();
			
			
			registrationStatusTemp.setType("Message");
			registrationStatusTemp.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			if(booth != null){
				registrationStatusTemp.setMessage("From Booth - "+booth.getPartNo()+", "+reqMsg);
			   registrationStatusTemp.setBoothId(booth.getBoothId());
			}else{
				registrationStatusTemp.setMessage(reqMsg);
			}
			registrationStatusTempDAO.save(registrationStatusTemp);
		}
		catch(Exception e)
		{
			LOG.error(e);
		}
	}
	
	public ByeElectionVO getMessagesInfo(Integer startIndex,Integer maxIndex){
		ByeElectionVO returnVo = new ByeElectionVO();
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		List<ByeElectionVO>  resultList = new ArrayList<ByeElectionVO>();
		returnVo.setRecognizeList(resultList);
		try{
			Long count = twoWayMessageDAO.getMessagesCount();
			returnVo.setTotalVoters(count);
			//0message,1insertedTime,2mobileNo,3partNo
			List<Object[]> messagesList = twoWayMessageDAO.getMessagesInfo(startIndex,maxIndex);
			for(Object[] message:messagesList){
				ByeElectionVO vo = new ByeElectionVO();
				if(message[0] != null){
				    vo.setName(message[0].toString());
				}else{
					vo.setName("");
				}
				if(message[1] != null){
				    vo.setPercentage(dateFormat.format(((Date)message[1])));
				}else{
					vo.setPercentage("");
				}
				if(message[2] != null){
				    vo.setType(message[2].toString());
				}else{
					vo.setType("");
				}
				if(message[3] != null){
				    vo.setPartNo(message[3].toString());
				}else{
				    vo.setPartNo("");
				}
				resultList.add(vo);
			}
		}catch(Exception e){
			LOG.error("Exception rised in getMessagesInfo ",e);
		}
		return returnVo;
	}
	
	public void saveErroInfo(String message,String reqMobileNo,TwoWaySmsMobile twoWaySmsMobile,String errorReason)
	{
		try{
			DateUtilService date = new DateUtilService();
		ErrorStatusSms errorStatus = new ErrorStatusSms();
		errorStatus.setMessage(message);
		errorStatus.setMobileNo(reqMobileNo);
		errorStatus.setReason(errorReason);
		if(twoWaySmsMobile != null){
			errorStatus.setTwoWaySmsMobile(twoWaySmsMobile);
		}
		errorStatus.setInsertedTime(date.getCurrentDateAndTime());
		errorStatusSmsDAO.save(errorStatus);
		}
		catch(Exception e)
		{
			
			LOG.error("Exception in saveErroInfo()", e);
		}
	}
	public boolean isAlpha(String name) {
		
		 try { 
		        Integer.parseInt(name); 
		    } catch(NumberFormatException e) { 
		        return false; 
		    }
		    // only got here if we didn't return false
		    return true;
		
	}
	/*public CadreCommitteeMemberVO getBoothsCurrentStatus(Long accessValue){
		accessValue = 291l;
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_TIME_PATTERN);
		 DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		CadreCommitteeMemberVO returnVo = new CadreCommitteeMemberVO();
		List<CadreCommitteeMemberVO> knownList = new ArrayList<CadreCommitteeMemberVO>();
		List<CadreCommitteeMemberVO> unKnownList = new ArrayList<CadreCommitteeMemberVO>();
		returnVo.setKnownList(knownList);
		returnVo.setUnKnownList(unKnownList);
		try{
		CadreCommitteeMemberVO cadreCommitteeMemberVO = null;
		//0partNo,1totalVoters,2polled count,3boothType,4insertedTime,5mobile
		List<Object[]> knownBoothsResults = new ArrayList<Object[]>();
		try{
		 knownBoothsResults = registrationStatusDAO.getAllKnownBoothsInfo(accessValue,12l);
		}catch(Exception e){
			LOG.error(e);
		}
		for(Object[] booth:knownBoothsResults){
			cadreCommitteeMemberVO = new CadreCommitteeMemberVO();
			cadreCommitteeMemberVO.setName(booth[0].toString());
			if(booth[1] != null){
			   cadreCommitteeMemberVO.setTotal(Long.valueOf(booth[1].toString()));
			}
			if(booth[2] != null){
			    cadreCommitteeMemberVO.setStatus(booth[2].toString());
			}else{
				cadreCommitteeMemberVO.setStatus("");
			}
			if(booth[6] != null){
			    cadreCommitteeMemberVO.setRole(booth[6].toString());
			}else{
				cadreCommitteeMemberVO.setRole("");
			}
			if(booth[4] != null){
				
			    //cadreCommitteeMemberVO.setLocationName(sdf.format((Date)booth[4]));
				cadreCommitteeMemberVO.setLocationName(dateFormat.format((Date)booth[4]));
				
			}else{
				cadreCommitteeMemberVO.setLocationName("");
			}
			if(booth[5] != null){
			    cadreCommitteeMemberVO.setMembershipNo(booth[5].toString());
			}else{
				cadreCommitteeMemberVO.setMembershipNo("");
			}
            
              knownList.add(cadreCommitteeMemberVO);
		}
		 //0polled count,1insertedTime,2mobile
		List<Object[]> unKnownBoothsResults = registrationStatusDAO.getAllUnKnownBoothsInfo();
		for(Object[] booth:unKnownBoothsResults){
			cadreCommitteeMemberVO = new CadreCommitteeMemberVO();
			if(booth[0] != null){
			    cadreCommitteeMemberVO.setStatus(booth[0].toString());
			}else{
				cadreCommitteeMemberVO.setStatus("");
			}
			if(booth[1] != null){
				
			    cadreCommitteeMemberVO.setLocationName(dateFormat.format((Date)booth[1]));
			}else{
				cadreCommitteeMemberVO.setLocationName("");
			}
			if(booth[2] != null){
			    cadreCommitteeMemberVO.setMembershipNo(booth[2].toString());
			}else{
				cadreCommitteeMemberVO.setMembershipNo("");
			}
			
             unKnownList.add(cadreCommitteeMemberVO);
		}
		}catch(Exception e){
			LOG.error("Exception Rised in getBoothsCurrentStatus ",e);
		}
		return returnVo;
	}*/
	
	/*public ByeElectionVO getByeEleBoothsCurrentStatusSummary(Long accessValue)
	{
		ByeElectionVO returnVo = new ByeElectionVO();
		accessValue = 291l;
		
		Long totalBooths = 0l;
		Long recognizeBoothCnt = 0l;
		Long unrecognizeBoothCnt = 0l;
		try{
		 List<Long> list = boothDAO.getAllBoothsInAConstituency(accessValue,12l);
		 if(list != null && list.size() > 0)
			 totalBooths = new Long(list.size());
		 List<BigInteger> knownBooths = registrationStatusDAO.getAllKnownBoothsInfoByConstituency(accessValue,12l);
		 if(knownBooths != null && knownBooths.size() > 0)
			 recognizeBoothCnt = new Long(knownBooths.size());
		  List<BigInteger> unknownBooths = registrationStatusDAO.getAllUnRecognizedBoothsInfoByConstituency(accessValue,12l);
		 returnVo.setTotalVoters(totalBooths);
		 returnVo.setPolledVotes(recognizeBoothCnt);
		 if(unknownBooths != null && unknownBooths.size() > 0)
			 unrecognizeBoothCnt =new Long(unknownBooths.size());
		 returnVo.setId(unrecognizeBoothCnt);
		 
		}		
		catch(Exception e)
		{
			
		}
		return returnVo;
		
	}*/

	
	
	public List<CadreCommitteeMemberVO> getClustesAndDivisionNames(Long typeId){
	
		List<CadreCommitteeMemberVO> returnList = new ArrayList<CadreCommitteeMemberVO>();
	
		try{
			List<String> names = new ArrayList<String>();
		if(typeId == 1L)	
			names =  tirupatiByelectionDAO.getClusterNames();
		else if(typeId == 2L)
			names =  tirupatiByelectionDAO.getDivisionNames();
		
		for(String name:names){
			CadreCommitteeMemberVO vo = new CadreCommitteeMemberVO();
			if(name != null && !name.isEmpty())
			{
			vo.setName(name.toString());
			vo.setStatus(name.toString());
			returnList.add(vo);
			}
		}

		}catch(Exception e){
			LOG.error("Exception Raised in getClustesAndDivisionNames ",e);
		}
		return returnList;
	}
	

	/*public ByeElectionVO getByeEleBoothsCurrentStatusSummaryInfo(Long accessValue,String status)
	{
		ByeElectionVO returnVo = new ByeElectionVO();
		accessValue = 291l;
		List<BigInteger> boothIds = new ArrayList<BigInteger>();
		List<ByeElectionVO> result = new ArrayList<ByeElectionVO>();
		returnVo.setRecognizeList(result);
		try{
		 if(status.equalsIgnoreCase("recognize"))
			 boothIds = registrationStatusDAO.getAllKnownBoothsInfoByConstituency(accessValue,12l);
		 if(status.equalsIgnoreCase("unrecognize"))
			 boothIds = registrationStatusDAO.getAllUnRecognizedBoothsInfoByConstituency(accessValue,12l);
		 if(boothIds != null && boothIds.size() > 0)
		 { 
			List<Long> booths = new ArrayList<Long>();
			for(BigInteger val : boothIds)
			{
				booths.add(Long.valueOf(val.toString()));
			}
			
		 List<TirupatiByelection> list = tirupatiByelectionDAO.getModelByboothIds(booths);
		 
			if(list != null && list.size() > 0)
			{
				returnVo = setData(list,booths,status,accessValue);	
			}
		 }
		 
		}		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnVo;
		
	}*/


	public ByeElectionVO getByeEleBoothsCurrentStatusReport(Long accessValue,Long typeId,String type){
		accessValue = 291l;
		
		ByeElectionVO returnVo = new ByeElectionVO();
		
			try{
			
			List<TirupatiByelection> list = tirupatiByelectionDAO.getModelByType(typeId,type);
			if(list != null && list.size() > 0)
			{
				returnVo = setData(list,null,null,accessValue);
			}
		
		
		}catch(Exception e){
			LOG.error("Exception Rised in getByeEleBoothsCurrentStatusReport ",e);
		}
		return returnVo;
	}
	public static Comparator<ByeElectionVO> sortData = new Comparator<ByeElectionVO>()
		    {
		        public int compare(ByeElectionVO vo1, ByeElectionVO vo2)
		        {
		        	 return vo1.getPartNo().compareToIgnoreCase(vo2.getPartNo());
		        }
		    };
		    
	public ByeElectionVO setData(List<TirupatiByelection> list,List<Long> boothIds,String status,Long accessValue)
	{
		ByeElectionVO returnVo = new ByeElectionVO();
		  ByeElectionVO byeElectionVO = null;
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		List<Long> presentBoothIds = new ArrayList<Long>();
		List<ByeElectionVO> recognizeList = new ArrayList<ByeElectionVO>();
		try{
		if(list != null && list.size() > 0)
		{
			
			for(TirupatiByelection params : list)
			{
				byeElectionVO = new ByeElectionVO();
				byeElectionVO.setTotalVoters(Long.valueOf(params.getTotalVoters2015().toString()));
				byeElectionVO.setPreTotalVoters(Long.valueOf(params.getTotalVoters2014().toString()));
				byeElectionVO.setBoothLocation(params.getBoothLocation().toString());
				byeElectionVO.setPartNo(params.getPartNo2015().toString());
				byeElectionVO.setPrevPartNo(params.getPartNo2014().toString());
				byeElectionVO.setPrevPercentage(params.getPollingPercentage2014());
				byeElectionVO.setPrevPolledVotes(Long.valueOf(params.getPolledVotes2014().toString()));
				byeElectionVO.setPreVotersInPresent(Long.valueOf(params.getVoterIn2015From2014().toString()));
				byeElectionVO.setBoothId(Long.valueOf(params.getBoothId2015().toString()));
				byeElectionVO.setCluster(params.getClusterName());
				byeElectionVO.setWard(params.getDivisionName());
				recognizeList.add(byeElectionVO);
				presentBoothIds.add(params.getBoothId2015());
			}
		}
		returnVo = getByeEleBoothsCurrentStatusSummary1(accessValue,presentBoothIds);
		
		Collections.sort(recognizeList,sortData);	
		returnVo.setRecognizeList(recognizeList);
		 List<Object[]> booths = null;
		if(status == null)
			 booths = registrationStatusDAO.getBoothsInfo(presentBoothIds,12l);
		if(boothIds != null && boothIds.size()  > 0)
		{
			 if(status.equalsIgnoreCase("recognize"))
			 booths = registrationStatusDAO.getRecognizeBoothsInfo(boothIds,12l);
			 if(status.equalsIgnoreCase("unrecognize"))
				 booths = registrationStatusDAO.getUnRecognizeBoothsInfo(boothIds,12l); 
			 if(status.equalsIgnoreCase("all"))
				 booths = registrationStatusDAO.getBoothsInfo(boothIds,12l); 
			 
		}
			 if(booths != null && booths.size() > 0)
			 {
				 for(Object[] params : booths)
				 {
					ByeElectionVO vo = getMatchedBooth(recognizeList,Long.valueOf(params[0].toString()));
					if(vo != null)
					{
						vo.setPolledVotes(Long.valueOf(params[2].toString()));
						String percentage = "0.0";
						if(vo.getTotalVoters() > 0)
						percentage = (new BigDecimal(vo.getPolledVotes()*(100.0)/vo.getTotalVoters())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						vo.setPercentage(percentage);
						if(params[3] != null){
							
						    vo.setTime(dateFormat.format((Date)params[3]));
						}else{
							vo.setTime("");
						}
					}
					 
				 }
			 
			 
		}
		}
		catch(Exception e)
		{
			LOG.error("Exception in setData() method in CadreRegistration service");
		}
		return returnVo;
	
	}
	public ByeElectionVO getByeEleBoothsCurrentStatusSummary1(Long accessValue,List<Long> boothIds)
	{
		ByeElectionVO returnVo = new ByeElectionVO();
		accessValue = 291l;
		
		Long totalBooths = 0l;
		Long recognizeBoothCnt = 0l;
		Long unrecognizeBoothCnt = 0l;
		Long allBooths = 0l;
		try{
			 List<Long> list = boothDAO.getAllBoothsInAConstituency(accessValue,12l);
			 if(list != null && list.size() > 0){
				 totalBooths = new Long(list.size());
			 }
			 returnVo.setTotalVoters(totalBooths);
			 
			 List<BigInteger> knownBooths = registrationStatusDAO.getRecognizeByBooths(boothIds,12l);
			 List<BigInteger> unknownBooths = registrationStatusDAO.getUnrecognizeByBooths(boothIds,12l);
			 List<BigInteger> allBoothsList = registrationStatusDAO.getAllBooths(boothIds,12l);
			 
			 List<Object[]> knownBoothsVtrsCnt = registrationStatusDAO.getRecognizeByBoothsTotalVotersAndPolledVotes(boothIds,12l);
			 List<Object[]> unknownBoothsVtrsCnt = registrationStatusDAO.getUnrecognizeByBoothsTotalVotersAndPolledVotes(boothIds,12l);
			 List<Object[]> allBoothsListVtrsCnt = registrationStatusDAO.getAllBoothsTotalVotersAndPolledVotes(boothIds,12l);
			 
			 Long kbttlVtrs = 0l;
			 Long kbttlVtrsPlld = 0l;
			 String kbttlVtrsPercentage = "0.0";
			 
			 Long ukbttlVtrs = 0l;
			 Long ukbttlVtrsPlld = 0l;
			 String ukbttlVtrsPercentage = "0.0";
			 
			 Long abttlVtrs = 0l;
			 Long abttlVtrsPlld = 0l;
			 String abttlVtrsPercentage = "0.0";
			 
			 if(knownBoothsVtrsCnt!=null && knownBoothsVtrsCnt.size()>0){
				 for(Object[] obj:knownBoothsVtrsCnt){
					 if(obj[0]!=null && obj[1]!=null){
						 kbttlVtrs = Long.valueOf(obj[0].toString());
						 kbttlVtrsPlld =((Double)obj[1]).longValue();
						 
						 kbttlVtrsPercentage = (new BigDecimal(kbttlVtrsPlld*(100.0)/kbttlVtrs)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();	
					 }
				 }
			 }
			 
			 returnVo.setKbVotersCount(kbttlVtrs);
			 returnVo.setKbPolledCount(kbttlVtrsPlld);
			 returnVo.setKbPercentage(kbttlVtrsPercentage);
			 
			 if(unknownBoothsVtrsCnt!=null && unknownBoothsVtrsCnt.size()>0){
				 for(Object[] obj:unknownBoothsVtrsCnt){
					 if(obj[0]!=null && obj[1]!=null){
						 ukbttlVtrs = Long.valueOf(obj[0].toString());
						 ukbttlVtrsPlld =((Double)obj[1]).longValue();
						 
						 ukbttlVtrsPercentage = (new BigDecimal(ukbttlVtrsPlld*(100.0)/ukbttlVtrs)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					 }
				 }
			 }
			 
			 returnVo.setUkbVotersCount(ukbttlVtrs);
			 returnVo.setUkbPolledCount(ukbttlVtrsPlld);
			 returnVo.setUkbPercentage(ukbttlVtrsPercentage);
			 
			 if(allBoothsListVtrsCnt!=null && allBoothsListVtrsCnt.size()>0){
				 for(Object[] obj:allBoothsListVtrsCnt){
					 if(obj[0]!=null && obj[1]!=null){
						 abttlVtrs = Long.valueOf(obj[0].toString());
						 abttlVtrsPlld =((Double)obj[1]).longValue();
						 
						 abttlVtrsPercentage = (new BigDecimal(abttlVtrsPlld*(100.0)/abttlVtrs)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					 }
				 }
			 }
			 
			 returnVo.setAbVotersCount(abttlVtrs);
			 returnVo.setAbPolledCount(abttlVtrsPlld);
			 returnVo.setAbPercentage(abttlVtrsPercentage);
			 
			 if(knownBooths != null && knownBooths.size() > 0){
				 recognizeBoothCnt = new Long(knownBooths.size());
			 }
			 returnVo.setPolledVotes(recognizeBoothCnt);
			 
			 if(unknownBooths != null && unknownBooths.size() > 0){
				 unrecognizeBoothCnt =new Long(unknownBooths.size());
			 }
			 returnVo.setId(unrecognizeBoothCnt);
			 
			 if(allBoothsList != null && allBoothsList.size() > 0){
				 allBooths = new Long(allBoothsList.size()); 
			 }
			 returnVo.setPreTotalVoters(allBooths);
		}		
		catch(Exception e)
		{
			
		}
		return returnVo;
		
	}
	
	public ByeElectionVO getByeEleBoothsCurrentStatusSummaryInfo1(Long accessValue,String status,Long typeId,String type)
	{
		ByeElectionVO returnVo = new ByeElectionVO();
		accessValue = 291l;
		List<BigInteger> boothIds = new ArrayList<BigInteger>();
		List<ByeElectionVO> result = new ArrayList<ByeElectionVO>();
		returnVo.setRecognizeList(result);
		try{
			List<Long> presentBoothIds = new ArrayList<Long>();
		List<TirupatiByelection> list = tirupatiByelectionDAO.getModelByType(typeId,type);
		if(list != null && list.size() > 0)
		{
			for(TirupatiByelection obj : list)
			{
				presentBoothIds.add(Long.valueOf(obj.getBoothId2015()));
			}
		
		 if(status.equalsIgnoreCase("recognize"))
			 boothIds = registrationStatusDAO.getRecognizeByBooths(presentBoothIds,12l);
		 if(status.equalsIgnoreCase("unrecognize"))
			 boothIds = registrationStatusDAO.getUnrecognizeByBooths(presentBoothIds,12l);
		 if(status.equalsIgnoreCase("all"))
			 boothIds = registrationStatusDAO.getAllBooths(presentBoothIds,12l);
		 
		 if(boothIds != null && boothIds.size() > 0)
		 { 
			List<Long> booths = new ArrayList<Long>();
			for(BigInteger val : boothIds)
			{
				booths.add(Long.valueOf(val.toString()));
			}
			
		 List<TirupatiByelection> dataList = tirupatiByelectionDAO.getModelByboothIds(booths);
		 
			if(list != null && list.size() > 0)
			{
				returnVo = setData(dataList,booths,status,accessValue);	
			}
		 }
		}
		}		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnVo;
		
	}
	public ByeElectionVO getMatchedBooth(List<ByeElectionVO> resultList,Long boothId)
	{
		try{
			if(resultList == null || resultList.size() == 0)
				return null;
			for(ByeElectionVO vo : resultList)
			{
				if(vo.getBoothId().longValue() == boothId)
					return vo;
			}
		}
		catch(Exception e)
		{
			
		}
		return null;
	}
	
	public ByeElectionVO getByeEleBoothsErrorInfo()
	{
		ByeElectionVO returnVO = new ByeElectionVO();
		List<ByeElectionVO> returnList = new ArrayList<ByeElectionVO>();
		try{
			DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
			List<ErrorStatusSms> errorList = errorStatusSmsDAO.getErrorSmsInfo();
			if(errorList != null && errorList.size() > 0)
			{
				for(ErrorStatusSms obj : errorList)
				{
					ByeElectionVO vo = new ByeElectionVO();
					vo.setTime(obj.getInsertedTime() != null ? dateFormat.format((Date)obj.getInsertedTime()) : "");
					vo.setName(obj.getMessage() != null ? obj.getMessage().toString() : "");
					vo.setPartNo(obj.getMobileNo() != null ? obj.getMobileNo() : "");
					vo.setType(obj.getReason() != null ? obj.getReason() : "");
					vo.setPercentage(obj.getInsertedTime() != null ? obj.getInsertedTime().toString() : "");
					returnList.add(vo);
					
				}
				returnVO.setRecognizeList(returnList);
			}
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getErrorInfo()", e);
		}
		return returnVO;
	}
	public ByeElectionVO getByeElelatestUpdates(Integer startIndex,Integer maxIndex)
	{
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		ByeElectionVO returnVo = new ByeElectionVO();
		try{
			 List<Object[]> list = registrationStatusTempDAO.getLatestMessages(startIndex,maxIndex);
			 if(list != null && list.size() > 0)
			 {
				 List<ByeElectionVO> returnList = new ArrayList<ByeElectionVO>();
				 returnVo.setRecognizeList(returnList);
				 for(Object[] obj : list)
				 {
					 ByeElectionVO vo = new ByeElectionVO();
					 vo.setPartNo(obj[0] != null ? obj[0].toString() : "");
					 vo.setType(obj[1] != null ? obj[1].toString() : "");
					 vo.setTime(obj[2] != null ? dateFormat.format((Date)obj[2]) : "");
					 vo.setPercentage(obj[2]  != null ? obj[2].toString() : "");
					 returnList.add(vo);
				 } 
			 }
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getByeElelatestUpdates()", e);
		}
		return returnVo;
		
	}
	
	
	public String saveMissedCallDetails(MissedCallCampaignVO vo){
		
		try{
			CadreMissedCallCampaign cadreMissedCallCampaign = new CadreMissedCallCampaign();
			String mobileNo = vo.getFrom().trim();						
			Long unixSeconds = (Long)vo.getRing_time();
			Date time=new Date((Long)unixSeconds*1000);
			cadreMissedCallCampaign.setMobileNumber(mobileNo);
			cadreMissedCallCampaign.setInsertedTime(time);
			cadreMissedCallCampaign.setCallUid(vo.getCalluid());
			cadreMissedCallCampaign.setToMobileNumber(vo.getToMobileNo());
			cadreMissedCallCampaign.setCallStatus(vo.getCallStatus());
			cadreMissedCallCampaign.setUrl(vo.getUrl());
			cadreMissedCallCampaign.setMissedCallCampaignId(vo.getId());
			
			cadreMissedCallCampaignDAO.save(cadreMissedCallCampaign);
	       }catch(Exception e){
				LOG.error(e);
			}
		return "success";
     
   }
	
	/*public MissedCallsDetailsVO getMissedCallDetail(String fromDateStr,String toDateStr,Long stateId)
	{
		MissedCallsDetailsVO missedCallDetails = new MissedCallsDetailsVO();
			
		try{	
			

			DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			//DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-mm-dd");
			
			Date fromDate = userDateFormat.parse(fromDateStr);
			
			Date toDate = userDateFormat.parse(toDateStr);
		
		
			Long totalMissedCalls = 0l;
			Long singleMemRegisteredCnt =0l;
			Long multipleMemRegisteredCnt = 0L;
			if(stateId == 0L)
				totalMissedCalls = 	cadreMissedCallCampaignDAO.getAllMissedCallsCount(fromDate,toDate);
			else 
				totalMissedCalls = tdpCadreDAO.getMissedCallsCountByState(fromDate,toDate,stateId);
			
			if(totalMissedCalls != null){
				missedCallDetails.setTotalCount(totalMissedCalls);
			}
			List<String> mobileNos = new ArrayList<String>();
			
			if(stateId == 0L)
				mobileNos  = 	cadreMissedCallCampaignDAO.getAllMobileNos(fromDate,toDate);
			else 
				mobileNos = tdpCadreDAO.getMissedCallMobileNosByState(fromDate,toDate,stateId);
			
			
			
			List<Long> singleMemRegistered = tdpCadreDAO.getSingleMemberMobileNosCount(mobileNos,stateId);
			if(singleMemRegistered != null && singleMemRegistered.size() >0) {
				for(Long cnt :singleMemRegistered){
					singleMemRegisteredCnt = singleMemRegisteredCnt+cnt;
				}
				
				missedCallDetails.setSingleMemberRegCnt(Long.valueOf(singleMemRegistered.size()));
			}
			List<Long> multipleMemRegistered = tdpCadreDAO.getMultipleMemberMobileNosCount(mobileNos,stateId);
			if(multipleMemRegistered != null && multipleMemRegistered.size() >0){
				//for(Long cnt1 :multipleMemRegistered){
				//	multipleMemRegisteredCnt = multipleMemRegisteredCnt+cnt1;
				//}
				missedCallDetails.setMultiMemberRegCnt(Long.valueOf(multipleMemRegistered.size()));
			}
				
			
			
			
			//Long matchedCount = 0l;
			//matchedCount = tdpCadreDAO.getMatchedMobileNosByState(mobileNos);
			
			//if(matchedCount != null){
				missedCallDetails.setMismatchedCnt(missedCallDetails.getTotalCount() - ( missedCallDetails.getSingleMemberRegCnt() + missedCallDetails.multiMemberRegCnt) );
			//}	
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getErrorInfo()", e);
		}
		return missedCallDetails;
	}*/
	
	public MissedCallsDetailsVO getMissedCallDetail(String fromDateStr,String toDateStr,Long stateId)
	{
		MissedCallsDetailsVO missedCallDetails = new MissedCallsDetailsVO();
			
		try{	
			

			DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date fromDate = userDateFormat.parse(fromDateStr);
			Date toDate = userDateFormat.parse(toDateStr);
		
			Long totalMissedCalls = 0l;
			Long singleMemRegisteredCnt =0l;
			Long multipleMemRegisteredCnt = 0L;
			Long totalCnt = 0L;
			if(stateId == 0L)
				totalMissedCalls = 	cadreMissedCallCampaignDAO.getAllMissedCallsCount(fromDate,toDate);
			//else 
				//totalMissedCalls = tdpCadreDAO.getMissedCallsCountByState(fromDate,toDate,stateId);
			
			
			
			List<Object[]> list = tdpCadreDAO.getMemberMobileNumbersCount(fromDate,toDate,stateId);
			Map<String,Integer> mobileMap = new HashMap<String, Integer>(0);
			
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					String mobileNo = params[0].toString();
					Integer count = mobileMap.get(mobileNo);
					if(count == null)
						count = 0;
					mobileMap.put(mobileNo,++count);
				}
			}
			for(Map.Entry<String,Integer> entry : mobileMap.entrySet())
			{
				totalCnt++;
				if(entry.getValue().intValue() == 1)
					singleMemRegisteredCnt++;
				else
					multipleMemRegisteredCnt++;
			}
			
			missedCallDetails.setSingleMemberRegCnt(singleMemRegisteredCnt);
			missedCallDetails.setMultiMemberRegCnt(multipleMemRegisteredCnt);
			if(stateId == 0L){
				if(totalMissedCalls != null)
					missedCallDetails.setTotalCount(totalMissedCalls);
			}
			else{
				missedCallDetails.setTotalCount(totalCnt);
			}
			missedCallDetails.setMismatchedCnt(missedCallDetails.getTotalCount() - ( missedCallDetails.getSingleMemberRegCnt() + missedCallDetails.getMultiMemberRegCnt()) );
			
			
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getErrorInfo()", e);
		}
		return missedCallDetails;
	}
	
	
	
	/*public List<MissedCallsDetailsVO> getMissedCallDetailByDistrict(String fromDateStr,String toDateStr,Long stateId,String task)
	{
		List<MissedCallsDetailsVO> resultList = new ArrayList<MissedCallsDetailsVO>();
			
		try{	
			DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			//DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-mm-dd");
			
			Date fromDate = userDateFormat.parse(fromDateStr);
			
			Date toDate = userDateFormat.parse(toDateStr);
			
			Map<Long,String> districtNames = new HashMap<Long, String>();
			List<Object[]> names = districtDAO.getDistrictIdAndNameByStateForStateTypeId(1L,stateId);
			if(names != null){
				for(Object[] param:names){
					districtNames.put((Long)param[0], param[1].toString());
				}
			}
			List<String> mobileNos = new ArrayList<String>();
			
			if(stateId == 0L)
				mobileNos  = 	cadreMissedCallCampaignDAO.getAllMobileNos(fromDate,toDate);
			else 
				mobileNos = tdpCadreDAO.getMissedCallMobileNosByState(fromDate,toDate,stateId);
			
			List<Object[]> result = new ArrayList<Object[]>();
			if(task.equalsIgnoreCase("getSingleMember")){
				 result = tdpCadreDAO.getMissedCallsSingleMemberCountByDistrict(mobileNos, stateId);
				
			}else{
			 	result = tdpCadreDAO.getMissedCallsCountByDistrict(mobileNos, stateId);
			}
			Long count= 0L;
			if(result != null && result.size() > 0){
				for(Object[] params : result){
					MissedCallsDetailsVO vo = new MissedCallsDetailsVO();
					vo.setDistrictCount(((BigInteger)params[0]).longValue());
					vo.setDistrictId(((BigInteger)params[1]).longValue());
					count = count+vo.getDistrictCount();
					if(districtNames != null){
						if(districtNames.get(((BigInteger)params[1]).longValue()) != null ){
							vo.setName(districtNames.get(((BigInteger)params[1]).longValue()) != null ? districtNames.get(((BigInteger)params[1]).longValue()) : "");
						}
					}
					resultList.add(vo);
				}
			}
			if(resultList != null && resultList.size() > 0){
				resultList.get(0).setTotalCount(count);
			}
		
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getErrorInfo()", e);
		}
		return  resultList;
	}*/
	
	public List<MissedCallsDetailsVO> getMissedCallDetailByDistrict(String fromDateStr,String toDateStr,Long stateId,String task)
	{
		List<MissedCallsDetailsVO> resultList = new ArrayList<MissedCallsDetailsVO>();
			
		try{	
			DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date fromDate = userDateFormat.parse(fromDateStr);
			Date toDate = userDateFormat.parse(toDateStr);
			
			Map<Long,String> districtNames = new HashMap<Long,String>();
			Map<Long,Long> regCnt = new HashMap<Long,Long>();
			List<Object[]> names = districtDAO.getDistrictIdAndNameByStateForStateTypeId(1L,stateId);
			if(names != null){
				for(Object[] param:names){
					districtNames.put((Long)param[0], param[1].toString());
				}
			}
			List<Object[]> regCountList=tdpCadreDAO.districtWiseRegCountForDistrict(stateId);
			
			if(regCountList != null){
				for(Object[] param:regCountList){
					regCnt.put((Long)param[0], (Long)param[1]);
				}
			}		
					
			List<Object[]> list = tdpCadreDAO.getDistrictWiseMemberMobileNumbersCount(fromDate,toDate,stateId);
			Map<Long,Integer> disCntMap = new HashMap<Long, Integer>(0);
			Map<Long,List<String>> disSingleMap = new HashMap<Long, List<String>>(0);
			MissedCallsDetailsVO missedCallsDetailsVO = null;
			
			if(task.equalsIgnoreCase("getSingleMember"))
			{
				for(Object[] params : list)
				{
					try{
					List<String> mobList = disSingleMap.get((Long)params[0]);
					if(mobList == null)
						mobList = new ArrayList<String>(0);
					mobList.add(params[1].toString());
					disSingleMap.put((Long)params[0],mobList);
					}catch(Exception e)
					{
						LOG.error(e);
					}
				}
				
				for(Map.Entry<Long,List<String>> entry : disSingleMap.entrySet())
				{
					Map<String,Long> mobTempMap = new HashMap<String,Long>(0);
					long singleCount = 0l;
					for(String mobile : entry.getValue())
					{
						Long cnt = mobTempMap.get(mobile);
						if(cnt == null)
							cnt = 0L;
						mobTempMap.put(mobile,++cnt);
					}
					for(Map.Entry<String,Long> entry2 : mobTempMap.entrySet())
					{
						//if(entry2.getValue().longValue() == 1L)
							singleCount++;
					}
					missedCallsDetailsVO = new MissedCallsDetailsVO();
					missedCallsDetailsVO.setDistrictId(entry.getKey());
					missedCallsDetailsVO.setDistrictCount(singleCount);
					missedCallsDetailsVO.setName(districtNames.get(entry.getKey()) !=  null ? districtNames.get(entry.getKey()) : "");
					missedCallsDetailsVO.setTotalCount(regCnt.get(entry.getKey()) !=  null ? regCnt.get(entry.getKey()) : 0l);
					resultList.add(missedCallsDetailsVO);
				}
			}
			else
			{
				for(Object[] params : list)
				{
					Long districtId = (Long)params[0];
					Integer count = disCntMap.get(districtId);
					
					if(count == null)
						count = 0;
					disCntMap.put(districtId,++count);
				}
			}
			
			for(Map.Entry<Long,Integer> entry : disCntMap.entrySet())
			{
				missedCallsDetailsVO = new MissedCallsDetailsVO();
				missedCallsDetailsVO.setDistrictId(entry.getKey());
				missedCallsDetailsVO.setDistrictCount(new Integer(entry.getValue()).longValue());
				missedCallsDetailsVO.setName(districtNames.get(entry.getKey()) !=  null ? districtNames.get(entry.getKey()) : "");
				missedCallsDetailsVO.setTotalCount(regCnt.get(entry.getKey()) !=  null ? regCnt.get(entry.getKey()) : 0l);
				resultList.add(missedCallsDetailsVO);
			}
			/*if(resultList != null && resultList.size() > 0){
				resultList.get(0).setTotalCount(new Integer(list.size()).longValue());
			}*/
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getErrorInfo()", e);
		}
		return  resultList;
	}
	
	public List<MissedCallsDetailsVO> missedCallDetailsForADistrict(Long districtId,String startDateString,String endDateString){
		  
		 List<MissedCallsDetailsVO> missedCallsDetailsVOList=null;
		  try{
			  Date startDate=new SimpleDateFormat("MM/dd/yyyy").parse(startDateString);
			  Date endDate=new SimpleDateFormat("MM/dd/yyyy").parse(endDateString);
			  
			  Map<Long,MissedCallsDetailsVO> resultMap=new LinkedHashMap<Long,MissedCallsDetailsVO>();
			  List<Object[]> constituenciesList=constituencyDAO.getTheConstituenciesInADistrict(districtId);
			  if(constituenciesList!=null && constituenciesList.size()>0){
				  for (Object[] objects : constituenciesList) {
					  MissedCallsDetailsVO missedCallsDetailsVO =resultMap.get((Long)objects[0]);
					  if(missedCallsDetailsVO==null){
						  MissedCallsDetailsVO missedCallsDetailsvo=new MissedCallsDetailsVO();
						  missedCallsDetailsvo.setName(objects[2].toString());
						  missedCallsDetailsvo.setConstituencyId((Long)objects[0]);
						  missedCallsDetailsvo.setConstituencyName(objects[1].toString());
						  missedCallsDetailsvo.setTotalCount(0l);
						  missedCallsDetailsvo.setPrintedCount(0l);
						  missedCallsDetailsvo.setMissedCallsCount(0l);
						  missedCallsDetailsvo.setSingleMemberRegCnt(0l);
						  missedCallsDetailsvo.setMultiMemberRegCnt(0l);
						  resultMap.put((Long)objects[0], missedCallsDetailsvo); 
						}
				     }
			   }
			  
			  List<Object[]> regCountList=tdpCadreDAO.constituencyWiseRegCountForDistrict(districtId);//cid,count.
			  List<Object[]> printCountList=zebraPrintDetailsDAO.getTotalPrintingCountByDistrict(districtId);//cid,cname,count.
			  //List<Object[]>  receivedMissedCallsList=tdpCadreDAO.constituencyWiseRecivingMissedCallsCount(districtId,startDate,endDate);//cid,count.
			  //List<Object[]>  multiMemberRegisteredForMobileList=tdpCadreDAO.multiMemberRegisteredForMobile(districtId,startDate,endDate);//cid,mno,count.
			  
			  if(regCountList!=null && regCountList.size()>0){
				  for (Object[] parm : regCountList){
					  MissedCallsDetailsVO missedCallsDetailsVO= resultMap.get((Long)parm[0]);
					  missedCallsDetailsVO.setTotalCount((Long)parm[1]);
						 
					}
		       }
			   			  
			  if(printCountList!=null && printCountList.size()>0){
				  for (Object[] obj : printCountList) {
					  MissedCallsDetailsVO missedCallsDetailsVO=resultMap.get((Long)obj[0]);
					  missedCallsDetailsVO.setPrintedCount((Long)obj[2]);
				}
				  
			  }
			  
			  /*if(receivedMissedCallsList!=null && receivedMissedCallsList.size()>0){
				  for (Object[] obj : receivedMissedCallsList){
					  MissedCallsDetailsVO missedCallsDetailsVO=resultMap.get((Long)obj[0]);
					  missedCallsDetailsVO.setMissedCallsCount((Long)obj[1]);
				}
			  }
			  
			  if(multiMemberRegisteredForMobileList!=null && multiMemberRegisteredForMobileList.size()>0){
				  for (Object[] obj : multiMemberRegisteredForMobileList) {
					  MissedCallsDetailsVO missedCallsDetailsVO= resultMap.get((Long)obj[0]);
					  if( (Long.parseLong(obj[2].toString().trim()))==1){
						  missedCallsDetailsVO.setSingleMemberRegCnt(missedCallsDetailsVO.getSingleMemberRegCnt()+1);
					  }
					  else if((Long.parseLong(obj[2].toString().trim()))>1){
						  missedCallsDetailsVO.setMultiMemberRegCnt(missedCallsDetailsVO.getMultiMemberRegCnt()+1);
					  }					  
				}
			  }*/
			  
			  List<Object[]> list = tdpCadreDAO.getConstituencyWiseMemberMobileNumbersCount(districtId,startDate,endDate);
			  List<String> mobileNosList  = new ArrayList<String>();
			  
			  Map<Long,List<String>> disSingleMap = new HashMap<Long, List<String>>(0);
			  //MissedCallsDetailsVO missedCallsDetailsVO = null;
			  for(Object[] params : list){		
					List<String> mobList = disSingleMap.get((Long)params[0]);
					if(mobList == null)
						mobList = new ArrayList<String>(0);				
					mobList.add(params[1].toString());
					disSingleMap.put((Long)params[0],mobList);
					//if(!mobileNosList.contains(params[1].toString()))
						//mobileNosList.add(params[1].toString());
			  }
					
			  for(Map.Entry<Long,List<String>> entry : disSingleMap.entrySet()){
					Map<String,Long> mobTempMap = new HashMap<String,Long>(0);
					Long singleCount = 0l;
					Long multiCount = 0l;
					Long mobileCnt = 0L;
					for(String mobile : entry.getValue())
					{
						Long cnt = mobTempMap.get(mobile);
						if(cnt == null)
							cnt = 0L;
						mobTempMap.put(mobile,++cnt);
					}
					for(Map.Entry<String,Long> entry2 : mobTempMap.entrySet())
					{
						mobileCnt = mobileCnt+1;
						if(entry2.getValue().longValue() == 1L)
							singleCount= singleCount +1;
						else
							multiCount= multiCount+1;
					}
					MissedCallsDetailsVO missedCallsDetailsVO =resultMap.get(entry.getKey());
					missedCallsDetailsVO.setMissedCallsCount(mobileCnt);
					missedCallsDetailsVO.setSingleMemberRegCnt(singleCount);
					missedCallsDetailsVO.setMultiMemberRegCnt(multiCount);
			 }
			
			  
			 if(resultMap!=null && resultMap.size()>0){
			   missedCallsDetailsVOList=new ArrayList<MissedCallsDetailsVO>(resultMap.values());
			 }
			
		  }catch(Exception e){
			  LOG.error("Exception Occured in missedCallDetailsForADistrict()", e);
		  }
		 return  missedCallsDetailsVOList;
	  } 
	public List<CardPrintUserVO> getCardPrintCountForAllUsers(CardPrintUserVO inputVo)
	{
		List<CardPrintUserVO>  finalList = new ArrayList<CardPrintUserVO>();
		try{
			if(inputVo.getUname() == null || inputVo.getUname().trim().length()  == 0)
			 {
					CardPrintUserVO returnVO = new CardPrintUserVO();
					returnVO.setStatus("Invalid");
					finalList.add(returnVO);
					 return finalList;
			   
			 }
			 List validCheck = cardPrintUserDAO.checkUserEixsts(inputVo.getUname(),inputVo.getPwd());
			 if(validCheck == null || validCheck.size() == 0)
			 {
				 CardPrintUserVO returnVO = new CardPrintUserVO();
				 returnVO.setStatus("Invalid");
				 finalList.add(returnVO);
				 return finalList;
			 }
			 Date startDate = null;
			 Date endDate1 = null;
			String strDate = inputVo.getFromDate();
			String endDate = inputVo.getEndDate();
			if(strDate != null && !strDate.isEmpty())
			startDate=new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
			if(endDate != null && !endDate.isEmpty())
		    endDate1=new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
		   List<Object[]> list = cadreCardNumberUpdationDAO.getPrintCountsForAllUser(startDate,endDate1);
		   if(list != null && list.size() > 0)
		   setPrintCnts(finalList,list,"print");
		   List<Object[]> list1 = cadreCardNumberUpdationDAO.getReprintCountsForAllUser(startDate,endDate1);
		   setPrintCnts(finalList,list1,"reprint");
		}
		catch(Exception e)
		{
			 LOG.error("Exception Occured in getCardPrintUsersCount()", e);
		}
		return finalList;
	}

	public List<CardPrintUserVO> setPrintCnts(List<CardPrintUserVO> finalList,List<Object[]> list,String status)
	{
		  if(list != null && list.size() > 0)
		  {
			  for(Object[] params : list)
			  {
				  CardPrintUserVO userVO = getMatchedVO(finalList,(Long)params[3]);
				  if(userVO == null)
				  {
					  userVO = new CardPrintUserVO();
					  userVO.setId((Long)params[3]);
					  userVO.setUname(params[1].toString());
					  finalList.add(userVO);
				  } 
				  CardPrintUserVO dateVo = getMatchedDate(userVO.getSubList(), params[2].toString());
				  if(dateVo == null)
				  {
					  dateVo = new CardPrintUserVO();  
					  dateVo.setDate(params[2].toString());
					  if(status.equalsIgnoreCase("print"))
					  {
					  dateVo.setPrintCnt(dateVo.getPrintCnt() + (Long)params[0]);
					  userVO.setPrintCnt(userVO.getPrintCnt() + (Long)params[0]);
					  }
					  else
					  {
						  dateVo.setReprintCnt(dateVo.getReprintCnt() + (Long)params[0]); 
						  userVO.setReprintCnt(userVO.getReprintCnt() + (Long)params[0]);
					  }
					  dateVo.setCount(dateVo.getCount() + (Long)params[0]);
					  userVO.setTotal(userVO.getTotal() + (Long)params[0]);
					  userVO.getSubList().add(dateVo);
				  }
				  else
				  {
					  if(status.equalsIgnoreCase("print"))
					  {
					  dateVo.setPrintCnt(dateVo.getPrintCnt() + (Long)params[0]);
					  userVO.setPrintCnt(userVO.getPrintCnt() + (Long)params[0]);
					  }
					  else
					  {
						  dateVo.setReprintCnt(dateVo.getReprintCnt() + (Long)params[0]); 
						  userVO.setReprintCnt(userVO.getReprintCnt() + (Long)params[0]);
					  }
					  dateVo.setCount(dateVo.getCount() + (Long)params[0]);
					  userVO.setTotal(userVO.getTotal() + (Long)params[0]);
				  }
			  }
				finalList.get(0).setStatus("success");
				
		  }
		return finalList;
	}
	
	public List<CardPrintUserVO> getCardPrintCountByUser(CardPrintUserVO inputVo)
	{
		List<CardPrintUserVO>  finalList = new ArrayList<CardPrintUserVO>();
		try{
			if(inputVo.getUname() == null || inputVo.getUname().trim().length()  == 0)
			 {
					CardPrintUserVO returnVO = new CardPrintUserVO();
					returnVO.setStatus("Invalid");
					finalList.add(returnVO);
					 return finalList;
			   
			 }
			 List validCheck = cardPrintUserDAO.checkUserEixsts(inputVo.getUname(),inputVo.getPwd());
			 if(validCheck == null || validCheck.size() == 0)
			 {
				 CardPrintUserVO returnVO = new CardPrintUserVO();
				 returnVO.setStatus("Invalid");
				 finalList.add(returnVO);
				 return finalList;
			 }
			 Date startDate = null;
			 Date endDate1 = null;
			String strDate = inputVo.getFromDate();
			String endDate = inputVo.getEndDate();
			if(strDate != null && !strDate.isEmpty())
			startDate=new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
			if(endDate != null && !endDate.isEmpty())
		    endDate1=new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
		   List<Object[]> list = cadreCardNumberUpdationDAO.getPrintCountsForUser(startDate,endDate1,(Long)validCheck.get(0));
			  if(list != null && list.size() > 0)
			  {
				  for(Object[] params : list)
				  {
					  CardPrintUserVO userVO = getMatchedVO(finalList,(Long)params[3]);
					
					  if(userVO == null)
					  {
						  userVO = new CardPrintUserVO();
						  userVO.setId((Long)params[3]);
						  userVO.setUname(params[1].toString());
						  finalList.add(userVO);
					  }
					  
					  CardPrintUserVO dateVo = getMatchedDate(userVO.getSubList(),params[2].toString());
					  if(dateVo == null)
					  {
						  dateVo = new CardPrintUserVO();
						  dateVo.setDate(params[2].toString());
						
						  userVO.getSubList().add(dateVo);
					  }
					 
					  CardPrintUserVO memberShipVo = getMatchedMemberShipNo(dateVo.getSubList(),params[0].toString());
					  if(memberShipVo == null)
					  {
						  memberShipVo = new CardPrintUserVO();
						  String cardNo = params[0].toString().trim();
						  if(cardNo.length() > 8){
							  cardNo = cardNo.substring(cardNo.length() - 8);
							}
						  memberShipVo.setMembershipNumber(cardNo);
						  if(params[4] == null)
						  {
							  dateVo.setPrintCnt(dateVo.getPrintCnt() + 1);
							  memberShipVo.setPrintCnt(memberShipVo.getPrintCnt() + 1);
							  userVO.setPrintCnt(userVO.getPrintCnt() + 1);
						  }
						  else
						  {
							  dateVo.setReprintCnt(dateVo.getReprintCnt() + 1);
							  memberShipVo.setReprintCnt(memberShipVo.getReprintCnt() + 1);
							  userVO.setReprintCnt(userVO.getReprintCnt() + 1);
						  }
						  memberShipVo.setCount(memberShipVo.getCount() + 1);
						  dateVo.setTotal(dateVo.getTotal() + 1);
						  userVO.setTotal(userVO.getTotal() + 1);
						  dateVo.getSubList().add(memberShipVo); 
					  }
					  else
					  {
						  if(params[4] == null)
						  {
							  dateVo.setPrintCnt(dateVo.getPrintCnt() + 1);
							  memberShipVo.setPrintCnt(memberShipVo.getPrintCnt() + 1);
							  userVO.setPrintCnt(userVO.getPrintCnt() + 1);
						  }
						  else
						  {
							  dateVo.setReprintCnt(dateVo.getReprintCnt() + 1);
							  memberShipVo.setReprintCnt(memberShipVo.getReprintCnt() + 1);
							  userVO.setReprintCnt(userVO.getReprintCnt() + 1);
						  }
						  memberShipVo.setCount(memberShipVo.getCount() + 1);  
						  dateVo.setTotal(dateVo.getTotal() + 1);
						  userVO.setTotal(userVO.getTotal() + 1);
						 
					  }
					  
					 
				  }
				  finalList.get(0).setStatus("success");
			  }
		}
					
		catch(Exception e)
		{
			 LOG.error("Exception Occured in getCardPrintUsersCount()", e);
		}
		return finalList;
	}

	public CardPrintUserVO getMatchedVO(List<CardPrintUserVO> resultList,Long userId)
	{
		 try{
			
			if(resultList == null || resultList.size() == 0)
				return null;
			for(CardPrintUserVO vo : resultList)
			{
			  if(vo.getId().longValue() == userId.longValue())
				  return vo;
			}
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		return null;
	}
	
	public CardPrintUserVO getMatchedDate(List<CardPrintUserVO> resultList,String date)
	{
		 try{
			
			if(resultList == null || resultList.size() == 0)
				return null;
			for(CardPrintUserVO vo : resultList)
			{
			  if(vo.getDate().toString().equalsIgnoreCase(date.toString()))
				  return vo;
			}
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		return null;
	}
	
	
	public CardPrintUserVO getMatchedMemberShipNo(List<CardPrintUserVO> resultList,String memberShipNo)
	{
		 try{
			if(memberShipNo!= null && memberShipNo.trim().length() > 8){
				memberShipNo = memberShipNo.trim().substring(memberShipNo.trim().length() - 8);
			}
			if(resultList == null || resultList.size() == 0)
				return null;
			for(CardPrintUserVO vo : resultList)
			{
			  if(vo.getMembershipNumber().toString().equalsIgnoreCase(memberShipNo.toString()))
				  return vo;
			}
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		return null;
	}
	
	public ResultStatus updateCadreFamilyInfo(final List<TdpCadreFamilyDetailsVO> inputList,final Long userId)
	{
		ResultStatus rs = new ResultStatus();
		try{
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					DateUtilService date= new DateUtilService();
					Long userPrimaryKey=null;
					if(inputList != null && inputList.size() > 0)
					{
						int i=0;
						tdpCadreFamilyInfoDAO.moveFamilyInfoToHistoryByCadre(inputList.get(0).getTdpCadreId());
						for(TdpCadreFamilyDetailsVO vo : inputList)
						{
							
							//saving UserAdressDetails
							if(i==0)
							{
								i=i+1;
								AddressVO addressVO=vo.getAddressVo();
								
								UserAddress userAddress=null;
								UserAddress returnUserAddress=null;
								
								if(addressVO !=null){
									userAddress=new UserAddress();
									
									String hsNo=addressVO.getHouseNo();
									String street=addressVO.getStreet();
									String pinCode=addressVO.getPinCodeStr();
									Long stateId=addressVO.getStateId();
									Long districtId=addressVO.getDistrictId();
									Long constituencyId=addressVO.getConstituencyId();
									Long tehsilId=addressVO.getTehsilId();
									Long panchayatId=addressVO.getPanchaytId();
									Long boothId=addressVO.getBoothId();
									String landMark=addressVO.getLandMarkStr();
									Long localElectionBodyId=addressVO.getLocalElectionBodyId();
									Long wardId=addressVO.getWardId();
									if(hsNo !=null && !hsNo.equalsIgnoreCase("")){
										userAddress.setHouseNo(hsNo);
									}else{
										userAddress.setHouseNo(null);
									}
									if(street !=null && !street.equalsIgnoreCase("") ){
										userAddress.setStreet(street);
									}else{
										userAddress.setStreet(null);
									}
									if(pinCode !=null && !pinCode.equalsIgnoreCase("") ){
										userAddress.setPinCode(pinCode);
									}
									else{
										userAddress.setPinCode(null);
									}
									if(stateId !=null && stateId !=0l){
										userAddress.setState(stateDAO.get(stateId));
									}
									if(districtId !=null && districtId !=0l){
										userAddress.setDistrict(districtDAO.get(districtId));
									}
									if(constituencyId !=null && constituencyId !=0l){
										userAddress.setConstituency(constituencyDAO.get(constituencyId));
									}
									if(tehsilId !=null && tehsilId !=0l){
										userAddress.setTehsil(tehsilDAO.get(tehsilId));
									}
									if(panchayatId !=null && panchayatId !=0l){
										userAddress.setPanchayatId(panchayatId);
									}
									if(boothId !=null && boothId !=0l){
										userAddress.setBooth(boothDAO.get(boothId));
									}
									if(landMark !=null && !landMark.equalsIgnoreCase(""))
									{
										userAddress.setLocalArea(landMark);
									}
									else{
										userAddress.setLocalArea(null);
									}
									if(localElectionBodyId !=null && localElectionBodyId !=0l){
										userAddress.setLocalElectionBody(localElectionBodyDAO.get(localElectionBodyId));
									}
									if(wardId !=null && wardId !=0l){
										userAddress.setWard(constituencyDAO.get(wardId));
									}								
									if(userAddress !=null){
										returnUserAddress=userAddressDAO.save(userAddress);
									}
									userPrimaryKey=returnUserAddress.getUserAddressId();
								}
							}
							
							
						TdpCadreFamilyInfo tdpCadreFamilyInfo = new TdpCadreFamilyInfo();
						
							if(vo.getAge() != null && vo.getAge().longValue()>0L)
								tdpCadreFamilyInfo.setAge(vo.getAge());
							
							if(vo.getGender() != null && !vo.getGender().isEmpty())
								tdpCadreFamilyInfo.setGender(vo.getGender());
							
							if(vo.getCasteStateId() != null && vo.getCasteStateId().longValue()>0L)
								tdpCadreFamilyInfo.setCasteStateId(vo.getCasteStateId());
							
							if(vo.getEducationId() != null && vo.getEducationId().longValue() > 0L)
								tdpCadreFamilyInfo.setEducationId(vo.getEducationId());
							
							if(vo.getOccupationId() != null && vo.getOccupationId().longValue()>0L)
								tdpCadreFamilyInfo.setOccupationId(vo.getOccupationId());
							
							if(vo.getEmail() != null && !vo.getEmail().isEmpty())
								tdpCadreFamilyInfo.setEmail(vo.getEmail());
							
							if(vo.getFacebookUrl() != null && !vo.getFacebookUrl().isEmpty())
								tdpCadreFamilyInfo.setFacebookUrl(vo.getFacebookUrl());
								
							if(vo.getMobileNo() != null && !vo.getMobileNo().isEmpty())
								tdpCadreFamilyInfo.setMobileNo(vo.getMobileNo());	
							
							
							if(vo.getName() != null && !vo.getName().isEmpty())
								tdpCadreFamilyInfo.setName(vo.getName());
							
							if(vo.getRelationId() != null && vo.getRelationId().longValue()>0)
								tdpCadreFamilyInfo.setRelationId(vo.getRelationId());
							
							try {
								
								if(vo.getDob() != null && !vo.getDob().isEmpty())
									tdpCadreFamilyInfo.setDob(format.parse(vo.getDob()));
								
								if(vo.getPartyMemberSince() != null && !vo.getPartyMemberSince().isEmpty())
									tdpCadreFamilyInfo.setPartyMemberSince(format.parse(vo.getPartyMemberSince()));
								
								
								if(vo.getMarriageDay() != null && !vo.getMarriageDay().isEmpty())
									tdpCadreFamilyInfo.setMarriageDay(format.parse(vo.getMarriageDay()));
								
							} catch (Exception e) {}
							
							
							if(vo.getTdpCadreId() != null && vo.getTdpCadreId().longValue()>0L)
								tdpCadreFamilyInfo.setTdpCadreId(vo.getTdpCadreId());
							
							if(vo.getVotercardNo() != null && !vo.getVotercardNo().isEmpty())
								tdpCadreFamilyInfo.setVoterId(voterDAO.getVoterIdByIdCardNo(vo.getVotercardNo()));
							
							if(vo.getWhatsappStatus() != null && !vo.getWhatsappStatus().isEmpty())
								tdpCadreFamilyInfo.setWhatsappStatus(vo.getWhatsappStatus());
							
							tdpCadreFamilyInfo.setIsDeleted("N");
							tdpCadreFamilyInfo.setInsertedBy(userId);
							tdpCadreFamilyInfo.setInsertedTime(date.getCurrentDateAndTime());
							tdpCadreFamilyInfo.setUpdatedTime(date.getCurrentDateAndTime());
							tdpCadreFamilyInfo.setUpdatedBy(userId);
							if(userPrimaryKey !=null){
								tdpCadreFamilyInfo.setUserAddressId(userPrimaryKey);
							}
							tdpCadreFamilyInfoDAO.save(tdpCadreFamilyInfo);
							
						}
					}
					
				}
			});
			rs.setResultCode(ResultCodeMapper.SUCCESS);
		}
		catch (Exception e) {
			LOG.error("Exception in updateCadreFamilyInfo()", e);
			rs.setResultCode(ResultCodeMapper.FAILURE);
		}
		return rs;
		
	}
	public List<SelectOptionVO> getBloodGroups()
	{
		List<SelectOptionVO> bloodGroups = new ArrayList<SelectOptionVO>(0);
		try{
		 List<BloodGroup> list = bloodGroupDAO.getAll();
		
		if(list != null && list.size() > 0)
		{
			
			SelectOptionVO selectOptionVO = null;
			for(BloodGroup bloodGroup : list)
			{
				selectOptionVO = new SelectOptionVO(bloodGroup.getBloodGroupId(),
						bloodGroup.getBloodGroup());
				bloodGroups.add(selectOptionVO);
			}
			
			
		}
		}
		catch (Exception e) {
			LOG.error("Exception in getBloodGroups()", e);
		}
		return bloodGroups;
	}
	

	public List<CadreRegistrationVO> searchCadreDetailsForFamilyDetlsUpdate(String mobileNo,String voterId,String membership)
	{
		List<CadreRegistrationVO> returnList = new ArrayList<CadreRegistrationVO>();
		try {
			
			String memberShipNo = "AP14"+membership ;
			String memberShipNo1 = "TS14"+membership ;
			
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select model.tdpCadreId,model.image,model.userAddress.constituency.name,model.memberShipNo,model.mobileNo,model.firstname" +
					" ,model.casteStateId,model.gender,model.emailId,model.age,model.dateOfBirth,model.voterId,model.occupationId,model.educationId," +
					"  model.userAddress " +
					"   from  TdpCadre model where model.isDeleted='N' and model.enrollmentYear = 2014 ");
			
			if(membership != null && !membership.isEmpty())
				queryStr.append(" and (model.memberShipNo ='"+memberShipNo.trim()+"' OR model.memberShipNo ='"+memberShipNo1.trim()+"' OR model.memberShipNo ='"+membership.trim()+"') ");
		    if(mobileNo != null && !mobileNo.isEmpty())
				queryStr.append(" and model.mobileNo = '" +mobileNo+"'");
			if(voterId != null && !voterId.isEmpty())
				queryStr.append(" and model.voter.voterIDCardNo = '"+voterId+"'");
			List<Object[]> resultList = tdpCadreDAO.getCadreDetails(queryStr.toString());
			Map<Long,CadreRegistrationVO> cadreMap = new HashMap<Long, CadreRegistrationVO>();
			
			if(resultList != null && resultList.size() > 0){
				for (Object[] obj : resultList){
					CadreRegistrationVO vo = new CadreRegistrationVO();					
					vo.setCadreId((Long)obj[0]);
					vo.setImageBase64String(obj[1] != null ? obj[1].toString(): "");
					vo.setConstituencyId(obj[2] != null ? obj[2].toString() : "");
					
					if(obj[3] != null){
						if(obj[3].toString().trim().length() > 8){
							vo.setAddress(obj[3].toString().trim().substring(obj[3].toString().trim().length()-8));
						}else{
							vo.setAddress(obj[3].toString());
						}
					}else{
						vo.setAddress("");
					}
					//vo.setAddress(obj[3] != null ? obj[3].toString().substring(4) : "");
					vo.setMobileNumber(obj[4] != null ? obj[4].toString(): "");
					vo.setNameType(obj[5]  != null ? obj[5].toString() :"");
					vo.setCasteId( obj[6] != null ?(Long)obj[6]: null);
					vo.setGender( obj[7] != null ? obj[7].toString() : null);
					vo.setEmail(obj[8]!= null ? obj[8].toString() : null);
					vo.setAge(obj[9] != null ? (Long)obj[9] : null);
					vo.setEducationId(obj[13] != null ? (Long)obj[13] : null);
					vo.setOccupationId(obj[12] != null ? (Long)obj[12] : null);
					vo.setDobStr(obj[10] != null ? obj[10].toString(): null);
					vo.setVoterCardNo(obj[11] != null ? voterDAO.get((Long)obj[11]).getVoterIDCardNo() : null);
					
					//UserAddress userAddres=null;
					
					if(obj[14] !=null){
						UserAddress	userAddress=(UserAddress) obj[14];
						if(userAddress !=null){
							settingUserDetailsOfCadre(userAddress,vo);
						}
					}
					
					
					//returnList.add(vo);
					cadreMap.put(vo.getCadreId(), vo);
				}				
			}
			
			List<TdpCadreFamilyInfo> updatedCadreInfoList = tdpCadreFamilyInfoDAO.getCadresFamilyDetailsBytdpCadreIdList(cadreMap.keySet());
			if(updatedCadreInfoList != null && updatedCadreInfoList.size()>0)
			{
				for (TdpCadreFamilyInfo familyVO : updatedCadreInfoList) {
					CadreRegistrationVO vo = cadreMap.get(familyVO.getTdpCadreId());
					if(vo != null)
					{
						vo.setNameType(getFinalStringValue(familyVO.getName()));
						vo.setMobileNumber(getFinalStringValue(familyVO.getMobileNo()));
						vo.setGender(getFinalStringValue(familyVO.getGender()));
						vo.setAge(familyVO.getAge() != null ? Long.valueOf(familyVO.getAge()):0L);
						vo.setMarriageDateStr(getFinalStringValue(familyVO.getMarriageDay()));
						vo.setDobStr(getFinalStringValue(familyVO.getDob()));
						vo.setEmail(getFinalStringValue(familyVO.getEmail()));
						vo.setFaceboohUrl(familyVO.getFacebookUrl());
						vo.setCasteId(familyVO.getCasteStateId() != null ? Long.valueOf(familyVO.getCasteStateId()):0L);
						vo.setEducationId(familyVO.getEducationId() != null ? Long.valueOf(familyVO.getEducationId()):0L);
						vo.setOccupationId(familyVO.getOccupationId() != null ? Long.valueOf(familyVO.getOccupationId()):0L);
						vo.setWhatsAppStatus(getFinalStringValue(familyVO.getWhatsappStatus()));
						vo.setPartyMemberSinceStr(getFinalStringValue(familyVO.getPartyMemberSince()));
						vo.setCadreId(familyVO.getTdpCadreId());
						//vo.setBloodGroupId(Long.valueOf(getFinalStringValue(familyVO.getOccupationId())));

						//Updating UserAddress
						
						UserAddress userAddress=familyVO.getUserAddress();
						if(userAddress !=null){
							settingUserDetailsOfCadre(userAddress,vo);
						}
								
					}
				}
			}
			
			if(cadreMap != null && cadreMap.size()>0)
			{
				for (Long tdpCadreeId : cadreMap.keySet()) {
					returnList.add(cadreMap.get(tdpCadreeId));
				}
			}
		} catch (Exception e) {
			LOG.error(" exception occured in searchCadreDetailsForFamilyDetlsUpdate()",e);	
		}
		
		return returnList;
	}
	//Setting && Updating UserDetails
	public void settingUserDetailsOfCadre(UserAddress userAddress,CadreRegistrationVO vo){
		
		AddressVO addressVO=new AddressVO();
		try{
			addressVO.setHouseNo(userAddress.getHouseNo() !=null ? userAddress.getHouseNo().toString() :null);
			addressVO.setStreet(userAddress.getStreet() !=null ? userAddress.getStreet().toString():null);
			addressVO.setPinCodeStr(userAddress.getPinCode() !=null ? userAddress.getPinCode().toString() : null);
			addressVO.setStateId(userAddress.getState() !=null ? userAddress.getState().getStateId().longValue() :0l);
			addressVO.setDistrictId(userAddress.getDistrict() !=null ? userAddress.getDistrict().getDistrictId().longValue() :0l);
			addressVO.setConstituencyId(userAddress.getConstituency() !=null ? userAddress.getConstituency().getConstituencyId().longValue() :0l );
			
			Tehsil tehsilModel=userAddress.getTehsil();
			Long tehsilIdLong=0l;
			if(tehsilModel !=null){
				String tehsilStr=tehsilModel.getTehsilId().toString();
				String tehsilIdStr="2"+tehsilStr;
				 tehsilIdLong=Long.parseLong(tehsilIdStr);
			}
			addressVO.setTehsilId(tehsilIdLong.longValue());
			
			LocalElectionBody localElectionModel=userAddress.getLocalElectionBody();
			Long localElectionLong=0l;
			if(localElectionModel !=null){
				String localElectionStr=localElectionModel.getLocalElectionBodyId().toString();
				String  localElectionIdStr="1"+localElectionStr;
				localElectionLong=Long.parseLong(localElectionIdStr);
			}
			addressVO.setLocalElectionBodyId(localElectionLong.longValue());
			
			//Panchayat && Ward
			Panchayat panchayatModel=userAddress.getPanchayat();
			Long panchayatIdLong=0l;
			if(panchayatModel !=null){
				String panchayatStr=panchayatModel.getPanchayatId().toString();
				String panchayatIdStr="1"+panchayatStr;
				panchayatIdLong=Long.parseLong(panchayatIdStr);
			}
			addressVO.setPanchaytId(panchayatIdLong.longValue());
			
			Constituency constituecnyModel=userAddress.getWard();
			Long constituencyIdLong=0l;
			if(constituecnyModel !=null){
				String constiStr=constituecnyModel.getConstituencyId().toString();
				String  constIdStr="2"+constiStr;
				constituencyIdLong=Long.parseLong(constIdStr);
			}
			addressVO.setWardId(constituencyIdLong);
			
			/*addressVO.setPanchaytId(userAddress.getPanchayat() !=null ? userAddress.getPanchayat().getPanchayatId().longValue() :0l);*/
		/*	addressVO.setWardId(userAddress.getWard() !=null ? userAddress.getWard().getConstituencyId().longValue() :0l);*/
			addressVO.setLandMarkStr(userAddress.getLocalArea() !=null ? userAddress.getLocalArea().toString() :null);
			
			List<SelectOptionVO> selectDistrictList=null;
			List<SelectOptionVO> selectConstituencyList=null;
			List<SelectOptionVO> selectMandalList=null;
			List<GenericVO> genericPanchayatList=null;
			if(addressVO.getStateId() !=null && addressVO.getStateId() !=0l){
				selectDistrictList=regionServiceDataImp.getDistrictsForState(addressVO.getStateId());
			}
			if(addressVO.getDistrictId() !=null && addressVO.getDistrictId() !=0l){
				selectConstituencyList=regionServiceDataImp.getConstituenciesByDistrictID(addressVO.getDistrictId());
			}
			SelectOptionVO tehsilIst=null;
			if(addressVO.getConstituencyId() !=null && addressVO.getConstituencyId() !=0l){

				List<SelectOptionVO> subRegions = getRegionServiceDataImp().getSubRegionsInConstituency(addressVO.getConstituencyId(), IConstants.PRESENT_YEAR, null);
				subRegions.add(0, new SelectOptionVO(0l,"Select Location"));
				List stateValue = constituencyDAO.getStateForParliamentConstituency(addressVO.getConstituencyId());
				Object[] list = (Object[])stateValue.get(0);
				if((Long)list[0] == 24){
					for(SelectOptionVO subRegion:subRegions){
						subRegion.setName(subRegion.getName().replaceAll("MANDAL", "TALUK"));
					}
				}
				setRegionsList(subRegions);
					if(subRegions !=null){
						tehsilIst=new SelectOptionVO();
						tehsilIst.setSelectOptionsList(subRegions);
					}
					
			}
			if(tehsilIst != null && tehsilIst.getSelectOptionsList() != null && tehsilIst.getSelectOptionsList().size()>0)
			for(SelectOptionVO regions:tehsilIst.getSelectOptionsList()){
				if(regions.getId() !=null && regions.getId() !=0l){
					
					/*String regionstr=regions.getId().toString();
					Long regionId=Long.parseLong(regionstr.substring(1,regionstr.length()));*/
					
					if((addressVO.getTehsilId() !=null &&addressVO.getTehsilId() !=0l) &&(addressVO.getTehsilId().longValue() == regions.getId().longValue())){
						genericPanchayatList=cadreCommitteeService.getPanchayatDetailsByMandalIdAddingParam(regions.getId());
					}else if((addressVO.getLocalElectionBodyId() !=null &&addressVO.getLocalElectionBodyId() !=0l) &&(addressVO.getLocalElectionBodyId().longValue() == regions.getId().longValue())){
						genericPanchayatList=cadreCommitteeService.getPanchayatDetailsByMandalIdAddingParam(regions.getId());
					}
				}
				
			}
			if(selectDistrictList !=null){
				addressVO.setDistrictList(selectDistrictList);
			}
			if(selectConstituencyList !=null){
				addressVO.setAddressTypeList(selectConstituencyList);
			}
			if(tehsilIst.getSelectOptionsList() !=null){
				addressVO.setTehsilList(tehsilIst.getSelectOptionsList());
			}
			
			if(genericPanchayatList !=null){
				addressVO.setPanchayatList(genericPanchayatList);
			}
			
			if(addressVO !=null){
				vo.setAddressVO(addressVO);
			}
		}
		catch (Exception e) {
			LOG.error(" exception occured in settingUserDetailsOfCadre()",e);
		}
		
		
	}
	
	
	public String getFinalStringValue(Object date){
		String value = "";
		try {
			if(date != null && date.toString().trim().length()>0)
			{
				return date.toString().trim();
			}
		} catch (Exception e) {
			LOG.error(" exception occured in getFinalStringValue()",e);	
		}
		return value;
	}
	
	public List<TdpCadreFamilyDetailsVO> getFamilyDetailsByCadreId(Long tdpCadreId)
	{
		List<TdpCadreFamilyDetailsVO> returnList = new ArrayList<TdpCadreFamilyDetailsVO>();
		try {
			Map<String ,TdpCadreFamilyDetailsVO > familyMap = new LinkedHashMap<String, TdpCadreFamilyDetailsVO>();
			//Map<String ,TdpCadreFamilyDetailsVO > newfamilyMap = new LinkedHashMap<String, TdpCadreFamilyDetailsVO>();

/*
			Set<Long> tdpCadreIds = new HashSet<Long>();
			tdpCadreIds.add(tdpCadreId);
			List<TdpCadreFamilyInfo> updatedCadreInfoList = tdpCadreFamilyInfoDAO.getCadresFamilyDetailsBytdpCadreIdList(tdpCadreIds);
			if(updatedCadreInfoList != null && updatedCadreInfoList.size()>0)
			{
				for (TdpCadreFamilyInfo familyVO : updatedCadreInfoList) {
					TdpCadreFamilyDetailsVO fmilyVO = new TdpCadreFamilyDetailsVO();
					fmilyVO.setVoterId(Long.valueOf(getFinalStringValue(familyVO.getVoterId())));
					fmilyVO.setName(getFinalStringValue(familyVO.getName()));
					fmilyVO.setDob(getFinalStringValue(familyVO.getDob()));
					fmilyVO.setMobileNo(getFinalStringValue(familyVO.getMobileNo()));
					fmilyVO.setRelationId(familyVO.getRelationId() != null ? Long.valueOf(familyVO.getRelationId()):0L);
					fmilyVO.setEmail(getFinalStringValue(familyVO.getEmail()));
					fmilyVO.setEducationId(familyVO.getEducationId() != null ? Long.valueOf(familyVO.getEducationId()):0L);
					fmilyVO.setMarriageDay(getFinalStringValue(familyVO.getMarriageDay()));
					fmilyVO.setCasteStateId(familyVO.getCasteStateId() != null ? Long.valueOf(familyVO.getCasteStateId()): 0L);
					fmilyVO.setOccupationId(familyVO.getOccupationId() != null ? Long.valueOf(getFinalStringValue(familyVO.getOccupationId())):0L);
					fmilyVO.setWhatsappStatus(getFinalStringValue(familyVO.getWhatsappStatus()));
					
					familyMap.put(familyVO.getVoter().getVoterIDCardNo().trim(), fmilyVO);
				}
			}
			
			*/
		
			
			List<TdpCadreFamilyInfo> familyInfoDetls = tdpCadreFamilyInfoDAO.getCadreFamilyDetailsBytdpCadreId(tdpCadreId);
			if(familyInfoDetls != null && familyInfoDetls.size() > 0){
				for (TdpCadreFamilyInfo tdpCadreFamilyInfo : familyInfoDetls){
					String voterCardNo = tdpCadreFamilyInfo.getVoterId() != null ? tdpCadreFamilyInfo.getVoter().getVoterIDCardNo():null;
					
					TdpCadreFamilyDetailsVO vo = new TdpCadreFamilyDetailsVO();	
								
					vo.setName(tdpCadreFamilyInfo.getName());
					vo.setMobileNo(tdpCadreFamilyInfo.getMobileNo());
					vo.setGender(tdpCadreFamilyInfo.getGender());
					vo.setAge(tdpCadreFamilyInfo.getAge());
					vo.setEducationId(tdpCadreFamilyInfo.getEducationId());
					vo.setOccupationId(tdpCadreFamilyInfo.getOccupationId());
					vo.setRelationId(tdpCadreFamilyInfo.getRelationId());				
					vo.setDob(tdpCadreFamilyInfo.getDob() != null ? tdpCadreFamilyInfo.getDob().toString() : null);
					vo.setEmail(tdpCadreFamilyInfo.getEmail());
					vo.setFacebookUrl(tdpCadreFamilyInfo.getFacebookUrl());
					vo.setMarriageDay(tdpCadreFamilyInfo.getMarriageDay() != null ? tdpCadreFamilyInfo.getMarriageDay().toString() : null);
					vo.setWhatsappStatus(tdpCadreFamilyInfo.getWhatsappStatus());
					vo.setPartyMemberSince(tdpCadreFamilyInfo.getPartyMemberSince() != null ? tdpCadreFamilyInfo.getPartyMemberSince().toString() : "");
					vo.setCasteStateId(tdpCadreFamilyInfo.getCasteStateId());
					vo.setVotercardNo(voterCardNo);
					
					vo.setOccupation(tdpCadreFamilyInfo.getOccupationId() != null?tdpCadreFamilyInfo.getOccupation().getOccupation():"");
					vo.setEducation(tdpCadreFamilyInfo.getEducationId() != null?tdpCadreFamilyInfo.getEducation().getQualification():"");
					vo.setRelation(tdpCadreFamilyInfo.getRelationId() != null?tdpCadreFamilyInfo.getVoterRelation().getDescription():"");
					//vo.setRelativeName(tdpCadreFamilyInfo.getVoter() != null?tdpCadreFamilyInfo.getVoter().getRelativeName():null);
					
					
					if(tdpCadreFamilyInfo.getRelationId() != null && tdpCadreFamilyInfo.getRelationId() != 13L)
					{
						returnList.add(vo);	
					}
							
				}				
			}else{
				
				//0voterId,1educationId,2occupationId,3voterName,4age,5gender,6relationId,7voterIdCardNo,8qualification,9occupation,10relation,11relativeName
				List<Object[]> familyDetls = tdpCadreFamilyDetailsDAO.getCadreFamilyDetailsBytdpCadreId(tdpCadreId);
				if(familyDetls != null && familyDetls.size() > 0){
					for(Object[] obj : familyDetls){
						
						TdpCadreFamilyDetailsVO vo =  new TdpCadreFamilyDetailsVO();
						
						vo.setVotercardNo(obj[0].toString());
						vo.setName(obj[3] != null ?obj[3].toString() : null);
						vo.setGender(obj[5] != null ? obj[5].toString(): null);
						vo.setAge(obj[4] != null ? (Long)obj[4] : null);
						vo.setEducationId(obj[1] != null ? (Long)obj[1] :null);
						vo.setOccupationId( obj[2] != null ?  (Long)obj[2] : null);
						vo.setRelationId(obj[6] != null ? (Long)obj[6] : null);	
						vo.setVotercardNo(obj[7] != null ? obj[7].toString() : "");
						vo.setEducation(obj[8] != null ? obj[8].toString() : "");
						vo.setOccupation(obj[9] != null ? obj[9].toString() : "");
						vo.setRelation(obj[10] != null ? obj[10].toString() : "");
						//vo.setRelativeName(obj[11] != null ? obj[11].toString() : null);
						
						returnList.add(vo);			
						
					}
				}
				else
				{
					  TdpCadre tdpCadre = tdpCadreDAO.get(tdpCadreId);
					  Booth booth = tdpCadre.getUserAddress().getBooth();
					  Long boothId = booth != null ? booth.getBoothId():null;
					  Long voterId = 0L;
					  String houseNo = null;
					  boolean isFamilyVoterId = false;
					  if(tdpCadre.getVoter() != null)
					  {
						  houseNo  = tdpCadre.getVoter() != null ? tdpCadre.getVoter().getHouseNo():"";
						  voterId = tdpCadre.getVoterId();
					  }
					  else
					  {
						  houseNo  = tdpCadre.getFamilyVoter() != null ? tdpCadre.getFamilyVoter().getHouseNo():"";
						  voterId = tdpCadre.getFamilyVoterId();
						  isFamilyVoterId  =true;
					  }
						if((houseNo != null && houseNo.toString().trim().length()>0) && (boothId != null && boothId.longValue() >0L))
						{
							//0voterId,1name,2relativeName,3relationshipType,4gender,5age,6voterIDCardNo,7relativeName
							List<Object[]> familyInfo = boothPublicationVoterDAO.getFamilyDetaislByHouseNoAndBoothId(boothId,houseNo);
							
							if(familyInfo != null && familyInfo.size()>0)
							{
								for (Object[] family : familyInfo) 
								{
									Long familyVoterID = family[0] != null ? Long.valueOf(family[0].toString().trim()):0L;
									
									if((familyVoterID.longValue() != voterId.longValue()) || (isFamilyVoterId))
									{
										TdpCadreFamilyDetailsVO fmilyVO = new TdpCadreFamilyDetailsVO();
										fmilyVO.setVoterId(family[0] != null ? Long.valueOf(family[0].toString().trim()):0L);
										fmilyVO.setName(family[1] != null ? family[1].toString():"");
										fmilyVO.setGender(family[4] != null ? family[4].toString():"");
										fmilyVO.setAge(family[5] != null ? Long.valueOf(family[5].toString()):0L);								
										fmilyVO.setVotercardNo(family[6] != null ? family[6].toString():"");
										fmilyVO.setRelation(family[3] != null ? family[3].toString():"");
										fmilyVO.setRelativeName(family[7] != null ? family[7].toString():null);
										
										//returnList.add(fmilyVO);
										familyMap.put(fmilyVO.getVotercardNo().trim(), fmilyVO);
									}								
								}
							}
						}
				}
			}
			
			if(familyMap !=null && familyMap.size()>0)
			{
				for (String voterCardNo : familyMap.keySet()) {
					returnList.add(familyMap.get(voterCardNo));
				}
			}
			
			
			
		} catch (Exception e) {
			LOG.error(" exception occured in getFamilyDetailsByCadreId()",e);	
		}
		
		return returnList;
	}
	
	public CardPrintUserVO getCadrePrintDetails(String startDate,String endDate,List<Long> enrollmentYearIds)
	{
		CardPrintUserVO vo = new CardPrintUserVO();
		try{
			 Date startDate1 = null;
			 Date endDate1 = null;
		
			if(startDate != null && !startDate.isEmpty())
			startDate1=new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
			if(endDate != null && !endDate.isEmpty())
		    endDate1=new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
			  List reprintList = cadreCardNumberUpdationDAO.getReprintCountsByDate(startDate1,endDate1,enrollmentYearIds);
			  if(reprintList != null && reprintList.size() > 0)
			  {
				  vo.setReprintCnt((Long)reprintList.get(0));
			  }
				List cadreCnt = tdpCadreDAO.getNewlyRegistredCadreCnt(startDate1,endDate1,enrollmentYearIds);
				if(cadreCnt != null && cadreCnt.size() > 0)
				{
					vo.setTotal((Long)cadreCnt.get(0));
				}
			
		}
		catch (Exception e) {
			LOG.error(" exception occured in getCadrePrintDetails()",e);	
		}
		return vo;
		
	}
	public List<CadreRegistrationVO> getCadreWithFamilyDetailsOfEachCadre(Long cadreId){

		List<CadreRegistrationVO> returnList = new ArrayList<CadreRegistrationVO>();
		try {
			
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select model.tdpCadreId,model.image,model.userAddress.constituency.name,model.memberShipNo,model.mobileNo,model.firstname" +
					" ,model.casteStateId,model.gender,model.emailId,model.age,model.dateOfBirth,model.voterId,model.occupationId,model.educationId," +
					"  model.userAddress " +
					"   from  TdpCadre model where model.isDeleted='N' and model.enrollmentYear = 2014 ");
			
			if(cadreId !=null)
			{
				queryStr.append(" and model.tdpCadreId ='"+cadreId+"'");
			}
			
			List<Object[]> resultList = tdpCadreDAO.getCadreDetails(queryStr.toString());
			Map<Long,CadreRegistrationVO> cadreMap = new HashMap<Long, CadreRegistrationVO>();
			
			if(resultList != null && resultList.size() > 0){
				for (Object[] obj : resultList){
					CadreRegistrationVO vo = new CadreRegistrationVO();					
					vo.setCadreId((Long)obj[0]);
					vo.setImageBase64String(obj[1] != null ? obj[1].toString(): "");
					vo.setConstituencyId(obj[2] != null ? obj[2].toString() : "");
					
					if(obj[3] != null){
						if(obj[3].toString().trim().length() > 8){
							vo.setAddress(obj[3].toString().trim().substring(obj[3].toString().trim().length()-8));
						}else{
							vo.setAddress(obj[3].toString());
						}
					}else{
						vo.setAddress("");
					}
					//vo.setAddress(obj[3] != null ? obj[3].toString().substring(4) : "");
					vo.setMobileNumber(obj[4] != null ? obj[4].toString(): "");
					vo.setNameType(obj[5]  != null ? obj[5].toString() :"");
					vo.setCasteId( obj[6] != null ?(Long)obj[6]: null);
					vo.setGender( obj[7] != null ? obj[7].toString() : null);
					vo.setEmail(obj[8]!= null ? obj[8].toString() : null);
					vo.setAge(obj[9] != null ? (Long)obj[9] : null);
					vo.setEducationId(obj[13] != null ? (Long)obj[13] : null);
					vo.setOccupationId(obj[12] != null ? (Long)obj[12] : null);
					vo.setDobStr(obj[10] != null ? obj[10].toString(): null);
					vo.setVoterCardNo(obj[11] != null ? voterDAO.get((Long)obj[11]).getVoterIDCardNo() : null);
					
					//UserAddress userAddres=null;
					
					if(obj[14] !=null){
						UserAddress	userAddress=(UserAddress) obj[14];
						if(userAddress !=null){
							settingUserDetailsOfCadre(userAddress,vo);
						}
					}
					
					
					//returnList.add(vo);
					cadreMap.put(vo.getCadreId(), vo);
				}				
			}
			
			List<TdpCadreFamilyInfo> updatedCadreInfoList = tdpCadreFamilyInfoDAO.getCadresFamilyDetailsBytdpCadreIdList(cadreMap.keySet());
			if(updatedCadreInfoList != null && updatedCadreInfoList.size()>0)
			{
				for (TdpCadreFamilyInfo familyVO : updatedCadreInfoList) {
					CadreRegistrationVO vo = cadreMap.get(familyVO.getTdpCadreId());
					if(vo != null)
					{
						vo.setNameType(getFinalStringValue(familyVO.getName()));
						vo.setMobileNumber(getFinalStringValue(familyVO.getMobileNo()));
						vo.setGender(getFinalStringValue(familyVO.getGender()));
						vo.setAge(familyVO.getAge() != null ? Long.valueOf(familyVO.getAge()):0L);
						vo.setMarriageDateStr(getFinalStringValue(familyVO.getMarriageDay()));
						vo.setDobStr(getFinalStringValue(familyVO.getDob()));
						vo.setEmail(getFinalStringValue(familyVO.getEmail()));
						vo.setFaceboohUrl(familyVO.getFacebookUrl());
						vo.setCasteId(familyVO.getCasteStateId() != null ? Long.valueOf(familyVO.getCasteStateId()):0L);
						vo.setEducationId(familyVO.getEducationId() != null ? Long.valueOf(familyVO.getEducationId()):0L);
						vo.setOccupationId(familyVO.getOccupationId() != null ? Long.valueOf(familyVO.getOccupationId()):0L);
						vo.setWhatsAppStatus(getFinalStringValue(familyVO.getWhatsappStatus()));
						vo.setPartyMemberSinceStr(getFinalStringValue(familyVO.getPartyMemberSince()));
						vo.setCadreId(familyVO.getTdpCadreId());
						//vo.setBloodGroupId(Long.valueOf(getFinalStringValue(familyVO.getOccupationId())));

						//Updating UserAddress
						
						UserAddress userAddress=familyVO.getUserAddress();
						if(userAddress !=null){
							settingUserDetailsOfCadre(userAddress,vo);
						}
								
					}
				}
			}
			
			if(cadreMap != null && cadreMap.size()>0)
			{
				for (Long tdpCadreeId : cadreMap.keySet()) {
					returnList.add(cadreMap.get(tdpCadreeId));
				}
			}
		} catch (Exception e) {
			LOG.error(" exception occured in getCadreDetailsOfEachCadre()",e);	
		}
		
		return returnList;
	}
	
	public ResultStatus updateMobileNumberForCadre(Long tdpCadreId,String mobileNo,Long userId)
	{
		ResultStatus resultStatus = new ResultStatus();
		try {
			
			if(tdpCadreId != null && tdpCadreId.longValue() > 0L)
			{
				TdpCadre tdpCadre = tdpCadreDAO.get(tdpCadreId);
				saveDataToHistoryTable(tdpCadre);
				tdpCadre.setMobileNo(mobileNo);
				
				tdpCadre.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
				tdpCadre.setUpdatedWebUserId(userId);
				
				tdpCadreDAO.save(tdpCadre);
				
				resultStatus.setResultCode(0);
				resultStatus.setMessage("SUCCESS");
			}
			else
			{
				resultStatus.setResultCode(0);
				resultStatus.setMessage("INVALID MOBILE NUMBER");
			}
			
		} catch (Exception e) {
			resultStatus.setResultCode(1);
			resultStatus.setMessage("ERROR");
			LOG.error("Exception raised in updateMobileNumberForCadre  method in WebServiceHandlerService",e);
		}
		
		return resultStatus;
	}
	
	
	public SurveyCadreResponceVO saveAfflicatedCadreRegistration(final List<CadreRegistrationVO> cadreRegistrationVOList,final String registrationType){
		final SurveyCadreResponceVO surveyCadreResponceVO = new SurveyCadreResponceVO();
		
		TdpCadreBackupDetails tdpCadreBackupDetails= null;
		if(IConstants.ENABLE_LOGS_SAVE)
		{
			tdpCadreBackupDetails = updateRequestDetailsForBackup(cadreRegistrationVOList,registrationType);
		}
		
		try {
			LOG.info("Entered into saveAfflicatedCadreRegistration in CadreRegistrationService service");
			
						if(cadreRegistrationVOList != null && cadreRegistrationVOList.size() >0)
						{
							for (CadreRegistrationVO cadreRegistrationVO : cadreRegistrationVOList)
							{	
								Boolean flag = true;
								if(cadreRegistrationVO != null)
								{	
									//cadreRegistrationVO.setCadreRegType("rtcCadre");
									
									   //SAVE WITH VOTERID.
									if(cadreRegistrationVO.getVoterId() != null 
											&& cadreRegistrationVO.getVoterId().trim().length() > 0 
											&& Long.valueOf(cadreRegistrationVO.getVoterId()) > 0)
									{
										flag = false;
										List<TdpCadre> voterIdsList =  null;
										if(cadreRegistrationVO.getMemberTypeId() != null)
											voterIdsList = tdpCadreDAO.getAffliatedCadreByVoterId(Long.valueOf(cadreRegistrationVO.getVoterId()),Long.valueOf(cadreRegistrationVO.getMemberTypeId()));
										if(voterIdsList != null && voterIdsList.size()  == 0)
										{
											TdpCadre tdpCadre = new TdpCadre();
											tdpCadre.setCardNo(cadreRegistrationVO.getVoterCardNumber());
											tdpCadre.setVoterCardType("VOTER");
											tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
										}
										else
										{
											if(voterIdsList.size() > 0)
											{
												List<Long> existingVoters = new ArrayList<Long>();
												
												for (TdpCadre tdpCadre : voterIdsList) 
												{
													existingVoters.add(tdpCadre.getTdpCadreId());
												}
												boolean needUpdate = true;
												try{
												if(cadreRegistrationVO.getFamilyVoterId() != null && voterIdsList.get(0) != null && voterIdsList.get(0).getVoterId() != null && cadreRegistrationVO.getFamilyVoterId().longValue() > 0 && cadreRegistrationVO.getFamilyVoterId().longValue() == voterIdsList.get(0).getVoterId().longValue()){
													needUpdate = false;
												}
												}catch(Exception e){
													
												}
												if(needUpdate){
													//tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(existingVoters);
												}
												TdpCadre tdpCadre = new TdpCadre();
												
												if(needUpdate){
													tdpCadre = voterIdsList.get(0);
													tdpCadre.setCardNo(cadreRegistrationVO.getVoterCardNumber());
													tdpCadre.setVoterCardType("VOTER");
													saveDataToHistoryTable(tdpCadre);
													tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"update",false);
												}else{
													tdpCadre.setCardNo(cadreRegistrationVO.getVoterCardNumber());
													tdpCadre.setVoterCardType("VOTER");
													tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
												}
											}
											
										}
										
									}
									    //SAVE VOTERCARD NUMBER (OR) FAMILY VOTER CARD NUMBER COMES.
									if(flag)
									{
										
										//SAVE USING VOTER CARD NUMBER .
										if(cadreRegistrationVO.getVoterCardNumber() != null 
												&& !cadreRegistrationVO.getVoterCardNumber().equalsIgnoreCase("null") 
												&& cadreRegistrationVO.getVoterCardNumber().trim().length() > 0)
										{
											List<Long> voterCardNumbersList = voterDAO.getVoterId(cadreRegistrationVO.getVoterCardNumber());
											if(voterCardNumbersList != null && voterCardNumbersList.size() > 0)
											{
												Long voterId = voterCardNumbersList.get(0);
												List<TdpCadre> voterIdsList = tdpCadreDAO.getAffliatedCadreByVoterId(voterId,Long.valueOf(cadreRegistrationVO.getMemberTypeId()));
												if(voterIdsList.size()  == 0)
												{
													TdpCadre tdpCadre = new TdpCadre();
													if(registrationType.equalsIgnoreCase("TAB")){
														tdpCadre.setVoterCardVerified("S");
														tdpCadre.setVoterCardType("VOTER");
														tdpCadre.setVoterId(voterId);
														tdpCadre.setCardNo(cadreRegistrationVO.getVoterCardNumber());
													}
													tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
												}
												else
												{
													if(voterIdsList.size() > 0)
													{

														List<Long> existingVoters = new ArrayList<Long>();
														
														for (TdpCadre tdpCadre : voterIdsList) 
														{
															existingVoters.add(tdpCadre.getTdpCadreId());
														}
														boolean needUpdate = true;
														try{
														if(cadreRegistrationVO.getFamilyVoterId() != null && voterIdsList.get(0) != null && voterIdsList.get(0).getVoterId() != null && cadreRegistrationVO.getFamilyVoterId().longValue() > 0 && cadreRegistrationVO.getFamilyVoterId().longValue() == voterIdsList.get(0).getVoterId().longValue()){
															needUpdate = false;
														}
														}catch(Exception e){
															
														}
														if(needUpdate){
															tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(existingVoters);
														}
														TdpCadre tdpCadre = new TdpCadre();
														tdpCadre.setCardNo(cadreRegistrationVO.getVoterCardNumber());
														if(registrationType.equalsIgnoreCase("TAB")){
															tdpCadre.setVoterCardVerified("S");
															tdpCadre.setVoterCardType("VOTER");
														}
														if(needUpdate){
															tdpCadre = voterIdsList.get(0);
															saveDataToHistoryTable(tdpCadre);
															tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"update",false);
														}else{
															tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
														}
													}
												}
											}else{
												TdpCadre tdpCadre = new TdpCadre();
												tdpCadre.setCardNo(cadreRegistrationVO.getVoterCardNumber());
												if(registrationType.equalsIgnoreCase("TAB")){
													tdpCadre.setVoterCardVerified("F");
													tdpCadre.setVoterCardType("VOTER");
												}
												tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
											}
										             //main else if
										}else if(registrationType.equalsIgnoreCase("TAB") 
												&& cadreRegistrationVO.getRelativeVoterCardNo() != null 
												&& !cadreRegistrationVO.getRelativeVoterCardNo().equalsIgnoreCase("null") 
												&& cadreRegistrationVO.getRelativeVoterCardNo().trim().length() > 0){
											
											//SAVE USING FAMILY VOTER CARD NUMBER .
											
											List<Long> voterCardNumbersList = voterDAO.getVoterId(cadreRegistrationVO.getRelativeVoterCardNo());
											if(voterCardNumbersList != null && voterCardNumbersList.size() > 0)
											{
												Long voterId = voterCardNumbersList.get(0);
												List<TdpCadre> voterIdsList = tdpCadreDAO.getAffliatedCadreByFamilyVoterId(voterId, cadreRegistrationVO.getRefNo());
												if(voterIdsList.size()  == 0){
													TdpCadre tdpCadre = new TdpCadre();
													if(registrationType.equalsIgnoreCase("TAB")){
														tdpCadre.setVoterCardVerified("S");
														tdpCadre.setVoterCardType("FAMILYVOTER");
														tdpCadre.setCardNo(cadreRegistrationVO.getRelativeVoterCardNo());
														tdpCadre.setFamilyVoterId(voterId);
														tdpCadre.setVoterId(null);
													}
													tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
												}else{
													if(voterIdsList.size() > 0)
													{

														List<Long> existingVoters = new ArrayList<Long>();
														
														for (TdpCadre tdpCadre : voterIdsList) 
														{
															existingVoters.add(tdpCadre.getTdpCadreId());
														}
														boolean needUpdate = true;
														try{
														if(cadreRegistrationVO.getFamilyVoterId() != null && voterIdsList.get(0) != null && voterIdsList.get(0).getVoterId() != null && cadreRegistrationVO.getFamilyVoterId().longValue() > 0 && cadreRegistrationVO.getFamilyVoterId().longValue() == voterIdsList.get(0).getVoterId().longValue()){
															needUpdate = false;
														}
														}catch(Exception e){
															
														}
														if(needUpdate){
															tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(existingVoters);
														}
														TdpCadre tdpCadre = new TdpCadre();
														tdpCadre.setCardNo(cadreRegistrationVO.getRelativeVoterCardNo());
														if(registrationType.equalsIgnoreCase("TAB")){
															tdpCadre.setVoterCardVerified("S");
															tdpCadre.setVoterCardType("FAMILYVOTER");
														}
														if(needUpdate){
															tdpCadre = voterIdsList.get(0);
															saveDataToHistoryTable(tdpCadre);
															tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"update",false);
														}else{
															tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
														}
													}
												}
											}else{
												TdpCadre tdpCadre = new TdpCadre();
												tdpCadre.setCardNo(cadreRegistrationVO.getRelativeVoterCardNo());
												if(registrationType.equalsIgnoreCase("TAB")){
													tdpCadre.setVoterCardVerified("F");
													tdpCadre.setVoterCardType("FAMILYVOTER");
												}
												tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
											}
											
											//main else if
										}else if(cadreRegistrationVO.getCadreId() != null && cadreRegistrationVO.getCadreId().longValue() > 0){
											TdpCadre cadre = null;
											try{
												cadre = tdpCadreDAO.get(cadreRegistrationVO.getCadreId());
											}catch(Exception e){
												LOG.error(e);
											}
											if(cadre != null){
												
												List<Long> existingVoters = new ArrayList<Long>();
												existingVoters.add(cadreRegistrationVO.getCadreId());
												
												tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(existingVoters);
												saveDataToHistoryTable(cadre);
												tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,cadre,"update",false);
											}else{
												TdpCadre tdpCadre = new TdpCadre();
												tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
											}
											   
											////main else if
										} else{   //SAVE WHEN FAMILY VOTER ID COMES
									    	if(cadreRegistrationVO.getFamilyVoterId() != null 
									    			&& cadreRegistrationVO.getFamilyVoterId().longValue() > 0){
									    		List<TdpCadre> voterIdsList = tdpCadreDAO.getAffliatedCadreByFamilyVoterId(cadreRegistrationVO.getFamilyVoterId().longValue(), cadreRegistrationVO.getRefNo());
									    		if(voterIdsList.size() > 0){

													List<Long> existingVoters = new ArrayList<Long>();
													
													for (TdpCadre tdpCadre : voterIdsList) 
													{
														existingVoters.add(tdpCadre.getTdpCadreId());
													}
													boolean needUpdate = true;
													try{
													if(cadreRegistrationVO.getFamilyVoterId() != null && voterIdsList.get(0) != null && voterIdsList.get(0).getVoterId() != null && cadreRegistrationVO.getFamilyVoterId().longValue() > 0 && cadreRegistrationVO.getFamilyVoterId().longValue() == voterIdsList.get(0).getVoterId().longValue()){
														needUpdate = false;
													}
													}catch(Exception e){
														
													}
													if(needUpdate){
														tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(existingVoters);
													}
													TdpCadre tdpCadre = new TdpCadre();
													tdpCadre.setCardNo(cadreRegistrationVO.getRelativeVoterCardNo());
													tdpCadre.setVoterCardType("FAMILYVOTER");
													if(needUpdate){
														tdpCadre = voterIdsList.get(0);
														saveDataToHistoryTable(tdpCadre);
														tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"update",false);
													}else{
														tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
													}
												}else{
													TdpCadre tdpCadre = new TdpCadre();
													tdpCadre.setCardNo(cadreRegistrationVO.getRelativeVoterCardNo());
													tdpCadre.setVoterCardType("FAMILYVOTER");
													tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
												}
									    		//main else
									    	}else{
									    		TdpCadre tdpCadre = new TdpCadre();
									    		tdpAffliatedCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
									    	}
										}
									}
								    
									
									
								}
							}
						
							
						}
				

		} catch (Exception e) {
			if(tdpCadreBackupDetails != null)
			{
				TdpCadreBackupDetails details =  tdpCadreBackupDetailsDAO.get(tdpCadreBackupDetails.getTdpCadreBackupDetailsId());
				if(details != null)
				{
					details.setException(e.getStackTrace().toString());
					tdpCadreBackupDetailsDAO.save(details);
				}
			}
			surveyCadreResponceVO.setResultCode(ResultCodeMapper.FAILURE);
			surveyCadreResponceVO.setStatus("EXCEPTION");
			LOG.error("Exception raised in saveAfflicatedCadreRegistration in CadreRegistrationService service", e);
		}
		
		return surveyCadreResponceVO;
	}
	
	public List<VoterInfoVO> getSearchDetailsCadreRegistrationRTC(Long constituencyId, String seachType, String candidateName, String voterCardId, String houseNo,Long panchayatId,Long boothId,String isPresentCadre,Integer startIndex,Integer maxIndex,Long memberTypeId)
	{
		String cadrePath="images/cadre_images/";
		
		StringBuilder searchQuery = new StringBuilder();
		List<VoterInfoVO> returnList = null;
		List searchList = null;
		Long count = 0l;
		SimpleDateFormat format  = new SimpleDateFormat("yy-MM-dd");
		
		try {

			
			if(seachType.equalsIgnoreCase("voter"))
			{
				if(candidateName != null && candidateName.trim().length()>0)
				{
					searchQuery.append(" BPV.voter.name like '%"+candidateName+"%' and");
				}
				if(voterCardId != null  && voterCardId.trim().length()>0)
				{
					searchQuery.append("  BPV.voter.voterIDCardNo like '%"+voterCardId+"%' and");
				}
				if(houseNo != null  && houseNo.trim().length()>0)
				{
					searchQuery.append(" BPV.voter.houseNo like '%"+houseNo+"%' and" );
				}

				searchList = boothPublicationVoterDAO.getVotersDetailsForCadreRegistratiobByconstituencIdRTC(constituencyId,IConstants.AFFILIATED_VOTER_PUBLICATION_ID,searchQuery.toString(),panchayatId,boothId,isPresentCadre,startIndex,maxIndex);
				 count = boothPublicationVoterDAO.getVotersDetailsForCadreRegistratiobByconstituencIdCountRTC(constituencyId,IConstants.AFFILIATED_VOTER_PUBLICATION_ID,searchQuery.toString(),panchayatId,boothId,isPresentCadre);
				
				List<Long> voterIds = new ArrayList<Long>();
				if(searchList != null && searchList.size()>0 )
				{
					returnList = new ArrayList<VoterInfoVO>();
					
					for (Object param : searchList)
					{
						
						Object[] voter = (Object[]) param;
						
						voterIds.add(voter[0] != null ? Long.valueOf(voter[0].toString().trim()):0L);
						
						VoterInfoVO vo = new VoterInfoVO();
						vo.setId(voter[0] != null ? Long.valueOf(voter[0].toString().trim()):0L);
						vo.setVoterId(voter[0] != null ? Long.valueOf(voter[0].toString().trim()):0L);
						vo.setName(voter[1] != null ? voter[1].toString().trim():"");
						vo.setRelativeName(voter[2] != null ? voter[2].toString().trim():"");
						vo.setAge(voter[3] != null ? voter[3].toString().trim():"");
						vo.setHouseNo(voter[4] != null ? voter[4].toString().trim():"");
						vo.setRelationType(voter[5] != null ? voter[5].toString().trim():"");
						vo.setGender(voter[6] != null ? voter[6].toString().trim():"");
						vo.setVoterCardNo(voter[7]!=null ?voter[7].toString().trim():"");
						//vo.setImage(voter[7]!=null ?voterPath+voter[8].toString().trim()+"/"+voter[7].toString().trim()+".jpg":"");
						vo.setIsRegistered("N");						
						returnList.add(vo);
					}
				}
				//get voter images by publication id 11.
				List<Object[]> imagesList = boothPublicationVoterDAO.getVoterImagesByVoterIds(voterIds,IConstants.AFFILIATED_VOTER_PUBLICATION_ID);
				if(imagesList!=null && imagesList.size()>0){
					for(Object[] obj:imagesList){
						
						Long voterId=obj[0]!=null?(Long)obj[0]:0l;
						if(voterId!=0l){
							VoterInfoVO voterVO = getmatchedVOByVoterId(returnList,voterId);
							if(voterVO!=null){
								String voterCardNo=obj[1]!=null?obj[1].toString().trim():null;
								if(voterCardNo!=null){
									voterVO.setImage(IConstants.VOTER_IMG_FOLDER_PATH+"/"+cadreDetailsService.getVoterImageUrlByVoterId(voterId));
								}
								
							}
						}
						
						
					}
				}
				
				if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0") && voterIds.size()>0)
				{
					List<Long> tdpCadreVoterIds = tdpCadreDAO.getVoterDetailsByVoterIdsAndRTCAffliatedCadre(voterIds,memberTypeId);
					
					if(tdpCadreVoterIds != null && tdpCadreVoterIds.size()>0)
					{
						for (Long voterId : tdpCadreVoterIds) 
						{
							VoterInfoVO voterVO = getmatchedVOByVoterId(returnList,voterId);
							if(voterVO != null)
							{
								voterVO.setIsRegistered("Y");
							}
						}
						
					}
				}
				
			}
			
			else if(seachType.equalsIgnoreCase("cadre"))
			{
				
				if(candidateName != null && candidateName.trim().length()>0)
				{
					searchQuery.append(" TC.firstname like '%"+candidateName+"%' and");
				}
				if(voterCardId != null  && voterCardId.trim().length()>0)
				{
					searchQuery.append("  TC.voter.voterIDCardNo like '%"+voterCardId+"%' and");
				}
				if(houseNo != null  && houseNo.trim().length()>0)
				{
					searchQuery.append("  TC.houseNo like '%"+houseNo+"%' and" );
				}

				
				searchList = tdpCadreDAO.getCadreDetailsForCadreRegistratiobByconstituencIdRTC(constituencyId, searchQuery.toString(), panchayatId, boothId, isPresentCadre,startIndex,maxIndex,memberTypeId);
				 count = tdpCadreDAO.getCadreDetailsForCadreRegistratiobByconstituencIdCountRTC(constituencyId, searchQuery.toString(), panchayatId, boothId, isPresentCadre,memberTypeId);
				
				if(searchList != null && searchList.size()>0 )
				{
					returnList = new ArrayList<VoterInfoVO>();
					
					for (Object voter1 : searchList)
					{
						Object[] voter = (Object[]) voter1;
						
						VoterInfoVO vo = new VoterInfoVO();
						vo.setId(voter[0] != null ? Long.valueOf(voter[0].toString().trim()):0L);
						vo.setCadreId(voter[0] != null ? Long.valueOf(voter[0].toString().trim()):0L);
						vo.setName(voter[1] != null ? voter[1].toString().trim():" -- ");
						vo.setRelativeName(voter[2] != null ? voter[2].toString().trim():" -- ");
						//vo.setAge(voter[3] != null ? voter[3].toString().trim():" -- ");
						vo.setHouseNo(voter[6] != null ? voter[6].toString().trim():" -- ");
						vo.setGender(voter[5] != null ? voter[5].toString().trim():" -- ");
						vo.setRelationType(voter[9] != null ? voter[9].toString().trim():"");
						
						vo.setImage(voter[10]!=null ?cadrePath+voter[10].toString().trim():null);
						String dateOfBirth = 	voter[3] != null ? voter[3].toString().substring(0,10):" "	;
						
						if(dateOfBirth != null && dateOfBirth.trim().length()>0)
						{
							Calendar startDate = new GregorianCalendar();
							Calendar endDate = new GregorianCalendar();
							
							startDate.setTime(format.parse(dateOfBirth.trim()));
							
							endDate.setTime(new Date());

							int diffYear = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
							
							vo.setAge(String.valueOf(diffYear));
						}
						else if(voter[4] != null && voter[4].toString().trim().length()>0 )
						{
							vo.setAge(voter[4].toString());
						}
						
						if(voter[8] != null)
						{
							try {
								
								Voter voterEntity = voterDAO.getVoterByVoterID(voter[8]!= null? Long.valueOf(voter[8].toString()):0L);
								
								if(voterEntity != null)
								{
									vo.setName(voterEntity.getName() != null ? voterEntity.getName().toString():" -- ");
									vo.setRelativeName(voterEntity.getRelativeName() != null ? voterEntity.getRelativeName().toString():" -- ");
									vo.setAge(voterEntity.getAge() != null ? voterEntity.getAge().toString():"--");
									vo.setHouseNo(voterEntity.getHouseNo() != null ? voterEntity.getHouseNo().toString():" -- ");
									vo.setGender(voterEntity.getGender() != null ? voterEntity.getGender().toString():" -- ");
									vo.setRelationType(voterEntity.getRelationshipType() != null ? voterEntity.getRelationshipType().toString():" -- ");
									vo.setVoterId(voterEntity.getVoterId()!= null ? voterEntity.getVoterId():0L);
								}
							} catch (Exception e) {}
							
						}
						
						returnList.add(vo);
					}
				}
				
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getSearchDetailsCadreRegistration in CadreRegistrationService service", e);
		}
		if(returnList !=null && returnList.size() > 0){
			returnList.get(0).setCount(count);
		}
		return returnList;
	}
	
	//SAVING AFFLICATED CADRE SAVING 1111
	public void tdpAffliatedCadreSavingLogic(final String registrationType,final List<CadreRegistrationVO> cadreRegistrationVOList ,final CadreRegistrationVO cadreRegistrationVO, final SurveyCadreResponceVO surveyCadreResponceVO,TdpCadre tdpCadreNew,String insertTypeNew,final boolean statusVar)
	{
		
		if((registrationType.equalsIgnoreCase("ONLINE") && cadreRegistrationVO.getOrderId() != null && cadreRegistrationVO.getOrderId().trim().length() > 0)
				 || (cadreRegistrationVO.getDataSourceType() != null && cadreRegistrationVO.getDataSourceType().trim().equalsIgnoreCase("ONLINE"))){
			List<TdpCadre> tdpCadres = tdpCadreDAO.checkOnlineAccountExistsOrNot(cadreRegistrationVO.getOrderId());
			if(tdpCadres != null && tdpCadres.size() > 0 && tdpCadres.get(0) != null){
				tdpCadreNew = tdpCadres.get(0);
				insertTypeNew ="update";
			}
		}
		final String insertType = insertTypeNew;
		final TdpCadre tdpCadre = tdpCadreNew;
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				public void doInTransactionWithoutResult(TransactionStatus status) {
					TdpCadre  tdpCadre1 = null;
					
					if(registrationType.equalsIgnoreCase("ONLINE")  && !insertType.equalsIgnoreCase("update") ){
						if(cadreRegistrationVO.getCadreId() != null && cadreRegistrationVO.getCadreId().longValue()>0L){
							tdpCadre.setPayMentStatus(IConstants.NOT_REQUIRED);
							tdpCadre.setParentTdpCadreId(cadreRegistrationVO.getCadreId());
							cadreRegistrationVO.setParentTdpCadreId(cadreRegistrationVO.getCadreId());
						}
						else
							tdpCadre.setPayMentStatus(IConstants.NOT_PAID_STATUS);
					}
					if(registrationType != null && !registrationType.equalsIgnoreCase("null") && registrationType.trim().length() > 0 && !insertType.equalsIgnoreCase("update"))
					{
						if(tdpCadre.getDataSourceType() == null){
						  tdpCadre.setDataSourceType(registrationType.trim().toUpperCase());
						}
						else if(cadreRegistrationVO.getDataSourceType() != null && cadreRegistrationVO.getDataSourceType().trim().equalsIgnoreCase("ONLINE")){
							tdpCadre.setDataSourceType(cadreRegistrationVO.getDataSourceType().trim());
						}
					}
					
					 if(registrationType.equalsIgnoreCase("TAB")  && !insertType.equalsIgnoreCase("update") ){
						 tdpCadre.setMode(cadreRegistrationVO.getMode());
					}
					
					if(cadreRegistrationVO.getVoterName() != null && !cadreRegistrationVO.getVoterName().equalsIgnoreCase("null") && cadreRegistrationVO.getVoterName().trim().length() > 0)
					{
						tdpCadre.setFirstname(cadreRegistrationVO.getVoterName());
					}
					if(cadreRegistrationVO.getAge() != null && cadreRegistrationVO.getAge() > 0)
					{
						tdpCadre.setAge(cadreRegistrationVO.getAge());
					}
					else if(tdpCadre.getVoterId() != null)
					{
						tdpCadre.setAge(voterDAO.get(tdpCadre.getVoterId()).getAge());
					}
					else
					{
						tdpCadre.setAge(null);
					}
					if(cadreRegistrationVO.getDobStr() != null && cadreRegistrationVO.getDobStr().trim().length() > 0 && !cadreRegistrationVO.getDobStr().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setDateOfBirth(convertToDateFormet(cadreRegistrationVO.getDobStr().toString()));
					}
					if(cadreRegistrationVO.getGender() != null && !cadreRegistrationVO.getGender().equalsIgnoreCase("null") && cadreRegistrationVO.getGender().trim().length() > 0)
					{
						if(cadreRegistrationVO.getVoterId() != null && cadreRegistrationVO.getVoterId().trim().length() > 0 && Long.valueOf(cadreRegistrationVO.getVoterId()) > 0)
						{
							String gen = voterDAO.get(Long.valueOf(cadreRegistrationVO.getVoterId())).getGender();
							if(gen.equalsIgnoreCase("Male") || gen.equalsIgnoreCase("m")){
								if(cadreRegistrationVO.getGender().equalsIgnoreCase("Male") || cadreRegistrationVO.getGender().equalsIgnoreCase("m")){
									tdpCadre.setGender("M");
								}
								else
								{
									surveyCadreResponceVO.setErrorCode("VOTER GENDER MISS MATCH");
								}
							}else if(gen.equalsIgnoreCase("female") || gen.equalsIgnoreCase("f")){
								if(cadreRegistrationVO.getGender().equalsIgnoreCase("female") || cadreRegistrationVO.getGender().equalsIgnoreCase("f")){
									tdpCadre.setGender("F");
								}
								else
								{
									surveyCadreResponceVO.setErrorCode("VOTER GENDER MISS MATCH");
								}
							}
						}
						else
						{
							tdpCadre.setGender(cadreRegistrationVO.getGender());
						}
						
					}
					if(cadreRegistrationVO.getRelativeName() != null && !cadreRegistrationVO.getRelativeName().equalsIgnoreCase("null") && cadreRegistrationVO.getRelativeName().trim().length() > 0)
					{
						tdpCadre.setRelativename(cadreRegistrationVO.getRelativeName());
					}else{
						tdpCadre.setRelativename(null);
					}
					if(cadreRegistrationVO.getRelativeType() != null && !cadreRegistrationVO.getRelativeType().equalsIgnoreCase("null") && cadreRegistrationVO.getRelativeType().trim().length() > 0)
					{
						 if(registrationType.equalsIgnoreCase("TAB")){
							 Long relativeTypeId = cadreRegistrationVO.getRelativeType().trim().length()>0 ? Long.valueOf(cadreRegistrationVO.getRelativeType()):0L;;
								tdpCadre.setRelativeType(voterRelationDAO.get(relativeTypeId).getDescription());
						 }
						 else 
						 {
							 tdpCadre.setRelativeType(cadreRegistrationVO.getRelativeType());
						 }
					}else{
						tdpCadre.setRelativeType(null);
					}
					if(cadreRegistrationVO.getVoterId() != null && Long.valueOf(cadreRegistrationVO.getVoterId()) > 0)
					{
						tdpCadre.setVoterId(Long.valueOf(cadreRegistrationVO.getVoterId()));
					}
					else if(cadreRegistrationVO.getVoterCardNumber() != null && !cadreRegistrationVO.getVoterCardNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getVoterCardNumber().trim().length() > 0)
					{
						List<Long> voterCardNumbersList = voterDAO.getVoterId(cadreRegistrationVO.getVoterCardNumber());
						if(voterCardNumbersList != null && voterCardNumbersList.size() > 0)
						{
							tdpCadre.setVoterId(voterCardNumbersList.get(0));
						}
					}
					else
					{
						surveyCadreResponceVO.setErrorCode("VOTER ID DOES NOT EXISTS");
					}
					
					
					if(cadreRegistrationVO.getPreviousEnrollmentNumber() != null && !cadreRegistrationVO.getPreviousEnrollmentNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getPreviousEnrollmentNumber().trim().length() > 0)
					{
						List<String> preDetailsList = tdpCadreDAO.getExistingCadreMemberDetails(cadreRegistrationVO.getPreviousEnrollmentNumber());
						if(preDetailsList == null)
						{
							surveyCadreResponceVO.setErrorCode("PREVIOUS ENROLLMENT NUMBER DOES NOT EXISTS");
						}
						else
						{
							tdpCadre.setPreviousEnrollmentNo(cadreRegistrationVO.getPreviousEnrollmentNumber());
						}
						
					}else{
						tdpCadre.setPreviousEnrollmentNo(null);
					}
					if(cadreRegistrationVO.getPartyMemberSinceStr() != null && cadreRegistrationVO.getPartyMemberSinceStr().trim().length() > 0 && !cadreRegistrationVO.getPartyMemberSinceStr().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setPartyMemberSince(convertToDateFormet(cadreRegistrationVO.getPartyMemberSinceStr()));
					}else{
						tdpCadre.setPartyMemberSince(null);
					}
					if(cadreRegistrationVO.getBloodGroupId() != null && cadreRegistrationVO.getBloodGroupId().longValue() > 0)
					{
						tdpCadre.setBloodGroupId(cadreRegistrationVO.getBloodGroupId());
					}else{
						tdpCadre.setBloodGroupId(null);
					}
					
					if(cadreRegistrationVO.getCasteId() != null && cadreRegistrationVO.getCasteId() > 0)
					{
						tdpCadre.setCasteStateId(cadreRegistrationVO.getCasteId());
					}else{
						tdpCadre.setCasteStateId(null);
					}
					
					if(cadreRegistrationVO.getMobileNumber() != null && !cadreRegistrationVO.getMobileNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getMobileNumber().trim().length() > 0)
					{
						tdpCadre.setMobileNo(cadreRegistrationVO.getMobileNumber());
					}else{
						tdpCadre.setMobileNo(null);
					}
					
					if(cadreRegistrationVO.getEmailId() != null && !cadreRegistrationVO.getEmailId().equalsIgnoreCase("null") && cadreRegistrationVO.getEmailId().trim().length()>0)
					{
						tdpCadre.setEmailId(cadreRegistrationVO.getEmailId().trim());
					}
					else{
						tdpCadre.setEmailId(null);
					}
					
					if(cadreRegistrationVO.getEducationId() != null && cadreRegistrationVO.getEducationId().longValue() > 0)
					{
						tdpCadre.setEducationId(cadreRegistrationVO.getEducationId());
					}else{
						tdpCadre.setEducationId(null);
					}
					
					if(cadreRegistrationVO.getOccupationId() != null && cadreRegistrationVO.getOccupationId().longValue() > 0)
					{
						tdpCadre.setOccupationId(cadreRegistrationVO.getOccupationId());
					}else{
						tdpCadre.setOccupationId(null);
					}
					if(cadreRegistrationVO.getHouseNo() != null && !cadreRegistrationVO.getHouseNo().equalsIgnoreCase("null") && cadreRegistrationVO.getHouseNo().trim().length() > 0)
					{
						tdpCadre.setHouseNo(cadreRegistrationVO.getHouseNo());
					}else{
						
						if(cadreRegistrationVO.getPrsntAddrsHNo() != null && !cadreRegistrationVO.getPrsntAddrsHNo().equalsIgnoreCase("null") && cadreRegistrationVO.getPrsntAddrsHNo().trim().length() > 0)
						{
							tdpCadre.setHouseNo(cadreRegistrationVO.getPrsntAddrsHNo());
						}else{
							tdpCadre.setHouseNo(null);
						}
						//tdpCadre.setHouseNo(null);
					}
					
					if(cadreRegistrationVO.getCreatedUserId() != null && cadreRegistrationVO.getCreatedUserId().longValue() > 0  )
					{
					  if(!insertType.equalsIgnoreCase("update")){
						if(registrationType.equalsIgnoreCase("TAB")){
						  if(tdpCadre.getCreatedUnionTabUserId() == null && tdpCadre.getInsertedWebUserId() == null){
						      tdpCadre.setCreatedUnionTabUserId(cadreRegistrationVO.getCreatedUserId().longValue());
						  }else{
							  tdpCadre.setUpdatedUnionTabUserId(cadreRegistrationVO.getCreatedUserId().longValue());
						  }
						}else{
							if(tdpCadre.getCreatedUnionTabUserId() == null && tdpCadre.getInsertedWebUserId() == null){
							    tdpCadre.setInsertedWebUserId(cadreRegistrationVO.getCreatedUserId().longValue());
							}else{
								tdpCadre.setUpdatedWebUserId(cadreRegistrationVO.getCreatedUserId().longValue());
							}
						}
					  }else{
					    if(registrationType.equalsIgnoreCase("TAB")){
						     tdpCadre.setUpdatedUnionTabUserId(cadreRegistrationVO.getCreatedUserId().longValue());
						}else{
							 tdpCadre.setUpdatedWebUserId(cadreRegistrationVO.getCreatedUserId().longValue());
						}
					  }
					}
					   
						if(cadreRegistrationVO.getEmployeeId() != null){
							tdpCadre.setIdCardNo(cadreRegistrationVO.getEmployeeId());
						}
						if(cadreRegistrationVO.getDesignationId() != null){
							tdpCadre.setDesignationId(cadreRegistrationVO.getDesignationId());
							if(cadreRegistrationVO.getDesignationId() != null && cadreRegistrationVO.getDesignationId().longValue() == 8L && 
									 cadreRegistrationVO.getOtherDesignationStr() != null && cadreRegistrationVO.getOtherDesignationStr().trim().length()>0)//if others
								tdpCadre.setDesignationName(cadreRegistrationVO.getOtherDesignationStr().trim());
						}
						
						//tdpCadre.setTdpMemberTypeId(2l);
						//tdpCadre.setUnionTypeId(1l);
						
						if(cadreRegistrationVO.getMemberTypeId() != null && !cadreRegistrationVO.getMemberTypeId().isEmpty()){
							tdpCadre.setTdpMemberTypeId(Long.valueOf(cadreRegistrationVO.getMemberTypeId()));
						}
						
						if(tdpCadre.getTdpMemberTypeId() != null && tdpCadre.getTdpMemberTypeId() == 2L){
							
							if(cadreRegistrationVO.getUnionTypeId() != null && cadreRegistrationVO.getUnionTypeId().longValue()>0L){
								tdpCadre.setUnionTypeId(Long.valueOf(cadreRegistrationVO.getUnionTypeId()));
							}
							
							//ZONES SAVING.
							//SAVING FOR TAB.
							TdpCadreLocation tdpCadreLocation = new TdpCadreLocation();
							if((cadreRegistrationVO.getZoneId() == null || cadreRegistrationVO.getZoneId() == 0l) && (cadreRegistrationVO.getRegionId() == null || cadreRegistrationVO.getRegionId() == 0l) && (cadreRegistrationVO.getDepotId() != null)){
								
								Object[] regionZoneDetails=rtcDepotDAO.getRegionAndZoneByDepotId(cadreRegistrationVO.getDepotId());
								if(regionZoneDetails!=null && regionZoneDetails.length>0){
									
									tdpCadreLocation.setRtcZoneId(regionZoneDetails[2]!=null?(Long)regionZoneDetails[2]:null);
									tdpCadreLocation.setRtcRegionId(regionZoneDetails[1]!=null?(Long)regionZoneDetails[1]:null);
									tdpCadreLocation.setRtcDepotId(cadreRegistrationVO.getDepotId());
								}
								
				            }else{//SAVING FOR WEB.
				            	
				            	if(cadreRegistrationVO.getZoneId() != null && cadreRegistrationVO.getZoneId() > 0l){
									tdpCadreLocation.setRtcZoneId(cadreRegistrationVO.getZoneId());
								}
								if(cadreRegistrationVO.getRegionId() != null && cadreRegistrationVO.getRegionId() > 0l){
									tdpCadreLocation.setRtcRegionId(cadreRegistrationVO.getRegionId());
								}
								if(cadreRegistrationVO.getDepotId() != null && cadreRegistrationVO.getDepotId() > 0l){
									tdpCadreLocation.setRtcDepotId(cadreRegistrationVO.getDepotId());
								}
				            }
							
							tdpCadreLocation = tdpCadreLocationDAO.save(tdpCadreLocation);
							
							if(tdpCadreLocation.getTdpCadreLocationId() !=null && tdpCadreLocation.getTdpCadreLocationId() > 0l){
								tdpCadre.setTdpCadreLocationId(tdpCadreLocation.getTdpCadreLocationId());
							}
						}
						
						if(cadreRegistrationVO.getSchoolName() != null && !cadreRegistrationVO.getSchoolName().isEmpty()){
							tdpCadre.setSchoolName(cadreRegistrationVO.getSchoolName());
						}
						
						 UserAddress userAddress = new UserAddress();
						 
					String cadreImgConstituency ="" ;
					if(registrationType.equalsIgnoreCase("TAB")){
						//ADDRESS SAVING.
							 
								 if(cadreRegistrationVO.getHouseNo() != null && cadreRegistrationVO.getHouseNo().length() > 0l){
									 userAddress.setHouseNo(cadreRegistrationVO.getHouseNo());
									 userAddress.setHouseNo(cadreRegistrationVO.getHouseNo());
								   }
						 
						    	   if(cadreRegistrationVO.getPerAddrsDistId() != null && cadreRegistrationVO.getPerAddrsDistId() > 0l){
									   userAddress.setDistrict(districtDAO.get(cadreRegistrationVO.getPerAddrsDistId()));
								   }
								   
								   if(cadreRegistrationVO.getPerAddrsConstId() != null && cadreRegistrationVO.getPerAddrsConstId() > 0l){
									   userAddress.setConstituency(constituencyDAO.get(cadreRegistrationVO.getPerAddrsConstId()));
									   
									   cadreImgConstituency =cadreRegistrationVO.getPerAddrsConstId()!=null? cadreRegistrationVO.getPerAddrsConstId().toString():"";
								   }
								   
								   if(cadreRegistrationVO.getPerAddrsMandalId() != null && cadreRegistrationVO.getPerAddrsMandalId() > 0l){
									   userAddress.setTehsil(tehsilDAO.get(cadreRegistrationVO.getPerAddrsMandalId()));
								   }
								   
								   if(cadreRegistrationVO.getPerAddrsLebId() != null && cadreRegistrationVO.getPerAddrsLebId() > 0l){
									   userAddress.setLocalElectionBody(localElectionBodyDAO.get(cadreRegistrationVO.getPerAddrsLebId()));
								   }
								   
								   if(cadreRegistrationVO.getPerAddrsVillId() != null && cadreRegistrationVO.getPerAddrsVillId() > 0l){
									   userAddress.setPanchayatId(cadreRegistrationVO.getPerAddrsVillId());
								   }
								   
								   if(cadreRegistrationVO.getPerAddrsWardId() != null && cadreRegistrationVO.getPerAddrsWardId() > 0l){
									   userAddress.setWard(constituencyDAO.get(cadreRegistrationVO.getPerAddrsWardId()));
								   }
								   
								   if(cadreRegistrationVO.getStreet() != null && cadreRegistrationVO.getStreet().trim() != ""){
									   userAddress.setStreet(cadreRegistrationVO.getStreet());
									}
								   
								   if(cadreRegistrationVO.getLandMark() != null && cadreRegistrationVO.getLandMark().trim() != ""){
									   userAddress.setAddressLane1(cadreRegistrationVO.getLandMark());
								   }
								   if(cadreRegistrationVO.getPrsntAddrsPincode() != null && cadreRegistrationVO.getPrsntAddrsPincode().trim() != ""){
									   userAddress.setPinCode(cadreRegistrationVO.getPrsntAddrsPincode());
								   }
						   userAddress= userAddressDAO.save(userAddress);
						   tdpCadre.setUserAddress(userAddress);
						   
						   UserAddress workLocation = new UserAddress();
						   boolean isWorkLocationAvailable = false;
						   if(cadreRegistrationVO.getWorkAddrsHNo() != null && cadreRegistrationVO.getWorkAddrsHNo().length() > 0l){
							   workLocation.setHouseNo(cadreRegistrationVO.getWorkAddrsHNo());
							   isWorkLocationAvailable = true;
						   }
						   
						   if(cadreRegistrationVO.getWorkAddrsDistId() != null && cadreRegistrationVO.getWorkAddrsDistId() > 0l){
							   workLocation.setDistrict(districtDAO.get(cadreRegistrationVO.getWorkAddrsDistId()));
							   isWorkLocationAvailable = true;
						   }
						   
						   if(cadreRegistrationVO.getWorkAddrsConstId() != null && cadreRegistrationVO.getWorkAddrsConstId() > 0l){
							   workLocation.setConstituency(constituencyDAO.get(cadreRegistrationVO.getWorkAddrsConstId()));
							   isWorkLocationAvailable = true;
						   }
						   
						   if(cadreRegistrationVO.getWorkAddrsMandalId() != null && cadreRegistrationVO.getWorkAddrsMandalId() > 0l){
							   workLocation.setTehsil(tehsilDAO.get(cadreRegistrationVO.getWorkAddrsMandalId()));
							   isWorkLocationAvailable = true;
						   }
						   
						   if(cadreRegistrationVO.getWorkAddrsLebId() != null && cadreRegistrationVO.getWorkAddrsLebId() > 0l){
							   workLocation.setLocalElectionBody(localElectionBodyDAO.get(cadreRegistrationVO.getWorkAddrsLebId()));
							   isWorkLocationAvailable = true;
						   }
						   
						   if(cadreRegistrationVO.getWorkAddrsVillId() != null && cadreRegistrationVO.getWorkAddrsVillId() > 0l){
							   workLocation.setPanchayatId(cadreRegistrationVO.getWorkAddrsVillId());
							   isWorkLocationAvailable = true;
						   }
						   
						   if(cadreRegistrationVO.getWorkAddrsWardId() != null && cadreRegistrationVO.getWorkAddrsWardId() > 0l){
							   workLocation.setWard(constituencyDAO.get(cadreRegistrationVO.getWorkAddrsWardId()));
							   isWorkLocationAvailable = true;
						   }
						   
						   if(cadreRegistrationVO.getWorkAddrsStreet() != null && cadreRegistrationVO.getWorkAddrsStreet().trim() != ""){
							   workLocation.setStreet(cadreRegistrationVO.getWorkAddrsStreet());
							   isWorkLocationAvailable = true;
							}
						   
						   if(cadreRegistrationVO.getWorkAddrsLandmark() != null && cadreRegistrationVO.getWorkAddrsLandmark().trim() != ""){
							   workLocation.setAddressLane1(cadreRegistrationVO.getWorkAddrsLandmark());
							   isWorkLocationAvailable = true;
						   }
						   if(cadreRegistrationVO.getWorkAddrsPincode() != null && cadreRegistrationVO.getWorkAddrsPincode().trim() != ""){
							   workLocation.setPinCode(cadreRegistrationVO.getWorkAddrsPincode());
							   isWorkLocationAvailable = true;
						   }
						   if(isWorkLocationAvailable)
							   tdpCadre.setWorkLocation(userAddressDAO.save(workLocation));
						 
					}
					else{
						
						UserAddress presentAddress = new UserAddress();

						if(cadreRegistrationVO.getPrsntAddrsHNo() != null && cadreRegistrationVO.getPrsntAddrsHNo().length() > 0l){
							   presentAddress.setHouseNo(cadreRegistrationVO.getPrsntAddrsHNo());
						   }
						   
						   if(cadreRegistrationVO.getPrsntAddrsDistId() != null && cadreRegistrationVO.getPrsntAddrsDistId() > 0l){
							   presentAddress.setDistrict(districtDAO.get(cadreRegistrationVO.getPrsntAddrsDistId()));
						   }
						   
						   if(cadreRegistrationVO.getPrsntAddrsConstId() != null && cadreRegistrationVO.getPrsntAddrsConstId() > 0l){
							   presentAddress.setConstituency(constituencyDAO.get(cadreRegistrationVO.getPrsntAddrsConstId()));
							   
							   cadreImgConstituency =cadreRegistrationVO.getPrsntAddrsConstId()!=null? cadreRegistrationVO.getPrsntAddrsConstId().toString():"";
						   }
						   
						   if(cadreRegistrationVO.getPrsntAddrsMandalId() != null && cadreRegistrationVO.getPrsntAddrsMandalId() > 0l){
							   String mandalIdStr = cadreRegistrationVO.getPrsntAddrsMandalId().toString();
							   cadreRegistrationVO.setPrsntAddrsMandalId(Long.valueOf(mandalIdStr.substring(1)));
							   String mandalORLeb = String.valueOf(mandalIdStr.charAt(0));
							   if(mandalORLeb.trim().equalsIgnoreCase("4")){
								   presentAddress.setTehsil(tehsilDAO.get(cadreRegistrationVO.getPrsntAddrsMandalId()));
							   }
							   else if(mandalORLeb.trim().equalsIgnoreCase("5")){
								   LocalElectionBody leb = localElectionBodyDAO.get(cadreRegistrationVO.getPrsntAddrsMandalId());
								   presentAddress.setLocalElectionBody(leb);
								   presentAddress.setTehsil(leb.getTehsil());
							   }
							   
						   }
						   
						   if(cadreRegistrationVO.getPrsntAddrsLebId() != null && cadreRegistrationVO.getPrsntAddrsLebId() > 0l){
							   presentAddress.setLocalElectionBody(localElectionBodyDAO.get(cadreRegistrationVO.getPrsntAddrsLebId()));
						   }
						   
						   if(cadreRegistrationVO.getPrsntAddrsVillId() != null && cadreRegistrationVO.getPrsntAddrsVillId() > 0l){
							   
							   String panchayatIdStr = cadreRegistrationVO.getPrsntAddrsVillId().toString();
							   cadreRegistrationVO.setPrsntAddrsVillId(Long.valueOf(panchayatIdStr.substring(1)));
							   String panchayatORWard = String.valueOf(panchayatIdStr.charAt(0));
							   if(panchayatORWard.trim().equalsIgnoreCase("7")){
								   presentAddress.setPanchayatId(cadreRegistrationVO.getPrsntAddrsVillId());
							   }
							   else if(panchayatORWard.trim().equalsIgnoreCase("8")){
								   presentAddress.setWard(constituencyDAO.get(cadreRegistrationVO.getPrsntAddrsVillId()));
							   }
						   }
						   
						   if(cadreRegistrationVO.getPrsntAddrsWardId() != null && cadreRegistrationVO.getPrsntAddrsWardId() > 0l){
							   presentAddress.setWard(constituencyDAO.get(cadreRegistrationVO.getPrsntAddrsWardId()));
						   }
						   
						   if(cadreRegistrationVO.getPrsntAddrsStreet() != null && cadreRegistrationVO.getPrsntAddrsStreet().trim() != ""){
							   presentAddress.setStreet(cadreRegistrationVO.getPrsntAddrsStreet());
							}
						   
						   if(cadreRegistrationVO.getPrsntAddrsLandmark() != null && cadreRegistrationVO.getPrsntAddrsLandmark().trim() != ""){
							   presentAddress.setAddressLane1(cadreRegistrationVO.getPrsntAddrsLandmark());
						   }
						   if(cadreRegistrationVO.getPrsntAddrsPincode() != null && cadreRegistrationVO.getPrsntAddrsPincode().trim() != ""){
							   presentAddress.setPinCode(cadreRegistrationVO.getPrsntAddrsPincode());
						   }
							   presentAddress= userAddressDAO.save(presentAddress);
							   tdpCadre.setUserAddress(presentAddress);

							   if(tdpCadre.getTdpMemberTypeId() != null && tdpCadre.getTdpMemberTypeId().longValue() != 5L){
									 UserAddress workLocation = new UserAddress();
									 boolean isWorkLocationAvailable = false;
								   if(cadreRegistrationVO.getWorkAddrsHNo() != null && cadreRegistrationVO.getWorkAddrsHNo().length() > 0l){
									   workLocation.setHouseNo(cadreRegistrationVO.getWorkAddrsHNo());
									   isWorkLocationAvailable = true;
								   }
									   
								   if(cadreRegistrationVO.getWorkAddrsDistId() != null && cadreRegistrationVO.getWorkAddrsDistId() > 0l){
									   workLocation.setDistrict(districtDAO.get(cadreRegistrationVO.getWorkAddrsDistId()));
									   isWorkLocationAvailable = true;
								   }
								   
								   if(cadreRegistrationVO.getWorkAddrsConstId() != null && cadreRegistrationVO.getWorkAddrsConstId() > 0l){
									   workLocation.setConstituency(constituencyDAO.get(cadreRegistrationVO.getWorkAddrsConstId()));
									   isWorkLocationAvailable = true;
								   }
								   
								   if(cadreRegistrationVO.getWorkAddrsMandalId() != null && cadreRegistrationVO.getWorkAddrsMandalId() > 0l){
									 
									   String mandalIdStr = cadreRegistrationVO.getWorkAddrsMandalId().toString();
									   cadreRegistrationVO.setWorkAddrsMandalId(Long.valueOf(mandalIdStr.substring(1)));
									   String mandalORLeb = String.valueOf(mandalIdStr.charAt(0));
									   if(mandalORLeb.trim().equalsIgnoreCase("4")){
										   workLocation.setTehsil(tehsilDAO.get(cadreRegistrationVO.getWorkAddrsMandalId()));
									   }
									   else if(mandalORLeb.trim().equalsIgnoreCase("5")){
										   LocalElectionBody leb = localElectionBodyDAO.get(cadreRegistrationVO.getWorkAddrsMandalId());
										   workLocation.setLocalElectionBody(leb);
										   workLocation.setTehsil(leb.getTehsil());
									   }
									   isWorkLocationAvailable = true;
								   }
								   
								   if(cadreRegistrationVO.getWorkAddrsLebId() != null && cadreRegistrationVO.getWorkAddrsLebId() > 0l){
									   workLocation.setLocalElectionBody(localElectionBodyDAO.get(cadreRegistrationVO.getWorkAddrsLebId()));
									   isWorkLocationAvailable = true;
								   }
								   
								   if(cadreRegistrationVO.getWorkAddrsVillId() != null && cadreRegistrationVO.getWorkAddrsVillId() > 0l){
									   
									   String panchayatIdStr = cadreRegistrationVO.getWorkAddrsVillId().toString();
									   cadreRegistrationVO.setWorkAddrsVillId(Long.valueOf(panchayatIdStr.substring(1)));
									   String panchayatORWard = String.valueOf(panchayatIdStr.charAt(0));
									   if(panchayatORWard.trim().equalsIgnoreCase("7")){
										   workLocation.setPanchayatId(cadreRegistrationVO.getWorkAddrsVillId());
									   }
									   else if(panchayatORWard.trim().equalsIgnoreCase("8")){
										   workLocation.setWard(constituencyDAO.get(cadreRegistrationVO.getWorkAddrsVillId()));
									   }
									   isWorkLocationAvailable = true;
								   }
								   
								   if(cadreRegistrationVO.getWorkAddrsWardId() != null && cadreRegistrationVO.getWorkAddrsWardId() > 0l){
									   workLocation.setWard(constituencyDAO.get(cadreRegistrationVO.getWorkAddrsWardId()));
									   isWorkLocationAvailable = true;
								   }
								   
								   if(cadreRegistrationVO.getWorkAddrsStreet() != null && cadreRegistrationVO.getWorkAddrsStreet().trim() != ""){
									   workLocation.setStreet(cadreRegistrationVO.getWorkAddrsStreet());
									   isWorkLocationAvailable = true;
									}
								   
								   if(cadreRegistrationVO.getWorkAddrsLandmark() != null && cadreRegistrationVO.getWorkAddrsLandmark().trim() != ""){
									   workLocation.setAddressLane1(cadreRegistrationVO.getWorkAddrsLandmark());
									   isWorkLocationAvailable = true;
								   }
								   if(cadreRegistrationVO.getWorkAddrsPincode() != null && cadreRegistrationVO.getWorkAddrsPincode().trim() != ""){
									   workLocation.setPinCode(cadreRegistrationVO.getWorkAddrsPincode());
									   isWorkLocationAvailable = true;
								   }
								   if(isWorkLocationAvailable)
									   tdpCadre.setWorkLocation(userAddressDAO.save(workLocation));
							   }
					}
						
					TdpCadreOnline tdpCadreOnline = null;
					if(registrationType.equalsIgnoreCase("ONLINE")){
						tdpCadre.setCardNo(cadreRegistrationVO.getVoterCardNo());
						tdpCadreOnline = new TdpCadreOnline(); 
						tdpCadreOnline.setOrderId(cadreRegistrationVO.getOrderId());
						tdpCadreOnline.setArea(cadreRegistrationVO.getArea());
						tdpCadreOnline.setAddress(cadreRegistrationVO.getPermanentAddress());
						tdpCadreOnline.setPincode(cadreRegistrationVO.getPincode());
						tdpCadreOnline.setDeliveryMode(cadreRegistrationVO.getDeliveryMode());
						tdpCadreOnline.setShipCountry(cadreRegistrationVO.getShipCountry());
						tdpCadreOnline.setShipAddress(cadreRegistrationVO.getShipAddress());
						tdpCadreOnline.setEmail(cadreRegistrationVO.getEmail());
						tdpCadreOnline.setPermanentAddress(cadreRegistrationVO.getAddress());
						tdpCadreOnline.setOnlineId(cadreRegistrationVO.getOnlineId());
						tdpCadreOnline = tdpCadreOnlineDAO.save(tdpCadreOnline);
						
					}
					tdpCadre.setTdpCadreOnline(tdpCadreOnline);
					try{
						if(cadreRegistrationVO.getVoterTeluguName()!= null && cadreRegistrationVO.getVoterTeluguName().trim().length() > 0 && tdpCadre.getVoterId() != null &&  tdpCadre.getVoterId().longValue() > 0){
							List<VoterNames> voterNames = voterNamesDAO.gerVoterNamesObjByVoterId(tdpCadre.getVoterId());
							
							if(voterNames != null && voterNames.size() > 0 && voterNames.get(0) != null){
								for(VoterNames voterName:voterNames){
									if(voterName != null){
										voterName.setFirstName(cadreRegistrationVO.getVoterTeluguName().trim());
										voterName.setLastName("");
										voterName.getVoterNamesId();
										
										//voterNamesDAO.updateVoterName(voterName.getFirstName(), voterName.getVoterNamesId());
										voterNamesDAO.save(voterName);
									}
								}
							}else{
								VoterNames voterName = new VoterNames();
								//voterName.setVoter(voterDAO.get(tdpCadre.getVoterId()));
								voterName.setVoterId(tdpCadre.getVoterId());
								voterName.setFirstName(cadreRegistrationVO.getVoterTeluguName().trim());
								voterName.setLastName("");
								voterNamesDAO.save(voterName);
							}
							
							
						}
						
						if(userAddress.getConstituency() != null && userAddress.getConstituency().getConstituencyId() != null){
							cadreRegistrationVO.setConstituencyId( userAddress.getConstituency().getConstituencyId().toString());
						}
						
					}catch(Exception e){
						LOG.error(e);
					}
					
					
					
					if(cadreRegistrationVO.getNomineeName() != null && cadreRegistrationVO.getNomineeName().trim().length() > 0 && !cadreRegistrationVO.getNomineeName().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setNomineeName(cadreRegistrationVO.getNomineeName());
					}
					/*if(cadreRegistrationVO.getAadheerNo() != null && cadreRegistrationVO.getAadheerNo().trim().length() > 0 && !cadreRegistrationVO.getAadheerNo().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setAadheerNo(cadreRegistrationVO.getAadheerNo());
						tdpCadre.setCadreAadherNo(cadreRegistrationVO.getAadheerNo());
					}*/
					if(cadreRegistrationVO.getVoterRelationId() != null && cadreRegistrationVO.getVoterRelationId().longValue() > 0)
					{
						tdpCadre.setVoterRelationId(cadreRegistrationVO.getVoterRelationId());
					}
					if(cadreRegistrationVO.getCadreType() != null && cadreRegistrationVO.getCadreType().trim().length() > 0 && cadreRegistrationVO.getCadreType().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setCadreType(cadreRegistrationVO.getCadreType());
					}
					if(!insertType.equalsIgnoreCase("update") && tdpCadre.getInsertedTime() == null){
					   tdpCadre.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					}
					tdpCadre.setUpdatedTime(dateUtilService.getCurrentDateAndTime());	
					//if(cadreRegistrationVO.getCadreRegType().equalsIgnoreCase("rtcCadre")){
						tdpCadre.setEnrollmentYear(IConstants.UNIONS_REGISTRATION_YEAR);
					//}else{
					//	tdpCadre.setEnrollmentYear(IConstants.CADRE_ENROLLMENT_NUMBER);
					//}
					
					if(cadreRegistrationVO.getLongititude() != null && !cadreRegistrationVO.getLongititude().equalsIgnoreCase("null") && cadreRegistrationVO.getLongititude().trim().length() > 0)
					{
						tdpCadre.setLongititude(cadreRegistrationVO.getLongititude());
					}
					
					if(cadreRegistrationVO.getLatitude() != null && !cadreRegistrationVO.getLatitude().equalsIgnoreCase("null") && cadreRegistrationVO.getLatitude().trim().length() > 0)
					{
						tdpCadre.setLatitude(cadreRegistrationVO.getLatitude());
					}
					tdpCadre.setIsDeleted("N");
					
					if(cadreRegistrationVO.getSurveyTimeStr() != null && cadreRegistrationVO.getSurveyTimeStr().trim().length() > 0 && !cadreRegistrationVO.getSurveyTimeStr().trim().equalsIgnoreCase("null"))
					{
						
						try {
							SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_AND_TIME_FORMAT_24HRS);
							
							if(sdf.parse(cadreRegistrationVO.getSurveyTimeStr()).before(tdpCadre.getInsertedTime())){
								
								if(tdpCadre.getSurveyTime() == null){
									
								   tdpCadre.setSurveyTime(sdf.parse(cadreRegistrationVO.getSurveyTimeStr()));
								}
							}else
							{
								if(tdpCadre.getSurveyTime() == null)	
								tdpCadre.setSurveyTime(tdpCadre.getInsertedTime());
								tdpCadre.setRefSurveyTime(sdf.parse(cadreRegistrationVO.getSurveyTimeStr()));
							}
							
							if(tdpCadre.getSurveyTime() == null){
								tdpCadre.setSurveyTime(tdpCadre.getInsertedTime());
							}
						} catch (Exception e) {
							LOG.error(e);
						}
						
					}else if( insertType.equalsIgnoreCase("new") && registrationType != null && (registrationType.equalsIgnoreCase("WEB") )){
						if(tdpCadre.getSurveyTime() == null){
						   tdpCadre.setSurveyTime(tdpCadre.getInsertedTime());
						}
					}
					/*
					if(cadreRegistrationVO.getNomineeAge() != null && cadreRegistrationVO.getNomineeAge().longValue() > 0)
					{
						tdpCadre.setNomineeAge(cadreRegistrationVO.getNomineeAge());
					}
					if(cadreRegistrationVO.getNomineeGender() != null && cadreRegistrationVO.getNomineeGender().trim().length() > 0 &&  !cadreRegistrationVO.getNomineeGender().trim().equalsIgnoreCase("null"))
					{
						if(cadreRegistrationVO.getNomineeGender().trim().equalsIgnoreCase("1"))
						{
							tdpCadre.setNomineeGender("Male");
						}
						else
						{
							tdpCadre.setNomineeGender("Female");
						}
						
					}
					*/
					if(cadreRegistrationVO.getUniqueKey() != null && !cadreRegistrationVO.getUniqueKey().equalsIgnoreCase("null") && cadreRegistrationVO.getUniqueKey().trim().length() > 0)
					{
						tdpCadre.setUniqueKey(cadreRegistrationVO.getUniqueKey());
						if(cadreRegistrationVOList.size() == 1)
						{
							surveyCadreResponceVO.setUniqueKey(cadreRegistrationVO.getUniqueKey());
							surveyCadreResponceVO.setVoterName(cadreRegistrationVO.getVoterName());
							if(cadreRegistrationVO.getVoterId()!=null && cadreRegistrationVO.getVoterId().trim().length()>0){
								surveyCadreResponceVO.setVoterId(Long.valueOf(cadreRegistrationVO.getVoterId()));
							}
							
							surveyCadreResponceVO.setRelativeName(cadreRegistrationVO.getRelativeName());
						}
					}
					if(cadreRegistrationVO.getCandidateAadherNo() != null && cadreRegistrationVO.getCandidateAadherNo().trim().length() > 0 && !cadreRegistrationVO.getCandidateAadherNo().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setCadreAadherNo(cadreRegistrationVO.getCandidateAadherNo());
					}else{
						tdpCadre.setCadreAadherNo(null);
					}
					if(cadreRegistrationVO.getCadrePrevYear() != null && cadreRegistrationVO.getCadrePrevYear().trim().length() > 0 && !cadreRegistrationVO.getCadrePrevYear().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setPreviousMembershipYear(cadreRegistrationVO.getCadrePrevYear());
					}
					if(cadreRegistrationVO.getPhotoType() != null && cadreRegistrationVO.getPhotoType().trim().length() > 0 && !cadreRegistrationVO.getPhotoType().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setPhotoType(cadreRegistrationVO.getPhotoType() );
					}
					else
					{
						tdpCadre.setPhotoType("VOTER");
					}
					if(cadreRegistrationVO.getNameType() != null && cadreRegistrationVO.getNameType().trim().length() > 0 && !cadreRegistrationVO.getNameType().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setNameType(cadreRegistrationVO.getNameType().toUpperCase());
					}
					else
					{
						tdpCadre.setNameType("VOTER");
					}
					if(cadreRegistrationVO.getRelationType() != null && cadreRegistrationVO.getRelationType().trim().length() > 0 && !cadreRegistrationVO.getRelationType().trim().equalsIgnoreCase("null"))
					{
						tdpCadre.setRelativeType(cadreRegistrationVO.getRelationType());
					}				
					if(cadreRegistrationVO.isRelative()){
					   tdpCadre.setIsRelative("Y");
					}
					
					if(cadreRegistrationVO.getFamilyVoterId() != null && cadreRegistrationVO.getFamilyVoterId().longValue() > 0 && registrationType.equalsIgnoreCase("TAB"))
					{
						tdpCadre.setFamilyVoterId(cadreRegistrationVO.getFamilyVoterId());
						try {
							if(cadreRegistrationVO.getVoterId() != null && cadreRegistrationVO.getVoterId().trim().length() > 0)
							{
								if(cadreRegistrationVO.getFamilyVoterId().longValue() == Long.valueOf(cadreRegistrationVO.getVoterId().toString()).longValue())
								{
									tdpCadre.setVoterId(null);
									tdpCadre.setGender(cadreRegistrationVO.getGender());
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						tdpCadre.setIsRelative("Y");
						 tdpCadre.setRelationTypeId(cadreRegistrationVO.getRelationTypeId());
						
					}else{
						   tdpCadre.setIsRelative("N");
						   tdpCadre.setRelationTypeId(null);
						}
					//tdpCadre.setCardNo(cadreRegistrationVO.getVoterCardNumber());
					if(statusVar){
						tdpCadre.setNoVoterId("Y");
						
						if(cadreRegistrationVO.getVoterCardNumber() != null && cadreRegistrationVO.getVoterCardNumber().trim().length()>0){
						   Long count = tdpCadreDAO.checkCardNoExistsOrNot(cadreRegistrationVO.getVoterCardNumber());
						   if(count.longValue() > 0){
						      tdpCadre.setIsDeleted("Y");
						      tdpCadre.setIsDuplicate("Y");
						   }
						}
					}
					if(cadreRegistrationVO.getFamilyVoterId()!=null && registrationType.equalsIgnoreCase("WEB")){
						tdpCadre.setFamilyVoterId(cadreRegistrationVO.getFamilyVoterId());
						tdpCadre.setIsRelative("Y");
						 tdpCadre.setRelationTypeId(cadreRegistrationVO.getRelationTypeId());
					}
					boolean noSms = false;
					if(registrationType != null && (registrationType.equalsIgnoreCase("TAB") || registrationType.equalsIgnoreCase("WEB") || 
							registrationType.equalsIgnoreCase("ONLINE")) && insertType.equalsIgnoreCase("new")){
						String userId = "0000";
						if(cadreRegistrationVO.getCreatedUserId() != null){
						   userId = cadreRegistrationVO.getCreatedUserId().toString();
						}
						String ref = getReferenceNo(userId,registrationType);
						
							if(tdpCadre.getRefNo() == null || tdpCadre.getRefNo().trim().length() == 0){
							  ref = getUniueRefNo(ref,registrationType);
							  tdpCadre.setRefNo(ref);
							}
							cadreRegistrationVO.setRefNo(tdpCadre.getRefNo());
							
							surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
							//uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre);
							tdpCadre.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
							  tdpCadre1 = tdpCadreDAO.save(tdpCadre);	
						
					}else if(registrationType != null && (registrationType.equalsIgnoreCase("TAB") || registrationType.equalsIgnoreCase("WEB") ||
							registrationType.equalsIgnoreCase("ONLINE")) && !insertType.equalsIgnoreCase("new")){
						surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
						tdpCadre.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
						tdpCadre1 = tdpCadreDAO.save(tdpCadre);	
				    }else{
					  if(insertType.equalsIgnoreCase("new")){
							 							 
						     tdpCadre.setRefNo(cadreRegistrationVO.getRefNo());
						       
								surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
								tdpCadre.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
								  tdpCadre1 = tdpCadreDAO.save(tdpCadre);	
						  
					  }else{
						 
						  surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
						  tdpCadre.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
						    tdpCadre1= tdpCadreDAO.save(tdpCadre);	
					  }
					}
					   if(tdpCadre1.getMemberShipNo() == null || tdpCadre1.getMemberShipNo().trim().length() == 0){
						   Long distId =1l;
						   if(userAddress.getDistrict() != null){
							   distId = userAddress.getDistrict().getDistrictId();
						   }
						  String membershipNo = getMemberShipNo(distId,tdpCadre1.getTdpCadreId());
						  tdpCadre1.setMemberShipNo(membershipNo);
						}
					   uploadProfileImageForAffliatedCadre(cadreRegistrationVO,registrationType,tdpCadre1,cadreImgConstituency, insertType);
					   tdpCadre.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					   tdpCadre1 = tdpCadreDAO.save(tdpCadre1);
					   
					   
					//SAVING THE TELUGU NAME OF NON VOTER -- START //SASI
					if(tdpCadre1.getVoterId() == null && cadreRegistrationVO.getVoterTeluguName()!= null && cadreRegistrationVO.getVoterTeluguName().trim().length() > 0){
						TdpCadreTeluguNames model = new TdpCadreTeluguNames();
						model.setTeluguName(cadreRegistrationVO.getVoterTeluguName().trim());
						model.setTdpCadreId(tdpCadre1.getTdpCadreId());
						model.setEnglishName(cadreRegistrationVO.getVoterName());
						model.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						tdpCadreTeluguNamesDAO.save(model);
					}
					//SAVING THE TELUGU NAME OF NON VOTER -- END
					   
					surveyCadreResponceVO.setEnrollmentNumber(tdpCadre1.getRefNo());
					
					surveyCadreResponceVO.setMembershipNo(tdpCadre.getMemberShipNo());
					
					if(surveyCadreResponceVO.getMembershipNo() == null )
					{
						surveyCadreResponceVO.setMembershipNo(tdpCadre1.getMemberShipNo());
					}
					
					surveyCadreResponceVO.setStatus("SUCCESS");
					surveyCadreResponceVO.setResultCode(ResultCodeMapper.SUCCESS);
					if(insertType.equalsIgnoreCase("new") && cadreRegistrationVO.getMobileNumber() != null && cadreRegistrationVO.getMobileNumber().trim().length() > 0 && cadreRegistrationVO.getRefNo() != null && !noSms){
					   //sendSMS(cadreRegistrationVO.getMobileNumber().trim(), "Thank You for registering as TDP cadre.For further queries use Ref No "+cadreRegistrationVO.getRefNo());
						if(!statusVar){
								Boolean flag = true;
							try{
								
								if(cadreRegistrationVO.getFamilyVoterId() != null && cadreRegistrationVO.getFamilyVoterId().toString().trim().length() > 0 && !cadreRegistrationVO.getFamilyVoterId().toString().trim().equalsIgnoreCase("null") && cadreRegistrationVO.getFamilyVoterId().longValue() > 0)
								{
									Long  count = tdpCadreDAO.checkForFamilyExists(cadreRegistrationVO.getUniqueKey());
									if(count > 1)
									{
										flag = false;
									}
									
								}
								
								if(flag)
								{   
									String jobCode =  "";
									if(cadreRegistrationVO.getDataSourceType() != null && !cadreRegistrationVO.getDataSourceType().trim().equalsIgnoreCase("ONLINE"))
											jobCode = sendSMSForAffliatedCadre(cadreRegistrationVO.getCreatedUserId(),cadreRegistrationVO.getMobileNumber().trim(), "Thanks for registration, your Membership ID  :"+tdpCadre1.getMemberShipNo());
									else if(cadreRegistrationVO.getParentTdpCadreId() != null && cadreRegistrationVO.getParentTdpCadreId().longValue()>0L)
										jobCode = sendSMSForAffliatedCadre(cadreRegistrationVO.getCreatedUserId(),cadreRegistrationVO.getMobileNumber().trim(), "Thanks for TNGF registration, your Membership ID  :"+tdpCadre1.getMemberShipNo());
								}
								
								
							}catch (Exception e) {
								LOG.error("Exception Raised while sending SMS"+e);
							}
							
						}
					}
				}
				});
				
		//} catch (Exception e) {
		//	LOG.error("Exception raised in tdpCadreSavingLogic in CadreRegistrationService service", e);
		//}
	}
	public static String folderCreation(String folderPath){
	  	 try {
	  		 LOG.debug(" in FolderForArticle ");
			 
			 String activityDocDir = createFolder(folderPath);
			 if(!activityDocDir.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 return "SUCCESS";
			 
		} catch (Exception e) {
			LOG.error(" Failed to Create");
			return "FAILED";
		}
	}
	
	public static String createFolder(String dir){
	 	try {
			File theDir = new File(dir);
			  // if the directory does not exist, create it
			  if (!theDir.exists()) {
			    boolean result = false;
			    try{
			        theDir.mkdir();
			        result = true;
			     } catch(SecurityException se){
			        //handle it
			     }        
			     if(result) {    
			      LOG.debug("DIR With Name "+dir+" created");  
			     }
			  }else{
				  LOG.debug("DIR With Name "+dir+" EXISTS");
			  }
			  return "SUCCESS";
		} catch (Exception e) {
			LOG.error(dir+" Failed to Create");
			return "FAILED";
		}
	}
	public String uploadAffliatedCadreImage(String  voterCardNo,String url,String uploadImageContentType,File uploadImage,String cadreImgConstituency)
	{
		try{
			String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			
			
			 String filePath =  url +"images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + cadreImgConstituency;
			 String folderCreation = folderCreation(filePath);
			
			LOG.info("Cadre File Path -- "+filePath);
			
			BufferedImage image = ImageIO.read(uploadImage);
			
			
			if(image == null)
				return null;
			LOG.info("Image is Read");
			String constiName[] = uploadImageContentType.split("/");
			String fileName = filePath+ pathSeperator +voterCardNo.toString()+"."+constiName[1];
			LOG.info("file name -- "+fileName);
			//String imageName =  cadreId.toString()+"."+constiName[1];
			
			FileImageOutputStream filName = new FileImageOutputStream(new File(fileName));
			
			ImageIO.write(image, constiName[1],filName);
			LOG.info("file uploaded");
            filName.close();
            return "success";
		}
		catch(Exception e)
		{
			LOG.error("Exception raised in uploadCadreImage in CadreRegistrationService service", e);
			return null;
			
		}
	}
	public void uploadProfileImageForAffliatedCadre(CadreRegistrationVO cadreRegistrationVO,String registrationType,TdpCadre tdpCadre,String cadreImgConstituency,String insertType){
		
		
		LOG.error("PHOTOTYPE: "+cadreRegistrationVO.getPhotoType());
		LOG.error("EnrollmentNumber: "+cadreRegistrationVO.getPreviousEnrollmentNumber());
		LOG.error("VoterId: "+tdpCadre.getVoterId());
		LOG.error("ConstituencyId: "+cadreRegistrationVO.getConstituencyId());
		
		if(cadreRegistrationVO.getPhotoType() != null && cadreRegistrationVO.getPhotoType().trim().equalsIgnoreCase("cadre")){
			LOG.error("1");
		  if(cadreRegistrationVO.getPreviousEnrollmentNumber() != null && cadreRegistrationVO.getPreviousEnrollmentNumber().trim().length() > 0){
			  LOG.error("2");
			  String reqImage = getCadreImage(cadreRegistrationVO.getPreviousEnrollmentNumber().trim());
			  if(reqImage != null){
				  LOG.error("3");
				  String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
				  
				  //folder creation with constituency name in cadre_images folder.
				  
				  String folderCreationPart =  IConstants.STATIC_CONTENT_FOLDER_URL+"images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + cadreImgConstituency;
				  String folderCreation = folderCreation(folderCreationPart);
				  
				  String destinationPath = folderCreationPart + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
				  String sourcePath =   IConstants.STATIC_CONTENT_FOLDER_URL+"images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + reqImage;
				  String status = copyFile(sourcePath,destinationPath);
				  LOG.error("CADRE: SP:"+sourcePath+" DP"+destinationPath);
				   if(status.equalsIgnoreCase("success")){
					   LOG.error("4");
					   tdpCadre.setImage(cadreImgConstituency + "/" + tdpCadre.getMemberShipNo()+".jpg");
					   LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
				   }else{
					   LOG.error("5");
					   if(tdpCadre.getVoterId() != null){
							Voter voter = voterDAO.get(tdpCadre.getVoterId());
							if(voter != null && cadreRegistrationVO.getConstituencyId() != null && Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()).longValue() > 0){
								List<String> partNos = boothPublicationVoterDAO.getPartNo(Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()), voter.getVoterId());
								LOG.error("partNos size : "+partNos.size());
								if(partNos.size() > 0 && partNos.get(0) != null && voter.getVoterIDCardNo() != null){
								   sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL + IConstants.VOTER_IMG_FOLDER_PATH+"/"+(voter.getImagePath() != null ? voter.getImagePath() : "");
								   LOG.error("CADRENOTVOTER: SP:"+sourcePath+" DP:"+destinationPath+" VOTERID: "+voter.getVoterId());
								    status = copyFile(sourcePath,destinationPath);
								    LOG.error("Status : "+status);
								   if(status.equalsIgnoreCase("success")){
									   tdpCadre.setImage(cadreImgConstituency + "/" + tdpCadre.getMemberShipNo()+".jpg");
									   LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
								   }
								}
						   }
					  }
				   }
			  }else{
				  LOG.error("6");
				   if(tdpCadre.getVoterId() != null){
					   LOG.error("7");
						Voter voter = voterDAO.get(tdpCadre.getVoterId());
						 String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
						if(voter != null && cadreRegistrationVO.getConstituencyId() != null && Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()).longValue() > 0){
							List<String> partNos = boothPublicationVoterDAO.getPartNo(Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()), voter.getVoterId());
							LOG.error("partNos size : "+partNos.size());
							if(partNos.size() > 0 && partNos.get(0) != null && voter.getVoterIDCardNo() != null){
								
								 String folderCreationPart =  IConstants.STATIC_CONTENT_FOLDER_URL+"images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + cadreImgConstituency;
								 String folderCreation = folderCreation(folderCreationPart);
								 String destinationPath = folderCreationPart + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
								
							   String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +IConstants.VOTER_IMG_FOLDER_PATH+pathSeperator+(voter.getImagePath() != null ? voter.getImagePath() : "");
							   LOG.error("CADRENOTVOTER: SP:"+sourcePath+" DP:"+destinationPath+" VOTERID: "+voter.getVoterId());
							    String status = copyFile(sourcePath,destinationPath);
							    LOG.error("Status : "+status);
							   if(status.equalsIgnoreCase("success")){
								   tdpCadre.setImage(cadreImgConstituency + "/" + tdpCadre.getMemberShipNo()+".jpg");
								   LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
							   }
							}
					   }
				  }
			   }
		  }else{
			  LOG.error("8");
			   if(tdpCadre.getVoterId() != null){
				   LOG.error("9");
					Voter voter = voterDAO.get(tdpCadre.getVoterId());
					 String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
					if(voter != null && cadreRegistrationVO.getConstituencyId() != null && Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()).longValue() > 0){
						List<String> partNos = boothPublicationVoterDAO.getPartNo(Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()), voter.getVoterId());
						LOG.error("partNos size : "+partNos.size());
						if(partNos.size() > 0 && partNos.get(0) != null && voter.getVoterIDCardNo() != null){
							
							 String folderCreationPart =  IConstants.STATIC_CONTENT_FOLDER_URL+"images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + cadreImgConstituency;
							 String folderCreation = folderCreation(folderCreationPart);
							 String destinationPath = folderCreationPart + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
							 
						   String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +IConstants.VOTER_IMG_FOLDER_PATH+pathSeperator+(voter.getImagePath() != null ? voter.getImagePath() : "");
						   LOG.error("CADRENOTVOTER: SP:"+sourcePath+" DP:"+destinationPath+" VOTERID: "+voter.getVoterId());
						    String status = copyFile(sourcePath,destinationPath);
						    LOG.error("Status : "+status);
						   if(status.equalsIgnoreCase("success")){
							   tdpCadre.setImage(cadreImgConstituency + "/" + tdpCadre.getMemberShipNo()+".jpg");
							   LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
						   }
						}
				   }
			  }
		   }
		}else if(cadreRegistrationVO.getPhotoType() != null && cadreRegistrationVO.getPhotoType().trim().equalsIgnoreCase("voter") ){
			  LOG.error("10");
		  if(tdpCadre.getVoterId() != null){
			  LOG.error("11");
			    String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			    
			    String folderCreationPart =  IConstants.STATIC_CONTENT_FOLDER_URL+"images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + cadreImgConstituency;
				String folderCreation = folderCreation(folderCreationPart);
				String destinationPath = folderCreationPart + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
				
				Voter voter = voterDAO.get(tdpCadre.getVoterId());
				if(voter != null && cadreRegistrationVO.getConstituencyId() != null && Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()).longValue() > 0){
					  LOG.error("12");
					List<String> partNos = boothPublicationVoterDAO.getPartNo(Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()), voter.getVoterId());
					LOG.error("partNos size : "+partNos.size());
					if(partNos.size() > 0 && partNos.get(0) != null && voter.getVoterIDCardNo() != null){
					   String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +IConstants.VOTER_IMG_FOLDER_PATH+"/"+(voter.getImagePath() != null ? voter.getImagePath() : "");
					   LOG.error("VOTER: SP:"+sourcePath+" DP:"+destinationPath+" VOTERID: "+voter.getVoterId());
					   String status = copyFile(sourcePath,destinationPath);
					   LOG.error("Status : "+status);
					   if(status.equalsIgnoreCase("success")){
						   tdpCadre.setImage(cadreImgConstituency + "/" + tdpCadre.getMemberShipNo()+".jpg");
						   LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
					   }
					}
			   }
		  }
		}else{		
			  LOG.error("13");
			if(cadreRegistrationVO.getUploadImage() != null && !cadreRegistrationVO.getUploadImage().toString().equalsIgnoreCase("null")){
				  LOG.error("14");
				 LOG.error("IMAGE: MS:"+tdpCadre.getMemberShipNo());
				String result = uploadAffliatedCadreImage(tdpCadre.getMemberShipNo() , cadreRegistrationVO.getPath() , cadreRegistrationVO.getUploadImageContentType() , cadreRegistrationVO.getUploadImage(),cadreImgConstituency);
				if(result != null){
					 String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
					tdpCadre.setImage(cadreImgConstituency + "/" + tdpCadre.getMemberShipNo()+"."+cadreRegistrationVO.getUploadImageContentType().split("/")[1]);
				}
			}else if(cadreRegistrationVO.getAbsolutePath() != null && cadreRegistrationVO.getAbsolutePath().trim().length() > 0){
				try{
					String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
					
					 String folderCreationPart =  IConstants.STATIC_CONTENT_FOLDER_URL+"images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + cadreImgConstituency;
					 String folderCreation = folderCreation(folderCreationPart);
					 String destinationPath = folderCreationPart + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
					
                    boolean status = false;
					URL head1 = new URL(cadreRegistrationVO.getAbsolutePath()+".jpg");
					if(checkUrlExit(head1)){
						status = copyNewImg(head1,destinationPath);
					}
					URL head2 = new URL(cadreRegistrationVO.getAbsolutePath()+".JPG");
					if(!status && checkUrlExit(head2)){
						status = copyNewImg(head2,destinationPath);
					}
					URL head3 = new URL(cadreRegistrationVO.getAbsolutePath()+".jpeg");
					if(!status && checkUrlExit(head3)){
						status = copyNewImg(head3,destinationPath);
					}
					URL head4 = new URL(cadreRegistrationVO.getAbsolutePath()+".JPEG");
					if(!status && checkUrlExit(head4)){
						status = copyNewImg(head4,destinationPath);
					}
					URL head5 = new URL(cadreRegistrationVO.getAbsolutePath()+".png");
					if(!status && checkUrlExit(head5)){
						status = copyNewImg(head5,destinationPath);
					}
					URL head6 = new URL(cadreRegistrationVO.getAbsolutePath()+".PNG");
					if(!status && checkUrlExit(head6)){
						status = copyNewImg(head6,destinationPath);
					}
					if(status){
						tdpCadre.setImage(cadreImgConstituency + "/" + tdpCadre.getMemberShipNo()+".jpg");
						tdpCadre.setPhotoType("NEW");
					}
				}catch(Exception e){
					 LOG.error(e);
				}
			}
			else if(cadreRegistrationVO.getPhotoType() != null  && cadreRegistrationVO.getPhotoType().trim().equalsIgnoreCase("new")  && cadreRegistrationVO.getImageBase64String() != null && 
					cadreRegistrationVO.getImageBase64String().trim().length() > 0){
				  LOG.error("15");
					String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
					
					String folderCreationPart =  IConstants.STATIC_CONTENT_FOLDER_URL+"images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + cadreImgConstituency;
					String folderCreation = folderCreation(folderCreationPart);
					String filePath = folderCreationPart + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
					
					//String filePath = "D:/" + tdpCadre.getMemberShipNo()+".jpg";
					cadreRegistrationVO.setImageBase64String(cadreRegistrationVO.getImageBase64String().replace("_", "/"));
					cadreRegistrationVO.setImageBase64String(cadreRegistrationVO.getImageBase64String().replace("-", "+"));
					boolean status = imageAndStringConverter.convertBase64StringToImage(cadreRegistrationVO.getImageBase64String(),filePath);
					//System.out.println(cadreRegistrationVO.getImageBase64String());
					 LOG.error("BASE64: DP:"+filePath);
					 try{
					 if(cadreRegistrationVO.getImageBase64String().length() > 55){
					     LOG.error("BASE64FIRST50C: "+cadreRegistrationVO.getImageBase64String().substring(0, 50));
					 }else{
						 LOG.error("BASE64FIRST50C: "+cadreRegistrationVO.getImageBase64String());
					 }
					 }catch(Exception ex){
						 
					 }
					if(status){
						tdpCadre.setImage(cadreImgConstituency + "/" + tdpCadre.getMemberShipNo()+".jpg");
						LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
					}
			}else{
				
				  LOG.error("16");
				 if(tdpCadre.getVoterId() != null && !insertType.equalsIgnoreCase("update")){// For save scanario only it should work.
					  LOG.error("17");
					    String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
					    
					    String folderCreationPart =  IConstants.STATIC_CONTENT_FOLDER_URL+"images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + cadreImgConstituency;
						String folderCreation = folderCreation(folderCreationPart);
						String destinationPath = folderCreationPart + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
						
						Voter voter = voterDAO.get(tdpCadre.getVoterId());
						if(voter != null && cadreRegistrationVO.getConstituencyId() != null && Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()).longValue() > 0){
							List<String> partNos = boothPublicationVoterDAO.getPartNo(Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()), voter.getVoterId());
							LOG.error("partNos size : "+partNos.size());
							if(partNos.size() > 0 && partNos.get(0) != null && voter.getVoterIDCardNo() != null){
							   String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +IConstants.VOTER_IMG_FOLDER_PATH+"/"+(voter.getImagePath() != null ? voter.getImagePath() : "");
							   LOG.error("VOTERN: SP:"+sourcePath+" DP:"+destinationPath+" VOTERID: "+voter.getVoterId());
							   String status = copyFile(sourcePath,destinationPath);
							   LOG.error("Status : "+status);
							   if(status.equalsIgnoreCase("success")){
								   tdpCadre.setImage(cadreImgConstituency + "/" + tdpCadre.getMemberShipNo()+".jpg");
								   LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
							   }
							}
					   }
				  }
			}
			
		}
		
	}

	private String sendSMSForAffliatedCadre(Long userId,String mobileNo,String message){
		  
		  if(!IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver")){
        	return "error"; 
          }
		  
		 /* SmsHistory smsHistory = smsSenderService.sendSMS(userId, "Affiliated Graduates Enrollment", false, message, mobileNo);
		  if(smsHistory != null)
			  return "success";
		  else
			  return "error";
		  */
		  
	      HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
	      client.getHttpConnectionManager().getParams().setConnectionTimeout(
	        Integer.parseInt("30000"));
	    
	      boolean isEnglish = true;
	      
	      PostMethod post = new PostMethod("http://smscountry.com/SMSCwebservice_Bulk.aspx");
	      
	      post.addParameter("User",IConstants.ADMIN_USERNAME_FOR_SMS);
	      post.addParameter("passwd",IConstants.ADMIN_PASSWORD_FOR_SMS);
	      post.addParameter("mobilenumber", mobileNo);
	      post.addParameter("message", message);
	      post.addParameter("mtype", isEnglish ? "N" : "OL");
	      post.addParameter("DR", "Y");
	      
	      
	      try 
	      {
	        int statusCode = client.executeMethod(post);
	        
	        if (statusCode != HttpStatus.SC_OK) {
	          System.out.println("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
	          return "error";
	        }
	        else{
	          return "success";
	        }

	      }catch (Exception e) {
	          System.out.println("Exception rised in sending sms while cadre registration "+e);
	          return "exception";
	      } finally {
	          post.releaseConnection();
	      }
	      
	    }
	
	
	private String sendSMSAffliatedCadre(Long userId,String mobileNo,String message){
		  
		  if(!IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver")){
      	return "error"; 
        }
		  
		  SmsHistory smsHistory = smsSenderService.sendSMS(userId, "Affiliated Graduates Enrollment", false, message, mobileNo);
		  if(smsHistory != null)
			  return "success";
		  else
			  return "error";
		  
		  /*
	      HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
	      client.getHttpConnectionManager().getParams().setConnectionTimeout(
	        Integer.parseInt("30000"));
	    
	      boolean isEnglish = true;
	      
	      PostMethod post = new PostMethod("http://smscountry.com/SMSCwebservice_Bulk.aspx");
	      
	      post.addParameter("User",IConstants.ADMIN_USERNAME_FOR_SMS);
	      post.addParameter("passwd",IConstants.ADMIN_PASSWORD_FOR_SMS);
	      post.addParameter("mobilenumber", mobileNo);
	      post.addParameter("message", message);
	      post.addParameter("mtype", isEnglish ? "N" : "OL");
	      post.addParameter("DR", "Y");
	      
	       
	      try 
	      {
	        int statusCode = client.executeMethod(post);
	        
	        if (statusCode != HttpStatus.SC_OK) {
	          System.out.println("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
	          return "error";
	        }
	        else{
	          return "success";
	        }

	      }catch (Exception e) {
	          System.out.println("Exception rised in sending sms while cadre registration "+e);
	          return "exception";
	      } finally {
	          post.releaseConnection();
	      }
	      */
	    }
	
	public List<GenericVO> getCadreMemberTypeListByYear(){
		List<GenericVO> returnList  = null;
		
		try {
			List<Object[]> tdpMemberTypeList = tdpMemberTypeDAO.getCadreMemberTypeListByYear();
			if(tdpMemberTypeList != null && tdpMemberTypeList.size()>0){
				returnList = new ArrayList<GenericVO>(0);
				for (Object[] member : tdpMemberTypeList) {
					GenericVO vo = new GenericVO();
					
					//vo.setId(commonMethodsUtilService.getLongValueForObject(member[0]));
					//vo.setName(commonMethodsUtilService.getStringValueForObject(member[1]));
					vo.setId(Long.valueOf(member[0] != null ? member[0].toString():"0L"));
					vo.setName(member[1]!=null?member[1].toString():null);
					if(vo.getId() != null && vo.getId() != 1L)
						returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getCadreMemberTypeListByYear in CadreRegistrationService service", e);
		}
		return returnList;
	}
	
	
	
public List<TdpCadreVO> getLocationwiseCadreRegistraionDetails(List<Long> membereTypeIdsList,String searchTypeStr,String startDate,String toDate,String searchDatType) {
		
		List<TdpCadreVO>  returnList = null;
		try {
			
			Map<Long,TdpCadreVO> cadrelocationWiseMap=new HashMap<Long,TdpCadreVO>();
			List<Object[]> cadreRegisteredCountdtls =  null;
			
			if(searchTypeStr.equalsIgnoreCase("Constituency")){
				List<Object[]> constituencyList=constituencyDAO.getAllAssemblyConstituenciesByStateTypeId(1L,1L,null);
				if(constituencyList!=null && constituencyList.size()>0){
					for(Object[] distCadre:constituencyList) {
						TdpCadreVO tdpvo=new TdpCadreVO();
						tdpvo.setId(commonMethodsUtilService.getLongValueForObject(distCadre[0]));
						tdpvo.setName(commonMethodsUtilService.getStringValueForObject(distCadre[1]));
						/*tdpvo.setId(Long.valueOf(distCadre[0]!=null?distCadre[0].toString():"0L"));
						tdpvo.setName(distCadre[1]!=null?distCadre[1].toString():null);*/
						tdpvo.setTabCount(0L);
						tdpvo.setWebCount(0L);
						tdpvo.setTotalCount(0L);
						cadrelocationWiseMap.put(tdpvo.getId(), tdpvo);
					}
				}	
			}else if(searchTypeStr.equalsIgnoreCase("District")){
				List<Object[]> districtList=districtDAO.getDistrictIdAndNameByStateForStateTypeId(1L,1L);
				if(districtList!=null && districtList.size()>0){
					for(Object[] distCadre:districtList){
						TdpCadreVO tdpvo=new TdpCadreVO();
						tdpvo.setId(commonMethodsUtilService.getLongValueForObject(distCadre[0]));
						tdpvo.setName(commonMethodsUtilService.getStringValueForObject(distCadre[1]));
						/*tdpvo.setId(Long.valueOf(distCadre[0]!=null? distCadre[0].toString():"0L"));
						tdpvo.setName(distCadre[1]!=null?distCadre[1].toString():null);*/
						tdpvo.setTabCount(0L);
						tdpvo.setWebCount(0L);
						tdpvo.setTotalCount(0L);
						cadrelocationWiseMap.put(tdpvo.getId(),tdpvo);
					}
				}
			}
			
			Date stDate=null;
			Date edDate=null;
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
			Date today = dateUtilService.getCurrentDateAndTime();
			if(startDate!=null && startDate.trim().length()>0 && toDate!=null && toDate.trim().length()>0){
				stDate=sdf.parse(startDate.trim());
				edDate=sdf.parse(toDate.trim());
				cadreRegisteredCountdtls =  tdpCadreDAO.getLocationwiseCadreRegistraionDetails(membereTypeIdsList, searchTypeStr,stDate,edDate);
			}				
				
			else
			{
		        if(searchDatType.equalsIgnoreCase("total")){
		        	stDate = null;
					edDate = null;
				}else if(searchDatType.equalsIgnoreCase("today")){
					stDate = today;
					edDate = today;
				}else if(searchDatType.equalsIgnoreCase("last 7 days")){
					Calendar cal = Calendar.getInstance();
					cal.setTime(today);
					cal.add(Calendar.DATE, -7);
					Date last7ThDay = cal.getTime();
					stDate = last7ThDay;
					edDate = today;
				}else if(searchDatType.equalsIgnoreCase("last 30 days")){
					
					Calendar cal = Calendar.getInstance();
					cal.setTime(today);
					cal.add(Calendar.DATE, -30);
					Date last30ThDay = cal.getTime();
					stDate = last30ThDay;
					edDate = today;
				}
			}
			
			 cadreRegisteredCountdtls = tdpCadreDAO.getLocationwiseCadreRegistraionDetails(membereTypeIdsList, searchTypeStr, stDate,edDate);
			if(cadreRegisteredCountdtls != null && cadreRegisteredCountdtls.size()>0)
			{
				for (Object[] cadreDtls : cadreRegisteredCountdtls) {
					Long locationId = commonMethodsUtilService.getLongValueForObject(cadreDtls[0]);
					/*Long locationId=Long.valueOf(cadreDtls[0]!=null ?cadreDtls[0].toString():"0L");*/
					TdpCadreVO vo = cadrelocationWiseMap.get(locationId);
					if(vo != null){
						String typeStr = commonMethodsUtilService.getStringValueForObject(cadreDtls[2]);
						Long count = commonMethodsUtilService.getLongValueForObject(cadreDtls[3]);
						if(typeStr != null && !typeStr.isEmpty())
						{
							if(typeStr.trim().equalsIgnoreCase("TAB")){
								vo.setTabCount(count);
							}
							else if(typeStr.trim().equalsIgnoreCase("WEB")){
								vo.setWebCount(count);								
							}
							
							Long webCount = vo.getWebCount() != null ? Long.valueOf(vo.getWebCount().toString().trim()):0L;
							Long tabCount = vo.getTabCount() != null ? Long.valueOf(vo.getTabCount().toString().trim()):0L;
							Long totalCount = webCount + tabCount;
							if(totalCount != null && totalCount.longValue()>0L)
								vo.setTotalCount(totalCount);
						}
					}
				}
			}
			
			if(cadrelocationWiseMap != null && cadrelocationWiseMap.size()>0)
			{
				returnList = new ArrayList<TdpCadreVO>(0);
				for (Long locationId : cadrelocationWiseMap.keySet()) {
					returnList.add(cadrelocationWiseMap.get(locationId));
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getLocationwiseCadreRegistraionDetails in CadreRegistrationService service", e);
		}
		return returnList;
	}
	
	public AffiliatedCadreVO getCadreCountsByTdpMemberType(String searchType){
		AffiliatedCadreVO finalvo = new AffiliatedCadreVO();
		
		try {
			
			Date stDate = null;
			Date edDate = null;
			Date today = dateUtilService.getCurrentDateAndTime();
			//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
			List<AffiliatedCadreVO> voList = new ArrayList<AffiliatedCadreVO>();
			Map<Long,AffiliatedCadreVO> afflCdrMap = new LinkedHashMap<Long, AffiliatedCadreVO>();
			
			if(searchType.equalsIgnoreCase("Total")){
				stDate = null;
				edDate = null;
			}
			else if(searchType.equalsIgnoreCase("Today")){
				stDate = today;
				edDate = today;
			}
			else if(searchType.equalsIgnoreCase("Last 7 days")){
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(today);
				cal.add(Calendar.DATE, -7);
				Date last7ThDay = cal.getTime();
				stDate = last7ThDay;
				edDate = today;
			}
			else if(searchType.equalsIgnoreCase("Last 30 days")){
				Calendar cal = Calendar.getInstance();
				cal.setTime(today);
				cal.add(Calendar.DATE, -30);
				Date last30ThDay = cal.getTime();
				stDate = last30ThDay;
				edDate = today;
			}
			
			//0.count,1.tdpMemberTypeId,2.memberType,3.dataSourceType
			List<Object[]> list = tdpCadreDAO.getCadreCountsByTdpMemberType(stDate, edDate);
			if(list != null && list.size() > 0){
				for (Object[] obj : list) {
					AffiliatedCadreVO vo = null;
					Long id = Long.valueOf(obj[1] != null ? obj[1].toString():"0L");
					String sourceType = obj[3] != null ? obj[3].toString():"";
					
					vo = afflCdrMap.get(id);
					if(vo != null){
						if(id.longValue() == vo.getTdpMemberTypeId().longValue()){
							if(vo.getCount() != null && vo.getCount().longValue() > 0L){
								vo.setCount(vo.getCount().longValue() + Long.valueOf(Long.valueOf(obj[0] != null ? obj[0].toString():"0L")));
							}
							else{
								vo.setCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0L"));
							}
							if(sourceType.trim().equalsIgnoreCase("WEB")){
								vo.setWebCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0L"));
							}
							else if(sourceType.trim().equalsIgnoreCase("TAB")){
								vo.setTabCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0L"));
							}
							else if(sourceType.trim().equalsIgnoreCase("ONLINE")){
								vo.setOnlineCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0L"));
							}
						}
					}
					else{
						vo = new AffiliatedCadreVO();
						vo.setTdpMemberTypeId(id);
						vo.setMemberType(obj[2] != null ? obj[2].toString():"");
						vo.setCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0L"));
						if(sourceType.trim().equalsIgnoreCase("WEB")){
							vo.setWebCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0L"));
						}
						else if(sourceType.trim().equalsIgnoreCase("TAB")){
							vo.setTabCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0L"));
						}
						else if(sourceType.trim().equalsIgnoreCase("ONLINE")){
							vo.setOnlineCount(Long.valueOf(obj[0] != null ? obj[0].toString():"0L"));
						}
						afflCdrMap.put(id, vo);
					}
				}
			}
			
			voList.addAll(afflCdrMap.values());
			finalvo.setAffiliatedCadreVoList(voList);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getCadreCountsByTdpMemberType in CadreRegistrationService service", e);
		}
		return finalvo;
	}
	
	public AffiliatedCadreVO getAllTotalCountsForAll(String searchType){	
	AffiliatedCadreVO finalvo = new AffiliatedCadreVO();
		
		try {
			
			Date stDate = null;
			Date edDate = null;
			Date today = dateUtilService.getCurrentDateAndTime();
			//List<AffiliatedCadreVO> voList = new ArrayList<AffiliatedCadreVO>();
			//Map<Long,AffiliatedCadreVO> afflCdrMap = new LinkedHashMap<Long, AffiliatedCadreVO>();
			
			if(searchType.equalsIgnoreCase("Total")){
				stDate = null;
				edDate = null;
			}
			else if(searchType.equalsIgnoreCase("Today")){
				stDate = today;
				edDate = today;
			}
			else if(searchType.equalsIgnoreCase("Last 7 days")){
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(today);
				cal.add(Calendar.DATE, -7);
				Date last7ThDay = cal.getTime();
				stDate = last7ThDay;
				edDate = today;
			}
			else if(searchType.equalsIgnoreCase("Last 30 days")){
				Calendar cal = Calendar.getInstance();
				cal.setTime(today);
				cal.add(Calendar.DATE, -30);
				Date last30ThDay = cal.getTime();
				stDate = last30ThDay;
				edDate = today;
			}
				List<Object[]> list = tdpCadreDAO.getAllCountsForUnionMembersRegistered(stDate, edDate);
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {			
							String sourceType = obj[0] != null ? obj[0].toString():"";

							if(sourceType.trim().equalsIgnoreCase("WEB")){
								finalvo.setWebCount(Long.valueOf(obj[1] != null ? obj[1].toString():"0L"));
							}
							else if(sourceType.trim().equalsIgnoreCase("TAB")){
								finalvo.setTabCount(Long.valueOf(obj[1] != null ? obj[1].toString():"0L"));
							}
							else if(sourceType.trim().equalsIgnoreCase("ONLINE")){
								finalvo.setOnlineCount(Long.valueOf(obj[1] != null ? obj[1].toString():"0L"));
							}
							if(finalvo.getCount() != null && finalvo.getCount().longValue() > 0L){
								finalvo.setCount(finalvo.getCount().longValue() + Long.valueOf(Long.valueOf(obj[1] != null ? obj[1].toString():"0L")));
							}
							else{
								finalvo.setCount(Long.valueOf(obj[1] != null ? obj[1].toString():"0L"));
							}
						}
				}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getAllTotalCountsForAllAffiliatedUnionMembers in CadreRegistrationService service", e);
		}
		return finalvo;
	}
	public List<PartyMeetingWSVO> getRegistrationCadreDetails(RtcUnionInputVO inputVO)
	  {
		  List<PartyMeetingWSVO> resultList = new ArrayList<PartyMeetingWSVO>();
		  try{
			 
			   /* Date stDate = null;
				Date edDate = null;
				Date today = dateUtilService.getCurrentDateAndTime();
				
				if(inputVO.getDateType().equalsIgnoreCase("Total")){
					stDate = null;
					edDate = null;
				}
				else if(inputVO.getDateType().equalsIgnoreCase("Today")){
					stDate = today;
					edDate = today;
				}
				else if(inputVO.getDateType().equalsIgnoreCase("Last 7 days")){
					
					Calendar cal = Calendar.getInstance();
					cal.setTime(today);
					cal.add(Calendar.DATE, -7);
					Date last7ThDay = cal.getTime();
					stDate = last7ThDay;
					edDate = today;
				}
				else if(inputVO.getDateType().equalsIgnoreCase("Last 30 days")){
					Calendar cal = Calendar.getInstance();
					cal.setTime(today);
					cal.add(Calendar.DATE, -30);
					Date last30ThDay = cal.getTime();
					stDate = last30ThDay;
					edDate = today;
				}*/
				
				Date stDate=null;
				Date edDate=null;
				SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
				Date today = dateUtilService.getCurrentDateAndTime();
				if(inputVO.getStartDate()!=null && inputVO.getStartDate().trim().length()>0 && inputVO.getToDate()!=null && inputVO.getToDate().trim().length()>0){
					stDate=sdf.parse(inputVO.getStartDate().trim());
					edDate=sdf.parse(inputVO.getToDate().trim());
				}
				
				
				List<Long> tdpCadreIdsList = tdpCadreDAO.getCadreDetailsByTdpMemberType(stDate,edDate,inputVO);
				List<Object[]> cadreDetails = tdpCadreDAO.getCadreFormalDetailsByYear(tdpCadreIdsList,2016L);
				List<Long> voterIds =new ArrayList<Long>();
				
		        if(cadreDetails != null && cadreDetails.size() > 0){
		          for (Object[] obj : cadreDetails) {
		            PartyMeetingWSVO vo = new PartyMeetingWSVO();
		            
		            Long cadreId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
		            String image = obj[5] != null ? obj[5].toString():"";
		            vo.setTdpCadreId(cadreId);
		            vo.setName(obj[1] != null ? obj[1].toString():"");
		            vo.setDateOfBirth(obj[2] != null ? obj[2].toString():"");
		            vo.setAge(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
		            vo.setMobileNo(obj[4] != null ? obj[4].toString():"");
		            vo.setImgStr("https://mytdp.com/images/"+IConstants.CADRE_IMAGES+"/"+image+"");
		            vo.setMemberShipNo(obj[6] != null ? obj[6].toString():"");
		            vo.setVoterCardNo(obj[9] != null ? obj[9].toString():"");
		            vo.setRegThrough(obj[10] != null ? obj[10].toString():"");
		            vo.setMemberType(obj[12] != null ? obj[12].toString():"");
		            vo.setVoterId(obj[13] != null ? (Long)obj[13]:0l);
		            resultList.add(vo);
		            
		            
		            //get voterIds.
		            if(obj[13]!=null){
		            	voterIds.add((Long)obj[13]);
		            }
		            
		          }
		          
		          //check cadre in 2014.
		          if(voterIds!=null && voterIds.size()>0){
		        	  List<Object[]> list=tdpCadreDAO.getmemberShipIdsByVoterIds(IConstants.CADRE_ENROLLMENT_NUMBER,voterIds);
		        	  if(list!=null && list.size()>0){
		        		 for(Object[] obj:list){
		        			 Long voterId=obj[0]!=null?(Long)obj[0]:0l;
		        			 if(voterId>0){
		        				 PartyMeetingWSVO voterVO = getMatchedVoter(voterId,resultList);
		        				 if(voterVO!=null){
		        					 voterVO.setCadreMembershipno(obj[1]!=null?obj[1].toString():"");
		        					 voterVO.setCadreDataSourceType(obj[3]!=null?obj[3].toString():"");
		        				 }
		        			 }
		        		 }
		        	  }
		        	  
		          }
		          
		          
		          
		        }
		  }
		  catch (Exception e) {
			  e.printStackTrace();
			  LOG.error("Exception raised in getRegistrationCadreDetails in CadreRegistrationService service", e);	}
		return resultList;
		  
	}
	public List<PartyMeetingWSVO> getRegistrationCadreDetailsSourceWise(RtcUnionInputVO inputVO)
	  {
		  List<PartyMeetingWSVO> resultList = new ArrayList<PartyMeetingWSVO>();
		  try{
				Date stDate=null;
				Date edDate=null;
				SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
				Date today = dateUtilService.getCurrentDateAndTime();
				if(inputVO.getStartDate()!=null && inputVO.getStartDate().trim().length()>0 && inputVO.getToDate()!=null && inputVO.getToDate().trim().length()>0){
					stDate=sdf.parse(inputVO.getStartDate().trim());
					edDate=sdf.parse(inputVO.getToDate().trim());
				}
				List<Long> tdpCadreIdsList = tdpCadreDAO.getCadreDetailsByTdpMemberTypeSourceWise(stDate,edDate,inputVO);
				List<Object[]> cadreDetails = tdpCadreDAO.getCadreDetailsByYearSourceWise(tdpCadreIdsList,2016L);
				List<Long> voterIds =new ArrayList<Long>();
				
		        if(cadreDetails != null && cadreDetails.size() > 0){
		          for (Object[] obj : cadreDetails) {
		            PartyMeetingWSVO vo = new PartyMeetingWSVO();
		            
		            Long cadreId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
		            String image = obj[5] != null ? obj[5].toString():"";
		            vo.setTdpCadreId(cadreId);
		            vo.setName(obj[1] != null ? obj[1].toString():"");
		            vo.setDateOfBirth(obj[2] != null ? obj[2].toString():"");
		            vo.setAge(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
		            vo.setMobileNo(obj[4] != null ? obj[4].toString():"");
		            vo.setImgStr("https://mytdp.com/images/"+IConstants.CADRE_IMAGES+"/"+image+"");
		            vo.setMemberShipNo(obj[6] != null ? obj[6].toString():"");
		            vo.setVoterCardNo(obj[9] != null ? obj[9].toString():"");
		            vo.setRegThrough(obj[10] != null ? obj[10].toString():"");
		            vo.setMemberType(obj[12] != null ? obj[12].toString():"");
		            vo.setVoterId(obj[13] != null ? (Long)obj[13]:0l);
		            resultList.add(vo);
		          }
		        }
		  }
		  catch (Exception e) {
			  e.printStackTrace();
			  LOG.error("Exception raised in getRegistrationCadreDetailsSourceWise in CadreRegistrationService service", e);	}
		return resultList;
		  
	  }
	
	public PartyMeetingWSVO getMatchedVoter(Long id,List<PartyMeetingWSVO> list){
		
			try{
				if(list == null)
					return null;
				for(PartyMeetingWSVO vo : list)
				{
					if(id.longValue() == vo.getVoterId().longValue())
						return vo;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				
			}
			return null;
		}
public List<TdpCadreVO> getLocationwiseCadreRegistraionDetailsForAffliatedCadre(List<Long> membereTypeIdsList,String searchTypeStr,String startDate,String toDate) {
		
		List<TdpCadreVO>  returnList = null;
		try {
			
			Map<Long,TdpCadreVO> cadrelocationWiseMap=new HashMap<Long,TdpCadreVO>();
			List<Object[]> cadreRegisteredCountdtls =  null;
			
			if(searchTypeStr.equalsIgnoreCase("Constituency")){
				List<Object[]> constituencyList=constituencyDAO.getAllAssemblyConstituenciesByStateTypeId(1L,1L,null);
				if(constituencyList!=null && constituencyList.size()>0){
					for(Object[] distCadre:constituencyList) {
						TdpCadreVO tdpvo=new TdpCadreVO();
						tdpvo.setId(commonMethodsUtilService.getLongValueForObject(distCadre[0]));
						tdpvo.setName(commonMethodsUtilService.getStringValueForObject(distCadre[1]));
						tdpvo.setTabCount(0L);
						tdpvo.setWebCount(0L);
						tdpvo.setOnlineCount(0L);
						tdpvo.setTotalCount(0L);
						cadrelocationWiseMap.put(tdpvo.getId(), tdpvo);
					}
				}	
			}else if(searchTypeStr.equalsIgnoreCase("District")){
				List<Object[]> districtList=districtDAO.getDistrictIdAndNameByStateForStateTypeId(1L,1L);
				if(districtList!=null && districtList.size()>0){
					for(Object[] distCadre:districtList){
						TdpCadreVO tdpvo=new TdpCadreVO();
						tdpvo.setId(commonMethodsUtilService.getLongValueForObject(distCadre[0]));
						tdpvo.setName(commonMethodsUtilService.getStringValueForObject(distCadre[1]));
						tdpvo.setTabCount(0L);
						tdpvo.setWebCount(0L);
						tdpvo.setOnlineCount(0L);
						tdpvo.setTotalCount(0L);
						cadrelocationWiseMap.put(tdpvo.getId(),tdpvo);
					}
				}
			}
			
			Date stDate=null;
			Date edDate=null;
			SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
			Date today = dateUtilService.getCurrentDateAndTime();
			if(startDate!=null && startDate.trim().length()>0 && toDate!=null && toDate.trim().length()>0){
				stDate=sdf.parse(startDate.trim());
				edDate=sdf.parse(toDate.trim());
			}				
				
			
			 cadreRegisteredCountdtls = tdpCadreDAO.getLocationwiseCadreRegistraionDetails(membereTypeIdsList, searchTypeStr, stDate,edDate);
			if(cadreRegisteredCountdtls != null && cadreRegisteredCountdtls.size()>0)
			{
				for (Object[] cadreDtls : cadreRegisteredCountdtls) {
					Long locationId = commonMethodsUtilService.getLongValueForObject(cadreDtls[0]);
					/*Long locationId=Long.valueOf(cadreDtls[0]!=null ?cadreDtls[0].toString():"0L");*/
					TdpCadreVO vo = cadrelocationWiseMap.get(locationId);
					if(vo != null){
						String typeStr = commonMethodsUtilService.getStringValueForObject(cadreDtls[2]);
						Long count = commonMethodsUtilService.getLongValueForObject(cadreDtls[3]);
						if(typeStr != null && !typeStr.isEmpty())
						{
							if(typeStr.trim().equalsIgnoreCase("TAB")){
								vo.setTabCount(count);
							}
							else if(typeStr.trim().equalsIgnoreCase("WEB")){
								vo.setWebCount(count);								
							}
							else if(typeStr.trim().equalsIgnoreCase("ONLINE")){
								vo.setOnlineCount(count);
							}
							Long webCount = vo.getWebCount() != null ? Long.valueOf(vo.getWebCount().toString().trim()):0L;
							Long tabCount = vo.getTabCount() != null ? Long.valueOf(vo.getTabCount().toString().trim()):0L;
							Long onlineCount = vo.getOnlineCount() != null ? Long.valueOf(vo.getOnlineCount().toString().trim()):0L;
							Long totalCount = webCount + tabCount + onlineCount;
							if(totalCount != null && totalCount.longValue()>0L)
								vo.setTotalCount(totalCount);
						}
					}
				}
			}
			
			if(cadrelocationWiseMap != null && cadrelocationWiseMap.size()>0)
			{
				returnList = new ArrayList<TdpCadreVO>(0);
				for (Long locationId : cadrelocationWiseMap.keySet()) {
					returnList.add(cadrelocationWiseMap.get(locationId));
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getLocationwiseCadreRegistraionDetails in CadreRegistrationService service", e);
		}
		return returnList;
	}
	
	public ResultStatus saveRegistrationQueriesForm(final RegistrationQueriesVO regQueriesVO){
		ResultStatus resultStatus = new ResultStatus();
		try {
			resultStatus = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus status) {
					UserFeedback userFeedback = new UserFeedback();
					
					userFeedback.setUserFeedbackModuleId(1l);
					userFeedback.setName(regQueriesVO.getName());
					userFeedback.setMobile(regQueriesVO.getMobileNo());
					userFeedback.setEmail(regQueriesVO.getEmail());
					userFeedback.setFeedbackDescription(regQueriesVO.getDescription());
					
					userFeedback = userFeedbackDAO.save(userFeedback);
					
					
					//Email Sending...
					EmailDetailsVO mailvo = new EmailDetailsVO();
					
					String content = "";
					content = content + ""+getHeader()+ "<br/>";
					content = content + "<p style='margin-left:20px;'><b>Name </b>: "+regQueriesVO.getName()+"</p> ";
					content = content + "<p style='margin-left:20px'><b>Mobile </b>: "+regQueriesVO.getMobileNo()+"</p> ";
					content = content + "<p style='margin-left:20px'><b>Email </b>: "+regQueriesVO.getEmail()+"</p> ";
					content = content + "<p style='margin-left:20px'><b>Description </b>: "+regQueriesVO.getDescription()+"</p>";//regQueriesVO.getDescription();
					content = content + "<p style='margin-left:20px'><br>Thanks,</p> ";
					content = content + "<p style='margin-left:20px'><b>"+regQueriesVO.getName()+"</b></p> ";
					
					/*String content = "";
					content = content + "<p style='text-align:center'>"+getHeader()+ "</p><br/>";
					content = content + regQueriesVO.getDescription()+" <br/><br/> ";//regQueriesVO.getDescription();
					
					content = content + "<p style='text-align:center'>Thanks for your feed back . We will contact you shortly.</p><br/><br/>";
			    	content = content + "<p style='text-align:center'>Thanks,</p> <br/>";
			    	content = content + "<p style='text-align:center'>Telugu Nadu Graduates Federation (TNGF),</p> <br/>";
			    	content = content + "<p style='text-align:center'>Telugu Desam Party.</p>";*/
					
					String subject = "TNGF Enrollment Registration Form Feedback";
					//mailvo.setContent(content);
					//mailvo.setToAddress("sravanth.itgrids.hyd@gmail.com");
					
					List<EmailDetailsVO> mailvoList = new ArrayList<EmailDetailsVO>();
					mailvoList.add(new EmailDetailsVO(subject,content,"sravanth.itgrids.hyd@gmail.com"));
					//mailvoList.add(new EmailDetailsVO(subject,content,"a.dakavaram@gmail.com"));
					mailvoList.add(new EmailDetailsVO(subject,content,"srishailam.itgrids.hyd@gmail.com"));
					mailvoList.add(new EmailDetailsVO(subject,content,IConstants.LOCALFROMEMAILID));
					/*String message = regQueriesVO.getDescription();
					message = message+"Thanks for your feedback,We will contact shortly.";
					smsSenderService.sendSMS(1l, "TNGF", true, message, regQueriesVO.getMobileNo());*/
					
					return mailService.sendEmails(mailvoList);
					
				}
			}); 
			
			//resultStatus.setMessage(IConstants.SUCCESS);
			//resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		} catch (Exception e) {
			resultStatus.setMessage(IConstants.FAILURE);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			LOG.error("Exception raised in saveRegistrationQueriesForm in CadreRegistrationService service", e);
		}
		return resultStatus;
	}
	
	public static String getHeader()
 	{
 	
 		String header="<table cellspacing='0' cellpadding='0'  border='0' style='border-collapse:collapse;border:0'>";
 		header+="<tbody><tr><td><table cellspacing='0' cellpadding='0' width='100%' border='0' style='border-collapse:collapse;background-color:#f9f9f9;border-top:0;border-bottom:0'>";
        header+="<tbody><tr><td valign='top' style=''>";
 		header+="<table cellspacing='0' cellpadding='0' width='100%' border='0' style='border-collapse:collapse'><tbody><tr>"+
 				"<td valign='top'><table cellspacing='0' cellpadding='0' width='100%' border='0' align='left' style='border-collapse:collapse'>"+
 				"<tbody><tr><td valign='top' style='text-align:left; padding-top:5px; padding-bottom:5px;'>"+
                 "<img src='https://www.mytdp.com/images/mytdp_logo.png'  width='100%'/>"+
 				"</td></tr><tr><td valign='top' style='text-align:center;'>"+
 				"</td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table>";
 		
 		return header;
 	}
	
	public ResultStatus updatePaymenntStatus(final Long userId,final String memberShipNo,final String AuthDesc, final String moduleStr,final String subTypeStr,final String enrollmentNumber){
		ResultStatus status = new ResultStatus();
		LOG.error("entered into  CCAVVENUE with \n time: "+new DateUtilService().getCurrentDateAndTimeInStringFormat()+", memberShipNo :"+memberShipNo+" ,enrollmentNumber:"+enrollmentNumber+",Payment AuthDesc: "+AuthDesc+" in updatePaymenntStatus() .");
		try {
			
			Long GlobalTdpCadreId = (Long) transactionTemplate.execute(new TransactionCallback() {
				public Object doInTransaction(TransactionStatus arg0) {
					Long  tdpCadreId = 0L;
					//List<Object[]> tdpCadreList = tdpCadreDAO.checkMemberPaymentExistsByTypeId(memberShipNo,IConstants.TGNF_REGISTRATION_CADRE_TYPE_ID,IConstants.UNIONS_REGISTRATION_YEAR);
					List<Object[]> tdpCadreList = tdpCadreDAO.checkMemberPaymentExistsByTypeId(memberShipNo,0L,IConstants.CADRE_ENROLLMENT_YEAR);
					if(tdpCadreList != null && tdpCadreList.size()>0){
						Object[] tdpCadrEObj = tdpCadreList.get(0);
						tdpCadreId = commonMethodsUtilService.getLongValueForObject(tdpCadrEObj[0]);
						String paymentStatusStr = commonMethodsUtilService.getStringValueForObject(tdpCadrEObj[2]);
						if(paymentStatusStr != null && paymentStatusStr.trim().equalsIgnoreCase(IConstants.PAID_STATUS)){
							;//sendSMSForAffliatedCadre(userId,commonMethodsUtilService.getStringValueForObject(tdpCadrEObj[1]).trim(), "Thanks for TNGF registration, your Membership ID  :"+memberShipNo);
						}
						else if(paymentStatusStr != null && paymentStatusStr.trim().equalsIgnoreCase(IConstants.NOT_PAID_STATUS)  && AuthDesc.equalsIgnoreCase("Y")){
							TdpCadre tdpCadre = tdpCadreDAO.get(tdpCadreId);
							if(tdpCadre != null){
								
								List<TdpCadreEnrollmentYear> tdpCadreEnrollmentYearList = tdpCadreEnrollmentYearDAO.getOnlineTdpCadreEnrollmentYearDetailsByTdpCadreId(tdpCadreId,"ONLINE");
								if(tdpCadreEnrollmentYearList != null && tdpCadreEnrollmentYearList.size() > 0){
									TdpCadreEnrollmentYear tdpCadreEnrollmentYear = tdpCadreEnrollmentYearList.get(0);
									tdpCadreEnrollmentYear.setIsDeleted("N");
									tdpCadreEnrollmentYear.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
									tdpCadreEnrollmentYearDAO.save(tdpCadreEnrollmentYear);
									
									saveDataToHistoryTable(tdpCadre);
									tdpCadre.setPayMentStatus(IConstants.PAID_STATUS);
									tdpCadre.setIsDeleted("N");
									tdpCadre.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
									tdpCadre.setUpdatedUserId(userId);
									tdpCadreDAO.save(tdpCadre);
									//sendSMSForAffliatedCadre(userId,commonMethodsUtilService.getStringValueForObject(tdpCadrEObj[1]).trim(), "Thanks for TNGF registration, your Membership ID  :"+memberShipNo);
									//System.out.println("TNGF Registration sms sent status :"+smsSentStatus);
									String mobileNoStr = tdpCadre.getMobileNo().trim();
									String mobileNo = mobileNoStr;
									if(mobileNoStr != null && mobileNoStr.length()>10)
										mobileNo = mobileNoStr.substring(mobileNoStr.length()-10, mobileNoStr.length());
									sendSMSInTelugu(mobileNo.trim(), getUniCodeMessage(StringEscapeUtils.unescapeJava("\u0C2A\u0C3E\u0C30\u0C4D\u0C1F\u0C40 \u0C38\u0C2D\u0C4D\u0C2F\u0C24\u0C4D\u0C35\u0C02 \u0C24\u0C40\u0C38\u0C41\u0C15\u0C41\u0C28\u0C4D\u0C28\u0C02\u0C26\u0C41\u0C15\u0C41 \u0C27\u0C28\u0C4D\u0C2F\u0C35\u0C3E\u0C26\u0C2E\u0C32\u0C41. ")+"Ref. No: "+enrollmentNumber));
									
									LOG.error(" Successfully SMS Sent  to TDPCADREID : "+tdpCadreId+" mobileno: "+tdpCadre.getMobileNo()+" with \n time: "+new DateUtilService().getCurrentDateAndTimeInStringFormat()+", memberShipNo :"+memberShipNo+" ,enrollmentNumber:"+enrollmentNumber+",Payment AuthDesc: "+AuthDesc+" in updatePaymenntStatus() .");
								}
							}
						}
						else{
							LOG.error(" CCAVVENUE Payment Failed SMS NOT Sent   with \n time: "+new DateUtilService().getCurrentDateAndTimeInStringFormat()+", memberShipNo :"+memberShipNo+" ,enrollmentNumber:"+enrollmentNumber+",Payment AuthDesc: "+AuthDesc+" in updatePaymenntStatus() .");
						}
					}
					else{
						LOG.error("No Cadre Details Found  with \n time: "+new DateUtilService().getCurrentDateAndTimeInStringFormat()+", memberShipNo :"+memberShipNo+" ,enrollmentNumber:"+enrollmentNumber+",Payment AuthDesc: "+AuthDesc+" in updatePaymenntStatus() .");
					}
					return tdpCadreId;
				}
			} );
			
			PaymentTransactionVO paymentTransactionVO = new PaymentTransactionVO();
			//paymentTransactionVO.setPaymentModuleGatewayMerchantDetailsId(1L);
			//paymentTransactionVO.setPaymentGatewayId(1L);
			//paymentTransactionVO.setPaymentMethodId(1L);
			paymentTransactionVO.setTransactionId("2016-2018_"+memberShipNo+"_TDPCADREID_"+GlobalTdpCadreId);
			paymentTransactionVO.setTransactionTime(dateUtilService.getCurrentDateAndTime());
			paymentTransactionVO.setUuid(String.valueOf(UUID.randomUUID()));
			paymentTransactionVO.setIpAddress(paymentTransactionVO.getIpAddress());
			paymentTransactionVO.setStatusCode(AuthDesc);// ccavvenue payment status 'Y', 'N' or null	
			paymentTransactionVO.setRedirectUrl(IConstants.CADRE_ONLINE_REGISTRATION_REDIRECTURL);
			paymentTransactionVO.setReferenceUserId("2016-2018_"+GlobalTdpCadreId);
		//	paymentTransactionVO.setPaymentModuleId(1L);
			paymentGatewayService.savePaymenyTransactionDetails(paymentTransactionVO);
			
			status.setResultCode(0);
			status.setMessage(IConstants.SUCCESS);
		} catch (Exception e) {
			LOG.error(" CCAVVENUE Exception occuredin  generating payment gateway basic details  with \n time: "+new DateUtilService().getCurrentDateAndTimeInStringFormat()+", memberShipNo :"+memberShipNo+" ,enrollmentNumber:"+enrollmentNumber+",Payment AuthDesc: "+AuthDesc+" in updatePaymenntStatus() .",e);
			status.setResultCode(1);
			status.setMessage(IConstants.FAILURE);
		}
		return status;
	}
	
	public String  checkPaymentStatus(String memberShipNo){
		String status = null;
		try {
			List<Object[]> tdpCadreList = tdpCadreDAO.checkMemberPaymentExistsByTypeId(memberShipNo,IConstants.TGNF_REGISTRATION_CADRE_TYPE_ID,IConstants.UNIONS_REGISTRATION_YEAR);
			if(tdpCadreList != null && tdpCadreList.size()>0){
				Object[] tdpCadrEObj = tdpCadreList.get(0);
				return commonMethodsUtilService.getStringValueForObject(tdpCadrEObj[2]);
			}
			
		} catch (Exception e) {
			status =IConstants.FAILURE;
			LOG.error("Exception riased at checkPaymentStatus in RtcUnionService Service class", e);
		}
		return status;
	}
	
	public String  updatePaymentStatus(final Long tdpCadreId){
		String status = "";
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					
					TdpCadre tdpCadre = tdpCadreDAO.get(tdpCadreId);
					saveDataToHistoryTable(tdpCadre);
					tdpCadre.setPayMentStatus(IConstants.PAID_STATUS);
					tdpCadre.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
					tdpCadreDAO.save(tdpCadre);
				}
			});
			
			status =IConstants.SUCCESS;
		} catch (Exception e) {
			status =IConstants.FAILURE;
			LOG.error("Exception riased at updatePaymentStatus in RtcUnionService Service class", e);
		}
		return status;
	}
	
	public List<IdAndNameVO> getStateWiseDistrict(Long stateId) {
		List<IdAndNameVO> districtList = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> alldistrictlist = districtDAO.getDistrictsForState(stateId);
			if (alldistrictlist != null && alldistrictlist.size() > 0) {
				for (Object[] objects : alldistrictlist) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(objects[0] != null ? (Long) objects[0] : 0l);
					vo.setName(objects[1] != null ? objects[1].toString() : "");
					districtList.add(vo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in getStateWiseConstituency() in CadreRegistrationService class", e);
		}
		return districtList;
	}
	
	public List<IdAndNameVO> getDistrictWiseConstituency(Long districtId) {
		List<IdAndNameVO> constituencyList = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> allConstituencylist = constituencyDAO.getDistrictWiseConstituency(districtId);
			if (allConstituencylist != null && allConstituencylist.size() > 0) {
				for (Object[] objects : allConstituencylist) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(objects[0] != null ? (Long) objects[0] : 0l);
					vo.setName(objects[1] != null ? objects[1].toString() : "");
					vo.setMobileNumber(objects[2] != null ? objects[2].toString() : "");
					constituencyList.add(vo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in getDistrictWiseConstituency() in CadreRegistrationService class", e);
		}
		return constituencyList;
	}
	public List<IdAndNameVO> getConstitencyWiseTehsil(Long constituencyId) {
		List<IdAndNameVO> tehsilList = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> allTehsilList = delimitationConstituencyMandalDetailsDAO.getConstitencyWiseTehsil(constituencyId);
			
			if (allTehsilList != null && allTehsilList.size() > 0) {
				for (Object[] objects : allTehsilList) {
					IdAndNameVO vo = new IdAndNameVO();
					  Long mandalId=Long.valueOf("1"+objects[0].toString());
					vo.setId(mandalId);
					vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1])+" Mandal");
					vo.setMobileNumber("RURAL");
					tehsilList.add(vo);

				}
			}
		List<Object[]> allTownsList = assemblyLocalElectionBodyDAO.getConstitencyWiseTowns(constituencyId);
		if(allTownsList!=null && allTownsList.size() > 0)
		{
			for(Object[] objects : allTownsList)
			{
				IdAndNameVO vo = new IdAndNameVO();
				 Long townId=Long.valueOf("2"+objects[0].toString());
				vo.setId(townId);
				vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1])+" Town" );
				vo.setMobileNumber("URBAN");
				tehsilList.add(vo);
			}
		}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			LOG.error("Exception raised in getConstitencyWiseTehsil() in WebServiceHandlerService class", e);
		}
		return tehsilList;
	}
	/*public CadreRegistrationVO getFamilyVoterDetails(Long voterId,CadreRegistrationVO vo)
	{
		List<CadreRegistrationVO> returnList = new ArrayList<CadreRegistrationVO>();
	  	try{
	  		String houseNo=null;
	  		Long boothId=null;
	  		List<Object[]> voterDetails = boothPublicationVoterDAO.getVoterDetails(voterId);
	  		if(voterDetails!=null && voterDetails.size()>0)
	  		{
	  			for(Object[] objects :voterDetails)
	  			{
	  				CadreRegistrationVO cadreRegistration = new CadreRegistrationVO();
	  				cadreRegistration.setTdpCadreId(objects[0] != null ? (Long) objects[0] : 0l);
	  				vo.setHouseNo(objects[1] != null ? objects[1].toString() : "");
	  				
	  			}
	  		}
	  		List<Object[]> familyVoterDetails = boothPublicationVoterDAO.getFamilyVoterDetails(boothId,houseNo);
	  		if(familyVoterDetails != null && familyVoterDetails.size() > 0){
	  			for (Object[] objects : familyVoterDetails) {
	  				CadreRegistrationVO cadreRegistration = new CadreRegistrationVO();
	  				cadreRegistration.setTdpCadreId(objects[0] != null ? (Long) objects[0] : 0l);
	  				vo.setHouseNo(objects[1] != null ? objects[1].toString() : "");
	  				vo.setRelativeName(objects[2] != null ? objects[2].toString() : "");
	  				vo.setVoterName(objects[3] != null ? objects[3].toString() : "");
	  				vo.setGender(objects[4] != null ? objects[4].toString() : "");
	  				vo.setAge(objects[5] != null ? (Long) objects[5] : 0l);
	  				returnList.add(vo);
	  			}
	  		}
	  	}
	  	catch(Exception e)
	  	{
	  		e.printStackTrace();
	  		LOG.error("Exception Occured in getFamilyVoterDetails() Method - Exception is : ",e);
	  	}
	  	return returnList;
	  	
		
	}*/
	
	 public List<IdAndNameVO> getPanchayatOrConsList(Long mandalOrMunpaId,String typeId){
			List<IdAndNameVO> retunList=new ArrayList<IdAndNameVO>();
			try{
				if(typeId.equalsIgnoreCase("1")){
					String subStrId=mandalOrMunpaId.toString().substring(0,1);
					if(subStrId.trim().equalsIgnoreCase("1")){
						List<Object[]> panchList=panchayatDAO.getPanchayatList(Long.valueOf(mandalOrMunpaId.toString().substring(1)));
						for (Object[] objects : panchList) {
							IdAndNameVO idAndNameVO =new IdAndNameVO();
							
							idAndNameVO.setId(objects[0]!=null?(Long)objects[0]:0l);
							idAndNameVO.setName(objects[1]!=null?objects[1].toString():"");
							retunList.add(idAndNameVO);
						}
					}
					if(subStrId.trim().equalsIgnoreCase("2")){
						List<Object[]> consiList=boothDAO.getboothList(Long.valueOf(mandalOrMunpaId.toString().substring(1)));
						for (Object[] objects : consiList) {
							IdAndNameVO  idAndNameVO=new IdAndNameVO();
							idAndNameVO.setId(objects[0]!=null?(Long)objects[0]:0l);
							idAndNameVO.setName(objects[1]!=null?objects[1].toString():"");//partNo
							idAndNameVO.setName("Booth NO: "+idAndNameVO.getName());
							retunList.add(idAndNameVO);
						}
					}
				}else if(typeId.equalsIgnoreCase("2") || typeId.equalsIgnoreCase("3")){
					String subStrId=mandalOrMunpaId.toString().substring(0,1);
					
					 List<Object[]> wardsList = null;
					 if(subStrId.trim().equalsIgnoreCase("2")){
						 wardsList = assemblyLocalElectionBodyWardDAO.findByLocalElectionBody(Long.valueOf(mandalOrMunpaId.toString().substring(1)),IConstants.DELIMITATION_YEAR.toString());
						 if(commonMethodsUtilService.isListOrSetValid(wardsList)){
							 List<PaymentGatewayVO> wardsListDtls = new ArrayList<PaymentGatewayVO>(0);
							 //PaymentGatewayVO vo1 = new PaymentGatewayVO();
							 for (Object[] param : wardsList) {
								 IdAndNameVO  vo=new IdAndNameVO();
								 vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
								 vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
								 
								 String[] strArr = vo.getName().split("-");
								 String wardName = "WARD-";
								 if(strArr != null && strArr.length>0){
									 int length = strArr[1].toString().length();
									 if(length ==1)
										 wardName = wardName+"00"+strArr[1].toString();
									 else if(length ==2)
										 wardName = wardName+"0"+strArr[1].toString();
									 vo.setMobileNumber(wardName);
								 }
								 if(!commonMethodsUtilService.getStringValueForObject(param[2]).isEmpty())
									 vo.setName(vo.getName()+"- ("+commonMethodsUtilService.getStringValueForObject(param[2])+")");
								 retunList.add(vo);
							}
							 
							 	if(commonMethodsUtilService.isListOrSetValid(retunList)){
								 
								 Collections.sort(retunList, new Comparator<IdAndNameVO>() {
									public int compare(IdAndNameVO o1,IdAndNameVO o2) {
										return o1.getMobileNumber().compareTo(o2.getMobileNumber());
									}
								});
							 }
						 }
					 }
					 else if(wardsList == null || wardsList.size()==0) {
						 
							if(subStrId.trim().equalsIgnoreCase("1")){
								List<Object[]> panchList=panchayatDAO.getPanchayatList(Long.valueOf(mandalOrMunpaId.toString().substring(1)));
								for (Object[] objects : panchList) {
									IdAndNameVO idAndNameVO =new IdAndNameVO();
									
									idAndNameVO.setId(objects[0]!=null?(Long)objects[0]:0l);
									idAndNameVO.setName(objects[1]!=null?objects[1].toString():"");
									retunList.add(idAndNameVO);
								}
							}else{
								retunList.add(new IdAndNameVO(9999l,"OTHER WARD"));
							}
							/*if(subStrId.trim().equalsIgnoreCase("2")){
								List<Object[]> consiList=boothDAO.getboothList(Long.valueOf(mandalOrMunpaId.toString().substring(1)));
								for (Object[] objects : consiList) {
									IdAndNameVO  idAndNameVO=new IdAndNameVO();
									idAndNameVO.setId(objects[0]!=null?(Long)objects[0]:0l);
									idAndNameVO.setName(objects[1]!=null?objects[1].toString():"");//partNo
									idAndNameVO.setName("Booth NO: "+idAndNameVO.getName());
									retunList.add(idAndNameVO);
								}
							}
							*/
					 }
				}
			}catch(Exception e){
				 LOG.error("Error occured at getPanchayatOrConsList() in CadreRegistrationService {}",e);
				
			}
			return retunList;
			
		}
	 
	 public List<IdAndNameVO> getBoothsList(Long panchayatId){
		  List<IdAndNameVO> retunBoothList=new ArrayList<IdAndNameVO>();
		  try{
				  List<Object[]> boothListForPan=boothDAO.getBoothListFrPanchayat(panchayatId);
				  for (Object[] objects : boothListForPan) {
					  IdAndNameVO idAndNameVO=new IdAndNameVO();
					  idAndNameVO.setId(objects[0]!=null?(Long)objects[0]:0l);
					  idAndNameVO.setName(objects[1]!=null?objects[1].toString():"");
					  idAndNameVO.setName("Booth No:"+idAndNameVO.getName());
					retunBoothList.add(idAndNameVO);
				}
			
		  }catch(Exception e){
			  LOG.error("Error occured at getBoothsList() in WebServiceHandlerService {}",e); 
		  }
		  return retunBoothList;

}


	public List<VoterSearchVO> getVotersBySearch(Long constituencyId,Long mandalId,Long villageId,Long boothId,String name,String hNo,String voterCardNo,Long cadreSurveyUserId){
		List<VoterSearchVO> returnList = new ArrayList<VoterSearchVO>();
		try {
			Map<Long,IdAndNameVO> voterCadreMap = new LinkedHashMap<Long, IdAndNameVO>();
			List<Long> voterIds = new ArrayList<Long>();
			String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			String searchType = "";
			Long searchVal = 0l;
			
			if(boothId != null && boothId.longValue() > 0l){
				searchVal = boothId;
				searchType = "booth";
			}
			else if(villageId != null && villageId.longValue() > 0l){
				searchVal = villageId;
				searchType = "village";
			}
			else if(mandalId != null && mandalId.longValue() > 0l){
				Long id = Long.valueOf(mandalId.toString().substring(0, 1));
				if(id != null && id.longValue() == 1l){
					searchVal = Long.valueOf(mandalId.toString().substring(1));
					searchType = "mandal";
				}
				else if(id != null && id.longValue() == 2l){
					searchVal = Long.valueOf(mandalId.toString().substring(1));
					searchType = "munci";
				}
			}
			else{
				searchVal = constituencyId;
				searchType = "const";
			}
			
			List<Long> constituencyIds =  null;
			List<Long> assignedBoothIds =  null;
			if(cadreSurveyUserId != null && cadreSurveyUserId.longValue()>0L){
				constituencyIds = cadreSurveyUserAssignDetailsDAO.getConstituencyIdByUserId(cadreSurveyUserId);
				assignedBoothIds = cadreRegistrationAllowAreasDAO.getAssignedBoothDetailsInAssemblyList(constituencyIds);
			} 
			
			List<Object[]> list1 = boothPublicationVoterDAO.getVotersBySearch(searchVal, searchType, name, hNo, voterCardNo,assignedBoothIds);
			if(commonMethodsUtilService.isListOrSetValid(list1)){
				for (Object[] obj : list1) {
					VoterSearchVO vo = new VoterSearchVO();
					Long voterId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					
					vo.setVoterId(voterId.toString());
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setRelationshipType(obj[2] != null ? obj[2].toString():"");
					vo.setRelativeName(obj[3] != null ? obj[3].toString():"");
					vo.setGender(obj[4] != null ? obj[4].toString():"");
					vo.setAge(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
					vo.setVoterIDCardNo(obj[6] != null ? obj[6].toString():"");
					//vo.setMobileNo(obj[7] != null ? obj[7].toString():"");
					vo.setImagePath(IConstants.VOTER_IMG_FOLDER_PATH+"/"+(obj[7] != null ? obj[7].toString():""));
					vo.setHouseNo(obj[8] != null ? obj[8].toString():"");
					vo.setTotalImagePathStr("http://mytdp.com/"+vo.getImagePath());
					returnList.add(vo);
					voterIds.add(voterId);
				}
				
				List<Object[]> list = boothPublicationVoterDAO.getRegisteredCadresForVoterIds(voterIds);
				if(commonMethodsUtilService.isListOrSetValid(list)){
					for (Object[] obj : list) {
						Long vId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						IdAndNameVO vo = voterCadreMap.get(vId);
						if(vo == null){
							vo = new IdAndNameVO();
							vo.setId(vId);
							vo.setAttenteeCount(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));  //tdpCadreId
							vo.setName(obj[2] != null ? obj[2].toString():"");     //MemberShipNo
							
							vo.setMobileNumber(obj[3] != null ? obj[3].toString().trim():"" );
							vo.setInviteeAttendeeCnt(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
							vo.setImagePathStr("http://mytdp.com/images/cadre_images/"+(obj[5] != null ? obj[5].toString():""));
							vo.setIsCsd(obj[6] != null ? obj[6].toString():"");
							if(vo.getInviteeAttendeeCnt() == 3l)
								vo.getEnrollmentYears().add("2014-2016");
							else if(vo.getInviteeAttendeeCnt() == 4l)
								vo.getEnrollmentYears().add("2016-2018");
							voterCadreMap.put(vId, vo);
						}
						else{
							Long enrid = Long.valueOf(obj[4] != null ? obj[4].toString():"0");
							if(enrid.longValue() > vo.getInviteeAttendeeCnt().longValue())
								vo.setInviteeAttendeeCnt(enrid);
							if(vo.getInviteeAttendeeCnt() == 3l)
								vo.getEnrollmentYears().add("2014-2016");
							else if(vo.getInviteeAttendeeCnt() == 4l)
								vo.getEnrollmentYears().add("2016-2018");
							vo.setIsCsd(obj[6] != null ? obj[6].toString():"");
						}
					}
				}
				
				if(commonMethodsUtilService.isListOrSetValid(returnList)){
					for (VoterSearchVO voterVO : returnList) {
						Long voterId = Long.valueOf(voterVO.getVoterId().toString());
						IdAndNameVO vo = voterCadreMap.get(voterId);
						if(vo != null){
							voterVO.setTdpCadreId(vo.getAttenteeCount());
							voterVO.setMemberShipNo(vo.getName());
							voterVO.setEnrollmentYearId(vo.getInviteeAttendeeCnt());
							voterVO.setTotalImagePathStr(vo.getImagePathStr());
							voterVO.setMobileNumber(vo.getMobileNumber());
							voterVO.setIsCsd(vo.getIsCsd());
							voterVO.getEnrollmentYearList().addAll(vo.getEnrollmentYears());
						}
					}
				}
				
				if(commonMethodsUtilService.isListOrSetValid(returnList)){
					for (VoterSearchVO vo : returnList) {
						if(vo.getIsCsd() != null && vo.getIsCsd().trim().equalsIgnoreCase("Y"))
							vo.setEnrollmentYearId(3l);
					}
				}
				
				if(commonMethodsUtilService.isListOrSetValid(returnList)){
					for (VoterSearchVO voterSearchVO : returnList) {
						if(voterSearchVO.getTdpCadreId() != null && voterSearchVO.getTdpCadreId().longValue() > 0l){
							if(voterSearchVO.getEnrollmentYearId() != null && voterSearchVO.getEnrollmentYearId().longValue() == 3l)
								voterSearchVO.setStatus("renewal");
							else if(voterSearchVO.getEnrollmentYearId() != null && voterSearchVO.getEnrollmentYearId().longValue() == 4l)
								voterSearchVO.setStatus("update");
						}
						else
							voterSearchVO.setStatus("new");
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception riased at getVotersBySearch in CadreRegistrationService Service class", e);
		}
		return returnList;
	}
	
	public List<TdpCadreVO> getTdpCadresBySearch(Long cadreSurveyUserId,String membershipNo,String mobileNo,String voterId){
		List<TdpCadreVO> returnList = new ArrayList<TdpCadreVO>();
		try {
			String searchType = "";
			Map<Long,TdpCadreVO> cadreMap = new LinkedHashMap<Long, TdpCadreVO>();
			
			if(membershipNo != null && !membershipNo.trim().equalsIgnoreCase("0") && membershipNo.trim().length() > 0)
				searchType = "memberShip";
			else if(mobileNo != null && !mobileNo.trim().equalsIgnoreCase("0") && mobileNo.trim().length() > 0)
				searchType = "mobile";
			else if(voterId != null && !voterId.trim().equalsIgnoreCase("0") && voterId.trim().length() > 0)
				searchType = "voter";
			List<Long> constituencyIdsList = null;
			List<Long> assignedBoothIds =  null;
			if(cadreSurveyUserId!= null && cadreSurveyUserId.longValue()>0L){
				constituencyIdsList = cadreSurveyUserAssignDetailsDAO.getAssignedConstiteuncyListByUserId(cadreSurveyUserId);
				if(!commonMethodsUtilService.isListOrSetValid(constituencyIdsList)){
					TdpCadreVO tdpCadreVO = new TdpCadreVO();
					tdpCadreVO.setStatus("noaccess");
					returnList = new ArrayList<TdpCadreVO>(0);
					returnList.add(tdpCadreVO);
					return returnList;
				}
				if(constituencyIdsList != null && constituencyIdsList.size()>0){//if booths access for cadresurveyuser
					assignedBoothIds = cadreRegistrationAllowAreasDAO.getAssignedBoothDetailsInAssemblyList(constituencyIdsList);
				}
			}
				
			List<Object[]> list = tdpCadreDAO.getTdpCadreDetailsBySearch(constituencyIdsList, searchType, membershipNo, mobileNo, voterId,null);
			
			List<Long> voterIdsList = new ArrayList<Long>(0);
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] obj : list) {
					Long cadreId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					TdpCadreVO vo = cadreMap.get(cadreId);
					if(vo == null){
						vo = new TdpCadreVO();
						String moblStr=obj[7]!=null?obj[7].toString().trim():"";
						String finalMblStr = "";
						if(moblStr != null && moblStr.trim().length() > 0){
							Character[] array = new Character[moblStr.trim().length()];
							for (int i = 0; i < moblStr.length() ; i++) {
						      array[i] = new Character(moblStr.charAt(i));
						      if(i==2 || i==3 || i == 5 || i==6 || i==7)
						    	  finalMblStr = finalMblStr+"*";
						      else
						    	  finalMblStr = finalMblStr+array[i].toString();
						   }
						}
						
						vo.setId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.setMemberShipNo(obj[1] != null ? obj[1].toString():"");
						vo.setName(obj[2] != null ? obj[2].toString():"");
						vo.setRelativeName(obj[3] != null ? obj[3].toString():"");
						vo.setRelativeType(obj[4] != null ? obj[4].toString():"");
						vo.setHouseNo(obj[5] != null ? obj[5].toString():"");
						vo.setImageURL("images/"+ IConstants.CADRE_IMAGES + "/" +(obj[6] != null ? obj[6].toString():""));
						vo.setMobileNo(obj[7] != null ? obj[7].toString():"");//actual mobile number
						vo.setOccupation(finalMblStr);//encrypted mobile number
						vo.setGender(obj[8] != null ? obj[8].toString():"");
						vo.setAge(Long.valueOf(obj[9] != null ? obj[9].toString():"0"));
						vo.setVoterId(Long.valueOf(obj[10] != null ? obj[10].toString():"0"));
						vo.setVoterCardNo(obj[11] != null ? obj[11].toString():"");
						vo.setFamilyVoterId(Long.valueOf(obj[12] != null ? obj[12].toString():"0"));
						vo.setFamilyVoterCardNo(obj[13] != null ? obj[13].toString():"");
						vo.setEnrollmentYearId(Long.valueOf(obj[14] != null ? obj[14].toString():"0"));
						vo.setIsCsd(obj[15] != null ? obj[15].toString():"");
						vo.setTotalImagePathStr("http://mytdp.com/"+vo.getImageURL());
						
						if(vo.getEnrollmentYearId() == 3l)
							vo.getEnrollmentYearList().add("2014-2016");
						else if(vo.getEnrollmentYearId() == 4l)
							vo.getEnrollmentYearList().add("2016-2018");
						
						voterIdsList.add(commonMethodsUtilService.getLongValueForObject(obj[10]));
						voterIdsList.add(commonMethodsUtilService.getLongValueForObject(obj[12]));
						cadreMap.put(cadreId, vo);
					}
					else{
						vo.setEnrollmentYearId(Long.valueOf(obj[14] != null ? obj[14].toString():"0"));
						vo.setIsCsd(obj[15] != null ? obj[15].toString():"");
						
						if(vo.getEnrollmentYearId() == 3l)
							vo.getEnrollmentYearList().add("2014-2016");
						else if(vo.getEnrollmentYearId() == 4l)
							vo.getEnrollmentYearList().add("2016-2018");
						
						voterIdsList.add(commonMethodsUtilService.getLongValueForObject(obj[10]));
						voterIdsList.add(commonMethodsUtilService.getLongValueForObject(obj[12]));
						
					}
				}
			}
			
			if(cadreMap != null)
				returnList = new ArrayList<TdpCadreVO>(cadreMap.values());
			
			if(returnList != null && !returnList.isEmpty()){
				for (TdpCadreVO vo : returnList) {
					if(vo.getIsCsd() != null && vo.getIsCsd().trim().equalsIgnoreCase("Y"))
						vo.setEnrollmentYearId(3l);
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(returnList)){
				for (TdpCadreVO tdpCadreVO : returnList) {
					if(tdpCadreVO.getId() != null && tdpCadreVO.getId().longValue() > 0l && tdpCadreVO.getEnrollmentYearId() != null && tdpCadreVO.getEnrollmentYearId().longValue() == 3l)
						tdpCadreVO.setStatus("renewal");
					else if(tdpCadreVO.getId() != null && tdpCadreVO.getId().longValue() > 0l && tdpCadreVO.getEnrollmentYearId() != null && tdpCadreVO.getEnrollmentYearId().longValue() == 4l)
						tdpCadreVO.setStatus("update");
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(assignedBoothIds)){//if booths access for cadresurveyuser
				if(!commonMethodsUtilService.isListOrSetValid(voterIdsList))
					returnList.clear();
				else{
					List<Object[]> getBoothIdsOfVoterIds = boothPublicationVoterDAO.getBoothsOfVoterIds(voterIdsList, IConstants.VOTER_DATA_PUBLICATION_ID);
					Map<Long,Long> voterBoothsMap = new HashMap<Long, Long>(0);
					if(commonMethodsUtilService.isListOrSetValid(getBoothIdsOfVoterIds)){
						for (Object[] param : getBoothIdsOfVoterIds) {
							voterBoothsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[1]));
						}
					}
					
					List<TdpCadreVO> finalList = new ArrayList<TdpCadreVO>(0);
					if(commonMethodsUtilService.isListOrSetValid(returnList)){
						for (TdpCadreVO vo : returnList) {
							Long boothId = voterBoothsMap.get(vo.getVoterId()) != null?voterBoothsMap.get(vo.getVoterId()):voterBoothsMap.get(vo.getFamilyVoterId());
							if(assignedBoothIds.contains(boothId))
								finalList.add(vo);
						}
					}
					returnList.clear();
					returnList.addAll(finalList);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception riased at getTdpCadresBySearch in CadreRegistrationService Service class", e);
		}
		return returnList;
	}
	public List<IdAndNameVO> getStateWiseConstituency() {
		List<IdAndNameVO> consList = new ArrayList<IdAndNameVO>();
		try {
			List<Object[]> allConslist = constituencyDAO.getStateWiseConstituency();
			if (allConslist != null && allConslist.size() > 0) {
				for (Object[] objects : allConslist) {
					IdAndNameVO vo = new IdAndNameVO();
					vo.setId(objects[0] != null ? (Long) objects[0] : 0l);
					vo.setName(objects[1] != null ? objects[1].toString() : "");
					consList.add(vo);
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised in getStateWiseConstituency() in WebServiceHandlerService class",e);
		}
		return consList;
	}
	
	
	public List<VoterSearchVO> getOnlineCadreRegistrationVoterDetails(String voterCardNo){
		List<VoterSearchVO> returnList = new ArrayList<VoterSearchVO>();
		try {
			Map<Long,IdAndNameVO> voterCadreMap = new LinkedHashMap<Long, IdAndNameVO>();
			List<Long> voterIds = new ArrayList<Long>();
			
			/*List<Object[]> list1 = voterDAO.getOnlineCadreRegistrationVoterDetails(voterCardNo);
			 if(commonMethodsUtilService.isListOrSetValid(list1)){
				for (Object[] obj : list1) {
					VoterSearchVO vo = new VoterSearchVO();
					Long voterId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					vo.setVoterId(voterId.toString());
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setRelativeName(obj[2] != null ? obj[2].toString():"");
					vo.setRelationshipType(obj[3] != null ? obj[3].toString():"");
					vo.setGender(obj[4] != null ? obj[4].toString():"");
					vo.setAge(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
					vo.setVoterIDCardNo(obj[6] != null ? obj[6].toString():"");
					vo.setHouseNo(obj[7] != null ? obj[7].toString():"");
					returnList.add(vo);
					voterIds.add(voterId);
				}
			 */
			Long publDateId = boothPublicationVoterDAO.getPublicationDateIdByVoterIDCardNo(voterCardNo);
			if(publDateId != null && publDateId.longValue() > 0l){
				List<Object[]> list1 = boothPublicationVoterDAO.getVoterIdDetailsByPublicationIdAndCardNo(voterCardNo,publDateId);
				if(commonMethodsUtilService.isListOrSetValid(list1)){
					for (Object[] obj : list1) {
						VoterSearchVO vo = new VoterSearchVO();
						Long voterId = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
						vo.setVoterId(voterId.toString());
						vo.setName(obj[4] != null ? obj[4].toString():"");
						vo.setAge(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
						vo.setGender(obj[6] != null ? obj[6].toString():"");
						vo.setRelativeName(obj[8] != null ? obj[8].toString():"");
						vo.setHouseNo(obj[9] != null ? obj[9].toString():"");
						vo.setRelationshipType(obj[10] != null ? obj[10].toString():"");
						vo.setVoterIDCardNo(voterCardNo);					
						returnList.add(vo);
						voterIds.add(voterId);
					}
					
					
					List<Object[]> list = voterDAO.getRegisteredCadresForVoterIds(voterIds);
					if(commonMethodsUtilService.isListOrSetValid(list)){
						for (Object[] obj : list) {
							Long vId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
							IdAndNameVO vo = voterCadreMap.get(vId);
							if(vo == null){
								vo = new IdAndNameVO();
								
								String moblStr=obj[3]!=null?obj[3].toString().trim():"";
								String finalMblStr = "";
								if(moblStr != null && moblStr.trim().length() > 0){
									Character[] array = new Character[moblStr.trim().length()];
									for (int i = 0; i < moblStr.length() ; i++) {
								      array[i] = new Character(moblStr.charAt(i));
								      if(i==2 || i==3 ||i == 5 || i==6 || i==7)
								    	  finalMblStr = finalMblStr+"*";
								      else
								    	  finalMblStr = finalMblStr+array[i].toString();
								   }
								}
								
								vo.setId(vId);
								vo.setAttenteeCount(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));  //tdpCadreId
								vo.setName(obj[2] != null ? obj[2].toString():"");     //MemberShipNo
								vo.setMobileNumber(finalMblStr);
								vo.setActualMobNumber(obj[3] != null ? obj[3].toString():"");
								vo.setInviteeAttendeeCnt(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
								voterCadreMap.put(vId,vo);
							}
							else{
								Long enrid = Long.valueOf(obj[4] != null ? obj[4].toString():"0");
								if(enrid.longValue() > vo.getInviteeAttendeeCnt().longValue())
									vo.setInviteeAttendeeCnt(enrid);
							}
						}
					}
					
					if(commonMethodsUtilService.isListOrSetValid(returnList)){
						for (VoterSearchVO voterVO : returnList) {
							Long voterId = Long.valueOf(voterVO.getVoterId().toString());
							IdAndNameVO vo = voterCadreMap.get(voterId);
							if(vo != null){
								voterVO.setTdpCadreId(vo.getAttenteeCount());
								voterVO.setMemberShipNo(vo.getName());
								voterVO.setEnrollmentYearId(vo.getInviteeAttendeeCnt());
								voterVO.setMobileNumber(vo.getMobileNumber());
								voterVO.setActualMobiNumber(vo.getActualMobNumber());
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception riased at getOnlineCadreRegistrationVoterDetails in CadreRegistrationService Service class", e);
		}
		return returnList;
	}	
	
	public List<VoterSearchVO> getOnliCadRegistrSearchVoteDetails(Long constituencyId,Long mandalId,Long villageId,Long boothId, String type, String typeVal){
		List<VoterSearchVO> returnList = new ArrayList<VoterSearchVO>();
		try {
			Map<Long,IdAndNameVO> voterCadreMap = new LinkedHashMap<Long, IdAndNameVO>();
			List<Long> voterIds = new ArrayList<Long>();
			String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			String searchType = "";
			Long searchVal = 0l;
			
			if(boothId != null && boothId.longValue() > 0l){
				searchVal = boothId;
				searchType = "booth";
			}
			else if(villageId != null && villageId.longValue() > 0l){
				searchVal = villageId;
				searchType = "village";
			}
			else if(mandalId != null && mandalId.longValue() > 0l){
				Long id = Long.valueOf(mandalId.toString().substring(0, 1));
				if(id != null && id.longValue() == 1l){
					searchVal = Long.valueOf(mandalId.toString().substring(1));
					searchType = "mandal";
				}
				else if(id != null && id.longValue() == 2l){
					searchVal = Long.valueOf(mandalId.toString().substring(1));
					searchType = "munci";
				}
			}
			else{
				searchVal = constituencyId;
				searchType = "const";
			}
			
			List<Object[]> list1 = boothPublicationVoterDAO.getOnliCadRegistrSearchVoteDetails(searchVal, searchType, type, typeVal);
			if(commonMethodsUtilService.isListOrSetValid(list1)){
				for (Object[] obj : list1) {
					VoterSearchVO vo = new VoterSearchVO();
					Long voterId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
					vo.setVoterId(voterId.toString());
					vo.setName(obj[1] != null ? obj[1].toString():"");
					vo.setRelationshipType(obj[2] != null ? obj[2].toString():"");
					vo.setRelativeName(obj[3] != null ? obj[3].toString():"");
					vo.setGender(obj[4] != null ? obj[4].toString():"");
					vo.setAge(Long.valueOf(obj[5] != null ? obj[5].toString():"0"));
					vo.setVoterIDCardNo(obj[6] != null ? obj[6].toString():"");
					vo.setHouseNo(obj[7] != null ? obj[7].toString():"");
					vo.setTotalImagePathStr(obj[8] != null ? obj[8].toString():"");// newly added 
					returnList.add(vo);
					voterIds.add(voterId);
				}
				
				List<Object[]> list = boothPublicationVoterDAO.getRegisteredCadresForVoterIds(voterIds);
				if(commonMethodsUtilService.isListOrSetValid(list)){
					for (Object[] obj : list) {
						Long vId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						IdAndNameVO vo = voterCadreMap.get(vId);
						if(vo == null){
							vo = new IdAndNameVO();
							
							String moblStr=obj[3]!=null?obj[3].toString().trim():"";
							String finalMblStr = "";
							if(moblStr != null && moblStr.trim().length() > 0){
								Character[] array = new Character[moblStr.trim().length()];
								for (int i = 0; i < moblStr.length() ; i++) {
							      array[i] = new Character(moblStr.charAt(i));
							      if(i==2 || i==3 || i == 5 || i==6 || i==7)
							    	  finalMblStr = finalMblStr+"*";
							      else
							    	  finalMblStr = finalMblStr+array[i].toString();
							   }
							}
							
							vo.setId(vId);
							vo.setAttenteeCount(Long.valueOf(obj[1] != null ? obj[1].toString():"0"));  //tdpCadreId
							vo.setName(obj[2] != null ? obj[2].toString():"");     //MemberShipNo
							vo.setMobileNumber(finalMblStr);
							vo.setActualMobNumber(obj[3] != null ? obj[3].toString():"");
							vo.setInviteeAttendeeCnt(Long.valueOf(obj[4] != null ? obj[4].toString():"0"));
							vo.setImagePathStr(obj[5] != null ? obj[5].toString():""); // newly added 
							voterCadreMap.put(vId,vo);
						}
						else{
							Long enrid = Long.valueOf(obj[4] != null ? obj[4].toString():"0");
							if(enrid.longValue() > vo.getInviteeAttendeeCnt().longValue())
								vo.setInviteeAttendeeCnt(enrid);
						}
					}
				}
				
				if(commonMethodsUtilService.isListOrSetValid(returnList)){
					for (VoterSearchVO voterVO : returnList) {
						Long voterId = Long.valueOf(voterVO.getVoterId().toString());
						IdAndNameVO vo = voterCadreMap.get(voterId);
						if(vo != null){
							voterVO.setTdpCadreId(vo.getAttenteeCount());
							voterVO.setMemberShipNo(vo.getName());
							voterVO.setEnrollmentYearId(vo.getInviteeAttendeeCnt());
							voterVO.setTotalImagePathStr(vo.getImagePathStr());
							voterVO.setMobileNumber(vo.getMobileNumber());
						    voterVO.setActualMobiNumber(vo.getActualMobNumber());
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception riased at getOnliCadRegistrSearchVoteDetails in CadreRegistrationService Service class", e);
		}
		return returnList;
	}
	
	public List<KeyValueVO> getStateWiseAssemblyConstituency(Long stateId){
		List<KeyValueVO> finalList = new ArrayList<KeyValueVO>();
		try{			
			List<Object[]> constObj = constituencyDAO.getStateWiseAssemblyConstituency(stateId);
			if(constObj !=null && constObj.size()>0l){				
				for (Object[] obj : constObj) {
					KeyValueVO VO = new KeyValueVO();
					
					VO.setId(obj[0] !=null ? (Long)obj[0]:0l);
					VO.setName(obj[1] !=null ? obj[1].toString():"");
					
					finalList.add(VO);
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception riased at getStateWiseAssemblyConstituency in CadreRegistrationService Service class", e);
		}
		return finalList;
	}
	
	public List<GISUserTrackingVO> getLatestLattitudeLangitudeOfTabUser(GISUserTrackingVO inputVO){
		List<GISUserTrackingVO> finalList = new ArrayList<GISUserTrackingVO>();
		try{			
			
			SimpleDateFormat date = new SimpleDateFormat("MM-dd-yyyy");
			
			Date startDate=null;
			Date endDate =null;
			
			if(inputVO.getStartDate() !=null && !inputVO.getStartDate().trim().isEmpty() && inputVO.getEndDate() !=null && !inputVO.getEndDate().trim().isEmpty()){
				
				startDate = date.parse(inputVO.getStartDate());
				endDate   = date.parse(inputVO.getEndDate());
			}
			else{
				startDate = new DateUtilService().getCurrentDateAndTime();
				endDate = new DateUtilService().getCurrentDateAndTime();
			}
			//0.tabUserId,1.name,2.mobileNo,3.lattitude,4.longitude,5.surveyTime
			List<Object[]> latestOb1j = tdpCadreDAO.getLatestLattitudeLangitudeOfTabUser(inputVO.getId(),startDate,endDate);
			List<Long> tdpCadreIdsList = new ArrayList<Long>(0);
			if(latestOb1j !=null && latestOb1j.size()>0){
				for(Object[] obj : latestOb1j) {		
					tdpCadreIdsList.add(obj[0] !=null ? (Long)obj[0]:0l);
				}
				
				List<Object[]> latestObj =  tdpCadreDAO.getTabUserInfoDetailsByTdpCadreIds(tdpCadreIdsList);
				if(latestObj !=null && latestObj.size()>0){
					for(Object[] obj : latestObj) {	
						GISUserTrackingVO Vo= new GISUserTrackingVO();
						
						Vo.setId(obj[0] !=null ? (Long)obj[0]:0l);
						Vo.setName(obj[1] !=null ? obj[1].toString():"");
						Vo.setMobileNo(obj[2] !=null ? obj[2].toString():"");
						Vo.setLattitude(obj[3] !=null ? obj[3].toString():"");
						Vo.setLongitude(obj[4] !=null ? obj[4].toString():"");
						Vo.setSurveyTime(obj[5] !=null ? obj[5].toString():"");
						
						finalList.add(Vo);
					}
				}
			}			
			
		}catch(Exception e){
			LOG.error("Exception riased at getLatestLattitudeLangitudeOfTabUser in CadreRegistrationService class", e);
		}
		return finalList;
	}
		
	public ResultStatus syncCadreTabRecordsStatus(List<CadreTabRecordsStatusVO> cadreTabRecordsStatusList){
		
		ResultStatus result = new ResultStatus();
		
		try{
			
			//SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(cadreTabRecordsStatusList !=null && cadreTabRecordsStatusList.size()>0){
				for (CadreTabRecordsStatusVO tabVO : cadreTabRecordsStatusList) {	
					cadreTabRecordsStatusDAO.deleteExstngCadreTdpRecords(tabVO.getCadreSurveyUserId(),tabVO.getTabUserInfoId(),sdf.parse(tabVO.getSurveyDate()));
					
					CadreTabRecordsStatus model = new CadreTabRecordsStatus();	
					
					model.setCadreSurveyUserId(tabVO.getCadreSurveyUserId());
					model.setTabUserInfoId(tabVO.getTabUserInfoId());
					if(tabVO.getImeiNo() !=null && !tabVO.getImeiNo().trim().isEmpty())
						model.setImeiNo(tabVO.getImeiNo());
					if(tabVO.getLattitude() !=null && !tabVO.getLattitude().trim().isEmpty())
						model.setLattitude(tabVO.getLattitude());
					if(tabVO.getLongittude() !=null && !tabVO.getLongittude().trim().isEmpty())
						model.setLongitude(tabVO.getLongittude());
					model.setTotalRecords(tabVO.getTotalRecords() !=null ? tabVO.getTotalRecords() :0l);
					model.setSync(tabVO.getSync() !=null ? tabVO.getSync():0l);
					model.setPending(tabVO.getPending() !=null ? tabVO.getPending() :0l);
					model.setIsDeleted("N");
					
					
					model.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					model.setKafkaPending(tabVO.getKafkaPending() !=null ? tabVO.getKafkaPending() :0l);
					model.setKafkaSync(tabVO.getKafkaSync() !=null ? tabVO.getKafkaSync() :0l);
					if(tabVO.getSurveyDate() !=null && !tabVO.getSurveyDate().trim().isEmpty())
						model.setSurveyDate(tabVO.getSurveyDate() !=null ? sdf.parse(tabVO.getSurveyDate()):null);
					if(tabVO.getMinRecordTime() != null && !tabVO.getMinRecordTime().trim().isEmpty())
						model.setMinRecordTime(tabVO.getMinRecordTime() !=null ? tabVO.getMinRecordTime():null);
					if(tabVO.getMaxRecordTime() != null && !tabVO.getMaxRecordTime().trim().isEmpty())
						model.setMaxRecordTime(tabVO.getMaxRecordTime() !=null ? tabVO.getMaxRecordTime():null);
					
					cadreTabRecordsStatusDAO.save(model);
					
					result.setExceptionMsg("success");
					result.setResultCode(0);
				}
			}
			
		}catch(Exception e){			
			LOG.error("Exception riased at syncCadreTabRecordsStatus in CadreRegistrationService Service class", e);
			result.setExceptionMsg("failre");
			result.setResultCode(1);
		}
		return result;
	}
	public List<GISUserTrackingVO> getLatestLattitudeLangitudeOfTabUserAgentDetails(Long constituencyId,String startDate,String endDate){
		List<GISUserTrackingVO> finalList = new ArrayList<GISUserTrackingVO>();
		try{			
			
			Date fromDate=null;
			Date toDate=null;
			
			if(startDate !=null && endDate !=null){
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				fromDate = sdf.parse(startDate);
				 toDate  =sdf.parse(endDate);
			}else{
				fromDate = new DateUtilService().getCurrentDateAndTime();
				 toDate  =new DateUtilService().getCurrentDateAndTime();
			}
			
			//0.tabUserId,1.name,2.mobileNo,3.lattitude,4.longitude,5.surveyTime
			//List<Object[]> latestObj = tdpCadreDAO.getLatestLattitudeLangitudeOfTabUser(constituencyId,fromDate,toDate);
			
			List<Object[]> latestOb1j = tdpCadreDAO.getLatestLattitudeLangitudeOfTabUser(constituencyId,fromDate,toDate);
			List<Long> tdpCadreIdsList = new ArrayList<Long>(0);
			if(latestOb1j !=null && latestOb1j.size()>0){
				for(Object[] obj : latestOb1j) {		
					tdpCadreIdsList.add(obj[0] !=null ? (Long)obj[0]:0l);
				}
				
				List<Object[]> latestObj =  tdpCadreDAO.getTabUserInfoDetailsByTdpCadreIds(tdpCadreIdsList);
				if(latestObj !=null && latestObj.size()>0){
					for(Object[] obj : latestObj) {					
						GISUserTrackingVO Vo= new GISUserTrackingVO();
						
						Vo.setId(obj[0] !=null ? (Long)obj[0]:0l);
						Vo.setName(obj[1] !=null ? obj[1].toString():"");
						Vo.setMobileNo(obj[2] !=null ? obj[2].toString():"");
						Vo.setLattitude(obj[3] !=null ? obj[3].toString():"");
						Vo.setLongitude(obj[4] !=null ? obj[4].toString():"");
						Vo.setSurveyTime(obj[5] !=null ? obj[5].toString():"");
						
						finalList.add(Vo);
					}
				}			
			}
		}catch(Exception e){
			LOG.error("Exception raised at getLatestLattitudeLangitudeOfTabUserAgentDetails in CadreRegistrationService Service class", e);
		}
		return finalList;
	}
	public List<CadreRegisterInfo> getDistrictsByState(Long stateId)
	{
		List<CadreRegisterInfo> cadreRegisterInfoList=null;
		List<Object[]> returnList = null;
		//Long distId;
		try {
				returnList = districtDAO.getDistrictForState(stateId);
				if(returnList !=null && returnList.size()>0)
				{
					cadreRegisterInfoList = new ArrayList<CadreRegisterInfo>();
					for (Object[] objects : returnList) 
					{
						CadreRegisterInfo cadreRegisterInfo = new CadreRegisterInfo();
						cadreRegisterInfo = new CadreRegisterInfo();
						cadreRegisterInfo.setId(Long.valueOf(objects[0].toString()));
						cadreRegisterInfo.setName(objects[1].toString());
						
						cadreRegisterInfoList.add(cadreRegisterInfo);
					}
				}
			}
			catch (Exception e) {
				LOG.error("Exception raised in getDistrictsByState in CadreRegistrationService service", e);
			}
		return cadreRegisterInfoList;
	}
	
	public List<GenericVO> getStateWiseDistrictsForUsers(Long stateId,Long userId) {
		List<GenericVO> districtList = null;
		try {
			
			List<Object[]> list =userDistrictAccessInfoDAO.getLocationIdList(userId);
			if (commonMethodsUtilService.isListOrSetValid(list)) {
				districtList = new ArrayList<GenericVO>();
				for (Object[] param : list) {
					if (!districtList.contains(new GenericVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])))) {
						districtList.add(new GenericVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])));
					}
				}
			}else{
				List<Object[]> alldistrictlist = districtDAO.getDistrictsForState(stateId);
				if (commonMethodsUtilService.isListOrSetValid(alldistrictlist)) {
					districtList = new ArrayList<GenericVO>();
					for (Object[] param : alldistrictlist) {
						if (!districtList.contains(new GenericVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])))) {
							districtList.add(new GenericVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])));
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in getStateWiseConstituency() in CadreRegistrationService class", e);
		}
		return districtList;
	}
	public List<GenericVO> getConstituenciesByDistrictForUser(Long districtId,Long userId) {
		List<GenericVO> districtList = null;
		try {
			
			List<Object[]> list =userConstituencyAccessInfoDAO.findByUser(userId);
			if (commonMethodsUtilService.isListOrSetValid(list)) {
				districtList = new ArrayList<GenericVO>();
				for (Object[] param : list) {
					if (!districtList.contains(new GenericVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])))) {
						districtList.add(new GenericVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])));
					}
			}
			}else{
				List<Object[]>  allConstituencylist = constituencyDAO.getDistrictWiseConstituency(districtId);
				if (commonMethodsUtilService.isListOrSetValid(allConstituencylist)) {
					districtList = new ArrayList<GenericVO>();
					for (Object[] param : allConstituencylist) {
						if (!districtList.contains(new GenericVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])))) {
							districtList.add(new GenericVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])));
						}
				}
			}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in getStateWiseConstituency() in CadreRegistrationService class", e);
		}
		return districtList;
	}
}
