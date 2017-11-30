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
import com.itgrids.dao.IPetitionMemberDAO;
import com.itgrids.dto.AddressVO;
import com.itgrids.dto.PetitionMemberVO;
import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.dto.ResponseVO;
import com.itgrids.model.Document;
import com.itgrids.model.LocationAddress;
import com.itgrids.model.PetitionMember;
import com.itgrids.service.IRepresentationRequestService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;

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
	
	public ResponseVO saveRepresentRequestDetails(final RepresentationRequestVO dataVO){
		ResponseVO responseVO = new ResponseVO();
		try {
			
			// Petition Member Saving
			PetitionMember petitionMember = savePetitionMember(dataVO);
			LocationAddress address = saveLocationAddress(dataVO.getCandidateAddressVO());
			// Document Saving
			Document document = saveDocument(dataVO.getFile(),"","D:\\static_content\\voter_images\\",dataVO.getUserId());
			
			
		} catch (Exception e) {
			responseVO.setResponseCode("1");
			responseVO.setMessage("Problem Occured while updation Details.");
			LOG.error("Exception Occured in RepresentationRequestService "+responseVO.getMessage());
		}
		return responseVO;
	}
	
	public Document saveDocument(MultipartFile file,String sourcePath, String destinationPath,Long userId){
		Document document = new Document();
		try {
			commonMethodsUtilService.copyFile(file.getOriginalFilename(),destinationPath);
			byte[] fileData = file.getBytes();
			Files.write(fileData,new File(destinationPath+"abc.jpg"));
			document.setDocumentPath(destinationPath);
			document.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			document.setInsertedUserId(userId);
			documentDAO.save(document);
			
		} catch (Exception e) {
			LOG.error("Exception Occured in saveDocument "+e.getMessage());
		}
		return document;
	}
	public PetitionMember savePetitionMember(RepresentationRequestVO petitionMemberVO){
		PetitionMember petitionMember = new PetitionMember();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			try {
				if(petitionMemberVO != null){
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
					petitionMemberDAO.save(petitionMember);
				}
			} catch (Exception e) {
				LOG.error("Exception Occured in savePetitionMember "+e.getMessage());
				petitionMember = null;
			}
		return petitionMember;
	}
	
	public LocationAddress saveLocationAddress(AddressVO addressVO){
		LocationAddress locationAddress = new LocationAddress();
		try {
			
		} catch (Exception e) {
			LOG.error("Exception Occured in savePetitionMember "+e.getMessage());
			locationAddress = null;
		}
		return locationAddress;
	}
	
	
}
