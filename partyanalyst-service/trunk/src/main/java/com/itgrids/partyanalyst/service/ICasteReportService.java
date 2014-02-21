package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;

public interface ICasteReportService {
	public List<CastVO> getCasteWiseInfo(Long constituencyId,Long publicationId,String type,Long userId,String partialChecked);
	
	public ResultStatus getVoterAddressDetails(Long constituencyId,Long publicationId,Long userId);

}
