package com.itgrids.eliteclub.dao.impl;

import com.itgrids.eliteclub.model.ContentType;

import com.itgrids.eliteclub.dao.ContentTypeDAO;

public class ContentTypeDAOImpl extends AbstractDaoImpl<ContentType, Integer> implements ContentTypeDAO{

	protected ContentTypeDAOImpl()
	{
		super(ContentType.class);
	}

}
