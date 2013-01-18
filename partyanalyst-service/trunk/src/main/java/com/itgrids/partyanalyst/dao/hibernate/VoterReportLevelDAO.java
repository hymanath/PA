package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.model.VoterReportLevel;

public class VoterReportLevelDAO extends GenericDaoHibernate<VoterReportLevel, Long> implements IVoterReportLevelDAO{
	public VoterReportLevelDAO()
	{
		super(VoterReportLevel.class);
	}
	
	public Long getReportLevelIdByType(String type)
	{
		Query queryObj = getSession().createQuery("select model.voterReportLevelId from VoterReportLevel model where model.reportLevel = :type ");
		queryObj.setParameter("type", type);
		return (Long) queryObj.uniqueResult();
		
	}

}
