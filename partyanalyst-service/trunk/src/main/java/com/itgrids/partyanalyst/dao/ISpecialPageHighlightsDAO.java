package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SpecialPageHighlights;

	public interface ISpecialPageHighlightsDAO extends GenericDao<SpecialPageHighlights, Long>{

	public List<Object> getMaxOrderNo(Long specialPageId);

	public List<Object[]> getSpecialPageHighLightsBySpecailPageId(Long specialPageId);
	
	public Integer deleteSpecialHighlightsDescription(Long id);
}
