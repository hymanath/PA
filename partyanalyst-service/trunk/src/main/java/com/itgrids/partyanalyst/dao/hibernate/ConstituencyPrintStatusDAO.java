package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IConstituencyPrintStatusDAO;
import com.itgrids.partyanalyst.model.ConstituencyPrintStatus;

public class ConstituencyPrintStatusDAO extends GenericDaoHibernate<ConstituencyPrintStatus, Long> implements IConstituencyPrintStatusDAO {

	public ConstituencyPrintStatusDAO(){
		super(ConstituencyPrintStatus.class);
	}

}
