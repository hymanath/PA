package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IMeesevaCentersMonthWiseAchievementDAO;
import com.itgrids.model.MeesevaCentersMonthWiseAchievement;

@Repository
public class MeesevaCentersMonthWiseAchievementDAO extends GenericDaoHibernate<MeesevaCentersMonthWiseAchievement, Long> implements IMeesevaCentersMonthWiseAchievementDAO{

	public MeesevaCentersMonthWiseAchievementDAO() {
		super(MeesevaCentersMonthWiseAchievement.class);
	}

	public int deleteRecordsFrmTable(){
		Query query = getSession().createQuery("delete from MeesevaCentersMonthWiseAchievement");
		return query.executeUpdate();
	}
	
	public List<Object[]> getTargetAchFrDates(Date fromDate,Date toDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.target,"
				+ "model.ruralAchievement,"
				+ "model.urbanAchievement,"
				+ "date(model.date)"
				+ " from MeesevaCentersMonthWiseAchievement model"
				+ " where");
		if(fromDate != null && toDate != null){
			sb.append(" date(model.date) between :fromDate and :toDate");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(fromDate != null && toDate != null){
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
}
