package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.INregaComponentServiceDAO;
import com.itgrids.model.NregaComponentService;

@Repository
public class NregaComponentServiceDAO extends GenericDaoHibernate<NregaComponentService, Long> implements INregaComponentServiceDAO{

	public NregaComponentServiceDAO() {
		super(NregaComponentService.class);
	}

}
