package com.itgrids.cardprint.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.cardprint.dao.IDistrictDAO;
import com.itgrids.cardprint.model.District;

public class DistrictDAO extends GenericDaoHibernate<District, Long> implements IDistrictDAO {

	public DistrictDAO(){
		super(District.class);
	}

}
