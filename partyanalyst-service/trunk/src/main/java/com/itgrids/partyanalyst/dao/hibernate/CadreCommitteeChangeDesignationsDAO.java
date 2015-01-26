package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreCommitteeChangeDesignationsDAO;
import com.itgrids.partyanalyst.model.CadreCommitteeChangeDesignations;
import com.itgrids.partyanalyst.model.CadreCommitteeIncreasedPositions;

public class CadreCommitteeChangeDesignationsDAO extends GenericDaoHibernate<CadreCommitteeChangeDesignations, Long>  implements ICadreCommitteeChangeDesignationsDAO
{
	public CadreCommitteeChangeDesignationsDAO() {
		super(CadreCommitteeChangeDesignations.class);
	}
}
