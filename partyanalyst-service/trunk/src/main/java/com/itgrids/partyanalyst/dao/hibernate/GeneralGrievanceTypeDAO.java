package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.GeneralGrievanceType;

import com.itgrids.partyanalyst.dao.IGeneralGrievanceTypeDAO;

public class GeneralGrievanceTypeDAO extends GenericDaoHibernate<GeneralGrievanceType, Long> implements IGeneralGrievanceTypeDAO{

	public GeneralGrievanceTypeDAO() {
		super(GeneralGrievanceType.class);
		
	}

}
