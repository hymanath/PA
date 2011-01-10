package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DelimitationBlock;

public interface IDelimitationBlockDAO extends GenericDao<DelimitationBlock, Long>{

	public List<DelimitationBlock> findByDelimitationWardIdAndBlock(Long delimWardId,Long blockId);
}
