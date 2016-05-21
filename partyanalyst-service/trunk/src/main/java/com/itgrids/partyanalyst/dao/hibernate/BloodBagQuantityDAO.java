package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBloodBagQuantityDAO;
import com.itgrids.partyanalyst.model.BloodBagQuantity;

public class BloodBagQuantityDAO extends GenericDaoHibernate<BloodBagQuantity, Long> implements IBloodBagQuantityDAO{

	public BloodBagQuantityDAO() {
		super(BloodBagQuantity.class);
	}

}
