package com.rwss.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rwss.dao.IRwsMinHabdetViewDAO;
import com.rwss.model.RwsMinHabdetView;

public class RwsMinHabdetViewDAO extends GenericDaoHibernate<RwsMinHabdetView,String> implements IRwsMinHabdetViewDAO {


	@Autowired
	SessionFactory sessionFactory;
	
	public RwsMinHabdetViewDAO() {
		super(RwsMinHabdetView.class);
		// TODO Auto-generated constructor stub
	}

	
	
}
