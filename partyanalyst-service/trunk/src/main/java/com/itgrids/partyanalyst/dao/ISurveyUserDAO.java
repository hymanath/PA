package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyUser;

public interface ISurveyUserDAO extends GenericDao<SurveyUser, Long>
{
	
	
	public Long getUserDetails(String userName,String password);
	
	/**
	 * @author  anilkumar
	 * Jul 2, 2014
	 * @param 
	 */
	public Object getUserDetailsAndUserType(String userName,String password);

	
	public List<Object[]> getSurveyUsersByUserType(Long userTypeId);
	
	public List<Object[]> getSurveyUsersByUserTypeForLeaderAssign(Long userTypeId);
	public List<Object[]> getUsersDetailsBySurveyUserIds(List<Long> userIds);

}
