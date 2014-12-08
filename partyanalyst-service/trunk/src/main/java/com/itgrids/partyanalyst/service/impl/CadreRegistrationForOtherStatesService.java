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

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ISmsJobStatusDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreFamilyDetailsDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.CadreFamilyVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.SmsJobStatus;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.TdpCadreFamilyDetails;
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
	private DateUtilService dateUtilService = new DateUtilService();

	private IVoterDAO voterDAO;
	private IConstituencyDAO constituencyDAO;

	private ITdpCadreFamilyDetailsDAO tdpCadreFamilyDetailsDAO;
	private ImageAndStringConverter imageAndStringConverter = new ImageAndStringConverter();
	private ISmsJobStatusDAO smsJobStatusDAO;

	
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

	public void tdpCadreSavingLogic(final CadreRegistrationVO cadreRegistrationVO,final SurveyCadreResponceVO surveyCadreResponceVO,final String insertType, final boolean statusVar) {
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
					Long distId = 1l;
					if (userAddress.getDistrict() != null) {
						distId = userAddress.getDistrict().getDistrictId();
					}
					String membershipNo = getMemberShipNo(distId,
							tdpCadre1.getTdpCadreId());
					tdpCadre1.setMemberShipNo(membershipNo);
				}
				uploadProfileImage(cadreRegistrationVO,tdpCadre1);
				tdpCadre1 = tdpCadreDAO.save(tdpCadre1);

				
				// SAVING THE TELUGU NAME OF NON VOTER -- END

				surveyCadreResponceVO.setEnrollmentNumber(tdpCadre1.getRefNo());

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
								tdpCadreFamilyDetails.setIsDeleted("N");
								tdpCadreFamilyDetails.setInsertedDate(dateUtilService.getCurrentDateAndTime());
								tdpCadreFamilyDetails.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
								tdpCadreFamilyDetailsDAO.save(tdpCadreFamilyDetails);
							}

						}

					}
				}
				surveyCadreResponceVO.setStatus("SUCCESS");
				surveyCadreResponceVO.setResultCode(ResultCodeMapper.SUCCESS);
				if (insertType.equalsIgnoreCase("new") && cadreRegistrationVO.getMobileNumber() != null && cadreRegistrationVO.getMobileNumber().trim().length() > 0 && cadreRegistrationVO.getRefNo() != null) {
					if (!statusVar) {
						try {
								String jobCode = sendSMSInTelugu(cadreRegistrationVO.getMobileNumber().trim(),
										getUniCodeMessage(StringEscapeUtils.unescapeJava("\u0C24\u0C46\u0C32\u0C41\u0C17\u0C41 \u0C26\u0C47\u0C36\u0C02 \u0C2A\u0C3E\u0C30\u0C4D\u0C1F\u0C40 \u0C15\u0C4D\u0C30\u0C3F\u0C2F\u0C3E\u0C36\u0C40\u0C32 \u0C15\u0C3E\u0C30\u0C4D\u0C2F\u0C15\u0C30\u0C4D\u0C24\u0C17\u0C3E \u0C28\u0C2E\u0C4B\u0C26\u0C41 \u0C1A\u0C47\u0C38\u0C41\u0C15\u0C41\u0C28\u0C4D\u0C28\u0C02\u0C26\u0C41\u0C15\u0C41 \u0C27\u0C28\u0C4D\u0C2F\u0C35\u0C3E\u0C26\u0C3E\u0C32\u0C41. \u0C2E\u0C40 \u0C2F\u0C4A\u0C15\u0C4D\u0C15 \u0C30\u0C3F\u0C2B\u0C30\u0C46\u0C28\u0C4D\u0C38\u0C4D \u0C28\u0C46\u0C02\u0C2C\u0C30\u0C4D : ")
												+ cadreRegistrationVO.getRefNo()));
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

					}
				}
			}
		});

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

	private String getMemberShipNo(Long districtId, Long id) {
		String memberShipNo = "AP14";
		if (districtId != null && districtId.longValue() < 11l) {
			memberShipNo = "TS14";
		}
		String randomNo = memberShipNo + "0" + id;

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
