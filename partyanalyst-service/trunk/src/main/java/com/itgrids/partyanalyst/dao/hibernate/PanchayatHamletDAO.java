package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.model.PanchayatHamlet;

public class PanchayatHamletDAO extends GenericDaoHibernate<PanchayatHamlet,Long> implements IPanchayatHamletDAO{

	public PanchayatHamletDAO()
	{
		super(PanchayatHamlet.class);
	}
}
