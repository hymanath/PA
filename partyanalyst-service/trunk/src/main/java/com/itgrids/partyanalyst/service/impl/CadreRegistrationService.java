package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreParticipatedElectionDAO;
import com.itgrids.partyanalyst.dao.ICadrePreviousRolesDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.hibernate.UserAddressDAO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.CadreParticipatedElection;
import com.itgrids.partyanalyst.model.CadrePreviousRoles;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.UserAddress;
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

	/**
	 * This service is used for saving cadre data from tab as well web also
	 * @author Prasad Thiragabathina
	 * @date 26-09-2014
	 * @param inputsList
	 * @return resultStatus
	 */
	public ResultStatus saveCadreRegistration(final CadreRegistrationVO cadreRegistrationVO)
	{
		final ResultStatus resultStatus = new ResultStatus();
		
		try {
			LOG.info("Entered into saveCadreRegistration in CadreRegistrationService service");
			
				transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					public void doInTransactionWithoutResult(TransactionStatus status) {
						
							if(cadreRegistrationVO != null)
							{
								TdpCadre tdpCadre = new TdpCadre();
								
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
								if(cadreRegistrationVO.getPreviousEnrollmentNumber() != null && !cadreRegistrationVO.getPreviousEnrollmentNumber().equalsIgnoreCase("null") && cadreRegistrationVO.getPreviousEnrollmentNumber().trim().length() > 0)
								{
									tdpCadre.setMemberShipNo(cadreRegistrationVO.getPreviousEnrollmentNumber());
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
								getVoterAddressDetails(cadreRegistrationVO.getVoterId(),userAddress);
								userAddress = userAddressDAO.save(userAddress);
								tdpCadre.setUserAddress(userAddress);						
								tdpCadre.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								tdpCadre.setUpdatedTime(dateUtilService.getCurrentDateAndTime());		
								tdpCadre.setEnrollmentYear(IConstants.CADRE_ENROLLMENT_NUMBER);
								tdpCadre = tdpCadreDAO.save(tdpCadre);						
								List<CadrePreviousRollesVO> previousRollesPartList = cadreRegistrationVO.getPreviousRollesList();
								if(previousRollesPartList != null && previousRollesPartList.size() > 0)
								{
									for (CadrePreviousRollesVO rolesVO : previousRollesPartList)
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
								
								List<CadrePreviousRollesVO> previousElectionPartList = cadreRegistrationVO.getPreviousParicaptedElectionsList();
								if(previousElectionPartList != null && previousElectionPartList.size() > 0)
								{
									for (CadrePreviousRollesVO electionVO : previousElectionPartList)
									{
										if(tdpCadre != null)
										{
											CadreParticipatedElection cadreParticipatedElection = new CadreParticipatedElection();
											
											cadreParticipatedElection.setTdpCadreId(tdpCadre.getTdpCadreId());
											
											if(electionVO.getElectionTypeId() != null && electionVO.getElectionTypeId().longValue() > 0)
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
							resultStatus.setResultCode(ResultCodeMapper.SUCCESS);

				}});

		} catch (Exception e) {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			LOG.error("Exception raised in saveCadreRegistration in CadreRegistrationService service", e);
		}
		
		return resultStatus;
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
	
	

}
