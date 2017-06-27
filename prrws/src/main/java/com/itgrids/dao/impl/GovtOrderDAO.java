package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.dao.IGovtOrderDAO;
import com.itgrids.model.GovtOrder;

public class GovtOrderDAO extends GenericDaoHibernate<GovtOrder, Long> implements IGovtOrderDAO {
	public GovtOrderDAO(){
		super(GovtOrder.class);
	}
}
