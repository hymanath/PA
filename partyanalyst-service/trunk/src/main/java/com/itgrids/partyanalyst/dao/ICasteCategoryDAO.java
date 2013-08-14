package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CasteCategory;

public interface ICasteCategoryDAO extends GenericDao<CasteCategory, Long>{
	
	public List<Object[]> getCasteCategoryDetails();
	public List<Object[]> getAllCasteCategoryDetails();
	
	public List<CasteCategory> getCasteCategoryList();
	
}
