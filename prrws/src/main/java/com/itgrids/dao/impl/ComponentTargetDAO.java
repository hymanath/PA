package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IComponentTargetDAO;
import com.itgrids.model.ComponentTarget;
@Repository
public class ComponentTargetDAO extends GenericDaoHibernate<ComponentTarget, Long> implements IComponentTargetDAO{

	public ComponentTargetDAO() {
		super(ComponentTarget.class);
		
	}

}
