package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.model.Occupation;

public class OccupationDAO extends GenericDaoHibernate<Occupation, Long> implements IOccupationDAO  {

	public OccupationDAO() {
		super(Occupation.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<Occupation> getOccupationDetailsByOccupationType(String occupationType) {
		
		return getHibernateTemplate().find("from Occupation model where model.occupation = ?",occupationType);
	}
	@SuppressWarnings("unchecked")
	public List<Occupation> getOccupationList()
	{
		return getHibernateTemplate().find(" from Occupation model ");
	}

}
