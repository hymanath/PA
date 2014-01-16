package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.excel.booth.VoterVO;

public interface ICastePredictionService {
	
	public List<VoterVO> getCountForCaste(String usertype);

}
