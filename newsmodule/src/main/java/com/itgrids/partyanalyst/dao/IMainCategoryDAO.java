package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;


import com.itgrids.partyanalyst.model.MainCategory;

public interface IMainCategoryDAO extends GenericDao<MainCategory, Long>{
	public List<Object[]> getCategories();
}
