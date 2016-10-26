package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IParliamentAssemblyDAO;
import com.itgrids.partyanalyst.model.ParliamentAssembly;

public class ParliamentAssemblyDAO extends GenericDaoHibernate<ParliamentAssembly, Long> implements
		IParliamentAssemblyDAO {
	public ParliamentAssemblyDAO() {
		super(ParliamentAssembly.class);
	}
}
