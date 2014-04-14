package com.itgrids.eliteclub.dao.impl;

import com.itgrids.eliteclub.dao.CategoryDAO;
import com.itgrids.eliteclub.model.Category;

public class CategoryDAOImpl extends AbstractDaoImpl<Category, Integer>implements CategoryDAO{

	protected CategoryDAOImpl()
	{
		super(Category.class);
	}

}
