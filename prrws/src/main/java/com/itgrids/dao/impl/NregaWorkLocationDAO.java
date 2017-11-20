package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.INregaWorkLocationDAO;
import com.itgrids.model.NregaWorkLocation;

@Repository
public class NregaWorkLocationDAO extends GenericDaoHibernate<NregaWorkLocation, Long> implements INregaWorkLocationDAO{

	public NregaWorkLocationDAO() {
		super(NregaWorkLocation.class);
		
	}

}
