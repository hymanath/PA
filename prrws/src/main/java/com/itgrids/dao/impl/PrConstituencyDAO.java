package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.dao.IPrConstituencyDAO;
import com.itgrids.model.PrConstituency;

public class PrConstituencyDAO extends GenericDaoHibernate<PrConstituency, Long> implements IPrConstituencyDAO {
	public PrConstituencyDAO(){
		super(PrConstituency.class);
	}
}
