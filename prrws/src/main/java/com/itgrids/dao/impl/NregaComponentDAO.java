package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.INregaComponentDAO;
import com.itgrids.model.NregaComponent;

@Repository
public class NregaComponentDAO extends GenericDaoHibernate<NregaComponent, Long> implements INregaComponentDAO{

	public NregaComponentDAO() {
		super(NregaComponent.class);
	}

}
