package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.SpecialPage;

public interface ISpecialPageDAO extends GenericDao<SpecialPage,Long>{
	
	public List<Object[]> getSpecialPageDetails(Long specialPageId);
}
