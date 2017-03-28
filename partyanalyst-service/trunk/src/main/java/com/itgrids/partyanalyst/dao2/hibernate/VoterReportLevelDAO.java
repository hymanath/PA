package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
   
	public VoterReportLevel getReportLevelByType(String type)
	{
		Query queryObj = getSession().createQuery("select model from VoterReportLevel model where model.reportLevel = :type ");
		queryObj.setParameter("type", type);
		return (VoterReportLevel)queryObj.uniqueResult();
		
	}
	
	public String getReportLevelTypeById(Long reportLevelId)
	{
		Query queryObj = getSession().createQuery("select model.reportLevel from VoterReportLevel model where model.voterReportLevelId = :voterReportLevelId");
		queryObj.setParameter("voterReportLevelId", reportLevelId);
		return (String) queryObj.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<VoterReportLevel> getVoterReportLevelList()
	{
		return getHibernateTemplate().find(" from VoterReportLevel model ");
	}
	
 	
}
