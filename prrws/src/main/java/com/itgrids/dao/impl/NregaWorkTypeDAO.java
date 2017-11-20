package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.INregaWorkTypeDAO;
import com.itgrids.model.NregaWorkType;

@Repository
public class NregaWorkTypeDAO extends GenericDaoHibernate<NregaWorkType, Long> implements INregaWorkTypeDAO{

	public NregaWorkTypeDAO() {
		super(NregaWorkType.class);
		
	}

}
