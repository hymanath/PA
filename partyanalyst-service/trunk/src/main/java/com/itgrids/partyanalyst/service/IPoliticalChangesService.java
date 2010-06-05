package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IPoliticalChangesService {
	
	public List<SelectOptionVO> getAllPoliticalInformationSources();
	
	public void savePoliticalChangeDataReceivedFromUser(PoliticalChangesVO politicalChangesVO);
	
	public List<PoliticalChangesVO> getAllPoliticalChanges(Long userId);
	
	public void deltePoliticalChangeBasedOnUser(Long politicalChangeId);
	
	public PoliticalChangesVO getExternalPersonDetails(Long politicalChangeId);
}
