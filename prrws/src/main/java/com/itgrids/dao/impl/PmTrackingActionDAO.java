package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.model.PmTrackingAction;
@Repository
public class PmTrackingActionDAO extends GenericDaoHibernate<PmTrackingAction, Long> {
	@Autowired
	SessionFactory sessionFactory;
	public PmTrackingActionDAO() {
		super(PmTrackingAction.class);
	}

}
