package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreSurveyUserAssignee;

public interface ICadreSurveyUserAssigneeDAO extends GenericDao<CadreSurveyUserAssignee, Long>{
	public List checkUserExists(Long userId,Date startDate);
	public Long getLatestUserByCadreSurveyUser(Long userId);
	public List<Object[]> getDuplicateUsersForUserId(Long userId);
	

}
