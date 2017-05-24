package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ISpecialGrievanceTypeDAO;
import com.itgrids.partyanalyst.model.SpecialGrievanceType;

public class SpecialGrievanceTypeDAO extends GenericDaoHibernate<SpecialGrievanceType, Long> implements ISpecialGrievanceTypeDAO{

	public SpecialGrievanceTypeDAO() {
		super(SpecialGrievanceType.class);
		
	}

}
