package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TabUserInfo;

public interface ITabUserInfoDAO extends GenericDao<TabUserInfo, Long> {
	
	public List<Object[]> getTotalTabUserImages();
	public List<Object[]> getSurveyUserBasicDetailsBySurveyUserIds(List<Long> cadreSurveyUserIds);
}
