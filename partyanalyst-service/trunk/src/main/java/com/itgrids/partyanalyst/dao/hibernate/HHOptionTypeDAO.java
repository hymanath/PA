package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHHOptionTypeDAO;
import com.itgrids.partyanalyst.model.HHOptionType;


public class HHOptionTypeDAO extends GenericDaoHibernate<HHOptionType,Long> implements IHHOptionTypeDAO {
	
	public HHOptionTypeDAO() {
		super(HHOptionType.class);
	}
	
	
}
