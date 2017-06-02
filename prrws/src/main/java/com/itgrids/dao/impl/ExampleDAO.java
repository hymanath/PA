package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IExampleDao;
import com.itgrids.model.ExampleModel;

@Repository
public class ExampleDAO extends GenericDaoHibernate<ExampleModel, Integer> implements IExampleDao {

	@Autowired
	SessionFactory sessionFactory;

	public ExampleDAO() {
		super(ExampleModel.class);

	}

}
