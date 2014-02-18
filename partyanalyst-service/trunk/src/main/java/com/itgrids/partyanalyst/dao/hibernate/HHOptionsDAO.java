package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHHOptionsDAO;
import com.itgrids.partyanalyst.model.HHOptions;


public class HHOptionsDAO extends GenericDaoHibernate<HHOptions,Long> implements IHHOptionsDAO {
	
	public HHOptionsDAO() {
		super(HHOptions.class);
	}
	
	
}
