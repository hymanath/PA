package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.model.ExampleModel;
import com.itgrids.model.TdpEmailmodel;

@Repository("tdpEmailDao")
public class TdpEmailDaoImpl extends GenericDaoHibernate<TdpEmailmodel, Integer> implements TdpEmailDao {

	@Autowired
	SessionFactory sessionFactory;

	public TdpEmailDaoImpl() {
		super(TdpEmailmodel.class);

	}

	@SuppressWarnings("unchecked")
	public  List<Object[]> getAllTdpEmailmodel() {
		Query criteria =getSession().createQuery("select model.tdpEmailId, model.email,model.isActive from" +
				" TdpEmail model");
		return criteria.list();
	}

}
