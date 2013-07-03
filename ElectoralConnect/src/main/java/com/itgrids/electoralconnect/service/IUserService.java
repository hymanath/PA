package com.itgrids.electoralconnect.service;

import java.util.List;

import com.itgrids.electoralconnect.dto.CommentVO;
import com.itgrids.electoralconnect.dto.RegistrationVO;
import com.itgrids.electoralconnect.dto.UserProfileVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IUserService {
	
	public String validateEmail(String emailId);
	
	public RegistrationVO registerUser(UserProfileVO userProfileVO);
	
	public RegistrationVO checkForValidUser(String username,String passward);
	
	public ResultStatus updateUserPassword(String password,Long userId);
	
	public RegistrationVO forgetPasswordService(String username);
	
	public ResultStatus saveComment(Long userId,Long annoncementId,String comment);
	
	//public List<CommentVO> getTop5CommentsCommentedByUser(Long announcementId);
	
	public List<CommentVO> getAllCommentsCommentedByUser(Long announcementId,int startIndex,int maxIndex);
}
