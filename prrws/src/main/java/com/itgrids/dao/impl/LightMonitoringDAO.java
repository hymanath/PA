 package com.itgrids.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILightMonitoringDAO;
import com.itgrids.model.District;
import com.itgrids.model.LightMonitoring;


@Repository
public class LightMonitoringDAO extends GenericDaoHibernate<LightMonitoring ,Long> implements ILightMonitoringDAO{

	@Autowired
	SessionFactory sessionFactory; 

	public LightMonitoringDAO() {
		super(LightMonitoring.class);
	}
	public List<LightMonitoring> getLiveDateForCurrentDateSelection(Date date){
		//Query query = getSession().createQuery(" select model from LightMonitoring model where date(model.surveyDate) = :date ");
		Query query = getSession().createQuery(" select model.isDeleted from LightMonitoring model where date(model.surveyDate) = :date ");
		
		      query.setDate("date", date);
		return  query.list();
	}
	
}


			