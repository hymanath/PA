package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreCommitteeIncreasedPositionsDAO;
import com.itgrids.partyanalyst.model.CadreCommitteeIncreasedPositions;

public class CadreCommitteeIncreasedPositionsDAO extends GenericDaoHibernate<CadreCommitteeIncreasedPositions, Long>  implements ICadreCommitteeIncreasedPositionsDAO
{
	public CadreCommitteeIncreasedPositionsDAO() {
		super(CadreCommitteeIncreasedPositions.class);
	}
}
