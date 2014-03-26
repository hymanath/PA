package com.itgrids.partyanalyst.service;

import java.io.IOException;
import java.util.List;

import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ICasteReportService {
	public List<CastVO> getCasteWiseInfo(Long constituencyId,Long publicationId,String type,Long userId,String partialChecked);
	
	public ResultStatus getVoterAddressDetails(Long constituencyId,Long publicationId,Long userId);

	
	//anilUpdate
	public  List<SelectOptionVO> loadConstituenciesForReport(List<Long> notIds) ;
	public ResultStatus  generateXL(List<Long> constIds,Long topPercent,boolean notConsiderWeights,List<Long> notIds)  throws IOException ;
	 public List<CastVO> getPanchayatsInVoterRange(Long constitunecyId,Long publicationId,Long userId,boolean considerPartial);

	 public void updatePriority();
	 
	 public ResultStatus getVoterAddressDetailsForCriticalPanchayats(Long constituencyId,List<Long> panchayatIdsList,Long publicationId,Long userId);
	
}
