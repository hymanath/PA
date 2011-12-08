package com.itgrids.partyanalyst.service;

import java.util.List;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import java.util.List;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
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
	
	public ResultStatus uploadPartyManifesto(FileVO fileVO);
}
