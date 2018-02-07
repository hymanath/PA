package com.itgrids.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import com.itgrids.dao.IEntitlementUrlDAO;
import com.itgrids.dao.IEntitlementsGroupEntitlementUrlDAO;
import com.itgrids.dao.ILocationAddressDAO;
import com.itgrids.dao.IPetitionDAO;
import com.itgrids.dao.IPmActionTypeDAO;
import com.itgrids.dao.IPmBriefLeadDAO;
import com.itgrids.dao.IPmDepartmentDesignationHierarchyDAO;
import com.itgrids.dao.IPmDepartmentDesignationOfficerDAO;
import com.itgrids.dao.IPmDeptDesignationPrePostStatusDetailsDAO;
import com.itgrids.dao.IPmDocumentTypeDAO;
import com.itgrids.dao.IPmGrantDAO;
import com.itgrids.dao.IPmOfficerUserDAO;
import com.itgrids.dao.IPmPetitionAssignedOfficerDAO;
import com.itgrids.dao.IPmPetitionDocumentDAO;
import com.itgrids.dao.IPmPetitionTypeDAO;
import com.itgrids.dao.IPmRefCandidateDAO;
import com.itgrids.dao.IPmRefCandidateDesignationDAO;
import com.itgrids.dao.IPmRepresenteeDAO;
import com.itgrids.dao.IPmRepresenteeDesignationDAO;
import com.itgrids.dao.IPmRepresenteeRefDetailsDAO;
import com.itgrids.dao.IPmRepresenteeRefDocumentDAO;
import com.itgrids.dao.IPmRequiredFileFormatTextDAO;
import com.itgrids.dao.IPmRequiredLettersImagesDAO;
import com.itgrids.dao.IPmStatusDAO;
import com.itgrids.dao.IPmSubWorkCoveringLetterDAO;
import com.itgrids.dao.IPmSubWorkDetailsDAO;
import com.itgrids.dao.IPmTrackingActionDAO;
import com.itgrids.dao.IPmTrackingDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.CadreRegistrationVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.MenuVO;
import com.itgrids.dto.PetitionFileVO;
import com.itgrids.dto.PetitionHistoryVO;
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
	
	@Autowired
	private IPmTrackingActionDAO pmTrackingActionDAO;
	@Autowired
	private IEntitlementsGroupEntitlementUrlDAO entitlementsGroupEntitlementUrlDAO;
	@Autowired
	private IEntitlementUrlDAO entitlementUrlDAO;
	@Autowired
	private IPmRequiredFileFormatTextDAO pmRequiredFileFormatTextDAO;
	@Autowired
	private IPmPetitionTypeDAO pmPetitionTypeDAO;
	@Autowired
	private IPmDocumentTypeDAO pmDocumentTypeDAO;
	@Autowired
	private IPmActionTypeDAO pmActionTypeDAO;
	@Autowired
	private IPmDeptDesignationPrePostStatusDetailsDAO pmDeptDesignationPrePostStatusDetailsDAO;
	
	/*@Autowired
	private IPmDepartmentDesignationStatusDAO pmDepartmentDesignationStatusDAO;*/
	public ResponseVO saveRepresentRequestDetailsTest(PmRequestVO pmRequestVO){
		return null;
	}
	
	public ResponseVO saveRepresentRequestDetails(PmRequestVO pmRequestVO){
		ResponseVO responseVO = new ResponseVO();
		try {
			if(pmRequestVO.getSaveType() != null && pmRequestVO.getSaveType().equalsIgnoreCase("Test"))
				return saveRepresentRequestDetailsTest(pmRequestVO);
			
			Map<String,List<Long>> existingDetailsMap = null;
			PmRepresentee pmRepresentee =  null;
			PmPetitionAssignedOfficer pmPetitionAssignedOfficer = null;
			String insertionType = "new";
			UserVO userVO = getPmOffceUserDetails(IConstants.DEFAULT_PETITION_ASSIGNED_USER_ID,null);//lokesh Nara
			Date uploadDate=dateUtilService.getCurrentDateAndTime();
			if(pmRequestVO.getExistingPetitionId() != null && pmRequestVO.getExistingPetitionId().longValue()>0L){
				insertionType = "update";
				if(pmRequestVO.getRemarks() == null || pmRequestVO.getRemarks().isEmpty())
					pmRequestVO.setRemarks(pmTrackingActionDAO.get(5L).getActionName());// EDIT OR UPDATE PETITIONS
				Long representeeId = pmRepresenteeRefDetailsDAO.getRepresenteeDetailsByPetitonId(pmRequestVO.getExistingPetitionId());
				if(representeeId != null && representeeId.longValue()>0L){
					pmRepresentee = pmRepresenteeDAO.get(representeeId);
					existingDetailsMap = getToupdatePetitionSubWorksAndDocumentExistingRecordDetails(pmRequestVO.getExistingPetitionId());
					pmRequestVO.setRepresenteeId(representeeId);
				}
				pmRepresentee = saveRepresenteeDetails(pmRequestVO,insertionType);
			}
			if(pmRepresentee == null){
				/** Start Petition Representee Details saving */
					pmRepresentee = saveRepresenteeDetails(pmRequestVO,insertionType);
				/** End Petition Member Details saving */
			}
			
			if(pmRequestVO.getRemarks() == null || pmRequestVO.getRemarks().isEmpty())
				pmRequestVO.setRemarks(pmTrackingActionDAO.get(1L).getActionName());
			
			/** Start Petition Referrer Details */
				if(pmRepresentee != null){
					Petition petition = savePetitionWorkDetails(pmRepresentee.getPmRepresenteeId(),pmRequestVO,insertionType,userVO.getDeptDesignationOfficerId(),uploadDate);
					if(petition == null)
						throw new Exception("Petition details not saved successfully.");
					savePetitionReferralDetails(pmRepresentee.getPmRepresenteeId(),petition.getPetitionId(),pmRequestVO,uploadDate);
					if(insertionType.equalsIgnoreCase("new")){
						pmPetitionAssignedOfficer = new PmPetitionAssignedOfficer();
						pmPetitionAssignedOfficer.setPetitionId(petition.getPetitionId());
						pmPetitionAssignedOfficer.setPmDepartmentDesignationId(userVO.getDeptDesignationId());
						pmPetitionAssignedOfficer.setPmDepartmentDesignationOfficerId(userVO.getDeptDesignationOfficerId());
						pmPetitionAssignedOfficer.setRemarks("New Petition Created and assigned to Minister peshi.");
						pmPetitionAssignedOfficer.setIsDeleted("N");
						pmPetitionAssignedOfficer.setActionType("COMPLETED");
						pmPetitionAssignedOfficer.setInsertedTime(uploadDate);
						pmPetitionAssignedOfficer.setInsertedUserId(pmRequestVO.getUserId());
						pmPetitionAssignedOfficer.setUpdatedTime(uploadDate);
						pmPetitionAssignedOfficer.setUpdatedUserId(pmRequestVO.getUserId());
						pmPetitionAssignedOfficer.setPmStatusId(1L);//default pending
						pmPetitionAssignedOfficer = pmPetitionAssignedOfficerDAO.save(pmPetitionAssignedOfficer);
					}
				}else{
					throw new Exception("Representee or Referrer details not saved successfully.");
				}
				
			if(insertionType.trim().equalsIgnoreCase("update") && commonMethodsUtilService.isMapValid(existingDetailsMap))	
				updatePetitionSubWorksAndDocumentDetails(existingDetailsMap,pmRequestVO.getUserId());
		
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
	public Map<String,List<Long>> getToupdatePetitionSubWorksAndDocumentExistingRecordDetails(Long petitionId){
		Map<String,List<Long>> existingDetailsMap = new LinkedHashMap<String,List<Long>>();
		try{
			List<Long> petitionDocumentIds =  pmPetitionDocumentDAO.getPmPetitionDocumentIds(petitionId);
			if(commonMethodsUtilService.isListOrSetValid(petitionDocumentIds))
				existingDetailsMap.put("petitionDocumentIds", petitionDocumentIds);
			
			List<Long> subWorkIds = pmSubWorkDetailsDAO.getPmSubWorkDetailsIds(petitionId);
			if(commonMethodsUtilService.isListOrSetValid(subWorkIds))
				existingDetailsMap.put("subWorkIds", subWorkIds);
			
			List<Long> pmRepresenteeRefDetailsIds =	pmRepresenteeRefDetailsDAO.getPmRepresenteeRefDetailsIds(petitionId);
			if(commonMethodsUtilService.isListOrSetValid(pmRepresenteeRefDetailsIds))
				existingDetailsMap.put("pmRepresenteeRefDetailsIds", pmRepresenteeRefDetailsIds);
			
			List<Long> representeeRefDocumentIds = pmRepresenteeRefDocumentDAO.getPmRepresenteeRefDocumentIds(pmRepresenteeRefDetailsIds);
			if(commonMethodsUtilService.isListOrSetValid(representeeRefDocumentIds))
				existingDetailsMap.put("representeeRefDocumentIds", representeeRefDocumentIds);
			
		}catch(Exception e){
			existingDetailsMap =null;
			LOG.error("Exception Occured in getToupdatePetitionSubWorksAndDocumentExistingRecordDetails in PmRequestDetailsService",e);
		}
		return existingDetailsMap;
	}
	
	@SuppressWarnings("static-access")
	public ResultStatus updatePetitionSubWorksAndDocumentDetails(Map<String,List<Long>> existingDetailsMap,Long userId){
		ResultStatus resultStatus = new ResultStatus();
		try{
			Date currentTime=dateUtilService.getCurrentDateAndTime();
			
			List<Long> petitionDocumentIds =  existingDetailsMap.get("petitionDocumentIds");
			if(commonMethodsUtilService.isListOrSetValid(petitionDocumentIds))
			    pmPetitionDocumentDAO.updatePmpetitionDocuments(petitionDocumentIds,userId);
			
			List<Long> subWorkIds = existingDetailsMap.get("subWorkIds");
			if(commonMethodsUtilService.isListOrSetValid(subWorkIds))
				pmSubWorkDetailsDAO.updatePmsubWorkDetails(subWorkIds, currentTime, userId);
			
			List<Long> pmRepresenteeRefDetailsIds =	existingDetailsMap.get("pmRepresenteeRefDetailsIds");
			if(commonMethodsUtilService.isListOrSetValid(pmRepresenteeRefDetailsIds))
				pmRepresenteeRefDetailsDAO.updatePmRepresenteRefDetails(pmRepresenteeRefDetailsIds,currentTime,userId);
			
			List<Long> representeeRefDocumentIds = existingDetailsMap.get("representeeRefDocumentIds");
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
	public Long  savePetitionReferralDetails(Long pmRepresenteeId,Long petitionId,PmRequestVO pmRequestVO,Date uploadDate){
		Long noOfRefCount=0L;
		try {
			if(pmRepresenteeId != null && pmRepresenteeId.longValue()>0L){
				List<PmRepresenteeDesignation> pmRepresenteeList = pmRepresenteeDesignationDAO.getPmRepresenteeDesignationByRepresenteeId(pmRepresenteeId);
				if(pmRequestVO.getRepresentationType() != null &&	 pmRequestVO.getRepresentationType().equalsIgnoreCase("SELF")){
					Long orderNo =1L;
					List<Long> existingRefIds = new ArrayList<Long>(0);
					if(commonMethodsUtilService.isListOrSetValid(pmRequestVO.getSelfReferList())){
						for (PmRequestVO refVO : pmRequestVO.getSelfReferList()) {
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
												petitionRefferer.setInsertedTime(uploadDate);
												petitionRefferer.setUpdatedTime(uploadDate);
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
																	Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER,pmRequestVO.getUserId(),uploadDate);
																	if(petitionWorkDocument != null)
																		savePetitionReffererDocument(petitionId,petitionRefferer.getPmRepresenteeRefDetailsId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId(),uploadDate);
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
																					savePetitionReffererDocument(petitionId,petitionRefferer.getPmRepresenteeRefDetailsId(),documentId,pmRequestVO.getUserId(),uploadDate);
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
															Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER,pmRequestVO.getUserId(),uploadDate);
															if(petitionWorkDocument != null )
																savePetitionReffererDocument(petitionId,petitionRefferer.getPmRepresenteeRefDetailsId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId(),uploadDate);
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
																			savePetitionReffererDocument(petitionId,petitionRefferer.getPmRepresenteeRefDetailsId(),documentId,pmRequestVO.getUserId(),uploadDate);
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
					//}else{// for files uploading and himself he is a referrer for a petition
						List<PmRefCandidateDesignation> pmRefCandidateList = pmRefCandidateDesignationDAO.getPmRepresenteeDesignationByPmRefCandidateId(pmRequestVO.getRefCandidateId());
						if(commonMethodsUtilService.isListOrSetValid(pmRefCandidateList)){
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
															Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER,pmRequestVO.getUserId(),uploadDate);
															if(petitionWorkDocument != null)
																savePetitionReffererDocument(petitionId,petitionRefferer.getPmRepresenteeRefDetailsId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId(),uploadDate);
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
																		savePetitionReffererDocument(petitionId,petitionRefferer.getPmRepresenteeRefDetailsId(),documentId,pmRequestVO.getUserId(),uploadDate);
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
					}else{// if no referrers , then himself he is a referrer for a petition
						List<PmRefCandidateDesignation> pmRefCandidateList = pmRefCandidateDesignationDAO.getPmRepresenteeDesignationByPmRefCandidateId(pmRequestVO.getRefCandidateId());
						if(commonMethodsUtilService.isListOrSetValid(pmRefCandidateList)){
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
															Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER,pmRequestVO.getUserId(),uploadDate);
															if(petitionWorkDocument != null)
																savePetitionReffererDocument(petitionId,petitionRefferer.getPmRepresenteeRefDetailsId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId(),uploadDate);
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
																		savePetitionReffererDocument(petitionId,petitionRefferer.getPmRepresenteeRefDetailsId(),documentId,pmRequestVO.getUserId(),uploadDate);
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
																Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER,pmRequestVO.getUserId(),uploadDate);
																if(petitionWorkDocument != null)
																	savePetitionReffererDocument(petitionId,petitionRefferer.getPmRepresenteeRefDetailsId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId(),uploadDate);
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
																				savePetitionReffererDocument(petitionId,petitionRefferer.getPmRepresenteeRefDetailsId(),documentId,pmRequestVO.getUserId(),uploadDate);
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
														Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER,pmRequestVO.getUserId(),uploadDate);
														if(petitionWorkDocument != null )
															savePetitionReffererDocument(petitionId,petitionRefferer.getPmRepresenteeRefDetailsId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId(),uploadDate);
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
																		savePetitionReffererDocument(petitionId,petitionRefferer.getPmRepresenteeRefDetailsId(),documentId,pmRequestVO.getUserId(),uploadDate);
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
	
	public PmRepresenteeRefDocument savePetitionReffererDocument(Long petitionId,Long pmRepresenteeRefDetailsId,Long documentId,Long userId,Date uploadDate){
		PmRepresenteeRefDocument petitionReffererDocument = null;
		try {
			if(documentId != null && documentId.longValue()>0L){
				petitionReffererDocument = new PmRepresenteeRefDocument();
				petitionReffererDocument.setDocumentId(documentId);
				petitionReffererDocument.setPmRepresenteeRefDetailsId(pmRepresenteeRefDetailsId);
				petitionReffererDocument.setIsDeleted("N");
				if(uploadDate == null){
					petitionReffererDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					petitionReffererDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				}else{
					petitionReffererDocument.setInsertedTime(uploadDate);
					petitionReffererDocument.setUpdatedTime(uploadDate);
				}
				
				petitionReffererDocument.setInsertedUserId(userId);
				petitionReffererDocument.setUpdatedUserId(userId);
				petitionReffererDocument = pmRepresenteeRefDocumentDAO.save(petitionReffererDocument);
			}
			
			PetitionTrackingVO pmTrackingVO = new PetitionTrackingVO();
			pmTrackingVO.setPmStatusId(pititionDAO.get(petitionId).getPmStatusId());
			pmTrackingVO.setUserId(userId);
			pmTrackingVO.setPetitionId(petitionId);
			pmTrackingVO.setDocumentId(documentId);
			pmTrackingVO.setRemarks("Uploaded referral member(s) related documents");
			pmTrackingVO.setPmTrackingActionId(4L);// UPLOAD  A FILE
			updatePetitionTracking(pmTrackingVO,uploadDate);
			
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionReffererDocument() "+e.getMessage());
			 petitionReffererDocument = null;
		}
		return petitionReffererDocument;
	}
	
	@SuppressWarnings("static-access")
	public Long savePetitionSubWorkDetails(Petition petiton,PetitionsWorksVO mainDataVO,List<PetitionsWorksVO> subWorksList,int uiBuildSeriesNo,Long userId,
			 String insertionType,PmRequestVO pmRequestVO,Long assignedToPmPetitionAssignedOfficerId,Date uploadDate){
		Long noOfWorksCount = 0L;
		try {
			Long petitonId =petiton.getPetitionId();
			Long oldPetitionStatusId=0L;
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			if(commonMethodsUtilService.isListOrSetValid(subWorksList)){
				for (PetitionsWorksVO dataVO : subWorksList) {
					if((dataVO != null && dataVO.getWorkTypeId() != null && dataVO.getWorkTypeId().longValue()>0L) || 
							(dataVO.getEstimateCost() != null && !dataVO.getEstimateCost().isEmpty()) || 
							(dataVO.getLocationScopeId() != null && dataVO.getLocationScopeId().longValue()>0L) || 
							(dataVO.geteOfficeId() != null && !dataVO.geteOfficeId().isEmpty()) || 
						//	(dataVO.getAddressVO() != null && dataVO.getAddressVO().getDistrictId() != null && dataVO.getAddressVO().getDistrictId().longValue() >0L) ||
							(dataVO.getGrievanceDescription() != null && !dataVO.getGrievanceDescription().isEmpty()))
						{
						PmSubWorkDetails pmSubWorkDetails = null;
						PmSubWorkDetails petitionSubWorkLocationDetails = new PmSubWorkDetails();
						PetitionTrackingVO pmTrackingVO = new PetitionTrackingVO();
						pmTrackingVO.setPmStatusId(1L);
						if(dataVO.getWorkId() != null && dataVO.getWorkId().longValue()>0L){
							pmSubWorkDetails = pmSubWorkDetailsDAO.get(dataVO.getWorkId());
							if(pmSubWorkDetails != null)
								pmTrackingVO.setPmStatusId(pmSubWorkDetails.getPmStatusId());
							
						}else{
							petitionSubWorkLocationDetails.setPmStatusId(1L);// default pending endorsment
						}
						
						petitionSubWorkLocationDetails.setPetitionId(petitonId);						
						petitionSubWorkLocationDetails.setGrievanceDescrption(dataVO.getGrievanceDescription());
						
						if(dataVO.getEstimateCost() != null && !dataVO.getEstimateCost().isEmpty())
							petitionSubWorkLocationDetails.setCostEstimation(String.valueOf(dataVO.getEstimateCost()));
						
						if(mainDataVO.getDeptId() != null && mainDataVO.getDeptId().longValue()>0L)
							petitionSubWorkLocationDetails.setPmDepartmentId(mainDataVO.getDeptId());
						if(mainDataVO.getSubjectId() != null && mainDataVO.getSubjectId().longValue()>0L)
							petitionSubWorkLocationDetails.setPmSubjectId(mainDataVO.getSubjectId());
						if(mainDataVO.getSubSubjectId() != null && mainDataVO.getSubSubjectId().longValue()>0L)
							petitionSubWorkLocationDetails.setPmSubSubjectId(mainDataVO.getSubSubjectId());
						if(dataVO.getLocationScopeId() != null && dataVO.getLocationScopeId().longValue()>0L)
							petitionSubWorkLocationDetails.setLocationScopeId(dataVO.getLocationScopeId());
						
						petitionSubWorkLocationDetails.seteOfficeId(dataVO.geteOfficeId());
						petitionSubWorkLocationDetails.setUiBuildSeriesNo(String.valueOf(uiBuildSeriesNo));
						
						if(dataVO.getLocationScopeId() != null && dataVO.getLocationScopeId().longValue()>0L){
							if(dataVO.getLocationScopeId().longValue()==3L){
								petitionSubWorkLocationDetails.setLocationValue(dataVO.getAddressVO().getDistrictId());
								dataVO.getAddressVO().setAssemblyId(null);
								dataVO.getAddressVO().setTehsilId(null);
								dataVO.getAddressVO().setPanchayatId(null);
							}else if(dataVO.getLocationScopeId().longValue()==4L){
								petitionSubWorkLocationDetails.setLocationValue(dataVO.getAddressVO().getAssemblyId());
								dataVO.getAddressVO().setTehsilId(null);
								dataVO.getAddressVO().setPanchayatId(null);
							}else if(dataVO.getLocationScopeId().longValue()==5L){
								petitionSubWorkLocationDetails.setLocationValue(Long.valueOf(dataVO.getAddressVO().getTehsilId().toString().substring(1)));
								dataVO.getAddressVO().setPanchayatId(null);
							}else if(dataVO.getLocationScopeId().longValue()==6L){
								petitionSubWorkLocationDetails.setLocationValue(Long.valueOf(dataVO.getAddressVO().getPanchayatId().toString().substring(1)));
							}
						
							LocationAddress address = saveLocationAddress(dataVO.getAddressVO());
							if(address != null && address.getLocationAddressId() != null && address.getLocationAddressId().longValue()>0L)
								petitionSubWorkLocationDetails.setAddressId(address.getLocationAddressId());
						}
						
						if(dataVO.getWorkTypeId() != null && dataVO.getWorkTypeId().longValue()>0L)
							petitionSubWorkLocationDetails.setPmWorkTypeId(dataVO.getWorkTypeId());
						
						petitionSubWorkLocationDetails.setIsDeleted("N");
						if(petiton != null && petiton.getIsOldData() != null && petiton.getIsOldData().equalsIgnoreCase("Y")){
							if(pmSubWorkDetails == null && (oldPetitionStatusId == null || oldPetitionStatusId.longValue()==0L)){
								List<Long> petitionIdsList = new ArrayList<Long>(0);
								petitionIdsList.add(petitonId);
								List<Object[]> subwrksList = pmSubWorkDetailsDAO.getPetitionsDetailedSubWorksIdsList(petitionIdsList);
								if(commonMethodsUtilService.isListOrSetValid(subwrksList)){
									oldPetitionStatusId = commonMethodsUtilService.getLongValueForObject(subwrksList.get(0)[2]);
								}
							}
							if(petiton.getEndorsmentNo() != null){
								petitionSubWorkLocationDetails.setInsertedUserId(petiton.getInsertedUserId());
								petitionSubWorkLocationDetails.setEndorsmentDate(petiton.getEndorsmentDate());
								petitionSubWorkLocationDetails.setWorkEndorsmentNo(petiton.getEndorsmentNo());
								
								
								if(oldPetitionStatusId !=null && oldPetitionStatusId.longValue()>0L){
									petitionSubWorkLocationDetails.setPmStatusId(oldPetitionStatusId);
									if(pmSubWorkDetails != null)
										pmTrackingVO.setPmStatusId(pmSubWorkDetails.getPmStatusId());
								}
							}
							
							petitionSubWorkLocationDetails.setInsertedTime(petiton.getInsertedTime());
							petitionSubWorkLocationDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							petitionSubWorkLocationDetails.setInsertedUserId(petiton.getInsertedUserId());
							petitionSubWorkLocationDetails.setUpdatedUserId(userId);
						}
						else if((insertionType != null && insertionType.trim().equalsIgnoreCase("new"))){
							petitionSubWorkLocationDetails.setPmStatusId(1L);// endorsement pending
							petitionSubWorkLocationDetails.setCoveringLetterPath(null);
							petitionSubWorkLocationDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
							petitionSubWorkLocationDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							petitionSubWorkLocationDetails.setInsertedUserId(userId);
							petitionSubWorkLocationDetails.setUpdatedUserId(userId);
						}else{
							petitionSubWorkLocationDetails.setInsertedTime(petiton.getInsertedTime());
							petitionSubWorkLocationDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							petitionSubWorkLocationDetails.setInsertedUserId(userId);
							petitionSubWorkLocationDetails.setUpdatedUserId(userId);
							if(pmSubWorkDetails != null)
								petitionSubWorkLocationDetails.setPmStatusId(pmSubWorkDetails.getPmStatusId());
							/*
							if(petiton.getIsOldData() != null && petiton.getIsOldData().equalsIgnoreCase("Y")){
								if(oldPetitionStatusId == null || oldPetitionStatusId.longValue()==0L){
									List<Long> petitionIdsList = new ArrayList<Long>(0);
									petitionIdsList.add(petitonId);
									List<Object[]> subwrksList = pmSubWorkDetailsDAO.getPetitionsDetailedSubWorksIdsList(petitionIdsList);
									if(commonMethodsUtilService.isListOrSetValid(subwrksList)){
										oldPetitionStatusId = commonMethodsUtilService.getLongValueForObject(subwrksList.get(0)[2]);
									}
								}
								if(petiton.getEndorsmentNo() != null){
									petitionSubWorkLocationDetails.setInsertedUserId(petiton.getInsertedUserId());
									petitionSubWorkLocationDetails.setEndorsmentDate(petiton.getEndorsmentDate());
									petitionSubWorkLocationDetails.setWorkEndorsmentNo(petiton.getEndorsmentNo());
									if(pmSubWorkDetails != null && (pmSubWorkDetails.getPmStatusId() == null || pmSubWorkDetails.getPmStatusId().longValue() ==0L))
										petitionSubWorkLocationDetails.setPmStatusId(oldPetitionStatusId);
								}
							}else{
								if(pmRequestVO.getEndorsmentNo() != null){
									if(pmRequestVO.getEndorsmentDate() != null && !pmRequestVO.getEndorsmentDate().trim().isEmpty())
										petitionSubWorkLocationDetails.setEndorsmentDate(format.parse(pmRequestVO.getEndorsmentDate()));
									petitionSubWorkLocationDetails.setWorkEndorsmentNo(pmRequestVO.getEndorsmentNo());
								}else if(pmSubWorkDetails != null){
									petitionSubWorkLocationDetails.setWorkEndorsmentNo(pmSubWorkDetails.getWorkEndorsmentNo());
									petitionSubWorkLocationDetails.setEndorsmentDate(pmSubWorkDetails.getEndorsmentDate());
								}
								if(pmSubWorkDetails != null){
									petitionSubWorkLocationDetails.setPmStatusId(pmSubWorkDetails.getPmStatusId());// endorsement pending
									petitionSubWorkLocationDetails.setPmLeadId(pmSubWorkDetails.getPmLeadId());
									petitionSubWorkLocationDetails.setPmGrantId(pmSubWorkDetails.getPmGrantId());
									petitionSubWorkLocationDetails.setPmBriefLeadId(pmSubWorkDetails.getPmBriefLeadId());
									petitionSubWorkLocationDetails.setCoveringLetterPath(pmSubWorkDetails.getCoveringLetterPath());
								}
								petitionSubWorkLocationDetails.setInsertedTime(petiton.getInsertedTime());
								petitionSubWorkLocationDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								petitionSubWorkLocationDetails.setInsertedUserId(petiton.getInsertedUserId());
								petitionSubWorkLocationDetails.setUpdatedUserId(userId);
							}
						*/} 
						
						petitionSubWorkLocationDetails = pmSubWorkDetailsDAO.save(petitionSubWorkLocationDetails);
						noOfWorksCount=noOfWorksCount+1;
						
						pmTrackingVO.setUserId(userId);
						pmTrackingVO.setPetitionId(petitonId);
						pmTrackingVO.setRemarks(pmRequestVO.getRemarks());
						if(insertionType != null && insertionType.trim().equalsIgnoreCase("new"))
							pmTrackingVO.setPmTrackingActionId(1L);//CREATE A PETITION
						else
							pmTrackingVO.setPmTrackingActionId(5L);//EDIT/UPDATED  PETITION
						pmTrackingVO.setAssignedToPmPetitionAssignedOfficerId(assignedToPmPetitionAssignedOfficerId);
						pmTrackingVO.setRemarks(" New work Created and assigned to Minister Peshi.");
						pmTrackingVO.setPmSubWorkDetailsId(petitionSubWorkLocationDetails.getPmSubWorkDetailsId());
						updatePetitionTracking(pmTrackingVO,uploadDate);
					}
				}
			}else if(commonMethodsUtilService.isListOrSetValid( pmRequestVO.getWorksList())){
				PetitionsWorksVO dataVO = pmRequestVO.getWorksList().get(0);
				if(dataVO != null){
					PmSubWorkDetails petitionSubWorkLocationDetails = new PmSubWorkDetails();
					petitionSubWorkLocationDetails.setPetitionId(petitonId);						
					petitionSubWorkLocationDetails.setGrievanceDescrption(dataVO.getGrievanceDescription());
					
					if(dataVO.getEstimateCost() != null && !dataVO.getEstimateCost().isEmpty())
						petitionSubWorkLocationDetails.setCostEstimation(String.valueOf(dataVO.getEstimateCost()));
					petitionSubWorkLocationDetails.setUiBuildSeriesNo("1");
					if(pmRequestVO.getEndorsmentDate() != null && !pmRequestVO.getEndorsmentDate().isEmpty())
						petitionSubWorkLocationDetails.setEndorsmentDate(format.parse(pmRequestVO.getEndorsmentDate()));
					petitionSubWorkLocationDetails.setWorkEndorsmentNo(pmRequestVO.getEndorsmentNo());
					petitionSubWorkLocationDetails.setPmStatusId(1L);// endorsement pending
					petitionSubWorkLocationDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					petitionSubWorkLocationDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					petitionSubWorkLocationDetails.setInsertedUserId(userId);
					petitionSubWorkLocationDetails.setUpdatedUserId(userId);
					petitionSubWorkLocationDetails = pmSubWorkDetailsDAO.save(petitionSubWorkLocationDetails);
					
					
					PetitionTrackingVO pmTrackingVO = new PetitionTrackingVO();
					pmTrackingVO.setPmStatusId(1L);
					pmTrackingVO.setUserId(userId);
					pmTrackingVO.setPetitionId(petitonId);
					pmTrackingVO.setRemarks(pmRequestVO.getRemarks());
					if(insertionType != null && insertionType.trim().equalsIgnoreCase("new"))
						pmTrackingVO.setPmTrackingActionId(1L);//CREATE A PETITION
					else
						pmTrackingVO.setPmTrackingActionId(5L);//EDIT/UPDATED  PETITION
					pmTrackingVO.setAssignedToPmPetitionAssignedOfficerId(assignedToPmPetitionAssignedOfficerId);
					pmTrackingVO.setRemarks("New work Created and assigned to Minister Peshi.");
					pmTrackingVO.setPmSubWorkDetailsId(petitionSubWorkLocationDetails.getPmSubWorkDetailsId());
					updatePetitionTracking(pmTrackingVO,uploadDate);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionSubWorkDetails() "+e.getMessage());
			return null;
		}
		return noOfWorksCount;
	}
	
	@SuppressWarnings("static-access")
	public Petition savePetitionWorkDetails(Long pmRepresenteeId,PmRequestVO pmRequestVO,String insertionType,Long assignedToPmPetitionAssignedOfficerId,Date uploadDate){
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
								PetitionTrackingVO pmTrackingVO = new PetitionTrackingVO();
								pmTrackingVO.setPmStatusId(1L);
								pmTrackingVO.setUserId(pmRequestVO.getUserId());
								pmTrackingVO.setRemarks(pmRequestVO.getRemarks());
								
								petition = new Petition();
								if(pmRequestVO.getExistingPetitionId() != null && pmRequestVO.getExistingPetitionId().longValue() >0L){
									petition = pititionDAO.get(pmRequestVO.getExistingPetitionId());
									//petition.setRepresentationDate(format.parse(pmRequestVO.getRepresentationdate()));
									pmTrackingVO.setPmTrackingActionId(5L);//EDIT/UPDATED  PETITION
									if(pmRequestVO.getRepresentationdate() != null && !pmRequestVO.getRepresentationdate().trim().isEmpty() )
										petition.setRepresentationDate(format.parse(pmRequestVO.getRepresentationdate().trim()));
									if(petition!= null && petition.getIsOldData() != null && petition.getIsOldData().equalsIgnoreCase("Y")){
										if(pmRequestVO.getEndorsmentDate() != null && !pmRequestVO.getEndorsmentDate().trim().isEmpty())
											petition.setEndorsmentDate(format.parse(pmRequestVO.getEndorsmentDate().trim()));
										
										petition.setEndorsmentNo(petition.getEndorsmentNo());
										petition.setIsOldData(petition.getIsOldData());
									}
									
									pmTrackingVO.setPmStatusId(petition.getPmStatusId());
									
								}else{
									petition.setEndorsmentDate(null);
									petition.setEndorsmentNo(null);
									petition.setPmStatusId(1L);// Pending Endorsment
									petition.setIsOldData("N");
									if(pmRequestVO.getRepresentationdate() != null && !pmRequestVO.getRepresentationdate().trim().isEmpty())
										petition.setRepresentationDate(format.parse(pmRequestVO.getRepresentationdate().trim()));
									petition.setRepresenteeType(pmRequestVO.getRepresentationType());
									petition.setInsertedTime(dateUtilService.getCurrentDateAndTime());
									petition.setInsertedUserId(pmRequestVO.getUserId());
									pmTrackingVO.setPmTrackingActionId(1L);//CREATE A PETITION 
								}
								
								if(dataVO.getEstimateCost() != null)
									petition.setEstimationCost(dataVO.getEstimateCost());
								petition.setWorkName(dataVO.getWorkName());
								petition.setNoOfWorks(dataVO.getNoOfWorks());
								petition.setIsDeleted("N");
								petition.setIsPreviousPetition(dataVO.getIsPreviousPetition());
								petition.setGrievanceDescription(dataVO.getGrievanceDescription());
								petition.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
								if(pmRequestVO.getUserId() != null && pmRequestVO.getUserId().longValue()>0L)
									petition.setUpdatedUserId(pmRequestVO.getUserId());
								petition = pititionDAO.save(petition);
								
								pmTrackingVO.setPetitionId(petition.getPetitionId());
								
								if(insertionType != null){
									if(insertionType.equalsIgnoreCase("new") )
										pmTrackingVO.setRemarks("New Petition Created and assigned to Minister Peshi.");
									else
										pmTrackingVO.setRemarks(" Petition details edited/updated.");
								}
								pmTrackingVO.setAssignedToPmPetitionAssignedOfficerId(assignedToPmPetitionAssignedOfficerId);
								updatePetitionTracking(pmTrackingVO,uploadDate);
								
							}
							i=i+1;
							if(petition != null && petition.getPetitionId() != null && petition.getPetitionId().longValue()>0L && commonMethodsUtilService.isListOrSetValid(dataVO.getSubWorksList())){
								Long tempsubmittedWorksCount = savePetitionSubWorkDetails(petition,dataVO.getSubWorksList().get(0),dataVO.getSubWorksList(),i,pmRequestVO.getUserId(),insertionType,pmRequestVO,assignedToPmPetitionAssignedOfficerId,uploadDate);
								if(tempsubmittedWorksCount == null)
									throw new Exception(" Sub works are not saved successfully.");
								submittedWorksCount = submittedWorksCount+tempsubmittedWorksCount;
							}else{
								Long tempsubmittedWorksCount = savePetitionSubWorkDetails(petition,null,null,i,pmRequestVO.getUserId(),insertionType,pmRequestVO,assignedToPmPetitionAssignedOfficerId,uploadDate);
								if(tempsubmittedWorksCount == null)
									throw new Exception(" Sub works are not entered by user .");
								submittedWorksCount = submittedWorksCount+tempsubmittedWorksCount;
							}
							
							if(i==1){
								if(dataVO.getFileList() != null && dataVO.getFileList().size()>0){
									for (MultipartFile file : dataVO.getFileList()) {
										Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER,pmRequestVO.getUserId(),uploadDate);
										if(petitionWorkDocument != null)
											savePetitionWorkDocument(petition.getPetitionId(),petitionWorkDocument.getDocumentId(),pmRequestVO.getUserId(),uploadDate);
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
														savePetitionWorkDocument(petition.getPetitionId(),documentId,pmRequestVO.getUserId(),uploadDate);
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
	
	public PmPetitionDocument savePetitionWorkDocument(Long petitionId,Long documentId,Long userId,Date uploadDate){
		PmPetitionDocument petitionWorkDocument = null;
		try {
			if(documentId != null && documentId.longValue()>0L){
				petitionWorkDocument = new PmPetitionDocument();
				petitionWorkDocument.setPetitionId(petitionId);
				petitionWorkDocument.setDocumentId(documentId);
				petitionWorkDocument.setInsertedUserId(userId);
				petitionWorkDocument.setUpdatedUserId(userId);
				if(uploadDate == null){
					petitionWorkDocument.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					petitionWorkDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				}else{
					petitionWorkDocument.setInsertedTime(uploadDate);
					petitionWorkDocument.setUpdatedTime(uploadDate);
				}
				petitionWorkDocument.setIsDeleted("N");
				petitionWorkDocument = pmPetitionDocumentDAO.save(petitionWorkDocument);
			}
			
			PetitionTrackingVO pmTrackingVO = new PetitionTrackingVO();
			pmTrackingVO.setPmStatusId(pititionDAO.get(petitionId).getPmStatusId());
			pmTrackingVO.setUserId(userId);
			pmTrackingVO.setPetitionId(petitionId);
			pmTrackingVO.setDocumentId(documentId);
			pmTrackingVO.setRemarks("Uploaded work related documents ");
			pmTrackingVO.setPmTrackingActionId(4L);// UPLOAD  A FILE
			updatePetitionTracking(pmTrackingVO,uploadDate);
			
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionReffererDocument() "+e.getMessage());
			petitionWorkDocument = null;
		}
		return petitionWorkDocument;
	}
	
	public Document saveDocument(MultipartFile file, String destinationPath,Long userId,Date uploadDate){
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
					if(uploadDate == null){
						document.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					}else{
						document.setInsertedTime(uploadDate);
					}
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
							if(pmRequestVO.getTdpCadreId() != null && pmRequestVO.getTdpCadreId().longValue()>0L){
								if(pmRequestVO.getTdpCadreId() != null && pmRepresentee.getTdpCadreId() != null && pmRepresentee.getTdpCadreId().longValue()>0L)
									pmRepresentee.setTdpCadreId(Long.valueOf(setDataToAttribute(pmRequestVO.getTdpCadreId().toString(),pmRepresentee.getTdpCadreId().toString())));
								else
									pmRepresentee.setTdpCadreId(pmRequestVO.getTdpCadreId());
							}
							
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
	    		referralCanIds = pmRepresenteeRefDetailsDAO.getPmReferralCandidateIdsByDesigIds(dataVo.getDesignationIds(),dataVo.getReferrerCandidateId(),dataVo.getStatusIds());
	    	}
	    	//  0 petitonrefCndidateId ,1 name,2 designation ,3 stateId,4 stateName
	    	// 5 districtId 6 district name 7 constincuyId,8 constincyName, 9 tehsilId,10 teshilName
	    	// 11 panchayId,12 panchaytname,13 mobilNo,14 emailId,14 desigantionId
	    	List<Object[]> referalObjs = pmRefCandidateDesignationDAO.getCandidatseDetailsByDesignationAndLocation(dataVo.getDeptId(),dataVo.getLocationLevelId(),dataVo.getLocationValue(),referralCanIds,dataVo.getDesignationIds());
	    	if(referalObjs != null && referalObjs.size() > 0){
	    		for( Object[] param : referalObjs ){
	    			RepresentationRequestVO mainV0 = new RepresentationRequestVO();
	    			mainV0.setReferrerCandidateId(commonMethodsUtilService.getLongValueForObject(param[0]));
	    			mainV0.setDesignationId(commonMethodsUtilService.getLongValueForObject(param[38]));//refCandidateDesigId
	    			PetitionMemberVO petitionMemberVO = new PetitionMemberVO();
	    			petitionMemberVO.setId(commonMethodsUtilService.getLongValueForObject(param[15]));//desigantionId
	    			petitionMemberVO.setName(commonMethodsUtilService.getCapitalStringValueForObject(param[1]));
	    			petitionMemberVO.setMemberType(commonMethodsUtilService.getStringValueForObject(param[2]));//designation
	    			petitionMemberVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[13]));
	    			petitionMemberVO.setEmailId(commonMethodsUtilService.getStringValueForObject(param[14]));
	    			if(param[18] != null)
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
			 Map<Long,List<Long>> assignedPetitionsMap =  getAssignedPetitionforPetitionDeptDesignationOfficer(inputVO.getLocationId(),null);
			 Set<Long> petitionIdsList = assignedPetitionsMap.keySet();
			 
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
					searchData = pmRepresenteeRefDetailsDAO.getRepresentativeSearchWiseDetails(inputVO,startDate,endDate,petitionIdsList);
					setSearchDetailsData(searchData,mapData);
				}
				if(inputVO.getLocationValues() != null && inputVO.getLocationValues().size() >0){
					searchData = null;
					inputVO.getSearchLvlVals().clear();
					inputVO.getSearchLvlVals().addAll(inputVO.getLocationValues());
					inputVO.setSearchLevelId(5l);
					searchData = pmRepresenteeRefDetailsDAO.getRepresentativeSearchWiseDetails(inputVO,startDate,endDate,petitionIdsList);
					setSearchDetailsData(searchData,mapData);
				}
			    }else{
				searchData = pmRepresenteeRefDetailsDAO.getRepresentativeSearchWiseDetails(inputVO,startDate,endDate,petitionIdsList);
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
						vo.setPetitionStatusId(commonMethodsUtilService.getLongValueForObject(param[18]));
						vo.setDesigName(commonMethodsUtilService.getStringValueForObject(param[7]));
						vo.getDesigList().add(commonMethodsUtilService.getStringValueForObject(param[7]));
						//vo.set
						vo.setReferrerName(commonMethodsUtilService.getCapitalStringValueForObject(param[5]));
						vo.getReferList().add(commonMethodsUtilService.getCapitalStringValueForObject(param[5]));
						if(commonMethodsUtilService.getLongValueForObject(param[0]) >0l)
							mapData.put(commonMethodsUtilService.getLongValueForObject(param[0]), vo);
					}else{
						if(vo.getDesigList() != null && vo.getDesigList().size() >0 && !vo.getDesigList().contains(commonMethodsUtilService.getStringValueForObject(param[7]))){
							String designations = vo.getDesigName().concat(", "+commonMethodsUtilService.getStringValueForObject(param[7]));
							vo.setDesigName(designations);
							vo.getDesigList().add(commonMethodsUtilService.getStringValueForObject(param[7]));
						}
						if(vo.getReferList() != null && vo.getReferList().size() >0 && !vo.getReferList().contains(commonMethodsUtilService.getCapitalStringValueForObject(param[5]))){
							String refName = vo.getReferrerName().concat(", "+commonMethodsUtilService.getCapitalStringValueForObject(param[5]));
							vo.setReferrerName(refName);
							vo.getReferList().add(commonMethodsUtilService.getCapitalStringValueForObject(param[5]));
						}
					}
					vo.setPetitionId(commonMethodsUtilService.getLongValueForObject(param[0]));
					vo.setEndorsementNO(commonMethodsUtilService.getStringValueForObject(param[1]));
					if(param[2] != null){
						vo.setEndorsmentDate(commonMethodsUtilService.getStringValueForObject(param[2]).substring(0, 10));
					}
					vo.setEstimationCost(commonMethodsUtilService.getStringValueForObject(param[3]));
					vo.setName(commonMethodsUtilService.getCapitalStringValueForObject(param[4]));
					//vo.setReferrerName(commonMethodsUtilService.getCapitalStringValueForObject(param[5]));
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
					subWorkVO.setEstimationCost(commonMethodsUtilService.getStringValueForObject(param[12]));
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
	public List<RepresenteeViewVO> getStatusList(List<Long> statId){
		List<RepresenteeViewVO> returnList = new ArrayList<RepresenteeViewVO>();
		try {
			
			//List<PmStatus> list = pmStatusDAO.getAll();
			 List<Object[]> statusList = pmStatusDAO.getPmStatusList( statId);
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
	
	@SuppressWarnings("static-access")
	public PmRequestEditVO getPetitionBasicDetails(Long petitionId,String pageType,Map<Long,KeyValueVO> petitionFilesListMap, Map<Long,List<KeyValueVO>> refFilesListMap ){
		PmRequestEditVO returnVO = null; 
		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dmyFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			List<Object[]> pmRepresenteePetitionsDetils = pmRepresenteeRefDetailsDAO.getPmRepresenteRefDetails(petitionId);
			if(commonMethodsUtilService.isListOrSetValid(pmRepresenteePetitionsDetils)){
					returnVO = new PmRequestEditVO();
					int i=0;
					Long representeeRefCandidateId =0L;
					boolean alreadyReprDataSet =false;
				for (Object[] param : pmRepresenteePetitionsDetils) {
					
					if(i == 0){
						 returnVO.setPetitionId(petitionId);
						 returnVO.setNoOfWorks(commonMethodsUtilService.getLongValueForObject(param[27]));
						 returnVO.setEstimateCost("");
						 returnVO.setIsOldData(commonMethodsUtilService.getStringValueForObject(param[70]));
						 
						 if(!commonMethodsUtilService.getStringValueForObject(param[26]).isEmpty()){
							 returnVO.setEstimateCost(commonMethodsUtilService.getStringValueForObject(param[26]));
							 returnVO.setEstimateCostStr(commonMethodsUtilService.getStringValueForObject(param[26]));
							// if(Long.valueOf(commonMethodsUtilService.getStringValueForObject(param[26]))>0L)
							//	 returnVO.setEstimateCostStr(commonMethodsUtilService.calculateAmountInWords(Long.valueOf(commonMethodsUtilService.getStringValueForObject(param[26]))));
						 }
						 
						 returnVO.setEndorsmentNo(commonMethodsUtilService.getStringValueForObject(param[22]));
						 returnVO.setEndorsmentDate("");
						 returnVO.setRepresentationdate("");
						 
						 if(commonMethodsUtilService.getStringValueForObject(param[24]).length()>10)
							 returnVO.setEndorsmentDate(dmyFormat.format(ymdFormat.parse(commonMethodsUtilService.getStringValueForObject(param[24]).substring(0, 10))));
						 else if(param[24] != null)
							 returnVO.setEndorsmentDate(dmyFormat.format(ymdFormat.parse(commonMethodsUtilService.getStringValueForObject(param[24]).substring(0, 10))));
						 if(commonMethodsUtilService.getStringValueForObject(param[23]).length()>10)
							 returnVO.setRepresentationdate(dmyFormat.format(ymdFormat.parse(commonMethodsUtilService.getStringValueForObject(param[23]).substring(0, 10))));
						 else if(param[23] != null)
							 returnVO.setRepresentationdate(dmyFormat.format(ymdFormat.parse(commonMethodsUtilService.getStringValueForObject(param[23]).substring(0, 10))));
						 
						 returnVO.setWorkName(commonMethodsUtilService.getStringValueForObject(param[25]));
						 returnVO.setGrievanceDescription(commonMethodsUtilService.getStringValueForObject(param[25]));
						 returnVO.setRepresentationType(commonMethodsUtilService.getStringValueForObject(param[50]));
						 
						 if(param[28] != null && commonMethodsUtilService.getLongValueForObject(param[28]).longValue()>0L)
							 returnVO.setStatusId(commonMethodsUtilService.getLongValueForObject(param[28]));
						 
						 if(commonMethodsUtilService.isMapValid(petitionFilesListMap)){
							 returnVO.getFileList().addAll(petitionFilesListMap.values());
						 }
						 
						 representeeRefCandidateId = commonMethodsUtilService.getLongValueForObject(param[31]);
						// if(returnVO.getRepresentationType().equalsIgnoreCase("REPRESENTEE")){
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
						// }
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
					 refVO.setAddressVO(refAddressVO);// for in ui repre. address building
					 
					 if(commonMethodsUtilService.isMapValid(refFilesListMap) && refFilesListMap.get(refVO.getId()) != null && commonMethodsUtilService.isListOrSetValid(refFilesListMap.get(refVO.getId()))){
						refVO.getFileNamesList().addAll(refFilesListMap.get(refVO.getId()));
					 }
					 
					 if(!commonMethodsUtilService.isListOrSetValid(returnVO.getReferDetailsList())){
						 if(representeeRefCandidateId != null && representeeRefCandidateId.longValue() == refVO.getRefCandidateId()){
							 if(!alreadyReprDataSet){
								 alreadyReprDataSet=true;
								 returnVO.getRepresenteeDetailsList().clear();
								 returnVO.getRepresenteeDetailsList().add(refVO);
							 }
						 }else 
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
							 if(representeeRefCandidateId != null && representeeRefCandidateId.longValue() == refVO.getRefCandidateId()){
								 if(!alreadyReprDataSet){
									 alreadyReprDataSet=true;
									 returnVO.getRepresenteeDetailsList().clear();
									 returnVO.getRepresenteeDetailsList().add(refVO);
								 }
							 }else 
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
		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dmyFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			List<Object[]> subWorksList = pmSubWorkDetailsDAO.getPetitionSubWorksDetails(petitionId);
			List<Long> workIdsList = new ArrayList<Long>(0);
			if(commonMethodsUtilService.isListOrSetValid(subWorksList)){
				for (Object[] param : subWorksList) {
					workIdsList.add(commonMethodsUtilService.getLongValueForObject(param[17]));
					PetitionsWorksVO vo = new PetitionsWorksVO();
					vo.setPetitionId(commonMethodsUtilService.getLongValueForObject(param[0]));
					vo.setWorkId(commonMethodsUtilService.getLongValueForObject(param[17]));
					vo.setEndorsmentNo(commonMethodsUtilService.getStringValueForObject(param[38]));
					vo.setEndorsmentDate("");
					if(commonMethodsUtilService.getStringValueForObject(param[39]).length()>10)
						vo.setEndorsmentDate(dmyFormat.format(ymdFormat.parse(commonMethodsUtilService.getStringValueForObject(param[39]).substring(0, 10))));
					 else if(param[39] != null)
						 vo.setEndorsmentDate(dmyFormat.format(ymdFormat.parse(commonMethodsUtilService.getStringValueForObject(param[39]).substring(0, 10))));
					
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
						vo.setEstimateCost(commonMethodsUtilService.getStringValueForObject(param[1]));
						 vo.setEstimateCostStr(commonMethodsUtilService.getStringValueForObject(param[1]));
						//if(!commonMethodsUtilService.getStringValueForObject(param[1]).isEmpty() && Long.valueOf(commonMethodsUtilService.getStringValueForObject(param[1]))>0L)
							// vo.setEstimateCostStr(commonMethodsUtilService.calculateAmountInWords(Long.valueOf(commonMethodsUtilService.getStringValueForObject(param[1]))));
					}else{
						vo.setEstimateCost("");
						vo.setEstimateCostStr("");
					}
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
					 
					/* String seriesNo = commonMethodsUtilService.getStringValueForObject(param[35]);
					 if(pageType == null){
						 seriesNo = (vo.getEndorsmentNo() == null || vo.getEndorsmentNo().isEmpty())?"0":vo.getEndorsmentNo();
					 }*/
					 
					 String seriesNo = (vo.getEndorsmentNo() == null || vo.getEndorsmentNo().isEmpty())?"0":vo.getEndorsmentNo();
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
				PetitionTrackingVO dataVO = new PetitionTrackingVO();
				dataVO.setPetitionId(petitionId);
				PetitionHistoryVO historyVO = null;
				if(pageType == null){
					historyVO = getPetitionAndWorkWiseHistoryDetails(dataVO);
				}
				if(historyVO != null){
					List<PetitionsWorksVO> worksList  = new ArrayList<>();
					//PetitionHistoryVO petitionHistoryVO = null;
					if(commonMethodsUtilService.isMapValid(petitionSubWorksMap)){
						for (String endorsNo : petitionSubWorksMap.keySet()) {
							worksList = petitionSubWorksMap.get(endorsNo);
							if(commonMethodsUtilService.isListOrSetValid(worksList) && commonMethodsUtilService.isListOrSetValid(historyVO.getSubList1())){
								for (PetitionsWorksVO workVO : worksList) {
									for (PetitionHistoryVO vo : historyVO.getSubList1()) {
										if(workVO.getWorkId() != null && vo.getWorkId() != null && workVO.getWorkId().longValue() == vo.getWorkId().longValue()){
											workVO.getHistoryList().add(vo);
										}
										//if(vo.getWorkId() != null && vo.getWorkId().longValue()==0L)
										//	petitionHistoryVO = vo;
									}
								}
							}
						}
					}
					
					if(worksList != null){
						/* Only petition wise history */
						PetitionsWorksVO firstWorkVO = worksList.get(0);
						if(firstWorkVO != null && !commonMethodsUtilService.isListOrSetValid(firstWorkVO.getPetitionHistoryList())){
							if(commonMethodsUtilService.isListOrSetValid(historyVO.getPetitionHistoryList())){
								for (PetitionHistoryVO hVO : historyVO.getPetitionHistoryList()) {
									if(hVO.getWorkId() != null && hVO.getWorkId().longValue()==0L)
										firstWorkVO.getPetitionHistoryList().add(hVO);// petition overall Histroy , not work wise
								}
							}
							
						}
						/* Only petition wise history */
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ getPetitionsSubWorksDetails "+e.getMessage());
		}
		return petitionSubWorksMap;
	}
	
	
	public Map<Long,PetitionHistoryVO> getPetitionWorksHistoryDetails(){
		Map<Long,PetitionHistoryVO> historyMap = new HashMap<Long, PetitionHistoryVO>(0);
		try {
			
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ getPetitionWorksHistoryDetails "+e.getMessage());
		}
		return historyMap;
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
	
	public List<PetitionFileVO> getRequiredFilesTypeTemplate(){
		List<PetitionFileVO> globalFilesList = new ArrayList<PetitionFileVO>(0);
		try {
			globalFilesList.add(new PetitionFileVO("COVERING LETTER",null));
			globalFilesList.add(new PetitionFileVO("ACTION COPY",null));
			globalFilesList.add(new PetitionFileVO("DETAILED REPORT",null));
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ getPetitionsRequiredFilesMap "+e.getMessage());
		}
		return globalFilesList;
	}
	
	public KeyValueVO getAssignedPetitionOfficersStatusDetails(Long petitionId,Long userId){
		KeyValueVO returnVO = null; 
		try {
			List<Object[]> list = pmOfficerUserDAO.getPmOffceUserDetails(userId);
			List<Long> deptDesignationOfficerIdsList = new ArrayList<>();
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] userObj : list) {
					if(userObj != null && userObj.length > 0){
						deptDesignationOfficerIdsList.add(commonMethodsUtilService.getLongValueForObject(userObj[7]));
					}
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(deptDesignationOfficerIdsList)){
				List<Object[]> assignedDetails = pmPetitionAssignedOfficerDAO.getActionTypeDetailsForDeptDesiOfficerId(deptDesignationOfficerIdsList,petitionId);
				if(commonMethodsUtilService.isListOrSetValid(assignedDetails)){
					returnVO = new KeyValueVO(); 
					Object[] param = assignedDetails.get(0);
					returnVO.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
					returnVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in PmRequestDetailsService @ getAssignedPetitionOfficersStatusDetails "+e.getMessage());
		}
		return returnVO;
	}
	 @SuppressWarnings("static-access")//11111
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
			 KeyValueVO assignedWorksStatusVO = getAssignedPetitionOfficersStatusDetails(petitionId,userId);
			 List<KeyValueVO> allFilesList = new ArrayList<KeyValueVO>(0);
			 if(assignedWorksStatusVO != null){
				 petitionVO.setActionType(assignedWorksStatusVO.getValue());
				 petitionVO.setWorksStatus(assignedWorksStatusVO.getName());
			 }
			 
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
				KeyValueVO userAccessStatusVO = getPmDeptStatusIdsByUserIdsLst(userId,"false");  
				if(userAccessStatusVO != null && commonMethodsUtilService.isListOrSetValid(userAccessStatusVO.getDeptIdsList())){
					KeyValueVO pendingVO = statusMap.get(1L);//pending endorsment 
					if(pendingVO != null){ 
						UserVO userVO = getPmOffceUserDetails(userId,null);
						List<Long> postStatusIdsList = new ArrayList<>();
						if(userVO != null){
							if(commonMethodsUtilService.isMapValid(petitionSubWorksMap)){
								List<Long> statusIdsList = new ArrayList<>();
								for (String seriesNo : petitionSubWorksMap.keySet()) {
									List<PetitionsWorksVO> workTypeVOList = petitionSubWorksMap.get(seriesNo);
									if(commonMethodsUtilService.isListOrSetValid(workTypeVOList)){
										for (PetitionsWorksVO worksVO : workTypeVOList) {
											if(!statusIdsList.contains(worksVO.getStatusId()))
												statusIdsList.add(worksVO.getStatusId());
										}
									}
								}
								postStatusIdsList = pmDeptDesignationPrePostStatusDetailsDAO.getItSelfandPoststatusDetail(statusIdsList,userVO.getDesignationId());
							}
						}
						for (Long statusId : statusMap.keySet()) {
							if(commonMethodsUtilService.isListOrSetValid(userAccessStatusVO.getDeptIdsList()) && commonMethodsUtilService.isListOrSetValid(postStatusIdsList)){
								//if(userAccessStatusVO.getDeptIdsList().contains(statusId) || postStatusIdsList.contains(statusId)) {
								if(postStatusIdsList.contains(statusId)) {
									pendingVO.getSubList().add(new KeyValueVO(statusId, statusMap.get(statusId).getValue()));
								}
							}else{
								if(userAccessStatusVO.getDeptIdsList().contains(statusId)) {
									pendingVO.getSubList().add(new KeyValueVO(statusId, statusMap.get(statusId).getValue()));
								}
							}
						}
					}
				}
			 }
			 
			 Map<String,Map<Long,Map<Long,Map<Long,List<PetitionsWorksVO>>>>> endorsementDeptWorksMap = new LinkedHashMap<String,Map<Long,Map<Long,Map<Long,List<PetitionsWorksVO>>>>>();
			 Map<Long,KeyValueVO> departmentsMap = new TreeMap<Long,KeyValueVO>();
			 PetitionsWorksVO pendignEndorsVO = null;
			 
				if(commonMethodsUtilService.isMapValid(petitionSubWorksMap)){
					for (String seriesNo : petitionSubWorksMap.keySet()) {
						List<PetitionFileVO> globalFilesList = getRequiredFilesTypeTemplate();
						
						List<PetitionsWorksVO> workTypeVOList = petitionSubWorksMap.get(seriesNo);
						if(commonMethodsUtilService.isListOrSetValid(workTypeVOList)){
							for (PetitionsWorksVO worksVO : workTypeVOList) {
								
								/* setting petition wise history */
								if(!commonMethodsUtilService.isListOrSetValid(petitionVO.getHistoryList())){
									petitionVO.getHistoryList().addAll(worksVO.getPetitionHistoryList());
								}
								
								worksVO.setReportTypeFilesList(getRequiredFilesTypeTemplate());
								
								List<PetitionFileVO> filesList = petitionRequiredFilesMap.get(worksVO.getWorkId());
									if(commonMethodsUtilService.isListOrSetValid(filesList)){
										//if(workTypeVOList.size()>1){
											for (PetitionFileVO vo : filesList) {
												if(vo.getKey() != null && vo.getKey().equalsIgnoreCase("COVERING LETTER")){
													for (PetitionFileVO fileTypeVO : globalFilesList) {
														if(fileTypeVO.getKey().equalsIgnoreCase(vo.getKey())){
															fileTypeVO.getFilesList().addAll(vo.getFilesList());
															if(commonMethodsUtilService.isListOrSetValid(vo.getFilesList())){
																for (PetitionFileVO fileVO : filesList) {
																	if(commonMethodsUtilService.isListOrSetValid(fileVO.getFilesList())){
																		for (PetitionFileVO file : fileVO.getFilesList()) {
																			allFilesList.add(new KeyValueVO(file.getId(),file.getValue()));
																		}
																	}
																}
															}
														}
													}
												}else{
													for (PetitionFileVO fileTypeVO : worksVO.getReportTypeFilesList()) {
														if(!fileTypeVO.getKey().equalsIgnoreCase("COVERING LETTER")){
															if(fileTypeVO.getKey().equalsIgnoreCase(vo.getKey())){
																fileTypeVO.getFilesList().addAll(vo.getFilesList());
																if(commonMethodsUtilService.isListOrSetValid(vo.getFilesList())){
																	for (PetitionFileVO fileVO : filesList) {
																		if(commonMethodsUtilService.isListOrSetValid(fileVO.getFilesList())){
																			for (PetitionFileVO file : fileVO.getFilesList()) {
																				allFilesList.add(new KeyValueVO(file.getId(),file.getValue()));
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										//}
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
						
						if(pageType != null && !pageType.equalsIgnoreCase("viewPage")){
							if(commonMethodsUtilService.isListOrSetValid(workTypeVOList)){
								for (PetitionsWorksVO petitionsWorksVO : workTypeVOList) {
									
									Map<Long,Map<Long,Map<Long,List<PetitionsWorksVO>>>> deptWorksMap = new LinkedHashMap<Long,Map<Long,Map<Long,List<PetitionsWorksVO>>>>();
									Map<Long,Map<Long,List<PetitionsWorksVO>>> subjectsMap = new LinkedHashMap<Long, Map<Long,List<PetitionsWorksVO>>>();
									Map<Long,List<PetitionsWorksVO>> subSubjectsMap = new LinkedHashMap<Long, List<PetitionsWorksVO>>();
									List<PetitionsWorksVO> worksList = new ArrayList<PetitionsWorksVO>(0);
									
									if(endorsementDeptWorksMap.get(petitionsWorksVO.getEndorsmentNo()) != null){
										deptWorksMap = endorsementDeptWorksMap.get(petitionsWorksVO.getEndorsmentNo());
										if(deptWorksMap.get(petitionsWorksVO.getDeptId()) !=null){
											subjectsMap = deptWorksMap.get(petitionsWorksVO.getDeptId());
											if(!commonMethodsUtilService.isMapValid(subjectsMap))
												subjectsMap = new LinkedHashMap<Long, Map<Long,List<PetitionsWorksVO>>>();
											
											if(subjectsMap.get(petitionsWorksVO.getSubjectId()) !=null){
												subSubjectsMap = subjectsMap.get(petitionsWorksVO.getSubjectId());
												
												if(!commonMethodsUtilService.isMapValid(subSubjectsMap))
													subSubjectsMap = new LinkedHashMap<Long, List<PetitionsWorksVO>>();
												
												if(subSubjectsMap.get(petitionsWorksVO.getSubSubjectId()) != null){
													worksList = subSubjectsMap.get(petitionsWorksVO.getSubSubjectId());
												}
												if(!commonMethodsUtilService.isListOrSetValid(worksList))
													worksList = new ArrayList<PetitionsWorksVO>(0);
											}
										}
									}
									
									
									worksList.add(petitionsWorksVO);
									subSubjectsMap.put(petitionsWorksVO.getSubSubjectId(), worksList);
									subjectsMap.put(petitionsWorksVO.getSubjectId(), subSubjectsMap);
									deptWorksMap.put(petitionsWorksVO.getDeptId(), subjectsMap);
									endorsementDeptWorksMap.put(petitionsWorksVO.getEndorsmentNo(), deptWorksMap);
								}
							}
						}else{
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
								vo.setReportTypeFilesList(globalFilesList);
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
								
								if(!vo.getEndorsmentNo().isEmpty() && !vo.getEndorsmentNo().trim().equalsIgnoreCase("0"))
									petitionVO.getSubWorksList().add(vo);
								else
									pendignEndorsVO = vo;
							}
							
						}
					}
				}
				
				if(commonMethodsUtilService.isMapValid(endorsementDeptWorksMap)){
					for (String endNo : endorsementDeptWorksMap.keySet()) {
						Map<Long,Map<Long,Map<Long,List<PetitionsWorksVO>>>> deptWorksMap = endorsementDeptWorksMap.get(endNo);
						PetitionsWorksVO endorsVO = new PetitionsWorksVO();
						if(commonMethodsUtilService.isMapValid(deptWorksMap)){
							for (Long deptId : deptWorksMap.keySet()) {
								Map<Long,Map<Long,List<PetitionsWorksVO>>> subjectsMap  = deptWorksMap.get(deptId);
								
								if(commonMethodsUtilService.isMapValid(subjectsMap)){
									for (Long subId : subjectsMap.keySet()) {
										Map<Long,List<PetitionsWorksVO>> subSubjectsMap =subjectsMap.get(subId);
										if(commonMethodsUtilService.isMapValid(subSubjectsMap)){
											for (Long subSubjectId : subSubjectsMap.keySet()) {
												PetitionsWorksVO vo = new PetitionsWorksVO();
												List<PetitionsWorksVO> worksList =subSubjectsMap.get(subSubjectId);																
												if(commonMethodsUtilService.isListOrSetValid(worksList)){
													PetitionsWorksVO worksVO= worksList.get(0);
													if(worksVO != null){
														vo.setEndorsmentNo(worksVO.getEndorsmentNo());
														vo.setEndorsmentDate(worksVO.getEndorsmentDate());
														
														endorsVO.setEndorsmentNo(worksVO.getEndorsmentNo());
														endorsVO.setEndorsmentDate(worksVO.getEndorsmentDate());
														//vo.setNoOfWorks(Long.valueOf(String.valueOf(workTypeVOList.size())));
														//vo.setReportTypeFilesList(globalFilesList);
														///if(pageType != null && !seriesNo.isEmpty())
														//	vo.setUiSeriesNo(Long.valueOf(seriesNo));
														//else
														vo.setEndorsmentNo(worksVO.getEndorsmentNo());
														vo.setDeptId(worksVO.getDeptId());
														vo.setDeptName(worksVO.getDeptName());
														vo.setSubjectId(worksVO.getSubjectId());
														vo.setSubject(worksVO.getSubject());
														vo.setSubSubjectId(worksVO.getSubSubjectId());
														vo.setSubSubject(worksVO.getSubSubject());
														vo.getSubWorksList().addAll(worksList);
														endorsVO.getSubWorksList().add(vo);
													}
												}
											}
										}
									}
								}
								//endorsVO.getSubWorksList().add(vo);
							}
						}
						
						if(!endorsVO.getEndorsmentNo().isEmpty() && !endorsVO.getEndorsmentNo().trim().equalsIgnoreCase("0") && petitionVO != null && commonMethodsUtilService.isListOrSetValid(petitionVO.getSubWorksList()))
							petitionVO.getSubWorksList().add(endorsVO);
						else
							pendignEndorsVO = endorsVO;
					}
				}
				if(pendignEndorsVO != null){
					petitionVO.getSubWorksList().add(pendignEndorsVO);
				}
				 if(commonMethodsUtilService.isMapValid(petitionFilesListMap)){
					 allFilesList.addAll(petitionFilesListMap.values());
				 }
				 if(commonMethodsUtilService.isMapValid(refFilesListMap)){
					 for (Long fileId : refFilesListMap.keySet()) {
						 if(refFilesListMap.get(fileId) != null)
						 allFilesList.addAll(refFilesListMap.get(fileId));
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
				if(commonMethodsUtilService.isListOrSetValid(allFilesList))
					petitionVO.getAllFileList().addAll(allFilesList);
				
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
				if(userVO == null)
					userVO = new UserVO();
				
				List<Object[]> list = pmOfficerUserDAO.getPmOffceUserDetails(userId);
				if(commonMethodsUtilService.isListOrSetValid(list)){
					Object[] userObj = list.get(0);
					if(userObj != null && userObj.length > 0){
						userVO.setDeptName(commonMethodsUtilService.getStringValueForObject(userObj[1]));
						userVO.setDesignation(commonMethodsUtilService.getStringValueForObject(userObj[3]));
						userVO.setUserName(commonMethodsUtilService.getStringValueForObject(userObj[5]));
						userVO.setPhoneNo(commonMethodsUtilService.getStringValueForObject(userObj[6]));
						//userVO.setUserName(commonMethodsUtilService.getStringValueForObject(userObj[3]));
						userVO.setDeptDesignationOfficerId(commonMethodsUtilService.getLongValueForObject(userObj[7]));
						userVO.setDeptDesignationId(commonMethodsUtilService.getLongValueForObject(userObj[8]));
						userVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(userObj[2]));
						userVO.setPmOfficerId(commonMethodsUtilService.getLongValueForObject(userObj[4]));
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
				LOG.error("Exception Occured in getRegistrationPersonDetails ",e);
			}
			return cadrInfoVo;
		}
		
		private List<Long> getAssignedOfficerStatusIdsList(Long userId){
			List<Long> returnList = new ArrayList<>();
			try {
				//UserVO userVO = getPmOffceUserDetails(userId,null);
				
				List<Object[]> list = pmOfficerUserDAO.getPmOffceUserDetails(userId);
				List<Long> deptDesignationIdsList = new ArrayList<>();
				if(commonMethodsUtilService.isListOrSetValid(list)){
					for (Object[] userObj : list) {
						if(userObj != null && userObj.length > 0){
							deptDesignationIdsList.add(commonMethodsUtilService.getLongValueForObject(userObj[8]));
						}
					}
				}
				returnList = pmPetitionAssignedOfficerDAO.getAssingedOfficerStatusIdsList(deptDesignationIdsList);
			} catch (Exception e) {
				LOG.error("Exception Occured in getAssignedOfficerStatusIdsList ",e);
				returnList=null;
			}
			return returnList;
		}
		
		@SuppressWarnings("static-access")
		public RepresenteeViewVO getCompleteOrStatusOverviewDetails(Long userId,String startDate,String endDate){
			RepresenteeViewVO returnVO = new RepresenteeViewVO();
			try {
				Map<Long,List<Long>> petitionsMap= getAssignedPetitionforPetitionDeptDesignationOfficer(userId,null);
				Set<Long> petitionsIdsList = new HashSet<>();
				if(commonMethodsUtilService.isMapValid(petitionsMap)){
					petitionsIdsList = petitionsMap.keySet();
				}
				KeyValueVO deptVO = getDeptIdsListBYUserIds(userId);
				List<Long> deptIds = deptVO.getDeptIdsList();
				List<Long> statusIds = new ArrayList<>();
				
				boolean isAccessDashboard=false;
				
				if(userId != null && userId.longValue()>0L){
					List<Object[]> list = pmOfficerUserDAO.getPmOffceUserDetails(userId);
					if(commonMethodsUtilService.isListOrSetValid(list)){
						for (Object[] param : list) {
							if(!isAccessDashboard){
								if(IConstants.DASHBOARD_ACCESS_OFFICER_DESIGNATION_IDS.contains(commonMethodsUtilService.getLongValueForObject(param[9]))){
									isAccessDashboard=true;
								}
							}
							if(isAccessDashboard)
								break;
						}
					}	
				}
				
				if(isAccessDashboard){
					KeyValueVO userAccesStatusVO = getPmDeptStatusIdsByUserIdsLst(userId,"true");
					statusIds = userAccesStatusVO.getDeptIdsList();
				}else{
					statusIds = getAssignedOfficerStatusIdsList(userId);
				}
				
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
				List<Object[]> objectList = pmSubWorkDetailsDAO.getCompleteOrStatusOverviewDetails(deptIds, fromDate, toDate,"",petitionsIdsList);
				if(commonMethodsUtilService.isListOrSetValid(objectList)){
					
					for (Object[] param : objectList) {
						RepresenteeViewVO statusVO = statuMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
						if(statusVO == null){
							statusVO = new RepresenteeViewVO();
							statusVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
							statusVO.setName(commonMethodsUtilService.getCapitalStringValueForObject(param[3]));
							statuMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), statusVO);
						}
						statusVO.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(param[1]));
						statusVO.getSubWorkIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
						//statusVO.setNoOfWorks(statusVO.getNoOfWorks().longValue()+commonMethodsUtilService.getLongValueForObject(param[0]));
						returnVO.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(param[1]));
						//returnVO.setNoOfWorks(returnVO.getNoOfWorks().longValue()+commonMethodsUtilService.getLongValueForObject(param[0]));
						returnVO.getSubWorkIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
						String estimationCost = commonMethodsUtilService.getStringValueForObject(param[4]);
						if(returnVO.getEstimationCost() != null && returnVO.getEstimationCost() != "" && estimationCost != null && estimationCost != ""){
							BigDecimal decmial= new BigDecimal(returnVO.getEstimationCost());
							BigDecimal decmial2= new BigDecimal(statusVO.getEstimationCost());
							BigDecimal decmial1= new BigDecimal(estimationCost);
							BigDecimal totalCost = decmial.add(decmial1);
							BigDecimal totalCost1 = decmial2.add(decmial1);
							statusVO.setEstimationCost(totalCost1.toString());
							returnVO.setEstimationCost(totalCost.toString());
						}
					}
				}
				
				//List<Object[]> referrerList = pmSubWorkDetailsDAO.getCompleteOrStatusOverviewDetails(deptIds, fromDate, toDate,"statusReferral");
				setObjectListToMap(objectList,statuMap, "statusReferral");
				setObjectListForCompleteOverview(objectList,returnVO, "statusReferral");
				//List<Object[]> subjectList = pmSubWorkDetailsDAO.getCompleteOrStatusOverviewDetails(deptIds, fromDate, toDate,"statusSubject");
				setObjectListToMap(objectList,statuMap, "statusSubject");
				setObjectListForCompleteOverview(objectList,returnVO, "statusSubject");
				//List<Object[]> deptlist = pmSubWorkDetailsDAO.getCompleteOrStatusOverviewDetails(deptIds, fromDate, toDate,"statusDept");
				setObjectListToMap(objectList,statuMap, "statusDept");
				setObjectListForCompleteOverview(objectList,returnVO, "statusDept");
				
				if(commonMethodsUtilService.isMapValid(statuMap)){
					for (Map.Entry<Long, RepresenteeViewVO> entry : statuMap.entrySet()) {
						setOthersDataToLastIndexOfList(entry.getValue().getReferrerList());
						//setOthersDataToLastIndexOfList(entry.getValue().getSubList());
						//setOthersDataToLastIndexOfList(entry.getValue().getDeptList());
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
							inProgressVO.getPetitionIds().addAll(entry.getValue().getPetitionIds());
							if(entry.getValue().getReferrerList() != null && entry.getValue().getReferrerList().size() >0){
								for (RepresenteeViewVO refferVO : entry.getValue().getReferrerList()) {
									RepresenteeViewVO inprogressrefMap = inprogreeReferMap.get(refferVO.getId());
									if(inprogressrefMap == null){
										inprogressrefMap = new RepresenteeViewVO();
										inprogressrefMap.setId(refferVO.getId());
										inprogressrefMap.setName(refferVO.getName());
										inprogreeReferMap.put(inprogressrefMap.getId(), inprogressrefMap);
									}
									//inprogressrefMap.setNoOfWorks(inprogressrefMap.getNoOfWorks()+refferVO.getNoOfWorks());
									inprogressrefMap.getSubWorkIds().addAll(refferVO.getSubWorkIds());
									inprogressrefMap.getPetitionIds().addAll(refferVO.getPetitionIds());
									//inprogressrefMap.setEstimationCost(Long.valueOf(Long.valueOf(inprogressrefMap.getEstimationCost())+Long.valueOf(refferVO.getEstimationCost())).toString());
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
									//inprogressSubMapVO.setNoOfWorks(inprogressSubMapVO.getNoOfWorks()+subjVO.getNoOfWorks());
									inprogressSubMapVO.getSubWorkIds().addAll(subjVO.getSubWorkIds());
									inprogressSubMapVO.getPetitionIds().addAll(subjVO.getPetitionIds());
									//inprogressSubMapVO.setEstimationCost(Long.valueOf(Long.valueOf(inprogressSubMapVO.getEstimationCost())+Long.valueOf(subjVO.getEstimationCost())).toString());
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
									//inprogressDeptMapVO.setNoOfWorks(inprogressDeptMapVO.getNoOfWorks()+deptVO1.getNoOfWorks());
									inprogressDeptMapVO.getSubWorkIds().addAll(deptVO1.getSubWorkIds());
									inprogressDeptMapVO.getPetitionIds().addAll(deptVO1.getPetitionIds());
									//inprogressDeptMapVO.setEstimationCost(Long.valueOf(Long.valueOf(inprogressDeptMapVO.getEstimationCost())+Long.valueOf(deptVO1.getEstimationCost())).toString());
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
				//setOthersDataToLastIndexOfList(returnVO.getSubList());
				//setOthersDataToLastIndexOfList(returnVO.getDeptList());
				
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
						Long id= 0l;
						String name = "";
						Long preferrableId = 0l;
						if(type != null && type.equalsIgnoreCase("statusReferral")){
						  id = commonMethodsUtilService.getLongValueForObject(param[5]);
						  name = commonMethodsUtilService.getStringValueForObject(param[6]);
						  preferrableId = commonMethodsUtilService.getLongValueForObject(param[7]);
						}else if(type != null && type.equalsIgnoreCase("statusSubject")){
							 id = commonMethodsUtilService.getLongValueForObject(param[8]);
							 name = commonMethodsUtilService.getStringValueForObject(param[9]);
							 preferrableId = commonMethodsUtilService.getLongValueForObject(param[10]);
						}else if(type != null && type.equalsIgnoreCase("statusDept")){
							 id = commonMethodsUtilService.getLongValueForObject(param[11]);
							 name = commonMethodsUtilService.getStringValueForObject(param[12]);
							 preferrableId = commonMethodsUtilService.getLongValueForObject(param[13]);
						}
						if(type != null && type.equalsIgnoreCase("statusReferral")){
							//if(id.longValue() != 7l && id.longValue() != 4l && id.longValue() != 2l && id.longValue() != 1l ){
							if(preferrableId.longValue() == 0l || id == 0l ){
								id = 0l; 
								name="OTHERS";
							}
							 VO = getMatchVO(returnVO.getReferrerList(),id);
							 if(VO == null){
								 VO = new  RepresenteeViewVO();
								 returnVO.getReferrerList().add(VO);
							 }
						}else if(type != null && type.equalsIgnoreCase("statusDept")){
							//if(id.longValue() != 34l && id.longValue() != 27l && id.longValue() != 22l && id.longValue() != 5l ){
							if(preferrableId.longValue() == 0l || id == 0l ){
								id = 0l;
								name="OTHERS";
							}
							 VO = getMatchVO(returnVO.getDeptList(),id);
							
							 if(VO == null){
								 VO = new  RepresenteeViewVO();
								 returnVO.getDeptList().add(VO);
							 }
							 if (returnVO!=null) {
				    				Collections.sort( returnVO.getDeptList(), sortListBasedOnId);
				    				
				    			}	
						}else if(type != null && type.equalsIgnoreCase("statusSubject")){
							//if(id.longValue() != 16l && id.longValue() != 3l && id.longValue() != 13l && id.longValue() != 22l){
							if(preferrableId.longValue() == 0l || id == 0l ){
								id = 0l; 
								name="OTHERS";
							}
							 VO = getMatchVO(returnVO.getSubList(),id);
							
							 if(VO == null){
								 VO = new  RepresenteeViewVO();
									 returnVO.getSubList().add(VO);
							 }
							 if (returnVO!=null) {
				    				Collections.sort( returnVO.getSubList(), sortListBasedOnId);
				    				
				    			}		
						}
						 VO.setId(id);
						 VO.setName(name);
						 VO.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(param[1]));
						 VO.getSubWorkIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
						// VO.setNoOfWorks(VO.getNoOfWorks().longValue()+commonMethodsUtilService.getLongValueForObject(param[0]));
						 String estimationCost = commonMethodsUtilService.getStringValueForObject(param[4]);
							if(VO.getEstimationCost() != null && VO.getEstimationCost() != "" && estimationCost != null && estimationCost != ""){
								BigDecimal decmial= new BigDecimal(VO.getEstimationCost());
								BigDecimal decmial1= new BigDecimal(estimationCost);
								BigDecimal totalCost = decmial.add(decmial1);
								VO.setEstimationCost(totalCost.toString());
							}
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
						Long id= 0l;
						String name = "";
						Long preferrableId = 0l;
						if(type != null && type.equalsIgnoreCase("statusReferral")){
						  id = commonMethodsUtilService.getLongValueForObject(param[5]);
						  name = commonMethodsUtilService.getStringValueForObject(param[6]);
						  preferrableId = commonMethodsUtilService.getLongValueForObject(param[7]);
						}else if(type != null && type.equalsIgnoreCase("statusSubject")){
							 id = commonMethodsUtilService.getLongValueForObject(param[8]);
							 name = commonMethodsUtilService.getStringValueForObject(param[9]);
							 preferrableId = commonMethodsUtilService.getLongValueForObject(param[10]);
						}else if(type != null && type.equalsIgnoreCase("statusDept")){
							 id = commonMethodsUtilService.getLongValueForObject(param[11]);
							 name = commonMethodsUtilService.getStringValueForObject(param[12]);
							 preferrableId = commonMethodsUtilService.getLongValueForObject(param[13]);
						}
						if(type != null && type.equalsIgnoreCase("statusReferral")){
							
							//if(id.longValue() != 7l && id.longValue() != 4l && id.longValue() != 2l && id.longValue() != 1l ){
							if(preferrableId.longValue() == 0l || id == 0l){
								id = 0l; 
								name="OTHERS";
							}
							 VO = getMatchVO(statusVO.getReferrerList(),id);
							 if(VO == null){
								 VO = new  RepresenteeViewVO();
									 statusVO.getReferrerList().add(VO);
							}
						}else if(type != null && type.equalsIgnoreCase("statusDept")){
							
							//if(id.longValue() != 34l && id.longValue() != 27l && id.longValue() != 22l && id.longValue() != 5l ){
							if(preferrableId.longValue() == 0l || id == 0l){
								id = 0l;
								name="OTHERS";
							}
							 VO = getMatchVO(statusVO.getDeptList(),id);
							
							 if(VO == null){
								 VO = new  RepresenteeViewVO();
								 statusVO.getDeptList().add(VO);
							 }
							 if (statusVO!=null) {
				    				Collections.sort( statusVO.getDeptList(), sortListBasedOnId);
				    				
				    			}	
							 
						}else if(type != null && type.equalsIgnoreCase("statusSubject")){
							//if(id.longValue() != 16l && id.longValue() != 3l && id.longValue() != 13l && id.longValue() != 22l){
							if(preferrableId.longValue() == 0l || id == 0l){
								id = 0l; 
								name="OTHERS";
							}
							 VO = getMatchVO(statusVO.getSubList(),id);
							
							 if(VO == null){
								 VO = new  RepresenteeViewVO();
								 statusVO.getSubList().add(VO);
							 }
							 if (statusVO!=null) {
				    				Collections.sort( statusVO.getSubList(), sortListBasedOnId);
				    				
				    			}							 						 
						}
						VO.setId(id);
						VO.setName(name);
						VO.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(param[1]));
						//VO.setNoOfWorks(VO.getNoOfWorks().longValue()+commonMethodsUtilService.getLongValueForObject(param[0]));
						VO.getSubWorkIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
						String estimationCost = commonMethodsUtilService.getStringValueForObject(param[4]);
						if(VO.getEstimationCost() != null && VO.getEstimationCost() != "" && estimationCost != null && estimationCost != ""){
							BigDecimal decmial= new BigDecimal(VO.getEstimationCost());
							BigDecimal decmial1= new BigDecimal(estimationCost);
							BigDecimal totalCost = decmial.add(decmial1);
							VO.setEstimationCost(totalCost.toString());
						}
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
				Long itration = 0l;
				if(deptIdsObjLst != null && deptIdsObjLst.size() >0){
					for (Object[] objects : deptIdsObjLst) {
						itration =itration +1l;
						deptVO.getDeptIdsList().add(commonMethodsUtilService.getLongValueForObject(objects[0]));
						deptVO.setDesignation(commonMethodsUtilService.getStringValueForObject(objects[2]));
						deptVO.setDesignationId(commonMethodsUtilService.getLongValueForObject(objects[1]));
						if(deptVO.getDeptIdsList() != null && deptVO.getDeptIdsList().size() >0 && !deptVO.getDeptIdsList().contains(commonMethodsUtilService.getStringValueForObject(objects[0]))){
							if(itration == 1l){
								String dept = deptVO.getDeptName().concat(commonMethodsUtilService.getStringValueForObject(objects[3]));
								deptVO.setDeptName(dept);
							}else{
								String dept = deptVO.getDeptName().concat(","+commonMethodsUtilService.getStringValueForObject(objects[3]));
								deptVO.setDeptName(dept);
							}
							if(deptVO.getDepDesigIds() == null){
								deptVO.setDepDesigIds(new ArrayList<Long>(0));
								deptVO.getDepDesigIds().add(commonMethodsUtilService.getLongValueForObject(objects[4]));
							}else if(!deptVO.getDepDesigIds().contains(commonMethodsUtilService.getLongValueForObject(objects[4]))){
								deptVO.getDepDesigIds().add(commonMethodsUtilService.getLongValueForObject(objects[4]));
							}
							deptVO.getDeptIdsList().add(commonMethodsUtilService.getLongValueForObject(objects[0]));
						}
					}
				}
			}catch(Exception e){
				LOG.error("Exception raised into PmRequestDetailsService of getDeptIdsListBYUserIds() ",e);
			}
			return deptVO;
		}
		
		public KeyValueVO getPmDeptStatusIdsByUserIdsLst(Long userId,String isDashboard){
			KeyValueVO deptVO = new KeyValueVO();
			try{
				LOG.info("entered into PmRequestDetailsService  of getPmDeptStatusIdsByUserIds");
				List<Long> deptIdsObjLst = pmOfficerUserDAO.getPmDeptStatusIdByUserIdsLst(userId,isDashboard);
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
			RepresenteeViewVO leadVO=null;
			try { 
				
				Map<Long,List<Long>> petitionsMap= getAssignedPetitionforPetitionDeptDesignationOfficer(userId,null);
				Set<Long> petitionsIdsList = new HashSet<>();
				if(commonMethodsUtilService.isMapValid(petitionsMap)){
					petitionsIdsList = petitionsMap.keySet();
				}
				
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
				List<Object[]> leadObjects = pmSubWorkDetailsDAO.getLeadWiseOverviewDetails(deptIds, fromDate, toDate,petitionsIdsList);
				if(commonMethodsUtilService.isListOrSetValid(leadObjects)){
					leadMap = new HashMap<Long,RepresenteeViewVO>();
					for (Object[] param : leadObjects) {
						 Long leadId = commonMethodsUtilService.getLongValueForObject(param[1]);
						 if(leadId.longValue() == 12L || leadId.longValue() == 3L || leadId.longValue() == 2L||leadId.longValue() == 4L || leadId.longValue() == 10L || leadId.longValue() == 9L||leadId.longValue() == 6L||leadId.longValue() == 5L){
							leadVO=leadMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
							if(leadVO == null){
								leadVO =new RepresenteeViewVO();
								leadVO.getStatusList().addAll(setLeadStatusTemplate(statusIds));
								leadVO.setId(commonMethodsUtilService.getLongValueForObject(param[1]));
								leadVO.setName(commonMethodsUtilService.getStringValueForObject(param[2]));
								leadVO.setNoOfWorks(leadVO.getNoOfWorks()+commonMethodsUtilService.getLongValueForObject(param[0]));
								leadVO.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(param[5]));
								leadMap.put(commonMethodsUtilService.getLongValueForObject(param[1]), leadVO);
						}else{
							leadVO.setNoOfWorks(leadVO.getNoOfWorks()+commonMethodsUtilService.getLongValueForObject(param[0]));
							leadVO.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(param[5]));
						}
					}else{
						    leadVO=leadMap.get(0L);
						    if(leadVO == null){
						    	leadVO = new RepresenteeViewVO();
						    	leadVO.getStatusList().addAll(setLeadStatusTemplate(statusIds));
						    	leadVO.setId(0L);
						    	leadVO.setName("OTHERS");
						    	leadVO.setNoOfWorks(leadVO.getNoOfWorks()+commonMethodsUtilService.getLongValueForObject(param[0]));
						    	leadVO.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(param[5]));
						    	leadMap.put(0l, leadVO);
						    }else{
						  	  	leadVO.setNoOfWorks(leadVO.getNoOfWorks()+commonMethodsUtilService.getLongValueForObject(param[0]));
						  	    leadVO.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(param[5]));
						    }
					   }
						 String estimationCost = commonMethodsUtilService.getStringValueForObject(param[6]);
							if(leadVO.getEstimationCost() != null && leadVO.getEstimationCost() != "" && estimationCost != null && estimationCost != ""){
								BigDecimal decmial= new BigDecimal(leadVO.getEstimationCost());
								BigDecimal decmial1= new BigDecimal(estimationCost);
								BigDecimal totalCost = decmial.add(decmial1);
								leadVO.setEstimationCost(totalCost.toString());
							}
						if(IConstants.PETITION_COMPLETED_IDS.contains(param[3])){
							RepresenteeViewVO completedVO = getMatchVO(leadVO.getStatusList(), 2l);
							if(completedVO != null){
								completedVO.setName("Completed");
								completedVO.setNoOfWorks(completedVO.getNoOfWorks()+commonMethodsUtilService.getLongValueForObject(param[0]));
								completedVO.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(param[5]));
								if(completedVO.getEstimationCost() != null && completedVO.getEstimationCost() != "" && estimationCost != null && estimationCost != ""){
									BigDecimal decmial= new BigDecimal(completedVO.getEstimationCost());
									BigDecimal decmial1= new BigDecimal(estimationCost);
									BigDecimal totalCost = decmial.add(decmial1);
									completedVO.setEstimationCost(totalCost.toString());
								}
							}
						}else if(IConstants.PETITION_IN_PROGRESS_IDS.contains(param[3])){
							RepresenteeViewVO inprogressVO = getMatchVO(leadVO.getStatusList(), 1l);
							if(inprogressVO != null){
								inprogressVO.setName("Pending");
								inprogressVO.setNoOfWorks(inprogressVO.getNoOfWorks()+commonMethodsUtilService.getLongValueForObject(param[0]));
								inprogressVO.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(param[5]));
								if(inprogressVO.getEstimationCost() != null && inprogressVO.getEstimationCost() != "" && estimationCost != null && estimationCost != ""){
									BigDecimal decmial= new BigDecimal(inprogressVO.getEstimationCost());
									BigDecimal decmial1= new BigDecimal(estimationCost);
									BigDecimal totalCost = decmial.add(decmial1);
									inprogressVO.setEstimationCost(totalCost.toString());
								}
							}	
					}
				}
				if(commonMethodsUtilService.isMapValid(leadMap)){
					leadList = new ArrayList();
					leadList.addAll(leadMap.values());
				}
				if (leadList.size() > 0) {
	    				Collections.sort(leadList, sortListBasedOnId);
	    				
	    			}
				}
			    }catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised into PmRequestDetailsService of getLeadWiseOverviewDetails() ",e);
			}
			return leadList;
		}
		public static Comparator<RepresenteeViewVO> sortListBasedOnId = new Comparator<RepresenteeViewVO>() {
		public int compare(RepresenteeViewVO o1, RepresenteeViewVO o2) {
			if(o1.getId() != null && o2.getId() != null){
				return o2.getId().compareTo(o1.getId());
			}
			return 0;
		}
	};
		
			
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
		
		public String genarateEndorsementNo(String endrsementNo,String userType,String depts,Date date){
			String outputStr="";
			if(endrsementNo != null && endrsementNo.trim().length() >0 && userType !=null && userType.trim().length() >0 && depts != null  && date !=null ){
				String yearStr="";
				String dateStr="";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					Calendar cal =Calendar.getInstance();
					cal.setTime(date);
					Integer year=cal.get(Calendar.YEAR);
					yearStr=year.toString();
				 dateStr=	sdf.format(date);
				 if(userType != null && (userType.equalsIgnoreCase("AP MINISTER") || userType.equalsIgnoreCase("MINISTER"))){
					 userType = "M";
				 }else if(userType != null && userType.contains("OSD")){
					 userType = "OSD";
				 }else{
					 userType = "";
				 }
				 if(depts == null){
					 depts = "";
				 }
				 
				 String[] strArr = dateStr.split("-");
				 String dateFormat = strArr[0]+"."+strArr[1]+"."+strArr[2];
				//String deptStr = commonMethodsUtilService.convertStringFromListWithOutDuplicates(depts);
				outputStr=endrsementNo+"/"+userType+"("+depts+")/"+yearStr+", Dt: "+dateFormat;
			}
			return "Endt NO. "+outputStr;
		}
		public ResultStatus updatePetitionsStatusDetails(Long userId,List<Long> petitionIdsList, List<Long> subWorkIdsList,String remark,Long statusId){
			return null;
		}
		
		public ResultStatus generateCoveringLetterForPetition(InputVO inputVO){
			ResultStatus resultStatus = new ResultStatus();
			try {
				
				//Department ids  Converting to List<Long> to List<String>
				List<String> deptIds = null;
				if(commonMethodsUtilService.isListOrSetValid(inputVO.getDeptIdsList())){
					deptIds = new ArrayList<String>();
					for (Long deptId : inputVO.getDeptIdsList()) {
						deptIds.add(deptId.toString());
					}
				}
				if(inputVO.getLeadName() != null && !inputVO.getLeadName().equalsIgnoreCase("0")){
					PmBriefLead briefLead = pmBriefLeadDAO.get(Long.valueOf(inputVO.getLeadName()));
					inputVO.setLeadName(briefLead.getBriefLead());
				}
				
				if(inputVO.getGroupName() != null && !inputVO.getGroupName().equalsIgnoreCase("0")){
					PmGrant pmGrant = pmGrantDAO.get(Long.valueOf(inputVO.getGroupName()));
				 inputVO.setGroupName(pmGrant.getPmGrantName());//grantType
				}
				Long ofcrId = pmDepartmentDesignationOfficerDAO.geOfficerIdByDeptDesigIds(inputVO.getSubProgramIdsList());
				/*Long ofcrId = 0l;
				if(ofcrIds != null && ofcrIds.size() >0){
					 ofcrId = (Long)ofcrIds.get(0);
					//ofcrId = commonMethodsUtilService.getLongValueForObject(list[0]);
				}*/
				List<Object[]> coveringLetrImages = pmRequiredLettersImagesDAO.getDesignationWiseImages(inputVO.getDesignationIds(), inputVO.getType(),ofcrId);
			
				/*Object[] maxList = pmSubWorkDetailsDAO.getMaxEndorsementAndTempEndorsementNos();
				Long saveTempEndorseNo = 0l;
				int saveStatus = 0;
				if(maxList != null){
					Long maxEndorNo = commonMethodsUtilService.getLongValueForObject(maxList[0]);
					Long tempEnodrse = commonMethodsUtilService.getLongValueForObject(maxList[1]);
					if(maxEndorNo.longValue()>tempEnodrse.longValue()){
						saveTempEndorseNo = maxEndorNo.longValue() +1l;
						 saveStatus = pmSubWorkDetailsDAO.saveTempEndorseNo(inputVO.getPageId(),inputVO.getSchemeIdsList(),saveTempEndorseNo.toString(),inputVO.getBlockLevelId(),dateUtilService.getCurrentDateAndTime());
						//inputVO.setEndValue(saveTempEndorseNo.toString());
					}else if(tempEnodrse.longValue()>maxEndorNo.longValue()){
						saveTempEndorseNo =tempEnodrse.longValue() +1l;
						 saveStatus = pmSubWorkDetailsDAO.saveTempEndorseNo(inputVO.getPageId(),inputVO.getSchemeIdsList(),saveTempEndorseNo.toString(),inputVO.getBlockLevelId(),dateUtilService.getCurrentDateAndTime());
						//inputVO.setEndValue(saveTempEndorseNo.toString());
					}
				}
				if(saveStatus != 0){
					inputVO.setEndValue(saveTempEndorseNo.toString());
				}*/
				String str1 = pmRequiredFileFormatTextDAO.getCoverLetterMessage(inputVO.getDesignationIds());
				String endorseStr = genarateEndorsementNo(inputVO.getEndValue(),inputVO.getDisplayType(),inputVO.getDeptCode(),dateUtilService.getCurrentDateAndTime());
				inputVO.setCategory(endorseStr);
				PmRequestEditVO petitionDetailsVO =setPmRepresenteeDataToResultView(inputVO.getPageId(),inputVO.getpType(),inputVO.getBlockLevelId());
				String staticPath = commonMethodsUtilService.createInnerFolders(IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER+"/Covering_Letter");
							if(staticPath != null && staticPath.equalsIgnoreCase("FAILED"))
								throw new Exception("File path not available . Please check once file path.");
				//String datePath = commonMethodsUtilService.generateImagePathWithDateTime();
				String fileName = inputVO.getEndValue()+"_"+Math.abs(new Random().nextInt())+".PDF";
				//String fileName = datePath+"_"+inputVO.getEndValue()+".PDF";
				String filePath = ITextCoveringLetterGeneration.generateCOVERINGLETTER(inputVO,coveringLetrImages,endorseStr,petitionDetailsVO,str1,staticPath,fileName);
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
									updatePetitionTracking(pmTrackingVO,null);
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
								updatePetitionTracking(pmTrackingVO,null);
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
		public void updatePetitionTracking(PetitionTrackingVO pmTrackingVO,Date uploadDate){
			try {
				PmTracking pmTracking = new PmTracking();
				pmTracking.setPetitionId(pmTrackingVO.getPetitionId());
				pmTracking.setPmSubWorkDetailsId(pmTrackingVO.getPmSubWorkDetailsId());
				pmTracking.setPmStatusId(pmTrackingVO.getPmStatusId());
				pmTracking.setRemarks(pmTrackingVO.getRemarks());
				pmTracking.setPmTrackingActionId(pmTrackingVO.getPmTrackingActionId());
				pmTracking.setPmActionTypeId(pmTrackingVO.getPmActionTypeId());
				pmTracking.setActionType(pmTrackingVO.getActionType());
				//pmTracking.setPmDepartmentDesignationOfficerId(pmTrackingVO.getPmDeptDesignationOfficerId());
				pmTracking.setDocumentId(pmTrackingVO.getDocumentId());
				pmTracking.setInsertedUserId(pmTrackingVO.getUserId());
				pmTracking.setUpdateUserId(pmTrackingVO.getUserId());
				pmTracking.setPmDocumentTypeId(pmTrackingVO.getPmDocumentTypeId());
				if(uploadDate == null){
					pmTracking.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					pmTracking.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				}else{
					pmTracking.setInsertedTime(uploadDate);
					pmTracking.setUpdatedTime(uploadDate);
				}
				pmTracking.setAssignedToPmDepartmentDesignationOfficerId(pmTrackingVO.getAssignedToPmPetitionAssignedOfficerId());
				
				UserVO userVO = getPmOffceUserDetails(pmTrackingVO.getUserId(),null);
				if(userVO != null){
					if(userVO.getDeptDesignationOfficerId() != null && userVO.getDeptDesignationOfficerId().longValue()>0L)
						pmTracking.setPmDepartmentDesignationOfficerId(userVO.getDeptDesignationOfficerId());
					if(userVO.getDeptDesignationId() != null && userVO.getDeptDesignationId().longValue()>0L)
						pmTracking.setPmDepartmentDesignationId(userVO.getDeptDesignationId());
				}
				pmTracking.setIsDeleted("N");
				pmTrackingDAO.save(pmTracking);
				
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised into PmRequestDetailsService of updatePetitionTracking() ",e);
			}
		}
		
		public List<KeyValueVO> getLoginUserAccessSubDeptDesignationDetail(List<Long> deptIdsList , Long userId){
			 List<KeyValueVO>  returnList = new ArrayList<KeyValueVO>();
			try {
				List<Long> deptDesignationIdsList = pmOfficerUserDAO.getPmDeptDesignationIdByUserId(userId);
				if(commonMethodsUtilService.isListOrSetValid(deptDesignationIdsList)){
					List<Object[]> childDeptDesignationsList = pmDepartmentDesignationHierarchyDAO.getSubDesignationDetailsForParentDeptDesignations(deptDesignationIdsList,deptIdsList);
					if(commonMethodsUtilService.isListOrSetValid(childDeptDesignationsList)){
						for (Object[] param : childDeptDesignationsList) {
							//returnList.add(new KeyValueVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])+" - "+commonMethodsUtilService.getStringValueForObject(param[2])));
							returnList.add(new KeyValueVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(param[1]))));
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
			 List<KeyValueVO>  returnList = new ArrayList<KeyValueVO>();
			try {
				if(deptDesignationId != null && deptDesignationId.longValue()>0L){
					List<Object[]> deptDesignationOfficerDetails = pmDepartmentDesignationOfficerDAO.getDeptDesignationOfficerDetailsByDeptDesignation(deptDesignationId);
					if(commonMethodsUtilService.isListOrSetValid(deptDesignationOfficerDetails)){
						for (Object[] param : deptDesignationOfficerDetails) {
							Long pmDepartmentDesignationOfficerId = commonMethodsUtilService.getLongValueForObject(param[0]);
							String officerName = commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(param[1]));
							String mobileNo = commonMethodsUtilService.getStringValueForObject(param[2]);
							String dept =  "";//commonMethodsUtilService.getStringValueForObject(param[3]);
							String designation = commonMethodsUtilService.getStringValueForObject(param[4]);
							
							String finalName = dept+"   "+officerName+"-"+mobileNo+" ("+designation+") ";
							if(mobileNo.isEmpty())
								finalName = dept+"   "+officerName+" ("+designation+")";
							if(officerName.equalsIgnoreCase(designation))
								finalName = officerName+"   ("+designation+")";
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
		
		public boolean isUpdateStatusForAssigningPetition(String assignBy, String assignTo, RepresenteeViewVO inputVO){
			try {
				
				if(inputVO.getActionType() != null && inputVO.getActionType().equalsIgnoreCase("COMPLETED")){
					if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L)
						return true;
				}
				else if(inputVO.getActionType() != null && !inputVO.getActionType().equalsIgnoreCase("ASSIGNED")){
					if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L)
						return true;
				}
				else if(assignBy.equalsIgnoreCase("STATE") && assignTo.equalsIgnoreCase("STATE")){
					if(inputVO.getActionType() != null && !inputVO.getActionType().equalsIgnoreCase("ASSIGNED")){
						if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L)
							return true;
					}
				}
				else if(assignBy.equalsIgnoreCase("STATE") && assignTo.equalsIgnoreCase("DISTRICT")){
					if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L)
						return true;
				}
				else if(assignBy.equalsIgnoreCase("DISTRICT") && assignTo.equalsIgnoreCase("STATE")){
					if(inputVO.getActionType() != null && !inputVO.getActionType().equalsIgnoreCase("ASSIGNED")){
						if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L)
							return true;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised into PmRequestDetailsService of isUpdateStatusForAssigningPetition() ",e);
			}
			return false;
		}
		
		public List<KeyValueVO> getPetitionAssignedPrincipalSecretoryDetails(Long petitionId){
			List<KeyValueVO>  assignedPrincipalSecretoryList = null;
			try {
				List<Object[]> list   = pmPetitionAssignedOfficerDAO.getPetitionAssignedPrinciplSecretoryDetails(petitionId);
				if(commonMethodsUtilService.isListOrSetValid(list)){
					assignedPrincipalSecretoryList = new ArrayList<>();
					for (Object[] param : list) {
						assignedPrincipalSecretoryList.add(new KeyValueVO(commonMethodsUtilService.getLongValueForObject(param[0]),"",commonMethodsUtilService.getLongValueForObject(param[1])));
					}
				}
			} catch (Exception e) {
				LOG.error("Exception raised into PmRequestDetailsService of getPetitionAssignedPrincipalSecretoryDetails() ",e);
			}
			return assignedPrincipalSecretoryList;
		}
		public ResultStatus endorsingSubWorksAndAssigningToOfficer(RepresenteeViewVO inputVO){
			ResultStatus resultStatus = new ResultStatus();
			try {
				if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue() == 1L)
					inputVO.setStatusId(6L);//move to pending action memo
				Date updatedTime=dateUtilService.getCurrentDateAndTime();
				resultStatus.setExceptionMsg("FAIL");
				if(inputVO.getActionType() != null){
					String endorsmentNo="";
					PmSubWorkDetails pmSubWorkDetails = null;
					Set<Long> workIds = new HashSet<Long>(inputVO.getWorkIds());
					PmPetitionAssignedOfficer pmPetitionAssignedOfficer = null;
					Document document =null;
					UserVO userVO = getPmOffceUserDetails(inputVO.getId(),null);
										
					if(commonMethodsUtilService.isListOrSetValid(workIds)){
						boolean alreadyUpdaeted=false; // only once documents should be tracked for petition, but for works multiple times.
						for (Long subWorkId : workIds) {
							pmSubWorkDetails = pmSubWorkDetailsDAO.get(subWorkId);
							if(inputVO.getActionType() != null && inputVO.getActionType().equalsIgnoreCase("ASSIGNED")){
								inputVO.setStatusId(pmSubWorkDetails.getPmStatusId());
							}
							if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L){
								
								endorsmentNo = pmSubWorkDetails.getWorkEndorsmentNo();
								if(pmSubWorkDetails != null){
									if(pmSubWorkDetails.getPmStatusId() !=null && (!IConstants.PETITION_COMPLETED_IDS.contains(pmSubWorkDetails.getPmStatusId().longValue()))){
										
										if(pmSubWorkDetails != null){
											if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L && (inputVO.getStatusId().longValue() == 6L) ){
												if(pmSubWorkDetails.getWorkEndorsmentNo() == null || pmSubWorkDetails.getWorkEndorsmentNo().trim().isEmpty()){
													if(inputVO.getLeadId() != null && inputVO.getLeadId().longValue()>0L)
														pmSubWorkDetails.setPmBriefLeadId(inputVO.getLeadId());
													if(inputVO.getGrantId() != null && inputVO.getGrantId().longValue()>0L)
														pmSubWorkDetails.setPmGrantId(inputVO.getGrantId());
													if(inputVO.getStatusId().longValue() == 6L){
															pmSubWorkDetails.setWorkEndorsmentNo(inputVO.getEndorsementNO());
															pmSubWorkDetails.setEndorsmentDate(updatedTime);
													}
												}
											}
											endorsmentNo =  pmSubWorkDetails != null?pmSubWorkDetails.getWorkEndorsmentNo():inputVO.getEndorsementNO() != null?inputVO.getEndorsementNO():"";
											pmSubWorkDetails.setUpdatedTime(updatedTime);
											pmSubWorkDetails.setUpdatedUserId(inputVO.getId());
											/*if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L && (inputVO.getStatusId().longValue() == 4L || inputVO.getStatusId().longValue() == 5L || 
													inputVO.getStatusId().longValue() == 6L) ){
												pmSubWorkDetails.setPmStatusId(inputVO.getStatusId());
											}*/
											boolean isUpdate = true;//isUpdateStatusForAssigningPetition(assignBy, assignTo, inputVO);
											if(isUpdate){
												if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L){
													pmSubWorkDetails.setPmStatusId(inputVO.getStatusId());
												}
											}else{// if not status change existing status will be there.
												inputVO.setStatusId(pmSubWorkDetails.getPmStatusId());
											}
												pmSubWorkDetailsDAO.save(pmSubWorkDetails);
										}
										
										
										if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L && (inputVO.getStatusId().longValue() == 6L || inputVO.getStatusId().longValue() == 7L || inputVO.getActionType() != null ) ){
											if(inputVO.getDeptDesigOffcrId() != null ){
												pmPetitionAssignedOfficer = new PmPetitionAssignedOfficer();
												if(inputVO.getStatusId().longValue()==3L && (inputVO.getDeptDesigOffcrId() == null || inputVO.getDeptDesigOffcrId().longValue()==0L)){
													List<KeyValueVO>  assignedPrincipalSecretoryList = getPetitionAssignedPrincipalSecretoryDetails(pmSubWorkDetails.getPetitionId());
													if(commonMethodsUtilService.isListOrSetValid(assignedPrincipalSecretoryList)){
														KeyValueVO vo = assignedPrincipalSecretoryList.get(0);
														if(vo!=null){
															inputVO.setDeptDesigOffcrId(vo.getKey());
															inputVO.setDeptDesigId(vo.getCount());
														}
													}
												}
												
												if(commonMethodsUtilService.getLongValueForObject(inputVO.getPetitionId()) >0l && (commonMethodsUtilService.getLongValueForObject(inputVO.getDeptDesigOffcrId()) >0l)){
													List<Object[]> list = pmDepartmentDesignationOfficerDAO.getDeptDesignationOfficerDetailsByDeptAndOffId(inputVO.getDeptDesigId(),inputVO.getDeptDesigOffcrId());
													Long deptDesigId = 0l;
													Long deptDesigOfficerId = 0l;
													if(commonMethodsUtilService.isListOrSetValid(list)){
														Object[] param = list.get(0);
														//inputVO.setDeptDesigOffcrId(commonMethodsUtilService.getLongValueForObject(param[0]));
														//inputVO.setDeptDesigId(commonMethodsUtilService.getLongValueForObject(param[1]));
														deptDesigOfficerId = commonMethodsUtilService.getLongValueForObject(param[0]);
														deptDesigId = commonMethodsUtilService.getLongValueForObject(param[1]);
													}else{
														throw new Exception(" Assign to members data not available ");
													}
													pmPetitionAssignedOfficer.setPetitionId(inputVO.getPetitionId());
													pmPetitionAssignedOfficer.setPmSubWorkDetailsId(subWorkId);
													pmPetitionAssignedOfficer.setPmDepartmentDesignationId(deptDesigId);
													pmPetitionAssignedOfficer.setPmDepartmentDesignationOfficerId(deptDesigOfficerId);
													pmPetitionAssignedOfficer.setRemarks(inputVO.getRemark());
													pmPetitionAssignedOfficer.setIsDeleted("N");
													pmPetitionAssignedOfficer.setInsertedTime(updatedTime);
													pmPetitionAssignedOfficer.setInsertedUserId(inputVO.getId());
													pmPetitionAssignedOfficer.setUpdatedTime(updatedTime);
													pmPetitionAssignedOfficer.setUpdatedUserId(inputVO.getId());
													
													if(userVO != null){
														List<Object[]> list1 = pmDepartmentDesignationOfficerDAO.getDeptDesignationOfficerDetailsByDeptAndOffId(userVO.getDesignationId(),userVO.getPmOfficerId());
														if(commonMethodsUtilService.isListOrSetValid(list1)){
															Object[] param = list1.get(0);
															pmPetitionAssignedOfficer.setAssignedByPmDepartmentDesignationOfficerId(commonMethodsUtilService.getLongValueForObject(param[0]));
														}else{
															throw new Exception(" Assign by members data not available ");
														}
													}
													pmPetitionAssignedOfficer.setAssignedToPmDepartmentDesignationOfficerId(deptDesigId);
													
													pmPetitionAssignedOfficer.setActionType(inputVO.getActionType());
													if( inputVO.getActionTypeId() != null && inputVO.getActionTypeId().longValue()>0L)
														pmPetitionAssignedOfficer.setPmActionTypeId(inputVO.getActionTypeId());
													
													pmPetitionAssignedOfficer.setPmStatusId(inputVO.getStatusId());
													pmPetitionAssignedOfficer = pmPetitionAssignedOfficerDAO.save(pmPetitionAssignedOfficer);
												}
											}
										}
										
										if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L && inputVO.getStatusId().longValue() == 6L){
											if(inputVO.getCoverLetterPath() != null & !inputVO.getCoverLetterPath().isEmpty()){
												document = new Document();
												document.setPath(inputVO.getCoverLetterPath());
												document.setInsertedTime(updatedTime);
												document.setInsertedUserId(inputVO.getId());
												document = documentDAO.save(document);
												if(document != null)
													resultStatus =saveCoveringLetterDocument(updatedTime,inputVO,pmPetitionAssignedOfficer,inputVO.getRemark(),inputVO.getWorkIds(),document.getDocumentId(),inputVO.getId(), endorsmentNo,inputVO.getPetitionId(),inputVO.getStatusType(),inputVO.getStatusId(),inputVO.getDocumentTypeId(),inputVO.getRefNo());
											}
											
										}else if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L && inputVO.getStatusId().longValue() != 6L){
											String status="";
											if(commonMethodsUtilService.isListOrSetValid(inputVO.getFilesList())){
												for (MultipartFile file : inputVO.getFilesList()) {
													document = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL+IConstants.PETITIONS_FOLDER,inputVO.getId(),null);
													if(document != null)
														resultStatus =saveCoveringLetterDocument(updatedTime,inputVO,pmPetitionAssignedOfficer,inputVO.getRemark(),inputVO.getWorkIds(),document.getDocumentId(),
																inputVO.getId(), endorsmentNo,inputVO.getPetitionId(),inputVO.getStatusType(),inputVO.getStatusId(),inputVO.getDocumentTypeId(),inputVO.getRefNo());
												}
											}
										}else{
											PetitionTrackingVO pmTrackingVO = new PetitionTrackingVO();
											pmTrackingVO.setPmActionTypeId(inputVO.getActionTypeId());
											pmTrackingVO.setActionType(inputVO.getActionType());
											if(inputVO.getStatusId() == null || inputVO.getStatusId().longValue()==0L){
												pmTrackingVO.setPmStatusId(pmSubWorkDetailsDAO.get(subWorkId).getPmStatusId());// latest statusId , while updating only remarks for tracking
												pmTrackingVO.setPmTrackingActionId(3L);//COMMENT 
											}else{
												pmTrackingVO.setPmStatusId(inputVO.getStatusId());
												pmTrackingVO.setPmTrackingActionId(2L);//STATUS CHANGED
												if(document != null)
													pmTrackingVO.setPmTrackingActionId(4L);//File Uploded
											}
											if(pmPetitionAssignedOfficer != null)
												pmTrackingVO.setAssignedToPmPetitionAssignedOfficerId(pmPetitionAssignedOfficer.getPmPetitionAssignedOfficerId());
											
											pmTrackingVO.setUserId(inputVO.getId());
											pmTrackingVO.setPetitionId(inputVO.getPetitionId());
											pmTrackingVO.setRemarks(inputVO.getRemark());
											if(document != null)
												pmTrackingVO.setDocumentId(document.getDocumentId());
											pmTrackingVO.setPmSubWorkDetailsId(subWorkId);
											//updatePetitionTracking(pmTrackingVO); // works wise tracking 
											if(inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L && !alreadyUpdaeted){
												if(inputVO.getStatusId().longValue() ==4L && inputVO.getStatusId().longValue() ==5L && inputVO.getStatusId().longValue() ==8L)
													pmTrackingVO.setPmStatusId(8L);
												else
													pmTrackingVO.setPmStatusId(2L);
													pmTrackingVO.setPmSubWorkDetailsId(null);// for peition tracking
													updatePetitionTracking(pmTrackingVO,updatedTime);
										}
									}
								}else if(inputVO != null && inputVO.getStatusId() != null){
									
									pmSubWorkDetails.setPmStatusId(inputVO.getStatusId());
									pmSubWorkDetails.setUpdatedTime(updatedTime);
									pmSubWorkDetails.setUpdatedUserId(inputVO.getId());
									pmSubWorkDetailsDAO.save(pmSubWorkDetails);
									
									PetitionTrackingVO pmTrackingVO = new PetitionTrackingVO();
									pmTrackingVO.setPmActionTypeId(inputVO.getActionTypeId());
									pmTrackingVO.setActionType("COMPLETED");
									pmTrackingVO.setUserId(inputVO.getId());
									pmTrackingVO.setPetitionId(inputVO.getPetitionId());
									pmTrackingVO.setRemarks(inputVO.getRemark());
									pmTrackingVO.setPmTrackingActionId(3L);//COMMENT 
									pmTrackingVO.setPmSubWorkDetailsId(subWorkId);
									updatePetitionTracking(pmTrackingVO,updatedTime); // works wise tracking 
								}
							}
							}
						}
					}else{
						if(inputVO.getStatusId() == null || inputVO.getStatusId().longValue()==0L){
							PetitionTrackingVO pmTrackingVO = new PetitionTrackingVO();
							pmTrackingVO.setPmActionTypeId(inputVO.getActionTypeId());
							pmTrackingVO.setActionType(inputVO.getActionType());
							pmTrackingVO.setUserId(inputVO.getId());
							pmTrackingVO.setPetitionId(inputVO.getPetitionId());
							pmTrackingVO.setRemarks(inputVO.getRemark());
							pmTrackingVO.setPmTrackingActionId(3L);//COMMENT 
							if(document != null){
								pmTrackingVO.setDocumentId(document.getDocumentId());
								//pmTrackingVO.setPmDocumentTypeId(4l);
							}
							updatePetitionTracking(pmTrackingVO,updatedTime); // works wise tracking 
						}
					}
					
					if(inputVO.getPetitionId() != null && inputVO.getPetitionId().longValue()>0L && inputVO.getStatusId() != null && inputVO.getStatusId().longValue()>0L){
						Petition petition = pititionDAO.get(inputVO.getPetitionId());
						if(petition != null){
							if(inputVO.getStatusId().longValue() ==4L || inputVO.getStatusId().longValue() ==5L || inputVO.getStatusId().longValue() ==8L){
								petition.setPmStatusId(8L);
								List<Object[]> list = pmSubWorkDetailsDAO.getAllWorksLatesStatusDetails(inputVO.getPetitionId());
								List<Long> statusIdsList = new ArrayList<Long>();
								if(commonMethodsUtilService.isListOrSetValid(list)){
									for (Object[] param : list) {
										Long statusId = commonMethodsUtilService.getLongValueForObject(param[2]);
										// not possible || not next year || not completed
										if(statusId.longValue() != 4L && statusId.longValue() !=5L && statusId.longValue() !=8L)
											statusIdsList.add(statusId);
									}
									/*if(petition.getNoOfWorks() == list.size()){
										petition.setPmStatusId(8L);
									}else{
										petition.setPmStatusId(9L);
									}*/
								}
								
								if(!commonMethodsUtilService.isListOrSetValid(statusIdsList)){
									petition.setPmStatusId(8L);//Completed petition
									
									PetitionTrackingVO pititionTrackingVO = new PetitionTrackingVO();
									pititionTrackingVO.setPmActionTypeId(inputVO.getActionTypeId());
									pititionTrackingVO.setActionType(inputVO.getActionType());
									pititionTrackingVO.setPmStatusId(8L);
									pititionTrackingVO.setUserId(inputVO.getId());
									pititionTrackingVO.setPetitionId(inputVO.getPetitionId());
									pititionTrackingVO.setRemarks(inputVO.getRemark());
									pititionTrackingVO.setPmTrackingActionId(2L);//STATUS CHANGED
									if(document != null){
										pititionTrackingVO.setDocumentId(document.getDocumentId());
										//pititionTrackingVO.setPmTrackingActionId(4L);//Upload file
										pititionTrackingVO.setPmDocumentTypeId(4l);
									}
									if(pmPetitionAssignedOfficer != null)
										pititionTrackingVO.setAssignedToPmPetitionAssignedOfficerId(pmPetitionAssignedOfficer.getPmPetitionAssignedOfficerId());
									updatePetitionTracking(pititionTrackingVO,updatedTime);// petition wise tracking 
								}
							}else if(inputVO.getStatusId().longValue() ==6L){
								if(petition.getPmStatusId() != null && petition.getPmStatusId().longValue() !=2L){
									petition.setPmStatusId(2L); // in progress
									
									PetitionTrackingVO pititionTrackingVO = new PetitionTrackingVO();
									pititionTrackingVO.setPmActionTypeId(inputVO.getActionTypeId());
									pititionTrackingVO.setActionType(inputVO.getActionType());
									pititionTrackingVO.setPmStatusId(2L);
									pititionTrackingVO.setUserId(inputVO.getId());
									pititionTrackingVO.setPetitionId(inputVO.getPetitionId());
									pititionTrackingVO.setRemarks(inputVO.getRemark());
									pititionTrackingVO.setPmTrackingActionId(4L);//covering letter uploaded
									if(document != null){
										pititionTrackingVO.setDocumentId(document.getDocumentId());
										//pititionTrackingVO.setPmDocumentTypeId(4l);
									}
									if(pmPetitionAssignedOfficer != null)
										pititionTrackingVO.setAssignedToPmPetitionAssignedOfficerId(pmPetitionAssignedOfficer.getPmPetitionAssignedOfficerId());
									updatePetitionTracking(pititionTrackingVO,updatedTime);// petition wise tracking 
								}
							}
							
							petition.setEndorsmentNo(pmSubWorkDetails.getWorkEndorsmentNo());
							petition.setEndorsmentDate(pmSubWorkDetails.getEndorsmentDate());
							
							pititionDAO.save(petition);
						}
					}
					
					resultStatus.setExceptionMsg("SUCCESS");
				}
			} catch (Exception e) {
				e.printStackTrace();
				resultStatus.setExceptionMsg("FAIL");
				LOG.error("Exception raised into PmRequestDetailsService of endorsingSubWorksAndAssigningToOfficer() ",e);
			}
			return resultStatus;
		}
		public ResultStatus saveCoveringLetterDocument(Date updatedTime,RepresenteeViewVO inputVO,PmPetitionAssignedOfficer pmPetitionAssignedOfficer,String remarks,List<Long> subWorkIds,
				Long documentId,Long userId,String endorsmentNo,Long petitonId,String reportType,Long statusId,Long documentTypeId,String refNo){
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
						if(documentTypeId != null && documentTypeId.longValue() >0l){
							pmSubWorkCoveringLetter.setDocumentTypeId(documentTypeId);
						}/*else{
							pmSubWorkCoveringLetter.setDocumentTypeId(4l);
						}*/
						pmSubWorkCoveringLetter.setRefNo(refNo);
						pmSubWorkCoveringLetter = pmSubWorkCoveringLetterDAO.save(pmSubWorkCoveringLetter);
						PetitionTrackingVO pmTrackingVO = new PetitionTrackingVO();
						pmTrackingVO.setPmStatusId(statusId);// PENDING ACTION MEMO 
						pmTrackingVO.setUserId(userId);
						pmTrackingVO.setPetitionId(petitonId);
						if(statusId == 6L)
							pmTrackingVO.setRemarks("Uploaded covering letter");
						else if(statusId == 7L)
							pmTrackingVO.setRemarks("uploaded action memo document");
						else if(statusId == 3L)
							pmTrackingVO.setRemarks("Uploaded detailed report document");
						pmTrackingVO.setActionType(inputVO.getActionType());
						if(pmTrackingVO.getRemarks() != null && !pmTrackingVO.getRemarks().isEmpty())
							pmTrackingVO.setRemarks(pmTrackingVO.getRemarks()+" , "+remarks);
						pmTrackingVO.setPmDocumentTypeId(documentTypeId);
						pmTrackingVO.setPmTrackingActionId(4L);//FILE UPLOAD
						pmTrackingVO.setDocumentId(documentId);
						pmTrackingVO.setPmSubWorkDetailsId(subWorkId);
						if(pmPetitionAssignedOfficer != null)
							pmTrackingVO.setAssignedToPmPetitionAssignedOfficerId(pmPetitionAssignedOfficer.getPmPetitionAssignedOfficerId());
						updatePetitionTracking(pmTrackingVO,updatedTime);
						
						pmTrackingVO.setPmSubWorkDetailsId(null);
						updatePetitionTracking(pmTrackingVO,updatedTime);
						
					}
				}
				status.setExceptionMsg("SUCCESS");
			} catch (Exception e) {
				LOG.error("Exception Occured in PmRequestDetailsService @ savePetitionReffererDocument() "+e.getMessage());
				status.setExceptionMsg("FAIL");
			}
			return status;
		}
		
		public RepresenteeViewVO getReferralWiseOverviewDetails(InputVO inputVO){ 
			RepresenteeViewVO returnVO = new RepresenteeViewVO();
			try {
				Long userId= inputVO.getLocationId();
				Map<Long,List<Long>> petitionsMap= getAssignedPetitionforPetitionDeptDesignationOfficer(userId,null);
				Set<Long> petitionsIdsList = new HashSet<>();
				if(commonMethodsUtilService.isMapValid(petitionsMap)){
					petitionsIdsList = petitionsMap.keySet();
				}
				
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				Date startDate = null;
				Date endDate = null;
				KeyValueVO deptVO = getDeptIdsListBYUserIds(inputVO.getLocationId());
				inputVO.setDeptIdsList(deptVO.getDeptIdsList());
				if(inputVO.getFromDate() != null && inputVO.getToDate() != null && !inputVO.getFromDate().isEmpty() && !inputVO.getToDate().isEmpty()){
					startDate = format.parse(inputVO.getFromDate());
					endDate = format.parse(inputVO.getToDate());
				}
				List<Object[]> referralList = pmSubWorkDetailsDAO.getReferralWiseOverviewDetails(inputVO,startDate,endDate,petitionsIdsList);
				//if(inputVO.getDesignationIds() != null && inputVO.getDesignationIds().size() >0){
					setReferralDesignationsDetails(referralList,returnVO.getReferrerList());
				//}else{
					setDesignationWiseCount(referralList,returnVO.getSubList());
				//}
					
					//setOthersDataToLastIndexOfList(returnVO.getSubList());
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception Occured in PmRequestDetailsService @ getReferralWiseOverviewDetails() "+e.getMessage());
			}
			return returnVO;
		}
		public void setDesignationWiseCount(List<Object[]> list,List<RepresenteeViewVO> returnList){
			try{
				
				Map<Long,RepresenteeViewVO> map = new HashMap<Long,RepresenteeViewVO>();
				if(commonMethodsUtilService.isListOrSetValid(list)){
					for (Object[] objects : list) {
						Long id = commonMethodsUtilService.getLongValueForObject(objects[4]);
						String name =  commonMethodsUtilService.getStringValueForObject(objects[5]);
						Long preferrableDesiId = commonMethodsUtilService.getLongValueForObject(objects[9]);
						//if(id.longValue() != 1l && id.longValue() != 2l && id.longValue() != 7l){
						if(preferrableDesiId.longValue() == 0l){
							id= 0l;
							name = "Others";
						}
						RepresenteeViewVO refDesigCan = map.get(id);
						if(refDesigCan == null){
							refDesigCan = new RepresenteeViewVO();
							refDesigCan.setDeptDesigId(id);
							refDesigCan.setDesigName(name);
							if(refDesigCan.getDesigName() != null && refDesigCan.getDesigName().trim().equalsIgnoreCase("Minister")){
								refDesigCan.setOrder(0L);
							}
							else if(refDesigCan.getDesigName() != null && refDesigCan.getDesigName().trim().equalsIgnoreCase("MLA")){
								refDesigCan.setOrder(1L);
							}
							else if(refDesigCan.getDesigName() != null && refDesigCan.getDesigName().trim().equalsIgnoreCase("MLC")){
								refDesigCan.setOrder(2L);
							}
							else if(refDesigCan.getDesigName() != null && refDesigCan.getDesigName().trim().equalsIgnoreCase("Others")){
								refDesigCan.setOrder(3L);
							}
							map.put(id, refDesigCan);
						}
						refDesigCan.getSubWorkIds().add(commonMethodsUtilService.getLongValueForObject(objects[6]));
						refDesigCan.setNoOfWorks(refDesigCan.getNoOfWorks()+commonMethodsUtilService.getLongValueForObject(objects[0]));
						refDesigCan.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(objects[1]));
						String estimationCost = commonMethodsUtilService.getStringValueForObject(objects[8]);
						if(refDesigCan.getEstimationCost() != null && refDesigCan.getEstimationCost() != "" && estimationCost != null && estimationCost != ""){
							BigDecimal decmial= new BigDecimal(refDesigCan.getEstimationCost());
							BigDecimal decmial1= new BigDecimal(estimationCost);
							BigDecimal totalCost = decmial.add(decmial1);
							refDesigCan.setEstimationCost(totalCost.toString());
						}
					}
				}
				if(commonMethodsUtilService.isMapValid(map)){
					returnList.addAll(map.values());
				}
				if (returnList.size() > 0) {
    				Collections.sort(returnList, sortListBasedOnDesignation);
				}
			}catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception Occured in PmRequestDetailsService @ setDesignationWiseCount() "+e.getMessage());
			}
		}
		 public static Comparator<RepresenteeViewVO> sortListBasedOnDesignation = new Comparator<RepresenteeViewVO>() {
				public int compare(RepresenteeViewVO o1, RepresenteeViewVO o2) {
					if(o1.getOrder() != null && o2.getOrder() != null){
						return o1.getOrder().compareTo(o2.getOrder());
					}
					return 0;
				}
			};
		
		
		public List<RepresenteeViewVO> setStatusList(List<Long> statusIds){
			List<RepresenteeViewVO> returnList = new ArrayList<RepresenteeViewVO>();
			try {
				RepresenteeViewVO vo = new RepresenteeViewVO();
				vo.setId(1l);
				vo.setName("Pending");
				RepresenteeViewVO vo1 = new RepresenteeViewVO();
				vo1.setId(2l);
				vo1.setName("Rejected");
				RepresenteeViewVO vo2 = new RepresenteeViewVO();
				vo2.setId(3l);
				vo2.setName("Completed");
				returnList.add(vo);
				returnList.add(vo1);
				returnList.add(vo2);
				
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception Occured in PmRequestDetailsService @ setStatusList() "+e.getMessage());
			}
			return returnList;
		}
		public void setReferralDesignationsDetails(List<Object[]> list,List<RepresenteeViewVO> returnList){
			try{
				List<Long> pendingIds = new ArrayList<Long>();
				List<Long> completedIds = new ArrayList<Long>();
				pendingIds.add(1l);
				pendingIds.add(3l);
				pendingIds.add(6l);
				pendingIds.add(7l);
				
				completedIds.add(4l);
				completedIds.add(8l);
				Map<Long,RepresenteeViewVO> map = new HashMap<Long,RepresenteeViewVO>();
				if(commonMethodsUtilService.isListOrSetValid(list)){
					for (Object[] objects : list) {
						RepresenteeViewVO refDesigCan = map.get(commonMethodsUtilService.getLongValueForObject(objects[6]));
						if(refDesigCan == null){
							refDesigCan = new RepresenteeViewVO();
							refDesigCan.setStatusList(setStatusList(null));
							refDesigCan.setId(commonMethodsUtilService.getLongValueForObject(objects[6]));
							refDesigCan.setReferrerName(commonMethodsUtilService.getCapitalStringValueForObject(objects[7]));
							refDesigCan.setDeptDesigId(commonMethodsUtilService.getLongValueForObject(objects[4]));
							refDesigCan.setDesigName(commonMethodsUtilService.getStringValueForObject(objects[5]));
							refDesigCan.setName(commonMethodsUtilService.getStringValueForObject(objects[7]));
							map.put(commonMethodsUtilService.getLongValueForObject(objects[6]), refDesigCan);
						}
						refDesigCan.setNoOfWorks(refDesigCan.getNoOfWorks()+commonMethodsUtilService.getLongValueForObject(objects[0]));
						refDesigCan.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(objects[1]));
						Long statusId = commonMethodsUtilService.getLongValueForObject(objects[2]);
						if(pendingIds.contains(statusId)){
							statusId=1l;
						}else if(completedIds.contains(statusId)){
							statusId=3l;
						}else if(statusId.longValue() == 5l){
							statusId=2l;
						} 
						RepresenteeViewVO statusVO = getMatchVO(refDesigCan.getStatusList(),statusId);
						if(statusVO != null){
							statusVO.setNoOfWorks(statusVO.getNoOfWorks()+commonMethodsUtilService.getLongValueForObject(objects[0]));
							statusVO.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(objects[1]));
						//if(statusId.longValue() == 3l){
							String estimationCost = commonMethodsUtilService.getStringValueForObject(objects[8]);
							if(refDesigCan.getEstimationCost() != null && refDesigCan.getEstimationCost() != "" && estimationCost != null && estimationCost != ""){
								BigDecimal decmial= new BigDecimal(refDesigCan.getEstimationCost());
								BigDecimal decmial1= new BigDecimal(estimationCost);
								BigDecimal totalCost = decmial.add(decmial1);
								refDesigCan.setEstimationCost(totalCost.toString());
							}
						//}
						}
					}
				}
				if(commonMethodsUtilService.isMapValid(map)){
					returnList.addAll(map.values());
				}
				if (returnList.size() > 0) {
    				Collections.sort(returnList, sortListBasedOnDesignation);
				}
			}catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception Occured in PmRequestDetailsService @ setReferralDesignationsDetails() "+e.getMessage());
			}
		}
		
		/**
		 * @author krishna
		 * @param Long petitionId 
		 * @param List<Long> petitionIdsList 
		 * @description {This service is used to history overview details.}
		 * @return List<PetitionHistoryVO>
		 * @Date 27-01-2018
		 */
		public List<PetitionHistoryVO> getPetitionTrackingHistoryDetails(PetitionTrackingVO dataVO){
			 List<PetitionHistoryVO> petitionHistoryVOLst  = new ArrayList<PetitionHistoryVO>() ;
			 Map<String,Map<String,PetitionHistoryVO>> dateHistoryMap = new HashMap<String,Map<String,PetitionHistoryVO>>();
			 Map<String,PetitionHistoryVO> subInnerMap = null;
			  
			 try {
			 List<Object[]> historyDtlsList = null;
			 if(!commonMethodsUtilService.isListOrSetValid(dataVO.getSubworkIdsList()))
			 historyDtlsList = pmTrackingDAO.getPetitionTrackingHistoryDetails(dataVO.getPetitionId(),null);
			 else if(commonMethodsUtilService.isListOrSetValid(dataVO.getSubworkIdsList()))
			 historyDtlsList =pmTrackingDAO.getPetitionTrackingHistoryDetails(null,dataVO.getSubworkIdsList());
			 if(commonMethodsUtilService.isListOrSetValid(historyDtlsList)){
			 for (Object[] param : historyDtlsList) {
				 
				 String historyDateTime = commonMethodsUtilService.getStringValueForObject(param[22]);
				 
				 String date = historyDateTime.substring(0,10).toString();
				 String time = historyDateTime.substring(11, 17).toString();
				 
				  subInnerMap = dateHistoryMap.get(date);
				 if(subInnerMap == null)         
						 subInnerMap = new HashMap<String,PetitionHistoryVO>();
						 
						 dateHistoryMap.put(date, subInnerMap);
						 PetitionHistoryVO timeInnerVO = subInnerMap.get(time);
						 if(timeInnerVO == null){
							 timeInnerVO = new PetitionHistoryVO();
							 timeInnerVO.setTimeStr(time);
							 timeInnerVO.setPmTrackingId(commonMethodsUtilService.getLongValueForObject(param[0]));
							 timeInnerVO.setPetitionId(commonMethodsUtilService.getLongValueForObject(param[1]));
							 timeInnerVO.setPmSubWorkDetailsId(commonMethodsUtilService.getLongValueForObject(param[2]));
							 timeInnerVO.setTrackingActionId(commonMethodsUtilService.getLongValueForObject(param[3]));
							 timeInnerVO.setActionName(commonMethodsUtilService.getStringValueForObject(param[4]));
							 timeInnerVO.setStatusId(commonMethodsUtilService.getLongValueForObject(param[5]));
							 timeInnerVO.setStautus(commonMethodsUtilService.getStringValueForObject(param[6]));
							 timeInnerVO.setRemarks(commonMethodsUtilService.getStringValueForObject(param[7]));
							 timeInnerVO.setDocumentId(commonMethodsUtilService.getLongValueForObject(param[8]));
							 timeInnerVO.setPath(commonMethodsUtilService.getStringValueForObject(param[9]));
							 timeInnerVO.setPmDeptDesgOfficerId(commonMethodsUtilService.getLongValueForObject(param[10]));
							 timeInnerVO.setPmDeptDesgId(commonMethodsUtilService.getLongValueForObject(param[11]));
							 timeInnerVO.setPmDepartmentId(commonMethodsUtilService.getLongValueForObject(param[12]));
							 timeInnerVO.setPmDepartment(commonMethodsUtilService.getStringValueForObject(param[13]));
							 timeInnerVO.setPmOfficerId(commonMethodsUtilService.getLongValueForObject(param[14]));
							 timeInnerVO.setPmOfficerName(commonMethodsUtilService.getStringValueForObject(param[15]));
							 timeInnerVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[16]));
							 subInnerMap.put(timeInnerVO.getTimeStr(), timeInnerVO);
						 }
				         
			    }
			 }
			 
			 //while iterating
			 if(dateHistoryMap != null && dateHistoryMap.size() >0){
				 
				 for(Map.Entry<String, Map<String,PetitionHistoryVO>> entrySet : dateHistoryMap.entrySet()){
					 PetitionHistoryVO vo = new PetitionHistoryVO();
					 vo.setDatestr(entrySet.getKey());
					 Map<String,PetitionHistoryVO> subMap = entrySet.getValue();
					 if(subMap != null && subMap.size() >0){
						 List<PetitionHistoryVO> timeList = new ArrayList<PetitionHistoryVO>();
						 for(Map.Entry<String, PetitionHistoryVO> subEntrySet : subMap.entrySet()){
							if(subEntrySet != null){
								PetitionHistoryVO PetitionHistoryVO = subEntrySet.getValue();
								   timeList.add(PetitionHistoryVO);
							}
						 }
						 vo.setSubList1(timeList);
						 
					 }
					 petitionHistoryVOLst.add(vo);
			     }
			   }
			 
			 }catch (Exception e) {
			 LOG.error("Exception raised into PmRequestDetailsService of getPetitionTrackingDetails() ",e);
			 }
			    return petitionHistoryVOLst;
			 }
		
		public List<RepresenteeViewVO> getBriefLeads(Long userId,List<Long> deptIds){
			List<RepresenteeViewVO> returnList = new ArrayList<RepresenteeViewVO>();
			try {
				List<Object[]> list = pmSubWorkDetailsDAO.getPmBriefLeadIds(deptIds);
				if(commonMethodsUtilService.isListOrSetValid(list)){
					for (Object[] param : list) {
						RepresenteeViewVO vo = new RepresenteeViewVO();
						vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						returnList.add(vo);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				 LOG.error("Exception raised into PmRequestDetailsService of getBriefLeads() ",e);
			}
			return returnList;
		}
		

		
		public PetitionHistoryVO getPetitionAndWorkWiseHistoryDetails(PetitionTrackingVO dataVO){
			PetitionHistoryVO returnVO = new PetitionHistoryVO();
			try{
				LOG.info("entered into  PmRequestDetailsService of getPetitionAndWorkWiseHistoryDetails");
				Long petitionStatusId =0L;
				String insertedTimeStr = "";
				String insertedUserName = "";
				Map<String,Long> statusWiseMap = new LinkedHashMap<String,Long>(0);
				statusWiseMap.put("TOTAL", 0L);
				statusWiseMap.put("PENDING", 0L);
				statusWiseMap.put("ENDORSED", 0L);
				//statusWiseMap.put("IN-PROGRESS", 0L);
				statusWiseMap.put("COMPLETED", 0L);
				//Map<Long,List<PetitionHistoryVO>> finalHistoryMap = new HashMap<Long,List<PetitionHistoryVO>>();
				Map<Long,List<PetitionHistoryVO>> historyMap = new HashMap<Long,List<PetitionHistoryVO>>();
				
				Map<Long,Map<String,Map<String,List<PetitionHistoryVO>>>> newHistoryMap = new TreeMap<Long,Map<String,Map<String,List<PetitionHistoryVO>>>>();
				
				 List<Object[]> historyList = null;
				 if(!commonMethodsUtilService.isListOrSetValid(dataVO.getSubworkIdsList()))
					 historyList = pmTrackingDAO.getPetitionAndWorkWiseHistoryDetails(dataVO.getPetitionId(),null);
				 else if(commonMethodsUtilService.isListOrSetValid(dataVO.getSubworkIdsList()))
					 historyList =pmTrackingDAO.getPetitionAndWorkWiseHistoryDetails(null,dataVO.getSubworkIdsList());
					
					if(commonMethodsUtilService.isListOrSetValid(historyList)){
						for (Object[] param : historyList) {
							Long petitinId =commonMethodsUtilService.getLongValueForObject(param[0]);
							Long workId=commonMethodsUtilService.getLongValueForObject(param[1]);
							String workIsDeleted=commonMethodsUtilService.getStringValueForObject(param[26]);
							String petitionIsDeleted=commonMethodsUtilService.getStringValueForObject(param[27]);
							if(petitionIsDeleted != null && petitionIsDeleted.equalsIgnoreCase("N")){
								if(workIsDeleted != null && (workIsDeleted.equalsIgnoreCase("Y") || workIsDeleted.equalsIgnoreCase("")) && workId.longValue()>0L )
								  continue;
								Long actionId = commonMethodsUtilService.getLongValueForObject(param[2]);
								String actionName =commonMethodsUtilService.getStringValueForObject(param[3]);
								String path=commonMethodsUtilService.getStringValueForObject(param[4]);
								String remarks=commonMethodsUtilService.getStringValueForObject(param[5]);
								String dateTimeStr=commonMethodsUtilService.getStringValueForObject(param[6]);
								Long insertedUserId = commonMethodsUtilService.getLongValueForObject(param[7]);
								String userName =commonMethodsUtilService.getStringValueForObject(param[8]);
								Long statusId = commonMethodsUtilService.getLongValueForObject(param[9]);
								String stautus=commonMethodsUtilService.getStringValueForObject(param[10]);
								Long officerId = commonMethodsUtilService.getLongValueForObject(param[11]);
								String officerName=commonMethodsUtilService.getStringValueForObject(param[12]);
								String mobileNo=commonMethodsUtilService.getStringValueForObject(param[13]);
								Long pmOfficerdesgId = commonMethodsUtilService.getLongValueForObject(param[14]);
								String designation=commonMethodsUtilService.getStringValueForObject(param[15]);
								String deptName = commonMethodsUtilService.getStringValueForObject(param[16]);
								String deptShortName = commonMethodsUtilService.getStringValueForObject(param[17]);
								String subWorkDesc = commonMethodsUtilService.getStringValueForObject(param[18]);
								String petitionDesc = commonMethodsUtilService.getStringValueForObject(param[19]);
								Long documentId = commonMethodsUtilService.getLongValueForObject(param[20]);
								
								Long assignedToDeptDesiOfficeId = commonMethodsUtilService.getLongValueForObject(param[21]);
								String assignedToDesignation = commonMethodsUtilService.getStringValueForObject(param[22]);
								String assignedToDesignationShortName = commonMethodsUtilService.getStringValueForObject(param[23]);
								String assignedToOfficerName = commonMethodsUtilService.getStringValueForObject(param[24]);
								String assignedToOfficerMobileNo = commonMethodsUtilService.getStringValueForObject(param[25]);
								String pmDocumentType= commonMethodsUtilService.getStringValueForObject(param[28]);
								
								String dateStr = dateTimeStr.substring(0, 11).trim(); //2018-01-28 17:15:46 --> 2018-01-28
								String timeStr = dateTimeStr.substring(11, 16).trim(); //2018-01-28 17:15:46 --> 17:15
								if(assignedToDeptDesiOfficeId != null && assignedToDeptDesiOfficeId.longValue()>0L && assignedToDesignation.isEmpty()){
									PmPetitionAssignedOfficer pmPetitionAssignedOfficer = pmPetitionAssignedOfficerDAO.get(assignedToDeptDesiOfficeId);
									if(pmPetitionAssignedOfficer != null){
										if(pmPetitionAssignedOfficer.getPmDepartmentDesignation() != null){
											if(pmPetitionAssignedOfficer.getPmDepartmentDesignation().getPmOfficerDesignation() != null){
												assignedToDesignation = pmPetitionAssignedOfficer.getPmDepartmentDesignation().getPmOfficerDesignation().getDesignation();
												assignedToDesignationShortName = pmPetitionAssignedOfficer.getPmDepartmentDesignation().getPmOfficerDesignation().getShortName();
											}
										}
										if(pmPetitionAssignedOfficer.getPmDepartmentDesignationOfficer() != null){
											if(pmPetitionAssignedOfficer.getPmDepartmentDesignationOfficer().getPmOfficer() != null){
												assignedToOfficerName = pmPetitionAssignedOfficer.getPmDepartmentDesignationOfficer().getPmOfficer().getName();
												assignedToOfficerMobileNo = pmPetitionAssignedOfficer.getPmDepartmentDesignationOfficer().getPmOfficer().getMobileNo();
											}
										}
									}
								}
								if(assignedToDesignation == null)assignedToDesignation="";
								if(assignedToDesignationShortName == null)assignedToDesignationShortName="";
								if(assignedToOfficerName == null)assignedToOfficerName="";
								if(assignedToOfficerMobileNo == null)assignedToOfficerMobileNo="";
										
								if(!assignedToOfficerMobileNo.isEmpty())
									assignedToOfficerName = assignedToOfficerName+" "+assignedToOfficerMobileNo;
								if(deptShortName != null && !deptShortName.isEmpty())
									deptName = deptShortName ;
								
								//List<PetitionHistoryVO> historyWorkList = new LinkedList<PetitionHistoryVO>();
								Map<String,Map<String,List<PetitionHistoryVO>>> dateStrMap = new LinkedHashMap<String,Map<String,List<PetitionHistoryVO>>>();
								Map<String,List<PetitionHistoryVO>> timeMap = new LinkedHashMap<String,List<PetitionHistoryVO>>();
								List<PetitionHistoryVO> historyWorkList = new LinkedList<PetitionHistoryVO>();
								PetitionHistoryVO historyVO = new PetitionHistoryVO();
								if(newHistoryMap.get(workId) != null){
									dateStrMap = newHistoryMap.get(workId);
								}
								if(dateStrMap.get(dateStr) != null){
									timeMap = dateStrMap.get(dateStr);
								}
								if(timeMap.get(timeStr) != null){
									historyWorkList = timeMap.get(timeStr);
								}
								
								historyVO.setId(petitinId);
								historyVO.setWorkId(workId);
								historyVO.setActionId(actionId);
								historyVO.setActionName(actionName);
								historyVO.setPath(path);
								historyVO.setRemarks(remarks);
								historyVO.setTimeStr(timeStr);
								historyVO.setInsertedUserId(insertedUserId);
								historyVO.setUserName(userName);
								historyVO.setStatusId(statusId);
								historyVO.setStautus(stautus);
								historyVO.setOfficerId(officerId);
								historyVO.setOfficerName(officerName);
								historyVO.setPmOfficerDesgId(pmOfficerdesgId);
								historyVO.setDesignation(designation);
								historyVO.setPmDepartment(deptName);
								historyVO.setSubWorkDesc(subWorkDesc);
								historyVO.setPetitionDesc(petitionDesc);
								historyVO.setId(documentId);
								historyVO.setAssignedToDesignation(assignedToDesignation);
								historyVO.setAssignedToOfficerName(assignedToOfficerName);
								historyVO.setShortName(assignedToDesignationShortName);
								historyVO.setDocumentType(pmDocumentType);
								if(historyVO.getOfficerName() ==null || historyVO.getOfficerName().isEmpty())
									historyVO.setOfficerName(mobileNo);
								else if(mobileNo !=null && !mobileNo.isEmpty())
									historyVO.setOfficerName(historyVO.getOfficerName()+"-"+mobileNo);
								
								historyWorkList.add(historyVO);
								timeMap.put(timeStr, historyWorkList);
								dateStrMap.put(dateStr, timeMap);
								newHistoryMap.put(workId, dateStrMap);
							}
						}
						
						List<Object[]> list = pmSubWorkDetailsDAO.getAllWorksLatesStatusDetails(dataVO.getPetitionId());
						if(commonMethodsUtilService.isListOrSetValid(list)){
							for (Object[] param : list) {
								//Long petitinId = commonMethodsUtilService.getLongValueForObject(param[0]);
								Long subWorkId = commonMethodsUtilService.getLongValueForObject(param[1]);
								Long subWorkStatusId = commonMethodsUtilService.getLongValueForObject(param[2]);
								petitionStatusId = commonMethodsUtilService.getLongValueForObject(param[3]);
								insertedTimeStr = commonMethodsUtilService.getStringValueForObject(param[4]);
								Long userId = commonMethodsUtilService.getLongValueForObject(param[5]);
								
								if(subWorkId.longValue()>0L && subWorkStatusId.longValue()>0L){
									statusWiseMap.put("TOTAL",statusWiseMap.get("TOTAL")+1L);
									
									if(subWorkStatusId.longValue() == 1L){
										statusWiseMap.put("PENDING",statusWiseMap.get("PENDING")+1L);
									}
									else if(subWorkStatusId.longValue() == 6L){
										statusWiseMap.put("ENDORSED",statusWiseMap.get("ENDORSED")+1L);
									}
									else if(subWorkStatusId.longValue() == 3L || subWorkStatusId.longValue() == 7L){
									//	statusWiseMap.put("IN-PROGRESS",statusWiseMap.get("IN-PROGRESS")+1L);
										statusWiseMap.put("ENDORSED",statusWiseMap.get("ENDORSED")+1L);
									}else if(subWorkStatusId.longValue() == 4L || subWorkStatusId.longValue() == 5L || subWorkStatusId.longValue() == 8L){
										statusWiseMap.put("COMPLETED",statusWiseMap.get("COMPLETED")+1L);
									}
								}
								
								//finalHistoryMap.put(subWorkId, historyMap.get(subWorkId));
							}
							returnVO.setPetitionId(dataVO.getPetitionId());
							returnVO.setInsertedDate(insertedTimeStr);
							returnVO.setStatusId(petitionStatusId);
							returnVO.setUserName(insertedUserName);
						}
					}
				
					List<PetitionHistoryVO> petitionHistoryVOList = new ArrayList<PetitionHistoryVO>(0);
					List<Object[]> actionsList = pmTrackingActionDAO.getActionsList();
					if(commonMethodsUtilService.isMapValid(newHistoryMap)){
						for (Long workId : newHistoryMap.keySet()) {
							PetitionHistoryVO workVO = new PetitionHistoryVO();
							
							Map<String,Map<String,List<PetitionHistoryVO>>> dateStrMap = newHistoryMap.get(workId);
							if(commonMethodsUtilService.isMapValid(dateStrMap)){
								for (String dateStr : dateStrMap.keySet()) {
									
									PetitionHistoryVO dateVO = new PetitionHistoryVO();
									Map<String,List<PetitionHistoryVO>> timeMap =  dateStrMap.get(dateStr);
									if(commonMethodsUtilService.isMapValid(timeMap)){
										for (String timeStr : timeMap.keySet()) {
											PetitionHistoryVO timeVO = new PetitionHistoryVO();
											List<PetitionHistoryVO> historyWorkList = timeMap.get(timeStr);
											if(commonMethodsUtilService.isListOrSetValid(historyWorkList)){
												for (PetitionHistoryVO hVO : historyWorkList) {
													
													workVO.setWorkId(workId);
													workVO.setSubWorkDesc(hVO.getSubWorkDesc());
													dateVO.setTimeStr(dateStr);
													
													timeVO.setTimeStr(timeStr);
													timeVO.setId(hVO.getId());
													timeVO.setWorkId(hVO.getWorkId());
													//timeVO.setActionId(hVO.getActionId());
													//timeVO.setActionName(hVO.getActionName());
													
													//timeVO.setId(hVO.getId());
													//timeVO.setPath(hVO.getPath());
													//timeVO.setRemarks(hVO.getRemarks());
													timeVO.setTimeStr(timeStr);
													timeVO.setInsertedUserId(hVO.getInsertedUserId());
													timeVO.setUserName(hVO.getUserName());
													if(workId != null && workId.longValue() ==0L){
														timeVO.setStatusId(petitionStatusId);
														timeVO.setStautus(pmStatusDAO.get(petitionStatusId).getStatus());
													}else{
														timeVO.setStatusId(hVO.getStatusId());
														timeVO.setStautus(hVO.getStautus());
													}
													
													timeVO.setOfficerId(hVO.getOfficerId());
													timeVO.setOfficerName(hVO.getOfficerName());
													timeVO.setPmOfficerDesgId(hVO.getPmOfficerDesgId());
													timeVO.setDesignation(commonMethodsUtilService.toConvertStringToTitleCase(hVO.getDesignation()));
													timeVO.setPmDepartment(hVO.getPmDepartment());
													timeVO.setSubWorkDesc(hVO.getSubWorkDesc());
													timeVO.setPetitionDesc(hVO.getPetitionDesc());
													timeVO.setOfficerName(hVO.getOfficerName());
													
													timeVO.setAssignedToDesignation(hVO.getAssignedToDesignation());
													timeVO.setAssignedToOfficerName(hVO.getAssignedToOfficerName());
													timeVO.setShortName(timeVO.getShortName());
													
													if(!commonMethodsUtilService.isListOrSetValid(timeVO.getSubList1()))
														timeVO.setSubList1(getTrackingActionsList(actionsList));
													
													if(commonMethodsUtilService.isListOrSetValid(timeVO.getSubList1())){
														for (PetitionHistoryVO actionVO : timeVO.getSubList1()) {
															if(actionVO != null && actionVO.getActionId().longValue()==hVO.getActionId()){
																actionVO.setRemarks(hVO.getRemarks());
																
																if(actionVO.getActionId().longValue() == 2L){
																	actionVO.setStatusId(hVO.getStatusId());
																	actionVO.setStautus(hVO.getStautus());
																}else if(actionVO.getActionId().longValue() == 4L){
																	if(hVO.getStatusId() == 1L && (hVO.getRemarks().contains("UPLOADED WORK RELATED DOCUMENTS") || hVO.getRemarks().contains("Uploaded work related documents"))){
																		PetitionHistoryVO workDocVO = new PetitionHistoryVO();
																		PetitionHistoryVO workDocVO1 =  getMatchedActionVO(2L,actionVO.getSubList1());
																		if(workDocVO1 != null){
																			workDocVO = workDocVO1;//actionVO.getSubList1().get(0);
																		}else{
																			workDocVO.setId(2L);
																			workDocVO.setName("PETITION DOCUMENTS");
																			actionVO.getSubList1().add(workDocVO);
																		}
																		//workDocVO.getFilesList().add(new KeyValueVO(hVO.getId(),"http://www.mydepartments.in/PRRWS/"+hVO.getPath()));
																		KeyValueVO fileVO =  getMatchedActionVO1(hVO.getId(),workDocVO.getFilesList());
																		if(fileVO == null){
																			workDocVO.getFilesList().add(new KeyValueVO(hVO.getId(),"http://www.mydepartments.in/PRRWS/"+hVO.getPath().trim()));
																		}
																	}else if(hVO.getStatusId() == 1L && (hVO.getRemarks().contains("UPLOADED REFERRAL MEMBER RELATED DOCUMENTS") || hVO.getRemarks().contains("Uploaded referral member(s) related documents"))){
																		PetitionHistoryVO workDocVO = new PetitionHistoryVO();
																		PetitionHistoryVO workDocVO1 =  getMatchedActionVO(1L,actionVO.getSubList1());
																		if(workDocVO1 != null){
																			workDocVO = workDocVO1;//actionVO.getSubList1().get(0);
																		}else{
																			workDocVO.setId(1L);
																			workDocVO.setName("REFERRAL DOCUMENTS");
																			actionVO.getSubList1().add(workDocVO);
																		}
																		//workDocVO.getFilesList().add(new KeyValueVO(hVO.getId(),"http://www.mydepartments.in/PRRWS/"+hVO.getPath()));
																		KeyValueVO fileVO =  getMatchedActionVO1(hVO.getId(),workDocVO.getFilesList());
																		if(fileVO == null){
																			workDocVO.getFilesList().add(new KeyValueVO(hVO.getId(),"http://www.mydepartments.in/PRRWS/"+hVO.getPath().trim()));
																		}
																	}else if(hVO.getStatusId() == 6L && (hVO.getRemarks().contains("UPLOADED COVERING DOCUMENT(S)") || hVO.getRemarks().contains("Uploaded covering letter"))){
																		PetitionHistoryVO workDocVO = new PetitionHistoryVO();
																		PetitionHistoryVO workDocVO1 =  getMatchedActionVO(3L,actionVO.getSubList1());
																		if(workDocVO1 != null){
																			workDocVO = workDocVO1;//actionVO.getSubList1().get(0);
																		}else{
																			workDocVO.setId(3L);
																			workDocVO.setName("COVERING LETTER");
																			actionVO.getSubList1().add(workDocVO);
																		}
																		//workDocVO.getFilesList().add(new KeyValueVO(hVO.getId(),"http://www.mydepartments.in/PRRWS/"+hVO.getPath()));
																		KeyValueVO fileVO =  getMatchedActionVO1(hVO.getId(),workDocVO.getFilesList());
																		if(fileVO == null){
																			workDocVO.getFilesList().add(new KeyValueVO(hVO.getId(),"http://www.mydepartments.in/PRRWS/"+hVO.getPath().trim()));
																		}
																	}else if(hVO.getStatusId() == 3L && (hVO.getRemarks().contains("UPLOADED DETAILED DOCUMENT(S)") || hVO.getRemarks().contains("Uploaded detailed report document"))){
																		PetitionHistoryVO workDocVO = new PetitionHistoryVO();
																		PetitionHistoryVO workDocVO1 =  getMatchedActionVO(5L,actionVO.getSubList1());
																		if(workDocVO1 != null){
																			workDocVO = workDocVO1;//actionVO.getSubList1().get(0);
																		}else{
																			workDocVO.setId(5L);
																			workDocVO.setName("DETAILED REPORT");
																			actionVO.getSubList1().add(workDocVO);
																		}
																		//workDocVO.getFilesList().add(new KeyValueVO(hVO.getId(),"http://www.mydepartments.in/PRRWS/"+hVO.getPath()));
																		KeyValueVO fileVO =  getMatchedActionVO1(hVO.getId(),workDocVO.getFilesList());
																		if(fileVO == null){
																			workDocVO.getFilesList().add(new KeyValueVO(hVO.getId(),"http://www.mydepartments.in/PRRWS/"+hVO.getPath().trim()));
																		}
																	}else if(hVO.getStatusId() == 7L && (hVO.getRemarks().contains("UPLOADED ACTION COPY DOCUMENT(S)") || hVO.getRemarks().contains("uploaded action memo document"))){
																		PetitionHistoryVO workDocVO = new PetitionHistoryVO();
																		PetitionHistoryVO workDocVO1 =  getMatchedActionVO(4L,actionVO.getSubList1());
																		if(workDocVO1 != null){
																			workDocVO = workDocVO1;//actionVO.getSubList1().get(0);
																		}else{
																			workDocVO.setId(4L);
																			workDocVO.setName("ACTION MEMO");
																			actionVO.getSubList1().add(workDocVO);
																		}
																		//workDocVO.getFilesList().add(new KeyValueVO(hVO.getId(),"http://www.mydepartments.in/PRRWS/"+hVO.getPath().trim()));
																		KeyValueVO fileVO =  getMatchedActionVO1(hVO.getId(),workDocVO.getFilesList());
																		if(fileVO == null){
																			workDocVO.getFilesList().add(new KeyValueVO(hVO.getId(),"http://www.mydepartments.in/PRRWS/"+hVO.getPath().trim()));
																		}
																	}else if(hVO.getStatusId() == 8L){
																		PetitionHistoryVO workDocVO = new PetitionHistoryVO();
																		PetitionHistoryVO workDocVO1 =  getMatchedActionVO(6L,actionVO.getSubList1());
																		if(workDocVO1 != null){
																			workDocVO = workDocVO1;//actionVO.getSubList1().get(0);
																		}else{
																			workDocVO.setId(6L);
																			workDocVO.setName(hVO.getDocumentType()+"  DOCUMENT");
																			actionVO.getSubList1().add(workDocVO);
																		}
																		KeyValueVO fileVO =  getMatchedActionVO1(hVO.getId(),workDocVO.getFilesList());
																		if(fileVO == null){
																			workDocVO.getFilesList().add(new KeyValueVO(hVO.getId(),"http://www.mydepartments.in/PRRWS/"+hVO.getPath().trim()));
																		}
																		
																	}
																}break;
															}
														}
													}
													
												}
											}
											
											if(commonMethodsUtilService.isListOrSetValid(timeVO.getSubList1())){
												List<PetitionHistoryVO> finalActionsList = new ArrayList<PetitionHistoryVO>();
												for (PetitionHistoryVO actionVO : timeVO.getSubList1()) {
													if(actionVO != null && actionVO.getRemarks() !=null)
														finalActionsList.add(actionVO);
												}
												if(commonMethodsUtilService.isListOrSetValid(finalActionsList)){
													timeVO.getSubList1().clear();
													timeVO.getSubList1().addAll(finalActionsList);
												}
											}
											dateVO.getSubList1().add(timeVO);
										}
									}
									workVO.getSubList1().add(dateVO);
								}
							}
							petitionHistoryVOList.add(workVO);
							historyMap.put(workVO.getWorkId(), petitionHistoryVOList);
						}
					}
					
				if(commonMethodsUtilService.isMapValid(historyMap)){
					for (Long workId : historyMap.keySet()) {
						if(workId != null && workId.longValue()>0L){
							PetitionHistoryVO workVO = new PetitionHistoryVO();
							workVO.setWorkId(workId);
							if(historyMap.get(workId) != null){
								workVO.getSubList1().addAll(historyMap.get(workId));
								if(!commonMethodsUtilService.isListOrSetValid(returnVO.getSubList1())){
									List<PetitionHistoryVO> voList = new ArrayList<PetitionHistoryVO>();
									returnVO.setSubList1(voList);
								}
								returnVO.getSubList1().add(workVO);
							}
							//break;
						}
					}
				}
				
				if(commonMethodsUtilService.isMapValid(historyMap) && historyMap.get(0L) != null){
					returnVO.getPetitionHistoryList().addAll(historyMap.get(0L));
				}
				
				if(commonMethodsUtilService.isMapValid(statusWiseMap)){
					for (String statusStr : statusWiseMap.keySet()) {
						returnVO.getStatusList().add(new KeyValueVO(0L,statusStr,statusWiseMap.get(statusStr)));
					}
				}
					
			}catch(Exception e){
				LOG.error("Exception raised into PmRequestDetailsService of getPetitionAndWorkWiseHistoryDetails",e);
			}
			return returnVO;
		}
		
		public PetitionHistoryVO getMatchedActionVO(Long actionId,List<PetitionHistoryVO> list){
			PetitionHistoryVO returnVO = null;
			try {
				if(actionId != null && actionId.longValue()>0L && commonMethodsUtilService.isListOrSetValid(list)){
					for (PetitionHistoryVO vo : list) {
						if(vo.getId() != null && vo.getId().longValue()==actionId.longValue())
							return vo;
					}
				}
			} catch (Exception e) {
				LOG.error("Exception raised into PmRequestDetailsService of getPetitionAndWorkWiseHistoryDetails",e);
			}
			return returnVO;
		}
		public KeyValueVO getMatchedActionVO1(Long actionId,List<KeyValueVO> list){
			KeyValueVO returnVO = null;
			try {
				if(actionId != null && actionId.longValue()>0L && commonMethodsUtilService.isListOrSetValid(list)){
					for (KeyValueVO vo : list) {
						if(vo.getKey() != null && vo.getKey().longValue()==actionId.longValue())
							return vo;
					}
				}
			} catch (Exception e) {
				LOG.error("Exception raised into PmRequestDetailsService of getMatchedActionVO1",e);
			}
			return returnVO;
		}
		
		
		public Map<Long,List<Long>> getAssignedPetitionforPetitionDeptDesignationOfficer(Long userId,Long pmDeptDesignationOfficerId){
			 Map<Long,List<Long>> petitiosWorksMap = new HashMap<>();
			try {
				boolean isAccessDashboard=false;
				List<Long> pmDeptDesignationOfficerIdsList = new ArrayList<>();
				if(pmDeptDesignationOfficerId == null || pmDeptDesignationOfficerId.longValue() ==0L ){
					if(userId != null && userId.longValue()>0L){
						List<Object[]> list = pmOfficerUserDAO.getPmOffceUserDetails(userId);
						if(commonMethodsUtilService.isListOrSetValid(list)){
							for (Object[] param : list) {
								if(!pmDeptDesignationOfficerIdsList.contains(commonMethodsUtilService.getLongValueForObject(param[7])))
									pmDeptDesignationOfficerIdsList.add(commonMethodsUtilService.getLongValueForObject(param[7]));
								if(!isAccessDashboard){
									if(IConstants.DASHBOARD_ACCESS_OFFICER_DESIGNATION_IDS.contains(commonMethodsUtilService.getLongValueForObject(param[9]))){
										isAccessDashboard=true;
									}
								}
								if(isAccessDashboard)
									break;
							}
						}	
					}
				}
				
				if(!isAccessDashboard){
					petitiosWorksMap.put(0L, new ArrayList<Long>());
					List<Object[]> assignedPetitionDetails = pmPetitionAssignedOfficerDAO.getAssignedPetitionforPetitionDeptDesignationOfficer(pmDeptDesignationOfficerIdsList);
					if(commonMethodsUtilService.isListOrSetValid(assignedPetitionDetails)){
						for (Object[] param : assignedPetitionDetails) {
							List<Long> petitionsIdsList = new ArrayList<>();
							Long petitionId =commonMethodsUtilService.getLongValueForObject(param[0]);
							Long subWorksId =commonMethodsUtilService.getLongValueForObject(param[1]);
							if(petitiosWorksMap.get(petitionId) != null){
								petitionsIdsList = petitiosWorksMap.get(petitionId);
							}
							petitionsIdsList.add(subWorksId);
							petitiosWorksMap.put(petitionId, petitionsIdsList);
						}
					}
				}
				
				
			} catch (Exception e) {
				LOG.error("Exception raised into PmRequestDetailsService of getAssignedPetitionforPetitionDeptDesignationOfficer",e);
			}
			return petitiosWorksMap;
		}
		
		public List<PetitionHistoryVO> getTrackingActionsList(List<Object[]> actionsList) {
			List<PetitionHistoryVO> returnList = new ArrayList<>();
			try {
				if(commonMethodsUtilService.isListOrSetValid(actionsList)){
					for (Object[] param : actionsList) {
						returnList.add(new PetitionHistoryVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])));
					}
				}
			} catch (Exception e) {
				LOG.error("Exception raised into PmRequestDetailsService of getTrackingActionsList",e);
			}
			return returnList;
		}
		public List<MenuVO> userIdsByEntitlementsLogin(Long userId){
			List<MenuVO> resultList = null;
			Map<Long, MenuVO> userLoginMap = new HashMap<Long,MenuVO>();
		
			try{
				LOG.info(" Entered into PmRequestDetailsService of userIdsByEntitlementsLogin ");
				List<Object[]> entitlementObjsList = entitlementsGroupEntitlementUrlDAO.getUserIdsByEntitlementsLogin(userId);
				
				if(entitlementObjsList != null && entitlementObjsList.size() >0){
					for(Object[] accessObj : entitlementObjsList){
						
						Long entitlementGroupId = commonMethodsUtilService.getLongValueForObject(accessObj[0]);
						MenuVO menuVO = userLoginMap.get(entitlementGroupId);
					
						if(menuVO == null)	{
							menuVO = new MenuVO();
							menuVO.setId(entitlementGroupId);
							menuVO.setName(commonMethodsUtilService.getStringValueForObject(accessObj[1]));
						  }
						
						   MenuVO subVO = new MenuVO();
					       subVO.setEntitlementUrlId(commonMethodsUtilService.getLongValueForObject(accessObj[2]));
					       subVO.setUrl(commonMethodsUtilService.getStringValueForObject(accessObj[4]));
					       subVO.setName(commonMethodsUtilService.getStringValueForObject(accessObj[3]));
							
					        menuVO.getSubList().add(subVO);
							userLoginMap.put(menuVO.getId(), menuVO);
					}
				}
				if(userLoginMap != null && userLoginMap.size() >0){
					resultList = new ArrayList<MenuVO>(userLoginMap.values());
				}
			
		}catch(Exception e){
			LOG.error("Exception raised into PmRequestDetailsService of userIdsByEntitlementsLogin",e);
		}
			return resultList;
		  }	
		/*public List<KeyValueVO> getLoginUserAccessStatusWiseDeptDesignations(List<Long> deptDesigIdsList , Long userId,Long statusId){
			 List<KeyValueVO>  returnList = new ArrayList<KeyValueVO>();
			try {
				List<Long> deptDesignationIdsList = pmOfficerUserDAO.getPmDeptDesignationIdByUserId(userId);
				if(commonMethodsUtilService.isListOrSetValid(deptDesignationIdsList)){
					List<Object[]> childDeptDesignationsList = pmDepartmentDesignationHierarchyDAO.getSubDesignationDetailsForParentDeptDesignations(deptDesignationIdsList);
					if(commonMethodsUtilService.isListOrSetValid(childDeptDesignationsList)){
						for (Object[] param : childDeptDesignationsList) {
							//returnList.add(new KeyValueVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])+" - "+commonMethodsUtilService.getStringValueForObject(param[2])));
							returnList.add(new KeyValueVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(param[1]))));
						}
					}
				}
				List<Object[]> desigList = pmDepartmentDesignationStatusDAO.getLoginUserAccessStatusWiseDeptDesignations(deptDesigIdsList,statusId);
				if(commonMethodsUtilService.isListOrSetValid(desigList)){
					for (Object[] param : desigList) {
						returnList.add(new KeyValueVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(param[1]))));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception raised into PmRequestDetailsService of getLoginUserAccessSubDeptDesignationDetail() ",e);
				returnList = null;
			}
			return returnList;
		}*/
		
		/**
		 * @Author : krishna 
		 * @Date : 2nd feb,2018
		 * @return List<KeyValueVO> 
		 */
		public List<KeyValueVO> getPmPetitionList(){
			 List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
			try{
				List<Object[]> pmPetitionObjsList = pmPetitionTypeDAO.getPmPetitionList();
				if(pmPetitionObjsList != null && pmPetitionObjsList.size() >0){
					for(Object[] petitionObj : pmPetitionObjsList){
						KeyValueVO vo = new KeyValueVO();
						vo.setKey(commonMethodsUtilService.getLongValueForObject(petitionObj[0]));
						vo.setValue(commonMethodsUtilService.getStringValueForObject(petitionObj[1]));
						resultList.add(vo);
					}
				}
			}catch(Exception e){
				LOG.error("exception raised into PmRequestDetailsService of getPmPetitionList()",e);
			}
			return resultList;
		}
		
		/**
		 * @Author : krishna 
		 * @Date : 2nd feb,2018
		 * @return List<KeyValueVO> 
		 */
		public List<KeyValueVO> getPmDocumentTypeList(){
			 List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
			try{
				List<Object[]> pmDocmentTypeObjsList = pmDocumentTypeDAO.getPmDocumentTypeList();
				if(pmDocmentTypeObjsList != null && pmDocmentTypeObjsList.size() >0){
					for(Object[] petitionObj : pmDocmentTypeObjsList){
						KeyValueVO vo = new KeyValueVO();
						vo.setKey(commonMethodsUtilService.getLongValueForObject(petitionObj[0]));
						vo.setValue(commonMethodsUtilService.getStringValueForObject(petitionObj[1]));
						resultList.add(vo);
					}
				}
			}catch(Exception e){
				LOG.error("exception raised into PmRequestDetailsService of getPmDocumentTypeList()",e);
			}
			return resultList;
		}
		/**
		 * @Author : krishna 
		 * @Date : 2nd feb,2018
		 * @return List<KeyValueVO> 
		 */
		public List<KeyValueVO> getPmActionTypeList(){
			 List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
			try{
				List<Object[]> pmActionTypeObjsList = pmActionTypeDAO.getPmActionTypeList();
				if(pmActionTypeObjsList != null && pmActionTypeObjsList.size() >0){
					for(Object[] petitionObj : pmActionTypeObjsList){
						KeyValueVO vo = new KeyValueVO();
						vo.setKey(commonMethodsUtilService.getLongValueForObject(petitionObj[0]));
						vo.setValue(commonMethodsUtilService.getStringValueForObject(petitionObj[1]));
						resultList.add(vo);
					}
				}
			}catch(Exception e){
				LOG.error("exception raised into PmRequestDetailsService of getPmActionTypeList()",e);
			}
			return resultList;
		}
		
		public boolean assingThePetitiontoOfficers(Long assignedByDeptDesignationOfficerId,Long assignedToDeptDesignationOfficerId,String assignType)
		{
			boolean isAssigned=false;
			try {
				
			} catch (Exception e) {
				LOG.error("exception raised into PmRequestDetailsService of assingThePetitiontoOfficers()",e);
			}
			return isAssigned;
		}
		
}