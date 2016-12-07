package com.itgrids.cardprint.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.cardprint.dao.IElectionTypeDAO;
import com.itgrids.cardprint.model.ElectionType;

public class ElectionTypeDAO  extends GenericDaoHibernate<ElectionType, Long> implements IElectionTypeDAO{

	public ElectionTypeDAO() {
		super(ElectionType.class);
	}

}
