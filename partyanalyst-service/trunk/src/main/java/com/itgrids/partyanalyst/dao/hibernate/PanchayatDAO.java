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
}
