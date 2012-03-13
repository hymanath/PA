package com.itgrids.partyanalyst.service;

import java.util.List;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.PartyPageVO;
import com.itgrids.partyanalyst.dto.PartyVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.File;
public interface IPartyDetailsService {
	
	public List<SelectOptionVO> getAllPartysNames() ;

	public PartyVO getPartyDetails(Long partyId);

	public List<String> getPartyProfileDescriptionById(Long partyId);

	public List<FileVO> getNewsToDisplay(Long partyId, int firstResult,
			int maxResult, String queryType);

	public List<FileVO> getPartyLatestVideos(Long partyId, Integer startIndex,
			Integer maxRecords);

	public FileVO copyFileToFileVO(File file);

	public List<FileVO> getFirstThreePhotoGallaryDetail(Long partyId);

	public List<FileVO> getPartyPhotoGallaryDetailWithOutGallerySizeZero(
			Long partyId, int firstRecord, int maxRecord, String type);

	public List<FileVO> getPartyPhotoGallaryDetail(Long partyId,
			int firstRecord, int maxRecord, String type);
	
	public ResultStatus saveDescription(GallaryVO gallaryVO);
	
	public ResultStatus createNewGallaryOrUpdateGallary(GallaryVO gallaryVO,String createOrUpdate);
	
	public List<SelectOptionVO> getPartyGallarySelectList(Long partyId,String contentType);
	
	public List<FileVO> getElectionType();
	
	public List<FileVO> getPartyManifestoInfo(long partyId);

	public List<FileVO> getSelectedState(Long partyId);
	
	public ResultStatus uploadPartyManifesto(FileVO fileVO);
	
	public List<GallaryVO> getPartyProfileInfo(Long partyId);
	
	public ResultStatus deleteProfileDescById(Long profDescId);
	
	public ResultStatus updateProfileDescription(List<GallaryVO> gallaryVO , Long partyId);
	
	public ResultStatus subScribeEmailAlertForAUser(String emailId ,Long partyId);
	
	public GallaryVO getPartyGalleryDetails(Long galleryId,Long partyId);
	
	public List<SelectOptionVO> getElectionTypesBasedOnStateIdAndPartyId(Long partyId ,Long stateId);
	
	public List<SelectOptionVO> getElectionYearsBasedOnElectionTypeIdAndPartyId(Long electionTypeId , Long partyId , Long stateId);
	
	public List<FileVO> getPartyRelatedManifestoBasedOnYear(Long electionId,Long partyId,Long stateId);
	
	public List<FileVO> getPartyManifestoBasedOnStateIdAndPartyd(Long stateId,Long partyId);
	
	public List<FileVO> getPartyManifestoDetailsBasedOnSelection(PartyPageVO partyPageVO);
	
	public ResultStatus savePartyMessageFromPeople(GallaryVO gallaryVO);
		
	public String getPartyVisibility(Long gallaryId);
	
	public List<CandidateCommentsVO> getMessageToParty(Long partyId,int startIndex,int resultsCount);
}
