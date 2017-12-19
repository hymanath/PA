package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.model.NregaFAType;

import com.itgrids.dao.INregaFATypeDAO;
@Repository
public class NregaFATypeDAO extends GenericDaoHibernate<NregaFAType, Long> implements INregaFATypeDAO{

	public NregaFATypeDAO() {
		super(NregaFAType.class);
		
	}

}
