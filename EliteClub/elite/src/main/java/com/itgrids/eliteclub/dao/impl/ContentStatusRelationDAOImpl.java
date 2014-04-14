package com.itgrids.eliteclub.dao.impl;

import com.itgrids.eliteclub.dao.ContentStatusRelationDAO;
import com.itgrids.eliteclub.model.ContentStatusRelation;

public class ContentStatusRelationDAOImpl extends AbstractDaoImpl<ContentStatusRelation, Integer> implements ContentStatusRelationDAO{

	protected ContentStatusRelationDAOImpl()
	{
		super(ContentStatusRelation.class);
	}

}
