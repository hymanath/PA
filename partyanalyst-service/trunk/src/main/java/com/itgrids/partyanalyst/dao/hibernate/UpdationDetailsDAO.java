package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IUpdationDetailsDAO;
import com.itgrids.partyanalyst.model.UpdationDetails;

public class UpdationDetailsDAO extends GenericDaoHibernate<UpdationDetails, Long> implements
IUpdationDetailsDAO {

	public UpdationDetailsDAO() {
		super(UpdationDetails.class);
		// TODO Auto-generated constructor stub
	}

}
