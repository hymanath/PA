package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.dao.IPrDistrictDAO;
import com.itgrids.model.PrDistrict;

public class PrDistrictDAO extends GenericDaoHibernate<PrDistrict, Long> implements IPrDistrictDAO {
	public PrDistrictDAO(){
		super(PrDistrict.class);
	}
}
