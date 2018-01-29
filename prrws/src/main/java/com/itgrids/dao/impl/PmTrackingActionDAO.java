package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmTrackingActionDAO;
import com.itgrids.model.PmTrackingAction;
@Repository
public class PmTrackingActionDAO extends GenericDaoHibernate<PmTrackingAction, Long> implements IPmTrackingActionDAO{
	@Autowired
	SessionFactory sessionFactory;
	public PmTrackingActionDAO() {
		super(PmTrackingAction.class);
	}

	public List<Object[]> getActionsList(){
		return getSession().createQuery(" select distinct model.pmTrackingActionId,model.actionName from PmTrackingAction model ").list();
	}
}
