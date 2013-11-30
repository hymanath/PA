package com.itgrids.partyanalyst.service;

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.AnalysisVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.NewsAnalysisVO;

public interface INewsAnalysisService {
	
	
	public ResultStatus saveDesignationDetails(final String designationString);
	
	public ResultStatus savePartyDetails(final String partyShortName,final String PartyLongName);
	
	public NewsAnalysisVO analyseNewsWithSelectedParameters(AnalysisVO analysisVO);
	
	public List<FileVO> getSelectedNews(NewsAnalysisVO vo,Date fromDate,Date toDate,Integer startIndex,Integer maxIndex);
}
