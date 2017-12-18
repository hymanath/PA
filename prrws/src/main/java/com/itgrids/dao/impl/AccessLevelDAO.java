package com.itgrids.dao.impl;

import java.io.Serializable;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IAccessLevelDAO;
import com.itgrids.model.AccessLevel;

@Repository
public class AccessLevelDAO extends GenericDaoHibernate<AccessLevel, Long> implements IAccessLevelDAO{

	public AccessLevelDAO() {
		super(AccessLevel.class);
	}

}
