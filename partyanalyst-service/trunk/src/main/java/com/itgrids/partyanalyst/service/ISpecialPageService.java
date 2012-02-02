package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CustomPageVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.MetaInfoVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SpecialPageVO;

public interface ISpecialPageService {
	
	public List<String> getSpecialPageDescription(Long specialPageId);
	
	public ResultStatus subScribeEmailAlertForAEvent(String emailId,Long specialPageId) ;

	public List<SpecialPageVO> getEventProfileInfo(Long specialPageId);
	
	public ResultStatus deleteEventProfileDescById(Long specialPageDescriptionId);
	
	public ResultStatus saveDescription(SpecialPageVO specialPageVO);
	
	public ResultStatus updateEventProfileDescripton(List<SpecialPageVO> specialPageVO,Long specialPageId);
	
	public ResultStatus createNewGallaryOrUpdateGallary(GallaryVO gallaryVO,String createOrUpdate);
	
	public List<SelectOptionVO> getSource();
	
	public List<SelectOptionVO> getLanguage();
	
	public ResultStatus uploadAFile(FileVO fileVO);
	
	public Long getLocationScopeValue(Long scope,String locationValue);
	
	public List<SelectOptionVO> getEventGallarySelectList(Long specialPageId,
				String contentType);
				
	public List<SelectOptionVO> getPartyGallarySelectList(Long specialPageId,
			String contentType);
			
	public SpecialPageVO getSpecialPageBasicDetails(Long specialPageId);

	public List<FileVO> getPhotoGalleryBasedOnSpecialPageId(Long specialPageId);

	public List<FileVO> getNewsGalleryBasedOnSpecialPageId(Long specialPageId,int startingRecord,int maxRecord,String type);

	public List<FileVO> getVideoGalleryBasedOnSpecialPageId(Long specialPageId ,int startingRecord,int maxRecord);

	public List<FileVO> setGallaryObjectToFileVO(List<Object[]> records);

	public List<FileVO> getSpecialPageGallaryDetailWithOutGallerySizeZero(
			Long specialPageId, int firstRecord, int maxRecord, String type);
	
	public List<CustomPageVO> getCustomPagesOfASpecialPage(Long specialPageId);
	
	public ResultStatus createNewSpecialPage(GallaryVO gallaryVO);
	
	public List<FileVO> getSpecialPageGallaryDetail(Long specialPageId,int firstRecord,int maxRecord,String type);
	
	public MetaInfoVO getMetaInfoForASpecialPage(Long specialPageId);
	
	public ResultStatus saveMetaInfoForASpecialPage(MetaInfoVO metaInfoVO);
}
