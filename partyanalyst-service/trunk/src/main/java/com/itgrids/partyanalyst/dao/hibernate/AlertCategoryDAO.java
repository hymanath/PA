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
}
