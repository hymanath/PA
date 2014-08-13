package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyUserBoothAssign;

public interface ISurveyUserBoothAssignDAO extends GenericDao<SurveyUserBoothAssign, Long>{
	   public List<?> getBoothsForUser(long userId);
	   public List<?> getVoterDataForUser(long userId);
	public List<Long> getBoothIdsBySurveyUser(Long surveyUserId);
	
	public List<Object[]> getAllTheAssignedBoothsByConstituencyIdAndUserId(Long constituencyId,Long userId);



	public List<Long> getAssignedBoothsForUser(Long surveyUserId);
	
	public List<Long> getAssignedDetailsForUser(List<Long> surveyUserId);
	public List<Long> checkForSpecialBooth(Long boothId);

}
