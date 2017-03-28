package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EventSurveyUser;

public interface IEventSurveyUserDAO extends GenericDao<EventSurveyUser, Long>{
	public List<Object[]> getUserDetailsByUnamePwd(String uname,String pwd);
	
	public List<EventSurveyUser> checkValidUserOrNot(String uname,String pwd);
	
	public Long checkUserBlockedOrNot(Long userId);
	public List<Object[]> getPeshiAppForGrievance(Date fromDate,Date toDate,String memberShipId);
	public List<Object[]> getFilePathUrlForComplaintIds(List<Long> complaintIds);
}
