package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IPoliticalChangesService {
	
	public List<SelectOptionVO> getAllPoliticalInformationSources();
	
	public ResultStatus savePoliticalChangeDataReceivedFromUser(PoliticalChangesVO politicalChangesVo);	
	
	public List<PoliticalChangesVO> getAllPoliticalChanges(Long userId);
	
	public void deltePoliticalChangeBasedOnUser(Long politicalChangeId);
	
	public PoliticalChangesVO getExternalPersonDetails(Long politicalChangeId);
	
	public PoliticalChangesVO getDetailsBylocalPoliticalChangeId(Long politicalChangeId);
}
