package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileBrandDAO;
import com.itgrids.partyanalyst.model.MobileBrand;

public class MobileBrandDAO extends GenericDaoHibernate<MobileBrand, Long> implements IMobileBrandDAO{

	public MobileBrandDAO() {
		super(MobileBrand.class);
		
	}
}
