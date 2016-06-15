package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileNumberDetailsDAO;
import com.itgrids.partyanalyst.model.MobileNumberDetails;

public class MobileNumberDetailsDAO extends GenericDaoHibernate<MobileNumberDetails, Long> implements IMobileNumberDetailsDAO{

	public MobileNumberDetailsDAO() {
		super(MobileNumberDetails.class);
		// TODO Auto-generated constructor stub
	}
	
}
