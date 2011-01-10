package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Block;

public interface IBlockDAO extends GenericDao<Block, Long>{

	@SuppressWarnings("unchecked")
	public List findByWardIdAndBlockName(Long wardId,String blockName);
	
	public List<Block> findBlockByWardIdAndBlockName(Long wardId,String blockName);
}
