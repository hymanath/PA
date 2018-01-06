package com.itgrids.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.itgrids.dao.IDocumentDAO;
import com.itgrids.dao.ILocationAddressDAO;
import com.itgrids.dao.IPetitionDAO;
import com.itgrids.dao.IPmBriefLeadDAO;
import com.itgrids.dao.IPmDepartmentDesignationHierarchyDAO;
import com.itgrids.dao.IPmDepartmentDesignationOfficerDAO;
import com.itgrids.dao.IPmGrantDAO;
import com.itgrids.dao.IPmOfficerUserDAO;
import com.itgrids.dao.IPmPetitionAssignedOfficerDAO;
import com.itgrids.dao.IPmPetitionDocumentDAO;
import com.itgrids.dao.IPmRefCandidateDAO;
import com.itgrids.dao.IPmRefCandidateDesignationDAO;
import com.itgrids.dao.IPmRepresenteeDAO;
import com.itgrids.dao.IPmRepresenteeDesignationDAO;
import com.itgrids.dao.IPmRepresenteeRefDetailsDAO;
import com.itgrids.dao.IPmRepresenteeRefDocumentDAO;
import com.itgrids.dao.IPmRequiredLettersImagesDAO;
import com.itgrids.dao.IPmStatusDAO;
import com.itgrids.dao.IPmSubWorkCoveringLetterDAO;
import com.itgrids.dao.IPmSubWorkDetailsDAO;
import com.itgrids.dao.IPmTrackingDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.CadreRegistrationVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.PetitionFileVO;
import com.itgrids.dto.PetitionMemberVO;
import com.itgrids.dto.PetitionTrackingVO;
import com.itgrids.dto.PetitionsWorksVO;
import com.itgrids.dto.PmRequestEditVO;
import com.itgrids.dto.PmRequestVO;
import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.RepresenteeViewVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.dto.ResultStatus;
import com.itgrids.dto.UserVO;
import com.itgrids.model.Document;
import com.itgrids.model.LocationAddress;
import com.itgrids.model.Petition;
import com.itgrids.model.PmBriefLead;
import com.itgrids.model.PmGrant;
import com.itgrids.model.PmPetitionAssignedOfficer;
import com.itgrids.model.PmPetitionDocument;
import com.itgrids.model.PmRefCandidate;
import com.itgrids.model.PmRefCandidateDesignation;
import com.itgrids.model.PmRepresentee;
import com.itgrids.model.PmRepresenteeDesignation;
import com.itgrids.model.PmRepresenteeRefDetails;
import com.itgrids.model.PmRepresenteeRefDocument;
import com.itgrids.model.PmStatus;
import com.itgrids.model.PmSubWorkCoveringLetter;
import com.itgrids.model.PmSubWorkDetails;
import com.itgrids.model.PmTracking;
import com.itgrids.service.ILocationDetailsService;
import com.itgrids.service.IPmRequestDetailsService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;
import com.itgrids.utils.ITextCoveringLetterGeneration;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
	private IPmOfficerUserDAO pmOfficerUserDAO;
	
	@Autowired
	private ILocationDetailsService locationDetailsService;
	@Autowired
	private IPmStatusDAO pmStatusDAO;
	
	@Autowired
	private IPmTrackingDAO pmTrackingDAO;
	
	@Autowired
	private IPmDepartmentDesignationHierarchyDAO pmDepartmentDesignationHierarchyDAO;
	
	@Autowired
	private IPmDepartmentDesignationOfficerDAO pmDepartmentDesignationOfficerDAO;
	
	@Autowired
	private IPmSubWorkCoveringLetterDAO pmSubWorkCoveringLetterDAO;
	
	@Autowired
	private IPmBriefLeadDAO pmBriefLeadDAO;
	@Autowired
	private IPmGrantDAO pmGrantDAO;
	@Autowired
	private IPmRequiredLettersImagesDAO pmRequiredLettersImagesDAO;
	@Autowired
	private IPmPetitionAssignedOfficerDAO pmPetitionAssignedOfficerDAO;
	
	public ResponseVO saveRepresentRequestDetails(PmRequestVO pmRequestVO){
		ResponseVO responseVO = new ResponseVO();
		try {
			PmRepresentee pmRepresentee =  null;
			String insertionType = "new";
			if(pmRequestVO.getRemarks() == null || pmRequestVO.getRemarks().isEmpty())
				pmRequestVO.setRemarks(" NEW PETITION CREATED. ");
			if(pmRequestVO.getExistingPetitionId() != null && pmRequestVO.getExistingPetitionId().longValue()>0L){
				insertionType = "update";
				Long representeeId = pmRepresenteeRefDetailsDAO.getRepresenteeDetailsByPetitonId(pmRequestVO.getExistingPetitionId());
				if(representeeId != null && representeeId.longValue()>0L){
					pmRepresentee = pmRepresenteeDAO.get(representeeId);
					updatePetitionSubWorksAndDocumentDetails(pmRequestVO.getExistingPetitionId(),pmRequestVO.getUserId());
					pmRequestVO.setRepresenteeId(representeeId);
				}
				pmRepresentee = saveRepresenteeDetails(pmRequestVO,insertionType);
			}
			if(pmRepresentee == null){
				/** Start Petition Representee Details saving */
					pmRepresentee = saveRepresenteeDetails(pmRequestVO,insertionType);
				/** End Petition Member Details saving */
			}
			/** Start Petition Referrer Details */
				if(pmRepresentee != null){
					Petition petition = savePetitionWorkDetails(pmRepresentee.getPmRepresenteeId(),pmRequestVO,insertionType);
					if(petition == null)
						throw new Exception("Petition details not saved successfully.");
					
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
	
	@SuppressWarnings("static-access")
	public ResultStatus updatePetitionSubWorksAndDocumentDetails(Long petitionId,Long userId){
		ResultStatus resultStatus = new ResultStatus();
		try{
			Date currentTime=dateUtilService.getCurrentDateAndTime();
			
			List<Long> petitionDocumentIds =  pmPetitionDocumentDAO.getPmPetitionDocumentIds(petitionId);
			if(commonMethodsUtilService.isListOrSetValid(petitionDocumentIds))
			    pmPetitionDocumentDAO.updatePmpetitionDocuments(petitionDocumentIds,userId);
			
			List<Long> subWorkIds = pmSubWorkDetailsDAO.getPmSubWorkDetailsIds(petitionId);
			if(commonMethodsUtilService.isListOrSetValid(subWorkIds))
				pmSubWorkDetailsDAO.updatePmsubWorkDetails(subWorkIds, currentTime, userId);
			
			List<Long> pmRepresenteeRefDetailsIds =	pmRepresenteeRefDetailsDAO.getPmRepresenteeRefDetailsIds(petitionId);
			if(commonMethodsUtilService.isListOrSetValid(pmRepresenteeRefDetailsIds))
				pmRepresenteeRefDetailsDAO.updatePmRepresenteRefDetails(pmRepresenteeRefDetailsIds,currentTime,userId);
			
			List<Long> representeeRefDocumentIds = pmRepresenteeRefDocumentDAO.getPmRepresenteeRefDocumentIds(pmRepresenteeRefDetailsIds);
			if(commonMethodsUtilService.isListOrSetValid(representeeRefDocumentIds))
				pmRepresenteeRefDocumentDAO.updatePmPmRepresenteeRefDocumens(representeeRefDocumentIds, currentTime, userId);
			
			resultStatus.setResultCode(1);
		}catch(Exception e){
			resultStatus.setResultCode(0);
			LOG.error("Exception Occured in updatePetitionSubWorksAndDocumentDetails in PmRequestDetailsService",e);
		}
		return resultStatus;
	}
	
	@SuppressWarnings("static-access")
	public Long  savePetitionReferralDetails(Long pmRepresenteeId,Long petitionId,PmRequestVO pmRequestVO){
		Long noOfRefCount=0L;
		try {
			if(pmRepresenteeId != null && pmRepresenteeId.longValue()>0L){
				List<PmRepresenteeDesignation> pmRepresenteeList = pmRepresenteeDesignationDAO.getPmRepresenteeDesignationByRepresenteeId(pmRepresenteeId);
				if(pmRequestVO.getRepresentationType() != null &&	 pmRequestVO.getRepresentationType().equalsIgnoreCase("SELF")){
					List<PmRefCandidateDesignation> pmRefCandidateList = pmRefCandidateDesignationDAO.getPmRepresenteeDesignationByPmRefCandidateId(pmRequestVO.getRefCandidateId());
					if(commonMethodsUtilService.isListOrSetValid(pmRefCandidateList)){
						Long orderNo =1L;
						boolean isFilesUploaded = false;
						for (PmRefCandidateDesignation pmRefDesignation : pmRefCandidateList) {
								if(commonMethodsUtilService.isListOrSetValid(pmRepresenteeList)){
									for (PmRepresenteeDesignation pmRepRefDesignation : pmRepresenteeList) {
										PmRepresenteeRefDetails petitionRefferer = new PmRepresenteeRefDetails(); 
										petitionRefferer.setPetitionId(petitionId);
										petitionRefferer.setPmRefCandidateId(pmRequestVO.getRefCandidateId());
										petitionRefferer.setPmRefCandidateDesignationId(pmRefDesignation.getPmRefCandidateDesignationId());// ref and representee designation same. himself referrer
										petitionRefferer.setPmRepresenteeId(pmRepRefDesignation.getPmRepresenteeId());
										petitionRefferer.setPmRepresenteeDesignationId(pmRepRefDesignation.getPmRepresenteeDesignationId());
										petitionRefferer.setIsDeleted("N");
										petitionRefferer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
										petitionRefferer.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
										petitionRefferer.setInsertedUserId(pmRequestVO.getUserId());
										petitionRefferer.setUpdatedUserId(pmRequestVO.getUserId());
										petitionRefferer.setOrderNo(orderNo);
										petitionRefferer = pmRepresenteeRefDetailsDAO.save(petitionRefferer);
										if(!isFilesUploaded){
											isFilesUploaded = true;
											if(petitionRefferer.getPmRepresenteeRefDetailsId() != null && petitionRefferer.getPmRepresenteeRefDetailsId().longValue()>0L && 
													 commonMethodsUtilService.isListOrSetValid(pmRequestVO.getReferList()) && commonMethodsUtilService.isListOrSetValid(pmRequestVO.getReferList().get(0).getFileList())){
												for (MultipartFile file : pmRequestVO.getReferList().get(0).getFileList()) {
														Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER,pmRequestVO.getUserId());
														if(petitionWorkDocument != null)
															savePetitionReffererDocument(petitionRefferer.getPmRepresenteeRefDetailsId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId());
												}
										}
										if(petitionRefferer.getPmRepresenteeRefDetailsId() != null && petitionRefferer.getPmRepresenteeRefDetailsId().longValue()>0L && 
												 commonMethodsUtilService.isListOrSetValid(pmRequestVO.getReferList()) && 
												 commonMethodsUtilService.isListOrSetValid(pmRequestVO.getReferList().get(0).getFileNamesList())){
											
											if(pmRequestVO.getReferList().get(0).getFileNamesList() != null && pmRequestVO.getReferList().get(0).getFileNamesList().size()>0){
												for (KeyValueVO item : pmRequestVO.getReferList().get(0).getFileNamesList()) {
													if(item != null && item.getValue() != null){
														try {
															List<Long> documentsIsList=documentDAO.getdocumentByFilePath(item.getValue().substring(34));
															if(commonMethodsUtilService.isListOrSetValid(documentsIsList)){
																Long documentId=0L;
																for (Long docId : documentsIsList) {
																	if(docId != null && docId.longValue()>0L)
																		documentId = docId;break;
																}
																if(documentId != null)
																	savePetitionReffererDocument(petitionRefferer.getPmRepresenteeRefDetailsId(),documentId,pmRequestVO.getUserId());
															} 
														}catch (Exception e) {}
													}
												}
											}
										}
									}
									orderNo = orderNo+1L;
								}
							}
						}
					}
				}else if (commonMethodsUtilService.isListOrSetValid(pmRequestVO.getReferList()) && pmRequestVO.getRepresentationType() != null &&	 pmRequestVO.getRepresentationType().equalsIgnoreCase("REPRESENTEE") ){
					Long orderNo =1L;
					List<Long> existingRefIds = new ArrayList<Long>(0);
					for (PmRequestVO refVO : pmRequestVO.getReferList()) {
						//List<PmRepresenteeDesignation> pmRepresenteeList = pmRepresenteeDesignationDAO.getPmRepresenteeDesignationByRepresenteeId(pmRepresenteeId);
						if(refVO != null && commonMethodsUtilService.isListOrSetValid(pmRepresenteeList)){
							boolean isFilesUploaded = false;
							for (PmRepresenteeDesignation pmRepresenteeDesignation : pmRepresenteeList) {
								if(refVO.getRefCandidateId() != null){
									List<PmRefCandidateDesignation> pmRefCandidateList = pmRefCandidateDesignationDAO.getPmRepresenteeDesignationByPmRefCandidateId(refVO.getRefCandidateId());
									if(commonMethodsUtilService.isListOrSetValid(pmRefCandidateList)){
										for (PmRefCandidateDesignation pmRefDesignation : pmRefCandidateList) {
											
											PmRepresenteeRefDetails petitionRefferer = new PmRepresenteeRefDetails(); 
											petitionRefferer.setPetitionId(petitionId);
											petitionRefferer.setPmRefCandidateId(refVO.getRefCandidateId());
											petitionRefferer.setPmRefCandidateDesignationId(pmRefDesignation.getPmRefCandidateDesignationId());
											petitionRefferer.setPmRepresenteeId(pmRepresenteeId);
											petitionRefferer.setPmRepresenteeDesignationId(pmRepresenteeDesignation.getPmRepresenteeDesignationId());
											petitionRefferer.setIsDeleted("N");
											petitionRefferer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
											petitionRefferer.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
											petitionRefferer.setInsertedUserId(pmRequestVO.getUserId());
											petitionRefferer.setUpdatedUserId(pmRequestVO.getUserId());
											petitionRefferer.setOrderNo(orderNo);
											petitionRefferer = pmRepresenteeRefDetailsDAO.save(petitionRefferer);
											
											if(!existingRefIds.contains(refVO.getRefCandidateId())){
												existingRefIds.add(refVO.getRefCandidateId());
												orderNo=orderNo+1L;
												if(!isFilesUploaded){
													isFilesUploaded=true;
													if(petitionRefferer.getPmRepresenteeRefDetailsId() != null && petitionRefferer.getPmRepresenteeRefDetailsId().longValue()>0L && 
															 commonMethodsUtilService.isListOrSetValid(refVO.getFileList())){
														for (MultipartFile file : refVO.getFileList()) {
																Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER,pmRequestVO.getUserId());
																if(petitionWorkDocument != null)
																	savePetitionReffererDocument(petitionRefferer.getPmRepresenteeRefDetailsId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId());
														}
													}
													
													if(petitionRefferer.getPmRepresenteeRefDetailsId() != null && petitionRefferer.getPmRepresenteeRefDetailsId().longValue()>0L && 
															 commonMethodsUtilService.isListOrSetValid(refVO.getFileNamesList())){
														if(refVO.getFileNamesList() != null && refVO.getFileNamesList().size()>0){
															for (KeyValueVO item : refVO.getFileNamesList()) {
																if(item != null && item.getValue() != null){
																	try{
																		List<Long> documentsIsList=documentDAO.getdocumentByFilePath(item.getValue().substring(34));
																		if(commonMethodsUtilService.isListOrSetValid(documentsIsList)){
																			Long documentId=0L;
																			for (Long docId : documentsIsList) {
																				if(docId != null && docId.longValue()>0L)
																					documentId = docId;break;
																			}
																			if(documentId != null)
																				savePetitionReffererDocument(petitionRefferer.getPmRepresenteeRefDetailsId(),documentId,pmRequestVO.getUserId());
																		}
																	}catch (Exception e) {}
																}
															}
														}
													}
												}
											}
										}
									}else{// no refferer
										PmRepresenteeRefDetails petitionRefferer = new PmRepresenteeRefDetails(); 
										petitionRefferer.setPetitionId(petitionId);
										petitionRefferer.setPmRepresenteeId(pmRepresenteeId);
										petitionRefferer.setPmRepresenteeDesignationId(pmRepresenteeDesignation.getPmRepresenteeDesignationId());
										petitionRefferer.setIsDeleted("N");
										petitionRefferer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
										petitionRefferer.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
										petitionRefferer.setInsertedUserId(pmRequestVO.getUserId());
										petitionRefferer.setUpdatedUserId(pmRequestVO.getUserId());
										petitionRefferer.setOrderNo(orderNo+1L);
										petitionRefferer = pmRepresenteeRefDetailsDAO.save(petitionRefferer);
										
										if(!isFilesUploaded){
											isFilesUploaded=true;
											if(petitionRefferer.getPmRepresenteeRefDetailsId() != null && petitionRefferer.getPmRepresenteeRefDetailsId().longValue()>0L && 
													 commonMethodsUtilService.isListOrSetValid(refVO.getFileList())){
												for (MultipartFile file : refVO.getFileList()) {
														Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER,pmRequestVO.getUserId());
														if(petitionWorkDocument != null )
															savePetitionReffererDocument(petitionRefferer.getPmRepresenteeRefDetailsId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId());
												}
											}
											if(petitionRefferer.getPmRepresenteeRefDetailsId() != null && petitionRefferer.getPmRepresenteeRefDetailsId().longValue()>0L && 
													 commonMethodsUtilService.isListOrSetValid(refVO.getFileNamesList())){
												if(refVO.getFileNamesList() != null && refVO.getFileNamesList().size()>0){
													for (KeyValueVO item : refVO.getFileNamesList()) {
														if(item != null && item.getValue() != null){
															try{
																List<Long> documentsIsList=documentDAO.getdocumentByFilePath(item.getValue().substring(34));
																if(commonMethodsUtilService.isListOrSetValid(documentsIsList)){
																	Long documentId=0L;
																	for (Long docId : documentsIsList) {
																		if(docId != null && docId.longValue()>0L)
																			documentId = docId;break;
																	}
																	if(documentId != null)
																		savePetitionReffererDocument(petitionRefferer.getPmRepresenteeRefDetailsId(),documentId,pmRequestVO.getUserId());
																}
															}catch (Exception e) {}
														}
													}
												}
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
				petitionReffererDocument.setInsertedUserId(userId);
				petitionReffererDocument.setUpdatedUserId(userId);
				petitionReffererDocument = pmRepresenteeRefDocumentDAO.save(petitionReffererDocument);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionReffererDocument() "+e.getMessage());
			 petitionReffererDocument = null;
		}
		return petitionReffererDocument;
	}
	
	@SuppressWarnings("static-access")
	public Long savePetitionSubWorkDetails(Long petitonId,PetitionsWorksVO mainDataVO,List<PetitionsWorksVO> subWorksList,int uiBuildSeriesNo,Long userId,String insertionType,String remarks){
		Long noOfWorksCount = 0L;
		try {
			if(commonMethodsUtilService.isListOrSetValid(subWorksList)){
				for (PetitionsWorksVO dataVO : subWorksList) {
					if(dataVO != null && dataVO.getWorkTypeId() != null && dataVO.getWorkTypeId().longValue()>0L){
						PmSubWorkDetails petitionSubWorkLocationDetails = new PmSubWorkDetails();
						petitionSubWorkLocationDetails.setPetitionId(petitonId);						
						petitionSubWorkLocationDetails.setGrievanceDescrption(dataVO.getGrievanceDescription());
						
						if(dataVO.getEstimateCost() != null && !dataVO.getEstimateCost().isEmpty())
							petitionSubWorkLocationDetails.setCostEstimation(Long.valueOf(dataVO.getEstimateCost()));
						
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
							}else if(dataVO.getLocationScopeId().longValue()==6L){
								petitionSubWorkLocationDetails.setLocationValue(Long.valueOf(dataVO.getAddressVO().getPanchayatId().toString().substring(1)));
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
						petitionSubWorkLocationDetails.setInsertedUserId(userId);
						petitionSubWorkLocationDetails.setUpdatedUserId(userId);
						petitionSubWorkLocationDetails = pmSubWorkDetailsDAO.save(petitionSubWorkLocationDetails);
						noOfWorksCount=noOfWorksCount+1;
						
						
						PetitionTrackingVO pmTrackingVO = new PetitionTrackingVO();
						pmTrackingVO.setPmStatusId(1L);
						pmTrackingVO.setUserId(userId);
						pmTrackingVO.setPetitionId(petitonId);
						pmTrackingVO.setRemarks(remarks);
						if(insertionType != null && insertionType.trim().equalsIgnoreCase("new"))
							pmTrackingVO.setPmTrackingActionId(1L);//CREATE A PETITION
						else
							pmTrackingVO.setPmTrackingActionId(5L);//EDIT/UPDATED  PETITION
						pmTrackingVO.setPmSubWorkDetailsId(petitionSubWorkLocationDetails.getPmSubWorkDetailsId());
						updatePetitionTracking(pmTrackingVO);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionSubWorkDetails() "+e.getMessage());
			return null;
		}
		return noOfWorksCount;
	}
	
	@SuppressWarnings("static-access")
	public Petition savePetitionWorkDetails(Long pmRepresenteeId,PmRequestVO pmRequestVO,String insertionType){
		Petition petition = null;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			if(pmRequestVO != null){
				if(commonMethodsUtilService.isListOrSetValid(pmRequestVO.getWorksList())){
					int i=0;
					Long submittedWorksCount=0L;
					//Long noOfWorks=0L;
					for (PetitionsWorksVO dataVO : pmRequestVO.getWorksList()) {
						if(dataVO != null){
							if(i==0){
								petition = new Petition();
								if(pmRequestVO.getExistingPetitionId() != null && pmRequestVO.getExistingPetitionId().longValue() >0L){
									petition = pititionDAO.get(pmRequestVO.getExistingPetitionId());
								}else{
									petition.setEndorsmentDate(null);
									petition.setEndorsmentNo(null);
									petition.setRepresentationDate(format.parse(pmRequestVO.getRepresentationdate()));
									petition.setRepresenteeType(pmRequestVO.getRepresentationType());
									petition.setInsertedTime(dateUtilService.getCurrentDateAndTime());
									petition.setInsertedUserId(pmRequestVO.getUserId());
								}
								
								if(dataVO.getEstimateCost() != null)
									petition.setEstimationCost(dataVO.getEstimateCost());
								petition.setWorkName(dataVO.getWorkName());
								petition.setNoOfWorks(dataVO.getNoOfWorks());
								petition.setIsDeleted("N");
								petition.setIsPreviousPetition(dataVO.getIsPreviousPetition());
								petition.setGrievanceDescription(dataVO.getGrievanceDescription());
								petition.setPmStatusId(1L);//pending endorsment
								petition.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								petition.setUpdatedUserId(pmRequestVO.getUserId());
								petition = pititionDAO.save(petition);
							}
							i=i+1;
							if(petition != null && petition.getPetitionId() != null && petition.getPetitionId().longValue()>0L && commonMethodsUtilService.isListOrSetValid(dataVO.getSubWorksList())){
								Long tempsubmittedWorksCount = savePetitionSubWorkDetails(petition.getPetitionId(),dataVO.getSubWorksList().get(0),dataVO.getSubWorksList(),i,pmRequestVO.getUserId(),insertionType,pmRequestVO.getRemarks());
								if(tempsubmittedWorksCount == null)
									throw new Exception(" Sub works are not saved successfully.");
								submittedWorksCount = submittedWorksCount+tempsubmittedWorksCount;
							}
							
							if(i==1){
								if(dataVO.getFileList() != null && dataVO.getFileList().size()>0){
									for (MultipartFile file : dataVO.getFileList()) {
										Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER,pmRequestVO.getUserId());
										if(petitionWorkDocument != null)
											savePetitionWorkDocument(petition.getPetitionId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId());
									}
								}
								if(pmRequestVO.getFileNamesList() != null && pmRequestVO.getFileNamesList().size()>0){
									for (KeyValueVO item : pmRequestVO.getFileNamesList()) {
										if(item != null && item.getValue() != null){
											try{
												List<Long> documentsIsList=documentDAO.getdocumentByFilePath(item.getValue().substring(34));
												if(commonMethodsUtilService.isListOrSetValid(documentsIsList)){
													Long documentId=0L;
													for (Long docId : documentsIsList) {
														if(docId != null && docId.longValue()>0L)
															documentId = docId;break;
													}
													if(documentId != null)
														savePetitionWorkDocument(petition.getPetitionId(),documentId,pmRequestVO.getUserId());
												}
											}catch (Exception e) {}
										}
									}
								}
							}
						}
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
				petitionWorkDocument.setInsertedUserId(userId);
				petitionWorkDocument.setUpdatedUserId(userId);
				petitionWorkDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				petitionWorkDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
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
				String staticPath = commonMethodsUtilService.createInnerFolders(destinationPath);
				if(staticPath != null && staticPath.equalsIgnoreCase("FAILED"))
					throw new Exception("File path not available . Please check once file path.");
				
				String datePath = commonMethodsUtilService.generateImagePathWithDateTime();
				String fileExtensionStr = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
				if(fileExtensionStr.equalsIgnoreCase(".PNG") || fileExtensionStr.equalsIgnoreCase(".PDF") || fileExtensionStr.equalsIgnoreCase(".JPG") 
						|| fileExtensionStr.equalsIgnoreCase(".JPEG") || fileExtensionStr.equalsIgnoreCase(".BMP") || fileExtensionStr.equalsIgnoreCase(".TIFF") ){
					String fileName = datePath+"_"+Math.abs(new Random().nextInt())+fileExtensionStr;
					//String fileUrl = staticPath.replace(IConstants.STATIC_CONTENT_FOLDER_URL,"")+"/"+fileName;
					String fileUrl = staticPath.replace(IConstants.STATIC_CONTENT_FOLDER_URL,"")+fileName;
					
					byte[] fileData = file.getBytes();
					
					Files.write(fileData,new File(staticPath+fileName));
					
					document = new Document();
					document.setPath(fileUrl);
					document.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					document.setInsertedUserId(userId);
					document = documentDAO.save(document);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ saveDocument() "+e.getMessage());
			document = null;
		}
		return document;
	}
	
	@SuppressWarnings("static-access")
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
			LOG.error("Exception Occured in PmRequestDetailsService @  checkIsExistPmRepresentee "+e.getMessage());
			pmRepresentee = null;
		}
		return pmRepresentee;
	}
	
	public String setDataToAttribute(String existing,String newValue){
		try {
			if(existing == null)
				return newValue;
			else if(newValue != null && !newValue.isEmpty())
				return newValue;
		} catch (Exception e) {
			LOG.error("Exception Occured  in PmRequestDetailsService @  setDataToAttribute "+e.getMessage());
		}
		return existing;
	}
	public PmRepresentee saveRepresenteeDetails(PmRequestVO pmRequestVO,String type){
		PmRepresentee pmRepresentee = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				if(pmRequestVO.getRepresentationType() != null && pmRequestVO.getRepresentationType().equalsIgnoreCase("REPRESENTEE")){
					if(pmRequestVO != null){
						pmRepresentee = checkIsExistPmRepresentee(pmRequestVO.getVoterCardNo(),pmRequestVO.getAdharCardNo(),null);
						if(pmRepresentee==null && type.equalsIgnoreCase("new")){
							pmRepresentee = new PmRepresentee();
							pmRepresentee.setName(pmRequestVO.getName());
							pmRepresentee.setMobileNo(pmRequestVO.getMobileNO());
							pmRepresentee.setEmail(pmRequestVO.getEmail());
							pmRepresentee.setVoterCardNo(pmRequestVO.getVoterCardNo());
							pmRepresentee.setAdharCardNo(pmRequestVO.getAdharCardNo());
							pmRepresentee.setTdpCadreId(pmRequestVO.getTdpCadreId());
							pmRepresentee.setImagePath(pmRequestVO.getRepImagePath());
							pmRepresentee.setIsDeleted("N");
							
							LocationAddress address = saveLocationAddress(pmRequestVO.getAddressVO());
							pmRepresentee.setAddressId(address.getLocationAddressId());
							pmRepresentee.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							pmRepresentee.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							pmRepresentee.setInsertedUserId(pmRequestVO.getUserId());
							pmRepresentee.setUpdatedUserId(pmRequestVO.getUserId());
							pmRepresentee = pmRepresenteeDAO.save(pmRepresentee);
						}else{
							if(pmRequestVO.getRepresenteeId() != null && pmRequestVO.getRepresenteeId().longValue()>0L)
								pmRepresentee = pmRepresenteeDAO.get(pmRequestVO.getRepresenteeId());
							
							if(pmRepresentee.getName() != null && !pmRepresentee.getName().isEmpty())
								pmRepresentee.setName(setDataToAttribute(pmRequestVO.getName(),pmRepresentee.getName()));
							
							pmRepresentee.setMobileNo(setDataToAttribute(pmRequestVO.getMobileNO(),pmRepresentee.getMobileNo()));
							pmRepresentee.setEmail(setDataToAttribute(pmRequestVO.getEmail(),pmRepresentee.getEmail()));
							pmRepresentee.setVoterCardNo(setDataToAttribute(pmRequestVO.getVoterCardNo(),pmRepresentee.getVoterCardNo()));
							pmRepresentee.setAdharCardNo(setDataToAttribute(pmRequestVO.getAdharCardNo(),pmRepresentee.getAdharCardNo()));
							if(pmRequestVO.getTdpCadreId() != null)
								pmRepresentee.setTdpCadreId(Long.valueOf(setDataToAttribute(pmRequestVO.getTdpCadreId().toString(),pmRepresentee.getTdpCadreId().toString())));
							else
								pmRepresentee.setTdpCadreId(pmRequestVO.getTdpCadreId());
							pmRepresentee.setImagePath(setDataToAttribute(pmRequestVO.getRepImagePath(),pmRepresentee.getImagePath()));

							pmRepresentee.setIsDeleted("N");
							pmRepresentee.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							pmRepresentee.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							pmRepresentee.setInsertedUserId(pmRequestVO.getUserId());
							pmRepresentee.setUpdatedUserId(pmRequestVO.getUserId());
							pmRepresentee = pmRepresenteeDAO.save(pmRepresentee);
						}
						
						List<PmRepresenteeDesignation> pmRepresenteeDesignationList = pmRepresenteeDesignationDAO.getPmRepresenteeDesignationByRepresenteeId(pmRepresentee.getPmRepresenteeId());
						List<Long>	 pmRepresenteeDesignationIdsList=new ArrayList<Long>(0);
						if(commonMethodsUtilService.isListOrSetValid(pmRepresenteeDesignationList)){
							for (PmRepresenteeDesignation designation : pmRepresenteeDesignationList) {
								pmRepresenteeDesignationIdsList.add(designation.getPmDesignationId());
							}
							pmRepresenteeDesignationDAO.inActiveExistingDesignationsByIds(pmRepresenteeDesignationIdsList);
						}
						if(pmRequestVO.getRepresenteeDesignationId() == null ||  pmRequestVO.getRepresenteeDesignationId().longValue()==0L){
							pmRequestVO.setRepresenteeDesignationId(75L);// default Other Designation
						}
						//if(!pmRepresenteeDesignationIdsList.contains(pmRequestVO.getRepresenteeDesignationId())){
							if(pmRequestVO.getRepresenteeDesignationId() != null && pmRequestVO.getRepresenteeDesignationId().longValue()>0L && 
									pmRepresentee != null && pmRepresentee.getPmRepresenteeId() != null && pmRepresentee.getPmRepresenteeId().longValue()>0L){
								PmRepresenteeDesignation pmRepresenteeDesignation = new PmRepresenteeDesignation();
								pmRepresenteeDesignation.setPmRepresenteeId(pmRepresentee.getPmRepresenteeId());
								pmRepresenteeDesignation.setPmDesignationId(pmRequestVO.getRepresenteeDesignationId());
								pmRepresenteeDesignation.setIsActive("Y");
								pmRepresenteeDesignation.setIsDeleted("N");
								if(pmRequestVO.getStartDate() != null && pmRequestVO.getEndDate() != null){
									pmRepresenteeDesignation.setStartDate(format.parse(pmRequestVO.getStartDate()));
									pmRepresenteeDesignation.setEndDate(format.parse(pmRequestVO.getEndDate()));
								}
								pmRepresenteeDesignation.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								pmRepresenteeDesignation.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								pmRepresenteeDesignationDAO.save(pmRepresenteeDesignation);
							}
						//}
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
								pmRepresentee.setAddressId(pmRefCandidate.getNativeAddressId());// here we are mantaining candidate native address
								pmRepresentee.setInsertedTime(dateUtilService.getCurrentDateAndTime());
								pmRepresentee.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								pmRepresentee.setInsertedUserId(pmRequestVO.getUserId());
								pmRepresentee.setUpdatedUserId(pmRequestVO.getUserId());
								pmRepresentee.setImagePath(pmRequestVO.getRepImagePath());
								pmRepresentee = pmRepresenteeDAO.save(pmRepresentee);
							}
						}
						
						if(pmRepresentee != null){
							List<PmRepresenteeDesignation> pmRepresenteeDesignationList = pmRepresenteeDesignationDAO.getPmRepresenteeDesignationByRepresenteeId(pmRepresentee.getPmRepresenteeId());
							List<Long>	 pmRepresenteeDesignationIdsList=new ArrayList<Long>(0);
							if(commonMethodsUtilService.isListOrSetValid(pmRepresenteeDesignationList)){
								for (PmRepresenteeDesignation designation : pmRepresenteeDesignationList) {
									pmRepresenteeDesignationIdsList.add(designation.getPmDesignationId());
								}
							}
							
							List<PmRefCandidateDesignation> pmRefCandidateList = pmRefCandidateDesignationDAO.getPmRepresenteeDesignationByPmRefCandidateId(pmRequestVO.getRefCandidateId());
							if(commonMethodsUtilService.isListOrSetValid(pmRefCandidateList)){
								for (PmRefCandidateDesignation pmRefCandDesignation : pmRefCandidateList) {
									if(!pmRepresenteeDesignationIdsList.contains(pmRefCandDesignation.getPmDesginationId())){
										PmRepresenteeDesignation pmRepresenteeDesignation = new PmRepresenteeDesignation();
										pmRepresenteeDesignation.setPmRepresenteeId(pmRepresentee.getPmRepresenteeId());
										pmRepresenteeDesignation.setPmDesignationId(pmRefCandDesignation.getPmDesginationId());
										pmRepresenteeDesignation.setIsActive("Y");
										pmRepresenteeDesignation.setIsDeleted("N");
										if(pmRequestVO.getStartDate() != null && pmRequestVO.getEndDate() != null){
											pmRepresenteeDesignation.setStartDate(format.parse(pmRequestVO.getStartDate()));
											pmRepresenteeDesignation.setEndDate(format.parse(pmRequestVO.getEndDate()));
										}
										pmRepresenteeDesignation.setInsertedTime(dateUtilService.getCurrentDateAndTime());
										pmRepresenteeDesignation.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
										pmRepresenteeDesignationDAO.save(pmRepresenteeDesignation);
									}
								}
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
	    	
	    	List<Long> referralCanIds = null;
	    	if(dataVo.getReprType() != null && dataVo.getReprType().equalsIgnoreCase("referralView")){
	    		referralCanIds = pmRepresenteeRefDetailsDAO.getPmReferralCandidateIdsByDesigIds(dataVo.getDesignationIds());
	    	}
	    	//  0 petitonrefCndidateId ,1 name,2 designation ,3 stateId,4 stateName
	    	// 5 districtId 6 district name 7 constincuyId,8 constincyName, 9 tehsilId,10 teshilName
	    	// 11 panchayId,12 panchaytname,13 mobilNo,14 emailId,14 desigantionId
	    	List<Object[]> referalObjs = pmRefCandidateDesignationDAO.getCandidatseDetailsByDesignationAndLocation(dataVo.getDeptId(),dataVo.getLocationLevelId(),dataVo.getLocationValue(),referralCanIds,dataVo.getDesignationIds());
	    	if(referalObjs != null && referalObjs.size() > 0){
	    		for( Object[] param : referalObjs ){
	    			RepresentationRequestVO mainV0 = new RepresentationRequestVO();
	    			mainV0.setReferrerCandidateId(commonMethodsUtilService.getLongValueForObject(param[0]));
	    			PetitionMemberVO petitionMemberVO = new PetitionMemberVO();
	    			petitionMemberVO.setId(commonMethodsUtilService.getLongValueForObject(param[15]));//desigantionId
	    			petitionMemberVO.setName(commonMethodsUtilService.getCapitalStringValueForObject(param[1]));
	    			petitionMemberVO.setMemberType(commonMethodsUtilService.getStringValueForObject(param[2]));//designation
	    			petitionMemberVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[13]));
	    			petitionMemberVO.setEmailId(commonMethodsUtilService.getStringValueForObject(param[14]));
	    			petitionMemberVO.setImagePath("https://www.mytdp.com/images/cadre_images/"+commonMethodsUtilService.getStringValueForObject(param[18]));
	    			petitionMemberVO.setPartyName(commonMethodsUtilService.getStringValueForObject(param[19]));
	    			mainV0.setPetitionMemberVO(petitionMemberVO);
	    			
	    			AddressVO addressVO= new AddressVO();
	    			addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[3]));
	    			addressVO.setStateName(commonMethodsUtilService.getCapitalStringValueForObject(param[4]));
	    			addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[5]));
	    			addressVO.setDistrictName(commonMethodsUtilService.getCapitalStringValueForObject(param[6]));
	    			addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[7]));
	    			addressVO.setAssemblyName(commonMethodsUtilService.getCapitalStringValueForObject(param[8]));
	    			addressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[9]));
	    			addressVO.setTehsilName(commonMethodsUtilService.getCapitalStringValueForObject(param[10]));
	    			addressVO.setLocalElectionBodyId(commonMethodsUtilService.getLongValueForObject(param[32]));
	    			addressVO.setLocalElectionBodyName(commonMethodsUtilService.getCapitalStringValueForObject(param[33])+" "+commonMethodsUtilService.getCapitalStringValueForObject(param[34]));
	    			addressVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[11]));
	    			addressVO.setPanchayatName(commonMethodsUtilService.getCapitalStringValueForObject(param[12]));
	    			
	    			addressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[16]));
	    			addressVO.setParliamentName(commonMethodsUtilService.getCapitalStringValueForObject(param[17]));
	    			
	    			if(dataVo.getLocationLevelId()!= null && dataVo.getLocationLevelId().longValue() ==10L){
	    				addressVO.setAssemblyId(addressVO.getParliamentId());
		    			addressVO.setAssemblyName(addressVO.getParliamentName());
	    			}
	    			mainV0.setCandidateAddressVO(addressVO);
	    			
	    			AddressVO nativeAddressVO= new AddressVO();
	    			nativeAddressVO.setStateId(commonMethodsUtilService.getLongValueForObject(param[20]));
	    			nativeAddressVO.setStateName(commonMethodsUtilService.getCapitalStringValueForObject(param[21]));
	    			nativeAddressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[22]));
	    			nativeAddressVO.setDistrictName(commonMethodsUtilService.getCapitalStringValueForObject(param[23]));
	    			nativeAddressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(param[24]));
	    			nativeAddressVO.setAssemblyName(commonMethodsUtilService.getCapitalStringValueForObject(param[25]));
	    			nativeAddressVO.setTehsilId(commonMethodsUtilService.getLongValueForObject(param[26]));
	    			nativeAddressVO.setTehsilName(commonMethodsUtilService.getCapitalStringValueForObject(param[27]));
	    			nativeAddressVO.setLocalElectionBodyId(commonMethodsUtilService.getLongValueForObject(param[35]));
	    			nativeAddressVO.setLocalElectionBodyName(commonMethodsUtilService.getCapitalStringValueForObject(param[36])+" "+commonMethodsUtilService.getCapitalStringValueForObject(param[37]));
	    			nativeAddressVO.setPanchayatId(commonMethodsUtilService.getLongValueForObject(param[28]));
	    			nativeAddressVO.setPanchayatName(commonMethodsUtilService.getCapitalStringValueForObject(param[29]));
	    			nativeAddressVO.setParliamentId(commonMethodsUtilService.getLongValueForObject(param[30]));
	    			nativeAddressVO.setParliamentName(commonMethodsUtilService.getCapitalStringValueForObject(param[31]));
	    			
	    			mainV0.setCandidateNativeAddressVO(nativeAddressVO);
	    			returnList.add(mainV0);
	    		}
	    	}
	    } catch (Exception e) {
	      LOG.error("Exception Occured in PmRequestDetailsService @  savePetitionMember "+e.getMessage());
	    }
	     return returnList;
	  }
	public List<RepresenteeViewVO> getRepresentativeSearchWiseDetails(InputVO inputVO){
		List<RepresenteeViewVO> finalList = new ArrayList<RepresenteeViewVO>();
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = null;
			Date endDate = null;
			KeyValueVO deptVO = getDeptIdsListBYUserIds(inputVO.getLocationId());
			inputVO.setDeptIdsList(deptVO.getDeptIdsList());
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null && !inputVO.getFromDate().isEmpty() && !inputVO.getToDate().isEmpty()){
				startDate = format.parse(inputVO.getFromDate());
				endDate = format.parse(inputVO.getToDate());
			}
			//List<Long> localElecBodyIds = null;
			if(inputVO.getSearchLevelId() != null && inputVO.getSearchLevelId() ==5l){
				if(inputVO.getSearchLvlVals() != null && inputVO.getSearchLvlVals().size() >0){
					for (Long long1 : inputVO.getSearchLvlVals() ) {
						if(long1.toString().substring(0, 1).equalsIgnoreCase("2")){
							if(inputVO.getLocationIds() == null){
								inputVO.setLocationIds(new ArrayList<Long>());
							}
							inputVO.getLocationIds().add(Long.valueOf(long1.toString().substring(1)));
							//inputVO.getSearchLvlVals().remove(long1);
						}else if(long1.toString().substring(0, 1).equalsIgnoreCase("1")){
							//inputVO.getSearchLvlVals().remove(long1);
							if(inputVO.getLocationValues() == null){
								inputVO.setLocationValues(new ArrayList<Long>());
							}
							inputVO.getLocationValues().add(Long.valueOf(long1.toString().substring(1)));
						}
					}
				}
			}
			List<Object[]> searchData = null;
			Map<Long,RepresenteeViewVO> mapData = new HashMap<Long,RepresenteeViewVO>();
			if(inputVO.getSearchLevelId() != null && inputVO.getSearchLevelId() ==5l){
				if(inputVO.getLocationIds() != null && inputVO.getLocationIds().size() >0){
					searchData = null;
					inputVO.getSearchLvlVals().clear();
					inputVO.getSearchLvlVals().addAll(inputVO.getLocationIds());
					inputVO.setSearchLevelId(7l);
					searchData = pmRepresenteeRefDetailsDAO.getRepresentativeSearchWiseDetails(inputVO,startDate,endDate);
					setSearchDetailsData(searchData,mapData);
				}
				if(inputVO.getLocationValues() != null && inputVO.getLocationValues().size() >0){
					searchData = null;
					inputVO.getSearchLvlVals().clear();
					inputVO.getSearchLvlVals().addAll(inputVO.getLocationValues());
					inputVO.setSearchLevelId(5l);
					searchData = pmRepresenteeRefDetailsDAO.getRepresentativeSearchWiseDetails(inputVO,startDate,endDate);
					setSearchDetailsData(searchData,mapData);
				}
				 
			}else{
				searchData = pmRepresenteeRefDetailsDAO.getRepresentativeSearchWiseDetails(inputVO,startDate,endDate);
				setSearchDetailsData(searchData,mapData);
			}
			
			Long minPending = commonMethodsUtilService.getLongValueForObject(inputVO.getStartValue());
			Long maxPending = commonMethodsUtilService.getLongValueForObject(inputVO.getEndValue());
			
			//Map<Long,RepresenteeViewVO> statusSummeryMap = new HashMap<Long,RepresenteeViewVO>();
			
			for (Map.Entry<Long, RepresenteeViewVO> entry : mapData.entrySet()) {
				if(minPending.longValue() >0l &&  maxPending.longValue() >0l && entry.getValue().getStatusType().equalsIgnoreCase("pending")){
					finalList.add(entry.getValue());
				}else if(minPending.longValue() ==0l || maxPending.longValue() ==0l){
					finalList.add(entry.getValue());
				}
			}
			if(commonMethodsUtilService.isListOrSetValid(finalList)){
				finalList.get(0).getStatusList().addAll(getStatusList(null));
				setStatusSummeryDetails(searchData,finalList.get(0).getStatusList());
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in PmRequestDetailsService @ getRepresentativeSearchWiseDetails() "+e.getMessage());
		}
		return finalList;
	}
	public void setStatusSummeryDetails(List<Object[]> searchData,List<RepresenteeViewVO> list){
		try{
			if(searchData != null && searchData.size()>0){
				for (Object[] param : searchData) {
					//RepresenteeViewVO vo = new RepresenteeViewVO();
					RepresenteeViewVO vo = getMatchVO(list,commonMethodsUtilService.getLongValueForObject(param[10]));
					if(vo != null){
						vo.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
						vo.getSubWorkIds().add(commonMethodsUtilService.getLongValueForObject(param[15]));
					}
					
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in PmRequestDetailsService @ setStatusSummeryDetails() "+e.getMessage());
		}
	}
	
	public void setSearchDetailsData(List<Object[]> searchData,Map<Long,RepresenteeViewVO> mapData){
		try{
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
					vo.setEndorsementNO(commonMethodsUtilService.getStringValueForObject(param[1]));
					if(param[2] != null){
						vo.setEndorsmentDate(commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
					}
					vo.setEstimationCost(commonMethodsUtilService.getStringValueForObject(param[3]));
					vo.setName(commonMethodsUtilService.getCapitalStringValueForObject(param[4]));
					vo.setReferrerName(commonMethodsUtilService.getCapitalStringValueForObject(param[5]));
					vo.setNoOfWorks(commonMethodsUtilService.getLongValueForObject(param[6]));
					 dateUtilService.noOfDayBetweenDates(commonMethodsUtilService.getStringValueForObject(param[8]),commonMethodsUtilService.getStringValueForObject(param[9]));
					commonMethodsUtilService.getLongValueForObject(param[10]);
					vo.setWorkName(commonMethodsUtilService.getStringValueForObject(param[11]));
					//RepresenteeViewVO vo =mapData.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(param[8] != null){
						vo.setRaisedDate(commonMethodsUtilService.getStringValueForObject(param[8]).substring(0, 10));
					}
					/*if(statusId.longValue() != 0l && !statusIds.contains(statusId) && petionPendingDays.longValue()>=minPending.longValue()
							&& petionPendingDays.longValue() <= maxPending.longValue() && vo != null){
						vo.setStatusType("pending");
					}*/
					Long subWorkId = commonMethodsUtilService.getLongValueForObject(param[15]);
					RepresenteeViewVO subWorkVO = getMatchVO(vo.getSubList(), subWorkId);
					if(subWorkVO == null){
						subWorkVO = new RepresenteeViewVO();
						subWorkVO.setId(subWorkId);
						vo.getSubList().add(subWorkVO);
					}
					subWorkVO.setWorkName(commonMethodsUtilService.getStringValueForObject(param[13]));
					subWorkVO.setEstimationCost(commonMethodsUtilService.getLongValueForObject(param[12]).toString());
					subWorkVO.setStatusType(commonMethodsUtilService.getStringValueForObject(param[16]));
					subWorkVO.setEndorsementNO(commonMethodsUtilService.getStringValueForObject(param[14]));
					subWorkVO.setStatusId(commonMethodsUtilService.getLongValueForObject(param[10]));
					if(param[17] != null)
						subWorkVO.setEndorsmentDate(commonMethodsUtilService.getStringValueForObject(param[17]).substring(0, 10));
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in PmRequestDetailsService @ setSearchDetailsData() "+e.getMessage());
		}
	}
	
	
	@SuppressWarnings("static-access")
	public List<RepresenteeViewVO> getStatusList(Long statId){
		List<RepresenteeViewVO> returnList = new ArrayList<RepresenteeViewVO>();
		try {
			
			//List<PmStatus> list = pmStatusDAO.getAll();
			 List<Object[]> statusList = pmStatusDAO.getPmStatusList(statId);
			 Map<Long,KeyValueVO> statusMap = new LinkedHashMap<Long,KeyValueVO>();
			 if(commonMethodsUtilService.isListOrSetValid(statusList)){
				for (Object[] param : statusList) {
					statusMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), new KeyValueVO(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1])));
				} 
			 }
			 
			if(commonMethodsUtilService.isMapValid(statusMap)){
				for (Long statusId : statusMap.keySet()) {
					KeyValueVO vo1 = statusMap.get(statusId);
					if(vo1 != null){
						RepresenteeViewVO vo = new RepresenteeViewVO();
						vo.setId(vo1.getKey());
						vo.setName(vo1.getValue());
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
	public Map<Long,KeyValueVO> getPmPetitionDocumentsByPetition(Long petitionId){
		Map<Long,KeyValueVO> petitionFilesListMap = new HashMap<Long,KeyValueVO>(0);
		try {
			List<Object[]> uploadedPetitionFilesList = pmPetitionDocumentDAO.getPmPetitionDocumentByPetition(petitionId);
			if(commonMethodsUtilService.isListOrSetValid(uploadedPetitionFilesList)){
				for (Object[] param : uploadedPetitionFilesList) {
					Long id = commonMethodsUtilService.getLongValueForObject(param[0]);
					String path = "http://www.mydepartments.in/PRRWS/"+commonMethodsUtilService.getStringValueForObject(param[1]);
					petitionFilesListMap.put(id, new KeyValueVO(id,path));
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getPmPetitionDocumentByPetition @ getPmPetitionDocumentsByPetition "+e.getMessage());
		}
		return petitionFilesListMap;
	}
	
	
	
	public Map<Long,List<KeyValueVO>> getPmRepresenteeRefDocumentsByPetition(Long petitionId){
		Map<Long,List<KeyValueVO>> refFilesListMap = new HashMap<Long,List<KeyValueVO>>(0);
		try {
			List<Object[]> uploadedWorksFilesList = pmRepresenteeRefDocumentDAO.getPmRepresenteeRefDocumentByPetition(petitionId);
			if(commonMethodsUtilService.isListOrSetValid(uploadedWorksFilesList)){
				for (Object[] param : uploadedWorksFilesList) {
					Long id = commonMethodsUtilService.getLongValueForObject(param[0]);
					String path = "http://www.mydepartments.in/PRRWS/"+commonMethodsUtilService.getStringValueForObject(param[1]);
					List<KeyValueVO> fileList = new ArrayList<KeyValueVO>();
					if(refFilesListMap.get(id) != null){
						fileList = refFilesListMap.get(id);
					}
					fileList.add(new KeyValueVO(id,path));
					refFilesListMap.put(id,fileList);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ getPmRepresenteeRefDocumentsByPetition "+e.getMessage());
		}
		return refFilesListMap;
	}
	
	public PmRequestEditVO getPetitionBasicDetails(Long petitionId,String pageType,Map<Long,KeyValueVO> petitionFilesListMap, Map<Long,List<KeyValueVO>> refFilesListMap ){
		PmRequestEditVO returnVO = null;
		try {
			List<Object[]> pmRepresenteePetitionsDetils = pmRepresenteeRefDetailsDAO.getPmRepresenteRefDetails(petitionId);
			if(commonMethodsUtilService.isListOrSetValid(pmRepresenteePetitionsDetils)){
					returnVO = new PmRequestEditVO();
					int i=0;
				for (Object[] param : pmRepresenteePetitionsDetils) {
					
					if(i == 0){
						 returnVO.setPetitionId(petitionId);
						 returnVO.setNoOfWorks(commonMethodsUtilService.getLongValueForObject(param[27]));
						 returnVO.setEstimateCost("");
						 if(!commonMethodsUtilService.getStringValueForObject(param[26]).isEmpty()){
							 returnVO.setEstimateCost(String.valueOf(Long.valueOf(commonMethodsUtilService.getStringValueForObject(param[26]))));
							 if(Long.valueOf(commonMethodsUtilService.getStringValueForObject(param[26]))>0L)
								 returnVO.setEstimateCost(commonMethodsUtilService.calculateAmountInWords(Long.valueOf(commonMethodsUtilService.getStringValueForObject(param[26]))));
						 }
						 //returnVO.setEndorsmentNo(commonMethodsUtilService.getStringValueForObject(param[22]));
						 returnVO.setEndorsmentNo("");
						 if(commonMethodsUtilService.getStringValueForObject(param[24]).length()>10)
							 returnVO.setEndorsmentDate(commonMethodsUtilService.getStringValueForObject(param[24]).substring(0, 10));
						 else
							 returnVO.setEndorsmentDate(commonMethodsUtilService.getStringValueForObject(param[24]));
						 if(commonMethodsUtilService.getStringValueForObject(param[23]).length()>10)
							 returnVO.setRepresentationdate(commonMethodsUtilService.getStringValueForObject(param[23]).substring(0, 10));
						 else 
							 returnVO.setRepresentationdate(commonMethodsUtilService.getStringValueForObject(param[23]));
						 
						 returnVO.setWorkName(commonMethodsUtilService.getStringValueForObject(param[25]));
						 returnVO.setGrievanceDescription(commonMethodsUtilService.getStringValueForObject(param[25]));
						 returnVO.setRepresentationType(commonMethodsUtilService.getStringValueForObject(param[50]));
						 
						 if(commonMethodsUtilService.isMapValid(petitionFilesListMap)){
							 returnVO.getFileList().addAll(petitionFilesListMap.values());
						 }
						 
						 if(returnVO.getRepresentationType().equalsIgnoreCase("REPRESENTEE")){
							 PmRequestVO  representeeVO = new PmRequestVO();
							 representeeVO.setRepresenteeId(commonMethodsUtilService.getLongValueForObject(param[30]));					
							 representeeVO.setName(commonMethodsUtilService.getCapitalStringValueForObject(param[0]));
							 representeeVO.setMobileNO(commonMethodsUtilService.getStringValueForObject(param[1]));
							 representeeVO.setEmail(commonMethodsUtilService.getStringValueForObject(param[2]));
							 representeeVO.setVoterCardNo(commonMethodsUtilService.getStringValueForObject(param[3]));
							 representeeVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[33]));
							 representeeVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[34]));
							 representeeVO.setRefCandidateId(commonMethodsUtilService.getLongValueForObject(param[31]));
							 representeeVO.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[51]));
							 if(representeeVO.getTdpCadreId() != null && representeeVO.getTdpCadreId().longValue()>0L)
								 representeeVO.setCandidatePath(commonMethodsUtilService.getStringValueForObject(param[69]));
							 
							 AddressVO addressVO = setAddressDetailsToResultView(pageType,param[4],param[5],param[6],param[7],param[8],param[63]);
							 
							 addressVO.setStateName(commonMethodsUtilService.getCapitalStringValueForObject(param[37]));
							 addressVO.setDistrictName(commonMethodsUtilService.getCapitalStringValueForObject(param[38]));
							 addressVO.setAssemblyName(commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getCapitalStringValueForObject(param[39])));
							 if(commonMethodsUtilService.getLongValueForObject(param[8]) >0L)// muncipality
								 addressVO.setTehsilName(commonMethodsUtilService.getCapitalStringValueForObject(param[41])+" "+commonMethodsUtilService.getCapitalStringValueForObject(param[42]));
							 else{
								 addressVO.setTehsilName(commonMethodsUtilService.getCapitalStringValueForObject(param[40]));
								 addressVO.setPanchayatName(commonMethodsUtilService.getCapitalStringValueForObject(param[64]));
							 }
							 if(!commonMethodsUtilService.isListOrSetValid(addressVO.getPanchaytsList())){
								 addressVO.getPanchaytsList().add(new KeyValueVO(addressVO.getPanchayatId(), addressVO.getPanchayatName()));
							 }
							 if(!commonMethodsUtilService.isListOrSetValid(addressVO.getMandalsList())){
								 addressVO.getMandalsList().add(new KeyValueVO(addressVO.getTehsilId(), addressVO.getTehsilName()));
							 }
							 if(!commonMethodsUtilService.isListOrSetValid(addressVO.getConstituencyList())){
								 addressVO.getConstituencyList().add(new KeyValueVO(addressVO.getAssemblyId(), addressVO.getAssemblyName()));
							 }
							 
							 representeeVO.setAddressVO(addressVO);
							 returnVO.getRepresenteeDetailsList().add(representeeVO);
						 }
						 i=i+1;
					}
					
					 PmRequestVO refVO = new PmRequestVO();
					 refVO.setId(commonMethodsUtilService.getLongValueForObject(param[32]));// for documents this refer id required
					 refVO.setRefCandidateId(commonMethodsUtilService.getLongValueForObject(param[9]));
					 refVO.setName(commonMethodsUtilService.getCapitalStringValueForObject(param[10]));
					 refVO.setMobileNO(commonMethodsUtilService.getStringValueForObject(param[11]));
					 refVO.setEmail(commonMethodsUtilService.getStringValueForObject(param[12]));
					 refVO.setPartyId(commonMethodsUtilService.getLongValueForObject(param[13]));
					 refVO.setPartyName(commonMethodsUtilService.getStringValueForObject(param[14]));
					 //"https://www.mytdp.com/images/cadre_images/"+commonMethodsUtilService.getStringValueForObject(param[18])
					 refVO.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[49]));
					 if(refVO.getTdpCadreId() != null && refVO.getTdpCadreId().longValue()>0L)
						 refVO.setCandidatePath("https://www.mytdp.com/images/cadre_images/"+commonMethodsUtilService.getStringValueForObject(param[15]));
					 else
						 refVO.setCandidatePath(commonMethodsUtilService.getStringValueForObject(param[15]));
					 
					 refVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[35]));
					 refVO.setDesignation(commonMethodsUtilService.getStringValueForObject(param[36]));
					 
					 AddressVO natAddressVO = setAddressDetailsToResultView(pageType,param[58],param[59],param[60],param[61],param[62],param[67]);
					 natAddressVO.setStateName(commonMethodsUtilService.getCapitalStringValueForObject(param[52]));
					 natAddressVO.setDistrictName(commonMethodsUtilService.getCapitalStringValueForObject(param[53]));
					 natAddressVO.setAssemblyName(commonMethodsUtilService.getCapitalStringValueForObject(commonMethodsUtilService.getCapitalStringValueForObject(param[54])));
					 if(commonMethodsUtilService.getLongValueForObject(param[62]) >0L)// muncipality
						 natAddressVO.setTehsilName(commonMethodsUtilService.getCapitalStringValueForObject(param[56])+" "+commonMethodsUtilService.getCapitalStringValueForObject(param[57]));
					 else{
						 natAddressVO.setTehsilName(commonMethodsUtilService.getCapitalStringValueForObject(param[55]));
						 natAddressVO.setPanchayatName(commonMethodsUtilService.getCapitalStringValueForObject(param[68]));
					 }
					 
					 if(!commonMethodsUtilService.isListOrSetValid(natAddressVO.getPanchaytsList())){
						 natAddressVO.getPanchaytsList().add(new KeyValueVO(natAddressVO.getPanchayatId(), natAddressVO.getPanchayatName()));
					 }
					 if(!commonMethodsUtilService.isListOrSetValid(natAddressVO.getMandalsList())){
						 natAddressVO.getMandalsList().add(new KeyValueVO(natAddressVO.getTehsilId(), natAddressVO.getTehsilName()));
					 }
					 if(!commonMethodsUtilService.isListOrSetValid(natAddressVO.getConstituencyList())){
						 natAddressVO.getConstituencyList().add(new KeyValueVO(natAddressVO.getAssemblyId(), natAddressVO.getAssemblyName()));
					 }
					 refVO.setCandidateNativeAddressVO(natAddressVO);
					 
					 AddressVO refAddressVO = setAddressDetailsToResultView(pageType,param[16],param[17],param[18],param[19],param[20],param[65]);
					 refAddressVO.setStateName(commonMethodsUtilService.getCapitalStringValueForObject(param[43]));
					 refAddressVO.setDistrictName(commonMethodsUtilService.getCapitalStringValueForObject(param[44]));
					 refAddressVO.setAssemblyName(commonMethodsUtilService.getCapitalStringValueForObject(commonMethodsUtilService.getCapitalStringValueForObject(param[45])));
					 if(commonMethodsUtilService.getLongValueForObject(param[8]) >0L)// muncipality
						 refAddressVO.setTehsilName(commonMethodsUtilService.getCapitalStringValueForObject(param[47])+" "+commonMethodsUtilService.getCapitalStringValueForObject(param[48]));
					 else{
						 refAddressVO.setTehsilName(commonMethodsUtilService.getCapitalStringValueForObject(param[46]));
						 refAddressVO.setPanchayatName(commonMethodsUtilService.getCapitalStringValueForObject(param[66]));
					 }
					 
					 if(!commonMethodsUtilService.isListOrSetValid(refAddressVO.getPanchaytsList())){
						 refAddressVO.getPanchaytsList().add(new KeyValueVO(refAddressVO.getPanchayatId(), refAddressVO.getPanchayatName()));
					 }
					 if(!commonMethodsUtilService.isListOrSetValid(refAddressVO.getMandalsList())){
						 refAddressVO.getMandalsList().add(new KeyValueVO(refAddressVO.getTehsilId(), refAddressVO.getTehsilName()));
					 }
					 if(!commonMethodsUtilService.isListOrSetValid(refAddressVO.getConstituencyList())){
						 refAddressVO.getConstituencyList().add(new KeyValueVO(refAddressVO.getAssemblyId(), refAddressVO.getAssemblyName()));
					 }
					 refVO.setCandidateAddressVO(refAddressVO);
					 
					 if(commonMethodsUtilService.isMapValid(refFilesListMap) && refFilesListMap.get(refVO.getId()) != null && commonMethodsUtilService.isListOrSetValid(refFilesListMap.get(refVO.getId()))){
						refVO.getFileNamesList().addAll(refFilesListMap.get(refVO.getId()));
					 }
					 
					 if(!commonMethodsUtilService.isListOrSetValid(returnVO.getReferDetailsList())){
						 returnVO.getReferDetailsList().add(refVO);
					 }else{
						 boolean isAvailable=false;
						 for (PmRequestVO childReffVO : returnVO.getReferDetailsList()) {
							 if(childReffVO != null){
								 if(childReffVO.getRefCandidateId() != null && refVO.getRefCandidateId() != null && childReffVO.getRefCandidateId().longValue() == refVO.getRefCandidateId().longValue()){
									 String[] designationArr = childReffVO.getDesignation().split(",");
									 boolean isDesignationAdded=false;
									 if(designationArr != null && designationArr.length>0){
										 for (String designatinStr : designationArr) {
											if(refVO.getDesignation().trim().equalsIgnoreCase(designatinStr.trim()) )
												isDesignationAdded = true;
										}
										 if(!isDesignationAdded)
											 childReffVO.setDesignation(childReffVO.getDesignation()+", "+refVO.getDesignation());
									 }
									
									if(commonMethodsUtilService.isMapValid(refFilesListMap)){
										if(commonMethodsUtilService.isListOrSetValid(refFilesListMap.get(refVO.getId())))
											childReffVO.getFileNamesList().addAll(refFilesListMap.get(refVO.getId()));
									 }
									isAvailable=true;
								}
							 }
						}
						 if(!isAvailable){
							 returnVO.getReferDetailsList().add(refVO);
						 }
					 }
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ getPetitionBasicDetails "+e.getMessage());
		}
		return returnVO;
	}
	
	public Map<String,List<PetitionsWorksVO>> getPetitionsSubWorksDetails(Long petitionId,String pageType){
		Map<String,List<PetitionsWorksVO>> petitionSubWorksMap = new LinkedHashMap<String,List<PetitionsWorksVO>>(0);
		try {
			List<Object[]> subWorksList = pmSubWorkDetailsDAO.getPetitionSubWorksDetails(petitionId);
			if(commonMethodsUtilService.isListOrSetValid(subWorksList)){
				for (Object[] param : subWorksList) {
					PetitionsWorksVO vo = new PetitionsWorksVO();
					vo.setPetitionId(commonMethodsUtilService.getLongValueForObject(param[0]));
					vo.setWorkId(commonMethodsUtilService.getLongValueForObject(param[17]));
					vo.setEndorsmentNo(commonMethodsUtilService.getStringValueForObject(param[38]));
					
					if(commonMethodsUtilService.getStringValueForObject(param[39]).length()>10)
						vo.setEndorsmentDate(commonMethodsUtilService.getStringValueForObject(param[39]).substring(0, 10));
					 else
						 vo.setEndorsmentDate(commonMethodsUtilService.getStringValueForObject(param[39]));
					
					vo.setWorkName(commonMethodsUtilService.getStringValueForObject(param[2]));
					vo.setWorkTypeId(commonMethodsUtilService.getLongValueForObject(param[11]));
					vo.setGrievanceDescription(commonMethodsUtilService.getStringValueForObject(param[2]));
					vo.setSubjectId(commonMethodsUtilService.getLongValueForObject(param[4]));
					vo.setSubSubjectId(commonMethodsUtilService.getLongValueForObject(param[5]));
					vo.setLeadId(commonMethodsUtilService.getLongValueForObject(param[6]));
					vo.setBriefLeadId(commonMethodsUtilService.getLongValueForObject(param[7]));
					vo.setDeptId(commonMethodsUtilService.getLongValueForObject(param[10]));
					vo.seteOfficeId(commonMethodsUtilService.getStringValueForObject(param[3]));
					if(param[1] != null){
						vo.setEstimateCost(String.valueOf(new Double(commonMethodsUtilService.getDoubleValueForObject(param[1])).longValue()));
						if(!commonMethodsUtilService.getStringValueForObject(param[1]).isEmpty() && Long.valueOf(commonMethodsUtilService.getStringValueForObject(param[1]))>0L)
							 vo.setEstimateCost(commonMethodsUtilService.calculateAmountInWords(Long.valueOf(commonMethodsUtilService.getStringValueForObject(param[1]))));
					}else
						vo.setEstimateCost("");
					vo.setLocationScopeId(commonMethodsUtilService.getLongValueForObject(param[19]));
					vo.setLocationValue(commonMethodsUtilService.getLongValueForObject(param[20]));
					vo.setGrantId(commonMethodsUtilService.getLongValueForObject(param[8]));
					
					vo.setWorkType(commonMethodsUtilService.getCapitalStringValueForObject(param[32]));
					vo.setDeptName(commonMethodsUtilService.getCapitalStringValueForObject(param[31]));
					vo.setSubject(commonMethodsUtilService.getCapitalStringValueForObject(param[33]));
					vo.setSubSubject(commonMethodsUtilService.getCapitalStringValueForObject(param[34]));
					vo.setLeadName(commonMethodsUtilService.getCapitalStringValueForObject(param[27]));
					vo.setBriefLeadName(commonMethodsUtilService.getCapitalStringValueForObject(param[28]));
					vo.setGrantName(commonMethodsUtilService.getCapitalStringValueForObject(param[29]));
					vo.setStatus(commonMethodsUtilService.getCapitalStringValueForObject(param[30]));
					vo.setStatusId(commonMethodsUtilService.getLongValueForObject(param[9]));
					
					 AddressVO refAddressVO = setAddressDetailsToResultView(pageType,param[12],param[13],param[14],param[15],param[16],param[36]); 
					 refAddressVO.setStateName(commonMethodsUtilService.getCapitalStringValueForObject(param[21]));
					 refAddressVO.setDistrictName(commonMethodsUtilService.getCapitalStringValueForObject(param[22]));
					 refAddressVO.setAssemblyName(commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getCapitalStringValueForObject(param[23])));
					 if(commonMethodsUtilService.getLongValueForObject(param[16]) >0L)// muncipality
						 refAddressVO.setTehsilName(commonMethodsUtilService.getCapitalStringValueForObject(param[25])+" "+commonMethodsUtilService.getCapitalStringValueForObject(param[26]));
					 else{
						 refAddressVO.setTehsilName(commonMethodsUtilService.getCapitalStringValueForObject(param[24]));
						 refAddressVO.setPanchayatName(commonMethodsUtilService.getCapitalStringValueForObject(param[37]));
					 }
					 
					 if(!commonMethodsUtilService.isListOrSetValid(refAddressVO.getPanchaytsList())){
						 refAddressVO.getPanchaytsList().add(new KeyValueVO(refAddressVO.getPanchayatId(), refAddressVO.getPanchayatName()));
					 }
					 if(!commonMethodsUtilService.isListOrSetValid(refAddressVO.getMandalsList())){
						 refAddressVO.getMandalsList().add(new KeyValueVO(refAddressVO.getTehsilId(), refAddressVO.getTehsilName()));
					 }
					 if(!commonMethodsUtilService.isListOrSetValid(refAddressVO.getConstituencyList())){
						 refAddressVO.getConstituencyList().add(new KeyValueVO(refAddressVO.getAssemblyId(), refAddressVO.getAssemblyName()));
					 }
					 vo.setAddressVO(refAddressVO);
					 
					 String seriesNo = commonMethodsUtilService.getStringValueForObject(param[35]);
					 if(pageType == null){
						 seriesNo = (vo.getEndorsmentNo() == null || vo.getEndorsmentNo().isEmpty())?"0":vo.getEndorsmentNo();
					 }
					 List<PetitionsWorksVO> worksList = new LinkedList<PetitionsWorksVO>();
					 if(petitionSubWorksMap.get(seriesNo) != null){
						 worksList=petitionSubWorksMap.get(seriesNo);
					 }else  if(pageType != null){
						 if(vo.getDeptId() != null && vo.getDeptId().longValue()>0L){
								List<KeyValueVO> subjectsList = null;//locationDetailsService.getPmSubjectList(vo.getDeptId());
								if(commonMethodsUtilService.isListOrSetValid(subjectsList)){
									vo.getSubjectsList().addAll(subjectsList);
								}else{
									vo.getSubjectsList().add(new KeyValueVO(vo.getSubjectId(),vo.getSubject()));
								}
							}
							if(vo.getSubjectId() != null && vo.getSubjectId().longValue()>0L){
								List<KeyValueVO> subSubjectsList = null;//locationDetailsService.getPmSubSubjectList(vo.getSubjectId());
								if(commonMethodsUtilService.isListOrSetValid(subSubjectsList)){
									vo.getSubSubjectsList().addAll(subSubjectsList);
								}else{
									vo.getSubSubjectsList().add(new KeyValueVO(vo.getSubSubjectId(),vo.getSubSubject()));
								}
							}
					 }
					 worksList.add(vo);
					 petitionSubWorksMap.put(seriesNo, worksList);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ getPetitionsSubWorksDetails "+e.getMessage());
		}
		return petitionSubWorksMap;
	}
	
	
	@SuppressWarnings("static-access")
	public  Map<Long,List<PetitionFileVO>> getPetitionsRequiredFilesMap(Long petitionId){
		 Map<Long,List<PetitionFileVO>> petitionRequiredFilesMap = new HashMap<Long,List<PetitionFileVO>>(0);
		try {
			List<Object[]> requiredDocumenmtsList = pmSubWorkCoveringLetterDAO.getSubWorkWiseRequiredDocumentsDetailsByPetitionId(petitionId);
			if(commonMethodsUtilService.isListOrSetValid(requiredDocumenmtsList)){
				Map<Long,Map<String,PetitionFileVO>> filesMap = new HashMap<Long,Map<String,PetitionFileVO>>();
				for (Object[] param : requiredDocumenmtsList) {
					Long subWorkId = commonMethodsUtilService.getLongValueForObject(param[0]);
					String reportType = commonMethodsUtilService.getStringValueForObject(param[1]);
					String path = "http://www.mydepartments.in/PRRWS/"+commonMethodsUtilService.getStringValueForObject(param[2]);
					Long docId = commonMethodsUtilService.getLongValueForObject(param[3]);
					if(subWorkId == null || subWorkId.longValue()==0L)
						subWorkId = 0L;//petition wise all documents
					
					Map<String,PetitionFileVO> fileTypeMap = new HashMap<String,PetitionFileVO>(0);
					PetitionFileVO fileTypeVO = new PetitionFileVO();
					
					if(filesMap.get(subWorkId) != null){
						fileTypeMap = filesMap.get(subWorkId);
						if(fileTypeMap.get(reportType) != null){
							fileTypeVO = fileTypeMap.get(reportType);
						}
					}
					
					fileTypeVO.setKey(reportType);
					fileTypeVO.getFilesList().add(new PetitionFileVO(docId,path));
					fileTypeMap.put(reportType, fileTypeVO);
					filesMap.put(subWorkId, fileTypeMap);
				}
				
				if(commonMethodsUtilService.isMapValid(filesMap)){
					for (Long workId : filesMap.keySet()) {
						if(filesMap.get(workId) != null){
							Map<String,PetitionFileVO> fileTypeMap = filesMap.get(workId);
							if(commonMethodsUtilService.isMapValid(fileTypeMap)){
								List<PetitionFileVO> filesList = new ArrayList<PetitionFileVO>(0);
								if(petitionRequiredFilesMap.get(workId) != null){
									filesList = petitionRequiredFilesMap.get(workId);
								}
								for (String reportTypeStr : fileTypeMap.keySet()) {
									if(fileTypeMap.get(reportTypeStr) != null)
										filesList.add(fileTypeMap.get(reportTypeStr));
								}
								petitionRequiredFilesMap.put(workId, filesList);
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ getPetitionsRequiredFilesMap "+e.getMessage());
		}
		return petitionRequiredFilesMap;
	}
	
	
	 @SuppressWarnings("static-access")
	public PmRequestEditVO setPmRepresenteeDataToResultView(Long petitionId,String pageType,Long userId){
		 PmRequestEditVO returnVO = null;
		 try {
			 if(pageType != null && pageType.equalsIgnoreCase("viewPage")){
				 pageType = null;
			 }
			 Map<Long,KeyValueVO> petitionFilesListMap = getPmPetitionDocumentsByPetition(petitionId);
			 Map<Long,List<KeyValueVO>> refFilesListMap =getPmRepresenteeRefDocumentsByPetition(petitionId);
			 PmRequestEditVO petitionVO = getPetitionBasicDetails(petitionId,pageType,petitionFilesListMap,refFilesListMap);
			 Map<String,List<PetitionsWorksVO>> petitionSubWorksMap = getPetitionsSubWorksDetails(petitionId,pageType);
			 Map<Long,List<PetitionFileVO>> petitionRequiredFilesMap =  getPetitionsRequiredFilesMap(petitionId);
			 
			// Covering letter /Detailed report/action copy /all petition wise documents
			 if(commonMethodsUtilService.isMapValid(petitionRequiredFilesMap) && petitionRequiredFilesMap.get(0L) != null)
				 petitionVO.getReportTypeFilesList().addAll(petitionRequiredFilesMap.get(0L));
			 
			 List<Object[]> statusList = pmStatusDAO.getPmStatusList(null);
			 Map<Long,KeyValueVO> statusMap = new LinkedHashMap<Long,KeyValueVO>();
			 if(commonMethodsUtilService.isListOrSetValid(statusList)){
				for (Object[] param : statusList) {
					statusMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), new KeyValueVO(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1])));
				} 
			 }
			 
			 if(pageType == null){
				//user access status details
				KeyValueVO userAccessStatusVO = getPmDeptStatusIdsByUserIdsLst(userId);
				if(userAccessStatusVO != null && commonMethodsUtilService.isListOrSetValid(userAccessStatusVO.getDeptIdsList())){
					KeyValueVO pendingVO = statusMap.get(1L);//pending endorsment 
					if(pendingVO != null){
						for (Long statusId : statusMap.keySet()) {
							if(userAccessStatusVO.getDeptIdsList().contains(statusId))
								pendingVO.getSubList().add(new KeyValueVO(statusId, statusMap.get(statusId).getValue()));
						}
					}
				}
			 }
			
			 Map<Long,KeyValueVO> departmentsMap = new TreeMap<Long,KeyValueVO>();
				if(commonMethodsUtilService.isMapValid(petitionSubWorksMap)){
					for (String seriesNo : petitionSubWorksMap.keySet()) {
						List<PetitionsWorksVO> workTypeVOList = petitionSubWorksMap.get(seriesNo);
						if(commonMethodsUtilService.isListOrSetValid(workTypeVOList)){
							for (PetitionsWorksVO worksVO : workTypeVOList) {
								
								List<PetitionFileVO> filesList = petitionRequiredFilesMap.get(worksVO.getWorkId());
									if(commonMethodsUtilService.isListOrSetValid(filesList)){
										worksVO.getReportTypeFilesList().addAll(filesList);
									}
								
								if(departmentsMap.get(worksVO.getDeptId()) == null){
									departmentsMap.put(worksVO.getDeptId(), new KeyValueVO(worksVO.getDeptId(), worksVO.getDeptName()));
								}
								KeyValueVO deptVO=departmentsMap.get(worksVO.getDeptId());
								deptVO.setCount(deptVO.getCount()+1L);
								if(!worksVO.getEndorsmentNo().isEmpty())
									deptVO.setTotalCount(deptVO.getTotalCount()+1L);
								
								if(statusMap.get(worksVO.getStatusId()) == null){
									statusMap.put(worksVO.getStatusId(), new KeyValueVO(worksVO.getStatusId(), worksVO.getStatus()));
								}
								KeyValueVO statusVO=statusMap.get(worksVO.getStatusId());
								statusVO.setCount(statusVO.getCount()+1L);
							}
						}
						
						if(commonMethodsUtilService.isListOrSetValid(workTypeVOList)){
							PetitionsWorksVO worksVO= workTypeVOList.get(0);
							if(commonMethodsUtilService.isListOrSetValid(worksVO.getSubWorksList())){
								PetitionsWorksVO subWorksVO= worksVO.getSubWorksList().get(0);
								if(subWorksVO != null){
									for (PetitionsWorksVO vo : worksVO.getSubWorksList()) {
										if(commonMethodsUtilService.isListOrSetValid(worksVO.getSubjectsList())){
											subWorksVO.setSubjectsList(vo.getSubjectsList());
											subWorksVO.setSubSubjectsList(vo.getSubSubjectsList());
											break;
										}
									}
								}
							}
							
							PetitionsWorksVO vo = new PetitionsWorksVO();
							vo.setEndorsmentNo(worksVO.getEndorsmentNo());
							vo.setEndorsmentDate(worksVO.getEndorsmentDate());
							vo.setNoOfWorks(Long.valueOf(String.valueOf(workTypeVOList.size())));
							
							if(pageType != null && !seriesNo.isEmpty())
								vo.setUiSeriesNo(Long.valueOf(seriesNo));
							else
								vo.setEndorsmentNo(seriesNo);
							
							vo.setDeptId(worksVO.getDeptId());
							vo.setDeptName(worksVO.getDeptName());
							vo.setSubjectId(worksVO.getSubjectId());
							vo.setSubject(worksVO.getSubject());
							vo.setSubSubjectId(worksVO.getSubSubjectId());
							vo.setSubSubject(worksVO.getSubSubject());
							vo.getSubWorksList().addAll(workTypeVOList);
							petitionVO.getSubWorksList().add(vo);
						}
					}
				}
				
				/*String endorsmentNo ="";
				if(petitionVO != null && petitionVO.getEndorsmentNo() != null && !petitionVO.getEndorsmentNo().isEmpty())
					endorsmentNo=petitionVO.getEndorsmentNo();
				
				if(endorsmentNo != null && !endorsmentNo.isEmpty()){
					List<String> coveringLetterDetails = pmSubWorkCoveringLetterDAO.getCoveringLetterDetailsByEndorsmentNo(endorsmentNo);
					if(commonMethodsUtilService.isListOrSetValid(coveringLetterDetails)){
						List<KeyValueVO> coveringLetterPathsList = new ArrayList<KeyValueVO>(0);
						for (String letterPath : coveringLetterDetails) {
							coveringLetterPathsList.add(new KeyValueVO(0L,"http://www.mydepartments.in/PRRWS/"+letterPath));
						}
						petitionVO.getCoveringLetterPathsList().addAll(coveringLetterPathsList);
					}
				}
				*/
			if(petitionVO != null){
				if(commonMethodsUtilService.isMapValid(statusMap)){
					petitionVO.getStatusList().addAll(statusMap.values());
				}
				if(commonMethodsUtilService.isMapValid(departmentsMap)){
					petitionVO.getDeptList().addAll(departmentsMap.values());
				}
				
				return petitionVO;
			}
		} catch (Exception e) {
			 LOG.error("Exception Occured in PmRequestDetailsService @ setPmRepresenteeDataToResultView "+e.getMessage());
		}
		 return returnVO;
	 }
	 @SuppressWarnings("static-access")
	public AddressVO setAddressDetailsToResultView(String pageType, Object state,Object district,Object assembly,Object mandal,Object muncipality,Object panchayat){
		 AddressVO addressVO = new AddressVO();
		 try {
			 addressVO.setStateId(commonMethodsUtilService.getLongValueForObject(state));
			 addressVO.setDistrictId(commonMethodsUtilService.getLongValueForObject(district));
			 addressVO.setAssemblyId(commonMethodsUtilService.getLongValueForObject(assembly));
			 if(commonMethodsUtilService.getLongValueForObject(muncipality) >0L){// muncipality{
				 addressVO.setTehsilId(Long.valueOf("1"+commonMethodsUtilService.getLongValueForObject(muncipality)));
		 	}else{
				 addressVO.setTehsilId(Long.valueOf("2"+commonMethodsUtilService.getLongValueForObject(mandal)));
				 addressVO.setPanchayatId(Long.valueOf("2"+commonMethodsUtilService.getLongValueForObject(panchayat)));
				 if(addressVO.getTehsilId() != null && addressVO.getTehsilId().longValue()>0L){
					 if(pageType != null){
						 List<KeyValueVO> panchaytsList = null;//locationDetailsService.getPanchayatsByTehsilId(commonMethodsUtilService.getLongValueForObject(mandal));//starting letter 2 for panchayats, 1 for wards
						 if(commonMethodsUtilService.isListOrSetValid(panchaytsList)){
							 addressVO.getPanchaytsList().addAll(panchaytsList);
						 } 
					 }
				 }
		 	}
			 if(pageType != null){
				 List<LocationVO> constituencyList = null;//locationDetailsService.getConstituencyNamesByDistrictId(addressVO.getDistrictId(),"all",null);
				 if(commonMethodsUtilService.isListOrSetValid(constituencyList)){
					 for (LocationVO vo : constituencyList) {
						 addressVO.getConstituencyList().add(new KeyValueVO(vo.getLocationId(), vo.getLocationName()));
					}
				 }
				 List<KeyValueVO> mandalsList = null;//locationDetailsService.getTehsilsAndLocalElectionBodyForConstituencyId(addressVO.getAssemblyId(),"all",null);
				 if(commonMethodsUtilService.isListOrSetValid(mandalsList)){
						 addressVO.getMandalsList().addAll(mandalsList);
				 }
			 }
			 
		} catch (Exception e) {
			LOG.error("Exception Occured in setAddressDetailsToResultView "+e.getMessage());
			addressVO = null;
		}
		 return addressVO;
	 }
		public UserVO getPmOffceUserDetails(Long userId, UserVO userVO){
			try{
				//UserVO userVO = null;
				// 0-deptId, 1-deptName,2-designtionIdm,3-designtionName,4-officerId,5-name,6-mobileNo
				List<Object[]> list = pmOfficerUserDAO.getPmOffceUserDetails(userId);
				if(commonMethodsUtilService.isListOrSetValid(list)){
					Object[] userObj = list.get(0);
					if(userObj != null && userObj.length > 0){
						userVO.setDeptName(commonMethodsUtilService.getCapitalStringValueForObject(userObj[1]));
						userVO.setDesignation(commonMethodsUtilService.getStringValueForObject(userObj[3]));
						userVO.setUserName(commonMethodsUtilService.getCapitalStringValueForObject(userObj[5]));
						userVO.setPhoneNo(commonMethodsUtilService.getStringValueForObject(userObj[6]));
					}
				}
				
				
			}catch(Exception e){
				LOG.error("Exception Occured in getPmOffceUserDetails ");
			}
			return userVO;
		}
		public CadreRegistrationVO getRegistrationPersonDetails(Map<String,String> inputMap){
			CadreRegistrationVO cadrInfoVo=null;
			try{
				//String inputStr=convertingStaticInputVOToString(voterId,familyVoterId,tdpCadreId,status); https://mytdp.com/login.action
				  //WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://localhost:8080/PartyAnalyst/WebService/getRegistrationPersonDetails");
				WebResource webResource = commonMethodsUtilService.getWebResourceObject("http://www.mytdp.com/WebService/getRegistrationPersonDetails");
					String authStringEnc = commonMethodsUtilService.getAuthenticationString("itgrids","Itgrids@123");	        
					ClientResponse response = webResource.accept("application/json").type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class,inputMap);
					 if(response.getStatus() != 200){
			 	    	  throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			 	      }else{
			 	    	 String output = response.getEntity(String.class);
			 	    		JSONObject jobj = new JSONObject(output);
			 	    		cadrInfoVo = new CadreRegistrationVO();
			 	    		cadrInfoVo.setLastName(jobj.getString("lastName"));
			 	    		cadrInfoVo.setName(jobj.getString("name"));
			 	    		cadrInfoVo.setId(jobj.getString("id").matches("-?\\d+(\\.\\d+)?") ? jobj.getLong("id"):0L);
			 	    		cadrInfoVo.setConstituencyId(jobj.getString("constituencyId"));
			 	    		cadrInfoVo.setStateId(jobj.getString("stateId").matches("-?\\d+(\\.\\d+)?") ? jobj.getLong("stateId"):0L);
			 	    		cadrInfoVo.setDistrictId(jobj.getString("districtId").matches("-?\\d+(\\.\\d+)?") ? jobj.getLong("districtId"):0L);
			 	    		cadrInfoVo.setGender(jobj.getString("gender"));
			 	    		cadrInfoVo.setEmail(jobj.getString("email"));
			 	    		cadrInfoVo.setPincode(jobj.getString("pincode").matches("-?\\d+(\\.\\d+)?") ? jobj.getLong("pincode"):0L);
			 	    		cadrInfoVo.setHouseNo(jobj.getString("houseNo"));
			 	    		cadrInfoVo.setAge(jobj.getString("age").matches("-?\\d+(\\.\\d+)?") ? jobj.getLong("age"):0L);
			 	    		cadrInfoVo.setMandalId(jobj.getString("mandalId").matches("-?\\d+(\\.\\d+)?") ? jobj.getLong("mandalId"):0L);
			 	    		cadrInfoVo.setMobileNumber(jobj.getString("mobileNumber"));
			 	    		cadrInfoVo.setLocalElectionBodyId(jobj.getString("localElectionBodyId").matches("-?\\d+(\\.\\d+)?") ? jobj.getLong("localElectionBodyId"):0L);
			 	    		cadrInfoVo.setCasteId(jobj.getString("casteId").matches("-?\\d+(\\.\\d+)?") ? jobj.getLong("casteId"):0L);
			 	    		cadrInfoVo.setUserAddressId(jobj.getString("userAddressId").matches("-?\\d+(\\.\\d+)?") ? jobj.getLong("userAddressId"):0L);
			 	    		cadrInfoVo.setMembershipNo(jobj.getString("membershipNo"));
			 	    		cadrInfoVo.setVoterCardNo(jobj.getString("voterCardNo"));
			 	    		cadrInfoVo.setDobStr(jobj.getString("dobStr"));
			 	    		cadrInfoVo.setTdpCadreId(jobj.getString("tdpCadreId").matches("-?\\d+(\\.\\d+)?") ? jobj.getLong("tdpCadreId"):0L);
			 	    		cadrInfoVo.setOccupationId(jobj.getString("occupationId").matches("-?\\d+(\\.\\d+)?") ? jobj.getLong("occupationId"):0L);
			 	    		cadrInfoVo.setImagePath(jobj.getString("imagePath"));
			 	    		cadrInfoVo.setVillageId(jobj.getString("villageId").matches("-?\\d+(\\.\\d+)?") ? jobj.getLong("villageId"):0L);
			 	    		cadrInfoVo.setEducationId(jobj.getString("educationId").matches("-?\\d+(\\.\\d+)?") ? jobj.getLong("educationId"):0L);
			 	    		cadrInfoVo.setMemberTypeId(jobj.getString("memberTypeId"));
			 	    		cadrInfoVo.setVoterCardNumber(jobj.getString("voterCardNumber"));
			 	    		cadrInfoVo.setImageBase64String(jobj.getString("imageBase64String"));
			 	    	}
			 	      
			}catch(Exception e){
				LOG.error("Exception Occured in getRegistrationPersonDetails ");
			}
			return cadrInfoVo;
		}
		
		@SuppressWarnings("static-access")
		public RepresenteeViewVO getCompleteOrStatusOverviewDetails(Long userId,String startDate,String endDate){
			RepresenteeViewVO returnVO = new RepresenteeViewVO();
			try {
				KeyValueVO deptVO = getDeptIdsListBYUserIds(userId);
				List<Long> deptIds = deptVO.getDeptIdsList();
				KeyValueVO userAccesStatusVO = getPmDeptStatusIdsByUserIdsLst(userId);
				List<Long> statusIds = userAccesStatusVO.getDeptIdsList();
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				Date fromDate = null;
				Date toDate = null;
				
				if(startDate != null && endDate != null && !startDate.isEmpty() && !endDate.isEmpty()){
					fromDate = format.parse(startDate);
					toDate = format.parse(endDate);
				}
				Map<Long,RepresenteeViewVO> statuMap = null;
				List<PmStatus> statusList = pmStatusDAO.getAll();
				if(commonMethodsUtilService.isListOrSetValid(statusList)){
					statuMap = new LinkedHashMap<Long,RepresenteeViewVO>();
					
					for (PmStatus pmStatus : statusList) {
						//if(pmStatus.getIsDeleted().equalsIgnoreCase("N") && pmStatus.getPmStatusId() != 2l){
							RepresenteeViewVO	statusVO = new RepresenteeViewVO();
							if(statusIds != null && statusIds.size() >0 && statusIds.contains(pmStatus.getPmStatusId())){
								statusVO.setStatusType("UserStatus");
							}
							statusVO.setId(pmStatus.getPmStatusId());
							statusVO.setName(pmStatus.getStatus());
							statuMap.put(statusVO.getId(), statusVO);
						//}
					}
				}
				List<Object[]> objectList = pmSubWorkDetailsDAO.getCompleteOrStatusOverviewDetails(deptIds, fromDate, toDate,"");
				if(commonMethodsUtilService.isListOrSetValid(objectList)){
					
					for (Object[] param : objectList) {
						RepresenteeViewVO statusVO = statuMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
						if(statusVO == null){
							statusVO = new RepresenteeViewVO();
							statusVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
							statusVO.setName(commonMethodsUtilService.getCapitalStringValueForObject(param[3]));
							statuMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), statusVO);
						}
						statusVO.setTotalRepresents(statusVO.getTotalRepresents().longValue()+1l);
						statusVO.setNoOfWorks(statusVO.getNoOfWorks().longValue()+commonMethodsUtilService.getLongValueForObject(param[0]));
						returnVO.setTotalRepresents(returnVO.getTotalRepresents().longValue()+1l);
						returnVO.setNoOfWorks(returnVO.getNoOfWorks().longValue()+commonMethodsUtilService.getLongValueForObject(param[0]));
					}
				}
				
				List<Object[]> referrerList = pmSubWorkDetailsDAO.getCompleteOrStatusOverviewDetails(deptIds, fromDate, toDate,"statusReferral");
				setObjectListToMap(referrerList,statuMap, "statusReferral");
				setObjectListForCompleteOverview(referrerList,returnVO, "statusReferral");
				List<Object[]> subjectList = pmSubWorkDetailsDAO.getCompleteOrStatusOverviewDetails(deptIds, fromDate, toDate,"statusSubject");
				setObjectListToMap(subjectList,statuMap, "statusSubject");
				setObjectListForCompleteOverview(subjectList,returnVO, "statusSubject");
				List<Object[]> deptlist = pmSubWorkDetailsDAO.getCompleteOrStatusOverviewDetails(deptIds, fromDate, toDate,"statusDept");
				setObjectListToMap(deptlist,statuMap, "statusDept");
				setObjectListForCompleteOverview(deptlist,returnVO, "statusDept");
				
				if(commonMethodsUtilService.isMapValid(statuMap)){
					for (Map.Entry<Long, RepresenteeViewVO> entry : statuMap.entrySet()) {
						setOthersDataToLastIndexOfList(entry.getValue().getReferrerList());
						setOthersDataToLastIndexOfList(entry.getValue().getSubList());
						setOthersDataToLastIndexOfList(entry.getValue().getDeptList());
						returnVO.getStatusList().add(entry.getValue());
					}
					
				}
				
				List<Long> inProgressStatusIds = IConstants.PETITION_IN_PROGRESS_IDS;
				RepresenteeViewVO inProgressVO = statuMap.get(2l);
				
				Map<Long,RepresenteeViewVO> inprogreeReferMap = new HashMap<Long,RepresenteeViewVO>();
				Map<Long,RepresenteeViewVO> inprogreeSubjMap = new HashMap<Long,RepresenteeViewVO>();
				Map<Long,RepresenteeViewVO> inprogreeDeptMap = new HashMap<Long,RepresenteeViewVO>();
				if(commonMethodsUtilService.isMapValid(statuMap)){
					for(Map.Entry<Long,RepresenteeViewVO> entry :statuMap.entrySet()){
						if(inProgressStatusIds.contains(entry.getKey()) && inProgressVO != null ){
							inProgressVO.setNoOfWorks(inProgressVO.getNoOfWorks()+entry.getValue().getNoOfWorks());
							inProgressVO.setTotalRepresents(inProgressVO.getTotalRepresents()+entry.getValue().getTotalRepresents());
							if(entry.getValue().getReferrerList() != null && entry.getValue().getReferrerList().size() >0){
								for (RepresenteeViewVO refferVO : entry.getValue().getReferrerList()) {
									RepresenteeViewVO inprogressrefMap = inprogreeReferMap.get(refferVO.getId());
									if(inprogressrefMap == null){
										inprogressrefMap = new RepresenteeViewVO();
										inprogressrefMap.setId(refferVO.getId());
										inprogressrefMap.setName(refferVO.getName());
										inprogreeReferMap.put(inprogressrefMap.getId(), inprogressrefMap);
									}
									inprogressrefMap.setNoOfWorks(inprogressrefMap.getNoOfWorks()+refferVO.getNoOfWorks());
									inprogressrefMap.setTotalRepresents(inprogressrefMap.getTotalRepresents()+refferVO.getTotalRepresents());
								}
							}
							if(entry.getValue().getSubList() != null && entry.getValue().getSubList().size() >0){
								for (RepresenteeViewVO subjVO : entry.getValue().getSubList()) {
									RepresenteeViewVO inprogressSubMapVO = inprogreeSubjMap.get(subjVO.getId());
									if(inprogressSubMapVO == null){
										inprogressSubMapVO = new RepresenteeViewVO();
										inprogressSubMapVO.setId(subjVO.getId());
										inprogressSubMapVO.setName(subjVO.getName());
										inprogreeSubjMap.put(inprogressSubMapVO.getId(), inprogressSubMapVO);
									}
									inprogressSubMapVO.setNoOfWorks(inprogressSubMapVO.getNoOfWorks()+subjVO.getNoOfWorks());
									inprogressSubMapVO.setTotalRepresents(inprogressSubMapVO.getTotalRepresents()+subjVO.getTotalRepresents());
								}
							}
							if(entry.getValue().getReferrerList() != null && entry.getValue().getReferrerList().size() >0){
								for (RepresenteeViewVO deptVO1 : entry.getValue().getReferrerList()) {
									RepresenteeViewVO inprogressDeptMapVO = inprogreeDeptMap.get(deptVO1.getId());
									if(inprogressDeptMapVO == null){
										inprogressDeptMapVO = new RepresenteeViewVO();
										inprogressDeptMapVO.setId(deptVO1.getId());
										inprogressDeptMapVO.setName(deptVO1.getName());
										inprogreeDeptMap.put(inprogressDeptMapVO.getId(), inprogressDeptMapVO);
									}
									inprogressDeptMapVO.setNoOfWorks(inprogressDeptMapVO.getNoOfWorks()+deptVO1.getNoOfWorks());
									inprogressDeptMapVO.setTotalRepresents(inprogressDeptMapVO.getTotalRepresents()+deptVO1.getTotalRepresents());
								}
							}
						}else if(entry.getKey().longValue() != 2l){
							returnVO.getList().add(entry.getValue());
							//setOthersDataToLastIndexOfList(entry.getValue().getReferrerList());
							//setOthersDataToLastIndexOfList(entry.getValue().getSubList());
							//setOthersDataToLastIndexOfList(entry.getValue().getDeptList());
						}
					}
				}
				
				if(commonMethodsUtilService.isMapValid(inprogreeReferMap)){
					inProgressVO.getReferrerList().addAll(inprogreeReferMap.values());
					//setOthersDataToLastIndexOfList(inProgressVO.getReferrerList());
				}
				if(commonMethodsUtilService.isMapValid(inprogreeSubjMap)){
					inProgressVO.getSubList().addAll(inprogreeSubjMap.values());
					//setOthersDataToLastIndexOfList(inProgressVO.getSubList());
				}
				if(commonMethodsUtilService.isMapValid(inprogreeDeptMap)){
					inProgressVO.getDeptList().addAll(inprogreeDeptMap.values());
					//setOthersDataToLastIndexOfList(inProgressVO.getDeptList());
				}
				returnVO.getList().add(inProgressVO);
				setOthersDataToLastIndexOfList(returnVO.getReferrerList());
				setOthersDataToLastIndexOfList(returnVO.getSubList());
				setOthersDataToLastIndexOfList(returnVO.getDeptList());
				
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception Occured in getCompleteOrStatusOverviewDetails ");
			}
			return returnVO;
		}
		
		public void setOthersDataToLastIndexOfList(List<RepresenteeViewVO> list) {
			try {
				if(commonMethodsUtilService.isListOrSetValid(list)){
					RepresenteeViewVO firstIndexVO = list.get(0);
					list.add(list.size(), firstIndexVO);
					list.remove(0);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception Occured in setOthersDataToLastIndexOfList ");
			}
		}
		@SuppressWarnings("static-access")
		public void setObjectListForCompleteOverview(List<Object[]> objectList,RepresenteeViewVO returnVO,String type){
			try {
				
				if(commonMethodsUtilService.isListOrSetValid(objectList)){
					for (Object[] param : objectList) {
						RepresenteeViewVO VO  = null;
						Long id = commonMethodsUtilService.getLongValueForObject(param[4]);
						String name = commonMethodsUtilService.getStringValueForObject(param[5]);
						if(type != null && type.equalsIgnoreCase("statusReferral")){
							if(id.longValue() != 7l && id.longValue() != 4l && id.longValue() != 2l && id.longValue() != 1l ){
								id = 0l; 
								name="OTHERS";
							}
							 VO = getMatchVO(returnVO.getReferrerList(),id);
							 if(VO == null){
								 VO = new  RepresenteeViewVO();
								 returnVO.getReferrerList().add(VO);
							 }
						}else if(type != null && type.equalsIgnoreCase("statusDept")){
							if(id.longValue() != 34l && id.longValue() != 27l && id.longValue() != 22l && id.longValue() != 5l ){
								id = 0l;
								name="OTHERS";
							}
							 VO = getMatchVO(returnVO.getDeptList(),id);
							
							 if(VO == null){
								 VO = new  RepresenteeViewVO();
								 returnVO.getDeptList().add(VO);
							 }
						}else if(type != null && type.equalsIgnoreCase("statusSubject")){
							if(id.longValue() != 188l && id.longValue() != 20l && id.longValue() != 67l && id.longValue() != 439l && id.longValue() != 447l ){
								id = 0l; 
								name="OTHERS";
							}
							 VO = getMatchVO(returnVO.getSubList(),id);
							
							 if(VO == null){
								 VO = new  RepresenteeViewVO();
									 returnVO.getSubList().add(VO);
							 }
						}
						 VO.setId(id);
						 VO.setName(name);
						VO.setTotalRepresents(VO.getTotalRepresents().longValue()+1l);
						VO.setNoOfWorks(VO.getNoOfWorks().longValue()+commonMethodsUtilService.getLongValueForObject(param[0]));
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception Occured in setObjectListToMap ");
			}
		}
		@SuppressWarnings("static-access")
		public void setObjectListToMap(List<Object[]> objectList,Map<Long,RepresenteeViewVO> statuMap,String type){
			try {
				
				if(commonMethodsUtilService.isListOrSetValid(objectList) && commonMethodsUtilService.isMapValid(statuMap)){
					//statuMap = new HashMap<Long,RepresenteeViewVO>();
					for (Object[] param : objectList) {
						RepresenteeViewVO statusVO = statuMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
						if(statusVO == null){
							statusVO = new RepresenteeViewVO();
							statuMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), statusVO);
						}
						RepresenteeViewVO VO  = null;
						Long id = commonMethodsUtilService.getLongValueForObject(param[4]);
						String name = commonMethodsUtilService.getStringValueForObject(param[5]);
						if(type != null && type.equalsIgnoreCase("statusReferral")){
							
							if(id.longValue() != 7l && id.longValue() != 4l && id.longValue() != 2l && id.longValue() != 1l ){
								id = 0l; 
								name="OTHERS";
							}
							 VO = getMatchVO(statusVO.getReferrerList(),id);
							 if(VO == null){
								 VO = new  RepresenteeViewVO();
									 statusVO.getReferrerList().add(VO);
							}
						}else if(type != null && type.equalsIgnoreCase("statusDept")){
							
							if(id.longValue() != 34l && id.longValue() != 27l && id.longValue() != 22l && id.longValue() != 5l ){
								id = 0l;
								name="OTHERS";
							}
							 VO = getMatchVO(statusVO.getDeptList(),id);
							
							 if(VO == null){
								 VO = new  RepresenteeViewVO();
								 statusVO.getDeptList().add(VO);
							 }
						}else if(type != null && type.equalsIgnoreCase("statusSubject")){
							if(id.longValue() != 188l && id.longValue() != 20l && id.longValue() != 67l && id.longValue() != 439l && id.longValue() != 447l ){
								id = 0l; 
								name="OTHERS";
							}
							 VO = getMatchVO(statusVO.getSubList(),id);
							
							 if(VO == null){
								 VO = new  RepresenteeViewVO();
								 statusVO.getSubList().add(VO);
							}
						}
						VO.setId(id);
						VO.setName(name);
						VO.setTotalRepresents(VO.getTotalRepresents().longValue()+1l);
						VO.setNoOfWorks(VO.getNoOfWorks().longValue()+commonMethodsUtilService.getLongValueForObject(param[0]));
						
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception Occured in setObjectListToMap ");
			}
		}
		private RepresenteeViewVO getMatchVO(List<RepresenteeViewVO> subList,Long id) {
			 try {
				  if (subList == null || subList.size() == 0) {
					  return null;
				  }
				  for (RepresenteeViewVO VO : subList) {
					if (VO.getId().longValue() == id.longValue()) {
						return VO;
					}
				}
			} catch (Exception e) {
				 LOG.error("Exception raised at getMatchVO - PmRequestDetailsService service",e);
			 }
			 return null;
		}
		public KeyValueVO getDeptIdsListBYUserIds(Long userId){
			KeyValueVO deptVO = new KeyValueVO();
			try{
				LOG.info("entered into PmRequestDetailsService  of getDeptIdsListBYUserIds");
				List<Object[]> deptIdsObjLst = pmOfficerUserDAO.getPmDeptIdByUserId(userId);
				if(deptIdsObjLst != null && deptIdsObjLst.size() >0){
					for (Object[] objects : deptIdsObjLst) {
						deptVO.getDeptIdsList().add(commonMethodsUtilService.getLongValueForObject(objects[0]));
						deptVO.setDesignation(commonMethodsUtilService.getStringValueForObject(objects[2]));
						deptVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(objects[1]));
					}
				}
			}catch(Exception e){
				LOG.error("Exception raised into PmRequestDetailsService of getDeptIdsListBYUserIds() ",e);
			}
			return deptVO;
		}
		
		public KeyValueVO getPmDeptStatusIdsByUserIdsLst(Long userId){
			KeyValueVO deptVO = new KeyValueVO();
			try{
				LOG.info("entered into PmRequestDetailsService  of getPmDeptStatusIdsByUserIds");
				List<Long> deptIdsObjLst = pmOfficerUserDAO.getPmDeptStatusIdByUserIdsLst(userId);
				if(deptIdsObjLst != null && deptIdsObjLst.size() >0){
					deptVO.setDeptIdsList(deptIdsObjLst);
				}
			}catch(Exception e){
				LOG.error("Exception raised into PmRequestDetailsService of getPmDeptStatusIdsByUserIds() ",e);
			}
			return deptVO;
		}
		
		public List<RepresenteeViewVO> getLeadWiseOverviewDetails(Long userId,String startDate,String endDate){
			List<RepresenteeViewVO> leadList = null;
			try {
				KeyValueVO deptVO = getDeptIdsListBYUserIds(userId);
				List<Long> deptIds = deptVO.getDeptIdsList();
				//KeyValueVO userAccesStatusVO = getPmDeptStatusIdsByUserIdsLst(userId);
				//List<Long> statusIds = userAccesStatusVO.getDeptIdsList();
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				Date fromDate = null;
				Date toDate = null;
				
				if(startDate != null && endDate != null && !startDate.isEmpty() && !endDate.isEmpty()){
					fromDate = format.parse(startDate);
					toDate = format.parse(endDate);
				}
				List<Long> statusIds = Arrays.asList(1l,2l);
				Map<Long,RepresenteeViewVO> leadMap = null;
				List<Object[]> leadObjects = pmSubWorkDetailsDAO.getLeadWiseOverviewDetails(deptIds, fromDate, toDate);
				if(commonMethodsUtilService.isListOrSetValid(leadObjects)){
					leadMap = new HashMap<Long,RepresenteeViewVO>();
					for (Object[] param : leadObjects) {
						RepresenteeViewVO leadVO = leadMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
						if(leadVO == null){
							leadVO = new RepresenteeViewVO();
							leadVO.getStatusList().addAll(setLeadStatusTemplate(statusIds));
							if(commonMethodsUtilService.getLongValueForObject(param[1])>0l)
							leadMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), leadVO);
						}
						leadVO.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
						leadVO.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
						leadVO.setNoOfWorks(leadVO.getNoOfWorks()+commonMethodsUtilService.getLongValueForObject(param[0]));
						leadVO.setTotalRepresents(leadVO.getTotalRepresents()+1l);
						if(IConstants.PETITION_COMPLETED_IDS.contains(param[3])){
							RepresenteeViewVO completedVO = getMatchVO(leadVO.getStatusList(), 2l);
							if(completedVO != null){
								completedVO.setName("Completed");
								completedVO.setNoOfWorks(completedVO.getNoOfWorks()+commonMethodsUtilService.getLongValueForObject(param[0]));
								completedVO.setTotalRepresents(completedVO.getTotalRepresents()+1l);
							}
						}else if(IConstants.PETITION_IN_PROGRESS_IDS.contains(param[3])){
							RepresenteeViewVO inprogressVO = getMatchVO(leadVO.getStatusList(), 1l);
							if(inprogressVO != null){
								inprogressVO.setName("Pending");
								inprogressVO.setNoOfWorks(inprogressVO.getNoOfWorks()+commonMethodsUtilService.getLongValueForObject(param[0]));
								inprogressVO.setTotalRepresents(inprogressVO.getTotalRepresents()+1l);
							}
						}
					}
				}
				
				if(commonMethodsUtilService.isMapValid(leadMap)){
					leadList = new ArrayList();
					leadList.addAll(leadMap.values());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised into PmRequestDetailsService of getLeadWiseOverviewDetails() ",e);
			}
			return leadList;
		}
		
		public List<RepresenteeViewVO> setLeadStatusTemplate(List<Long> statusIds){
			List<RepresenteeViewVO> list = new ArrayList<RepresenteeViewVO>();
			try {
				if(commonMethodsUtilService.isListOrSetValid(statusIds)){
					for (Long long1 : statusIds) {
						RepresenteeViewVO statusVO = new RepresenteeViewVO();
						statusVO.setId(long1);
						statusVO.setWorkName("Work");
						list.add(statusVO);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised into PmRequestDetailsService of setLeadStatusTemplate() ",e);
			}
			return list;
		}
		
		public String genarateEndorsementNo(String endrsementNo,String userType,List<String> depts,Date date){
			String outputStr="";
			if(endrsementNo != null && endrsementNo.trim().length() >0 && userType !=null && userType.trim().length() >0 && depts != null && depts.size() >0 && date !=null ){
				String yearStr="";
				String dateStr="";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal =Calendar.getInstance();
					cal.setTime(date);
					Integer year=cal.get(Calendar.YEAR);
					yearStr=year.toString();
				 dateStr=	sdf.format(date);
				 
				String deptStr = commonMethodsUtilService.convertStringFromListWithOutDuplicates(depts);
				outputStr=endrsementNo+"/"+userType+"("+deptStr+")/"+yearStr+"/"+dateStr;
			}
			return "Endt NO. "+outputStr;
		}
		public ResultStatus updatePetitionsStatusDetails(Long userId,List<Long> petitionIdsList, List<Long> subWorkIdsList,String remark,Long statusId){
			return null;
		}
		
		public ResultStatus generateCoveringLetterForPetition(InputVO inputVO){
			ResultStatus resultStatus = new ResultStatus();
			try {
				List<String> deptIds = null;
				if(commonMethodsUtilService.isListOrSetValid(inputVO.getDeptIdsList())){
					deptIds = new ArrayList<String>();
					for (Long deptId : inputVO.getDeptIdsList()) {
						deptIds.add(deptId.toString());
					}
				}
				PmBriefLead briefLead = pmBriefLeadDAO.get(Long.valueOf(inputVO.getLeadName()));
				inputVO.setLeadName(briefLead.getBriefLead());
				
				PmGrant pmGrant = pmGrantDAO.get(Long.valueOf(inputVO.getGroupName()));
				inputVO.setGroupName(pmGrant.getPmGrantName());//grantType
				List<Object[]> coveringLetrImages = pmRequiredLettersImagesDAO.getDesignationWiseImages(inputVO.getDesignationIds(), inputVO.getType());
				
				inputVO.setAssetTypeList(deptIds);//deptIds
				String endorseStr = genarateEndorsementNo(inputVO.getEndValue(),inputVO.getDisplayType(),inputVO.getAssetTypeList(),dateUtilService.getCurrentDateAndTime());
				inputVO.setCategory(endorseStr);
				String filePath = ITextCoveringLetterGeneration.generateCOVERINGLETTER(inputVO,coveringLetrImages);
				resultStatus.setExceptionMsg(filePath);
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised into PmRequestDetailsService of generateCoveringLetterForPetition() ",e);
			}
			return resultStatus;
		}
		
		public Long uploadSpecialFilesforPetition(Long userId,Long pmSubWorkDetailsId, String endorsmentNo, Long documentId,String reportType){
			PmSubWorkCoveringLetter pmSubWorkCoveringLetter = new PmSubWorkCoveringLetter();
			try {
				if(documentId != null && documentId.longValue()>0L){
					pmSubWorkCoveringLetter.setDocumentId(documentId);
					pmSubWorkCoveringLetter.setEndorsmentNo(endorsmentNo);
					pmSubWorkCoveringLetter.setPmSubWorkDetailsId(pmSubWorkDetailsId);
					pmSubWorkCoveringLetter.setIsDeleted("N");
					pmSubWorkCoveringLetter.setReportType(reportType);
					pmSubWorkCoveringLetterDAO.save(pmSubWorkCoveringLetter);
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised into PmRequestDetailsService of uploadActionMemoCopyforPetition() ",e);
			}
			return pmSubWorkCoveringLetter.getPmSubWorkCoveringLetterId();
		}
		
		
		public ResultStatus updatePetitionsStatusDetails(Long userId,String petitionIdsStr, String remark, Long statusId){
			ResultStatus resultStatus = new ResultStatus();
			List<Long> petitionIdsList = new ArrayList<Long>(0);
			try {
				
				if(petitionIdsStr != null && !petitionIdsStr.isEmpty()){
					String[] petitionIdsArr = petitionIdsStr.split(",");
					
					if(petitionIdsArr != null && petitionIdsArr.length>0){
						for (int i = 0; i < petitionIdsArr.length; i++) {
							if(petitionIdsArr[i] != null && !petitionIdsArr[i].isEmpty()){
								petitionIdsList.add(commonMethodsUtilService.getLongValueForString(petitionIdsArr[i]));
							}
						}
					}
				}
				
				List<Object[]> petitionSubWorksDetails = pmSubWorkDetailsDAO.getPetitionsDetailedSubWorksIdsList(petitionIdsList);
				Map<Long , Long> noStatusChangeSubWorkExistingStatusMap = new HashMap<Long , Long>(0);
				Map<Long , Long> StatusChangeSubWorkDetailsMap = new HashMap<Long , Long>(0);
				if(commonMethodsUtilService.isListOrSetValid(petitionSubWorksDetails)){
					for (Object[] param : petitionSubWorksDetails) {
						Long existingStatusId= commonMethodsUtilService.getLongValueForObject(param[2]);
						// No status change just given Comment was given works details
						if(existingStatusId.longValue()>0L && statusId != null && statusId.longValue() == existingStatusId.longValue())
							noStatusChangeSubWorkExistingStatusMap.put(commonMethodsUtilService.getLongValueForObject(param[1]),existingStatusId);
						else
							StatusChangeSubWorkDetailsMap.put(commonMethodsUtilService.getLongValueForObject(param[1]),existingStatusId);
					}
				}
				
				if(statusId != null && statusId.longValue() !=0L && (remark != null && !remark.isEmpty()))
					return updatePetitionsRemarksDetails(userId,petitionIdsList, remark, noStatusChangeSubWorkExistingStatusMap);
				
				if(petitionIdsStr != null && !petitionIdsStr.isEmpty()){
					String[] petitionIdsArr = petitionIdsStr.split(",");
					if(petitionIdsArr != null && petitionIdsArr.length>0){
						for (int i = 0; i < petitionIdsArr.length; i++) {
							if(petitionIdsArr[i] != null && !petitionIdsArr[i].isEmpty()){
								petitionIdsList.add(commonMethodsUtilService.getLongValueForString(petitionIdsArr[i]));
							}
						}
					}
					
					if(commonMethodsUtilService.isListOrSetValid(petitionIdsList)){
						pmSubWorkDetailsDAO.updatePetitionSubWorkStatusdetails(petitionIdsList,dateUtilService.getCurrentDateAndTime(),userId,statusId);
						if(commonMethodsUtilService.isListOrSetValid(petitionSubWorksDetails)){
							for (Object[] param : petitionSubWorksDetails) {
								Long subWorkId = commonMethodsUtilService.getLongValueForObject(param[1]);
								if(StatusChangeSubWorkDetailsMap.get(subWorkId) != null){
									PetitionTrackingVO pmTrackingVO = new PetitionTrackingVO();
									pmTrackingVO.setPmStatusId(statusId);
									pmTrackingVO.setUserId(userId);
									pmTrackingVO.setPetitionId(commonMethodsUtilService.getLongValueForObject(param[0]));
									pmTrackingVO.setRemarks(remark);
									pmTrackingVO.setPmTrackingActionId(2L);//STATUS CHANGED
									pmTrackingVO.setPmSubWorkDetailsId(subWorkId);
									updatePetitionTracking(pmTrackingVO);
								}
							}
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised into PmRequestDetailsService of updatePetitionsStatusDetails() ",e);
			}
			return resultStatus;
			
		}
		
		public ResultStatus updatePetitionsRemarksDetails(Long userId,List<Long> petitionIdsList, String remark, Map<Long , Long> noStatusChangeSubWorkExistingStatusMap){
			ResultStatus resultStatus = new ResultStatus();
			try {
				if(commonMethodsUtilService.isMapValid(noStatusChangeSubWorkExistingStatusMap)){
					List<Object[]> petitionSubWorksDetails = pmSubWorkDetailsDAO.getPetitionsDetailedSubWorksIdsList(petitionIdsList);
					if(commonMethodsUtilService.isListOrSetValid(petitionSubWorksDetails)){
						for (Object[] param : petitionSubWorksDetails) {
							Long subWorkId = commonMethodsUtilService.getLongValueForObject(param[1]);
							if(noStatusChangeSubWorkExistingStatusMap.get(subWorkId) != null){
								PetitionTrackingVO pmTrackingVO = new PetitionTrackingVO();
								pmTrackingVO.setPmStatusId(noStatusChangeSubWorkExistingStatusMap.get(subWorkId));
								pmTrackingVO.setUserId(userId);
								pmTrackingVO.setPetitionId(commonMethodsUtilService.getLongValueForObject(param[0]));
								pmTrackingVO.setRemarks(remark);
								pmTrackingVO.setPmTrackingActionId(3L);//COMMENT 
								pmTrackingVO.setPmSubWorkDetailsId(subWorkId);
								updatePetitionTracking(pmTrackingVO);
							} 
						}
					}
				}
				new ResultStatus(0,"Updated Successfully...");
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised into PmRequestDetailsService of updatePetitionsRemarksDetails() ",e);
			}
			return resultStatus;
		}
		public void updatePetitionTracking(PetitionTrackingVO pmTrackingVO){
			try {
				PmTracking pmTracking = new PmTracking();
				pmTracking.setPetitionId(pmTrackingVO.getPetitionId());
				pmTracking.setPmSubWorkDetailsId(pmTrackingVO.getPmSubWorkDetailsId());
				pmTracking.setPmStatusId(pmTrackingVO.getPmStatusId());
				pmTracking.setRemarks(pmTrackingVO.getRemarks());
				pmTracking.setPmTrackingActionId(pmTrackingVO.getPmTrackingActionId());
				pmTracking.setPmDepartmentDesignationOfficerId(pmTrackingVO.getPmDeptDesignationOfficerId());
				pmTracking.setDocumentId(pmTrackingVO.getDocumentId());
				pmTracking.setInsertedUserId(pmTrackingVO.getUserId());
				pmTracking.setUpdateUserId(pmTrackingVO.getUserId());
				pmTracking.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				pmTracking.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				pmTrackingDAO.save(pmTracking);
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised into PmRequestDetailsService of updatePetitionTracking() ",e);
			}
		}
		
		public List<KeyValueVO> getLoginUserAccessSubDeptDesignationDetail(List<Long> deptIdsList , Long userId){
			 List<KeyValueVO>  returnList = new ArrayList<>();
			try {
				List<Long> deptDesignationIdsList = pmOfficerUserDAO.getPmDeptDesignationIdByUserId(userId);
				if(commonMethodsUtilService.isListOrSetValid(deptDesignationIdsList)){
					List<Object[]> childDeptDesignationsList = pmDepartmentDesignationHierarchyDAO.getSubDesignationDetailsForParentDeptDesignations(deptDesignationIdsList);
					if(commonMethodsUtilService.isListOrSetValid(childDeptDesignationsList)){
						for (Object[] param : childDeptDesignationsList) {
							returnList.add(new KeyValueVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])));
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised into PmRequestDetailsService of getLoginUserAccessSubDeptDesignationDetail() ",e);
				returnList = null;
			}
			return returnList;
		}
		
		public List<KeyValueVO> getDeptDesignationOfficerDetail(Long deptDesignationId , Long userId){
			 List<KeyValueVO>  returnList = new ArrayList<>();
			try {
				if(deptDesignationId != null && deptDesignationId.longValue()>0L){
					List<Object[]> deptDesignationOfficerDetails = pmDepartmentDesignationOfficerDAO.getDeptDesignationOfficerDetailsByDeptDesignation(deptDesignationId);
					if(commonMethodsUtilService.isListOrSetValid(deptDesignationOfficerDetails)){
						for (Object[] param : deptDesignationOfficerDetails) {
							Long pmDepartmentDesignationOfficerId = commonMethodsUtilService.getLongValueForObject(param[0]);
							String officerName = commonMethodsUtilService.getStringValueForObject(param[1]);
							String mobileNo = commonMethodsUtilService.getStringValueForObject(param[2]);
							String dept =  commonMethodsUtilService.getStringValueForObject(param[3]);
							String designation = commonMethodsUtilService.getStringValueForObject(param[4]);
							
							String finalName = dept+"   "+designation+"   "+officerName+"   "+mobileNo;
							if(mobileNo.isEmpty())
								finalName = dept+"   "+designation+"   "+officerName;
							if(officerName.equalsIgnoreCase(designation))
								finalName = dept+"   "+officerName+"   "+mobileNo;
							returnList.add(new KeyValueVO(pmDepartmentDesignationOfficerId,finalName));
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised into PmRequestDetailsService of getDeptDesignationOfficerDetail() ",e);
				returnList = null;
			}
			return returnList;
		}
		
		public ResultStatus endorsingSubWorksAndAssigningToOfficer(RepresenteeViewVO inputVO){
			ResultStatus resultStatus = new ResultStatus();
			try {
				if(commonMethodsUtilService.isListOrSetValid(inputVO.getWorkIds())){
					for (Long subWorkId : inputVO.getWorkIds()) {
						PmSubWorkDetails pmSubWorkDetails = pmSubWorkDetailsDAO.get(subWorkId);
						if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L){
							if(pmSubWorkDetails != null){
								if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L && (inputVO.getStatusId().longValue() == 6L) ){
									pmSubWorkDetails.setPmLeadId(inputVO.getLeadId());
									pmSubWorkDetails.setPmGrantId(inputVO.getGrantId());
									pmSubWorkDetails.setWorkEndorsmentNo(inputVO.getEndorsementNO());
								}
								
								pmSubWorkDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								pmSubWorkDetails.setUpdatedUserId(inputVO.getId());
								pmSubWorkDetails.setPmStatusId(inputVO.getStatusId());
								pmSubWorkDetailsDAO.save(pmSubWorkDetails);
							}
							
							if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L && (inputVO.getStatusId().longValue() == 6L || inputVO.getStatusId().longValue() == 7L) ){
								if(inputVO.getDeptDesigOffcrId() != null ){
									PmPetitionAssignedOfficer pmPetitionAssignedOfficer = new PmPetitionAssignedOfficer();
									if(commonMethodsUtilService.getLongValueForObject(inputVO.getPetitionId()) >0l && commonMethodsUtilService.getLongValueForObject(inputVO.getDeptDesigOffcrId()) >0l){
										pmPetitionAssignedOfficer.setPetitionId(inputVO.getPetitionId());
										pmPetitionAssignedOfficer.setPmSubWorkDetailsId(subWorkId);
										pmPetitionAssignedOfficer.setPmDepartmentDesignationId(inputVO.getDeptDesigId());
										pmPetitionAssignedOfficer.setPmDepartmentDesignationOfficerId(inputVO.getDeptDesigOffcrId());
										pmPetitionAssignedOfficer.setRemarks(inputVO.getRemark());
										pmPetitionAssignedOfficer.setIsDeleted("N");
										pmPetitionAssignedOfficer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
										pmPetitionAssignedOfficer.setInsertedUserId(inputVO.getId());
										pmPetitionAssignedOfficer.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
										pmPetitionAssignedOfficer.setUpdatedUserId(inputVO.getId());
										pmPetitionAssignedOfficer = pmPetitionAssignedOfficerDAO.save(pmPetitionAssignedOfficer);
									}
									
								}
							}
						}
						
						
						PetitionTrackingVO pmTrackingVO = new PetitionTrackingVO();
						pmTrackingVO.setPmStatusId(inputVO.getStatusId());// PENDING ACTION MEMO 
						pmTrackingVO.setUserId(inputVO.getId());
						pmTrackingVO.setPetitionId(inputVO.getPetitionId());
						pmTrackingVO.setRemarks(inputVO.getRemark());
						pmTrackingVO.setPmTrackingActionId(2L);//STATUS CHANGED
						pmTrackingVO.setPmSubWorkDetailsId(subWorkId);
						updatePetitionTracking(pmTrackingVO);
						
					}
				}
				//String status="";
				if(commonMethodsUtilService.isListOrSetValid(inputVO.getFilesList())){
					for (MultipartFile file : inputVO.getFilesList()) {
						Document petitionCoverLetter = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER,inputVO.getId());
						if(petitionCoverLetter != null)
							resultStatus =saveCoveringLetterDocument(inputVO.getRemark(),inputVO.getWorkIds(),petitionCoverLetter.getDocumentId(),inputVO.getId(), inputVO.getEndorsementNO(),inputVO.getPetitionId(),inputVO.getStatusType(),inputVO.getStatusId());
					}
				}
				
				resultStatus.setExceptionMsg("SUCCESS");
			} catch (Exception e) {
				e.printStackTrace();
				resultStatus.setExceptionMsg("FAIL");
				LOG.error("Exception raised into PmRequestDetailsService of endorsingSubWorksAndAssigningToOfficer() ",e);
			}
			return resultStatus;
		}
		public ResultStatus saveCoveringLetterDocument(String remarks,List<Long> subWorkIds,Long documentId,Long userId,String endorsmentNo,Long petitonId,String reportType,Long statusId){
			ResultStatus status=new ResultStatus();
			try {
				if(documentId != null && documentId.longValue()>0L && commonMethodsUtilService.isListOrSetValid(subWorkIds)){
					for (Long subWorkId : subWorkIds) {
						PmSubWorkCoveringLetter pmSubWorkCoveringLetter = new PmSubWorkCoveringLetter();
						
						pmSubWorkCoveringLetter.setPetitionId(petitonId);
						pmSubWorkCoveringLetter.setEndorsmentNo(endorsmentNo);
						pmSubWorkCoveringLetter.setPmSubWorkDetailsId(subWorkId);
						pmSubWorkCoveringLetter.setDocumentId(documentId);
						pmSubWorkCoveringLetter.setReportType(reportType);
						pmSubWorkCoveringLetter.setIsDeleted("N");
						
						pmSubWorkCoveringLetter = pmSubWorkCoveringLetterDAO.save(pmSubWorkCoveringLetter);
						
						PetitionTrackingVO pmTrackingVO = new PetitionTrackingVO();
						pmTrackingVO.setPmStatusId(statusId);// PENDING ACTION MEMO 
						pmTrackingVO.setUserId(userId);
						pmTrackingVO.setPetitionId(petitonId);
						pmTrackingVO.setRemarks(remarks);
						pmTrackingVO.setPmTrackingActionId(4L);//FILE UPLOAD
						pmTrackingVO.setDocumentId(documentId);
						pmTrackingVO.setPmSubWorkDetailsId(subWorkId);
						updatePetitionTracking(pmTrackingVO);
					}
				}
				status.setExceptionMsg("SUCCESS");
			} catch (Exception e) {
				LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionReffererDocument() "+e.getMessage());
				status.setExceptionMsg("FAIL");
			}
			return status;
		}
}
