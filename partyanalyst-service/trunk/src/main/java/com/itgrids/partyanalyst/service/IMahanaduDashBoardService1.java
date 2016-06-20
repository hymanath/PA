package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.EmailAttributesVO;
import com.itgrids.partyanalyst.dto.MahanaduEventVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.StatesEventVO;

public interface IMahanaduDashBoardService1 {
	
	public List<MahanaduEventVO> LocationWiseEventAttendeeCounts(String startDate,String endDate,Long parenteventId,List<Long> subEventIds,Long reportLevelId,List<Long> stateIds,String stateType);
	public StatesEventVO stateWiseEventAttendeeCounts(String startDate,String endDate,Long parenteventId,List<Long> subEventIds,boolean forPdf);
	
	public ResultStatus sendEmailWithAttachment(EmailAttributesVO fileNamesVO);
	public EmailAttributesVO createMainPdfFile(String buildString,EmailAttributesVO fileNamesVO);
	
	public String calcPercantage(Long totalValue,Long subValue);
	public void getAllImages(Long parentId,List<Long> subEventIds,String startDate,String endDate,List<Long> stateIds);
	public List<MahanaduEventVO> getPublicrepresentatives(String startDateStr,String endDateStr,Long eventId,List<Long> subEventIds);
	
	public List<MahanaduEventVO> casteWiseEventAttendeeCounts(String startDate,String endDate,Long parenteventId,List<Long> subEventIds);
	public List<MahanaduEventVO> ageWiseEventAttendeeCounts(String startDate,String endDate,Long parenteventId,List<Long> subEventIds);
}

