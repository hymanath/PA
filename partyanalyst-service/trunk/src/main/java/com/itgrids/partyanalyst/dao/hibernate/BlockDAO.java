package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBlockDAO;
import com.itgrids.partyanalyst.model.Block;

public class BlockDAO extends GenericDaoHibernate<Block ,Long> implements IBlockDAO{
	
	public BlockDAO(){
		super(Block.class);
	}

}
