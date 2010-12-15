package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationBlockDAO;
import com.itgrids.partyanalyst.model.DelimitationBlock;

public class DelimitationBlockDAO extends GenericDaoHibernate<DelimitationBlock, Long> implements IDelimitationBlockDAO{

	public DelimitationBlockDAO(){
		super(DelimitationBlock.class);
	}
}
