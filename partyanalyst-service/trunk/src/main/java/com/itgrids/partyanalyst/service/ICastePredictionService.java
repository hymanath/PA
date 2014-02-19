package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.UserVoterDetails;

public interface ICastePredictionService {
	
	public List<VoterVO> getCountForCaste(Long userId);
	
	public List<SelectOptionVO> getPredictedCasteList();
	
	public ResultStatus insertPredictedCasteIntoMainCasteTables(Long userId,Long casteStateId,Integer firstRecord,Integer maxRecords);
	
	public UserVoterDetails insertCasteAndPhoneNumberFromAndroid(Long voterId,Long casteStateId,String phoneNumber);

}
