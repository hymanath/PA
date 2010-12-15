package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IBlockDAO;

public class BlockDAOHibernateTest extends BaseDaoTestCase{
	
	private IBlockDAO blockDAO;

	public void setBlockDAO(IBlockDAO blockDAO) {
		this.blockDAO = blockDAO;
	}
	public void test(){
		blockDAO.getAll();		
	}

}
