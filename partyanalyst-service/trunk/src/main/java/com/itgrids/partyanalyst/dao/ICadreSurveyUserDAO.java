package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreSurveyUser;

public interface ICadreSurveyUserDAO extends GenericDao<CadreSurveyUser, Long>{
	
	public List<Long> getUserByUserNameAndPassword(String userName , String password);

	public List<Object[]> getCadreSurveyUsersList(List<Long> getCadreSurveyUsersList);
	
	public List<Object[]> getUserMobileNos(Set<Long> ids);
	
	public Long  getTotalCadreSurveyDetails();
	
	public List<CadreSurveyUser> getCadreSurveyUserByUsername(String username);
	
	public List<Object[]> getAllUserMobileNos(Long locationType,List<Long> locationIds);
	
	public List<CadreSurveyUser> getByUserNameAndPassword(String userName , String password);
	
	public List<Object[]> getCadreSurveyUserDetails(List<Long> ids);
	
	public List<Long> getCadreSurveyUserDetailsByType();
	
	public List<Object[]> getCadreSurveyUserList(List<Long> assignedCadreSurveyUsers);
	
	public Object[] getCadreSurveyUserBasicDetails(Long cadreSurveyUserId);
}
