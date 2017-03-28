package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SpecialPageData;

public interface ISpecialPageDataDAO extends GenericDao<SpecialPageData, Long>{
	
	public List<String> getSpecialPageDataBySpecialPageId(Long specialPageId);
	
	public List<SpecialPageData> getSpecialPageDataObjectBySpecialPageId(Long specialPageId);

}
