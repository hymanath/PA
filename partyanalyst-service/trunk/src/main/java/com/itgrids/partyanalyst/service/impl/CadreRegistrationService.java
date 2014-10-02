package com.itgrids.partyanalyst.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBloodGroupDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICadreLevelDAO;
import com.itgrids.partyanalyst.dao.ICadreParticipatedElectionDAO;
import com.itgrids.partyanalyst.dao.ICadrePreviousRolesDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyDesignationDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;
import com.itgrids.partyanalyst.dto.VoterInfoVO;
import com.itgrids.partyanalyst.model.BloodGroup;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.CadreParticipatedElection;
import com.itgrids.partyanalyst.model.CadrePreviousRoles;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

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
	
	

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public ICadreLevelDAO getCadreLevelDAO() {
		return cadreLevelDAO;
	}

	public void setCadreLevelDAO(ICadreLevelDAO cadreLevelDAO) {
		this.cadreLevelDAO = cadreLevelDAO;
	}

	public IPartyDesignationDAO getPartyDesignationDAO() {
		return partyDesignationDAO;
	}

	public void setPartyDesignationDAO(IPartyDesignationDAO partyDesignationDAO) {
		this.partyDesignationDAO = partyDesignationDAO;
	}

	public IElectionTypeDAO getElectionTypeDAO() {
		return electionTypeDAO;
	}

	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}

	public IBloodGroupDAO getBloodGroupDAO() {
		return bloodGroupDAO;
	}

	public void setBloodGroupDAO(IBloodGroupDAO bloodGroupDAO) {
		this.bloodGroupDAO = bloodGroupDAO;
	}

	public ICasteStateDAO getCasteStateDAO() {
		return casteStateDAO;
	}

	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public ICadreDAO getCadreDAO() {
		return cadreDAO;
	}

	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}

	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public ICadrePreviousRolesDAO getCadrePreviousRolesDAO() {
		return cadrePreviousRolesDAO;
	}

	public ICadreParticipatedElectionDAO getCadreParticipatedElectionDAO() {
		return cadreParticipatedElectionDAO;
	}

	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public ICountryDAO getCountryDAO() {
		return countryDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public IUserAddressDAO getUserAddressDAO() {
		return userAddressDAO;
	}

	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
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
	

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
	}

	
	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
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
								
								if(cadreRegistrationVO != null)
								{
									TdpCadre tdpCadre = new TdpCadre();
									
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
									if(cadreRegistrationVO.getVoterId() != null && cadreRegistrationVO.getVoterId().longValue() > 0)
									{
										tdpCadre.setVoterId(cadreRegistrationVO.getVoterId());
									}
									else if(cadreRegistrationVO.getVoterCardNumber() != null && !cadreRegistrationVO.getVoterCardNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getVoterCardNumber().trim().length() > 0)
									{
										List<Long> voterCardNumbersList = voterDAO.getVoterId(cadreRegistrationVO.getVoterCardNumber());
										if(voterCardNumbersList != null && voterCardNumbersList.size() > 0)
										{
											tdpCadre.setVoterId(voterDAO.getVoterId(cadreRegistrationVO.getVoterCardNumber()).get(0));
										}
										else
										{
											surveyCadreResponceVO.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
											surveyCadreResponceVO.setStatus("DATA NOT FOUND");
											return;
										}
										
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
										tdpCadre.setInsertedUserId(cadreRegistrationVO.getCreatedUserId().longValue());
									}
									if(cadreRegistrationVO.getUpdatedUserId() != null && cadreRegistrationVO.getUpdatedUserId().longValue() > 0)
									{
										tdpCadre.setUpdatedUserId(cadreRegistrationVO.getUpdatedUserId().longValue());
									}
									
									UserAddress userAddress = new UserAddress();
									getVoterAddressDetails(tdpCadre.getVoterId(),userAddress);
									userAddress = userAddressDAO.save(userAddress);
									tdpCadre.setUserAddress(userAddress);						
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
									}
									synchronized (surveyCadreResponceVO) {
										String memberNumber = tdpCadreDAO.getLatestMemberNumber();
										if(memberNumber == null)
										{
											tdpCadre.setMemberShipNo("10000001");
										}
										else
										{
											tdpCadre.setMemberShipNo(String.valueOf((Long.valueOf(memberNumber) + 1l)));
											surveyCadreResponceVO.setEnrollmentNumber(tdpCadre.getMemberShipNo());
										}
									}
									if(cadreRegistrationVO.getUniqueKey() != null && !cadreRegistrationVO.getUniqueKey().equalsIgnoreCase("null") && cadreRegistrationVO.getUniqueKey().trim().length() > 0)
									{
										tdpCadre.setUniqueKey(cadreRegistrationVO.getUniqueKey());
										if(cadreRegistrationVOList.size() == 1)
										{
											surveyCadreResponceVO.setUniqueKey(cadreRegistrationVO.getUniqueKey());
											surveyCadreResponceVO.setVoterName(cadreRegistrationVO.getVoterName());
											surveyCadreResponceVO.setVoterId(cadreRegistrationVO.getVoterId());
											surveyCadreResponceVO.setRelativeName(cadreRegistrationVO.getRelativeName());
										}
									}
									if(cadreRegistrationVO.getUploadImage() != null && !cadreRegistrationVO.getUploadImage().toString().equalsIgnoreCase("null"))
									{
										String result = uploadCadreImage(tdpCadre.getMemberShipNo() , cadreRegistrationVO.getPath() , cadreRegistrationVO.getUploadImageContentType() , cadreRegistrationVO.getUploadImage());
										if(result != null)
										{
											tdpCadre.setImage(tdpCadre.getMemberShipNo()+"."+cadreRegistrationVO.getUploadImageContentType().split("/")[1]);
										}
									}
									
									tdpCadre = tdpCadreDAO.save(tdpCadre);						
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
													
													if(rolesVO.getDesignationLevelId() != null && rolesVO.getDesignationLevelId().longValue() > 0)
													{
														cadrePreviousRoles.setCadreLevelId(rolesVO.getDesignationLevelId());
													}
													if(rolesVO.getDesignationLevelValue() != null && rolesVO.getDesignationLevelValue().longValue() > 0)
													{
														cadrePreviousRoles.setPartyDesignationId(rolesVO.getDesignationLevelValue());
													}
													if(rolesVO.getFromDate() != null)
													{
														cadrePreviousRoles.setFromDate(rolesVO.getFromDate());
													}
													if(rolesVO.getToDate() != null)
													{
														cadrePreviousRoles.setToDate(rolesVO.getToDate());
													}
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
													CadreParticipatedElection cadreParticipatedElection = new CadreParticipatedElection();
													
													cadreParticipatedElection.setTdpCadreId(tdpCadre.getTdpCadreId());
													/*List<Election> electionsList = electionDAO.findByElectionTypeYearAndState(electionVO.getElectionTypeId(),String.valueOf(electionVO.getElectionYear()),1l,1l);
													if(electionsList != null && electionsList.size() > 0)
													{
														if(electionsList.get(0) != null)
														{
															cadreParticipatedElection.setElectionId(electionsList.get(0).getElectionId());
														}
													}*/
													if(electionVO.getElectionTypeId() != null && electionVO.getElectionTypeId() > 0)
													{
														cadreParticipatedElection.setElectionId(electionVO.getElectionTypeId());
													}
													if(electionVO.getConstituencyId() != null && electionVO.getConstituencyId().longValue() > 0)
													{
														cadreParticipatedElection.setConstituencyId(electionVO.getConstituencyId());
													}
													cadreParticipatedElectionDAO.save(cadreParticipatedElection);
												}
											}
											
											
										}
									}
								}
							}
						}
							
						surveyCadreResponceVO.setStatus("SUCCESS");
						surveyCadreResponceVO.setResultCode(ResultCodeMapper.SUCCESS);
							

				}});

		} catch (Exception e) {
			surveyCadreResponceVO.setResultCode(ResultCodeMapper.FAILURE);
			surveyCadreResponceVO.setStatus("EXCEPTION");
			LOG.error("Exception raised in saveCadreRegistration in CadreRegistrationService service", e);
		}
		
		return surveyCadreResponceVO;
	}
	
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
			return null;
		}
	}
	
	/**
	 * @author Prasad Thiragabathina
	 * @date 26-09-2014
	 * @param voterId
	 * @param returnList
	 */
	public void getVoterAddressDetails(Long voterId,UserAddress userAddress)
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
					
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getVoterAddressDetails in CadreRegistrationService service", e);
		}
	}
	

	public List<VoterInfoVO> getSearchDetailsCadreRegistration(Long constituencyId, String seachType, String candidateName, String voterCardId, String houseNo,Long panchayatId,Long boothId,String villagesCovered)
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

				searchList = boothPublicationVoterDAO.getVotersDetailsForCadreRegistratiobByconstituencId(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID,searchQuery.toString(),panchayatId,boothId,villagesCovered);
				
				if(searchList != null && searchList.size()>0 )
				{
					returnList = new ArrayList<VoterInfoVO>();
					
					for (Object param : searchList)
					{
						Object[] voter = (Object[]) param;
						VoterInfoVO vo = new VoterInfoVO();
						vo.setId(voter[0] != null ? Long.valueOf(voter[0].toString().trim()):0L);
						vo.setVoterId(voter[0] != null ? Long.valueOf(voter[0].toString().trim()):0L);
						vo.setName(voter[1] != null ? voter[1].toString().trim():"");
						vo.setRelativeName(voter[2] != null ? voter[2].toString().trim():"");
						vo.setAge(voter[3] != null ? voter[3].toString().trim():"");
						vo.setHouseNo(voter[4] != null ? voter[4].toString().trim():"");
						vo.setRelationType(voter[5] != null ? voter[5].toString().trim():"");
						vo.setGender(voter[6] != null ? voter[6].toString().trim():"");
						
						returnList.add(vo);
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
					//searchQuery.append("  BPV.voter.voterIDCardNo like '%"+voterCardId+"%' and");
				}
				if(houseNo != null  && houseNo.trim().length()>0)
				{
					searchQuery.append("  UA.houseNo like '%"+houseNo+"%' and" );
				}

				
				searchList = tdpCadreDAO.getCadreDetailsForCadreRegistratiobByconstituencId(constituencyId, searchQuery.toString(), panchayatId, boothId, villagesCovered);
				
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
						vo.setRelationType(" -- ");
						
						String dateOfBirth = 	voter[3] != null ? voter[3].toString().substring(0, 10):" "	;
						
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
					vo.setGender(voter.getGender() != null ? voter.getGender():"");
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
							String dateOfBirth = tdpCadre.getDateOfBirth() != null ? tdpCadre.getDateOfBirth().toString().substring(0, 10):"";
							vo.setDateOfBirth(dateOfBirth);
							
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
								vo.setVoterCardNo(" -- ");
								vo.setHouseNo(" -- ");
								vo.setRelationType(" -- ");
								
								if(dateOfBirth != null && dateOfBirth.trim().length()>0)
								{
									Calendar startDate = new GregorianCalendar();
									Calendar endDate = new GregorianCalendar();
									
									startDate.setTime(format.parse(dateOfBirth));
									
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
							vo.setCasteName(tdpCadre.getCasteState() != null ? tdpCadre.getCasteState().getCasteStateId().toString():"0");
							vo.setEducation(tdpCadre.getEducationalQualifications() != null ? tdpCadre.getEducationalQualifications().getEduQualificationId().toString():"0");
							vo.setOccupation(tdpCadre.getOccupation() != null ? tdpCadre.getOccupation().getOccupationId().toString():"0");
							vo.setLocation(tdpCadre.getUserAddress() != null ? (tdpCadre.getUserAddress().getHamlet() != null ?tdpCadre.getUserAddress().getHamlet().getHamletName().toString():""):"");
							vo.setMobileNo(tdpCadre.getMobileNo() != null ? tdpCadre.getMobileNo():"");
							vo.setMemberShipId(tdpCadre.getMemberShipNo() != null ? tdpCadre.getMemberShipNo().toString():"");
							vo.setActiveDate(tdpCadre.getPartyMemberSince() != null ? tdpCadre.getPartyMemberSince().toString():"");
							
						} catch (Exception e) {
							LOG.error("Exception raised in getCandidateInfoBySearchCriteria in CadreRegistrationService service", e);
						}
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
								
								if( familyVoterID != voterId)
								{
									VoterInfoVO fmilyVO = new VoterInfoVO();
									fmilyVO.setVoterId(family[0] != null ? Long.valueOf(family[0].toString().trim()):0L);
									fmilyVO.setName(family[1] != null ? family[1].toString():"");
									fmilyVO.setRelativeName(family[2] != null ? family[2].toString():"");
									fmilyVO.setRelationType(family[3] != null ? family[3].toString():"");
									fmilyVO.setGender(family[4] != null ? family[4].toString():"");
									fmilyVO.setAge(family[5] != null ? family[5].toString():"");								
									fmilyVO.setVoterCardNo(family[6] != null ? family[6].toString():"");
									
									familyList.add(fmilyVO);
								}
							}
							
							vo.setVoterInfoVOList(familyList);
						}
					}
				}
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

	public List<GenericVO> getConstiteuncyDetailsByConstiteuncy(Long constituencyId)
	{
		LOG.info("Exception raised in getConstiteuncyDetailsByConstiteuncy in CadreRegistrationService service");
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
								vo.setName(param[1] != null ? "Booth - "+param[1].toString():"");
								
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
								vo.setName(param[2] != null ? "Booth - "+param[2].toString():"");
								
								returnList.add(vo);
								
							}
						}
						
					}
				}
				else
				{
					List<Object[]> boothList = boothDAO.getAllBoothsInAMuncipality(locationId, IConstants.VOTER_DATA_PUBLICATION_ID);
					
					if(boothList != null && boothList.size()>0)
					{
						for (Object[] param : boothList)
						{
							GenericVO vo = new GenericVO();
							
							vo.setId(param[0] != null ? Long.valueOf(param[0].toString()):0L);
							vo.setName(param[2] != null ? "Booth - "+param[2].toString():"");
							
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
		List<SelectOptionVO> returnList = new ArrayList<SelectOptionVO>();
		SelectOptionVO mainVO = new SelectOptionVO();
		
		try{
						
			List<ElectionType> electionTypeList = electionTypeDAO.getElectionTypeList();
			
			List<SelectOptionVO> electionTypes = null;
			
			if(electionTypeList != null && electionTypeList.size()>0)
			{
				electionTypes = new ArrayList<SelectOptionVO>();
				
				for (ElectionType electionType : electionTypeList) 
				{
					SelectOptionVO vo = new SelectOptionVO();
					vo.setId(electionType.getElectionTypeId() != null ? electionType.getElectionTypeId():0L);
					vo.setName(electionType.getElectionType() != null ? electionType.getElectionType():"");
					
					electionTypes.add(vo);
				}
			}
			
			mainVO.setSelectOptionsList(electionTypes);
			
			
			@SuppressWarnings("unchecked")
			List<Object[]> electionIdList = electionDAO.findElectionYearsForElectionTypeAndStateId(2L,1L);
			
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
			
			mainVO.setSelectOptionsList1(electionList);
			
			returnList.add(mainVO);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getOptionDetailsForCadre in CadreRegistrationService service", e);
		}
		
		return returnList;
		
	}
	
}
