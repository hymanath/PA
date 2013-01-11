package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.model.VoterReportLevel;

public class VoterReportLevelDAO extends GenericDaoHibernate<VoterReportLevel, Long> implements IVoterReportLevelDAO{
	public VoterReportLevelDAO()
	{
		super(VoterReportLevel.class);
	}
	
	

}
