package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IMeesevaCentersAchievementDAO;
import com.itgrids.model.MeesevaCentersAchievement;

@Repository
public class MeesevaCentersAchievementDAO extends GenericDaoHibernate<MeesevaCentersAchievement, Long> implements IMeesevaCentersAchievementDAO{

	public MeesevaCentersAchievementDAO() {
		super(MeesevaCentersAchievement.class);
	}
	
	public List<Object[]> getMeesevaTargetAchieveCounts(){
		Query query = getSession().createQuery("select model.monthName,"
				+ "model.target,"
				+ "model.achievement"
				+ " from MeesevaCentersAchievement model"
				+ " where model.isDeleted = 'N'");
		
		return query.list();
	}

}
