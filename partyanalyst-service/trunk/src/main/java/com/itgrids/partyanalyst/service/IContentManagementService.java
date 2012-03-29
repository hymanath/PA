package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ContentDetailsVO;

public interface IContentManagementService {

	public ContentDetailsVO getSelectedContentAndRelatedGalleries(Long contentId, String requestFrom, Long requestPageId);
}
