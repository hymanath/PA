package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IMailsSendingService {
	
	public ResultStatus sendEmailFriendRequest(EmailDetailsVO emailDetailsVO);

	public ResultStatus acceptEmailFriendRequest(EmailDetailsVO emailDetailsVO);
	
	public ResultStatus sendMessageToConnectUser(EmailDetailsVO emailDetailsVO);
	
	public ResultStatus sendMailsToPasswordnotUpdatedusers();
	
	public ResultStatus acceptEmailForAnalyzeConstituency(EmailDetailsVO emailDetailsVO);
	
	public void acceptEmailForUserComments(EmailDetailsVO emailDetailsVO);
	
	public ResultStatus sendEmailForConnectedUsers(EmailDetailsVO emailDetailsVO);
	
	
	
	
	
}
