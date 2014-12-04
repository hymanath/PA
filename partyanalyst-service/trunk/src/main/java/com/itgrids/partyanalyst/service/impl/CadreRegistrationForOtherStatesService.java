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
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.io.FileUtils;
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
import com.itgrids.partyanalyst.dao.ICardReceiverDAO;
import com.itgrids.partyanalyst.dao.ICardSenderDAO;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ICountryDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyDesignationDAO;
import com.itgrids.partyanalyst.dao.ISmsJobStatusDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITabRecordsStatusDAO;
import com.itgrids.partyanalyst.dao.ITabUserKeysDAO;
import com.itgrids.partyanalyst.dao.ITabUserLoginDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreBackupDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreFamilyDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreOnlineDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreTeluguNamesDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreVerfiedDataDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterNamesDAO;
import com.itgrids.partyanalyst.dao.IVoterRelationDAO;
import com.itgrids.partyanalyst.dto.CadreFamilyVO;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.CadreParticipatedElection;
import com.itgrids.partyanalyst.model.CadrePreviousRoles;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.SmsJobStatus;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.TdpCadreFamilyDetails;
import com.itgrids.partyanalyst.model.TdpCadreOnline;
import com.itgrids.partyanalyst.model.TdpCadreTeluguNames;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterNames;
import com.itgrids.partyanalyst.service.ICadreRegistrationForOtherStatesService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;

public class CadreRegistrationForOtherStatesService implements
		ICadreRegistrationForOtherStatesService {
	private static final Logger LOG = Logger
			.getLogger(CadreRegistrationForOtherStatesService.class);
	private ITdpCadreDAO tdpCadreDAO;
	private TransactionTemplate transactionTemplate;
	private ICadrePreviousRolesDAO cadrePreviousRolesDAO;
	private ICadreParticipatedElectionDAO cadreParticipatedElectionDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private ICountryDAO countryDAO;
	private IStateDAO stateDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IUserAddressDAO userAddressDAO;
	private DateUtilService dateUtilService;

	private ICadreDAO cadreDAO;
	private IVoterDAO voterDAO;
	private IConstituencyDAO constituencyDAO;
	private ITehsilDAO tehsilDAO;
	private IPanchayatDAO panchayatDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IBoothDAO boothDAO;
	private ICasteStateDAO casteStateDAO;
	private IBloodGroupDAO bloodGroupDAO;

	private IElectionDAO electionDAO;
	private ICadreLevelDAO cadreLevelDAO;
	private IPartyDesignationDAO partyDesignationDAO;
	private IElectionTypeDAO electionTypeDAO;
	private ITdpCadreFamilyDetailsDAO tdpCadreFamilyDetailsDAO;
	private ImageAndStringConverter imageAndStringConverter = new ImageAndStringConverter();

	private ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO;
	private ICadreSurveyUserDAO cadreSurveyUserDAO;
	private IRegionServiceData regionServiceDataImp;
	private IOccupationDAO occupationDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private INominationDAO nominationDAO;
	private IVoterRelationDAO voterRelationDAO;
	private ICadreCommitteeDAO cadreCommitteeDAO;
	private ICadreCommitteeLevelDAO cadreCommitteeLevelDAO;
	private ICadreRolesDAO cadreRolesDAO;
	private ICadreCommitteeRoleDAO cadreCommitteeRoleDAO;
	private ITdpCadreBackupDetailsDAO tdpCadreBackupDetailsDAO;

	private ICardSenderDAO cardSenderDAO;
	private ICardReceiverDAO cardReceiverDAO;

	private ICasteDAO casteDAO;
	private IVoterNamesDAO voterNamesDAO;
	private ITdpCadreOnlineDAO tdpCadreOnlineDAO;
	private ISmsJobStatusDAO smsJobStatusDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IDistrictDAO districtDAO;
	private ITabRecordsStatusDAO tabRecordsStatusDAO;
	private ITabUserLoginDetailsDAO tabUserLoginDetailsDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private ITdpCadreTeluguNamesDAO tdpCadreTeluguNamesDAO;
	private ITdpCadreVerfiedDataDAO tdpCadreVerfiedDataDAO;
	private ITabUserKeysDAO tabUserKeysDAO;

	public void tdpCadreSavingLogic(final String registrationType,
			final List<CadreRegistrationVO> cadreRegistrationVOList,
			final CadreRegistrationVO cadreRegistrationVO,
			final SurveyCadreResponceVO surveyCadreResponceVO,
			final TdpCadre tdpCadre,final String insertType, final boolean statusVar) {
		/* try { */
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
				if (cadreRegistrationVO.getVoterId() != null && Long.valueOf(cadreRegistrationVO.getVoterId()) > 0) {
					tdpCadre.setVoterId(Long.valueOf(cadreRegistrationVO.getVoterId()));
				}else if (cadreRegistrationVO.getVoterCardNumber() != null && cadreRegistrationVO.getVoterCardNumber().trim().length() > 0) {
					List<Long> voterCardNumbersList = voterDAO.getVoterId(cadreRegistrationVO.getVoterCardNumber());
					if (voterCardNumbersList != null && voterCardNumbersList.size() > 0) {
						 tdpCadre.setVoterId(voterCardNumbersList.get(0));
					}
				}
				if (cadreRegistrationVO.getAge() != null && cadreRegistrationVO.getAge() > 0) {
					tdpCadre.setAge(cadreRegistrationVO.getAge());
				} else if (tdpCadre.getVoterId() != null) {
					tdpCadre.setAge(voterDAO.get(tdpCadre.getVoterId()).getAge());
				} 

				if (cadreRegistrationVO.getPartyMemberSinceStr() != null && cadreRegistrationVO.getPartyMemberSinceStr().trim().length() > 0) {
					tdpCadre.setPartyMemberSince(convertToDateFormet(cadreRegistrationVO.getPartyMemberSinceStr()));
				} 
				if (cadreRegistrationVO.getBloodGroupId() != null && cadreRegistrationVO.getBloodGroupId().longValue() > 0) {
					tdpCadre.setBloodGroupId(cadreRegistrationVO.getBloodGroupId());
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
		

				UserAddress userAddress = new UserAddress();
				Constituency constituency  = constituencyDAO.get(Long.valueOf(cadreRegistrationVO.getConstituencyId()));
				userAddress.setConstituency(constituency);
				userAddress.setState(constituency.getState());

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
				tdpCadre.setEnrollmentYear(IConstants.CADRE_ENROLLMENT_NUMBER);

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
				tdpCadre.setPhotoType("NEW");
				if (cadreRegistrationVO.getRelationType() != null  && cadreRegistrationVO.getRelationType().trim().length() > 0) {
					tdpCadre.setRelativeType(cadreRegistrationVO.getRelationType());
				}
				
				tdpCadre.setCardNo(cadreRegistrationVO.getVoterCardNumber());
				
				if (cadreRegistrationVO.getFamilyVoterId() != null
						&& registrationType.equalsIgnoreCase("WEB")) {
					tdpCadre.setFamilyVoterId(cadreRegistrationVO
							.getFamilyVoterId());
					tdpCadre.setIsRelative("Y");
					tdpCadre.setRelationTypeId(cadreRegistrationVO
							.getRelationTypeId());
				}

				if (registrationType != null
						&& (registrationType.equalsIgnoreCase("WEB") || registrationType
								.equalsIgnoreCase("ONLINE"))
						&& insertType.equalsIgnoreCase("new")) {
					String userId = "0000";
					if (cadreRegistrationVO.getCreatedUserId() != null) {
						userId = cadreRegistrationVO.getCreatedUserId()
								.toString();
					}
					String ref = getReferenceNo(userId, registrationType);

					if (tdpCadre.getRefNo() == null
							|| tdpCadre.getRefNo().trim().length() == 0) {
						ref = getUniueRefNo(ref, registrationType);
						tdpCadre.setRefNo(ref);
					}
					cadreRegistrationVO.setRefNo(tdpCadre.getRefNo());

					surveyCadreResponceVO.setEnrollmentNumber(tdpCadre
							.getRefNo());
					// uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre);
					tdpCadre1 = tdpCadreDAO.save(tdpCadre);

				} else if (registrationType != null
						&& (registrationType.equalsIgnoreCase("WEB") || registrationType
								.equalsIgnoreCase("ONLINE"))
						&& !insertType.equalsIgnoreCase("new")) {
					surveyCadreResponceVO.setEnrollmentNumber(tdpCadre
							.getRefNo());
					tdpCadre1 = tdpCadreDAO.save(tdpCadre);
				} else {
					if (insertType.equalsIgnoreCase("new")) {

						tdpCadre.setRefNo(cadreRegistrationVO.getRefNo());

						surveyCadreResponceVO.setEnrollmentNumber(tdpCadre
								.getRefNo());
						// uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre);
						tdpCadre1 = tdpCadreDAO.save(tdpCadre);

					} else {
						// tdpCadre.setRefNo(cadreRegistrationVO.getRefNo());
						// uploadProfileImage(cadreRegistrationVO,registrationType,tdpCadre);
						surveyCadreResponceVO.setEnrollmentNumber(tdpCadre
								.getRefNo());

						tdpCadre1 = tdpCadreDAO.save(tdpCadre);
					}
				}
				if (tdpCadre1.getMemberShipNo() == null
						|| tdpCadre1.getMemberShipNo().trim().length() == 0) {
					Long distId = 1l;
					if (userAddress.getDistrict() != null) {
						distId = userAddress.getDistrict().getDistrictId();
					}
					String membershipNo = getMemberShipNo(distId,
							tdpCadre1.getTdpCadreId());
					tdpCadre1.setMemberShipNo(membershipNo);
				}
				uploadProfileImage(cadreRegistrationVO, registrationType,
						tdpCadre1);
				tdpCadre1 = tdpCadreDAO.save(tdpCadre1);

				// SAVING THE TELUGU NAME OF NON VOTER -- START //SASI
				if (tdpCadre1.getVoterId() == null
						&& cadreRegistrationVO.getVoterTeluguName() != null
						&& cadreRegistrationVO.getVoterTeluguName().trim()
								.length() > 0) {
					TdpCadreTeluguNames model = new TdpCadreTeluguNames();
					model.setTeluguName(cadreRegistrationVO
							.getVoterTeluguName().trim());
					model.setTdpCadreId(tdpCadre1.getTdpCadreId());
					model.setEnglishName(cadreRegistrationVO.getVoterName());
					model.setInsertedTime(dateUtilService
							.getCurrentDateAndTime());
					tdpCadreTeluguNamesDAO.save(model);
				}
				// SAVING THE TELUGU NAME OF NON VOTER -- END

				surveyCadreResponceVO.setEnrollmentNumber(tdpCadre1.getRefNo());
				List<CadrePreviousRollesVO> previousRollesPartList = cadreRegistrationVO
						.getPreviousRollesList();
				if (previousRollesPartList != null
						&& previousRollesPartList.size() > 0) {
					for (CadrePreviousRollesVO rolesVO : previousRollesPartList) {
						if (rolesVO != null) {
							if (tdpCadre1 != null) {
								CadrePreviousRoles cadrePreviousRoles = new CadrePreviousRoles();
								cadrePreviousRoles.setTdpCadreId(tdpCadre1
										.getTdpCadreId());

								if (rolesVO.getCadreCommitteeId() != null
										&& rolesVO.getCadreCommitteeId()
												.longValue() > 0
										&& rolesVO.getCadreCommitteeLevelId() != null
										&& rolesVO.getCadreCommitteeLevelId()
												.longValue() > 0
										&& rolesVO.getCadreRoleId() != null
										&& rolesVO.getCadreRoleId().longValue() > 0) {
									List<Long> cadreCommotteRoleIds = cadreCommitteeRoleDAO.getCadreCommitteRoleIdBySelection(
											rolesVO.getCadreCommitteeLevelId(),
											rolesVO.getCadreRoleId(),
											rolesVO.getCadreCommitteeId());
									if (cadreCommotteRoleIds != null
											&& cadreCommotteRoleIds.size() > 0) {
										cadrePreviousRoles
												.setCadreCommitteeRoleId(cadreCommotteRoleIds
														.get(0));
									}

								}
								if (rolesVO.getFromDateStr() != null
										&& rolesVO.getFromDateStr().trim()
												.length() > 0
										&& !rolesVO.getFromDateStr().trim()
												.equalsIgnoreCase("null")) {
									cadrePreviousRoles
											.setFromDate(convertToDateFormet(rolesVO
													.getFromDateStr()));
								}
								if (rolesVO.getToDateStr() != null
										&& rolesVO.getToDateStr().trim()
												.length() > 0
										&& !rolesVO.getToDateStr().trim()
												.equalsIgnoreCase("null")) {
									cadrePreviousRoles
											.setToDate(convertToDateFormet(rolesVO
													.getToDateStr()));
								}
								cadrePreviousRoles
										.setInsertedDate(dateUtilService
												.getCurrentDateAndTime());
								cadrePreviousRoles
										.setUpdatedDate(dateUtilService
												.getCurrentDateAndTime());
								cadrePreviousRoles.setIsDeleted("N");
								cadrePreviousRolesDAO.save(cadrePreviousRoles);
								// }

							}
						}

					}
				}

				List<CadrePreviousRollesVO> previousElectionPartList = cadreRegistrationVO
						.getPreviousParicaptedElectionsList();
				if (previousElectionPartList != null
						&& previousElectionPartList.size() > 0) {
					for (CadrePreviousRollesVO electionVO : previousElectionPartList) {
						if (electionVO != null) {
							if (tdpCadre1 != null) {
								if (electionVO.getConstituencyId() != null
										&& electionVO.getConstituencyId()
												.trim().length() > 0) {
									CadreParticipatedElection cadreParticipatedElection = new CadreParticipatedElection();

									cadreParticipatedElection
											.setTdpCadreId(tdpCadre1
													.getTdpCadreId());
									if (electionVO.getElectionTypeId() != null
											&& electionVO.getElectionTypeId()
													.trim().length() > 0) {
										if (Long.valueOf(electionVO
												.getElectionTypeId()) > 0) {
											cadreParticipatedElection.setElectionId(Long.valueOf(electionVO
													.getElectionTypeId()));
										}

									}
									if (electionVO.getConstituencyId() != null
											&& electionVO.getConstituencyId()
													.trim().length() > 0) {
										if (Long.valueOf(
												electionVO.getConstituencyId())
												.longValue() > 0) {
											cadreParticipatedElection.setConstituencyId(Long.valueOf(electionVO
													.getConstituencyId()));
										}

									}
									if (electionVO.getCandidateId() != null
											&& electionVO.getCandidateId()
													.trim().length() > 0) {
										if (Long.valueOf(
												electionVO.getCandidateId())
												.longValue() > 0) {
											cadreParticipatedElection.setCandidateId((Long
													.valueOf(electionVO
															.getCandidateId())));
										}
									}
									cadreParticipatedElection.setIsDeleted("N");
									cadreParticipatedElectionDAO
											.save(cadreParticipatedElection);
								}

							}
						}

					}
				}

				List<CadreFamilyVO> cadreFamilyDetailsList = cadreRegistrationVO
						.getCadreFamilyDetails();
				if (cadreFamilyDetailsList != null
						&& cadreFamilyDetailsList.size() > 0) {
					for (CadreFamilyVO cadreFamilyVO : cadreFamilyDetailsList) {
						if (tdpCadre1 != null) {
							if (cadreFamilyVO != null) {
								// if((cadreFamilyVO.getOccupationId() != null
								// &&
								// cadreFamilyVO.getOccupationId().longValue() >
								// 0) || cadreFamilyVO.getEducationId() != null
								// && cadreFamilyVO.getEducationId().longValue()
								// > 0)
								// {
								TdpCadreFamilyDetails tdpCadreFamilyDetails = new TdpCadreFamilyDetails();
								tdpCadreFamilyDetails.setTdpCadreId(tdpCadre1
										.getTdpCadreId());
								if (cadreFamilyVO.getVoterName() != null
										&& !cadreFamilyVO.getVoterName()
												.equalsIgnoreCase("null")
										&& cadreFamilyVO.getVoterName().trim()
												.length() > 0) {
									tdpCadreFamilyDetails
											.setVoterName(cadreFamilyVO
													.getVoterName());
								}
								if (cadreFamilyVO.getOccupationId() != null
										&& cadreFamilyVO.getOccupationId()
												.longValue() > 0l) {
									tdpCadreFamilyDetails
											.setOccupationId(cadreFamilyVO
													.getOccupationId());
								}
								if (cadreFamilyVO.getEducationId() != null
										&& cadreFamilyVO.getEducationId()
												.longValue() > 0l) {
									tdpCadreFamilyDetails
											.setEducationId(cadreFamilyVO
													.getEducationId());
								}
								if (cadreFamilyVO.getAge() != null
										&& cadreFamilyVO.getAge().longValue() > 0l) {
									tdpCadreFamilyDetails.setAge(cadreFamilyVO
											.getAge());
								}
								if (cadreFamilyVO.getGender() != null
										&& cadreFamilyVO.getGender().trim()
												.length() > 0) {
									tdpCadreFamilyDetails
											.setGender(cadreFamilyVO
													.getGender().trim());
								}
								if (cadreFamilyVO.getFamilyRelationId() != null
										&& cadreFamilyVO.getFamilyRelationId()
												.longValue() > 0) {
									tdpCadreFamilyDetails
											.setFamilyRelationId(cadreFamilyVO
													.getFamilyRelationId()
													.longValue());
								}
								if (cadreFamilyVO.getVoterId() != null
										&& cadreFamilyVO.getVoterId()
												.longValue() > 0l) {
									tdpCadreFamilyDetails
											.setVoterId(cadreFamilyVO
													.getVoterId());
									try {
										Voter voter = voterDAO
												.get(cadreFamilyVO.getVoterId());
										if (tdpCadreFamilyDetails.getGender() == null) {
											tdpCadreFamilyDetails
													.setGender(voter
															.getGender());
										}
										if (tdpCadreFamilyDetails.getAge() == null) {
											tdpCadreFamilyDetails.setAge(voter
													.getAge());
										}
									} catch (Exception e) {

									}
								} else {
									if (cadreFamilyVO.getVoterCadreNO() != null
											&& !cadreFamilyVO.getVoterCadreNO()
													.equalsIgnoreCase("null")
											&& cadreFamilyVO.getVoterCadreNO()
													.trim().length() > 0) {
										List<Long> voterCardNumbersList = voterDAO
												.getVoterId(cadreFamilyVO
														.getVoterCadreNO());
										if (voterCardNumbersList != null
												&& voterCardNumbersList.size() > 0) {
											tdpCadreFamilyDetails
													.setVoterId(voterCardNumbersList
															.get(0));
											Voter voter = voterDAO
													.get(voterCardNumbersList
															.get(0));
											if (tdpCadreFamilyDetails
													.getGender() == null) {
												tdpCadreFamilyDetails
														.setGender(voter
																.getGender());
											}
											if (tdpCadreFamilyDetails.getAge() == null) {
												tdpCadreFamilyDetails
														.setAge(voter.getAge());
											}
										}

									}
								}
								tdpCadreFamilyDetails.setIsDeleted("N");
								tdpCadreFamilyDetails
										.setInsertedDate(dateUtilService
												.getCurrentDateAndTime());
								tdpCadreFamilyDetails
										.setUpdatedDate(dateUtilService
												.getCurrentDateAndTime());
								tdpCadreFamilyDetailsDAO
										.save(tdpCadreFamilyDetails);
								// }

							}

						}

					}
				}
				surveyCadreResponceVO.setStatus("SUCCESS");
				surveyCadreResponceVO.setResultCode(ResultCodeMapper.SUCCESS);
				if (insertType.equalsIgnoreCase("new")
						&& cadreRegistrationVO.getMobileNumber() != null
						&& cadreRegistrationVO.getMobileNumber().trim()
								.length() > 0
						&& cadreRegistrationVO.getRefNo() != null) {
					// sendSMS(cadreRegistrationVO.getMobileNumber().trim(),
					// "Thank You for registering as TDP cadre.For further queries use Ref No "+cadreRegistrationVO.getRefNo());
					if (!statusVar) {
						Boolean flag = true;
						try {

							if (cadreRegistrationVO.getFamilyVoterId() != null
									&& cadreRegistrationVO.getFamilyVoterId()
											.toString().trim().length() > 0
									&& !cadreRegistrationVO.getFamilyVoterId()
											.toString().trim()
											.equalsIgnoreCase("null")
									&& cadreRegistrationVO.getFamilyVoterId()
											.longValue() > 0) {
								Long count = tdpCadreDAO
										.checkForFamilyExists(cadreRegistrationVO
												.getUniqueKey());
								if (count > 1) {
									flag = false;
								}

							}

							if (flag) {
								String jobCode = sendSMSInTelugu(
										cadreRegistrationVO.getMobileNumber()
												.trim(),
										getUniCodeMessage(StringEscapeUtils
												.unescapeJava("\u0C24\u0C46\u0C32\u0C41\u0C17\u0C41 \u0C26\u0C47\u0C36\u0C02 \u0C2A\u0C3E\u0C30\u0C4D\u0C1F\u0C40 \u0C15\u0C4D\u0C30\u0C3F\u0C2F\u0C3E\u0C36\u0C40\u0C32 \u0C15\u0C3E\u0C30\u0C4D\u0C2F\u0C15\u0C30\u0C4D\u0C24\u0C17\u0C3E \u0C28\u0C2E\u0C4B\u0C26\u0C41 \u0C1A\u0C47\u0C38\u0C41\u0C15\u0C41\u0C28\u0C4D\u0C28\u0C02\u0C26\u0C41\u0C15\u0C41 \u0C27\u0C28\u0C4D\u0C2F\u0C35\u0C3E\u0C26\u0C3E\u0C32\u0C41. \u0C2E\u0C40 \u0C2F\u0C4A\u0C15\u0C4D\u0C15 \u0C30\u0C3F\u0C2B\u0C30\u0C46\u0C28\u0C4D\u0C38\u0C4D \u0C28\u0C46\u0C02\u0C2C\u0C30\u0C4D : ")
												+ cadreRegistrationVO
														.getRefNo()));
								Long tdpCadreId = tdpCadre1.getTdpCadreId();
								if (tdpCadreId != null) {
									if (tdpCadre1.getMobileNo() != null) {
										jobCode = jobCode.replace("OK:", "");
										SmsJobStatus smsJobStatus = new SmsJobStatus();
										smsJobStatus.setTdpCadreId(tdpCadreId);

										smsJobStatus.setMobileNumber(tdpCadre1
												.getMobileNo());
										smsJobStatus.setJobCode(jobCode);
										smsJobStatus
												.setFromTask("Registration");

										smsJobStatusDAO.save(smsJobStatus);
										// tdpCadreDAO.updateSmsJobCode(tdpCadreId,
										// jobCode.trim());
									}
								}
							}

						} catch (Exception e) {
							LOG.error("Exception Raised while sending SMS" + e);
						}

					}
				}
			}
		});

		/*
		 * } catch (Exception e) { LOG.error(
		 * "Exception raised in tdpCadreSavingLogic in CadreRegistrationService service"
		 * , e); }
		 */
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

	public String getUniueRefNo(String ref, String dataSource) {
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

	public String getReferenceNo(String userId, String registrationType) {
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
		if (registrationType.equalsIgnoreCase("ONLINE")) {
			ref = "TR-O-" + userId + "-";
		}

		return ref;
	}

	private String getMemberShipNo(Long districtId, Long id) {
		String memberShipNo = "AP14";
		if (districtId != null && districtId.longValue() < 11l) {
			memberShipNo = "TS14";
		}
		String randomNo = memberShipNo + "0" + id;

		return randomNo;
	}

	public void uploadProfileImage(CadreRegistrationVO cadreRegistrationVO,
			String registrationType, TdpCadre tdpCadre) {
		LOG.error("PHOTOTYPE: " + cadreRegistrationVO.getPhotoType());
		LOG.error("EnrollmentNumber: "
				+ cadreRegistrationVO.getPreviousEnrollmentNumber());
		LOG.error("VoterId: " + tdpCadre.getVoterId());
		LOG.error("ConstituencyId: " + cadreRegistrationVO.getConstituencyId());
		if (cadreRegistrationVO.getPhotoType() != null
				&& cadreRegistrationVO.getPhotoType().trim()
						.equalsIgnoreCase("cadre")) {
			LOG.error("1");
			if (cadreRegistrationVO.getPreviousEnrollmentNumber() != null
					&& cadreRegistrationVO.getPreviousEnrollmentNumber().trim()
							.length() > 0) {
				LOG.error("2");
				String reqImage = getCadreImage(cadreRegistrationVO
						.getPreviousEnrollmentNumber().trim());
				if (reqImage != null) {
					LOG.error("3");
					String pathSeperator = System
							.getProperty(IConstants.FILE_SEPARATOR);
					String destinationPath = IConstants.STATIC_CONTENT_FOLDER_URL
							+ "images"
							+ pathSeperator
							+ IConstants.CADRE_IMAGES
							+ pathSeperator
							+ tdpCadre.getMemberShipNo() + ".jpg";
					String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL
							+ "images" + pathSeperator
							+ IConstants.CADRE_IMAGES + pathSeperator
							+ reqImage;
					String status = copyFile(sourcePath, destinationPath);
					LOG.error("CADRE: SP:" + sourcePath + " DP"
							+ destinationPath);
					if (status.equalsIgnoreCase("success")) {
						LOG.error("4");
						tdpCadre.setImage(tdpCadre.getMemberShipNo() + ".jpg");
						LOG.error("Success:" + tdpCadre.getMemberShipNo()
								+ ".jpg");
					} else {
						LOG.error("5");
						if (tdpCadre.getVoterId() != null) {
							Voter voter = voterDAO.get(tdpCadre.getVoterId());
							if (voter != null
									&& cadreRegistrationVO.getConstituencyId() != null
									&& Long.valueOf(
											cadreRegistrationVO
													.getConstituencyId().trim())
											.longValue() > 0) {
								List<String> partNos = boothPublicationVoterDAO
										.getPartNo(Long
												.valueOf(cadreRegistrationVO
														.getConstituencyId()
														.trim()), voter
												.getVoterId());
								LOG.error("partNos size : " + partNos.size());
								if (partNos.size() > 0
										&& partNos.get(0) != null
										&& voter.getVoterIDCardNo() != null) {
									sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL
											+ "voter_images"
											+ pathSeperator
											+ cadreRegistrationVO
													.getConstituencyId().trim()
											+ pathSeperator
											+ "Part"
											+ partNos.get(0).trim()
											+ pathSeperator
											+ voter.getVoterIDCardNo().trim()
											+ ".jpg";
									LOG.error("CADRENOTVOTER: SP:" + sourcePath
											+ " DP:" + destinationPath
											+ " VOTERID: " + voter.getVoterId());
									status = copyFile(sourcePath,
											destinationPath);
									LOG.error("Status : " + status);
									if (status.equalsIgnoreCase("success")) {
										tdpCadre.setImage(tdpCadre
												.getMemberShipNo() + ".jpg");
										LOG.error("Success:"
												+ tdpCadre.getMemberShipNo()
												+ ".jpg");
									}
								}
							}
						}
					}
				} else {
					LOG.error("6");
					if (tdpCadre.getVoterId() != null) {
						LOG.error("7");
						Voter voter = voterDAO.get(tdpCadre.getVoterId());
						String pathSeperator = System
								.getProperty(IConstants.FILE_SEPARATOR);
						if (voter != null
								&& cadreRegistrationVO.getConstituencyId() != null
								&& Long.valueOf(
										cadreRegistrationVO.getConstituencyId()
												.trim()).longValue() > 0) {
							List<String> partNos = boothPublicationVoterDAO
									.getPartNo(Long.valueOf(cadreRegistrationVO
											.getConstituencyId().trim()), voter
											.getVoterId());
							LOG.error("partNos size : " + partNos.size());
							if (partNos.size() > 0 && partNos.get(0) != null
									&& voter.getVoterIDCardNo() != null) {
								String destinationPath = IConstants.STATIC_CONTENT_FOLDER_URL
										+ "images"
										+ pathSeperator
										+ IConstants.CADRE_IMAGES
										+ pathSeperator
										+ tdpCadre.getMemberShipNo() + ".jpg";
								String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL
										+ "voter_images"
										+ pathSeperator
										+ cadreRegistrationVO
												.getConstituencyId().trim()
										+ pathSeperator
										+ "Part"
										+ partNos.get(0).trim()
										+ pathSeperator
										+ voter.getVoterIDCardNo().trim()
										+ ".jpg";
								LOG.error("CADRENOTVOTER: SP:" + sourcePath
										+ " DP:" + destinationPath
										+ " VOTERID: " + voter.getVoterId());
								String status = copyFile(sourcePath,
										destinationPath);
								LOG.error("Status : " + status);
								if (status.equalsIgnoreCase("success")) {
									tdpCadre.setImage(tdpCadre
											.getMemberShipNo() + ".jpg");
									LOG.error("Success:"
											+ tdpCadre.getMemberShipNo()
											+ ".jpg");
								}
							}
						}
					}
				}
			} else {
				LOG.error("8");
				if (tdpCadre.getVoterId() != null) {
					LOG.error("9");
					Voter voter = voterDAO.get(tdpCadre.getVoterId());
					String pathSeperator = System
							.getProperty(IConstants.FILE_SEPARATOR);
					if (voter != null
							&& cadreRegistrationVO.getConstituencyId() != null
							&& Long.valueOf(
									cadreRegistrationVO.getConstituencyId()
											.trim()).longValue() > 0) {
						List<String> partNos = boothPublicationVoterDAO
								.getPartNo(Long.valueOf(cadreRegistrationVO
										.getConstituencyId().trim()), voter
										.getVoterId());
						LOG.error("partNos size : " + partNos.size());
						if (partNos.size() > 0 && partNos.get(0) != null
								&& voter.getVoterIDCardNo() != null) {
							String destinationPath = IConstants.STATIC_CONTENT_FOLDER_URL
									+ "images"
									+ pathSeperator
									+ IConstants.CADRE_IMAGES
									+ pathSeperator
									+ tdpCadre.getMemberShipNo() + ".jpg";
							String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL
									+ "voter_images"
									+ pathSeperator
									+ cadreRegistrationVO.getConstituencyId()
											.trim()
									+ pathSeperator
									+ "Part"
									+ partNos.get(0).trim()
									+ pathSeperator
									+ voter.getVoterIDCardNo().trim() + ".jpg";
							LOG.error("CADRENOTVOTER: SP:" + sourcePath
									+ " DP:" + destinationPath + " VOTERID: "
									+ voter.getVoterId());
							String status = copyFile(sourcePath,
									destinationPath);
							LOG.error("Status : " + status);
							if (status.equalsIgnoreCase("success")) {
								tdpCadre.setImage(tdpCadre.getMemberShipNo()
										+ ".jpg");
								LOG.error("Success:"
										+ tdpCadre.getMemberShipNo() + ".jpg");
							}
						}
					}
				}
			}
		} else if (cadreRegistrationVO.getPhotoType() != null
				&& cadreRegistrationVO.getPhotoType().trim()
						.equalsIgnoreCase("voter")) {
			LOG.error("10");
			if (tdpCadre.getVoterId() != null) {
				LOG.error("11");
				String pathSeperator = System
						.getProperty(IConstants.FILE_SEPARATOR);
				String destinationPath = IConstants.STATIC_CONTENT_FOLDER_URL
						+ "images" + pathSeperator + IConstants.CADRE_IMAGES
						+ pathSeperator + tdpCadre.getMemberShipNo() + ".jpg";
				Voter voter = voterDAO.get(tdpCadre.getVoterId());
				if (voter != null
						&& cadreRegistrationVO.getConstituencyId() != null
						&& Long.valueOf(
								cadreRegistrationVO.getConstituencyId().trim())
								.longValue() > 0) {
					LOG.error("12");
					List<String> partNos = boothPublicationVoterDAO.getPartNo(
							Long.valueOf(cadreRegistrationVO
									.getConstituencyId().trim()), voter
									.getVoterId());
					LOG.error("partNos size : " + partNos.size());
					if (partNos.size() > 0 && partNos.get(0) != null
							&& voter.getVoterIDCardNo() != null) {
						String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL
								+ "voter_images"
								+ pathSeperator
								+ cadreRegistrationVO.getConstituencyId()
										.trim()
								+ pathSeperator
								+ "Part"
								+ partNos.get(0).trim()
								+ pathSeperator
								+ voter.getVoterIDCardNo().trim() + ".jpg";
						LOG.error("VOTER: SP:" + sourcePath + " DP:"
								+ destinationPath + " VOTERID: "
								+ voter.getVoterId());
						String status = copyFile(sourcePath, destinationPath);
						LOG.error("Status : " + status);
						if (status.equalsIgnoreCase("success")) {
							tdpCadre.setImage(tdpCadre.getMemberShipNo()
									+ ".jpg");
							LOG.error("Success:" + tdpCadre.getMemberShipNo()
									+ ".jpg");
						}
					}
				}
			}
		} else {
			LOG.error("13");
			if (cadreRegistrationVO.getUploadImage() != null
					&& !cadreRegistrationVO.getUploadImage().toString()
							.equalsIgnoreCase("null")) {
				LOG.error("14");
				LOG.error("IMAGE: MS:" + tdpCadre.getMemberShipNo());
				String result = uploadCadreImage(tdpCadre.getMemberShipNo(),
						cadreRegistrationVO.getPath(),
						cadreRegistrationVO.getUploadImageContentType(),
						cadreRegistrationVO.getUploadImage());
				if (result != null) {
					tdpCadre.setImage(tdpCadre.getMemberShipNo()
							+ "."
							+ cadreRegistrationVO.getUploadImageContentType()
									.split("/")[1]);
				}
			} else if (cadreRegistrationVO.getAbsolutePath() != null
					&& cadreRegistrationVO.getAbsolutePath().trim().length() > 0) {
				try {
					String pathSeperator = System
							.getProperty(IConstants.FILE_SEPARATOR);
					String destinationPath = IConstants.STATIC_CONTENT_FOLDER_URL
							+ "images"
							+ pathSeperator
							+ IConstants.CADRE_IMAGES
							+ pathSeperator
							+ tdpCadre.getMemberShipNo() + ".jpg";
					boolean status = false;
					URL head1 = new URL(cadreRegistrationVO.getAbsolutePath()
							+ ".jpg");
					if (checkUrlExit(head1)) {
						status = copyNewImg(head1, destinationPath);
					}
					URL head2 = new URL(cadreRegistrationVO.getAbsolutePath()
							+ ".JPG");
					if (!status && checkUrlExit(head2)) {
						status = copyNewImg(head2, destinationPath);
					}
					URL head3 = new URL(cadreRegistrationVO.getAbsolutePath()
							+ ".jpeg");
					if (!status && checkUrlExit(head3)) {
						status = copyNewImg(head3, destinationPath);
					}
					URL head4 = new URL(cadreRegistrationVO.getAbsolutePath()
							+ ".JPEG");
					if (!status && checkUrlExit(head4)) {
						status = copyNewImg(head4, destinationPath);
					}
					URL head5 = new URL(cadreRegistrationVO.getAbsolutePath()
							+ ".png");
					if (!status && checkUrlExit(head5)) {
						status = copyNewImg(head5, destinationPath);
					}
					URL head6 = new URL(cadreRegistrationVO.getAbsolutePath()
							+ ".PNG");
					if (!status && checkUrlExit(head6)) {
						status = copyNewImg(head6, destinationPath);
					}
					if (status) {
						tdpCadre.setImage(tdpCadre.getMemberShipNo() + ".jpg");
						tdpCadre.setPhotoType("NEW");
					}
				} catch (Exception e) {
					LOG.error(e);
				}
			} else if (cadreRegistrationVO.getPhotoType() != null
					&& cadreRegistrationVO.getPhotoType().trim()
							.equalsIgnoreCase("new")
					&& cadreRegistrationVO.getImageBase64String() != null
					&& cadreRegistrationVO.getImageBase64String().trim()
							.length() > 0) {
				LOG.error("15");
				String pathSeperator = System
						.getProperty(IConstants.FILE_SEPARATOR);
				String filePath = IConstants.STATIC_CONTENT_FOLDER_URL
						+ "images" + pathSeperator + IConstants.CADRE_IMAGES
						+ pathSeperator + tdpCadre.getMemberShipNo() + ".jpg";
				// String filePath = "D:/" + tdpCadre.getMemberShipNo()+".jpg";
				cadreRegistrationVO.setImageBase64String(cadreRegistrationVO
						.getImageBase64String().replace("_", "/"));
				cadreRegistrationVO.setImageBase64String(cadreRegistrationVO
						.getImageBase64String().replace("-", "+"));
				boolean status = imageAndStringConverter
						.convertBase64StringToImage(
								cadreRegistrationVO.getImageBase64String(),
								filePath);
				// System.out.println(cadreRegistrationVO.getImageBase64String());
				LOG.error("BASE64: DP:" + filePath);
				try {
					if (cadreRegistrationVO.getImageBase64String().length() > 55) {
						LOG.error("BASE64FIRST50C: "
								+ cadreRegistrationVO.getImageBase64String()
										.substring(0, 50));
					} else {
						LOG.error("BASE64FIRST50C: "
								+ cadreRegistrationVO.getImageBase64String());
					}
				} catch (Exception ex) {

				}
				if (status) {
					tdpCadre.setImage(tdpCadre.getMemberShipNo() + ".jpg");
					LOG.error("Success:" + tdpCadre.getMemberShipNo() + ".jpg");
				}
			} else {
				LOG.error("16");
				if (tdpCadre.getVoterId() != null) {
					LOG.error("17");
					String pathSeperator = System
							.getProperty(IConstants.FILE_SEPARATOR);
					String destinationPath = IConstants.STATIC_CONTENT_FOLDER_URL
							+ "images"
							+ pathSeperator
							+ IConstants.CADRE_IMAGES
							+ pathSeperator
							+ tdpCadre.getMemberShipNo() + ".jpg";
					Voter voter = voterDAO.get(tdpCadre.getVoterId());
					if (voter != null
							&& cadreRegistrationVO.getConstituencyId() != null
							&& Long.valueOf(
									cadreRegistrationVO.getConstituencyId()
											.trim()).longValue() > 0) {
						List<String> partNos = boothPublicationVoterDAO
								.getPartNo(Long.valueOf(cadreRegistrationVO
										.getConstituencyId().trim()), voter
										.getVoterId());
						LOG.error("partNos size : " + partNos.size());
						if (partNos.size() > 0 && partNos.get(0) != null
								&& voter.getVoterIDCardNo() != null) {
							String sourcePath = IConstants.STATIC_CONTENT_FOLDER_URL
									+ "voter_images"
									+ pathSeperator
									+ cadreRegistrationVO.getConstituencyId()
											.trim()
									+ pathSeperator
									+ "Part"
									+ partNos.get(0).trim()
									+ pathSeperator
									+ voter.getVoterIDCardNo().trim() + ".jpg";
							LOG.error("VOTERN: SP:" + sourcePath + " DP:"
									+ destinationPath + " VOTERID: "
									+ voter.getVoterId());
							String status = copyFile(sourcePath,
									destinationPath);
							LOG.error("Status : " + status);
							if (status.equalsIgnoreCase("success")) {
								tdpCadre.setImage(tdpCadre.getMemberShipNo()
										+ ".jpg");
								LOG.error("Success:"
										+ tdpCadre.getMemberShipNo() + ".jpg");
							}
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

	private String sendSMSInTelugu(String mobileNo, String msg) {

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
			String mtype = "OL";
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

	public String getCadreImage(String enrolmentId) {
		List<String> cadreImages = tdpCadreDAO
				.getCadreImageByPreviousEnrolId(enrolmentId);
		String reqImage = null;
		for (String img : cadreImages) {
			if (reqImage != null) {
				break;
			} else if (img != null && img.trim().length() > 0) {
				reqImage = img.trim();
			}
		}
		return reqImage;
	}

	public String uploadCadreImage(String voterCardNo, String url,
			String uploadImageContentType, File uploadImage) {
		try {
			String pathSeperator = System
					.getProperty(IConstants.FILE_SEPARATOR);

			String filePath = url + "images" + pathSeperator
					+ IConstants.CADRE_IMAGES + pathSeperator;

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
}
