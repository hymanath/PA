package com.itgrids.partyanalyst.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;

public interface ICasteReportService {
	public List<CastVO> getCasteWiseInfo(Long constituencyId,Long publicationId,String type,Long userId,String partialChecked);
	
	public ResultStatus getVoterAddressDetails(Long constituencyId,Long publicationId,Long userId);

	
	//anilUpdate
	public  List<SelectOptionVO> loadConstituenciesForReport(List<Long> notIds) ;
	public ResultStatus  generateXL(List<Long> constIds,Long topPercent,boolean notConsiderWeights,List<Long> notIds)  throws IOException ;
	 public List<CastVO> getPanchayatsInVoterRange(Long constitunecyId,Long publicationId,Long userId);



	
}
