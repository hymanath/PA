package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPrConstituencyDAO;
import com.itgrids.model.PrConstituency;

@Repository
public class PrConstituencyDAO extends GenericDaoHibernate<PrConstituency, Long> implements IPrConstituencyDAO {
	public PrConstituencyDAO(){
		super(PrConstituency.class);
	}
}
