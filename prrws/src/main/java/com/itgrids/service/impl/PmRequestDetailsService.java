package com.itgrids.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.itgrids.dao.IDocumentDAO;
import com.itgrids.dao.ILocationAddressDAO;
import com.itgrids.dao.IPetitionDAO;
import com.itgrids.dao.IPmPetitionDocumentDAO;
import com.itgrids.dao.IPmRefCandidateDAO;
import com.itgrids.dao.IPmRefCandidateDesignationDAO;
import com.itgrids.dao.IPmRepresenteeDAO;
import com.itgrids.dao.IPmRepresenteeDesignationDAO;
import com.itgrids.dao.IPmRepresenteeRefDetailsDAO;
import com.itgrids.dao.IPmRepresenteeRefDocumentDAO;
import com.itgrids.dao.IPmSubWorkDetailsDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.PetitionsWorksVO;
import com.itgrids.dto.PmRequestVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.model.Document;
import com.itgrids.model.LocationAddress;
import com.itgrids.model.Petition;
import com.itgrids.model.PetitionReffererDocument;
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
			if(pmRepresenteeId != null && pmRepresenteeId.longValue()>0L && commonMethodsUtilService.isListOrSetValid(pmRequestVO.getReferList())){
				for (PmRequestVO refVO : pmRequestVO.getReferList()) {
					List<PmRepresenteeDesignation> pmRepresenteeList = pmRepresenteeDesignationDAO.getPmRepresenteeDesignationByRepresenteeId(pmRepresenteeId);
					if(commonMethodsUtilService.isListOrSetValid(pmRepresenteeList)){
						for (PmRepresenteeDesignation pmRepresenteeDesignation : pmRepresenteeList) {
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
									petitionRefferer = pmRepresenteeRefDetailsDAO.save(petitionRefferer);
									
									if(petitionRefferer.getPmRepresenteeRefDetailsId() != null && petitionRefferer.getPmRepresenteeRefDetailsId().longValue()>0L && 
											 commonMethodsUtilService.isListOrSetValid(refVO.getFileList())){
										for (MultipartFile file : refVO.getFileList()) {
												Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL,pmRequestVO.getUserId());
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
								petitionRefferer = pmRepresenteeRefDetailsDAO.save(petitionRefferer);
								
								if(petitionRefferer.getPmRepresenteeRefDetailsId() != null && petitionRefferer.getPmRepresenteeRefDetailsId().longValue()>0L && 
										 commonMethodsUtilService.isListOrSetValid(refVO.getFileList())){
									for (MultipartFile file : refVO.getFileList()) {
											Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL,pmRequestVO.getUserId());
											savePetitionReffererDocument(petitionRefferer.getPmRepresenteeRefDetailsId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId());
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
				petitionReffererDocument = pmRepresenteeRefDocumentDAO.save(petitionReffererDocument);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionReffererDocument() "+e.getMessage());
			 petitionReffererDocument = null;
		}
		return petitionReffererDocument;
	}
	
	public Long savePetitionSubWorkDetails(Long petitonId,List<PetitionsWorksVO> subWorksList){
		Long noOfWorksCount = 0L;
		try {
			if(commonMethodsUtilService.isListOrSetValid(subWorksList)){
				for (PetitionsWorksVO dataVO : subWorksList) {
					PmSubWorkDetails petitionSubWorkLocationDetails = new PmSubWorkDetails();
					petitionSubWorkLocationDetails.setPetitionId(petitonId);
					petitionSubWorkLocationDetails.setGrievanceDescrption(dataVO.getGrievanceDescription());
					petitionSubWorkLocationDetails.setCostEstimation(Long.valueOf(String.valueOf(dataVO.getEstimateCost())));
					petitionSubWorkLocationDetails.setPmDepartmentId(dataVO.getDeptId());
					petitionSubWorkLocationDetails.setPmSubjectId(dataVO.getSubjectId());
					petitionSubWorkLocationDetails.setPmSubSubjectId(dataVO.getSubSubjectId());
					petitionSubWorkLocationDetails.setLocationScopeId(dataVO.getLocationScopeId());
					petitionSubWorkLocationDetails.setLocationValue(dataVO.getLocationValue());
					
					LocationAddress address = saveLocationAddress(dataVO.getAddressVO());
					if(address != null && address.getLocationAddressId() != null && address.getLocationAddressId().longValue()>0L)
						petitionSubWorkLocationDetails.setAddressId(address.getLocationAddressId());
					
					petitionSubWorkLocationDetails.setPmLeadId(dataVO.getLeadId());
					petitionSubWorkLocationDetails.setPmBriefLeadId(dataVO.getBriefLeadId());
					petitionSubWorkLocationDetails.setPmGrantId(dataVO.getGrantId());
					petitionSubWorkLocationDetails.setPmStatusId(1L);// endorsement pending
					petitionSubWorkLocationDetails.setCoveringLetterPath(null);
					petitionSubWorkLocationDetails.setIsDeleted("N");
					
					petitionSubWorkLocationDetails = pmSubWorkDetailsDAO.save(petitionSubWorkLocationDetails);
					noOfWorksCount=noOfWorksCount+1;
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionSubWorkDetails() "+e.getMessage());
		}
		return noOfWorksCount;
	}
	
	public Petition savePetitionWorkDetails(Long pmRepresenteeId,PmRequestVO pmRequestVO){
		Petition petition = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(pmRequestVO != null){
				if(commonMethodsUtilService.isListOrSetValid(pmRequestVO.getWorksList())){
					for (PetitionsWorksVO dataVO : pmRequestVO.getWorksList()) {
						petition = new Petition();
						petition.setEndorsmentDate(null);
						petition.setEndorsmentNo(null);
						petition.setRepresentationDate(format.parse(pmRequestVO.getRepresentationdate()));
						if(dataVO.getEstimateCost() != null)
							petition.setEstimationCost(Long.valueOf(String.valueOf(dataVO.getEstimateCost())));
						petition.setWorkName(dataVO.getWorkName());
						petition.setNoOfWork(dataVO.getNoOfWorks());
						petition.setIsDeleted("N");
						petition.setIsPreviousPetition(dataVO.getIsPreviousPetition());
						petition.setGrievanceDescription(dataVO.getGrievanceDescription());
						petition.setPmStatusId(1L);//pending endorsment
						petition = pititionDAO.save(petition);
						
						if(petition.getPetitionId() != null && petition.getPetitionId().longValue()>0L){
							Long submittedWorksCount = savePetitionSubWorkDetails(petition.getPetitionId(),dataVO.getSubWorksList());
							if(submittedWorksCount.longValue() != dataVO.getNoOfWorks()){
								throw new Exception(" The submitted No of works not saved . Please check once.");
							}
							
							if(dataVO.getFileList() != null && dataVO.getFileList().size()>0){
								for (MultipartFile file : dataVO.getFileList()) {
									Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL,pmRequestVO.getUserId());
									savePetitionWorkDocument(petition.getPetitionId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId());
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionWorkDetails() "+e.getMessage());
			petition = null;
		}
		return petition;
	}
	
	public PmPetitionDocument savePetitionWorkDocument(Long petitionWorkDetailsId,Long documentId,Long userId){
		PmPetitionDocument petitionWorkDocument = null;
		try {
			if(documentId != null && documentId.longValue()>0L){
				petitionWorkDocument = new PmPetitionDocument();
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
				String tempPath = commonMethodsUtilService.createInnerFolders(destinationPath);
				String staticPath = commonMethodsUtilService.createInnerFolders(destinationPath+"Petition_Documents");
				String datePath = commonMethodsUtilService.generateImagePathWithDateTime();
				
				document = new Document();
				commonMethodsUtilService.copyFile(file.getOriginalFilename(),staticPath);
				byte[] fileData = file.getBytes();
				Files.write(fileData,new File(staticPath+"//"+datePath+".jpg"));
				document.setDocumentPath(staticPath+"//"+datePath+".jpg");
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
							pmRepresentee = pmRepresenteeDAO.save(pmRepresentee);
						}
						
						if(pmRepresentee != null && pmRepresentee.getPmRepresenteeId() != null && pmRepresentee.getPmRepresenteeId().longValue()>0L){
							PmRepresenteeDesignation pmRepresenteeDesignation = new PmRepresenteeDesignation();
							pmRepresenteeDesignation.setPmRepresenteeId(pmRepresentee.getPmRepresenteeId());
							pmRepresenteeDesignation.setPmRepresenteeDesignationId(pmRequestVO.getRepresenteeDesignationId());
							pmRepresenteeDesignation.setIsActive("Y");
							if(pmRequestVO.getStartDate() != null && pmRequestVO.getEndDate() != null){
								pmRepresenteeDesignation.setStartDate(format.parse(pmRequestVO.getStartDate()));
								pmRepresenteeDesignation.setEndDate(format.parse(pmRequestVO.getEndDate()));
							}
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
								pmRepresentee.setAddressId(pmRefCandidate.getAddressId());
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
	
}
