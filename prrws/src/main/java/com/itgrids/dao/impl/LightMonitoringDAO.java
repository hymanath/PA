 package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILightMonitoringDAO;
import com.itgrids.model.LightMonitoring;


@Repository
public class LightMonitoringDAO extends GenericDaoHibernate<LightMonitoring ,Long> implements ILightMonitoringDAO{

	@Autowired
	SessionFactory sessionFactory; 

	public LightMonitoringDAO() {
		super(LightMonitoring.class);
	}
	public List<LightMonitoring> getLiveDateForCurrentDateSelection(Date date){
		Query query = getSession().createQuery(" select model from LightMonitoring model where date(model.surveyDate) = :date ");
		
		      query.setDate("date", date);
		return  query.list();
	}
	
}


			