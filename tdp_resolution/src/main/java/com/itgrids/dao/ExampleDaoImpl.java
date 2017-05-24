package com.itgrids.dao;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.model.ExampleModel;

@Repository("exampleDao")
public class ExampleDaoImpl extends GenericDaoHibernate<ExampleModel, Integer> implements ExampleDao {

	@Autowired
	SessionFactory sessionFactory;

	public ExampleDaoImpl() {
		super(ExampleModel.class);

	}

	public ExampleModel getExampleByUsername(String userName) {
		Criteria criteria =getSession().createCriteria(ExampleModel.class);
		criteria.add(Restrictions.eq("userName", userName));
		return (ExampleModel)criteria.uniqueResult();
	}

}
