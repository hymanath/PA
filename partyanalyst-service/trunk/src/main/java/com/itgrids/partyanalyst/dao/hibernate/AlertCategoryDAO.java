package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAlertCategoryDAO;
import com.itgrids.partyanalyst.model.AlertCategory;

public class AlertCategoryDAO extends GenericDaoHibernate<AlertCategory, Long>
		implements IAlertCategoryDAO {
	public AlertCategoryDAO(){
		super(AlertCategory.class);
	}
	public List<Object[]> getAllCategory(){
		Query query = getSession().createQuery("select model.alertCategoryId, model.category from AlertCategory model   ");
		return query.list();
	}
	public List<Object[]> getAllCategoryOrderBy(){
		Query query = getSession().createQuery("select model.alertCategoryId, model.category from AlertCategory model order by model.order  ");
		return query.list();
	}
	public List<Object[]> getAllCategory1(){
		Query query = getSession().createQuery("select model.alertCategoryId, model.category from AlertCategory model where model.alertCategoryId  not in (1) order by model.order ");
		return query.list();
	}
	public List<Object[]> getAlertCategoryByCategoryIds(List<Long> alertCategoryIds){
		Query query = getSession().createQuery("select model.alertCategoryId, model.category,' ' from AlertCategory model " +
				                               " where model.alertCategoryId in(:alertCategoryIds) order by model.order  ");
		query.setParameterList("alertCategoryIds",alertCategoryIds);
		return query.list();
	}
	public List<Object[]> getAllCategoryForLocationWiseGrievance(){
		Query query = getSession().createQuery("select model.alertCategoryId, model.category from AlertCategory model where model.alertCategoryId in (2,3,4,5) order by model.order  ");
		return query.list();
	}
	
	public Long getIdOfName(String category){
		Query query = getSession().createQuery("select model.alertCategoryId from " +
				" AlertCategory model where model.category =:category  ");
		return (Long) query.uniqueResult();
	}
}
