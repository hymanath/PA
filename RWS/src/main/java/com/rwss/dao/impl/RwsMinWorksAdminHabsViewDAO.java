package com.rwss.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rwss.dao.IRwsMinWorksAdminHabsViewDAO;
import com.rwss.model.RwsMinWorksAdminHabsView;

@Repository
public class RwsMinWorksAdminHabsViewDAO extends GenericDaoHibernate<RwsMinWorksAdminHabsView, String> implements IRwsMinWorksAdminHabsViewDAO  {

	@Autowired
	SessionFactory sessionFactory;
	
	public RwsMinWorksAdminHabsViewDAO() {
		super(RwsMinWorksAdminHabsView.class);
		// TODO Auto-generated constructor stub
	}

}
