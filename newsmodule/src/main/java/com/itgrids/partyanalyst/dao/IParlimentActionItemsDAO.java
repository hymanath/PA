package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ParlimentActionItems;

public interface IParlimentActionItemsDAO extends GenericDao<ParlimentActionItems, Long>{

	public List<Object[]> getParlimentActionItems(List<Long> pfbIds);
}
