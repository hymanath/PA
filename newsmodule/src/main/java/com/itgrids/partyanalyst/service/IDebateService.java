package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.DebateDetailsVO;
import com.itgrids.partyanalyst.dto.DebateVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IDebateService {

	public DebateVO getDebateDetailsForSelected(Long debateId);
	
	public ResultStatus saveDebateDetails(final DebateDetailsVO debateDetailsVO);
	
	public ResultStatus saveNewRole(Long userId, String newRole);
	
	public ResultStatus saveNewCharacteristic(Long userId, String newRole);
	
	public ResultStatus saveNewDebateQuestion(Long userId, String newRole);
	
	public ResultStatus insertChannelDetails(Long userId, String newRole);
	
	public ResultStatus insertObserverDetails(Long userId, String newRole);
	
	
}
