package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreCommitteeLevelDAO;
import com.itgrids.partyanalyst.model.CadreCommitteeLevel;

public class CadreCommitteeLevelDAO extends GenericDaoHibernate<CadreCommitteeLevel, Long> implements ICadreCommitteeLevelDAO{

	public CadreCommitteeLevelDAO()
	{
		super(CadreCommitteeLevel.class);
	}

}
