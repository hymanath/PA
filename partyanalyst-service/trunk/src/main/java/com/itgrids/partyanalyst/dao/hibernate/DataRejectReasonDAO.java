package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDataRejectReasonDAO;
import com.itgrids.partyanalyst.model.DataRejectReason;

public class DataRejectReasonDAO extends GenericDaoHibernate<DataRejectReason, Long> implements IDataRejectReasonDAO{

	public DataRejectReasonDAO() {
		super(DataRejectReason.class);
		
	}

}
