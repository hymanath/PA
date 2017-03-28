package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.SpecialPageVO;
import com.itgrids.partyanalyst.model.SpecialPageInfo;

public interface ISpecialPageInfoDAO extends GenericDao<SpecialPageInfo,Long>{
	
	public List<SpecialPageInfo> getSpecialPageInfo(Long specialPageId);
	
	public List<Object[]> getSpecialPagesForHomePage();
	
	public List<SpecialPageInfo> getAllSpecialPageListForHomePage();
	
	public Long getSpecialPageInfoIdBySpecialPageId(Long specialPageId);
	
}
