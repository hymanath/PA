package com.itgrids.partyanalyst.service.impl;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

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

import com.itgrids.partyanalyst.dao.IBloodGroupDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreCommitteeDAO;
import com.itgrids.partyanalyst.dao.ICadreCommitteeLevelDAO;
import com.itgrids.partyanalyst.dao.ICadreCommitteeRoleDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICadreLevelDAO;
import com.itgrids.partyanalyst.dao.ICadreParticipatedElectionDAO;
import com.itgrids.partyanalyst.dao.ICadrePreviousRolesDAO;
import com.itgrids.partyanalyst.dao.ICadreRolesDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssignDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.ICardReceiverDAO;
import com.itgrids.partyanalyst.dao.ICardSenderDAO;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyDesignationDAO;
import com.itgrids.partyanalyst.dao.ISmsJobStatusDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreBackupDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreFamilyDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreOnlineDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterNamesDAO;
import com.itgrids.partyanalyst.dao.IVoterRelationDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreFamilyVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.dto.CadrePrintVO;
import com.itgrids.partyanalyst.dto.CadreRegisterInfo;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CardSenderVO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;
import com.itgrids.partyanalyst.dto.VoterInfoVO;
import com.itgrids.partyanalyst.model.BloodGroup;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.CadreParticipatedElection;
import com.itgrids.partyanalyst.model.CadrePreviousRoles;
import com.itgrids.partyanalyst.model.CadreSurveyUser;
import com.itgrids.partyanalyst.model.CadreSurveyUserAssignDetails;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CardReceiver;
import com.itgrids.partyanalyst.model.CardSender;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.SmsJobStatus;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.TdpCadreBackupDetails;
import com.itgrids.partyanalyst.model.TdpCadreFamilyDetails;
import com.itgrids.partyanalyst.model.TdpCadreOnline;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterNames;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;
import com.itgrids.partyanalyst.utils.RandomNumberGeneraion;

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
					String cadreBasicInfo = "Voter Name " + ":" + inputResponse.getVoterName() + "-" +"Date Of Birth "+ ":" + inputResponse.getDob() +"-"+ "Gender "+ ":" +inputResponse.getGender()+ "-" +"Relative Name"+ ":" + inputResponse.getRelativeName() +"-" +"VoterCardNumber"+ ":" + inputResponse.getVoterCardNo() + "-" + "H.NO" + ":" + inputResponse.getHouseNo() + "-" +"Party Member Since" + ":" +inputResponse.getPartyMemberSince() + "-" + "Blood Group " + ":" + inputResponse.getBloodGroupId() + "-" + "Street/hamle" + ":" +inputResponse.getStreet() +"-" +"Caste" + ":" + inputResponse.getCasteId() + "-" + "Mobile No" + ":" + inputResponse.getMobileNumber() + "-" + "Education" + ":" +inputResponse.getEducationId() + "-" + "Occupation " + ":" +inputResponse.getOccupationId() + "-" + "Previous Enroll Ment No " + ":" + inputResponse.getPreviousEnrollmentNumber();
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
					}
				
					
					tdpCadreBackupDetails.setRefNo(inputResponse.getRefNo());
					tdpCadreBackupDetails.setCadreBasicInfo(cadreBasicInfo);
					tdpCadreBackupDetails.setCadrePreviousRoles(previousRoles);
					tdpCadreBackupDetails.setCadrePreviousElections(previousElections);
					tdpCadreBackupDetails.setUpdatedBy(inputResponse.getUpdatedUserId() != null ? inputResponse.getUpdatedUserId().longValue():0L);
					tdpCadreBackupDetails.setDataSourceType(registrationType);
					tdpCadreBackupDetails.setFamilyDetails(familyDetails);
					tdpCadreBackupDetails.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
				
					 tdpCadreBackupDetails = tdpCadreBackupDetailsDAO.save(tdpCadreBackupDetails);
					
				}
				return tdpCadreBackupDetails;
				}
			      
			});
			return returnData;
			}
		 catch (Exception e) {
		LOG.error("exception occured in updateRequestDetailsForBackup in CadreRegistrationService service");
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
		
		TdpCadreBackupDetails tdpCadreBackupDetails = updateRequestDetailsForBackup(cadreRegistrationVOList,registrationType);
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
												tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(existingVoters);
												tdpCadreDAO.inActiveTdpCadreByCadreIds(existingVoters);
												//emptyTdpCadreData(voterIdsList.get(0));
												
												TdpCadre tdpCadre = new TdpCadre();
												if(voterIdsList.get(0) != null){
													tdpCadre.setImage(voterIdsList.get(0).getImage());
													tdpCadre.setRefNo(voterIdsList.get(0).getRefNo());
													tdpCadre.setMemberShipNo(voterIdsList.get(0).getMemberShipNo());
												}
												tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
											
												
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
														tdpCadreDAO.inActiveTdpCadreByCadreIds(existingVoters);
														//emptyTdpCadreData(voterIdsList.get(0));
														
														TdpCadre tdpCadre = new TdpCadre();
														if(voterIdsList.get(0) != null){
															tdpCadre.setImage(voterIdsList.get(0).getImage());
															tdpCadre.setRefNo(voterIdsList.get(0).getRefNo());
															tdpCadre.setMemberShipNo(voterIdsList.get(0).getMemberShipNo());
														}
														tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new",false);
														
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
						tdpCadre.setDataSourceType(registrationType.trim().toUpperCase());
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
							if(gen.equalsIgnoreCase(cadreRegistrationVO.getGender()))
							{
								tdpCadre.setGender(cadreRegistrationVO.getGender());
							}
							else
							{
								surveyCadreResponceVO.setErrorCode("VOTER GENDER MISS MATCH");
							}
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
						    tdpCadre.setInsertedUserId(cadreRegistrationVO.getCreatedUserId().longValue());
						}else{
							tdpCadre.setInsertedWebUserId(cadreRegistrationVO.getCreatedUserId().longValue());
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
										voterNamesDAO.save(voterName);
									}
								}
							}else{
								VoterNames voterName = new VoterNames();
								voterName.setVoter(voterDAO.get(tdpCadre.getVoterId()));
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
					if(!insertType.equalsIgnoreCase("update")){
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
							tdpCadre.setSurveyTime(sdf.parse(cadreRegistrationVO.getSurveyTimeStr()));
						} catch (Exception e) {
							// TODO: handle exception
						}
						
					}else if( insertType.equalsIgnoreCase("new") && registrationType != null && (registrationType.equalsIgnoreCase("WEB") )){
						tdpCadre.setSurveyTime(tdpCadre.getInsertedTime());
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
					
					if(registrationType != null && (registrationType.equalsIgnoreCase("WEB") || registrationType.equalsIgnoreCase("ONLINE")) && insertType.equalsIgnoreCase("new")){
						String userId = "0000";
						if(cadreRegistrationVO.getCreatedUserId() != null){
						   userId = cadreRegistrationVO.getCreatedUserId().toString();
						}
						String ref = getReferenceNo(userId,registrationType);
						synchronized (CadreRegistrationService.class) {
							if(tdpCadre.getRefNo() == null || tdpCadre.getRefNo().trim().length() == 0){
							  ref = getUniueRefNo(ref,registrationType);
							  tdpCadre.setRefNo(ref);
							}
							cadreRegistrationVO.setRefNo(tdpCadre.getRefNo());
							if(userAddress.getDistrict() != null)
							{
								if(tdpCadre.getMemberShipNo() == null || tdpCadre.getMemberShipNo().trim().length() == 0){
								  String membershipNo = getMemberShipNo(userAddress.getDistrict().getDistrictId());
								  tdpCadre.setMemberShipNo(membershipNo);
								}
							}
							
							surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
							uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre);
							  tdpCadre1 = tdpCadreDAO.save(tdpCadre);	
						}
					}else if(registrationType != null && (registrationType.equalsIgnoreCase("WEB") || registrationType.equalsIgnoreCase("ONLINE")) && !insertType.equalsIgnoreCase("new")){
						surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
						tdpCadre1 = tdpCadreDAO.save(tdpCadre);	
				    }else{
					  if(insertType.equalsIgnoreCase("new")){
						  synchronized (CadreRegistrationService.class) {
						     tdpCadre.setRefNo(cadreRegistrationVO.getRefNo());
						     if(userAddress.getDistrict() != null)
						     {
						    	 if(tdpCadre.getMemberShipNo() == null || tdpCadre.getMemberShipNo().trim().length() == 0){
						    	 	String membershipNo = getMemberShipNo(userAddress.getDistrict().getDistrictId());
							        tdpCadre.setMemberShipNo(membershipNo);
						    	 }
						     }
						       
								surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
								uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre);
								  tdpCadre1 = tdpCadreDAO.save(tdpCadre);	
						  }
					  }else{
						  //tdpCadre.setRefNo(cadreRegistrationVO.getRefNo());
						  uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre);
						  surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
						  
						    tdpCadre1= tdpCadreDAO.save(tdpCadre);	
					  }
					}
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
					surveyCadreResponceVO.setStatus("SUCCESS");
					surveyCadreResponceVO.setResultCode(ResultCodeMapper.SUCCESS);
					if(insertType.equalsIgnoreCase("new") && cadreRegistrationVO.getMobileNumber() != null && cadreRegistrationVO.getMobileNumber().trim().length() > 0 && cadreRegistrationVO.getRefNo() != null){
					   //sendSMS(cadreRegistrationVO.getMobileNumber().trim(), "Thank You for registering as TDP cadre.For further queries use Ref No "+cadreRegistrationVO.getRefNo());
						if(!statusVar){
						
							try{
								String jobCode = sendSMSInTelugu(cadreRegistrationVO.getMobileNumber().trim(), getUniCodeMessage(StringEscapeUtils.unescapeJava("\u0C24\u0C46\u0C32\u0C41\u0C17\u0C41 \u0C26\u0C47\u0C36\u0C02 \u0C2A\u0C3E\u0C30\u0C4D\u0C1F\u0C40 \u0C15\u0C3E\u0C30\u0C4D\u0C2F\u0C15\u0C30\u0C4D\u0C24\u0C17\u0C3E \u0C28\u0C2E\u0C4B\u0C26\u0C41 \u0C1A\u0C47\u0C38\u0C41\u0C15\u0C41\u0C28\u0C4D\u0C28\u0C02\u0C26\u0C41\u0C15\u0C41 \u0C27\u0C28\u0C4D\u0C2F\u0C35\u0C3E\u0C26\u0C3E\u0C32\u0C41. \u0C2E\u0C40 \u0C2F\u0C4A\u0C15\u0C4D\u0C15 \u0C30\u0C3F\u0C2B\u0C30\u0C46\u0C28\u0C4D\u0C38\u0C4D \u0C28\u0C46\u0C02\u0C2C\u0C30\u0C4D : ")+cadreRegistrationVO.getRefNo()));
								
								
								Long tdpCadreId = tdpCadre1.getTdpCadreId();
								if(tdpCadreId!=null){
									if(tdpCadre1.getMobileNo()!=null){
										jobCode = jobCode.replace("OK:", "");
										SmsJobStatus smsJobStatus = new SmsJobStatus();
										smsJobStatus.setTdpCadreId(tdpCadreId);
										
										smsJobStatus.setMobileNumber(tdpCadre1.getMobileNo());
										smsJobStatus.setJobCode(jobCode);
										smsJobStatus.setFromTask("Registration");
										
										smsJobStatusDAO.save(smsJobStatus);
										//tdpCadreDAO.updateSmsJobCode(tdpCadreId, jobCode.trim());
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
					if(cadreRegistrationVO.getWardId() != null && cadreRegistrationVO.getWardId().trim().length() > 0 && Long.valueOf(cadreRegistrationVO.getWardId().trim()).longValue() > 0l)
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
	

	public List<VoterInfoVO> getSearchDetailsCadreRegistration(Long constituencyId, String seachType, String candidateName, String voterCardId, String houseNo,Long panchayatId,Long boothId,String isPresentCadre)
	{
		String cadrePath="images/cadre_images/";
		String voterPath="voter_images/"+constituencyId+"/Part";
		StringBuilder searchQuery = new StringBuilder();
		List<VoterInfoVO> returnList = null;
		List searchList = null;
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

				searchList = boothPublicationVoterDAO.getVotersDetailsForCadreRegistratiobByconstituencId(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID,searchQuery.toString(),panchayatId,boothId,isPresentCadre);
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
						vo.setImage(voter[7]!=null ?voterPath+voter[8].toString().trim()+"/"+voter[7].toString().trim()+".jpg":"");
						vo.setIsRegistered("N");						
						returnList.add(vo);
					}
				}
				
				if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))
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

				
				searchList = tdpCadreDAO.getCadreDetailsForCadreRegistratiobByconstituencId(constituencyId, searchQuery.toString(), panchayatId, boothId, isPresentCadre);
				
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
		
		return returnList;
	}
	
	public List<VoterInfoVO> getCandidateInfoBySearchCriteria(String searchType, Long candidateId,String staticContentLoc,String constituencyId)
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
							List<String> partNos = boothPublicationVoterDAO.getPartNo(Long.valueOf(constituencyId), voter.getVoterId());
							if(voter.getVoterIDCardNo() != null && partNos.size() > 0 && partNos.get(0) != null){
								String filePath = staticContentLoc +"voter_images"+pathSeperator+constituencyId+pathSeperator+"Part"+partNos.get(0).trim()+pathSeperator+voter.getVoterIDCardNo()+".jpg";
								if(checkFileExistingOrNot(filePath)){
									vo.setVoterImagePresent(true);
									vo.setVoterImage("voter_images"+pathSeperator+constituencyId+pathSeperator+"Part"+partNos.get(0).trim()+pathSeperator+voter.getVoterIDCardNo()+".jpg");
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
						
						List<TdpCadre> tdpCadreList = tdpCadreDAO.getVoterByVoterId(voterId);
						
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
								vo.setMemberShipId(tdpCadre.getPreviousEnrollmentNo() != null ? tdpCadre.getPreviousEnrollmentNo().toString():"");
								vo.setActiveDate(tdpCadre.getPartyMemberSince() != null ? new SimpleDateFormat("yyyy-MM-dd").format(tdpCadre.getPartyMemberSince()):"");
								if(tdpCadre.getIsRelative() != null && tdpCadre.getIsRelative().equalsIgnoreCase("Y")){
								  vo.setRelative("true");
								  vo.setRelationTypeId(tdpCadre.getRelationType() != null ? tdpCadre.getRelationType().getVoterRelationId() : 0l);
								}
								existingFamilyInfo =  getExistingCadreFamilyInfo(tdpCadreId);
								existingParticipationDetails = getExistingCadreParticipationInfo(tdpCadreId);
								existingrollsDetails = getExistingRollsInfo(tdpCadreId);
								
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
							vo.setCasteId(tdpCadre.getCasteState() != null ? tdpCadre.getCasteState().getCasteStateId():0L);							
							vo.setCasteName(tdpCadre.getCasteState() != null ? tdpCadre.getCasteState().getCaste().getCasteName().toString():"");	
							
							vo.setOccupationId(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupationId():0L);
							vo.setOccupation(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupation():"");
							
							vo.setEducation(tdpCadre.getEducationalQualifications() != null ? tdpCadre.getEducationalQualifications().getEduQualificationId().toString():"0");
							
							vo.setLocation(tdpCadre.getUserAddress() != null ? (tdpCadre.getUserAddress().getStreet() != null ?tdpCadre.getUserAddress().getStreet().toString():""):"");
							vo.setMobileNo(tdpCadre.getMobileNo() != null ? tdpCadre.getMobileNo():"");
							vo.setMemberShipId(tdpCadre.getPreviousEnrollmentNo() != null ? tdpCadre.getPreviousEnrollmentNo().toString():"");
							vo.setActiveDate(tdpCadre.getPartyMemberSince() != null ? new SimpleDateFormat("yyyy-MM-dd").format(tdpCadre.getPartyMemberSince()):"");
							if(tdpCadre.getIsRelative() != null && tdpCadre.getIsRelative().equalsIgnoreCase("Y")){
							  vo.setRelative("true");
							  vo.setRelationTypeId(tdpCadre.getRelationType() != null ? tdpCadre.getRelationType().getVoterRelationId() : 0l);
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
								Voter voter =tdpCadre.getVoter();
								Long constiId = tdpCadre.getUserAddress().getConstituency().getConstituencyId();
								if(voter != null &&  constiId!= null){
									List<String> partNos = boothPublicationVoterDAO.getPartNo(constiId, voter.getVoterId());
									if(partNos.size() > 0 && partNos.get(0) != null && voter.getVoterIDCardNo() != null){
										String filePath = staticContentLoc +"voter_images"+pathSeperator+constituencyId+pathSeperator+"Part"+partNos.get(0).trim()+pathSeperator+voter.getVoterIDCardNo()+".jpg";
										if(checkFileExistingOrNot(filePath)){
											vo.setVoterImagePresent(true);
											vo.setVoterImage("voter_images"+pathSeperator+constituencyId+pathSeperator+"Part"+partNos.get(0).trim()+pathSeperator+voter.getVoterIDCardNo()+".jpg");
										}
									}
							   }
							}
							
							existingFamilyInfo =  getExistingCadreFamilyInfo(tdpCadreId);
							existingParticipationDetails = getExistingCadreParticipationInfo(tdpCadreId);
							existingrollsDetails = getExistingRollsInfo(tdpCadreId);

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
					List<Object[]> voterInfo = boothPublicationVoterDAO.getBoothIdsDetailsOfVoterIds(votersList, IConstants.VOTER_DATA_PUBLICATION_ID);
					
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
			Election election = electionDAO.get(electionId);
			List<Object[]> constituencyList = null;
			if(election != null )
			{
				String electionType = election.getElectionScope().getElectionType().getElectionType();
				
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
			
			List<Long> constiIds = new ArrayList<Long>();
			
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
			}
			
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
						vo.setCaste(param[2] != null ? param[2].toString().trim():"");
						
						vo.setId(param[3] != null ? Long.valueOf(param[3].toString().trim()):0L);
						
						returnList.add(vo);
					}
				}
				
		} catch (Exception e) {
			LOG.error("Entered into getExistingCadreInfo method in CadreRegistrationService class",e);
		}
 		
 		return returnList;
 	}
	private String sendSMS(String mobileNo,String message){

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
		number = number+randomNum.nextInt(999999);
		if(number.length()<6){
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
		
		return ref;
	}
	
	public String getUniueRefNo(String ref,String dataSource){
		String randomNo = null;
		while(true){
			 randomNo = ref+getRandomNo();
			Long count = tdpCadreDAO.checkRandomNoExistsOrNot(dataSource, randomNo);
			if(count.longValue() == 0l){
				break;
			}
			
		}
		
		return randomNo;
	}
	
	private  String sendSMSInTelugu1(String mobileNo,String message){
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
				LOG.error("Exception rised in sending sms while cadre registration",e);
				return "exception";
		} finally {
				post.releaseConnection();
		}
		
	}
	
	private  String sendSMSInTelugu(String mobileNo,String msg){
		
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
	
	private String getMemberShipNo(Long districtId){
		String memberShipNo ="AP14";
		if(districtId != null && districtId.longValue() < 11l){
			memberShipNo = "TS14";
		}
		String randomNo = "";
		while(true){
			randomNo = memberShipNo+RandomNumberGeneraion.randomGenerator(8);
			Long count = tdpCadreDAO.checkMemberShipExistsOrNot(randomNo);
			if(count.longValue() == 0l){
				break;
			}
			
		}
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
							if(voter != null && cadreRegistrationVO.getConstituencyId() != null && Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()).longValue() > 0){
								List<String> partNos = boothPublicationVoterDAO.getPartNo(Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()), voter.getVoterId());
								LOG.error("partNos size : "+partNos.size());
								if(partNos.size() > 0 && partNos.get(0) != null && voter.getVoterIDCardNo() != null){
								   sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +"voter_images"+pathSeperator+cadreRegistrationVO.getConstituencyId().trim()+pathSeperator+"Part"+partNos.get(0).trim()+pathSeperator+voter.getVoterIDCardNo().trim()+".jpg";
								   LOG.error("CADRENOTVOTER: SP:"+sourcePath+" DP:"+destinationPath+" VOTERID: "+voter.getVoterId());
								    status = copyFile(sourcePath,destinationPath);
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
							   String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +"voter_images"+pathSeperator+cadreRegistrationVO.getConstituencyId().trim()+pathSeperator+"Part"+partNos.get(0).trim()+pathSeperator+voter.getVoterIDCardNo().trim()+".jpg";
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
						   String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +"voter_images"+pathSeperator+cadreRegistrationVO.getConstituencyId().trim()+pathSeperator+"Part"+partNos.get(0).trim()+pathSeperator+voter.getVoterIDCardNo().trim()+".jpg";
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
					   String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +"voter_images"+pathSeperator+cadreRegistrationVO.getConstituencyId().trim()+pathSeperator+"Part"+partNos.get(0).trim()+pathSeperator+voter.getVoterIDCardNo().trim()+".jpg";
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
							   String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +"voter_images"+pathSeperator+cadreRegistrationVO.getConstituencyId().trim()+pathSeperator+"Part"+partNos.get(0).trim()+pathSeperator+voter.getVoterIDCardNo().trim()+".jpg";
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
				returnVO.setVoterCardNo(voterIdDetails.get(0)[5] != null ? voterIdDetails.get(0)[5].toString() : "");
				returnVO.setVillageEng(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getPanchayatName() : "");
				returnVO.setMandalEng(userAddress.getTehsil() != null ?  userAddress.getTehsil().getTehsilName() :"");
				returnVO.setConstiEng(userAddress.getConstituency() != null ?  userAddress.getConstituency().getName()  : "");
				returnVO.setDistrictEng(userAddress.getDistrict() != null ?  userAddress.getDistrict().getDistrictName() :"");
				if(userAddress.getConstituency() != null && userAddress.getBooth() !=null)
				{
					String url = "http://mytdp.com/voter_images/"+userAddress.getConstituency().getConstituencyId().toString().trim()+"/"+"Part"+userAddress.getBooth().getPartNo().trim()+"/"+returnVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";
					returnVO.setVoterImgPath(url);
					List<Object[]> names = voterNamesDAO.getVoterTeluguNames((Long)voterIdDetails.get(0)[4] );
					if(names != null && names.size() > 0)
					{
						String name = "";
						if( names.get(0)[0] != null && names.get(0)[0] .toString().trim().length() > 0)
						{
							name = names.get(0)[0].toString() ;
						}
						if(names.get(0)[1] != null && names.get(0)[1] .toString().trim().length() > 0)
						{
							name = name +  names.get(0)[1].toString() ;
						}
						
						if(name.trim().length() > 0)
						//name = name.replaceAll(",", " ").replaceAll(".", " ");
						returnVO.setVoterName(name);
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
					cadrePrintVO.setFirstCode(objects[0] != null ? objects[0].toString() : "");
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
					if(userAddress.getConstituency() != null && userAddress.getBooth() !=null)
					{
						String url = "http://mytdp.com/voter_images/"+userAddress.getConstituency().getConstituencyId().toString().trim()+"/"+"Part"+userAddress.getBooth().getPartNo().trim()+"/"+cadrePrintVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";;
						//String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +""+"\\"+userAddress.getConstituency().getConstituencyId().toString().trim()+"\\"+"Part"+userAddress.getBooth().getPartNo().trim()+"\\"+cadrePrintVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";
						cadrePrintVO.setVoterImgPath(url);
					}
					
					
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
		synchronized ("copyFile"){
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
	 }
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
	
	public CastVO getAllCastes()
	{
	CastVO returnVo = new CastVO();
	List<CastVO> castesList =new ArrayList<CastVO>();
	List<CastVO> casteStateList =new ArrayList<CastVO>();
	List<Object[]> list = casteDAO.getCastes();
	List<Object[]> list1 = casteStateDAO.getAllCasteInfo();
		try{
			
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					CastVO vo = new CastVO();
					vo.setCasteId((Long)params[0]);
					vo.setCastName(params[1] != null ? params[1].toString() : "");
					castesList.add(vo);
				}
			}
			if(list1 != null && list1.size() > 0)
			{
				for(Object[] params : list1)
				{
					CastVO vo = new CastVO();
					vo.setCastStateId((Long)params[0]);
					vo.setCasteId((Long)params[1]);
					vo.setCasteCategoryGroupId((Long)params[2]);
					vo.setStateId((Long)params[3]);
					
					casteStateList.add(vo);
				}
			}
			returnVo.setCasteList(castesList);
			returnVo.setCasteStateList(casteStateList);
		}
		catch (Exception e) {
			LOG.error("Exception raised in getAllCastes in CadreRegistrationService service", e);
		}
		return returnVo;
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
					cadrePrintVO.setFirstCode(objects[0] != null ? objects[0].toString() : "");
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
								String url = "http://mytdp.com/images/cadre_images/"+objects[8].toString();
								cadrePrintVO.setImage(url);
							}
						}
						else if(cadrePrintVO.getPhotoType().equalsIgnoreCase("CADRE")){
							if(objects[8] != null){
								String url = "http://mytdp.com/images/cadre_images/"+objects[8].toString();
								cadrePrintVO.setImage(url);
							}
						}
						else if(cadrePrintVO.getPhotoType().equalsIgnoreCase("VOTER")){
							if(userAddress.getConstituency() != null && userAddress.getBooth() !=null)
							{
								String url = "http://mytdp.com/voter_images/"+userAddress.getConstituency().getConstituencyId().toString().trim()+"/"+"Part"+userAddress.getBooth().getPartNo().trim()+"/"+cadrePrintVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";;
								//String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +""+"\\"+userAddress.getConstituency().getConstituencyId().toString().trim()+"\\"+"Part"+userAddress.getBooth().getPartNo().trim()+"\\"+cadrePrintVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";
								cadrePrintVO.setImage(url);
							}
						}
					}
					/*else{
						cadrePrintVO.setPhotoType("");
					}
				   if(objects[8] != null){
						String cadreUrl = "http://mytdp.com/images/cadre_images/"+objects[8].toString();
						cadrePrintVO.setCadreImgPath(cadreUrl);
					}
					
					if(userAddress.getConstituency() != null && userAddress.getBooth() !=null)
					{
						String url = "http://mytdp.com/voter_images/"+userAddress.getConstituency().getConstituencyId().toString().trim()+"/"+"Part"+userAddress.getBooth().getPartNo().trim()+"/"+cadrePrintVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";;
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
	
	public List<CadreRegisterInfo> getConstsByStateWiseAction(Long stateId)
	{
		List<CadreRegisterInfo> cadreRegisterInfoList=null;
		List<Object[]> returnList = null;
		//Long distId;
		try {
				returnList = tdpCadreDAO.getConstsByStateWiseAction(stateId);
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
					cadrePrintVO.setFirstCode(objects[0] != null ? objects[0].toString() : "");
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
					
								//String url = "http://mytdp.com/images/cadre_images/"+objects[8].toString();
								cadrePrintVO.setImage((objects[8] != null  ? "http://mytdp.com/images/cadre_images/"+objects[8].toString() : ""));
								cadrePrintVO.setImgPath1((objects[8] != null  ? "http://mytdp.com/images/cadre_images/"+objects[8].toString() : ""));
								
								if(userAddress.getConstituency() != null && userAddress.getBooth() !=null){
									cadrePrintVO.setImgPath2("http://mytdp.com/voter_images/"+userAddress.getConstituency().getConstituencyId().toString().trim()+"/"+"Part"+userAddress.getBooth().getPartNo().trim()+"/"+cadrePrintVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg");
								}
								else{
									cadrePrintVO.setImgPath2("");
								}
							
							
						}
						else if(cadrePrintVO.getPhotoType().equalsIgnoreCase("CADRE")){							
								//String url = "http://mytdp.com/images/cadre_images/"+objects[8].toString();
								cadrePrintVO.setImage((objects[8] != null  ? "http://mytdp.com/images/cadre_images/"+objects[8].toString() : ""));
								cadrePrintVO.setImgPath1((objects[8] != null  ? "http://mytdp.com/images/cadre_images/"+objects[8].toString() : ""));								
								if(userAddress.getConstituency() != null && userAddress.getBooth() !=null)
								{
									cadrePrintVO.setImgPath2("http://mytdp.com/voter_images/"+userAddress.getConstituency().getConstituencyId().toString().trim()+"/"+"Part"+userAddress.getBooth().getPartNo().trim()+"/"+cadrePrintVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg");
								}
								else{
									cadrePrintVO.setImgPath2("");
								}
						}
						else if(cadrePrintVO.getPhotoType().equalsIgnoreCase("VOTER")){
							if(userAddress.getConstituency() != null && userAddress.getBooth() !=null)
							{
								String url = "http://mytdp.com/voter_images/"+userAddress.getConstituency().getConstituencyId().toString().trim()+"/"+"Part"+userAddress.getBooth().getPartNo().trim()+"/"+cadrePrintVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";
								//String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL +""+"\\"+userAddress.getConstituency().getConstituencyId().toString().trim()+"\\"+"Part"+userAddress.getBooth().getPartNo().trim()+"\\"+cadrePrintVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";
								cadrePrintVO.setImage(url);								
								cadrePrintVO.setImgPath1(objects[8] != null  ? "http://mytdp.com/images/cadre_images/"+objects[8].toString() : "");
								cadrePrintVO.setImgPath2(objects[8] != null  ? "http://mytdp.com/images/cadre_images/"+objects[8].toString() : "");
							}
						}
					}
					/*else{
						cadrePrintVO.setPhotoType("");
					}*/
				   /*if(objects[8] != null){
						String cadreUrl = "http://mytdp.com/images/cadre_images/"+objects[8].toString();
						cadrePrintVO.setImage(cadreUrl);
					}
					
					if(userAddress.getConstituency() != null && userAddress.getBooth() !=null)
					{
						String url = "http://mytdp.com/voter_images/"+userAddress.getConstituency().getConstituencyId().toString().trim()+"/"+"Part"+userAddress.getBooth().getPartNo().trim()+"/"+cadrePrintVO.getVoterCardNo().toUpperCase().toString().trim()+".jpg";;
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
			int updatedCount = smsJobStatusDAO.updateSmsSentStatus(mobile, jobcode, date, statusCode);
			 
		} catch (Exception e) {
			LOG.error("Exception Raised in updateSmsJobStatus "+e);
			return "failed";
		}
		return "success";
	}
	
}
