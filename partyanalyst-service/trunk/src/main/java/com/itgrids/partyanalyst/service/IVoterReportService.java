package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterReportVO;

public interface IVoterReportService {
	
	public VoterReportVO getVoterDetailsInaLocation(String range,Long rangeValue);
	
	public ResultStatus insertVotersPartyDataToIntermediateTables(Long reportLevelValue, Long publicationDateId,Long userId);
	
	public ResultStatus deletevotermodificationFromIntermediateTables(Long constituencyId,Long publicationId);
	
	public ResultStatus insertVotersCasteDataInIntermediateTables(Long reportLevelValue, Long publicationDateId,Long userId);
	
	 public ResultStatus deleteVoterModifiedData(Long constituencyId,Long publicationDateId);
	 
	public void  getVotersCastWiseDetailsInALocationFromIntermediateTable(Long userId,Long reportLvlId,Long locationId,Long publicationDateId,Long constituencyId,VoterCastInfoVO voterCastInfoVO);
	
	public void getPartyNGenderWiseVotersCountByPublIdInALocFromIntermedTable(Long userId,Long reportLvlId,Long levelValue,Long publicationDateId,Long constituencyId,VoterCastInfoVO mainVO);
	
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleMandal(List<SelectOptionVO> mandalsList,Long publicationDateId,Long userId,Long constituencyId);
	
	public List<VoterCastInfoVO> getVotersCastInfoForMultipleValues(List<SelectOptionVO> subList,Long publicationDateId,Long userId,Long constituencyId,Long locationLvl);
}
