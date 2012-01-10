package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.model.PanchayatHamlet;

public class PanchayatHamletDAO extends GenericDaoHibernate<PanchayatHamlet,Long> implements IPanchayatHamletDAO{

	public PanchayatHamletDAO()
	{
		super(PanchayatHamlet.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getHamletsOfAPanchayat(Long panchayatId)
	{
		return getHibernateTemplate().find("select model.hamlet.hamletId,model.hamlet.hamletName from PanchayatHamlet model where model.panchayat.panchayatId = ? order by model.hamlet.hamletName",panchayatId);
	}
}
