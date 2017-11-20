package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IWorkGroupDAO;
import com.itgrids.model.WorkGroup;

@Repository
public class WorkGroupDAO extends GenericDaoHibernate<WorkGroup, Long> implements IWorkGroupDAO{

	public WorkGroupDAO() {
		super(WorkGroup.class);
		
	}

}
