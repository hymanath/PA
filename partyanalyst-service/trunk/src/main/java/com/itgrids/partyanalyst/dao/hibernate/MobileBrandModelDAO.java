package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileBrandModelDAO;
import com.itgrids.partyanalyst.model.MobileBrandModel;

public class MobileBrandModelDAO extends GenericDaoHibernate<MobileBrandModel, Long> implements IMobileBrandModelDAO{

	public MobileBrandModelDAO() {
		super(MobileBrandModel.class);
		
	}
}
