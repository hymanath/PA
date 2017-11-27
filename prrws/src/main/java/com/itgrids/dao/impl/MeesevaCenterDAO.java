package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IMeesevaCenterDAO;
import com.itgrids.model.MeesevaCenter;

@Repository
public class MeesevaCenterDAO extends GenericDaoHibernate<MeesevaCenter, Long> implements IMeesevaCenterDAO{

	public MeesevaCenterDAO() {
		super(MeesevaCenter.class);
	}

}
