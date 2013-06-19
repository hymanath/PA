package com.itgrids.partyanalyst.service;





import java.util.List;

import com.itgrids.partyanalyst.dto.ContentDetailsVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;

public interface IContentManagementService {

	public ContentDetailsVO getSelectedContentAndRelatedGalleries(Long contentId, String requestFrom, Long requestPageId , String isCustomer);
	
	/*public ContentDetailsVO getSelectedContentAndRelatedGalleriesInPopup(Long contentId, String requestFrom, Long requestPageId , String isCustomer,List<FileGallary> allNewsListForPopup);*/
	
	public GallaryVO getResponseGallariesForSelectedGallary(Long fileGallaryId,Integer startIndex, Integer maxIndex);
	
	public GallaryVO getMainArticlesDetails(Long fileGallaryId, Integer startIndex, Integer maxIndex);
	public List<FileVO> getResponseTrackingNews(Long responseFileGallaryId);
	
}
