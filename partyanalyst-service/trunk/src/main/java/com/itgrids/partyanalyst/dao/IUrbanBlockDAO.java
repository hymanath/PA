package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UrbanBlock;

public interface IUrbanBlockDAO extends GenericDao<UrbanBlock, Long>{

	public List<Object[]> getUrbanBlocksForLocality(Long localityId);
}
