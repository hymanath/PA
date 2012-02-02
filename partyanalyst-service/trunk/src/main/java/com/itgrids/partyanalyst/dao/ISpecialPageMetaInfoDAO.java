package com.itgrids.partyanalyst.dao;

import java.util.List;
import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.SpecialPageMetaInfo;

public interface ISpecialPageMetaInfoDAO extends GenericDao<SpecialPageMetaInfo, Long>{
	
	public List<Object[]> getMetaInfoForASpecialPage(Long specialPageId);

}
