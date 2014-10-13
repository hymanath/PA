package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreCommitteeDAO;
import com.itgrids.partyanalyst.model.CadreCommittee;

public class CadreCommitteeDAO extends GenericDaoHibernate<CadreCommittee, Long> implements ICadreCommitteeDAO{

	public CadreCommitteeDAO() 
	{
		super(CadreCommittee.class);
	}

}
