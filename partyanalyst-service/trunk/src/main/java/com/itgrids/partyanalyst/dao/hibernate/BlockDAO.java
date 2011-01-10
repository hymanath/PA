package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBlockDAO;
import com.itgrids.partyanalyst.model.Block;

public class BlockDAO extends GenericDaoHibernate<Block ,Long> implements IBlockDAO{
	
	public BlockDAO(){
		super(Block.class);
	}

	@SuppressWarnings("unchecked")
	public List findByWardIdAndBlockName(Long wardId, String blockName) {
		
		Object[] params = {wardId,blockName};
		return getHibernateTemplate().find("select count(model.blockId) from Block model where model.ward.wardId = ? and model.blockName = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List<Block> findBlockByWardIdAndBlockName(Long wardId,
			String blockName) {
		Object[] params = {wardId,blockName};
		return getHibernateTemplate().find("from Block model where model.ward.wardId = ? and model.blockName = ?",params);
	}

}
