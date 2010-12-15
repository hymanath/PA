package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICensusYearDAO;
import com.itgrids.partyanalyst.model.CensusYear;

public class CensusYearDAO extends GenericDaoHibernate<CensusYear,Long> implements ICensusYearDAO{

	public CensusYearDAO() {
		super(CensusYear.class);
		
	}
}
