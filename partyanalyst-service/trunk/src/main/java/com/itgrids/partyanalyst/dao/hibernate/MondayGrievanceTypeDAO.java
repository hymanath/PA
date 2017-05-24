package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMondayGrievanceTypeDAO;
import com.itgrids.partyanalyst.model.MondayGrievanceType;

public class MondayGrievanceTypeDAO extends GenericDaoHibernate<MondayGrievanceType, Long> implements IMondayGrievanceTypeDAO{

	public MondayGrievanceTypeDAO() {
		super(MondayGrievanceType.class);
		
	}

}
