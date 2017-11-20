package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.INregaWorkDAO;
import com.itgrids.model.NregaWork;

@Repository
public class NregaWorkDAO extends GenericDaoHibernate<NregaWork, Long> implements INregaWorkDAO{

	public NregaWorkDAO() {
		super(NregaWork.class);
		
	}

}
