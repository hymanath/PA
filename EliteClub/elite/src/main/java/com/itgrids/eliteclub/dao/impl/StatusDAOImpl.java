package com.itgrids.eliteclub.dao.impl;

import com.itgrids.eliteclub.dao.StatusDAO;
import com.itgrids.eliteclub.model.Status;

public class StatusDAOImpl extends AbstractDaoImpl<Status, Integer> implements StatusDAO{

	protected StatusDAOImpl()
	{
		super(Status.class);
	}

}
