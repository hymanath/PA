package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDelimitationVillageDAO;
import com.itgrids.partyanalyst.model.DelimitationVillage;

public class DelimitationVillageDAO extends GenericDaoHibernate<DelimitationVillage, Long> implements IDelimitationVillageDAO{
	
	public DelimitationVillageDAO()
	{
		super(DelimitationVillage.class);
	}

}
