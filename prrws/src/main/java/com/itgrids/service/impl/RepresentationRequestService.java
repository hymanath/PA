package com.itgrids.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.itgrids.dao.IDocumentDAO;
import com.itgrids.dao.ILocationAddressDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.PetitionMemberVO;
import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.model.Document;
import com.itgrids.model.LocationAddress;
import com.itgrids.model.PetitionMember;
import com.itgrids.model.PetitionRefferer;
import com.itgrids.model.PetitionReffererCandidate;
import com.itgrids.model.PetitionReffererDocument;
import com.itgrids.model.PetitionSubWorkLocationDetails;
import com.itgrids.model.PetitionWorkDetails;
import com.itgrids.model.PetitionWorkDocument;
import com.itgrids.service.IRepresentationRequestService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;

@Service
@Transactional
public class RepresentationRequestService implements IRepresentationRequestService{
	private static final Logger LOG = Logger.getLogger(RepresentationRequestService.class);
	
	@Autowired
	private DateUtilService dateUtilService;
	
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	
	@Autowired
	private IDocumentDAO documentDAO;
	
	//@Autowired
	//private IPetitionMemberDAO petitionMemberDAO;
	//@Autowired
	//private IPetitionReffererCandidateDAO petitionReffererCandidateDAO;
	
	//@Autowired
	//private IPetitionReffererDAO petitionReffererDAO;
	
	//@Autowired
	//private IPetitionReffererDocumentDAO petitionReffererDocumentDAO;
	
	//@Autowired
	//private IPetitionWorkDetailsDAO petitionWorkDetailsDAO;
	
	//@Autowired
	//private IPetitionSubWorkLocationDetailsDAO petitionSubWorkLocationDetailsDAO;
	
	@Autowired
	private ILocationAddressDAO locationAddressDAO;
	
	//@Autowired
	//private IPetitionWorkDocumentDAO petitionWorkDocumentDAO;
	
	public ResponseVO saveRepresentRequestDetails(RepresentationRequestVO dataVO){
		ResponseVO responseVO = new ResponseVO();
		try {
			
			/** Start Petition Member Details saving */
			
				LocationAddress petitionMemberAddress = null;
				if(dataVO.getCandidateAddressVO() != null)
					petitionMemberAddress = saveLocationAddress(dataVO.getCandidateAddressVO());
				PetitionMember petitionMember = savePetitionMember(dataVO);
				
				/*if(dataVO.getPetitionMemberVO().getFilesList() != null && dataVO.getPetitionMemberVO().getFilesList().size()>0){
					for (MultipartFile file : dataVO.getPetitionMemberVO().getFilesList()) {
						Document petitionMemberDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL,dataVO.getUserId());
					}
				}*/
				
			/** End Petition Member Details saving */
			
			/** Start Petition Referrer Details */
				
				if(petitionMember != null){
				PetitionRefferer petitionRefferer = savePetitionReferralDetails(petitionMember.getPetitionMemberId(),dataVO.getPetitionMemberVO().getReferrerCandidateIdsList(),dataVO.getUserId());
				if(dataVO.getFilesList() != null && dataVO.getFilesList().size()>0){
					for (MultipartFile file : dataVO.getFilesList()) {
						Document petitionRefDocument = saveDocument(file,IConstants.STATIC_CONTENT_PETITIONS_FOLDER_URL,dataVO.getUserId());
						PetitionReffererDocument petitionReffererDocument = savePetitionReffererDocument(petitionRefferer.getPetitionReffererId(),petitionRefDocument.getDocumentId(),dataVO.getUserId());
					}
				}
				}
			
			/** Start Petition Referrer Details */
				if(petitionMember != null){
					PetitionWorkDetails petitionWorkDetails = savePetitionWorkDetails(petitionMember.getPetitionMemberId(),dataVO);
					PetitionSubWorkLocationDetails petitionSubWorkLocationDetails = savePetitionSubWorkDetails(petitionWorkDetails.getPetitionWorkDetailsId(),dataVO);
					if(dataVO.getWorkFilesList() != null && dataVO.getWorkFilesList().size()>0){
						for (MultipartFile file : dataVO.getWorkFilesList()) {
							Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_PETITIONS_FOLDER_URL,dataVO.getUserId());
							savePetitionWorkDocument(petitionWorkDetails.getPetitionWorkDetailsId(),petitionWorkDocument.getDocumentId(),dataVO.getUserId());
						}
					}
				}
			responseVO.setResponseCode("0");
			responseVO.setMessage(IConstants.SUCCESS);
		} catch (Exception e) {
			responseVO.setResponseCode("1");
			responseVO.setMessage(IConstants.FAILURE);
			LOG.error("Exception Occured in RepresentationRequestService "+e.getMessage());
		}
		return responseVO;
	}
	
	public PetitionWorkDocument savePetitionWorkDocument(Long petitionWorkDetailsId,Long documentId,Long userId){
		PetitionWorkDocument petitionWorkDocument = null;
		try {
			if(documentId != null && documentId.longValue()>0L){
				petitionWorkDocument = new PetitionWorkDocument();
				petitionWorkDocument.setDocumentId(documentId);
				petitionWorkDocument.setPetitionWorkDetailsId(petitionWorkDetailsId);
				petitionWorkDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				petitionWorkDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				petitionWorkDocument.setInsertedUserId(userId);
				petitionWorkDocument.setUpdatedUserId(userId);	
				petitionWorkDocument.setIsDeleted("N");
				petitionWorkDocument = null;//petitionWorkDocumentDAO.save(petitionWorkDocument);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in RepresentationRequestService @ savePetitionReffererDocument() "+e.getMessage());
			petitionWorkDocument = null;
		}
		return petitionWorkDocument;
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
			LOG.error("Exception Occured in RepresentationRequestService @ saveLocationAddress() "+e.getMessage());
			locationAddress = null;
		}
		return locationAddress;
	}
	
	
	public PetitionMember savePetitionMember(RepresentationRequestVO petitionMemberVO){
		PetitionMember petitionMember = null;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			try {
				if(petitionMemberVO.getPetitionMemberVO() != null && petitionMemberVO.getPetitionMemberVO().getMemberType() != null && 
						 petitionMemberVO.getPetitionMemberVO().getMemberType().equalsIgnoreCase("SELF")){
					if(petitionMemberVO != null && petitionMemberVO.getPetitionMemberVO() != null){
						petitionMember = new PetitionMember();
						petitionMember.setCandidateName(petitionMemberVO.getPetitionMemberVO().getName());
						
						if(petitionMemberVO.getPetitionMemberVO().getRepresentationDate() != null && petitionMemberVO.getPetitionMemberVO().getRepresentationDate().length()>=10)
							petitionMember.setRepresentationDate(format.parse(petitionMemberVO.getPetitionMemberVO().getRepresentationDate()));
						if(petitionMemberVO.getPetitionMemberVO().getEndorsmentDate() != null && petitionMemberVO.getPetitionMemberVO().getEndorsmentDate().length()>=10)
							petitionMember.setEndorsmentDate(format.parse(petitionMemberVO.getPetitionMemberVO().getEndorsmentDate()));
						petitionMember.setMemberType(petitionMemberVO.getPetitionMemberVO().getMemberType());
						petitionMember.setMobileNo(petitionMemberVO.getPetitionMemberVO().getMobileNo());
						petitionMember.setEmailId(petitionMemberVO.getPetitionMemberVO().getEmailId());
						petitionMember.setVoterCardNo(petitionMemberVO.getPetitionMemberVO().getVoterCardNo());
						petitionMember.setIsDeleted("N");
						petitionMember.setIsExpired("N");
						petitionMember.setInsertedUserId(petitionMemberVO.getUserId());
						petitionMember.setUpdatedUserId(petitionMemberVO.getUserId());
						petitionMember.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						petitionMember.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						
						
						PetitionReffererCandidate petitionReffererCandidate =null;// petitionReffererCandidateDAO.get(petitionMemberVO.getPetitionMemberVO().getReferrerCandidateIdsList().get(0));
						if(petitionReffererCandidate != null){
							petitionMember.setCandidateName(petitionReffererCandidate.getName());
							petitionMember.setAddressId(petitionReffererCandidate.getAddressId());
							petitionMember.setMobileNo(petitionReffererCandidate.getMobileNo());
							petitionMember.setEmailId(petitionReffererCandidate.getEmailId());
							petitionMember.setPetitionReffererCandidateId(petitionMemberVO.getPetitionMemberVO().getReferrerCandidateIdsList().get(0));
							petitionMember.setPetitionDesignationId(petitionReffererCandidate.getPetitionDesignationId());
						}
						
						petitionMember = null;//petitionMemberDAO.save(petitionMember);
						petitionMemberVO.getPetitionMemberVO().getReferrerCandidateIdsList().clear();// no refferer candidate for PetitionRefferrrer
					}
				}else if(petitionMemberVO.getPetitionMemberVO() != null && petitionMemberVO.getPetitionMemberVO().getMemberType() != null &&
						 petitionMemberVO.getPetitionMemberVO().getMemberType().equalsIgnoreCase("REPRESENT")){
					if(petitionMemberVO.getPetitionMemberVO().getReferrerCandidateIdsList() != null && petitionMemberVO.getPetitionMemberVO().getReferrerCandidateIdsList().size()>0){
						for (Long  referrerCandidateId: petitionMemberVO.getPetitionMemberVO().getReferrerCandidateIdsList()) {
							if(petitionMemberVO != null && petitionMemberVO.getPetitionMemberVO() != null){
								
								petitionMember = new PetitionMember();
								petitionMember.setCandidateName(petitionMemberVO.getPetitionMemberVO().getName());
								
								if(petitionMemberVO.getPetitionMemberVO().getRepresentationDate() != null && petitionMemberVO.getPetitionMemberVO().getRepresentationDate().length()>=10)
									petitionMember.setRepresentationDate(format.parse(petitionMemberVO.getPetitionMemberVO().getRepresentationDate()));
								if(petitionMemberVO.getPetitionMemberVO().getEndorsmentDate() != null && petitionMemberVO.getPetitionMemberVO().getEndorsmentDate().length()>=10)
									petitionMember.setEndorsmentDate(format.parse(petitionMemberVO.getPetitionMemberVO().getEndorsmentDate()));
								
								/*PetitionReffererCandidate petitionReffererCandidate = petitionReffererCandidateDAO.get(referrerCandidateId);
								if(petitionReffererCandidate != null){
									petitionMember.setCandidateName(petitionReffererCandidate.getName());
									petitionMember.setAddressId(petitionReffererCandidate.getAddressId());
									petitionMember.setMobileNo(petitionReffererCandidate.getMobileNo());
									petitionMember.setEmailId(petitionReffererCandidate.getEmailId());
								}
								*/
								
								petitionMember.setPetitionReffererCandidateId(referrerCandidateId);
								petitionMember.setMemberType(petitionMemberVO.getPetitionMemberVO().getMemberType());
								petitionMember.setVoterCardNo(petitionMemberVO.getPetitionMemberVO().getVoterCardNo());
								petitionMember.setIsDeleted("N");
								petitionMember.setIsExpired("N");
								petitionMember.setInsertedUserId(petitionMemberVO.getUserId());
								petitionMember.setUpdatedUserId(petitionMemberVO.getUserId());
								petitionMember.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								petitionMember.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								petitionMember = null;//petitionMemberDAO.save(petitionMember);
							}
						}
					}
				}	
			} catch (Exception e) {
				LOG.error("Exception Occured in RepresentationRequestService @ savePetitionMember() "+e.getMessage());
				petitionMember = null;
			}
		return petitionMember;
	}
	
	
	public PetitionSubWorkLocationDetails savePetitionSubWorkDetails(Long petitionWorkDetailsId,RepresentationRequestVO tempDataVO){
		PetitionSubWorkLocationDetails petitionSubWorkLocationDetails = null;
		try {
			if(tempDataVO.getPetitionMemberVO() != null){
				if(commonMethodsUtilService.isListOrSetValid(tempDataVO.getWorksList())){
					for (RepresentationRequestVO dataVO : tempDataVO.getWorksList()) {
						petitionSubWorkLocationDetails = new PetitionSubWorkLocationDetails();
						petitionSubWorkLocationDetails.setPetitionWorkDetailsId(petitionWorkDetailsId);
						petitionSubWorkLocationDetails.setPetitionDepartmentId(dataVO.getDeptId());
						petitionSubWorkLocationDetails.setDescription(dataVO.getProjectDescription());
						petitionSubWorkLocationDetails.setCostEstimation(dataVO.getEstimationCost());
						petitionSubWorkLocationDetails.setPetitionSubjectId(dataVO.getSubjectId());
						
						petitionSubWorkLocationDetails.setIsDeleted("N");
						petitionSubWorkLocationDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
						petitionSubWorkLocationDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
						petitionSubWorkLocationDetails.setInsertedUserId(dataVO.getUserId());
						petitionSubWorkLocationDetails.setUpdatedUserId(dataVO.getUserId());
						
						if(dataVO.getCandidateAddressVO() != null){
							LocationAddress workLocatinAddress = saveLocationAddress(dataVO.getCandidateAddressVO());
							petitionSubWorkLocationDetails.setAddressId(workLocatinAddress.getLocationAddressId());
						}
						petitionSubWorkLocationDetails =null;// petitionSubWorkLocationDetailsDAO.save(petitionSubWorkLocationDetails);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in RepresentationRequestService @ savePetitionSubWorkDetails() "+e.getMessage());
			 petitionSubWorkLocationDetails = null;
		}
		return petitionSubWorkLocationDetails;
	}
	
	public PetitionWorkDetails savePetitionWorkDetails(Long petitionMemberId,RepresentationRequestVO dataVO){
		PetitionWorkDetails petitionWorkDetails = null;
		try {
			if(dataVO.getPetitionMemberVO() != null){
				petitionWorkDetails = new PetitionWorkDetails();
				petitionWorkDetails.setPetitionMemberId(petitionMemberId);
				petitionWorkDetails.setCostEstimation(dataVO.getEstimationCost());
				petitionWorkDetails.setWorkName(dataVO.getWorkName());
				petitionWorkDetails.setNoOfWorks(dataVO.getNoOfWorks());
				petitionWorkDetails.setSubject(dataVO.getSubject());
				petitionWorkDetails.setPetitionSubjectId(dataVO.getSubjectId());
				petitionWorkDetails.setPetitionSubSubjectId(dataVO.getSubSubjectId());
				petitionWorkDetails.setPetitionGrantId(dataVO.getGrantId());
				petitionWorkDetails.setPetitionStatusId(dataVO.getStatusId());
				petitionWorkDetails.setPetitionDepartmentId(dataVO.getDeptId());
				petitionWorkDetails.setIsPreviousPetition(dataVO.getIsPreviousPetition());
				petitionWorkDetails.setPreviousPetitionRefNo(dataVO.getPreviousPetitionRefNo());
				petitionWorkDetails.setProjectDescription(dataVO.getProjectDescription());
				petitionWorkDetails.setIsDeleted("N");
				petitionWorkDetails.setPetitionLeadId(dataVO.getPetitionLeadId());
				petitionWorkDetails.setPetitionBriefLeadId(dataVO.getBriefLeadId());
				petitionWorkDetails.setPetitionGrantId(dataVO.getPetitionGrantId());
				petitionWorkDetails.setPetitionStatusId(dataVO.getPetitionStatusId());
				petitionWorkDetails.setRemarks(dataVO.getRemarks());
				petitionWorkDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				petitionWorkDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				petitionWorkDetails.setInsertedUserId(dataVO.getUserId());
				petitionWorkDetails.setUpdatedUserId(dataVO.getUserId());
				petitionWorkDetails = null;//petitionWorkDetailsDAO.save(petitionWorkDetails);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in RepresentationRequestService @ savePetitionWorkDetails() "+e.getMessage());
			 petitionWorkDetails = null;
		}
		return petitionWorkDetails;
	}
	
	
	public PetitionReffererDocument savePetitionReffererDocument(Long petitionReffererId,Long documentId,Long userId){
		PetitionReffererDocument petitionReffererDocument = null;
		try {
			if(documentId != null && documentId.longValue()>0L){
				petitionReffererDocument = new PetitionReffererDocument();
				petitionReffererDocument.setDocumentId(documentId);
				petitionReffererDocument.setPetitionReffererId(petitionReffererId);
				petitionReffererDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				petitionReffererDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				petitionReffererDocument.setInsertedUserId(userId);
				petitionReffererDocument.setUpdatedUserId(userId);	petitionReffererDocument.setIsDeleted("N");
				petitionReffererDocument = null;//petitionReffererDocumentDAO.save(petitionReffererDocument);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in RepresentationRequestService @ savePetitionReffererDocument() "+e.getMessage());
			 petitionReffererDocument = null;
		}
		return petitionReffererDocument;
	}
	
	public PetitionRefferer savePetitionReferralDetails(Long petitionMemeberId,List<Long> refferalCandidateIdsList,Long userId){
		PetitionRefferer petitionRefferer = null;
		try {
			if(petitionMemeberId != null && petitionMemeberId.longValue()>0L && refferalCandidateIdsList != null && refferalCandidateIdsList.size()>0){
				for (Long refferalCandidateId : refferalCandidateIdsList) {
					petitionRefferer = new PetitionRefferer(); 
					petitionRefferer.setPetitionMemberId(petitionMemeberId);
					petitionRefferer.setPetitionReffererCandidateId(refferalCandidateId);
					petitionRefferer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					petitionRefferer.setUpdatedTime(dateUtilService.getCurrentDateAndTime());petitionRefferer.setIsDeleted("N");
					petitionRefferer.setInsertedUserId(userId);
					petitionRefferer.setUpdatedUserId(userId);
					petitionRefferer =null;// petitionReffererDAO.save(petitionRefferer);
				}
			}else{// self candidates no reffere candidate id
				petitionRefferer = new PetitionRefferer(); 
				petitionRefferer.setPetitionMemberId(petitionMemeberId);
				//petitionRefferer.setPetitionReffererCandidateId(refferalCandidateId);
				petitionRefferer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				petitionRefferer.setUpdatedTime(dateUtilService.getCurrentDateAndTime());petitionRefferer.setIsDeleted("N");
				petitionRefferer.setInsertedUserId(userId);
				petitionRefferer.setUpdatedUserId(userId);
				petitionRefferer = null;//petitionReffererDAO.save(petitionRefferer);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in RepresentationRequestService @ savePetitionReferralDetails() "+e.getMessage());
			 petitionRefferer = null;
		}
		return petitionRefferer;
	}
	
	
	public Document saveDocument(MultipartFile file, String destinationPath,Long userId){
		Document document = null;
		try {
			if(file != null){
				String tempPath = commonMethodsUtilService.createInnerFolders(destinationPath);
				String staticPath = commonMethodsUtilService.createInnerFolders(destinationPath+"Petition_Documents");
				String datePath = commonMethodsUtilService.generateImagePathWithDateTime();
				
				document = new Document();
				commonMethodsUtilService.copyFile(file.getOriginalFilename(),staticPath);
				byte[] fileData = file.getBytes();
				Files.write(fileData,new File(staticPath+"//"+datePath+".jpg"));
				document.setPath(staticPath+"//"+datePath+".jpg");
				document.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				document.setInsertedUserId(userId);
				document = documentDAO.save(document);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in RepresentationRequestService @ saveDocument() "+e.getMessage());
			document = null;
		}
		return document;
	}
	
	//111111
	public RepresentationRequestVO getRepresentationRequestDetailsByRepresentationRequestId(Long representationMemberId){
		RepresentationRequestVO representationRequestVO = null;
		try {
			PetitionMember petitionMember = null;//petitionMemberDAO.get(representationMemberId);
			if(petitionMember != null){
				representationRequestVO = setMemberDataToResultView(petitionMember);
				List<Object[]> reffererCandidateList = null;// petitionReffererDAO.getPetitionReffererDetailsByMemberId(representationMemberId);
				updatePetitionReffererDetails(reffererCandidateList,representationRequestVO);
				
				Set<Long> representatinMemberIdsList= new HashSet<Long>(0);
				representatinMemberIdsList.add(representationMemberId);
				List<Object[]> workDetailsList = null;//petitionWorkDetailsDAO.getWorkLocationDetailsByPetitionMemberId((representatinMemberIdsList));
				List<Long> workDetailsIdsList = new ArrayList<Long>(0);
				updatePetitionWorkDetails(workDetailsList,representationRequestVO,workDetailsIdsList);
				if(commonMethodsUtilService.isListOrSetValid(workDetailsIdsList)){
					List<PetitionSubWorkLocationDetails> subWorksList = null;//petitionSubWorkLocationDetailsDAO.getSubWorkDetailsByWorkDetailsIdsList(workDetailsIdsList);
					updatePetitionSubWorkDetails(subWorksList,representationRequestVO);
				}
			}
		} catch (Exception e) {
			  LOG.error("Exception Occured in getRepresentationRequestDetailsByRepresentationRequestId "+e.getMessage());
		}
		return representationRequestVO;
	}

	public void updatePetitionSubWorkDetails(List<PetitionSubWorkLocationDetails> subWorksList,RepresentationRequestVO representationRequestVO){
		try {
			if(commonMethodsUtilService.isListOrSetValid(subWorksList) && representationRequestVO != null){
				for (PetitionSubWorkLocationDetails subVO : subWorksList) {
					RepresentationRequestVO subWorkVO = new RepresentationRequestVO();
					subWorkVO.setId(subVO.getPetitionSubWorkLocationDetailsId());
					subWorkVO.setEstimationCost(subVO.getCostEstimation());
					subWorkVO.setSubjectId(subVO.getPetitionSubjectId());
					subWorkVO.setLocationLevelId(subVO.getLocationScopeId());
					subWorkVO.setLocationValue(subVO.getLocationScopeValue());
					subWorkVO.setProjectDescription(subVO.getDescription());
					representationRequestVO.getWorksList().add(subWorkVO);
					
					AddressVO addressVO = setAddressDetailsToResultView(subVO.getLocationAddress());
					if(addressVO != null)
						subWorkVO.setCandidateAddressVO(addressVO);
					
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in updatePetitionSubWorkDetails "+e.getMessage());
		}
	}
	
	public void updatePetitionWorkDetails(List<Object[]> workDetailsList,RepresentationRequestVO representationRequestVO ,List<Long> workDetailsIdsList){
		try {
			if(commonMethodsUtilService.isListOrSetValid(workDetailsList) && representationRequestVO != null){
				for (Object[] param : workDetailsList) {
					representationRequestVO.setWorkName(commonMethodsUtilService.getStringValueForObject(param[1]));
					representationRequestVO.setNoOfWorks(commonMethodsUtilService.getLongValueForObject(param[2]));
					representationRequestVO.setIsPreviousPetition(commonMethodsUtilService.getStringValueForObject(param[3]));
					representationRequestVO.setPreviousPetitionRefNo(commonMethodsUtilService.getStringValueForObject(param[4]));
					
					representationRequestVO.setSubject(commonMethodsUtilService.getStringValueForObject(param[6]));
					representationRequestVO.setSubjectId(commonMethodsUtilService.getLongValueForObject(param[7]));
					representationRequestVO.setDeptId(commonMethodsUtilService.getLongValueForObject(param[9]));
					representationRequestVO.setEstimationCost(commonMethodsUtilService.getLongValueForObject(param[11]));
					representationRequestVO.setProjectDescription(commonMethodsUtilService.getStringValueForObject(param[12]));
					
					workDetailsIdsList.add(commonMethodsUtilService.getLongValueForObject(param[13]));
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in updatePetitionWorkDetails "+e.getMessage());
		}
	}
	
	public void updatePetitionReffererDetails(List<Object[]> reffererCandidateList,RepresentationRequestVO representationRequestVO ){
		try {
			if(commonMethodsUtilService.isListOrSetValid(reffererCandidateList) && representationRequestVO != null ){
				for (Object[] param : reffererCandidateList) {
					representationRequestVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					representationRequestVO.setReffererId(commonMethodsUtilService.getLongValueForObject(param[1]));
					
					PetitionMemberVO petiRefCandiVO = new PetitionMemberVO();
					petiRefCandiVO.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
					petiRefCandiVO.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
					petiRefCandiVO.setMemberType(commonMethodsUtilService.getStringValueForObject(param[4]));// designation
					petiRefCandiVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[5]));
					petiRefCandiVO.setEmailId(commonMethodsUtilService.getStringValueForObject(param[6]));
					
					Long addressId = commonMethodsUtilService.getLongValueForObject(param[7]);
					
					if(addressId != null && addressId.longValue()>0L){
						LocationAddress address = locationAddressDAO.get(addressId);
						if(address != null){
							AddressVO addressVO = setAddressDetailsToResultView(address);
							petiRefCandiVO.setAddressVO(addressVO);
						}
					}
					
					IdNameVO partyVO = new IdNameVO();
					partyVO.setId(commonMethodsUtilService.getLongValueForObject(param[8]));
					partyVO.setName(commonMethodsUtilService.getStringValueForObject(param[9]));
					petiRefCandiVO.setIdNameVO(partyVO);
					
					representationRequestVO.setPetitionRefCandidateVO(petiRefCandiVO);
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in updatePetitionReffererDetails "+e.getMessage());
		}
		
	}
	public RepresentationRequestVO setMemberDataToResultView(PetitionMember resultPetitionMember){
		RepresentationRequestVO representationRequestVO = null;
		try {
			SimpleDateFormat format  = new SimpleDateFormat("dd-MM-yyyy");
			if(resultPetitionMember != null){
				representationRequestVO = new RepresentationRequestVO();
				
				PetitionMemberVO petiMemberVO = new PetitionMemberVO();
				petiMemberVO.setId(resultPetitionMember.getPetitionMemberId());
				petiMemberVO.setName(resultPetitionMember.getCandidateName());
				representationRequestVO.setRepresenteeType(resultPetitionMember.getMemberType());
				petiMemberVO.setMobileNo(resultPetitionMember.getMobileNo());
				petiMemberVO.setEmailId(resultPetitionMember.getEmailId());
				petiMemberVO.setVoterCardNo(resultPetitionMember.getVoterCardNo());
				
				if(resultPetitionMember.getRepresentationDate() != null)
					petiMemberVO.setRepresentationDate(format.format(resultPetitionMember.getRepresentationDate()));
				if(resultPetitionMember.getRepresentationDate() != null) 
					petiMemberVO.setEndorsmentDate(format.format(resultPetitionMember.getEndorsmentDate()));
				
				AddressVO addressVO = setAddressDetailsToResultView(resultPetitionMember.getLocationAddress());
				if(addressVO != null)
					petiMemberVO.setAddressVO(addressVO);
				if(petiMemberVO != null)
					representationRequestVO.setPetitionMemberVO(petiMemberVO);
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in setMemberDataToResultView "+e.getMessage());
			 representationRequestVO = null;
		}
		return representationRequestVO;
	}
	
	
	public AddressVO setAddressDetailsToResultView(LocationAddress address){
		AddressVO addressVO = null;
		try {
			if(address != null){
				addressVO = new AddressVO();
				addressVO.setStateId(address.getStateId());
				addressVO.setDistrictId(address.getDistrictId());
				addressVO.setParliamentId(address.getParliamentId());
				addressVO.setAssemblyId(address.getConstituencyId());
				addressVO.setTehsilId(address.getTehsilId());
				addressVO.setPanchayatId(address.getPanchayatId());
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in setMemberDataToResultView "+e.getMessage());
			 addressVO = null;
		}
		return addressVO;
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
		    	List<Object[]> referalObjs = null;//petitionReffererCandidateDAO.getCandidatseDetailsByDesignationAndLocation(dataVo.getDeptId(),dataVo.getLocationLevelId(),dataVo.getLocationValue());
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
		    			mainV0.setCandidateAddressVO(addressVO);
		    			returnList.add(mainV0);
		    		}
		    	}
		    } catch (Exception e) {
		      LOG.error("Exception Occured in savePetitionMember "+e.getMessage());
		    }
		     return returnList;
		  }
		
		 public List<RepresentationRequestVO> getRepresentativeSearchWiseDetails(InputVO inputVO){
		    	List<RepresentationRequestVO> resultList = new ArrayList<RepresentationRequestVO>();
		    	Map<Long,RepresentationRequestVO> representWiseSearchMap = new HashMap<Long,RepresentationRequestVO>();
		    	List<Long> memberidsLst = new ArrayList<Long>();
		    	
		    	try{
		    		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		    		Long searchLevelId=inputVO.getSearchLevelId();
		    		Long searchLevelValue=inputVO.getSearchLevelValue();
		    		Date startDate = null;
		    		Date endDate = null;
		    		
		    		if(inputVO.getFromDate() != null && !inputVO.getFromDate().toString().equalsIgnoreCase("") && 
		    				inputVO.getToDate() != null && !inputVO.getToDate().toString().equalsIgnoreCase("") ){
		    			startDate = format.parse(inputVO.getFromDate());
		    			endDate = format.parse(inputVO.getToDate());
		    		}
		    		
		    		LOG.info("enterd into LocationDetailsService getRepresentativeSearchWiseDetails");
		    		List<Object[]> representRefObjLst =null;//petitionMemberDAO.getRepresentativeSearchDetailsBy(inputVO.getFilterType(),inputVO.getFilterValue(),searchLevelId,searchLevelValue,startDate,endDate);
		    		if(representRefObjLst != null && representRefObjLst.size() >0){
		    			for(Object[] objs : representRefObjLst){
		    				Long petitinMembrId = commonMethodsUtilService.getLongValueForObject(objs[0]);
		    				RepresentationRequestVO searchVO = representWiseSearchMap.get(petitinMembrId);
		    				if(searchVO == null){
		    					searchVO = new RepresentationRequestVO();
		    					searchVO.setPetitionMemberId(petitinMembrId);
		    					searchVO.setRefCode(commonMethodsUtilService.getStringValueForObject(objs[1]));
		    					searchVO.setCandidateName(commonMethodsUtilService.getStringValueForObject(objs[2]));
		    					searchVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(objs[3]));
		    					searchVO.setAge(commonMethodsUtilService.getLongValueForObject(objs[4]));
		    					
		    					searchVO.setRepresenteeType(commonMethodsUtilService.getStringValueForObject(objs[7]));
		    					
		    					if(commonMethodsUtilService.getStringValueForObject(objs[7]).equalsIgnoreCase("REPRESENT")){
		    						searchVO.setRefDesignation(commonMethodsUtilService.getStringValueForObject(objs[16]));
		    						searchVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(objs[8]));
			    					searchVO.setDistrict(commonMethodsUtilService.getStringValueForObject(objs[9]));
			    					searchVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objs[10]));
			    					searchVO.setConstituency(commonMethodsUtilService.getStringValueForObject(objs[11]));
		    					}else if(commonMethodsUtilService.getStringValueForObject(objs[7]).equalsIgnoreCase("SELF")){
		    						searchVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(objs[5]));
			    					searchVO.setDesignation(commonMethodsUtilService.getStringValueForObject(objs[6]));
			    					
		    						searchVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(objs[12]));
			    					searchVO.setDistrict(commonMethodsUtilService.getStringValueForObject(objs[13]));
			    					searchVO.setConstituencyId(commonMethodsUtilService.getLongValueForObject(objs[14]));
			    					searchVO.setConstituency(commonMethodsUtilService.getStringValueForObject(objs[15]));
		    					}
		    					
		    					memberidsLst.add(petitinMembrId);
		    					representWiseSearchMap.put(searchVO.getPetitionMemberId(), searchVO);
		    				}
		    			}
		    		}
		    		
		    		List<Object[]> workLocationObjLst = null;//petitionWorkDetailsDAO.getWorkLocationDetailsByPetitionMemberId(representWiseSearchMap.keySet());
		    		if(workLocationObjLst != null && workLocationObjLst.size() >0){
		    			for(Object[] workObj : workLocationObjLst){
		    				Long memberId = commonMethodsUtilService.getLongValueForObject(workObj[0]);
		    				RepresentationRequestVO workVO = representWiseSearchMap.get(memberId);
		    				if(workVO != null){
		    					//workVO.setPetitionMemberId(commonMethodsUtilService.getLongValueForObject(workObj[0]));
		    					workVO.setWorkName(commonMethodsUtilService.getStringValueForObject(workObj[1]));
		    					workVO.setNoOfWorks(commonMethodsUtilService.getLongValueForObject(workObj[2]));
		    					workVO.setIsPreviousPetition(commonMethodsUtilService.getStringValueForObject(workObj[3]));
		    					workVO.setPreviousPetitionRefNo(commonMethodsUtilService.getStringValueForObject(workObj[4]));
		    					workVO.setDate(commonMethodsUtilService.getStringValueForObject(workObj[5]));
		    					workVO.setSubject(commonMethodsUtilService.getStringValueForObject(workObj[6]));
		    					workVO.setDepartrmentId(commonMethodsUtilService.getLongValueForObject(workObj[7]));
		    					workVO.setDepartrment(commonMethodsUtilService.getStringValueForObject(workObj[8]));
		    					if(workVO.getDate() != null && !workVO.getDate().isEmpty() && workVO.getDate().trim().length()>10)
		    						workVO.setDate(workVO.getDate().substring(0, 11));
		    					workVO.setSubject(commonMethodsUtilService.getStringValueForObject(workObj[10]));
		    					workVO.setSubSubject(commonMethodsUtilService.getStringValueForObject(workObj[15]));
		    					workVO.setLead(commonMethodsUtilService.getStringValueForObject(workObj[16]));
		    					workVO.setBriefLead(commonMethodsUtilService.getStringValueForObject(workObj[17]));
		    					workVO.setGrant(commonMethodsUtilService.getStringValueForObject(workObj[19]));
		    					workVO.setStatus(commonMethodsUtilService.getStringValueForObject(workObj[18]));
		    					workVO.setRemarks(commonMethodsUtilService.getStringValueForObject(workObj[20]));
		    				}
		    			}
		    		}
		    		
		    		if(commonMethodsUtilService.isMapValid(representWiseSearchMap)){
		    			resultList.addAll(representWiseSearchMap.values());
		    		}
		    		 
		    	}catch(Exception e){
		    		LOG.error("Exception raised into LocationDetailsService of getRepresentativeSearchWiseDetails() ",e);
		    	}
		    	return resultList;
		    }
}
