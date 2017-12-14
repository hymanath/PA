package com.itgrids.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.itgrids.dao.IDocumentDAO;
import com.itgrids.dao.ILocationAddressDAO;
import com.itgrids.dao.IPetitionDAO;
import com.itgrids.dao.IPetitionStatusDAO;
import com.itgrids.dao.IPmPetitionDocumentDAO;
import com.itgrids.dao.IPmRefCandidateDAO;
import com.itgrids.dao.IPmRefCandidateDesignationDAO;
import com.itgrids.dao.IPmRepresenteeDAO;
import com.itgrids.dao.IPmRepresenteeDesignationDAO;
import com.itgrids.dao.IPmRepresenteeRefDetailsDAO;
import com.itgrids.dao.IPmRepresenteeRefDocumentDAO;
import com.itgrids.dao.IPmSubWorkDetailsDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.PetitionMemberVO;
import com.itgrids.dto.PetitionsWorksVO;
import com.itgrids.dto.PmRequestVO;
import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.RepresenteeViewVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.model.Document;
import com.itgrids.model.LocationAddress;
import com.itgrids.model.Petition;
import com.itgrids.model.PetitionStatus;
import com.itgrids.model.PmPetitionDocument;
import com.itgrids.model.PmRefCandidate;
import com.itgrids.model.PmRefCandidateDesignation;
import com.itgrids.model.PmRepresentee;
import com.itgrids.model.PmRepresenteeDesignation;
import com.itgrids.model.PmRepresenteeRefDetails;
import com.itgrids.model.PmRepresenteeRefDocument;
import com.itgrids.model.PmSubWorkDetails;
import com.itgrids.service.IPmRequestDetailsService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;

@Service
@Transactional
public class PmRequestDetailsService implements IPmRequestDetailsService{

	private static final Logger LOG = Logger.getLogger(PmRequestDetailsService.class);
	
	@Autowired
	private DateUtilService dateUtilService;
	
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	
	@Autowired
	private IDocumentDAO documentDAO;
	
	@Autowired
	private ILocationAddressDAO locationAddressDAO;
	
	@Autowired
	private IPmRepresenteeDAO pmRepresenteeDAO;
	
	@Autowired
	private IPmRepresenteeDesignationDAO pmRepresenteeDesignationDAO;
	
	@Autowired
	private IPmRefCandidateDAO pmRefCandidateDAO;
	
	@Autowired
	private IPetitionDAO pititionDAO;
	
	@Autowired
	private IPmSubWorkDetailsDAO pmSubWorkDetailsDAO;
	
	@Autowired
	private IPmPetitionDocumentDAO pmPetitionDocumentDAO;
	
	@Autowired
	private IPmRepresenteeRefDetailsDAO pmRepresenteeRefDetailsDAO;
	
	@Autowired
	private IPmRefCandidateDesignationDAO pmRefCandidateDesignationDAO;
	
	@Autowired
	private IPmRepresenteeRefDocumentDAO pmRepresenteeRefDocumentDAO;
	
	@Autowired
	private IPetitionStatusDAO petitionStatusDAO;
	public ResponseVO saveRepresentRequestDetails(PmRequestVO pmRequestVO){
		ResponseVO responseVO = new ResponseVO();
		try {
			
			/** Start Petition Representee Details saving */
				PmRepresentee pmRepresentee = saveRepresenteeDetails(pmRequestVO);
			/** End Petition Member Details saving */
			
			/** Start Petition Referrer Details */
				if(pmRepresentee != null){
					Petition petition = savePetitionWorkDetails(pmRepresentee.getPmRepresenteeId(),pmRequestVO);
					savePetitionReferralDetails(pmRepresentee.getPmRepresenteeId(),petition.getPetitionId(),pmRequestVO);
				}
			responseVO.setResponseCode("0");
			responseVO.setMessage(IConstants.SUCCESS);
		} catch (Exception e) {
			responseVO.setResponseCode("1");
			responseVO.setMessage(IConstants.FAILURE);
			LOG.error("Exception Occured in PmRequestDetailsService "+e.getMessage());
		}
		return responseVO;
	}
	
	public Long  savePetitionReferralDetails(Long pmRepresenteeId,Long petitionId,PmRequestVO pmRequestVO){
		Long noOfRefCount=0L;
		try {
			if(pmRepresenteeId != null && pmRepresenteeId.longValue()>0L){
				
				if(pmRequestVO.getRepresentationType() != null &&	 pmRequestVO.getRepresentationType().equalsIgnoreCase("SELF")){
					List<PmRefCandidateDesignation> pmRefCandidateList = pmRefCandidateDesignationDAO.getPmRepresenteeDesignationByPmRefCandidateId(pmRequestVO.getRefCandidateId());
					if(commonMethodsUtilService.isListOrSetValid(pmRefCandidateList)){
						for (PmRefCandidateDesignation pmRefDesignation : pmRefCandidateList) {
							PmRepresenteeRefDetails petitionRefferer = new PmRepresenteeRefDetails(); 
							petitionRefferer.setPetitionId(petitionId);
							petitionRefferer.setPmRefCandidateId(pmRequestVO.getRefCandidateId());
							petitionRefferer.setPmRefCandidateDesignationId(pmRefDesignation.getPmDesginationId());// ref and representee designation same. himself referrer
							petitionRefferer.setPmRepresenteeId(pmRepresenteeId);
							petitionRefferer.setPmRepresenteeDesignationId(pmRefDesignation.getPmDesginationId());
							petitionRefferer.setIsDeleted("N");
							petitionRefferer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							petitionRefferer.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							petitionRefferer = pmRepresenteeRefDetailsDAO.save(petitionRefferer);
							
							if(petitionRefferer.getPmRepresenteeRefDetailsId() != null && petitionRefferer.getPmRepresenteeRefDetailsId().longValue()>0L && 
									 commonMethodsUtilService.isListOrSetValid(pmRequestVO.getReferList()) && commonMethodsUtilService.isListOrSetValid(pmRequestVO.getReferList().get(0).getFileList())){
								for (MultipartFile file : pmRequestVO.getReferList().get(0).getFileList()) {
										Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_PETITIONS_FOLDER_URL,pmRequestVO.getUserId());
										savePetitionReffererDocument(petitionRefferer.getPmRepresenteeRefDetailsId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId());
								}
							}
						}
					}
				}else if (commonMethodsUtilService.isListOrSetValid(pmRequestVO.getReferList()) && pmRequestVO.getRepresentationType() != null &&	 pmRequestVO.getRepresentationType().equalsIgnoreCase("REPRESENTEE") ){
					for (PmRequestVO refVO : pmRequestVO.getReferList()) {
						List<PmRepresenteeDesignation> pmRepresenteeList = pmRepresenteeDesignationDAO.getPmRepresenteeDesignationByRepresenteeId(pmRepresenteeId);
						if(commonMethodsUtilService.isListOrSetValid(pmRepresenteeList)){
							for (PmRepresenteeDesignation pmRepresenteeDesignation : pmRepresenteeList) {
								if(refVO.getRefCandidateId() != null){
									List<PmRefCandidateDesignation> pmRefCandidateList = pmRefCandidateDesignationDAO.getPmRepresenteeDesignationByPmRefCandidateId(refVO.getRefCandidateId());
									if(commonMethodsUtilService.isListOrSetValid(pmRefCandidateList)){
										for (PmRefCandidateDesignation pmRefDesignation : pmRefCandidateList) {
											PmRepresenteeRefDetails petitionRefferer = new PmRepresenteeRefDetails(); 
											petitionRefferer.setPetitionId(petitionId);
											petitionRefferer.setPmRefCandidateId(refVO.getRefCandidateId());
											petitionRefferer.setPmRefCandidateDesignationId(pmRefDesignation.getPmDesginationId());
											petitionRefferer.setPmRepresenteeId(pmRepresenteeId);
											petitionRefferer.setPmRepresenteeDesignationId(pmRepresenteeDesignation.getPmDesignationId());
											petitionRefferer.setIsDeleted("N");
											petitionRefferer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
											petitionRefferer.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
											petitionRefferer = pmRepresenteeRefDetailsDAO.save(petitionRefferer);
											
											if(petitionRefferer.getPmRepresenteeRefDetailsId() != null && petitionRefferer.getPmRepresenteeRefDetailsId().longValue()>0L && 
													 commonMethodsUtilService.isListOrSetValid(refVO.getFileList())){
												for (MultipartFile file : refVO.getFileList()) {
														Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_PETITIONS_FOLDER_URL,pmRequestVO.getUserId());
														savePetitionReffererDocument(petitionRefferer.getPmRepresenteeRefDetailsId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId());
												}
											}
											
										}
									}else{// no refferer
										PmRepresenteeRefDetails petitionRefferer = new PmRepresenteeRefDetails(); 
										petitionRefferer.setPetitionId(petitionId);
										petitionRefferer.setPmRepresenteeId(pmRepresenteeId);
										petitionRefferer.setPmRepresenteeDesignationId(pmRepresenteeDesignation.getPmDesignationId());
										petitionRefferer.setIsDeleted("N");
										petitionRefferer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
										petitionRefferer.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
										petitionRefferer = pmRepresenteeRefDetailsDAO.save(petitionRefferer);
										
										if(petitionRefferer.getPmRepresenteeRefDetailsId() != null && petitionRefferer.getPmRepresenteeRefDetailsId().longValue()>0L && 
												 commonMethodsUtilService.isListOrSetValid(refVO.getFileList())){
											for (MultipartFile file : refVO.getFileList()) {
													Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_PETITIONS_FOLDER_URL,pmRequestVO.getUserId());
													savePetitionReffererDocument(petitionRefferer.getPmRepresenteeRefDetailsId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId());
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionReferralDetails() "+e.getMessage());
		}
		return noOfRefCount;
	}
	
	public PmRepresenteeRefDocument savePetitionReffererDocument(Long pmRepresenteeRefDetailsId,Long documentId,Long userId){
		PmRepresenteeRefDocument petitionReffererDocument = null;
		try {
			if(documentId != null && documentId.longValue()>0L){
				petitionReffererDocument = new PmRepresenteeRefDocument();
				petitionReffererDocument.setDocumentId(documentId);
				petitionReffererDocument.setPmRepresenteeRefDetailsId(pmRepresenteeRefDetailsId);
				petitionReffererDocument.setIsDeleted("N");
				petitionReffererDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				petitionReffererDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				petitionReffererDocument = pmRepresenteeRefDocumentDAO.save(petitionReffererDocument);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionReffererDocument() "+e.getMessage());
			 petitionReffererDocument = null;
		}
		return petitionReffererDocument;
	}
	
	public Long savePetitionSubWorkDetails(Long petitonId,PetitionsWorksVO mainDataVO,List<PetitionsWorksVO> subWorksList,int uiBuildSeriesNo){
		Long noOfWorksCount = 0L;
		try {
			if(commonMethodsUtilService.isListOrSetValid(subWorksList)){
				for (PetitionsWorksVO dataVO : subWorksList) {
					if(dataVO.getWorkTypeId() != null && dataVO.getWorkTypeId().longValue()>0L){
						PmSubWorkDetails petitionSubWorkLocationDetails = new PmSubWorkDetails();
						petitionSubWorkLocationDetails.setPetitionId(petitonId);						
						petitionSubWorkLocationDetails.setGrievanceDescrption(dataVO.getGrievanceDescription());
						petitionSubWorkLocationDetails.setCostEstimation(Double.valueOf(dataVO.getEstimateCost()));
						petitionSubWorkLocationDetails.setPmDepartmentId(mainDataVO.getDeptId());
						petitionSubWorkLocationDetails.setPmSubjectId(mainDataVO.getSubjectId());
						petitionSubWorkLocationDetails.setPmSubSubjectId(mainDataVO.getSubSubjectId());
						petitionSubWorkLocationDetails.setLocationScopeId(dataVO.getLocationScopeId());
						petitionSubWorkLocationDetails.seteOfficeId(dataVO.geteOfficeId());
						petitionSubWorkLocationDetails.setUiBuildSeriesNo(String.valueOf(uiBuildSeriesNo));
						
						if(dataVO.getLocationScopeId() != null && dataVO.getLocationScopeId().longValue()>0L){
							if(dataVO.getLocationScopeId().longValue()==3L)
								petitionSubWorkLocationDetails.setLocationValue(dataVO.getAddressVO().getDistrictId());
							else if(dataVO.getLocationScopeId().longValue()==4L)
								petitionSubWorkLocationDetails.setLocationValue(dataVO.getAddressVO().getAssemblyId());
							else if(dataVO.getLocationScopeId().longValue()==5L){
								petitionSubWorkLocationDetails.setLocationValue(Long.valueOf(dataVO.getAddressVO().getTehsilId().toString().substring(1)));
							}
						}
						
						
						LocationAddress address = saveLocationAddress(dataVO.getAddressVO());
						if(address != null && address.getLocationAddressId() != null && address.getLocationAddressId().longValue()>0L)
							petitionSubWorkLocationDetails.setAddressId(address.getLocationAddressId());
						
						petitionSubWorkLocationDetails.setPmWorkTypeId(dataVO.getWorkTypeId());
						petitionSubWorkLocationDetails.setPmLeadId(dataVO.getLeadId());
						petitionSubWorkLocationDetails.setPmBriefLeadId(dataVO.getBriefLeadId());
						petitionSubWorkLocationDetails.setPmGrantId(dataVO.getGrantId());
						petitionSubWorkLocationDetails.setPmStatusId(1L);// endorsement pending
						petitionSubWorkLocationDetails.setCoveringLetterPath(null);
						petitionSubWorkLocationDetails.setIsDeleted("N");
						petitionSubWorkLocationDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						petitionSubWorkLocationDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						
						petitionSubWorkLocationDetails = pmSubWorkDetailsDAO.save(petitionSubWorkLocationDetails);
						noOfWorksCount=noOfWorksCount+1;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionSubWorkDetails() "+e.getMessage());
		}
		return noOfWorksCount;
	}
	
	public Petition savePetitionWorkDetails(Long pmRepresenteeId,PmRequestVO pmRequestVO){
		Petition petition = null;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			if(pmRequestVO != null){
				if(commonMethodsUtilService.isListOrSetValid(pmRequestVO.getWorksList())){
					int i=0;
					Long submittedWorksCount=0L;
					Long noOfWorks=0L;
					for (PetitionsWorksVO dataVO : pmRequestVO.getWorksList()) {
						//if(dataVO.getDeptId() != null && dataVO.getDeptId().longValue()>0L && dataVO.getSubjectId() != null && dataVO.getSubjectId().longValue()>0L){
							if(i==0){
								petition = new Petition();
								petition.setEndorsmentDate(null);
								petition.setEndorsmentNo(null);
								petition.setRepresentationDate(format.parse(pmRequestVO.getRepresentationdate()));
								petition.setRepresenteeType(pmRequestVO.getRepresentationType());
								if(dataVO.getEstimateCost() != null)
									petition.setEstimationCost(Double.valueOf(dataVO.getEstimateCost()));
								petition.setWorkName(dataVO.getWorkName());
								petition.setNoOfWorks(dataVO.getNoOfWorks());
								petition.setIsDeleted("N");
								petition.setIsPreviousPetition(dataVO.getIsPreviousPetition());
								petition.setGrievanceDescription(dataVO.getGrievanceDescription());
								petition.setPmStatusId(1L);//pending endorsment
								petition.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								petition.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								petition = pititionDAO.save(petition);
								noOfWorks = dataVO.getNoOfWorks();
							}
							i=i+1;
							if(petition != null && petition.getPetitionId() != null && petition.getPetitionId().longValue()>0L && commonMethodsUtilService.isListOrSetValid(dataVO.getSubWorksList())){
								Long tempsubmittedWorksCount = savePetitionSubWorkDetails(petition.getPetitionId(),dataVO.getSubWorksList().get(0),dataVO.getSubWorksList(),i);
								submittedWorksCount = submittedWorksCount+tempsubmittedWorksCount;
								
								if(dataVO.getFileList() != null && dataVO.getFileList().size()>0){
									for (MultipartFile file : dataVO.getFileList()) {
										Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_PETITIONS_FOLDER_URL,pmRequestVO.getUserId());
										savePetitionWorkDocument(petition.getPetitionId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId());
									}
								}
							}
						//}
					}
					/*if(submittedWorksCount.longValue() != noOfWorks){
						throw new Exception(" The submitted No of works not matched with no of works . Please check once.");
					}*/
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionWorkDetails() "+e.getMessage());
			petition = null;
		}
		return petition;
	}
	
	public PmPetitionDocument savePetitionWorkDocument(Long petitionId,Long documentId,Long userId){
		PmPetitionDocument petitionWorkDocument = null;
		try {
			if(documentId != null && documentId.longValue()>0L){
				petitionWorkDocument = new PmPetitionDocument();
				petitionWorkDocument.setPetitionId(petitionId);
				petitionWorkDocument.setDocumentId(documentId);
				petitionWorkDocument.setIsDeleted("N");
				petitionWorkDocument = pmPetitionDocumentDAO.save(petitionWorkDocument);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionReffererDocument() "+e.getMessage());
			petitionWorkDocument = null;
		}
		return petitionWorkDocument;
	}
	
	public Document saveDocument(MultipartFile file, String destinationPath,Long userId){
		Document document = null;
		try {
			if(file != null){
				//String tempPath = commonMethodsUtilService.createInnerFolders(destinationPath);
				String staticPath = commonMethodsUtilService.createInnerFolders(destinationPath+"Petition_Documents");
				String datePath = commonMethodsUtilService.generateImagePathWithDateTime();
				staticPath = staticPath.substring(1);// remove first charactor
				staticPath = staticPath.replace("//", "/");
				document = new Document();
				commonMethodsUtilService.copyFile(file.getOriginalFilename(),staticPath);
				byte[] fileData = file.getBytes();
				String fileExtensionStr = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
				Files.write(fileData,new File(staticPath+"/"+datePath+fileExtensionStr));
				document.setPath(staticPath+"/"+datePath+fileExtensionStr);
				document.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				document.setInsertedUserId(userId);
				document = documentDAO.save(document);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ saveDocument() "+e.getMessage());
			document = null;
		}
		return document;
	}
	
	public PmRepresentee checkIsExistPmRepresentee(String voterCardNo,String adharCardNo,Long refCandidateId){
		PmRepresentee pmRepresentee = null;
		try {
			List<Long> representeeIdsList =null;
			if(refCandidateId == null || refCandidateId.longValue()==0L)
				representeeIdsList = pmRepresenteeDAO.getExistingPetitionRepresenteeDetailsById(voterCardNo,adharCardNo);
			else
				representeeIdsList = pmRepresenteeDAO.getExistingPetitionRepresenteeDetailsByRefId(refCandidateId);
			
			if(commonMethodsUtilService.isListOrSetValid(representeeIdsList)){
				pmRepresentee = pmRepresenteeDAO.get(representeeIdsList.get(0));
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in checkIsExistPmRepresentee "+e.getMessage());
			pmRepresentee = null;
		}
		return pmRepresentee;
	}
	
	public String setDataToAttribute(String existing,String newValue){
		try {
			if(commonMethodsUtilService.checkStringForNull(existing) != null && commonMethodsUtilService.checkStringForNull(newValue) != null && 
					!existing.equalsIgnoreCase(newValue) )
				return newValue;
		} catch (Exception e) {
			LOG.error("Exception Occured in setDataToAttribute "+e.getMessage());
		}
		return null;
	}
	public PmRepresentee saveRepresenteeDetails(PmRequestVO pmRequestVO){
		PmRepresentee pmRepresentee = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				if(pmRequestVO.getRepresentationType() != null && pmRequestVO.getRepresentationType().equalsIgnoreCase("REPRESENT")){
					if(pmRequestVO != null){
						
						pmRepresentee = checkIsExistPmRepresentee(pmRequestVO.getVoterCardNo(),pmRequestVO.getAdharCardNo(),null);
						if(pmRepresentee==null){
							pmRepresentee = new PmRepresentee();
							pmRepresentee.setName(pmRequestVO.getName());
							pmRepresentee.setMobileNo(pmRequestVO.getMobileNO());
							pmRepresentee.setEmail(pmRequestVO.getEmail());
							pmRepresentee.setVoterCardNo(pmRequestVO.getVoterCardNo());
							pmRepresentee.setAdharCardNo(pmRequestVO.getAdharCardNo());
							pmRepresentee.setTdpCadreId(pmRequestVO.getTdpCadreId());
							pmRepresentee.setIsDeleted("N");
							
							LocationAddress address = saveLocationAddress(pmRequestVO.getAddressVO());
							pmRepresentee.setAddressId(address.getLocationAddressId());
							pmRepresentee.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							pmRepresentee.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							pmRepresentee = pmRepresenteeDAO.save(pmRepresentee);
						}else{
							pmRepresentee.setName(setDataToAttribute(pmRequestVO.getName(),pmRepresentee.getName()));
							pmRepresentee.setMobileNo(setDataToAttribute(pmRequestVO.getMobileNO(),pmRepresentee.getMobileNo()));
							pmRepresentee.setEmail(setDataToAttribute(pmRequestVO.getEmail(),pmRepresentee.getEmail()));
							pmRepresentee.setVoterCardNo(setDataToAttribute(pmRequestVO.getVoterCardNo(),pmRepresentee.getVoterCardNo()));
							pmRepresentee.setAdharCardNo(setDataToAttribute(pmRequestVO.getAdharCardNo(),pmRepresentee.getAdharCardNo()));
							if(pmRequestVO.getTdpCadreId() != null)
								pmRepresentee.setTdpCadreId(Long.valueOf(setDataToAttribute(pmRequestVO.getTdpCadreId().toString(),pmRepresentee.getTdpCadreId().toString())));
							else
								pmRepresentee.setTdpCadreId(pmRequestVO.getTdpCadreId());
							
							pmRepresentee.setIsDeleted("N");
							pmRepresentee.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							pmRepresentee.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							pmRepresentee = pmRepresenteeDAO.save(pmRepresentee);
						}
						
						if(pmRequestVO.getRepresenteeDesignationId() != null && pmRequestVO.getRepresenteeDesignationId().longValue()>0L && 
								pmRepresentee != null && pmRepresentee.getPmRepresenteeId() != null && pmRepresentee.getPmRepresenteeId().longValue()>0L){
							PmRepresenteeDesignation pmRepresenteeDesignation = new PmRepresenteeDesignation();
							pmRepresenteeDesignation.setPmRepresenteeId(pmRepresentee.getPmRepresenteeId());
							pmRepresenteeDesignation.setPmRepresenteeDesignationId(pmRequestVO.getRepresenteeDesignationId());
							pmRepresenteeDesignation.setIsActive("Y");
							if(pmRequestVO.getStartDate() != null && pmRequestVO.getEndDate() != null){
								pmRepresenteeDesignation.setStartDate(format.parse(pmRequestVO.getStartDate()));
								pmRepresenteeDesignation.setEndDate(format.parse(pmRequestVO.getEndDate()));
							}
							pmRepresenteeDesignation.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							pmRepresenteeDesignation.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							pmRepresenteeDesignationDAO.save(pmRepresenteeDesignation);
						}
					}
				}else if(pmRequestVO.getRepresentationType() != null &&	 pmRequestVO.getRepresentationType().equalsIgnoreCase("SELF")){
					if(pmRequestVO.getRefCandidateId() != null){
						pmRepresentee = checkIsExistPmRepresentee(null,null,pmRequestVO.getRefCandidateId());
						if(pmRepresentee ==null){
							PmRefCandidate pmRefCandidate = pmRefCandidateDAO.get(pmRequestVO.getRefCandidateId());
							if(pmRefCandidate != null){
								pmRepresentee = new PmRepresentee();
								pmRepresentee.setName(pmRefCandidate.getName());
								pmRepresentee.setMobileNo(pmRefCandidate.getMobileNo());
								pmRepresentee.setEmail(pmRefCandidate.getEmail());
								pmRepresentee.setTdpCadreId(pmRefCandidate.getTdpCadreId());
								pmRepresentee.setIsDeleted("N");
								pmRepresentee.setPmRefCandidateId(pmRequestVO.getRefCandidateId());
								pmRepresentee.setAddressId(pmRefCandidate.getAddressId());
								pmRepresentee.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								pmRepresentee.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								pmRepresentee = pmRepresenteeDAO.save(pmRepresentee);
							}
						}
					}
				}	
			} catch (Exception e) {
				LOG.error("Exception Occured in PmRequestDetailsService @ saveRepresenteeDetails() "+e.getMessage());
				pmRepresentee = null;
			}
		return pmRepresentee;
	}
	
	public LocationAddress saveLocationAddress(AddressVO addressVO){
		LocationAddress locationAddress = null;
		try {
			if(addressVO != null){
				locationAddress = new LocationAddress();
				locationAddress.setStateId(addressVO.getStateId());
				locationAddress.setDistrictId(addressVO.getDistrictId());
				locationAddress.setParliamentId(addressVO.getParliamentId());
				locationAddress.setConstituencyId(addressVO.getAssemblyId());
				
				if(addressVO.getTehsilId() != null && addressVO.getTehsilId().longValue()>0L){
					String idStr = addressVO.getTehsilId().toString();
					String firstChar = idStr.substring(0, 1);
					if(firstChar != null && firstChar.equalsIgnoreCase("2"))
						locationAddress.setTehsilId(Long.valueOf(addressVO.getTehsilId().toString().substring(1)));
					else if(firstChar != null && firstChar.equalsIgnoreCase("1"))
						locationAddress.setLocalElectionBodyId(Long.valueOf(addressVO.getTehsilId().toString().substring(1)));
				}
				if(addressVO.getPanchayatId() != null && addressVO.getPanchayatId().longValue()>0L){
					String idStr = addressVO.getPanchayatId().toString();
					String firstChar = idStr.substring(0, 1);
					if(firstChar != null && firstChar.equalsIgnoreCase("2"))
						locationAddress.setPanchayatId(Long.valueOf(addressVO.getPanchayatId().toString().substring(1)));
					//else if(firstChar != null && firstChar.equalsIgnoreCase("1"))
					//	locationAddress.setWardId(Long.valueOf(addressVO.getPanchayatId().toString().substring(1)));
				}
				
				locationAddress = locationAddressDAO.save(locationAddress);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ saveLocationAddress() "+e.getMessage());
			locationAddress = null;
		}
		return locationAddress;
	}
	
	/**
	 * Date : 30/11/2017
	 * Author :babu kurakula <href:kondababu.kurakula@itgrids.com>
	 * @description : to get candidate detailse based on  desi Id and locationValue
	 * @param : designationId,locationLevelId,locationValue
	 * @return  List<LocationFundDetailsVO> 
	 */
	public List<RepresentationRequestVO> getPetitionReferredMemberDetails(RepresentationRequestVO dataVo){
	    List<RepresentationRequestVO> returnList = new ArrayList<RepresentationRequestVO>();
	    try {
	    	//  0 petitonrefCndidateId ,1 name,2 designation ,3 stateId,4 stateName
	    	// 5 districtId 6 district name 7 constincuyId,8 constincyName, 9 tehsilId,10 teshilName
	    	// 11 panchayId,12 panchaytname,13 mobilNo,14 emailId,14 desigantionId
	    	List<Object[]> referalObjs = pmRefCandidateDesignationDAO.getCandidatseDetailsByDesignationAndLocation(dataVo.getDeptId(),dataVo.getLocationLevelId(),dataVo.getLocationValue());
	    	if(referalObjs != null && referalObjs.size() > 0){
	    		for( Object[] param : referalObjs ){
	    			RepresentationRequestVO mainV0 = new RepresentationRequestVO();
	    			mainV0.setReferrerCandidateId(commonMethodsUtilService.getLongValueForObject(param[0]));
	    			PetitionMemberVO petitionMemberVO = new PetitionMemberVO();
	    			petitionMemberVO.setId(commonMethodsUtilService.getLongValueForObject(param[15]));//desigantionId
	    			petitionMemberVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
	    			petitionMemberVO.setMemberType(commonMethodsUtilService.getStringValueForObject(param[2]));//designation
	    			petitionMemberVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[13]));
	    			petitionMemberVO.setEmailId(commonMethodsUtilService.getStringValueForObject(param[14]));
	    			mainV0.setPetitionMemberVO(petitionMemberVO);
	    			AddressVO addressVO= new AddressVO();
	    			addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[3]));
	    			addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[4]));
	    			addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[5]));
	    			addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[6]));
	    			addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[7]));
	    			addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[8]));
	    			addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[9]));
	    			addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[10]));
	    			addressVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[11]));
	    			addressVO.setPanchayatName(commonMethodsUtilService.getStringValueForObject(param[12]));
	    			
	    			addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[16]));
	    			addressVO.setParliamentName(commonMethodsUtilService.getStringValueForObject(param[17]));
	    			
	    			if(dataVo.getLocationLevelId().longValue() ==10L){
	    				addressVO.setAssemblyId(addressVO.getParliamentId());
		    			addressVO.setAssemblyName(addressVO.getParliamentName());
	    			}
	    			
	    			mainV0.setCandidateAddressVO(addressVO);
	    			returnList.add(mainV0);
	    		}
	    	}
	    } catch (Exception e) {
	      LOG.error("Exception Occured in savePetitionMember "+e.getMessage());
	    }
	     return returnList;
	  }
	public List<RepresenteeViewVO> getRepresentativeSearchWiseDetails(InputVO inputVO){
		List<RepresenteeViewVO> finalList = new ArrayList<RepresenteeViewVO>();
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = null;
			Date endDate = null;
			
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null && !inputVO.getFromDate().isEmpty() && !inputVO.getToDate().isEmpty()){
				startDate = format.parse(inputVO.getFromDate());
				endDate = format.parse(inputVO.getToDate());
			}
			Map<Long,RepresenteeViewVO> mapData = new HashMap<Long,RepresenteeViewVO>();
			List<Object[]> searchData = pmRepresenteeRefDetailsDAO.getRepresentativeSearchWiseDetails(inputVO,startDate,endDate);
			List<Long> statusIds = new ArrayList<Long>();
			statusIds.add(6l);
			statusIds.add(7l);
			statusIds.add(8l);
			
			Long minPending = commonMethodsUtilService.getLongValueForObject(inputVO.getStartValue());
			Long maxPending = commonMethodsUtilService.getLongValueForObject(inputVO.getEndValue());
			
			if(searchData != null && searchData.size()>0){
				for (Object[] param : searchData) {
					//RepresenteeViewVO vo = new RepresenteeViewVO();
					RepresenteeViewVO vo = mapData.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(vo == null){
						vo = new RepresenteeViewVO();
						vo.setDesigName(commonMethodsUtilService.getStringValueForObject(param[7]));
						vo.getDesigList().add(commonMethodsUtilService.getStringValueForObject(param[7]));
						//vo.set
						if(commonMethodsUtilService.getLongValueForObject(param[0]) >0l)
							mapData.put(commonMethodsUtilService.getLongValueForObject(param[0]), vo);
					}else{
						if(vo.getDesigList() != null && vo.getDesigList().size() >0 && !vo.getDesigList().contains(commonMethodsUtilService.getStringValueForObject(param[7]))){
							String designations = vo.getDesigName().concat(", "+commonMethodsUtilService.getStringValueForObject(param[7]));
							vo.setDesigName(designations);
							vo.getDesigList().add(commonMethodsUtilService.getStringValueForObject(param[7]));
						}
					}
					vo.setPetitionId(commonMethodsUtilService.getLongValueForObject(param[0]));
					vo.setEndorsementNO(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(param[2] != null){
						vo.setEndorsmentDate(commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
					}
					vo.setEstimationCost(commonMethodsUtilService.getStringValueForObject(param[3]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(param[4]));
					vo.setReferrerName(commonMethodsUtilService.getStringValueForObject(param[5]));
					vo.setNoOfWorks(commonMethodsUtilService.getLongValueForObject(param[6]));
					Long petionPendingDays = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[8]),commonMethodsUtilService.getStringValueForObject(param[9]));
					Long statusId = commonMethodsUtilService.getLongValueForObject(param[10]);
					vo.setWorkName(commonMethodsUtilService.getStringValueForObject(param[11]));
					//RepresenteeViewVO vo =mapData.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(param[8] != null){
						vo.setRaisedDate(commonMethodsUtilService.getStringValueForObject(param[8]).substring(0, 10));
					}
					if(statusId.longValue() != 0l && !statusIds.contains(statusId) && petionPendingDays.longValue()>=minPending.longValue()
							&& petionPendingDays.longValue() <= maxPending.longValue() && vo != null){
						vo.setStatusType("pending");
					}
				}
			}
			
			
			
			/*if(searchData != null && searchData.size()>0){
				for (Object[] param : searchData) {
					Long petionPendingDays = dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[8]),commonMethodsUtilService.getStringValueForObject(param[9]));
					Long statusId = commonMethodsUtilService.getLongValueForObject(commonMethodsUtilService.getLongValueForObject(param[10]));
					RepresenteeViewVO vo =mapData.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					
					if(statusId.longValue() != 0l && !statusIds.contains(statusId) && petionPendingDays.longValue()>=minPending.longValue()
							&& petionPendingDays.longValue() <= maxPending.longValue() && vo != null){
						vo.setStatusType("pending");
					}
				}
			}*/
			
			for (Map.Entry<Long, RepresenteeViewVO> entry : mapData.entrySet()) {
				if(minPending.longValue() >0l &&  maxPending.longValue() >0l && entry.getValue().getStatusType().equalsIgnoreCase("pending")){
					finalList.add(entry.getValue());
				}else if(minPending.longValue() ==0l || maxPending.longValue() ==0l){
					finalList.add(entry.getValue());
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in PmRequestDetailsService @ getRepresentativeSearchWiseDetails() "+e.getMessage());
		}
		return finalList;
	}
	public List<RepresenteeViewVO> getStatusList(){
		List<RepresenteeViewVO> returnList = new ArrayList<RepresenteeViewVO>();
		try {
			List<PetitionStatus> list = petitionStatusDAO.getAll();
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (PetitionStatus petitionStatus : list) {
					if(petitionStatus.getIsDeleted().equalsIgnoreCase("N")){
						RepresenteeViewVO vo = new RepresenteeViewVO();
						vo.setId(commonMethodsUtilService.getLongValueForObject(petitionStatus.getPetitionStatusId()));
						vo.setName(commonMethodsUtilService.getStringValueForObject(petitionStatus.getDescription()));
						returnList.add(vo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in PmRequestDetailsService @ getStatusList() "+e.getMessage());
		}
		return returnList;
	}
	
	 public PmRequestVO getPMRequestDetailsByPetitionId(Long petitionId){
		 PmRequestVO pmRequestVO = null;
		 try {
			if(petitionId != null && petitionId.longValue()>0L){
				pmRequestVO = setPmRepresenteeDataToResultView(petitionId);
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in getPmRequestSearchWiseDetails "+e.getMessage());
		}
		 return pmRequestVO;
	 }
	 
	 public PmRequestVO setPmRepresenteeDataToResultView(Long petitionId){
		 PmRequestVO returnVO = null;
		 try {
			List<Object[]> pmRepresenteePetitionsDetils = pmRepresenteeRefDetailsDAO.getPmRepresenteRefDetails(petitionId);
			Map<Long,List<PmRequestVO>> representeeMap = new HashMap<Long,List<PmRequestVO>>(0);
			if(commonMethodsUtilService.isListOrSetValid(pmRepresenteePetitionsDetils)){
				for (Object[] param : pmRepresenteePetitionsDetils) {
					PmRequestVO  pmRequestVO = new PmRequestVO();
					 pmRequestVO.setRepresenteeId(commonMethodsUtilService.getLongValueForObject(param[30]));
					 pmRequestVO.setRefCandidateId(commonMethodsUtilService.getLongValueForObject(param[31]));
					 pmRequestVO.setName(commonMethodsUtilService.getStringValueForObject(param[0]));
					 pmRequestVO.setMobileNO(commonMethodsUtilService.getStringValueForObject(param[1]));
					 pmRequestVO.setEmail(commonMethodsUtilService.getStringValueForObject(param[2]));
					 pmRequestVO.setVoterCardNo(commonMethodsUtilService.getStringValueForObject(param[3]));
					 pmRequestVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[33]));
					 pmRequestVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[34]));
					 
					 AddressVO addressVO = setAddressDetailsToResultView(param[4],param[5],param[6],param[7],param[8]);
					 addressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[37]));
					 addressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[38]));
					 addressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[39]));
					 if(commonMethodsUtilService.getLongValueForObject(param[8]) >0L)// muncipality
						 addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[41])+" "+commonMethodsUtilService.getStringValueForObject(param[42]));
					 else
						 addressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[40]));
					 pmRequestVO.setAddressVO(addressVO);
					 
					 PmRequestVO refVO = new PmRequestVO();
					 refVO.setId(commonMethodsUtilService.getLongValueForObject(param[32]));// for documents this refer id required
					 refVO.setRefCandidateId(commonMethodsUtilService.getLongValueForObject(param[9]));
					 refVO.setName(commonMethodsUtilService.getStringValueForObject(param[10]));
					 refVO.setMobileNO(commonMethodsUtilService.getStringValueForObject(param[11]));
					 refVO.setEmail(commonMethodsUtilService.getStringValueForObject(param[12]));
					 refVO.setPartyId(commonMethodsUtilService.getLongValueForObject(param[13]));
					 refVO.setPartyName(commonMethodsUtilService.getStringValueForObject(param[14]));
					 refVO.setCandidatePath(commonMethodsUtilService.getStringValueForObject(param[15]));
					 refVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[35]));
					 refVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[36]));
					 
					 AddressVO refAddressVO = setAddressDetailsToResultView(param[16],param[17],param[18],param[19],param[20]);
					 refAddressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[43]));
					 refAddressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[44]));
					 refAddressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[45]));
					 if(commonMethodsUtilService.getLongValueForObject(param[8]) >0L)// muncipality
						 refAddressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[47])+" "+commonMethodsUtilService.getStringValueForObject(param[48]));
					 else
						 refAddressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[46]));
					 refVO.setAddressVO(refAddressVO);
					 pmRequestVO.getReferList().add(refVO);
					 
					 PetitionsWorksVO petitionVO = new PetitionsWorksVO();
					 petitionVO.setPetitionId(petitionId);
					 petitionVO.setNoOfWorks(commonMethodsUtilService.getLongValueForObject(param[35]));
					 petitionVO.setEstimateCost(commonMethodsUtilService.getStringValueForObject(param[35]));
					 petitionVO.setEndorsmentNo(commonMethodsUtilService.getStringValueForObject(param[22]));
					 pmRequestVO.setRepresentationdate(commonMethodsUtilService.getStringValueForObject(param[23]));
					 petitionVO.setEndorsmentDate(commonMethodsUtilService.getStringValueForObject(param[24]));
					 petitionVO.setWorkName(commonMethodsUtilService.getStringValueForObject(param[25]));
					 petitionVO.setGrievanceDescription(commonMethodsUtilService.getStringValueForObject(param[25]));
					 pmRequestVO.getWorksList().add(petitionVO);
					 
					 List<PmRequestVO> list =new ArrayList<PmRequestVO>(0);
					 if(representeeMap.get(pmRequestVO.getRepresenteeId()) != null){
						 list = representeeMap.get(pmRequestVO.getRepresenteeId());
					 }
					 list.add(pmRequestVO);
					 representeeMap.put(pmRequestVO.getRepresenteeId(), list);
				}
				
				List<Object[]> subWorksList = pmSubWorkDetailsDAO.getPetitionSubWorksDetails(petitionId);
				Map<Long,List<PetitionsWorksVO>> petitionSubWorksMap = new HashMap<Long,List<PetitionsWorksVO>>(0);
				if(commonMethodsUtilService.isListOrSetValid(subWorksList)){
					for (Object[] param : subWorksList) {
						
						PetitionsWorksVO vo = new PetitionsWorksVO();
						vo.setPetitionId(commonMethodsUtilService.getLongValueForObject(param[0]));
						vo.setWorkId(commonMethodsUtilService.getLongValueForObject(param[17]));
						vo.setWorkName(commonMethodsUtilService.getStringValueForObject(param[2]));
						vo.setWorkTypeId(commonMethodsUtilService.getLongValueForObject(param[11]));
						vo.setGrievanceDescription(commonMethodsUtilService.getStringValueForObject(param[2]));
						vo.setSubjectId(commonMethodsUtilService.getLongValueForObject(param[4]));
						vo.setSubSubjectId(commonMethodsUtilService.getLongValueForObject(param[5]));
						vo.setLeadId(commonMethodsUtilService.getLongValueForObject(param[6]));
						vo.setBriefLeadId(commonMethodsUtilService.getLongValueForObject(param[7]));
						vo.setDeptId(commonMethodsUtilService.getLongValueForObject(param[10]));
						vo.seteOfficeId(commonMethodsUtilService.getStringValueForObject(param[3]));
						vo.setEstimateCost(commonMethodsUtilService.getStringValueForObject(param[1]));
						vo.setLocationScopeId(commonMethodsUtilService.getLongValueForObject(param[19]));
						vo.setLocationValue(commonMethodsUtilService.getLongValueForObject(param[20]));
						vo.setGrantId(commonMethodsUtilService.getLongValueForObject(param[8]));
						//vo.setRemarks(commonMethodsUtilService.getStringValueForObject(param[36]));
						
						vo.setWorkType(commonMethodsUtilService.getStringValueForObject(param[32]));
						vo.setDeptName(commonMethodsUtilService.getStringValueForObject(param[31]));
						vo.setSubject(commonMethodsUtilService.getStringValueForObject(param[33]));
						vo.setSubSubject(commonMethodsUtilService.getStringValueForObject(param[34]));
						vo.setLeadName(commonMethodsUtilService.getStringValueForObject(param[27]));
						vo.setBriefLeadName(commonMethodsUtilService.getStringValueForObject(param[28]));
						vo.setGrantName(commonMethodsUtilService.getStringValueForObject(param[29]));
						vo.setStatus(commonMethodsUtilService.getStringValueForObject(param[30]));
						
						 AddressVO refAddressVO = setAddressDetailsToResultView(param[12],param[13],param[14],param[15],param[16]);
						 refAddressVO.setStateName(commonMethodsUtilService.getStringValueForObject(param[21]));
						 refAddressVO.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[22]));
						 refAddressVO.setAssemblyName(commonMethodsUtilService.getStringValueForObject(param[23]));
						 if(commonMethodsUtilService.getLongValueForObject(param[8]) >0L)// muncipality
							 refAddressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[25])+" "+commonMethodsUtilService.getStringValueForObject(param[26]));
						 else
							 refAddressVO.setTehsilName(commonMethodsUtilService.getStringValueForObject(param[24]));
						 vo.setAddressVO(refAddressVO);
						 
						 List<PetitionsWorksVO> worksList = new ArrayList<PetitionsWorksVO>();
						 if(petitionSubWorksMap.get(vo.getPetitionId()) != null){
							 worksList=petitionSubWorksMap.get(vo.getPetitionId());
						 }
						 worksList.add(vo);
						 petitionSubWorksMap.put(vo.getPetitionId(), worksList);
					}
				}
				
				if(commonMethodsUtilService.isMapValid(representeeMap)){
					for (Long  petitinId : representeeMap.keySet()) {
						 List<PmRequestVO> list = representeeMap.get(petitinId);
						 if(commonMethodsUtilService.isListOrSetValid(list)){
							 for (PmRequestVO petitonVO : list) {
								List<PetitionsWorksVO> subWorkList =  petitionSubWorksMap.get(petitionId);
								if(commonMethodsUtilService.isListOrSetValid(petitonVO.getWorksList())){
									petitonVO.getWorksList().get(0).getSubWorksList().addAll(subWorkList);
								}
								returnVO = petitonVO;
							}
						 }
					}
					
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in setPmRepresenteeDataToResultView "+e.getMessage());
		}
		 return returnVO;
	 }
	 
	 public AddressVO setAddressDetailsToResultView(Object param4,Object param5,Object param6,Object param7,Object param8){
		 AddressVO addressVO = new AddressVO();
		 try {
			 addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param4));
			 addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param5));
			 addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param6));
			 if(commonMethodsUtilService.getLongValueForObject(param8) >0L)// muncipality
				 addressVO.setTehsilId(Long.valueOf("1"+commonMethodsUtilService.getLongValueForObject(param8)));
			 else
				 addressVO.setTehsilId(Long.valueOf("2"+commonMethodsUtilService.getLongValueForObject(param7)));
			 
		} catch (Exception e) {
			LOG.error("Exception Occured in setAddressDetailsToResultView "+e.getMessage());
			addressVO = null;
		}
		 return addressVO;
	 }
}
