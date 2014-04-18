package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterNamesDAO;
import com.itgrids.partyanalyst.dao.IVoterNamesTempDAO;
import com.itgrids.partyanalyst.model.VoterNamesTemp;

public class VoterNamesTempDAO extends GenericDaoHibernate<VoterNamesTemp, Long> implements IVoterNamesTempDAO{

	public VoterNamesTempDAO() {
		super(VoterNamesTemp.class);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<VoterNamesTemp> getVotersByACNO(Integer startIndex,Integer maxIndex,Long constituencyId)
	{
		Query query = getSession().createQuery("select model from VoterNamesTemp model where model.constituency.constituencyId =:constituencyId");
		query.setParameter("constituencyId" ,constituencyId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
		
	}
	
	public Long getVotersCountACNO(Long constituencyId)
	{
		Query query = getSession().createQuery("select count(*) from VoterNamesTemp model where model.constituency.constituencyId =:constituencyId");
		query.setParameter("constituencyId" ,constituencyId);
		
		return (Long) query.uniqueResult();
		
	}
	public List<Object[]> getConstituencies()
	{
		return getHibernateTemplate().find("select distinct model.constituency.constituencyId,model.constituency.name from VoterNamesTemp model");
	}
	
	

}
