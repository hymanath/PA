package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.model.Panchayat;

public class PanchayatDAO extends GenericDaoHibernate<Panchayat,Long> implements IPanchayatDAO{

	public PanchayatDAO()
	{
		super(Panchayat.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatsByTehsilId(Long tehsilId)
	{
		return getHibernateTemplate().find("select model.panchayatId, model.panchayatName from Panchayat model where model.tehsil.tehsilId = ?",tehsilId);
	}
	  
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatsByConstituencyId(Long constituencyId)
	{
		return getHibernateTemplate().find("select model.panchayatId, model.panchayatName from Panchayat model where model.tehsil.tehsilId in(select model1.tehsil.tehsilId from DelimitationConstituencyMandal model1 where model1.delimitationConstituency.year =2009 and model1.delimitationConstituency.constituency.constituencyId = ?)",constituencyId);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getPanchayatsBymandalId(Long mandalId)
	{
		return getHibernateTemplate().find("select model.panchayatId, model.panchayatName from Panchayat model where model.tehsil.tehsilId =?",mandalId);	
	}
}
