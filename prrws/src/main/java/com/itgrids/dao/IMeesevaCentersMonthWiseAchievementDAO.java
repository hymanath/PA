package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.MeesevaCentersMonthWiseAchievement;

public interface IMeesevaCentersMonthWiseAchievementDAO extends GenericDao<MeesevaCentersMonthWiseAchievement, Long>{
	
	public int deleteRecordsFrmTable();
	public List<Object[]> getTargetAchFrDates(Date fromDate,Date toDate);
}
