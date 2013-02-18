package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterModificationTempDAO;
import com.itgrids.partyanalyst.model.VoterModificationTemp;

public class VoterModificationTempDAO extends GenericDaoHibernate<VoterModificationTemp,Long> implements IVoterModificationTempDAO{
	
	public VoterModificationTempDAO()
	{
		super(VoterModificationTemp.class);
	}

}
