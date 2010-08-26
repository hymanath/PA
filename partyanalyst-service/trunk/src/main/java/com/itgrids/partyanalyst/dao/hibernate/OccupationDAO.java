package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IOccupationDAO;
import com.itgrids.partyanalyst.model.Occupation;

public class OccupationDAO extends GenericDaoHibernate<Occupation, Long> implements IOccupationDAO  {

	public OccupationDAO() {
		super(Occupation.class);
		
	}

}
