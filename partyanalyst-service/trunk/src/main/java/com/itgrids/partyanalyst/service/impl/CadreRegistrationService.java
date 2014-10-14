package com.itgrids.partyanalyst.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
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
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreFamilyDetailsDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterRelationDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreFamilyVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadrePrintVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
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
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.TdpCadreFamilyDetails;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.Voter;
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
	
	
	
	public void setCadreCommitteeRoleDAO(
			ICadreCommitteeRoleDAO cadreCommitteeRoleDAO) {
		this.cadreCommitteeRoleDAO = cadreCommitteeRoleDAO;
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

	/**
	 * This service is used for saving cadre data from tab as well web also
	 * @author Prasad Thiragabathina
	 * @date 26-09-2014
	 * @param cadreRegistrationVOList
	 * @return surveyCadreResponceVO
	 */
	public SurveyCadreResponceVO saveCadreRegistration(final List<CadreRegistrationVO> cadreRegistrationVOList,final String registrationType)
	{
		final SurveyCadreResponceVO surveyCadreResponceVO = new SurveyCadreResponceVO();
		
		try {
			LOG.info("Entered into saveCadreRegistration in CadreRegistrationService service");
			
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
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
											tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new");
										}
										else
										{
											if(voterIdsList.size() > 0)
											{
												cadrePreviousRolesDAO.inActiveCadreRollesDetailsById(voterIdsList.get(0).getTdpCadreId());
												cadreParticipatedElectionDAO.inActiveCadreElectionDetailsById(voterIdsList.get(0).getTdpCadreId());
												tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(voterIdsList.get(0).getTdpCadreId());
												emptyTdpCadreData(voterIdsList.get(0));
												tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,voterIdsList.get(0),"update");
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
										if(cadreRegistrationVO.getVoterCardNumber() != null && cadreRegistrationVO.getVoterCardNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getVoterCardNumber().trim().length() > 0)
										{
											List<Long> voterCardNumbersList = voterDAO.getVoterId(cadreRegistrationVO.getVoterCardNumber());
											if(voterCardNumbersList != null && voterCardNumbersList.size() > 0)
											{
												Long voterId = voterCardNumbersList.get(0);
												List<TdpCadre> voterIdsList = tdpCadreDAO.getVoterByVoterId(voterId);
												if(voterIdsList.size()  == 0)
												{
													TdpCadre tdpCadre = new TdpCadre();
													tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new");
												}
												else
												{
													if(voterIdsList.size() > 0)
													{
														cadrePreviousRolesDAO.inActiveCadreRollesDetailsById(voterIdsList.get(0).getTdpCadreId());
														cadreParticipatedElectionDAO.inActiveCadreElectionDetailsById(voterIdsList.get(0).getTdpCadreId());
														tdpCadreFamilyDetailsDAO.inActiveCadreFamilyDetailsById(voterIdsList.get(0).getTdpCadreId());
														emptyTdpCadreData(voterIdsList.get(0));
														tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,voterIdsList.get(0),"update");
													}
												}
											}
										}
									    else
										{
									    	TdpCadre tdpCadre = new TdpCadre();
											tdpCadreSavingLogic(registrationType,cadreRegistrationVOList,cadreRegistrationVO,surveyCadreResponceVO,tdpCadre,"new");
										}
									}
								    
									
									
								}
							}
						
							
						}
				}});

		} catch (Exception e) {
			surveyCadreResponceVO.setResultCode(ResultCodeMapper.FAILURE);
			surveyCadreResponceVO.setStatus("EXCEPTION");
			LOG.error("Exception raised in saveCadreRegistration in CadreRegistrationService service", e);
		}
		
		return surveyCadreResponceVO;
	}
	
	
	/**
	 * @author Prasad Thiragabathina
	 * @date 26-09-2014
	 * @param registrationType
	 * @param cadreRegistrationVOList
	 * @param cadreRegistrationVO
	 * @param surveyCadreResponceVO
	 */
	public void tdpCadreSavingLogic(String registrationType,List<CadreRegistrationVO> cadreRegistrationVOList , CadreRegistrationVO cadreRegistrationVO,SurveyCadreResponceVO surveyCadreResponceVO,TdpCadre tdpCadre,String insertType)
	{
		try {	
				if(registrationType != null && !registrationType.equalsIgnoreCase("null") && registrationType.trim().length() > 0)
				{
					tdpCadre.setDataSourceType(registrationType.trim().toUpperCase());
				}
				if(cadreRegistrationVO.getVoterName() != null && !cadreRegistrationVO.getVoterName().equalsIgnoreCase("null") && cadreRegistrationVO.getVoterName().trim().length() > 0)
				{
					tdpCadre.setFirstname(cadreRegistrationVO.getVoterName());
				}
				if(cadreRegistrationVO.getDob() != null )
				{
					tdpCadre.setDateOfBirth(cadreRegistrationVO.getDob());
				}
				if(cadreRegistrationVO.getGender() != null && !cadreRegistrationVO.getGender().equalsIgnoreCase("null") && cadreRegistrationVO.getGender().trim().length() > 0)
				{
					tdpCadre.setGender(cadreRegistrationVO.getGender());
				}
				if(cadreRegistrationVO.getRelativeName() != null && !cadreRegistrationVO.getRelativeName().equalsIgnoreCase("null") && cadreRegistrationVO.getRelativeName().trim().length() > 0)
				{
					tdpCadre.setRelativename(cadreRegistrationVO.getRelativeName());
				}
				if(cadreRegistrationVO.getVoterId() != null && Long.valueOf(cadreRegistrationVO.getVoterId()) > 0)
				{
					tdpCadre.setVoterId(Long.valueOf(cadreRegistrationVO.getVoterId()));
				}
				if(cadreRegistrationVO.getAge() != null && Long.valueOf(cadreRegistrationVO.getAge()) > 0)
				{
					tdpCadre.setAge(Long.valueOf(cadreRegistrationVO.getAge()));
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
				if(cadreRegistrationVO.getPreviousEnrollmentNumber() != null && !cadreRegistrationVO.getPreviousEnrollmentNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getPreviousEnrollmentNumber().trim().length() > 0)
				{
					tdpCadre.setPreviousEnrollmentNo(cadreRegistrationVO.getPreviousEnrollmentNumber());
				}
				if(cadreRegistrationVO.getPartyMemberSince() != null)
				{
					tdpCadre.setPartyMemberSince(cadreRegistrationVO.getPartyMemberSince());
				}
				if(cadreRegistrationVO.getBloodGroupId() != null && cadreRegistrationVO.getBloodGroupId().longValue() > 0)
				{
					tdpCadre.setBloodGroupId(cadreRegistrationVO.getBloodGroupId());
				}
				
				if(cadreRegistrationVO.getCasteId() != null && cadreRegistrationVO.getCasteId() > 0)
				{
					tdpCadre.setCasteStateId(cadreRegistrationVO.getCasteId());
				}
				
				if(cadreRegistrationVO.getMobileNumber() != null && !cadreRegistrationVO.getMobileNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getMobileNumber().trim().length() > 0)
				{
					tdpCadre.setMobileNo(cadreRegistrationVO.getMobileNumber());
				}
				
				if(cadreRegistrationVO.getEducationId() != null && cadreRegistrationVO.getEducationId().longValue() > 0)
				{
					tdpCadre.setEducationId(cadreRegistrationVO.getEducationId());
				}
				
				if(cadreRegistrationVO.getOccupationId() != null && cadreRegistrationVO.getOccupationId().longValue() > 0)
				{
					tdpCadre.setOccupationId(cadreRegistrationVO.getOccupationId());
				}
				if(cadreRegistrationVO.getHouseNo() != null && !cadreRegistrationVO.getHouseNo().equalsIgnoreCase("null") && cadreRegistrationVO.getHouseNo().trim().length() > 0)
				{
					tdpCadre.setHouseNo(cadreRegistrationVO.getHouseNo());
				}
				if(cadreRegistrationVO.getPreviousEnrollmentNumber() != null && !cadreRegistrationVO.getPreviousEnrollmentNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getPreviousEnrollmentNumber().trim().length() > 0)
				{
					tdpCadre.setPreviousEnrollmentNo(cadreRegistrationVO.getPreviousEnrollmentNumber());
				}
				if(cadreRegistrationVO.getCreatedUserId() != null && cadreRegistrationVO.getCreatedUserId().longValue() > 0  )
				{
					if(registrationType.equalsIgnoreCase("TAB")){
					    tdpCadre.setInsertedUserId(cadreRegistrationVO.getCreatedUserId().longValue());
					}else{
						tdpCadre.setInsertedWebUserId(cadreRegistrationVO.getCreatedUserId().longValue());
					}
				}
				if(cadreRegistrationVO.getUpdatedUserId() != null && cadreRegistrationVO.getUpdatedUserId().longValue() > 0)
				{
					if(registrationType.equalsIgnoreCase("TAB")){
					     tdpCadre.setUpdatedUserId(cadreRegistrationVO.getUpdatedUserId().longValue());
					}else{
						 tdpCadre.setUpdatedWebUserId(cadreRegistrationVO.getUpdatedUserId().longValue());
					}
				}
				
				UserAddress userAddress = new UserAddress();
				if(tdpCadre.getVoterId() != null && tdpCadre.getVoterId().longValue() > 0)
				{
					getVoterAddressDetails(tdpCadre.getVoterId(),userAddress,cadreRegistrationVO.getStreet());
				}
				else
				{
					if(cadreRegistrationVO.getConstituencyId() != null && cadreRegistrationVO.getConstituencyId().trim().length() > 0 && !cadreRegistrationVO.getConstituencyId().trim().equalsIgnoreCase("null"))
					{
						if(Long.valueOf(cadreRegistrationVO.getConstituencyId()) > 0)
						{
							userAddress.setConstituency(constituencyDAO.get(Long.valueOf(cadreRegistrationVO.getConstituencyId())));
							userAddress.setCountry(countryDAO.get(1l));
							userAddress.setState(stateDAO.get(1l));
							userAddress.setConstituency(constituencyDAO.get(Long.valueOf(cadreRegistrationVO.getConstituencyId())));
							userAddress.setDistrict(constituencyDAO.get(Long.valueOf(cadreRegistrationVO.getConstituencyId())).getDistrict());
							if(cadreRegistrationVO.getStreet() != null && cadreRegistrationVO.getStreet().trim().length() > 0 && cadreRegistrationVO.getStreet().trim().equalsIgnoreCase("null"))
							{
								userAddress.setStreet(cadreRegistrationVO.getStreet());
							}
							if(cadreRegistrationVO.getPanchayatId() != null && cadreRegistrationVO.getPanchayatId().trim().length() > 0 && !cadreRegistrationVO.getPanchayatId().trim().equalsIgnoreCase("null"))
							{
								if(Long.valueOf(cadreRegistrationVO.getPanchayatId()) > 0)
								{
									userAddress.setPanchayat(panchayatDAO.get(Long.valueOf(cadreRegistrationVO.getPanchayatId())));
									userAddress.setTehsil(panchayatDAO.get(Long.valueOf(cadreRegistrationVO.getPanchayatId())).getTehsil());
								}
								
							}
							if(cadreRegistrationVO.getBoothId() != null && cadreRegistrationVO.getBoothId().trim().length() > 0 && !cadreRegistrationVO.getBoothId().trim().equalsIgnoreCase("null"))
							{
								if(Long.valueOf(cadreRegistrationVO.getBoothId()) > 0)
								{
									userAddress.setBooth(boothDAO.get(Long.valueOf(cadreRegistrationVO.getBoothId())));
								}
								
								
							}
							if(cadreRegistrationVO.getMuncipalityId() !=null && cadreRegistrationVO.getMuncipalityId().trim().length() > 0 && !cadreRegistrationVO.getMuncipalityId().trim().equalsIgnoreCase("null"))
							{
								if(Long.valueOf(cadreRegistrationVO.getMuncipalityId()) > 0)
								{
									userAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(cadreRegistrationVO.getMuncipalityId())));
								}
								
							}
						}
						
					}
					
				}
				
				userAddress = userAddressDAO.save(userAddress);
				tdpCadre.setUserAddress(userAddress);	
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
				tdpCadre.setInsertedTime(dateUtilService.getCurrentDateAndTime());
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
				if(cadreRegistrationVO.getSurveyTime() != null)
				{
					tdpCadre.setSurveyTime(cadreRegistrationVO.getSurveyTime());
				}else if( insertType.equalsIgnoreCase("new") && registrationType != null && (registrationType.equalsIgnoreCase("WEB") || registrationType.equalsIgnoreCase("ONLINE"))){
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
				   tdpCadre.setRelationTypeId(cadreRegistrationVO.getRelationTypeId());
				}else{
				   tdpCadre.setIsRelative("N");
				   tdpCadre.setRelationTypeId(null);
				}
				if(registrationType != null && (registrationType.equalsIgnoreCase("WEB") || registrationType.equalsIgnoreCase("ONLINE")) && insertType.equalsIgnoreCase("new")){
					String userId = "0000";
					if(cadreRegistrationVO.getCreatedUserId() != null){
					   userId = cadreRegistrationVO.getCreatedUserId().toString();
					}
					String ref = getReferenceNo(userId,registrationType);
					synchronized (CadreRegistrationService.class) {
						ref = getUniueRefNo(ref,registrationType);
						tdpCadre.setRefNo(ref);
						cadreRegistrationVO.setRefNo(ref);
						String membershipNo = getMemberShipNo(userAddress.getDistrict().getDistrictId());
						tdpCadre.setMemberShipNo(membershipNo);
						surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
						uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre);
						tdpCadre = tdpCadreDAO.save(tdpCadre);	
					}
				}else{
				  if(insertType.equalsIgnoreCase("new")){
					  synchronized (CadreRegistrationService.class) {
					     tdpCadre.setRefNo(cadreRegistrationVO.getRefNo());
					        String membershipNo = getMemberShipNo(userAddress.getDistrict().getDistrictId());
							surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getMemberShipNo());
							uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre);
					     tdpCadre = tdpCadreDAO.save(tdpCadre);	
					  }
				  }else{
					  uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre);
					  surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getMemberShipNo());
					tdpCadre = tdpCadreDAO.save(tdpCadre);	
				  }
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
								
								//if(rolesVO.getFromDate() != null && rolesVO.getToDate() != null)
								//{
									/*if(rolesVO.getDesignationLevelId() != null && rolesVO.getDesignationLevelId().longValue() > 0)
									{
										cadrePreviousRoles.setCadreLevelId(rolesVO.getDesignationLevelId());
									}
									if(rolesVO.getDesignationLevelValue() != null && rolesVO.getDesignationLevelValue().longValue() > 0)
									{
										cadrePreviousRoles.setPartyDesignationId(rolesVO.getDesignationLevelValue());
									}*/
									if(rolesVO.getCadreCommitteeId() != null && rolesVO.getCadreCommitteeId().longValue() > 0 && rolesVO.getCadreCommitteeLevelId() != null && rolesVO.getCadreCommitteeLevelId().longValue() > 0 && rolesVO.getCadreRoleId() != null && rolesVO.getCadreRoleId().longValue() > 0 )
									{
										List<Long> cadreCommotteRoleIds = cadreCommitteeRoleDAO.getCadreCommitteRoleIdBySelection(rolesVO.getCadreCommitteeLevelId(), rolesVO.getCadreRoleId(), rolesVO.getCadreCommitteeId());
										if(cadreCommotteRoleIds != null && cadreCommotteRoleIds.size() > 0)
										{
											cadrePreviousRoles.setCadreCommitteeRoleId(cadreCommotteRoleIds.get(0));
										}
										
									}
									if(rolesVO.getFromDate() != null)
									{
										cadrePreviousRoles.setFromDate(rolesVO.getFromDate());
									}
									if(rolesVO.getToDate() != null)
									{
										cadrePreviousRoles.setToDate(rolesVO.getToDate());
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
							if(tdpCadre != null)
							{
								if(electionVO.getConstituencyId() != null && electionVO.getConstituencyId().trim().length()  > 0)
								{
									CadreParticipatedElection cadreParticipatedElection = new CadreParticipatedElection();
									
									cadreParticipatedElection.setTdpCadreId(tdpCadre.getTdpCadreId());
									if(electionVO.getElectionTypeId() != null && electionVO.getElectionTypeId().trim().length() > 0)
									{
										cadreParticipatedElection.setElectionId(Long.valueOf(electionVO.getElectionTypeId()));
									}
									if(electionVO.getConstituencyId() != null && electionVO.getConstituencyId().trim().length()> 0)
									{
										cadreParticipatedElection.setConstituencyId(Long.valueOf(electionVO.getConstituencyId()));
									}
									if(electionVO.getCandidateId() != null && electionVO.getCandidateId().trim().length()> 0)
									{
										if(previousElectionPartList.get(0).getCandidateId() != null)
										{
											cadreParticipatedElection.setCandidateId((Long.valueOf(previousElectionPartList.get(0).getCandidateId())));
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
						if(tdpCadre != null)
						{
							if(cadreFamilyVO != null)
							{
								if((cadreFamilyVO.getOccupationId() != null && cadreFamilyVO.getOccupationId().longValue() > 0) || cadreFamilyVO.getEducationId() != null && cadreFamilyVO.getEducationId().longValue() > 0)
								{
									TdpCadreFamilyDetails tdpCadreFamilyDetails = new TdpCadreFamilyDetails();
									tdpCadreFamilyDetails.setTdpCadreId(tdpCadre.getTdpCadreId());
									if(cadreFamilyVO.getVoterName() != null && cadreFamilyVO.getVoterName().equalsIgnoreCase("null") && cadreFamilyVO.getVoterName().trim().length() > 0)
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
									
									if(cadreFamilyVO.getVoterId() != null && cadreFamilyVO.getVoterId().longValue() > 0l)
									{
										tdpCadreFamilyDetails.setVoterId(cadreFamilyVO.getVoterId());
									}
									else
									{
										if(cadreFamilyVO.getVoterCadreNO() != null && !cadreFamilyVO.getVoterCadreNO().equalsIgnoreCase("null") && cadreFamilyVO.getVoterCadreNO().trim().length() > 0)
										{
											List<Long> voterCardNumbersList = voterDAO.getVoterId(cadreRegistrationVO.getVoterCardNumber());
											if(voterCardNumbersList != null && voterCardNumbersList.size() > 0)
											{
												tdpCadreFamilyDetails.setVoterId(voterCardNumbersList.get(0));
											}
										}
									}
									tdpCadreFamilyDetails.setIsDeleted("N");
									tdpCadreFamilyDetails.setInsertedDate(dateUtilService.getCurrentDateAndTime());
									tdpCadreFamilyDetails.setUpdatedDate(dateUtilService.getCurrentDateAndTime());	
									tdpCadreFamilyDetailsDAO.save(tdpCadreFamilyDetails);
								}
								
							}
							
						}
						
					}
				}
				surveyCadreResponceVO.setStatus("SUCCESS");
				surveyCadreResponceVO.setResultCode(ResultCodeMapper.SUCCESS);
				if(insertType.equalsIgnoreCase("new") && cadreRegistrationVO.getMobileNumber() != null && cadreRegistrationVO.getMobileNumber().trim().length() > 0 && cadreRegistrationVO.getRefNo() != null){
				   //sendSMS(cadreRegistrationVO.getMobileNumber().trim(), "Thank You for registering as TDP cadre.For further queries use Ref No "+cadreRegistrationVO.getRefNo());
				   sendSMSInTelugu(cadreRegistrationVO.getMobileNumber().trim(), getUniCodeMessage(StringEscapeUtils.unescapeJava("\u0C24\u0C46\u0C32\u0C41\u0C17\u0C41 \u0C26\u0C47\u0C36\u0C02 \u0C2A\u0C3E\u0C30\u0C4D\u0C1F\u0C40 \u0C15\u0C3E\u0C30\u0C4D\u0C2F\u0C15\u0C30\u0C4D\u0C24\u0C17\u0C3E \u0C28\u0C2E\u0C4B\u0C26\u0C41 \u0C1A\u0C47\u0C38\u0C41\u0C15\u0C41\u0C28\u0C4D\u0C28\u0C02\u0C26\u0C41\u0C15\u0C41 \u0C27\u0C28\u0C4D\u0C2F\u0C35\u0C3E\u0C26\u0C3E\u0C32\u0C41. \u0C2E\u0C40 \u0C2F\u0C4A\u0C15\u0C4D\u0C15 \u0C30\u0C3F\u0C2B\u0C30\u0C46\u0C28\u0C4D\u0C38\u0C4D \u0C28\u0C46\u0C02\u0C2C\u0C30\u0C4D : ")+cadreRegistrationVO.getRefNo()));
				}
		} catch (Exception e) {
			LOG.error("Exception raised in tdpCadreSavingLogic in CadreRegistrationService service", e);
		}
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
			tdpCadre.setMobileNo(null);
			tdpCadre.setLatitude(null);
			tdpCadre.setLongititude(null);
			tdpCadre.setSurveyTime(null);
			tdpCadre.setUniqueKey(null);
			tdpCadre.setImage(null);
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
	public void getVoterAddressDetails(Long voterId,UserAddress userAddress,String street)
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
					if(booth.getLocalBodyWard() != null)
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
					if(street != null && !street.equalsIgnoreCase("null") && street.trim().length() > 0)
					{
						userAddress.setStreet(street);
					}
					
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getVoterAddressDetails in CadreRegistrationService service", e);
		}
	}
	

	public List<VoterInfoVO> getSearchDetailsCadreRegistration(Long constituencyId, String seachType, String candidateName, String voterCardId, String houseNo,Long panchayatId,Long boothId,String isPresentCadre)
	{
		
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
					searchQuery.append("  UA.houseNo like '%"+houseNo+"%' and" );
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
						
						if(voter[7] != null)
						{
							try {
								
								Voter voterEntity = voterDAO.getVoterByVoterID(voter[7]!= null? Long.valueOf(voter[7].toString()):0L);
								
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
	
	public List<VoterInfoVO> getCandidateInfoBySearchCriteria(String searchType, Long candidateId)
	{
		List<VoterInfoVO> returnList = null;
		SimpleDateFormat format  = new SimpleDateFormat("yy/MM/dd");
		VoterInfoVO vo = null;
		try {
			
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
								
								vo.setBlodGroupId(tdpCadre.getBloodGroup() != null ? tdpCadre.getBloodGroupId():0L);
								
								vo.setCasteId(tdpCadre.getCasteState() != null ? tdpCadre.getCasteState().getCasteStateId():0L);							
								vo.setCasteName(tdpCadre.getCasteState() != null ? tdpCadre.getCasteState().getCaste().getCasteName().toString():"");	
								vo.setDateOfBirth(tdpCadre.getDateOfBirth() != null ? new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yy-MM-dd").parse(tdpCadre.getDateOfBirth().toString())):"");
								vo.setOccupationId(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupationId():0L);
								vo.setOccupation(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupation():"");
								
								vo.setEducation(tdpCadre.getEducationalQualifications() != null ? tdpCadre.getEducationalQualifications().getEduQualificationId().toString():"0");
								
								vo.setLocation(tdpCadre.getUserAddress() != null ? (tdpCadre.getUserAddress().getStreet() != null ?tdpCadre.getUserAddress().getStreet().toString():""):"");
								vo.setMobileNo(tdpCadre.getMobileNo() != null ? tdpCadre.getMobileNo():"");
								vo.setMemberShipId(tdpCadre.getPreviousEnrollmentNo() != null ? tdpCadre.getPreviousEnrollmentNo().toString():"");
								vo.setActiveDate(tdpCadre.getPartyMemberSince() != null ? new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yy-MM-dd").parse(tdpCadre.getPartyMemberSince().toString())):"");
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
								vo.setCadreId(tdpCadre.getTdpCadreId());
								Date dateOfBirth = tdpCadre.getDateOfBirth() != null ? tdpCadre.getDateOfBirth():null;
								vo.setDateOfBirth(dateOfBirth != null ? new SimpleDateFormat("dd-MM-yyyy").format(dateOfBirth):"");
								
								if(tdpCadre.getVoter() != null)
									
								{
									vo.setHouseNo(tdpCadre.getVoter().getHouseNo());
									vo.setName(tdpCadre.getVoter().getName());
									vo.setRelativeName(tdpCadre.getVoter().getRelativeName());
									vo.setRelationType(tdpCadre.getVoter().getRelationshipType());
									vo.setAge(tdpCadre.getVoter().getAge().toString());
									vo.setGender(tdpCadre.getVoter().getGender());
									vo.setVoterId(tdpCadre.getVoter() != null ? tdpCadre.getVoter().getVoterId(): 0L);
									vo.setVoterCardNo(tdpCadre.getVoter() != null ? tdpCadre.getVoter().getVoterIDCardNo():"");
									
									voterId = tdpCadre.getVoter() != null ? tdpCadre.getVoter().getVoterId(): 0L;
									houseNo = tdpCadre.getVoter().getHouseNo() != null ? tdpCadre.getVoter().getHouseNo().toString():"";								
								}
								else
								{
									vo.setName(tdpCadre.getFirstname() != null? tdpCadre.getFirstname():"");
									vo.setRelativeName(tdpCadre.getRelativename() != null ? tdpCadre.getRelativename():"");
									vo.setGender(tdpCadre.getGender() != null ? tdpCadre.getGender().toString():"");
									
									vo.setVoterId(0L);
									vo.setVoterCardNo("");
									vo.setHouseNo("");
									vo.setRelationType("");
									
									if(dateOfBirth != null)
									{
										Calendar startDate = new GregorianCalendar();
										Calendar endDate = new GregorianCalendar();
										
										startDate.setTime(dateOfBirth);
										
										endDate.setTime(new Date());

										int diffYear = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
										
										vo.setAge(String.valueOf(diffYear));
									}
									else if(tdpCadre.getAge() != null && tdpCadre.getAge().toString().trim().length()>0 )
									{
										vo.setAge(tdpCadre.getAge().toString());
									}
								}
								
								vo.setBlodGroupId(tdpCadre.getBloodGroup() != null ? tdpCadre.getBloodGroupId():0L);
								
								vo.setCasteId(tdpCadre.getCasteState() != null ? tdpCadre.getCasteState().getCasteStateId():0L);							
								vo.setCasteName(tdpCadre.getCasteState() != null ? tdpCadre.getCasteState().getCaste().getCasteName().toString():"");	
								
								vo.setOccupationId(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupationId():0L);
								vo.setOccupation(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupation():"");
								
								vo.setEducation(tdpCadre.getEducationalQualifications() != null ? tdpCadre.getEducationalQualifications().getEduQualificationId().toString():"0");
								
								vo.setLocation(tdpCadre.getUserAddress() != null ? (tdpCadre.getUserAddress().getStreet() != null ?tdpCadre.getUserAddress().getStreet().toString():""):"");
								vo.setMobileNo(tdpCadre.getMobileNo() != null ? tdpCadre.getMobileNo():"");
								vo.setMemberShipId(tdpCadre.getMemberShipNo() != null ? tdpCadre.getMemberShipNo().toString():"");
								vo.setActiveDate(tdpCadre.getPartyMemberSince() != null ? new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("yy-MM-dd").parse(tdpCadre.getPartyMemberSince().toString())):"");
								vo.setAadharNo(tdpCadre.getAadheerNo() != null ? tdpCadre.getAadheerNo():"");
								vo.setNomineeName(tdpCadre.getNomineeName() != null ? tdpCadre.getNomineeName():"");
								vo.setVoterRelationId(tdpCadre.getVoterRelationId() != null ? tdpCadre.getVoterRelationId():0L);
								vo.setNomineAge(tdpCadre.getNomineeAge() != null ? tdpCadre.getNomineeAge().toString():"");
								//vo.setNomineeGender(tdpCadre.getNomineeGender() != null ? Long.valueOf(tdpCadre.getNomineeGender()):0L);
								
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
								
							} catch (Exception e) {
								LOG.error("Exception raised in getCandidateInfoBySearchCriteria in CadreRegistrationService service", e);
							}
						
						existingFamilyInfo =  getExistingCadreFamilyInfo(tdpCadre.getTdpCadreId());
						existingParticipationDetails = getExistingCadreParticipationInfo(tdpCadre.getTdpCadreId());
						existingrollsDetails = getExistingRollsInfo(tdpCadre.getTdpCadreId());
						
						
					}
				}
				
				if(voterId != null && voterId.longValue() != 0L)
				{
					List<Long> votersList = new ArrayList<Long>();
					votersList.add(voterId);
					
					List<Object[]> voterInfo = boothPublicationVoterDAO.getBoothIdsDetailsOfVoterIds(votersList, IConstants.VOTER_DATA_PUBLICATION_ID);
					
					if(voterInfo != null && voterInfo.size()>0)
					{
						Object boothPublicationVoter = voterInfo.get(0);
						Object[] boothInfo = (Object[]) boothPublicationVoter;
						
						Long boothId = boothInfo[2] != null ?Long.valueOf(boothInfo[2].toString()):0L;
						
						if((houseNo != null && houseNo.toString().trim().length()>0) && (boothId != null && boothId.longValue() >0L))
						{
							List<Object[]> familyInfo = boothPublicationVoterDAO.getFamilyDetaislByHouseNoAndBoothId(boothId,houseNo);
							
							if(familyInfo != null && familyInfo.size()>0)
							{
								familyList = new ArrayList<VoterInfoVO>();
								
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
										
										VoterInfoVO existingFamilyMember = getmatchedVOByVoterId(existingFamilyInfo,family[0] != null ? Long.valueOf(family[0].toString().trim()):0L);
										
										if(existingFamilyMember != null)
										{
											fmilyVO.setEducation(existingFamilyMember.getEducation());
											fmilyVO.setOccupation(existingFamilyMember.getOccupation());
											fmilyVO.setOccupationId(existingFamilyMember.getOccupationId());
											existingFamilyInfo.remove(existingFamilyMember);
										}
										
										
										familyList.add(fmilyVO);
									}
								}
								
								if(existingFamilyInfo != null && existingFamilyInfo.size()>0)
								{
									for (VoterInfoVO family : existingFamilyInfo) 
									{
										
										if( family.getVoterId().longValue() != voterId.longValue())
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
								vo.setVoterInfoVOList(familyList);
							}
						}
					} 
					
				}
			}
			else
			{
				returnList = new ArrayList<VoterInfoVO>();
				vo = new VoterInfoVO();
				vo.setCadreId(0L);
				vo.setDateOfBirth("01-01-1980");	
				vo.setHouseNo("");
				vo.setName("");
				vo.setRelativeName("");
				vo.setRelationType("");
				vo.setAge("");
				vo.setGender(null);						
				vo.setVoterId(0L);
				vo.setVoterCardNo("");						
				vo.setBlodGroupId(0L);						
				vo.setCasteId(0L);							
				vo.setCasteName("");						
				vo.setOccupationId(0L);
				vo.setOccupation("");						
				vo.setEducation("0");						
				vo.setLocation("");
				vo.setMobileNo("");
				vo.setMemberShipId("");
				vo.setActiveDate("01-01-1980");
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
			
			if(participationInfo != null && participationInfo.size()>0)
			{
				for (Object[] participation : participationInfo)
				{
					GenericVO vo = new GenericVO();
					vo.setId(participation[0] != null ? Long.valueOf(participation[0].toString().trim()):0L);		//electionScopeId
					vo.setCount(participation[1] != null ? Long.valueOf(participation[1].toString().trim()):0L);	// election Id
					vo.setRank(participation[2] != null ? Long.valueOf(participation[2].toString().trim()):0L);		// constituencyId
					vo.setNomineeAge(participation[3] != null ? Long.valueOf(participation[3].toString().trim()):0L); // candidate Id
					
					List<BasicVO> constituencyList = constituencyListForElection(vo.getCount(),vo.getRank());
					
					if(constituencyList != null && constituencyList.size()>0)
					{
						vo.getBasicVO().setHamletCasteInfo(constituencyList);
					}
					
					List<BasicVO> participatedCandList = participatedCandList(vo.getCount(),vo.getRank());
					
					if(participatedCandList != null && participatedCandList.size()>0)
					{
						vo.getBasicVO().setHamletVoterInfo(participatedCandList);
					}
					
					List<SelectOptionVO> electionTypeList = getElectionYearsByElectionType(participation[0] != null ? Long.valueOf(participation[0].toString().trim()):0L);
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
	
	/*
	
	public List<GenericVO> getExistingCadreParticipationInfo(Long tdpCadreId)
	{
		List<GenericVO> returnList = new ArrayList<GenericVO>();
		
		try {
			
			List<Object[]> participationInfo = cadreParticipatedElectionDAO.getPreviousParticipationInfoByTdpCadreId(tdpCadreId);
			
			if(participationInfo != null && participationInfo.size()>0)
			{
				for (Object[] participation : participationInfo)
				{
					GenericVO vo = new GenericVO();
					vo.setId(participation[0] != null ? Long.valueOf(participation[0].toString().trim()):0L);		//electionScopeId
					vo.setCount(participation[1] != null ? Long.valueOf(participation[1].toString().trim()):0L);	// election Id
					vo.setRank(participation[2] != null ? Long.valueOf(participation[2].toString().trim()):0L);		// constituencyId
					
					List<SelectOptionVO> electionTypeList = getElectionYearsByElectionType(participation[0] != null ? Long.valueOf(participation[0].toString().trim()):0L);
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
	*/
	public List<GenericVO> getExistingRollsInfo(Long tdpCadreId)
	{
		List<GenericVO> returnList = new ArrayList<GenericVO>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		try {
			
			List<Object[]> participationInfo = cadrePreviousRolesDAO.getexistingRolesForTdpCadreByTdpCadreId(tdpCadreId);
			
			if(participationInfo != null && participationInfo.size()>0)
			{
				for (Object[] participation : participationInfo)
				{
					GenericVO vo = new GenericVO();
					vo.setId(participation[0] != null ? Long.valueOf(participation[0].toString().trim()):0L);		//Committe Level Id
					vo.setCount(participation[1] != null ? Long.valueOf(participation[1].toString().trim()):0L);	// Committe Id
					vo.setRank(participation[2] != null ? Long.valueOf(participation[2].toString().trim()):0L);	// Committe role id
					vo.setStartTime(participation[3] != null ?format.format(format1.parse(participation[3].toString())):"");		// from date
					vo.setEndTime(participation[4] != null ? format.format(format1.parse(participation[4].toString())):"");		// to date 
					
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
				List<Object[]> cadreFamilyInfo = tdpCadreFamilyDetailsDAO.getCadreFamilyDetailsBytdpCadreId(tdpCadreId);

				if(cadreFamilyInfo != null && cadreFamilyInfo.size()>0)
				{
					returnList = new ArrayList<VoterInfoVO>();
					
					for (Object[] voter : cadreFamilyInfo) 
					{
						VoterInfoVO voterVO = new VoterInfoVO();
						Voter voter1 = (Voter) (voter[0] != null ? voter[0]:null);
						
						if(voter1 != null)
						{
							voterVO.setVoterId(voter1 != null ? voter1.getVoterId():0L);
							voterVO.setName(voter1.getName() != null ? voter1.getName():"");
							voterVO.setAge(voter1.getAge() != null ? voter1.getAge().toString():"");
							voterVO.setGender(voter1.getGender() != null ? voter1.getGender():"");
							voterVO.setVoterCardNo(voter1.getVoterIDCardNo() != null ? voter1.getVoterIDCardNo():"");
						}
						else
						{
							voterVO.setName(voter[3] != null ? voter[3].toString().trim():"");
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
								
								vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L);
								vo.setName(param[1] != null ? param[1].toString():"");
								
								returnList.add(vo);

							}
						}
						
						List<Object[]> localelectinbody = localElectionBodyDAO.getMuncipalitiesAndCorporationsInAConstituency(tehsilIds);
						
						if(localelectinbody != null && localelectinbody.size()>0)
						{
							for (Object[] param : localelectinbody)
							{
								GenericVO vo = new GenericVO();
								
								vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L);
								vo.setName(param[1] != null ? param[1].toString()+" Muncipality/Corporation ":"");
								
								returnList.add(vo);
								
							}
						}
					}
					
					
				}
				else if(araaType != null && araaType.equalsIgnoreCase("URBAN"))
				{
					/*List<Object[]> boothList = boothDAO.getAllBoothsInUrban(constituencyId, IConstants.VOTER_DATA_PUBLICATION_ID);
					
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
					*/
					
					if(constituency != null )
					{
							GenericVO vo = new GenericVO();
							
							vo.setId(constituency.getConstituencyId());
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
			}
			
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
	
	public List<GenericVO> getExistingCadreInfo(String candidateName,Long constituencyId,Long panchayatId,Long boothId,String isPresentCadre){
 		LOG.info("Entered into getExistingCadreInfo method in CadreRegistrationService class");
 		List<GenericVO>  returnList = null;
 		try {
			
 			List<Object[]> cadreInfo = tdpCadreDAO.getexistringCadreInfoByLocation(candidateName,constituencyId,panchayatId,boothId,isPresentCadre);
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
		String ref="TRW"+userId;
		if(registrationType.equalsIgnoreCase("ONLINE")){
			ref="TRO"+userId;
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
	
	private  String sendSMSInTelugu(String mobileNo,String message){

		HttpClient client = new HttpClient(new MultiThreadedHttpConnectionManager());
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
			Integer.parseInt("30000"));
	
		boolean isEnglish = false;
		
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
		if(cadreRegistrationVO.getUploadImage() != null && !cadreRegistrationVO.getUploadImage().toString().equalsIgnoreCase("null"))
		{
			String result = uploadCadreImage(tdpCadre.getMemberShipNo() , cadreRegistrationVO.getPath() , cadreRegistrationVO.getUploadImageContentType() , cadreRegistrationVO.getUploadImage());
			if(result != null)
			{
				tdpCadre.setImage(tdpCadre.getMemberShipNo()+"."+cadreRegistrationVO.getUploadImageContentType().split("/")[1]);
			}
		}
		
		if(registrationType.equalsIgnoreCase("TAB") && cadreRegistrationVO.getImageBase64String() != null && 
				cadreRegistrationVO.getImageBase64String().trim().length() > 0)
		{
				String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
				String filePath = cadreRegistrationVO.getPath() + "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
				boolean status = imageAndStringConverter.convertBase64StringToImage(cadreRegistrationVO.getImageBase64String(),filePath);
				
				if(status)
					tdpCadre.setImage(tdpCadre.getMemberShipNo()+".jpg");
		}
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
				returnVO.setVoterName(voterIdDetails.get(0)[2] != null ? voterIdDetails.get(0)[2].toString() : "");
				returnVO.setRelativeName(voterIdDetails.get(0)[3] != null ? voterIdDetails.get(0)[3].toString() : "");
				returnVO.setVoterId(voterIdDetails.get(0)[4] != null ? (Long)voterIdDetails.get(0)[4] : 0l);
				returnVO.setVoterCardNo(voterIdDetails.get(0)[5] != null ? voterIdDetails.get(0)[5].toString() : "");
				returnVO.setVillageEng(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getPanchayatName() : "");
				returnVO.setMandalEng(userAddress.getTehsil() != null ?  userAddress.getTehsil().getTehsilName() :"");
				returnVO.setConstiEng(userAddress.getConstituency() != null ?  userAddress.getConstituency().getName()  : "");
				returnVO.setDistrictEng(userAddress.getDistrict() != null ?  userAddress.getDistrict().getDistrictName() :"");
				
				
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
					cadrePrintVO.setVillageEng(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getPanchayatName() : "");
					cadrePrintVO.setMandalEng(userAddress.getTehsil() != null ?  userAddress.getTehsil().getTehsilName() :"");
					cadrePrintVO.setConstiEng(userAddress.getConstituency() != null ?  userAddress.getConstituency().getName()  : "");
					cadrePrintVO.setDistrictEng(userAddress.getDistrict() != null ?  userAddress.getDistrict().getDistrictName() :"");
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
	
}
