package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IMailsSendingService {
	
	public ResultStatus sendEmailFriendRequest(String userName,String email,String requestFrom,String senderName , String msg);

	public ResultStatus acceptEmailFriendRequest(String userName,String email,String requestFrom,String senderName);
	
	public ResultStatus sendMessageToConnectUser(String userName,String email,String requestFrom,String msg,String senderName);
}
