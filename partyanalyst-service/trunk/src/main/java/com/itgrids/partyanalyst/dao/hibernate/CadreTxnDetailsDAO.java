package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICadreTxnDetailsDAO;
import com.itgrids.partyanalyst.model.CadreTxnDetails;

public class CadreTxnDetailsDAO extends GenericDaoHibernate<CadreTxnDetails, Long> implements ICadreTxnDetailsDAO{

	public CadreTxnDetailsDAO() {
		super(CadreTxnDetails.class);
	}

}
