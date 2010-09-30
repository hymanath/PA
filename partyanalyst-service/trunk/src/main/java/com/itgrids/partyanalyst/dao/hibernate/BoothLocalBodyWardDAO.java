package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBoothLocalBodyWardDAO;
import com.itgrids.partyanalyst.model.BoothLocalBodyWard;

public class BoothLocalBodyWardDAO extends GenericDaoHibernate<BoothLocalBodyWard, Long> implements IBoothLocalBodyWardDAO{

	public BoothLocalBodyWardDAO(){
		super(BoothLocalBodyWard.class);
	}
	
}
