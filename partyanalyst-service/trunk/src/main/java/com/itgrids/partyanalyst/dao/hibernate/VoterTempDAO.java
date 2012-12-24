package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterTempDAO;
import com.itgrids.partyanalyst.model.VoterTemp;

public class VoterTempDAO extends GenericDaoHibernate<VoterTemp,Long> implements IVoterTempDAO{

	public VoterTempDAO()
	{
		super(VoterTemp.class);
	}
}
