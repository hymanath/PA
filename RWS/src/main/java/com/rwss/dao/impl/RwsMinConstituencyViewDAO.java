package com.rwss.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rwss.dao.IRwsMinConstituencyViewDAO;
import com.rwss.model.RwsMinConstituencyView;

@Repository
public class RwsMinConstituencyViewDAO extends GenericDaoHibernate<RwsMinConstituencyView,String> implements IRwsMinConstituencyViewDAO {

	public RwsMinConstituencyViewDAO() {
		super(RwsMinConstituencyView.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;

}
