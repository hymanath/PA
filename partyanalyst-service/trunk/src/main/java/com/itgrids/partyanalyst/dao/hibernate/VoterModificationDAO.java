package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.model.VoterModification;

public class VoterModificationDAO extends GenericDaoHibernate<VoterModification,Long> implements IVoterModificationDAO{
	
	public VoterModificationDAO()
	{
		super(VoterModification.class);
	}
}
