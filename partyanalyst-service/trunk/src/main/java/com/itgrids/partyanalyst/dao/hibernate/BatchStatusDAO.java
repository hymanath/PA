package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBatchStatusDAO;
import com.itgrids.partyanalyst.model.BatchStatus;

public class BatchStatusDAO extends GenericDaoHibernate<BatchStatus, Long> implements IBatchStatusDAO{

	public BatchStatusDAO() {
		super(BatchStatus.class);
	}

}
