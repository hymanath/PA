package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreCommitteeRoleDAO;
import com.itgrids.partyanalyst.model.CadreCommitteeRole;

public class CadreCommitteeRoleDAO extends GenericDaoHibernate<CadreCommitteeRole, Long> implements ICadreCommitteeRoleDAO{

	public CadreCommitteeRoleDAO() 
	{
		super(CadreCommitteeRole.class);
	}

}
