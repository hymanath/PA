package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.EmailAttributesVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.StatesEventVO;

public interface IMahanaduDashBoardService1 {
	
	public List<MahanaduEventVO> LocationWiseEventAttendeeCounts(String startDate,String endDate,Long parenteventId,List<Long> subEventIds,Long reportLevelId,List<Long> stateIds,String stateType);
	public StatesEventVO stateWiseEventAttendeeCounts(String startDate,String endDate,Long parenteventId,List<Long> subEventIds);
	
	public ResultStatus sendEmailWithAttachment(EmailAttributesVO fileNamesVO);
	public EmailAttributesVO createMainPdfFile(String buildString);
	
	public String calcPercantage(Long totalValue,Long subValue);
}

