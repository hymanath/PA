package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICampCallStatusDAO;
import com.itgrids.partyanalyst.model.CampCallStatus;

public class CampCallStatusDAO extends GenericDaoHibernate<CampCallStatus, Long> implements ICampCallStatusDAO{

	public CampCallStatusDAO() {
		super(CampCallStatus.class);
		// TODO Auto-generated constructor stub
	}

}
