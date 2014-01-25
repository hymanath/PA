package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;

public interface ICastePredictionService {
	
	public List<VoterVO> getCountForCaste(Long userId);
	
	public List<SelectOptionVO> getPredictedCasteList();
	
	public ResultStatus insertPredictedCasteIntoMainCasteTables(Long userId,Long casteStateId,Integer firstRecord,Integer maxRecords);

}
