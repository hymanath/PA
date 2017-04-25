package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUrbanBlockDAO;
import com.itgrids.partyanalyst.model.UrbanBlock;

public class UrbanBlockDAO extends GenericDaoHibernate<UrbanBlock, Long> implements IUrbanBlockDAO{

	public UrbanBlockDAO() {
		super(UrbanBlock.class);
		
	}

}
