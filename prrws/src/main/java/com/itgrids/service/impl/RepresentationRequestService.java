package com.itgrids.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;
import com.itgrids.dao.IDocumentDAO;
import com.itgrids.dao.ILocationAddressDAO;
import com.itgrids.dao.IPetitionMemberDAO;
import com.itgrids.dao.IPetitionReffererCandidateDAO;
import com.itgrids.dao.IPetitionReffererDAO;
import com.itgrids.dao.IPetitionReffererDocumentDAO;
import com.itgrids.dao.IPetitionSubWorkLocationDetailsDAO;
import com.itgrids.dao.IPetitionWorkDetailsDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.PetitionMemberVO;
import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.model.Document;
import com.itgrids.model.LocationAddress;
import com.itgrids.model.PetitionMember;
import com.itgrids.model.PetitionRefferer;
import com.itgrids.model.PetitionReffererDocument;
import com.itgrids.model.PetitionSubWorkLocationDetails;
import com.itgrids.model.PetitionWorkDetails;
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
	
	@Autowired
	private IPetitionMemberDAO petitionMemberDAO;
	@Autowired
	private IPetitionReffererCandidateDAO petitionReffererCandidateDAO;
	
	@Autowired
	private IPetitionReffererDAO petitionReffererDAO;
	
	@Autowired
	private IPetitionReffererDocumentDAO petitionReffererDocumentDAO;
	
	@Autowired
	private IPetitionWorkDetailsDAO petitionWorkDetailsDAO;
	
	@Autowired
	private IPetitionSubWorkLocationDetailsDAO petitionSubWorkLocationDetailsDAO;
	
	@Autowired
	private ILocationAddressDAO locationAddressDAO;
	
	public ResponseVO saveRepresentRequestDetails(RepresentationRequestVO dataVO){
		ResponseVO responseVO = new ResponseVO();
		try {
			
			/** Start Petition Member Details saving */
			
				LocationAddress petitionMemberAddress = null;
				if(dataVO.getCandidateAddressVO() != null)
					petitionMemberAddress = saveLocationAddress(dataVO.getCandidateAddressVO());
				PetitionMember petitionMember = savePetitionMember(dataVO);
				
				if(dataVO.getPetitionMemberVO().getFilesList() != null && dataVO.getPetitionMemberVO().getFilesList().size()>0){
					for (MultipartFile file : dataVO.getPetitionMemberVO().getFilesList()) {
						Document petitionMemberDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL,dataVO.getUserId());
					}
				}
				
			/** End Petition Member Details saving */
			
			/** Start Petition Referrer Details */
				
				PetitionRefferer petitionRefferer = savePetitionReferralDetails(petitionMember.getPetitionMemberId(),dataVO.getReferrerCandidateId(),dataVO.getUserId());
				if(dataVO.getFilesList() != null && dataVO.getFilesList().size()>0){
					for (MultipartFile file : dataVO.getFilesList()) {
						Document petitionRefDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL,dataVO.getUserId());
						PetitionReffererDocument petitionReffererDocument = savePetitionReffererDocument(petitionRefferer.getPetitionReffererId(),petitionRefDocument.getDocumentId(),dataVO.getUserId());
					}
				}
			
			/** Start Petition Referrer Details */
			
			PetitionWorkDetails petitionWorkDetails = savePetitionWorkDetails(petitionMember.getPetitionMemberId(),dataVO);
			if(dataVO.getFilesList() != null && dataVO.getFilesList().size()>0){
				for (MultipartFile file : dataVO.getFilesList()) {
					Document petitionWorkDocument = saveDocument(file,IConstants.STATIC_CONTENT_FOLDER_URL,dataVO.getUserId());
					PetitionSubWorkLocationDetails petitionSubWorkLocationDetails = savePetitionSubWorkDetails(petitionWorkDetails.getPetitionWorkDetailsId(),dataVO);
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
				if(petitionMemberVO != null){
					 petitionMember = new PetitionMember();
					petitionMember.setCandidateName(petitionMemberVO.getPetitionMemberVO().getName());
					petitionMember.setRepresentationDate(format.parse(petitionMemberVO.getPetitionMemberVO().getRepresentationDate()));
					petitionMember.setEndorsmentDate(format.parse(petitionMemberVO.getPetitionMemberVO().getEndorsmentDate()));
					petitionMember.setMobileNo(petitionMemberVO.getPetitionMemberVO().getMobileNo());
					petitionMember.setEmailId(petitionMemberVO.getPetitionMemberVO().getEmailId());
					petitionMember.setVoterCardNo(petitionMemberVO.getPetitionMemberVO().getVoterCardNo());
					petitionMember.setIsDeleted("N");
					petitionMember.setIsExpired("N");
					petitionMember.setInsertedUserId(petitionMemberVO.getUserId());
					petitionMember.setUpdatedUserId(petitionMemberVO.getUserId());
					petitionMember.setInsertedTime(dateUtilService.getCurrentDateAndTime());
					petitionMember.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
					petitionMember = petitionMemberDAO.save(petitionMember);
				}
			} catch (Exception e) {
				LOG.error("Exception Occured in RepresentationRequestService @ savePetitionMember() "+e.getMessage());
			}
		return petitionMember;
	}
	
	
	public PetitionSubWorkLocationDetails savePetitionSubWorkDetails(Long petitionWorkDetailsId,RepresentationRequestVO dataVO){
		PetitionSubWorkLocationDetails petitionSubWorkLocationDetails = null;
		try {
			if(dataVO.getPetitionMemberVO() != null){
				petitionSubWorkLocationDetails = new PetitionSubWorkLocationDetails();
				petitionSubWorkLocationDetails.setPetitionWorkDetailsId(petitionWorkDetailsId);
				petitionSubWorkLocationDetails.setPetitionDepartmentId(dataVO.getDeptId());
				petitionSubWorkLocationDetails.setDescription(dataVO.getProjectDescription());
				petitionSubWorkLocationDetails.setIsDeleted("N");
				petitionSubWorkLocationDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				petitionSubWorkLocationDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				petitionSubWorkLocationDetails.setInsertedUserId(dataVO.getUserId());
				petitionSubWorkLocationDetails.setUpdatedUserId(dataVO.getUserId());
				
				if(dataVO.getCandidateAddressVO() != null){
					LocationAddress workLocatinAddress = saveLocationAddress(dataVO.getCandidateAddressVO());
					petitionSubWorkLocationDetails.setAddressId(workLocatinAddress.getLocationAddressId());
				}
				
				
				petitionSubWorkLocationDetails = petitionSubWorkLocationDetailsDAO.save(petitionSubWorkLocationDetails);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in RepresentationRequestService @ savePetitionSubWorkDetails() "+e.getMessage());
		}
		return petitionSubWorkLocationDetails;
	}
	
	public PetitionWorkDetails savePetitionWorkDetails(Long petitionMemberId,RepresentationRequestVO dataVO){
		PetitionWorkDetails petitionWorkDetails = null;
		try {
			if(dataVO.getPetitionMemberVO() != null){
				petitionWorkDetails = new PetitionWorkDetails();
				petitionWorkDetails.setPetitionMemberId(petitionMemberId);
				petitionWorkDetails.setWorkName(dataVO.getWorkName());
				petitionWorkDetails.setNoOfWorks(dataVO.getNoOfWorks());
				petitionWorkDetails.setSubject(dataVO.getSubject());
				petitionWorkDetails.setPetitionDepartmentId(dataVO.getDeptId());
				petitionWorkDetails.setIsPreviousPetition(dataVO.getIsPreviousPetition());
				petitionWorkDetails.setPreviousPetitionRefNo(dataVO.getPreviousPetitionRefNo());
				petitionWorkDetails.setProjectDescription(dataVO.getProjectDescription());
				petitionWorkDetails.setIsDeleted("N");
				petitionWorkDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				petitionWorkDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				petitionWorkDetails.setInsertedUserId(dataVO.getUserId());
				petitionWorkDetails.setUpdatedUserId(dataVO.getUserId());
				petitionWorkDetails = petitionWorkDetailsDAO.save(petitionWorkDetails);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in RepresentationRequestService @ savePetitionWorkDetails() "+e.getMessage());
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
				petitionReffererDocument = petitionReffererDocumentDAO.save(petitionReffererDocument);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in RepresentationRequestService @ savePetitionReffererDocument() "+e.getMessage());
		}
		return petitionReffererDocument;
	}
	
	public PetitionRefferer savePetitionReferralDetails(Long petitionMemeberId,Long refferalCandidateId,Long userId){
		PetitionRefferer petitionRefferer = null;
		try {
			if(petitionMemeberId != null && petitionMemeberId.longValue()>0L){
				petitionRefferer = new PetitionRefferer();
				petitionRefferer.setPetitionMemberId(petitionMemeberId);
				petitionRefferer.setPetitionReffererCandidateId(refferalCandidateId);
				petitionRefferer.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				petitionRefferer.setUpdatedTime(dateUtilService.getCurrentDateAndTime());petitionRefferer.setIsDeleted("N");
				petitionRefferer.setInsertedUserId(userId);
				petitionRefferer.setUpdatedUserId(userId);
				petitionRefferer = petitionReffererDAO.save(petitionRefferer);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in RepresentationRequestService @ savePetitionReferralDetails() "+e.getMessage());
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
				document.setDocumentPath(staticPath+"//"+datePath+".jpg");
				document.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				document.setInsertedUserId(userId);
				document = documentDAO.save(document);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in RepresentationRequestService @ saveDocument() "+e.getMessage());
		}
		return document;
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
		    	List<Object[]> referalObjs = petitionReffererCandidateDAO.getCandidatseDetailsByDesignationAndLocation(dataVo.getDeptId(),dataVo.getLocationLevelId(),dataVo.getLocationValue());
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
}
