package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.ICadreParticipatedElectionDAO;
import com.itgrids.partyanalyst.dao.ICadrePreviousRolesDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.CadreParticipatedElection;
import com.itgrids.partyanalyst.model.CadrePreviousRoles;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;

public class CadreRegistrationService implements ICadreRegistrationService {
	
	private static final Logger LOG = Logger.getLogger(CadreRegistrationService.class);
	

	private ITdpCadreDAO                    tdpCadreDAO ;
	private TransactionTemplate             transactionTemplate;
	private ICadrePreviousRolesDAO		    cadrePreviousRolesDAO;
	private ICadreParticipatedElectionDAO	cadreParticipatedElectionDAO;


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
								
								if(cadreRegistrationVO.getVoterName() != null && cadreRegistrationVO.getVoterName().equalsIgnoreCase("null"))
								{
									tdpCadre.setFirstname(cadreRegistrationVO.getVoterName());
								}
								if(cadreRegistrationVO.getDob() != null )
								{
									tdpCadre.setDateOfBirth(cadreRegistrationVO.getDob());
								}
								if(cadreRegistrationVO.getGender() != null && cadreRegistrationVO.getGender().equalsIgnoreCase("null"))
								{
									tdpCadre.setGender(cadreRegistrationVO.getGender());
								}
								if(cadreRegistrationVO.getRelativeName() != null && cadreRegistrationVO.getRelativeName().equalsIgnoreCase("null"))
								{
									tdpCadre.setRelativename(cadreRegistrationVO.getRelativeName());
								}
								if(cadreRegistrationVO.getVoterId() != null && cadreRegistrationVO.getVoterId().longValue() > 0)
								{
									tdpCadre.setVoterId(cadreRegistrationVO.getVoterId());
								}
								if(cadreRegistrationVO.getPreviousEnrollmentNumber() != null && cadreRegistrationVO.getPreviousEnrollmentNumber().equalsIgnoreCase("null"))
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
								
								if(cadreRegistrationVO.getMobileNumber() != null && cadreRegistrationVO.getMobileNumber().equalsIgnoreCase("null"))
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
								if(cadreRegistrationVO.getHouseNo() != null && cadreRegistrationVO.getHouseNo().equalsIgnoreCase("null"))
								{
									tdpCadre.setHouseNo(cadreRegistrationVO.getHouseNo());
								}
								tdpCadreDAO.save(tdpCadre);
								
								List<CadrePreviousRollesVO> previousRollesPartList = cadreRegistrationVO.getPreviousRollesList();
								if(previousRollesPartList != null && previousRollesPartList.size() > 0)
								{
									for (CadrePreviousRollesVO rolesVO : previousRollesPartList)
									{
										 
										if(tdpCadre != null)
										{
											CadrePreviousRoles cadrePreviousRoles = new CadrePreviousRoles();
											cadrePreviousRoles.setTdpCadre(tdpCadre);
											
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
											
											cadreParticipatedElection.setTdpCadre(tdpCadre);
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
	
	

}
