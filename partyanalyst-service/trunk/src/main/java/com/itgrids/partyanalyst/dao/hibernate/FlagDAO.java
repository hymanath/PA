package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;



import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IFlagDAO;
import com.itgrids.partyanalyst.model.Flag;

public class FlagDAO extends GenericDaoHibernate<Flag,Long> implements IFlagDAO
{

	public FlagDAO() {
		super(Flag.class);
		
	}
	
	
	public List<Object> checkFlagName(String flagName)
	{
		return getHibernateTemplate().find("select model.flagId from Flag model where model.name = ?",flagName);
	}
	public List<Flag> getAllFlags()
	{
		return getHibernateTemplate().find("select model from Flag model order by model.flagId desc");
	}
	
	public Integer deleteFlag(Long flagId)
	{
		Query query = getSession().createQuery("delete from Flag model where model.flagId = :flagId");
		query.setParameter("flagId", flagId);
		return query.executeUpdate();
	}
	public List<Object[]> getAllFlagsList()
	{
		return getHibernateTemplate().find("select model.flagId,model.name from Flag model order by model.flagId desc");
	}

}
