package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;



import com.itgrids.partyanalyst.model.CasteCategoryGroup;

public interface ICasteCategoryGroupDAO extends GenericDao<CasteCategoryGroup,Long>{

	public List<Object[]> getCasteCategoryGroupNames(Long casteCategoryId);
	public List<Object[]> getAllCasteCategoryGroupDetails(Long casteCategoryId);
	public Long getCasteNamesOfCategories(Long casteCategoryId);
	public List<Object[]> getAllCasteCategoryGroupInfoDetails();
	
	public List<CasteCategoryGroup> getCasteCategoryGroupList();
	
}
