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
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBloodGroupDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreAddressDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ISmsJobStatusDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreFamilyDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreTeluguNamesDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterNamesDAO;
import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;
import com.itgrids.partyanalyst.dto.VoterInfoVO;
import com.itgrids.partyanalyst.model.BloodGroup;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.CadreAddress;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.SmsJobStatus;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.ICadreRegistrationForOtherStatesService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;

public class CadreRegistrationForOtherStatesService implements
		ICadreRegistrationForOtherStatesService {
	private static final Logger LOG = Logger.getLogger(CadreRegistrationForOtherStatesService.class);
	private ITdpCadreDAO tdpCadreDAO;
	private TransactionTemplate transactionTemplate;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IUserAddressDAO userAddressDAO;
	private ITehsilDAO tehsilDAO;
	private IBoothDAO boothDAO;
	private IVoterNamesDAO voterNamesDAO;
	private DateUtilService dateUtilService = new DateUtilService();

	private IVoterDAO voterDAO;
	private IConstituencyDAO constituencyDAO;
    private ITdpCadreTeluguNamesDAO tdpCadreTeluguNamesDAO;
	private ITdpCadreFamilyDetailsDAO tdpCadreFamilyDetailsDAO;
	private ImageAndStringConverter imageAndStringConverter = new ImageAndStringConverter();
	private ISmsJobStatusDAO smsJobStatusDAO;
	private ICasteStateDAO casteStateDAO;
	private IBloodGroupDAO bloodGroupDAO;
	private IDistrictDAO  districtDAO;
	private ICadreAddressDAO cadreAddressDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IStateDAO stateDAO;
	
	
	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public ITdpCadreFamilyDetailsDAO getTdpCadreFamilyDetailsDAO() {
		return tdpCadreFamilyDetailsDAO;
	}

	public void setTdpCadreFamilyDetailsDAO(
			ITdpCadreFamilyDetailsDAO tdpCadreFamilyDetailsDAO) {
		this.tdpCadreFamilyDetailsDAO = tdpCadreFamilyDetailsDAO;
	}

	public ISmsJobStatusDAO getSmsJobStatusDAO() {
		return smsJobStatusDAO;
	}

	public void setSmsJobStatusDAO(ISmsJobStatusDAO smsJobStatusDAO) {
		this.smsJobStatusDAO = smsJobStatusDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public void setVoterNamesDAO(IVoterNamesDAO voterNamesDAO) {
		this.voterNamesDAO = voterNamesDAO;
	}

	public void setTdpCadreTeluguNamesDAO(
			ITdpCadreTeluguNamesDAO tdpCadreTeluguNamesDAO) {
		this.tdpCadreTeluguNamesDAO = tdpCadreTeluguNamesDAO;
	}

	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}

	public void setBloodGroupDAO(IBloodGroupDAO bloodGroupDAO) {
		this.bloodGroupDAO = bloodGroupDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public void setCadreAddressDAO(ICadreAddressDAO cadreAddressDAO) {
		this.cadreAddressDAO = cadreAddressDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
//9999
	public void tdpCadreSavingLogic(final AddressVO addressVO,final CadreRegistrationVO cadreRegistrationVO,final SurveyCadreResponceVO surveyCadreResponceVO,final String insertType, final boolean statusVar,final String registrationType) {
	  try{
		if(cadreRegistrationVO.getVoterId() != null && cadreRegistrationVO.getVoterId().trim().length() > 0){
			String status = checkVoterAlreadyRegisteredOrNot(Long.valueOf(cadreRegistrationVO.getVoterId().trim()));
			if(status.equalsIgnoreCase("alreadyRegistered")){
				surveyCadreResponceVO.setStatus("alreadyRegistered");
			}
		}
		final TdpCadre tdpCadre = new TdpCadre();
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				TdpCadre tdpCadre1 = null;
                tdpCadre.setDataSourceType("WEB");
				
				if (cadreRegistrationVO.getVoterName() != null && cadreRegistrationVO.getVoterName().trim().length() > 0) {
					tdpCadre.setFirstname(cadreRegistrationVO.getVoterName());
				}
				if (cadreRegistrationVO.getDobStr() != null && cadreRegistrationVO.getDobStr().trim().length() > 0) {
					tdpCadre.setDateOfBirth(convertToDateFormet(cadreRegistrationVO.getDobStr().toString()));
				}
				
				tdpCadre.setGender(cadreRegistrationVO.getGender());

				if (cadreRegistrationVO.getRelativeName() != null && cadreRegistrationVO.getRelativeName().trim().length() > 0) {
					tdpCadre.setRelativename(cadreRegistrationVO.getRelativeName());
				}
				if(registrationType != null && (registrationType.trim().equalsIgnoreCase("voterId")))
                {
					if (cadreRegistrationVO.getVoterId() != null && Long.valueOf(cadreRegistrationVO.getVoterId()) > 0) {
						tdpCadre.setVoterId(Long.valueOf(cadreRegistrationVO.getVoterId()));
					}
					if (cadreRegistrationVO.getAge() != null && cadreRegistrationVO.getAge() > 0) {
						tdpCadre.setAge(cadreRegistrationVO.getAge());
					} else if (tdpCadre.getVoterId() != null && cadreRegistrationVO.getCadreType() != null && cadreRegistrationVO.getCadreType().trim().equalsIgnoreCase("voter")) {
						tdpCadre.setAge(voterDAO.get(tdpCadre.getVoterId()).getAge());
					} 
                }
				else if(registrationType != null && (registrationType.trim().equalsIgnoreCase("skip")))
				{
					if (cadreRegistrationVO.getVoterCardNumber() != null  && cadreRegistrationVO.getVoterCardNumber().trim().length() > 0) {
						tdpCadre.setCardNo(cadreRegistrationVO.getVoterCardNumber());
							/*
							 * List<String> voterCardNo = new ArrayList<String>();
							voterCardNo.add(cadreRegistrationVO.getVoterCardNumber());
							List<Object[]> voterDetils = voterDAO.getVoterIdCardNo(voterCardNo);
							if(voterDetils != null && voterDetils.size()>0)
							{
								for (Object[] voters : voterDetils) {
									Long voterId = voters[1] != null ? Long.valueOf(voters[1].toString().trim()):0L;
									if(voterId != null && voterId.longValue()>0)
									{
										//tdpCadre.setVoterId(voterId);
										
										tdpCadre.setAge(voterDAO.get(tdpCadre.getVoterId()).getAge());
									}
								}
							}
							
			             	*/		
					}
					
					if (tdpCadre.getAge() == null || tdpCadre.getAge().longValue() == 0l) {
						tdpCadre.setAge(cadreRegistrationVO.getAge());
					}
				}
							
				if (cadreRegistrationVO.getVoterCardNumber() != null  && cadreRegistrationVO.getVoterCardNumber().trim().length() > 0) {
					tdpCadre.setCardNo(cadreRegistrationVO.getVoterCardNumber());
				}
				
				if (tdpCadre.getAge() == null || tdpCadre.getAge().longValue() == 0l) {
					tdpCadre.setAge(cadreRegistrationVO.getAge());
				}
				
				if (cadreRegistrationVO.getPartyMemberSinceStr() != null && cadreRegistrationVO.getPartyMemberSinceStr().trim().length() > 0) {
					tdpCadre.setPartyMemberSince(convertToDateFormet(cadreRegistrationVO.getPartyMemberSinceStr()));
				} 
				if (cadreRegistrationVO.getBloodGroupId() != null && cadreRegistrationVO.getBloodGroupId().longValue() > 0) {
					tdpCadre.setBloodGroupId(cadreRegistrationVO.getBloodGroupId());
				}

				if (cadreRegistrationVO.getEmailId() != null && cadreRegistrationVO.getEmailId().trim().length()>0) {
					tdpCadre.setEmailId(cadreRegistrationVO.getEmailId());
				}
				
				if (cadreRegistrationVO.getCasteId() != null && cadreRegistrationVO.getCasteId() > 0) {
					tdpCadre.setCasteStateId(cadreRegistrationVO.getCasteId());
				}
				
				if (cadreRegistrationVO.getMobileNumber() != null && cadreRegistrationVO.getMobileNumber().trim().length() > 0) {
					tdpCadre.setMobileNo(cadreRegistrationVO.getMobileNumber());
				}
				if (cadreRegistrationVO.getEducationId() != null && cadreRegistrationVO.getEducationId().longValue() > 0) {
					tdpCadre.setEducationId(cadreRegistrationVO.getEducationId());
				}

				if (cadreRegistrationVO.getOccupationId() != null && cadreRegistrationVO.getOccupationId().longValue() > 0) {
					tdpCadre.setOccupationId(cadreRegistrationVO.getOccupationId());
				}
				if (cadreRegistrationVO.getHouseNo() != null && cadreRegistrationVO.getHouseNo().trim().length() > 0) {
					tdpCadre.setHouseNo(cadreRegistrationVO.getHouseNo());
				} 
				
				if (cadreRegistrationVO.getCreatedUserId() != null && cadreRegistrationVO.getCreatedUserId().longValue() > 0) {
					if (!insertType.equalsIgnoreCase("update")) {
						tdpCadre.setInsertedWebUserId(cadreRegistrationVO.getCreatedUserId().longValue());
					} else {
						tdpCadre.setUpdatedWebUserId(cadreRegistrationVO.getCreatedUserId().longValue());
					}
				}
		
               Long reqVoterId = null;
               if(registrationType != null && (!registrationType.trim().equalsIgnoreCase("skip") && !registrationType.trim().equalsIgnoreCase("apVoterId")))
               {
            	   if(cadreRegistrationVO.getVoterId() != null && cadreRegistrationVO.getVoterId().trim().length() > 0){
                	   reqVoterId = Long.valueOf(cadreRegistrationVO.getVoterId().trim());
                   }else if(cadreRegistrationVO.getFamilyVoterId() != null && cadreRegistrationVO.getFamilyVoterId().longValue() > 0){
                	   reqVoterId = cadreRegistrationVO.getFamilyVoterId();
                   }
               }
               
               if(registrationType != null && registrationType.trim().equalsIgnoreCase("skip")){
            	   if(tdpCadre.getVoterId() != null && tdpCadre.getVoterId().longValue() != 0l)
            		   reqVoterId = tdpCadre.getVoterId();
               }
               
				UserAddress userAddress = new UserAddress();//if(stateId == 29)
				if(addressVO.getStateId() != null && addressVO.getStateId().longValue() >0)
				{
					userAddress.setState(stateDAO.get(addressVO.getStateId()));
				}
				//else
				//{
					if(reqVoterId != null){
						getUserAddressDetails(reqVoterId,userAddress);
					}else{
						if(cadreRegistrationVO.getConstituencyId() != null && cadreRegistrationVO.getConstituencyId().trim().length() > 0){
							
							Constituency constituency = constituencyDAO.get(Long.valueOf(cadreRegistrationVO.getConstituencyId().trim()));
							userAddress.setConstituency(constituency);
							userAddress.setState(constituency.getState());
							userAddress.setDistrict(constituency.getDistrict());
						}
						if(cadreRegistrationVO.getBoothId() != null && cadreRegistrationVO.getBoothId().trim().length() > 0 && Long.valueOf(cadreRegistrationVO.getBoothId().trim())>0){
							userAddress.setBooth(boothDAO.get(Long.valueOf(cadreRegistrationVO.getBoothId().trim())));
						}
						if(cadreRegistrationVO.getMuncipalityId() != null && cadreRegistrationVO.getMuncipalityId().trim().length() > 0  && Long.valueOf(cadreRegistrationVO.getMuncipalityId().trim())>0){
							
							if(cadreRegistrationVO.getMuncipalityId().trim().substring(0,1).equalsIgnoreCase("1")){
								userAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(cadreRegistrationVO.getMuncipalityId().trim().substring(1))));
							}else{
								userAddress.setTehsil(tehsilDAO.get(Long.valueOf(cadreRegistrationVO.getMuncipalityId().trim().substring(1))));
							}
						}
					}
				//}
				
				if(addressVO.getStreet() != null && addressVO.getStreet().trim().length()>0)
				{
					String street = addressVO.getStreet() != null && addressVO.getStreet().trim().length()>0?addressVO.getStreet():"";
					String colony = addressVO.getAddress1() != null && addressVO.getAddress1().trim().length()>0?addressVO.getAddress1():"";
					String landmark = addressVO.getAddress2() != null && addressVO.getAddress2().trim().length()>0?addressVO.getAddress2():"";
					
					userAddress.setStreet(street+" , "+colony+" ,"+landmark);
				}
				
				userAddress = userAddressDAO.save(userAddress);
				tdpCadre.setUserAddress(userAddress);

				if (cadreRegistrationVO.getNomineeName() != null && cadreRegistrationVO.getNomineeName().trim().length() > 0) {
					tdpCadre.setNomineeName(cadreRegistrationVO.getNomineeName());
				}

					tdpCadre.setAadheerNo(cadreRegistrationVO.getAadheerNo());
				if (cadreRegistrationVO.getVoterRelationId() != null && cadreRegistrationVO.getVoterRelationId().longValue() > 0) {
					tdpCadre.setVoterRelationId(cadreRegistrationVO.getVoterRelationId());
				}
				
				if (!insertType.equalsIgnoreCase("update") && tdpCadre.getInsertedTime() == null) {
					tdpCadre.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				}
				tdpCadre.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				tdpCadre.setEnrollmentYear(IConstants.CADRE_ENROLLMENT_YEAR_FOROTHER_STATES);

				tdpCadre.setIsDeleted("N");
				tdpCadre.setSurveyTime(tdpCadre.getInsertedTime());

				if (cadreRegistrationVO.getNomineeAge() != null && cadreRegistrationVO.getNomineeAge().longValue() > 0) {
					tdpCadre.setNomineeAge(cadreRegistrationVO.getNomineeAge());
				}
				
				if (cadreRegistrationVO.getNomineeGender() != null	&& cadreRegistrationVO.getNomineeGender().trim().length() > 0) {
					if (cadreRegistrationVO.getNomineeGender().trim().equalsIgnoreCase("1")) {
						tdpCadre.setNomineeGender("Male");
					} else {
						tdpCadre.setNomineeGender("Female");
					}
				}
				
				if (cadreRegistrationVO.getCandidateAadherNo() != null && cadreRegistrationVO.getCandidateAadherNo().trim().length() > 0) {
					tdpCadre.setCadreAadherNo(cadreRegistrationVO.getCandidateAadherNo());
				}
				
				if (cadreRegistrationVO.getRelationType() != null  && cadreRegistrationVO.getRelationType().trim().length() > 0) {
					tdpCadre.setRelativeType(cadreRegistrationVO.getRelationType());
				}
				if (cadreRegistrationVO.getVoterCardNumber() != null  && cadreRegistrationVO.getVoterCardNumber().trim().length() > 0) {
					tdpCadre.setCardNo(cadreRegistrationVO.getVoterCardNumber());
				}
				
				
				if (cadreRegistrationVO.getFamilyVoterId() != null){
					tdpCadre.setFamilyVoterId(cadreRegistrationVO.getFamilyVoterId());
					tdpCadre.setIsRelative("Y");
					tdpCadre.setRelationTypeId(cadreRegistrationVO.getRelationTypeId());
				}
	
					String userId = "0000";
					if (cadreRegistrationVO.getCreatedUserId() != null) {
						userId = cadreRegistrationVO.getCreatedUserId().toString();
					}
					String ref = getReferenceNo(userId);

					if (tdpCadre.getRefNo() == null || tdpCadre.getRefNo().trim().length() == 0) {
						ref = getUniueRefNo(ref);
						tdpCadre.setRefNo(ref);
					}
					cadreRegistrationVO.setRefNo(tdpCadre.getRefNo());

					surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
					// uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre);
					tdpCadre1 = tdpCadreDAO.save(tdpCadre);

				if (tdpCadre1.getMemberShipNo() == null || tdpCadre1.getMemberShipNo().trim().length() == 0) {
					String membershipNo = getMemberShipNo(tdpCadre1.getTdpCadreId());
					/*if(tdpCadre1.getUserAddress() != null)
					{
						if(tdpCadre1.getUserAddress().getState() != null)
						{
							Long stateId = tdpCadre1.getUserAddress().getState().getStateId();
							if(stateId == 12)
							{
								membershipNo = "KA14"+membershipNo;
							}
							else if(stateId == 29)
							{
								membershipNo = "AN14"+membershipNo;
							}
							else if(stateId == 24)
							{
								membershipNo = "TN14"+membershipNo;
							}
							else if(stateId == 15)
							{
								membershipNo = "MH14"+membershipNo;
							}
							else if(stateId == 14)
							{
								membershipNo = "MP14"+membershipNo;
							}
							else if(stateId == 13)
							{
								membershipNo = "KR14"+membershipNo;
							}
						}
					}			*/		
					tdpCadre1.setMemberShipNo(membershipNo);
				}
				uploadProfileImage(cadreRegistrationVO,tdpCadre1);
				tdpCadre1 = tdpCadreDAO.save(tdpCadre1);
				
				//Saving Postal Address Info
				CadreAddress cadreAddress = new CadreAddress();
				cadreAddress.setTdpCadreId(tdpCadre1.getTdpCadreId());
				cadreAddress.setStateId(districtDAO.get(addressVO.getDistrictId()).getState().getStateId());
				cadreAddress.setDistrictId(addressVO.getDistrictId());
				
				if(addressVO.getTehsilId() != null && addressVO.getTehsilId().longValue() > 0){
					if(addressVO.getTehsilId().toString().trim().substring(0,1).equalsIgnoreCase("1")){
						cadreAddress.setLocalElectionBodyId(new Long(addressVO.getTehsilId().toString().trim().substring(1)));
					}else{
				        cadreAddress.setTehsilId(new Long(addressVO.getTehsilId().toString().trim().substring(1)));
					}
				  
				}
				cadreAddress.setVillage(addressVO.getHamletName());
				cadreAddress.setHouseNo(addressVO.getHouseNo());
				cadreAddress.setPincode(addressVO.getPinCode());
				cadreAddress.setLandmark(addressVO.getStreet());
				cadreAddressDAO.save(cadreAddress);
				// Postal Address Info Saving End
				
				//SAVING THE TELUGU NAME OF NON VOTER -- START //SASI
			/*	if(tdpCadre1.getVoterId() == null && cadreRegistrationVO.getVoterTeluguName()!= null && cadreRegistrationVO.getVoterTeluguName().trim().length() > 0){
					TdpCadreTeluguNames model = new TdpCadreTeluguNames();
					model.setTeluguName(cadreRegistrationVO.getVoterTeluguName().trim());
					model.setTdpCadreId(tdpCadre1.getTdpCadreId());
					model.setEnglishName(cadreRegistrationVO.getVoterName());
					model.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					tdpCadreTeluguNamesDAO.save(model);
				}*/
				//SAVING THE TELUGU NAME OF NON VOTER -- END

				surveyCadreResponceVO.setEnrollmentNumber(tdpCadre1.getRefNo());
				/*
				List<CadreFamilyVO> cadreFamilyDetailsList = cadreRegistrationVO.getCadreFamilyDetails();
				if (cadreFamilyDetailsList != null && cadreFamilyDetailsList.size() > 0) {
					for (CadreFamilyVO cadreFamilyVO : cadreFamilyDetailsList) {
						if (tdpCadre1 != null) {
							if (cadreFamilyVO != null) {
								
								TdpCadreFamilyDetails tdpCadreFamilyDetails = new TdpCadreFamilyDetails();
								tdpCadreFamilyDetails.setTdpCadreId(tdpCadre1.getTdpCadreId());
								if (cadreFamilyVO.getVoterName() != null && cadreFamilyVO.getVoterName().trim().length() > 0) {
									tdpCadreFamilyDetails.setVoterName(cadreFamilyVO.getVoterName());
								}
								if (cadreFamilyVO.getOccupationId() != null && cadreFamilyVO.getOccupationId().longValue() > 0l) {
									tdpCadreFamilyDetails.setOccupationId(cadreFamilyVO.getOccupationId());
								}
								if (cadreFamilyVO.getEducationId() != null && cadreFamilyVO.getEducationId().longValue() > 0l) {
									tdpCadreFamilyDetails.setEducationId(cadreFamilyVO.getEducationId());
								}
								if (cadreFamilyVO.getAge() != null && cadreFamilyVO.getAge().longValue() > 0l) {
									tdpCadreFamilyDetails.setAge(cadreFamilyVO.getAge());
								}
								if (cadreFamilyVO.getGender() != null && cadreFamilyVO.getGender().trim().length() > 0) {
									tdpCadreFamilyDetails.setGender(cadreFamilyVO.getGender().trim());
								}
								if (cadreFamilyVO.getFamilyRelationId() != null && cadreFamilyVO.getFamilyRelationId().longValue() > 0) { 
									tdpCadreFamilyDetails.setFamilyRelationId(cadreFamilyVO.getFamilyRelationId().longValue());
								}
								
								if(registrationType != null && !registrationType.trim().equalsIgnoreCase("noVoterId"))
					               {
										if (cadreFamilyVO.getVoterId() != null && cadreFamilyVO.getVoterId().longValue() > 0l) {
											tdpCadreFamilyDetails.setVoterId(cadreFamilyVO.getVoterId());
											try {
												Voter voter = voterDAO.get(cadreFamilyVO.getVoterId());
												if (tdpCadreFamilyDetails.getGender() == null) {
													tdpCadreFamilyDetails.setGender(voter.getGender());
												}
												if (tdpCadreFamilyDetails.getAge() == null) {
													tdpCadreFamilyDetails.setAge(voter.getAge());
												}
											} catch (Exception e) {
		
											}
										} else {
											if (cadreFamilyVO.getVoterCadreNO() != null	&& cadreFamilyVO.getVoterCadreNO().trim().length() > 0) {
												List<Long> voterCardNumbersList = voterDAO.getVoterId(cadreFamilyVO.getVoterCadreNO());
												if (voterCardNumbersList != null && voterCardNumbersList.size() > 0) {
													tdpCadreFamilyDetails.setVoterId(voterCardNumbersList.get(0));
													Voter voter = voterDAO.get(voterCardNumbersList.get(0));
													if (tdpCadreFamilyDetails.getGender() == null) {
														tdpCadreFamilyDetails.setGender(voter.getGender());
													}
													if (tdpCadreFamilyDetails.getAge() == null) {
														tdpCadreFamilyDetails.setAge(voter.getAge());
													}
												}
		
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
				}/*
				try{
					if(cadreRegistrationVO.getVoterTeluguName()!= null && cadreRegistrationVO.getVoterTeluguName().trim().length() > 0 && tdpCadre.getVoterId() != null &&  tdpCadre.getVoterId().longValue() > 0){
						List<VoterNames> voterNames = voterNamesDAO.gerVoterNamesObjByVoterId(tdpCadre.getVoterId());
						
						if(voterNames != null && voterNames.size() > 0 && voterNames.get(0) != null){
							for(VoterNames voterName:voterNames){
								if(voterName != null){
									voterName.setFirstName(cadreRegistrationVO.getVoterTeluguName().trim());
									voterName.setLastName("");
									voterName.getVoterNamesId();
									voterNamesDAO.save(voterName);
								}
							}
						}else{
							VoterNames voterName = new VoterNames();
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
				}*/
				surveyCadreResponceVO.setStatus("SUCCESS");
				surveyCadreResponceVO.setResultCode(ResultCodeMapper.SUCCESS);
				if (insertType.equalsIgnoreCase("new") && cadreRegistrationVO.getMobileNumber() != null && cadreRegistrationVO.getMobileNumber().trim().length() > 0 && cadreRegistrationVO.getRefNo() != null) {
					//if (!statusVar) {
						try {
								String jobCode = sendSMSInEnglish(cadreRegistrationVO.getMobileNumber().trim(),"Thank you for registering as TDP Cadre.For further queries use below reference no."+cadreRegistrationVO.getRefNo());
								Long tdpCadreId = tdpCadre1.getTdpCadreId();
								if (tdpCadreId != null) {
									if (tdpCadre1.getMobileNo() != null) {
										jobCode = jobCode.replace("OK:", "");
										SmsJobStatus smsJobStatus = new SmsJobStatus();
										smsJobStatus.setTdpCadreId(tdpCadreId);

										smsJobStatus.setMobileNumber(tdpCadre1.getMobileNo());
										smsJobStatus.setJobCode(jobCode);
										smsJobStatus.setFromTask("Registration");

										smsJobStatusDAO.save(smsJobStatus);
									}
								}
							

						} catch (Exception e) {
							LOG.error("Exception Raised while sending SMS" + e);
						}

					//}
				}
			}
		});
		surveyCadreResponceVO.setStatus("SUCCESS");
	  }catch(Exception e){
		  LOG.error("Exception Rised in tdpCadreSavingLogic",e);
		  surveyCadreResponceVO.setStatus("error");
	  }
	}

	public Date convertToDateFormet(String dateStr) {
		Date date = null;
		try {
			SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
			date = originalFormat.parse(dateStr);
		} catch (Exception e) {
			LOG.error(
					"Exception raised in convertToDateFormet method in CadreRegistrationAction Action",
					e);
		}
		return date;

	}

	public String getUniueRefNo(String ref) {
		String randomNo = null;

		randomNo = ref + getRandomNo();

		return randomNo;
	}

	public String getRandomNo() {
		String number = "";
		Random randomNum = new Random();
		number = number + randomNum.nextInt(99999999);
		if (number.length() < 8) {
			for (int i = number.length(); i < 6; i++) {
				number = "0" + number;
			}
		}
		return number;
	}

	public String getReferenceNo(String userId) {
		if (userId.length() > 4) {
			userId = userId.substring(0, 4);
		} else if (userId.length() < 4) {
			if (userId.length() == 1) {
				userId = "000" + userId;
			} else if (userId.length() == 2) {
				userId = "00" + userId;
			} else if (userId.length() == 3) {
				userId = "0" + userId;
			}
		}
		String ref = "TR-W-" + userId + "-";
		

		return ref;
	}

	private String getMemberShipNo(Long id){
		if(id.longValue() > 9999999l){
			id = id-7000000l;
		}
		
		String randomNo = "0"+id;
		
		return randomNo;
	}

	public void uploadProfileImage(CadreRegistrationVO cadreRegistrationVO,TdpCadre tdpCadre){
		LOG.error("PHOTOTYPE: "+cadreRegistrationVO.getPhotoType());
		LOG.error("EnrollmentNumber: "+cadreRegistrationVO.getPreviousEnrollmentNumber());
		LOG.error("VoterId: "+tdpCadre.getVoterId());
		LOG.error("ConstituencyId: "+cadreRegistrationVO.getConstituencyId());
		if(cadreRegistrationVO.getPhotoType() != null && cadreRegistrationVO.getPhotoType().trim().equalsIgnoreCase("voter") ){
			  LOG.error("10");
		  if(tdpCadre.getVoterId() != null){
			  LOG.error("11");
			    String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
				String destinationPath = IConstants.STATIC_CONTENT_FOLDER_URL+ "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator +"2015"+ pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
				Voter voter = voterDAO.get(tdpCadre.getVoterId());
				if(voter != null && voter.getImagePath() != null && voter.getImagePath().length() > 0 ){
				   String status = copyFile(IConstants.STATIC_CONTENT_FOLDER_URL+"voter_images/"+voter.getImagePath(),destinationPath);
				   LOG.error("Status : "+status);
				   if(status.equalsIgnoreCase("success")){
					   tdpCadre.setImage("2015/"+tdpCadre.getMemberShipNo()+".jpg");
					   LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
				   }
			   }
		  }
		}else{		
			  LOG.error("13");
			if(cadreRegistrationVO.getUploadImage() != null && !cadreRegistrationVO.getUploadImage().toString().equalsIgnoreCase("null")){
				 LOG.error("IMAGE: MS:"+tdpCadre.getMemberShipNo());
				String result = uploadCadreImage(tdpCadre.getMemberShipNo() , cadreRegistrationVO.getPath() , cadreRegistrationVO.getUploadImageContentType() , cadreRegistrationVO.getUploadImage());
				if(result != null){
					tdpCadre.setImage("2015/"+tdpCadre.getMemberShipNo()+"."+cadreRegistrationVO.getUploadImageContentType().split("/")[1]);
				}
			}
			else if(cadreRegistrationVO.getPhotoType() != null  && cadreRegistrationVO.getPhotoType().trim().equalsIgnoreCase("new")  && cadreRegistrationVO.getImageBase64String() != null && 
					cadreRegistrationVO.getImageBase64String().trim().length() > 0){
				  LOG.error("15");
					String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
					String filePath = IConstants.STATIC_CONTENT_FOLDER_URL + "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator +"2015"+ pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
					//String filePath = "D:/" + tdpCadre.getMemberShipNo()+".jpg";
					cadreRegistrationVO.setImageBase64String(cadreRegistrationVO.getImageBase64String().replace("_", "/"));
					cadreRegistrationVO.setImageBase64String(cadreRegistrationVO.getImageBase64String().replace("-", "+"));
					boolean status = imageAndStringConverter.convertBase64StringToImage(cadreRegistrationVO.getImageBase64String(),filePath);
					//System.out.println(cadreRegistrationVO.getImageBase64String());
					 LOG.error("BASE64: DP:"+filePath);
					if(status){
						tdpCadre.setImage("2015/"+tdpCadre.getMemberShipNo()+".jpg");
						LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
					}
			}else{
				  LOG.error("16");
				 if(tdpCadre.getVoterId() != null){
					  LOG.error("17");
					    String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
						String destinationPath = IConstants.STATIC_CONTENT_FOLDER_URL+ "images" + pathSeperator + IConstants.CADRE_IMAGES + pathSeperator +"2015"+ pathSeperator + tdpCadre.getMemberShipNo()+".jpg";
						Voter voter = voterDAO.get(tdpCadre.getVoterId());
						if(voter != null && voter.getImagePath() != null && voter.getImagePath().length() > 0 ){
							   String status = copyFile(IConstants.STATIC_CONTENT_FOLDER_URL+"voter_images/"+voter.getImagePath(),destinationPath);
							   LOG.error("Status : "+status);
							   if(status.equalsIgnoreCase("success")){
								   tdpCadre.setImage("2015/"+tdpCadre.getMemberShipNo()+".jpg");
								   LOG.error("Success:"+tdpCadre.getMemberShipNo()+".jpg");
							   }
						   }
				  }
			}
		}
		
	}

	public String getUniCodeMessage(String message) {
		String returnMessage = "";
		for (int i = 0; i < message.length(); i++) {
			String character = Integer.toHexString(message.charAt(i));
			if (character.length() == 1) {
				returnMessage = returnMessage + "000" + character;
			} else if (character.length() == 2) {
				returnMessage = returnMessage + "00" + character;
			} else if (character.length() == 3) {
				returnMessage = returnMessage + "0" + character;
			} else {
				returnMessage = returnMessage + "" + character;
			}

		}
		return returnMessage;
	}

	private String sendSMSInEnglish(String mobileNo, String msg) {

		try {

			if (!IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver")) {
				return "error";
			}

			String postData = "";
			String retval = "";
			// give all Parameters In String String User ="User_Name";
			String passwd = IConstants.ADMIN_PASSWORD_FOR_SMS;
			String mobilenumber = mobileNo;
			String message = msg;
			String sid = IConstants.ADMIN_SENDERID_FOR_SMS;
			String mtype = "N";
			String DR = "Y";
			postData += "User="
					+ URLEncoder.encode(IConstants.ADMIN_USERNAME_FOR_SMS,
							"UTF-8") + "&passwd=" + passwd + "&mobilenumber="
					+ mobilenumber + "&message="
					+ URLEncoder.encode(message, "UTF-8") + "&sid=" + sid
					+ "&mtype=" + mtype + "&DR=" + DR;
			URL url = new URL("http://smscountry.com/SMSCwebservice_Bulk.aspx");
			// URL url = new
			// URL("http://smscountry.com/SMSCwebservice_Bulk.aspx");
			HttpURLConnection urlconnection = (HttpURLConnection) url
					.openConnection();
			// If You Are Behind The Proxy Server Set IP And PORT else Comment
			// Below 4 Lines
			// Properties sysProps = System.getProperties();
			// sysProps.put("proxySet", "true");
			// sysProps.put("proxyHost", "Proxy Ip");
			// sysProps.put("proxyPort", "PORT");
			urlconnection.setRequestMethod("POST");
			urlconnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlconnection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(
					urlconnection.getOutputStream());
			out.write(postData);
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlconnection.getInputStream()));
			String decodedString;
			while ((decodedString = in.readLine()) != null) {
				retval += decodedString;
			}
			in.close();
			// System.out.println(retval);
			return retval;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			LOG.error("Exception Raised while Getting JobCode of SMS" + e);
			return "00000";
		}
	}

	public String copyFile(String sourcePath, String destinationPath) {
		// synchronized ("copyFile"){
		try {
			File file = new File(sourcePath);
			if (file.exists()) {
				FileUtils.copyFile(file, new File(destinationPath));
				LOG.error("Copy success");
				return "success";
			}
		} catch (Exception e) {
			LOG.error("Exception raised in copyFile ", e);
			LOG.error("Copy error");
			return "error";
		}
		// }
		return "failure";
	}
	public String uploadCadreImage(String voterCardNo, String url,
			String uploadImageContentType, File uploadImage) {
		try {
			String pathSeperator = System
					.getProperty(IConstants.FILE_SEPARATOR);

			String filePath = url + "images" + pathSeperator
					+ IConstants.CADRE_IMAGES + pathSeperator+"2015"+ pathSeperator;

			LOG.info("Cadre File Path -- " + filePath);

			BufferedImage image = ImageIO.read(uploadImage);

			if (image == null)
				return null;
			LOG.info("Image is Read");
			String constiName[] = uploadImageContentType.split("/");
			String fileName = filePath + voterCardNo.toString() + "."
					+ constiName[1];
			LOG.info("file name -- " + fileName);
			// String imageName = cadreId.toString()+"."+constiName[1];

			FileImageOutputStream filName = new FileImageOutputStream(new File(
					fileName));

			ImageIO.write(image, constiName[1], filName);
			LOG.info("file uploaded");
			filName.close();
			return "success";
		} catch (Exception e) {
			LOG.error(
					"Exception raised in uploadCadreImage in CadreRegistrationService service",
					e);
			return null;

		}
	}

	public boolean checkUrlExit(URL url) throws Exception {
		URLConnection connection = url.openConnection();
		connection.connect();
		HttpURLConnection httpConnection = (HttpURLConnection) connection;
		int code = httpConnection.getResponseCode();
		if (code == 200) {
			return true;
		} else {
			return false;
		}
	}

	public boolean copyNewImg(URL head, String destinationPath)
			throws Exception {
		BufferedImage image1 = ImageIO.read(head.openStream());
		if (image1 != null) {
			ImageIO.write(image1, "jpg", new File(destinationPath));
			return true;
		}
		return false;
	}
	
	public List<GenericVO> getTehsilByConstiteuncy(Long constituencyId)
	{
		LOG.info("entered into getTehsilByConstiteuncy in CadreRegistrationForOtherStatesService ");
		List<GenericVO> returnList = new ArrayList<GenericVO>();
		try {
	        Long stateId = constituencyDAO.get(constituencyId).getState().getStateId();
			List<Object[]> tehsilList = tehsilDAO.findTehsilsByConstituencyIdAndPublicationDateId(constituencyId,getPublicationId(stateId)); 
			List<Object[]> localBdyList = boothDAO.getAllLocalBodies(constituencyId,getPublicationId(stateId)); 

			if(tehsilList != null && tehsilList.size()>0)
			{
				for (Object[] param : tehsilList) 
				{
					GenericVO vo = new GenericVO();
					
					vo.setId(param[0] != null ? Long.valueOf("2"+param[0].toString()):0L);
					vo.setName(param[1] != null ? param[1].toString():"");
					
					returnList.add(vo);
				}
			}
			if(localBdyList != null && localBdyList.size()>0)
			{
				for (Object[] param : localBdyList) 
				{
					GenericVO vo = new GenericVO();
					
					vo.setId(param[0] != null ? Long.valueOf("1"+param[0].toString()):0L);
					vo.setName(param[1] != null ? param[1].toString():"");
					
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getTehsilByConstiteuncy ", e);
		}
		return returnList;
		
	}
	
	
	public List<GenericVO> getBoothsByConstiteuncy(Long constituencyId)
	{
		LOG.info("entered into getBoothsByConstiteuncy in CadreRegistrationForOtherStatesService ");
		List<GenericVO> returnList = new ArrayList<GenericVO>();
		try {
			List<Object[]> boothsList = boothDAO.getBoothsInAConstituencyByPublication(constituencyId,IConstants.OTHER_STATE_PUBLICATION_ID); 

			if(boothsList != null && boothsList.size()>0)
			{
				for (Object[] param : boothsList) 
				{
					GenericVO vo = new GenericVO();
					vo.setId(param[0] != null ? Long.valueOf(""+param[0].toString()):0L);
					vo.setName(param[1] != null ? "Booth-"+param[1].toString():"");
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getBoothsByConstiteuncy ", e);
		}
		return returnList;
		
	}
	
	public List<GenericVO> getBoothsDetailsByTehsil(Long tehsilId){
		LOG.info("entered into getBoothsDetailsByTehsil in CadreRegistrationForOtherStatesService ");
		List<GenericVO> returnList = new ArrayList<GenericVO>();
		try {
			Long id = new Long(tehsilId.toString().trim().substring(1));
			List<Object[]> tehsilList = null;
			 
			if(tehsilId.toString().trim().substring(0,1).equalsIgnoreCase("1")){
				Long stateId = localElectionBodyDAO.get(id).getDistrict().getState().getStateId();
			    tehsilList = boothDAO.getAllBoothsByTehsilOrLclBdyId(id,getPublicationId(stateId),"LocalBody"); 		
			}else{
				
				Long stateId = tehsilDAO.get(id).getDistrict().getState().getStateId();
				 
				tehsilList = boothDAO.getAllBoothsByTehsilOrLclBdyId(id,getPublicationId(stateId),"Tehsil"); 	
			}
			
			if(tehsilList != null && tehsilList.size()>0)
			{
				for (Object[] param : tehsilList) 
				{
					GenericVO vo = new GenericVO();
					
					vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L);
					vo.setName(param[1] != null ? param[1].toString():"");
					
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getBoothsDetailsByTehsil ", e);
		}
		return returnList;
	}
	
	public List<VoterInfoVO> getSearchDetailsCadreRegistration(Long stateId,Long constituencyId, String candidateName, String voterCardId,
			String houseNo,Long tehsilId,Long boothId,Integer startIndex,Integer maxIndex){
		StringBuilder searchQuery = new StringBuilder();
		List<VoterInfoVO> returnList = null;
		List<Object[]> searchList = null;
		Long count = 0l;
		Map<Long,VoterInfoVO> votersMap = new HashMap<Long,VoterInfoVO>();
		try {

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
					searchQuery.append(" BPV.voter.houseNo like '"+houseNo+"' and" );
				}
				if(stateId != null && stateId.longValue() != 29L)
				  stateId = constituencyDAO.get(constituencyId).getState().getStateId();
				 
				//0 voterId,1name,2relative name,3age,4houseno,5relationtype,6gender,7voterIDCardNo,8path
				searchList = boothPublicationVoterDAO.getVotersDetailsForCadreRegistratiobByLocationIds(stateId,constituencyId,getPublicationId(stateId),searchQuery.toString(),tehsilId,boothId,startIndex,maxIndex);
				 count = boothPublicationVoterDAO.getVotersDetailsForCadreRegistratiobByLocationIdsCount(stateId,constituencyId,getPublicationId(stateId),searchQuery.toString(),tehsilId,boothId);
				
				List<Long> voterIds = new ArrayList<Long>();
				if(searchList != null && searchList.size()>0 ){
					
					returnList = new ArrayList<VoterInfoVO>();
					
					for (Object[] voter : searchList){
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
						vo.setImage(voter[8]!=null ?voter[8].toString():null);
						vo.setIsRegistered("N");
						votersMap.put(vo.getId(), vo);
						returnList.add(vo);
					}
				}
				
				if( voterIds.size()>0){
					List<Long> tdpCadreVoterIds = tdpCadreDAO.getVoterDetailsByVoterIds(voterIds);
					if(tdpCadreVoterIds != null && tdpCadreVoterIds.size()>0){
						List<Object[]> tdpCadreList = tdpCadreDAO.getMembershipNosByTdpCadreIds(tdpCadreVoterIds);
						Map<Long,String> cadreMembershipMap = new LinkedHashMap<Long, String>();
						
						if(tdpCadreList != null && tdpCadreList.size()>0)
						{
							for (Object[] cadre : tdpCadreList) {
								Long tpdCadeId = cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0;
								if(tpdCadeId>0)
									cadreMembershipMap.put(tpdCadeId, cadre[1] != null ? cadre[1].toString().trim():"");
							}
						}
						for (Long voterId : tdpCadreVoterIds){
							VoterInfoVO voterVO =votersMap.get(voterId);
							if(voterVO != null){
								voterVO.setIsRegistered("Y");
								voterVO.setMemberShipId(cadreMembershipMap.get(voterId));
							}
						}
					}
				}	
		} catch (Exception e) {
			LOG.error("Exception raised in getSearchDetailsCadreRegistration in CadreRegistrationForOtherStatesService service", e);
		}
		if(returnList !=null && returnList.size() > 0){
			returnList.get(0).setCount(count);
		}
		return returnList;
	}
	
	public String checkVoterAlreadyRegisteredOrNot(Long voterId){
		Long count = tdpCadreDAO.checkVoterRegisteredOrNot(voterId, IConstants.CADRE_ENROLLMENT_YEAR_FOROTHER_STATES);
		if(count.longValue() > 0){
			return "alreadyRegistered";
		}else{
			return "available";
		}
	}
	
	public boolean checkFileExistingOrNot(String path){
		File f = new File(path);
		if(f.exists()) { 
			return true;
		}else{
			return false;
		}
	}
	
	public List<VoterInfoVO> getCandidateInfoBySearchCriteria(String voterType, Long candidateId,Long constituencyId){
		List<VoterInfoVO> returnList = new ArrayList<VoterInfoVO>();
		VoterInfoVO vo = new VoterInfoVO();
		try {
			Long voterId = 0L;
			//String houseNo = null;
			//List<VoterInfoVO> familyList = null;
			
			if(candidateId != null && candidateId != 0L){
				  Voter voter = voterDAO.get(candidateId);
				  if(voter != null ){
					if(voterType.equalsIgnoreCase("voter")){
						
						vo.setName(voter.getName() != null ? voter.getName():"");
						vo.setRelativeName(voter.getRelativeName() != null ? voter.getRelativeName():"");
						vo.setRelationType(voter.getRelationshipType() != null ? voter.getRelationshipType():"");
						vo.setAge(voter.getAge() != null? voter.getAge().toString():"");
						vo.setDateOfBirth(voter.getDateOfBirth() != null ? voter.getDateOfBirth().toString():"");
						vo.setGender(voter.getGender());
						
						if(voter.getImagePath() != null && voter.getImagePath().trim().length() > 0 && checkFileExistingOrNot(IConstants.STATIC_CONTENT_FOLDER_URL+"voter_images/"+voter.getImagePath())){
							vo.setVoterImagePresent(true);
							vo.setVoterImage("voter_images/"+voter.getImagePath());
						}
					}
					vo.setVoterId(voter.getVoterId());
					vo.setVoterCardNo(voter.getVoterIDCardNo() != null ? voter.getVoterIDCardNo() :"");
					vo.setHouseNo(voter.getHouseNo() != null ? voter.getHouseNo().toString():"");
					//houseNo = vo.getHouseNo();
					//voterId = candidateId;
				  }
				
				/*
				if(voterId != null && voterId.longValue() != 0L && vo != null){
					List<Long> votersList = new ArrayList<Long>();
					votersList.add(voterId);
					if(voterType.equalsIgnoreCase("voter")){
	                    List<VoterNames> voterNamesList = voterNamesDAO.gerVoterNamesObjByVoterId(voterId);
						if(voterNamesList != null && voterNamesList.size() > 0 && voterNamesList.get(0) != null){
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
					}
					 Long stateId = constituencyDAO.get(constituencyId).getState().getStateId();
					 
					List<Object[]> voterInfo = boothPublicationVoterDAO.getBoothIdsDetailsOfVoterIds(votersList,getPublicationId(stateId));
					
					if(voterInfo != null && voterInfo.size()>0){
						Object[] boothInfo = (Object[])voterInfo.get(0);
						
						Long boothId = boothInfo[2] != null ?Long.valueOf(boothInfo[2].toString()):0L;
						
						familyList = new ArrayList<VoterInfoVO>();

							if((houseNo != null && houseNo.toString().trim().length()>0) && (boothId != null && boothId.longValue() >0L)){
								List<Object[]> familyInfo = boothPublicationVoterDAO.getFamilyDetaislByHouseNoAndBoothId(boothId,houseNo);
								
								if(familyInfo != null && familyInfo.size()>0){
									for (Object[] family : familyInfo){
										Long familyVoterID = family[0] != null ? Long.valueOf(family[0].toString().trim()):0L;
										
										if( familyVoterID.longValue() != voterId.longValue()){
											VoterInfoVO fmilyVO = new VoterInfoVO();
											fmilyVO.setVoterId(family[0] != null ? Long.valueOf(family[0].toString().trim()):0L);
											fmilyVO.setName(family[1] != null ? family[1].toString():"");
											fmilyVO.setAge(family[5] != null ? family[5].toString():"");								
											fmilyVO.setVoterCardNo(family[6] != null ? family[6].toString():"");
											boolean member = true;
											if(voterType.equalsIgnoreCase("voter") && fmilyVO.getVoterId().longValue() == candidateId.longValue()){
												member = false;
											}
											if(member){
											  familyList.add(fmilyVO);
											}
										}
									}
								}
							}
						
						vo.setVoterInfoVOList(familyList);
					} 
					
				}*/
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
			
			
			returnList.add(vo);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getCandidateInfoBySearchCriteria in CadreRegistrationService service", e);
		}
		
		return returnList;
	}
	
	public Long getDistrictIdByConstituencyId(Long constituencyId){
		Long districtId = null;
		List<Long> districtIdsList  = constituencyDAO.getDistrictByConstituencyId(constituencyId);
		if(districtIdsList.size() > 0 && districtIdsList.get(0)!= null){
			districtId = districtIdsList.get(0);
		}
		return districtId;
	}
	public List<SelectOptionVO> getAllDistrictsByStateId(Long stateId){
		List<SelectOptionVO> returnlist = new ArrayList<SelectOptionVO>();
		SelectOptionVO vo = null;
		List<Object[]> districtsList = (List<Object[]>)districtDAO.getDistrictIdAndNameByState(stateId);
		for(Object[] district:districtsList){
			vo = new SelectOptionVO();
			vo.setId((Long)district[0]);
			vo.setName(district[1].toString());
			returnlist.add(vo);
		}
		return returnlist;
	}
	
	public List<SelectOptionVO> getAllMandalsInADistrict(Long district){
         
		LOG.info("entered into getAllMandalsInADistrict in CadreRegistrationForOtherStatesService ");
		List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		try {
			 Long stateId = districtDAO.get(district).getState().getStateId();
			 
			//List<Object[]> tehsilList = boothDAO.findTehsilsByDistrictIdAndPublicationDateId(district,getPublicationId(stateId)); 
			List<Object[]> tehsilList = tehsilDAO.findTehsilsByDistrict(district); 
			List<Object[]> localBdyList = boothDAO.getAllLocalBodiesByDistrictIdAndPublicationDateId(district,getPublicationId(stateId)); 

			if(tehsilList != null && tehsilList.size()>0)
			{
				for (Object[] param : tehsilList) 
				{
					SelectOptionVO vo = new SelectOptionVO();
					
					vo.setId(param[0] != null ? Long.valueOf("2"+param[0].toString()):0L);
					vo.setName(param[1] != null ? param[1].toString():"");
					
					returnList.add(vo);
				}
			}
			if(localBdyList != null && localBdyList.size()>0)
			{
				for (Object[] param : localBdyList) 
				{
					SelectOptionVO vo = new SelectOptionVO();
					
					vo.setId(param[0] != null ? Long.valueOf("1"+param[0].toString()):0L);
					vo.setName(param[1] != null ? param[1].toString():"");
					
					returnList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getAllMandalsInADistrict ", e);
		}
		return returnList;
	}
	
	public Long getStateByConstituencyId(Long constituencyId){
		Long districtId = null;
		List<Long> districtIdsList  = constituencyDAO.getStateByConstituencyId(constituencyId);
		if(districtIdsList.size() > 0 && districtIdsList.get(0)!= null){
			districtId = districtIdsList.get(0);
		}
		return districtId;
	}
	
	public void getUserAddressDetails(Long voterId,UserAddress userAddress){
		try {
			LOG.info("Entered into getVoterAddressDetails in CadreRegistrationForOtherStatesService service");
			
			List<Booth> locationDetails = boothPublicationVoterDAO.getOtherStateVoterAddressDetails(voterId);
			if(locationDetails != null && locationDetails.size() > 0)
			{
				Booth booth = locationDetails.get(0);
				if(booth != null)
				{
					userAddress.setBooth(booth);
					
					if(booth.getConstituency() != null)
					{
						userAddress.setConstituency(booth.getConstituency());
						userAddress.setState(booth.getConstituency().getState());
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
					
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getUserAddressDetails in CadreRegistrationForOtherStatesService service", e);
		}
	}
	
	public static Long getPublicationId(Long stateId){
		Map<Long,Long> publicationsMap = new HashMap<Long,Long>();
		publicationsMap.put(12L, 13l);
		publicationsMap.put(15L, 14l);
		publicationsMap.put(1l, 11l);
		publicationsMap.put(12l, 13l);
		publicationsMap.put(24l, 13l);
		publicationsMap.put(29l, 13l);
		publicationsMap.put(13l, 13l);
		publicationsMap.put(14l, 13l);
		publicationsMap.put(15l, 13l);

		return publicationsMap.get(stateId);
	}
	
	public List<SelectOptionVO> getCasteDetailsByCasteCategoryId(Long casteCategoryGroupId,Long stateId)
	{	
		List<SelectOptionVO> returnList = null;
		try {
			List<Object[]> list = null;
			if(casteCategoryGroupId != null && casteCategoryGroupId.longValue() >0)
			{
				list = casteStateDAO.getStatewisesCastNamesByGroupId(casteCategoryGroupId,stateId);
			}
			else if(casteCategoryGroupId != null && casteCategoryGroupId.longValue() == 0L)
			{
				list = casteStateDAO.getAllCasteDetailsForVoters(stateId);
			}
			if(list != null && list.size() > 0)
			{
				returnList = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				for(Object[] caste : list)
				{
					selectOptionVO = new SelectOptionVO(caste[0] != null ? Long.valueOf(caste[0].toString().trim()):0L,caste[1] != null ? caste[1].toString().trim():"");
					returnList.add(selectOptionVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getCasteDetailsByCasteCategoryId in CadreRegistrationForOtherStatesService service", e);
		}
		
		return returnList;
	}
	
	public String checkIsAlreadyTempCardGenerated(Long mobile,String name)
	{
		try{
			Long count = tdpCadreDAO.getIsAlreadyTempararyRegistered(mobile,name);
			if(count != null && count.longValue() >0L)
			{
				return "alreadyRegistered";
			}
			else
			{
				return "notRegistered";
			}
		}catch (Exception e) {
			LOG.error("Exception raised in checkIsAlreadyTempCardGenerated in CadreRegistrationForOtherStatesService service", e);
		}
		return null;
	}
	public void tdpTempararyCadreSavingLogic(final AddressVO addressVO,final CadreRegistrationVO cadreRegistrationVO,final SurveyCadreResponceVO surveyCadreResponceVO,final String insertType, final boolean statusVar,final String registrationType) 
	{
	  try{
		if(cadreRegistrationVO.getMobileNumber() != null && cadreRegistrationVO.getMobileNumber().trim().length() > 0){
			String status = checkIsAlreadyTempCardGenerated(Long.valueOf(cadreRegistrationVO.getMobileNumber().trim()),cadreRegistrationVO.getVoterName().trim());
			if(status.equalsIgnoreCase("alreadyRegistered")){
				surveyCadreResponceVO.setStatus("alreadyRegistered");
			}
		}
		final TdpCadre tdpCadre = new TdpCadre();
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			public void doInTransactionWithoutResult(TransactionStatus status) {
				TdpCadre tdpCadre1 = null;
                tdpCadre.setDataSourceType("WEB");
				
				if (cadreRegistrationVO.getVoterName() != null && cadreRegistrationVO.getVoterName().trim().length() > 0) {
					tdpCadre.setFirstname(cadreRegistrationVO.getVoterName());
				}
				
				if (cadreRegistrationVO.getMobileNumber() != null && cadreRegistrationVO.getMobileNumber().trim().length() > 0) {
					tdpCadre.setMobileNo(cadreRegistrationVO.getMobileNumber());
				}				
				
				if (cadreRegistrationVO.getCreatedUserId() != null && cadreRegistrationVO.getCreatedUserId().longValue() > 0) {
					if (!insertType.equalsIgnoreCase("update")) {
						tdpCadre.setInsertedWebUserId(cadreRegistrationVO.getCreatedUserId().longValue());
					} else {
						tdpCadre.setUpdatedWebUserId(cadreRegistrationVO.getCreatedUserId().longValue());
					}
				}
		
				UserAddress userAddress = new UserAddress();
				if(addressVO.getStateId() != null && addressVO.getStateId().longValue() >0)
				{
					userAddress.setState(stateDAO.get(addressVO.getStateId()));
					userAddress = userAddressDAO.save(userAddress);
					tdpCadre.setUserAddress(userAddress);
				}

				if (!insertType.equalsIgnoreCase("update") && tdpCadre.getInsertedTime() == null) {
					tdpCadre.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				}
				tdpCadre.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				tdpCadre.setEnrollmentYear(IConstants.CADRE_ENROLLMENT_YEAR_FOROTHER_STATES);

				tdpCadre.setIsDeleted("T");
				tdpCadre.setSurveyTime(tdpCadre.getInsertedTime());

					String userId = "0000";
					if (cadreRegistrationVO.getCreatedUserId() != null) {
						userId = cadreRegistrationVO.getCreatedUserId().toString();
					}
					String ref = getReferenceNo(userId);

					if (tdpCadre.getRefNo() == null || tdpCadre.getRefNo().trim().length() == 0) {
						ref = getUniueRefNo(ref);
						tdpCadre.setRefNo(ref);
					}
					cadreRegistrationVO.setRefNo(tdpCadre.getRefNo());
					tdpCadre.setIsDeleted("T");
					surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getRefNo());
					tdpCadre1 = tdpCadreDAO.save(tdpCadre);

				if (tdpCadre1.getMemberShipNo() == null || tdpCadre1.getMemberShipNo().trim().length() == 0) {
					String membershipNo = getMemberShipNo(tdpCadre1.getTdpCadreId());
					tdpCadre1.setMemberShipNo(membershipNo);
				}
				uploadProfileImage(cadreRegistrationVO,tdpCadre1);
				tdpCadre1 = tdpCadreDAO.save(tdpCadre1);
				
				surveyCadreResponceVO.setEnrollmentNumber(tdpCadre1.getRefNo());			
				surveyCadreResponceVO.setStatus("SUCCESS");
				surveyCadreResponceVO.setResultCode(ResultCodeMapper.SUCCESS);				
			}
		});
		surveyCadreResponceVO.setStatus("SUCCESS");
	  }catch(Exception e){
		  LOG.error("Exception Rised in tdpTempararyCadreSavingLogic",e);
		  surveyCadreResponceVO.setStatus("error");
	  }
	}
	
public List<IdNameVO> getConstituencyByState(Long stateId){
		
		// STATE - 1, DISTRICT - 2, MANDAL - 5, PANCHAYAT - 7,  MUNCIPAL-CORP-GHMC - 6, WARD - 9, INCHARGE - 10, DIVISION - 11
		
		List<IdNameVO> resultList = new ArrayList<IdNameVO>();
			List<Object[]> asslyList = constituencyDAO.getConstituencyByState(stateId);
			if(asslyList!=null && asslyList.size()>0){
				for(Object[] obj:asslyList){
					IdNameVO vo = new IdNameVO(Long.valueOf(obj[0].toString()), obj[1].toString());
					
					resultList.add(vo);
				}
			}
		
		
		return resultList;
	}
}
