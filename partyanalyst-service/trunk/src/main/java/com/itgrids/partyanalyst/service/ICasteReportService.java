package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CastVO;

public interface ICasteReportService {
	public List<CastVO> getCasteWiseInfo(Long constituencyId,Long publicationId,String type,Long userId);

}
