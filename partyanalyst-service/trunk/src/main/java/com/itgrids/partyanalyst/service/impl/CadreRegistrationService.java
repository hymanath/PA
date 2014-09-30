package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreDAO;
import com.itgrids.partyanalyst.dao.ICadreParticipatedElectionDAO;
import com.itgrids.partyanalyst.dao.ICadrePreviousRolesDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.VoterInfoVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.CadreParticipatedElection;
import com.itgrids.partyanalyst.model.CadrePreviousRoles;
import com.itgrids.partyanalyst.model.Constituency;
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

	
	public ICadreDAO getCadreDAO() {
		return cadreDAO;
	}

	public void setCadreDAO(ICadreDAO cadreDAO) {
		this.cadreDAO = cadreDAO;
	}

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
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
	
	public List<VoterInfoVO> getSearchDetailsCadreRegistration(Long constituencyId, String seachType, String candidateName, String voterCardId, String houseNo)
	{
		
		StringBuilder searchQuery = new StringBuilder();
		List<VoterInfoVO> returnList = null;
		List searchList = null;
		SimpleDateFormat format  = new SimpleDateFormat("yy/MM/dd");
		
		try {
			
			if(seachType.equalsIgnoreCase("voter"))
			{
				if(candidateName != null && candidateName.trim().length()>0)
				{
					searchQuery.append(" BPV.voter.name like '%"+candidateName+"%' and");
				}
				if(voterCardId != null  && houseNo.trim().length()>0)
				{
					searchQuery.append("  BPV.voter.voterIDCardNo like '%"+voterCardId+"%' and");
				}
				if(houseNo != null  && houseNo.trim().length()>0)
				{
					searchQuery.append(" BPV.voter.houseNo like '%"+houseNo+"%' and" );
				}

				searchList = 	boothPublicationVoterDAO.getVotersDetailsForCadreRegistratiobByconstituencId(constituencyId,IConstants.VOTER_DATA_PUBLICATION_ID,searchQuery.toString());
				
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
				if(candidateName != null   && candidateName.trim().length()>0)
				{
					searchQuery.append(" C.firstName like '%"+candidateName+"%' OR C.lastName like '%"+candidateName+"%' and ");
				}
				
				searchList = cadreDAO.getCadreDetailsForCadreRegistratiobByconstituencId(constituencyId,searchQuery.toString());
				
				List<Long> cadreIdList = new ArrayList<Long>();
				
				if(searchList != null && searchList.size()>0 )
				{
					returnList = new ArrayList<VoterInfoVO>();
					
					for (Object param : searchList)
					{
						Object[] cadre = (Object[]) param;
						
						cadreIdList.add(cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L);
						
						
						VoterInfoVO vo = new VoterInfoVO();
						vo.setId(cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L);
						vo.setCadreId(cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L);
						vo.setName(cadre[1] != null ? cadre[1].toString().trim():"");
						
						vo.setRelativeName(cadre[3] != null ? cadre[3].toString().trim():"");
						
						String dateOfBirth = cadre[4] != null ? cadre[4].toString().trim():"";
						
						if(dateOfBirth != null && dateOfBirth.trim().length()>0)
						{
							Calendar startDate = new GregorianCalendar();
							Calendar endDate = new GregorianCalendar();
							
							startDate.setTime(format.parse(dateOfBirth));
							
							endDate.setTime(new Date());

							int diffYear = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
							
							vo.setAge(String.valueOf(diffYear));
						}
						else if(cadre[5] != null )
						{
							vo.setAge(cadre[5] != null ? cadre[5].toString().trim():"");
						}
						
						vo.setHouseNo(" -- ");
						vo.setRelationType(" -- ");
						
						vo.setGender(cadre[6] != null ? cadre[6].toString().trim():"");
						
						returnList.add(vo);
					}
				}
				
				
				if(voterCardId != null   && voterCardId.trim().length()>0)
				{
					searchQuery.append("  V.voterIDCardNo like '%"+voterCardId+"%' and");
				}
				if(houseNo != null   && voterCardId.trim().length()>0)
				{
					searchQuery.append("  V.houseNo like '%"+houseNo+"%' and");
				}
				
				List<Object[]> cadreDetails = cadreDAO.getvoterdetailsByCadreIds(cadreIdList,searchQuery.toString());
				
				if(cadreDetails != null && cadreDetails.size()>0)
				{
					for (Object[] cadre : cadreDetails) 
					{
						VoterInfoVO vo = getMatchedVOById(returnList, Long.valueOf(cadre[0].toString()));
						
						if(vo != null )
						{
							vo.setCadreId(cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L);
							vo.setName(cadre[1] != null ? cadre[1].toString().trim():"");
							vo.setId(cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L);
							vo.setRelativeName(cadre[ 2] != null ? cadre[2].toString().trim():"");
							
							if(cadre[3] != null )
							{
								vo.setAge(cadre[3] != null ? cadre[3].toString().trim():"");
							}
							vo.setGender(cadre[4] != null ? cadre[4].toString().trim():"");
							vo.setHouseNo(cadre[5] != null ? cadre[5].toString().trim():"");
							vo.setRelationType(cadre[6] != null ? cadre[6].toString().trim():"");
						}
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getSearchDetailsCadreRegistration in CadreRegistrationService service", e);
		}
		
		return returnList;
	}
	
	private VoterInfoVO getMatchedVOById(List<VoterInfoVO> list , Long id)
	{
		VoterInfoVO voterInfoVO = null;
		try {

			if(list != null && list.size()>0)
			{
				for (VoterInfoVO voterVO : list) 
				{
					if(voterVO.getId().longValue() == id.longValue())
					{
						return voterInfoVO;
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getMatchedVOById in CadreRegistrationService service", e);
		}
		
		return voterInfoVO;
	}
	public List<VoterInfoVO> getCandidateInfoBySearchCriteria(String searchType, Long candidateId)
	{
		List<VoterInfoVO> returnList = null;
		SimpleDateFormat format  = new SimpleDateFormat("yy/MM/dd");
		
		try {
			
			if(searchType.equalsIgnoreCase("voter"))
			{
				Voter voter = voterDAO.get(candidateId);
				
				if(voter != null )
				{
					returnList = new ArrayList<VoterInfoVO>();
					VoterInfoVO vo = new VoterInfoVO();
					
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
					returnList.add(vo);
				}
			}
			
			else if(searchType.equalsIgnoreCase("cadre"))
			{
				
				List<Cadre> searchList = cadreDAO.findByCadreIDs(candidateId.toString());
				
				if(searchList != null && searchList.size()>0 )
				{
					returnList = new ArrayList<VoterInfoVO>();
					
					for (Object param : searchList)
					{
						try {

							Cadre cadre = (Cadre) param;
							VoterInfoVO vo = new VoterInfoVO();
							vo.setCadreId(cadre.getCadreId());
							String dateOfBirth = cadre.getDateOfBirth() != null ? cadre.getDateOfBirth().toString().substring(0, 10):"";
							vo.setDateOfBirth(dateOfBirth);
							
							if(cadre.getVoter() != null)
							{
								vo.setHouseNo(cadre.getVoter().getHouseNo());
								vo.setName(cadre.getVoter().getName());
								vo.setRelativeName(cadre.getVoter().getRelativeName());
								vo.setRelationType(cadre.getVoter().getRelationshipType());
								vo.setAge(cadre.getVoter().getAge().toString());
								vo.setGender(cadre.getVoter().getGender());
								vo.setVoterId(cadre.getVoter() != null ? cadre.getVoter().getVoterId(): 0L);
								vo.setVoterCardNo(cadre.getVoter() != null ? cadre.getVoter().getVoterIDCardNo():"");
																
							}
							else
							{
								vo.setName(cadre.getFirstName() != null? cadre.getFirstName():"");
								vo.setName(vo.getName() + " "+(cadre.getLastName() != null ? cadre.getLastName():""));
								vo.setRelativeName(cadre.getFatherOrSpouseName() != null ? cadre.getFatherOrSpouseName():"");
								vo.setGender(cadre.getGender() != null ? cadre.getGender().toString():"");
								
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
								else if(cadre.getAge() != null && cadre.getAge().toString().trim().length()>0 )
								{
									vo.setAge(cadre.getAge().toString());
								}
							}
							
							vo.setBlodGroupId(cadre.getBloodGroup() != null ? cadre.getBloodGroupId():0L);
							vo.setCasteName(cadre.getCasteState() != null ? cadre.getCasteState().getCaste().getCasteName():"");
							vo.setActiveDate(cadre.getActiveDateField() != null ? cadre.getActiveDateField().toString().substring(0, 10):"");
							vo.setEducation(cadre.getEducation() != null ? cadre.getEducation().getEduQualificationId().toString():"0");
							vo.setOccupation(cadre.getOccupation() != null ? cadre.getOccupation().getOccupationId().toString():"0");
							vo.setLocation(cadre.getCurrentAddress() != null ? (cadre.getCurrentAddress().getHamlet() != null ?cadre.getCurrentAddress().getHamlet().getHamletName().toString():""):"");
							vo.setMobileNo(cadre.getMobile() != null ? cadre.getMobile():"");
							
							returnList.add(vo);
						
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getCandidateInfoBySearchCriteria in CadreRegistrationService service", e);
		}
		
		return returnList;
	}

}
