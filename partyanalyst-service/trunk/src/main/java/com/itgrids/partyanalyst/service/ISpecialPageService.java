package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
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
}
