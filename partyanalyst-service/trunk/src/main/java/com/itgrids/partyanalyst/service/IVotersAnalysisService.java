package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;

import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;

public interface IVotersAnalysisService {
	
	public List<SelectOptionVO> publicationDetailsBasedOnConstituency(Long constituencyId);
	
	public List<VoterVO> getVoterDetails(Long publicationDateId, Long boothId,
			Long panchayatId ,Integer startIndex , Integer maxRecords , String order,
			String columnName);
	
	public List<Long> getImpFamiles(Long id,Long publicationDateId,String name);

	public VotersInfoForMandalVO getVotersCount(String type,Long id,Long publicationDateId);
}
