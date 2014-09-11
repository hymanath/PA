package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMobileSourceTypeDAO;
import com.itgrids.partyanalyst.model.MobileSourceType;

public class MobileSourceTypeDAO extends GenericDaoHibernate<MobileSourceType, Long> implements IMobileSourceTypeDAO {

	public MobileSourceTypeDAO() {
		super(MobileSourceType.class);
	}


}
