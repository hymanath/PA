package com.rwss.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rwss.dao.IRwsMinStressedHabViewDAO;
import com.rwss.model.RwsMinStressedHabView;

public class RwsMinStressedHabViewDAO extends GenericDaoHibernate<RwsMinStressedHabView, String> implements IRwsMinStressedHabViewDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public RwsMinStressedHabViewDAO() {
		super(RwsMinStressedHabView.class);
	}

}
