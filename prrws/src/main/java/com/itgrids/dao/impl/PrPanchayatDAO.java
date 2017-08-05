package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPrPanchayatDAO;
import com.itgrids.model.PrPanchayat;

@Repository
public class PrPanchayatDAO extends GenericDaoHibernate<PrPanchayat, Long>
		implements IPrPanchayatDAO {
	
	public PrPanchayatDAO()
	{
		super(PrPanchayat.class);
	}
}
