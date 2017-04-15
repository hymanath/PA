package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEditionsDAO;
import com.itgrids.partyanalyst.model.Editions;

public class EditionsDAO extends GenericDaoHibernate<Editions, Long> implements IEditionsDAO {
	public EditionsDAO(){
		super(Editions.class);
	} 
	public List<Object[]> getAllprintMediaAlerts(){ 
		Query query = getSession().createQuery("select model.newsPaper.newsPaperId, model.newsPaper.newsPaper from Editions model ");
		return query.list();
	}
}
